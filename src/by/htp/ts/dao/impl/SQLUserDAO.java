package by.htp.ts.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ts.bean.User;
import by.htp.ts.dao.DAOException;
import by.htp.ts.dao.UserDAO;
import by.htp.ts.dao.connection_pool.ConnectionPool;
import by.htp.ts.dao.connection_pool.ConnectionPoolException;

public class SQLUserDAO implements UserDAO {
	private static Lock lock; // = new ReentrantLock()
	private static final Logger log = LogManager.getLogger(SQLTreatmentDAO.class);

	private final static String SELECT_AUTHORIZATION = "SELECT * FROM user WHERE login = ? AND password = ?";
	private final static String SELECT_ID = "SELECT * FROM user WHERE id = ?";
	private final static String SELECT_INFO = "SELECT * FROM user_info WHERE id = ?";
	private final static String SELECT_ROLE = "SELECT * FROM roles WHERE id = ?";
	private final static String SELECT_ROLE_ID = "SELECT * FROM roles WHERE role = ?";
	private final static String INSERT_USER_INFO = "INSERT INTO user_info (name, surname, email) VALUES (?,?,?);";
	private final static String SELECT_USER_INFO_ID = "SELECT * FROM user_info WHERE name = ? AND surname = ? AND email = ?;";
	private final static String INSERT_USER = "INSERT INTO user (login, password, `roles_id`, `user_info_id`) VALUES (?,?,?,?);";

	@SuppressWarnings("resource")
	@Override
	public User authorization(String login, String password) throws DAOException, InterruptedException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;
		int userId;
		int userRolesId;
		int userInfoId;

		try {
			con = ConnectionPool.getInstance().takeConnection();
			pst = con.prepareStatement(SELECT_AUTHORIZATION);
			pst.setString(1, login);
			pst.setString(2, password);
			rs = pst.executeQuery();

			if (rs.next()) {
				userId = rs.getInt("id");
				userRolesId = rs.getInt("roles_id");
				userInfoId = rs.getInt("user_info_id");
				user = new User();
				user.setId(userId);
			} else {
				log.error("Such user does not exist");
				throw new SQLException();
			}

			pst = con.prepareStatement(SELECT_INFO);
			pst.setInt(1, userInfoId);
			rs = pst.executeQuery();
			if (rs.next()) {
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setEmail(rs.getString("email"));
			} else {
				log.error("Info for this user does not exist");
				throw new SQLException();
			}

			pst = con.prepareStatement(SELECT_ROLE);
			pst.setInt(1, userRolesId);
			rs = pst.executeQuery();
			if (rs.next()) {
				user.setRole(rs.getString("role"));
			} else {
				log.error("Role for this user does not exist");
				throw new SQLException();
			}

		} catch (SQLException e) {
			throw new DAOException(e);

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return user;
	}

	
	@SuppressWarnings("resource")
	@Override
	public User registration(User user) throws DAOException, InterruptedException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {

			con = ConnectionPool.getInstance().takeConnection();
			con.setAutoCommit(false);
			int userId;
			int userRolesId;
			int userInfoId;
			
			lock = new ReentrantLock();
			lock.lock();
			
			pst = con.prepareStatement(SELECT_ROLE_ID);
			pst.setString(1, user.getRole());
			rs = pst.executeQuery();
			if (rs.next()) {
				userRolesId = rs.getInt("id");
			}else {
				// Change exception to something
				String message = "Specified role does not exist";
				throw new SQLException(message);
			}
			
			pst = con.prepareStatement(INSERT_USER_INFO);
			pst.setString(1, user.getName());
			pst.setString(2, user.getSurname());
			pst.setString(3, user.getEmail());
			pst.executeUpdate();
			pst = con.prepareStatement(SELECT_USER_INFO_ID);
			pst.setString(1, user.getName());
			pst.setString(2, user.getSurname());
			pst.setString(3, user.getEmail());
			rs = pst.executeQuery();
			if (rs.next()) {
				rs.last();
				userInfoId = rs.getInt(1);
			}else {
				// temporary exception
				String message = "Specified info does not exist";
				throw new SQLException(message);
			}
			
			pst = con.prepareStatement(INSERT_USER);
			pst.setString(1, user.getLogin());
			pst.setString(2, user.getPassword());
			pst.setInt(3, userRolesId);
			pst.setInt(4, userInfoId);
			pst.executeUpdate();
			pst = con.prepareStatement(SELECT_AUTHORIZATION);
			pst.setString(1, user.getLogin());
			pst.setString(2, user.getPassword());
			rs = pst.executeQuery();
			if (rs.next()) {
				userId = rs.getInt("id");
			}else {
				// temporary exception
				String message = "Error while retrieving user id";
				throw new SQLException(message);
			}
			user.setId(userId);
			con.commit();
			return user;

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e);
			}
			throw new DAOException(e);

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);

		} finally {
			lock.unlock();
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public User getUserById(int id) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;
		int userRolesId;
		int userInfoId;

		try {
			con = ConnectionPool.getInstance().takeConnection();
			pst = con.prepareStatement(SELECT_ID);
			pst.setString(1, id+"");
			rs = pst.executeQuery();

			if (rs.next()) {
				userRolesId = rs.getInt("roles_id");
				userInfoId = rs.getInt("user_info_id");
				user = new User();
				user.setId(id);
			} else {
				log.error("Such user does not exist");
				throw new SQLException();
			}

			pst = con.prepareStatement(SELECT_INFO);
			pst.setInt(1, userInfoId);
			rs = pst.executeQuery();
			if (rs.next()) {
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setEmail(rs.getString("email"));
			} else {
				log.error("Info for this user does not exist");
				throw new SQLException();
			}

			pst = con.prepareStatement(SELECT_ROLE);
			pst.setInt(1, userRolesId);
			rs = pst.executeQuery();
			if (rs.next()) {
				user.setRole(rs.getString("role"));
			} else {
				log.error("Role for this user does not exist");
				throw new SQLException();
			}

		} catch (SQLException e) {
			throw new DAOException(e);

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return user;
	}

}

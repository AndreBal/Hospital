package by.htp.ts.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import by.htp.ts.bean.Treatment;
import by.htp.ts.bean.User;
import by.htp.ts.dao.DAOException;
import by.htp.ts.dao.DAOFactory;
import by.htp.ts.dao.TreatmentDAO;
import by.htp.ts.dao.UserDAO;
import by.htp.ts.dao.connection_pool.ConnectionPool;
import by.htp.ts.dao.connection_pool.ConnectionPoolException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SQLTreatmentDAO implements TreatmentDAO {

	private static Lock lock;
	private static final Logger log = LogManager.getLogger(SQLTreatmentDAO.class);

	private final static String SELECT_TREATMENT_BY_HISTORY_NUMBER = "SELECT * FROM hospital.treatment LEFT JOIN hospital.performed_treatment ON hospital.treatment.id = hospital.performed_treatment.treatment_id WHERE history_number = ?";
	private final static String SELECT_TREATMENT_BY_TREATMENT_ID = "SELECT * FROM hospital.treatment WHERE id = ?";
	private final static String DELETE_TREATMENT_BY_TREATMENT_ID = "DELETE FROM hospital.treatment WHERE id = ?";
	private final static String INSERT_TREATMENT = "INSERT INTO `hospital`.`treatment` ( `type`, `completion_status`, `description`, `appointer_id`, `history_number`) VALUES ( ?, ?, ?, ?, ?);";
	private final static String UPDATE_TREATMENT = "UPDATE `hospital`.`treatment` SET `type` = ?, `description` = ? WHERE (`id` = ?);";
	private final static String INSERT_TREATMENT_PERFROM = "INSERT INTO `hospital`.`performed_treatment` (`performed_date`, `performer_id`, `treatment_id`) VALUES (?, ?, ?);";
	private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Override
	public List<Treatment> getHistoryTreatment(int number) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Treatment> treatments = new ArrayList<Treatment>();

		try {
			con = ConnectionPool.getInstance().takeConnection();
			pst = con.prepareStatement(SELECT_TREATMENT_BY_HISTORY_NUMBER);
			pst.setLong(1, number);
			rs = pst.executeQuery();

			parseTreatment(treatments, rs);

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

		return treatments;
	}

	private void parseTreatment(List<Treatment> treatments, ResultSet rs) throws SQLException, DAOException {
		while (rs.next()) {
			Treatment treatment = new Treatment();
			treatment.setId(rs.getInt("id"));
			treatment.setType(rs.getString("type"));
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			treatment.setAppointer(userDAO.getUserById(rs.getInt("appointer_id")));
			boolean completedStatus = rs.getBoolean("completion_status");
			treatment.setCompleted(completedStatus);

			if (completedStatus) {
				treatment.setPerformedDate(rs.getDate("performed_date"));
				treatment.setPerformer(userDAO.getUserById(rs.getInt("performer_id")));
			}
			treatment.setDescription(rs.getString("description"));

			treatments.add(treatment);
		}
	}

	@Override
	public void deleteUnperformedTreatment(int id) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getInstance().takeConnection();
			pst = con.prepareStatement(SELECT_TREATMENT_BY_TREATMENT_ID);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if (rs.getBoolean("completion_status")) {
				log.error("Cannot delete already performed treatment");
				throw new DAOException("Cannot delete already performed treatment");
			} else {
				lock = new ReentrantLock();
				lock.lock();
				pst = con.prepareStatement(DELETE_TREATMENT_BY_TREATMENT_ID);
				pst.setLong(1, id);
				pst.executeUpdate();
			}

		} catch (SQLException e) {
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
	public void addTreatment(Treatment treatment, int historyNumber) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {

			con = ConnectionPool.getInstance().takeConnection();
			con.setAutoCommit(false);

			String type = treatment.getType();
			boolean completionStatus = treatment.isCompleted();
			String description = treatment.getDescription();
			int appointerId = treatment.getAppointer().getId();

			lock = new ReentrantLock();
			lock.lock();

			pst = con.prepareStatement(INSERT_TREATMENT);
			pst.setString(1, type);
			pst.setBoolean(2, completionStatus);
			pst.setString(3, description);
			pst.setInt(4, appointerId);
			pst.setInt(5, historyNumber);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			log.error("Exeption during inserting treatment");
			throw new DAOException("Exeption during inserting treatment", e);

		} catch (ConnectionPoolException e) {
			log.error("Connection pool exception");
			throw new DAOException("Exception during connection to database", e);
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
				log.error("Exception during resourse closing");
				throw new DAOException("Exception during resourse closing", e);
			}
		}

	}

	public void modifyTreatment(Treatment treatment) throws DAOException{
		Connection con = null;
		PreparedStatement pst = null;
		try {

			con = ConnectionPool.getInstance().takeConnection();
			con.setAutoCommit(false);

			int id = treatment.getId();
			String type = treatment.getType();
			String description = treatment.getDescription();

			lock = new ReentrantLock();
			lock.lock();

			pst = con.prepareStatement(UPDATE_TREATMENT);
			pst.setString(1, type);
			pst.setString(2, description);
			pst.setInt(3, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			log.error("Such treatment does not exist");
			throw new DAOException("Such treatment does not exist", e);

		} catch (ConnectionPoolException e) {
			log.error("Connection pool exception");
			throw new DAOException("Exception during connection to database", e);
		} finally {
			lock.unlock();
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				log.error("Exception during resourse closing");
				throw new DAOException("Exception during resourse closing", e);
			}
		}
	}

	public void performTreatment(int performerId, int treatmentId)throws DAOException{
		Connection con = null;
		PreparedStatement pst = null;
		try {

			con = ConnectionPool.getInstance().takeConnection();
			con.setAutoCommit(false);

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			String date =  sdf.format(cal.getTime());

			lock = new ReentrantLock();
			lock.lock();

			pst = con.prepareStatement(INSERT_TREATMENT_PERFROM);
			pst.setString(1, date);
			pst.setInt(2, performerId);
			pst.setInt(3, treatmentId);
			pst.executeUpdate();
		} catch (SQLException e) {
			log.error("Exeption during perform insert to database");
			throw new DAOException("Exeption during perform insert to database", e);

		} catch (ConnectionPoolException e) {
			log.error("Connection pool exception");
			throw new DAOException("Exception during connection to database", e);
		} finally {
			lock.unlock();
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				log.error("Exception during resourse closing");
				throw new DAOException("Exception during resourse closing", e);
			}
		}
		
	}
}

package by.htp.ts.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ts.bean.History;
import by.htp.ts.bean.User;
import by.htp.ts.dao.DAOException;
import by.htp.ts.dao.HistoryDAO;
import by.htp.ts.dao.connection_pool.ConnectionPool;
import by.htp.ts.dao.connection_pool.ConnectionPoolException;

public class SQLHistoryDAO implements HistoryDAO {

	private static final Logger log = LogManager.getLogger(SQLTreatmentDAO.class);

	private final static String SELECT_ID = "SELECT * FROM medical_history WHERE number = ?";

	public History getHistoryByNumber(int id) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		History history = new History();
		String provisionalDiagnosis;
		String currentDiagnosis;
		String finalDiagnosis;
		String status;

		try {
			con = ConnectionPool.getInstance().takeConnection();
			pst = con.prepareStatement(SELECT_ID);
			pst.setString(1, id + "");
			rs = pst.executeQuery();

			if (rs.next()) {
				provisionalDiagnosis = rs.getString("provisional_diagnosis");
				currentDiagnosis = rs.getString("diagnosis");
				finalDiagnosis = rs.getString("final_diagnosis");
				status = rs.getString("status");
				history = new History();
				history.setNumber(id);
				history.setProvisionalDiagnosis(provisionalDiagnosis);
				history.setCurrentDiagnosis(currentDiagnosis);
				history.setFinalDiagnosis(finalDiagnosis);
				history.setStatus(status);
			} else {
				log.error("Such history does not exist");
				throw new SQLException("Such history does not exist");
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
		
		return history;
	}

}

package by.htp.ts.dao;

import by.htp.ts.dao.impl.SQLHistoryDAO;
import by.htp.ts.dao.impl.SQLTreatmentDAO;
import by.htp.ts.dao.impl.SQLUserDAO;

public class DAOFactory {

	private static final DAOFactory INSTANCE = new DAOFactory();

	private final UserDAO sqlUserImpl = new SQLUserDAO();
	private final TreatmentDAO sqlTreatmentImpl = new SQLTreatmentDAO();
	private final HistoryDAO sqlHistoryImpl = new SQLHistoryDAO();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return INSTANCE;
	}

	public UserDAO getUserDAO() {
		return sqlUserImpl;
	}
	
	public TreatmentDAO getTreatmentDAO() {
		return sqlTreatmentImpl;
	}
	
	public HistoryDAO getHistoryDAO() {
		return sqlHistoryImpl;
	}
}

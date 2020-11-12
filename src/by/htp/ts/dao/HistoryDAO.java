package by.htp.ts.dao;

import by.htp.ts.bean.History;
import by.htp.ts.bean.User;

public interface HistoryDAO {
	
	History getHistoryByNumber(int id) throws DAOException;

}

package by.htp.ts.service;

import by.htp.ts.bean.Treatment;
import by.htp.ts.dao.DAOException;

public interface TreatmentService {

	void addTreatment(Treatment treatment, int historyNumber) throws ServiceException;
	
}

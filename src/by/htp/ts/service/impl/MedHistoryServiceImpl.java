package by.htp.ts.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.ts.bean.History;
import by.htp.ts.bean.Treatment;
import by.htp.ts.dao.DAOException;
import by.htp.ts.dao.DAOFactory;
import by.htp.ts.dao.HistoryDAO;
import by.htp.ts.dao.TreatmentDAO;
import by.htp.ts.service.MedHistoryService;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.Validator;

public class MedHistoryServiceImpl implements MedHistoryService {

	@Override
	public History getHistory(String hisNumber) throws ServiceException {
		if (!Validator.validate("hisNumber", hisNumber)) {
			throw new ServiceException("History number was input incorrectly");
		}
		int number = Integer.parseInt(hisNumber);
		History history;

		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		HistoryDAO historyDao = daoObjectFactory.getHistoryDAO();
		try {
			history = historyDao.getHistoryByNumber(number);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}

		return history;
	}

	@Override
	public List<Treatment> getTreatment(String hisNumber) throws ServiceException {
		if (!Validator.validate("hisNumber", hisNumber)) {
			throw new ServiceException("History number was input incorrectly");
		}
		int number = Integer.parseInt(hisNumber);
		List<Treatment> treatmentList = new ArrayList<Treatment>();
		
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		TreatmentDAO treatmentDao = daoObjectFactory.getTreatmentDAO();
		
		try {
			treatmentList = treatmentDao.getHistoryTreatment(number);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		
		return treatmentList;
	}

}

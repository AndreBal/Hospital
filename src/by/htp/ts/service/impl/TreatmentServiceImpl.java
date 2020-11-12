package by.htp.ts.service.impl;

import by.htp.ts.bean.Treatment;
import by.htp.ts.dao.DAOException;
import by.htp.ts.dao.DAOFactory;
import by.htp.ts.dao.TreatmentDAO;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.TreatmentService;

public class TreatmentServiceImpl implements TreatmentService {

	@Override
	public void addTreatment(Treatment treatment, int historyNumber) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		TreatmentDAO treatmentDao = daoObjectFactory.getTreatmentDAO();
		try {
			treatmentDao.addTreatment(treatment,historyNumber);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		
	}

}

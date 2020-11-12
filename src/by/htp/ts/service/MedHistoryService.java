package by.htp.ts.service;

import java.util.List;

import by.htp.ts.bean.History;
import by.htp.ts.bean.Treatment;

public interface MedHistoryService {
	
	History getHistory(String hisNumber) throws ServiceException;
	
	List<Treatment> getTreatment(String hisNumber) throws ServiceException;

}

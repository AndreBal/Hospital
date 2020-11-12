package by.htp.ts.dao;

import java.util.List;

import by.htp.ts.bean.Treatment;

public interface TreatmentDAO {

	List<Treatment> getHistoryTreatment(int number) throws DAOException;
	
	void deleteUnperformedTreatment(int id) throws DAOException;
	
	void addTreatment(Treatment treatment, int historyNumber) throws DAOException;
	
	public void modifyTreatment(Treatment treatment) throws DAOException;
	
	public void performTreatment(int performerId, int treatmentId)throws DAOException;
	
}

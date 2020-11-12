package by.htp.ts.service;

import by.htp.ts.service.impl.MedHistoryServiceImpl;
import by.htp.ts.service.impl.TreatmentServiceImpl;
import by.htp.ts.service.impl.UserServiceImpl;

public class ServiceProvider {
private static final ServiceProvider instance = new ServiceProvider();
	
	private final UserService userService = new UserServiceImpl();
	private final MedHistoryService medHisService = new MedHistoryServiceImpl();
	private final TreatmentService treatmentService = new TreatmentServiceImpl();
	
	private ServiceProvider() {}
	
	public static ServiceProvider getInstance() {
		return instance;
	}
	
	public UserService getUserService() {
		return userService;
	}
	
	public MedHistoryService getMedicalHistoryService() {
		return medHisService;
	}
	public TreatmentService getTreatmentService() {
		return treatmentService;
	}
}

package by.htp.ts.service;

public class ServiceException extends Exception{
	
	private static final long serialVersionUID = 3794130299728944278L;
	
	public ServiceException() {
		super();
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message) {
		super(message);
	}

}

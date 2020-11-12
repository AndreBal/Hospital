package by.htp.ts.dao;

public class DAOException extends Exception {
	
	private static final long serialVersionUID = 2209493852968417846L;

	public DAOException(String message, Exception e) {
		super(message , e);
	}
	
	public DAOException(String message) {
		super(message);
	}
	
	public DAOException(Exception e) {
		super(e);
	}
	
	public DAOException() {
		super();
	}
}

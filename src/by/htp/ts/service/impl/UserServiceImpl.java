package by.htp.ts.service.impl;

import by.htp.ts.bean.User;
import by.htp.ts.dao.DAOException;
import by.htp.ts.dao.DAOFactory;
import by.htp.ts.dao.UserDAO;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.UserService;
import by.htp.ts.service.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
	
	private static final Logger log = LogManager.getLogger(UserService.class);

	@Override
	public User authorization(String login, String password) throws ServiceException, InterruptedException {
		if (!Validator.validate("login", login)) {
			throw new ServiceException("Login input incorrect");
		}

		if (!Validator.validate("password", password)) {
			throw new ServiceException("Password input incorrect");
		}
		User user;

		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			user = userDAO.authorization(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return user;
	}

	@Override
	public User registration(User user) throws ServiceException, InterruptedException {
		String name = user.getName();
		String surname = user.getSurname();
		String email = user.getEmail();
		String role = user.getRole();
		String login = user.getLogin();
		String password = user.getPassword();
		if (!Validator.validate("name", name)) {
			log.error("Name input incorrect");
			throw new ServiceException("Name input incorrect");
		}
		if (!Validator.validate("surname", surname)) {
			log.error("Surname input incorrect");
			throw new ServiceException("Surname input incorrect");
		}

		if (!Validator.validate("email", email)) {
			log.error("Email input incorrect");
			throw new ServiceException("Email input incorrect");
		}

		if (!Validator.validate("role", role)) {
			log.error("Role input incorrect");
			throw new ServiceException("Role input incorrect");
		}

		if (!Validator.validate("login", login)) {
			log.error("Login input incorrect");
			throw new ServiceException("Login input incorrect");
		}

		if (!Validator.validate("password", password)) {
			log.error("Password input incorrect");
			throw new ServiceException("Password input incorrect");
		}
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			user = userDAO.registration(user);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return user;
	}

}

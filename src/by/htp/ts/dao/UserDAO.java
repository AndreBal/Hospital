package by.htp.ts.dao;

import by.htp.ts.bean.User;

public interface UserDAO {

	User authorization(String login, String password) throws DAOException, InterruptedException;

	User registration(User user) throws DAOException, InterruptedException;

	User getUserById(int id) throws DAOException;

}

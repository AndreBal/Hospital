package by.htp.ts.service;

import by.htp.ts.bean.User;

public interface UserService {
	User authorization(String login, String password) throws ServiceException, InterruptedException;
	User registration(User user) throws ServiceException, InterruptedException;
}

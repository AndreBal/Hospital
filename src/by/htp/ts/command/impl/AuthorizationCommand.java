package by.htp.ts.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ts.bean.User;
import by.htp.ts.command.Command;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.ServiceProvider;
import by.htp.ts.service.UserService;

public class AuthorizationCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
		String login;
		String password; 
		login = request.getParameter(RequestParameter.LOGIN);
		password = request.getParameter(RequestParameter.PASSWORD);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		
		User user;
		HttpSession session;
		
		try {
			user = userService.authorization(login, password);
			
			if(user == null) {
				response.sendRedirect("Controller?command=go_to_sign_in_page&errorMessage=login or password error");
				return;
			}
			session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("Controller?command=go_to_main_page");
		} catch (ServiceException e) {
			response.sendRedirect("error.jsp?error=" + e.getMessage());
		}	
	}

}

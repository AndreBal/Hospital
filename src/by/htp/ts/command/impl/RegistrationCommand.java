package by.htp.ts.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ts.bean.User;
import by.htp.ts.command.Command;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.ServiceProvider;
import by.htp.ts.service.UserService;

public class RegistrationCommand implements Command {
	
	private static final Logger log = LogManager.getLogger(UserService.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, InterruptedException {
		String login = request.getParameter(RequestParameter.LOGIN);
		String password = request.getParameter(RequestParameter.PASSWORD);
		String name = request.getParameter(RequestParameter.NAME);
		String surname = request.getParameter(RequestParameter.SURNAME);
		String email = request.getParameter(RequestParameter.EMAIL);
		String role = request.getParameter(RequestParameter.ROLE);
		User user = new User();
		user.setName(name);
		user.setSurname(surname);
		user.setRole(role);
		user.setEmail(email);
		user.setLogin(login);
		user.setPassword(password);

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();

		HttpSession session = request.getSession(true);
		
		try {
			user = userService.registration(user);

			session = request.getSession(true);
			session.setAttribute("user", user);
			response.sendRedirect("Controller?command=go_to_main_page");
		} catch (ServiceException e) {
			log.error(e.getMessage());
			response.sendRedirect("error.jsp?error=" + e.getMessage());

		}

	}

}

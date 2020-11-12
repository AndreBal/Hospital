package by.htp.ts.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ts.bean.User;
import by.htp.ts.command.Command;

public class GoToMainPage implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);		
		
		if(session == null) {
			response.sendRedirect("Controller?command=go_to_sign_in_page&errorMessage=session invalidate");
			return;
		}
		
		User user = (User) session.getAttribute("user");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

}

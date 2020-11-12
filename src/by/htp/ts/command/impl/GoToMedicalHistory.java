package by.htp.ts.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ts.command.Command;

public class GoToMedicalHistory implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, InterruptedException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/medicalHistory.jsp");
		dispatcher.forward(request, response);
		
	}

}

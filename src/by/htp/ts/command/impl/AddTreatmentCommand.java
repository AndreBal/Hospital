package by.htp.ts.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ts.bean.Treatment;
import by.htp.ts.command.Command;
import by.htp.ts.dao.DAOException;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.ServiceProvider;
import by.htp.ts.service.TreatmentService;

public class AddTreatmentCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, InterruptedException {
		String description = request.getParameter(RequestParameter.DESCRIPTION);
		String type = request.getParameter(RequestParameter.TYPE);
		Treatment treatment = new Treatment();
		int historyNumber = (int) request.getSession().getAttribute("curentHistoryNumber");
		treatment.setCompleted(false);
		treatment.setType(description);
		treatment.setType(type);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		TreatmentService treatmentService = provider.getTreatmentService();
		
		try {
			treatmentService.addTreatment(treatment, historyNumber);
		} catch (ServiceException e) {
			response.sendRedirect("error.jsp?error=" + e.getMessage());
		}
		
	}

}

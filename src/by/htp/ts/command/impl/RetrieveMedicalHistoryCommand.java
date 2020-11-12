package by.htp.ts.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ts.bean.History;
import by.htp.ts.bean.Treatment;
import by.htp.ts.command.Command;
import by.htp.ts.service.MedHistoryService;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.ServiceProvider;
import by.htp.ts.service.UserService;

public class RetrieveMedicalHistoryCommand implements Command{
	
	private static final Logger log = LogManager.getLogger(UserService.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, InterruptedException {
		
		String number = request.getParameter(RequestParameter.HISTORY_NUMBER);
		
		
		
		ServiceProvider provider = ServiceProvider.getInstance();
		MedHistoryService medHisService = provider.getMedicalHistoryService();
		
		List<Treatment> treatmentList;
		History medicalHistory;
		try {
			treatmentList = medHisService.getTreatment(number);
			medicalHistory = medHisService.getHistory(number);
			
			request.getSession().setAttribute("curentHistoryNumber", number);
			
			request.setAttribute("treatmentList", treatmentList);
			request.setAttribute("history", medicalHistory);
			
			request.getRequestDispatcher("/WEB-INF/jsp/medicalHistory.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			response.sendRedirect("error.jsp?error=" + e.getMessage());
		}
		
		
	}

}

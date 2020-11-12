package by.htp.ts.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ts.command.Command;
import by.htp.ts.command.CommandProvider;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private final static String COMMAND_NAME = "command";
	private final CommandProvider provider = new CommandProvider();

	public Controller() {
		super();
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost( request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Command command;
		String commandName = request.getParameter(COMMAND_NAME);
		command = provider.getCommand(commandName);
		try {
			command.execute(request, response);
		} catch (InterruptedException e) {
			throw new ServletException(e);
		}
	}
	

}

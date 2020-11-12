package by.htp.ts.command;

import java.util.HashMap;
import java.util.Map;

import by.htp.ts.command.impl.AddTreatmentCommand;
import by.htp.ts.command.impl.AuthorizationCommand;
import by.htp.ts.command.impl.GoToMainPage;
import by.htp.ts.command.impl.GoToMedicalHistory;
import by.htp.ts.command.impl.GoToRegistrationPage;
import by.htp.ts.command.impl.GoToSignInPage;
import by.htp.ts.command.impl.LogOut;
import by.htp.ts.command.impl.RegistrationCommand;
import by.htp.ts.command.impl.RetrieveMedicalHistoryCommand;

public final class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.GO_TO_SIGN_IN_PAGE, new GoToSignInPage());
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.RETRIEVE_MEDICAL_HISTORY, new RetrieveMedicalHistoryCommand());
		commands.put(CommandName.GO_TO_MEDICAL_HISTORY, new GoToMedicalHistory());
		commands.put(CommandName.ADD_TREATMENT, new AddTreatmentCommand());
		commands.put(CommandName.LOG_OUT, new LogOut());
	}

	public Command getCommand(String name) {
		CommandName commandName;
		commandName = CommandName.valueOf(name.toUpperCase());
		return commands.get(commandName);
	}
}

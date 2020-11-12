package by.htp.ts.service;

import java.util.regex.Pattern;

public class Validator {
	public static boolean validate(String param, String value) {
		if (value == null || value.isEmpty()) {
			return false;
		}
		String regex;
		switch (param) {
			case ("login"): regex = ".{1,20}";
			break;
			case ("password"): regex = ".{1,20}";
			break;
			case ("name"): regex = ".{1,20}";
			break;
			case ("surname"): regex = ".{1,20}";
			break;
			case ("email"): regex = "^(.+)@(.+)$";
			break;
			case ("role"): regex = ".{1,20}";
			break;
			case ("hisNumber"): regex = "[0-9]+";
			break;
			default: return false;
		}

		if (!Pattern.matches(regex, value)) {
			return false;
		}
		return true;
	}
}

package com.noi.utility.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

	public static boolean isEmailAddressValid(String email) {
		String regex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)";
		return email.matches(regex);
	}

	/**
	 * isPhoneNumberValid: Validate phone number using Java reg ex. This method
	 * checks if the input string is a valid phone number.
	 * 
	 * @param email
	 *            String. Phone number to validate
	 * @return boolean: true if phone number is valid, false otherwise.
	 */
	public static boolean isPhoneNumberValid(String phoneNumber) {
		boolean isValid = false;

		// Initialize reg ex for phone number.
		String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
		CharSequence inputStr = phoneNumber;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}
	
	/**
	 * Password matching expression. Password must be at least 4 characters, 
	 * no more than 10 characters, 
	 * and must include at least one lower case letter, and one numeric digit.
	 * @param password
	 * @return
	 */
	public static boolean isPasswordValid(String password) {
		boolean isValid = false;
		String expression = "^[a-zA-Z0-9](?=.*\\d)(?=.*[a-z])\\w{4,10}$";
		CharSequence inputStr = password;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * 4-10 chars alphanumeric
	 * @param password
	 * @return
	 */
	public static boolean isUsernameValid(String password) {
		boolean isValid = false;
		String expression = "^[a-z0-9]\\w{4,10}$";
		CharSequence inputStr = password;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}
}

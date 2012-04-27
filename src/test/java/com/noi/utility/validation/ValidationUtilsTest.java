package com.noi.utility.validation;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.noi.utility.text.DateUtilsTest;

public class ValidationUtilsTest extends TestCase {

	static Logger logger = Logger.getLogger(DateUtilsTest.class);

	public void testEmailValidation() {
		logger.debug("testEmailValidation");
		String[] items = { "cgraham@gmail.com", "Clay Graham", "clay gera@foo bar.com" };
		boolean[] result = { true, false, false };
		for (int i = 0; i < items.length; i++) {
			assertEquals(result[i], ValidationUtils
					.isEmailAddressValid(items[i]));
		}

	}
	
	public void testPasswordValidation() {
		logger.debug("testPasswordValidation");
		String[] items = { "tattler23", "tattler foo", "3223", "passive", "fo1", "thereshouldbeless" };
		boolean[] result = { true, false, false, false, false,false,false };
		for (int i = 0; i < items.length; i++) {
			assertEquals(result[i], ValidationUtils
					.isPasswordValid(items[i]));
		}

	}
	
	public void testUsernameValidation() {
		logger.debug("testUsernameValidation");
		String[] items = { "tattler23", "tattler foo", "3223", "passive", "fo1", "thereshouldbeless", "NickGarner" };
		boolean[] result = { true, false, false, true, false, false, false };
		for (int i = 0; i < items.length; i++) {
			assertEquals(result[i], ValidationUtils
					.isUsernameValid(items[i]));
		}

	}
}

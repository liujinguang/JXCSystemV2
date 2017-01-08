package com.lzw.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.vt.middleware.password.AlphabeticalCharacterRule;
import edu.vt.middleware.password.CharacterCharacteristicsRule;
import edu.vt.middleware.password.DigitCharacterRule;
import edu.vt.middleware.password.LengthRule;
import edu.vt.middleware.password.LowercaseCharacterRule;
import edu.vt.middleware.password.NonAlphanumericCharacterRule;
import edu.vt.middleware.password.Password;
import edu.vt.middleware.password.PasswordData;
import edu.vt.middleware.password.PasswordValidator;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.RuleResult;
import edu.vt.middleware.password.UppercaseCharacterRule;
import edu.vt.middleware.password.UsernameRule;
import edu.vt.middleware.password.WhitespaceRule;

public class UsernamePasswordValidator {
	private static Logger rootLogger = Logger.getLogger("rootLogger");

	public static boolean validatePassword(String password) {
		List<Rule> ruleList = new ArrayList<Rule>();
		ruleList.add(new LengthRule(6, 16));
		ruleList.add(new WhitespaceRule());
		CharacterCharacteristicsRule charRule = new CharacterCharacteristicsRule();
		charRule.getRules().add(new DigitCharacterRule(1));
		charRule.getRules().add(new NonAlphanumericCharacterRule(1));
		charRule.getRules().add(new UppercaseCharacterRule(1));
		charRule.getRules().add(new LowercaseCharacterRule(1));
		charRule.setNumberOfCharacteristics(4);
		ruleList.add(charRule);
		PasswordValidator validator = new PasswordValidator(ruleList);
		PasswordData passwordData = new PasswordData(new Password(password));
		RuleResult result = validator.validate(passwordData);
		rootLogger.debug(result.toString());
		return result.isValid();
	}

	public static boolean validateUsername(String name) {
		List<Rule> rules = new ArrayList<Rule>();
		rules.add(new LengthRule(6, 16));
		rules.add(new WhitespaceRule());
//		rules.add(new DigitCharacterRule(0));
//		rules.add(new AlphabeticalCharacterRule());
//		rules.add(new UsernameRule(true, true));
		
		PasswordValidator validator = new PasswordValidator(rules);
		PasswordData passwordData = new PasswordData(new Password(name));
		RuleResult result = validator.validate(passwordData);
		rootLogger.debug(result.toString());
		return result.isValid();
	}
}

package team.boolbee.poc.spring.security.mvc.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import team.boolbee.poc.spring.security.model.Person;

public class PersonValidator implements Validator {

	private static final String EMAIL_REGEXP = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$";

	public boolean supports(Class clazz) {
		return clazz.equals(Person.class);
	}

	public void validate(Object command, Errors errors) {
		Person person = (Person) command;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "required.surname", "Surname is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required.");
		if (StringUtils.hasText(person.getEmail())) {
			validateEmail(person.getEmail(), errors);
		}
	}
	
	public void validateEmail(String email, Errors errors) {
		Pattern pattern = Pattern.compile(EMAIL_REGEXP);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			if (!errors.hasFieldErrors("email")) {
				errors.rejectValue("email", "invalid.email", new Object[] { email },
						"{0} is an invalid email.");
			}
		}
	}
}
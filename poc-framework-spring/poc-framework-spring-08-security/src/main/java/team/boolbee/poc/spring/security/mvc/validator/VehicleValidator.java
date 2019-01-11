package team.boolbee.poc.spring.security.mvc.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import team.boolbee.poc.spring.hibernate.model.Vehicle;

public class VehicleValidator implements Validator {

	private static final String PLATE_REGEXP = "^[0-9]{4}[a-zA-Z]{3}$";

	public boolean supports(Class clazz) {
		return clazz.equals(Vehicle.class);
	}

	public void validate(Object command, Errors errors) {
		Vehicle vehicle = (Vehicle) command;

		ValidationUtils.rejectIfEmpty(errors, "type", "required.type", "Type is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "plateNumber", "required.plateNumber",
				"The license plate number is required.");
		validatePlateNumber(vehicle.getPlateNumber(), errors);
	}

	public void validatePlateNumber(String plateNumber, Errors errors) {
		Pattern pattern = Pattern.compile(PLATE_REGEXP);
		Matcher matcher = pattern.matcher(plateNumber);
		if (!matcher.matches()) {
			if (!errors.hasFieldErrors("plateNumber")) {
				errors.rejectValue("plateNumber", "invalid.plateNumber", new Object[] { plateNumber },
						"{0} is an invalid license plate number.");
			}
		}
	}
}
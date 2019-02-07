package team.boolbee.poc.spring.security.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import team.boolbee.poc.spring.security.model.Person;
import team.boolbee.poc.spring.security.model.Vehicle;
import team.boolbee.poc.spring.security.model.VehicleType;
import team.boolbee.poc.spring.security.mvc.validator.PersonValidator;
import team.boolbee.poc.spring.security.services.VehicleRegistrationService;

public class UserRegistrationFormController extends AbstractWizardFormController {

	public UserRegistrationFormController() {
		setCommandClass(Person.class);
		setCommandName("person");
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		vehicles.add(new Vehicle());

		Person personForm = new Person();
		personForm.setVehicles(vehicles);
		return personForm;
	}

	@Override
	protected Map referenceData(HttpServletRequest request, Object command, Errors errors, int page) throws Exception {
		Person person = (Person) command;
		Map<String, Object> referenceData = new HashMap<String, Object>();
		if (page == 1 && request.getParameter("_target1") != null) {
			referenceData.put("nextVehicle", person.getVehicles().size() - 1);
		}

		referenceData.put("vehicleTypes", VehicleType.values());
		return referenceData;
	}

	@Override
	protected void validatePage(Object command, Errors errors, int page) {
		PersonValidator validator = (PersonValidator) getValidator();
		if (page == 0) {
			validator.validate(command, errors);
		}
	}

	@Override
	protected void postProcessPage(HttpServletRequest request, Object command, Errors errors, int page)
			throws Exception {
		Person person = (Person) command;
		if (page == 1 && request.getParameter("_target1") != null) {
			person.addVehicle(new Vehicle());
		}
	}

	@Override
	protected ModelAndView processCancel(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		return new ModelAndView(getSuccessView());
	}

	@Override
	protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		Person person = (Person) command;

		// Se elimina el último vehiculo que está sin rellenar
		person.getVehicles().remove(person.getVehicles().size() - 1);

		vehicleRegistrationService.register(person);

		return new ModelAndView(getSuccessView(), "person", person);
	}

	private String getSuccessView() {
		return getPages()[getPages().length - 1];
	}

	// injected
	private VehicleRegistrationService vehicleRegistrationService;

	public void setVehicleRegistrationService(VehicleRegistrationService vehicleRegistrationService) {
		this.vehicleRegistrationService = vehicleRegistrationService;
	}
}
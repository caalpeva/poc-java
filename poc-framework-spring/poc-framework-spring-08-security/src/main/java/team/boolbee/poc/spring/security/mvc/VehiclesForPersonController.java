package team.boolbee.poc.spring.security.mvc;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import team.boolbee.poc.spring.hibernate.model.Person;
import team.boolbee.poc.spring.hibernate.model.Vehicle;
import team.boolbee.poc.spring.hibernate.services.VehicleRegistrationService;

public class VehiclesForPersonController extends AbstractCommandController {

	public VehiclesForPersonController() {
		setCommandClass(Person.class);
		setCommandName("person");
	}

	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {

		Person person = (Person) command;

		person = vehicleRegistrationService.getPersonById(person.getId());
		Collection<Vehicle> vehicles = vehicleRegistrationService.getVehiclesForPersons(person.getId());
		
		Map model = errors.getModel();
		model.put("vehicles", vehicles);
		model.put("person", person);

		return new ModelAndView("vehiclesForPerson", model);
	}

	// injected
	private VehicleRegistrationService vehicleRegistrationService;

	public void setVehicleRegistrationService(VehicleRegistrationService vehicleRegistrationService) {
		this.vehicleRegistrationService = vehicleRegistrationService;
	}

}
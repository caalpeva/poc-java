package team.boolbee.poc.spring.security.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import team.boolbee.poc.spring.security.model.Person;
import team.boolbee.poc.spring.security.model.Vehicle;
import team.boolbee.poc.spring.security.model.VehicleType;
import team.boolbee.poc.spring.security.services.VehicleRegistrationService;

public class AddVehicleFormController extends SimpleFormController {

	public AddVehicleFormController() {
		setCommandClass(Vehicle.class);
		setCommandName("vehicle");
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Person person = vehicleRegistrationService.getPersonById(Integer.valueOf(request.getParameter("personId"))); 
		Vehicle vehicleForm = (Vehicle) super.formBackingObject(request);
		vehicleForm.setPerson(person);
		return vehicleForm;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map<String, Object> referenceData = new HashMap<String, Object>();
		referenceData.put("personId", request.getParameter("personId"));
		referenceData.put("vehicleTypes", VehicleType.values());
		return referenceData;
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		Vehicle vehicle = (Vehicle) command;
		vehicleRegistrationService.register(vehicle);
		
		return new ModelAndView(getSuccessView());
	}
	
	// injected
	private VehicleRegistrationService vehicleRegistrationService;

	public void setVehicleRegistrationService(VehicleRegistrationService vehicleRegistrationService) {
		this.vehicleRegistrationService = vehicleRegistrationService;
	}

}
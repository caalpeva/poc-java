package team.boolbee.poc.spring.security.services;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import junit.framework.TestCase;
import team.boolbee.poc.spring.security.model.Person;
import team.boolbee.poc.spring.security.model.Vehicle;
import team.boolbee.poc.spring.security.model.VehicleType;
import team.boolbee.poc.spring.security.mvc.VehiclesForPersonController;

public class VehiclesForPersonControllerTest extends TestCase {

	private final int TEST_PERSON_ID = 1;

	private VehiclesForPersonController controller;

	private VehicleRegistrationService registrationService;

	public VehiclesForPersonControllerTest() {
	}

	@Override
	protected void setUp() throws Exception {
		controller = new VehiclesForPersonController();
		controller.setCommandClass(Person.class);

		registrationService = createMock(VehicleRegistrationService.class);

		Person testPerson = new Person();
		testPerson.setId(TEST_PERSON_ID);
		testPerson.setName("Test name");
		testPerson.setSurname("Test surname");
		testPerson.setEmail("testperson@mailinator.com");

		List<Vehicle> expectedVehicles = new ArrayList<Vehicle>();
		Vehicle vehicle = new Vehicle();
		vehicle.setPlateNumber("3259EFD");
		vehicle.setRegistrationDate(new Date());
		vehicle.setType(VehicleType.BICYCLE);
		vehicle.setPerson(testPerson);
		expectedVehicles.add(vehicle);

		vehicle = new Vehicle();
		vehicle.setPlateNumber("4044JUL");
		vehicle.setRegistrationDate(new Date());
		vehicle.setType(VehicleType.AUTOMOBILE);
		vehicle.setPerson(testPerson);
		expectedVehicles.add(vehicle);

		expect(registrationService.getPersonById(testPerson.getId())).andReturn(testPerson);
		expect(registrationService.getVehiclesForPersons(testPerson.getId())).andReturn(expectedVehicles);
		replay(registrationService);

		controller.setVehicleRegistrationService(registrationService);
	}

	@SuppressWarnings("unchecked")
	public void testSimpleCase() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.addParameter("id", String.valueOf(TEST_PERSON_ID));
		request.setRequestURI("http://localhost:8080/poc-webapp-spring-security/vehiclesforperson.htm");

		HttpServletResponse response = new MockHttpServletResponse();

		ModelAndView modelAndView = controller.handleRequest(request, response);

		assertNotNull("ModelAndView should not be null", modelAndView);
		assertEquals("View name should be 'vehiclesForPerson'", "vehiclesForPerson", modelAndView.getViewName());

		Map<String, Object> map = modelAndView.getModel();
		assertTrue("Model should contain 'vehicles' key", map.containsKey("vehicles"));

		List<Vehicle> vehicles = (List<Vehicle>) map.get("vehicles");
		assertNotNull("Model element 'vehicles' should not be null", vehicles);
		assertEquals("Model element 'vehicles' should contain 2 items", 2, vehicles.size());

		for (Vehicle vehicle : vehicles) {
			assertEquals("Vehicle's person identifier is incorrect", Integer.valueOf(TEST_PERSON_ID), vehicle.getPerson().getId());
		} // for
	}
}
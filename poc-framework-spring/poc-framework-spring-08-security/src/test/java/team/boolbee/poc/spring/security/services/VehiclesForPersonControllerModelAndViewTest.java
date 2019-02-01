package team.boolbee.poc.spring.security.services;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.web.servlet.ModelAndView;

import team.boolbee.poc.spring.security.model.Person;
import team.boolbee.poc.spring.security.model.Vehicle;
import team.boolbee.poc.spring.security.model.VehicleType;
import team.boolbee.poc.spring.security.mvc.VehiclesForPersonController;

public class VehiclesForPersonControllerModelAndViewTest extends AbstractModelAndViewTests {

	private final int TEST_PERSON_ID = 1;

	private VehiclesForPersonController controller;

	private VehicleRegistrationService registrationService;
	
	private List<Vehicle> expectedVehicles;

	public VehiclesForPersonControllerModelAndViewTest() {
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

		expectedVehicles = new ArrayList<Vehicle>();
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

	public void testSimpleCase() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.addParameter("id", String.valueOf(TEST_PERSON_ID));
		request.setRequestURI("http://localhost:8080/poc-webapp-spring-security/vehiclesforperson.htm");

		HttpServletResponse response = new MockHttpServletResponse();

		ModelAndView modelAndView = controller.handleRequest(request, response);

		assertNotNull("ModelAndView should not be null", modelAndView);
		assertViewName(modelAndView, "vehiclesForPerson");
		assertModelAttributeAvailable(modelAndView, "vehicles");
		assertCompareListModelAttribute(modelAndView, "vehicles", expectedVehicles);
	}
}
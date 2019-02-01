package team.boolbee.poc.spring.security.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import team.boolbee.poc.spring.security.model.Person;
import team.boolbee.poc.spring.security.model.Vehicle;
import team.boolbee.poc.spring.security.model.VehicleType;
import team.boolbee.poc.spring.security.services.VehicleRegistrationService;

public class VehicleRegistrationServiceTransactionalDataSourceTest extends AbstractTransactionalDataSourceSpringContextTests {

	private VehicleRegistrationService registrationServiceDAO;

	public VehicleRegistrationServiceTransactionalDataSourceTest() {
	}

	@Override
	protected String[] getConfigLocations() {
		setAutowireMode(AUTOWIRE_BY_NAME);
		return new String[] { "spring-context.xml", "spring-datasource.xml", "spring-data-hibernate.xml",
				"spring-jmx-server-mbean.xml", "spring-scheduler.xml", "spring-service.xml", "spring-tx.xml",
				"spring-email.xml" };
	}

	
	
	@Override
	protected void onSetUpBeforeTransaction() throws Exception {
		registrationServiceDAO = (VehicleRegistrationService) applicationContext.getBean("vehicleRegistrationService");
	}

	@Override
	protected void onSetUpInTransaction() throws Exception {
		// Do nothing
	}

	@SuppressWarnings("deprecation")
	public void testAddPersonsWithVehicles() {
		Vehicle vehicle = new Vehicle();
		vehicle.setPlateNumber("AHR 7811");
		vehicle.setRegistrationDate(new Date());
		vehicle.setType(VehicleType.AUTOMOBILE);

		Person person = new Person();
		person.setName("Dan");
		person.setSurname("Aykroyd");
		person.setBirthDate(new Date(52, 6, 1));
		person.setEmail("daykroyd@mailinator.com");
		person.addVehicle(vehicle);

		int before = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM PERSON");

		registrationServiceDAO.register(person);

		int after = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM PERSON");
		assertEquals(before + 1, after);

		String name = (String) jdbcTemplate.queryForObject("SELECT NAME FROM PERSON WHERE ID = ?",
				new Object[] { person.getId() },
				String.class);
		
		assertEquals(person.getName(), name);

		vehicle = new Vehicle();
		vehicle.setPlateNumber("2107 PZG");
		vehicle.setRegistrationDate(new Date());
		vehicle.setType(VehicleType.MOTORCYCLE);

		person = new Person();
		person.setName("Bill");
		person.setSurname("Murray");
		person.setBirthDate(new Date(50, 8, 21));
		person.setEmail("bmurray@mailinator.com");
		person.addVehicle(vehicle);

		before = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM PERSON");

		registrationServiceDAO.register(person);

		after = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM PERSON");
		assertEquals(before + 1, after);

		@SuppressWarnings("unchecked")
		List<Person> persons = jdbcTemplate.query("SELECT id, name, surname, birthDate, email FROM PERSON", new RowMapper() {
			public Object mapRow(ResultSet result, int rowNums) throws SQLException {
				Person person = new Person();
				person.setId(result.getInt(1));
				person.setName(result.getString(2));
				person.setSurname(result.getString(3));
				person.setBirthDate(result.getDate(4));
				person.setEmail(result.getString(5));
				return person;
			}
		});
		assertEquals(before + 1, persons.size());
		
		for (Person currentPerson : persons) {
			System.out.println(currentPerson);
		} // for

		List<Vehicle> vehicles = registrationServiceDAO.getVehiclesForDay(new Date());
		for (Vehicle currentVehicle : vehicles) {
			System.out.println(currentVehicle.getPlateNumber() + " " + currentVehicle.getRegistrationDate());
		} // for

		// Para confirmar las transacciones al finalizar el método de prueba
		//setComplete();

		// Para consignar los cambios inmediatamente
		// endTransaction();
	}

	@SuppressWarnings("deprecation")
	public void testAddPersonsWithSameVehicleFailure() {
		Vehicle vehicle = new Vehicle();
		vehicle.setPlateNumber("8302GTR");
		vehicle.setRegistrationDate(new Date());
		vehicle.setType(VehicleType.MOTORCYCLE);

		Person person = new Person();
		person.setName("Harold");
		person.setSurname("Ramis");
		person.setBirthDate(new Date(44, 11, 17));
		person.setEmail("hramis@mailinator.com");
		person.addVehicle(vehicle);

		int before = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM PERSON");
		
		registrationServiceDAO.register(person);

		int after = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM PERSON");
		assertEquals(before + 1, after);
		
		String name = (String) jdbcTemplate.queryForObject("SELECT NAME FROM PERSON WHERE ID = ?",
				new Object[] { person.getId() },
				String.class);
		
		assertEquals(person.getName(), name);

		vehicle = new Vehicle();
		vehicle.setPlateNumber("8302GTR");
		vehicle.setRegistrationDate(new Date());
		vehicle.setType(VehicleType.MOTORCYCLE);

		person = new Person();
		person.setName("Sigourney");
		person.setSurname("Weaver");
		person.setBirthDate(new Date(49, 9, 8));
		person.setEmail("sweaver@mailinator.com");
		person.addVehicle(vehicle);

		try {
			before = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM VEHICLE");
			registrationServiceDAO.register(person);
			fail("DataIntegrityViolationException must be generated");
		} catch (DataIntegrityViolationException e) {
			after = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM VEHICLE");
			assertEquals(before, after);
		}
	}

	// Una importante regla de las pruebas es que cada una de ellas debe
	// ejecutarse independientemente de otras. Cada prueba debe dejar su entorno
	// en un estado consistente después de haberse realizado.
	
	// Si se utiliza un método de confirmación de transacciones se deberá
	// realizar más tarde un trabajo extra de limpieza en los datos o la
	// base de datos contendrá datos contaminados. Estos datos pueden
	// interponerse en futuras pruebas y dificultar que las mismas sean
	// repetibles.

	@Override
	protected void onTearDownInTransaction() throws Exception {
		// Do nothing
	}

	@Override
	protected void onTearDownAfterTransaction() throws Exception {
		//deleteFromTables(new String[] { "VEHICLE", "PERSON" });
	}
}
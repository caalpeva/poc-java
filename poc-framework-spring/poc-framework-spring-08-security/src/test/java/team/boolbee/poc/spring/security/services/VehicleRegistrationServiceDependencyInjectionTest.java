package team.boolbee.poc.spring.security.services;

import org.acegisecurity.providers.dao.SaltSource;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class VehicleRegistrationServiceDependencyInjectionTest extends AbstractDependencyInjectionSpringContextTests {

	private VehicleRegistrationService registrationServiceDAO;
	private PasswordEncoder passwordEncoder;
	private SaltSource saltSource;
	
	public VehicleRegistrationServiceDependencyInjectionTest() {
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "spring-context.xml", "spring-datasource.xml", "spring-data-hibernate.xml",
				"spring-jmx-server-mbean.xml", "spring-scheduler.xml", "spring-service.xml", "spring-tx.xml",
				"spring-email.xml", "spring-security.xml" };
	}

	@Override
	protected ConfigurableApplicationContext createApplicationContext(String[] locations) {
		System.out.println("createApplicationContext()");
		// El contexto de aplicación únicamente se abre una vez, independientemente
		// de cuántos métodos de prueba existan, excepto si se fuerza su apertura
		// mediante el método setDirty().
		return super.createApplicationContext(locations);
	}
	
	@Override
	protected void onSetUp() throws Exception {
		System.out.println("onSetup()");
		registrationServiceDAO = (VehicleRegistrationService) applicationContext
				.getBean("vehicleRegistrationService");
		passwordEncoder = (PasswordEncoder) applicationContext.getBean("passwordEncoder");
		saltSource = (SaltSource) applicationContext.getBean("saltSource");
		
	}
	
	@Override
	protected void onTearDown() throws Exception {
		System.out.println("onTearDown()");
	}
	
	public void testEncryption() {
		String password = "admin2";
		String encodedPassword = passwordEncoder.encodePassword(password,
				saltSource != null? saltSource.getSalt(null): null);

		System.out.println("SALT:  " + (saltSource != null? saltSource.getSalt(null): null));
		System.out.println("UNENCODED: " + password);
		System.out.println("ENCODED:  " + encodedPassword);
		
		assertTrue(passwordEncoder.isPasswordValid(encodedPassword, password, (saltSource != null? saltSource.getSalt(null): null)));
	}
	
	public void testGetPersons() {
		System.out.println("testGetPersons()");
		registrationServiceDAO.getPersons();
		setDirty(); // Fuerza la apertura del contexto de aplicación
	}
	
	public void testGetVehiclesForPersons() {
		System.out.println("testGetVehiclesForPersons()");
		registrationServiceDAO.getVehiclesForPersons(1);
		setDirty(); // Fuerza la apertura del contexto de aplicación
	}

	// AbstractDependencyInjectionSpringContextTests es estupendo para
	// realizar pruebas de integración de objetos conectados entre si en un
	// contexto de aplicación Spring. Pero solo cubre los casos sencillos
	// no transaccionales.
	
	// En esta prueba se produce el efecto secundario de que realmente
	// se añaden datos a la base de datos.
	
	// Una importante regla de las pruebas es que cada una de ellas debe
	// ejecutarse independientemente de otras. Cada prueba debe dejar su entorno
	// en un estado consistente después de haberse realizado.
	
	// En este caso no se quiere que se añadan datos permanentemente
	// a la base de datos. Se necesita que se eliminen las inserciones de la base
	// de datos cuando se haya terminado dicha prueba.
	
	// Por lo tanto se comenta dicha prueba, para realizarla adecuadamente mediante
	// un soporte transaccional como AbstractTransactionalSpringContextTests o
	// AbstractTransactionalDataSourceSpringContextTests
	
	public void testAddPersonsWithVehicles() {
		System.out.println("testAddPersonsWithVehicles()");
		
//		Vehicle vehicle = new Vehicle();
//		vehicle.setPlateNumber("AHR 7811");
//		vehicle.setRegistrationDate(new Date());
//		vehicle.setType(VehicleType.AUTOMOBILE);
//
//		Person person = new Person();
//		person.setName("Dan");
//		person.setSurname("Aykroyd");
//		person.setBirthDate(new Date(52, 6, 1));
//		person.setEmail("daykroyd@mailinator.com");
//		person.addVehicle(vehicle);
//
//		registrationServiceDAO.register(person);
//
//		List<Person> persons = registrationServiceDAO.getPersons();
//		assertTrue(persons.contains(person));
//		
//		vehicle = new Vehicle();
//		vehicle.setPlateNumber("2107 PZG");
//		vehicle.setRegistrationDate(new Date());
//		vehicle.setType(VehicleType.MOTORCYCLE);
//
//		person = new Person();
//		person.setName("Bill");
//		person.setSurname("Murray");
//		person.setBirthDate(new Date(50, 8, 21));
//		person.setEmail("bmurray@mailinator.com");
//		person.addVehicle(vehicle);
//
//		registrationServiceDAO.register(person);
//
//		persons = registrationServiceDAO.getPersons();
//		assertTrue(persons.contains(person));
//		for (Person currentPerson : persons) {
//			System.out.println(currentPerson);
//		} // for
//
//		List<Vehicle> vehicles = registrationServiceDAO.getVehiclesForDay(new Date());
//		for (Vehicle currentVehicle : vehicles) {
//			System.out.println(currentVehicle.getPlateNumber() + " " + currentVehicle.getRegistrationDate());
//		} // for
	}
}
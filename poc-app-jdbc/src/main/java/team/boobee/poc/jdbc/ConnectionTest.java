package team.boobee.poc.jdbc;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class ConnectionTest {

	public static void main(String[] args) {
		try {
			DataSource dataSource = new DataSource("properties\\hsqldb-properties.xml");
			//DataSource dataSource = new DataSource("properties\\sqlserver-properties.xml");
			PersonDAO userDAO = new PersonDAO(dataSource);
			Person user = new Person();
			user.setFirstName("Donald");
			user.setLastName("Trump");
			userDAO.save(user);
			List<Person> users = userDAO.list();
			if (users != null && users.size() > 0) {
				for (Person currentUser: users) {
					System.out.println("ID: " + currentUser.getId() + "\t USER DATA: " + currentUser.getFirstName() + " " + currentUser.getLastName());
				}
			}
			System.out.println("FINAL");
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

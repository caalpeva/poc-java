package team.boolbee.poc.spring.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import team.boolbee.poc.spring.jdbc.model.Person;

// Proporciona funcionalidades Java 5, como varargs y auto-boxing
public class SimpleJdbcTemplatePersonDao implements PersonDao {

	private static final String PERSON_INSERT = "INSERT INTO PERSON (firstName, lastName, birthDate) "
			+ "VALUES (?, ?, ?)";

	private static final String PERSON_UPDATE = "UPDATE PERSON " + "SET firstName=?, lastName=?, birthDate=? "
			+ "WHERE id=?";

	private static final String PERSON_SELECT = "SELECT id, firstName, lastName, birthDate FROM PERSON";

	private static final String PERSON_BY_ID_SELECT = PERSON_SELECT + " WHERE id=?";

	private SimpleJdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.jdbcTemplate = simpleJdbcTemplate;
	}
	
	public void savePerson(Person person) {
		int id = jdbcTemplate.update(PERSON_INSERT,
				person.getFirstName(), person.getLastName(), person.getBirthDate());
		person.setId(id);
		//person.setId(queryForIdentity());
	}

	private int queryForIdentity() {
		return new Integer(jdbcTemplate.queryForInt("call identity()"));
	}

	public void updatePerson(Person person) {

	}

	public Person getPersonById(Integer id) {
		List<Person> matches = jdbcTemplate.query(
				PERSON_BY_ID_SELECT,
				new ParameterizedRowMapper<Person>() {
					public Person mapRow(ResultSet result, int rowNums) throws SQLException {
						Person person = new Person();
						person.setId(result.getInt(1));
						person.setFirstName(result.getString(2));
						person.setLastName(result.getString(3));
						person.setBirthDate(result.getDate(4));
						return person;
					}
				}, id);

		return matches.size() > 0 ? (Person) matches.get(0) : null;
	}

	public List<Person> list() {
		return jdbcTemplate.query(PERSON_SELECT,
				new ParameterizedRowMapper<Person>() {
					public Person mapRow(ResultSet result, int rowNums) throws SQLException {
						Person person = new Person();
						person.setId(result.getInt(1));
						person.setFirstName(result.getString(2));
						person.setLastName(result.getString(3));
						person.setBirthDate(result.getDate(4));
						return person;
					}
				});
	}
}
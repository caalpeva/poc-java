package team.boolbee.poc.spring.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import team.boolbee.poc.spring.jdbc.model.Person;

public class JdbcTemplatePersonDao implements PersonDao {

	private static final String PERSON_INSERT = "INSERT INTO PERSON (id, firstName, lastName, birthDate) "
			+ "VALUES (null, ?, ?, ?)";

	private static final String PERSON_UPDATE = "UPDATE PERSON " + "SET firstName=?, lastName=?, birthDate=? "
			+ "WHERE id=?";

	private static final String PERSON_SELECT = "SELECT id, firstName, lastName, birthDate FROM PERSON";

	private static final String PERSON_BY_ID_SELECT = PERSON_SELECT + " WHERE id=?";

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void savePerson(Person person) {
		jdbcTemplate.update(PERSON_INSERT,
				new Object[] { person.getFirstName(), person.getLastName(), person.getBirthDate() });
		person.setId(queryForIdentity());
	}

	private int queryForIdentity() {
		return new Integer(jdbcTemplate.queryForInt("call identity()"));
	}

	public void updatePerson(Person person) {

	}

	@SuppressWarnings("rawtypes")
	public Person getPersonById(Integer id) {
		List matches = jdbcTemplate.query(PERSON_BY_ID_SELECT, new Object[] { Long.valueOf(id) }, new RowMapper() {
			public Object mapRow(ResultSet result, int rowNums) throws SQLException {
				Person person = new Person();
				person.setId(result.getInt(1));
				person.setFirstName(result.getString(2));
				person.setLastName(result.getString(3));
				person.setBirthDate(result.getDate(4));
				return person;
			}
		});

		return matches.size() > 0 ? (Person) matches.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	public List<Person> list() {
		return jdbcTemplate.query(PERSON_SELECT, new RowMapper() {
			public Object mapRow(ResultSet result, int rowNums) throws SQLException {
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
package team.boobee.poc.spring.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import team.boobee.poc.spring.jdbc.domain.Person;

public class NamedParameterJdbcTemplatePersonDao implements PersonDao {

	private static final String PERSON_INSERT = "INSERT INTO PERSON (id, firstName, lastName, birthDate) "
			+ "VALUES (null, :firstName, :lastName, :birthDate)";

	private static final String PERSON_UPDATE = "UPDATE PERSON " + "SET firstName=?, lastName=?, birthDate=? "
			+ "WHERE id=:id";

	private static final String PERSON_SELECT = "SELECT id, firstName, lastName, birthDate FROM PERSON";

	private static final String PERSON_BY_ID_SELECT = PERSON_SELECT + " WHERE id=:id";

	private NamedParameterJdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void savePerson(Person person) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstName", person.getFirstName());
		parameters.put("lastName", person.getLastName());
		parameters.put("birthDate", person.getBirthDate());
		jdbcTemplate.update(PERSON_INSERT, parameters);
		person.setId(queryForIdentity());
	}

	private int queryForIdentity() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		return new Integer(jdbcTemplate.queryForInt("call identity()", parameters));
	}

	public void updatePerson(Person person) {

	}

	@SuppressWarnings("rawtypes")
	public Person getPersonById(Integer id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", Long.valueOf(id));
		List matches = jdbcTemplate.query(PERSON_BY_ID_SELECT, parameters, new RowMapper() {
			@Override
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
		Map<String, Object> parameters = new HashMap<String, Object>();
		return jdbcTemplate.query(PERSON_SELECT, parameters, new RowMapper() {
			@Override
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
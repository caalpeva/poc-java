package team.boobee.poc.spring.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import team.boobee.poc.spring.jdbc.domain.Person;

public class JdbcPersonDao extends JdbcDaoSupport implements PersonDao {

	private static final String PERSON_INSERT = "INSERT INTO PERSON (id, firstName, lastName, birthDate) "
			+ "VALUES (null, ?, ?, ?)";

	private static final String PERSON_UPDATE = "UPDATE PERSON " + "SET firstName=?, lastName=?, birthDate=? "
			+ "WHERE id=?";

	private static final String PERSON_SELECT = "SELECT id, firstName, lastName, birthDate FROM PERSON";

	private static final String PERSON_BY_ID_SELECT = PERSON_SELECT + " WHERE id=?";

	public void savePerson(Person person) {
		getJdbcTemplate().update(PERSON_INSERT,
				new Object[] { person.getFirstName(), person.getLastName(), person.getBirthDate() });
		person.setId(queryForIdentity());
	}

	private int queryForIdentity() {
		return new Integer(getJdbcTemplate().queryForInt("call identity()"));
	}

	public void updatePerson(Person person) {

	}

	@SuppressWarnings("rawtypes")
	public Person getPersonById(Integer id) {
		List matches = getJdbcTemplate().query(PERSON_BY_ID_SELECT, new Object[] { Long.valueOf(id) }, new RowMapper() {
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
		return getJdbcTemplate().query(PERSON_SELECT, new RowMapper() {
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
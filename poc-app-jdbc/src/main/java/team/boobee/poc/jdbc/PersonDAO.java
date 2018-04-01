package team.boobee.poc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDAO {

	public static final String INSERT_USER = "INSERT INTO USERS (FIRSTNAME, LASTNAME)" + " VALUES (?, ?)";
	
	public static final String QUERY_USERS = "SELECT * FROM USERS";

	private DataSource dataSource;
	
	public PersonDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void save(Person user) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(INSERT_USER);

			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}
	}
	
	public List<Person> list() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(QUERY_USERS);
			rs = stmt.executeQuery();
			
			List<Person> users = new ArrayList<Person>();
			while(rs.next()) {
				Person user = new Person();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				users.add(user);
			}
			
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}
		
		return null;
	}
}

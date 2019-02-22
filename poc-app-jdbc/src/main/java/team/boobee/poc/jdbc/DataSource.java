package team.boobee.poc.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class DataSource {

	private Properties properties;
	String dbms;
	String driver;
	String dbName;
	String userName;
	String password;
	String serverName;
	Integer portNumber;
	
	public DataSource(String fileName) throws InvalidPropertiesFormatException, IOException {
		properties = new Properties();
		FileInputStream fis = new FileInputStream(fileName);
		properties.loadFromXML(fis);
		fis.close();
		
		dbms = properties.getProperty("dbms");
		driver = properties.getProperty("driver");
		dbName = properties.getProperty("database_name");
		userName = properties.getProperty("user_name");
		password = properties.getProperty("password");
		serverName = properties.getProperty("server_name");
		portNumber = Integer.parseInt(properties.getProperty("port_number"));
		
		System.out.println("Set the following properties:");
		System.out.println("dbms: " + dbms);
		System.out.println("driver: " + driver);
		System.out.println("dbName: " + dbName);
		System.out.println("userName: " + userName);
		System.out.println("serverName: " + serverName);
		System.out.println("portNumber: " + portNumber);
	}
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", userName);
	    connectionProps.put("password", password);
	    Class.forName(driver);
	    String currentUrlString = null;
	    if (dbms.equals("hsqldb")) {
		    //jdbc:hsqldb:hsql://localhost/roadrantz/roadrantz
	    	currentUrlString = "jdbc:hsqldb:hsql://" + serverName + ":" + portNumber + "/roadrantz/" + dbName;
	    	//currentUrlString = "jdbc:hsqldb:hsql://" + serverName + "/" + dbName;
	    } else if (dbms.equals("sqlserver")) {
		    //jdbc:hsqldb:hsql://localhost/roadrantz/roadrantz
	    	currentUrlString = "jdbc:sqlserver://" + serverName + ";databaseName=" + dbName + ";sendStringAsUnicodeParameter=false;applicationName=testsLocal";
	    }
	    
	    conn = DriverManager.getConnection(currentUrlString, connectionProps);
	    
	    System.out.println("Connected to database");
	    return conn;
	}
}

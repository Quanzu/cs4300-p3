package edu.uga.cs4300.persistlayer;

/**
 * This class holds necessary information to configure a connection to the database.
 * Before running this application, users should verify that the configurations are valid for the client.
 * @author Dustin Nguyen
 */

public abstract class DbAccessConfiguration {

	/**
	 * The fully qualified name of the JDBC driver.
	 */
	static final String DB_DRIVE_NAME = "com.mysql.jdbc.Driver";
	
	/**
	 * The JDBC connection URL.
	 */
	static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/imdb2";
	
	/**
	 * The username for the database.
	 */
	static final String DB_CONNECTION_USERNAME = "root";
	
	/**
	 * The password for the database.
	 */
	static final String DB_CONNECTION_PASSWORD = "root";
	
} // DbAccessConfiguration

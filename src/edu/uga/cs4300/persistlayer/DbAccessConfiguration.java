package edu.uga.cs4300.persistlayer;

public abstract class DbAccessConfiguration {

	/**
	 * The fully qualified name of the JDBC driver.
	 */
	static final String DB_DRIVE_NAME = "com.mysql.jdbc.Driver";
	
	/**
	 * The JDBC connection URL.
	 */
	static final String DB_CONNECTION_URL = "jdbc:mysql:/localhost:3306/ReviewServlet";
	
	/**
	 * The username for the database.
	 */
	static final String DB_CONNECTION_USERNAME = "root";
	
	/**
	 * The password for the database.
	 */
	static final String DB_CONNECTION_PASSWORD = "root";
	
} // DbAccessConfiguration

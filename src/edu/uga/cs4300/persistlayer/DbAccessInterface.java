package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides ways to connect and query to a database.
 * @author dustin
 */

public class DbAccessInterface  {
	
	/**
	 * Starts a connection to the database.
	 * @return connection object used to create queries. 
	 */
	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName(DbAccessConfiguration.DB_DRIVE_NAME);
			con = DriverManager.getConnection(DbAccessConfiguration.DB_CONNECTION_URL, DbAccessConfiguration.DB_CONNECTION_USERNAME, DbAccessConfiguration.DB_CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		return con;
	} // connect

	/**
	 * Close a connection to the database.
	 * @param con is the connection to be disconnected.
	 */
	public static void disconnect(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
	} // disconnect
	
	/**
	 * Returns the result from the given query
	 * @param query is typically a select statement
	 * @return the result set
	 */
	public static ResultSet retrieve (String query, Connection con) {
		ResultSet result = null;
		try {
			PreparedStatement statement = con.prepareStatement(query);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		return result;
	} // retrieve
	
	/**
	 * Adds an item to the database.
	 * @param query should typically be an insert statement.
	 * @return the amount of rows affected by this query.
	 */
	public static int create (String query, Connection con) {
		int rows = 0;
		try {
			PreparedStatement statement = con.prepareStatement(query);
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		return rows;
	} // create
	
	/**
	 * Updates an item within the database.
	 * @param query should be a replace or update statement.
	 * @return the amount of rows affected by this query.
	 */
	public static int update (String query, Connection con) {
		int rows = 0;
		try {
			PreparedStatement statement = con.prepareStatement(query);
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		return rows;
	} // update
	
	/**
	 * Deletes an item from the database.
	 * @param query should be a delete statement.
	 * @return the amount of rows affected by this query.
	 */
	public static int delete (String query, Connection con) {
		int rows = 0;
		try {
			PreparedStatement statement = con.prepareStatement(query);
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		return rows;
	} // delete
	
} // DbAccessInterface

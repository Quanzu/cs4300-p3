package edu.uga.cs4300.persistlayer;

import edu.uga.cs4300.objectlayer.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MoviePersistImpl {
	
	public void insertMovie(String title, int year, float rank, String genre) {
		Connection con = DbAccessInterface.connect();
		DbAccessInterface.create("INSERT INTO MOVIES (name, year, rank) VALUES (" + title +  "," + year + "," + rank +")", con);
		ResultSet movieJustAdded = DbAccessInterface.retrieve("SELECT id FROM MOVIES WHERE name = '" + title + "' AND year = " + year + "AND RANK = " + rank, con);
		int id = -1;
		try {
			id = movieJustAdded.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		DbAccessInterface.create("INSERT INTO MOVIES_GENRES (movie_id, genre) VALUES (" + id + ", '" + genre + "')", con);
		DbAccessInterface.disconnect(con);
	} // insertMovie
	
	public ArrayList<Movie> getAllMovies() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		Connection con = DbAccessInterface.connect();
		ResultSet result = DbAccessInterface.retrieve("SELECT * FROM MOVIES JOIN MOVIES_GENRES ON MOVIES.ID = MOVIES_GENRES.MOVIE_ID", con);
		try {
			while (result.next()) {
				Movie movie = new Movie(result.getInt(1),result.getString(2),result.getInt(3),result.getFloat(4), result.getString(6));
				movies.add(movie);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		DbAccessInterface.disconnect(con);
		return movies;
	} // restoreAllMovies
	
	public ArrayList<Movie> getMoviesFromGenre(String genre) {
		Connection con = DbAccessInterface.connect();
		ArrayList<Movie> movies = new ArrayList<Movie>();
		String selectFromGenreSQL ="SELECT * FROM MOVIES JOIN MOVIES_GENRES ON MOVIES.ID = MOVIES_GENRES.MOVIE_ID WHERE GENRE = '" + genre +"'";
		ResultSet result = DbAccessInterface.retrieve(selectFromGenreSQL,con);
		try {
			while (result.next()) {
				Movie movie = new Movie(result.getInt(1),result.getString(2),result.getInt(3),result.getFloat(4), result.getString(6));
				movies.add(movie);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		DbAccessInterface.disconnect(con);
		return movies;
	} // getMoviesFromGenre
	
} // MoviePersistImpl

package edu.uga.cs4300.persistlayer;

import edu.uga.cs4300.objectlayer.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class provides a means to access and retrieve database, particularly from the
 * movies and movies_genres tables.
 * @author Dustin Nguyen
 */
public class MoviePersistImpl {
	
	/**
	 * Generates a string of unique genres that the movie is classified in according to the database.
	 * @param id is the unique id of the movie, which should already exist in the database movie table.
	 * @param con is a connection that is assumed to be live.
	 * @return a StringBuilder that contains a unique list of genres that the movie is classified in, delimited by ","
	 */
	private StringBuilder getGenresOfMovie(int id, Connection con) {
		ResultSet genreResults = DbAccessImpl.retrieve("SELECT genre FROM movies_genres WHERE movie_id = " + id, con);
		StringBuilder movieGenre = new StringBuilder("");
		HashSet<String> genreSet = new HashSet<String>();
		try {
			while (genreResults.next()) genreSet.add(genreResults.getString(1).toLowerCase());
			for (String genre : genreSet) {
				if (movieGenre.toString().equals("")) movieGenre.append(genre);
				else movieGenre.append(", " + genre);				
			} // for
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		return movieGenre;
	} // getGenresOfMovie
	
	/**
	 * Performs an insertion into the database's movie table.
	 * @param title is the title of the movie
	 * @param year is the year the movie was made
	 * @param rank is a 0-10 rating of the movie
	 * @param genre should be delimited by a comma. For example: "Family, Musical, ..." if genre is left null or empty, the movie is not inserted into movies_genres table.
	 */
	public void insertMovie(String title, int year, float rank, String genre) {
		Connection con = DbAccessImpl.connect();
		DbAccessImpl.create("INSERT INTO MOVIES (name, year, rank) VALUES ('" + title +  "', " + year + ", " + rank +")", con);
		ResultSet movieJustAdded = DbAccessImpl.retrieve("SELECT id FROM MOVIES WHERE name = '" + title + "' AND year = " + year + " AND RANK < " + (rank + .0001) + " AND RANK > " + (rank - .0001), con);
		int id = -1;
		try {
			movieJustAdded.first();
			id = movieJustAdded.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		System.out.println(genre);
		DbAccessImpl.create("INSERT INTO MOVIES_GENRES (movie_id, genre) VALUES (" + id + ", '" + genre + "')", con);
		DbAccessImpl.disconnect(con);
	} // insertMovie
	
	/**
	 * @return an alphabetically ordered list of all movies within the movies table of the database.
	 */
	public ArrayList<Movie> getAllMovies() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		Connection con = DbAccessImpl.connect();
		ResultSet result = DbAccessImpl.retrieve("SELECT * FROM MOVIES ORDER BY name", con);
		try {
			while (result.next()) {
				Movie movie = new Movie(result.getInt(1),result.getString(2),result.getInt(3),result.getFloat(4), "genre not defined");
				StringBuilder movieGenre = getGenresOfMovie(result.getInt(1), con);
				if (!movieGenre.toString().equals("")) movie.setGenre(movieGenre.toString());
				movies.add(movie);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		DbAccessImpl.disconnect(con);
		return movies;
	} // restoreAllMovies
	
	/**
	 * @param genre should be a string of genre's delimited by a comma. Example: "Family, Musical, ... , Action"
	 * @return a list of movies that are classified within the specified genre(s).
	 */
	public ArrayList<Movie> getMoviesFromGenre(String genre) {
		Connection con = DbAccessImpl.connect();
		ArrayList<Movie> movies = new ArrayList<Movie>();
		String selectFromGenreSQL = "SELECT * FROM MOVIES JOIN MOVIES_GENRES ON MOVIES.ID = MOVIES_GENRES.MOVIE_ID WHERE GENRE = '" + genre +"'";
		ResultSet result = DbAccessImpl.retrieve(selectFromGenreSQL,con);
		try {
			while (result.next()) {
				Movie movie = new Movie(result.getInt(1),result.getString(2),result.getInt(3),result.getFloat(4), "genre not defined");
				StringBuilder movieGenre = getGenresOfMovie(result.getInt(1), con);
				if (!movieGenre.toString().equals("")) movie.setGenre(movieGenre.toString());
				movies.add(movie);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		DbAccessImpl.disconnect(con);
		return movies;
	} // getMoviesFromGenre
	
	/**
	 * @return a list of genres that have so-far been used to classify movies.
	 */
	public ArrayList<String> getGenreList() {
		Connection con = DbAccessImpl.connect();
		ArrayList<String> genres = new ArrayList<String>();
		ResultSet result = DbAccessImpl.retrieve("SELECT DISTINCT genre FROM MOVIES_GENRES", con);
		try {
			while (result.next()) {
				String genre = result.getString(1);
				genres.add(genre);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		DbAccessImpl.disconnect(con);
		return genres;
	} // getGenreFromMovies
	
} // MoviePersistImpl

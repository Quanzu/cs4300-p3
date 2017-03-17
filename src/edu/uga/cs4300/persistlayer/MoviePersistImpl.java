package edu.uga.cs4300.persistlayer;

import edu.uga.cs4300.objectlayer.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class MoviePersistImpl {
	
	/**
	 * Assumes connection is live
	 * @param id
	 * @return
	 */
	private StringBuilder getGenresOfMovie(int id, Connection con) {
		ResultSet genreResults = DbAccessInterface.retrieve("SELECT genre FROM movies_genres WHERE movie_id = " + id, con);
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
		ResultSet result = DbAccessInterface.retrieve("SELECT * FROM MOVIES ORDER BY name", con);
		try {
			while (result.next()) {
				Movie movie = new Movie(result.getInt(1),result.getString(2),result.getInt(3),result.getFloat(4), "Genre not defined!");
				StringBuilder movieGenre = getGenresOfMovie(result.getInt(1), con);
				if (!movieGenre.toString().equals("")) movie.setGenre(movieGenre.toString());
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
		String selectFromGenreSQL = "SELECT * FROM MOVIES JOIN MOVIES_GENRES ON MOVIES.ID = MOVIES_GENRES.MOVIE_ID WHERE GENRE = '" + genre +"'";
		ResultSet result = DbAccessInterface.retrieve(selectFromGenreSQL,con);
		try {
			while (result.next()) {
				Movie movie = new Movie(result.getInt(1),result.getString(2),result.getInt(3),result.getFloat(4), "Genre not defined!");
				StringBuilder movieGenre = getGenresOfMovie(result.getInt(1), con);
				if (!movieGenre.toString().equals("")) movie.setGenre(movieGenre.toString());
				movies.add(movie);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		DbAccessInterface.disconnect(con);
		return movies;
	} // getMoviesFromGenre
	
	public ArrayList<String> getGenreList() {
		Connection con = DbAccessInterface.connect();
		ArrayList<String> genres = new ArrayList<String>();
		ResultSet result = DbAccessInterface.retrieve("SELECT DISTINCT genre FROM MOVIES_GENRES", con);
		try {
			while (result.next()) {
				String genre = result.getString(1);
				genres.add(genre);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		DbAccessInterface.disconnect(con);
		return genres;
	} // getGenreFromMovies
	
} // MoviePersistImpl

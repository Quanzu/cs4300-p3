package edu.uga.cs4300.logiclayer;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import edu.uga.cs4300.objectlayer.Movie;
import edu.uga.cs4300.persistlayer.MoviePersistImpl;

/**
 * This class represents the controller class for retrieving information on movies
 * within the database, particularly from the movies and movies_genres tables. 
 * @author Dustin Nguyen
 */
public class MovieLogicImpl {
	
	private MoviePersistImpl moviePersistence = new MoviePersistImpl();
	
	/**
	 * Insert a user into the movies table based on user input.
	 * @param request is the current request of the servlet.
	 */
	public boolean insertMovie(HttpServletRequest request) {
		if (request.getParameter("title").equals("") || request.getParameter("year").equals("") || request.getParameter("rank").equals("") || request.getParameter("genre").equals("")) return false;
		String title = request.getParameter("title");
		int year = Integer.parseInt(request.getParameter("year"));
		float rank = Float.parseFloat(request.getParameter("rank"));
		String genre = request.getParameter("genre");
		moviePersistence.insertMovie(title, year, rank, genre);
		return true;
	} // insertMovie
	
	/**
	 * @return an alphabetized list of all movies from the movies table.
	 */
	public ArrayList<Movie> getAllMovies() {
		return moviePersistence.getAllMovies();
	} // restoreAllMovies
	
	/**
	 * @param genre should be a list of genres delimited by a comma. For example: "Family, Musical, ... , Action"
	 * @return a list of movies that are classified within the specified genre(s).
	 */
	public ArrayList<Movie> getMoviesFromGenre(String genre) {
		return moviePersistence.getMoviesFromGenre(genre);
	} // getMoviesFromGenre

	/**
	 * @return a list of genres that have been used to classify movies.
	 */
	public ArrayList<String> getGenreList() {
		return moviePersistence.getGenreList();
	} // getGenreList
	
	/**
	 * @param id is the id of the movie to retrieve
	 * @return a movie object representing the data retrieved from the database.
	 */
	public Movie getMovie(int id) {
		return moviePersistence.getMovie(id);
	} // getMovie
	
	/**
	 * @param title to be considered for selection
	 * @return a list of movies that contain the title as a substring.
	 */
	public ArrayList<Movie> getMovie(String title) {
		return moviePersistence.getMovie(title);
	} // getMovie
	
	/**
	 * @param request
	 * @return true if worked, false otherwise
	 */
	public boolean updateMovie(HttpServletRequest request) {
		if (request.getParameter("title").equals("") || request.getParameter("year").equals("") || request.getParameter("rank").equals("") || request.getParameter("genre").equals("")) return false;
		String title = request.getParameter("title");
		int year = Integer.parseInt(request.getParameter("year"));
		float rank = Float.parseFloat(request.getParameter("rank"));
		String genre = request.getParameter("genre");
		int id = Integer.parseInt(request.getParameter("movieId"));
		moviePersistence.updateMovie(id, title, year, rank, genre);
		return true;
	} // editMovie
	
	/**
	 * Delete a movie via its ID.
	 * @param request
	 */
	public void deleteMovie (HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("movieId"));
		moviePersistence.deleteMovie(id);
	} // deleteMovie
	
} // MovieLogicImpl

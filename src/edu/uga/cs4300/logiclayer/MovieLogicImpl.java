package edu.uga.cs4300.logiclayer;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import edu.uga.cs4300.boundary.ReviewServlet;
import edu.uga.cs4300.objectlayer.Movie;
import edu.uga.cs4300.persistlayer.MoviePersistImpl;


public class MovieLogicImpl {

	private MoviePersistImpl moviePersistence = new MoviePersistImpl();
		
	public void insertMovie(HttpServletRequest request) {
		String title = request.getParameter("title");
		int year = Integer.parseInt(request.getParameter("year"));
		float rank = Float.parseFloat(request.getParameter("rank"));
		String genre = request.getParameter("genre");
		moviePersistence.insertMovie(title, year, rank, genre);
	} // insertMovie
	
	public ArrayList<Movie> getAllMovies() {
		return moviePersistence.getAllMovies();
	} // restoreAllMovies
	
	public ArrayList<Movie> getMoviesFromGenre(String genre) {
		return moviePersistence.getMoviesFromGenre(genre);
	} // getMoviesFromGenre

	public ArrayList<String> getGenreList() {
		return moviePersistence.getGenreList();
	} // getGenresFromMovies
	
} // MovieLogicImpl

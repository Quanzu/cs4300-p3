package edu.uga.cs4300.logiclayer;


import java.util.ArrayList;

import edu.uga.cs4300.boundary.ReviewServlet;
import edu.uga.cs4300.objectlayer.Movie;
import edu.uga.cs4300.persistlayer.MoviePersistImpl;


public class MovieLogicImpl {

	private ReviewServlet servlet = null;
	private MoviePersistImpl moviePersistence = new MoviePersistImpl();
	
	public MovieLogicImpl(ReviewServlet servlet) {
		this.servlet = servlet;
	} // MovieLogicImpl
	
	public void insertMovie() {
		String title = servlet.getCurrentRequest().getParameter("title");
		int year = Integer.parseInt(servlet.getCurrentRequest().getParameter("year"));
		float rank = Float.parseFloat(servlet.getCurrentRequest().getParameter("rank"));
		String genre = servlet.getCurrentRequest().getParameter("genre");
		moviePersistence.insertMovie(title, year, rank, genre);
	} // insertMovie
	
	public ArrayList<Movie> getAllMovies() {
		return moviePersistence.getAllMovies();
	} // restoreAllMovies
	
	public ArrayList<Movie> getMoviesFromGenre() {
		String genre = servlet.getCurrentRequest().getParameter("genre");
		return moviePersistence.getMoviesFromGenre(genre);
	} // getMoviesFromGenre

} // MovieLogicImpl

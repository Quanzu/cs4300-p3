package edu.uga.cs4300.boundary;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uga.cs4300.logiclayer.MovieLogicImpl;
import edu.uga.cs4300.logiclayer.ReviewLogicImpl;
import edu.uga.cs4300.objectlayer.Movie;
import edu.uga.cs4300.objectlayer.Review;
import freemarker.template.Configuration;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateProcessor templateProcessor = null;
	private Configuration cfg = null;
	
    /**
     * Default constructor. 
     */
    public ReviewServlet() {
    	super();
    } // ReviewServlet
    
    public void init() {
    	cfg = new Configuration(Configuration.getVersion());
    	templateProcessor = new TemplateProcessor(cfg, getServletContext());
    } // init
 
    public void runViewAll(HttpServletResponse response) {
      	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	ArrayList<Movie> movies = movieLogic.getAllMovies();
    	templateProcessor.addToRoot("movies", movies);
    	templateProcessor.addToRoot("category", "All Movies");
    	templateProcessor.setTemplate("view.ftl");
    	templateProcessor.processTemplate(response);
    } // runViewAll
    
    public void runViewMoviesFromGenres(HttpServletRequest request, HttpServletResponse response) {
      	MovieLogicImpl movieLogic = new MovieLogicImpl();
      	ArrayList<String> genres = movieLogic.getGenreList();
      	HashSet<Movie> movieSet = new HashSet<Movie>(); // Using a set of movies so that all elements are unique. Protects against duplicates in DB.
      	StringBuilder header = new StringBuilder("");
      	// Search through set of genres, see if they were selected. Then add movies of genre to root.
      	for (String genre : genres) {
      		if (request.getParameter(genre) != null) {
      			if (header.toString().equals("")) header.append(genre);
      			else header.append(", " + genre);
      			ArrayList<Movie> movies = movieLogic.getMoviesFromGenre(request.getParameter(genre));
      			for (Movie m : movies) movieSet.add(m);
      		} // if
      	} // for
      	ArrayList<Movie> movies = new ArrayList<Movie>();
      	for (Movie m : movieSet) movies.add(m);
      	if (movies.size() == 0) { // If no genres selected, view all movies.
      		runViewAll(response);
      		return;
      	} else {
	    	templateProcessor.addToRoot("movies", movies);
	    	templateProcessor.addToRoot("category", "From Genres \"" + header.toString() + "\"");
	    	templateProcessor.setTemplate("view.ftl");
	    	templateProcessor.processTemplate(response);
      	} // if-else
    } // runViewGenre
    
    public void runViewGenres(HttpServletResponse response) {
    	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	ArrayList<String> genres = movieLogic.getGenreList();
    	templateProcessor.addToRoot("genres", genres);
    	templateProcessor.setTemplate("genre.ftl");
    	templateProcessor.processTemplate(response);
    } // runViewGenres
    
    public void runNewMovie(HttpServletResponse response) {
    	templateProcessor.setTemplate("new_movie.ftl");
    	templateProcessor.processTemplate(response);
    } // runNewMovie
    
    public void runAddNewMovie(HttpServletRequest request, HttpServletResponse response) {
    	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	String success = movieLogic.insertMovie(request) ? "Movie was successfully added!" : "Sorry, movie was not added due to empty form submission. Please try again.";
    	templateProcessor.addToRoot("success", success);
    	runNewMovie(response);
    } // addNewMovie
     
    public void runAddNewReview(HttpServletRequest request, HttpServletResponse response) {
    	ReviewLogicImpl reviewLogic = new ReviewLogicImpl();
    	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	String success = reviewLogic.insertReview(request) ? "Review was successfully added!" : "Sorry, review was not added due to empty form submission. Please try again.";
    	Integer movie_id = null;
    	
    	// Parse int b/c the DB formats ID with apostrophes (i.e. 1,000 when we need 1000)
    	try {
			movie_id = NumberFormat.getNumberInstance(Locale.US).parse(request.getParameter("movieId")).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		} // try-catch
    	
    	// Run template
    	Movie movie = movieLogic.getMovie(movie_id.intValue());
    	ArrayList<Review> reviews = reviewLogic.getReviews(movie_id.intValue());
    	templateProcessor.addToRoot("movie", movie);
    	templateProcessor.addToRoot("success", success);
    	templateProcessor.addToRoot("reviews", reviews);
    	templateProcessor.setTemplate("view_movie.ftl");
    	templateProcessor.processTemplate(response);
    } // runAddNewReview
    
    private void checkForMovieInquiry(HttpServletRequest request, HttpServletResponse response) {    	
    	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	ArrayList<Movie> movies = movieLogic.getAllMovies();
    	ArrayList<Review> reviews = new ArrayList<Review>();
    	ReviewLogicImpl reviewLogic = new ReviewLogicImpl();
    	for (Movie m : movies) {
    		String db_id = NumberFormat.getIntegerInstance().format(m.getId()); // ID of movie as formatted in DB
    		Integer plain_id = m.getId(); // ID in standard in form; needed for SQL query.
    		if (request.getParameter(db_id) != null) {
    			reviews = reviewLogic.getReviews(plain_id.intValue());
				if (reviews.size() > 0) templateProcessor.addToRoot("reviews", reviews);
    			templateProcessor.addToRoot("movie", m);
    			templateProcessor.setTemplate("view_movie.ftl");
    			templateProcessor.processTemplate(response);
    		} // if
    	} // for
    } // checkForMovieInquiry
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("viewAll") != null) runViewAll(response);
		else if (request.getParameter("viewGenre") != null) runViewGenres(response);
		else if (request.getParameter("addNewMovie") != null) runAddNewMovie(request, response);
		else if (request.getParameter("searchGenre") != null) runViewMoviesFromGenres(request, response);
		else if (request.getParameter("home") !=  null) response.sendRedirect("index.html");
		else if (request.getParameter("newReview") != null) runAddNewReview(request, response);
		else if (request.getParameter("newMovie") != null) runNewMovie(response);
		else if (request.getParameter("search") != null) runTitleSearch() // TO DO
		else checkForMovieInquiry(request, response);
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} // doPost

} // ReviewServlet

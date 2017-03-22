package edu.uga.cs4300.boundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;

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
    
    /**
     * Set up the server's configuration and template processor.
     */
    public void init() {
    	cfg = new Configuration(Configuration.getVersion());
    	cfg.setNumberFormat("0.######");
    	templateProcessor = new TemplateProcessor(cfg, getServletContext());
    } // init
 
    /**
     * Run procedure for viewing all movie titles.
     * @param response should be the servlet's current response object.
     */
    public void runViewAll(HttpServletResponse response) {
      	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	ArrayList<Movie> movies = movieLogic.getAllMovies();
    	templateProcessor.addToRoot("movies", movies);
    	templateProcessor.addToRoot("category", "All Movies");
    	templateProcessor.setTemplate("view.ftl");
    	templateProcessor.processTemplate(response);
    } // runViewAll
    
    /**
     * Run procedure for viewing movies out of a selection of genres.
     * @param request
     * @param response
     */
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
    
    /**
     * Run procedure for viewing a list of existing genres.
     * @param response
     */
    public void runViewGenres(HttpServletResponse response) {
    	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	ArrayList<String> genres = movieLogic.getGenreList();
    	templateProcessor.addToRoot("genres", genres);
    	templateProcessor.setTemplate("genre.ftl");
    	templateProcessor.processTemplate(response);
    } // runViewGenres
    
    /**
     * Run procedure for viewing form to submitting a new movie.
     * @param response
     */
    public void runNewMovie(HttpServletResponse response) {
    	templateProcessor.setTemplate("new_movie.ftl");
    	templateProcessor.processTemplate(response);
    } // runNewMovie
    
    /**
     * Run procedure for movie insertion into the database.
     * @param request
     * @param response
     */
    public void runAddNewMovie(HttpServletRequest request, HttpServletResponse response) {
    	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	String success = movieLogic.insertMovie(request) ? "Movie was successfully added!" : "Sorry, movie was not added due to empty form submission. Please try again.";
    	templateProcessor.addToRoot("success", success);
    	runNewMovie(response);
    } // addNewMovie
     
    /**
     * Run procedure for adding a new review to a movie.
     * @param request
     * @param response
     */
    public void runAddNewReview(HttpServletRequest request, HttpServletResponse response) {
    	ReviewLogicImpl reviewLogic = new ReviewLogicImpl();
    	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	String success = reviewLogic.insertReview(request) ? "Review was successfully added!" : "Sorry, review was not added due to empty form submission. Please try again.";
    	int movie_id = Integer.parseInt(request.getParameter("movieId"));
    		
    	// Run template
    	Movie movie = movieLogic.getMovie(movie_id);
    	ArrayList<Review> reviews = reviewLogic.getReviews(movie_id);
    	templateProcessor.addToRoot("movie", movie);
    	templateProcessor.addToRoot("success", success);
    	templateProcessor.addToRoot("reviews", reviews);
    	templateProcessor.setTemplate("view_movie.ftl");
    	templateProcessor.processTemplate(response);
    } // runAddNewReview
    
    /**
     * Run procedure for checking which movie was selected for viewing.
     * @param request
     * @param response
     */
    private void checkForMovieInquiry(HttpServletRequest request, HttpServletResponse response) {    	
    	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	ArrayList<Review> reviews = new ArrayList<Review>();
    	ReviewLogicImpl reviewLogic = new ReviewLogicImpl();
    	Enumeration<String> params = request.getParameterNames();
    	int id = Integer.parseInt(params.nextElement());
    	Movie movie = movieLogic.getMovie(id);
    	reviews = reviewLogic.getReviews(id);
    	if (reviews.size() > 0) templateProcessor.addToRoot("reviews", reviews);
		templateProcessor.addToRoot("movie", movie);
		templateProcessor.setTemplate("view_movie.ftl");
		templateProcessor.processTemplate(response);
    } // checkForMovieInquiry
    
    /**
     * Run procedure for retrieving a selection of movies based on the provided title.
     * @param request
     * @param response
     */
    public void runTitleSearch(HttpServletRequest request, HttpServletResponse response) {
    	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	ArrayList<Movie> movies = movieLogic.getMovie(request.getParameter("searchForTitle"));
     	templateProcessor.addToRoot("movies", movies);
     	templateProcessor.addToRoot("category", "Movies matching title \"" + request.getParameter("searchForTitle") + "\"");
     	templateProcessor.setTemplate("view.ftl");
     	templateProcessor.processTemplate(response);    	 
    } // runTitleSearch

    /**
     * Run procedure for displaying a form for editing movie information.
     * @param request
     * @param response
     */
    public void runEditMovie(HttpServletRequest request, HttpServletResponse response) {
    	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	int id = Integer.parseInt(request.getParameter("movieId"));
		Movie movie = movieLogic.getMovie(id);
		templateProcessor.addToRoot("movie", movie);
		templateProcessor.setTemplate("edit_movie.ftl");
		templateProcessor.processTemplate(response);
    } // runEditMovie
    
    /**
     * Run procedure for updating a movie once its been edited.
     * @param request
     * @param response
     */
    public void updateMovie(HttpServletRequest request, HttpServletResponse response) {
    	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	ReviewLogicImpl reviewLogic = new ReviewLogicImpl();
    	String success = movieLogic.updateMovie(request) ? "Movie has been successfully edited!" : "Movie could not be edited due to empty form submission.";
    	ArrayList<Review> reviews = new ArrayList<Review>();
		int id = Integer.parseInt(request.getParameter("movieId"));
		Movie movie = movieLogic.getMovie(id);
    	reviews = reviewLogic.getReviews(id);
    	if (reviews.size() > 0) templateProcessor.addToRoot("reviews", reviews);
    	templateProcessor.addToRoot("success", success);
    	templateProcessor.addToRoot("movie", movie);
    	templateProcessor.setTemplate("view_movie.ftl");
    	templateProcessor.processTemplate(response);
    } // runEditMovie

    /**
     * Run procedure for deleting a movie.
     * @param request
     * @param response
     */
    public void deleteMovie(HttpServletRequest request, HttpServletResponse response) {
    	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	ReviewLogicImpl reviewLogic = new ReviewLogicImpl();
    	reviewLogic.deleteAllReviews(request);
    	movieLogic.deleteMovie(request);
    	runViewAll(response);
    } // deleteMovie
    
    /**
     * Run procedure for deleting a selection of reviews.
     * @param request
     * @param response
     */
    public void deleteReviews(HttpServletRequest request, HttpServletResponse response) {
    	MovieLogicImpl movieLogic = new MovieLogicImpl();
    	ReviewLogicImpl reviewLogic = new ReviewLogicImpl();
    	reviewLogic.deleteReview(request);
    	Movie movie = movieLogic.getMovie(Integer.parseInt(request.getParameter("movieId")));
    	ArrayList<Review> reviews = reviewLogic.getReviews(Integer.parseInt(request.getParameter("movieId")));
    	if (reviews.size() > 0) templateProcessor.addToRoot("reviews", reviews);
    	templateProcessor.addToRoot("movie", movie);
    	templateProcessor.setTemplate("view_movie.ftl");
    	templateProcessor.processTemplate(response);
    } // deleteReviews
    
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
		else if (request.getParameter("search") != null) runTitleSearch(request, response); 
		else if (request.getParameter("updateMovie") != null) updateMovie(request, response);
		else if (request.getParameter("editMovie") != null) runEditMovie(request, response);
		else if (request.getParameter("deleteMovie") != null) deleteMovie(request, response);
		else if (request.getParameter("deleteReviews") != null) deleteReviews (request, response);
		else checkForMovieInquiry(request, response);
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} // doPost

} // ReviewServlet

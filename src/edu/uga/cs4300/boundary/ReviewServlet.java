package edu.uga.cs4300.boundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uga.cs4300.logiclayer.MovieLogicImpl;
import edu.uga.cs4300.objectlayer.Movie;
import freemarker.template.Configuration;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateProcessor templateProcessor = null;
	private Configuration cfg = null;
	private HttpServletRequest currentRequest = null;
	private HttpServletResponse currentResponse = null;
	
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
 
    public void runViewAll() {
      	MovieLogicImpl movieLogic = new MovieLogicImpl(this);
    	ArrayList<Movie> movies = movieLogic.getAllMovies();
    	templateProcessor.addToRoot("movies", movies);
    	templateProcessor.setTemplate("view.ftl");
    	templateProcessor.processTemplate(getCurrentResponse());
    } // runViewAll
    
    public void runViewGenre() {
    	
    } // runViewGenre
    
    public void runNewMovie() {
    	
    } // runNewMovie
    
	/**
	 * @return the currentRequest
	 */
	public HttpServletRequest getCurrentRequest() {
		return currentRequest;
	} // getCurrentRequest

	/**
	 * @return the currentResponse
	 */
	public HttpServletResponse getCurrentResponse() {
		return currentResponse;
	} // getCurrentResponse

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		currentRequest = request;
		currentResponse = response;
		if (request.getParameter("viewAll") != null) runViewAll();
		else if (request.getParameter("viewGenre") != null) runViewGenre();
		else if (request.getParameter("newMovie") != null) runNewMovie();
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} // doPost

} // ReviewServlet

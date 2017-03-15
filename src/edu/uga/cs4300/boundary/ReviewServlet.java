package edu.uga.cs4300.boundary;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateProcessor templateProcessor = null;
	private Configuration cfg = null;
	private String templateDir = "/WEB-INF/templates";
	
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
    	
    } // runViewAll
    
    public void runViewGenre() {
    	
    } // runViewGenre
    
    public void runNewMovie() {
    	
    } // runNewMovie
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

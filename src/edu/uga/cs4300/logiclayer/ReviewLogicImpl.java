package edu.uga.cs4300.logiclayer;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import edu.uga.cs4300.objectlayer.Review;
import edu.uga.cs4300.persistlayer.ReviewPersistImpl;

/**
 * This class acts as the control layer for interacting with the review objects within the database.
 * @author dustin
 *
 */

public class ReviewLogicImpl {
	
	private ReviewPersistImpl reviewPersistence = new ReviewPersistImpl();

	/**
	 * Attempt to insert new reviews into the database.
	 * @param request
	 * @return
	 */
	public boolean insertReview(HttpServletRequest request) {
		if (request.getParameter("reviewContent").equals("")) return false;
    	int movie_id = Integer.parseInt(request.getParameter("movieId"));
		StringBuilder content = new StringBuilder(request.getParameter("reviewContent"));
		for (int i = 0; i < content.length(); i++) { 
			if (content.charAt(i) == '\'') content.insert(i+1, '\''); // Accounting for apostrophes for insertion.
			i++;
		} // for
		reviewPersistence.insertReview(movie_id, content.toString());
		return true;
	} // insertReview
	
	/**
	 * Retrieve a list of reviews associated with a movie.
	 * @param movie_id is the id of the movie, as provided within the database.
	 * @return a list of reviews associated with the movie
	 */
	public ArrayList<Review> getReviews(int movie_id) {
		return reviewPersistence.getReviews(movie_id);
	} // getReviews
	
	/**
	 * Delete all reviews associated with a movie.
	 * @param request
	 */
	public void deleteAllReviews(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("movieId"));
		reviewPersistence.deleteAllReviews(id);
	} // deleteAllReviews
	
	/**
	 * Delete a selection of reviews.
	 * @param request
	 * @return true if worked, false if not.
	 */
	public boolean deleteReview(HttpServletRequest request) {
		Enumeration<String> submittedIds = request.getParameterNames();
		ArrayList<Integer> reviewId = new ArrayList<Integer>();
		while (submittedIds.hasMoreElements()) {
			String element = submittedIds.nextElement();
			if (element.equals("movieId") || element.equals("deleteReviews")) continue;
			reviewId.add(Integer.parseInt(element));
		} // while
		if (reviewId.size() == 0) return false;
		reviewPersistence.deleteReview(reviewId);
		return true;
	} // deleteReview
	
} // ReviewPersistImpl

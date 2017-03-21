package edu.uga.cs4300.logiclayer;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import edu.uga.cs4300.objectlayer.Review;
import edu.uga.cs4300.persistlayer.ReviewPersistImpl;

public class ReviewLogicImpl {
	
	private ReviewPersistImpl reviewPersistence = new ReviewPersistImpl();

	public boolean insertReview(HttpServletRequest request) {
		if (request.getParameter("reviewContent").equals("")) return false;
    	int movie_id = -1;
    	try {
    		movie_id = NumberFormat.getNumberInstance(Locale.US).parse(request.getParameter("movieId")).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		} // try-catch
		StringBuilder content = new StringBuilder(request.getParameter("reviewContent"));
		for (int i = 0; i < content.length(); i++) {
			if (content.charAt(i) == '\'') content.insert(i+1, '\'');
			i++;
		} // for
		reviewPersistence.insertReview(movie_id, content.toString());
		return true;
	} // insertReview
	
	public ArrayList<Review> getReviews(int movie_id) {
		return reviewPersistence.getReviews(movie_id);
	} // getReviews
	
} // ReviewPersistImpl

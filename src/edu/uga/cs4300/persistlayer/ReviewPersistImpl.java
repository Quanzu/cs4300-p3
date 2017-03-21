package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uga.cs4300.objectlayer.Review;

public class ReviewPersistImpl {

	/**
	 * Persist a review associated with a particular movie.
	 * @param movie_id is the id of the movie to be associated with.
	 * @param content is the content of the review
	 */
	public void insertReview(int movie_id, String content) {
		Connection con = DbAccessImpl.connect();
		DbAccessImpl.create("INSERT INTO REVIEWS (movie_id, review) VALUES (" + movie_id + ", '" + content + "')", con);
		DbAccessImpl.disconnect(con);
	} // insertReview
	
	/**
	 * @param movie_id should be an id that is persistent within the database.
	 * @return a list of all reviews associated with the provided movie id.
	 */
	public ArrayList<Review> getReviews(int movie_id) {
		Connection con = DbAccessImpl.connect();
		ArrayList<Review> reviews = new ArrayList<Review>();
		ResultSet result = DbAccessImpl.retrieve("SELECT * FROM REVIEWS WHERE MOVIE_ID = " + movie_id, con);
		try {
			while(result.next()) {
				Review review = new Review(result.getInt(1), result.getInt(2), result.getString(3));
				reviews.add(review);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		DbAccessImpl.disconnect(con);
		return reviews;
	} // getReviews
	
} // ReviewPersistImpl

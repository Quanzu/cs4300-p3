package edu.uga.cs4300.objectlayer;

/**
 * This class represents reviews of a movie left by creators of the website.
 * @author Dustin Nguyen
 */

public class Review {
	private int id = -1;
	private int movieId = -1;
	private String content = null;
	
	public Review(int id, int movieId, String content) {
		this.id = id;
		this.movieId = movieId;
		this.content = content;
	} // Review

	/**
	 * @return the id of the movie by which this review is associated with.
	 */
	public int getMovieId() {
		return movieId;
	} // getMovieId

	/**
	 * @param movieId is not validated by this function. The caller should ensure that
	 * the id is valid before passing it through this function.
	 */
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	} // setMovieId

	/**
	 * @return the content of the review.
	 */
	public String getContent() {
		return content;
	} // getContent

	/**
	 * @param content is not validated by this function. Any requirements for the content
	 * should be checked by the caller.
	 */
	public void setContent(String content) {
		this.content = content;
	} // setContent

	/**
	 * @return the id of the review, given when persisted into the database.
	 */
	public int getId() {
		return id;
	} // getId

	/**
	 * @param id should not be set to any value other than the value given to it when persisted into the database.
	 */
	public void setId(int id) {
		this.id = id;
	} // setId
	
} // Review

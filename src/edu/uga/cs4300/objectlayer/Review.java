package edu.uga.cs4300.objectlayer;

/**
 * This class represents reviews of a movie left by creators of the website.
 * @author Dustin Nguyen
 */

public class Review {
	private int movieId = -1;
	private String content = null;
	private String author = null;
	
	public Review(int movieId, String content, String author) {
		this.movieId = movieId;
		this.content = content;
		this.author = author;
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
	 * @return the author of the review
	 */
	public String getAuthor() {
		return author;
	} // getAuthor

	/**
	 * @param author is not validated in this function, but should not equal null. 
	 */
	public void setAuthor(String author) {
		this.author = author;
	} // setAuthor
	
} // Review

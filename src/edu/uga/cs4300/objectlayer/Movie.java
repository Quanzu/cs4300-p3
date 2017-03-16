package edu.uga.cs4300.objectlayer;

/**
 * This class represents a movie whose information can be added to the database.
 * @author Dustin Nguyen
 */

public class Movie {
	private int id = -1;
	private String name = null;
	private int year = -1;
	private float rank = -1;
	private String genre = null;
	
	public Movie(int id, String name, int year, float rank, String genre) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.rank = rank;
	} // Movie

	/**
	 * @return the id of the movie, which serves as a unique identifier in the database.
	 */
	public int getId() {
		return id;
	} // getId

	/**
	 * @param id should be a value retrieved from the database, not defined by the user.
	 */
	public void setId(int id) {
		this.id = id;
	} // setId

	/**
	 * @return the movie's title name.
	 */
	public String getName() {
		return name;
	} // getName

	/**
	 * @param name is the movie's title name.
	 */
	public void setName(String name) {
		this.name = name;
	} // setName

	/**
	 * @return the year this movie was released.
	 */
	public int getYear() {
		return year;
	} // getYear

	/**
	 * @param year is the year this movie was released. This method does not check the year, and
	 * trusts the caller to ensure that the year is within a valid timeline.
	 */
	public void setYear(int year) {
		this.year = year;
	} // setYear

	/**
	 * @return the movie's overall rating.
	 */
	public float getRank() {
		return rank;
	} // getRank
	
	/**
	 * @param rank should be greater than or equal to 0.
	 */
	public void setRank(float rank) {
		this.rank = rank;
	} // setRank

	/**
	 * @return the genre of the movie.
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param the genre of the movie.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

} // Movie

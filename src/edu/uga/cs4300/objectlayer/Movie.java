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
	private static final double FLOAT_PRECISION = 1;
	
	public Movie(int id, String name, int year, float rank, String genre) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.rank = rank;
		this.genre = genre;
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
	} // getGenre

	/**
	 * @param the genre of the movie.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	} // setGenre
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(rank);
		result = prime * result + year;
		return result;
	} // hashCode

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(rank) != Float.floatToIntBits(other.rank))
			return false;
		if (year != other.year)
			return false;
		return true;
	} // equals

} // Movie

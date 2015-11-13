package org.wahlzeit.model;

/**
 * The Coordinate class represents a Coordinate, where a Photo was taken
 */
public abstract class AbstractCoordinate implements Coordinate{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Gets the distance between this Coordiante and coord in kilometers
     * @methodtype get
     */
	public abstract double getDistance(Coordinate coord);
	
	/**
	 * @methodtype query
	 */
	public abstract boolean isEqual(Coordinate coord);


	
}

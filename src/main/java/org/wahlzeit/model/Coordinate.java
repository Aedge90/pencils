package org.wahlzeit.model;

/**
 * The Coordinate class represents a Coordinate, where a Photo was taken
 */

public interface Coordinate {
	
	 /**
     * Gets the distance between this Coordiante and coord in kilometers
     * @methodtype get
     */
	public double getDistance(Coordinate coord);
	
	/**
	 * @methodtype query
	 */
	public boolean isEqual(Coordinate coord);

}

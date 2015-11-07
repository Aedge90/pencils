package org.wahlzeit.model;

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

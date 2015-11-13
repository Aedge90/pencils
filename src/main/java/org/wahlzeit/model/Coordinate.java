package org.wahlzeit.model;

import java.io.Serializable;

/**
 * The Coordinate class represents a Coordinate, where a Photo was taken
 * and defines methods to get lat, lon, radius from every kind of coordinate
 */

public interface Coordinate extends Serializable{
	
	 /**
	 * @return latitude in degrees
    * @methodtype get
    */
	public abstract double getLatitude();
	
	 /**
	 * @return longitude in degrees
    * @methodtype get
    */
	public abstract double getLongitude();
	
	/**
	* @return radius in km
   * @methodtype get
   */
	public abstract double getRadius();
	
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

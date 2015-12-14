/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

/**
 * The SphericCoordinate class stores a spheric represenation of a coordinate
 */

public class SphericCoordinate extends AbstractCoordinate{

	/** 
	 * The radius of earth in kilometers
	 */
    public static final double EARTH_RADIUS = 6371;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Latitude in degrees
	 * Valid values: -90 (south pole) to 90 (north pole)
	 */
	private double latitude;
	
	/**
	 * Longitude in degrees
	 * Valid values: -180 (180 not included) to +180 (+ = Ost, - = West, 0 = Greenwich)
	 */
	private double longitude;
	
	/**
	 * Radius in km. Default is Earth radius (6371 km)
	 */
	private double radius;
	
	
	private SphericCoordinate(double latitude, double longitude, double radius){
		//preconditions
		assertInRangeLatitude(latitude);
		assertInRangeLongitude(longitude);
		assertInRangeRadius(radius);
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
	}
	
	public static SphericCoordinate getCoordinate(double latitude, double longitude) {
		return getCoordinate(latitude, longitude, EARTH_RADIUS);
	}
	
	public static SphericCoordinate getCoordinate(double latitude, double longitude, double radius) {
		String coordString = "spheric" + latitude + "" + longitude + "" + radius;
		SphericCoordinate result = (SphericCoordinate) allCoordinates.get(coordString);
		if (result == null) {
			synchronized (SphericCoordinate.class) {
				result = (SphericCoordinate) allCoordinates.get(coordString);
				if (result == null) {
					result = new SphericCoordinate(latitude, longitude, radius);
					allCoordinates.put(coordString, result);
				}
			}
		}
		return result;
	}
	
	/**
	* @return latitude in degrees
	* @methodtype get
	*/
	public double getLatitude(){
		return latitude;
	}
	
	/**
	* @return longitude in degrees
	* @methodtype get
	*/
	public double getLongitude(){
		return longitude;
	}
	
	/**
	* @return radius in km
	* @methodtype get
	*/
	public double getRadius(){
		return radius;
	}
	
	/**
	* @param latitude in degrees
	* @methodtype set
	*/
	public SphericCoordinate setLatitude(double latitude){
		//precondition
		assertInRangeLatitude(latitude);
		return new SphericCoordinate(latitude, this.longitude, this.radius);
	}
	
	/**
	* @param longitude in degrees
	* @methodtype set
	*/
	public SphericCoordinate setLongitude(double longitude){
		//precondition
		assertInRangeLongitude(longitude);
		return new SphericCoordinate(this.latitude, longitude, this.radius);
	}
	
	/**
	* @param radius in km
	* @methodtype set
	*/
	public SphericCoordinate setRadius(double radius){
		//precondition
		assertInRangeRadius(radius);
		return new SphericCoordinate(this.latitude, this.longitude, radius);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj;
	}



}

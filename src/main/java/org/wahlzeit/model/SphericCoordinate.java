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
	private final double latitude;
	
	/**
	 * Longitude in degrees
	 * Valid values: -180 (180 not included) to +180 (+ = Ost, - = West, 0 = Greenwich)
	 */
	private final double longitude;
	
	/**
	 * Radius in km. Default is Earth radius (6371 km)
	 */
	private double radius;
	
	
	public SphericCoordinate(double latitude, double longitude){
		this(latitude, longitude, EARTH_RADIUS);
	}
	
	public SphericCoordinate(double latitude, double longitude, double radius){
		if (latitude > 90 || latitude < -90) {
			throw new IllegalArgumentException("Latitude value is not valid. Range:[-90,90]");
		}
		if (longitude > 180 || longitude <= -180) {
			throw new IllegalArgumentException("Longitude value is not valid. Range:[-180,180]");	
		}
		if (radius < 0) {
			throw new IllegalArgumentException("Radius value is not valid. Range:[0,infinity[");	
		}
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SphericCoordinate other = (SphericCoordinate) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		if (Double.doubleToLongBits(radius) != Double
				.doubleToLongBits(other.radius))
			return false;
		return true;
	}



}

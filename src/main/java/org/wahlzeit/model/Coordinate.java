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

import org.wahlzeit.services.DataObject;

/**
 * The Coordinate class represents a Coordinate, where a Photo was taken
 */


public class Coordinate extends DataObject {


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
	 * Valid values: -180 to +180 (+ = Ost, - = West, 0 = Greenwich)
	 */
	private final double longitude;
	
	public Coordinate(double latitude, double longitude){
		if (latitude > 90 || latitude < -90) {
			throw new IllegalArgumentException("Latitude value is not valid. Range:[-90,90]");
		}
		if (longitude > 180 || longitude < -180) {
			throw new IllegalArgumentException("Longitude value is not valid. Range:[-180,180]");	
		}
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getDistance(Coordinate coord) {
		if(coord == null){
			throw new IllegalArgumentException("Argument was null");
		}
		return getLatitudinalDistance(coord) + getLongitudinalDistance(coord);
	}
	
	public double getLatitudinalDistance(Coordinate coord) {
		if(coord == null){
			throw new IllegalArgumentException("Argument was null");
		}
		return Math.abs(coord.latitude - latitude);
	}
	
	public double getLongitudinalDistance(Coordinate coord) {
		if(coord == null){
			throw new IllegalArgumentException("Argument was null");
		}
		return Math.abs(coord.longitude - longitude);
	}
	
	public double getLatitude(){
		return latitude;
	}
	
	public double getLongitude(){
		return longitude;
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
		Coordinate other = (Coordinate) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		return true;
	}
	
}

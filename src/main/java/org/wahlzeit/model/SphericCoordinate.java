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

import com.googlecode.objectify.annotation.Entity;
import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import org.wahlzeit.services.ObjectManager;

/**
 * The Coordinate class represents a Coordinate, where a Photo was taken
 */

@Entity
public class SphericCoordinate extends DataObject implements Coordinate {
	
    @Id
    String objectifyId = "idShpericCoordinate";
    @Parent
    Key parent = ObjectManager.applicationRootKey;
	
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
     * Gets the distance between this Coordiante and coord in kilometers
     * @throws IllegalArgumentException Thrown if coord is null
     * @methodtype get
     */
	@Override
	public double getDistance(Coordinate coord) {
		if(coord == null){
			throw new IllegalArgumentException("Argument was null");
		}
		SphericCoordinate coordSpheric;
		if(coord.getClass().equals(CartesianCoordinate.class)){
			//this is an instance of CartesianCoordinate so casting will work.
			CartesianCoordinate coordCartesian = (CartesianCoordinate) coord;
			//convert this Coordinate to SphericCoordinate
			coordSpheric = coordCartesian.toSpheric();
		}else{
			//this is an instance of SphericCoordinate so casting will work.
			coordSpheric = (SphericCoordinate) coord; 
		}
		return radius * Math.acos(
				(Math.sin(Math.toRadians(this.getLatitude())) * Math.sin(Math.toRadians(coordSpheric.getLatitude()))) +
				(Math.cos(Math.toRadians(this.getLatitude())) * Math.cos(Math.toRadians(coordSpheric.getLatitude())) *
						Math.cos(Math.toRadians(Math.abs(coordSpheric.getLongitude() - this.getLongitude())))) 
				) ;
	}

	@Override
	public boolean isEqual(Coordinate coord) {
		if (coord == null)
			return false;
		SphericCoordinate coordSpheric;
		if(coord.getClass().equals(CartesianCoordinate.class)){
			//this is an instance of CartesianCoordinate so casting will work.
			CartesianCoordinate coordCartesian = (CartesianCoordinate) coord;
			//convert this Coordinate to SphericCoordinate
			coordSpheric = coordCartesian.toSpheric();
		}else{
			//this is an instance of SphericCoordinate so casting will work.
			coordSpheric = (SphericCoordinate) coord; 
		}
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(coordSpheric.latitude)){
			return false;
		}
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(coordSpheric.longitude)){
			return false;
		}
		if (Double.doubleToLongBits(radius) != Double
				.doubleToLongBits(coordSpheric.radius)){
			return false;
		}
		return true;
	}
	
	/**
	 * Converts spheric representation of coordinates to cartesian representation
     * @methodtype conversion
     */
	public CartesianCoordinate toCartesian(){
		double x = Math.cos(latitude) * Math.cos(longitude);
		double y = Math.cos(latitude) * Math.sin(longitude);
		double z = Math.sin(latitude);
		return new CartesianCoordinate(x,y,z);
	}
	
	
	 /**
     * @methodtype get
     */
	public double getLatitude(){
		return latitude;
	}
	
	 /**
     * @methodtype get
     */
	public double getLongitude(){
		return longitude;
	}
	
	 /**
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

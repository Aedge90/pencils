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
 * The Coordinate class represents a Coordinate, where a Photo was taken
 */


public class Coordinate {

	private double latitude;
	private double longitude;
	
	public Coordinate(double latitude, double longitude){
		
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Coordinate getDistance(Coordinate coord) {
		if(coord == null){
			throw new IllegalArgumentException("Argument was null");
		}
		return new Coordinate(getLatitudinalDistance(coord),getLongitudinalDistance(coord));
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
}

package org.wahlzeit.model;


import com.googlecode.objectify.annotation.Container;


public class Location {
	
	/**
	 * The name of the Location
	 */
	private String name;
	
	/**
	 * The corresponding coordinate
	 */
	//@Container
	//protected Coordinate coordinate;	

    public Location(String name, Coordinate coordinate) {

        if(name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("name must not be null or empty");
        }

        if(coordinate == null) {
            throw new IllegalArgumentException("coordinate must not be null");
        }

        this.name = name;
        //this.coordinate = coordinate;
    }

    
    /**
    * @methodtype get
    */
   public String getName() {
       return this.name;
   }

   /**
    * @methodtype get
    */
   //public Coordinate getCoordinate() {
   //    return this.coordinate;
   //}
}

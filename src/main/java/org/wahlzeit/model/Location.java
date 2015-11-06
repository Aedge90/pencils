package org.wahlzeit.model;

import org.wahlzeit.services.ObjectManager;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Location {
	
    @Id
    String objectifyId = "idLocation";
    @Parent
    Key parent = ObjectManager.applicationRootKey;
	
	/**
	 * The name of the Location
	 */
	private String name;
	
	/**
	 * The corresponding coordinate
	 */
	protected Coordinate coordinate;	

    public Location(String name, Coordinate coordinate) {

        if(name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("name must not be null or empty");
        }

        if(coordinate == null) {
            throw new IllegalArgumentException("coordinate must not be null");
        }

        this.name = name;
        this.coordinate = coordinate;
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
   public Coordinate getCoordinate() {
       return this.coordinate;
   }
}

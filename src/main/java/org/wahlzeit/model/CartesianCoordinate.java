package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.services.ObjectManager;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

/**
 * The CartesianCoordinate class stores a cartesian represenation of a coordinate
 */

@Entity
public class CartesianCoordinate extends DataObject implements Coordinate{
	
    @Id
    String objectifyId = "idCartesianCoordinate";
    @Parent
    Key parent = ObjectManager.applicationRootKey;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 
	 * x in kilometers
	 */
	double x;

	/** 
	 * y in kilometers
	 */
	double y;
	
	/** 
	 * z in kilometers
	 */
	double z;
	
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
		SphericCoordinate thisCoordSpheric = this.toSpheric();
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
		return thisCoordSpheric.getRadius() * Math.acos(
				(Math.sin(Math.toRadians(thisCoordSpheric.getLatitude())) * Math.sin(Math.toRadians(coordSpheric.getLatitude()))) +
				(Math.cos(Math.toRadians(thisCoordSpheric.getLatitude())) * Math.cos(Math.toRadians(coordSpheric.getLatitude())) *
						Math.cos(Math.toRadians(Math.abs(coordSpheric.getLongitude() - thisCoordSpheric.getLongitude())))) 
				) ;
	}
	
	@Override
	public boolean isEqual(Coordinate coord) {
		if (coord == null)
			return false;
		CartesianCoordinate coordCartesian;
		if(coord.getClass().equals(SphericCoordinate.class)){
			//this is an instance of SphericCoordinate so casting will work.
			SphericCoordinate coordSpheric = (SphericCoordinate) coord;
			//convert this Coordinate to CartesianCoordinate
			coordCartesian = coordSpheric.toCartesian();
		}else{
			//this is an instance of CartesianCoordinate so casting will work.
			coordCartesian = (CartesianCoordinate) coord; 
		}
		if (Math.abs(x-coordCartesian.x) > 0.1){
			return false;
		}
		if (Math.abs(y-coordCartesian.y) > 0.1){
			return false;
		}
		if (Math.abs(z-coordCartesian.z) > 0.1){
			return false;
		}
		return true;
	}
	
	/**
	 * Converts cartesian representation of coordinates to spheric representation
     * @methodtype conversion
     */
	public SphericCoordinate toSpheric(){
		double latRad = Math.atan2(z, Math.sqrt(x*x + y*y));
		double lonRad = Math.atan2(y, x);
		return new SphericCoordinate(Math.toDegrees(latRad), Math.toDegrees(lonRad), Math.sqrt(x*x + y*y + z*z));
	}
	
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return the z
	 */
	public double getZ() {
		return z;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
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
		CartesianCoordinate other = (CartesianCoordinate) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
	
	
	

}

package org.wahlzeit.model;

/**
 * The CartesianCoordinate class stores a cartesian represenation of a coordinate
 */

public class CartesianCoordinate extends AbstractCoordinate{

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
	
	/**
	* @param x
	* @methodtype set
	*/
	public void setX(double x){
		this.x = x;
	}
	
	/**
	* @param y
	* @methodtype set
	*/
	public void setY(double y){
		this.y = y;
	}
	
	/**
	* @param z
	* @methodtype set
	*/
	public void setZ(double z){
		this.z = z;
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

	 /**
	 * @return latitude in degrees
     * @methodtype get
     */
	@Override
	public double getLatitude() {
		double latitude = Math.toDegrees(Math.atan2(z, Math.sqrt(x*x + y*y)));
		//postcondition
		assertInRangeLatitude(latitude);
		return latitude;
	}

	 /**
	 * @return longitude in degrees
     * @methodtype get
     */
	@Override
	public double getLongitude() {
		double longitude = Math.toDegrees(Math.atan2(y, x));
		//postcondition
		assertInRangeLongitude(longitude);
		return longitude;
	}

	 /**
	  * @return radius in km
     * @methodtype get
     */
	@Override
	public double getRadius() {
		double radius = Math.sqrt(x*x + y*y + z*z);
		//postcondition
		assertInRangeRadius(radius);
		return radius;
	}
	

}

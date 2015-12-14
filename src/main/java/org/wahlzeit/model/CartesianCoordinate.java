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
	private double x;

	/** 
	 * y in kilometers
	 */
	private double y;
	
	/** 
	 * z in kilometers
	 */
	private double z;
	
	private CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static CartesianCoordinate getCoordinate(double x, double y, double z) {
		String coordString = "cartesian" + x + "" + y + "" + z;
		CartesianCoordinate result = (CartesianCoordinate) allCoordinates.get(coordString);
		if (result == null) {
			synchronized (CartesianCoordinate.class) {
				result = (CartesianCoordinate) allCoordinates.get(coordString);
				if (result == null) {
					result = new CartesianCoordinate(x,y,z);
					allCoordinates.put(coordString, result);
				}
			}
		}
		return result;
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
	public CartesianCoordinate setX(double x){
		return new CartesianCoordinate(x, this.y, this.z);
	}
	
	/**
	* @param y
	* @methodtype set
	*/
	public CartesianCoordinate setY(double y){
		return new CartesianCoordinate(this.x, y, this.z);
	}
	
	/**
	* @param z
	* @methodtype set
	*/
	public CartesianCoordinate setZ(double z){
		return new CartesianCoordinate(this.x, this.y, z);
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
		return this == obj;
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

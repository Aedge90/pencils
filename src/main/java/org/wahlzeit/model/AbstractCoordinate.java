package org.wahlzeit.model;

/**
 * The AbstractCoordinate class
 */
public abstract class AbstractCoordinate implements Coordinate{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Gets the distance between this Coordiante and coord in kilometers
     * @methodtype get
     */
	public double getDistance(Coordinate coord) {
		assertNotNull(coord);
		assertSameRadius(this.getRadius(), coord.getRadius());
		return this.getRadius() * Math.acos(
				(Math.sin(Math.toRadians(this.getLatitude())) * Math.sin(Math.toRadians(coord.getLatitude()))) +
				(Math.cos(Math.toRadians(this.getLatitude())) * Math.cos(Math.toRadians(coord.getLatitude())) *
						Math.cos(Math.toRadians(Math.abs(coord.getLongitude() - this.getLongitude())))) 
				) ;
	}
	
	protected static void assertSameRadius(double radius1, double radius2) throws IllegalArgumentException {
		if (Math.abs(radius1 - radius2) > 0.1) {
			throw new IllegalArgumentException("Different Radius");
		}
	}
	
	protected static void assertNotNull(Coordinate coord) throws IllegalArgumentException {
		if (coord == null) {
			throw new IllegalArgumentException("Argument was null");
		}
	}
	
	protected static void assertInRangeLatitude(double latitude) throws IllegalArgumentException {
		if (latitude > 90 || latitude < -90) {
			throw new IllegalArgumentException("Latitude value is not valid. Range:[-90,90]");
		}
	}
	
	protected static void assertInRangeLongitude(double longitude) throws IllegalArgumentException {
		if (longitude > 180 || longitude <= -180) {
			throw new IllegalArgumentException("Longitude value is not valid. Range:[-180,180]");	
		}
	}
	
	protected static void assertInRangeRadius(double radius) throws IllegalArgumentException {
		if (radius < 0) {
			throw new IllegalArgumentException("Radius value is not valid. Range:[0,infinity[");	
		}
	}
	
	@Override
	public boolean isEqual(Coordinate coord) {
		if (coord == null){
			return false;
		}
		if (Math.abs(this.getLatitude() - coord.getLatitude()) > 0.1){
			return false;
		}
		if (Math.abs(this.getLongitude() - coord.getLongitude()) > 0.1){
			return false;
		}
		if (Math.abs(this.getRadius() - coord.getRadius()) > 0.1){
			return false;
		}
		return true;
	}
	
}

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
		if(coord == null){
			throw new IllegalArgumentException("Argument was null");
		}
		return this.getRadius() * Math.acos(
				(Math.sin(Math.toRadians(this.getLatitude())) * Math.sin(Math.toRadians(coord.getLatitude()))) +
				(Math.cos(Math.toRadians(this.getLatitude())) * Math.cos(Math.toRadians(coord.getLatitude())) *
						Math.cos(Math.toRadians(Math.abs(coord.getLongitude() - this.getLongitude())))) 
				) ;
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

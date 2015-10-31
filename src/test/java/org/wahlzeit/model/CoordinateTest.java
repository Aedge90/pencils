package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test class for Coordinate class.
 */
public class CoordinateTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	protected Coordinate firstCoord;
	protected Coordinate secondCoord = new Coordinate(10.3, 90.6);

	@Before
	public void setUp() {
		firstCoord = new Coordinate(40,60);
		secondCoord = new Coordinate(10.3, 90.6);
	}
	
	
	@Test
	public void testGetDistance() {
		assertEquals("should be 60.3", 60.3, firstCoord.getDistance(secondCoord), 0.1);	}
	
	@Test
	public void testGetLatitudinalDistance() {
		assertEquals("should be 29.7", 29.7, firstCoord.getLatitudinalDistance(secondCoord),0.1);
	}
	
	@Test
	public void testGetLongitudinalDistance() {
		assertEquals("should be 30.6", 30.6, firstCoord.getLongitudinalDistance(secondCoord),0.1);
	}
	
	@Test
	public void testGetDistanceWithNull() {
	    exception.expect(IllegalArgumentException.class);
	    firstCoord.getDistance(null);
	}
	
	@Test
	public void testGetLatitudinalDistanceWithNull() {
	    exception.expect(IllegalArgumentException.class);
	    firstCoord.getLatitudinalDistance(null);
	}
	
	@Test
	public void testGetLongitudinalDistanceWithNull() {
	    exception.expect(IllegalArgumentException.class);
	    firstCoord.getLongitudinalDistance(null);
	}

}

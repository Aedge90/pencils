package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * Test class for SphericCoordinate class.
 */
public class SphericCoordinateTest {
	
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testGetDistance() {
		double lat = 50.0;
		double lon = -32.0;
		double radius = 4500.0;
		SphericCoordinate sphericCoord1 = new SphericCoordinate(lat, lon, radius);
		SphericCoordinate sphericCoord2 = new SphericCoordinate(lat - 42 , lon + 80, radius);
		
		assertEquals(6083.5823081996, sphericCoord1.getDistance(sphericCoord2), 0.1);
		assertEquals(6083.5823081996, sphericCoord2.getDistance(sphericCoord1), 0.1);
	}
	
	@Test
	public void testIsEqual() {
		double lat = 42.0;
		double lon = 63.8;
		double radius = 36.453;
		SphericCoordinate equalSphericCoord1 = new SphericCoordinate(lat, lon, radius);
		SphericCoordinate equalSphericCoord2 = new SphericCoordinate(lat, lon, radius);
		SphericCoordinate nonequalSphericCoord1 = new SphericCoordinate(lat + 1 , lon, radius);
		SphericCoordinate nonequalSphericCoord2 = new SphericCoordinate(lat, lon + 1, radius);
		SphericCoordinate nonequalSphericCoord3 = new SphericCoordinate(lat, lon, radius + 1);
		
		assertTrue(equalSphericCoord1.isEqual(equalSphericCoord2));
		assertTrue(equalSphericCoord2.isEqual(equalSphericCoord1));
			
		assertFalse(equalSphericCoord1.isEqual(null));
		assertFalse(equalSphericCoord1.isEqual(nonequalSphericCoord1));
		assertFalse(equalSphericCoord1.isEqual(nonequalSphericCoord2));
		assertFalse(equalSphericCoord1.isEqual(nonequalSphericCoord3));
	}
	
	@Test
	public void testSetLatLonRadOutOfBounds() {
		SphericCoordinate sphericCoord = new SphericCoordinate(-1, 4);
		try {
			sphericCoord.setLatitude(-100);
			fail("Should have thrown an IllegalArgumentException!");
		} catch (IllegalArgumentException e) {}
		try {
			sphericCoord.setLongitude(-200);
			fail("Should have thrown an IllegalArgumentException!");
		} catch (IllegalArgumentException e) {}
		try {
			sphericCoord.setLatitude(+100);
			fail("Should have thrown an IllegalArgumentException!");
		} catch (IllegalArgumentException e) {}
		try {
			sphericCoord.setLongitude(+200);
			fail("Should have thrown an IllegalArgumentException!");
		} catch (IllegalArgumentException e) {}
		try {
			sphericCoord.setRadius(-100);
			fail("Should have thrown an IllegalArgumentException!");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	public void testGetDistanceWithNull() {
		SphericCoordinate sphericCoord = new SphericCoordinate(-1, 4);
	    exception.expect(IllegalArgumentException.class);
	    sphericCoord.getDistance(null);
	}

	@Test
	public void testGetDistanceDifferentRadius(){
		SphericCoordinate sphericCoord1 = new SphericCoordinate(-1, 4,100);
		SphericCoordinate sphericCoord2 = new SphericCoordinate(-1, 4,4354);
	    exception.expect(IllegalArgumentException.class);
	    sphericCoord1.getDistance(sphericCoord2);
	}
	
}


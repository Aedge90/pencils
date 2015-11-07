package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * Test class for Coordinate class.
 */
public class CoordinateTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private final LocalServiceTestHelper helper =
		    new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
	    helper.setUp();
	}
	

	@After
	public void tearDown() {
	    helper.tearDown();
	}
	
	@Test
	public void testIsEqualSpheric() {
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
	public void testIsEqualCartesian() {
		double x = 42.0;
		double y = 63.8;
		double z = 36.453;
		CartesianCoordinate equalCartesianCoord1 = new CartesianCoordinate(x, y, z);
		CartesianCoordinate equalCartesianCoord2 = new CartesianCoordinate(x, y, z);
		CartesianCoordinate nonequalCartesianCoord1 = new CartesianCoordinate(x + 1 , y, z);
		CartesianCoordinate nonequalCartesianCoord2 = new CartesianCoordinate(x, y + 1, z);
		CartesianCoordinate nonequalCartesianCoord3 = new CartesianCoordinate(x, y, z + 1);
		
		assertTrue(equalCartesianCoord1.isEqual(equalCartesianCoord2));
		assertTrue(equalCartesianCoord2.isEqual(equalCartesianCoord1));
			
		assertFalse(equalCartesianCoord1.isEqual(null));
		assertFalse(equalCartesianCoord1.isEqual(nonequalCartesianCoord1));
		assertFalse(equalCartesianCoord1.isEqual(nonequalCartesianCoord2));
		assertFalse(equalCartesianCoord1.isEqual(nonequalCartesianCoord3));
	}
	
	@Test
	public void testIsEqualCartesianAndSpheric() {

	}
	

	@Test
	public void testGetDistanceWithNullCartesianAndSpheric() {
		CartesianCoordinate cartesianCoord = new CartesianCoordinate(3, 4, 1);
		SphericCoordinate sphericCoord = new SphericCoordinate(-1, 4);
	    exception.expect(IllegalArgumentException.class);
	    cartesianCoord.getDistance(null);
	    exception.expect(IllegalArgumentException.class);
	    sphericCoord.getDistance(null);
	}

}

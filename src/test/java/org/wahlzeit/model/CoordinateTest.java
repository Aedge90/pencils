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
		double x = 2616.5280574416615;
		double y = 1764.8704593015705;
		double z = -2648.2849519085416;
		double lat = -40.0;
		double lon = 34.0;
		double radius = 4120.0;

		Coordinate equalSphericCoord = new SphericCoordinate(lat, lon, radius);
		Coordinate equalCartesianCoord = new CartesianCoordinate(x, y, z);
		
		Coordinate nonequalSphericCoord1 = new SphericCoordinate(lat + 1 , lon, radius);
		Coordinate nonequalSphericCoord2 = new SphericCoordinate(lat, lon + 1, radius);
		Coordinate nonequalSphericCoord3 = new SphericCoordinate(lat, lon, radius + 1);
		Coordinate nonequalCartesianCoord1 = new CartesianCoordinate(x + 1 , y, z);
		Coordinate nonequalCartesianCoord2 = new CartesianCoordinate(x, y + 1, z);
		Coordinate nonequalCartesianCoord3 = new CartesianCoordinate(x, y, z + 1);

		assertTrue(equalSphericCoord.isEqual(equalCartesianCoord));
		assertTrue(equalCartesianCoord.isEqual(equalSphericCoord));
			
		assertFalse(equalCartesianCoord.isEqual(null));
		assertFalse(equalCartesianCoord.isEqual(nonequalSphericCoord1));
		assertFalse(equalCartesianCoord.isEqual(nonequalSphericCoord2));
		assertFalse(equalCartesianCoord.isEqual(nonequalSphericCoord3));
		
		assertFalse(equalSphericCoord.isEqual(null));
		assertFalse(equalSphericCoord.isEqual(nonequalCartesianCoord1));
		assertFalse(equalSphericCoord.isEqual(nonequalCartesianCoord2));
		assertFalse(equalSphericCoord.isEqual(nonequalCartesianCoord3));
	}
	
	@Test
	public void testGetDistanceSpheric() {
		double lat = 50.0;
		double lon = -32.0;
		double radius = 4500.0;
		Coordinate sphericCoord1 = new SphericCoordinate(lat, lon, radius);
		Coordinate sphericCoord2 = new SphericCoordinate(lat - 42 , lon + 80, radius);
		
		assertEquals(6083.5823081996, sphericCoord1.getDistance(sphericCoord2), 0.1);
		assertEquals(6083.5823081996, sphericCoord2.getDistance(sphericCoord1), 0.1);
	}	
	
	@Test
	public void testGetDistanceCartesian() {
		double x = -4500;
		double y = 0;
		double z = 0;
		Coordinate cartesianCoord1 = new CartesianCoordinate(x, y, z);
		Coordinate cartesianCoord2 = new CartesianCoordinate(0, 4500, 0);
		
		assertEquals(7068.583470577, cartesianCoord1.getDistance(cartesianCoord2), 0.1);
		assertEquals(7068.583470577, cartesianCoord2.getDistance(cartesianCoord1), 0.1);
	}	
	
	@Test
	public void testGetDistanceCartesianAndSpheric() {
		double lat = 50.0;
		double lon = -32.0;
		double radius = 4500.0;
		Coordinate sphericCoord = new SphericCoordinate(lat, lon, radius);
		Coordinate sphericCoord2 = new SphericCoordinate(lat - 42 , lon + 80, radius);
		
		//this works, as it was tested before
		Coordinate cartesianCoord = ((SphericCoordinate)sphericCoord2).toCartesian();
		
		assertEquals(6083.5823081996, sphericCoord.getDistance(cartesianCoord), 0.1);
		assertEquals(6083.5823081996, cartesianCoord.getDistance(sphericCoord), 0.1);
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

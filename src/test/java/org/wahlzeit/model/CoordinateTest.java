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
 * Test class for Coordinate interface.
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
	public void testIsEqual() {
		double x = 2616.5280574416615;
		double y = 1764.8704593015705;
		double z = -2648.2849519085416;
		double lat = -40.0;
		double lon = 34.0;
		double radius = 4120.0;

		Coordinate equalSphericCoord = SphericCoordinate.getCoordinate(lat, lon, radius);
		Coordinate equalCartesianCoord = CartesianCoordinate.getCoordinate(x, y, z);
		
		Coordinate nonequalSphericCoord1 = SphericCoordinate.getCoordinate(lat + 1 , lon, radius);
		Coordinate nonequalSphericCoord2 = SphericCoordinate.getCoordinate(lat, lon + 1, radius);
		Coordinate nonequalSphericCoord3 = SphericCoordinate.getCoordinate(lat, lon, radius + 1);
		Coordinate nonequalCartesianCoord1 = CartesianCoordinate.getCoordinate(x + 1 , y, z);
		Coordinate nonequalCartesianCoord2 = CartesianCoordinate.getCoordinate(x, y + 1, z);
		Coordinate nonequalCartesianCoord3 = CartesianCoordinate.getCoordinate(x, y, z + 1);

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
	public void testGetDistance() {
		double lat = 50.0;
		double lon = -32.0;
		double radius = 4500.0;
		Coordinate sphericCoord = SphericCoordinate.getCoordinate(lat, lon, radius);

		Coordinate cartesianCoord = CartesianCoordinate.getCoordinate(2981.7840298268816, 3311.606660043558, 626.2789543202945);
		
		assertEquals(6083.5823081996, sphericCoord.getDistance(cartesianCoord), 0.1);
		assertEquals(6083.5823081996, cartesianCoord.getDistance(sphericCoord), 0.1);
	}	

}

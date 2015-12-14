package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
 * Test class for CartesianCoordinate class.
 */
public class CartesianCoordinateTest {
	
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testGetDistance() {
		double x = -4500;
		double y = 0;
		double z = 0;
		CartesianCoordinate cartesianCoord1 = CartesianCoordinate.getCoordinate(x, y, z);
		CartesianCoordinate cartesianCoord2 = CartesianCoordinate.getCoordinate(0, 4500, 0);
		
		assertEquals(7068.583470577, cartesianCoord1.getDistance(cartesianCoord2), 0.1);
		assertEquals(7068.583470577, cartesianCoord2.getDistance(cartesianCoord1), 0.1);
	}	
	
	@Test
	public void testIsEqualCartesian() {
		double x = 42.0;
		double y = 63.8;
		double z = 36.453;
		CartesianCoordinate equalCartesianCoord1 = CartesianCoordinate.getCoordinate(x, y, z);
		CartesianCoordinate equalCartesianCoord2 = CartesianCoordinate.getCoordinate(x, y, z);
		CartesianCoordinate nonequalCartesianCoord1 = CartesianCoordinate.getCoordinate(x + 1 , y, z);
		CartesianCoordinate nonequalCartesianCoord2 = CartesianCoordinate.getCoordinate(x, y + 1, z);
		CartesianCoordinate nonequalCartesianCoord3 = CartesianCoordinate.getCoordinate(x, y, z + 1);
		
		assertTrue(equalCartesianCoord1.isEqual(equalCartesianCoord2));
		assertTrue(equalCartesianCoord2.isEqual(equalCartesianCoord1));
			
		assertFalse(equalCartesianCoord1.isEqual(null));
		assertFalse(equalCartesianCoord1.isEqual(nonequalCartesianCoord1));
		assertFalse(equalCartesianCoord1.isEqual(nonequalCartesianCoord2));
		assertFalse(equalCartesianCoord1.isEqual(nonequalCartesianCoord3));
	}

	@Test
	public void testGetLatLonRad() {
		try{
			for(double i = -50000; i<50000; i += 10000){
				for(double j = -50000; j<50000; j += 10000){
					for(double k = -50000; k<50000; k += 10000){
						CartesianCoordinate coord = CartesianCoordinate.getCoordinate(i, j, k);
						coord.getLatitude();
						coord.getLongitude();
						coord.getRadius();
					}
				}
			}
		}catch(IllegalArgumentException e){
			fail("computed Lat Lon or Radius was out of Bounds");
		}
	}
	
	@Test
	public void testGetDistanceWithNull() {
		CartesianCoordinate cartesianCoord = CartesianCoordinate.getCoordinate(3, 4, 1);
	    exception.expect(IllegalArgumentException.class);
	    cartesianCoord.getDistance(null);
	}
}

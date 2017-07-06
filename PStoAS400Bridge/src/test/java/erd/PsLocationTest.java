package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.model.PsLocation;

public class PsLocationTest {

	@Test
	public void testFindCountryByLocation() {
		String location = "1005071";
		String result = PsLocation.findCountryByLocation(location);
		assertNotNull(result);
		System.out.println("\nresult = " + result);
	}

}

package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.model.PsDiversityEthnicity;

public class PsDiversEthnicTest {

	@Test
	public void testPsDiversEthnic() {
		String employeeId = "100600";
		String result = PsDiversityEthnicity.findEthnicGroupCodeByEmployeeId(employeeId);
		assertNotNull(result);
		System.out.println(result);
	}

}

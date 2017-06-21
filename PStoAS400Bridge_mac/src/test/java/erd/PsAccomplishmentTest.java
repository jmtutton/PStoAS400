package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.model.PsAccomplishment;

public class PsAccomplishmentTest {

	@Test
	public void testPsAccomplishment() {
		String employeeId = "717967";
		String accomplishmentCode = "DRUGTST";
		PsAccomplishment result = PsAccomplishment.findByEmployeeIdAndAccomplishmentCode(employeeId, accomplishmentCode);
		assertNotNull(result);
		System.out.println(result);
	}

}

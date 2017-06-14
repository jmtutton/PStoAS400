package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.model.PsActionReason;
import erd.model.PsCitizenship;

public class PsCitizenshipTest {

	@Test
	public void testPsCitizenship() {
		String employeeId = "100600";
		String countryCode = "USA";
		PsCitizenship result = PsCitizenship.findByEmployeeIdAndCountryCode(employeeId, countryCode);
		assertNotNull(result);
		System.out.println(result);
	}

}

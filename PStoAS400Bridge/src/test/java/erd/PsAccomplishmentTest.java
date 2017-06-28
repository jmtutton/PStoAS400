package erd;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import erd.model.PsAccomplishment;

public class PsAccomplishmentTest {

	@Test
	public void testPsAccomplishment() {
		String employeeId = "526147";
		List<String> accomplishmentCodeList = Arrays.asList("DRUGTST", "PHYS L3", "PHYS L4");
		PsAccomplishment result = PsAccomplishment.findByEmployeeIdAndAccomplishmentCodes(employeeId, accomplishmentCodeList);
		assertNotNull(result);
		System.out.println(result.toString());
	}

}

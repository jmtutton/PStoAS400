package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.model.PsContractData;

public class PsContractDataTest {

	@Test
	public void testFindByEmployeeId() {
		String employeeId = "518380";
		PsContractData result = PsContractData.findByEmployeeId(employeeId);
		assertNotNull(result);
		System.out.println(result);
	}

}

package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.model.HR100P;

public class HR100PTest {

	@Test
	public void testFindByEmployeeIdAndAddedDateAndChangedByProgramAndAddedByEmployeeId() {
		String employeeId = "99996";
		String addedDate = "20170710";
		String progWithError = "HRZ202A   ";
		String addedByEmployeeId = "208T1";
		HR100P result = HR100P.findByEmployeeIdAndAddedDateAndChangedByProgWithErrorAndAddedByEmployeeId(
				employeeId, addedDate, progWithError, addedByEmployeeId);
		assertNotNull(result);
		System.out.println("result.getAddedTime(): " + result.getAddedTime());
	}

}

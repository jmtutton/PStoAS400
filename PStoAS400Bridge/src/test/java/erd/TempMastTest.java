package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.model.TempMast;

public class TempMastTest {

	@Test
	public void testFindByEmployeeIdAndChangedDateAndChangedByProgramAndChangedByEmployeeId() {
		String employeeId = "500V8";
		String changedDate = "20170710";
		String changedByProgram = "HRZ202A   ";
		String changedByEmployeeId = "208T1";
		TempMast result = TempMast.findByEmployeeIdAndChangedDateAndChangedByProgramAndChangedByEmployeeId(
				employeeId, changedDate, changedByProgram, changedByEmployeeId);
		assertNotNull(result);
		System.out.println("result.getChangedTime(): " + result.getChangedTime());
	}

}

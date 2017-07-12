package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.model.CrossReferenceJobCode;

public class CrossReferenceJobCodeTest {

	@Test
	public void testFindActiveBySetIdJobCodeAndJobCodeAndEmployeeClassAndFullOrPartTimeAndRegularOrTemporaryAndDepartment() {
		String setIdJobCode = "CPEFM";
		String jobCode = "FM0022";
		String employeeClass = "F";
		String fullOrPartTime = "F";
		String regularOrTemporary = "R";
		String department = "20000";
		CrossReferenceJobCode result = 
				CrossReferenceJobCode.findActiveBySetIdJobCodeAndJobCodeAndEmployeeClassAndFullOrPartTimeAndRegularOrTemporaryAndDepartment(
						setIdJobCode, jobCode, employeeClass, fullOrPartTime, regularOrTemporary, department);
		assertNotNull(result);
	}

}

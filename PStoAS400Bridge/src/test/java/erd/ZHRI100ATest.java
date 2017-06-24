package erd;

import org.junit.Test;

import erd.controller.ZHRI100A;
import erd.model.Zhri100aFields;

public class ZHRI100ATest {

//	@Test
//	public void testZHRI100ABuiltWhereClauseDelay() {
//		String result = ZHRI100A.ZHRI100ABuiltWhereClauseDelay();
//		Assert.assertNotNull(result);
//		System.out.println(result);
//	}

	@Test
	public void testZHRI100A_callErrorRoutine() {
		String processName = "ZHRI100A";
		Zhri100aFields zhri100aFields = ZHRI100A.initializeMainProperties();
		zhri100aFields.setErrorMessageParameter("Error executing Call System command, contact HR-PeopleSoft Oncall");
		ZHRI100A.ZHRI100A_callErrorRoutine(processName, zhri100aFields);
	}

	@Test
	public void testZHRI100A_for_termination() {
		ZHRI100A.main();
	}
}

package erd;

import org.junit.Test;

import erd.controller.ZHRI100A;
import erd.model.ProcessParameters.CommonParameters;

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
		CommonParameters commonParameters = ZHRI100A.initializeCommonParameters();
		commonParameters.setErrorMessageParameter("Error executing Call System command, contact HR-PeopleSoft Oncall");
		commonParameters.setProcessName(processName);
		ZHRI100A.ZHRI100A_callErrorRoutine(commonParameters);
	}

	@Test
	public void testZHRI100A_for_termination() {
		ZHRI100A.main();
	}
}

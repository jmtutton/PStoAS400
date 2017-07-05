package erd;

import java.util.HashMap;

import org.junit.Test;

import erd.controller.NonPersonTermination;

public class NonPersonTerminateTest {

	@Test
	public void testDoProcess() {
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		NonPersonTermination nonPersonTermination = new NonPersonTermination();
		nonPersonTermination.doProcess(parameterMap);
	}

}

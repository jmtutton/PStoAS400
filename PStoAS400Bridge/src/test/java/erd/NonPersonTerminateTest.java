package erd;

import org.junit.Test;

import erd.controller.NonPersonTermination;
import erd.model.ProcessParameters.CommonParameters;

public class NonPersonTerminateTest {

	@Test
	public void testHr202ProcessMain() {
		CommonParameters commonParameters = null;
		NonPersonTermination nonPersonTermination = new NonPersonTermination();
		nonPersonTermination.processNonPersonTermination(commonParameters);
	}

}

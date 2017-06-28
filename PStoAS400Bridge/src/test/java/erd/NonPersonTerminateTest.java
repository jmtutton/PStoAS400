package erd;

import org.junit.Test;

import erd.controller.NonPersonTermination;
import erd.model.PszTriggerNonPerson;
import erd.model.ProcessParameters.CommonParameters;

public class NonPersonTerminateTest {

	@Test
	public void testHr202ProcessMain() {
		PszTriggerNonPerson trigger = null;
		CommonParameters commonParameters = null;
		NonPersonTermination nonPersonTermination = new NonPersonTermination();
		nonPersonTermination.HR202_processMain(trigger, commonParameters);
	}

}

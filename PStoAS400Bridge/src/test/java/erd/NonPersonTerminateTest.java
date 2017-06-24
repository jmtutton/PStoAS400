package erd;

import org.junit.Test;

import erd.controller.NonPersonTermination;
import erd.model.PszTriggerNonPerson;
import erd.model.Zhri100aFields;

public class NonPersonTerminateTest {

	@Test
	public void testHr202ProcessMain() {
		PszTriggerNonPerson trigger = null;
		Zhri100aFields zhri100aFields = null;
		NonPersonTermination nonPersonTermination = new NonPersonTermination(trigger, zhri100aFields);
		nonPersonTermination.HR202_processMain();
	}

}

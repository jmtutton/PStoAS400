package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.model.PsActionReason;

public class PsActnReasonTblTest {

	@Test
	public void testPsActnReasonTbl() {
		String actionCode = "TER";
		String actionReasonCode = "OTH";
		PsActionReason result = PsActionReason.findByActionCodeAndActionReasonCode(actionCode, actionReasonCode);
		assertNotNull(result);
		System.out.println(result);
	}

}

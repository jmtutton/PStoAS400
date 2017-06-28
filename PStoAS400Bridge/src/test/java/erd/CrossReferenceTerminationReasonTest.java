package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.model.CrossReferenceTerminationReason;

public class CrossReferenceTerminationReasonTest {

	@Test
	public void testFindByActionAndActionReasonAndStatus() {
		String action = ""; 
		String actionReason = "";
		String status = "A";
		CrossReferenceTerminationReason result = CrossReferenceTerminationReason.findByActionAndActionReasonAndStatus(action, actionReason, status);
		assertNotNull(result);
	}

}

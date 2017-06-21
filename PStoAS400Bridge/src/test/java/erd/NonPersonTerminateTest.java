package erd;

import java.util.Date;

import org.junit.Test;

import erd.controller.NonPersonTerminate;

public class NonPersonTerminateTest {

	@Test
	public void testHr202ProcessMain() {
		String psAuditOperId = "e859v1";
		Integer indexNum = 0;
		Date psDateIn = new Date(new java.util.Date().getTime());
		NonPersonTerminate.hr202ProcessMain(psAuditOperId, indexNum, psDateIn);
	}

}

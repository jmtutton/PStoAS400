package erd;

import java.math.BigDecimal;
import java.sql.Date;

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

	@Test
	public void testHr202CallSystem() {
		String psAuditEmpl = "e859v1";
		String psOprId = "e859v1";
		Integer status = 0;
		Date psTermDate = new Date(new java.util.Date().getTime());
		status = NonPersonTerminate.hr202CallSystem(psAuditEmpl, psOprId, psTermDate);
		System.out.println("status: " + status);
	}

	@Test
	public void testHr202GetTermDate() {
		String wrkEmplId = "e859v1";
		BigDecimal wrkIndexNum = new BigDecimal(0);
		Date psDateIn = new Date(new java.util.Date().getTime());
		Date psTermDate;
		psTermDate = NonPersonTerminate.hr202GetTermDate(wrkEmplId, wrkIndexNum, psDateIn);
		System.out.println("psTermDate: " + psTermDate);
	}
}

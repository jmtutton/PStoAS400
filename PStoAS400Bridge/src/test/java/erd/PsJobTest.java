package erd;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Date;

import org.junit.Test;

import erd.model.PsJob;

public class PsJobTest {

	@Test
	public void testFindMaxEffectiveDateByEmployeeIdAndEmploymentRecordNumber() {
		String employeeId = "558610";
		BigDecimal employmentRecordNumber = new BigDecimal(0);
		Date result = PsJob.findMaxEffectiveDateByEmployeeIdAndEmploymentRecordNumber(employeeId, employmentRecordNumber);
		assertNotNull(result);
		System.out.println(result);
	}

	@Test
	public void testFindMaxEffectiveSequenceByEmployeeIdAndEmploymentRecordNumberAndEffectiveDate() {
		String employeeId = "99047";
		BigDecimal employmentRecordNumber = new BigDecimal(0);
		Date effectiveDate = PsJob.findMaxEffectiveDateByEmployeeIdAndEmploymentRecordNumber(employeeId, employmentRecordNumber);
		BigDecimal result = PsJob.findMaxEffectiveSequenceByEmployeeIdAndEmploymentRecordNumberAndEffectiveDate(employeeId, employmentRecordNumber, effectiveDate);
		assertNotNull(result);
		System.out.println(result);
	}

	@Test
	public void testEmployeeIsContractor() {
		//TODO: need a test case where EMPL_CLASS = 'R'
		String employeeId = "524338";
		BigDecimal employmentRecordNumber = new BigDecimal(0);
		Date effectiveDate = PsJob.findMaxEffectiveDateByEmployeeIdAndEmploymentRecordNumber(employeeId, employmentRecordNumber);
		BigDecimal effectiveSequence = PsJob.findMaxEffectiveSequenceByEmployeeIdAndEmploymentRecordNumberAndEffectiveDate(employeeId, employmentRecordNumber, effectiveDate);
		boolean result = PsJob.employeeIsContractor(employeeId, effectiveDate, effectiveSequence);
		assertNotNull(result);
//		assertTrue(result);
		System.out.println("\nresult = " + result);
	}

}

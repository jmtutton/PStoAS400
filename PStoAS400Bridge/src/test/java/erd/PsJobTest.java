package erd;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;

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
		boolean result = PsJob.employeeIsContractor(employeeId);
		assertNotNull(result);
//		assertTrue(result);
		System.out.println("\nresult = " + result);
	}

	@Test
	public void testHr04GetJobData() {
		String employeeId = "524338";
		Date effectiveDate = new java.util.Date();
		PsJob result = PsJob.hr04GetJobData(employeeId, effectiveDate);
		assertNotNull(result);
		System.out.println("\nresult = " + result);
	}

	@Test
	public void testHr02GetJob() {
		String employeeId = "36735";
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 9, 1);
		Date effectiveDate = cal.getTime();
		System.out.println("\neffectiveDate = " + effectiveDate);
		BigDecimal effectiveSequence = new BigDecimal(0);
//		PsJob result = PsJob.hr02GetJob(employeeId, new java.util.Date(effectiveDate.getTime()), effectiveSequence);
		PsJob result = PsJob.hr02GetJob(employeeId, effectiveDate, effectiveSequence);
		assertNotNull(result);
		System.out.println("\nresult = " + result);
	}

}

package erd;

import static org.junit.Assert.*;

import java.util.Date;
import java.math.BigDecimal;

import org.junit.Test;

import erd.model.PsJob;

public class PsJobTest {

	@Test
	public void testFindMaxEffectiveDateByEmployeeIdAndEmploymentRecordNumber() {
		String employeeId = "558610";
		Integer employmentRecordNumber = 0;
		Date result = PsJob.findMaxEffectiveDateByEmployeeIdAndEmploymentRecordNumber(employeeId, employmentRecordNumber);
		assertNotNull(result);
		System.out.println(result);
	}

	@Test
	public void testFindMaxEffectiveSequenceByEmployeeIdAndEmploymentRecordNumberAndEffectiveDate() {
		String employeeId = "99047";
		Integer employmentRecordNumber = 0;
		Date effectiveDate = PsJob.findMaxEffectiveDateByEmployeeIdAndEmploymentRecordNumber(employeeId, employmentRecordNumber);
		Integer result = PsJob.findMaxEffectiveSequenceByEmployeeIdAndEmploymentRecordNumberAndEffectiveDate(employeeId, employmentRecordNumber, effectiveDate);
		assertNotNull(result);
		System.out.println(result);
	}

	@Test
	public void testCheckIfContractor() {
		//TODO: need a test case where EMPL_CLASS = 'R'
		String employeeId = "524338";
		boolean result = PsJob.ZHRI100A_checkIfContractor(employeeId);
		assertNotNull(result);
		assertFalse(result);
		System.out.println("\nresult: " + result);
	}

	@Test
	public void testCheckIfCorrect102A() {
		String employeeId = "349NV";
		Date effectiveDate = new Date();
		String processName = "ZHRI102A";
		boolean result = PsJob.ZHRI100A_checkIfCorrect102A(employeeId, effectiveDate, processName);
		assertNotNull(result);
		assertTrue(result);
		System.out.println("\nresult: " + result);
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
		String employeeId = "343526";
		Date effectiveDate = new Date();
		BigDecimal effectiveSequence = new BigDecimal(0);
		PsJob result = PsJob.getJob(employeeId, effectiveDate, effectiveSequence);
		assertNotNull(result);
	}

}

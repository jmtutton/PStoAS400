package erd;

import static org.junit.Assert.*;

import java.util.Date;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import erd.model.PsJob;

public class PsJobTest {

//	@Test
//	public void testFindMaxEffectiveDateByEmployeeIdAndEmploymentRecordNumber() {
//		String employeeId = "558610";
//		Integer employmentRecordNumber = 0;
//		Date result = PsJob.findMaxEffectiveDateByEmployeeIdAndEmploymentRecordNumber(employeeId, employmentRecordNumber);
//		assertNotNull(result);
//		System.out.println(result);
//	}

//	@Test
//	public void testFindMaxEffectiveSequenceByEmployeeIdAndEmploymentRecordNumberAndEffectiveDate() {
//		String employeeId = "99047";
//		Integer employmentRecordNumber = 0;
//		Date effectiveDate = PsJob.findMaxEffectiveDateByEmployeeIdAndEmploymentRecordNumber(employeeId, employmentRecordNumber);
//		Integer result = PsJob.findMaxEffectiveSequenceByEmployeeIdAndEmploymentRecordNumberAndEffectiveDate(employeeId, employmentRecordNumber, effectiveDate);
//		assertNotNull(result);
//		System.out.println(result);
//	}

	@Test
	public void testCheckIfContractor() {
		//TODO: need a test case where EMPL_CLASS = 'R'
		String employeeId = "524338";
		boolean result = PsJob.employeeIsContractor(employeeId);
		assertNotNull(result);
		assertFalse(result);
		System.out.println("\nresult: " + result);
	}

	@Test
	public void testCheckIfCorrect102A() {
		String employeeId = "349NV";
		Date effectiveDate = new Date();
		String processName = "ZHRI102A";
		boolean result = PsJob.correspondingJobRecordExists(employeeId, effectiveDate, processName);
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
		String employeeId = "323506";
		Date effectiveDate = new Date();
		try {
			effectiveDate = (new SimpleDateFormat("dd-MMM-yyyy")).parse("14-JUN-2017");
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		Date effectiveDatePlusOne = erd.DateUtil.addDays(effectiveDate, 1);
		BigInteger effectiveSequence = new BigInteger("0");
		PsJob result = PsJob.findByEmployeeIdAndEffectiveDateAndEffectiveSequence(employeeId, effectiveDatePlusOne, effectiveSequence);
		assertNotNull(result);
	}
	
	@Test
	public void testFindRegulatoryRegionByEmployeeId() {
		String employeeId = "323506";
		String result = PsJob.findRegulatoryRegionByEmployeeId(employeeId);
		assertNotNull(result);
		System.out.println("\nresult = " + result);
	}
	
	@Test
	public void testFindJobData() throws ParseException {
		String employeeId = "100310";
		Date effectiveDate = (new SimpleDateFormat("dd-MMM-yyyy")).parse("01-DEC-2017");
		System.out.println("employeeId = " + employeeId);
		System.out.println("effectiveDate = " + effectiveDate);
		PsJob result = PsJob.findJobData(employeeId, effectiveDate);
		assertNotNull(result);
		System.out.println("\nresult = " + result);
	}


}

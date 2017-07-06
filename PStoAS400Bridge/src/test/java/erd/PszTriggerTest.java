package erd;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import erd.model.PszTriggerEmployee;
import erd.model.PszTriggerNonPerson;

public class PszTriggerTest {
	
	@Test
	public void testFindBySequenceNumber_TriggerEmployee() {
		final BigDecimal SEQ_NUM = new BigDecimal(9073256);
		PszTriggerEmployee empTrig = (PszTriggerEmployee) PszTriggerEmployee.findBySequenceNumber(SEQ_NUM);
		assertNotNull(empTrig);
		assertEquals(empTrig.getSequenceNumber(), SEQ_NUM);
		System.out.println(empTrig.toString());
	}

	@Test
	public void testFindBySequenceNumber_TriggerNonPerson() {
		final BigDecimal SEQ_NUM = new BigDecimal(503);
		PszTriggerEmployee empTrig = (PszTriggerEmployee) PszTriggerEmployee.findBySequenceNumber(SEQ_NUM);
		assertNotNull(empTrig);
		assertEquals(empTrig.getSequenceNumber(), SEQ_NUM);
		System.out.println(empTrig.toString());
	}

//	@Test
//	public void testFindAll_NonPerson() {
//		@SuppressWarnings("unchecked")
//		List<PszTriggerNonPerson> empTrigList = (List<PszTriggerNonPerson>) PszTriggerNonPerson.findAll();
//		assertNotNull(empTrigList);
//	    for(PszTriggerSuperclass empTrig : empTrigList) {
//			System.out.println("SequenceNumber: " + empTrig.getSequenceNumber());
//	    }
//	}

	@Test
	public void testFindByCompletionStatusOrderBySequenceNumber_TriggerEmployee() {
		final String COMPLETION_STATUS = "P";
		List<PszTriggerEmployee> empTrigList = (List<PszTriggerEmployee>) PszTriggerEmployee.findByCompletionStatusOrderBySequenceNumber(COMPLETION_STATUS);
		assertNotNull(empTrigList);
	    for(PszTriggerEmployee empTrig : empTrigList) {
			System.out.println("CompletionStatus: " + empTrig.getCompletionStatus() + "  SequenceNumber: " + empTrig.getSequenceNumber());
	    }
	}

	@Test
	public void testFindByCompletionStatusOrderBySequenceNumber_TriggerNonPerson() {
		final String COMPLETION_STATUS = "P";	
		List<PszTriggerNonPerson> empTrigList = (List<PszTriggerNonPerson>) PszTriggerNonPerson.findByCompletionStatusOrderBySequenceNumber(COMPLETION_STATUS);
		assertNotNull(empTrigList);
	    for(PszTriggerNonPerson empTrig : empTrigList) {
			System.out.println("CompletionStatus: " + empTrig.getCompletionStatus() + "  SequenceNumber: " + empTrig.getSequenceNumber());
	    }
	}

//	@Test 
//	public void testFindAll_Employee() {
//		List<PszTriggerEmployee> empTrigList = (List<PszTriggerEmployee>) PszTriggerEmployee.findAll();
//		assertNotNull(empTrigList);
//	    for(PszTriggerEmployee empTrig : empTrigList) {
//			System.out.println("SequenceNumber: " + empTrig.getSequenceNumber());
//	    }
//	}

	@Test 
	public void testSetCompletionStatusBySequenceNumber_TriggerEmployee() {
		//TODO: don't have update privileges
		final String STATUS = "C";	
		final BigDecimal SEQ_NUM = new BigDecimal(9073256);
		int numUpdated = 0;
		numUpdated = PszTriggerEmployee.setCompletionStatusBySequenceNumber(STATUS, SEQ_NUM);
		assert(numUpdated > 0);
	}

	@Test 
	public void testfindTriggerEmployeeList() {
		List<PszTriggerEmployee> resultList = PszTriggerEmployee.findTriggerDataList();
		assertNotNull(resultList);
	}

	@Test 
	public void testCaseTest() {
		List<BigDecimal> resultList = PszTriggerEmployee.caseTest();
		assertNotNull(resultList);
		for(BigDecimal result : resultList) {
			System.out.println("SequenceNumber: " + result);
		}
	}

	@Test 
	public void testZHRI100A_checkIfPoiTermed() {
		String employeeId = "340243";
		Boolean result = PszTriggerNonPerson.isPoiToEmpTransfer(employeeId);
		assertNotNull(result);
		System.out.println("result: " + result);
	}
	

}

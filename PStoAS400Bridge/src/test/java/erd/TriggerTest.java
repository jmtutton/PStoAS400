package erd;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import erd.model.TriggerEmployee;
import erd.model.TriggerNonPerson;

public class TriggerTest {
	
	@Test
	public void testFindBySequenceNumber_TriggerEmployee() {
		final Integer SEQ_NUM = 9072140;
		TriggerEmployee empTrig = (TriggerEmployee) TriggerEmployee.findBySequenceNumber(SEQ_NUM);
		assertNotNull(empTrig);
		assertEquals(empTrig.getSequenceNumber(), SEQ_NUM);
		System.out.println(empTrig.toString());
	}

	@Test
	public void testFindBySequenceNumber_TriggerNonPerson() {
		final Integer SEQ_NUM = 503;
		TriggerNonPerson empTrig = (TriggerNonPerson) TriggerNonPerson.findBySequenceNumber(SEQ_NUM);
		assertNotNull(empTrig);
		assertEquals(empTrig.getSequenceNumber(), SEQ_NUM);
		System.out.println(empTrig.toString());
	}

//	@Test
//	public void testFindAll_NonPerson() {
//		@SuppressWarnings("unchecked")
//		List<TriggerNonPerson> empTrigList = (List<TriggerNonPerson>) TriggerNonPerson.findAll();
//		assertNotNull(empTrigList);
//	    for(TriggerSuperclass empTrig : empTrigList) {
//			System.out.println("SequenceNumber: " + empTrig.getSequenceNumber());
//	    }
//	}

	@Test
	public void testFindByCompletionStatusOrderBySequenceNumber_TriggerEmployee() {
		final String COMPLETION_STATUS = "P";
		List<TriggerEmployee> empTrigList = (List<TriggerEmployee>) TriggerEmployee.findByCompletionStatusOrderBySequenceNumber(COMPLETION_STATUS);
		assertNotNull(empTrigList);
	    for(TriggerEmployee empTrig : empTrigList) {
			System.out.println("CompletionStatus: " + empTrig.getCompletionStatus() + "  SequenceNumber: " + empTrig.getSequenceNumber());
	    }
	}

	@Test
	public void testFindByCompletionStatusOrderBySequenceNumber_TriggerNonPerson() {
		final String COMPLETION_STATUS = "P";	
		List<TriggerNonPerson> empTrigList = (List<TriggerNonPerson>) TriggerNonPerson.findByCompletionStatusOrderBySequenceNumber(COMPLETION_STATUS);
		assertNotNull(empTrigList);
	    for(TriggerNonPerson empTrig : empTrigList) {
			System.out.println("CompletionStatus: " + empTrig.getCompletionStatus() + "  SequenceNumber: " + empTrig.getSequenceNumber());
	    }
	}

	@Test 
	public void testFindAll_Employee() {
		List<TriggerEmployee> empTrigList = (List<TriggerEmployee>) TriggerEmployee.findAll();
		assertNotNull(empTrigList);
	    for(TriggerEmployee empTrig : empTrigList) {
			System.out.println("SequenceNumber: " + empTrig.getSequenceNumber());
	    }
	}

	@Test 
	public void testUpdateCompletionStatus_TriggerEmployee() {
		//TODO: don't have update privileges
		final String STATUS = "P";	
		final Integer SEQ_NUM = 9072090;
		int numUpdated = 0;
		numUpdated = TriggerEmployee.setCompletionStatusBySequenceNumber(STATUS, SEQ_NUM);
		assert(numUpdated > 0);

	}

}

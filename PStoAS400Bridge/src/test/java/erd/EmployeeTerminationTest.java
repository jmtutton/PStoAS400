package erd;

import org.junit.Test;

import erd.controller.Main;

public class EmployeeTerminationTest {

//	@Test
//	public void testBeginProgram() {
//		String completionStatus = "P";
//		String processName = "ZHRI102A";
//		List<PszTriggerEmployee> triggerList = PszTriggerEmployee.findByCompletionStatusAndProcessName(completionStatus, processName);
//		assertNotNull(triggerList);
//		CommonParameters commonParameters = ZHRI100A.initializeCommonParameters();
//		EmployeeTermination et = null;
//		PszTriggerEmployee trigger = null;
//		if(triggerList != null && !triggerList.isEmpty()) {
//			trigger = triggerList.get(0);
//			et = new EmployeeTermination();
//		}
//		else {
//			System.out.println("triggerList either null or empty");
//			java.util.Date effectiveDate = new java.util.Date();
//			java.util.Date sqlDate = new java.util.Date(effectiveDate.getTime());
//			BigInteger effectiveSequence = new BigInteger("0");
//			String employeeId = "347940";
//			String operatorId = "OPSHR";
//			BigInteger sequenceNumber = new BigInteger(90727260);
//			trigger = new PszTriggerEmployee();
//			trigger.setCompletionStatus(completionStatus);
//			trigger.setEffectiveDate(sqlDate);
//			trigger.setEffectiveSequence(effectiveSequence);
//			trigger.setEmployeeId(employeeId);
//			trigger.setOperatorId(operatorId);
//			trigger.setProcessName(processName);
//			trigger.setSequenceNumber(sequenceNumber);
//			et = new EmployeeTermination();
//		}
//		completionStatus = et.HR02_processMain(trigger, commonParameters);
//		System.out.println("************** completionStatus: " + completionStatus);
//		trigger = PszTriggerEmployee.findBySequenceNumber(trigger.getSequenceNumber());
//		System.out.println("************** trigger.completionStatus: " + trigger.getCompletionStatus());
//	}

	@Test
	public void testZHRI100A_main() {
		Main.main();
	}

}

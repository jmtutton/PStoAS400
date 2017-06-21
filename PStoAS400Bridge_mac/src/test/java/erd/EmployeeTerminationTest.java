package erd;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import erd.model.PszTriggerEmployee;

public class EmployeeTerminationTest {

	@Test
	public void testBeginProgram() {
		String completionStatus = "P";
		String processName = "ZHRI102A";
		List<PszTriggerEmployee> triggerList = PszTriggerEmployee.findByCompletionStatusAndProcessName(completionStatus, processName);
		assertNotNull(triggerList);
		if(triggerList != null && !triggerList.isEmpty()) {
//			TriggerEmployee trigger = triggerList.get(0);
//			EmployeeTermination et = new EmployeeTermination(trigger, null);
		}
		else {
			System.out.println("triggerList either null or empty");
			java.util.Date effectiveDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(effectiveDate.getTime());
			BigDecimal effectiveSequence = new BigDecimal(0);
			String employeeId = "347940";
			String operatorId = "OPSHR";
			Integer sequenceNumber = 90727260;
			PszTriggerEmployee trigger = new PszTriggerEmployee();
			trigger.setCompletionStatus(completionStatus);
			trigger.setEffectiveDate(sqlDate);
			trigger.setEffectiveSequence(effectiveSequence);
			trigger.setEmployeeId(employeeId);
			trigger.setOperatorId(operatorId);
			trigger.setProcessName(processName);
			trigger.setSequenceNumber(sequenceNumber);
//			EmployeeTermination et = new EmployeeTermination(trigger, null);
		}
	}

}

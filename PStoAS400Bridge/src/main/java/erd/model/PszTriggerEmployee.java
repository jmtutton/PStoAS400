package erd.model;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entity implementation class for PS_ZHRT_INTTRIGGER PeopleSoft employee event triggers table 
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name = "PS_ZHRT_INTTRIGGER")
@NamedQuery(name="PszTriggerEmployee.findAll", query="SELECT p FROM PszTriggerEmployee p")
public class PszTriggerEmployee extends PszTriggerSuperclass {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	   
	public PszTriggerEmployee() {
		super();
		ENTITY_NAME = "PszTriggerEmployee";
	}

	public static PszTriggerEmployee createMockTriggerForEmployeeTermination() {
		logger.debug("*** PszTriggerEmployee.createMockTriggerForEmployeeTermination()");
		BigInteger sequenceNumber = new BigInteger("9073256");
		String operatorId = "E208T1";
		String employeeId = "323506";
		Date effectiveDate = new Date();
		try {
			effectiveDate = (new SimpleDateFormat("dd-MMM-yyyy")).parse("14-JUN-2017");
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigInteger effectiveSequence = new BigInteger("0");
		String processName = "ZHRI102A";
		String completionStatus = "P";
		PszTriggerEmployee trigger = new PszTriggerEmployee();
		trigger.setCompletionStatus(completionStatus);
		trigger.setEffectiveDate(effectiveDate);
		trigger.setEffectiveSequence(effectiveSequence);
		trigger.setEmployeeId(employeeId);
		trigger.setOperatorId(operatorId);
		trigger.setProcessName(processName);
		trigger.setSequenceNumber(sequenceNumber);
		return trigger;
	}

	public static PszTriggerEmployee createMockTriggerForEmployeeNewHire() {
		logger.debug("*** PszTriggerEmployee.createMockTriggerForEmployeeTermination()");
		BigInteger sequenceNumber = new BigInteger("9073256");
		String operatorId = "E999X1";
		String employeeId = "323506";
		Date effectiveDate = new Date();
		try {
			effectiveDate = (new SimpleDateFormat("dd-MMM-yyyy")).parse("13-JUN-2017");
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigInteger effectiveSequence = new BigInteger("0");
		String processName = "ZHRI101A";
		String completionStatus = "P";
		PszTriggerEmployee trigger = new PszTriggerEmployee();
		trigger.setCompletionStatus(completionStatus);
		trigger.setEffectiveDate(effectiveDate);
		trigger.setEffectiveSequence(effectiveSequence);
		trigger.setEmployeeId(employeeId);
		trigger.setOperatorId(operatorId);
		trigger.setProcessName(processName);
		trigger.setSequenceNumber(sequenceNumber);
		return trigger;
	}

	/**
	 * Updates TASK_FLAG in PS_ZHRT_INTTRIGGER for given SEQ_NBR
	 * @see Update-Trigger-Row in ZHRI100A.SQR
	 * @param completionStatus
	 * @param sequenceNumber
	 * @return numberOfRecordsUpdated
	 */
	public static int updateTriggerRecord(String completionStatus, BigInteger sequenceNumber) {
		System.out.println("PszTriggerEmployee.setCompletionStatusBySequenceNumber()");
    	String triggerTypeClassName = MethodHandles.lookup().lookupClass().getSimpleName();
	    return PszTriggerSuperclass.setCompletionStatusBySequenceNumber(completionStatus, sequenceNumber, triggerTypeClassName);
	}
	
	/**
	 * @param sequenceNumber
	 * @return list of records from the PszTriggerEmployee table with matching sequenceNumber
	 */
	public static PszTriggerSuperclass findBySequenceNumber(BigInteger sequenceNumber) {
		System.out.println("PszTriggerEmployee.findBySequenceNumber()");
    	String triggerTypeClassName = MethodHandles.lookup().lookupClass().getSimpleName();
	    return PszTriggerSuperclass.findBySequenceNumber(sequenceNumber, triggerTypeClassName);
	}

}

package erd.model;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entity implementation class for PS_ZHRT_ALTTRIGGER PeopleSoft non-person event triggers table 
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name = "PS_ZHRT_ALTTRIGGER")
@NamedQuery(name="PszTriggerNonPerson.findAll", query="SELECT p FROM PszTriggerNonPerson p")
public class PszTriggerNonPerson extends PszTriggerSuperclass {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	
	//same properties as TriggerEmployee, with just this one extra field.  	   
	@Column(name="SEQUENCE", nullable=false, precision=38)
	private Integer eidIndexNumber;  //This field is the index (0-4) of an EID non-persons with multiple EIDs. 0 is the primary.

	public Integer getEidIndexNumber() {
		return eidIndexNumber;
	}

	public void setEidIndexNumber(Integer eidIndexNumber) {
		this.eidIndexNumber = eidIndexNumber;
	}

	public PszTriggerNonPerson() {
		super();
		PszTriggerNonPerson.ENTITY_NAME = "PszTriggerNonPerson";
	}
	
	@Override
	public String toString() {
		return "ENTITY_NAME: PszTriggerNonPerson\n" 
				+ "SequenceNumber: " + getSequenceNumber() + "\n"
				+ "OperatorId: " + getOperatorId() + "\n"
				+ "EmployeeId: " + getEmployeeId() + "\n"
				+ "EffectiveDate: " + getEffectiveDate() + "\n"
				+ "EffectiveSequence: " + getEffectiveSequence() + "\n"
				+ "ProcessName: " + getProcessName() + "\n"
				+ "CompletionStatus: " + getCompletionStatus() + "\n"
				+ "EidIndexNumber: " + getEidIndexNumber()
				;
	}
	
	/**
	 * This routine checks if it is a POI to EMP transfer. If it is then the the flag is changed to W and wait for Hire
	 * @see Check-If-POI-Termed in ZHRI100A.SQR
	 * @param employeeId
	 * @return true if record exists and completionStatus is 'C' or 'P'
	 */
	//TODO: 
	public static Boolean isPoiTermed(String employeeId) {
		logger.debug("*** PszTriggerNonPerson.ZHRI100A_checkIfPoiTermed()");
		//SELECT P.TASK_FLAG FROM PS_ZHRT_ALTTRIGGER P
		//WHERE P.PROC_NAME = 'ZHRI202A'
		//AND P.EMPLID = $PSEmplid AND P.SEQUENCE = 0
		//AND P.SEQ_NBR = 
				//(SELECT MAX(P2.SEQ_NBR) FROM PS_ZHRT_ALTTRIGGER P2
				//WHERE P2.PROC_NAME = 'ZHRI202A'
				//AND P2.EMPLID = $PSEmplid AND P2.SEQUENCE = 0)     
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    		    "SELECT p.completionStatus FROM PszTriggerNonPerson p "
	    		    		+ "WHERE TRIM(UPPER(p.processName)) = 'ZHRI202A' "
	    		    		+ "		AND TRIM(UPPER(p.employeeId)) = TRIM(UPPER(:employeeId)) "
	    		    		+ "		AND p.eidIndexNumber = 0 "
	    		    		+ "		AND p.sequenceNumber = "
	    		    		+ "			(SELECT MAX(p2.sequenceNumber) FROM PszTriggerNonPerson p2 "
	    		    		+ "				WHERE TRIM(UPPER(p2.processName)) = 'ZHRI202A' "
	    		    		+ "					AND TRIM(UPPER(p2.employeeId)) = TRIM(UPPER(:employeeId)) "
	    		    		+ "					AND TRIM(UPPER(p2.eidIndexNumber)) = 0) "
	    		    , String.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return ("C".equalsIgnoreCase(resultList.get(0)) || "P".equalsIgnoreCase(resultList.get(0)));
	    	}
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	    return false;	
	}
	
	public static PszTriggerNonPerson createMockTriggerForNonPersonTermination() {
		logger.debug("*** PszTriggerNonPerson.createMockTriggerForNonPersonTermination()");
		BigInteger sequenceNumber = new BigInteger("2111");
		String operatorId = "E208T1";
		String employeeId = "348017";
		Date effectiveDate = new Date();
		try {
			effectiveDate = (new SimpleDateFormat("dd-MMM-yyyy")).parse("27-JUN-2017");
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		BigInteger effectiveSequence = new BigInteger("0");
		String processName = "ZHRI202A";
		String completionStatus = "P";
		Integer eidIndexNumber = 0;
		PszTriggerNonPerson trigger = new PszTriggerNonPerson();
		trigger.setCompletionStatus(completionStatus);
		trigger.setEffectiveDate(effectiveDate);
		trigger.setEffectiveSequence(effectiveSequence);
		trigger.setEmployeeId(employeeId);
		trigger.setOperatorId(operatorId);
		trigger.setProcessName(processName);
		trigger.setSequenceNumber(sequenceNumber);
		trigger.setEidIndexNumber(eidIndexNumber);
		return trigger;
	}

	/**
	 * Updates TASK_FLAG in PS_ZHRT_ALTTRIGGER for given SEQ_NBR
	 * @see Update-Trigger-Row-NonEmp in ZHRI100A.SQR
	 * @param completionStatus
	 * @param sequenceNumber
	 * @return numberOfRecordsUpdated
	 */
	public static int setCompletionStatusBySequenceNumber(String completionStatus, BigInteger sequenceNumber) {
		logger.debug("*** PszTriggerNonPerson.setCompletionStatusBySequenceNumber()");
    	String triggerTypeClassName = MethodHandles.lookup().lookupClass().getSimpleName();
	    return PszTriggerSuperclass.setCompletionStatusBySequenceNumber(completionStatus, sequenceNumber, triggerTypeClassName);
	}
	
	/**
	 * @param sequenceNumber
	 * @return list of records from the PszTriggerNonPerson table with matching sequenceNumber
	 */
	public static PszTriggerSuperclass findBySequenceNumber(BigInteger sequenceNumber) {
		System.out.println("PszTriggerNonPerson.findBySequenceNumber()");
    	String triggerTypeClassName = MethodHandles.lookup().lookupClass().getSimpleName();
	    return PszTriggerSuperclass.findBySequenceNumber(sequenceNumber, triggerTypeClassName);
	}

}

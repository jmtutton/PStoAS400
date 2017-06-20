package erd.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;

/**
 * Entity implementation class for PS_ZHRT_INTTRIGGER PeopleSoft employee event triggers table 
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name = "PS_ZHRT_INTTRIGGER")
public class PszTriggerEmployee extends PszTriggerSuperclass {
	private static final long serialVersionUID = 1L;
	   
	public PszTriggerEmployee() {
		super();
		ENTITY_NAME = "PszTriggerEmployee";
	}

	public static PszTriggerEmployee findBySequenceNumber(Integer sequenceNumber) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PszTriggerEmployee> resultList = em.createQuery(
	    		    "SELECT t FROM PszTriggerEmployee t WHERE t.sequenceNumber = :sequenceNumber", PszTriggerEmployee.class)
	    		    .setParameter("sequenceNumber", sequenceNumber)
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		return resultList.get(0);
	    	}
	    } 
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
	}
	
	public static List<PszTriggerEmployee> findByCompletionStatusOrderBySequenceNumber(String completionStatus) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	return em.createQuery(
	    		    "SELECT t FROM PszTriggerEmployee t WHERE TRIM(UPPER(t.completionStatus)) = :completionStatus ORDER BY t.sequenceNumber", PszTriggerEmployee.class)
	    		    .setParameter("completionStatus", completionStatus.toUpperCase().trim())
	    		    .getResultList();
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
    }
	
	public static List<PszTriggerEmployee> findByCompletionStatusAndProcessName(String completionStatus, String processName) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	return em.createQuery(
	    		    "SELECT t FROM PszTriggerEmployee t WHERE TRIM(UPPER(t.completionStatus)) = :completionStatus "
	    		    		+ "AND TRIM(UPPER(t.processName)) = :processName "
	    		    		+ "ORDER BY t.sequenceNumber", PszTriggerEmployee.class)
	    		    .setParameter("completionStatus", completionStatus.toUpperCase().trim())
	    		    .setParameter("processName", processName.toUpperCase().trim())
	    		    .getResultList();
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
    }

	/**
	 * Replaces Update-Trigger-Row from ZHRI100A.SQR
	 * Updates the trigger file flag switch
	 */
	public static int setCompletionStatusBySequenceNumber(String completionStatus, Integer sequenceNumber) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		int numberOfRecordsUpdated = 0;
	    try {
	    	em.getTransaction().begin();
	    	numberOfRecordsUpdated = em.createQuery("UPDATE PszTriggerEmployee t SET t.completionStatus = :completionStatus "
	    				+ "WHERE t.sequenceNumber = :sequenceNumber")
	    		    .setParameter("completionStatus", completionStatus.toUpperCase().trim())
	    		    .setParameter("sequenceNumber", sequenceNumber)
	    		    .executeUpdate();
	    	em.getTransaction().commit();
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return numberOfRecordsUpdated;
	}
	
	public static PszTriggerEmployee createMockTriggerForEmployeeTermination() {
		String completionStatus = "P";
		String processName = "ZHRI102A";
		String employeeId = "347940";
		String operatorId = "OPSHR";
		BigDecimal effectiveSequence = new BigDecimal(0);
		Integer sequenceNumber = 90727260;
		java.util.Date effectiveDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(effectiveDate.getTime());
		PszTriggerEmployee trigger = new PszTriggerEmployee();
		trigger.setCompletionStatus(completionStatus);
		trigger.setEffectiveDate(sqlDate);
		trigger.setEffectiveSequence(effectiveSequence);
		trigger.setEmployeeId(employeeId);
		trigger.setOperatorId(operatorId);
		trigger.setProcessName(processName);
		trigger.setSequenceNumber(sequenceNumber);
		return trigger;
	}
}

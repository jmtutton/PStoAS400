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
public class TriggerEmployee extends TriggerSuperclass {
	private static final long serialVersionUID = 1L;
	   
	public TriggerEmployee() {
		super();
		ENTITY_NAME = "TriggerEmployee";
	}

	public static TriggerEmployee findBySequenceNumber(Integer sequenceNumber) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<TriggerEmployee> resultList = em.createQuery(
	    		    "SELECT t FROM TriggerEmployee t WHERE t.sequenceNumber = :sequenceNumber", TriggerEmployee.class)
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
	
	public static List<TriggerEmployee> findByCompletionStatusOrderBySequenceNumber(String completionStatus) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	return em.createQuery(
	    		    "SELECT t FROM TriggerEmployee t WHERE TRIM(UPPER(t.completionStatus)) = :completionStatus ORDER BY t.sequenceNumber", TriggerEmployee.class)
	    		    .setParameter("completionStatus", completionStatus.toUpperCase().trim())
	    		    .getResultList();
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
    }
	
	public static List<TriggerEmployee> findByCompletionStatusAndProcessName(String completionStatus, String processName) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	return em.createQuery(
	    		    "SELECT t FROM TriggerEmployee t WHERE TRIM(UPPER(t.completionStatus)) = :completionStatus "
	    		    		+ "AND TRIM(UPPER(t.processName)) = :processName "
	    		    		+ "ORDER BY t.sequenceNumber", TriggerEmployee.class)
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
	    	numberOfRecordsUpdated = em.createQuery("UPDATE TriggerEmployee t SET t.completionStatus = :completionStatus "
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
	
	public static TriggerEmployee createMockTriggerForEmployeeTermination() {
		String completionStatus = "P";
		String processName = "ZHRI102A";
		String employeeId = "347940";
		String operatorId = "OPSHR";
		BigDecimal effectiveSequence = new BigDecimal(0);
		Integer sequenceNumber = 90727260;
		java.util.Date effectiveDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(effectiveDate.getTime());
		TriggerEmployee trigger = new TriggerEmployee();
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

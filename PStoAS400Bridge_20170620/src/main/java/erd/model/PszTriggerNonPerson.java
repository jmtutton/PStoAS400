package erd.model;

import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for PS_ZHRT_ALTTRIGGER PeopleSoft non-person event triggers table 
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name = "PS_ZHRT_ALTTRIGGER")
public class PszTriggerNonPerson extends PszTriggerSuperclass {
	private static final long serialVersionUID = 1L;
	
	//same properties as TriggerEmployee, with just this one extra field.  	   
	@Column(name = "SEQUENCE")
	private Integer eidIndexNumber;  //This field is the index (0-4) of an EID non-persons with multiple EIDs. 0 is the primary.

	public Integer getEidIndexNumber() {
		return eidIndexNumber;
	}
	   
	public PszTriggerNonPerson() {
		super();
		PszTriggerNonPerson.ENTITY_NAME = "PszTriggerNonPerson";
	}
	
	@Override
	public String toString() {
		return "ENTITY_NAME: PszTriggerNonPerson\n" +
				"SequenceNumber: " + getSequenceNumber() + "\n" +
				"OperatorId: " + getOperatorId() + "\n" +
				"EmployeeId: " + getEmployeeId() + "\n" +
				"EffectiveDate: " + getEffectiveDate() + "\n" +
				"EffectiveSequence: " + getEffectiveSequence() + "\n" +
				"ProcessName: " + getProcessName() + "\n" +
				"CompletionStatus: " + getCompletionStatus() + "\n" +
				"EidIndexNumber: " + getEidIndexNumber();
	}
	
	public static PszTriggerNonPerson findBySequenceNumber(Integer seqNum) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PszTriggerNonPerson> resultList = em.createQuery(
	    		    "SELECT t FROM PszTriggerNonPerson t WHERE t.sequenceNumber = :seqNum", PszTriggerNonPerson.class)
	    		    .setParameter("seqNum", seqNum)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0);
	    	}
	    } 
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
	}
	
	public static List<PszTriggerNonPerson> findByCompletionStatusOrderBySequenceNumber(String completionStatus) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	return em.createQuery(
	    		    "SELECT t FROM PszTriggerNonPerson t WHERE TRIM(UPPER(t.completionStatus)) = :completionStatus ORDER BY t.sequenceNumber", PszTriggerNonPerson.class)
	    		    .setParameter("completionStatus", completionStatus.toUpperCase().trim())
	    		    .getResultList();
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
    }

	/**
	 * Replaces Update-Trigger-Row_NonEmp from ZHRI100A.SQR
	 * Updates the trigger file flag switch
	 */
	public static int setCompletionStatusBySequenceNumber(String completionStatus, Integer sequenceNumber) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		int numberOfRecordsUpdated = 0;
	    try {
	    	em.getTransaction().begin();
	    	numberOfRecordsUpdated = em.createQuery("UPDATE PszTriggerNonPerson t SET t.completionStatus = :completionStatus "
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
}

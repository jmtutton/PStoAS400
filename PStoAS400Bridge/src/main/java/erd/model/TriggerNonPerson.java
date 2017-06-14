package erd.model;

import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for PS_ZHRT_ALTTRIGGER PeopleSoft non-person event triggers table 
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name = "PS_ZHRT_ALTTRIGGER")
public class TriggerNonPerson extends TriggerSuperclass {
	private static final long serialVersionUID = 1L;
	
	//same properties as TriggerEmployee, with just this one extra field.  	   
	@Column(name = "SEQUENCE")
	private Integer eidIndexNumber;  //This field is the index (0-4) of an EID non-persons with multiple EIDs. 0 is the primary.

	public Integer getEidIndexNumber() {
		return eidIndexNumber;
	}
	   
	public TriggerNonPerson() {
		super();
		TriggerSuperclass.ENTITY_NAME = "TriggerNonPerson";
	}
	
	@Override
	public String toString() {
		return "ENTITY_NAME: TriggerNonPerson\n" +
				"SequenceNumber: " + getSequenceNumber() + "\n" +
				"OperatorID: " + getOperatorID() + "\n" +
				"EmployeeID: " + getEmployeeId() + "\n" +
				"EffectiveDate: " + getEffectiveDate() + "\n" +
				"EffectiveSequence: " + getEffectiveSequence() + "\n" +
				"ProcessName: " + getProcessName() + "\n" +
				"CompletionStatus: " + getCompletionStatus() + "\n" +
				"EidIndexNumber: " + getEidIndexNumber();
	}
	
	public static TriggerNonPerson findBySequenceNumber(Integer seqNum) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<TriggerNonPerson> resultList = em.createQuery(
	    		    "SELECT t FROM TriggerNonPerson t WHERE t.sequenceNumber = :seqNum", TriggerNonPerson.class)
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
	
	public static List<TriggerNonPerson> findByCompletionStatusOrderBySequenceNumber(String completionStatus) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	return em.createQuery(
	    		    "SELECT t FROM TriggerNonPerson t WHERE TRIM(UPPER(t.completionStatus)) = :completionStatus ORDER BY t.sequenceNumber", TriggerNonPerson.class)
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
	    	numberOfRecordsUpdated = em.createQuery("UPDATE TriggerNonPerson t SET t.completionStatus = :completionStatus "
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

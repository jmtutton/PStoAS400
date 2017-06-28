package erd.model;

import java.math.BigDecimal;
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
	 * findBySequenceNumber
	 * @param sequenceNumber
	 * @return PszTriggerNonPerson
	 */
	public static PszTriggerNonPerson findBySequenceNumber(BigDecimal sequenceNumber) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PszTriggerNonPerson> resultList = em.createQuery(
	    		    "SELECT p FROM PszTriggerNonPerson p WHERE p.sequenceNumber = :sequenceNumber", PszTriggerNonPerson.class)
	    		    .setParameter("sequenceNumber", sequenceNumber)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0);
	    	}
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	    return null;	
	}
	
	/**
	 * findByCompletionStatusOrderBySequenceNumber
	 * @param completionStatus
	 * @return List<PszTriggerNonPerson>
	 */
	public static List<PszTriggerNonPerson> findByCompletionStatusOrderBySequenceNumber(String completionStatus) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	return em.createQuery(
	    		    "SELECT p FROM PszTriggerNonPerson p "
	    		    		+ "WHERE TRIM(UPPER(p.completionStatus)) = :completionStatus "
	    		    		+ "ORDER BY p.sequenceNumber", PszTriggerNonPerson.class)
	    		    .setParameter("completionStatus", completionStatus.toUpperCase().trim())
	    		    .getResultList();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	    return null;	
    }
	
	/**
	 * findByCompletionStatusAndProcessName
	 * @param completionStatus
	 * @param processName
	 * @return List<PszTriggerNonPerson>
	 */
	public static List<PszTriggerNonPerson> findByCompletionStatusAndProcessName(String completionStatus, String processName) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	return em.createQuery(
	    		    "SELECT p FROM PszTriggerNonPerson p WHERE TRIM(UPPER(p.completionStatus)) = :completionStatus "
	    		    		+ "AND TRIM(UPPER(p.processName)) = :processName "
	    		    		+ "ORDER BY p.sequenceNumber", PszTriggerNonPerson.class)
	    		    .setParameter("completionStatus", completionStatus.toUpperCase().trim())
	    		    .setParameter("processName", processName.toUpperCase().trim())
	    		    .getResultList();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	    return null;	
    }

	/**
	 * setCompletionStatusBySequenceNumber
	 * @param completionStatus
	 * @param sequenceNumber
	 * @return numberOfRecordsUpdated
	 */
	public static int setCompletionStatusBySequenceNumber(String completionStatus, BigDecimal sequenceNumber) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		int numberOfRecordsUpdated = 0;
	    try {
	    	em.getTransaction().begin();
	    	numberOfRecordsUpdated = em.createQuery(
	    			"UPDATE PszTriggerEmployee p "
	    		    		+ "SET p.completionStatus = :completionStatus "
	    		    		+ "WHERE p.sequenceNumber = :sequenceNumber")
	    		    .setParameter("completionStatus", completionStatus.toUpperCase().trim())
	    		    .setParameter("sequenceNumber", sequenceNumber)
	    		    .executeUpdate();
	    	em.getTransaction().commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	    return numberOfRecordsUpdated;
	}

}

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

	/**
	 * Check-If-POI-Termed from ZHRI100A.SQR
	 * This routine checks if it is a POI to EMP transfer. If it is then the the flag is changed to W and wait for Hire
	 * @param 
	 * @param 
	 * @return
	 */
	//TODO: 
	public static Boolean ZHRI100A_checkIfPoiTermed(String employeeId) {
		System.out.println("********** PszTriggerNonPerson.ZHRI100A_checkIfPoiTermed()");
		//BEGIN-PROCEDURE Check-if-POI-Termed
		//LET $taskflag = ' '
		//BEGIN-SELECT
		//SEC.TASK_FLAG
		//LET $taskflag = LTRIM(RTRIM(&SEC.TASK_FLAG, ' '), ' ')
		//FROM PS_ZHRT_ALTTRIGGER SEC
		//WHERE SEC.PROC_NAME = 'ZHRI202A'
		//		AND SEC.EMPLID = $PSEmplid
		//		AND SEC.SEQUENCE = 0
		//		AND SEC.SEQ_NBR = 
		//			(SELECT MAX(SEC1.SEQ_NBR) FROM PS_ZHRT_ALTTRIGGER SEC1
		//				WHERE SEC1.PROC_NAME = 'ZHRI202A'
		//					AND SEC1.EMPLID = $PSEmplid
		//					AND SEC1.SEQUENCE = 0)     
		//END-SELECT
		//END-PROCEDURE Check-if-POI-Termed  
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    		    "SELECT p.completionStatus FROM PszTriggerNonPerson p "
	    		    		+ "WHERE TRIM(UPPER(p.processName)) = :processName "
	    		    		+ "		AND TRIM(UPPER(p.employeeId)) = :employeeId "
	    		    		+ "		AND p.eidIndexNumber = :eidIndexNumber "
	    		    		+ "		AND p.sequenceNumber = "
	    		    		+ "			(SELECT MAX(p2.sequenceNumber) FROM PszTriggerNonPerson p2 "
	    		    		+ "				WHERE TRIM(UPPER(p2.processName)) = :processName "
	    		    		+ "					AND TRIM(UPPER(p2.employeeId)) = :employeeId "
	    		    		+ "					AND TRIM(UPPER(p2.eidIndexNumber)) = :eidIndexNumber) ", String.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("eidIndexNumber", 0)
	    		    .setParameter("processName", "ZHRI202A")
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		String completionStatus = resultList.get(0);
	    		return ("C".equalsIgnoreCase(completionStatus) || "P".equalsIgnoreCase(completionStatus));
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

}

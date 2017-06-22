package erd.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.TemporalType;

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
		Integer effectiveSequence = 0;
		Integer sequenceNumber = 90727260;
		Date effectiveDate = new Date();
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
	
	//TODO: fix query
	public static List<PszTriggerEmployee> findTriggerDataList() {
		Date asOfToday = new Date();
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	//FROM PS_ZHRT_INTTRIGGER RZ, PS_JOB JB
			//WHERE RZ.TASK_FLAG = 'P'
			//		AND (RZ.EFFDT <= $AsOfToday 
			//		OR RZ.PROC_NAME='ZHRI101A' 
			//		OR RZ.PROC_NAME='ZHRI106A')
			//  	AND (CASE WHEN PROC_NAME IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END) = 
			//    		(SELECT MIN(CASE WHEN PROC_NAME IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END)  
			//      		FROM  PS_ZHRT_INTTRIGGER RZ2
			//            	WHERE RZ2.EMPLID = RZ.EMPLID
			//           		AND RZ2.TASK_FLAG = 'P'
			//               	AND (RZ2.EFFDT <= SYSDATE
			//						OR RZ2.PROC_NAME='ZHRI101A'
			//						OR RZ2.PROC_NAME='ZHRI106A'))
	  	  	// 		AND RZ.EMPLID NOT IN (SELECT I.EMPLID FROM PS_ZHRT_INTTRIGGER I WHERE I.EMPLID = RZ.EMPLID AND I.TASK_FLAG = 'W') 
			//  	AND JB.EMPLID = RZ.EMPLID
			//		AND JB.EFFDT = 
			//			(SELECT MAX(JB2.EFFDT) FROM  PS_JOB JB2
			//     			WHERE JB2.EMPLID = JB.EMPLID
			//         			AND JB2.EMPL_RCD = JB.EMPL_RCD)
			//  	AND JB.EFFSEQ = 
			//			(SELECT MAX(JB3.EFFSEQ) FROM PS_JOB JB3
			//       		WHERE JB3.EMPLID = JB.EMPLID
			//                	AND JB3.EMPL_RCD = JB.EMPL_RCD
			//                	AND JB3.EFFDT = JB.EFFDT)
	    	List<PszTriggerEmployee> resultList = em.createQuery(
	    		    "SELECT pe FROM PszTriggerEmployee pe, PsJob pj "
	    		    		+ "WHERE pe.completionStatus = :completionStatus "
	    		    		+ "		AND (pe.effectiveDate <= :asOfToday OR pe.processName = 'ZHRI101A' OR pe.processName = 'ZHRI106A') "	
//	    		    		+ "		AND (CASE WHEN processName IN ('ZHRI101A', 'ZHRI106A') THEN sequenceNumber ELSE sequenceNumber*10 END) = "
//	    		    		+ "			(SELECT MIN(CASE WHEN processName IN ('ZHRI101A', 'ZHRI106A') THEN sequenceNumber ELSE sequenceNumber*10 END) "
//	    		    		+ "				FROM PszTriggerEmployee pe2 "
//	    		    		+ "				WHERE pe2.employeeId = pe.employeeId "
//	    		    		+ "					AND pe2.completionStatus = :completionStatus "
//	    		    		+ "					AND (pe2.effectiveDate <= CURRENT_DATE "
//	    		    		+ "						OR pe2.processName = 'ZHRI101A' "
//	    		    		+ "						OR pe2.processName = 'ZHRI106A')) "
	    		    		+ "		AND pe.employeeId NOT IN (SELECT pe3.employeeId FROM PszTriggerEmployee pe3 WHERE pe3.employeeId = pe.employeeId AND pe3.completionStatus = 'W') " 
	    		    		+ "		AND pj.employeeId = pe.employeeId "
	    					+ "		AND pj.effectiveDate = "
	    		    		+ "			(SELECT MAX(pj2.effectiveDate) FROM PsJob pj2 "
	    		    		+ "				WHERE pj2.employeeId = pj.employeeId "
	    		    		+ "					AND pj2.employmentRecordNumber = pj.employmentRecordNumber) "
	    		    		+ "		AND pj.effectiveSequence = "
	    		    		+ "			(SELECT MAX(pj3.effectiveSequence) FROM PsJob pj3 "
	    		    		+ "				WHERE pj3.employeeId = pj.employeeId "
	    		    		+ "					AND pj3.employmentRecordNumber = pj.employmentRecordNumber "
	    		    		+ "					AND pj3.effectiveDate = pj.effectiveDate) "
	    			, PszTriggerEmployee.class)
	    		    .setParameter("asOfToday", asOfToday, TemporalType.DATE)
//	    		    .setParameter("completionStatus", "P")
	    		    .setParameter("completionStatus", "C")
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		return resultList;
	    	}
	    } 
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
		return null;
	}

	public static List<Integer> caseTest() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<Integer> resultList = em.createQuery(""
//	    			+ "SELECT "
//	    			+ "(CASE WHEN pe.processName IN ('ZHRI101A', 'ZHRI106A') THEN pe.sequenceNumber ELSE pe.sequenceNumber*10 END) "
//					+ "((CASE WHEN pe.processName IN ('ZHRI101A', 'ZHRI106A') THEN pe.sequenceNumber ELSE pe.sequenceNumber*10 END) = "
					+ "SELECT pe.sequenceNumber FROM PszTriggerEmployee pe WHERE (MIN(CASE WHEN pe.processName IN ('ZHRI101A', 'ZHRI106A') THEN pe.sequenceNumber ELSE pe.sequenceNumber*10 END) = "
	    			+ "(CASE WHEN pe.processName IN ('ZHRI101A', 'ZHRI106A') THEN pe.sequenceNumber ELSE pe.sequenceNumber*10 END)) "
//					+ "SELECT MIN(CASE WHEN pe.processName IN ('ZHRI101A', 'ZHRI106A') THEN pe.sequenceNumber ELSE pe.sequenceNumber*10 END) "
//	    		    + "FROM PszTriggerEmployee pe"
	    			, Integer.class)
	    		    .getResultList();
	    	return resultList;
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;
	}

	/**
	 * Update-Trigger-Row from ZHRI100A.SQR
	 * This routine update the trigger file flag switch
	 * @param seqNum
	 * @param status
	 * @return
	 */
	public static int updateCompletionStatus(Integer sequenceNumber, String completionStatus) {
		//BEGIN-PROCEDURE UPDATE-TRIGGER-ROW
		//BEGIN-SQL
		//UPDATE PS_ZHRT_INTTRIGGER
		//	SET TASK_FLAG = $CompletionStatus
		//WHERE SEQ_NBR = #WRKSEQUENCE
		//END-SQL
		//LET $CompletionStatus = 'P'     !Reset the completion Status for next pass
		//END-PROCEDURE UPDATE-TRIGGER-ROW
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		int numberOfRecordsUpdated = 0;
	    try {
	    	em.getTransaction().begin();
	    	numberOfRecordsUpdated = em.createQuery(
	    		    "UPDATE " + ENTITY_NAME + " SET completionStatus = UPPER(:status) WHERE sequenceNumber = :sequenceNumber")
	    		    .setParameter("sequenceNumber", sequenceNumber)
	    		    .setParameter("status", completionStatus)
	    		    .executeUpdate();
//				//alternatively:
//		    	TriggerEmployee trigger = em.find(TriggerEmployee.class, 1);
//		    	trigger.setCompletionStatus(status);
	    	em.getTransaction().commit();
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    }
	    return numberOfRecordsUpdated;
	}
}

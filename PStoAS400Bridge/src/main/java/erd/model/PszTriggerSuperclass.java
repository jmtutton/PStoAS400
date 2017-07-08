package erd.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Persistence;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 * Entity superclass for trigger table entities
 * @author John Tutton john@tutton.net
 * @see TriggerEmployee, TriggerNonPerson
 */
@Entity
@Inheritance(strategy  = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class PszTriggerSuperclass implements Serializable {
	public static final long serialVersionUID = 1L;
	
	protected static String ENTITY_NAME = "";

	@Id
	@Column(name="SEQ_NBR", nullable=false, precision=15)
	protected BigInteger sequenceNumber;
	   
	@Column(name="OPRID", nullable=false, length=30)
	protected String operatorId;

	@Column(name="EMPLID", nullable=false, length=11)
	protected String employeeId;

	@Column(name="EFFDT")
	@Temporal(TemporalType.DATE)
	protected Date effectiveDate;
	   
	@Column(name="EFFSEQ", nullable=false, precision=38)
	protected BigInteger effectiveSequence;
	   
	@Column(name="PROC_NAME", nullable=false, length=10)
	protected String processName;  //TODO: make enum
	   
	@Column(name="TASK_FLAG", nullable=false, length=1)
	protected String completionStatus;  //TODO: make enum with values 'P', 'C', 'E', 'W'

	public BigInteger getSequenceNumber() {
		return sequenceNumber;
	}
	public String getCompletionStatus() {
		return completionStatus != null ? completionStatus.trim() :  completionStatus;
	}
	public String getOperatorId() {
		return operatorId != null ? operatorId.trim() :  operatorId;
	}
	public String getEmployeeId() {
		return employeeId != null ? employeeId.trim() :  employeeId;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public BigInteger getEffectiveSequence() {
		return effectiveSequence;
	}
	public String getProcessName() {
		return processName != null ? processName.trim() :  processName;
	}
	
	public void setSequenceNumber(BigInteger sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId != null ? operatorId.trim() :  operatorId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId != null ? employeeId.trim() :  employeeId;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public void setEffectiveSequence(BigInteger effectiveSequence) {
		this.effectiveSequence = effectiveSequence;
	}
	public void setProcessName(String processName) {
		this.processName = processName != null ? processName.trim() :  processName;
	}
	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus != null ? completionStatus.trim() :  completionStatus;
	}
	
	@Override
	public String toString() {
		return "ENTITY_NAME: " + ENTITY_NAME + "\n" +
				"SequenceNumber: " + getSequenceNumber() + "\n" +
				"OperatorId: " + getOperatorId() + "\n" +
				"EmployeeId: " + getEmployeeId() + "\n" +
				"EffectiveDate: " + getEffectiveDate() + "\n" +
				"EffectiveSequence: " + getEffectiveSequence() + "\n" +
				"ProcessName: " + getProcessName() + "\n" +
				"CompletionStatus: " + getCompletionStatus();
	}
	
	/**
	 * 
	 * @return a list of all records from all subclass tables with the completionStatus = 'P'
	 */
	public static List<PszTriggerSuperclass> findPending() {
		System.out.println("PszTriggerSuperclass.findPending()");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	    	CriteriaQuery<PszTriggerSuperclass> criteriaQuery = criteriaBuilder.createQuery(PszTriggerSuperclass.class);
	    	Root<?> root = criteriaQuery.from(PszTriggerSuperclass.class);
	    	ParameterExpression<String> parameterExpression = criteriaBuilder.parameter(String.class);
	    	criteriaQuery.multiselect(root).where(criteriaBuilder.equal(root.get("completionStatus"), parameterExpression));
	    	criteriaQuery.orderBy(criteriaBuilder.asc(root.get("sequenceNumber")));
	    	TypedQuery<PszTriggerSuperclass> query = em.createQuery(criteriaQuery);
	    	query.setParameter(parameterExpression, "P");
	    	List<PszTriggerSuperclass> resultList = query.getResultList();
	    	return resultList;
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	    return null;	
    }

	public static PszTriggerSuperclass findBySequenceNumber(BigInteger sequenceNumber, String triggerTypeClassName) {
		System.out.println("PszTriggerSuperclass.findBySequenceNumber()");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	    	CriteriaQuery<?> criteriaQuery = null;
	    	Root<?> root = null;
	    	if("PszTriggerEmployee".equalsIgnoreCase(triggerTypeClassName)) {
		    	criteriaQuery = criteriaBuilder.createQuery(PszTriggerEmployee.class);
		    	root = criteriaQuery.from(PszTriggerEmployee.class);
	    	}
	    	else if("PszTriggerNonPerson".equalsIgnoreCase(triggerTypeClassName)) {
		    	criteriaQuery = criteriaBuilder.createQuery(PszTriggerNonPerson.class);
		    	root = criteriaQuery.from(PszTriggerNonPerson.class);
	    	}
	    	ParameterExpression<BigInteger> parameterExpression = criteriaBuilder.parameter(BigInteger.class);
	    	criteriaQuery.multiselect(root).where(criteriaBuilder.equal(root.get("sequenceNumber"), parameterExpression));
	    	TypedQuery<?> query = em.createQuery(criteriaQuery);
	    	query.setParameter(parameterExpression, sequenceNumber);
			@SuppressWarnings("unchecked")
			List<PszTriggerSuperclass> resultList = (List<PszTriggerSuperclass>) query.getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
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
	 * Updates TASK_FLAG in the table with the given trigger type for the given SEQ_NBR
	 * @see Update-Trigger-Row in ZHRI100A.SQR
	 * @see Update-Trigger-Row-NonEmp in ZHRI100A.SQR
	 * @param completionStatus
	 * @param sequenceNumber
	 * @return numberOfRecordsUpdated
	 */
	public static int setCompletionStatusBySequenceNumber(String completionStatus, BigInteger sequenceNumber, String triggerTypeClassName) {
		System.out.println("PszTriggerSuperclass.setCompletionStatusBySequenceNumber()");
		int numberOfRecordsUpdated = 0;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
    	EntityTransaction transaction = em.getTransaction();
	    try {
	    	transaction.begin();
	    	numberOfRecordsUpdated = em.createQuery(
	    			"UPDATE " + triggerTypeClassName + " p "
	    		    		+ "SET p.completionStatus = :completionStatus "
	    		    		+ "WHERE p.sequenceNumber = :sequenceNumber")
	    		    .setParameter("completionStatus", completionStatus.toUpperCase().trim())
	    		    .setParameter("sequenceNumber", sequenceNumber)
	    		    .executeUpdate();
	    	transaction.commit();
	    }
	    catch (Exception e) {
	    	if(transaction.isActive()) {
	    		transaction.rollback();
	    	}
	    	e.printStackTrace();
	    }
	    finally {
	    	em.close();
	    }
	    return numberOfRecordsUpdated;
	}
	
//	public static List<PszTriggerEmployee> findByCompletionStatusOrderBySequenceNumber(String completionStatus) {
//	System.out.println("PszTriggerEmployee.findByCompletionStatusOrderBySequenceNumber()");
//	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//	EntityManager em = emfactory.createEntityManager();
//    try {
//    	return em.createQuery(
//    		    "SELECT p FROM PszTriggerEmployee p "
//    		    		+ "WHERE TRIM(UPPER(p.completionStatus)) = :completionStatus "
//    		    		+ "ORDER BY p.sequenceNumber ASC "
//    		    , PszTriggerEmployee.class)
//    		    .setParameter("completionStatus", completionStatus.toUpperCase().trim())
//    		    .getResultList();
//    }
//    catch (Exception e) {
//    	e.printStackTrace();
//    } 
//    finally {
//    	em.close();
//    }
//    return null;	
//}

//public static List<PszTriggerEmployee> findByCompletionStatusAndProcessName(String completionStatus, String processName) {
//	System.out.println("PszTriggerEmployee.findByCompletionStatusAndProcessName()");
//	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//	EntityManager em = emfactory.createEntityManager();
//    try {
//    	return em.createQuery(
//    		    "SELECT p FROM PszTriggerEmployee p "
//    		    		+ "WHERE TRIM(UPPER(p.completionStatus)) = :completionStatus "
//    		    		+ "AND TRIM(UPPER(p.processName)) = :processName "
//    		    		+ "ORDER BY p.sequenceNumber ASC "
//    		    		+ "LIMIT 150 "
//    		    , PszTriggerEmployee.class)
//    		    .setParameter("completionStatus", completionStatus.toUpperCase().trim())
//    		    .setParameter("processName", processName.toUpperCase().trim())
//    		    .getResultList();
//    }
//    catch (Exception e) {
//    	e.printStackTrace();
//    } 
//    finally {
//    	em.close();
//    }
//    return null;	
//}

//public static List<PszTriggerEmployee> findPending() {
//	System.out.println("*** PszTriggerEmployee.findPending()");
//	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//	EntityManager em = emfactory.createEntityManager();
//    try {
//    	return em.createQuery(
//    		    "SELECT p FROM PszTriggerEmployee p "
//    		    		+ "WHERE TRIM(UPPER(p.completionStatus)) = 'P' "
//    		    		+ "ORDER BY p.sequenceNumber ASC "
//    		    , PszTriggerEmployee.class)
//    		    .getResultList();
//    }
//    catch (Exception e) {
//    	e.printStackTrace();
//    } 
//    finally {
//    	em.close();
//    }
//    return null;	
//}

//public static List<PszTriggerEmployee> findPendingWithLimit(Integer limit) {
//	System.out.println("*** PszTriggerEmployee.findPending()");
//	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//	EntityManager em = emfactory.createEntityManager();
//    try {
//    	return em.createQuery(
//    		    "SELECT p FROM PszTriggerEmployee p "
//    		    		+ "WHERE TRIM(UPPER(p.completionStatus)) = 'P' "
//    		    		+ "ORDER BY p.sequenceNumber ASC "
//    		    , PszTriggerEmployee.class)
//    			.setMaxResults(limit)
//    		    .getResultList();
//    }
//    catch (Exception e) {
//    	e.printStackTrace();
//    } 
//    finally {
//    	em.close();
//    }
//    return null;	
//}

////TODO: fix query
//public static List<PszTriggerEmployee> findTriggerDataList() {
//	System.out.println("PszTriggerEmployee.findTriggerDataList()");
//	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//	EntityManager em = emfactory.createEntityManager();
//    try {
//    	//FROM PS_ZHRT_INTTRIGGER RZ, PS_JOB JB
//		//WHERE RZ.TASK_FLAG = 'P'
//		//		AND (RZ.EFFDT <= $AsOfToday 
//		//		OR RZ.PROC_NAME='ZHRI101A' 
//		//		OR RZ.PROC_NAME='ZHRI106A')
//		//  	AND (CASE WHEN PROC_NAME IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END) = 
//		//    		(SELECT MIN(CASE WHEN PROC_NAME IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END)  
//		//      		FROM  PS_ZHRT_INTTRIGGER RZ2
//		//            	WHERE RZ2.EMPLID = RZ.EMPLID
//		//           		AND RZ2.TASK_FLAG = 'P'
//		//               	AND (RZ2.EFFDT <= SYSDATE
//		//						OR RZ2.PROC_NAME='ZHRI101A'
//		//						OR RZ2.PROC_NAME='ZHRI106A'))
//  	  	// 		AND RZ.EMPLID NOT IN (SELECT I.EMPLID FROM PS_ZHRT_INTTRIGGER I WHERE I.EMPLID = RZ.EMPLID AND I.TASK_FLAG = 'W') 
//		//  	AND JB.EMPLID = RZ.EMPLID
//		//		AND JB.EFFDT = 
//		//			(SELECT MAX(JB2.EFFDT) FROM  PS_JOB JB2
//		//     			WHERE JB2.EMPLID = JB.EMPLID
//		//         			AND JB2.EMPL_RCD = JB.EMPL_RCD)
//		//  	AND JB.EFFSEQ = 
//		//			(SELECT MAX(JB3.EFFSEQ) FROM PS_JOB JB3
//		//       		WHERE JB3.EMPLID = JB.EMPLID
//		//                	AND JB3.EMPL_RCD = JB.EMPL_RCD
//		//                	AND JB3.EFFDT = JB.EFFDT)
//    	List<PszTriggerEmployee> resultList = em.createQuery(
//    		    "SELECT pe FROM PszTriggerEmployee pe, PsJob pj "
//    		    		+ "WHERE pe.completionStatus = :completionStatus "
//    		    		+ "		AND (pe.effectiveDate <= :asOfToday OR pe.processName = 'ZHRI101A' OR pe.processName = 'ZHRI106A') "	
////    		    		+ "		AND (CASE WHEN processName IN ('ZHRI101A', 'ZHRI106A') THEN sequenceNumber ELSE sequenceNumber*10 END) = "
////    		    		+ "			(SELECT MIN(CASE WHEN processName IN ('ZHRI101A', 'ZHRI106A') THEN sequenceNumber ELSE sequenceNumber*10 END) "
////    		    		+ "				FROM PszTriggerEmployee pe2 "
////    		    		+ "				WHERE pe2.employeeId = pe.employeeId "
////    		    		+ "					AND pe2.completionStatus = :completionStatus "
////    		    		+ "					AND (pe2.effectiveDate <= CURRENT_DATE "
////    		    		+ "						OR pe2.processName = 'ZHRI101A' "
////    		    		+ "						OR pe2.processName = 'ZHRI106A')) "
//    		    		+ "		AND pe.employeeId NOT IN (SELECT pe3.employeeId FROM PszTriggerEmployee pe3 WHERE pe3.employeeId = pe.employeeId AND pe3.completionStatus = 'W') " 
//    		    		+ "		AND pj.employeeId = pe.employeeId "
//    					+ "		AND pj.effectiveDate = "
//    		    		+ "			(SELECT MAX(pj2.effectiveDate) FROM PsJob pj2 "
//    		    		+ "				WHERE pj2.employeeId = pj.employeeId "
//    		    		+ "					AND pj2.employmentRecordNumber = pj.employmentRecordNumber) "
//    		    		+ "		AND pj.effectiveSequence = "
//    		    		+ "			(SELECT MAX(pj3.effectiveSequence) FROM PsJob pj3 "
//    		    		+ "				WHERE pj3.employeeId = pj.employeeId "
//    		    		+ "					AND pj3.employmentRecordNumber = pj.employmentRecordNumber "
//    		    		+ "					AND pj3.effectiveDate = pj.effectiveDate) "
//    		    		+ "LIMIT 150 "
//    			, PszTriggerEmployee.class)
//    		    .setParameter("asOfToday", DateUtil.asOfToday(), TemporalType.DATE)
////    		    .setParameter("completionStatus", "P")
//    		    .setParameter("completionStatus", "C")
//    		    .getResultList();
//    	if(resultList != null && !resultList.isEmpty()) {
//    		return resultList;
//    	}
//    } 
//    catch (Exception e) {
//    	e.printStackTrace();
//    } 
//    finally {
//    	em.close();
//    }
//	return null;
//}

//public static List<BigInteger> caseTest() {
//	System.out.println("PszTriggerEmployee.caseTest()");
//	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//	EntityManager em = emfactory.createEntityManager();
//    try {
//    	List<BigInteger> resultList = em.createQuery(""
//    			+ "SELECT "
////	    		+ "(SELECT CASE WHEN processName IN ('ZHRI101A', 'ZHRI106A') THEN sequenceNumber ELSE sequenceNumber*10 END FROM PszTriggerEmployee) = "
////	    		+ "		(SELECT MIN(CASE WHEN processName IN ('ZHRI101A', 'ZHRI106A') THEN sequenceNumber ELSE sequenceNumber*10 END FROM PszTriggerEmployee) "
//    			+ "(SELECT CASE WHEN pe2.processName IN ('ZHRI101A', 'ZHRI106A') THEN pe2.sequenceNumber ELSE pe2.sequenceNumber*10 END "
//	    		+ "				FROM PszTriggerEmployee pe2 "
//	    		+ "				WHERE pe2.employeeId = '323506' "
//	    		+ "					AND pe2.completionStatus = 'P' "
//	    		+ "					AND (pe2.effectiveDate <= CURRENT_DATE "
//	    		+ "						OR pe2.processName = 'ZHRI101A' "
//	    		+ "						OR pe2.processName = 'ZHRI106A')) "
////	    		+ " = "
////				+ "(SELECT MIN(CASE WHEN pe3.processName IN ('ZHRI101A', 'ZHRI106A') THEN pe3.sequenceNumber ELSE pe3.sequenceNumber*10 END) "
////	    		+ "				FROM PszTriggerEmployee pe3 "
////	    		+ "				WHERE pe3.employeeId = '323506' "
////	    		+ "					AND pe3.completionStatus = 'P' "
////	    		+ "					AND (pe3.effectiveDate <= CURRENT_DATE "
////	    		+ "						OR pe3.processName = 'ZHRI101A' "
////	    		+ "						OR pe3.processName = 'ZHRI106A')) "
//	    		+ "FROM PszTriggerEmployee pe "
//    			, BigInteger.class)
//    		    .getResultList();
//    	return resultList;
//    }
//    catch (Exception e) {
//    	e.printStackTrace();
//    } 
//    finally {
//    	em.close();
//    }
//    return null;
//}
	
//	/**
//	 * findBySequenceNumber
//	 * @param sequenceNumber
//	 * @return PszTriggerNonPerson
//	 */
//	public static PszTriggerNonPerson findBySequenceNumber(BigInteger sequenceNumber) {
//		System.out.println("*** PszTriggerNonPerson.findBySequenceNumber()");
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//		EntityManager em = emfactory.createEntityManager();
//	    try {
//	    	List<PszTriggerNonPerson> resultList = em.createQuery(
//	    		    "SELECT p FROM PszTriggerNonPerson p WHERE p.sequenceNumber = :sequenceNumber"
//	    			, PszTriggerNonPerson.class)
//	    		    .setParameter("sequenceNumber", sequenceNumber)
//	    		    .getResultList();
//	    	if(resultList != null && resultList.size() > 0) {
//	    		return resultList.get(0);
//	    	}
//	    } 
//	    catch (Exception e) {
//	    	e.printStackTrace();
//	    } 
//	    finally {
//	    	em.close();
//	    }
//	    return null;	
//	}
	
//	/**
//	 * findByCompletionStatusOrderBySequenceNumber
//	 * @param completionStatus
//	 * @return List<PszTriggerNonPerson>
//	 */
//	public static List<PszTriggerNonPerson> findByCompletionStatusOrderBySequenceNumber(String completionStatus) {
//		System.out.println("*** PszTriggerNonPerson.findByCompletionStatusOrderBySequenceNumber()");
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//		EntityManager em = emfactory.createEntityManager();
//	    try {
//	    	return em.createQuery(
//	    		    "SELECT p FROM PszTriggerNonPerson p "
//	    		    		+ "WHERE TRIM(UPPER(p.completionStatus)) = :completionStatus "
//	    		    		+ "ORDER BY p.sequenceNumber ASC "
//	    		    , PszTriggerNonPerson.class)
//	    		    .setParameter("completionStatus", completionStatus.toUpperCase().trim())
//	    		    .getResultList();
//	    }
//	    catch (Exception e) {
//	    	e.printStackTrace();
//	    } 
//	    finally {
//	    	em.close();
//	    }
//	    return null;	
//   }
	
//	/**
//	 * findByCompletionStatusAndProcessName
//	 * @param completionStatus
//	 * @param processName
//	 * @return List<PszTriggerNonPerson>
//	 */
//	public static List<PszTriggerNonPerson> findByCompletionStatusAndProcessName(String completionStatus, String processName) {
//		System.out.println("*** PszTriggerNonPerson.findByCompletionStatusAndProcessName()");
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//		EntityManager em = emfactory.createEntityManager();
//	    try {
//	    	return em.createQuery(
//	    		    "SELECT p FROM PszTriggerNonPerson p WHERE TRIM(UPPER(p.completionStatus)) = :completionStatus "
//	    		    		+ "AND TRIM(UPPER(p.processName)) = :processName "
//	    		    		+ "ORDER BY p.sequenceNumber ASC "
//	    		    , PszTriggerNonPerson.class)
//	    		    .setParameter("completionStatus", completionStatus.toUpperCase().trim())
//	    		    .setParameter("processName", processName.toUpperCase().trim())
//	    		    .getResultList();
//	    }
//	    catch (Exception e) {
//	    	e.printStackTrace();
//	    } 
//	    finally {
//	    	em.close();
//	    }
//	    return null;	
//   }

//	/**
//	 * @param completionStatus
//	 * @param sequenceNumber
//	 * @return numberOfRecordsUpdated
//	 */
//	public static int update(PszTriggerNonPerson trigger) {
//		System.out.println("*** PszTriggerNonPerson.setCompletionStatusBySequenceNumber()");
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//		EntityManager em = emfactory.createEntityManager();
//		int numberOfRecordsUpdated = 0;
//	    try {
//	    	em.getTransaction().begin();
//	    	em.merge(trigger);
//	    	em.getTransaction().commit();
//	    }
//	    catch (Exception e) {
//	    	e.printStackTrace();
//	    } 
//	    finally {
//	    	em.close();
//	    }
//	    return numberOfRecordsUpdated;
//	}

}

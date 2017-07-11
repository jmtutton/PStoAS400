package erd.model;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entity superclass for trigger table entities
 * @author John Tutton john@tutton.net
 * @see TriggerEmployee
 * @see TriggerNonPerson
 */
@Entity
@Inheritance(strategy  = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class PszTriggerSuperclass implements Serializable {
	public static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	
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
	protected String processName;
	   
	@Column(name="TASK_FLAG", nullable=false, length=1)  //'P', 'C', 'E', 'W'
	protected String completionStatus;

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
		logger.debug("findPending()");
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

	/**
	 * 
	 * @param sequenceNumber
	 * @param triggerTypeClassName
	 * @return PszTriggerSuperclass record
	 */
	public static PszTriggerSuperclass findBySequenceNumber(BigInteger sequenceNumber, String triggerTypeClassName) {
		logger.debug("findBySequenceNumber()");
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
		logger.debug("setCompletionStatusBySequenceNumber()");
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
	
	/**
	 * 
	 * @param completionStatus
	 * @return PszTriggerSuperclass records
	 */
	public static List<PszTriggerSuperclass> findByCompletionStatusOrderBySequenceNumber(String completionStatus) {
		logger.debug("findByCompletionStatusOrderBySequenceNumber()");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	return em.createQuery(
	    		    "SELECT p FROM PszTriggerSuperclass p "
	    		    		+ "WHERE TRIM(UPPER(p.completionStatus)) = :completionStatus "
	    		    		+ "ORDER BY p.sequenceNumber ASC "
	    		    , PszTriggerSuperclass.class)
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
	 * 
	 * @param completionStatus
	 * @param processName
	 * @return PszTriggerSuperclass records
	 */
	public static List<PszTriggerSuperclass> findByCompletionStatusAndProcessName(String completionStatus, String processName) {
		logger.debug("findByCompletionStatusAndProcessName()");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	return em.createQuery(
	    		    "SELECT p FROM PszTriggerSuperclass p "
	    		    		+ "WHERE TRIM(UPPER(p.completionStatus)) = :completionStatus "
	    		    		+ "AND TRIM(UPPER(p.processName)) = :processName "
	    		    		+ "ORDER BY p.sequenceNumber ASC "
	    		    		+ "LIMIT 150 "
	    		    , PszTriggerSuperclass.class)
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
	 * 
	 * @param limit
	 * @return PszTriggerSuperclass records
	 */
	public static List<PszTriggerSuperclass> findPendingWithLimit(Integer limit) {
		logger.debug("findPending()");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	return em.createQuery(
	    		    "SELECT p FROM PszTriggerSuperclass p "
	    		    		+ "WHERE TRIM(UPPER(p.completionStatus)) = 'P' "
	    		    		+ "ORDER BY p.sequenceNumber ASC "
	    		    , PszTriggerSuperclass.class)
	    			.setMaxResults(limit)
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
	 * 
	 * @param trigger
	 * @return
	 */
	public static void update(PszTriggerSuperclass trigger) {
		logger.debug("update()");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	em.getTransaction().begin();
	    	em.merge(trigger);
	    	em.getTransaction().commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	}

	/**
	 * This procedure will get the trigger data that needs to be interfaced.
	 * @see Get-Trigger-Data-POI-Emp-Convert in ZHRI100A.SQR
	 */
	//TODO: 
	public static void findByPoiEmpConvert() {
		logger.debug("findByPoiEmpConvert()");
		
	}

}

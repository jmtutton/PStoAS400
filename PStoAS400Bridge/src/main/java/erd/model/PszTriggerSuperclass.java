package erd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Persistence;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.InheritanceType;

/**
 * Entity superclass for trigger table entities
 * @author	John Tutton john@tutton.net
 * @see		TriggerEmployee, TriggerNonPerson
 */
@MappedSuperclass
@Inheritance(strategy  = InheritanceType.TABLE_PER_CLASS)
abstract class PszTriggerSuperclass implements Serializable {
	public static final long serialVersionUID = 1L;
	
	protected static String ENTITY_NAME = "";

	@Id
	@Column(name = "SEQ_NBR", nullable = false, insertable = false, precision = 38)
	protected BigDecimal sequenceNumber;
	   
	@Column(name = "OPRID")
	protected String operatorId;

	@Column(name = "EMPLID")
	protected String employeeId;

	@Column(name = "EFFDT")
	@Temporal(TemporalType.DATE)
	protected Date effectiveDate;
	   
	@Column(name = "EFFSEQ", precision = 38)
	protected BigDecimal effectiveSequence;
	   
	@Column(name = "PROC_NAME")
	protected String processName;  //TODO: make enum
	   
	@Column(name = "TASK_FLAG")
	protected String completionStatus;  //TODO: make enum with values 'P', 'C', 'E'

	public BigDecimal getSequenceNumber() {
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
	public BigDecimal getEffectiveSequence() {
		return effectiveSequence;
	}
	public String getProcessName() {
		return processName != null ? processName.trim() :  processName;
	}
	
	public void setSequenceNumber(BigDecimal sequenceNumber) {
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
	public void setEffectiveSequence(BigDecimal effectiveSequence) {
		this.effectiveSequence = effectiveSequence;
	}
	public void setProcessName(String processName) {
		this.processName = processName != null ? processName.trim() :  processName;
	}
	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus != null ? completionStatus.trim() :  completionStatus;
	}
	
	//long count()
	//Boolean exists(ID id)
	//Iterable<T> findAll()
	//T findOne(ID id)
	//<S extends T> S save(S entity)
	public static <T extends PszTriggerSuperclass> T findOne(Integer seqNum) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//		EntityManager em = emfactory.createEntityManager();
//	    try {
//	    	return (T) em.createQuery(
//	    		    "SELECT trigger FROM " + ENTITY_NAME + " trigger WHERE trigger.sequenceNumber = :seqNum")
//	    		    .setParameter("seqNum", seqNum)
//	    		    .getSingleResult();
//	    } 
//	    catch (Exception e) {
//	       e.printStackTrace();
//	    } 
	    return null;	
	}
	
	public static List<?> findByCompletionStatus(String status) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		
	    try {
	    	return em.createQuery(
	    		    "SELECT trigger FROM " + ENTITY_NAME + " trigger WHERE UPPER(trigger.completionStatus) LIKE UPPER(:status) ORDER BY trigger.sequenceNumber")
	    		    .setParameter("status", status)
	    		    .getResultList();
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	}
	
	public static List<?> findWhereCompletionStatusIsP() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		
	    try {
	    	return em.createQuery(
	    		    "SELECT trigger FROM " + ENTITY_NAME + " trigger WHERE UPPER(trigger.completionStatus) LIKE 'P' ORDER BY trigger.sequenceNumber")
	    		    .getResultList();
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	}
	
	public static List<?> findWhereCompletionStatusIsNotP() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		
	    try {
	    	return em.createQuery(
	    		    "SELECT trigger FROM " + ENTITY_NAME + " trigger WHERE UPPER(trigger.completionStatus) NOT LIKE 'P' ORDER BY trigger.sequenceNumber")
	    		    .getResultList();
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
    return null;	
    }
	
	public static List<?> findAll() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		
	    try {
	    	return em.createQuery("SELECT trigger FROM " + ENTITY_NAME + " trigger ORDER BY trigger.sequenceNumber", PszTriggerSuperclass.class)
	    		    .getResultList();
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
	}
	   
	public static int deleteBySequenceNumber(Integer seqNum) {
//			!----------------------------------------------------------------------
//			! Procedure:  Delete-Trigger-Record
//			! Desc:  Deletes all trigger records where the sequence number exists in the history file
//			!----------------------------------------------------------------------
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		int numberOfRecordsDeleted = 0;
		
	    try {
	    	em.getTransaction().begin();
//		    	DELETE from PS_ZHRT_INTTRIGGER
//		    	  WHERE SEQ_NBR IN (SELECT SEQ_NBR FROM PS_ZHRT_HISTRIGGER)
	    	numberOfRecordsDeleted = em.createQuery(
	    		    "DELETE " + ENTITY_NAME + " WHERE sequenceNumber = :sequenceNumber")
	    		    .setParameter("sequenceNumber", seqNum)
	    		    .executeUpdate();
	    	em.getTransaction().commit();
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    }
	    return numberOfRecordsDeleted;
	}

	public static List<?> GetTriggerDataNonEmp() {
//		!----------------------------------------------------------------------
//		! Procedure:  Get-Trigger-Data-NonEmp
//		! Desc:  This procedure will get the trigger data for non employees and multiple 
//		! EIDs that needs to be interfaced
//		!----------------------------------------------------------------------
//		Begin-Procedure Get-Trigger-Data-NonEmp
//		Let $NCompletionStatus = 'P'   !Initialize the CompletionStatus field
//		Begin-Select loops=150
//		RN.SEQ_NBR
//		    MOVE &RN.SEQ_NBR TO #NWrkSequence
//		RN.OPRID
//		    LET $NAuditOprid = Ltrim(Rtrim(&RN.OPRID,' '),' ')
//		    LET $AuditOprid = Ltrim(Rtrim(&RZ.OPRID,' '),' ')
//		RN.EMPLID
//		    LET $NPSEmplid = Ltrim(Rtrim(&RN.EMPLID,' '),' ')
//		    Move $NPSEmplid to #NWrk_EmplID1
//		    LET $NWrk_Emplid2 =  edit(#NWrk_EmplID1,'099999999')
//		to_char(RN.EFFDT, 'YYYY-MM-DD') &RNEFFDT
//		    LET $NPSEffdt = &RNEFFDT
//		RN.EFFSEQ
//		    Move &RN.EFFSEQ to #NPSEffSeq
//		RN.PROC_NAME
//		    LET $NWrkProcess = ltrim(rtrim(&RN.PROC_NAME,' '),' ')           !Remove leading and trailing blanks
//		RN.SEQUENCE 
//		    move &RN.SEQUENCE TO #indexNum 
//		   DO Call-Programs-NonEmp  
//		   IF  $NPSEmplid = ''             !ZHR_MOD_ZHRI100A_110A
//		      Let $NCompletionStatus = 'E'
//		   End-if
//		   Let $PoiFlag = 'Y'
//		   If $NCompletionStatus <> 'P'
//		     Do Update-Trigger-Row_NonEmp
//		   End-If  
//		from PS_ZHRT_ALTTRIGGER RN
//		where RN.TASK_FLAG = 'P'
//		and (RN.EFFDT <= $AsOfToday or RN.PROC_NAME='ZHRI201A' or  RN.PROC_NAME='ZHRI206A')
//		and (case when proc_name in ('ZHRI201A', 'ZHRI206A') then SEQ_NBR else SEQ_NBR*10 END) = 
//		      (select min(case when proc_name in ('ZHRI201A', 'ZHRI206A') then SEQ_NBR else SEQ_NBR*10 END)  
//		                 from  PS_ZHRT_ALTTRIGGER RN2
//		                  where RN2.EMPLID = RN.EMPLID
//		                    AND RN2.SEQUENCE = RN.SEQUENCE
//		                    AND RN2.PROC_NAME = RN.PROC_NAME 
//		                    and RN2.TASK_FLAG = 'P'                                         
//		                    and (RN2.EFFDT <= SYSDATE or RN2.PROC_NAME='ZHRI201A' or        
//		                         RN2.PROC_NAME='ZHRI206A'))                                 
//		End-Select
//		End-Procedure Get-Trigger-Data-NonEmp
		return null;
	}
//
//	public static int updateCompletionStatus(Integer seqNum, String status) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//		EntityManager em = emfactory.createEntityManager();
//		int numberOfRecordsUpdated = 0;
//	    try {
//	    	em.getTransaction().begin();
//	    	numberOfRecordsUpdated = em.createQuery(
//	    		    "UPDATE " + ENTITY_NAME + " SET completionStatus = UPPER(:status) WHERE sequenceNumber = :sequenceNumber")
//	    		    .setParameter("sequenceNumber", seqNum)
//	    		    .setParameter("status", status)
//	    		    .executeUpdate();
////				//alternatively:
////		    	TriggerEmployee trigger = em.find(TriggerEmployee.class, 1);
////		    	trigger.setCompletionStatus(status);
//	    	em.getTransaction().commit();
//	    }
//	    catch (Exception e) {
//	       e.printStackTrace();
//	    }
//	    return numberOfRecordsUpdated;
//	}
	
	public enum CompletionStatusCode {
		P ("pending"), C ("completed"), E ("error");
		
	    private String description;

	    CompletionStatusCode(String description) {
	        this.description = description;
	    }

	    public String description() {
	        return description;
	    }
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
	
}

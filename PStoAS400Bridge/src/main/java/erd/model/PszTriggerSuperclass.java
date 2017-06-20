package erd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Persistence;
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
	@Column(name = "SEQ_NBR", nullable = false, insertable = false)
	protected Integer sequenceNumber;
	   
	@Column(name = "OPRID")
	protected String operatorId;

	@Column(name = "EMPLID")
	protected String employeeId;

	@Column(name = "EFFDT")
	protected Date effectiveDate;
	   
	@Column(name = "EFFSEQ")
	protected BigDecimal effectiveSequence;
	   
	@Column(name = "PROC_NAME")
	protected String processName;  //TODO: make enum
	   
	@Column(name = "TASK_FLAG")
	protected String completionStatus;  //TODO: make enum with values 'P', 'C', 'E'

	public void setCompletionStatus(String status) {
		this.completionStatus = status.trim();
	}
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	public String getCompletionStatus() {
		return completionStatus.trim();
	}
	public String getOperatorId() {
		return operatorId.trim();
	}
	public String getEmployeeId() {
		return employeeId.trim();
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public BigDecimal getEffectiveSequence() {
		return effectiveSequence;
	}
	public String getProcessName() {
		return processName.trim();
	}
	
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public void setEffectiveSequence(BigDecimal effectiveSequence) {
		this.effectiveSequence = effectiveSequence;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
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

	
	public static List<?> GetTriggerData() {
//		!----------------------------------------------------------------------
//		! Procedure:  Get-Trigger-Data
//		! Desc:  This procedure will get the trigger data that needs to be interfaced
//		!----------------------------------------------------------------------
//		Begin-Procedure Get-Trigger-Data
//		Let $CompletionStatus = 'P'   !Initialize the CompletionStatus field
//		Begin-Select loops=150
//		RZ.SEQ_NBR
//		    MOVE &RZ.SEQ_NBR TO #WrkSequence
//		RZ.OPRID
//		    LET $AuditOprid = Ltrim(Rtrim(&RZ.OPRID,' '),' ')
//		RZ.EMPLID
//		    LET $PSEmplid = Ltrim(Rtrim(&RZ.EMPLID,' '),' ')
//		    Move $PSEmplid to #Wrk_EmplID1
//		    LET $Wrk_Emplid2 =  edit(#Wrk_EmplID1,'099999999')
//		to_char(RZ.EFFDT, 'YYYY-MM-DD') &RZEFFDT
//		    LET $PSEffdt = &RZEFFDT
//		RZ.EFFSEQ
//		    Move &RZ.EFFSEQ to #PSEffSeq
//		RZ.PROC_NAME
//		    LET $WrkProcess = ltrim(rtrim(&RZ.PROC_NAME,' '),' ')           !Remove leading and trailing blanks
//		 If $file_open = 'N'
//		 !   open $open_file1 as 1 for-append record=337
//		 !   let $file_open = 'Y'
//		 End-If
//		    Do Check-If-Contractor
//		    Let $PoiFlag = 'N'    !Surya Added
//		    If $Found = 'N'     !Not a contractor
//		     and  $PSEmplid <> ''    ! not a blank emplid   ZHR_MOD_ZHRI100A_110A
//		!CQ 103011 Added a check for 'ZHRI102A' - to see if corresponding row on JOB 
//		       if $WrkProcess = 'ZHRI102A'
//		          Do Check-If-Correct102A               
//		          if $OK-to-process = 'Y'
//		             Do Call-Programs
//		          else
//		             Let $CompletionStatus = 'C'
//		             Do Update-Trigger-Row
//		          end-if
//		       else
//		          Do Call-Programs
//		       end-if
//		    Else
//		       If $Found = 'Y'
//		        Let $CompletionStatus = 'C'
//		       End-if
//		       IF  $PSEmplid = ''             !ZHR_MOD_ZHRI100A_110A
//		       Let $CompletionStatus = 'E'
//		       End-if
//		    End-If    !$Found = 'N'
//		    If $CompletionStatus <> 'P'
//		       If ($ADAction_Code <> '') AND ($ADLegOprid <> '')
//		         IF $AdFound = 'N'
//		         End-If
//		       End-If
//		     Do Update-Trigger-Row
//		    End-If    !$CompletionStatus <> 'P'
//		from PS_ZHRT_INTTRIGGER RZ
//		    ,PS_JOB JB
//		where RZ.TASK_FLAG = 'P'	
//		!************************************************************************************
//		! Start : ZHR_MOD_INTERFACE_PREHIRE
//		!************************************************************************************
//		  and (RZ.EFFDT <= $AsOfToday or RZ.PROC_NAME='ZHRI101A' or  RZ.PROC_NAME='ZHRI106A')
//		!************************************************************************************
//		! End : ZHR_MOD_INTERFACE_PREHIRE
//		!************************************************************************************
//		!************************************************************************************
//		!        PeopleCode 8.0 fires in different order than 8.3, need to insure the new hire and rehire
//		!        appear before the other changes even if they don't show up first in the sequence.
//		!************************************************************************************
//		  and (case when proc_name in ('ZHRI101A', 'ZHRI106A') then SEQ_NBR else SEQ_NBR*10 END) = 
//		      (select min(case when proc_name in ('ZHRI101A', 'ZHRI106A') then SEQ_NBR else SEQ_NBR*10 END)  
//		                 from  PS_ZHRT_INTTRIGGER RZ2
//		                  where RZ2.EMPLID = RZ.EMPLID
//		                    and RZ2.TASK_FLAG = 'P'
//		                    and (RZ2.EFFDT <= SYSDATE or RZ2.PROC_NAME='ZHRI101A' or
//		                         RZ2.PROC_NAME='ZHRI106A'))
//		!**********************************************************************************
//		! Andy Lee-Sue  10/08/2008 - Join in JOB record to ensure that only employees that 
//		!                            have a JOB record get processed.                      
//		!**********************************************************************************
//		  and JB.EMPLID = RZ.EMPLID
//		  and JB.EFFDT = (SELECT MAX(JB2.EFFDT)
//		                    FROM  PS_JOB JB2
//		                   WHERE  JB2.EMPLID = JB.EMPLID
//		                     AND  JB2.EMPL_RCD = JB.EMPL_RCD)
//		  and JB.EFFSEQ = (SELECT MAX(JB3.EFFSEQ)
//		                     FROM PS_JOB JB3
//		                    WHERE JB3.EMPLID = JB.EMPLID
//		                     AND  JB3.EMPL_RCD = JB.EMPL_RCD
//		                     AND  JB3.EFFDT = JB.EFFDT)
//		End-Select
//		End-Procedure Get-Trigger-Data
		return null;
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

	public static int updateCompletionStatus(Integer seqNum, String status) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		int numberOfRecordsUpdated = 0;
	    try {
	    	em.getTransaction().begin();
	    	numberOfRecordsUpdated = em.createQuery(
	    		    "UPDATE " + ENTITY_NAME + " SET completionStatus = UPPER(:status) WHERE sequenceNumber = :sequenceNumber")
	    		    .setParameter("sequenceNumber", seqNum)
	    		    .setParameter("status", status)
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
	
}

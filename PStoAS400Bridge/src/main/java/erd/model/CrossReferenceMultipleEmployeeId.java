package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the PS_ZHRR_MULTPL_EID database table.
 * Cross-Reference of Non-Person Employee data to Legacy Non-Person Employee data
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRR_MULTPL_EID")
@NamedQuery(name="CrossReferenceMultipleEmployeeId.findAll", query="SELECT p FROM CrossReferenceMultipleEmployeeId p")
public class CrossReferenceMultipleEmployeeId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="ZHRF_LEG_EMPL_ID", nullable=false, length=5)
	private String legacyEmployeeId;

	@Column(name="BUSINESS_UNIT", nullable=false, length=5)
	private String businessUnit;

	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String statusAsOfEffectiveDate;

	@Column(name="EFFDT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="EFFSEQ", nullable=false, precision=38)
	private Integer effectiveSequence;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="\"SEQUENCE\"", nullable=false, precision=38)
	private Integer sequence;

	@Column(name="ZHRF_ALT_EID_TYPE", nullable=false, length=2)
	private String legacyAltEidType;

	@Column(name="ZHRF_BRANCH", nullable=false, length=2)
	private String legacyBranch;

	@Column(name="ZHRF_GRP_NBR", nullable=false, length=2)
	private String legacyGroupNumber;

	public CrossReferenceMultipleEmployeeId() {
	}

	public String getBusinessUnit() {
		return this.businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getStatusAsOfEffectiveDate() {
		return this.statusAsOfEffectiveDate;
	}

	public void setStatusAsOfEffectiveDate(String statusAsOfEffectiveDate) {
		this.statusAsOfEffectiveDate = statusAsOfEffectiveDate;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Integer getEffectiveSequence() {
		return this.effectiveSequence;
	}

	public void setEffectiveSequence(Integer effectiveSequence) {
		this.effectiveSequence = effectiveSequence;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Timestamp getLastUpdatedDateAndTime() {
		return this.lastUpdatedDateAndTime;
	}

	public void setLastUpdatedDateAndTime(Timestamp lastUpdatedDateAndTime) {
		this.lastUpdatedDateAndTime = lastUpdatedDateAndTime;
	}

	public String getLastUpdatedUserId() {
		return this.lastUpdatedUserId;
	}

	public void setLastUpdatedUserId(String lastUpdatedUserId) {
		this.lastUpdatedUserId = lastUpdatedUserId;
	}

	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getLegacyAltEidType() {
		return this.legacyAltEidType;
	}

	public void setLegacyAltEidType(String legacyAltEidType) {
		this.legacyAltEidType = legacyAltEidType;
	}

	public String getLegacyBranch() {
		return this.legacyBranch;
	}

	public void setLegacyBranch(String legacyBranch) {
		this.legacyBranch = legacyBranch;
	}

	public String getLegacyGroupNumber() {
		return this.legacyGroupNumber;
	}

	public void setLegacyGroupNumber(String legacyGroupNumber) {
		this.legacyGroupNumber = legacyGroupNumber;
	}

	public String getLegacyEmployeeId() {
		return this.legacyEmployeeId;
	}

	public void setLegacyEmployeeId(String legacyEmployeeId) {
		this.legacyEmployeeId = legacyEmployeeId;
	}


	/**
	 * HR205-Get-Emp-Data from ZHRI205A.SQC
	 * Gets the employees data from the POI table that needs to be interfaced to the legacy system
	 * @param employeeId
	 */
	public CrossReferenceMultipleEmployeeId HR205_getEmpData(String employeeId) {
//		BEGIN-PROCEDURE HR205-GET-EMP-DATA
//		BEGIN-SELECT
//		EMP5.ZHRF_GRP_NBR,
//		EMP5.ZHRF_BRANCH,
//		EMP5.ZHRF_ALT_EID_TYPE  &EMP5.ZHRF_ALT_EID_TYPE
//		    let $PSGroup = ltrim(rtrim(&EMP5.ZHRF_GRP_NBR,' '),' ') 
//		    let $PSbranch = ltrim(rtrim(&EMP5.ZHRF_BRANCH,' '),' ')
//		    let $PSNickname = ''
//		    let $PSDeptid  = ltrim(rtrim('A',' '),' ') 
//		    let $PSReferral_Source  = ''
//		    let $PSAddress =  ''                    
//		    let $PSCity = ''
//		    let $EMPType = ltrim(rtrim(&EMP5.ZHRF_ALT_EID_TYPE,' '),' ')
//		    do HR205-GET-EMP-POI
//		    If $PSPoi= 'Y'
//		      do HR205-Get-primEid-POIdata
//		    else
//		      let $PSPosition = '98'    !for Alternate EIDs who are employees, Leg Position will be 98
//		      do HR205-Get-Alternate_Type
//		    end-if
//		FROM PS_ZHRR_MULTPL_EID EMP5
//		WHERE EMP5.EMPLID = $Wrk_Emplid
//		AND EMP5.SEQUENCE = $Wrk_indexNum
//		AND EMP5.EFFDT = (SELECT MAX(EMP1.EFFDT) FROM PS_ZHRR_MULTPL_EID EMP1
//		                WHERE EMP1.EMPLID = EMP5.EMPLID
//		                AND to_char(EMP1.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		End-Select
//		End-Procedure HR205-Get-EMP-data
		return null;
	}

	public CrossReferenceMultipleEmployeeId MainSqlEmp(String employeeId) {
//		!******************************
//		!get the max effdt for every employee
//		!  -store the last inactive row before the max effdt into s_dt. If null, use dummy date.
//		!  -get the min active effdt after s_dt
//		BEGIN-PROCEDURE MAIN-SQL-EMP
//		BEGIN-SELECT
//		B.EMPLID
//		B.EFFDT &B.EFFDT 
//		B.EFF_STATUS
//		  let $MAX_EFFDT =&B.EFFDT
//		  do get-last-inactive-dt-emp
//		  !if there are no inactive rows before, assign dummy date
//		  if $last_inactive_dt = '' 
//		    let $s_dt = strtodate('01-JAN-1900','DD-MON-YYYY')
//		  else
//		    let $s_dt = $last_inactive_dt
//			END-IF
//			!get the min(effdt) greater than the last inactive date
//			do get-min-effdt-emp
//		FROM PS_ZHRR_MULTPL_EID B
//		WHERE B.EMPLID = $Wrk_Emplid
//		AND B.SEQUENCE = $Wrk_indexNum
//		AND B.EFFDT = (SELECT MAX(B1.EFFDT) FROM PS_ZHRR_MULTPL_EID B1 WHERE B1.EMPLID = B.EMPLID AND B1.SEQUENCE = B.SEQUENCE and B1.EFFDT <= SYSDATE)
//		END-SELECT
//		END-PROCEDURE MAIN-SQL-EMP
		return null;
	}

	public CrossReferenceMultipleEmployeeId getMinEffdtEmp(String employeeId) {
		//!****************************
		//begin-procedure get-min-effdt-emp
		//LET $LegServiceDate =''
		//begin-select
		//min(B2.EFFDT) &B2.EFFDT
		//  Let $min_active_effdt = &B2.EFFDT
		//  Let $LegServiceDate = datetostr(strtodate($min_active_effdt,'DD-MON-YYYY'),'YYYYMMDD')
		//from PS_ZHRR_MULTPL_EID B2
		//WHERE B2.EMPLID = $Wrk_Emplid
		//AND B2.SEQUENCE = $Wrk_indexNum
		//AND B2.EFFDT > $s_dt
		//end-select
		//end-procedure get-min-effdt-emp		  
		return null;
	}

	public CrossReferenceMultipleEmployeeId getLastInactiveDtEmp(String employeeId) {
		//!*************************************
		//begin-procedure get-last-inactive-dt-emp
		//begin-select
		//B3.EFFDT &B3.EFFDT
		//  Let $last_inactive_dt =&B3.EFFDT
		//from PS_ZHRR_MULTPL_EID B3
		//WHERE B3.emplid = $Wrk_Emplid
		//AND B3.SEQUENCE = $Wrk_indexNum
		//AND B3.EFFDT = (SELECT MAX(B3A.EFFDT) FROM PS_ZHRR_MULTPL_EID B3A WHERE B3A.EMPLID = B3.EMPLID AND B3A.SEQUENCE = B3.SEQUENCE AND B3A.EFF_STATUS = 'I' AND to_char(B3A.EFFDT,'YYYY-MM-DD') < $MAX_EFFDT)
		//end-select
		//end-procedure get-last-inactive-dt-emp
		return null;
	}

	/**
	 * HR202-Get-Term-Date from ZHRI202A.SQC
	 * Gets the term date for POI/EMP
	 * @param employeeId
	 */
	public CrossReferenceMultipleEmployeeId HR202_getTermDate(String employeeId) {
		//Begin-Procedure HR202-get-term-date
		//Begin-Select
		//POI2.EFFDT
		//let $PSTermDate = LTRIM(RTRIM(&POI2.EFFDT,' '),' ')
		//FROM PS_ZHRT_PER_POI_TR POI2
		//WHERE POI2.EMPLID =  $Wrk_Emplid
		//AND POI2.EFFDT = (SELECT MAX(POI1.EFFDT) FROM PS_ZHRT_PER_POI_TR POI1
		//WHERE POI1.EMPLID = POI2.EMPLID
		//AND POI1.POI_TYPE = POI2.POI_TYPE
		//AND to_char(POI1.EFFDT,'YYYY-MM-DD') <= $PSDateIn)
		//End-Select
		//Begin-Select
		//EMP2.EFFDT
		//let $PSTermDate = LTRIM(RTRIM(&EMP2.EFFDT,' '),' ')
		//FROM PS_ZHRR_MULTPL_EID EMP2
		//WHERE EMP2.EMPLID = $Wrk_Emplid
		//AND EMP2.SEQUENCE = $Wrk_indexNum
		//AND EMP2.EFFDT = (SELECT MAX(EMP1.EFFDT) FROM PS_ZHRR_MULTPL_EID EMP1
		//WHERE EMP1.EMPLID = EMP2.EMPLID
		//AND to_char(EMP1.EFFDT,'YYYY-MM-DD') <= $PSDateIn)
		//End-Select
		//End-Procedure HR202-get-term-date
		return null;
	}

	/**
	 * HR201-Get-Emp-Data from ZHRI201A.SQC
	 * Gets the employees data from the POI table that needs to be interfaced to the legacy system
	 * @param employeeId
	 */
	public CrossReferenceMultipleEmployeeId HR201_getEmpData(String employeeId) {
		//Begin-Procedure HR201-Get-EMP-data
		//Begin-Select
		//EMP.ZHRF_GRP_NBR,
		//EMP.ZHRF_BRANCH,
		//EMP.ZHRF_ALT_EID_TYPE  &EMP.ZHRF_ALT_EID_TYPE
		//let $LegGroup = ltrim(rtrim(&EMP.ZHRF_GRP_NBR,' '),' ') 
		//let $LegBranch = ltrim(rtrim(&EMP.ZHRF_BRANCH,' '),' ')
		//let $LegNickName = ''
		//let $LegDepartment  = ltrim(rtrim('A',' '),' ') 
		//let $LegReferralSource  = ''
		//let $LegAddress1 =  ''                    
		//let $LegCity = ''  
		//let $EMPType = ltrim(rtrim(&EMP.ZHRF_ALT_EID_TYPE,' '),' ')
		//do HR201-GET-EMP-POI
		//If $PSPoi= 'Y'
			//do HR201-Get-primEid-POIdata
		//else
			//let $LegPosition = '98' !for Alternate EIDs who are employees, Leg Position will be 98
			//do HR201-Get-Alternate_Type
		//end-if
		//FROM PS_ZHRR_MULTPL_EID EMP
		//WHERE EMP.EMPLID = $Wrk_Emplid
		//AND EMP.SEQUENCE = $Wrk_indexNum
		//AND EMP.EFFDT = (SELECT MAX(EMP1.EFFDT) FROM PS_ZHRR_MULTPL_EID EMP1
		//WHERE EMP1.EMPLID = EMP.EMPLID
		//AND to_char(EMP1.EFFDT,'YYYY-MM-DD') <= $Wrk_Effdt)
		//End-Select
		//End-Procedure HR201-Get-EMP-data
		return null;
	}


	/**
	 * Get-LegId-For-SeqNum from ZHRI102A.SQR
	 * This routine gets the Legacy ID from Alternate EID Table
	 * @param employeeId
	 * @param sequence
	 */
	public static String ZHRI100A_getLegIdForSeqNum(String employeeId, Integer sequence) {
//		!check if the multiple EID table has the EID!
//		BEGIN-SELECT
//		MULT.ZHRF_LEG_EMPL_ID
//			LET $PSOprid = LTRIM(RTRIM(&MULT.ZHRF_LEG_EMPL_ID,' '),' ')
//		    IF $PSOprid <> ''
//		    	LET $Found = 'Y'
//		    END-IF
//		FROM PS_ZHRR_MULTPL_EID MULT
//		WHERE MULT.Emplid = $Wrk_Emplid  
//		AND MULT.Sequence = #indexNum      
//		END-SELECT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery("SELECT UPPER(TRIM(c.legacyEmployeeId)) FROM CrossReferenceEmployeeId c "
	    			+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    			+ "AND sequence = :sequence", String.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("sequence", sequence)
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

	/**
	 * Update-OprId from ZHRI100A.SQR
	 * This routine will UPDATE table PS_ZHRR_MULTPL_EID for the Non Employees and Multiple EIDs if the employee has a record in HR036P
	 * @param employeeId
	 * @param legacyEmployeeId
	 * @param sequence
	 */
	public static void ZHRI100A_updateOprId(String employeeId, String legacyEmployeeId, Integer sequence) {
		//BEGIN-PROCEDURE UPDATE-OPRID
		//LET $Update-Error-Flag = 'N'
		//!Update the PS_ZHRR_MULTPL_EID table for Multiple EIDs 
		//BEGIN-SQL  On-Error = Update-Error
		//UPDATE PS_ZHRR_MULTPL_EID
		//SET ZHRF_LEG_EMPL_ID = $LegEmplid
		//WHERE EMPLID = $Wrk_Emplid
		//AND SEQUENCE = #indexNum
		//END-SQL
		//END-PROCEDURE UPDATE-OPRID
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<CrossReferenceMultipleEmployeeId> resultList = em.createQuery(
	    			"SELECT c FROM CrossReferenceMultipleEmployeeId c "
	    					+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    					+ "AND sequence = :sequence", CrossReferenceMultipleEmployeeId.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("sequence", sequence)
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		CrossReferenceMultipleEmployeeId xrefEmployeeId = resultList.get(0);
		    	xrefEmployeeId.setLegacyEmployeeId(legacyEmployeeId.toUpperCase().trim());
		    	em.getTransaction().begin();
		    	em.persist(xrefEmployeeId);
		    	em.getTransaction().commit();
	    	}
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	}

	public static Date findEffectiveDateByEmployeeIdAndSequence(String wrkEmplId, Integer wrkIndexNum, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Insert-OprId from ZHRI100A.SQR
	 * This routine will insert a row into the PS_ZHRT_EMPID_CREF table for the employee if the employee has a record in HR006P
	 * @param employeeId
	 * @param legacyEmployeeId
	 */
	public static void ZHRI100A_insertOprId(String employeeId, String legacyEmployeeId) {
		//BEGIN-PROCEDURE INSERT-OPRID
		//LET $Insert-Error-Flag = 'N'
		//!Add to the PS_ZHRT_EMPID_CREF table
		//BEGIN-SQL On-Error = Insert-Error
		//	INSERT INTO PS_ZHRT_EMPID_CREF (EMPLID, ZHRF_LEG_EMPL_ID) VALUES ($Wrk_Emplid, $LegEmplid)
		//END-SQL
		//END-PROCEDURE INSERT-OPRID
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
    		CrossReferenceMultipleEmployeeId xrefEmployeeId = new CrossReferenceMultipleEmployeeId();
	    	xrefEmployeeId.setEmployeeId(employeeId.toUpperCase().trim());
	    	xrefEmployeeId.setLegacyEmployeeId(legacyEmployeeId.toUpperCase().trim());
	    	em.getTransaction().begin();
	    	em.persist(xrefEmployeeId);
	    	em.getTransaction().commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	}

}
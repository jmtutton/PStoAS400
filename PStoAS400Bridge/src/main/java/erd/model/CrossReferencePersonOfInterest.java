package erd.model;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The persistent class for the PS_ZHRT_PER_POI_TR database table.
 * Cross-Reference of Person of Interest data to Legacy Person of Interest data
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRT_PER_POI_TR")
@NamedQuery(name="CrossReferencePersonOfInterest.findAll", query="SELECT p FROM CrossReferencePersonOfInterest p")
public class CrossReferencePersonOfInterest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="BUSINESS_UNIT", nullable=false, length=5)
	private String businessUnit;

	@Column(name="COMPANY", nullable=false, length=3)
	private String company;

	@Column(name="DEPTID", nullable=false, length=10)
	private String departmentId;

	@Column(name="DESCR254", nullable=false, length=254)
	private String description;

	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String statusAsOfEffectiveDate;

	@Column(name="EFFDT",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="EXPECTED_END_DATE")
	@Temporal(TemporalType.DATE)
	private Date expectedEndDate;

	@Column(name="JOBTITLE", nullable=false, length=30)
	private String jobtitle;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="LOCATION", nullable=false, length=10)
	private String location;

	@Column(name="MANAGER_ID", nullable=false, length=11)
	private String managerId;

	@Column(name="POI_TYPE", nullable=false, length=5) //Person of Interest Type
	private String poiType;

	@Column(name="REG_REGION", nullable=false, length=5)
	private String regulatoryRegion;

	@Column(name="REPORTS_TO", nullable=false, length=8)
	private String reportsTo;

	@Column(name="Z_POI_AFFIL_CD", nullable=false, length=11)
	private String legacyPoiAffilCode;

	@Column(name="ZHRF_BRANCH", nullable=false, length=2)
	private String legacyBranch;

	@Column(name="ZHRF_GRP_NBR", nullable=false, length=2)
	private String legacyGroupNumber;

	@Column(name="ZHRF_LEVEL", nullable=false, length=1)
	private String legacyLevel;

	@Column(name="ZHRF_POI_CATEGORY", nullable=false, length=3)
	private String legacyPoiCategory;

	@Column(name="ZHRF_PWORD_1", nullable=false, length=50)
	private String legacyPassword1;

	@Column(name="ZHRF_PWORD_2", nullable=false, length=50)
	private String legacyPassword2;

	@Column(name="ZHRF_SERVICE_ROLE", nullable=false, length=1)
	private String legacyServiceRole;

	public CrossReferencePersonOfInterest() {
	}

	public String getBusinessUnit() {
		return this.businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDeptid() {
		return this.departmentId;
	}

	public void setDeptid(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDescr254() {
		return this.description;
	}

	public void setDescr254(String description) {
		this.description = description;
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

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getExpectedEndDate() {
		return this.expectedEndDate;
	}

	public void setExpectedEndDate(Date expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

	public String getJobtitle() {
		return this.jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
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

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getPoiType() {
		return this.poiType;
	}

	public void setPoiType(String poiType) {
		this.poiType = poiType;
	}

	public String getRegulatoryRegion() {
		return this.regulatoryRegion;
	}

	public void setRegulatoryRegion(String regulatoryRegion) {
		this.regulatoryRegion = regulatoryRegion;
	}

	public String getReportsTo() {
		return this.reportsTo;
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getLegacyPoiAffilCode() {
		return this.legacyPoiAffilCode;
	}

	public void setLegacyPoiAffilCode(String legacyPoiAffilCode) {
		this.legacyPoiAffilCode = legacyPoiAffilCode;
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

	public String getLegacyLevel() {
		return this.legacyLevel;
	}

	public void setLegacyLevel(String legacyLevel) {
		this.legacyLevel = legacyLevel;
	}

	public String getLegacyPoiCategory() {
		return this.legacyPoiCategory;
	}

	public void setLegacyPoiCategory(String legacyPoiCategory) {
		this.legacyPoiCategory = legacyPoiCategory;
	}

	public String getLegacyPassword1() {
		return this.legacyPassword1;
	}

	public void setLegacyPassword1(String legacyPassword1) {
		this.legacyPassword1 = legacyPassword1;
	}

	public String getLegacyPassword2() {
		return this.legacyPassword2;
	}

	public void setLegacyPassword2(String legacyPassword2) {
		this.legacyPassword2 = legacyPassword2;
	}

	public String getLegacyServiceRole() {
		return this.legacyServiceRole;
	}

	public void setLegacyServiceRole(String legacyServiceRole) {
		this.legacyServiceRole = legacyServiceRole;
	}

	public CrossReferencePersonOfInterest HR205GetPoiData(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR205-Get-POI-data
//		! Desc:  Gets the POIs data from the POI table that needs to be
//		!        interfaced to the legacy system
//		!----------------------------------------------------------------------
//		Begin-Procedure HR205-Get-POI-data
//		Begin-Select
//		POI5.ZHRF_POI_CATEGORY
//		POI5.ZHRF_GRP_NBR,
//		POI5.ZHRF_BRANCH,
//		POI5.EXPECTED_END_DATE,
//		POI5.MANAGER_ID, 
//		POI5.ZHRF_PWORD_1,
//		POI5.ZHRF_PWORD_2 
//		    let $POICat = ltrim(rtrim(&POI5.ZHRF_POI_CATEGORY,' '),' ') 
//		    let $PSGroup = ltrim(rtrim(&POI5.ZHRF_GRP_NBR,' '),' ') 
//		    let $PSbranch = ltrim(rtrim(&POI5.ZHRF_BRANCH,' '),' ') 
//		    let $PSNickname = ltrim(rtrim(&POI5.EXPECTED_END_DATE,' '),' ') 
//		    let $PSDeptid  = ltrim(rtrim('A',' '),' ')  
//		    let $PSReferral_Source  = ltrim(rtrim(&POI5.MANAGER_ID,' '),' ') 
//		    let $PSAddress =  ltrim(rtrim(&POI5.ZHRF_PWORD_1,' '),' ')                      
//		    let $PSCity = ltrim(rtrim(&POI5.ZHRF_PWORD_2,' '),' ') 
//		      do HR205-Get-POI-LegPosNo
//		FROM PS_ZHRT_PER_POI_TR POI5
//		WHERE POI5.EMPLID =  $Wrk_Emplid
//		AND POI5.EFFDT = (SELECT MAX(POI1.EFFDT) FROM PS_ZHRT_PER_POI_TR POI1
//		                WHERE POI5.EMPLID = POI1.EMPLID
//		                AND POI5.POI_TYPE = POI1.POI_TYPE
//		                AND to_char(POI1.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		End-Select
//		End-Procedure HR205-Get-POI-data
		return null;
	}

	public CrossReferencePersonOfInterest HR205GetPrimEidPoiData(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR205-Get-primEid-POIdata
//		! Desc:  Gets the primary Eids data when the 
//		!        interfaced to the legacy system
//		!----------------------------------------------------------------------
//		Begin-Procedure HR205-Get-primEid-POIdata
//		Begin-Select
//		POI5A.ZHRF_POI_CATEGORY
//		POI5A.EXPECTED_END_DATE,
//		POI5A.MANAGER_ID, 
//		POI5A.ZHRF_PWORD_1,
//		POI5A.ZHRF_PWORD_2 
//		    let $POICat = ltrim(rtrim(&POI5A.ZHRF_POI_CATEGORY,' '),' ') 
//		    let $PSNickname = ltrim(rtrim(&POI5A.EXPECTED_END_DATE,' '),' ') 
//		    let $PSReferral_Source  = ltrim(rtrim(&POI5A.MANAGER_ID,' '),' ') 
//		    let $PSAddress =  ltrim(rtrim(&POI5A.ZHRF_PWORD_1,' '),' ')                      
//		    let $PSCity = ltrim(rtrim(&POI5A.ZHRF_PWORD_2,' '),' ') 
//		    do HR205-Get-POI-LegPosNo
//		FROM PS_ZHRT_PER_POI_TR POI5A
//		WHERE POI5A.EMPLID =  $Wrk_Emplid
//		AND POI5A.EFF_STATUS = 'A'
//		AND POI5A.EFFDT = (SELECT MAX(POI5A1.EFFDT) FROM PS_ZHRT_PER_POI_TR POI5A1
//		                WHERE POI5A1.EMPLID = POI5A.EMPLID
//		                AND POI5A1.EFF_STATUS = 'A'
//		                AND POI5A1.POI_TYPE = POI5A.POI_TYPE
//		                AND POI5A1.EFFDT <= SYSDATE)
//		End-Select
//		End-Procedure HR205-Get-primEid-POIdata
		return null;
	}

	public CrossReferencePersonOfInterest MainSqlPoi(String employeeId) {
//		!******************************
//		!get the max effdt for every employee
//		!  -store the last inactive row before the max effdt into s_dt. If null, use dummy date.
//		!  -get the min active effdt after s_dt
//		BEGIN-PROCEDURE MAIN-SQL-POI
//		BEGIN-SELECT
//		A.EMPLID
//		A.EFFDT &A.EFFDT
//		A.EFF_STATUS
//		  Let $MAX_EFFDT = (&A.EFFDT)
//		  do get-last-inactive-dt-poi
//		  !if there are no inactive rows before, assign dummy date
//		  if $last_inactive_dt = '' 
//		    let $s_dt = strtodate('01-JAN-1900','DD-MON-YYYY')
//		  else
//		    let $s_dt = $last_inactive_dt
//			END-IF
//			!get the min(effdt) greater than the last inactive date
//			do get-min-effdt-poi
//		FROM PS_ZHRT_PER_POI_TR A
//		WHERE A.EMPLID = $Wrk_Emplid
//		AND A.EFFDT = (SELECT MAX(A1.EFFDT) FROM PS_ZHRT_PER_POI_TR A1 WHERE A1.EMPLID = A.EMPLID AND A1.POI_TYPE = A.POI_TYPE AND A1.EFFDT <= SYSDATE)
//		END-SELECT
//		END-PROCEDURE MAIN-SQL-POI
		return null;
	}

	public CrossReferencePersonOfInterest getMinEffDtPoi(String employeeId) {
//		!****************************
//		begin-procedure get-min-effdt-poi
//		LET $LegServiceDate =''
//		begin-select
//		min(A2.EFFDT) &A2.EFFDT
//		  Let $min_active_effdt = (&A2.EFFDT)
//		  Let $LegServiceDate = datetostr(strtodate($min_active_effdt,'DD-MON-YYYY'),'YYYYMMDD')
//		from PS_ZHRT_PER_POI_TR A2
//		WHERE A2.EMPLID = $Wrk_Emplid
//		AND A2.EFFDT > $s_dt
//		end-select
//		end-procedure get-min-effdt-poi				
		return null;
	}

	public CrossReferencePersonOfInterest getLastInactiveDtPoi(String employeeId) {
//		!*************************************
//		begin-procedure get-last-inactive-dt-poi
//		begin-select
//		A3.EFFDT &A3.EFFDT 
//		  Let $last_inactive_dt = (&A3.EFFDT )
//		from Ps_Zhrt_Per_Poi_Tr A3
//		WHERE A3.emplid = $Wrk_Emplid
//		AND A3.EFFDT = (SELECT MAX(A3A.EFFDT) FROM PS_ZHRT_PER_POI_TR A3A WHERE A3A.EMPLID = A3.EMPLID AND A3A.EFF_STATUS = 'I' AND to_char(A3A.EFFDT,'YYYY-MM-DD') < $MAX_EFFDT)
//		end-select
//		end-procedure get-last-inactive-dt-poi		
		return null;
	}

	public CrossReferencePersonOfInterest HR202GetTermDate(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR202-get-term-date
//		! Desc:  Gets the term date for POI/EMP
//		!----------------------------------------------------------------------
//		Begin-Procedure HR202-get-term-date
//		Begin-Select
//		POI2.EFFDT
//		    let $PSTermDate = LTRIM(RTRIM(&POI2.EFFDT,' '),' ')
//		FROM PS_ZHRT_PER_POI_TR POI2
//		WHERE POI2.EMPLID =  $Wrk_Emplid
//		AND POI2.EFFDT = (SELECT MAX(POI1.EFFDT) FROM PS_ZHRT_PER_POI_TR POI1
//		                WHERE POI1.EMPLID = POI2.EMPLID
//		                AND POI1.POI_TYPE = POI2.POI_TYPE
//		                AND to_char(POI1.EFFDT,'YYYY-MM-DD') <= $PSDateIn)
//		End-Select
//		Begin-Select
//		EMP2.EFFDT
//		    let $PSTermDate = LTRIM(RTRIM(&EMP2.EFFDT,' '),' ')
//		FROM PS_ZHRR_MULTPL_EID EMP2
//		WHERE EMP2.EMPLID = $Wrk_Emplid
//		AND EMP2.SEQUENCE = $Wrk_indexNum
//		AND EMP2.EFFDT = (SELECT MAX(EMP1.EFFDT) FROM PS_ZHRR_MULTPL_EID EMP1
//		                WHERE EMP1.EMPLID = EMP2.EMPLID
//		                AND to_char(EMP1.EFFDT,'YYYY-MM-DD') <= $PSDateIn)
//		End-Select
//		End-Procedure HR202-get-term-date
		return null;
	}

	public CrossReferencePersonOfInterest HR201GetPoiData(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR201-Get-POI-data
//		! Desc:  Gets the POIs data from the POI table that needs to be
//		!        interfaced to the legacy system
//		!----------------------------------------------------------------------
//		Begin-Procedure HR201-Get-POI-data
//		Begin-Select
//		POI.ZHRF_POI_CATEGORY
//		POI.ZHRF_GRP_NBR,
//		POI.ZHRF_BRANCH,
//		POI.EXPECTED_END_DATE,
//		POI.MANAGER_ID, 
//		POI.ZHRF_PWORD_1,
//		POI.ZHRF_PWORD_2 
//		    let $POICat = ltrim(rtrim(&POI.ZHRF_POI_CATEGORY,' '),' ') 
//		    let $LegGroup = ltrim(rtrim(&POI.ZHRF_GRP_NBR,' '),' ') 
//		    let $LegBranch = ltrim(rtrim(&POI.ZHRF_BRANCH,' '),' ') 
//		    let $LegNickName = ltrim(rtrim(&POI.EXPECTED_END_DATE,' '),' ') 
//		    let $LegDepartment  = ltrim(rtrim('A',' '),' ') 
//		    let $LegReferralSource  = ltrim(rtrim(&POI.MANAGER_ID,' '),' ') 
//		    let $LegAddress1 =  ltrim(rtrim(&POI.ZHRF_PWORD_1,' '),' ')                      
//		    let $LegCity = ltrim(rtrim(&POI.ZHRF_PWORD_2,' '),' ') 
//		    do HR201-Get-POI-LegPosNo
//		FROM PS_ZHRT_PER_POI_TR POI
//		WHERE POI.EMPLID =  $Wrk_Emplid
//		AND POI.EFFDT = (SELECT MAX(POI1.EFFDT) FROM PS_ZHRT_PER_POI_TR POI1
//		                WHERE POI.EMPLID = POI1.EMPLID
//		                AND POI.POI_TYPE = POI1.POI_TYPE
//		                AND to_char(POI1.EFFDT,'YYYY-MM-DD') <= $Wrk_Effdt)
//		End-Select
//		End-Procedure HR201-Get-POI-data
		return null;
	}

	public CrossReferencePersonOfInterest HR201GetPrimEidPoiData(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR201-Get-primEid-POIdata
//		! Desc:  Gets the primary Eids data when the 
//		!        interfaced to the legacy system
//		!----------------------------------------------------------------------
//		Begin-Procedure HR201-Get-primEid-POIdata
//		Begin-Select
//		PED.ZHRF_POI_CATEGORY
//		PED.EXPECTED_END_DATE,
//		PED.MANAGER_ID, 
//		PED.ZHRF_PWORD_1,
//		PED.ZHRF_PWORD_2 
//		    let $POICat = ltrim(rtrim(&PED.ZHRF_POI_CATEGORY,' '),' ') 
//		    let $LegNickName = ltrim(rtrim(&PED.EXPECTED_END_DATE,' '),' ') 
//		    let $LegReferralSource  = ltrim(rtrim(&PED.MANAGER_ID,' '),' ') 
//		    let $LegAddress1 =  ltrim(rtrim(&PED.ZHRF_PWORD_1,' '),' ')                      
//		    let $LegCity = ltrim(rtrim(&PED.ZHRF_PWORD_2,' '),' ') 
//		    do HR201-Get-POI-LegPosNo
//		FROM PS_ZHRT_PER_POI_TR PED
//		WHERE PED.EMPLID =  $Wrk_Emplid
//		AND PED.EFF_STATUS = 'A'
//		AND PED.EFFDT = (SELECT MAX(PED1.EFFDT) FROM PS_ZHRT_PER_POI_TR PED1
//		                WHERE PED1.EMPLID = PED.EMPLID
//		                AND PED1.EFF_STATUS = 'A'
//		                AND PED1.POI_TYPE = PED.POI_TYPE
//		                AND PED1.EFFDT <= SYSDATE)
//		End-Select
//		End-Procedure HR201-Get-primEid-POIdata
		return null;
	}

	public static Date findEffectiveDateByEmployeeId(String wrkEmplId, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

}
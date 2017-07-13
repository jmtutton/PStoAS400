package erd.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
	private String department;

	@Column(name="DESCR254", nullable=false, length=254)
	private String description;

	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String status;

	@Column(name="EFFDT",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="EXPECTED_END_DATE")
	@Temporal(TemporalType.DATE)
	private Date expectedEndDate;

	@Column(name="JOBTITLE", nullable=false, length=30)
	private String jobTitle;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="LOCATION", nullable=false, length=10)
	private String location;

	@Column(name="MANAGER_ID", nullable=false, length=11)
	private String managerId;

	@Column(name="POI_TYPE", nullable=false, length=5) //Person of Interest Type
	private String type;

	@Column(name="REG_REGION", nullable=false, length=5)
	private String region;

	@Column(name="REPORTS_TO", nullable=false, length=8)
	private String reportsTo;

	@Column(name="Z_POI_AFFIL_CD", nullable=false, length=11)
	private String affil;

	@Column(name="ZHRF_BRANCH", nullable=false, length=2)
	private String branch;

	@Column(name="ZHRF_GRP_NBR", nullable=false, length=2)
	private String group;

	@Column(name="ZHRF_LEVEL", nullable=false, length=1)
	private String level;

	@Column(name="ZHRF_POI_CATEGORY", nullable=false, length=3)
	private String category;

	@Column(name="ZHRF_PWORD_1", nullable=false, length=50)
	private String password1;

	@Column(name="ZHRF_PWORD_2", nullable=false, length=50)
	private String password2;

	@Column(name="ZHRF_SERVICE_ROLE", nullable=false, length=1)
	private String role;

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

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getReportsTo() {
		return this.reportsTo;
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getAffil() {
		return this.affil;
	}

	public void setAffil(String affil) {
		this.affil = affil;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPassword1() {
		return this.password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return this.password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public static CrossReferencePersonOfInterest findByEmployeeIdAndEffectiveDate(String employeeId, Date effectiveDate) {
		//SELECT
		//FROM PS_ZHRT_PER_POI_TR P
		//WHERE P.EMPLID = $EmplId
		//AND P.EFFDT = 
				//(SELECT MAX(POI1.EFFDT) FROM PS_ZHRT_PER_POI_TR P2
				//WHERE P.EMPLID = P2.EMPLID
				//AND P.POI_TYPE = P2.POI_TYPE
				//AND to_char(P2.EFFDT,'YYYY-MM-DD') <= $EffDt)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<CrossReferencePersonOfInterest> resultList = em.createQuery(
					"SELECT c FROM CrossReferencePersonOfInterest c "
					+ "WHERE UPPER(TRIM(c.employeeId)) = :employeeId "
						+ "AND c.effectiveDate = "
							+ "(SELECT MAX(c2.effectiveDate) FROM CrossReferencePersonOfInterest c2 "
							+ "WHERE UPPER(TRIM(c2.employeeId)) = UPPER(TRIM(c.employeeId)) "
								+ "AND c2.effectiveDate <= :effectiveDate) "
					, CrossReferencePersonOfInterest.class)
					.setParameter("employeeId", employeeId.trim().toUpperCase())
					.setParameter("effectiveDate", effectiveDate, TemporalType.DATE)
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

	public static CrossReferencePersonOfInterest findActiveByEmployeeId(String employeeId) {
		//SELECT FROM PS_ZHRT_PER_POI_TR P
		//WHERE P.EMPLID =  $EmplId
		//AND P.EFF_STATUS = 'A'
		//AND P.EFFDT = 
				//(SELECT MAX(PED1.EFFDT) FROM PS_ZHRT_PER_POI_TR P2
				//WHERE P2.EMPLID = P.EMPLID AND P2.EFF_STATUS = 'A' AND P2.POI_TYPE = P.POI_TYPE AND P2.EFFDT <= SYSDATE)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<CrossReferencePersonOfInterest> resultList = em.createQuery(
					"SELECT c FROM CrossReferencePersonOfInterest c "
					+ "WHERE UPPER(TRIM(c.employeeId)) = :employeeId "
					+ "AND c.effectiveDate = "
							+ "(SELECT MAX(c2.effectiveDate) FROM CrossReferencePersonOfInterest c2 "
							+ "WHERE UPPER(TRIM(c2.employeeId)) = UPPER(TRIM(c.employeeId)) "
							+ "AND UPPER(TRIM(c2.status)) = 'A' "
							+ "AND c2.effectiveDate <= CURRENT_DATE) "
					, CrossReferencePersonOfInterest.class)
					.setParameter("employeeId", employeeId.trim().toUpperCase())
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
	 * Gets the maximum EFFDT for an Employee ID
	 * @see ErdUtil
     * @see Main-Sql-Poi in ZHRISTDT.SQC PeopleCode file
	 * @param employeeId
	 * @return maximum effective date for employee ID
	 */
	public static Date findMaxEffectiveDateByEmployeeId(String employeeId) {
		//SELECT FROM PS_ZHRT_PER_POI_TR A
		//WHERE A.EMPLID = $EmplId
		//AND A.EFFDT = 
				//(SELECT MAX(A1.EFFDT) FROM PS_ZHRT_PER_POI_TR A1 
				//WHERE A1.EMPLID = A.EMPLID AND A1.POI_TYPE = A.POI_TYPE AND A1.EFFDT <= SYSDATE)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<Date> resultList = em.createQuery(
					"SELECT c.effectiveDate FROM CrossReferencePersonOfInterest c "
					+ "WHERE UPPER(TRIM(c.employeeId)) = :employeeId "
					+ "AND c.effectiveDate = "
							+ "(SELECT MAX(c2.effectiveDate) FROM CrossReferencePersonOfInterest c2 "
							+ "WHERE UPPER(TRIM(c2.employeeId)) = UPPER(TRIM(c.employeeId)) "
							+ "WHERE UPPER(TRIM(c2.type)) = UPPER(TRIM(c.type)) "
							+ "AND c2.effectiveDate <= CURRENT_DATE) "
					, Date.class)
					.setParameter("employeeId", employeeId.trim().toUpperCase())
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
	 * Gets the last inactive EFFDT for an Employee ID
	 * @see ErdUtil
	 * @see Get-Last-Inactive-Dt-Poi in ZHRISTDT.SQC PeopleCode file
	 * @param employeeId
	 * @return last inactive effective date for employee ID
	 */
	public static Date findLastInactiveDateByEmployeeIdAndMaxEffectiveDate(String employeeId, Date maxEffectiveDate) {
		//SELECT C.EFFDT FROM PS_ZHRT_PER_POI_TR C
		//WHERE C.EMPLID = $EmplId
		//AND C.EFFDT = 
				//(SELECT MAX(C2.EFFDT) FROM PS_ZHRT_PER_POI_TR C2
				//WHERE C2.EMPLID = C.EMPLID AND C2.EFF_STATUS = 'I' AND TO_CHAR(C2.EFFDT,'YYYY-MM-DD') < $MaxEffDt)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<Date> resultList = em.createQuery(
					"SELECT c.effectiveDate FROM CrossReferencePersonOfInterest c "
					+ "WHERE UPPER(TRIM(c.employeeId)) = :employeeId "
					+ "AND c.effectiveDate = "
							+ "(SELECT MAX(c2.effectiveDate) FROM CrossReferencePersonOfInterest c2 "
							+ "WHERE UPPER(TRIM(c2.employeeId)) = UPPER(TRIM(c.employeeId)) "
							+ "WHERE UPPER(TRIM(c2.status)) = 'I' "
							+ "AND c2.effectiveDate < :maxEffectiveDate) "
					, Date.class)
					.setParameter("employeeId", employeeId.trim().toUpperCase())
					.setParameter("maxEffectiveDate", maxEffectiveDate, TemporalType.DATE)
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
	 * Gets the minimum EFFDT for an Employee ID and last inactive EFFDT
	 * @see ErdUtil
	 * @see Get-Min-EffDt-Poi in ZHRISTDT.SQC PeopleCode file
	 * @param employeeId
	 * @return minimum effective date for employee ID greater than the last inactive date
	 */
	public static Date findMinEffectiveDateByEmployeeIdAndLastInactiveDate(String employeeId, Date lastInactiveDate) {
		//SELECT MIN(C.EFFDT) FROM PS_ZHRT_PER_POI_TR C
		//WHERE C.EMPLID = $EmplId AND C.EFFDT > $S_DT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<Date> resultList = em.createQuery(
					"SELECT MIN(c.effectiveDate) FROM CrossReferencePersonOfInterest c "
					+ "WHERE UPPER(TRIM(c.employeeId)) = :employeeId "
					+ "AND c.effectiveDate > :lastInactiveDate "
					, Date.class)
					.setParameter("employeeId", employeeId.trim().toUpperCase())
					.setParameter("lastInactiveDate", lastInactiveDate, TemporalType.DATE)
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

}
package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_ZHRT_JOBCD_CREF database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRT_JOBCD_CREF")
@NamedQuery(name="CrossReferenceJobCode.findAll", query="SELECT p FROM CrossReferenceJobCode p")
public class CrossReferenceJobCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SETID_JOBCODE", nullable=false, length=5)
	private String setIdJobCode;

	@Column(name="DEPARTMENT", nullable=false, length=10)
	private String department;

	@Column(name="EMPL_CLASS", nullable=false, length=3)
	private String employeeClass;

	@Column(name="FULL_PART_TIME", nullable=false, length=1)
	private String fullOrPartTime;

	@Column(name="JOBCODE", nullable=false, length=6)
	private String jobCode;

	@Column(name="REG_TEMP", nullable=false, length=1)
	private String regularOrTemporary;

	@Column(name="STATUS", nullable=false, length=1)
	private String status;

	@Column(name="ZHRF_LEGCATEGORYCD", nullable=false, length=5)
	private String legacyCategoryCode;

	@Column(name="ZHRF_LEGDEPTCD", nullable=false, length=5)
	private String legacyDepartmentCode;

	@Column(name="ZHRF_LEGJOBSTSCD", nullable=false, length=5)
	private String legacyJobStatusCode;

	@Column(name="ZHRF_LEGLEVEL", nullable=false, length=5)
	private String legacyLevel;

	@Column(name="ZHRF_LEGPOSITIONCD", nullable=false, length=5)
	private String legacyPositionCode;

	public CrossReferenceJobCode() {
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmployeeClass() {
		return this.employeeClass;
	}

	public void setEmployeeClass(String employeeClass) {
		this.employeeClass = employeeClass;
	}

	public String getFullOrPartTime() {
		return this.fullOrPartTime;
	}

	public void setFullOrPartTime(String fullOrPartTime) {
		this.fullOrPartTime = fullOrPartTime;
	}

	public String getJobCode() {
		return this.jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getRegularOrTemporary() {
		return this.regularOrTemporary;
	}

	public void setRegularOrTemporary(String regularOrTemporary) {
		this.regularOrTemporary = regularOrTemporary;
	}

	public String getSetIdJobCode() {
		return this.setIdJobCode;
	}

	public void setSetIdJobCode(String setIdJobCode) {
		this.setIdJobCode = setIdJobCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLegacyCategoryCode() {
		return this.legacyCategoryCode;
	}

	public void setLegacyCategoryCode(String legacyCategoryCode) {
		this.legacyCategoryCode = legacyCategoryCode;
	}

	public String getLegacyDepartmentCode() {
		return this.legacyDepartmentCode;
	}

	public void setLegacyDepartmentCode(String legacyDepartmentCode) {
		this.legacyDepartmentCode = legacyDepartmentCode;
	}

	public String getLegacyJobStatusCode() {
		return this.legacyJobStatusCode;
	}

	public void setLegacyJobStatusCode(String legacyJobStatusCode) {
		this.legacyJobStatusCode = legacyJobStatusCode;
	}

	public String getLegacyLevel() {
		return this.legacyLevel;
	}

	public void setLegacyLevel(String legacyLevel) {
		this.legacyLevel = legacyLevel;
	}

	public String getLegacyPositionCode() {
		return this.legacyPositionCode;
	}

	public void setLegacyPositionCode(String legacyPositionCode) {
		this.legacyPositionCode = legacyPositionCode;
	}

	public CrossReferenceJobCode HR01GetPosition(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR01-Get-Position
//		! Desc:  Get the Legacy position from the cross reference table using the
//		!        jobcode setid, jobcode, empl_class, full_part_time, reg_temp,
//		!        and departmentId from PeopleSoft
//		!----------------------------------------------------------------------
//		Begin-Procedure HR01-Get-Position
//		Let $Found = 'N'
//		Begin-Select
//		RPT3.ZHRF_LEGPOSITIONCD
//		    Let $LegPosition = &RPT3.ZHRF_LEGPOSITIONCD      !Move to a program field
//		RPT3.ZHRF_LEGDEPTCD
//		    Let $LegDepartment = &RPT3.ZHRF_LEGDEPTCD        !Move to a program field
//		RPT3.ZHRF_LEGJOBSTSCD
//		    Let $LegJobStatus = &RPT3.ZHRF_LEGJOBSTSCD       !Move to a program field
//		    Let $Found = 'Y'
//		from PS_ZHRT_JOBCD_CREF RPT3
//		where RPT3.SETID_JOBCODE = $PSSetID and RPT3.JOBCODE = $PSJobCode
//		  and RPT3.EMPL_CLASS = $PSEmplClass and RPT3.FULL_PART_TIME = $PSFullPartTime
//		  and RPT3.REG_TEMP = $PSRegTemp and RPT3.DEPARTMENT = $PSDeptid
//		  and RPT3.STATUS = 'A'
//		End-Select
//		If $Found = 'N'
//		    ! Let $WrkCriticalFlag = 'Y'
//		    ! Let $CallRPG = 'N'
//		    ! Let $ErrorMessageParm = 'Setid,Jobcode,EmplClass,F/P Time,R/T,Deptid not found in PS_ZHRT_JOBCD_CREF'
//		   !  Do Call-Error-Routine
//		    ! Let $WrkCriticalFlag = 'N'
//		End-If    !$Found = 'N'
//		End-Procedure HR01-Get-Position
		return null;
	}

	public CrossReferenceJobCode HR04GetPosition(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR04-Get-Position
//		! Desc:  Get the Legacy position from the cross reference table using the
//		!        jobcode setid, jobcode, empl_class, full_part_time, reg_temp,
//		!        and departmentId from PeopleSoft
//		!----------------------------------------------------------------------
//		Begin-Procedure HR04-Get-Position
//		Let $Found = 'N'
//		Begin-Select
//		CPT3.ZHRF_LEGPOSITIONCD
//		    let $LegPosition = &CPT3.ZHRF_LEGPOSITIONCD
//		CPT3.ZHRF_LEGDEPTCD
//		    Let $LegDepartment = &CPT3.ZHRF_LEGDEPTCD
//		CPT3.ZHRF_LEGJOBSTSCD
//		    Let $LegJobStatus = &CPT3.ZHRF_LEGJOBSTSCD
//		    Let $Found = 'Y'
//		from PS_ZHRT_JOBCD_CREF CPT3
//		where CPT3.SETID_JOBCODE = $PSSetID     and CPT3.JOBCODE = $PSJobCode
//		  and CPT3.EMPL_CLASS = $PSEmplClass and CPT3.FULL_PART_TIME = $PSFullPartTime
//		  and CPT3.REG_TEMP = $PSRegTemp   and CPT3.DEPARTMENT = $PSDeptid
//		  and CPT3.STATUS = 'A'
//		End-Select
//		If $Found = 'N'
//		   ! Let $ErrorMessageParm = 'Setid,Jobcode,EmplClass,F/P Time,R/T,Deptid not in XRef PS_ZHRT_JOBCD_CREF'
//		   ! Let $WrkCriticalFlag = 'Y'
//		   ! Let $CallRpg = 'N'
//		   ! Do Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//		   ! Do Call-Error-Routine         !From ZHRI100A.SQR
//		End-If    !$Found = 'N'
//		End-Procedure HR04-Get-Position
		return null;
	}
	
	/**
	 * Replaces SQC procedure HR01-Get-Position from ZHRI101A.SQC and HR04-Get-Position from ZHRI104A.SQC
	 * This procedure finds the legacy Job Code Cross with matching setIdJobCode, jobCode, employeeClass, 
	 * fullOrPartTime, regularOrTemporary, and department.
	 * @see ZHRI101A.SQC
	 * @see ZHRI104A.SQC
	 */
	public static List<CrossReferenceJobCode> findPosition(String setIdJobCode, String jobCode, String employeeClass, 
			String fullOrPartTime, String regularOrTemporary, String department) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<CrossReferenceJobCode> resultList = (List<CrossReferenceJobCode>) em.createQuery("SELECT p FROM CrossReferenceJobCode p "
	    				+ "WHERE p.setIdJobCode = :setIdJobCode AND p.jobCode = :jobCode AND p.employeeClass = :employeeClass "
	    				+ "AND p.fullOrPartTime = :fullOrPartTime AND p.regularOrTemporary = :regularOrTemporary AND p.department = :department AND p.status = :status ", CrossReferenceJobCode.class)
	    		    .setParameter("setIdJobCode", setIdJobCode)
	    		    .setParameter("jobCode", jobCode)
	    		    .setParameter("employeeClass", employeeClass)
	    		    .setParameter("fullOrPartTime", fullOrPartTime)
	    		    .setParameter("regularOrTemporary", regularOrTemporary)
	    		    .setParameter("department", department)
	    		    .setParameter("status", "A")
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList;
	    	}
	    	else 
	    		return null;
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
	}

}
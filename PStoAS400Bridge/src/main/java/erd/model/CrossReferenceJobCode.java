package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_ZHRT_JOBCD_CREF database table.
 * Cross-Reference of Job data to Legacy Job data
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

	/**
	 * @see HR01-Get-Position
	 * @see HR04-Get-Position procedure in ZHRI104A.SQC
	 * @param setIdJobCode
	 * @param jobCode
	 * @param employeeClass
	 * @param fullOrPartTime
	 * @param regularOrTemporary
	 * @param department
	 * @return CrossReferenceJobCode record
	 */
	public static CrossReferenceJobCode findActiveBySetIdJobCodeAndJobCodeAndEmployeeClassAndFullOrPartTimeAndRegularOrTemporaryAndDepartment(
			String setIdJobCode, String jobCode, String employeeClass, String fullOrPartTime, String regularOrTemporary, String department) {
		//SELECT FROM PS_ZHRT_JOBCD_CREF C
		//WHERE C.SETID_JOBCODE = $PSSetID AND C.JOBCODE = $PSJobCode
		//AND C.EMPL_CLASS = $PSEmplClass AND C.FULL_PART_TIME = $PSFullPartTime
		//AND C.REG_TEMP = $PSRegTemp AND C.DEPARTMENT = $PSDeptid
		//AND C.STATUS = 'A'
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<CrossReferenceJobCode> resultList = (List<CrossReferenceJobCode>) em.createQuery(
	    			"SELECT p FROM CrossReferenceJobCode p "
	    					+ "WHERE TRIM(UPPER(p.setIdJobCode)) = TRIM(UPPER(:setIdJobCode)) "
	    					+ "AND TRIM(UPPER(p.jobCode)) = TRIM(UPPER(:jobCode)) "
	    					+ "AND TRIM(UPPER(p.employeeClass)) = TRIM(UPPER(:employeeClass)) "
	    					+ "AND TRIM(UPPER(p.fullOrPartTime)) = TRIM(UPPER(:fullOrPartTime)) "
	    					+ "AND TRIM(UPPER(p.regularOrTemporary)) = TRIM(UPPER(:regularOrTemporary)) "
	    					+ "AND TRIM(UPPER(p.department)) = TRIM(UPPER(:department)) "
	    					+" AND TRIM(UPPER(p.status)) = 'A' "
	    			, CrossReferenceJobCode.class)
	    		    .setParameter("setIdJobCode", setIdJobCode)
	    		    .setParameter("jobCode", jobCode)
	    		    .setParameter("employeeClass", employeeClass)
	    		    .setParameter("fullOrPartTime", fullOrPartTime)
	    		    .setParameter("regularOrTemporary", regularOrTemporary)
	    		    .setParameter("department", department)
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
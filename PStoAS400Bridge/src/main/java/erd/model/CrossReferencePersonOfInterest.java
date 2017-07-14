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

	public static CrossReferencePersonOfInterest findByEmployeeIdAndEffectiveDate(String employeeId, Date effectiveDate) {
		//SELECT FROM PS_ZHRT_PER_POI_TR P
		//WHERE P.EMPLID = $EmplId
		//AND P.EFFDT = 
				//(SELECT MAX(POI1.EFFDT) FROM PS_ZHRT_PER_POI_TR P2
				//WHERE P.EMPLID = P2.EMPLID
				//AND P.POI_TYPE = P2.POI_TYPE
				//AND TO_CHAR(P2.EFFDT,'YYYY-MM-DD') <= $EffDt)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<CrossReferencePersonOfInterest> resultList = em.createQuery(
					"SELECT c FROM CrossReferencePersonOfInterest c "
					+ "WHERE UPPER(TRIM(c.employeeId)) = UPPER(TRIM(:employeeId)) "
						+ "AND c.effectiveDate = "
							+ "(SELECT MAX(c2.effectiveDate) FROM CrossReferencePersonOfInterest c2 "
							+ "WHERE UPPER(TRIM(c2.employeeId)) = UPPER(TRIM(c.employeeId)) "
								+ "AND c2.effectiveDate <= :effectiveDate) "
					, CrossReferencePersonOfInterest.class)
					.setParameter("employeeId", employeeId)
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
					+ "WHERE UPPER(TRIM(c.employeeId)) = UPPER(TRIM(:employeeId)) "
					+ "AND c.effectiveDate = "
							+ "(SELECT MAX(c2.effectiveDate) FROM CrossReferencePersonOfInterest c2 "
							+ "WHERE UPPER(TRIM(c2.employeeId)) = UPPER(TRIM(c.employeeId)) "
							+ "AND UPPER(TRIM(c2.status)) = 'A' "
							+ "AND c2.effectiveDate <= CURRENT_DATE) "
					, CrossReferencePersonOfInterest.class)
					.setParameter("employeeId", employeeId)
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
					+ "WHERE UPPER(TRIM(c.employeeId)) = UPPER(TRIM(:employeeId)) "
					+ "AND c.effectiveDate = "
							+ "(SELECT MAX(c2.effectiveDate) FROM CrossReferencePersonOfInterest c2 "
							+ "WHERE UPPER(TRIM(c2.employeeId)) = UPPER(TRIM(c.employeeId)) "
							+ "WHERE UPPER(TRIM(c2.type)) = UPPER(TRIM(c.type)) "
							+ "AND c2.effectiveDate <= CURRENT_DATE) "
					, Date.class)
					.setParameter("employeeId", employeeId)
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
					+ "WHERE UPPER(TRIM(c.employeeId)) = UPPER(TRIM(:employeeId)) "
					+ "AND c.effectiveDate = "
							+ "(SELECT MAX(c2.effectiveDate) FROM CrossReferencePersonOfInterest c2 "
							+ "WHERE UPPER(TRIM(c2.employeeId)) = UPPER(TRIM(c.employeeId)) "
							+ "WHERE UPPER(TRIM(c2.status)) = 'I' "
							+ "AND c2.effectiveDate < :maxEffectiveDate) "
					, Date.class)
					.setParameter("employeeId", employeeId)
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
		//WHERE C.EMPLID = $EmplId AND C.EFFDT > $LastInactiveDate
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<Date> resultList = em.createQuery(
					"SELECT MIN(c.effectiveDate) FROM CrossReferencePersonOfInterest c "
					+ "WHERE UPPER(TRIM(c.employeeId)) = UPPER(TRIM(:employeeId)) "
					+ "AND c.effectiveDate > :lastInactiveDate "
					, Date.class)
					.setParameter("employeeId", employeeId)
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
package erd.model;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entity implementation class for Entity: TEMPMAST
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="TEMPMAST")
@NamedQuery(name="TempMast.findAll", query="SELECT t FROM TempMast t")
public class TempMast implements Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	
	@Id
	@Column(name="TPMEMP", length=5, nullable=false) //five digit string left padded with zeros
	private String employeeId;

	@Column(name="TPMSTS", length=1)
	private String status;
	
	@Column(name="TPMNAM", length=33)
	private String employeeName;
	
	@Column(name="TPMEA1", length=30)
	private String streetAddress;
	
	@Column(name="TPMEA2", length=30)
	private String additionalAddress;
	
	@Column(name="TPMECN", length=10)
	private String county;
	
	@Column(name="TPMECT", length=17)
	private String city;
	
	@Column(name="TPMEST", length=2)
	private String state;
	
	@Column(name="TPMZIP", length=9)
	private String zip;
	
	@Column(name="TPMEPH", length=14)
	private String homePhone;
	
	@Column(name="TPMEDT", length=8) //YYYYMMDD
	private String employmentDate;
	
	@Column(name="TPMTMD", length=8) //YYYYMMDD
	private String terminationDate;
	
	@Column(name="TPMINT", length=3)
	private String employeeInitials;
	
	@Column(name="TPMPWD", length=5)
	private String updateCode;

	@Column(name="TPMBRN", length=2)
	private String branch;
	
	@Column(name="TPMDPT", length=1) //A,D,L,I,U,P,C,S
	private String department;
	
	@Column(name="TPMGP", length=2)
	private String group;
	
	@Column(name="TPMPOS", precision=3)
	private Integer position;
	
	@Column(name="TPMRGN", length=1)
	private String region;
	
	@Column(name="TPMCMT", length=50)
	private String resource;
	
	@Column(name="TPMSSN", length=20)
	private String socialSecurityNumber;
	
	@Column(name="TPMSEX", length=1)
	private String sex;
	
	@Column(name="TPMNNM", length=20)
	private String nickname;
	
	@Column(name="TPMTMI", length=10)
	private String terminalIdentification;
	
	@Column(name="TPMDTA", length=8) //YYYYMMDD
	private String addedDate;
	
	@Column(name="TPMTMA", precision=6) //HHMMSS
	private Integer addedTime;
	
	@Column(name="TPMPGA", length=10) //PPPPPP/JJJ
	private String addedByProgramJob;
	
	@Column(name="TPMEMA", length=5)
	private String addedByEmployeeId;
	
	@Column(name="TPMDTC", length=8) //YYYYMMDD
	private String changedDate;
	
	@Column(name="TPMTMC", precision=6) //HHMMSS
	private Integer changedTime;
	
	@Column(name="TPMPGC", length=10) //PPPPPP/JJJ
	private String changedByProgram;
	
	@Column(name="TPMEMC", length=5)
	private String changedByEmployeeId;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getAdditionalAddress() {
		return additionalAddress;
	}

	public void setAdditionalAddress(String additionalAddress) {
		this.additionalAddress = additionalAddress;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(String employmentDate) {
		this.employmentDate = employmentDate;
	}

	public String getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getEmployeeInitials() {
		return employeeInitials;
	}

	public void setEmployeeInitials(String employeeInitials) {
		this.employeeInitials = employeeInitials;
	}

	public String getUpdateCode() {
		return updateCode;
	}

	public void setUpdateCode(String updateCode) {
		this.updateCode = updateCode;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTerminalIdentification() {
		return terminalIdentification;
	}

	public void setTerminalIdentification(String terminalIdentification) {
		this.terminalIdentification = terminalIdentification;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public Integer getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(Integer addedTime) {
		this.addedTime = addedTime;
	}

	public String getAddedByProgramJob() {
		return addedByProgramJob;
	}

	public void setAddedByProgramJob(String addedByProgramJob) {
		this.addedByProgramJob = addedByProgramJob;
	}

	public String getAddedByEmployeeId() {
		return addedByEmployeeId;
	}

	public void setAddedByEmployeeId(String addedByEmployeeId) {
		this.addedByEmployeeId = addedByEmployeeId;
	}

	public String getChangedDate() {
		return changedDate;
	}

	public void setChangedDate(String changedDate) {
		this.changedDate = changedDate;
	}

	public Integer getChangedTime() {
		return changedTime;
	}

	public void setChangedTime(Integer changedTime) {
		this.changedTime = changedTime;
	}

	public String getChangedByProgram() {
		return changedByProgram;
	}

	public void setChangedByProgram(String changedByProgram) {
		this.changedByProgram = changedByProgram;
	}

	public String getChangedByEmployeeId() {
		return changedByEmployeeId;
	}

	public void setChangedByEmployeeId(String changedByEmployeeId) {
		this.changedByEmployeeId = changedByEmployeeId;
	}

	/**
	 * 
	 * @param employeeId
	 * @param changedDate
	 * @param changedByProgramJob
	 * @param changedByEmployeeId
	 * @return TempMast record
	 */
	public static TempMast findByEmployeeIdAndChangedDateAndChangedByProgramAndChangedByEmployeeId(
			String employeeId, String changedDate, String changedByProgram, String changedByEmployeeId) {
		logger.debug("findByEmployeeIdAndChangedDateAndChangedByProgramAndChangedByEmployeeId() ***");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<TempMast> resultList = em.createQuery(
	    		    "SELECT t FROM TempMast t "
	    		    		+ "WHERE TRIM(UPPER(t.employeeId)) = TRIM(UPPER(:employeeId)) "
	    		    		+ "AND TRIM(UPPER(t.changedDate)) = TRIM(UPPER(:changedDate)) "
	    		    		+ "AND TRIM(UPPER(t.changedByProgram)) = TRIM(UPPER(:changedByProgram)) "
	    		    		+ "AND TRIM(UPPER(t.changedByEmployeeId)) = TRIM(UPPER(:changedByEmployeeId)) "
	    		    		, TempMast.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("changedDate", changedDate)
	    		    .setParameter("changedByProgram", changedByProgram)
	    		    .setParameter("changedByEmployeeId", changedByEmployeeId)
	    		    .getResultList();
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

}

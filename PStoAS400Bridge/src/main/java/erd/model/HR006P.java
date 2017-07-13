package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import erd.ErdUtil;

/**
 * Entity implementation class for Entity: HR006P
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="HR006P")
@NamedQuery(name="HR006P.findAll", query="SELECT h FROM HR006P h")
public class HR006P implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HMREMP", length=5, unique=true, nullable=false) //five digit string left padded with zeros
	private String employeeId;

	@Column(name="HMRCCD", length=2)
	private String country;

	@Column(name="HMRNID", length=20)
	private String nationalId;

	@Column(name="HMRNAM", length=33)
	private String employeeName;

	@Column(name="HMRATN", length=30)
	private String atn;

	@Column(name="HMRSTR", length=30)
	private String streetAddress;

	@Column(name="HMRCTY", length=17)
	private String city;

	@Column(name="HMRST", length=2)
	private String state;

	@Column(name="HMRWRK", length=6)
	private String workSpace;

	@Column(name="HMRMGE", length=1) //S, M, D, W
	private String maritalStatus; 

	@Column(name="HMRENM", length=33)
	private String emergencyContactName;

	@Column(name="HMREPH", length=10)
	private String emergencyContactPhone;

	@Column(name="HMRERL", length=12)
	private String emergencyContactRelation;

	@Column(name="HMRTTL", length=3)
	private String title;

	@Column(name="HMRGPS", length=2)
	private String gpAssgnRecruitSource;

	@Column(name="HMRRSC", length=1)
	private String recruitSource;
	
	@Column(name="HMRCCL", length=40)
	private String college;
	
	@Column(name="HMRTSC", length=1)
	private String testCode;
	
	@Column(name="HMRTSR", precision=3)
	private Integer testResults;
	
	@Column(name="HMRTRC", length=1)
	private String terminationCode;
	
	@Column(name="HMRRSL", length=35)
	private String reasonForLeaving;
	
	@Column(name="HMRVOI", length=1)
	private String voluntaryOrInvoluntary;
	
	@Column(name="HMROVR", precision=3)
	private Integer overallRatingReview;
	
	@Column(name="HMRNXA", length=8) //YYYYMMDD
//	@Temporal(TemporalType.DATE)
//	private Date nextAppraisalDate;
	private String nextAppraisalDate;
	
	@Column(name="HMRDLN", length=20)
	private String driverLicenseNumber;
	
	@Column(name="HMRDLS", length=2)
	private String driverLicenseState;
	
	@Column(name="HMRGND", length=1) //M, F
	private String gender;
	
	@Column(name="HMRBDT", length=8) //YYYYMMDD
//	@Temporal(TemporalType.DATE)
//	private Date birthDate;
	private String birthDate;
	
	@Column(name="HMRGP", length=2)
	private String group;
	
	@Column(name="HMRBRN", length=2)
	private String branch;
	
	@Column(name="HMRRGN", length=1)
	private String region;
	
	@Column(name="HMRHPH", length=10)
	private String homePhone;
	
	@Column(name="HMROPH", length=10)
	private String officePhone;
	
	@Column(name="HMRRSN", length=35)
	private String recruitmentSource;
	
	@Column(name="HMRGYR", length=4) //YYYY
//	@Temporal(TemporalType.DATE)
//	private Date graduationYear;
	private String graduationYear;
	
	@Column(name="HMRMJR", length=25)
	private String collegeMajor;
	
	@Column(name="HMRRCE", length=1)
	private String raceOrigin;
	
	@Column(name="HMRZIP", length=9)
	private String zip;
	
	@Column(name="HMRNKN", length=20)
	private String nickname;
	
	@Column(name="HMRHDT", length=8) //YYYYMMDD
//	@Temporal(TemporalType.DATE)
//	private Date lastHireDate;
	private String lastHireDate;
	
	@Column(name="HMRLTD", length=8) //YYYYMMDD
//	@Temporal(TemporalType.DATE)
//	private Date lastTerminationDate;
	private String lastTerminationDate;
	
	@Column(name="HMRCTZ", length=2)
	private String citizenship;
	
	@Column(name="HMRHIR", length=1)
	private String eligibleForRehire;
	
	@Column(name="HMRSPS", length=20)
	private String spouseName;
	
	@Column(name="HMRRHR", length=1)
	private String employeeRehire;
	
	@Column(name="HMRREI", length=5)
	private String recruiterEmployeeId;
	
	@Column(name="HMRCTC", length=5)
	private String category;
	
	@Column(name="HMRDPT", length=5)
	private String department;
	
	@Column(name="HMRPOS", length=5)
	private String position;
	
	@Column(name="HMRLVL", length=5)
	private String level;
	
	@Column(name="HMRJSC", length=5)
	private String jobStatus;
	
	@Column(name="HMRWKS", length=5)
	private String workStatus;
	
	@Column(name="HMRUNF", length=1)
	private String unionFlag;
	
	@Column(name="HMREM#", precision=9)
	private Integer alternateEmployeeNumber;
	
	@Column(name="HMRADT", length=8) //YYYYMMDD
//	@Temporal(TemporalType.DATE)
//	private Date auditDate;
	private String auditDate;
	
	@Column(name="HMRPGM", length=10)
	private String auditProgram;
	
	@Column(name="HMREMC", length=5)
	private String changedByEmployeeId;

	
	public HR006P() {
		super();
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAtn() {
		return atn;
	}

	public void setAtn(String atn) {
		this.atn = atn;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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

	public String getWorkSpace() {
		return workSpace;
	}

	public void setWorkSpace(String workSpace) {
		this.workSpace = workSpace;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}

	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}

	public String getEmergencyContactRelation() {
		return emergencyContactRelation;
	}

	public void setEmergencyContactRelation(String emergencyContactRelation) {
		this.emergencyContactRelation = emergencyContactRelation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGpAssgnRecruitSource() {
		return gpAssgnRecruitSource;
	}

	public void setGpAssgnRecruitSource(String gpAssgnRecruitSource) {
		this.gpAssgnRecruitSource = gpAssgnRecruitSource;
	}

	public String getRecruitSource() {
		return recruitSource;
	}

	public void setRecruitSource(String recruitSource) {
		this.recruitSource = recruitSource;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public Integer getTestResults() {
		return testResults;
	}

	public void setTestResults(Integer testResults) {
		this.testResults = testResults;
	}

	public String getTerminationCode() {
		return terminationCode;
	}

	public void setTerminationCode(String terminationCode) {
		this.terminationCode = terminationCode;
	}

	public String getReasonForLeaving() {
		return reasonForLeaving;
	}

	public void setReasonForLeaving(String reasonForLeaving) {
		this.reasonForLeaving = reasonForLeaving;
	}

	public String getVoluntaryOrInvoluntary() {
		return voluntaryOrInvoluntary;
	}

	public void setVoluntaryOrInvoluntary(String voluntaryOrInvoluntary) {
		this.voluntaryOrInvoluntary = voluntaryOrInvoluntary;
	}

	public Integer getOverallRatingReview() {
		return overallRatingReview;
	}

	public void setOverallRatingReview(Integer overallRatingReview) {
		this.overallRatingReview = overallRatingReview;
	}

	public String getNextAppraisalDate() {
		return nextAppraisalDate;
	}

//	public Date getNextAppraisalDate() {
//		if(nextAppraisalDate != null) {
//			try {
//				return simpleDateFormatYyyyMmDd.parse(nextAppraisalDate);
//			} 
//			catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}

	public void setNextAppraisalDate(String nextAppraisalDate) {
		this.nextAppraisalDate = nextAppraisalDate;
	}

	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}

	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}

	public String getDriverLicenseState() {
		return driverLicenseState;
	}

	public void setDriverLicenseState(String driverLicenseState) {
		this.driverLicenseState = driverLicenseState;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

//	public Date getBirthDate() {
//		return birthDate;
//	}
//
//	public void setBirthDate(Date birthDate) {
//		this.birthDate = birthDate;
//	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getRecruitmentSource() {
		return recruitmentSource;
	}

	public void setRecruitmentSource(String recruitmentSource) {
		this.recruitmentSource = recruitmentSource;
	}

	public String getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getCollegeMajor() {
		return collegeMajor;
	}

	public void setCollegeMajor(String collegeMajor) {
		this.collegeMajor = collegeMajor;
	}

	public String getRaceOrigin() {
		return raceOrigin;
	}

	public void setRaceOrigin(String raceOrigin) {
		this.raceOrigin = raceOrigin;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLastHireDate() {
		return lastHireDate;
	}

	public void setLastHireDate(String lastHireDate) {
		this.lastHireDate = lastHireDate;
	}

	public String getLastTerminationDate() {
		return lastTerminationDate;
	}

	public void setLastTerminationDate(String lastTerminationDate) {
		this.lastTerminationDate = lastTerminationDate;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getEligibleForRehire() {
		return eligibleForRehire;
	}

	public void setEligibleForRehire(String eligibleForRehire) {
		this.eligibleForRehire = eligibleForRehire;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getEmployeeRehire() {
		return employeeRehire;
	}

	public void setEmployeeRehire(String employeeRehire) {
		this.employeeRehire = employeeRehire;
	}

	public String getRecruiterEmployeeId() {
		return recruiterEmployeeId;
	}

	public void setRecruiterEmployeeId(String recruiterEmployeeId) {
		this.recruiterEmployeeId = recruiterEmployeeId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getUnionFlag() {
		return unionFlag;
	}

	public void setUnionFlag(String unionFlag) {
		this.unionFlag = unionFlag;
	}

	public Integer getAlternateEmployeeNumber() {
		return alternateEmployeeNumber;
	}

	public void setAlternateEmployeeNumber(Integer alternateEmployeeNumber) {
		this.alternateEmployeeNumber = alternateEmployeeNumber;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditProgram() {
		return auditProgram;
	}

	public void setAuditProgram(String auditProgram) {
		this.auditProgram = auditProgram;
	}

	public String getChangedByEmployeeId() {
		return changedByEmployeeId;
	}

	public void setChangedByEmployeeId(String changedByEmployeeId) {
		this.changedByEmployeeId = changedByEmployeeId;
	}

	/**
	 * ZHRI100A.Get-Legacy-OprId
	 * Formulates legacy OprId from HR036P where HR036P.H36EM# = #wrk_emplid and HR036P.H36INX = #indexNum UNION
	 */
	public static HR006P zhri100AGetLegacyOprId(String employeeId, Boolean isPoi, Integer indexNumber) {
//		LET $LegEmplId = ''
//		String legacyEmployeeId = "";
//		MOVE $Wrk_Emplid to #wrk_emplid
//		IF $PoiFlag = 'N'
		if(!isPoi) {
//			MOVE 0 to #indexNum
			indexNumber = 0;
//		END-IF
		}
//		Begin-Select
//		HR036P.H36NAM
//		HR036P.H36EMP
//		HR036P.H36EM#
//	  	!This IF statement and OR part of Select is a workaround to some problem in v8 (gateway and new version of SQR). 
//		!The Select was hanging if it couldn't find a match in HR036P, so the Select assures that the Select always finds a match.
//	  	LET #WRK_CHR36_H36EM_NUM = &HR036P.H36EM#
//		Integer WRK_CHR36_H36EM_NUM;
//	  	IF #WRK_CHR36_H36EM_NUM = #wrk_emplid
//	    	LET $LegEmpName = &HR036P.H36NAM
//	    	DO Format-Employee-Name
//	    	LET $LegEmplid = &HR036P.H36EMP
//	    	LET $LegEmplid = substr($LegEmplid,1,5)
//	    	IF (#indexNum = 0 and $PoiFlag = 'Y') OR $PoiFlag = 'N'
//	    		DO Insert-OprId
//	    	ELSE
//	    		IF (#indexNum <> 0 AND $PoiFlag = 'Y')
//	        		DO Update-OprId
//	      		END-IF
//	    	END-IF  
//		END-IF    !#WRK_CHR36_H36EM_NUM = #wrk_emplid
//		FROM HR036P
//		WHERE HR036P.H36EM# = #wrk_emplid
//		AND HR036P.H36INX = #indexNum 
//		UNION
//		SELECT
//			' ', ' ' , 9999999999
//		FROM DUAL
//		End-Select
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<HR006P> resultList = em.createQuery(
	    		    "SELECT h FROM HR036P t "
	    		    		+ "WHERE h.employeeId = :employeeId"
	    		    		+ "AND h.indexNumber = :indexNumber", HR006P.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("indexNumber", indexNumber)
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		HR006P hr036P = resultList.get(0);
		    	if(hr036P.getEmployeeName() != null && !hr036P.getEmployeeName().isEmpty()) {
		    		hr036P.setEmployeeName(ErdUtil.formatLegacyEmployeeNameToPeopleSoftEmployeeName(hr036P.getEmployeeName()));
	    		}
//		    	LET $LegEmplid = substr($LegEmplid,1,5)
		    	if(hr036P.getEmployeeId() != null && !hr036P.getEmployeeId().isEmpty()) {
		    		hr036P.setEmployeeId(hr036P.getEmployeeId().substring(0,5));
	    		}
//		    	IF (#indexNum = 0 and $PoiFlag = 'Y') OR $PoiFlag = 'N'
//	    			DO Insert-OprId
//	    		ELSE
//	    			IF (#indexNum <> 0 AND $PoiFlag = 'Y')
//	        			DO Update-OprId
//	      			END-IF
//	    		END-IF  
	    		return hr036P;
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

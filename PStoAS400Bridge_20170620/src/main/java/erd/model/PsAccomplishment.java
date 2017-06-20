package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * The persistent class for the PS_ACCOMPLISHMENTS database table.
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ACCOMPLISHMENTS")
@NamedQuery(name="PsAccomplishment.findAll", query="SELECT p FROM PsAccomplishment p")
public class PsAccomplishment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="ACCOMPLISHMENT", length=8)
	private String accomplishmentCode;

	@Column(name="APS_HEDUC_CD_AUS", nullable=false, length=2)
	private String apsHeducCdAus;

	@Column(name="APS_MINOR_AUS", length=30)
	private String apsMinorAus;

	@Column(name="APS_MINOR_CODE_AUS", nullable=false, length=10)
	private String apsMinorCodeAus;

	@Column(name="AVERAGE_GRADE", nullable=false, length=5)
	private String averageGrade;

	@Column(name="BONUS_AMOUNT_FRA", nullable=false, precision=7, scale=2)
	private BigDecimal bonusAmountFra;

	@Column(name="BONUS_DT_FRA")
	private Date bonusDtFra;

	@Column(name="COUNTRY", nullable=false, length=3)
	private String countryIsoAlpha3Code;

	@Column(name="COUNTRY_OTHER", nullable=false, length=3)
	private String countryOther;

	@Column(name="DESCR", length=30)
	private String description;

	@Column(name="DT_ISSUED")
	private Date dateIssued;

	@Column(name="EDUC_LVL_AUS", nullable=false, length=1)
	private String educLvlAus;

	@Column(name="EDUCATOR", length=50)
	private String educator;

	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="EVALUATION_DT")
	private Date evaluationDt;

	@Column(name="EXPIRATN_DT")
	private Date expirationDate;

	@Column(name="FACULTY_CODE", nullable=false, length=10)
	private String facultyCode;

	@Column(name="FP_SUBJECT_CD", nullable=false, length=3)
	private String fpSubjectCd;

	@Column(name="GPA", nullable=false, precision=9, scale=2)
	private BigDecimal gpa;

	@Column(name="GRADUATE_INDICATOR", nullable=false, length=1)
	private String graduateIndicator;

	@Column(name="GRANTOR", length=20)
	private String grantor;

	@Column(name="GVT_CRED_HRS_TYPE", nullable=false, length=1)
	private String gvtCredHrsType;

	@Column(name="GVT_CREDIT_HOURS", nullable=false, length=3)
	private String gvtCreditHours;

	@Column(name="IPE_SW", nullable=false, length=1)
	private String ipeSw;

	@Column(name="ISSUED_BY", length=30)
	private String issuedBy;

	@Column(name="LICENSE_NBR", length=15)
	private String licenseNbr;

	@Column(name="LICENSE_VERIFIED", nullable=false, length=1)
	private String licenseVerified;

	@Column(name="MAJOR", length=30)
	private String major;

	@Column(name="MAJOR_CATEGORY", nullable=false, length=1)
	private String majorCategory;

	@Column(name="MAJOR_CODE", nullable=false, length=10)
	private String majorCode;

	@Column(name="MANDATE", length=15)
	private String mandate;

	@Column(name="MANDATE_BEGIN_DATE")
	private Date mandateBeginDate;

	@Column(name="MANDATE_END_DATE")
	private Date mandateEndDate;

	@Column(name="MANDATE_FUNCTION", length=15)
	private String mandateFunction;

	@Column(name="NATIVE_LANGUAGE", nullable=false, length=1)
	private String nativeLanguage;

	@Column(name="NVQ_CAND_DT")
	private Date nvqCandDt;

	@Column(name="NVQ_CAND_NBR", length=16)
	private String nvqCandNbr;

	@Column(name="NVQ_CERT_DT")
	private Date nvqCertDt;

	@Column(name="NVQ_COMP_DT")
	private Date nvqCompDt;

	@Column(name="NVQ_START_DT")
	private Date nvqStartDt;

	@Column(name="NVQ_STATUS", nullable=false, length=1)
	private String nvqStatus;

	@Column(name="PASSED", nullable=false, length=1)
	private String passed;

	@Column(name="PRACTIC_GRADE_GER", nullable=false, length=4)
	private String practicGradeGer;

	@Column(name="READ_PROFICIENCY", nullable=false, length=1)
	private String readProficiency;

	@Column(name="RENEWAL", nullable=false, length=1)
	private String renewal;

	@Column(name="SCHOOL", length=30)
	private String school;

	@Column(name="SCHOOL_CODE", nullable=false, length=10)
	private String schoolCode;

	@Column(name="SCORE", nullable=false, precision=9, scale=2)
	private BigDecimal score;

	@Column(name="SPEAK_PROFICIENCY", nullable=false, length=1)
	private String speakProficiency;

	@Column(name="\"STATE\"", nullable=false, length=6)
	private String state;

	@Column(name="STATE_OTHER", nullable=false, length=6)
	private String stateOther;

	@Column(name="SUBFACULTY_CODE", nullable=false, length=10)
	private String subfacultyCode;

	@Column(name="SUBFACULTY_NAME", length=50)
	private String subfacultyName;

	@Column(name="TEACHER", nullable=false, length=1)
	private String teacher;

	@Column(name="TERMINAL_DEGREE", nullable=false, length=1)
	private String terminalDegree;

	@Column(name="THEORY_GRADE_GER", nullable=false, length=4)
	private String theoryGradeGer;

	@Column(name="TRANSLATOR", nullable=false, length=1)
	private String translator;

	@Column(name="WRITE_PROFICIENCY", nullable=false, length=1)
	private String writeProficiency;

	@Column(name="YR_ACQUIRED", nullable=false, precision=38)
	private BigDecimal yrAcquired;

	public PsAccomplishment() {
	}

	public String getAccomplishmentCode() {
		return this.accomplishmentCode;
	}

	public void setAccomplishmentCode(String accomplishmentCode) {
		this.accomplishmentCode = accomplishmentCode;
	}

	public String getApsHeducCdAus() {
		return this.apsHeducCdAus;
	}

	public void setApsHeducCdAus(String apsHeducCdAus) {
		this.apsHeducCdAus = apsHeducCdAus;
	}

	public String getApsMinorAus() {
		return this.apsMinorAus;
	}

	public void setApsMinorAus(String apsMinorAus) {
		this.apsMinorAus = apsMinorAus;
	}

	public String getApsMinorCodeAus() {
		return this.apsMinorCodeAus;
	}

	public void setApsMinorCodeAus(String apsMinorCodeAus) {
		this.apsMinorCodeAus = apsMinorCodeAus;
	}

	public String getAverageGrade() {
		return this.averageGrade;
	}

	public void setAverageGrade(String averageGrade) {
		this.averageGrade = averageGrade;
	}

	public BigDecimal getBonusAmountFra() {
		return this.bonusAmountFra;
	}

	public void setBonusAmountFra(BigDecimal bonusAmountFra) {
		this.bonusAmountFra = bonusAmountFra;
	}

	public Date getBonusDtFra() {
		return this.bonusDtFra;
	}

	public void setBonusDtFra(Date bonusDtFra) {
		this.bonusDtFra = bonusDtFra;
	}

	public String getCountryCode() {
		return this.countryIsoAlpha3Code;
	}

	public void setCountryCode(String countryIsoAlpha3Code) {
		this.countryIsoAlpha3Code = countryIsoAlpha3Code;
	}

	public String getCountryOther() {
		return this.countryOther;
	}

	public void setCountryOther(String countryOther) {
		this.countryOther = countryOther;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateIssued() {
		return this.dateIssued;
	}

	public void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
	}

	public String getEducLvlAus() {
		return this.educLvlAus;
	}

	public void setEducLvlAus(String educLvlAus) {
		this.educLvlAus = educLvlAus;
	}

	public String getEducator() {
		return this.educator;
	}

	public void setEducator(String educator) {
		this.educator = educator;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEvaluationDt() {
		return this.evaluationDt;
	}

	public void setEvaluationDt(Date evaluationDt) {
		this.evaluationDt = evaluationDt;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getFacultyCode() {
		return this.facultyCode;
	}

	public void setFacultyCode(String facultyCode) {
		this.facultyCode = facultyCode;
	}

	public String getFpSubjectCd() {
		return this.fpSubjectCd;
	}

	public void setFpSubjectCd(String fpSubjectCd) {
		this.fpSubjectCd = fpSubjectCd;
	}

	public BigDecimal getGpa() {
		return this.gpa;
	}

	public void setGpa(BigDecimal gpa) {
		this.gpa = gpa;
	}

	public String getGraduateIndicator() {
		return this.graduateIndicator;
	}

	public void setGraduateIndicator(String graduateIndicator) {
		this.graduateIndicator = graduateIndicator;
	}

	public String getGrantor() {
		return this.grantor;
	}

	public void setGrantor(String grantor) {
		this.grantor = grantor;
	}

	public String getGvtCredHrsType() {
		return this.gvtCredHrsType;
	}

	public void setGvtCredHrsType(String gvtCredHrsType) {
		this.gvtCredHrsType = gvtCredHrsType;
	}

	public String getGvtCreditHours() {
		return this.gvtCreditHours;
	}

	public void setGvtCreditHours(String gvtCreditHours) {
		this.gvtCreditHours = gvtCreditHours;
	}

	public String getIpeSw() {
		return this.ipeSw;
	}

	public void setIpeSw(String ipeSw) {
		this.ipeSw = ipeSw;
	}

	public String getIssuedBy() {
		return this.issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public String getLicenseNbr() {
		return this.licenseNbr;
	}

	public void setLicenseNbr(String licenseNbr) {
		this.licenseNbr = licenseNbr;
	}

	public String getLicenseVerified() {
		return this.licenseVerified;
	}

	public void setLicenseVerified(String licenseVerified) {
		this.licenseVerified = licenseVerified;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajorCategory() {
		return this.majorCategory;
	}

	public void setMajorCategory(String majorCategory) {
		this.majorCategory = majorCategory;
	}

	public String getMajorCode() {
		return this.majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

	public String getMandate() {
		return this.mandate;
	}

	public void setMandate(String mandate) {
		this.mandate = mandate;
	}

	public Date getMandateBeginDate() {
		return this.mandateBeginDate;
	}

	public void setMandateBeginDate(Date mandateBeginDate) {
		this.mandateBeginDate = mandateBeginDate;
	}

	public Date getMandateEndDate() {
		return this.mandateEndDate;
	}

	public void setMandateEndDate(Date mandateEndDate) {
		this.mandateEndDate = mandateEndDate;
	}

	public String getMandateFunction() {
		return this.mandateFunction;
	}

	public void setMandateFunction(String mandateFunction) {
		this.mandateFunction = mandateFunction;
	}

	public String getNativeLanguage() {
		return this.nativeLanguage;
	}

	public void setNativeLanguage(String nativeLanguage) {
		this.nativeLanguage = nativeLanguage;
	}

	public Date getNvqCandDt() {
		return this.nvqCandDt;
	}

	public void setNvqCandDt(Date nvqCandDt) {
		this.nvqCandDt = nvqCandDt;
	}

	public String getNvqCandNbr() {
		return this.nvqCandNbr;
	}

	public void setNvqCandNbr(String nvqCandNbr) {
		this.nvqCandNbr = nvqCandNbr;
	}

	public Date getNvqCertDt() {
		return this.nvqCertDt;
	}

	public void setNvqCertDt(Date nvqCertDt) {
		this.nvqCertDt = nvqCertDt;
	}

	public Date getNvqCompDt() {
		return this.nvqCompDt;
	}

	public void setNvqCompDt(Date nvqCompDt) {
		this.nvqCompDt = nvqCompDt;
	}

	public Date getNvqStartDt() {
		return this.nvqStartDt;
	}

	public void setNvqStartDt(Date nvqStartDt) {
		this.nvqStartDt = nvqStartDt;
	}

	public String getNvqStatus() {
		return this.nvqStatus;
	}

	public void setNvqStatus(String nvqStatus) {
		this.nvqStatus = nvqStatus;
	}

	public String getPassed() {
		return this.passed;
	}

	public void setPassed(String passed) {
		this.passed = passed;
	}

	public String getPracticGradeGer() {
		return this.practicGradeGer;
	}

	public void setPracticGradeGer(String practicGradeGer) {
		this.practicGradeGer = practicGradeGer;
	}

	public String getReadProficiency() {
		return this.readProficiency;
	}

	public void setReadProficiency(String readProficiency) {
		this.readProficiency = readProficiency;
	}

	public String getRenewal() {
		return this.renewal;
	}

	public void setRenewal(String renewal) {
		this.renewal = renewal;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSchoolCode() {
		return this.schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public BigDecimal getScore() {
		return this.score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getSpeakProficiency() {
		return this.speakProficiency;
	}

	public void setSpeakProficiency(String speakProficiency) {
		this.speakProficiency = speakProficiency;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateOther() {
		return this.stateOther;
	}

	public void setStateOther(String stateOther) {
		this.stateOther = stateOther;
	}

	public String getSubfacultyCode() {
		return this.subfacultyCode;
	}

	public void setSubfacultyCode(String subfacultyCode) {
		this.subfacultyCode = subfacultyCode;
	}

	public String getSubfacultyName() {
		return this.subfacultyName;
	}

	public void setSubfacultyName(String subfacultyName) {
		this.subfacultyName = subfacultyName;
	}

	public String getTeacher() {
		return this.teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getTerminalDegree() {
		return this.terminalDegree;
	}

	public void setTerminalDegree(String terminalDegree) {
		this.terminalDegree = terminalDegree;
	}

	public String getTheoryGradeGer() {
		return this.theoryGradeGer;
	}

	public void setTheoryGradeGer(String theoryGradeGer) {
		this.theoryGradeGer = theoryGradeGer;
	}

	public String getTranslator() {
		return this.translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getWriteProficiency() {
		return this.writeProficiency;
	}

	public void setWriteProficiency(String writeProficiency) {
		this.writeProficiency = writeProficiency;
	}

	public BigDecimal getYrAcquired() {
		return this.yrAcquired;
	}

	public void setYrAcquired(BigDecimal yrAcquired) {
		this.yrAcquired = yrAcquired;
	}
	
	/**
	 * This procedure retrieves the Negative Drug Test Date and Physical Test 
	 * Date from the PeopleSoft Accomplishments Table to send back to 
	 * Option 7 of AAHR01.
	 * 
	 */
	public PsAccomplishment HR07GetAccomplishments(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR07-Get-Accomplishments
//		! Desc:  This procedure retrieves the Negative Drug Test Date and Physical
//		!        Test Date from the PeopleSoft Accomplishments Table to send
//		!        back to Option 7 of AAHR01.
		//TODO: split into findDRUGTST, findPHYSL3, findPHYSL4
//		!----------------------------------------------------------------------
//		Begin-Procedure HR07-Get-Accomplishments
//		begin-select
		//get issue date
//		to_char(CA7.DT_ISSUED, 'YYYY-MM-DD')  &CA7DT_ISSUED  //doesn't need to be formatted, since it is being passed to DB2 via JPA
//		    Let $PSIssueDate = &CA7DT_ISSUED
//		CA7.ACCOMPLISHMENT
		//trim accomplishmentCode value
//		    Let $SelectAccomplishment = Ltrim(Rtrim(&CA7.ACCOMPLISHMENT,' '),' ')
//		    !select the correct accomplishmentCode to pull in the correct date
		//swutch on accomplishmentCode
//		    evaluate $SelectAccomplishment
//		        !when accomplishmentCode is equal to negative drug test
//		        when = 'DRUGTST'
//		            Let $LegNegDrugTestDate = $PSIssueDate
//		            !Format negative drug test date so legacy will accept it (MM field, DD field and CCYY field)
//		            Unstring $PSIssueDate by '-' into $LegNegDrugTstYear $LegNegDrugTstMonth $LegNegDrugTstDay
//		            break                 !Exit the evaluate statement
//		        !when accomplishmentCode is equal to physical test date for level 3
//		        when = 'PHYS L3'
//		            Let $LegPhysical3TestDate = $PSIssueDate
//		            !Format physical test date so legacy will accept it (MM field, DD field and CCYY field)
//		            Unstring $PSIssueDate by '-' into $LegPhysTstYear $LegPhysTstMonth $LegPhysTstDay
//		            break                 !Exit the evaluate statement
//		        !when accomplishmentCode is equal to physical test date for level 4
//		        when = 'PHYS L4'
//		            Let $LegPhysical4TestDate = $PSIssueDate
//		            !Format physical test date so legacy will accept it (MM field, DD field and CCYY field)
//		            Unstring $PSIssueDate by '-' into $LegPhysTstYear $LegPhysTstMonth $LegPhysTstDay
//		            break                 !Exit the evaluate statement
//		        !when equal to anything else, do not get issue date
//		        when-other
//		            break                 !Exit the evaluate statement
//		    end-evaluate   !$SelectAccomplishment
//		 !select the maximum issue date in order to get the most current physical/drug test date because an employee could have multiple rows
//		from PS_ACCOMPLISHMENTS CA7
//		where CA7.Emplid = $PSEMPLID
//		    and CA7.ACCOMPLISHMENT in ('DRUGTST', 'PHYS L3', 'PHYS L4')
//		    and CA7.DT_ISSUED = (select max(CA72.DT_ISSUED)
//		                           from PS_ACCOMPLISHMENTS CA72
//		                          where CA72.EMPLID = CA7.EMPLID
//		                            and CA72.ACCOMPLISHMENT = CA7.ACCOMPLISHMENT
//		                            and CA72.DT_ISSUED <= $AsofToday)
//		End-select
//		End-Procedure HR07-Get-Accomplishments
		return null;
	}

	/**
	 * Replaces SQC procedure HR07-Get-Accomplishments in ZHRI107A.SQC
	 * This procedure retrieves the PsAccomplishment record for the Negative Drug Test or Physical Test, 
	 * with matching employeeId and the latest dateIssued value up to today, from the PeopleSoft Accomplishments Table 
	 * to send back to Option 7 of AAHR01.
	 */
	//TODO: verify that CURRENT_DATE is equivalent to $AsofToday
	public static PsAccomplishment findByEmployeeIdAndAccomplishmentCode(String employeeId, String accomplishmentCode) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsAccomplishment> resultList = (List<PsAccomplishment>) em.createQuery("SELECT p FROM PsAccomplishment p "
	    				+ "WHERE p.employeeId = :employeeId "
	    				+ "AND UPPER(TRIM(p.accomplishmentCode)) = :accomplishmentCode "
	    				+ "AND p.dateIssued = "
	    				+ 		"(SELECT MAX(p2.dateIssued) "
	    				+ 		"FROM PsAccomplishment p2 "
		            	+ 		"WHERE p2.employeeId = :employeeId "
		             	+ 		"AND UPPER(TRIM(p2.accomplishmentCode)) = :accomplishmentCode "
		            	+ 		"AND p2.dateIssued <= CURRENT_DATE)", PsAccomplishment.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("accomplishmentCode", accomplishmentCode.toUpperCase())
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0);
	    	}
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
	}
	
	@Override
	public String toString() {
		return "EmployeeId: " + getEmployeeId() + "\n" +
				"AccomplishmentCode: " + getAccomplishmentCode() + "\n" +
				"ApsHeducCdAus: " + getApsHeducCdAus() + "\n" +
				"ApsMinorAus: " + getApsMinorAus() + "\n" +
				"dateIssued: " + getDateIssued() + "\n" +
				"Description: " + getDescription() + "\n" +
				"WriteProficiency: " + getWriteProficiency() + "\n" +
				"YrAcquired: " + getYrAcquired();
	}
	
	public enum AccomplishmentCode {
		DRUGTST ("DRUGTST"), PHYS_L3 ("PHYS L3"), PHYS_L4 ("PHYS L4");
		
	    private String description;

	    AccomplishmentCode(String description) {
	        this.description = description;
	    }

	    public String description() {
	        return description;
	    }
	}
}
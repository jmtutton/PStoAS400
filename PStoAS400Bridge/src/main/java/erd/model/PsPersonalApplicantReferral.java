package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PS_PERS_APPL_REF database table.
 * Personal Referral Source Information about Applicant
 */
@Entity
@Table(name="PS_PERS_APPL_REF")
@NamedQuery(name="PsPersonalApplicantReferral.findAll", query="SELECT p FROM PsPersonalApplicantReferral p")
public class PsPersonalApplicantReferral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="APP_IS_FAMILY", nullable=false, length=1)
	private String applicantIsFamily;

	@Column(name="EFFDT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="EMPL_REFERRAL_ID", nullable=false, length=11)
	private String referralId;

	@Column(name="HRS_PERSON_ID", nullable=false, precision=15)
	private BigInteger recruiterId;

	@Column(name="HRS_PROFILE_SEQ", nullable=false, precision=38)
	private BigInteger profileSequence;

	@Column(name="HRS_SOURCE_ID", nullable=false, precision=15)
	private BigInteger sourceId;

	@Column(name="HRS_SUBSOURCE_ID", nullable=false, precision=15)
	private BigInteger subsourceId;

	@Column(name="PREV_EMPL_BY_COMPY", nullable=false, length=1)
	private String previousEmployer;

	@Column(name="RESUME_TEXT_FILE", nullable=false, length=64)
	private String resumeTextFile;

	@Column(name="SPECIFIC_REFER_SRC", nullable=false, length=50)
	private String specificReferralSource;

	public PsPersonalApplicantReferral() {
	}

	public String getApplicantIsFamily() {
		return this.applicantIsFamily;
	}

	public void setApplicantIsFamily(String applicantIsFamily) {
		this.applicantIsFamily = applicantIsFamily;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getReferralId() {
		return this.referralId;
	}

	public void setReferralId(String referralId) {
		this.referralId = referralId;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public BigInteger getRecruiterId() {
		return this.recruiterId;
	}

	public void setRecruiterId(BigInteger recruiterId) {
		this.recruiterId = recruiterId;
	}

	public BigInteger getProfileSequence() {
		return this.profileSequence;
	}

	public void setsProfileSequence(BigInteger profileSequence) {
		this.profileSequence = profileSequence;
	}

	public BigInteger getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(BigInteger sourceId) {
		this.sourceId = sourceId;
	}

	public BigInteger getSubsourceId() {
		return this.subsourceId;
	}

	public void setSubsourceId(BigInteger subsourceId) {
		this.subsourceId = subsourceId;
	}

	public String getPreviousEmployer() {
		return this.previousEmployer;
	}

	public void setPreviousEmployer(String previousEmployer) {
		this.previousEmployer = previousEmployer;
	}

	public String getResumeTextFile() {
		return this.resumeTextFile;
	}

	public void setResumeTextFile(String resumeTextFile) {
		this.resumeTextFile = resumeTextFile;
	}

	public String getSpecificReferralSource() {
		return this.specificReferralSource;
	}

	public void setSpecificReferralSource(String specificReferralSource) {
		this.specificReferralSource = specificReferralSource;
	}

	/**
	 * This routine will get the referral source data for each of the employee numbers 
	 * entered in the trigger file since it was removed from the Personal Data table to 
	 * the PS_PERS_APPL_INFO table in 8.3.
	 * @see HR05-Get-Referral-Source
	 * @param employeeId
	 * @return PsReferralSource
	 */
	public static PsPersonalApplicantReferral findByEmployeeIdAndEffectiveDate(String employeeId, Date effectiveDate) {
		//SELECT FROM PS_PERS_APPL_REF CPAI3
		//WHERE CPAI3.EMPLID = $EmplId
		//AND CPAI3.EFFDT = 
				//(SELECT MAX(CPAI3A.EFFDT)                                     
				//FROM PS_PERS_APPL_REF CPAI3A                                
				//WHERE CPAI3A.EMPLID = CPAI3.EMPLID                        
				//AND TO_CHAR(CPAI3A.EFFDT,'YYYY-MM-DD') <= $Effdt)     
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsPersonalApplicantReferral> resultList = em.createQuery(
	    			"SELECT p FROM PsPersonalApplicantReferral p "
	    					+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
							+ "AND p.effectiveDate = "
							+ "(SELECT MAX(p2.effectiveDate) FROM PsPersonalApplicantReferral p2 "
							+ "WHERE UPPER(TRIM(p2.employeeId)) = UPPER(TRIM(p.employeeId)) "
								+ "AND p2.effectiveDate <= :effectiveDate) "
	    					, PsPersonalApplicantReferral.class)
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
	
}
package erd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The persistent class for the PS_PERS_DATA_EFFDT database table.
 * Effective Dated Personal Data
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_PERS_DATA_EFFDT")
@NamedQuery(name="PsEffectiveDatedPersonalData.findAll", query="SELECT p FROM PsEffectiveDatedPersonalData p")
public class PsEffectiveDatedPersonalData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="ALTER_EMPLID", nullable=false, length=11)
	private String alternateEmployeeId;

	@Column(name="EFFDT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="FT_STUDENT", nullable=false, length=1)
	private String isFulltimeStudent;

	@Column(name="HIGHEST_EDUC_LVL", nullable=false, length=2)
	private String highestEducationLevel;

	@Column(name="LANG_CD", nullable=false, length=3)
	private String languageCode;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="MAR_STATUS", nullable=false, length=1)
	private String maritalStatus;

	@Column(name="MAR_STATUS_DT")
	@Temporal(TemporalType.DATE)
	private Date maritalStatusDate;

	@Column(name="SEX", nullable=false, length=1)
	private String gender;

	public PsEffectiveDatedPersonalData() {
	}

	public String getAlternateEmployeeId() {
		return this.alternateEmployeeId;
	}

	public void setAlternateEmployeeId(String alternateEmployeeId) {
		this.alternateEmployeeId = alternateEmployeeId;
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

	public String getIsFulltimeStudent() {
		return this.isFulltimeStudent;
	}

	public void setIsFulltimeStudent(String isFulltimeStudent) {
		this.isFulltimeStudent = isFulltimeStudent;
	}

	public String getHighestEducationLevel() {
		return this.highestEducationLevel;
	}

	public void setHighestEducationLevel(String highestEducationLevel) {
		this.highestEducationLevel = highestEducationLevel;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
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

	public String getMaritalStatus() {
		return this.maritalStatus != null ? this.maritalStatus.trim().toUpperCase() : this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Date getMaritalStatusDate() {
		return this.maritalStatusDate;
	}

	public void setMaritalStatusDate(Date maritalStatusDate) {
		this.maritalStatusDate = maritalStatusDate;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * @see HR01-Get-Personal-Data
	 * @see HR05-Get-Info
	 * @see HR201-Get-Personal-Data
	 * @param employeeId
	 * @param effectiveDate
	 * @return
	 */
	public static PsEffectiveDatedPersonalData findByEmployeeIdAndEffectiveDate(String employeeId, Date effectiveDate) {
		//SELECT FROM PS_PERS_DATA_EFFDT P
		//WHERE P.EMPLID = $Wrk_Emplid
		//AND P.EFFDT = 
				//(SELECT MAX(P2.EFFDT) FROM PS_PERS_DATA_EFFDT P2
				//WHERE P2.EMPLID = P.EMPLID AND TO_CHAR(P2.EFFDT,'YYYY-MM-DD') <= $EffDt)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<PsEffectiveDatedPersonalData> resultList = em.createQuery(
					"SELECT p FROM PsEffectiveDatedPersonalData p "
					+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
					+ "AND p.effectiveDate = "
							+ "(SELECT MAX(p2.effectiveDate) FROM PsEffectiveDatedPersonalData p2 "
							+ "WHERE UPPER(TRIM(p2.employeeId)) = UPPER(TRIM(p.employeeId)) "
							+ "AND p2.effectiveDate <= :effectiveDate) "
					, PsEffectiveDatedPersonalData.class)
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
package erd.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_PERS_NID database table.
 * National ID Record
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_PERS_NID")
@NamedQuery(name="PsPersonalNationalId.findAll", query="SELECT p FROM PsPersonalNationalId p")
public class PsPersonalNationalId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="COUNTRY", nullable=false, length=3)
	private String country;

	@Column(name="LASTUPDDTTM", nullable=false)
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="NATIONAL_ID", nullable=false, length=20)
	private String nationalId;

	@Column(name="NATIONAL_ID_TYPE", nullable=false, length=6)
	private String nationalIdType;

	@Column(name="PRIMARY_NID", nullable=false, length=1)
	private String isPrimaryId;

	@Column(name="SSN_KEY_FRA", nullable=false, length=2)
	private String socialSecurityNumberKey;

	@Column(name="TAX_REF_ID_SGP", nullable=false, length=1)
	private String isNationalRegistrationIdUsedAsTaxReferenceNumber;

	public PsPersonalNationalId() {
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public String getNationalId() {
		return this.nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getNationalIdType() {
		return this.nationalIdType;
	}

	public void setNationalIdType(String nationalIdType) {
		this.nationalIdType = nationalIdType;
	}

	public String getIsPrimaryId() {
		return this.isPrimaryId;
	}

	public void setIsPrimaryId(String isPrimaryId) {
		this.isPrimaryId = isPrimaryId;
	}

	public String getSocialSecurityNumberKey() {
		return this.socialSecurityNumberKey;
	}

	public void setSocialSecurityNumberKey(String socialSecurityNumberKey) {
		this.socialSecurityNumberKey = socialSecurityNumberKey;
	}

	public String getIsNationalRegistrationIdUsedAsTaxReferenceNumber() {
		return this.isNationalRegistrationIdUsedAsTaxReferenceNumber;
	}

	public void setIsNationalRegistrationIdUsedAsTaxReferenceNumber(String isNationalRegistrationIdUsedAsTaxReferenceNumber) {
		this.isNationalRegistrationIdUsedAsTaxReferenceNumber = isNationalRegistrationIdUsedAsTaxReferenceNumber;
	}

	/**
	 * This routine gets the social security number from the PeopleSoft tables and formats them for the legacy system.
	 * @see HR01-Get-Personal-Data
	 * @see HR05-Get-Pers-Nid in ZHRI105A.SQC
	 * @param employeeId
	 * @return nationalId
	 */
	public static String findPrimaryNationalIdByEmployeeIdAndCountry(String employeeId, String country) {
		//SELECT C.NATIONAL_ID FROM PS_PERS_NID C 
		//WHERE C.EMPLID = $PSEmplid AND C.COUNTRY = $PS_REG_REGION AND C.PRIMARY_NID='Y'
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<String> resultList = em.createQuery(
					"SELECT c.nationalId FROM PsPersonalNationalId c "
					+ "WHERE UPPER(TRIM(c.employeeId)) = UPPER(TRIM(:employeeId)) "
					+ "AND UPPER(TRIM(c.country)) = UPPER(TRIM(:country)) "
					+ "AND UPPER(TRIM(c.isPrimaryId)) = 'Y' "
					, String.class)
					.setParameter("employeeId", employeeId)
					.setParameter("country", country)
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
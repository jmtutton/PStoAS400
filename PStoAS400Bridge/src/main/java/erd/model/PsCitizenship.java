package erd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_CITIZENSHIP database table.
 * Employee/Dependent Citizenship
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_CITIZENSHIP")
@NamedQuery(name="PsCitizenship.findAll", query="SELECT p FROM PsCitizenship p")
public class PsCitizenship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="CITIZENSHIP_STATUS", nullable=false, length=1)
	private String citizenshipStatus;

	@Column(name="COUNTRY", nullable=false, length=3)
	private String countryIsoAlpha3Code;

	@Transient
	private String countryIsoAlpha2Code;

	@Column(name="DEPENDENT_ID", nullable=false, length=2)
	private String dependentId;

	@Column(name="PERM_STATUS_DT_SGP")
	@Temporal(TemporalType.DATE)
	private Date permStatusDtSgp;

	@Column(name="WORKER_TYPE_SGP", nullable=false, length=1)
	private String workerTypeSgp;

	public PsCitizenship() {
	}

	public String getCitizenshipStatus() {
		return this.citizenshipStatus;
	}

	public void setCitizenshipStatus(String citizenshipStatus) {
		this.citizenshipStatus = citizenshipStatus;
	}

	public String getCountryCode() {
		return this.countryIsoAlpha3Code;
	}

	public void setCountryCode(String countryIsoAlpha3Code) {
		this.countryIsoAlpha3Code = countryIsoAlpha3Code;
	}

	public String getDependentId() {
		return this.dependentId;
	}

	public void setDependentId(String dependentId) {
		this.dependentId = dependentId;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getPermStatusDtSgp() {
		return this.permStatusDtSgp;
	}

	public void setPermStatusDtSgp(Date permStatusDtSgp) {
		this.permStatusDtSgp = permStatusDtSgp;
	}

	public String getWorkerTypeSgp() {
		return this.workerTypeSgp;
	}

	public void setWorkerTypeSgp(String workerTypeSgp) {
		this.workerTypeSgp = workerTypeSgp;
	}
	
	/**
	 * Replaces SQC procedure HR05-Get-Citizenship from ZHRI105A.SQC
	 * This procedure retrieves a record from the Citizenship table 
	 * with matching Employee ID and Country Code, plus the corresponding 
	 * two letter country abbreviation from the Country table.
	 * @see HR05-Get-Citizenship in ZHRI105A.SQC
	 * @param employeeId
	 * @param countryCode
	 * @return
	 */
	public static PsCitizenship findByEmployeeIdAndCountryCode(String employeeId, String countryCode) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsCitizenship> resultList = (List<PsCitizenship>) em.createQuery("SELECT p, (p.countryIsoAlpha2Code = p2.countryIsoAlpha2Code) "
	    				+ "FROM PsCitizenship p, PsCountry p2 "
	    				+ "WHERE UPPER(TRIM(p.employeeId = UPPER(TRIM(:employeeId)) "
	    				+ "AND UPPER(TRIM(p.countryIsoAlpha3Code)) = UPPER(TRIM(:countryCode)) "
	    				+ "AND UPPER(TRIM(p2.countryIsoAlpha3Code)) = UPPER(TRIM(:countryCode)) "
	    				, PsCitizenship.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("countryCode", countryCode)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0);
	    	}
	    	else 
	    		return null;
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
	 * Replaces SQC procedure HR05-Get-Citizenship from ZHRI105A.SQC
	 * This procedure retrieves a record from the Citizenship table 
	 * with matching Employee ID and Country Code, plus the corresponding 
	 * two letter country abbreviation from the Country table.
	 * @see HR05-Get-Citizenship in ZHRI105A.SQC
	 * @param employeeId
	 * @param countryCode
	 * @return
	 */
	public static String findCountryIsoAlpha2CodeByEmployeeIdAndCountryCode(String employeeId, String countryCode) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = (List<String>) em.createQuery(
	    			"SELECT UPPER(TRIM(p.countryIsoAlpha2Code)) "
	    				+ "FROM PsCitizenship p, PsCountry p2 "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
	    				+ "AND UPPER(TRIM(p.countryIsoAlpha3Code)) = UPPER(TRIM(:countryCode)) "
	    				+ "AND UPPER(TRIM(p2.countryIsoAlpha3Code)) = UPPER(TRIM(:countryCode)) "
	    				, String.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("countryCode", countryCode)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0);
	    	}
	    	else 
	    		return null;
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
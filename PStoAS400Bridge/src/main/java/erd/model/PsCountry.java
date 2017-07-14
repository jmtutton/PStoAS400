package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_COUNTRY_TBL database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_COUNTRY_TBL")
@NamedQuery(name="PsCountry.findAll", query="SELECT p FROM PsCountry p")
public class PsCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COUNTRY", nullable=false, length=3)
	private String countryIsoAlpha3Code;

	@Column(name="ADDR_VALIDAT", nullable=false, length=1)
	private String isAddressValidationEnabled;

	@Column(name="COUNTRY_2CHAR", nullable=false, length=2)
	private String countryIsoAlpha2Code;

	@Column(name="DESCR", nullable=false, length=30)
	private String description;

	@Column(name="DESCRSHORT", nullable=false, length=10)
	private String shortDescription;

	@Column(name="EO_SEC_PAGE_NAME", nullable=false, length=18)
	private String addressEditPage;

	@Column(name="EU_MEMBER_STATE", nullable=false, length=1)
	private String isEuMemberState;

	@Column(name="POST_SRCH_AVAIL", nullable=false, length=1)
	private String isPostSearchAvailable;

	public PsCountry() {
	}

	public String getIsAddressValidationEnabled() {
		return this.isAddressValidationEnabled;
	}

	public void setIsAddressValidationEnabled(String isAddressValidationEnabled) {
		this.isAddressValidationEnabled = isAddressValidationEnabled;
	}

	public String getCountryIsoAlpha3Code() {
		return this.countryIsoAlpha3Code;
	}

	public void setCountryIsoAlpha3Code(String countryIsoAlpha3Code) {
		this.countryIsoAlpha3Code = countryIsoAlpha3Code;
	}

	public String getCountryIsoAlpha2Code() {
		return this.countryIsoAlpha2Code;
	}

	public void setCountryIsoAlpha2Code(String countryIsoAlpha2Code) {
		this.countryIsoAlpha2Code = countryIsoAlpha2Code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getAddressEditPage() {
		return this.addressEditPage;
	}

	public void setAddressEditPage(String addressEditPage) {
		this.addressEditPage = addressEditPage;
	}

	public String getIsEuMemberState() {
		return this.isEuMemberState;
	}

	public void setIsEuMemberState(String isEuMemberState) {
		this.isEuMemberState = isEuMemberState;
	}

	public String getIsPostSearchAvailable() {
		return this.isPostSearchAvailable;
	}

	public void setIsPostSearchAvailable(String isPostSearchAvailable) {
		this.isPostSearchAvailable = isPostSearchAvailable;
	}
	
	/**
	 * Gets the two character country code for the employee's country of citizenship.
	 * @see HR05-Get-Citizenship in ZHRI105A.SQC
	 * @param employeeId
	 * @return countryIsoAlpha2Code
	 */
	public static String findCitizenshipCountryIsoAlpha2CodeByEmployeeId(String employeeId) {
		//SELECT P.COUNTRY_2CHAR
		//FROM PS_COUNTRY_TBL P, PS_CITIZENSHIP P2
		//WHERE P2.EMPLID = $PSEmplid AND P.COUNTRY = P2.COUNTRY
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = (List<String>) em.createQuery(
	    			"SELECT UPPER(TRIM(p.countryIsoAlpha2Code)) "
	    				+ "FROM PsCountry p, PsCitizenship p2 "
	    				+ "WHERE UPPER(TRIM(p2.employeeId)) = UPPER(TRIM(:employeeId)) "
	    				+ "AND UPPER(TRIM(p.countryIsoAlpha3Code)) = UPPER(TRIM(p2.countryIsoAlpha3Code)) "
	    				, String.class)
	    		    .setParameter("employeeId", employeeId)
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
	 * Gets the two character ISO country code for the given three character ISO country code.
	 * @see Get-2Char-Country in ZHRI101A.SQC
	 * @param countryIsoAlpha3Code
	 * @return countryIsoAlpha2Code
	 */
	public static String findCountryIsoAlpha2CodeByCountryIsoAlpha3Code(String countryIsoAlpha3Code) {
		//SELECT P.COUNTRY_2CHAR 
		//FROM PS_COUNTRY_TBL P 
		//WHERE P.COUNTRY = $PsRegRegion
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    			"SELECT UPPER(TRIM(c.countryIsoAlpha2Code)) FROM PsCountry c "
	    					+ "WHERE UPPER(TRIM(c.countryIsoAlpha3Code)) = UPPER(TRIM(:countryIsoAlpha3Code)) "
	    			, String.class)
	    		    .setParameter("countryIsoAlpha3Code", countryIsoAlpha3Code)
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
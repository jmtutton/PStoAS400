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
	
	public static String findCountryIsoAlpha2CodeByCountryCode(String country) {
		//!----------------------------------------------------------------------------
		//! Procedure: GET-2CHAR-COUNTRY
		//! DESC: This procedure will get the 2 character country code
		//!-----------------------------------------------------------------------------
		//BEGIN-PROCEDURE GET-2CHAR-COUNTRY
		//BEGIN-SELECT
		//CTRY.COUNTRY_2CHAR
		//let $PS_NID_COUNTRY=ltrim(rtrim(&CTRY.COUNTRY_2CHAR,' '),' ')
		//let $PS_NID_COUNTRY=substr($PS_NID_COUNTRY,1,2)
		//FROM PS_COUNTRY_TBL  CTRY
		//WHERE CTRY.country=$PS_REG_REGION
		//END-SELECT
		//END-PROCEDURE
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    			"SELECT c.countryIsoAlpha2Code FROM PsCountry c "
	    					+ "WHERE UPPER(TRIM(c.country)) = :country ", String.class)
	    		    .setParameter("country", country.toUpperCase().trim())
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		String result  = resultList.get(0).trim().toUpperCase();
	    		if(result.length() > 2) {
	    			result = result.substring(0, 2);
	    		}
	    		return result;
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
	 * @see HR05-Get-Citizenship in ZHRI105A.SQC
	 * @param employeeId
	 * @return countryIsoAlpha2Code
	 */
	public static String findCountryIsoAlpha2CodeByEmployeeId(String employeeId) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = (List<String>) em.createQuery(
	    			"SELECT p.countryIsoAlpha2Code "
	    				+ "FROM PsCitizenship p, PsCountry p2 "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    				+ "AND UPPER(TRIM(p2.countryIsoAlpha3Code)) = UPPER(TRIM(p.countryIsoAlpha3Code)) "
	    				, String.class)
	    		    .setParameter("employeeId", employeeId.trim().toUpperCase())
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
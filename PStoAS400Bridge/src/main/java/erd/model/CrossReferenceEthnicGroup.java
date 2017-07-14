package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_ZHRT_ETHCD_CREF database table.
 * Cross-Reference of Ethnic Group to Legacy Ethic Code
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRT_ETHCD_CREF")
@NamedQuery(name="CrossReferenceEthnicGroup.findAll", query="SELECT p FROM CrossReferenceEthnicGroup p")
public class CrossReferenceEthnicGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ZHRF_LEGETHNICCD", nullable=false, length=1)
	private String legacyEthnicCode;

	@Column(name="ETHNIC_GROUP", nullable=false, length=1)
	private String ethnicGroup;

	@Column(name="STATUS", nullable=false, length=1)
	private String status;

	public CrossReferenceEthnicGroup() {
	}

	public String getEthnicGroup() {
		return this.ethnicGroup;
	}

	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLegacyEthnicCode() {
		return this.legacyEthnicCode;
	}

	public void setLegacyEthnicCode(String legacyEthnicCode) {
		this.legacyEthnicCode = legacyEthnicCode;
	}

	/**
	 * Gets the legacy ethnic code that corresponds to the PeopleSoft ethnic group using the cross reference table.
	 * @see HR01-Get-Legacy-Ethnic-Code in ZHRI101A.SQC
	 * @see HR05-Get-Ethnic-Group in ZHRI105A.SQC
	 * @param ethnicGroup
	 * @return legacyEthnicCode
	 */
	public static String findActiveLegacyEthnicCodeByEthnicGroup(String ethnicGroup) {
		//SELECT P.ZHRF_LEGETHNICCD
		//FROM PS_ZHRT_ETHCD_CREF P
		//WHERE P.ETHNIC_GROUP = $PsEthnicGroup                                          
		//AND P.STATUS = 'A'
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = (List<String>) em.createQuery(
	    			"SELECT UPPER(TRIM(p.legacyEthnicCode)) "
	    				+ "FROM CrossReferenceEthnicGroup p "
	    				+ "WHERE UPPER(TRIM(p.ethnicGroup)) = UPPER(TRIM(:ethnicGroup)) "
	    				+ "AND UPPER(TRIM(p.status)) = 'A' "
	    				, String.class)
	    		    .setParameter("ethnicGroup", ethnicGroup)
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
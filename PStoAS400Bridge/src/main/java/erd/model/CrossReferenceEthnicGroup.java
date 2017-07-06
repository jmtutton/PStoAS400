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

	public CrossReferenceEthnicGroup HR01GetLegacyEthnicCode(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR01-Get-Legacy-Ethnic-Code
//		! Desc:  Gets the legacy ethnic code that corresponds to the PeopleSoft
//		!        ethnic code using the cross reference table.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR01-Get-Legacy-Ethnic-Code
//		Let $Found = 'N'
//		Begin-Select
//		CPT6.ZHRF_LEGETHNICCD
//		    Let $LegRace = &CPT6.ZHRF_LEGETHNICCD   !changed for v8.3
//		    Let $Found = 'Y'
//		from PS_ZHRT_ETHCD_CREF CPT6
//		where CPT6.ETHNIC_GROUP = $PSEthnicCode
//		  and CPT6.STATUS = 'A'
//		End-Select
//		If $Found = 'N'
//		    ! Let $ErrorMessageParm = 'The ethnic code is not in the XRef table PS_ZHRT_ETHCD_CREF'
//		    ! Do Call-Error-Routine
//		End-If    !$Found = 'N'
//		End-Procedure HR01-Get-Legacy-Ethnic-Code
		return null;
	}
	
	/**
	 * Replaces SQC procedure HR01-Get-Legacy-Ethnic-Code from ZHRI101A.SQC and HR05-Get-Ethnic-Group from ZHRI105A.SQC
	 * This procedure finds the legacy ethnic code that corresponds to the PeopleSoft
	 * ethnic group.
	 * @see HR01-Get-Legacy-Ethnic-Code in ZHRI101A.SQC
	 * @see HR05-Get-Ethnic-Group in ZHRI105A.SQC
	 */
	public static List<CrossReferenceEthnicGroup> findGetLegacyEthnicCode(String ethnicGroup) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<CrossReferenceEthnicGroup> resultList = (List<CrossReferenceEthnicGroup>) em.createQuery("SELECT p FROM CrossReferenceEthnicCode p "
	    				+ "WHERE p.ethnicGroup = :ethnicGroup AND p.status = :status", CrossReferenceEthnicGroup.class)
	    		    .setParameter("ethnicGroup", ethnicGroup)
	    		    .setParameter("status", "A")
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList;
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
	 * This procedure gets the ethnic code from the cross reference table for the legacy system.
	 * @see HR05-Get-Ethnic-Group in ZHRI105A.SQC
	 * @return
	 */
	public static String findLegacyEthnicCodeByEthnicGroup(String ethnicGroup) {
		//BEGIN-SELECT
		//CPT62.ZHRF_LEGETHNICCD
		//CPT62.ETHNIC_GROUP
		//LET $PSEthnic_Group = &CPT62.ZHRF_LEGETHNICCD
		//FROM PS_ZHRT_ETHCD_CREF CPT62
		//WHERE CPT62.ETHNIC_GROUP = $PSEthnic_Group                                          
		//AND CPT62.STATUS = 'A'
		//END-SELECT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = (List<String>) em.createQuery(
	    			"SELECT p.legacyEthnicCode "
	    				+ "FROM CrossReferenceEthnicGroup p "
	    				+ "WHERE UPPER(TRIM(p.ethnicGroup)) = :ethnicGroup "
	    				+ "AND UPPER(TRIM(p.status)) = 'A' "
	    				, String.class)
	    		    .setParameter("ethnicGroup", ethnicGroup.trim().toUpperCase())
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
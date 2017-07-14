package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_ZHRT_CMPNY_CREF database table.
 * Cross-Reference of Company and Business Unit to Legacy Group and Legacy Region
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRT_CMPNY_CREF")
@NamedQuery(name="CrossReferenceCompany.findAll", query="SELECT p FROM CrossReferenceCompany p")
public class CrossReferenceCompany implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SETID", nullable=false, length=5)
	private String setId;

	@Column(name="BUSINESS_UNIT", nullable=false, length=5)
	private String businessUnit;

	@Column(name="COMPANY", nullable=false, length=3)
	private String company;

	@Column(name="STATUS", nullable=false, length=1)
	private String status;

	@Column(name="ZHRF_LEGGROUP", nullable=false, length=2)
	private String legacyGroup;

	@Column(name="ZHRF_LEGREGION", nullable=false, length=1)
	private String legacyRegion;

	public CrossReferenceCompany() {
	}

	public String getBusinessUnit() {
		return this.businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSetId() {
		return this.setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLegacyGroup() {
		return this.legacyGroup;
	}

	public void setLegacyGroup(String legacyGroup) {
		this.legacyGroup = legacyGroup;
	}

	public String getLegacyRegion() {
		return this.legacyRegion;
	}

	public void setLegacyRegion(String legacyRegion) {
		this.legacyRegion = legacyRegion;
	}

	/**
	 * This routine gets the business unit and old region from the cross reference table PT005P.
	 * @see HR05-Get-Business-Unit in ZHRI105A.SQC
	 * @param businessUnit
	 * @return legacyRegion
	 */
	public static String findLegacyRegionByBusinessUnit(String businessUnit) {
		//SELECT C.ZHRF_LEGREGION
		//FROM PS_ZHRT_CMPNY_CREF C
		//WHERE C.BUSINESS_UNIT = $BusinessUnit
		//AND C.STATUS = 'A'
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<String> resultList = em.createQuery(
					"SELECT UPPER(TRIM(c.legacyRegion)) FROM CrossReferenceCompany c "
					+ "WHERE UPPER(TRIM(c.businessUnit)) = UPPER(TRIM(:businessUnit)) "
					+ "AND UPPER(TRIM(c.status)) = 'A' "
					, String.class)
					.setParameter("businessUnit", businessUnit)
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

	/**
	 * @see HR01-Get-Group
	 * @see HR04-Get-Group
	 * @see HR09-Get-Group
	 * @param company
	 * @return legacyGroup
	 */
	public static String findActiveLegacyGroupByCompany(String company) {
		//SELECT C.ZHRF_LEGGROUP
		//FROM PS_ZHRT_CMPNY_CREF C
		//WHERE C.COMPANY = $PsCompany
		//AND C.STATUS = 'A'
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<String> resultList = em.createQuery(
					"SELECT UPPER(TRIM(c.legacyGroup)) FROM CrossReferenceCompany c "
					+ "WHERE UPPER(TRIM(c.company)) = UPPER(TRIM(:company)) "
					+ "AND UPPER(TRIM(c.status)) = 'A' "
					, String.class)
					.setParameter("company", company)
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
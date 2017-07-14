package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_DIVERS_ETHNIC database table.
 * Ethnic Diversity
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_DIVERS_ETHNIC")
@NamedQuery(name="PsDiversityEthnicity.findAll", query="SELECT p FROM PsDiversityEthnicity p")
public class PsDiversityEthnicity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="SETID", nullable=false, length=5)
	private String setId;

	@Column(name="APS_EC_NDS_AUS", nullable=false, length=1)
	private String refusedEthnicityDetails;

	@Column(name="ETHNIC_GRP_CD", nullable=false, length=8)
	private String ethnicGroupCode;

	@Column(name="PRIMARY_INDICATOR", nullable=false, length=1)
	private String isPrimaryIndicatorForMultiple;

	@Column(name="REG_REGION", nullable=false, length=5)
	private String regulatoryRegion;

	public PsDiversityEthnicity() {
	}

	public String getRefusedEthnicityDetails() {
		return this.refusedEthnicityDetails;
	}

	public void setRefusedEthnicityDetails(String refusedEthnicityDetails) {
		this.refusedEthnicityDetails = refusedEthnicityDetails;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEthnicGroupCode() {
		return this.ethnicGroupCode;
	}

	public void setEthnicGroupCode(String ethnicGroupCode) {
		this.ethnicGroupCode = ethnicGroupCode;
	}

	public String getIsPrimaryIndicator() {
		return this.isPrimaryIndicatorForMultiple;
	}

	public void setIsPrimaryIndicator(String isPrimaryIndicatorForMultiple) {
		this.isPrimaryIndicatorForMultiple = isPrimaryIndicatorForMultiple;
	}

	public String getRegulatoryRegion() {
		return this.regulatoryRegion;
	}

	public void setRegulatoryRegion(String regulatoryRegion) {
		this.regulatoryRegion = regulatoryRegion;
	}

	public String getSetId() {
		return this.setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}
	
	/**
	 * @see HR01-Get-Ethnic-Group in ZHRI101A.SQC
	 * @see HR05-Get-Diversity in ZHRI105A.SQC
	 * @param employeeId
	 * @return ethnicGroupCode
	 */
	public static String findEthnicGroupCodeByEmployeeId(String employeeId) {
		//SELECT P.ETHNIC_GRP_CD
		//FROM PS_DIVERS_ETHNIC P
		//WHERE P.EMPLID = $PsEmplId
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = (List<String>) em.createQuery(
	    			"SELECT UPPER(TRIM(p.ethnicGroupCode)) "
	    				+ "FROM PsDiversityEthnicity p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
	    				, String.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		if(resultList.size() > 1) {
		    		//if employee has more than one ethnicity, return "O" for "Other"
	    			return "O";
	    		}
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
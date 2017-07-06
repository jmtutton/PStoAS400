package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_DIVERS_ETHNIC database table.
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
	
	public PsDiversityEthnicity HR01GetEthnicGroup(String employeeId) {
//	!----------------------------------------------------------------------
//	! Procedure:  HR01-Get-Ethnic-Group
//	! Desc:  Gets the ethnic group of the employee
//	!----------------------------------------------------------------------
//	Begin-Procedure HR01-Get-Ethnic-Group
//	  Let #PS_ETHNIC_COUNT = 0
//	begin-select
//	DE.ETHNIC_GRP_CD
//	  Let $PS_Ethnic_Group_CD = &DE.ETHNIC_GRP_CD
//	  Let #PS_ETHNIC_COUNT = #PS_ETHNIC_COUNT + 1
//	FROM PS_DIVERS_ETHNIC DE
//	WHERE DE.EMPLID  = $Wrk_Emplid
//	END-SELECT
//	      !if greater than one, the interface will send 'O' for 'Other'
//	  If #PS_ETHNIC_COUNT > 1
//	    Let $PSEthnicCode = 'O'
//	    Else
//	    Do GET-ETHNIC-CODE
//	  End-if    !#PS_ETHNIC_COUNT > 1
//	End-procedure HR01-Get-Ethnic-Group
		return null;
	}
	
	/**
	 * This procedure retrieves a record from the PsDiversityEthnicity table 
	 * with matching Employee ID.
	 * @see HR01-Get-Ethnic-Group in ZHRI101A.SQC
	 * @param employeeId
	 * @return
	 */
	public static PsDiversityEthnicity findByEmployeeId(String employeeId) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsDiversityEthnicity> resultList = (List<PsDiversityEthnicity>) em.createQuery(
	    			"SELECT p FROM PsDiversityEthnicity p "
	    					+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    			, PsDiversityEthnicity.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		PsDiversityEthnicity result = resultList.get(0);
	    		//If employee has more than one ethnicity, set to "O" for "Other"
	    		if(resultList.size() > 1) {
	    			result.setEthnicGroupCode("O");
	    		}
	    		return result;
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
	 * @see HR05-Get-Diversity in ZHRI105A.SQC
	 * @param employeeId
	 * @return ethnicGroupCode
	 */
	public static String findEthnicGroupCodeByEmployeeId(String employeeId) {
		//BEGIN-SELECT
		//DE1.ETHNIC_GRP_CD
		//LET $PSETHNIC_GROUP1 = &DE1.ETHNIC_GRP_CD
		//LET #PS_ETHNIC_COUNT = #PS_ETHNIC_COUNT + 1
		//FROM PS_DIVERS_ETHNIC DE1
		//WHERE DE1.EMPLID = $PSEmplid
		//END-SELECT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = (List<String>) em.createQuery(
	    			"SELECT p.ethnicGroupCode "
	    				+ "FROM PsDiversityEthnicity p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    				, String.class)
	    		    .setParameter("employeeId", employeeId.trim().toUpperCase())
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		if(resultList.size() > 1) {
		    		//if employee has more than one ethnicity, return "O" for "Other"
	    			return "O";
	    		}
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
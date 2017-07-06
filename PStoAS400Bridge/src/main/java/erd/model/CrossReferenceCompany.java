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

	public static CrossReferenceCompany HR01GetGroup(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR01-Get-Group
//		! Desc:  Gets the group from the cross reference file using Company
//		!----------------------------------------------------------------------
//		Begin-Procedure HR01-Get-Group
//		Let $Found = 'N'
//		Begin-Select
//		CPT5.ZHRF_LEGGROUP
//		    Let $LegGroup = ltrim(rtrim(&CPT5.ZHRF_LEGGROUP,' '),' ')              !Remove leading and trailing blanks
//		    Let $Found = 'Y'
//		from PS_ZHRT_CMPNY_CREF CPT5
//		where CPT5.COMPANY = $PSCompany
//		  and CPT5.STATUS = 'A'
//		End-Select
//		If $Found = 'N'
//		     Let $WrkCriticalFlag = 'Y'
//		     Let $CallRPG = 'N'
//		     Let $ErrorMessageParm = 'The Company entered is not in the cross reference table PS_ZHRT_CMPNY_CREF'
//		     Do Call-Error-Routine
//		     Let $WrkCriticalFlag = 'N'
//		End-If    !$Found = 'N'
//		End-Procedure HR01-Get-Group
		return null;
	}

	public static CrossReferenceCompany HR04GetGroup(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR04-Get-Group
//		! Desc:  Gets the group from the cross reference file using Company
//		!----------------------------------------------------------------------
//		Begin-Procedure HR04-Get-Group
//		Let $Found = 'N'
//		Begin-Select
//		CPT52.ZHRF_LEGGROUP
//		    let $LegGroup = ltrim(rtrim(&CPT52.ZHRF_LEGGROUP,' '),' ')   !Remove leading and trailing blanks
//		    Let $Found = 'Y'
//		from PS_ZHRT_CMPNY_CREF CPT52
//		where CPT52.COMPANY = $PSCompany
//		and CPT52.STATUS = 'A'
//		End-Select
//		If $Found = 'N'
//		     Let $ErrorMessageParm = 'Company not found in XRef Table PS_ZHRT_CMPNY_CREF'
//		     Let $WrkCriticalFlag = 'Y'
//		 Let $CallRpg = 'N'
//		Do Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//		     Do Call-Error-Routine     !From ZHRI100A.SQR
//		End-If    !$Found = 'N'
//		End-Procedure HR04-Get-Group		
		return null;
	}

	public static CrossReferenceCompany HR09GetGroup(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR09-Get-Group
//		! Desc:  Gets the group from the cross reference file using Company
//		!----------------------------------------------------------------------
//		Begin-Procedure HR09-Get-Group
//		Let $Found = 'N'
//		Begin-Select
//		CPT53.ZHRF_LEGGROUP
//		    let $LegGroup = ltrim(rtrim(&CPT53.ZHRF_LEGGROUP,' '),' ')  !Remove leading and trailing blanks
//		    Let $Found = 'Y'
//		from PS_ZHRT_CMPNY_CREF CPT53
//		where CPT53.COMPANY = $PSCompany
//		  and CPT53.STATUS = 'A'
//		End-Select
//		If $Found = 'N'
//		     Let $ErrorMessageParm = 'Company not found in XRef Table PS_ZHRT_CMPNY_CREF'
//		     Let $WrkCriticalFlag = 'Y'
//		     Let $CallRpg = 'N'
//		     Do Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR     Do Prepare-Error-Parms    !From ZHRI100A.SQR
//		     Do Call-Error-Routine  !ZHRI100A.SQR
//		End-If    !$Found = 'N'
//		End-Procedure HR09-Get-Group
		return null;
	}
	
	/**
	 * This routine gets the business unit and old region from the cross reference table PT005P.
	 * @see HR05-Get-Business-Unit in ZHRI105A.SQC
	 * @return region
	 */
	public static String findLegacyRegionByBusinessUnit(String businessUnit) {
		//BEGIN-SELECT
		//CPT51.ZHRF_LEGREGION
		//LET $PSReg = &CPT51.ZHRF_LEGREGION
		//FROM PS_ZHRT_CMPNY_CREF CPT51
		//WHERE CPT51.BUSINESS_UNIT = &CJ7.Business_Unit
		//AND CPT51.STATUS = 'A'
		//END-SELECT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<CrossReferenceCompany> resultList = em.createQuery(
					"SELECT CrossReferenceCompany FROM CrossReferenceCompany c "
					+ "WHERE UPPER(TRIM(c.businessUnit)) = :businessUnit "
					+ "AND UPPER(TRIM(c.status)) = 'A' "
					, CrossReferenceCompany.class)
					.setParameter("businessUnit", businessUnit.trim().toUpperCase())
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0).getLegacyRegion();
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
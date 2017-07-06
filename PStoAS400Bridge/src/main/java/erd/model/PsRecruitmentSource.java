package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the PS_HRS_SOURCE_I database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_HRS_SOURCE_I")
@NamedQuery(name="PsRecruitmentSource.findAll", query="SELECT p FROM PsRecruitmentSource p")
public class PsRecruitmentSource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HRS_SOURCE_ID", nullable=false, precision=15)
	private BigDecimal sourceId;

	@Column(name="EFF_STATUS", length=1)
	private String status;

	@Column(name="EFFDT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="HRS_SOURCE_DESCR", nullable=false, length=254)
	private String sourceDescription;

	@Column(name="HRS_SOURCE_NAME", nullable=false, length=30)
	private String sourceName;

	@Column(name="HRS_SOURCE_STATUS")
	@Temporal(TemporalType.DATE)
	private Date sourceStatusDate;

	@Column(name="HRS_SOURCE_TYPE", nullable=false, length=3)
	private String sourceTypeCode;

	public PsRecruitmentSource() {
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getSourceDescription() {
		return this.sourceDescription != null ? this.sourceDescription.trim() : this.sourceDescription;
	}

	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}

	public BigDecimal getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(BigDecimal sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return this.sourceName != null ? this.sourceName.trim() : this.sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Date getSourceStatusDate() {
		return this.sourceStatusDate;
	}

	public void setSourceStatusDate(Date sourceStatusDate) {
		this.sourceStatusDate = sourceStatusDate;
	}

	public String getSourceTypeCode() {
		return this.sourceTypeCode;
	}

	public void setSourceTypeCode(String sourceTypeCode) {
		this.sourceTypeCode = sourceTypeCode;
	}

	public static PsRecruitmentSource HR01GetPersonalData(String employeeId) {
//	!----------------------------------------------------------------------
//	! Procedure:  HR01-Get-Personal-Data
//	! Desc:  Gets the employees data from the personal data effdt table that
//	!        needs to be interfaced to the legacy system.
//	!        All this data used to come from pers_data_effdt.
//	!----------------------------------------------------------------------
//	Begin-Procedure HR01-Get-Personal-Data
//	Begin-Select
//	CPDE.MAR_STATUS
//	CPDE.SEX            !Z_MOD_SUFFIX_GENDER_BD
//	     Let $PSMaritalStatus = ltrim(rtrim(&CPDE.MAR_STATUS,' '),' ')
//	     Let $PSSex = ltrim(rtrim(&CPDE.SEX,' '),' ')                 !Z_MOD_SUFFIX_GENDER_BD
//	from  PS_PERS_DATA_EFFDT CPDE
//	where CPDE.EMPLID = $Wrk_Emplid
//	  and CPDE.EFFDT = (SELECT MAX(EFFDT)
//	                      FROM PS_PERS_DATA_EFFDT CPDE2
//	                     WHERE CPDE2.EMPLID = CPDE.EMPLID
//	                       AND  to_char(CPDE2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//	End-Select
//	Begin-Select                                    !Z_MOD_SUFFIX_GENDER_BD
//	to_char(PPD.BIRTHDATE, 'YYYY-MM-DD')            &PPDBIRTHDATE
//	    Let $PSBirthdate = &PPDBIRTHDATE
//	from PS_PERSON PPD
//	where PPD.EMPLID = $Wrk_Emplid
//	End-Select                                      !Z_MOD_SUFFIX_GENDER_BD
//	Begin-Select
//	!CPD.SEX                                                  !Z_MOD_SUFFIX_GENDER_BD
//	!    Let $PSSex = ltrim(rtrim(&CPD.SEX,' '),' ')
//	!to_char(CPD.BIRTHDATE, 'YYYY-MM-DD')            &CPDBIRTHDATE
//	   ! Let $PSBirthdate = &CPDBIRTHDATE                     !Z_MOD_SUFFIX_GENDER_BD
//	CPD.LANG_CD   &CPD.Lang_Cd
//	    Let $Wrk_AD_PersDataBuild = 'Y'
//	    Let $PSLangCd = RTRIM(LTRIM(&CPD.Lang_Cd,' '),' ')
//	from PS_PERSONAL_DATA CPD
//	where CPD.EMPLID = $Wrk_Emplid
//	End-Select
//	Do HR01-get-location-country        !Get the country used to get the national id  
//	Begin-Select  !added for Defect 981
//	CPNID.NATIONAL_ID
//	    Let $PSNid = ltrim(rtrim(&CPNID.NATIONAL_ID,' '),' ')
//	CPNID.NATIONAL_ID_TYPE
//	    Let $PSNidType = ltrim(rtrim(&CPNID.NATIONAL_ID_TYPE,' '),' ')
//	from PS_PERS_NID CPNID
//	where CPNID.EMPLID = $Wrk_Emplid
//	  and CPNID.COUNTRY = $PS_REG_REGION    !dshen 01/11/2012 replace $PSloc_country with PS_REG_REGION
//	  and CPNID.primary_nid='Y'
//	End-Select
//	DO GET-2CHAR-COUNTRY
//	Begin-Select
//	CPAI.HRS_SOURCE_ID
//	CHSI.HRS_SOURCE_NAME
//	CHSI.HRS_SOURCE_DESCR
//	    Let $PSReferralSource = ltrim(rtrim(&CHSI.HRS_SOURCE_NAME,' '),' ')
//	    Let $PSRefSourceDescr = ltrim(rtrim(&CHSI.HRS_SOURCE_DESCR,' '),' ')
//	from PS_PERS_APPL_REF CPAI,
//	     PS_HRS_SOURCE_I CHSI
//	  where CPAI.EMPLID = $Wrk_Emplid
//	    and CPAI.EFFDT = (SELECT MAX(CPAI2.EFFDT)
//	                        FROM PS_PERS_APPL_REF CPAI2
//	                          WHERE CPAI2.EMPLID = CPAI.EMPLID
//	                            AND to_char(CPAI2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//	    and CHSI.HRS_SOURCE_ID = CPAI.HRS_SOURCE_ID
//	End-Select
//	Begin-Select
//	CN.NAME
//	    Let $PSName = ltrim(rtrim(&CN.NAME,' '),' ')
//	CN.MIDDLE_NAME
//	    Let $PSMiddleName = ltrim(rtrim(&CN.MIDDLE_NAME,' '),' ')
//	CN.FIRST_NAME
//	    Let $PSFirstName = ltrim(rtrim(&CN.FIRST_NAME,' '),' ')
//	CN.LAST_NAME
//	    Let $PSLastName = ltrim(rtrim(&CN.LAST_NAME,' '),' ')
//	CN.NAME_SUFFIX                                              !Z_MOD_SUFFIX_GENDER_BD
//	CN.NAME_PREFIX
//	    Let $PSNamePrefix = ltrim(rtrim(&CN.NAME_PREFIX,' '),' ')
//	    Let $PSNamePrefix = SUBSTR($PSNamePrefix,1,3)
//	    UPPERCASE $PSNamePrefix
//	 If &CN.NAME_SUFFIX <> ' '                                  !Z_MOD_SUFFIX_GENDER_BD
//	    Let $PSSuffix =   RTRIM(LTRIM(&CN.NAME_SUFFIX,' '),' ')  !Z_MOD_SUFFIX_GENDER_BD
//	    Let $PSLastName = $PSLastName || ' ' || $PSSuffix        !Z_MOD_SUFFIX_GENDER_BD
//	 End-if    !&CN.Name_Suffix <> ' '                          !Z_MOD_SUFFIX_GENDER_BD
//	    Let $ADPSLastName   = RTRIM(LTRIM(&CN.Last_Name,' '),' ')
//	    Let $ADPSFirstName  = RTRIM(LTRIM(&CN.First_Name,' '),' ')
//	    Let $ADPSMiddleName = RTRIM(LTRIM(&CN.Middle_Name,' '),' ')
//	    Let $ADPSMiddleName = SUBSTR($ADPSMiddleName,1,1)
//	    Let $Wrk_AD_PersDataEffdtBuild = 'Y'
//	from  PS_NAMES CN
//	where CN.EMPLID = $Wrk_Emplid
//	and CN.NAME_TYPE = 'PRI'
//	and CN.EFFDT     = (SELECT MAX(EFFDT) FROM PS_NAMES CN2
//	                      WHERE CN2.EMPLID   = CN.EMPLID
//	                      AND   CN2.NAME_TYPE  = CN.NAME_TYPE
//	                      AND   to_char(CN2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//	End-Select
//	Begin-Select
//	CNAME.FIRST_NAME
//	    Let $PSPreferredName = ltrim(rtrim(&CNAME.FIRST_NAME,' '),' ')
//	from  PS_NAMES CNAME
//	where CNAME.EMPLID = $Wrk_Emplid
//	and CNAME.NAME_TYPE = 'PRF'
//	and CNAME.EFFDT     = (SELECT MAX(EFFDT) FROM PS_NAMES CNAME2
//	                      WHERE CNAME2.EMPLID   = CNAME.EMPLID
//	                      AND   CNAME2.NAME_TYPE  = CNAME.NAME_TYPE
//	                      AND   to_char(CNAME2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//	End-Select
//	Begin-Select
//	CAD.ADDRESS1
//	    Let $PSAddress1 = ltrim(rtrim(&CAD.ADDRESS1,' '),' ')
//	CAD.CITY
//	    Let $PSCity = ltrim(rtrim(&CAD.CITY,' '),' ')
//	CAD.STATE
//	    Let $PSState = ltrim(rtrim(&CAD.STATE,' '),' ')
//	CAD.POSTAL
//	    Let $PSPostal = ltrim(rtrim(&CAD.POSTAL,' '),' ')
//	CAD.COUNTY
//	    Let $PSCounty = ltrim(rtrim(&CAD.COUNTY,' '),' ')
//	from PS_ADDRESSES CAD
//	where CAD.EMPLID = $Wrk_Emplid
//	  and CAD.ADDRESS_TYPE = 'HOME'
//	  and CAD.EFFDT    = (SELECT MAX(EFFDT) FROM PS_ADDRESSES CAD2
//	                      WHERE CAD2.EMPLID   = CAD.EMPLID
//	                      AND   CAD2.ADDRESS_TYPE  = CAD.ADDRESS_TYPE
//	                      AND   to_char(CAD2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//	End-Select
//	End-Procedure HR01-Get-Personal-Data
	return null;
}

	public static PsRecruitmentSource HR05GetReferralSource(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR05-Get-Referral-Source
//		! Desc:  This routine will get the referral source data for each of the
//		!        employee numbers entered in the trigger file since it was removed from
//		!        the Personal Data table to the Pers Appl Info table in 8.3.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR05-Get-Referral-Source
//		begin-select
//		CPAI3.HRS_SOURCE_ID
//		CHSI2.HRS_SOURCE_NAME
//		CHSI2.HRS_SOURCE_DESCR
//		    Let $PSRecruit_Source_Code = ltrim(rtrim(&CHSI2.HRS_SOURCE_NAME,' '),' ')
//		    Do HR05-format-referral-source
//		    Let $PSReferralSource = ltrim(rtrim(&CHSI2.HRS_SOURCE_NAME,' '),' ')
//		    Let $PSRefSourceDescr = ltrim(rtrim(&CHSI2.HRS_SOURCE_DESCR,' '),' ')
//		from PS_PERS_APPL_REF CPAI3,
//		     PS_HRS_SOURCE_I CHSI2
//		  where CPAI3.EMPLID = $Wrk_Emplid
//		    and CPAI3.EFFDT = (SELECT MAX(CPAI3A.EFFDT)
//		                        FROM PS_PERS_APPL_REF CPAI3A
//		                          WHERE CPAI3A.EMPLID = CPAI3.EMPLID
//		                            AND to_char(CPAI3A.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		    and CHSI2.HRS_SOURCE_ID = CPAI3.HRS_SOURCE_ID
//		end-select
//		End-Procedure HR05-Get-Referral-Source
		return null;
	}

	/**
	 * @see HR05-Get-Referral-Source
	 * @param recruitmentSourceId
	 * @return
	 */
	public static PsRecruitmentSource findByRecruitmentSourceId(String sourceId) {
		//BEGIN-SELECT
		//CHSI2.HRS_SOURCE_NAME                                                               ! ALS-10/08/2008
		//CHSI2.HRS_SOURCE_DESCR                                                              ! ALS-10/08/2008
		//FROM PS_HRS_SOURCE_I CHSI2                                                          ! ALS-10/08/2008
		//WHERE CHSI2.HRS_SOURCE_ID = CPAI3.HRS_SOURCE_ID                                   ! ALS-10/08/2008
		//END-SELECT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsRecruitmentSource> resultList = em.createQuery(
	    			"SELECT p FROM PsRecruitmentSource p "
	    					+ "WHERE UPPER(TRIM(p.sourceId)) = :sourceId ",
	    					PsRecruitmentSource.class)
	    		    .setParameter("sourceId", sourceId.trim().toUpperCase())
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
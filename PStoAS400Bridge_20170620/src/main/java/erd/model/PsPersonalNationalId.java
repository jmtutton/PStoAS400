package erd.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The persistent class for the PS_PERS_NID database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_PERS_NID")
@NamedQuery(name="PsPersonalNationalId.findAll", query="SELECT p FROM PsPersonalNationalId p")
public class PsPersonalNationalId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="COUNTRY", nullable=false, length=3)
	private String countryIsoAlpha3Code;

	@Column(name="LASTUPDDTTM", nullable=false)
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="NATIONAL_ID", nullable=false, length=20)
	private String nationalId;

	@Column(name="NATIONAL_ID_TYPE", nullable=false, length=6)
	private String nationalIdType;

	@Column(name="PRIMARY_NID", nullable=false, length=1)
	private String isPrimaryId;

	@Column(name="SSN_KEY_FRA", nullable=false, length=2)
	private String socialSecurityNumberKey;

	@Column(name="TAX_REF_ID_SGP", nullable=false, length=1)
	private String isNationalRegistrationIdUsedAsTaxReferenceNumber;

	public PsPersonalNationalId() {
	}

	public String getCountryCode() {
		return this.countryIsoAlpha3Code;
	}

	public void setCountryCode(String countryIsoAlpha3Code) {
		this.countryIsoAlpha3Code = countryIsoAlpha3Code;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Timestamp getLastUpdatedDateAndTime() {
		return this.lastUpdatedDateAndTime;
	}

	public void setLastUpdatedDateAndTime(Timestamp lastUpdatedDateAndTime) {
		this.lastUpdatedDateAndTime = lastUpdatedDateAndTime;
	}

	public String getLastUpdatedUserId() {
		return this.lastUpdatedUserId;
	}

	public void setLastUpdatedUserId(String lastUpdatedUserId) {
		this.lastUpdatedUserId = lastUpdatedUserId;
	}

	public String getNationalId() {
		return this.nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getNationalIdType() {
		return this.nationalIdType;
	}

	public void setNationalIdType(String nationalIdType) {
		this.nationalIdType = nationalIdType;
	}

	public String getIsPrimaryId() {
		return this.isPrimaryId;
	}

	public void setIsPrimaryId(String isPrimaryId) {
		this.isPrimaryId = isPrimaryId;
	}

	public String getSocialSecurityNumberKey() {
		return this.socialSecurityNumberKey;
	}

	public void setSocialSecurityNumberKey(String socialSecurityNumberKey) {
		this.socialSecurityNumberKey = socialSecurityNumberKey;
	}

	public String getIsNationalRegistrationIdUsedAsTaxReferenceNumber() {
		return this.isNationalRegistrationIdUsedAsTaxReferenceNumber;
	}

	public void setIsNationalRegistrationIdUsedAsTaxReferenceNumber(String isNationalRegistrationIdUsedAsTaxReferenceNumber) {
		this.isNationalRegistrationIdUsedAsTaxReferenceNumber = isNationalRegistrationIdUsedAsTaxReferenceNumber;
	}

	public PsPersonalNationalId HR01GetPersonalData(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR01-Get-Personal-Data
//		! Desc:  Gets the employees data from the personal data effdt table that
//		!        needs to be interfaced to the legacy system.
//		!        All this data used to come from pers_data_effdt.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR01-Get-Personal-Data
//		Begin-Select
//		CPDE.MAR_STATUS
//		CPDE.SEX            !Z_MOD_SUFFIX_GENDER_BD
//		     Let $PSMaritalStatus = ltrim(rtrim(&CPDE.MAR_STATUS,' '),' ')
//		     Let $PSSex = ltrim(rtrim(&CPDE.SEX,' '),' ')                 !Z_MOD_SUFFIX_GENDER_BD
//		from  PS_PERS_DATA_EFFDT CPDE
//		where CPDE.EMPLID = $Wrk_Emplid
//		  and CPDE.EFFDT = (SELECT MAX(EFFDT)
//		                      FROM PS_PERS_DATA_EFFDT CPDE2
//		                     WHERE CPDE2.EMPLID = CPDE.EMPLID
//		                       AND  to_char(CPDE2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		End-Select
//		Begin-Select                                    !Z_MOD_SUFFIX_GENDER_BD
//		to_char(PPD.BIRTHDATE, 'YYYY-MM-DD')            &PPDBIRTHDATE
//		    Let $PSBirthdate = &PPDBIRTHDATE
//		from PS_PERSON PPD
//		where PPD.EMPLID = $Wrk_Emplid
//		End-Select                                      !Z_MOD_SUFFIX_GENDER_BD
//		Begin-Select
//		!CPD.SEX                                                  !Z_MOD_SUFFIX_GENDER_BD
//		!    Let $PSSex = ltrim(rtrim(&CPD.SEX,' '),' ')
//		!to_char(CPD.BIRTHDATE, 'YYYY-MM-DD')            &CPDBIRTHDATE
//		   ! Let $PSBirthdate = &CPDBIRTHDATE                     !Z_MOD_SUFFIX_GENDER_BD
//		CPD.LANG_CD   &CPD.Lang_Cd
//		    Let $Wrk_AD_PersDataBuild = 'Y'
//		    Let $PSLangCd = RTRIM(LTRIM(&CPD.Lang_Cd,' '),' ')
//		from PS_PERSONAL_DATA CPD
//		where CPD.EMPLID = $Wrk_Emplid
//		End-Select
//		Do HR01-get-location-country        !Get the country used to get the national id  
//		Begin-Select  !added for Defect 981
//		CPNID.NATIONAL_ID
//		    Let $PSNid = ltrim(rtrim(&CPNID.NATIONAL_ID,' '),' ')
//		CPNID.NATIONAL_ID_TYPE
//		    Let $PSNidType = ltrim(rtrim(&CPNID.NATIONAL_ID_TYPE,' '),' ')
//		from PS_PERS_NID CPNID
//		where CPNID.EMPLID = $Wrk_Emplid
//		  and CPNID.COUNTRY = $PS_REG_REGION    !dshen 01/11/2012 replace $PSloc_country with PS_REG_REGION
//		  and CPNID.primary_nid='Y'
//		End-Select
//		DO GET-2CHAR-COUNTRY
//		Begin-Select
//		CPAI.HRS_SOURCE_ID
//		CHSI.HRS_SOURCE_NAME
//		CHSI.HRS_SOURCE_DESCR
//		    Let $PSReferralSource = ltrim(rtrim(&CHSI.HRS_SOURCE_NAME,' '),' ')
//		    Let $PSRefSourceDescr = ltrim(rtrim(&CHSI.HRS_SOURCE_DESCR,' '),' ')
//		from PS_PERS_APPL_REF CPAI,
//		     PS_HRS_SOURCE_I CHSI
//		  where CPAI.EMPLID = $Wrk_Emplid
//		    and CPAI.EFFDT = (SELECT MAX(CPAI2.EFFDT)
//		                        FROM PS_PERS_APPL_REF CPAI2
//		                          WHERE CPAI2.EMPLID = CPAI.EMPLID
//		                            AND to_char(CPAI2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		    and CHSI.HRS_SOURCE_ID = CPAI.HRS_SOURCE_ID
//		End-Select
//		Begin-Select
//		CN.NAME
//		    Let $PSName = ltrim(rtrim(&CN.NAME,' '),' ')
//		CN.MIDDLE_NAME
//		    Let $PSMiddleName = ltrim(rtrim(&CN.MIDDLE_NAME,' '),' ')
//		CN.FIRST_NAME
//		    Let $PSFirstName = ltrim(rtrim(&CN.FIRST_NAME,' '),' ')
//		CN.LAST_NAME
//		    Let $PSLastName = ltrim(rtrim(&CN.LAST_NAME,' '),' ')
//		CN.NAME_SUFFIX                                              !Z_MOD_SUFFIX_GENDER_BD
//		CN.NAME_PREFIX
//		    Let $PSNamePrefix = ltrim(rtrim(&CN.NAME_PREFIX,' '),' ')
//		    Let $PSNamePrefix = SUBSTR($PSNamePrefix,1,3)
//		    UPPERCASE $PSNamePrefix
//		 If &CN.NAME_SUFFIX <> ' '                                  !Z_MOD_SUFFIX_GENDER_BD
//		    Let $PSSuffix =   RTRIM(LTRIM(&CN.NAME_SUFFIX,' '),' ')  !Z_MOD_SUFFIX_GENDER_BD
//		    Let $PSLastName = $PSLastName || ' ' || $PSSuffix        !Z_MOD_SUFFIX_GENDER_BD
//		 End-if    !&CN.Name_Suffix <> ' '                          !Z_MOD_SUFFIX_GENDER_BD
//		    Let $ADPSLastName   = RTRIM(LTRIM(&CN.Last_Name,' '),' ')
//		    Let $ADPSFirstName  = RTRIM(LTRIM(&CN.First_Name,' '),' ')
//		    Let $ADPSMiddleName = RTRIM(LTRIM(&CN.Middle_Name,' '),' ')
//		    Let $ADPSMiddleName = SUBSTR($ADPSMiddleName,1,1)
//		    Let $Wrk_AD_PersDataEffdtBuild = 'Y'
//		from  PS_NAMES CN
//		where CN.EMPLID = $Wrk_Emplid
//		and CN.NAME_TYPE = 'PRI'
//		and CN.EFFDT     = (SELECT MAX(EFFDT) FROM PS_NAMES CN2
//		                      WHERE CN2.EMPLID   = CN.EMPLID
//		                      AND   CN2.NAME_TYPE  = CN.NAME_TYPE
//		                      AND   to_char(CN2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		End-Select
//		Begin-Select
//		CNAME.FIRST_NAME
//		    Let $PSPreferredName = ltrim(rtrim(&CNAME.FIRST_NAME,' '),' ')
//		from  PS_NAMES CNAME
//		where CNAME.EMPLID = $Wrk_Emplid
//		and CNAME.NAME_TYPE = 'PRF'
//		and CNAME.EFFDT     = (SELECT MAX(EFFDT) FROM PS_NAMES CNAME2
//		                      WHERE CNAME2.EMPLID   = CNAME.EMPLID
//		                      AND   CNAME2.NAME_TYPE  = CNAME.NAME_TYPE
//		                      AND   to_char(CNAME2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		End-Select
//		Begin-Select
//		CAD.ADDRESS1
//		    Let $PSAddress1 = ltrim(rtrim(&CAD.ADDRESS1,' '),' ')
//		CAD.CITY
//		    Let $PSCity = ltrim(rtrim(&CAD.CITY,' '),' ')
//		CAD.STATE
//		    Let $PSState = ltrim(rtrim(&CAD.STATE,' '),' ')
//		CAD.POSTAL
//		    Let $PSPostal = ltrim(rtrim(&CAD.POSTAL,' '),' ')
//		CAD.COUNTY
//		    Let $PSCounty = ltrim(rtrim(&CAD.COUNTY,' '),' ')
//		from PS_ADDRESSES CAD
//		where CAD.EMPLID = $Wrk_Emplid
//		  and CAD.ADDRESS_TYPE = 'HOME'
//		  and CAD.EFFDT    = (SELECT MAX(EFFDT) FROM PS_ADDRESSES CAD2
//		                      WHERE CAD2.EMPLID   = CAD.EMPLID
//		                      AND   CAD2.ADDRESS_TYPE  = CAD.ADDRESS_TYPE
//		                      AND   to_char(CAD2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		End-Select
//		End-Procedure HR01-Get-Personal-Data
		return null;
	}

	public PsPersonalNationalId findByEmployeeIdHR05(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR05-Get-Pers-Nid
//		! Desc:  This routine gets the social security number from the Peoplesoft
//		!        tables and formats them for the legacy system
//		!----------------------------------------------------------------------
//		Begin-Procedure HR05-Get-Pers-Nid
//		begin-select
//		CPN.National_Id
//		    Let $PSNATIONAL_ID = LTRIM(RTRIM(&CPN.National_Id,' '),' ')
//		from PS_Pers_Nid CPN
//		where CPN.Emplid = $PSEmplid
//		  and CPN.Country = $PS_REG_REGION    !dshen 01/12/2012 replace $PSLOC_COUNTRY with PS_REG_REGION
//		  and CPN.PRIMARY_NID='Y'
//		end-select
//		End-Procedure HR05-Get-Pers-Nid		
		return null;
	}

	public PsPersonalNationalId HR09GetPersonalData(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR09-Get-Personal-Data
//		! Desc:  Gets the employees National ID from the PERS_NID table
//		!----------------------------------------------------------------------
//		Begin-Procedure HR09-Get-Personal-Data
//		Begin-Select
//		CPNID3.NATIONAL_ID
//		    let $PSNid = ltrim(rtrim(&CPNID3.NATIONAL_ID,' '),' ')    !Remove leading and trailing blanks
//		from PS_PERS_NID CPNID3
//		where CPNID3.EMPLID = $Wrk_Emplid
//		and CPNID3.COUNTRY =  $PS_REG_REGION    ! dshen 01/12/2012 replace $PSLOC_COUNTRY with $PS_REG_REGION
//		and CPNID3.PRIMARY_NID='Y'
//		End-Select
//		End-Procedure HR09-Get-Personal-Data
		return null;
	}


}
package erd.model;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The persistent class for the PS_PERS_DATA_EFFDT database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_PERS_DATA_EFFDT")
@NamedQuery(name="PsEffectiveDatedPersonalData.findAll", query="SELECT p FROM PsEffectiveDatedPersonalData p")
public class PsEffectiveDatedPersonalData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="ALTER_EMPLID", nullable=false, length=11)
	private String alternateEmployeeId;

	@Column(name="EFFDT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="FT_STUDENT", nullable=false, length=1)
	private String isFulltimeStudent;

	@Column(name="HIGHEST_EDUC_LVL", nullable=false, length=2)
	private String highestEducationLevel;

	@Column(name="LANG_CD", nullable=false, length=3)
	private String languageCode;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="MAR_STATUS", nullable=false, length=1)
	private String maritalStatus;

	@Column(name="MAR_STATUS_DT")
	@Temporal(TemporalType.DATE)
	private Date maritalStatusDate;

	@Column(name="SEX", nullable=false, length=1)
	private String gender;

	public PsEffectiveDatedPersonalData() {
	}

	public String getAlternateEmployeeId() {
		return this.alternateEmployeeId;
	}

	public void setAlternateEmployeeId(String alternateEmployeeId) {
		this.alternateEmployeeId = alternateEmployeeId;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getIsFulltimeStudent() {
		return this.isFulltimeStudent;
	}

	public void setIsFulltimeStudent(String isFulltimeStudent) {
		this.isFulltimeStudent = isFulltimeStudent;
	}

	public String getHighestEducationLevel() {
		return this.highestEducationLevel;
	}

	public void setHighestEducationLevel(String highestEducationLevel) {
		this.highestEducationLevel = highestEducationLevel;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
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

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Date getMaritalStatusDate() {
		return this.maritalStatusDate;
	}

	public void setMaritalStatusDate(Date maritalStatusDate) {
		this.maritalStatusDate = maritalStatusDate;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String findGenderByEmployeeIdAndEffectiveDate(String employeeId, Date effectiveDate) {
		return null;
	}

	public PsEffectiveDatedPersonalData GetPersonalData205(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR205-Get-Personal-Data
//		! Desc:  This routine will get the Personal Data row for each of the
//		!        employee numbers entered in the trigger file.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR205-Get-Personal-Data
//		Begin-Select  
//		PER5.SEX            
//		     Let $PSGender = ltrim(rtrim(&PER5.Sex,' '),' ') 
//		     IF  $PSGender = 'U' 
//		      LET  $PSGender = ''
//		     END-IF            
//		from  PS_PERS_DATA_EFFDT PER5
//		where PER5.EMPLID = $Wrk_Emplid
//		  and PER5.EFFDT = (SELECT MAX(PER52.EFFDT)
//		                      FROM PS_PERS_DATA_EFFDT PER52
//		                     WHERE PER52.EMPLID = PER5.EMPLID
//		                       AND  to_char(PER52.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		End-Select
//		Begin-Select  
//		N5.MIDDLE_NAME
//		    Let $PSMName = ltrim(rtrim(&N5.MIDDLE_NAME,' '),' ')
//		N5.FIRST_NAME
//		    Let $PSFName = ltrim(rtrim(&N5.FIRST_NAME,' '),' ')
//		N5.LAST_NAME
//		    Let $PSLName = ltrim(rtrim(&N5.LAST_NAME,' '),' ')
//		from  PS_NAMES N5
//		where N5.EMPLID = $Wrk_Emplid
//		and N5.NAME_TYPE = 'PRI'
//		and N5.EFFDT     = (SELECT MAX(N52.EFFDT) FROM PS_NAMES N52
//		                      WHERE N52.EMPLID   = N5.EMPLID
//		                      AND   N52.NAME_TYPE  = N5.NAME_TYPE
//		                      AND   to_char(N52.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		End-Select
//		End-Procedure HR205-Get-Personal-Data
		return null;
	}

	public PsEffectiveDatedPersonalData GetPersonalData201(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR201-Get-Personal-Data
//		! Desc:  Gets the employees data from the personal data effdt table that
//		!        needs to be interfaced to the legacy system.
//		!        All this data used to come from pers_data_effdt.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR201-Get-Personal-Data
//		Begin-Select  
//		PER1.SEX            
//		     Let $PSSex = ltrim(rtrim(&PER1.SEX,' '),' ') 
//		     IF  $PSSex = 'U' 
//		      LET  $PSSex = ''
//		     END-IF            
//		from  PS_PERS_DATA_EFFDT PER1
//		where PER1.EMPLID = $Wrk_Emplid
//		  and PER1.EFFDT = (SELECT MAX(PER12.EFFDT)
//		                      FROM PS_PERS_DATA_EFFDT PER12
//		                     WHERE PER12.EMPLID = PER1.EMPLID
//		                       AND  to_char(PER12.EFFDT,'YYYY-MM-DD') <= $Wrk_Effdt)
//		End-Select 
//		Begin-Select  
//		N1.MIDDLE_NAME
//		    Let $PSMiddleName = ltrim(rtrim(&N1.MIDDLE_NAME,' '),' ')
//		N1.FIRST_NAME
//		    Let $PSFirstName = ltrim(rtrim(&N1.FIRST_NAME,' '),' ')
//		N1.LAST_NAME
//		    Let $PSLastName = ltrim(rtrim(&N1.LAST_NAME,' '),' ')
//		from  PS_NAMES N1
//		where N1.EMPLID = $Wrk_Emplid
//		and N1.NAME_TYPE = 'PRI'
//		and N1.EFFDT     = (SELECT MAX(N12.EFFDT) FROM PS_NAMES N12
//		                      WHERE N12.EMPLID   = N1.EMPLID
//		                      AND   N12.NAME_TYPE  = N1.NAME_TYPE
//		                      AND   to_char(N12.EFFDT,'YYYY-MM-DD') <= $Wrk_Effdt)
//		End-Select
//		End-Procedure HR201-Get-Personal-Data
		return null;
	}

	public PsEffectiveDatedPersonalData GetPersonalData01(String employeeId) {
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
//		Begin-Select       !added for v8.3
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

	public PsEffectiveDatedPersonalData HR05GetInfo(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR05-Get-Info
//		! Desc:  This new routine will get the name/address/marital status info row for each of the
//		!        employee numbers entered in the trigger file.  All this data used to come from
//		!        pers_data_effdt.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR05-Get-Info
//		begin-select
//		CAD3.Address1
//		CAD3.City
//		CAD3.State
//		CAD3.Postal
//		CPDE3.Mar_Status
//		to_char(CPDE3.Effdt, 'YYYY-MM-DD')    &CPDE3Effdt
//		  Let $Effdt = &CPDE3Effdt
//		  Let $PSAddress =  RTRIM(LTRIM(&CAD3.Address1,' '),' ')
//		  uppercase $PSAddress
//		  Do Replace-Character($PSAddress,'''','''''',$PSAddress)  !From ZRmvSpcChr.sqc
//		  Let $PSCity = RTRIM(LTRIM(&CAD3.City,' '),' ')
//		  uppercase $PSCity
//		  Do Replace-Character($PSCity,'''','''''',$PSCity)       !From ZRmvSpcChr.sqc
//		  Let $PSState = RTRIM(LTRIM(&CAD3.State,' '),' ')
//		  uppercase $PSState
//		  Let $PSZip = RTRIM(LTRIM(&CAD3.Postal,' '),' ')
//		  Let $PSMarital_Status = &CPDE3.Mar_Status
//		from  PS_PERS_DATA_EFFDT CPDE3,
//		      PS_ADDRESSES CAD3
//		where CPDE3.Emplid = $PSEmplid
//		  and CPDE3.Emplid = CAD3.Emplid
//		  and CAD3.ADDRESS_TYPE = 'HOME'
//		  and CAD3.EFFDT    = (SELECT MAX(EFFDT) FROM PS_ADDRESSES CAD4
//		                      WHERE CAD4.EMPLID   = CAD3.EMPLID
//		                      AND   CAD4.ADDRESS_TYPE  = CAD3.ADDRESS_TYPE
//		                      AND   to_char(CAD4.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		  and CPDE3.EFFDT = (SELECT MAX(EFFDT)
//		                      FROM PS_PERS_DATA_EFFDT CPDE4
//		                     WHERE CPDE4.EMPLID = CPDE3.EMPLID
//		                       AND  to_char(CPDE4.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		end-select
//		begin-select
//		CN3.Name
//		CN3.First_Name
//		CN3.Last_Name
//		CN3.Name_Prefix
//		CN3.Middle_Name
//		CN3.Name_Suffix   !ZHR_MOD_SUFFIX_LAST_NAME
//		     !Let $PSName = RTRIM(LTRIM(&CN3.Name,' '),' ')
//		     Do HR05-format-name
//		     Do Replace-Character($PSName,'''','''''',$PSName)               !From ZRmvSpcChr.sqc
//		     Let $PSName_Prefix = RTRIM(LTRIM(&CN3.Name_Prefix,' '),' ')
//		     uppercase $PSName_Prefix
//		     Do Replace-Character($PSName_Prefix,'''','''''',$PSName_Prefix) !From ZRmvSpcChr.sqc
//		     Let $ADPSLastName = RTRIM(LTRIM(&CN3.Last_Name,' '),' ')
//		     Let $ADPSFirstName = RTRIM(LTRIM(&CN3.First_Name,' '),' ')
//		     Let $ADPSMiddleName = RTRIM(LTRIM(&CN3.Middle_Name,' '),' ')
//		     Let $ADPSMiddleName = SUBSTR($ADPSMiddleName,1,1)
//		     Let $Wrk_AD_PersdataEffdtBuild = 'Y'
//		from  PS_NAMES CN3
//		where CN3.NAME_TYPE = 'PRI'
//		  and CN3.Emplid = $PSEmplid
//		  and CN3.EFFDT     = (SELECT MAX(EFFDT) FROM PS_NAMES CN4
//		                      WHERE CN4.EMPLID   = CN3.EMPLID
//		                      AND   CN4.NAME_TYPE  = CN3.NAME_TYPE
//		                      AND   to_char(CN4.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		end-select
//		End-Procedure HR05-Get-Info
		return null;
	}

	public PsEffectiveDatedPersonalData CheckEffdtTransaction(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  Check-Effdt-Transaction
//		!----------------------------------------------------------------------
//		Begin-Procedure Check-Effdt-Transaction
//		Let $WrkADEffdt = ''
//		IF $Wrkprocess = 'ZHRI102A'
//		  DO dtu-add-days($PSEffdt,1,$WrkADEffdt)
//		 ELSE
//		  Let $WrkADEffdt = $PSEffdt
//		END-IF
//		Let $AdFound = 'N'
//		BEGIN-SELECT
//		'XA'
//		   Let $AdFound = 'Y'
//		FROM  PS_JOB AD01
//		WHERE AD01.EMPLID = $PSEmplid
//		      AND to_char(AD01.EFFDT,'YYYY-MM-DD') > $WrkADEffdt
//		END-SELECT
//		!-----------------------------!
//		BEGIN-SELECT
//		'XB'
//		   Let $AdFound = 'Y'
//		FROM  PS_PERS_DATA_EFFDT AD02
//		WHERE AD02.EMPLID = $PSEmplid
//		    AND  to_char(AD02.EFFDT,'YYYY-MM-DD') > $Wrk_ADEffdt
//		END-SELECT
//		End-Procedure Check-Effdt-Transaction		
		return null;
	}

}
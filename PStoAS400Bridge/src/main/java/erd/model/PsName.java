package erd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The persistent class for the PS_NAMES database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_NAMES")
@NamedQuery(name="PsName.findAll", query="SELECT p FROM PsName p")
public class PsName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="COUNTRY_NM_FORMAT", nullable=false, length=3)
	private String supportedNameFormatTypes;

	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String statusAsOfEffectiveDate;

	@Column(name="EFFDT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="FIRST_NAME", nullable=false, length=30)
	private String firstName;

	@Column(name="FIRST_NAME_SRCH", nullable=false, length=30)
	private String firstNameSpecialCharactersRemoved;

	@Column(name="LAST_NAME", nullable=false, length=30)
	private String lastName;

	@Column(name="LAST_NAME_PREF_NLD", nullable=false, length=1)
	private String lastNamePreferenceCode;

	@Column(name="LAST_NAME_SRCH", nullable=false, length=30)
	private String lastNameSpecialCharactersRemoved;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="MIDDLE_NAME", nullable=false, length=30)
	private String middleName;

	@Column(name="NAME", nullable=false, length=50)
	private String name;

	@Column(name="NAME_AC", nullable=false, length=50)
	private String alternateCharacterName;

	@Column(name="NAME_DISPLAY", nullable=false, length=50)
	private String displayName;

	@Column(name="NAME_DISPLAY_SRCH", nullable=false, length=50)
	private String displayNameSpecialCharactersRemoved;

	@Column(name="NAME_FORMAL", nullable=false, length=60)
	private String formalName;

	@Column(name="NAME_INITIALS", nullable=false, length=6)
	private String nameInitials;

	@Column(name="NAME_PREFIX", nullable=false, length=4)
	private String namePrefix;

	@Column(name="NAME_ROYAL_PREFIX", nullable=false, length=15)
	private String nameRoyalPrefix;

	@Column(name="NAME_ROYAL_SUFFIX", nullable=false, length=15)
	private String nameRoyalSuffix;

	@Column(name="NAME_SUFFIX", nullable=false, length=15)
	private String nameSuffix;

	@Column(name="NAME_TITLE", nullable=false, length=30)
	private String nameTitle;

	@Column(name="NAME_TYPE", nullable=false, length=3)
	private String nameType;

	@Column(name="PARTNER_LAST_NAME", nullable=false, length=30)
	private String partnerLastName;

	@Column(name="PARTNER_ROY_PREFIX", nullable=false, length=15)
	private String partnerRoyalPrefix;

	@Column(name="PREF_FIRST_NAME", nullable=false, length=30)
	private String preferredFirstName;

	@Column(name="SECOND_LAST_NAME", nullable=false, length=30)
	private String secondLastName;

	@Column(name="SECOND_LAST_SRCH", nullable=false, length=30)
	private String secondLastNameSpecialCharactersRemoved;

	public PsName() {
	}

	public String getSupportedNameFormatTypes() {
		return this.supportedNameFormatTypes;
	}

	public void setSupportedNameFormatTypes(String supportedNameFormatTypes) {
		this.supportedNameFormatTypes = supportedNameFormatTypes;
	}

	public String getStatusAsOfEffectiveDate() {
		return this.statusAsOfEffectiveDate;
	}

	public void setStatusAsOfEffectiveDate(String statusAsOfEffectiveDate) {
		this.statusAsOfEffectiveDate = statusAsOfEffectiveDate;
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstNameSpecialCharactersRemoved() {
		return this.firstNameSpecialCharactersRemoved;
	}

	public void setFirstNameSpecialCharactersRemoved(String firstNameSpecialCharactersRemoved) {
		this.firstNameSpecialCharactersRemoved = firstNameSpecialCharactersRemoved;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastNamePrefNld() {
		return this.lastNamePreferenceCode;
	}

	public void setLastNamePrefNld(String lastNamePreferenceCode) {
		this.lastNamePreferenceCode = lastNamePreferenceCode;
	}

	public String getLastNameSrch() {
		return this.lastNameSpecialCharactersRemoved;
	}

	public void setLastNameSrch(String lastNameSpecialCharactersRemoved) {
		this.lastNameSpecialCharactersRemoved = lastNameSpecialCharactersRemoved;
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

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAc() {
		return this.alternateCharacterName;
	}

	public void setNameAc(String alternateCharacterName) {
		this.alternateCharacterName = alternateCharacterName;
	}

	public String getNameDisplay() {
		return this.displayName;
	}

	public void setNameDisplay(String displayName) {
		this.displayName = displayName;
	}

	public String getNameDisplaySrch() {
		return this.displayNameSpecialCharactersRemoved;
	}

	public void setNameDisplaySrch(String displayNameSpecialCharactersRemoved) {
		this.displayNameSpecialCharactersRemoved = displayNameSpecialCharactersRemoved;
	}

	public String getNameFormal() {
		return this.formalName;
	}

	public void setNameFormal(String formalName) {
		this.formalName = formalName;
	}

	public String getNameInitials() {
		return this.nameInitials;
	}

	public void setNameInitials(String nameInitials) {
		this.nameInitials = nameInitials;
	}

	public String getNamePrefix() {
		return this.namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getNameRoyalPrefix() {
		return this.nameRoyalPrefix;
	}

	public void setNameRoyalPrefix(String nameRoyalPrefix) {
		this.nameRoyalPrefix = nameRoyalPrefix;
	}

	public String getNameRoyalSuffix() {
		return this.nameRoyalSuffix;
	}

	public void setNameRoyalSuffix(String nameRoyalSuffix) {
		this.nameRoyalSuffix = nameRoyalSuffix;
	}

	public String getNameSuffix() {
		return this.nameSuffix;
	}

	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	public String getNameTitle() {
		return this.nameTitle;
	}

	public void setNameTitle(String nameTitle) {
		this.nameTitle = nameTitle;
	}

	public String getNameType() {
		return this.nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	public String getPartnerLastName() {
		return this.partnerLastName;
	}

	public void setPartnerLastName(String partnerLastName) {
		this.partnerLastName = partnerLastName;
	}

	public String getPartnerRoyPrefix() {
		return this.partnerRoyalPrefix;
	}

	public void setPartnerRoyPrefix(String partnerRoyalPrefix) {
		this.partnerRoyalPrefix = partnerRoyalPrefix;
	}

	public String getPrefFirstName() {
		return this.preferredFirstName;
	}

	public void setPrefFirstName(String preferredFirstName) {
		this.preferredFirstName = preferredFirstName;
	}

	public String getSecondLastName() {
		return this.secondLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	public String getSecondLastSrch() {
		return this.secondLastNameSpecialCharactersRemoved;
	}

	public void setSecondLastSrch(String secondLastNameSpecialCharactersRemoved) {
		this.secondLastNameSpecialCharactersRemoved = secondLastNameSpecialCharactersRemoved;
	}

	

	/**
	 * @see HR05-Get-Names-Table in ZHRI105A.SQC
	 * @param employeeId
	 * @param nameType
	 * @param effectiveDate
	 * @return PsName record
	 */
	public static PsName findByEmployeeIdAndNameTypeAndEffectiveDate(String employeeId, String nameType, Date effectiveDate) {
		//BEGIN-SELECT
		//FROM PS_Names CN5
		//WHERE CN5.Emplid = $PSEmplid
		//AND CN5.NAME_TYPE = 'PRF'
		//AND CN5.EFFDT = 
		//		(SELECT MAX(EFFDT) FROM PS_Names CN6
		//			WHERE CN6.EMPLID = CN5.EMPLID
		//       		AND CN6.NAME_TYPE = CN5.NAME_TYPE
		//           	AND TO_CHAR(CN6.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
		//END-SELECT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<PsName> resultList = em.createQuery(
					"SELECT PsName FROM PsName p "
					+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
						+ "AND UPPER(TRIM(p.nameType)) = :nameType "
						+ "AND p.effectiveDate = "
							+ "(SELECT MAX(p2.effectiveDate) FROM PsName p2 "
								+ "WHERE UPPER(TRIM(p2.employeeId)) = UPPER(TRIM(p.employeeId)) "
									+ "AND UPPER(TRIM(p2.nameType)) = UPPER(TRIM(p.nameType)) "
									+ "AND p2.effectiveDate <= :effectiveDate) "
					, PsName.class)
					.setParameter("employeeId", employeeId.trim().toUpperCase())
					.setParameter("nameType", nameType.trim().toUpperCase())
					.setParameter("effectiveDate", effectiveDate, TemporalType.DATE)
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

	
	public static PsName findByEmployeeId205(String employeeId) {
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

	public static PsName findByEmployeeId201(String employeeId) {
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

	public static PsName findByEmployeeId01(String employeeId) {
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
//		  and CPNID.COUNTRY = $PS_REG_REGION     !dshen 01/11/2012 replace $PSloc_country with PS_REG_REGION
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

	/**
	 * This routine Gets the nickname for employee and formats it in the form acceptable to the legacy system.
	 * @see HR05-Get-Names-Table in ZHRI105A.SQC
	 * @param employeeId
	 * @return nickname
	 */
	public static String findNicknameByEmployeeIdAndEffectiveDate(String employeeId, Date effectiveDate) {
//		//Begin-Procedure HR05-Get-Names-Table
//		//Let $Found = 'N'
//		//Let $PSNickname = ' '  !Initialize the nickname field to blanks
//		//begin-select
//		//CN5.Name_Type
//		//CN5.FIRST_NAME            !changed for v8.3
//		//Let $PSNickname = ltrim(rtrim(&CN5.FIRST_NAME,' '),' ') !changed for v8.3
//		//Let $PSPrfName = $PSNickname                                 !sree-UAAMOD
//		//Let $PSNickname = substr($PSNickName,1,20)
//		//uppercase $PSNickName
//		//Do Replace-Character($PSNickName,'''','''''',$PSNickName)   !From ZRmvSpcChr.sqc
//		//Let $Found = 'Y'
//		//Let $Wrk_AD_NamesBuild = 'Y'
//		//from PS_Names CN5
//		//where CN5.Emplid = $PSEmplid
//		//and CN5.NAME_TYPE = 'PRF'    !changed for v8.3
//		//and CN5.EFFDT = 
//		//	(SELECT MAX(EFFDT) FROM PS_Names CN6   !added for v8.3
//		//                      WHERE CN6.EMPLID     = CN5.EMPLID      !added for v8.3
//		//                      AND   CN6.NAME_TYPE  = CN5.NAME_TYPE
//		//                      AND   to_char(CN6.EFFDT,'YYYY-MM-DD') <= $PSEffdt) !added for v8.3
//		//end-select
//		//If $Found = 'N'
//		//Let $PSNickName = ' '
//		//End-if    !$Found = 'N'
//		//End-Procedure HR05-Get-Names-Table
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//		EntityManager em = emfactory.createEntityManager();
//		try {
//			List<PsName> resultList = em.createQuery(
//					"SELECT p.phone FROM PsPersonalPhone p "
//					+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
//					+ "AND UPPER(TRIM(p.phoneType)) = :phoneType "
//					, String.class)
//					.setParameter("employeeId", employeeId.trim().toUpperCase())
//					.setParameter("phoneType", phoneType.trim().toUpperCase())
//	    		    .getResultList();
//	    	if(resultList != null && resultList.size() > 0) {
//	    		return resultList.get(0);
//	    	}
//	    }
//	    catch (Exception e) {
//	    	e.printStackTrace();
//	    } 
//	    finally {
//	    	em.close();
//	    }
	    return null;	
	}

	/**
	 * @see HR05-Get-Info in ZHRI105A.SQC
	 * @param employeeId
	 * @param effectiveDate
	 * @param addressType
	 * @return PsName record
	 */
	public static PsName findByEmployeeIdAndEffectiveDateAndNameType(String employeeId, Date effectiveDate, String nameType) {
		//BEGIN-SELECT
		//FROM PS_NAMES CN3
		//WHERE CN3.NAME_TYPE = 'PRI'
		//  	AND CN3.EmplId = $PSEmplId
		//  	AND CN3.EFFDT = 
		//			(SELECT MAX(EFFDT) FROM PS_NAMES CN4
		//    			WHERE CN4.EMPLID   = CN3.EMPLID
		//           		AND CN4.NAME_TYPE  = CN3.NAME_TYPE
		//            		AND TO_CHAR(CN4.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
		//END-SELECT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<PsName> resultList = em.createQuery(
					"SELECT PsName FROM PsName p "
							+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
							+ "WHERE UPPER(TRIM(p.nameType)) = :nameType "
							+ "AND p.effectiveDate = "
							+ "(SELECT MAX(p2.effectiveDate) FROM PsName p2 "
								+ "WHERE UPPER(TRIM(p2.employeeId)) = UPPER(TRIM(p.employeeId)) "
									+ "AND UPPER(TRIM(p2.nameType)) = UPPER(TRIM(p.nameType)) "
									+ "AND p2.effectiveDate <= :effectiveDate) "
					, PsName.class)
					.setParameter("employeeId", employeeId.trim().toUpperCase())
					.setParameter("nameType", nameType.trim().toUpperCase())
					.setParameter("effectiveDate", effectiveDate, TemporalType.DATE)
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

	public static String GetPersDataEffdt(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  AD-Get-Pers-Data-Effdt
//		! Desc:  This routine will get the Personal Data row for each of the
//		!        employee numbers entered in the trigger file.  Pers_Data_Effdt table
//		!        no longer has name info, so are using Names table.
//		!----------------------------------------------------------------------
//		Begin-Procedure AD-Get-Pers-Data-effdt
//		begin-select
//		ADPDE2A.First_Name
//		ADPDE2A.Last_Name
//		ADPDE2A.Middle_Name
//		  Let $ADPSLastName   = RTRIM(LTRIM(&ADPDE2A.Last_Name,' '),' ')
//		  Let $ADPSFirstName  = RTRIM(LTRIM(&ADPDE2A.First_Name,' '),' ')
//		  Let $ADPSMiddleName = RTRIM(LTRIM(&ADPDE2A.Middle_Name,' '),' ')
//		  Let $ADPSMiddleName = SUBSTR($ADPSMiddleName,1,1)
//		from PS_NAMES ADPDE2A
//		where ADPDE2A.Emplid = $PSEmplid
//		  and ADPDE2A.NAME_TYPE = 'PRI'
//		  and ADPDE2A.Effdt = (select max(ADPDE2B.effdt) from  PS_NAMES ADPDE2B
//		                       where ADPDE2B.emplid    = ADPDE2A.emplid
//		                       and   ADPDE2B.NAME_TYPE = ADPDE2A.NAME_TYPE
//		                       and   to_char(ADPDE2B.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		end-select
//		end-procedure AD-Get-Pers_Data-Effdt
		return null;
	}

	public static PsName findNameSuffix(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  AD-Get-NameSuffix
//		! Desc:  This routine will get the Name Suffix row for each of the
//		!        employee numbers entered in the trigger file.
//		!----------------------------------------------------------------------
//		Begin-Procedure AD-Get-NameSuffix
//		begin-select
//		ANAME.Name_Suffix
//		  Let $ADNameSuffix  = RTRIM(LTRIM(&ANAME.Name_Suffix,' '),' ')
//		  Let $ADNameSuffix  = SUBSTR($ADNameSuffix,1,5)
//		from PS_NAMES ANAME
//		where ANAME.Emplid = $PSEmplid
//		  and ANAME.NAME_TYPE = 'PRI'
//		  and ANAME.Effdt = (select max(ANAME2.effdt) from  PS_NAMES ANAME2
//		                       where ANAME2.emplid     = ANAME.emplid
//		                       and   ANAME2.NAME_TYPE  = ANAME.NAME_TYPE
//		                       and   to_char(ANAME2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		end-select
//		end-procedure AD-Get-NameSuffix
		return null;
	}
	
	public static String findPreferredName(String employeeId) {
		return employeeId;
//	!----------------------------------------------------------------------
//	! Procedure:  AD-Get-Names
//	! Desc:  This routine gets the Preferred Name from PS_Names for Active Directory File Build
//	!----------------------------------------------------------------------
//	Begin-Procedure AD-Get-Names
//	Let $PSPrfName = ''
//	begin-select
//	ADN.First_Name   !changed for v8.3 and defect 993
//	  Let $PSPrfName = RTRIM(LTRIM(&ADN.First_Name,' '),' ')
//	from PS_Names ADN
//	where ADN.Emplid = $PSEmplid
//	  and ADN.NAME_TYPE = 'PRF'
//	  and ADN.EFFDT     = (SELECT MAX(EFFDT) FROM PS_Names ADN2
//	                      WHERE ADN2.EMPLID   = ADN.EMPLID
//	                      AND ADN2.NAME_TYPE  = ADN.NAME_TYPE
//	                      AND to_char(ADN2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//	end-select
//	end-procedure AD-Get-Names
	}
	
}
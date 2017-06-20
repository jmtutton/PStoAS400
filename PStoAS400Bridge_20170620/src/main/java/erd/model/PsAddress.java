package erd.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The persistent class for the PS_ADDRESSES database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ADDRESSES")
@NamedQuery(name="PsAddress.findAll", query="SELECT p FROM PsAddress p")
public class PsAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="ADDR_FIELD1", nullable=false, length=2)
	private String addrField1;

	@Column(name="ADDR_FIELD2", nullable=false, length=4)
	private String addrField2;

	@Column(name="ADDR_FIELD3", nullable=false, length=4)
	private String addrField3;

	@Column(name="ADDRESS_TYPE", nullable=false, length=4)
	private String addressType;

	@Column(name="ADDRESS1", nullable=false, length=55)
	private String address1;

	@Column(name="ADDRESS1_AC", nullable=false, length=55)
	private String address1AlternateCharacter;

	@Column(name="ADDRESS2", nullable=false, length=55)
	private String address2;

	@Column(name="ADDRESS2_AC", nullable=false, length=55)
	private String address2AlternateCharacter;

	@Column(name="ADDRESS3", nullable=false, length=55)
	private String address3;

	@Column(name="ADDRESS3_AC", nullable=false, length=55)
	private String address3AlternateCharacter;

	@Column(name="ADDRESS4", nullable=false, length=55)
	private String address4;

	@Column(name="CITY", nullable=false, length=30)
	private String city;

	@Column(name="CITY_AC", nullable=false, length=30)
	private String cityAlternateCharacter;

	@Column(name="COUNTRY", nullable=false, length=3)
	private String countryIsoAlpha3Code;

	@Column(name="COUNTY", nullable=false, length=30)
	private String county;

	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String statusAsOfEffectiveDate;

	@Column(name="EFFDT",nullable=false)
	private Date effectiveDate;

	@Column(name="GEO_CODE", nullable=false, length=11)
	private String geoCode;

	@Column(name="HOUSE_TYPE", nullable=false, length=2)
	private String houseType;

	@Column(name="IN_CITY_LIMIT", nullable=false, length=1)
	private String isInCityLimit;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="NUM1", nullable=false, length=6)
	private String num1;

	@Column(name="NUM2", nullable=false, length=6)
	private String num2;

	@Column(name="POSTAL", nullable=false, length=12)
	private String postal;

	@Column(name="REG_REGION", nullable=false, length=5)
	private String regulatoryRegion;

	@Column(name="\"STATE\"", nullable=false, length=6)
	private String state;

	public PsAddress() {
	}

	public String getAddrField1() {
		return this.addrField1;
	}

	public void setAddrField1(String addrField1) {
		this.addrField1 = addrField1;
	}

	public String getAddrField2() {
		return this.addrField2;
	}

	public void setAddrField2(String addrField2) {
		this.addrField2 = addrField2;
	}

	public String getAddrField3() {
		return this.addrField3;
	}

	public void setAddrField3(String addrField3) {
		this.addrField3 = addrField3;
	}

	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress1Ac() {
		return this.address1AlternateCharacter;
	}

	public void setAddress1Ac(String address1AlternateCharacter) {
		this.address1AlternateCharacter = address1AlternateCharacter;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress2Ac() {
		return this.address2AlternateCharacter;
	}

	public void setAddress2Ac(String address2AlternateCharacter) {
		this.address2AlternateCharacter = address2AlternateCharacter;
	}

	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress3Ac() {
		return this.address3AlternateCharacter;
	}

	public void setAddress3Ac(String address3AlternateCharacter) {
		this.address3AlternateCharacter = address3AlternateCharacter;
	}

	public String getAddress4() {
		return this.address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityAc() {
		return this.cityAlternateCharacter;
	}

	public void setCityAc(String cityAlternateCharacter) {
		this.cityAlternateCharacter = cityAlternateCharacter;
	}

	public String getCountryCode() {
		return this.countryIsoAlpha3Code;
	}

	public void setCountryCode(String countryIsoAlpha3Code) {
		this.countryIsoAlpha3Code = countryIsoAlpha3Code;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
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

	public String getGeoCode() {
		return this.geoCode;
	}

	public void setGeoCode(String geoCode) {
		this.geoCode = geoCode;
	}

	public String getHouseType() {
		return this.houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getIsInCityLimit() {
		return this.isInCityLimit;
	}

	public void setIsInCityLimit(String isInCityLimit) {
		this.isInCityLimit = isInCityLimit;
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

	public String getNum1() {
		return this.num1;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}

	public String getNum2() {
		return this.num2;
	}

	public void setNum2(String num2) {
		this.num2 = num2;
	}

	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getRegulatoryRegion() {
		return this.regulatoryRegion;
	}

	public void setRegulatoryRegion(String regulatoryRegion) {
		this.regulatoryRegion = regulatoryRegion;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public PsAddress HR01GetPersonalData(String employeeId, String addressType, Date effectiveDate) {
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

	public PsAddress HR05GetInfo(String employeeId, String addressType, Date effectiveDate) {
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

}
package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

/**
 * The persistent class for the PS_PERSONAL_DATA database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_PERSONAL_DATA")
@NamedQuery(name="PsPersonalData.findAll", query="SELECT p FROM PsPersonalData p")
public class PsPersonalData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="ADDR_FIELD1", nullable=false, length=2)
	private String addrField1;

	@Column(name="ADDR_FIELD1_OTHER", nullable=false, length=2)
	private String addrField1Other;

	@Column(name="ADDR_FIELD2", nullable=false, length=4)
	private String addrField2;

	@Column(name="ADDR_FIELD2_OTHER", nullable=false, length=4)
	private String addrField2Other;

	@Column(name="ADDR_FIELD3", nullable=false, length=4)
	private String addrField3;

	@Column(name="ADDR_FIELD3_OTHER", nullable=false, length=4)
	private String addrField3Other;

	@Column(name="address1", nullable=false, length=55)
	private String address1;

	@Column(name="ADDRESS1_AC", nullable=false, length=55)
	private String address1AlternateCharacter;

	@Column(name="ADDRESS1_OTHER", nullable=false, length=55)
	private String address1Other;

	@Column(name="address2", nullable=false, length=55)
	private String address2;

	@Column(name="ADDRESS2_AC", nullable=false, length=55)
	private String address2AlternateCharacter;

	@Column(name="ADDRESS2_OTHER", nullable=false, length=55)
	private String address2Other;

	@Column(name="address3", nullable=false, length=55)
	private String address3;

	@Column(name="ADDRESS3_AC", nullable=false, length=55)
	private String address3AlternateCharacter;

	@Column(name="ADDRESS3_OTHER", nullable=false, length=55)
	private String address3Other;

	@Column(name="address4", nullable=false, length=55)
	private String address4;

	@Column(name="ADDRESS4_OTHER", nullable=false, length=55)
	private String address4Other;

	@Column(name="ALTER_EMPLID", nullable=false, length=11)
	private String alternateEmployeeId;

	@Column(name="BARG_UNIT", nullable=false, length=4)
	private String bargUnit;

	@Column(name="BILINGUALISM_CODE", nullable=false, length=1)
	private String bilingualismCode;

	@Column(name="BIRTHCOUNTRY", nullable=false, length=3)
	private String birthCountry;

	@Column(name="BIRTHDATE")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column(name="BIRTHPLACE", nullable=false, length=30)
	private String birthPlace;

	@Column(name="BIRTHSTATE", nullable=false, length=6)
	private String birthState;

	@Column(name="CAMPUS_ID", nullable=false, length=16)
	private String campusId;

	@Column(name="CITIZEN_PROOF1", nullable=false, length=10)
	private String citizenProof1;

	@Column(name="CITIZEN_PROOF2", nullable=false, length=10)
	private String citizenProof2;

	@Column(name="CITY", nullable=false, length=30)
	private String city;

	@Column(name="CITY_AC", nullable=false, length=30)
	private String cityAlternateCharacter;

	@Column(name="CITY_OTHER", nullable=false, length=30)
	private String cityOther;

	@Column(name="COUNTRY", nullable=false, length=3)
	private String countryIsoAlpha3Code;

	@Column(name="COUNTRY_CODE", nullable=false, length=3)
	private String countryCode;

	@Column(name="COUNTRY_NM_FORMAT", nullable=false, length=3)
	private String supportedNameFormatTypes;

	@Column(name="COUNTRY_OTHER", nullable=false, length=3)
	private String countryOther;

	@Column(name="COUNTY", nullable=false, length=30)
	private String county;

	@Column(name="COUNTY_OTHER", nullable=false, length=30)
	private String countyOther;

	@Column(name="CPAMID", nullable=false, length=6)
	private String cpamid;

	@Column(name="DEATH_CERTIF_NBR", nullable=false, length=10)
	private String deathCertifNbr;

	@Column(name="DISABLED", nullable=false, length=1)
	private String disabled;

	@Column(name="DISABLED_VET", nullable=false, length=1)
	private String disabledVet;

	@Column(name="DT_OF_DEATH")
	@Temporal(TemporalType.DATE)
	private Date dateOfDeath;

	@Column(name="ENTRY_DT_FRA")
	@Temporal(TemporalType.DATE)
	private Date entryDtFra;

	@Column(name="EXPCTD_MILITARY_DT")
	@Temporal(TemporalType.DATE)
	private Date expctdMilitaryDt;

	@Column(name="EXTENSION", nullable=false, length=6)
	private String extension;

	@Column(name="FERPA", nullable=false, length=1)
	private String ferpa;

	@Column(name="FIRST_NAME", nullable=false, length=30)
	private String firstName;

	@Column(name="FIRST_NAME_SRCH", nullable=false, length=30)
	private String firstNameSpecialCharactersRemoved;

	@Column(name="FT_STUDENT", nullable=false, length=1)
	private String isFulltimeStudent;

	@Column(name="GEO_CODE", nullable=false, length=11)
	private String geoCode;

	@Column(name="GEO_CODE_OTHER", nullable=false, length=11)
	private String geoCodeOther;

	@Column(name="GRADE", nullable=false, length=3)
	private String grade;

	@Column(name="GVT_CHANGE_FLAG", nullable=false, length=1)
	private String gvtChangeFlag;

	@Column(name="GVT_CRED_MIL_SVCE", nullable=false, length=4)
	private String gvtCredMilSvce;

	@Column(name="GVT_CURR_AGCY_EMPL", nullable=false, length=1)
	private String gvtCurrAgcyEmpl;

	@Column(name="GVT_CURR_FED_EMPL", nullable=false, length=1)
	private String gvtCurrFedEmpl;

	@Column(name="GVT_DISABILITY_CD", nullable=false, length=2)
	private String gvtDisabilityCd;

	@Column(name="GVT_DRAFT_STATUS", nullable=false, length=1)
	private String gvtDraftStatus;

	@Column(name="GVT_HIGH_GRADE", nullable=false, length=3)
	private String gvtHighGrade;

	@Column(name="GVT_HIGH_PAY_PLAN", nullable=false, length=2)
	private String gvtHighPayPlan;

	@Column(name="GVT_MIL_GRADE", nullable=false, length=3)
	private String gvtMilGrade;

	@Column(name="GVT_MIL_RESRVE_CAT", nullable=false, length=1)
	private String gvtMilResrveCat;

	@Column(name="GVT_MIL_SEP_RET", nullable=false, length=1)
	private String gvtMilSepRet;

	@Column(name="GVT_MIL_SVCE_END")
	@Temporal(TemporalType.DATE)
	private Date gvtMilSvceEnd;

	@Column(name="GVT_MIL_SVCE_START")
	@Temporal(TemporalType.DATE)
	private Date gvtMilSvceStart;

	@Column(name="GVT_MIL_VERIFY", nullable=false, length=1)
	private String gvtMilVerify;

	@Column(name="GVT_MILITARY_COMP", nullable=false, length=1)
	private String gvtMilitaryComp;

	@Column(name="GVT_PAR_NBR_LAST", nullable=false, precision=38)
	private BigDecimal gvtParNbrLast;

	@Column(name="GVT_PAY_PLAN", nullable=false, length=2)
	private String gvtPayPlan;

	@Column(name="GVT_PREV_AGCY_EMPL", nullable=false, length=1)
	private String gvtPrevAgcyEmpl;

	@Column(name="GVT_PREV_FED_EMPL", nullable=false, length=1)
	private String gvtPrevFedEmpl;

	@Column(name="GVT_SEP_INCENT_DT")
	@Temporal(TemporalType.DATE)
	private Date gvtSepIncentDt;

	@Column(name="GVT_SEP_INCENTIVE", nullable=false, length=1)
	private String gvtSepIncentive;

	@Column(name="GVT_TENURE", nullable=false, length=1)
	private String gvtTenure;

	@Column(name="GVT_UNIF_SVC_CTR", nullable=false, length=1)
	private String gvtUnifSvcCtr;

	@Column(name="GVT_VET_PREF_APPT", nullable=false, length=1)
	private String gvtVetPrefAppt;

	@Column(name="GVT_VET_PREF_RIF", nullable=false, length=1)
	private String gvtVetPrefRif;

	@Column(name="GVT_YR_ATTAINED")
	@Temporal(TemporalType.DATE)
	private Date gvtYrAttained;

	@Column(name="HEALTH_CARE_NBR", nullable=false, length=12)
	private String healthCareNbr;

	@Column(name="HEALTH_CARE_STATE", nullable=false, length=6)
	private String healthCareState;

	@Column(name="HIGHEST_EDUC_LVL", nullable=false, length=2)
	private String highestEducationLevel;

	@Column(name="HONSEKI_JPN", nullable=false, length=2)
	private String honsekiJpn;

	@Column(name="HOUSE_TYPE", nullable=false, length=2)
	private String houseType;

	@Column(name="HOUSE_TYPE_OTHER", nullable=false, length=2)
	private String houseTypeOther;

	@Column(name="HR_RESPONSIBLE_ID", nullable=false, length=11)
	private String hrResponsibleId;

	@Column(name="IN_CITY_LIMIT", nullable=false, length=1)
	private String isInCityLimit;

	@Column(name="IN_CITY_LMT_OTHER", nullable=false, length=1)
	private String inCityLmtOther;

	@Column(name="LANG_CD", nullable=false, length=3)
	private String languageCode;

	@Column(name="LAST_NAME", nullable=false, length=30)
	private String lastName;

	@Column(name="LAST_NAME_PREF_NLD", nullable=false, length=1)
	private String lastNamePreferenceCode;

	@Column(name="LAST_NAME_SRCH", nullable=false, length=30)
	private String lastNameSpecialCharactersRemoved;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="MAR_STATUS", nullable=false, length=1)
	private String maritalStatus;

	@Column(name="MAR_STATUS_DT")
	@Temporal(TemporalType.DATE)
	private Date maritalStatusDate;

	@Column(name="MEDICARE_ENTLD_DT")
	@Temporal(TemporalType.DATE)
	private Date medicareEntldDt;

	@Column(name="MIDDLE_NAME", nullable=false, length=30)
	private String middleName;

	@Column(name="MILIT_SITUATN_ESP", nullable=false, length=3)
	private String militSituatnEsp;

	@Column(name="MILIT_SITUATN_FRA", nullable=false, length=3)
	private String militSituatnFra;

	@Column(name="MILITARY_END_ITA")
	@Temporal(TemporalType.DATE)
	private Date militaryEndIta;

	@Column(name="MILITARY_RANK_ITA", nullable=false, length=50)
	private String militaryRankIta;

	@Column(name="MILITARY_STAT_GER", nullable=false, length=1)
	private String militaryStatGer;

	@Column(name="MILITARY_STAT_ITA", nullable=false, length=1)
	private String militaryStatIta;

	@Column(name="MILITARY_STATUS", nullable=false, length=1)
	private String militaryStatus;

	@Column(name="MILITARY_TYPE_ITA", nullable=false, length=2)
	private String militaryTypeIta;

	@Column(name="NAME", nullable=false, length=50)
	private String name;

	@Column(name="NAME_AC", nullable=false, length=50)
	private String alternateCharacterName;

	@Column(name="NAME_DISPLAY", nullable=false, length=50)
	private String displayName;

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

	@Column(name="NUM1", nullable=false, length=6)
	private String num1;

	@Column(name="NUM1_OTHER", nullable=false, length=6)
	private String num1Other;

	@Column(name="NUM2", nullable=false, length=6)
	private String num2;

	@Column(name="NUM2_OTHER", nullable=false, length=6)
	private String num2Other;

	@Column(name="PARTNER_LAST_NAME", nullable=false, length=30)
	private String partnerLastName;

	@Column(name="PARTNER_ROY_PREFIX", nullable=false, length=15)
	private String partnerRoyalPrefix;

	@Column(name="PHONE", nullable=false, length=24)
	private String phone;

	@Column(name="PLACE_OF_DEATH", nullable=false, length=30)
	private String placeOfDeath;

	@Column(name="POSTAL", nullable=false, length=12)
	private String postal;

	@Column(name="POSTAL_OTHER", nullable=false, length=12)
	private String postalOther;

	@Column(name="PREF_FIRST_NAME", nullable=false, length=30)
	private String preferredFirstName;

	@Column(name="SAL_ADMIN_PLAN", nullable=false, length=4)
	private String salAdminPlan;

	@Column(name="SECOND_LAST_NAME", nullable=false, length=30)
	private String secondLastName;

	@Column(name="SECOND_LAST_SRCH", nullable=false, length=30)
	private String secondLastNameSpecialCharactersRemoved;

	@Column(name="SEX", nullable=false, length=1)
	private String sex;

	@Column(name="SMOKER", nullable=false, length=1)
	private String smoker;

	@Column(name="SMOKER_DT")
	@Temporal(TemporalType.DATE)
	private Date smokerDt;

	@Column(name="SOC_SEC_AFF_DT")
	@Temporal(TemporalType.DATE)
	private Date socSecAffDt;

	@Column(name="\"STATE\"", nullable=false, length=6)
	private String state;

	@Column(name="STATE_OTHER", nullable=false, length=6)
	private String stateOther;

	@Column(name="US_WORK_ELIGIBILTY", nullable=false, length=1)
	private String usWorkEligibilty;

	@Column(name="VA_BENEFIT", nullable=false, length=1)
	private String vaBenefit;

	public PsPersonalData() {
	}

	public String getAddrField1() {
		return this.addrField1;
	}

	public void setAddrField1(String addrField1) {
		this.addrField1 = addrField1;
	}

	public String getAddrField1Other() {
		return this.addrField1Other;
	}

	public void setAddrField1Other(String addrField1Other) {
		this.addrField1Other = addrField1Other;
	}

	public String getAddrField2() {
		return this.addrField2;
	}

	public void setAddrField2(String addrField2) {
		this.addrField2 = addrField2;
	}

	public String getAddrField2Other() {
		return this.addrField2Other;
	}

	public void setAddrField2Other(String addrField2Other) {
		this.addrField2Other = addrField2Other;
	}

	public String getAddrField3() {
		return this.addrField3;
	}

	public void setAddrField3(String addrField3) {
		this.addrField3 = addrField3;
	}

	public String getAddrField3Other() {
		return this.addrField3Other;
	}

	public void setAddrField3Other(String addrField3Other) {
		this.addrField3Other = addrField3Other;
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

	public String getAddress1Other() {
		return this.address1Other;
	}

	public void setAddress1Other(String address1Other) {
		this.address1Other = address1Other;
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

	public String getAddress2Other() {
		return this.address2Other;
	}

	public void setAddress2Other(String address2Other) {
		this.address2Other = address2Other;
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

	public String getAddress3Other() {
		return this.address3Other;
	}

	public void setAddress3Other(String address3Other) {
		this.address3Other = address3Other;
	}

	public String getAddress4() {
		return this.address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getAddress4Other() {
		return this.address4Other;
	}

	public void setAddress4Other(String address4Other) {
		this.address4Other = address4Other;
	}

	public String getAlternateEmployeeId() {
		return this.alternateEmployeeId;
	}

	public void setAlternateEmployeeId(String alternateEmployeeId) {
		this.alternateEmployeeId = alternateEmployeeId;
	}

	public String getBargUnit() {
		return this.bargUnit;
	}

	public void setBargUnit(String bargUnit) {
		this.bargUnit = bargUnit;
	}

	public String getBilingualismCode() {
		return this.bilingualismCode;
	}

	public void setBilingualismCode(String bilingualismCode) {
		this.bilingualismCode = bilingualismCode;
	}

	public String getBirthcountry() {
		return this.birthCountry;
	}

	public void setBirthcountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}

	public Date getBirthdate() {
		return this.birthDate;
	}

	public void setBirthdate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthplace() {
		return this.birthPlace;
	}

	public void setBirthplace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getBirthstate() {
		return this.birthState;
	}

	public void setBirthstate(String birthState) {
		this.birthState = birthState;
	}

	public String getCampusId() {
		return this.campusId;
	}

	public void setCampusId(String campusId) {
		this.campusId = campusId;
	}

	public String getCitizenProof1() {
		return this.citizenProof1;
	}

	public void setCitizenProof1(String citizenProof1) {
		this.citizenProof1 = citizenProof1;
	}

	public String getCitizenProof2() {
		return this.citizenProof2;
	}

	public void setCitizenProof2(String citizenProof2) {
		this.citizenProof2 = citizenProof2;
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

	public String getCityOther() {
		return this.cityOther;
	}

	public void setCityOther(String cityOther) {
		this.cityOther = cityOther;
	}

	public String getCountryIsoAlpha3Code() {
		return this.countryIsoAlpha3Code;
	}

	public void setCountryIsoAlpha3Code(String countryIsoAlpha3Code) {
		this.countryIsoAlpha3Code = countryIsoAlpha3Code;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getSupportedNameFormatTypes() {
		return this.supportedNameFormatTypes;
	}

	public void setSupportedNameFormatTypes(String supportedNameFormatTypes) {
		this.supportedNameFormatTypes = supportedNameFormatTypes;
	}

	public String getCountryOther() {
		return this.countryOther;
	}

	public void setCountryOther(String countryOther) {
		this.countryOther = countryOther;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCountyOther() {
		return this.countyOther;
	}

	public void setCountyOther(String countyOther) {
		this.countyOther = countyOther;
	}

	public String getCpamid() {
		return this.cpamid;
	}

	public void setCpamid(String cpamid) {
		this.cpamid = cpamid;
	}

	public String getDeathCertifNbr() {
		return this.deathCertifNbr;
	}

	public void setDeathCertifNbr(String deathCertifNbr) {
		this.deathCertifNbr = deathCertifNbr;
	}

	public String getDisabled() {
		return this.disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getDisabledVet() {
		return this.disabledVet;
	}

	public void setDisabledVet(String disabledVet) {
		this.disabledVet = disabledVet;
	}

	public Date getDateOfDeath() {
		return this.dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getEntryDtFra() {
		return this.entryDtFra;
	}

	public void setEntryDtFra(Date entryDtFra) {
		this.entryDtFra = entryDtFra;
	}

	public Date getExpctdMilitaryDt() {
		return this.expctdMilitaryDt;
	}

	public void setExpctdMilitaryDt(Date expctdMilitaryDt) {
		this.expctdMilitaryDt = expctdMilitaryDt;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFerpa() {
		return this.ferpa;
	}

	public void setFerpa(String ferpa) {
		this.ferpa = ferpa;
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

	public String getIsFulltimeStudent() {
		return this.isFulltimeStudent;
	}

	public void setIsFulltimeStudent(String isFulltimeStudent) {
		this.isFulltimeStudent = isFulltimeStudent;
	}

	public String getGeoCode() {
		return this.geoCode;
	}

	public void setGeoCode(String geoCode) {
		this.geoCode = geoCode;
	}

	public String getGeoCodeOther() {
		return this.geoCodeOther;
	}

	public void setGeoCodeOther(String geoCodeOther) {
		this.geoCodeOther = geoCodeOther;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGvtChangeFlag() {
		return this.gvtChangeFlag;
	}

	public void setGvtChangeFlag(String gvtChangeFlag) {
		this.gvtChangeFlag = gvtChangeFlag;
	}

	public String getGvtCredMilSvce() {
		return this.gvtCredMilSvce;
	}

	public void setGvtCredMilSvce(String gvtCredMilSvce) {
		this.gvtCredMilSvce = gvtCredMilSvce;
	}

	public String getGvtCurrAgcyEmpl() {
		return this.gvtCurrAgcyEmpl;
	}

	public void setGvtCurrAgcyEmpl(String gvtCurrAgcyEmpl) {
		this.gvtCurrAgcyEmpl = gvtCurrAgcyEmpl;
	}

	public String getGvtCurrFedEmpl() {
		return this.gvtCurrFedEmpl;
	}

	public void setGvtCurrFedEmpl(String gvtCurrFedEmpl) {
		this.gvtCurrFedEmpl = gvtCurrFedEmpl;
	}

	public String getGvtDisabilityCd() {
		return this.gvtDisabilityCd;
	}

	public void setGvtDisabilityCd(String gvtDisabilityCd) {
		this.gvtDisabilityCd = gvtDisabilityCd;
	}

	public String getGvtDraftStatus() {
		return this.gvtDraftStatus;
	}

	public void setGvtDraftStatus(String gvtDraftStatus) {
		this.gvtDraftStatus = gvtDraftStatus;
	}

	public String getGvtHighGrade() {
		return this.gvtHighGrade;
	}

	public void setGvtHighGrade(String gvtHighGrade) {
		this.gvtHighGrade = gvtHighGrade;
	}

	public String getGvtHighPayPlan() {
		return this.gvtHighPayPlan;
	}

	public void setGvtHighPayPlan(String gvtHighPayPlan) {
		this.gvtHighPayPlan = gvtHighPayPlan;
	}

	public String getGvtMilGrade() {
		return this.gvtMilGrade;
	}

	public void setGvtMilGrade(String gvtMilGrade) {
		this.gvtMilGrade = gvtMilGrade;
	}

	public String getGvtMilResrveCat() {
		return this.gvtMilResrveCat;
	}

	public void setGvtMilResrveCat(String gvtMilResrveCat) {
		this.gvtMilResrveCat = gvtMilResrveCat;
	}

	public String getGvtMilSepRet() {
		return this.gvtMilSepRet;
	}

	public void setGvtMilSepRet(String gvtMilSepRet) {
		this.gvtMilSepRet = gvtMilSepRet;
	}

	public Date getGvtMilSvceEnd() {
		return this.gvtMilSvceEnd;
	}

	public void setGvtMilSvceEnd(Date gvtMilSvceEnd) {
		this.gvtMilSvceEnd = gvtMilSvceEnd;
	}

	public Date getGvtMilSvceStart() {
		return this.gvtMilSvceStart;
	}

	public void setGvtMilSvceStart(Date gvtMilSvceStart) {
		this.gvtMilSvceStart = gvtMilSvceStart;
	}

	public String getGvtMilVerify() {
		return this.gvtMilVerify;
	}

	public void setGvtMilVerify(String gvtMilVerify) {
		this.gvtMilVerify = gvtMilVerify;
	}

	public String getGvtMilitaryComp() {
		return this.gvtMilitaryComp;
	}

	public void setGvtMilitaryComp(String gvtMilitaryComp) {
		this.gvtMilitaryComp = gvtMilitaryComp;
	}

	public BigDecimal getGvtParNbrLast() {
		return this.gvtParNbrLast;
	}

	public void setGvtParNbrLast(BigDecimal gvtParNbrLast) {
		this.gvtParNbrLast = gvtParNbrLast;
	}

	public String getGvtPayPlan() {
		return this.gvtPayPlan;
	}

	public void setGvtPayPlan(String gvtPayPlan) {
		this.gvtPayPlan = gvtPayPlan;
	}

	public String getGvtPrevAgcyEmpl() {
		return this.gvtPrevAgcyEmpl;
	}

	public void setGvtPrevAgcyEmpl(String gvtPrevAgcyEmpl) {
		this.gvtPrevAgcyEmpl = gvtPrevAgcyEmpl;
	}

	public String getGvtPrevFedEmpl() {
		return this.gvtPrevFedEmpl;
	}

	public void setGvtPrevFedEmpl(String gvtPrevFedEmpl) {
		this.gvtPrevFedEmpl = gvtPrevFedEmpl;
	}

	public Date getGvtSepIncentDt() {
		return this.gvtSepIncentDt;
	}

	public void setGvtSepIncentDt(Date gvtSepIncentDt) {
		this.gvtSepIncentDt = gvtSepIncentDt;
	}

	public String getGvtSepIncentive() {
		return this.gvtSepIncentive;
	}

	public void setGvtSepIncentive(String gvtSepIncentive) {
		this.gvtSepIncentive = gvtSepIncentive;
	}

	public String getGvtTenure() {
		return this.gvtTenure;
	}

	public void setGvtTenure(String gvtTenure) {
		this.gvtTenure = gvtTenure;
	}

	public String getGvtUnifSvcCtr() {
		return this.gvtUnifSvcCtr;
	}

	public void setGvtUnifSvcCtr(String gvtUnifSvcCtr) {
		this.gvtUnifSvcCtr = gvtUnifSvcCtr;
	}

	public String getGvtVetPrefAppt() {
		return this.gvtVetPrefAppt;
	}

	public void setGvtVetPrefAppt(String gvtVetPrefAppt) {
		this.gvtVetPrefAppt = gvtVetPrefAppt;
	}

	public String getGvtVetPrefRif() {
		return this.gvtVetPrefRif;
	}

	public void setGvtVetPrefRif(String gvtVetPrefRif) {
		this.gvtVetPrefRif = gvtVetPrefRif;
	}

	public Date getGvtYrAttained() {
		return this.gvtYrAttained;
	}

	public void setGvtYrAttained(Date gvtYrAttained) {
		this.gvtYrAttained = gvtYrAttained;
	}

	public String getHealthCareNbr() {
		return this.healthCareNbr;
	}

	public void setHealthCareNbr(String healthCareNbr) {
		this.healthCareNbr = healthCareNbr;
	}

	public String getHealthCareState() {
		return this.healthCareState;
	}

	public void setHealthCareState(String healthCareState) {
		this.healthCareState = healthCareState;
	}

	public String getHighestEducationLevel() {
		return this.highestEducationLevel;
	}

	public void setHighestEducationLevel(String highestEducationLevel) {
		this.highestEducationLevel = highestEducationLevel;
	}

	public String getHonsekiJpn() {
		return this.honsekiJpn;
	}

	public void setHonsekiJpn(String honsekiJpn) {
		this.honsekiJpn = honsekiJpn;
	}

	public String getHouseType() {
		return this.houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getHouseTypeOther() {
		return this.houseTypeOther;
	}

	public void setHouseTypeOther(String houseTypeOther) {
		this.houseTypeOther = houseTypeOther;
	}

	public String getHrResponsibleId() {
		return this.hrResponsibleId;
	}

	public void setHrResponsibleId(String hrResponsibleId) {
		this.hrResponsibleId = hrResponsibleId;
	}

	public String getIsInCityLimit() {
		return this.isInCityLimit;
	}

	public void setIsInCityLimit(String isInCityLimit) {
		this.isInCityLimit = isInCityLimit;
	}

	public String getInCityLmtOther() {
		return this.inCityLmtOther;
	}

	public void setInCityLmtOther(String inCityLmtOther) {
		this.inCityLmtOther = inCityLmtOther;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
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

	public Date getMedicareEntldDt() {
		return this.medicareEntldDt;
	}

	public void setMedicareEntldDt(Date medicareEntldDt) {
		this.medicareEntldDt = medicareEntldDt;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMilitSituatnEsp() {
		return this.militSituatnEsp;
	}

	public void setMilitSituatnEsp(String militSituatnEsp) {
		this.militSituatnEsp = militSituatnEsp;
	}

	public String getMilitSituatnFra() {
		return this.militSituatnFra;
	}

	public void setMilitSituatnFra(String militSituatnFra) {
		this.militSituatnFra = militSituatnFra;
	}

	public Date getMilitaryEndIta() {
		return this.militaryEndIta;
	}

	public void setMilitaryEndIta(Date militaryEndIta) {
		this.militaryEndIta = militaryEndIta;
	}

	public String getMilitaryRankIta() {
		return this.militaryRankIta;
	}

	public void setMilitaryRankIta(String militaryRankIta) {
		this.militaryRankIta = militaryRankIta;
	}

	public String getMilitaryStatGer() {
		return this.militaryStatGer;
	}

	public void setMilitaryStatGer(String militaryStatGer) {
		this.militaryStatGer = militaryStatGer;
	}

	public String getMilitaryStatIta() {
		return this.militaryStatIta;
	}

	public void setMilitaryStatIta(String militaryStatIta) {
		this.militaryStatIta = militaryStatIta;
	}

	public String getMilitaryStatus() {
		return this.militaryStatus;
	}

	public void setMilitaryStatus(String militaryStatus) {
		this.militaryStatus = militaryStatus;
	}

	public String getMilitaryTypeIta() {
		return this.militaryTypeIta;
	}

	public void setMilitaryTypeIta(String militaryTypeIta) {
		this.militaryTypeIta = militaryTypeIta;
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

	public String getNum1() {
		return this.num1;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}

	public String getNum1Other() {
		return this.num1Other;
	}

	public void setNum1Other(String num1Other) {
		this.num1Other = num1Other;
	}

	public String getNum2() {
		return this.num2;
	}

	public void setNum2(String num2) {
		this.num2 = num2;
	}

	public String getNum2Other() {
		return this.num2Other;
	}

	public void setNum2Other(String num2Other) {
		this.num2Other = num2Other;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlaceOfDeath() {
		return this.placeOfDeath;
	}

	public void setPlaceOfDeath(String placeOfDeath) {
		this.placeOfDeath = placeOfDeath;
	}

	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getPostalOther() {
		return this.postalOther;
	}

	public void setPostalOther(String postalOther) {
		this.postalOther = postalOther;
	}

	public String getPrefFirstName() {
		return this.preferredFirstName;
	}

	public void setPrefFirstName(String preferredFirstName) {
		this.preferredFirstName = preferredFirstName;
	}

	public String getSalAdminPlan() {
		return this.salAdminPlan;
	}

	public void setSalAdminPlan(String salAdminPlan) {
		this.salAdminPlan = salAdminPlan;
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

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSmoker() {
		return this.smoker;
	}

	public void setSmoker(String smoker) {
		this.smoker = smoker;
	}

	public Date getSmokerDt() {
		return this.smokerDt;
	}

	public void setSmokerDt(Date smokerDt) {
		this.smokerDt = smokerDt;
	}

	public Date getSocSecAffDt() {
		return this.socSecAffDt;
	}

	public void setSocSecAffDt(Date socSecAffDt) {
		this.socSecAffDt = socSecAffDt;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateOther() {
		return this.stateOther;
	}

	public void setStateOther(String stateOther) {
		this.stateOther = stateOther;
	}

	public String getUsWorkEligibilty() {
		return this.usWorkEligibilty;
	}

	public void setUsWorkEligibilty(String usWorkEligibilty) {
		this.usWorkEligibilty = usWorkEligibilty;
	}

	public String getVaBenefit() {
		return this.vaBenefit;
	}

	public void setVaBenefit(String vaBenefit) {
		this.vaBenefit = vaBenefit;
	}

	public static PsPersonalData HR01GetPersonalData(String employeeId) {
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

	
	/**
	 * This routine will get the Personal Data row for each of the employee numbers entered in the trigger file.
	 * @see HR05-Get-Personal-Data in ZHRI105A.SQC
	 */
	public static PsPersonalData findByEmployeeId(String employeeId) {
		//BEGIN-SELECT
		//FROM PS_Personal_Data CPD2, PS_ORIG_HIR_EMP_VW COHE                                                        
		//WHERE CPD2.Emplid = $PSEmplid
		//AND COHE.EMPLID = CPD2.EMPLID                                                     
		//END-SELECT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsPersonalData> resultList = (List<PsPersonalData>) em.createQuery(
	    			"SELECT PsPersonalData "
	    				+ "FROM PsPersonalData p, PsEmployeeOriginalHire p2 "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    				+ "AND UPPER(TRIM(p2.employeeId)) = UPPER(TRIM(p.employeeId)) "
	    				, PsPersonalData.class)
	    		    .setParameter("employeeId", employeeId.trim().toUpperCase())
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

	public String findLanguageCodeByEmployeeId(String employeeId) {
		return null;
	}

}
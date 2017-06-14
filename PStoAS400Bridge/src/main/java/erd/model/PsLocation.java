package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * The persistent class for the PS_LOCATION_TBL database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_LOCATION_TBL")
@NamedQuery(name="PsLocation.findAll", query="SELECT p FROM PsLocation p")
public class PsLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SETID", nullable=false, length=5)
	private String setId;

	@Column(name="LOCATION", nullable=false, length=10)
	private String location;

	@Column(name="ADDR_FIELD1", nullable=false, length=2)
	private String addrField1;

	@Column(name="ADDR_FIELD2", nullable=false, length=4)
	private String addrField2;

	@Column(name="ADDR_FIELD3", nullable=false, length=4)
	private String addrField3;

	@Column(name="ADDRESS1", nullable=false, length=55)
	private String address1;

	@Column(name="ADDRESS2", nullable=false, length=55)
	private String address2;

	@Column(name="ADDRESS3", nullable=false, length=55)
	private String address3;

	@Column(name="ADDRESS4", nullable=false, length=55)
	private String address4;

	@Column(name="ATTN_TO", nullable=false, length=30)
	private String attnTo;

	@Column(name="BUILDING", nullable=false, length=10)
	private String building;

	@Column(name="CAN_CMA", nullable=false, length=2)
	private String canCma;

	@Column(name="CAN_OEE_AREACD", nullable=false, length=2)
	private String canOeeAreacd;

	@Column(name="CITY", nullable=false, length=30)
	private String city;

	@Lob
	@Column(name="COMMENTS_2000")
	private String comments2000;

	@Column(name="COUNTRY", nullable=false, length=3)
	private String countryIsoAlpha3Code;

	@Column(name="COUNTRY_CODE", nullable=false, length=3)
	private String countryCode;

	@Column(name="COUNTY", nullable=false, length=30)
	private String county;

	@Column(name="DESCR", nullable=false, length=30)
	private String description;

	@Column(name="DESCR_AC", nullable=false, length=30)
	private String descrAc;

	@Column(name="DESCRSHORT", nullable=false, length=10)
	private String shortDescription;

	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String statusAsOfEffectiveDate;

	@Column(name="EFFDT", nullable=false)
	private Date effectiveDate;

	@Column(name="ESTABID", nullable=false, length=12)
	private String estabid;

	@Column(name="EXTENSION", nullable=false, length=6)
	private String extension;

	@Column(name="FAX", nullable=false, length=24)
	private String fax;

	@Column(name="\"FLOOR\"", nullable=false, length=10)
	private String floor;

	@Column(name="FON_ER_ID_MEX", nullable=false, length=12)
	private String fonErIdMex;

	@Column(name="FON_OFFICE_MEX", nullable=false, length=12)
	private String fonOfficeMex;

	@Column(name="GEO_CODE", nullable=false, length=11)
	private String geoCode;

	@Column(name="GEOLOC_CODE", nullable=false, length=10)
	private String geolocCode;

	@Column(name="GVT_DESIG_AGENT", nullable=false, length=4)
	private String gvtDesigAgent;

	@Column(name="GVT_GEOLOC_CD", nullable=false, length=9)
	private String gvtGeolocCd;

	@Column(name="HOLIDAY_SCHEDULE", nullable=false, length=6)
	private String holidaySchedule;

	@Column(name="HOUSE_TYPE", nullable=false, length=2)
	private String houseType;

	@Column(name="IN_CITY_LIMIT", nullable=false, length=1)
	private String isInCityLimit;

	@Column(name="INDUST_INSP_ID_GER", nullable=false, length=4)
	private String industInspIdGer;

	@Column(name="JURISDICTION", nullable=false, length=5)
	private String jurisdiction;

	@Column(name="LABEL_FORMAT_ID2", nullable=false, length=30)
	private String labelFormatId2;

	@Column(name="LABEL_FORMAT_ID3", nullable=false, length=30)
	private String labelFormatId3;

	@Column(name="LANG_CD", nullable=false, length=3)
	private String languageCode;

	@Column(name="LOC_TAX_MEX", nullable=false, length=1)
	private String locTaxMex;

	@Column(name="LOC_TAX_SPCL_MEX", nullable=false, length=4)
	private String locTaxSpclMex;

	@Column(name="LOCALITY", nullable=false, length=10)
	private String locality;

	@Column(name="MATRICULA_NBR", nullable=false, precision=38)
	private BigDecimal matriculaNbr;

	@Column(name="MESSAGE_TEXT2", nullable=false, length=100)
	private String messageText2;

	@Column(name="NCR_SW_CAN", nullable=false, length=1)
	private String ncrSwCan;

	@Column(name="NI_REPORT_SW_UK", nullable=false, length=1)
	private String niReportSwUk;

	@Column(name="NUM1", nullable=false, length=6)
	private String num1;

	@Column(name="NUM2", nullable=false, length=6)
	private String num2;

	@Column(name="OFFICE_TYPE", nullable=false, length=1)
	private String officeType;

	@Column(name="PHONE", nullable=false, length=24)
	private String phone;

	@Column(name="POSTAL", nullable=false, length=12)
	private String postal;

	@Column(name="REG_REGION", nullable=false, length=5)
	private String regulatoryRegion;

	@Column(name="SAL_ADMIN_PLAN", nullable=false, length=4)
	private String salAdminPlan;

	@Column(name="SECTOR", nullable=false, length=10)
	private String sector;

	@Column(name="SETID_SALARY", nullable=false, length=5)
	private String setidSalary;

	@Column(name="SPK_COMM_ID_GER", nullable=false, length=9)
	private String spkCommIdGer;

	@Column(name="\"STATE\"", nullable=false, length=6)
	private String state;

	@Column(name="TARIFF_AREA_GER", nullable=false, length=3)
	private String tariffAreaGer;

	@Column(name="TARIFF_GER", nullable=false, length=2)
	private String tariffGer;

	@Column(name="TBS_OFFICE_CD_CAN", nullable=false, length=10)
	private String tbsOfficeCdCan;

	@Column(name="TIME_STAMP")
	private Timestamp timeStamp;

	@Column(name="USG_LBL_FORMAT_ID", nullable=false, length=30)
	private String usgLblFormatId;

	@Column(name="Z_LRD_AIRPORT_LOCN", nullable=false, length=80)
	private String zLrdAirportLocn;

	@Column(name="Z_LRD_AIRPORTCODE", nullable=false, length=12)
	private String zLrdAirportcode;

	@Column(name="Z_LRD_LOC_ID", nullable=false, length=20)
	private String zLrdLocId;

	@Column(name="Z_LRD_PSPRODUCT_ID", nullable=false, length=11)
	private String zLrdPsproductId;

	@Column(name="ZHRF_AREA_PS", nullable=false, length=20)
	private String zhrfAreaPs;

	public PsLocation() {
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

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return this.address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getAttnTo() {
		return this.attnTo;
	}

	public void setAttnTo(String attnTo) {
		this.attnTo = attnTo;
	}

	public String getBuilding() {
		return this.building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getCanCma() {
		return this.canCma;
	}

	public void setCanCma(String canCma) {
		this.canCma = canCma;
	}

	public String getCanOeeAreacd() {
		return this.canOeeAreacd;
	}

	public void setCanOeeAreacd(String canOeeAreacd) {
		this.canOeeAreacd = canOeeAreacd;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getComments2000() {
		return this.comments2000;
	}

	public void setComments2000(String comments2000) {
		this.comments2000 = comments2000;
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

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescrAc() {
		return this.descrAc;
	}

	public void setDescrAc(String descrAc) {
		this.descrAc = descrAc;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
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

	public String getEstabid() {
		return this.estabid;
	}

	public void setEstabid(String estabid) {
		this.estabid = estabid;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getFonErIdMex() {
		return this.fonErIdMex;
	}

	public void setFonErIdMex(String fonErIdMex) {
		this.fonErIdMex = fonErIdMex;
	}

	public String getFonOfficeMex() {
		return this.fonOfficeMex;
	}

	public void setFonOfficeMex(String fonOfficeMex) {
		this.fonOfficeMex = fonOfficeMex;
	}

	public String getGeoCode() {
		return this.geoCode;
	}

	public void setGeoCode(String geoCode) {
		this.geoCode = geoCode;
	}

	public String getGeolocCode() {
		return this.geolocCode;
	}

	public void setGeolocCode(String geolocCode) {
		this.geolocCode = geolocCode;
	}

	public String getGvtDesigAgent() {
		return this.gvtDesigAgent;
	}

	public void setGvtDesigAgent(String gvtDesigAgent) {
		this.gvtDesigAgent = gvtDesigAgent;
	}

	public String getGvtGeolocCd() {
		return this.gvtGeolocCd;
	}

	public void setGvtGeolocCd(String gvtGeolocCd) {
		this.gvtGeolocCd = gvtGeolocCd;
	}

	public String getHolidaySchedule() {
		return this.holidaySchedule;
	}

	public void setHolidaySchedule(String holidaySchedule) {
		this.holidaySchedule = holidaySchedule;
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

	public String getIndustInspIdGer() {
		return this.industInspIdGer;
	}

	public void setIndustInspIdGer(String industInspIdGer) {
		this.industInspIdGer = industInspIdGer;
	}

	public String getJurisdiction() {
		return this.jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getLabelFormatId2() {
		return this.labelFormatId2;
	}

	public void setLabelFormatId2(String labelFormatId2) {
		this.labelFormatId2 = labelFormatId2;
	}

	public String getLabelFormatId3() {
		return this.labelFormatId3;
	}

	public void setLabelFormatId3(String labelFormatId3) {
		this.labelFormatId3 = labelFormatId3;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLocTaxMex() {
		return this.locTaxMex;
	}

	public void setLocTaxMex(String locTaxMex) {
		this.locTaxMex = locTaxMex;
	}

	public String getLocTaxSpclMex() {
		return this.locTaxSpclMex;
	}

	public void setLocTaxSpclMex(String locTaxSpclMex) {
		this.locTaxSpclMex = locTaxSpclMex;
	}

	public String getLocality() {
		return this.locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getMatriculaNbr() {
		return this.matriculaNbr;
	}

	public void setMatriculaNbr(BigDecimal matriculaNbr) {
		this.matriculaNbr = matriculaNbr;
	}

	public String getMessageText2() {
		return this.messageText2;
	}

	public void setMessageText2(String messageText2) {
		this.messageText2 = messageText2;
	}

	public String getNcrSwCan() {
		return this.ncrSwCan;
	}

	public void setNcrSwCan(String ncrSwCan) {
		this.ncrSwCan = ncrSwCan;
	}

	public String getNiReportSwUk() {
		return this.niReportSwUk;
	}

	public void setNiReportSwUk(String niReportSwUk) {
		this.niReportSwUk = niReportSwUk;
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

	public String getOfficeType() {
		return this.officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getSalAdminPlan() {
		return this.salAdminPlan;
	}

	public void setSalAdminPlan(String salAdminPlan) {
		this.salAdminPlan = salAdminPlan;
	}

	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSetId() {
		return this.setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	public String getSetidSalary() {
		return this.setidSalary;
	}

	public void setSetidSalary(String setidSalary) {
		this.setidSalary = setidSalary;
	}

	public String getSpkCommIdGer() {
		return this.spkCommIdGer;
	}

	public void setSpkCommIdGer(String spkCommIdGer) {
		this.spkCommIdGer = spkCommIdGer;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTariffAreaGer() {
		return this.tariffAreaGer;
	}

	public void setTariffAreaGer(String tariffAreaGer) {
		this.tariffAreaGer = tariffAreaGer;
	}

	public String getTariffGer() {
		return this.tariffGer;
	}

	public void setTariffGer(String tariffGer) {
		this.tariffGer = tariffGer;
	}

	public String getTbsOfficeCdCan() {
		return this.tbsOfficeCdCan;
	}

	public void setTbsOfficeCdCan(String tbsOfficeCdCan) {
		this.tbsOfficeCdCan = tbsOfficeCdCan;
	}

	public Timestamp getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUsgLblFormatId() {
		return this.usgLblFormatId;
	}

	public void setUsgLblFormatId(String usgLblFormatId) {
		this.usgLblFormatId = usgLblFormatId;
	}

	public String getZLrdAirportLocn() {
		return this.zLrdAirportLocn;
	}

	public void setZLrdAirportLocn(String zLrdAirportLocn) {
		this.zLrdAirportLocn = zLrdAirportLocn;
	}

	public String getZLrdAirportcode() {
		return this.zLrdAirportcode;
	}

	public void setZLrdAirportcode(String zLrdAirportcode) {
		this.zLrdAirportcode = zLrdAirportcode;
	}

	public String getZLrdLocId() {
		return this.zLrdLocId;
	}

	public void setZLrdLocId(String zLrdLocId) {
		this.zLrdLocId = zLrdLocId;
	}

	public String getZLrdPsproductId() {
		return this.zLrdPsproductId;
	}

	public void setZLrdPsproductId(String zLrdPsproductId) {
		this.zLrdPsproductId = zLrdPsproductId;
	}

	public String getZhrfAreaPs() {
		return this.zhrfAreaPs;
	}

	public void setZhrfAreaPs(String zhrfAreaPs) {
		this.zhrfAreaPs = zhrfAreaPs;
	}

//	public PsLocation findByLocation01(String location) {
////		!----------------------------------------------------------------------
////		! Procedure:  HR01-Get-Location-Country
////		! Desc:  Gets the country that the location is in to determine which
////		!        national id to pass back to the legacy system
////		!----------------------------------------------------------------------
////		Begin-Procedure HR01-Get-Location-Country
////		Begin-Select
////		CLT.COUNTRY
////		    LET $PSLoc_Country = &CLT.COUNTRY
////		    Let $Wrk_AD_CountryCdBuild = 'Y'
////		from PS_LOCATION_TBL CLT
////		where CLT.LOCATION = $PSLocation
////		End-Select
////		End-Procedure HR01-Get-Location-Country
//		return null;
//	}
//
//	public PsLocation findByLocation05(String location) {
////		!----------------------------------------------------------------------
////		! Procedure:  HR05-Get-Location-Country
////		! Desc:       This routine gets the country in which an employee is
////		!             currently working.
////		!----------------------------------------------------------------------
////		Begin-Procedure HR05-Get-Location-Country
////		begin-select
////		CLT2.Country
////		  Let $PSLoc_Country = &CLT2.Country
////		  Let $Wrk_AD_CountryCdBuild = 'Y'
////		from PS_Location_Tbl CLT2
////		where CLT2.Location = $PSLocation
////		end-select
////		End-Procedure HR05-Get-Location
//		return null;
//	}

	public PsLocation HR09Getjobdata(String location) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR09-Get-job-data
//		! Desc:  Gets the employees data from the job table that needs to be
//		!        interfaced to the legacy system
//		!----------------------------------------------------------------------
//		Begin-Procedure HR09-Get-job-data
//		Begin-Select
//		CJ8.COMPANY
//		    let $PSCompany = ltrim(rtrim(&CJ8.COMPANY,' '),' ')              !Remove leading and trailing blanks
//		CJ8.LOCATION
//		    let $PSLocation = ltrim(rtrim(&CJ8.LOCATION,' '),' ')            !Remove leading and trailing blanks
//		CJ8.DEPTID
//		    let $PSDeptid = ltrim(rtrim(&CJ8.DEPTID,' '),' ')                !Remove leading and trailing blanks
//		CJ8.BUSINESS_UNIT
//		    let $PSBusinessUnit = ltrim(rtrim(&CJ8.BUSINESS_UNIT,' '),' ')   !Remove leading and trailing blanks
//		CJ8.JOBCODE
//		    let $PSJobCode = ltrim(rtrim(&CJ8.JOBCODE,' '),' ')                  !Remove leading and trailing blanks
//		CJ8.EMPL_CLASS
//		    let $PSEmplClass = ltrim(rtrim(&CJ8.EMPL_CLASS,' '),' ')         !Remove leading and trailing blanks
//		CJ8.EMPL_STATUS
//		    let $PSEmplStatus = ltrim(rtrim(&CJ8.EMPL_STATUS,' '),' ')       !Remove leading and trailing blanks
//		CJ8.FULL_PART_TIME
//		    let $PSfullparttime = ltrim(rtrim(&CJ8.FULL_PART_TIME,' '),' ')  !Remove leading and trailing blanks
//		 let $Wrk_AD_JobDataBuild = 'Y'
//		from PS_JOB CJ8
//		where CJ8.EMPLID = $Wrk_Emplid
//		and to_char(CJ8.EFFDT,'YYYY-MM-DD') = $PSEffdt
//		AND CJ8.EFFSEQ =
//		         (SELECT MAX(CJ8B.EFFSEQ)
//		          FROM  PS_JOB CJ8B
//		          WHERE CJ8B.EMPLID   = CJ8.EMPLID
//		            AND CJ8B.EMPL_RCD = CJ8.EMPL_RCD
//		            AND CJ8B.EFFDT    = CJ8.EFFDT)
//		and CJ8.EMPL_RCD = 0
//		End-Select
//		Begin-Select
//		CLT3.COUNTRY
//		    let $PSLoc_Country = ltrim(rtrim(&CLT3.COUNTRY,' '),' ')         !Remove leading and trailing blanks
//		    Let $Wrk_AD_CountryCdBuild = 'Y'
//		from PS_LOCATION_TBL CLT3
//		where CLT3.LOCATION = $PSLOCATION
//		End-Select
//		End-Procedure HR09-Get-job-data
		return null;
	}

	public PsLocation findByLocation(String location) {
//		!----------------------------------------------------------------------
//		! Procedure: AD-Get-Country-Code
//		! Desc:  This routine will get the Country Code for Active Directory File Build
//		!----------------------------------------------------------------------
//		Begin-Procedure AD-Get-Country-Code
//		Let $PSLoc_Country = ''
//		Begin-Select
//		ADL2.COUNTRY
//		    let $PSLoc_Country = ltrim(rtrim(&ADL2.COUNTRY,' '),' ')         !Remove leading and trailing blanks
//		from PS_LOCATION_TBL ADL2
//		where ADL2.LOCATION = $PSLOCATION
//		end-select
//		End-Procedure AD-Get-Country-Code
		return null;
	}
	
	public String findCountryIsoAlpha3CodeByLocation(String location) {
		return null;
	}

}
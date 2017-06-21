package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the PS_CONTRACT_DATA database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_CONTRACT_DATA")
@NamedQuery(name="PsContractData.findAll", query="SELECT p FROM PsContractData p")
public class PsContractData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="ADD_CONTRACT", nullable=false, length=1)
	private String addContract;

	@Column(name="BEGIN_WEEKDAY", nullable=false, length=1)
	private String beginWeekday;

	@Column(name="CNT_MAX_HRS", nullable=false, precision=5, scale=2)
	private BigDecimal cntMaxHrs;

	@Column(name="CNT_MIN_HRS", nullable=false, precision=5, scale=2)
	private BigDecimal cntMinHrs;

	@Column(name="CNT_RSZ_CAT_BEL", nullable=false, length=3)
	private String cntRszCatBel;

	@Column(name="CNT_RSZ_SUBM_BEL", nullable=false, length=1)
	private String cntRszSubmBel;

	@Column(name="CNT_TEMPLATE_ID", nullable=false, length=11)
	private String cntTemplateId;

	@Lob
	private String comments;

	@Column(name="COMMENTSHORT", nullable=false, length=30)
	private String commentshort;

	@Column(name="COMP_RATE_DESCR", nullable=false, length=100)
	private String compRateDescr;

	@Column(name="CONTRACT_BEGIN_DT")
	@Temporal(TemporalType.DATE)
	private Date contractBeginDate;

	@Column(name="CONTRACT_END_DT")
	@Temporal(TemporalType.DATE)
	private Date contractEndDate;

	@Column(name="CONTRACT_NUM", nullable=false, length=25)
	private String contractNum;

	@Column(name="CONTRACT_STATUS", nullable=false, length=1)
	private String contractStatus;

	@Column(name="CONTRCT_EXP_END_DT")
	@Temporal(TemporalType.DATE)
	private Date contrctExpEndDt;

	@Column(name="CONTRIB_ID_ESP", nullable=false, length=5)
	private String contribIdEsp;

	@Column(name="DURATION_TYPE", nullable=false, length=1)
	private String durationType;

	@Column(name="END_WEEKDAY", nullable=false, length=1)
	private String endWeekday;

	@Column(name="HIRE_CTR_CD_ESP", nullable=false, length=3)
	private String hireCtrCdEsp;

	@Column(name="NEE_PROVIDER_ID", nullable=false, length=10)
	private String neeProviderId;

	@Column(name="PROBATION_PERIOD", nullable=false, precision=38)
	private BigDecimal probationPeriod;

	@Column(name="RED_CHRG_CAT_BEL", nullable=false, length=6)
	private String redChrgCatBel;

	@Column(name="REDCH_ENDDT_BEL")
	@Temporal(TemporalType.DATE)
	private Date redchEnddtBel;

	@Column(name="REDCH_STARTDT_BEL")
	@Temporal(TemporalType.DATE)
	private Date redchStartdtBel;

	@Column(name="REG_REGION", nullable=false, length=5)
	private String regulatoryRegion;

	@Column(name="RESPONSIBLE_ID", nullable=false, length=11)
	private String responsibleId;

	@Column(name="SCHEME_ID_ESP", nullable=false, length=4)
	private String schemeIdEsp;

	@Column(name="SIGNATURE_DT")
	@Temporal(TemporalType.DATE)
	private Date signatureDt;

	@Column(name="SOCIAL_BALANCE_BEL", nullable=false, length=3)
	private String socialBalanceBel;

	@Column(name="VACATION_PERIOD", nullable=false, precision=38)
	private BigDecimal vacationPeriod;

	@Column(name="VACN_PERIOD_UNIT", nullable=false, length=1)
	private String vacnPeriodUnit;

	@Column(name="WAIVE_COMPLIANCE", nullable=false, length=1)
	private String waiveCompliance;

	@Column(name="YEAR_SW", nullable=false, length=1)
	private String yearSw;

	public PsContractData() {
	}

	public String getAddContract() {
		return this.addContract;
	}

	public void setAddContract(String addContract) {
		this.addContract = addContract;
	}

	public String getBeginWeekday() {
		return this.beginWeekday;
	}

	public void setBeginWeekday(String beginWeekday) {
		this.beginWeekday = beginWeekday;
	}

	public BigDecimal getCntMaxHrs() {
		return this.cntMaxHrs;
	}

	public void setCntMaxHrs(BigDecimal cntMaxHrs) {
		this.cntMaxHrs = cntMaxHrs;
	}

	public BigDecimal getCntMinHrs() {
		return this.cntMinHrs;
	}

	public void setCntMinHrs(BigDecimal cntMinHrs) {
		this.cntMinHrs = cntMinHrs;
	}

	public String getCntRszCatBel() {
		return this.cntRszCatBel;
	}

	public void setCntRszCatBel(String cntRszCatBel) {
		this.cntRszCatBel = cntRszCatBel;
	}

	public String getCntRszSubmBel() {
		return this.cntRszSubmBel;
	}

	public void setCntRszSubmBel(String cntRszSubmBel) {
		this.cntRszSubmBel = cntRszSubmBel;
	}

	public String getCntTemplateId() {
		return this.cntTemplateId;
	}

	public void setCntTemplateId(String cntTemplateId) {
		this.cntTemplateId = cntTemplateId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCommentshort() {
		return this.commentshort;
	}

	public void setCommentshort(String commentshort) {
		this.commentshort = commentshort;
	}

	public String getCompRateDescr() {
		return this.compRateDescr;
	}

	public void setCompRateDescr(String compRateDescr) {
		this.compRateDescr = compRateDescr;
	}

	public Date getContractBeginDt() {
		return this.contractBeginDate;
	}

	public void setContractBeginDt(Date contractBeginDate) {
		this.contractBeginDate = contractBeginDate;
	}

	public Date getContractEndDt() {
		return this.contractEndDate;
	}

	public void setContractEndDt(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getContractNum() {
		return this.contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getContractStatus() {
		return this.contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public Date getContrctExpEndDt() {
		return this.contrctExpEndDt;
	}

	public void setContrctExpEndDt(Date contrctExpEndDt) {
		this.contrctExpEndDt = contrctExpEndDt;
	}

	public String getContribIdEsp() {
		return this.contribIdEsp;
	}

	public void setContribIdEsp(String contribIdEsp) {
		this.contribIdEsp = contribIdEsp;
	}

	public String getDurationType() {
		return this.durationType;
	}

	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEndWeekday() {
		return this.endWeekday;
	}

	public void setEndWeekday(String endWeekday) {
		this.endWeekday = endWeekday;
	}

	public String getHireCtrCdEsp() {
		return this.hireCtrCdEsp;
	}

	public void setHireCtrCdEsp(String hireCtrCdEsp) {
		this.hireCtrCdEsp = hireCtrCdEsp;
	}

	public String getNeeProviderId() {
		return this.neeProviderId;
	}

	public void setNeeProviderId(String neeProviderId) {
		this.neeProviderId = neeProviderId;
	}

	public BigDecimal getProbationPeriod() {
		return this.probationPeriod;
	}

	public void setProbationPeriod(BigDecimal probationPeriod) {
		this.probationPeriod = probationPeriod;
	}

	public String getRedChrgCatBel() {
		return this.redChrgCatBel;
	}

	public void setRedChrgCatBel(String redChrgCatBel) {
		this.redChrgCatBel = redChrgCatBel;
	}

	public Date getRedchEnddtBel() {
		return this.redchEnddtBel;
	}

	public void setRedchEnddtBel(Date redchEnddtBel) {
		this.redchEnddtBel = redchEnddtBel;
	}

	public Date getRedchStartdtBel() {
		return this.redchStartdtBel;
	}

	public void setRedchStartdtBel(Date redchStartdtBel) {
		this.redchStartdtBel = redchStartdtBel;
	}

	public String getRegulatoryRegion() {
		return this.regulatoryRegion;
	}

	public void setRegulatoryRegion(String regulatoryRegion) {
		this.regulatoryRegion = regulatoryRegion;
	}

	public String getResponsibleId() {
		return this.responsibleId;
	}

	public void setResponsibleId(String responsibleId) {
		this.responsibleId = responsibleId;
	}

	public String getSchemeIdEsp() {
		return this.schemeIdEsp;
	}

	public void setSchemeIdEsp(String schemeIdEsp) {
		this.schemeIdEsp = schemeIdEsp;
	}

	public Date getSignatureDt() {
		return this.signatureDt;
	}

	public void setSignatureDt(Date signatureDt) {
		this.signatureDt = signatureDt;
	}

	public String getSocialBalanceBel() {
		return this.socialBalanceBel;
	}

	public void setSocialBalanceBel(String socialBalanceBel) {
		this.socialBalanceBel = socialBalanceBel;
	}

	public BigDecimal getVacationPeriod() {
		return this.vacationPeriod;
	}

	public void setVacationPeriod(BigDecimal vacationPeriod) {
		this.vacationPeriod = vacationPeriod;
	}

	public String getVacnPeriodUnit() {
		return this.vacnPeriodUnit;
	}

	public void setVacnPeriodUnit(String vacnPeriodUnit) {
		this.vacnPeriodUnit = vacnPeriodUnit;
	}

	public String getWaiveCompliance() {
		return this.waiveCompliance;
	}

	public void setWaiveCompliance(String waiveCompliance) {
		this.waiveCompliance = waiveCompliance;
	}

	public String getYearSw() {
		return this.yearSw;
	}

	public void setYearSw(String yearSw) {
		this.yearSw = yearSw;
	}

	public PsContractData findByEmployeeIdAndContractBeginDate(String employeeId) {
//	!----------------------------------------------------------------------
//	! Procedure:  HR07-Get-Contract-Data
//	! Desc:  This procedure retrieves the contract date from the PeopleSoft
//	!        Contract Data Table to send back to Option 7 of AAHR01.
//	!----------------------------------------------------------------------
//	Begin-Procedure HR07-Get-Contract-Data
//	Begin-Select
//	to_char(CCD7.CONTRACT_BEGIN_DT, 'YYYY-MM-DD')  &CCD7EFFDT
//	   Let $LegContractDate = &CCD7EFFDT
//	    !Format contract date so legacy will accept it (MM field, DD field and CCYY field)
//	    Unstring $LegContractDate by '-' into $LegContractDtYear $LegContractDtMonth $LegContractDtDay
//	 !select the maximum contract date to get the most current one because an employee can have more than 1 active contract
//	from PS_CONTRACT_DATA CCD7
//	where CCD7.Emplid = $PSEmplid
//	  and CCD7.CONTRACT_BEGIN_DT = (select MAX(CONTRACT_BEGIN_DT) from PS_CONTRACT_DATA CCD8
//	                     WHERE CCD8.EMPLID = CCD7.EMPLID
//	                       AND CCD8.CONTRACT_BEGIN_DT <= $AsofToday)
//	End-Select
//	End-Procedure HR07-Get-Contract-Data
		return null;
	}
	
}
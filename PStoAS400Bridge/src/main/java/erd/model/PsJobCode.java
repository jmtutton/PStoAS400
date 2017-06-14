package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the PS_JOBCODE_TBL database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_JOBCODE_TBL")
@NamedQuery(name="PsJobCode.findAll", query="SELECT p FROM PsJobCode p")
public class PsJobCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SETID", nullable=false, length=5)
	private String setId;

	@Column(name="JOBCODE", nullable=false, length=6)
	private String jobCode;

	@Column(name="ACTIVITY_TYPE_FRA", nullable=false, length=3)
	private String activityTypeFra;

	@Column(name="ANN_CNTACT_HRS_AUS", nullable=false, precision=6, scale=2)
	private BigDecimal annCntactHrsAus;

	@Column(name="ASCO_CD_AUS", nullable=false, length=6)
	private String ascoCdAus;

	@Column(name="BA_CD", nullable=false, length=3)
	private String baCd;

	@Column(name="BARG_UNIT", nullable=false, length=4)
	private String bargUnit;

	@Column(name="BPS_ACTIVITY_CD", nullable=false, length=6)
	private String bpsActivityCd;

	@Column(name="CAN_NOC_CD", nullable=false, length=4)
	private String canNocCd;

	@Column(name="CAN_PAYEQ_JOB_CLS", nullable=false, length=3)
	private String canPayeqJobCls;

	@Column(name="CASUAL_TYPE_AUS", nullable=false, length=1)
	private String casualTypeAus;

	@Column(name="COMP_FREQUENCY", nullable=false, length=5)
	private String compFrequency;

	@Column(name="company", nullable=false, length=3)
	private String company;

	@Column(name="CURRENCY_CD", nullable=false, length=3)
	private String currencyCd;

	@Column(name="DESCR", nullable=false, length=30)
	private String jobDescription;

	@Lob
	@Column(name="DESCRLONG")
	private String descrlong;

	@Column(name="DESCRSHORT", nullable=false, length=10)
	private String shortDescription;

	@Column(name="DIRECTLY_TIPPED", nullable=false, length=1)
	private String directlyTipped;

	@Column(name="EEO_JOB_GROUP", nullable=false, length=4)
	private String eeoJobGroup;

	@Column(name="EEO1CODE", nullable=false, length=1)
	private String eeo1code;

	@Column(name="EEO4CODE", nullable=false, length=1)
	private String eeo4code;

	@Column(name="EEO5CODE", nullable=false, length=2)
	private String eeo5code;

	@Column(name="EEO6CODE", nullable=false, length=1)
	private String eeo6code;

	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String statusAsOfEffectiveDate;

	@Column(name="EFFDT", nullable=false)
	private Date effectiveDate;

	@Column(name="EG_ACADEMIC_RANK", nullable=false, length=3)
	private String egAcademicRank;

	@Column(name="EG_GROUP", nullable=false, length=6)
	private String egGroup;

	@Column(name="ELECTION_GROUP", nullable=false, length=5)
	private String electionGroup;

	@Column(name="EMP_CATEGRY_FRA", nullable=false, length=3)
	private String empCategryFra;

	@Column(name="ENCUMB_SAL_AMT", nullable=false, precision=18, scale=3)
	private BigDecimal encumbSalAmt;

	@Column(name="ENCUMB_SAL_OPTN", nullable=false, length=3)
	private String encumbSalOptn;

	@Column(name="ENCUMBER_INDC", nullable=false, length=1)
	private String encumberIndc;

	@Column(name="FA_PAY_PROGRAM", nullable=false, length=3)
	private String faPayProgram;

	@Column(name="FLSA_STATUS", nullable=false, length=1)
	private String flsaStatus;

	@Column(name="FP_ATCH_AREA", nullable=false, length=4)
	private String fpAtchArea;

	@Column(name="FP_AUTO_RATFY", nullable=false, length=1)
	private String fpAutoRatfy;

	@Column(name="FP_BUSINESS_CD", nullable=false, length=4)
	private String fpBusinessCd;

	@Column(name="FP_JOB_FMLY", nullable=false, length=4)
	private String fpJobFmly;

	@Column(name="FP_JOB_TYP", nullable=false, length=2)
	private String fpJobTyp;

	@Column(name="FP_JOBCD_ACC", nullable=false, length=1)
	private String fpJobcdAcc;

	@Column(name="FP_MATRIX_CD", nullable=false, length=4)
	private String fpMatrixCd;

	@Column(name="FP_MATRIX_TYP", nullable=false, length=3)
	private String fpMatrixTyp;

	@Column(name="FP_OFF_COLAG", nullable=false, length=1)
	private String fpOffColag;

	@Column(name="FP_PROF_CATG", nullable=false, length=3)
	private String fpProfCatg;

	@Column(name="FUNCTION_AUS", nullable=false, length=1)
	private String functionAus;

	@Column(name="FUNCTION_CD", nullable=false, length=2)
	private String functionCd;

	@Column(name="GRADE", nullable=false, length=3)
	private String grade;

	@Column(name="GVT_CLASSIFIER_ID", nullable=false, length=11)
	private String gvtClassifierId;

	@Column(name="GVT_CLS_STANDARD", nullable=false, length=15)
	private String gvtClsStandard;

	@Column(name="GVT_DT_CLASSIFIED")
	private Date gvtDtClassified;

	@Column(name="GVT_EMP_FIN_INT", nullable=false, length=1)
	private String gvtEmpFinInt;

	@Column(name="GVT_EXEC_FIN_DISCL", nullable=false, length=1)
	private String gvtExecFinDiscl;

	@Column(name="GVT_FUNC_CLASS", nullable=false, length=2)
	private String gvtFuncClass;

	@Column(name="GVT_FUND_SOURCE", nullable=false, length=1)
	private String gvtFundSource;

	@Column(name="GVT_IA_ACTIONS", nullable=false, length=1)
	private String gvtIaActions;

	@Column(name="GVT_LEO_POSITION", nullable=false, length=1)
	private String gvtLeoPosition;

	@Column(name="GVT_NFC_FUNCTN_CD", nullable=false, length=2)
	private String gvtNfcFunctnCd;

	@Column(name="GVT_NFC_PI_IND_OVR", nullable=false, length=1)
	private String gvtNfcPiIndOvr;

	@Column(name="GVT_OCC_SERIES", nullable=false, length=4)
	private String gvtOccSeries;

	@Column(name="GVT_OFF_TITLEPREFX", nullable=false, length=2)
	private String gvtOffTitleprefx;

	@Column(name="GVT_OFFICIAL_DESCR", nullable=false, length=70)
	private String gvtOfficialDescr;

	@Column(name="GVT_OPM_CERT_NBR", nullable=false, length=8)
	private String gvtOpmCertNbr;

	@Column(name="GVT_ORG_TTL_CD", nullable=false, length=4)
	private String gvtOrgTtlCd;

	@Column(name="GVT_ORG_TTL_DESCR", nullable=false, length=70)
	private String gvtOrgTtlDescr;

	@Column(name="GVT_PAREN_TITLE", nullable=false, length=1)
	private String gvtParenTitle;

	@Column(name="GVT_PATCOB_CD", nullable=false, length=1)
	private String gvtPatcobCd;

	@Column(name="GVT_PAY_BASIS", nullable=false, length=2)
	private String gvtPayBasis;

	@Column(name="GVT_PAY_PLAN", nullable=false, length=2)
	private String gvtPayPlan;

	@Column(name="GVT_PERF_PLAN", nullable=false, length=8)
	private String gvtPerfPlan;

	@Column(name="GVT_PI_UPD_IND", nullable=false, length=1)
	private String gvtPiUpdInd;

	@Column(name="GVT_POI", nullable=false, length=4)
	private String gvtPoi;

	@Column(name="GVT_POSN_CLASS_STD", nullable=false, length=254)
	private String gvtPosnClassStd;

	@Column(name="GVT_POSN_SENS_CD", nullable=false, length=1)
	private String gvtPosnSensCd;

	@Column(name="GVT_POSN_TITLE_CD", nullable=false, length=4)
	private String gvtPosnTitleCd;

	@Column(name="GVT_SUB_AGENCY", nullable=false, length=2)
	private String gvtSubAgency;

	@Column(name="GVT_TARGET_GRADE", nullable=false, length=2)
	private String gvtTargetGrade;

	@Column(name="HP_STATS_DUTIES", nullable=false, length=2)
	private String hpStatsDuties;

	@Column(name="HP_STATS_RPT_FLAG", nullable=false, length=1)
	private String hpStatsRptFlag;

	@Column(name="INAIL_CODE", nullable=false, length=11)
	private String inailCode;

	@Column(name="INSEE_CD_FRA", nullable=false, length=4)
	private String inseeCdFra;

	@Column(name="IPEDSSCODE", nullable=false, length=1)
	private String ipedsscode;

	@Column(name="JOB_ACCNTAB_PCT", nullable=false, precision=4, scale=1)
	private BigDecimal jobAccntabPct;

	@Column(name="JOB_ACCNTAB_POINTS", nullable=false, precision=38)
	private BigDecimal jobAccntabPoints;

	@Column(name="JOB_CTG_FRA_CD", nullable=false, length=2)
	private String jobCtgFraCd;

	@Column(name="JOB_FAMILY", nullable=false, length=6)
	private String jobFamily;

	@Column(name="JOB_FUNCTION", nullable=false, length=3)
	private String jobFunction;

	@Column(name="JOB_KNOWHOW_PCT", nullable=false, precision=4, scale=1)
	private BigDecimal jobKnowhowPct;

	@Column(name="JOB_KNOWHOW_POINTS", nullable=false, precision=38)
	private BigDecimal jobKnowhowPoints;

	@Column(name="JOB_POINTS_TOTAL", nullable=false, precision=38)
	private BigDecimal jobPointsTotal;

	@Column(name="JOB_PROBSLV_PCT", nullable=false, precision=4, scale=1)
	private BigDecimal jobProbslvPct;

	@Column(name="JOB_PROBSLV_POINTS", nullable=false, precision=38)
	private BigDecimal jobProbslvPoints;

	@Column(name="JOB_SUB_FUNC", nullable=false, length=3)
	private String jobSubFunc;

	@Column(name="LABOR_AGREEMENT", nullable=false, length=6)
	private String laborAgreement;

	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="MANAGER_LEVEL", nullable=false, length=2)
	private String managerLevel;

	@Column(name="MED_CHKUP_REQ", nullable=false, length=1)
	private String medChkupReq;

	@Column(name="MED_SURV_REQ", nullable=false, length=1)
	private String medSurvReq;

	@Column(name="MIL_RANK", nullable=false, length=5)
	private String milRank;

	@Column(name="MILITARY_SERVICE", nullable=false, length=8)
	private String militaryService;

	@Column(name="PKG_RULE_ID", nullable=false, length=10)
	private String pkgRuleId;

	@Column(name="PKG_TEMPLATE_ID", nullable=false, length=10)
	private String pkgTemplateId;

	@Column(name="POSN_MGMT_INDC", nullable=false, length=1)
	private String posnMgmtIndc;

	@Column(name="REG_REGION", nullable=false, length=5)
	private String regulatoryRegion;

	@Column(name="REG_TEMP", nullable=false, length=1)
	private String regularOrTemporary;

	@Column(name="RETRO_PERCENT", nullable=false, precision=6, scale=4)
	private BigDecimal retroPercent;

	@Column(name="RETRO_RATE", nullable=false, precision=6, scale=4)
	private BigDecimal retroRate;

	@Column(name="SAL_ADMIN_PLAN", nullable=false, length=4)
	private String salAdminPlan;

	@Column(name="SAL_RANGE_CURRENCY", nullable=false, length=3)
	private String salRangeCurrency;

	@Column(name="SAL_RANGE_FREQ", nullable=false, length=5)
	private String salRangeFreq;

	@Column(name="SAL_RANGE_MAX_RATE", nullable=false, precision=18, scale=6)
	private BigDecimal salRangeMaxRate;

	@Column(name="SAL_RANGE_MID_RATE", nullable=false, precision=18, scale=6)
	private BigDecimal salRangeMidRate;

	@Column(name="SAL_RANGE_MIN_RATE", nullable=false, precision=18, scale=6)
	private BigDecimal salRangeMinRate;

	@Column(name="SEASONAL", nullable=false, length=1)
	private String seasonal;

	@Column(name="SETID_SALARY", nullable=false, length=5)
	private String setidSalary;

	@Column(name="STD_HOURS", nullable=false, precision=6, scale=2)
	private BigDecimal stdHours;

	@Column(name="STD_HRS_FREQUENCY", nullable=false, length=5)
	private String stdHrsFrequency;

	@Column(name="STEP", nullable=false, precision=38)
	private BigDecimal step;

	@Column(name="SURVEY_JOB_CODE", nullable=false, length=8)
	private String surveyJobCode;

	@Column(name="SURVEY_SALARY", nullable=false, precision=38)
	private BigDecimal surveySalary;

	@Column(name="TEACH_WEEKS_AUS", nullable=false, precision=38)
	private BigDecimal teachWeeksAus;

	@Column(name="TECHNICAL", nullable=false, length=1)
	private String technical;

	@Column(name="TRN_PROGRAM", nullable=false, length=6)
	private String trnProgram;

	@Column(name="UK_SOC_CD", nullable=false, length=2)
	private String ukSocCd;

	@Column(name="UNION_CD", nullable=false, length=3)
	private String unionCd;

	@Column(name="US_OCC_CD", nullable=false, length=3)
	private String usOccCd;

	@Column(name="US_SOC_CD", nullable=false, length=10)
	private String usSocCd;

	@Column(name="WORK_DAY_HOURS", nullable=false, precision=6, scale=2)
	private BigDecimal workDayHours;

	@Column(name="WORKERS_COMP_CD", nullable=false, length=4)
	private String workersCompCd;

	public PsJobCode() {
	}

	public String getActivityTypeFra() {
		return this.activityTypeFra;
	}

	public void setActivityTypeFra(String activityTypeFra) {
		this.activityTypeFra = activityTypeFra;
	}

	public BigDecimal getAnnCntactHrsAus() {
		return this.annCntactHrsAus;
	}

	public void setAnnCntactHrsAus(BigDecimal annCntactHrsAus) {
		this.annCntactHrsAus = annCntactHrsAus;
	}

	public String getAscoCdAus() {
		return this.ascoCdAus;
	}

	public void setAscoCdAus(String ascoCdAus) {
		this.ascoCdAus = ascoCdAus;
	}

	public String getBaCd() {
		return this.baCd;
	}

	public void setBaCd(String baCd) {
		this.baCd = baCd;
	}

	public String getBargUnit() {
		return this.bargUnit;
	}

	public void setBargUnit(String bargUnit) {
		this.bargUnit = bargUnit;
	}

	public String getBpsActivityCd() {
		return this.bpsActivityCd;
	}

	public void setBpsActivityCd(String bpsActivityCd) {
		this.bpsActivityCd = bpsActivityCd;
	}

	public String getCanNocCd() {
		return this.canNocCd;
	}

	public void setCanNocCd(String canNocCd) {
		this.canNocCd = canNocCd;
	}

	public String getCanPayeqJobCls() {
		return this.canPayeqJobCls;
	}

	public void setCanPayeqJobCls(String canPayeqJobCls) {
		this.canPayeqJobCls = canPayeqJobCls;
	}

	public String getCasualTypeAus() {
		return this.casualTypeAus;
	}

	public void setCasualTypeAus(String casualTypeAus) {
		this.casualTypeAus = casualTypeAus;
	}

	public String getCompFrequency() {
		return this.compFrequency;
	}

	public void setCompFrequency(String compFrequency) {
		this.compFrequency = compFrequency;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCurrencyCd() {
		return this.currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}

	public String getJobDescription() {
		return this.jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getDescrlong() {
		return this.descrlong;
	}

	public void setDescrlong(String descrlong) {
		this.descrlong = descrlong;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDirectlyTipped() {
		return this.directlyTipped;
	}

	public void setDirectlyTipped(String directlyTipped) {
		this.directlyTipped = directlyTipped;
	}

	public String getEeoJobGroup() {
		return this.eeoJobGroup;
	}

	public void setEeoJobGroup(String eeoJobGroup) {
		this.eeoJobGroup = eeoJobGroup;
	}

	public String getEeo1code() {
		return this.eeo1code;
	}

	public void setEeo1code(String eeo1code) {
		this.eeo1code = eeo1code;
	}

	public String getEeo4code() {
		return this.eeo4code;
	}

	public void setEeo4code(String eeo4code) {
		this.eeo4code = eeo4code;
	}

	public String getEeo5code() {
		return this.eeo5code;
	}

	public void setEeo5code(String eeo5code) {
		this.eeo5code = eeo5code;
	}

	public String getEeo6code() {
		return this.eeo6code;
	}

	public void setEeo6code(String eeo6code) {
		this.eeo6code = eeo6code;
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

	public String getEgAcademicRank() {
		return this.egAcademicRank;
	}

	public void setEgAcademicRank(String egAcademicRank) {
		this.egAcademicRank = egAcademicRank;
	}

	public String getEgGroup() {
		return this.egGroup;
	}

	public void setEgGroup(String egGroup) {
		this.egGroup = egGroup;
	}

	public String getElectionGroup() {
		return this.electionGroup;
	}

	public void setElectionGroup(String electionGroup) {
		this.electionGroup = electionGroup;
	}

	public String getEmpCategryFra() {
		return this.empCategryFra;
	}

	public void setEmpCategryFra(String empCategryFra) {
		this.empCategryFra = empCategryFra;
	}

	public BigDecimal getEncumbSalAmt() {
		return this.encumbSalAmt;
	}

	public void setEncumbSalAmt(BigDecimal encumbSalAmt) {
		this.encumbSalAmt = encumbSalAmt;
	}

	public String getEncumbSalOptn() {
		return this.encumbSalOptn;
	}

	public void setEncumbSalOptn(String encumbSalOptn) {
		this.encumbSalOptn = encumbSalOptn;
	}

	public String getEncumberIndc() {
		return this.encumberIndc;
	}

	public void setEncumberIndc(String encumberIndc) {
		this.encumberIndc = encumberIndc;
	}

	public String getFaPayProgram() {
		return this.faPayProgram;
	}

	public void setFaPayProgram(String faPayProgram) {
		this.faPayProgram = faPayProgram;
	}

	public String getFlsaStatus() {
		return this.flsaStatus;
	}

	public void setFlsaStatus(String flsaStatus) {
		this.flsaStatus = flsaStatus;
	}

	public String getFpAtchArea() {
		return this.fpAtchArea;
	}

	public void setFpAtchArea(String fpAtchArea) {
		this.fpAtchArea = fpAtchArea;
	}

	public String getFpAutoRatfy() {
		return this.fpAutoRatfy;
	}

	public void setFpAutoRatfy(String fpAutoRatfy) {
		this.fpAutoRatfy = fpAutoRatfy;
	}

	public String getFpBusinessCd() {
		return this.fpBusinessCd;
	}

	public void setFpBusinessCd(String fpBusinessCd) {
		this.fpBusinessCd = fpBusinessCd;
	}

	public String getFpJobFmly() {
		return this.fpJobFmly;
	}

	public void setFpJobFmly(String fpJobFmly) {
		this.fpJobFmly = fpJobFmly;
	}

	public String getFpJobTyp() {
		return this.fpJobTyp;
	}

	public void setFpJobTyp(String fpJobTyp) {
		this.fpJobTyp = fpJobTyp;
	}

	public String getFpJobcdAcc() {
		return this.fpJobcdAcc;
	}

	public void setFpJobcdAcc(String fpJobcdAcc) {
		this.fpJobcdAcc = fpJobcdAcc;
	}

	public String getFpMatrixCd() {
		return this.fpMatrixCd;
	}

	public void setFpMatrixCd(String fpMatrixCd) {
		this.fpMatrixCd = fpMatrixCd;
	}

	public String getFpMatrixTyp() {
		return this.fpMatrixTyp;
	}

	public void setFpMatrixTyp(String fpMatrixTyp) {
		this.fpMatrixTyp = fpMatrixTyp;
	}

	public String getFpOffColag() {
		return this.fpOffColag;
	}

	public void setFpOffColag(String fpOffColag) {
		this.fpOffColag = fpOffColag;
	}

	public String getFpProfCatg() {
		return this.fpProfCatg;
	}

	public void setFpProfCatg(String fpProfCatg) {
		this.fpProfCatg = fpProfCatg;
	}

	public String getFunctionAus() {
		return this.functionAus;
	}

	public void setFunctionAus(String functionAus) {
		this.functionAus = functionAus;
	}

	public String getFunctionCd() {
		return this.functionCd;
	}

	public void setFunctionCd(String functionCd) {
		this.functionCd = functionCd;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGvtClassifierId() {
		return this.gvtClassifierId;
	}

	public void setGvtClassifierId(String gvtClassifierId) {
		this.gvtClassifierId = gvtClassifierId;
	}

	public String getGvtClsStandard() {
		return this.gvtClsStandard;
	}

	public void setGvtClsStandard(String gvtClsStandard) {
		this.gvtClsStandard = gvtClsStandard;
	}

	public Date getGvtDtClassified() {
		return this.gvtDtClassified;
	}

	public void setGvtDtClassified(Date gvtDtClassified) {
		this.gvtDtClassified = gvtDtClassified;
	}

	public String getGvtEmpFinInt() {
		return this.gvtEmpFinInt;
	}

	public void setGvtEmpFinInt(String gvtEmpFinInt) {
		this.gvtEmpFinInt = gvtEmpFinInt;
	}

	public String getGvtExecFinDiscl() {
		return this.gvtExecFinDiscl;
	}

	public void setGvtExecFinDiscl(String gvtExecFinDiscl) {
		this.gvtExecFinDiscl = gvtExecFinDiscl;
	}

	public String getGvtFuncClass() {
		return this.gvtFuncClass;
	}

	public void setGvtFuncClass(String gvtFuncClass) {
		this.gvtFuncClass = gvtFuncClass;
	}

	public String getGvtFundSource() {
		return this.gvtFundSource;
	}

	public void setGvtFundSource(String gvtFundSource) {
		this.gvtFundSource = gvtFundSource;
	}

	public String getGvtIaActions() {
		return this.gvtIaActions;
	}

	public void setGvtIaActions(String gvtIaActions) {
		this.gvtIaActions = gvtIaActions;
	}

	public String getGvtLeoPosition() {
		return this.gvtLeoPosition;
	}

	public void setGvtLeoPosition(String gvtLeoPosition) {
		this.gvtLeoPosition = gvtLeoPosition;
	}

	public String getGvtNfcFunctnCd() {
		return this.gvtNfcFunctnCd;
	}

	public void setGvtNfcFunctnCd(String gvtNfcFunctnCd) {
		this.gvtNfcFunctnCd = gvtNfcFunctnCd;
	}

	public String getGvtNfcPiIndOvr() {
		return this.gvtNfcPiIndOvr;
	}

	public void setGvtNfcPiIndOvr(String gvtNfcPiIndOvr) {
		this.gvtNfcPiIndOvr = gvtNfcPiIndOvr;
	}

	public String getGvtOccSeries() {
		return this.gvtOccSeries;
	}

	public void setGvtOccSeries(String gvtOccSeries) {
		this.gvtOccSeries = gvtOccSeries;
	}

	public String getGvtOffTitleprefx() {
		return this.gvtOffTitleprefx;
	}

	public void setGvtOffTitleprefx(String gvtOffTitleprefx) {
		this.gvtOffTitleprefx = gvtOffTitleprefx;
	}

	public String getGvtOfficialDescr() {
		return this.gvtOfficialDescr;
	}

	public void setGvtOfficialDescr(String gvtOfficialDescr) {
		this.gvtOfficialDescr = gvtOfficialDescr;
	}

	public String getGvtOpmCertNbr() {
		return this.gvtOpmCertNbr;
	}

	public void setGvtOpmCertNbr(String gvtOpmCertNbr) {
		this.gvtOpmCertNbr = gvtOpmCertNbr;
	}

	public String getGvtOrgTtlCd() {
		return this.gvtOrgTtlCd;
	}

	public void setGvtOrgTtlCd(String gvtOrgTtlCd) {
		this.gvtOrgTtlCd = gvtOrgTtlCd;
	}

	public String getGvtOrgTtlDescr() {
		return this.gvtOrgTtlDescr;
	}

	public void setGvtOrgTtlDescr(String gvtOrgTtlDescr) {
		this.gvtOrgTtlDescr = gvtOrgTtlDescr;
	}

	public String getGvtParenTitle() {
		return this.gvtParenTitle;
	}

	public void setGvtParenTitle(String gvtParenTitle) {
		this.gvtParenTitle = gvtParenTitle;
	}

	public String getGvtPatcobCd() {
		return this.gvtPatcobCd;
	}

	public void setGvtPatcobCd(String gvtPatcobCd) {
		this.gvtPatcobCd = gvtPatcobCd;
	}

	public String getGvtPayBasis() {
		return this.gvtPayBasis;
	}

	public void setGvtPayBasis(String gvtPayBasis) {
		this.gvtPayBasis = gvtPayBasis;
	}

	public String getGvtPayPlan() {
		return this.gvtPayPlan;
	}

	public void setGvtPayPlan(String gvtPayPlan) {
		this.gvtPayPlan = gvtPayPlan;
	}

	public String getGvtPerfPlan() {
		return this.gvtPerfPlan;
	}

	public void setGvtPerfPlan(String gvtPerfPlan) {
		this.gvtPerfPlan = gvtPerfPlan;
	}

	public String getGvtPiUpdInd() {
		return this.gvtPiUpdInd;
	}

	public void setGvtPiUpdInd(String gvtPiUpdInd) {
		this.gvtPiUpdInd = gvtPiUpdInd;
	}

	public String getGvtPoi() {
		return this.gvtPoi;
	}

	public void setGvtPoi(String gvtPoi) {
		this.gvtPoi = gvtPoi;
	}

	public String getGvtPosnClassStd() {
		return this.gvtPosnClassStd;
	}

	public void setGvtPosnClassStd(String gvtPosnClassStd) {
		this.gvtPosnClassStd = gvtPosnClassStd;
	}

	public String getGvtPosnSensCd() {
		return this.gvtPosnSensCd;
	}

	public void setGvtPosnSensCd(String gvtPosnSensCd) {
		this.gvtPosnSensCd = gvtPosnSensCd;
	}

	public String getGvtPosnTitleCd() {
		return this.gvtPosnTitleCd;
	}

	public void setGvtPosnTitleCd(String gvtPosnTitleCd) {
		this.gvtPosnTitleCd = gvtPosnTitleCd;
	}

	public String getGvtSubAgency() {
		return this.gvtSubAgency;
	}

	public void setGvtSubAgency(String gvtSubAgency) {
		this.gvtSubAgency = gvtSubAgency;
	}

	public String getGvtTargetGrade() {
		return this.gvtTargetGrade;
	}

	public void setGvtTargetGrade(String gvtTargetGrade) {
		this.gvtTargetGrade = gvtTargetGrade;
	}

	public String getHpStatsDuties() {
		return this.hpStatsDuties;
	}

	public void setHpStatsDuties(String hpStatsDuties) {
		this.hpStatsDuties = hpStatsDuties;
	}

	public String getHpStatsRptFlag() {
		return this.hpStatsRptFlag;
	}

	public void setHpStatsRptFlag(String hpStatsRptFlag) {
		this.hpStatsRptFlag = hpStatsRptFlag;
	}

	public String getInailCode() {
		return this.inailCode;
	}

	public void setInailCode(String inailCode) {
		this.inailCode = inailCode;
	}

	public String getInseeCdFra() {
		return this.inseeCdFra;
	}

	public void setInseeCdFra(String inseeCdFra) {
		this.inseeCdFra = inseeCdFra;
	}

	public String getIpedsscode() {
		return this.ipedsscode;
	}

	public void setIpedsscode(String ipedsscode) {
		this.ipedsscode = ipedsscode;
	}

	public BigDecimal getJobAccntabPct() {
		return this.jobAccntabPct;
	}

	public void setJobAccntabPct(BigDecimal jobAccntabPct) {
		this.jobAccntabPct = jobAccntabPct;
	}

	public BigDecimal getJobAccntabPoints() {
		return this.jobAccntabPoints;
	}

	public void setJobAccntabPoints(BigDecimal jobAccntabPoints) {
		this.jobAccntabPoints = jobAccntabPoints;
	}

	public String getJobCtgFraCd() {
		return this.jobCtgFraCd;
	}

	public void setJobCtgFraCd(String jobCtgFraCd) {
		this.jobCtgFraCd = jobCtgFraCd;
	}

	public String getJobFamily() {
		return this.jobFamily;
	}

	public void setJobFamily(String jobFamily) {
		this.jobFamily = jobFamily;
	}

	public String getJobFunction() {
		return this.jobFunction;
	}

	public void setJobFunction(String jobFunction) {
		this.jobFunction = jobFunction;
	}

	public BigDecimal getJobKnowhowPct() {
		return this.jobKnowhowPct;
	}

	public void setJobKnowhowPct(BigDecimal jobKnowhowPct) {
		this.jobKnowhowPct = jobKnowhowPct;
	}

	public BigDecimal getJobKnowhowPoints() {
		return this.jobKnowhowPoints;
	}

	public void setJobKnowhowPoints(BigDecimal jobKnowhowPoints) {
		this.jobKnowhowPoints = jobKnowhowPoints;
	}

	public BigDecimal getJobPointsTotal() {
		return this.jobPointsTotal;
	}

	public void setJobPointsTotal(BigDecimal jobPointsTotal) {
		this.jobPointsTotal = jobPointsTotal;
	}

	public BigDecimal getJobProbslvPct() {
		return this.jobProbslvPct;
	}

	public void setJobProbslvPct(BigDecimal jobProbslvPct) {
		this.jobProbslvPct = jobProbslvPct;
	}

	public BigDecimal getJobProbslvPoints() {
		return this.jobProbslvPoints;
	}

	public void setJobProbslvPoints(BigDecimal jobProbslvPoints) {
		this.jobProbslvPoints = jobProbslvPoints;
	}

	public String getJobSubFunc() {
		return this.jobSubFunc;
	}

	public void setJobSubFunc(String jobSubFunc) {
		this.jobSubFunc = jobSubFunc;
	}

	public String getJobcode() {
		return this.jobCode;
	}

	public void setJobcode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getLaborAgreement() {
		return this.laborAgreement;
	}

	public void setLaborAgreement(String laborAgreement) {
		this.laborAgreement = laborAgreement;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
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

	public String getManagerLevel() {
		return this.managerLevel;
	}

	public void setManagerLevel(String managerLevel) {
		this.managerLevel = managerLevel;
	}

	public String getMedChkupReq() {
		return this.medChkupReq;
	}

	public void setMedChkupReq(String medChkupReq) {
		this.medChkupReq = medChkupReq;
	}

	public String getMedSurvReq() {
		return this.medSurvReq;
	}

	public void setMedSurvReq(String medSurvReq) {
		this.medSurvReq = medSurvReq;
	}

	public String getMilRank() {
		return this.milRank;
	}

	public void setMilRank(String milRank) {
		this.milRank = milRank;
	}

	public String getMilitaryService() {
		return this.militaryService;
	}

	public void setMilitaryService(String militaryService) {
		this.militaryService = militaryService;
	}

	public String getPkgRuleId() {
		return this.pkgRuleId;
	}

	public void setPkgRuleId(String pkgRuleId) {
		this.pkgRuleId = pkgRuleId;
	}

	public String getPkgTemplateId() {
		return this.pkgTemplateId;
	}

	public void setPkgTemplateId(String pkgTemplateId) {
		this.pkgTemplateId = pkgTemplateId;
	}

	public String getPosnMgmtIndc() {
		return this.posnMgmtIndc;
	}

	public void setPosnMgmtIndc(String posnMgmtIndc) {
		this.posnMgmtIndc = posnMgmtIndc;
	}

	public String getRegulatoryRegion() {
		return this.regulatoryRegion;
	}

	public void setRegulatoryRegion(String regulatoryRegion) {
		this.regulatoryRegion = regulatoryRegion;
	}

	public String getRegTemp() {
		return this.regularOrTemporary;
	}

	public void setRegTemp(String regularOrTemporary) {
		this.regularOrTemporary = regularOrTemporary;
	}

	public BigDecimal getRetroPercent() {
		return this.retroPercent;
	}

	public void setRetroPercent(BigDecimal retroPercent) {
		this.retroPercent = retroPercent;
	}

	public BigDecimal getRetroRate() {
		return this.retroRate;
	}

	public void setRetroRate(BigDecimal retroRate) {
		this.retroRate = retroRate;
	}

	public String getSalAdminPlan() {
		return this.salAdminPlan;
	}

	public void setSalAdminPlan(String salAdminPlan) {
		this.salAdminPlan = salAdminPlan;
	}

	public String getSalRangeCurrency() {
		return this.salRangeCurrency;
	}

	public void setSalRangeCurrency(String salRangeCurrency) {
		this.salRangeCurrency = salRangeCurrency;
	}

	public String getSalRangeFreq() {
		return this.salRangeFreq;
	}

	public void setSalRangeFreq(String salRangeFreq) {
		this.salRangeFreq = salRangeFreq;
	}

	public BigDecimal getSalRangeMaxRate() {
		return this.salRangeMaxRate;
	}

	public void setSalRangeMaxRate(BigDecimal salRangeMaxRate) {
		this.salRangeMaxRate = salRangeMaxRate;
	}

	public BigDecimal getSalRangeMidRate() {
		return this.salRangeMidRate;
	}

	public void setSalRangeMidRate(BigDecimal salRangeMidRate) {
		this.salRangeMidRate = salRangeMidRate;
	}

	public BigDecimal getSalRangeMinRate() {
		return this.salRangeMinRate;
	}

	public void setSalRangeMinRate(BigDecimal salRangeMinRate) {
		this.salRangeMinRate = salRangeMinRate;
	}

	public String getSeasonal() {
		return this.seasonal;
	}

	public void setSeasonal(String seasonal) {
		this.seasonal = seasonal;
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

	public BigDecimal getStdHours() {
		return this.stdHours;
	}

	public void setStdHours(BigDecimal stdHours) {
		this.stdHours = stdHours;
	}

	public String getStdHrsFrequency() {
		return this.stdHrsFrequency;
	}

	public void setStdHrsFrequency(String stdHrsFrequency) {
		this.stdHrsFrequency = stdHrsFrequency;
	}

	public BigDecimal getStep() {
		return this.step;
	}

	public void setStep(BigDecimal step) {
		this.step = step;
	}

	public String getSurveyJobCode() {
		return this.surveyJobCode;
	}

	public void setSurveyJobCode(String surveyJobCode) {
		this.surveyJobCode = surveyJobCode;
	}

	public BigDecimal getSurveySalary() {
		return this.surveySalary;
	}

	public void setSurveySalary(BigDecimal surveySalary) {
		this.surveySalary = surveySalary;
	}

	public BigDecimal getTeachWeeksAus() {
		return this.teachWeeksAus;
	}

	public void setTeachWeeksAus(BigDecimal teachWeeksAus) {
		this.teachWeeksAus = teachWeeksAus;
	}

	public String getTechnical() {
		return this.technical;
	}

	public void setTechnical(String technical) {
		this.technical = technical;
	}

	public String getTrnProgram() {
		return this.trnProgram;
	}

	public void setTrnProgram(String trnProgram) {
		this.trnProgram = trnProgram;
	}

	public String getUkSocCd() {
		return this.ukSocCd;
	}

	public void setUkSocCd(String ukSocCd) {
		this.ukSocCd = ukSocCd;
	}

	public String getUnionCd() {
		return this.unionCd;
	}

	public void setUnionCd(String unionCd) {
		this.unionCd = unionCd;
	}

	public String getUsOccCd() {
		return this.usOccCd;
	}

	public void setUsOccCd(String usOccCd) {
		this.usOccCd = usOccCd;
	}

	public String getUsSocCd() {
		return this.usSocCd;
	}

	public void setUsSocCd(String usSocCd) {
		this.usSocCd = usSocCd;
	}

	public BigDecimal getWorkDayHours() {
		return this.workDayHours;
	}

	public void setWorkDayHours(BigDecimal workDayHours) {
		this.workDayHours = workDayHours;
	}

	public String getWorkersCompCd() {
		return this.workersCompCd;
	}

	public void setWorkersCompCd(String workersCompCd) {
		this.workersCompCd = workersCompCd;
	}

	public PsJobCode findByJobCode(String jobCode) {
		return null;
	}

	/**
	 * Replaces AD-Get-Job-Description from ZHRI100A.SQR
	 * Gets the job description for Active Directory File Build
	 */
	public String findJobDescriptionByJobCode(String jobCode) {
//		Begin-Select
//		AD9.JOBCODE
//		AD9.DESCR
//		 let $PSJobDescription = ltrim(rtrim(&AD9.DESCR,' '),' ')
//		from PS_JOBCODE_TBL AD9
//		where AD9.JOBCODE = $PSJobcode
//		End-select
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    		    "SELECT p.jobDescription FROM PsJobCode p WHERE p.sequenceNumber = :jobCode", String.class)
	    		    .setParameter("jobCode", jobCode)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0).trim();
	    	}
	    } 
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
	}

}
package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the PS_JOB database table.
 * Contains job records along with other data. Effective dated someone's job history will be stored in this table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_JOB")
@NamedQuery(name="PsJob.findAll", query="SELECT p FROM PsJob p")
public class PsJob implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="EMPL_RCD", nullable=false, precision=38)
	private BigDecimal employmentRecordNumber;

	@Column(name="ABSENCE_SYSTEM_CD", nullable=false, length=3)
	private String absenceSystemCd;

	@Column(name="ACCDNT_CD_FRA", nullable=false, length=1)
	private String accdntCdFra;

	@Column(name="ACCT_CD", nullable=false, length=25)
	private String acctCd;

	@Column(name="\"ACTION\"", nullable=false, length=3)
	private String action;

	@Column(name="ACTION_DT")
	@Temporal(TemporalType.DATE)
	private Date actionDt;

	@Column(name="ACTION_REASON", nullable=false, length=3)
	private String actionReason;

	@Column(name="ADDS_TO_FTE_ACTUAL", nullable=false, length=1)
	private String addsToFteActual;

	@Column(name="ANNL_BENEF_BASE_RT", nullable=false, precision=18, scale=3)
	private BigDecimal annlBenefBaseRt;

	@Column(name="ANNUAL_RT", nullable=false, precision=18, scale=3)
	private BigDecimal annualRt;

	@Column(name="APPT_TYPE", nullable=false, length=1)
	private String apptType;

	@Column(name="ASGN_END_DT")
	@Temporal(TemporalType.DATE)
	private Date asgnEndDt;

	@Column(name="ASGN_START_DT")
	@Temporal(TemporalType.DATE)
	private Date asgnStartDt;

	@Column(name="AUTO_END_FLG", nullable=false, length=1)
	private String autoEndFlg;

	@Column(name="BARG_UNIT", nullable=false, length=4)
	private String bargUnit;

	@Column(name="BAS_ACTION", nullable=false, length=3)
	private String basAction;

	@Column(name="BAS_GROUP_ID", nullable=false, length=3)
	private String basGroupId;

	@Column(name="BEN_STATUS", nullable=false, length=4)
	private String benStatus;

	@Column(name="BENEFIT_SYSTEM", nullable=false, length=2)
	private String benefitSystem;

	@Column(name="BORDER_WALKER", nullable=false, length=1)
	private String borderWalker;

	@Column(name="BUSINESS_UNIT", nullable=false, length=5)
	private String businessUnit;

	@Column(name="CHANGE_AMT", nullable=false, precision=18, scale=6)
	private BigDecimal changeAmt;

	@Column(name="CHANGE_PCT", nullable=false, precision=6, scale=3)
	private BigDecimal changePct;

	@Column(name="CLASS_INDC", nullable=false, length=1)
	private String classIndc;

	@Column(name="COBRA_ACTION", nullable=false, length=3)
	private String cobraAction;

	@Column(name="COMP_FREQUENCY", nullable=false, length=5)
	private String compFrequency;

	@Column(name="COMPANY", nullable=false, length=3)
	private String company;

	@Column(name="COMPRATE", nullable=false, precision=18, scale=6)
	private BigDecimal comprate;

	@Column(name="CONTRACT_NUM", nullable=false, length=25)
	private String contractNum;

	@Column(name="CTG_RATE", nullable=false, precision=38)
	private BigDecimal ctgRate;

	@Column(name="CUR_RT_TYPE", nullable=false, length=5)
	private String curRtType;

	@Column(name="CURRENCY_CD", nullable=false, length=3)
	private String currencyCd;

	@Column(name="CURRENCY_CD1", nullable=false, length=3)
	private String currencyCd1;

	@Column(name="DAILY_RT", nullable=false, precision=18, scale=3)
	private BigDecimal dailyRt;

	@Column(name="DEPT_ENTRY_DT")
	@Temporal(TemporalType.DATE)
	private Date deptEntryDt;

	@Column(name="DEPTID", nullable=false, length=10)
	private String departmentId;

	@Column(name="DIRECTLY_TIPPED", nullable=false, length=1)
	private String directlyTipped;

	@Column(name="EARNS_DIST_TYPE", nullable=false, length=1)
	private String earnsDistType;

	@Column(name="EEO_CLASS", nullable=false, length=1)
	private String eeoClass;

	@Column(name="EFFDT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="EFFSEQ", nullable=false, precision=38)
	private BigDecimal effectiveSequence;

	@Column(name="ELIG_CONFIG1", nullable=false, length=10)
	private String eligConfig1;

	@Column(name="ELIG_CONFIG2", nullable=false, length=10)
	private String eligConfig2;

	@Column(name="ELIG_CONFIG3", nullable=false, length=10)
	private String eligConfig3;

	@Column(name="ELIG_CONFIG4", nullable=false, length=10)
	private String eligConfig4;

	@Column(name="ELIG_CONFIG5", nullable=false, length=10)
	private String eligConfig5;

	@Column(name="ELIG_CONFIG6", nullable=false, length=10)
	private String eligConfig6;

	@Column(name="ELIG_CONFIG7", nullable=false, length=10)
	private String eligConfig7;

	@Column(name="ELIG_CONFIG8", nullable=false, length=10)
	private String eligConfig8;

	@Column(name="ELIG_CONFIG9", nullable=false, length=10)
	private String eligConfig9;

	@Column(name="EMPL_CLASS", nullable=false, length=3)
	private String employeeClass;

	@Column(name="EMPL_CTG", nullable=false, length=6)
	private String emplCtg;

	@Column(name="EMPL_CTG_L1", nullable=false, length=6)
	private String emplCtgL1;

	@Column(name="EMPL_CTG_L2", nullable=false, length=6)
	private String emplCtgL2;

	@Column(name="EMPL_STATUS", nullable=false, length=1)
	private String emplStatus;

	@Column(name="EMPL_TYPE", nullable=false, length=1)
	private String emplType;

	@Column(name="ENCUMB_OVERRIDE", nullable=false, length=1)
	private String encumbOverride;

	@Column(name="ENTRY_DATE")
	@Temporal(TemporalType.DATE)
	private Date entryDate;

	@Column(name="ESTABID", nullable=false, length=12)
	private String estabid;

	@Column(name="EXEMPT_HOURS_MONTH", nullable=false, precision=38)
	private BigDecimal exemptHoursMonth;

	@Column(name="EXEMPT_JOB_LBR", nullable=false, length=1)
	private String exemptJobLbr;

	@Column(name="EXPECTED_END_DATE")
	@Temporal(TemporalType.DATE)
	private Date expectedEndDate;

	@Column(name="EXPECTED_RETURN_DT")
	@Temporal(TemporalType.DATE)
	private Date expectedReturnDt;

	@Column(name="FICA_STATUS_EE", nullable=false, length=1)
	private String ficaStatusEe;

	@Column(name="FLSA_STATUS", nullable=false, length=1)
	private String flsaStatus;

	@Column(name="FORCE_PUBLISH")
	@Temporal(TemporalType.DATE)
	private Date forcePublish;

	@Column(name="FTE", nullable=false, precision=7, scale=6)
	private BigDecimal fte;

	@Column(name="FULL_PART_TIME", nullable=false, length=1)
	private String fullOrPartTime;

	@Column(name="FUNCTION_CD", nullable=false, length=2)
	private String functionCd;

	@Column(name="GL_PAY_TYPE", nullable=false, length=6)
	private String glPayType;

	@Column(name="GP_ASOF_DT_EXG_RT", nullable=false, length=1)
	private String gpAsofDtExgRt;

	@Column(name="GP_DFLT_CURRTTYP", nullable=false, length=1)
	private String gpDfltCurrttyp;

	@Column(name="GP_DFLT_ELIG_GRP", nullable=false, length=1)
	private String gpDfltEligGrp;

	@Column(name="GP_DFLT_EXRTDT", nullable=false, length=1)
	private String gpDfltExrtdt;

	@Column(name="GP_ELIG_GRP", nullable=false, length=10)
	private String gpEligGrp;

	@Column(name="GP_PAYGROUP", nullable=false, length=10)
	private String gpPaygroup;

	@Column(name="GRADE", nullable=false, length=3)
	private String grade;

	@Column(name="GRADE_ENTRY_DT")
	@Temporal(TemporalType.DATE)
	private Date gradeEntryDt;

	@Column(name="HIRE_DT")
	@Temporal(TemporalType.DATE)
	private Date hireDt;

	@Column(name="HOLIDAY_SCHEDULE", nullable=false, length=6)
	private String holidaySchedule;

	@Column(name="HOURLY_RT", nullable=false, precision=18, scale=6)
	private BigDecimal hourlyRt;

	@Column(name="HOURLY_RT_FRA", nullable=false, length=3)
	private String hourlyRtFra;

	@Column(name="HR_STATUS", nullable=false, length=1)
	private String hrStatus;

	@Column(name="INTERCTR_WRKS_CNCL", nullable=false, length=1)
	private String interctrWrksCncl;

	@Column(name="JOB_DATA_SRC_CD", nullable=false, length=3)
	private String jobDataSrcCd;

	@Column(name="JOB_ENTRY_DT")
	@Temporal(TemporalType.DATE)
	private Date jobEntryDt;

	@Column(name="JOB_INDICATOR", nullable=false, length=1)
	private String jobIndicator;

	@Column(name="JOBCODE", nullable=false, length=6)
	private String jobCode;

	@Column(name="LABOR_AGREEMENT", nullable=false, length=6)
	private String laborAgreement;

	@Column(name="LABOR_FACILITY_ID", nullable=false, length=10)
	private String laborFacilityId;

	@Column(name="LABOR_TYPE_GER", nullable=false, length=1)
	private String laborTypeGer;

	@Column(name="LAST_DATE_WORKED")
	@Temporal(TemporalType.DATE)
	private Date lastDateWorked;

	@Column(name="LAST_HIRE_DT")
	@Temporal(TemporalType.DATE)
	private Date lastHireDt;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="LAYOFF_EXEMPT_FLAG", nullable=false, length=1)
	private String layoffExemptFlag;

	@Column(name="LAYOFF_EXEMPT_RSN", nullable=false, length=11)
	private String layoffExemptRsn;

	@Column(name="LBR_FAC_ENTRY_DT")
	@Temporal(TemporalType.DATE)
	private Date lbrFacEntryDt;

	@Column(name="LDW_OVR", nullable=false, length=1)
	private String ldwOvr;

	@Column(name="LOCATION", nullable=false, length=10)
	private String location;

	@Column(name="LST_ASGN_START_DT")
	@Temporal(TemporalType.DATE)
	private Date lstAsgnStartDt;

	@Column(name="LUMP_SUM_PAY", nullable=false, length=1)
	private String lumpSumPay;

	@Column(name="MAIN_APPT_NUM_JPN", nullable=false, precision=38)
	private BigDecimal mainApptNumJpn;

	@Column(name="MATRICULA_NBR", nullable=false, precision=38)
	private BigDecimal matriculaNbr;

	@Column(name="MONTHLY_RT", nullable=false, precision=18, scale=3)
	private BigDecimal monthlyRt;

	@Column(name="OFFICER_CD", nullable=false, length=1)
	private String officerCd;

	@Column(name="PAID_FTE", nullable=false, precision=7, scale=6)
	private BigDecimal paidFte;

	@Column(name="PAID_HOURS", nullable=false, precision=6, scale=2)
	private BigDecimal paidHours;

	@Column(name="PAID_HRS_FREQUENCY", nullable=false, length=5)
	private String paidHrsFrequency;

	@Column(name="PAY_SYSTEM_FLG", nullable=false, length=2)
	private String paySystemFlg;

	@Column(name="PAY_UNION_FEE", nullable=false, length=1)
	private String payUnionFee;

	@Column(name="PAYGROUP", nullable=false, length=3)
	private String paygroup;

	@Column(name="PER_ORG", nullable=false, length=3)
	private String perOrg;

	@Column(name="PERFORM_GROUP_GER", nullable=false, length=2)
	private String performGroupGer;

	@Column(name="POI_TYPE", nullable=false, length=5)
	private String poiType;

	@Column(name="POSITION_ENTRY_DT")
	@Temporal(TemporalType.DATE)
	private Date positionEntryDt;

	@Column(name="POSITION_NBR", nullable=false, length=8)
	private String positionNbr;

	@Column(name="POSITION_OVERRIDE", nullable=false, length=1)
	private String positionOverride;

	@Column(name="POSN_CHANGE_RECORD", nullable=false, length=1)
	private String posnChangeRecord;

	@Column(name="PRORATE_CNT_AMT", nullable=false, length=1)
	private String prorateCntAmt;

	@Column(name="REG_REGION", nullable=false, length=5)
	private String regulatoryRegion;

	@Column(name="REG_TEMP", nullable=false, length=1)
	private String regularOrTemporary;

	@Column(name="REPORTS_TO", nullable=false, length=8)
	private String reportsTo;

	@Column(name="SAL_ADMIN_PLAN", nullable=false, length=4)
	private String salAdminPlan;

	@Column(name="SETID_DEPT", nullable=false, length=5)
	private String setIdDept;

	@Column(name="SETID_JOBCODE", nullable=false, length=5)
	private String setIdJobCode;

	@Column(name="SETID_LBR_AGRMNT", nullable=false, length=5)
	private String setidLbrAgrmnt;

	@Column(name="SETID_LOCATION", nullable=false, length=5)
	private String setidLocation;

	@Column(name="SETID_SALARY", nullable=false, length=5)
	private String setIdSalary;

	@Column(name="SETID_SUPV_LVL", nullable=false, length=5)
	private String setIdSupvLvl;

	@Column(name="SHIFT", nullable=false, length=1)
	private String shift;

	@Column(name="SHIFT_FACTOR", nullable=false, precision=4, scale=3)
	private BigDecimal shiftFactor;

	@Column(name="SHIFT_RT", nullable=false, precision=18, scale=6)
	private BigDecimal shiftRt;

	@Column(name="SOC_SEC_RISK_CODE", nullable=false, length=3)
	private String socSecRiskCode;

	@Column(name="SPK_COMM_ID_GER", nullable=false, length=9)
	private String spkCommIdGer;

	@Column(name="STD_HOURS", nullable=false, precision=6, scale=2)
	private BigDecimal stdHours;

	@Column(name="STD_HRS_FREQUENCY", nullable=false, length=5)
	private String stdHrsFrequency;

	@Column(name="STEP", nullable=false, precision=38)
	private BigDecimal step;

	@Column(name="STEP_ENTRY_DT")
	@Temporal(TemporalType.DATE)
	private Date stepEntryDt;

	@Column(name="SUPERVISOR_ID", nullable=false, length=11)
	private String supervisorId;

	@Column(name="SUPV_LVL_ID", nullable=false, length=8)
	private String supvLvlId;

	@Column(name="TARIFF_AREA_GER", nullable=false, length=3)
	private String tariffAreaGer;

	@Column(name="TARIFF_GER", nullable=false, length=2)
	private String tariffGer;

	@Column(name="TAX_LOCATION_CD", nullable=false, length=10)
	private String taxLocationCd;

	@Column(name="TERMINATION_DT")
	@Temporal(TemporalType.DATE)
	private Date terminationDt;

	@Column(name="UNION_CD", nullable=false, length=3)
	private String unionCd;

	@Column(name="UNION_FEE_AMOUNT", nullable=false, precision=8, scale=2)
	private BigDecimal unionFeeAmount;

	@Column(name="UNION_FEE_END_DT")
	@Temporal(TemporalType.DATE)
	private Date unionFeeEndDt;

	@Column(name="UNION_FEE_START_DT")
	@Temporal(TemporalType.DATE)
	private Date unionFeeStartDt;

	@Column(name="UNION_FULL_PART", nullable=false, length=1)
	private String unionFullPart;

	@Column(name="UNION_POS", nullable=false, length=1)
	private String unionPos;

	@Column(name="UNION_SENIORITY_DT")
	@Temporal(TemporalType.DATE)
	private Date unionSeniorityDt;

	@Column(name="VALUE_1_FRA", nullable=false, length=5)
	private String value1Fra;

	@Column(name="VALUE_2_FRA", nullable=false, length=5)
	private String value2Fra;

	@Column(name="VALUE_3_FRA", nullable=false, length=5)
	private String value3Fra;

	@Column(name="VALUE_4_FRA", nullable=false, length=5)
	private String value4Fra;

	@Column(name="VALUE_5_FRA", nullable=false, length=5)
	private String value5Fra;

	@Column(name="WORK_DAY_HOURS", nullable=false, precision=6, scale=2)
	private BigDecimal workDayHours;

	@Column(name="WPP_STOP_FLAG", nullable=false, length=1)
	private String wppStopFlag;

	@Column(name="WRKS_CNCL_FUNCTION", nullable=false, length=1)
	private String wrksCnclFunction;

	@Column(name="WRKS_CNCL_ROLE_CHE", nullable=false, length=30)
	private String wrksCnclRoleChe;

	public PsJob() {
	}

	public String getAbsenceSystemCd() {
		return this.absenceSystemCd.trim();
	}

	public void setAbsenceSystemCd(String absenceSystemCd) {
		this.absenceSystemCd = absenceSystemCd;
	}

	public String getAccdntCdFra() {
		return this.accdntCdFra.trim();
	}

	public void setAccdntCdFra(String accdntCdFra) {
		this.accdntCdFra = accdntCdFra;
	}

	public String getAcctCd() {
		return this.acctCd.trim();
	}

	public void setAcctCd(String acctCd) {
		this.acctCd = acctCd;
	}

	public String getAction() {
		return this.action.trim();
	}

	public Date getActionDt() {
		return this.actionDt;
	}

	public String getActionReason() {
		return this.actionReason.trim();
	}

	public void setActionReason(String actionReason) {
		this.actionReason = actionReason.trim();
	}

	public String getAddsToFteActual() {
		return this.addsToFteActual.trim();
	}

	public BigDecimal getAnnlBenefBaseRt() {
		return this.annlBenefBaseRt;
	}

	public BigDecimal getAnnualRt() {
		return this.annualRt;
	}

	public String getApptType() {
		return this.apptType.trim();
	}

	public Date getAsgnEndDt() {
		return this.asgnEndDt;
	}

	public Date getAsgnStartDt() {
		return this.asgnStartDt;
	}

	public String getAutoEndFlg() {
		return this.autoEndFlg.trim();
	}

	public String getBargUnit() {
		return this.bargUnit.trim();
	}

	public String getBasAction() {
		return this.basAction.trim();
	}

	public String getBasGroupId() {
		return this.basGroupId.trim();
	}

	public String getBenStatus() {
		return this.benStatus.trim();
	}

	public String getBenefitSystem() {
		return this.benefitSystem.trim();
	}

	public String getBorderWalker() {
		return this.borderWalker.trim();
	}

	public String getBusinessUnit() {
		return this.businessUnit.trim();
	}

	public BigDecimal getChangeAmt() {
		return this.changeAmt;
	}

	public BigDecimal getChangePct() {
		return this.changePct;
	}

	public String getClassIndc() {
		return this.classIndc.trim();
	}

	public String getCobraAction() {
		return this.cobraAction.trim();
	}

	public String getCompFrequency() {
		return this.compFrequency.trim();
	}

	public String getCompany() {
		return this.company.trim();
	}

	public BigDecimal getComprate() {
		return this.comprate;
	}

	public String getContractNum() {
		return this.contractNum.trim();
	}

	public BigDecimal getCtgRate() {
		return this.ctgRate;
	}

	public String getCurRtType() {
		return this.curRtType.trim();
	}

	public String getCurrencyCd() {
		return this.currencyCd.trim();
	}

	public String getCurrencyCd1() {
		return this.currencyCd1.trim();
	}

	public BigDecimal getDailyRt() {
		return this.dailyRt;
	}

	public Date getDeptEntryDt() {
		return this.deptEntryDt;
	}

	public String getDeptId() {
		return this.departmentId.trim();
	}

	public String getDirectlyTipped() {
		return this.directlyTipped.trim();
	}

	public String getEarnsDistType() {
		return this.earnsDistType.trim();
	}

	public String getEeoClass() {
		return this.eeoClass.trim();
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public BigDecimal getEffseq() {
		return this.effectiveSequence;
	}

	public String getEligConfig1() {
		return this.eligConfig1.trim();
	}

	public String getEligConfig2() {
		return this.eligConfig2.trim();
	}

	public String getEligConfig3() {
		return this.eligConfig3.trim();
	}

	public String getEligConfig4() {
		return this.eligConfig4.trim();
	}

	public String getEligConfig5() {
		return this.eligConfig5.trim();
	}

	public String getEligConfig6() {
		return this.eligConfig6.trim();
	}

	public String getEligConfig7() {
		return this.eligConfig7.trim();
	}

	public String getEligConfig8() {
		return this.eligConfig8.trim();
	}

	public String getEligConfig9() {
		return this.eligConfig9.trim();
	}

	public String getEmplClass() {
		return this.employeeClass.trim();
	}

	public String getEmplCtg() {
		return this.emplCtg.trim();
	}

	public String getEmplCtgL1() {
		return this.emplCtgL1.trim();
	}

	public String getEmplCtgL2() {
		return this.emplCtgL2.trim();
	}

	public BigDecimal getEmploymentRecordNumber() {
		return this.employmentRecordNumber;
	}

	public String getEmplStatus() {
		return this.emplStatus.trim();
	}

	public String getEmplType() {
		return this.emplType.trim();
	}

	public String getEmployeeId() {
		return this.employeeId.trim();
	}

	public String getEncumbOverride() {
		return this.encumbOverride.trim();
	}

	public Date getEntryDate() {
		return this.entryDate;
	}

	public String getEstabid() {
		return this.estabid.trim();
	}

	public BigDecimal getExemptHoursMonth() {
		return this.exemptHoursMonth;
	}

	public String getExemptJobLbr() {
		return this.exemptJobLbr.trim();
	}

	public Date getExpectedEndDate() {
		return this.expectedEndDate;
	}

	public Date getExpectedReturnDt() {
		return this.expectedReturnDt;
	}

	public String getFicaStatusEe() {
		return this.ficaStatusEe.trim();
	}

	public String getFlsaStatus() {
		return this.flsaStatus.trim();
	}

	public Date getForcePublish() {
		return this.forcePublish;
	}

	public BigDecimal getFte() {
		return this.fte;
	}

	public String getFullPartTime() {
		return this.fullOrPartTime.trim();
	}

	public String getFunctionCd() {
		return this.functionCd.trim();
	}

	public String getGlPayType() {
		return this.glPayType.trim();
	}

	public String getGpAsofDtExgRt() {
		return this.gpAsofDtExgRt.trim();
	}

	public String getGpDfltCurrttyp() {
		return this.gpDfltCurrttyp.trim();
	}

	public String getGpDfltEligGrp() {
		return this.gpDfltEligGrp.trim();
	}

	public String getGpDfltExrtdt() {
		return this.gpDfltExrtdt.trim();
	}

	public String getGpEligGrp() {
		return this.gpEligGrp.trim();
	}

	public String getGpPaygroup() {
		return this.gpPaygroup.trim();
	}

	public String getGrade() {
		return this.grade.trim();
	}

	public Date getGradeEntryDt() {
		return this.gradeEntryDt;
	}

	public Date getHireDt() {
		return this.hireDt;
	}

	public String getHolidaySchedule() {
		return this.holidaySchedule.trim();
	}

	public BigDecimal getHourlyRt() {
		return this.hourlyRt;
	}

	public String getHourlyRtFra() {
		return this.hourlyRtFra.trim();
	}

	public String getHrStatus() {
		return this.hrStatus.trim();
	}

	public String getInterctrWrksCncl() {
		return this.interctrWrksCncl.trim();
	}

	public String getJobDataSrcCd() {
		return this.jobDataSrcCd.trim();
	}

	public Date getJobEntryDt() {
		return this.jobEntryDt;
	}

	public String getJobIndicator() {
		return this.jobIndicator.trim();
	}

	public String getJobCode() {
		return this.jobCode.trim();
	}

	public String getLaborAgreement() {
		return this.laborAgreement.trim();
	}

	public String getLaborFacilityId() {
		return this.laborFacilityId.trim();
	}

	public String getLaborTypeGer() {
		return this.laborTypeGer.trim();
	}

	public Date getLastDateWorked() {
		return this.lastDateWorked;
	}

	public Date getLastHireDt() {
		return this.lastHireDt;
	}

	public Timestamp getLastUpdatedDateAndTime() {
		return this.lastUpdatedDateAndTime;
	}

	public String getLastUpdatedUserId() {
		return this.lastUpdatedUserId.trim();
	}

	public String getLayoffExemptFlag() {
		return this.layoffExemptFlag.trim();
	}

	public String getLayoffExemptRsn() {
		return this.layoffExemptRsn.trim();
	}

	public Date getLbrFacEntryDt() {
		return this.lbrFacEntryDt;
	}

	public String getLdwOvr() {
		return this.ldwOvr.trim();
	}

	public void setLdwOvr(String ldwOvr) {
		this.ldwOvr = ldwOvr;
	}

	public String getLocation() {
		return this.location.trim();
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getLstAsgnStartDt() {
		return this.lstAsgnStartDt;
	}

	public void setLstAsgnStartDt(Date lstAsgnStartDt) {
		this.lstAsgnStartDt = lstAsgnStartDt;
	}

	public String getLumpSumPay() {
		return this.lumpSumPay.trim();
	}

	public void setLumpSumPay(String lumpSumPay) {
		this.lumpSumPay = lumpSumPay;
	}

	public BigDecimal getMainApptNumJpn() {
		return this.mainApptNumJpn;
	}

	public void setMainApptNumJpn(BigDecimal mainApptNumJpn) {
		this.mainApptNumJpn = mainApptNumJpn;
	}

	public BigDecimal getMatriculaNbr() {
		return this.matriculaNbr;
	}

	public void setMatriculaNbr(BigDecimal matriculaNbr) {
		this.matriculaNbr = matriculaNbr;
	}

	public BigDecimal getMonthlyRt() {
		return this.monthlyRt;
	}

	public void setMonthlyRt(BigDecimal monthlyRt) {
		this.monthlyRt = monthlyRt;
	}

	public String getOfficerCd() {
		return this.officerCd.trim();
	}

	public void setOfficerCd(String officerCd) {
		this.officerCd = officerCd;
	}

	public BigDecimal getPaidFte() {
		return this.paidFte;
	}

	public void setPaidFte(BigDecimal paidFte) {
		this.paidFte = paidFte;
	}

	public BigDecimal getPaidHours() {
		return this.paidHours;
	}

	public void setPaidHours(BigDecimal paidHours) {
		this.paidHours = paidHours;
	}

	public String getPaidHrsFrequency() {
		return this.paidHrsFrequency.trim();
	}

	public void setPaidHrsFrequency(String paidHrsFrequency) {
		this.paidHrsFrequency = paidHrsFrequency;
	}

	public String getPaySystemFlg() {
		return this.paySystemFlg.trim();
	}

	public void setPaySystemFlg(String paySystemFlg) {
		this.paySystemFlg = paySystemFlg;
	}

	public String getPayUnionFee() {
		return this.payUnionFee.trim();
	}

	public void setPayUnionFee(String payUnionFee) {
		this.payUnionFee = payUnionFee;
	}

	public String getPaygroup() {
		return this.paygroup.trim();
	}

	public void setPaygroup(String paygroup) {
		this.paygroup = paygroup;
	}

	public String getPerOrg() {
		return this.perOrg.trim();
	}

	public void setPerOrg(String perOrg) {
		this.perOrg = perOrg;
	}

	public String getPerformGroupGer() {
		return this.performGroupGer.trim();
	}

	public void setPerformGroupGer(String performGroupGer) {
		this.performGroupGer = performGroupGer;
	}

	public String getPoiType() {
		return this.poiType.trim();
	}

	public void setPoiType(String poiType) {
		this.poiType = poiType;
	}

	public Date getPositionEntryDt() {
		return this.positionEntryDt;
	}

	public void setPositionEntryDt(Date positionEntryDt) {
		this.positionEntryDt = positionEntryDt;
	}

	public String getPositionNbr() {
		return this.positionNbr.trim();
	}

	public void setPositionNbr(String positionNbr) {
		this.positionNbr = positionNbr;
	}

	public String getPositionOverride() {
		return this.positionOverride.trim();
	}

	public void setPositionOverride(String positionOverride) {
		this.positionOverride = positionOverride;
	}

	public String getPosnChangeRecord() {
		return this.posnChangeRecord.trim();
	}

	public void setPosnChangeRecord(String posnChangeRecord) {
		this.posnChangeRecord = posnChangeRecord;
	}

	public String getProrateCntAmt() {
		return this.prorateCntAmt.trim();
	}

	public void setProrateCntAmt(String prorateCntAmt) {
		this.prorateCntAmt = prorateCntAmt;
	}

	public String getRegulatoryRegion() {
		return this.regulatoryRegion.trim();
	}

	public void setRegulatoryRegion(String regulatoryRegion) {
		this.regulatoryRegion = regulatoryRegion;
	}

	public String getRegTemp() {
		return this.regularOrTemporary.trim();
	}

	public void setRegTemp(String regularOrTemporary) {
		this.regularOrTemporary = regularOrTemporary;
	}

	public String getReportsTo() {
		return this.reportsTo.trim();
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getSalAdminPlan() {
		return this.salAdminPlan.trim();
	}

	public void setSalAdminPlan(String salAdminPlan) {
		this.salAdminPlan = salAdminPlan;
	}

	public String getSetIdDept() {
		return this.setIdDept.trim();
	}

	public void setSetIdDept(String setIdDept) {
		this.setIdDept = setIdDept;
	}

	public String getSetIdJobCode() {
		return this.setIdJobCode.trim();
	}

	public void setSetidJobCode(String setIdJobCode) {
		this.setIdJobCode = setIdJobCode;
	}

	public String getSetIdLbrAgrmnt() {
		return this.setidLbrAgrmnt.trim();
	}

	public void setSetidLbrAgrmnt(String setidLbrAgrmnt) {
		this.setidLbrAgrmnt = setidLbrAgrmnt;
	}

	public String getSetidLocation() {
		return this.setidLocation.trim();
	}

	public void setSetidLocation(String setidLocation) {
		this.setidLocation = setidLocation;
	}

	public String getSetIdSalary() {
		return this.setIdSalary.trim();
	}

	public void setSetIdSalary(String setIdSalary) {
		this.setIdSalary = setIdSalary;
	}

	public String getSetIdSupvLvl() {
		return this.setIdSupvLvl.trim();
	}

	public void setSetIdSupvLvl(String setIdSupvLvl) {
		this.setIdSupvLvl = setIdSupvLvl;
	}

	public String getShift() {
		return this.shift.trim();
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public BigDecimal getShiftFactor() {
		return this.shiftFactor;
	}

	public void setShiftFactor(BigDecimal shiftFactor) {
		this.shiftFactor = shiftFactor;
	}

	public BigDecimal getShiftRt() {
		return this.shiftRt;
	}

	public void setShiftRt(BigDecimal shiftRt) {
		this.shiftRt = shiftRt;
	}

	public String getSocSecRiskCode() {
		return this.socSecRiskCode.trim();
	}

	public void setSocSecRiskCode(String socSecRiskCode) {
		this.socSecRiskCode = socSecRiskCode;
	}

	public String getSpkCommIdGer() {
		return this.spkCommIdGer.trim();
	}

	public void setSpkCommIdGer(String spkCommIdGer) {
		this.spkCommIdGer = spkCommIdGer;
	}

	public BigDecimal getStdHours() {
		return this.stdHours;
	}

	public void setStdHours(BigDecimal stdHours) {
		this.stdHours = stdHours;
	}

	public String getStdHrsFrequency() {
		return this.stdHrsFrequency.trim();
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

	public Date getStepEntryDt() {
		return this.stepEntryDt;
	}

	public void setStepEntryDt(Date stepEntryDt) {
		this.stepEntryDt = stepEntryDt;
	}

	public String getSupervisorId() {
		return this.supervisorId.trim();
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getSupvLvlId() {
		return this.supvLvlId.trim();
	}

	public void setSupvLvlId(String supvLvlId) {
		this.supvLvlId = supvLvlId;
	}

	public String getTariffAreaGer() {
		return this.tariffAreaGer.trim();
	}

	public void setTariffAreaGer(String tariffAreaGer) {
		this.tariffAreaGer = tariffAreaGer;
	}

	public String getTariffGer() {
		return this.tariffGer.trim();
	}

	public void setTariffGer(String tariffGer) {
		this.tariffGer = tariffGer;
	}

	public String getTaxLocationCd() {
		return this.taxLocationCd.trim();
	}

	public void setTaxLocationCd(String taxLocationCd) {
		this.taxLocationCd = taxLocationCd;
	}

	public Date getTerminationDt() {
		return this.terminationDt;
	}

	public void setTerminationDt(Date terminationDt) {
		this.terminationDt = terminationDt;
	}

	public String getUnionCd() {
		return this.unionCd.trim();
	}

	public void setUnionCd(String unionCd) {
		this.unionCd = unionCd;
	}

	public BigDecimal getUnionFeeAmount() {
		return this.unionFeeAmount;
	}

	public void setUnionFeeAmount(BigDecimal unionFeeAmount) {
		this.unionFeeAmount = unionFeeAmount;
	}

	public Date getUnionFeeEndDt() {
		return this.unionFeeEndDt;
	}

	public void setUnionFeeEndDt(Date unionFeeEndDt) {
		this.unionFeeEndDt = unionFeeEndDt;
	}

	public Date getUnionFeeStartDt() {
		return this.unionFeeStartDt;
	}

	public void setUnionFeeStartDt(Date unionFeeStartDt) {
		this.unionFeeStartDt = unionFeeStartDt;
	}

	public String getUnionFullPart() {
		return this.unionFullPart.trim();
	}

	public void setUnionFullPart(String unionFullPart) {
		this.unionFullPart = unionFullPart;
	}

	public String getUnionPos() {
		return this.unionPos.trim();
	}

	public void setUnionPos(String unionPos) {
		this.unionPos = unionPos;
	}

	public Date getUnionSeniorityDt() {
		return this.unionSeniorityDt;
	}

	public void setUnionSeniorityDt(Date unionSeniorityDt) {
		this.unionSeniorityDt = unionSeniorityDt;
	}

	public String getValue1Fra() {
		return this.value1Fra.trim();
	}

	public void setValue1Fra(String value1Fra) {
		this.value1Fra = value1Fra;
	}

	public String getValue2Fra() {
		return this.value2Fra.trim();
	}

	public void setValue2Fra(String value2Fra) {
		this.value2Fra = value2Fra;
	}

	public String getValue3Fra() {
		return this.value3Fra.trim();
	}

	public void setValue3Fra(String value3Fra) {
		this.value3Fra = value3Fra;
	}

	public String getValue4Fra() {
		return this.value4Fra.trim();
	}

	public void setValue4Fra(String value4Fra) {
		this.value4Fra = value4Fra;
	}

	public String getValue5Fra() {
		return this.value5Fra.trim();
	}

	public void setValue5Fra(String value5Fra) {
		this.value5Fra = value5Fra;
	}

	public BigDecimal getWorkDayHours() {
		return this.workDayHours;
	}

	public void setWorkDayHours(BigDecimal workDayHours) {
		this.workDayHours = workDayHours;
	}

	public String getWppStopFlag() {
		return this.wppStopFlag.trim();
	}

	public void setWppStopFlag(String wppStopFlag) {
		this.wppStopFlag = wppStopFlag;
	}

	public String getWrksCnclFunction() {
		return this.wrksCnclFunction.trim();
	}

	public void setWrksCnclFunction(String wrksCnclFunction) {
		this.wrksCnclFunction = wrksCnclFunction;
	}

	public String getWrksCnclRoleChe() {
		return this.wrksCnclRoleChe.trim();
	}

	public void setWrksCnclRoleChe(String wrksCnclRoleChe) {
		this.wrksCnclRoleChe = wrksCnclRoleChe;
	}

	/**
	 * HR01-Get-Job-Data
	 * Gets the employees data from the job table that needs to be interfaced to the legacy system
	 */
	public PsJob hr01GetJobData(String employeeId) {
//		Begin-Select
//		CJ.COMPANY
//		    Let $PSCompany = ltrim(rtrim(&CJ.COMPANY,' '),' ')              !Remove leading and trailing blanks
//		CJ.LOCATION
//		    Let $PSLocation = ltrim(rtrim(&CJ.LOCATION,' '),' ')            !Remove leading and trailing blanks
//		CJ.BUSINESS_UNIT
//		    Let $PSBusinessUnit = ltrim(rtrim(&CJ.BUSINESS_UNIT,' '),' ')   !Remove leading and trailing blanks
//		to_char(CJ.EFFDT, 'YYYY-MM-DD')         &CJ.EFFDT
//		    Let $PSEffdt = &CJ.EFFDT                                        !Convert date format
//		CJ.REG_TEMP
//		    Let $PSRegTemp = ltrim(rtrim(&CJ.REG_TEMP,' '),' ')             !Remove leading and trailing blanks
//		CJ.FULL_PART_TIME
//		    Let $PSFullPartTime = ltrim(rtrim(&CJ.FULL_PART_TIME,' '),' ')  !Remove leading and trailing blanks
//		CJ.EMPL_CLASS
//		    Let $PSEmplClass = ltrim(rtrim(&CJ.EMPL_CLASS,' '),' ')         !Remove leading and trailing blanks
//		CJ.FLSA_STATUS
//		    Let $PSFlsaStatus = ltrim(rtrim(&CJ.FLSA_STATUS,' '),' ')       !Remove leading and trailing blanks
//		CJ.EMPL_STATUS
//		    Let $PSEmplStatus = ltrim(rtrim(&CJ.EMPL_STATUS,' '),' ')       !Remove leading and trailing blanks
//		CJ.DEPTID
//		    Let $PSDeptid = ltrim(rtrim(&CJ.DEPTID,' '),' ')                !Remove leading and trailing blanks
//		CJ.JOBCODE
//		    Let $PSJobcode = ltrim(rtrim(&CJ.JOBCODE,' '),' ')              !Remove leading and trailing blanks
//		CJ.SETID_JOBCODE
//		    Let $PSSetID = substr(&CJ.SETID_JOBCODE,1,5)                    !Move to a program field
//		CJ.ACTION
//		    Let $PSAction = &CJ.ACTION
//		CJ.ACTION_REASON
//		    Let $PSAction_Reason = &CJ.ACTION_REASON
//		CJ.REG_REGION
//		    let $PS_REG_REGION = ltrim(rtrim(&CJ.REG_REGION,' '),' ')
//		    Let $PSAction_Reason_All = $PSAction || $PSAction_Reason
//		    Let $Wrk_AD_JobDataBuild = 'Y'
//		from PS_JOB CJ
//		where CJ.EMPLID = $Wrk_Emplid
//		  and CJ.EMPL_RCD = 0
//		  and CJ.EFFDT = (SELECT MAX(EFFDT)
//		                    FROM  PS_JOB CJ2
//		                   WHERE  CJ2.EMPLID = CJ.EMPLID
//		                     AND  CJ2.EMPL_RCD = CJ.EMPL_RCD
//		                     AND  (CJ2.ACTION IN ('HIR','REH')
//		                     OR (CJ2.ACTION = 'DTA' AND CJ2.ACTION_REASON = 'CNV')
//		                     OR (CJ2.ACTION = 'TER' AND CJ2.ACTION_REASON = 'CNV'))
//		                  AND  to_char(CJ2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		  and CJ.EFFSEQ = (SELECT MAX(EFFSEQ)
//		                     FROM PS_JOB CJ3
//		                    WHERE CJ3.EMPLID = CJ.EMPLID
//		                      AND CJ3.EMPL_RCD = CJ.EMPL_RCD
//		                      AND (CJ3.ACTION IN ('HIR','REH')
//		                      OR (CJ3.ACTION = 'DTA' AND CJ3.ACTION_REASON = 'CNV')
//		                      OR (CJ3.ACTION = 'TER' AND CJ3.ACTION_REASON = 'CNV'))
//		                      AND CJ3.EFFDT = CJ.EFFDT)
//		End-Select
		return null;
	}

	/**
	 * HR02-Get-Job from ZHRI102A.SQC
	 * This routine will the Job Data row for each of the employee numbers entered in the trigger file.
	 */
	public static PsJob hr02GetJob(String employeeId, Date effectiveDate, BigDecimal effectiveSequence) {
		//BEGIN-SELECT
		//FROM PS_Job PS_Job
		//WHERE PS_Job.Emplid = $PSEmplid
		//  	AND TO_CHAR(PS_Job.EFFDT, 'YYYY-MM-DD') = $PSDate
		//  	AND PS_Job.EFFSEQ = #PSEFFSEQ
		//  	AND PS_Job.EMPL_RCD = 0
		//END-SELECT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsJob> resultList = em.createQuery("SELECT p FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    				+ "AND p.effectiveSequence = :effectiveSequence "
	    				+ "AND p.employmentRecordNumber = 0 "
	    				+ "AND p.effectiveDate = :effectiveDate "
	    				, PsJob.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("effectiveDate", effectiveDate, TemporalType.DATE)
	    		    .setParameter("effectiveSequence", effectiveSequence)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0);
	    	}
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
	}

	/**
	 * HR04-Get-Job-Data
	 * Gets the employees data from the job table that needs to be interfaced to the legacy system
	 */
	public static PsJob hr04GetJobData(String employeeId, Date effectiveDate) {
//		Begin-Select
//		CJ6.COMPANY
//		    let $PSCompany = ltrim(rtrim(&CJ6.COMPANY,' '),' ')              !Remove leading and trailing blanks
//		CJ6.BUSINESS_UNIT
//		    let $PSBusinessUnit = ltrim(rtrim(&CJ6.BUSINESS_UNIT,' '),' ')   !Remove leading and trailing blanks
//		CJ6.LOCATION
//		    let $PSLocation = ltrim(rtrim(&CJ6.LOCATION,' '),' ')            !Remove leading and trailing blanks
//		CJ6.SETID_JOBCODE
//		    let $PSSetID = ltrim(rtrim(&CJ6.SETID_JOBCODE,' '),' ')          !Remove leading and trailing blanks
//		CJ6.EMPL_STATUS
//		    let $PSEmplStatus = ltrim(rtrim(&CJ6.EMPL_STATUS,' '),' ')       !Remove leading and trailing blanks
//		CJ6.ACTION
//		    let $PSAction = ltrim(rtrim(&CJ6.ACTION,' '),' ')                !Remove leading and trailing blanks
//		CJ6.REG_TEMP
//		    let $PSRegTemp = ltrim(rtrim(&CJ6.REG_TEMP,' '),' ')             !Remove leading and trailing blanks
//		CJ6.FULL_PART_TIME
//		    let $PSFullPartTime = ltrim(rtrim(&CJ6.FULL_PART_TIME,' '),' ')  !Remove leading and trailing blanks
//		CJ6.EMPL_CLASS
//		    let $PSEmplClass = ltrim(rtrim(&CJ6.EMPL_CLASS,' '),' ')         !Remove leading and trailing blanks
//		CJ6.FLSA_STATUS
//		    let $PSFlsaStatus = ltrim(rtrim(&CJ6.FLSA_STATUS,' '),' ')       !Remove leading and trailing blanks
//		CJ6.DEPTID
//		    let $PSDeptid = ltrim(rtrim(&CJ6.DEPTID,' '),' ')                !Remove leading and trailing blanks
//		CJ6.JOBCODE
//		    let $PSJobcode = ltrim(rtrim(&CJ6.JOBCODE,' '),' ')              !Remove leading and trailing blanks
//		 let $Wrk_AD_JobDataBuild = 'Y'
//		from PS_JOB CJ6
//		where CJ6.EMPLID = $Wrk_Emplid
//		and to_char(CJ6.EFFDT, 'YYYY-MM-DD') =    $PSEffdt
//		AND CJ6.EFFSEQ =
//		         (SELECT MAX(CJ6B.EFFSEQ)
//		          FROM  PS_JOB CJ6B
//		          WHERE CJ6B.EMPLID   = CJ6.EMPLID
//		            AND CJ6B.EMPL_RCD = CJ6.EMPL_RCD
//		            AND CJ6B.EFFDT    = CJ6.EFFDT)
//		and CJ6.EMPL_RCD = 0
//		End-Select
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsJob> resultList = em.createQuery(
	    		    "SELECT pj FROM PsJob pj "
	    		    		+ "WHERE UPPER(TRIM(pj.employeeId)) = :employeeId "
	    		    		+ "		AND pj.effectiveDate = :effectiveDate "	
	    		    		+ "		AND pj.effectiveSequence = (SELECT MAX(pj2.effectiveSequence) FROM PsJob pj2 "
	    		    		+ "				WHERE pj2.employeeId = pj.employeeId "
	    		    		+ "					AND pj2.employmentRecordNumber = pj.employmentRecordNumber "
	    		    		+ "					AND pj2.effectiveDate = pj.effectiveDate) "
	    		    		+ "		AND pj.employmentRecordNumber = 0"
	    			, PsJob.class)
	    		    .setParameter("employeeId", employeeId.trim().toUpperCase())
	    		    .setParameter("effectiveDate", effectiveDate, TemporalType.DATE)
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		return resultList.get(0);
	    	}
	    } 
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
		return null;
	}

	/**
	 * Replaces HR05-Get-Region and HR05-Get-Region
	 * This procedure will get REG_REGION from PS_JOB
	 */
	public String findRegionByEmployeeIdAndEmploymentRecordNumber(String employeeId, BigDecimal employmentRecordNumber) {
		//Begin-Select
		//PS_JOB.REG_REGION
		//LET $PS_REG_REGION = LTRIM(RTRIM(&PS_JOB.REG_REGION,' '),' ')
		//FROM PS_JOB PS_JOB
		//WHERE PS_JOB.EMPLID = $Wrk_Emplid       
		//AND PS_JOB.EFFDT = 
		//	(SELECT MAX(EFFDT) FROM PS_JOB J8a
		//    	WHERE J8a.EMPLID = PS_JOB.EMPLID
		//        	AND J8a.EMPL_RCD = PS_JOB.EMPL_RCD)
		//AND PS_JOB.EFFSEQ =                                  
		//	(SELECT MAX(PS_JOB2.EFFSEQ) FROM  PS_JOB PS_JOB2                       
		//   		WHERE PS_JOB2.EMPLID   = PS_JOB.EMPLID        
		//            AND PS_JOB2.EMPL_RCD = PS_JOB.EMPL_RCD     
		//            AND PS_JOB2.EFFDT    = PS_JOB.EFFDT)        
		//			AND PS_JOB.EMPL_RCD = 0
		//End-Select
		return null;
	}

	/**
	 * AD-Get-JobStart-Date
	 * Gets the Job Start date from the job table.
	 * Returns $ADJobStartDt as YYYYMMDD
	 */
	//TODO: check if $ADJobStartYr, $ADJobStartMnth, $ADJobStartDay, $PSJobStartDt are used in the calling file???
	public String adGetJobStartDate(String employeeId, BigDecimal employmentRecordNumber, String jobCode, Date effectiveDate) {
		//Begin-Select
		//to_char(PS_JOB.EFFDT,'YYYY-MM-DD') &PS_JOB.EFFDT
		//LET $ADJobStartYr   = substr(&PS_JOB.EFFDT,1,4)
		//LET $ADJobStartMnth = substr(&PS_JOB.EFFDT,6,2)
		//LET $ADJobStartDay  = substr(&PS_JOB.EFFDT,9,2)
		//LET $ADJobStartDt   = $ADJobStartYr || $ADJobStartMnth || $ADJobStartDay
		//LET $PSJobStartDt 	= &PS_JOB.EFFDT
		//FROM PS_JOB PS_JOB
		//WHERE PS_JOB.EMPLID = $Wrk_Emplid
		//	AND PS_JOB.JOBCODE = $PSJobcode
		//  	AND PS_JOB.EFFDT = 
		//		(SELECT MIN(EFFDT) FROM  PS_JOB PS_JOB2
		//       		WHERE  PS_JOB.EMPLID = PS_JOB2.EMPLID
		//          		AND  PS_JOB.JOBCODE = PS_JOB2.JOBCODE
		//           		AND  PS_JOB.EMPL_RCD = PS_JOB2.EMPL_RCD
		//             	AND  to_char(PS_JOB2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
		//  	AND PS_JOB.EFFSEQ = 
		//		(SELECT MIN(EFFSEQ) FROM PS_JOB PS_JOB3
		//      		WHERE PS_JOB3.EMPLID = PS_JOB.EMPLID
		//        		AND PS_JOB3.EMPL_RCD = PS_JOB.EMPL_RCD
		//           		AND PS_JOB3.JOBCODE = PS_JOB.JOBCODE
		//             	AND PS_JOB3.EFFDT = PS_JOB.EFFDT)
		//end-select
		return null;
	}
	   
	/**
	 * Replaces Check-If-Contractor from ZHRI100A.SQR
	 * Checks to see if the employee is a contractor       
	 */
	public static boolean employeeIsContractor(String employeeId) {
//		Let $Found = 'N'
//		Begin-Select
//		'X'
//		    Let $Found = 'Y'
//		FROM PS_JOB RJ
//		WHERE RJ.EMPLID = $PSEmplid
//		  AND RJ.EMPL_CLASS = 'R'
//		  AND RJ.EFFDT = (SELECT MAX(EFFDT)
//		                    FROM  PS_JOB RJ2
//		                   WHERE  RJ2.EMPLID = RJ.EMPLID
//		                     AND  RJ2.EMPL_RCD = RJ.EMPL_RCD
//		                     AND  RJ2.EFFDT <= $AsOfToday)
//		  AND RJ.EFFSEQ = (SELECT MAX(EFFSEQ)
//		                     FROM PS_JOB RJ3
//		                    WHERE RJ3.EMPLID = RJ.EMPLID
//		                     AND  RJ3.EMPL_RCD = RJ.EMPL_RCD
//		                      AND RJ3.EFFDT = RJ.EFFDT)
//		End-Select
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<Date> resultList = em.createQuery("SELECT p FROM PsJob p WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    			+ "AND p.employeeClass = :employeeClass "
    				+ "AND p.effectiveDate = (SELECT MAX(p2.effectiveDate) FROM PsJob p2 WHERE p2.employeeId = p.employeeId "
    				+ "		AND p2.employmentRecordNumber = p.employmentRecordNumber AND p2.effectiveDate <= CURRENT_DATE) "
    				+ "AND p.effectiveSequence = (SELECT MAX(p3.effectiveSequence) FROM PsJob p3 WHERE p3.employeeId = p.employeeId "
    				+ "		AND p3.employmentRecordNumber = p.employmentRecordNumber AND p3.effectiveDate = p.effectiveDate) ",
	    				Date.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("employeeClass", "R".toUpperCase())
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return true;
	    	}
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return false;	
	}
		   
	/**
	 * Replaces Check-If-Contractor from ZHRI100A.SQR
	 * Checks to see if the employee is a contractor       
	 */
	public static boolean employeeIsContractor(String employeeId, Date effectiveDate, BigDecimal effectiveSequence) {
//		Let $Found = 'N'
//		Begin-Select
//		'X'
//		    Let $Found = 'Y'
//		FROM PS_JOB RJ
//		WHERE RJ.EMPLID = $PSEmplid
//		  AND RJ.EMPL_CLASS = 'R'
//		  AND RJ.EFFDT = (SELECT MAX(EFFDT)
//		                    FROM  PS_JOB RJ2
//		                   WHERE  RJ2.EMPLID = RJ.EMPLID
//		                     AND  RJ2.EMPL_RCD = RJ.EMPL_RCD
//		                     AND  RJ2.EFFDT <= $AsOfToday)
//		  AND RJ.EFFSEQ = (SELECT MAX(EFFSEQ)
//		                     FROM PS_JOB RJ3
//		                    WHERE RJ3.EMPLID = RJ.EMPLID
//		                     AND  RJ3.EMPL_RCD = RJ.EMPL_RCD
//		                      AND RJ3.EFFDT = RJ.EFFDT)
//		End-Select
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<Date> resultList = em.createQuery("SELECT p FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    				+ "AND p.effectiveDate = :effectiveDate "
	    				+ "AND p.effectiveSequence = :effectiveSequence "
	    				+ "AND p.employeeClass = :employeeClass ",
	    				Date.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("effectiveDate", effectiveDate, TemporalType.DATE)
	    		    .setParameter("effectiveSequence", effectiveSequence)
	    		    .setParameter("employeeClass", "R".toUpperCase())
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return true;
	    	}
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return false;	
	}
	   
	/**
	 */
	public static BigDecimal findMaxEffectiveSequenceByEmployeeIdAndEmploymentRecordNumberAndEffectiveDate(String employeeId, BigDecimal employmentRecordNumber, Date effectiveDate) {
//		 EFFSEQ = (SELECT MAX(EFFSEQ)
//        FROM PS_JOB RJ3
//       WHERE RJ3.EMPLID = RJ.EMPLID
//        AND  RJ3.EMPL_RCD = RJ.EMPL_RCD
//         AND RJ3.EFFDT = RJ.EFFDT)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<BigDecimal> resultList = em.createQuery("SELECT MAX(p.effectiveSequence) FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    				+ "AND p.employmentRecordNumber = :employmentRecordNumber "
	    				+ "AND p.effectiveDate = :effectiveDate ",
	    				BigDecimal.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("employmentRecordNumber", employmentRecordNumber)
	    		    .setParameter("effectiveDate", effectiveDate, TemporalType.DATE)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0);
	    	}
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
	}
	   
	/**
	 */
	public static Date findMaxEffectiveDateByEmployeeIdAndEmploymentRecordNumber(String employeeId, BigDecimal employmentRecordNumber) {
//		  EFFDT = (SELECT MAX(EFFDT)
//        FROM  PS_JOB RJ2
//       WHERE  RJ2.EMPLID = RJ.EMPLID
//         AND  RJ2.EMPL_RCD = RJ.EMPL_RCD
//         AND  RJ2.EFFDT <= $AsOfToday)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<Date> resultList = em.createQuery("SELECT MAX(p.effectiveDate) FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    				+ "AND p.employmentRecordNumber = :employmentRecordNumber "
	    				+ "AND p.effectiveDate <= CURRENT_DATE ",
	    				Date.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("employmentRecordNumber", employmentRecordNumber)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0);
	    	}
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
	}

	public static List<PsJob> findByEmployeeIdAndEffectiveDate(String employeeId, java.util.Date effectiveDate) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsJob> resultList = em.createQuery("SELECT p FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    				+ "AND p.effectiveDate = :effectiveDate ",
	    				PsJob.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("effectiveDate", effectiveDate, TemporalType.DATE)
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		return resultList;
	    	}
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
	}
	   
	/**
	 */
//	public static BigDecimal findEmploymentRecordNumberByEmployeeId(String employeeId) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//		EntityManager em = emfactory.createEntityManager();
//	    try {
//	    	List<Date> resultList = em.createQuery("SELECT p.employmentRecordNumber FROM PsJob p "
//	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
//	    				+ "AND p.employmentRecordNumber = :employmentRecordNumber "
//	    				+ "AND p.effectiveDate <= CURRENT_DATE ",
//	    				Date.class)
//	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
//	    		    .setParameter("employmentRecordNumber", employmentRecordNumber)
//	    		    .getResultList();
//	    	if(resultList != null && resultList.size() > 0) {
//	    		return resultList.get(0);
//	    	}
//	    }
//	    catch (Exception e) {
//	       e.printStackTrace();
//	    } 
//	    return null;	
//	}
	
	//TODO: Check-If-Correct102A
	//TODO: findJobStartDateByEmployeeIdAndJobCodeAndEffectiveDateAndEffectiveSequence(String employeeId, String jobCode, Date effectiveDate, BigDecimal effectiveSequence)

	

}
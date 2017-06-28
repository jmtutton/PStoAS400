package erd.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import org.eclipse.persistence.config.QueryHints;

import erd.DateUtil;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the PS_JOB database table.
 * Contains job records along with other data. Effective dated someone's job history will be stored in this table.
 * @author John Tutton john@tutton.net
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
	private Integer employmentRecordNumber;

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
	private Integer annlBenefBaseRt;

	@Column(name="ANNUAL_RT", nullable=false, precision=18, scale=3)
	private Integer annualRt;

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
	private Integer changeAmt;

	@Column(name="CHANGE_PCT", nullable=false, precision=6, scale=3)
	private Integer changePct;

	@Column(name="CLASS_INDC", nullable=false, length=1)
	private String classIndc;

	@Column(name="COBRA_ACTION", nullable=false, length=3)
	private String cobraAction;

	@Column(name="COMP_FREQUENCY", nullable=false, length=5)
	private String compFrequency;

	@Column(name="COMPANY", nullable=false, length=3)
	private String company;

	@Column(name="COMPRATE", nullable=false, precision=18, scale=6)
	private Integer comprate;

	@Column(name="CONTRACT_NUM", nullable=false, length=25)
	private String contractNum;

	@Column(name="CTG_RATE", nullable=false, precision=38)
	private Integer ctgRate;

	@Column(name="CUR_RT_TYPE", nullable=false, length=5)
	private String curRtType;

	@Column(name="CURRENCY_CD", nullable=false, length=3)
	private String currencyCd;

	@Column(name="CURRENCY_CD1", nullable=false, length=3)
	private String currencyCd1;

	@Column(name="DAILY_RT", nullable=false, precision=18, scale=3)
	private Integer dailyRt;

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
	private Integer effectiveSequence;

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
	private Integer exemptHoursMonth;

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
	private Integer fte;

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
	private Integer hourlyRt;

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
	private Integer mainApptNumJpn;

	@Column(name="MATRICULA_NBR", nullable=false, precision=38)
	private Integer matriculaNbr;

	@Column(name="MONTHLY_RT", nullable=false, precision=18, scale=3)
	private Integer monthlyRt;

	@Column(name="OFFICER_CD", nullable=false, length=1)
	private String officerCd;

	@Column(name="PAID_FTE", nullable=false, precision=7, scale=6)
	private Integer paidFte;

	@Column(name="PAID_HOURS", nullable=false, precision=6, scale=2)
	private Integer paidHours;

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
	private Integer shiftFactor;

	@Column(name="SHIFT_RT", nullable=false, precision=18, scale=6)
	private Integer shiftRt;

	@Column(name="SOC_SEC_RISK_CODE", nullable=false, length=3)
	private String socSecRiskCode;

	@Column(name="SPK_COMM_ID_GER", nullable=false, length=9)
	private String spkCommIdGer;

	@Column(name="STD_HOURS", nullable=false, precision=6, scale=2)
	private Integer stdHours;

	@Column(name="STD_HRS_FREQUENCY", nullable=false, length=5)
	private String stdHrsFrequency;

	@Column(name="STEP", nullable=false, precision=38)
	private Integer step;

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
	private Integer unionFeeAmount;

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
	private Integer workDayHours;

	@Column(name="WPP_STOP_FLAG", nullable=false, length=1)
	private String wppStopFlag;

	@Column(name="WRKS_CNCL_FUNCTION", nullable=false, length=1)
	private String wrksCnclFunction;

	@Column(name="WRKS_CNCL_ROLE_CHE", nullable=false, length=30)
	private String wrksCnclRoleChe;

	public PsJob() {
	}

	public String getAbsenceSystemCd() {
		return absenceSystemCd != null ? absenceSystemCd.trim() : absenceSystemCd;
	}

	public void setAbsenceSystemCd(String absenceSystemCd) {
		this.absenceSystemCd = absenceSystemCd != null ? absenceSystemCd.trim() : absenceSystemCd;
	}

	public String getAccdntCdFra() {
		return accdntCdFra != null ? accdntCdFra.trim() : accdntCdFra;
	}

	public void setAccdntCdFra(String accdntCdFra) {
		this.accdntCdFra = accdntCdFra != null ? accdntCdFra.trim() : accdntCdFra;
	}

	public String getAcctCd() {
		return acctCd != null ? acctCd.trim() : acctCd;
	}

	public void setAcctCd(String acctCd) {
		this.acctCd = acctCd != null ? acctCd.trim() : acctCd;
	}

	public String getAction() {
		return action != null ? action.trim() : action;
	}

	public Date getActionDt() {
		return this.actionDt;
	}

	public String getActionReason() {
		return actionReason != null ? actionReason.trim() : actionReason;
	}

	public void setActionReason(String actionReason) {
		this.actionReason = actionReason != null ? actionReason.trim() : actionReason;
	}

	public String getAddsToFteActual() {
		return addsToFteActual != null ? addsToFteActual.trim() : addsToFteActual;
	}

	public Integer getAnnlBenefBaseRt() {
		return this.annlBenefBaseRt;
	}

	public Integer getAnnualRt() {
		return this.annualRt;
	}

	public String getApptType() {
		return apptType != null ? apptType.trim() : apptType;
	}

	public Date getAsgnEndDt() {
		return this.asgnEndDt;
	}

	public Date getAsgnStartDt() {
		return this.asgnStartDt;
	}

	public String getAutoEndFlg() {
		return autoEndFlg != null ? autoEndFlg.trim() : autoEndFlg;
	}

	public String getBargUnit() {
		return bargUnit != null ? bargUnit.trim() : bargUnit;
	}

	public String getBasAction() {
		return basAction != null ? basAction.trim() : basAction;
	}

	public String getBasGroupId() {
		return basGroupId != null ? basGroupId.trim() : basGroupId;
	}

	public String getBenStatus() {
		return benStatus != null ? benStatus.trim() : benStatus;
	}

	public String getBenefitSystem() {
		return benefitSystem != null ? benefitSystem.trim() : benefitSystem;
	}

	public String getBorderWalker() {
		return borderWalker != null ? borderWalker.trim() : borderWalker;
	}

	public String getBusinessUnit() {
		return businessUnit != null ? businessUnit.trim() : businessUnit;
	}

	public Integer getChangeAmt() {
		return this.changeAmt;
	}

	public Integer getChangePct() {
		return this.changePct;
	}

	public String getClassIndc() {
		return classIndc != null ? classIndc.trim() : classIndc;
	}

	public String getCobraAction() {
		return cobraAction != null ? cobraAction.trim() : cobraAction;
	}

	public String getCompFrequency() {
		return compFrequency != null ? compFrequency.trim() : compFrequency;
	}

	public String getCompany() {
		return company != null ? company.trim() : company;
	}

	public Integer getComprate() {
		return this.comprate;
	}

	public String getContractNum() {
		return contractNum != null ? contractNum.trim() : contractNum;
	}

	public Integer getCtgRate() {
		return this.ctgRate;
	}

	public String getCurRtType() {
		return curRtType != null ? curRtType.trim() : curRtType;
	}

	public String getCurrencyCd() {
		return currencyCd != null ? currencyCd.trim() : currencyCd;
	}

	public String getCurrencyCd1() {
		return currencyCd1 != null ? currencyCd1.trim() : currencyCd1;
	}

	public Integer getDailyRt() {
		return this.dailyRt;
	}

	public Date getDeptEntryDt() {
		return this.deptEntryDt;
	}

	public String getDeptId() {
		return departmentId != null ? departmentId.trim() : departmentId;
	}

	public String getDirectlyTipped() {
		return directlyTipped != null ? directlyTipped.trim() : directlyTipped;
	}

	public String getEarnsDistType() {
		return earnsDistType != null ? earnsDistType.trim() : earnsDistType;
	}

	public String getEeoClass() {
		return eeoClass != null ? eeoClass.trim() : eeoClass;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public Integer getEffseq() {
		return this.effectiveSequence;
	}

	public String getEligConfig1() {
		return eligConfig1 != null ? eligConfig1.trim() : eligConfig1;
	}

	public String getEligConfig2() {
		return eligConfig2 != null ? eligConfig2.trim() : eligConfig2;
	}

	public String getEligConfig3() {
		return eligConfig3 != null ? eligConfig3.trim() : eligConfig3;
	}

	public String getEligConfig4() {
		return eligConfig4 != null ? eligConfig4.trim() : eligConfig4;
	}

	public String getEligConfig5() {
		return eligConfig5 != null ? eligConfig5.trim() : eligConfig5;
	}

	public String getEligConfig6() {
		return eligConfig6 != null ? eligConfig6.trim() : eligConfig6;
	}

	public String getEligConfig7() {
		return eligConfig7 != null ? eligConfig7.trim() : eligConfig7;
	}

	public String getEligConfig8() {
		return eligConfig8 != null ? eligConfig8.trim() : eligConfig8;
	}

	public String getEligConfig9() {
		return eligConfig9 != null ? eligConfig9.trim() : eligConfig9;
	}

	public String getEmplClass() {
		return employeeClass != null ? employeeClass.trim() : employeeClass;
	}

	public String getEmplCtg() {
		return emplCtg != null ? emplCtg.trim() : emplCtg;
	}

	public String getEmplCtgL1() {
		return emplCtgL1 != null ? emplCtgL1.trim() : emplCtgL1;
	}

	public String getEmplCtgL2() {
		return emplCtgL2 != null ? emplCtgL2.trim() : emplCtgL2;
	}

	public Integer getEmploymentRecordNumber() {
		return this.employmentRecordNumber;
	}

	public String getEmplStatus() {
		return emplStatus != null ? emplStatus.trim() : emplStatus;
	}

	public String getEmplType() {
		return emplType != null ? emplType.trim() : emplType;
	}

	public String getEmployeeId() {
		return employeeId != null ? employeeId.trim() : employeeId;
	}

	public String getEncumbOverride() {
		return encumbOverride != null ? encumbOverride.trim() : encumbOverride;
	}

	public Date getEntryDate() {
		return this.entryDate;
	}

	public String getEstabid() {
		return estabid != null ? estabid.trim() : estabid;
	}

	public Integer getExemptHoursMonth() {
		return this.exemptHoursMonth;
	}

	public String getExemptJobLbr() {
		return exemptJobLbr != null ? exemptJobLbr.trim() : exemptJobLbr;
	}

	public Date getExpectedEndDate() {
		return this.expectedEndDate;
	}

	public Date getExpectedReturnDt() {
		return this.expectedReturnDt;
	}

	public String getFicaStatusEe() {
		return ficaStatusEe != null ? ficaStatusEe.trim() : ficaStatusEe;
	}

	public String getFlsaStatus() {
		return flsaStatus != null ? flsaStatus.trim() : flsaStatus;
	}

	public Date getForcePublish() {
		return this.forcePublish;
	}

	public Integer getFte() {
		return this.fte;
	}

	public String getFullPartTime() {
		return fullOrPartTime != null ? fullOrPartTime.trim() : fullOrPartTime;
	}

	public String getFunctionCd() {
		return functionCd != null ? functionCd.trim() : functionCd;
	}

	public String getGlPayType() {
		return glPayType != null ? glPayType.trim() : glPayType;
	}

	public String getGpAsofDtExgRt() {
		return gpAsofDtExgRt != null ? gpAsofDtExgRt.trim() : gpAsofDtExgRt;
	}

	public String getGpDfltCurrttyp() {
		return gpDfltCurrttyp != null ? gpDfltCurrttyp.trim() : gpDfltCurrttyp;
	}

	public String getGpDfltEligGrp() {
		return gpDfltEligGrp != null ? gpDfltEligGrp.trim() : gpDfltEligGrp;
	}

	public String getGpDfltExrtdt() {
		return gpDfltExrtdt != null ? gpDfltExrtdt.trim() : gpDfltExrtdt;
	}

	public String getGpEligGrp() {
		return gpDfltExrtdt != null ? gpDfltExrtdt.trim() : gpDfltExrtdt;
	}

	public String getGpPaygroup() {
		return gpPaygroup != null ? gpPaygroup.trim() : gpPaygroup;
	}

	public String getGrade() {
		return grade != null ? grade.trim() : grade;
	}

	public Date getGradeEntryDt() {
		return this.gradeEntryDt;
	}

	public Date getHireDt() {
		return this.hireDt;
	}

	public String getHolidaySchedule() {
		return holidaySchedule != null ? holidaySchedule.trim() : holidaySchedule;
	}

	public Integer getHourlyRt() {
		return this.hourlyRt;
	}

	public String getHourlyRtFra() {
		return hourlyRtFra != null ? hourlyRtFra.trim() : hourlyRtFra;
	}

	public String getHrStatus() {
		return hrStatus != null ? hrStatus.trim() : hrStatus;
	}

	public String getInterctrWrksCncl() {
		return interctrWrksCncl != null ? interctrWrksCncl.trim() : interctrWrksCncl;
	}

	public String getJobDataSrcCd() {
		return jobDataSrcCd != null ? jobDataSrcCd.trim() : jobDataSrcCd;
	}

	public Date getJobEntryDt() {
		return this.jobEntryDt;
	}

	public String getJobIndicator() {
		return jobIndicator != null ? jobIndicator.trim() : jobIndicator;
	}

	public String getJobCode() {
		return jobCode != null ? jobCode.trim() : jobCode;
	}

	public String getLaborAgreement() {
		return laborAgreement != null ? laborAgreement.trim() : laborAgreement;
	}

	public String getLaborFacilityId() {
		return laborFacilityId != null ? laborFacilityId.trim() : laborFacilityId;
	}

	public String getLaborTypeGer() {
		return laborTypeGer != null ? laborTypeGer.trim() : laborTypeGer;
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
		return lastUpdatedUserId != null ? lastUpdatedUserId.trim() : lastUpdatedUserId;
	}

	public String getLayoffExemptFlag() {
		return layoffExemptFlag != null ? layoffExemptFlag.trim() : layoffExemptFlag;
	}

	public String getLayoffExemptRsn() {
		return layoffExemptRsn != null ? layoffExemptRsn.trim() : layoffExemptRsn;
	}

	public Date getLbrFacEntryDt() {
		return this.lbrFacEntryDt;
	}

	public String getLdwOvr() {
		return ldwOvr != null ? ldwOvr.trim() : ldwOvr;
	}

	public void setLdwOvr(String ldwOvr) {
		this.ldwOvr = ldwOvr != null ? ldwOvr.trim() : ldwOvr;
	}

	public String getLocation() {
		return location != null ? location.trim() : location;
	}

	public void setLocation(String location) {
		this.location = location != null ? location.trim() : location;
	}

	public Date getLstAsgnStartDt() {
		return this.lstAsgnStartDt;
	}

	public void setLstAsgnStartDt(Date lstAsgnStartDt) {
		this.lstAsgnStartDt = lstAsgnStartDt;
	}

	public String getLumpSumPay() {
		return lumpSumPay != null ? lumpSumPay.trim() : lumpSumPay;
	}

	public void setLumpSumPay(String lumpSumPay) {
		this.lumpSumPay = lumpSumPay != null ? lumpSumPay.trim() : lumpSumPay;
	}

	public Integer getMainApptNumJpn() {
		return this.mainApptNumJpn;
	}

	public void setMainApptNumJpn(Integer mainApptNumJpn) {
		this.mainApptNumJpn = mainApptNumJpn;
	}

	public Integer getMatriculaNbr() {
		return this.matriculaNbr;
	}

	public void setMatriculaNbr(Integer matriculaNbr) {
		this.matriculaNbr = matriculaNbr;
	}

	public Integer getMonthlyRt() {
		return this.monthlyRt;
	}

	public void setMonthlyRt(Integer monthlyRt) {
		this.monthlyRt = monthlyRt;
	}

	public String getOfficerCd() {
		return officerCd != null ? officerCd.trim() : officerCd;
	}

	public void setOfficerCd(String officerCd) {
		this.officerCd = officerCd != null ? officerCd.trim() : officerCd;
	}

	public Integer getPaidFte() {
		return this.paidFte;
	}

	public void setPaidFte(Integer paidFte) {
		this.paidFte = paidFte;
	}

	public Integer getPaidHours() {
		return this.paidHours;
	}

	public void setPaidHours(Integer paidHours) {
		this.paidHours = paidHours;
	}

	public String getPaidHrsFrequency() {
		return paidHrsFrequency != null ? paidHrsFrequency.trim() : paidHrsFrequency;
	}

	public void setPaidHrsFrequency(String paidHrsFrequency) {
		this.paidHrsFrequency = paidHrsFrequency != null ? paidHrsFrequency.trim() : paidHrsFrequency;
	}

	public String getPaySystemFlg() {
		return paySystemFlg != null ? paySystemFlg.trim() : paySystemFlg;
	}

	public void setPaySystemFlg(String paySystemFlg) {
		this.paySystemFlg = paySystemFlg != null ? paySystemFlg.trim() : paySystemFlg;
	}

	public String getPayUnionFee() {
		return payUnionFee != null ? payUnionFee.trim() : payUnionFee;
	}

	public void setPayUnionFee(String payUnionFee) {
		this.payUnionFee = payUnionFee != null ? payUnionFee.trim() : payUnionFee;
	}

	public String getPaygroup() {
		return paygroup != null ? paygroup.trim() : paygroup;
	}

	public void setPaygroup(String paygroup) {
		this.paygroup = paygroup != null ? paygroup.trim() : paygroup;
	}

	public String getPerOrg() {
		return perOrg != null ? perOrg.trim() : perOrg;
	}

	public void setPerOrg(String perOrg) {
		this.perOrg = perOrg != null ? perOrg.trim() : perOrg;
	}

	public String getPerformGroupGer() {
		return performGroupGer != null ? performGroupGer.trim() : performGroupGer;
	}

	public void setPerformGroupGer(String performGroupGer) {
		this.performGroupGer = performGroupGer != null ? performGroupGer.trim() : performGroupGer;
	}

	public String getPoiType() {
		return poiType != null ? poiType.trim() : poiType;
	}

	public void setPoiType(String poiType) {
		this.poiType = poiType != null ? poiType.trim() : poiType;
	}

	public Date getPositionEntryDt() {
		return this.positionEntryDt;
	}

	public void setPositionEntryDt(Date positionEntryDt) {
		this.positionEntryDt = positionEntryDt;
	}

	public String getPositionNbr() {
		return positionNbr != null ? positionNbr.trim() : positionNbr;
	}

	public void setPositionNbr(String positionNbr) {
		this.positionNbr = positionNbr != null ? positionNbr.trim() : positionNbr;
	}

	public String getPositionOverride() {
		return positionOverride != null ? positionOverride.trim() : positionOverride;
	}

	public void setPositionOverride(String positionOverride) {
		this.positionOverride = positionOverride != null ? positionOverride.trim() : positionOverride;
	}

	public String getPosnChangeRecord() {
		return posnChangeRecord != null ? posnChangeRecord.trim() : posnChangeRecord;
	}

	public void setPosnChangeRecord(String posnChangeRecord) {
		this.posnChangeRecord = posnChangeRecord != null ? posnChangeRecord.trim() : posnChangeRecord;
	}

	public String getProrateCntAmt() {
		return prorateCntAmt != null ? prorateCntAmt.trim() : prorateCntAmt;
	}

	public void setProrateCntAmt(String prorateCntAmt) {
		this.prorateCntAmt = prorateCntAmt != null ? prorateCntAmt.trim() : prorateCntAmt;
	}

	public String getRegulatoryRegion() {
		return regulatoryRegion != null ? regulatoryRegion.trim() : regulatoryRegion;
	}

	public void setRegulatoryRegion(String regulatoryRegion) {
		this.regulatoryRegion = regulatoryRegion != null ? regulatoryRegion.trim() : regulatoryRegion;
	}

	public String getRegTemp() {
		return regularOrTemporary != null ? regularOrTemporary.trim() : regularOrTemporary;
	}

	public void setRegTemp(String regularOrTemporary) {
		this.regularOrTemporary = regularOrTemporary != null ? regularOrTemporary.trim() : regularOrTemporary;
	}

	public String getReportsTo() {
		return reportsTo != null ? reportsTo.trim() : reportsTo;
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo != null ? reportsTo.trim() : reportsTo;
	}

	public String getSalAdminPlan() {
		return salAdminPlan != null ? salAdminPlan.trim() : salAdminPlan;
	}

	public void setSalAdminPlan(String salAdminPlan) {
		this.salAdminPlan = salAdminPlan != null ? salAdminPlan.trim() : salAdminPlan;
	}

	public String getSetIdDept() {
		return setIdDept != null ? setIdDept.trim() : setIdDept;
	}

	public void setSetIdDept(String setIdDept) {
		this.setIdDept = setIdDept != null ? setIdDept.trim() : setIdDept;
	}

	public String getSetIdJobCode() {
		return setIdJobCode != null ? setIdJobCode.trim() : setIdJobCode;
	}

	public void setSetidJobCode(String setIdJobCode) {
		this.setIdJobCode = setIdJobCode != null ? setIdJobCode.trim() : setIdJobCode;
	}

	public String getSetIdLbrAgrmnt() {
		return setidLbrAgrmnt != null ? setidLbrAgrmnt.trim() : setidLbrAgrmnt;
	}

	public void setSetidLbrAgrmnt(String setidLbrAgrmnt) {
		this.setidLbrAgrmnt = setidLbrAgrmnt != null ? setidLbrAgrmnt.trim() : setidLbrAgrmnt;
	}

	public String getSetidLocation() {
		return setidLocation != null ? setidLocation.trim() : setidLocation;
	}

	public void setSetidLocation(String setidLocation) {
		this.setidLocation = setidLocation != null ? setidLocation.trim() : setidLocation;
	}

	public String getSetIdSalary() {
		return setIdSalary != null ? setIdSalary.trim() : setIdSalary;
	}

	public void setSetIdSalary(String setIdSalary) {
		this.setIdSalary = setIdSalary != null ? setIdSalary.trim() : setIdSalary;
	}

	public String getSetIdSupvLvl() {
		return setIdSupvLvl != null ? setIdSupvLvl.trim() : setIdSupvLvl;
	}

	public void setSetIdSupvLvl(String setIdSupvLvl) {
		this.setIdSupvLvl = setIdSupvLvl != null ? setIdSupvLvl.trim() : setIdSupvLvl;
	}

	public String getShift() {
		return shift != null ? shift.trim() : shift;
	}

	public void setShift(String shift) {
		this.shift = shift != null ? shift.trim() : shift;
	}

	public Integer getShiftFactor() {
		return this.shiftFactor;
	}

	public void setShiftFactor(Integer shiftFactor) {
		this.shiftFactor = shiftFactor;
	}

	public Integer getShiftRt() {
		return this.shiftRt;
	}

	public void setShiftRt(Integer shiftRt) {
		this.shiftRt = shiftRt;
	}

	public String getSocSecRiskCode() {
		return socSecRiskCode != null ? socSecRiskCode.trim() : socSecRiskCode;
	}

	public void setSocSecRiskCode(String socSecRiskCode) {
		this.socSecRiskCode = socSecRiskCode != null ? socSecRiskCode.trim() : socSecRiskCode;
	}

	public String getSpkCommIdGer() {
		return spkCommIdGer != null ? spkCommIdGer.trim() : spkCommIdGer;
	}

	public void setSpkCommIdGer(String spkCommIdGer) {
		this.spkCommIdGer = spkCommIdGer != null ? spkCommIdGer.trim() : spkCommIdGer;
	}

	public Integer getStdHours() {
		return this.stdHours;
	}

	public void setStdHours(Integer stdHours) {
		this.stdHours = stdHours;
	}

	public String getStdHrsFrequency() {
		return stdHrsFrequency != null ? stdHrsFrequency.trim() : stdHrsFrequency;
	}

	public void setStdHrsFrequency(String stdHrsFrequency) {
		this.stdHrsFrequency = stdHrsFrequency != null ? stdHrsFrequency.trim() : stdHrsFrequency;
	}

	public Integer getStep() {
		return this.step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Date getStepEntryDt() {
		return this.stepEntryDt;
	}

	public void setStepEntryDt(Date stepEntryDt) {
		this.stepEntryDt = stepEntryDt;
	}

	public String getSupervisorId() {
		return supervisorId != null ? supervisorId.trim() : supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId != null ? supervisorId.trim() : supervisorId;
	}

	public String getSupvLvlId() {
		return supvLvlId != null ? supvLvlId.trim() : supvLvlId;
	}

	public void setSupvLvlId(String supvLvlId) {
		this.supvLvlId = supvLvlId != null ? supvLvlId.trim() : supvLvlId;
	}

	public String getTariffAreaGer() {
		return tariffAreaGer != null ? tariffAreaGer.trim() : tariffAreaGer;
	}

	public void setTariffAreaGer(String tariffAreaGer) {
		this.tariffAreaGer = tariffAreaGer != null ? tariffAreaGer.trim() : tariffAreaGer;
	}

	public String getTariffGer() {
		return tariffGer != null ? tariffGer.trim() : tariffGer;
	}

	public void setTariffGer(String tariffGer) {
		this.tariffGer = tariffGer != null ? tariffGer.trim() : tariffGer;
	}

	public String getTaxLocationCd() {
		return taxLocationCd != null ? taxLocationCd.trim() : taxLocationCd;
	}

	public void setTaxLocationCd(String taxLocationCd) {
		this.taxLocationCd = taxLocationCd != null ? taxLocationCd.trim() : taxLocationCd;
	}

	public Date getTerminationDt() {
		return this.terminationDt;
	}

	public void setTerminationDt(Date terminationDt) {
		this.terminationDt = terminationDt;
	}

	public String getUnionCd() {
		return unionCd != null ? unionCd.trim() : unionCd;
	}

	public void setUnionCd(String unionCd) {
		this.unionCd = unionCd != null ? unionCd.trim() : unionCd;
	}

	public Integer getUnionFeeAmount() {
		return this.unionFeeAmount;
	}

	public void setUnionFeeAmount(Integer unionFeeAmount) {
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
		return unionFullPart != null ? unionFullPart.trim() : unionFullPart;
	}

	public void setUnionFullPart(String unionFullPart) {
		this.unionFullPart = unionFullPart != null ? unionFullPart.trim() : unionFullPart;
	}

	public String getUnionPos() {
		return unionPos != null ? unionPos.trim() : unionPos;
	}

	public void setUnionPos(String unionPos) {
		this.unionPos = unionPos != null ? unionPos.trim() : unionPos;
	}

	public Date getUnionSeniorityDt() {
		return this.unionSeniorityDt;
	}

	public void setUnionSeniorityDt(Date unionSeniorityDt) {
		this.unionSeniorityDt = unionSeniorityDt;
	}

	public String getValue1Fra() {
		return value1Fra != null ? value1Fra.trim() : value1Fra;
	}

	public void setValue1Fra(String value1Fra) {
		this.value1Fra = value1Fra != null ? value1Fra.trim() : value1Fra;
	}

	public String getValue2Fra() {
		return value2Fra != null ? value2Fra.trim() : value2Fra;
	}

	public void setValue2Fra(String value2Fra) {
		this.value2Fra = value2Fra != null ? value2Fra.trim() : value2Fra;
	}

	public String getValue3Fra() {
		return value3Fra != null ? value3Fra.trim() : value3Fra;
	}

	public void setValue3Fra(String value3Fra) {
		this.value3Fra = value3Fra != null ? value3Fra.trim() : value3Fra;
	}

	public String getValue4Fra() {
		return value4Fra != null ? value4Fra.trim() : value4Fra;
	}

	public void setValue4Fra(String value4Fra) {
		this.value4Fra = value4Fra != null ? value4Fra.trim() : value4Fra;
	}

	public String getValue5Fra() {
		return value5Fra != null ? value5Fra.trim() : value5Fra;
	}

	public void setValue5Fra(String value5Fra) {
		this.value5Fra = value5Fra != null ? value5Fra.trim() : value5Fra;
	}

	public Integer getWorkDayHours() {
		return this.workDayHours;
	}

	public void setWorkDayHours(Integer workDayHours) {
		this.workDayHours = workDayHours;
	}

	public String getWppStopFlag() {
		return wppStopFlag != null ? wppStopFlag.trim() : wppStopFlag;
	}

	public void setWppStopFlag(String wppStopFlag) {
		this.wppStopFlag = wppStopFlag != null ? wppStopFlag.trim() : wppStopFlag;
	}

	public String getWrksCnclFunction() {
		return wrksCnclFunction != null ? wrksCnclFunction.trim() : wrksCnclFunction;
	}

	public void setWrksCnclFunction(String wrksCnclFunction) {
		this.wrksCnclFunction = wrksCnclFunction != null ? wrksCnclFunction.trim() : wrksCnclFunction;
	}

	public String getWrksCnclRoleChe() {
		return wrksCnclRoleChe != null ? wrksCnclRoleChe.trim() : wrksCnclRoleChe;
	}

	public void setWrksCnclRoleChe(String wrksCnclRoleChe) {
		this.wrksCnclRoleChe = wrksCnclRoleChe != null ? wrksCnclRoleChe.trim() : wrksCnclRoleChe;
	}

	/**
	 * HR01-Get-Job-Data
	 * Gets the employees data from the job table that needs to be interfaced to the legacy system
	 * @param employeeId
	 * @return
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
	 * finds PsJob records by employeeId, effectiveSequence, effectiveDate, and where employmentRecordNumber = 0
	 * @param employeeId
	 * @param effectiveDate
	 * @param effectiveSequence
	 * @return single PsJob record
	 */
	public static PsJob getJob(String employeeId, Date effectiveDate, BigDecimal effectiveSequence) {
		System.out.println("********** PsJob.getJob");
//		System.out.println("employeeId: " + employeeId);
//		System.out.println("effectiveDate: " + effectiveDate);
//		System.out.println("effectiveSequence: " + effectiveSequence);
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
	    				+ "AND p.effectiveDate = :effectiveDate "
	    				+ "AND p.effectiveSequence = :effectiveSequence "
	    				+ "AND p.employmentRecordNumber = 0 "
	    				, PsJob.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("effectiveDate", effectiveDate, TemporalType.DATE)
	    		    .setParameter("effectiveSequence", effectiveSequence)
	    		    .setHint(QueryHints.REFRESH, true)
	    		    .getResultList();
//    		System.out.println("getJob.resultList.size(): " + resultList.size());
	    	if(resultList != null && resultList.size() > 0) {
	    		PsJob result0 = resultList.get(0);
//	    		for(PsJob result : resultList) {
//		    		System.out.println("getJob.result.getAction(): " + result.getAction());
//		    		System.out.println("getJob.result.getEmployeeId(): " + result.getEmployeeId());
//		    		System.out.println("getJob.result.getActionReason(): " + result.getActionReason());
//		    		System.out.println("getJob.result.getEffectiveDate(): " + result.getEffectiveDate());
//		    		System.out.println("getJob.result.getEffseq(): " + result.getEffseq());
//	    		}
	    		return result0;
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

	/**
	 * HR04-Get-Job-Data
	 * Gets the employees data from the job table that needs to be interfaced to the legacy system
	 * @param employeeId
	 * @param effectiveDate
	 * @return
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
	    		    		+ "		AND pj.effectiveDate <= :effectiveDate "	
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
	    finally {
	    	em.close();
	    }
		return null;
	}

	/**
	 * Replaces HR05-Get-Region and HR05-Get-Region
	 * This procedure will get REG_REGION from PS_JOB
	 * @param employeeId
	 * @param employmentRecordNumber
	 * @return
	 */
	public String findRegionByEmployeeIdAndEmploymentRecordNumber(String employeeId, Integer employmentRecordNumber) {
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
	 * @param employeeId
	 * @param employmentRecordNumber
	 * @param jobCode
	 * @param effectiveDate
	 * @return
	 */
	//TODO: check if $ADJobStartYr, $ADJobStartMnth, $ADJobStartDay, $PSJobStartDt are used in the calling file???
	public String adGetJobStartDate(String employeeId, Integer employmentRecordNumber, String jobCode, Date effectiveDate) {
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
	 * 
	 * @param employeeId
	 * @param employmentRecordNumber
	 * @param effectiveDate
	 * @return
	 */
	public static Integer findMaxEffectiveSequenceByEmployeeIdAndEmploymentRecordNumberAndEffectiveDate(String employeeId, Integer employmentRecordNumber, Date effectiveDate) {
		//EFFSEQ = (SELECT MAX(EFFSEQ)
		//FROM PS_JOB RJ3
		//WHERE RJ3.EMPLID = RJ.EMPLID
		//AND RJ3.EMPL_RCD = RJ.EMPL_RCD
		//AND RJ3.EFFDT = RJ.EFFDT)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<Integer> resultList = em.createQuery("SELECT MAX(p.effectiveSequence) FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    				+ "AND p.employmentRecordNumber = :employmentRecordNumber "
	    				+ "AND p.effectiveDate <= :effectiveDate ",
	    				Integer.class)
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
	    finally {
	    	em.close();
	    }
	    return null;	
	}
	   
	/**
	 * 
	 * @param employeeId
	 * @param employmentRecordNumber
	 * @return
	 */
	public static Date findMaxEffectiveDateByEmployeeIdAndEmploymentRecordNumber(String employeeId, Integer employmentRecordNumber) {
		//EFFDT = (SELECT MAX(EFFDT)
		//FROM PS_JOB RJ2
		//WHERE RJ2.EMPLID = RJ.EMPLID
		//AND RJ2.EMPL_RCD = RJ.EMPL_RCD
		//AND RJ2.EFFDT <= $AsOfToday)
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
	    finally {
	    	em.close();
	    }
	    return null;	
	}

	/**
	 * 
	 * @param employeeId
	 * @param effectiveDate
	 * @return
	 */
	public static List<PsJob> findByEmployeeIdAndEffectiveDate(String employeeId, java.util.Date effectiveDate) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsJob> resultList = em.createQuery("SELECT p FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    				+ "AND p.effectiveDate <= :effectiveDate ",
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
	    finally {
	    	em.close();
	    }
	    return null;	
	}
	   
	/**
	 */
//	public static Integer findEmploymentRecordNumberByEmployeeId(String employeeId) {
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
	//TODO: findJobStartDateByEmployeeIdAndJobCodeAndEffectiveDateAndEffectiveSequence(String employeeId, String jobCode, Date effectiveDate, Integer effectiveSequence)
	   
	/**
	 * Replaces Check-If-Contractor from ZHRI100A.SQR
	 * Checks to see if the employee is a contractor       
	 * @param employeeId
	 * @return
	 */
	public static Boolean ZHRI100A_checkIfContractor(String employeeId) {
		//BEGIN-PROCEDURE CHECK-IF-CONTRACTOR
		//LET $Found = 'N'
		//BEGIN-SELECT
		//'X'
		//LET $Found = 'Y'
		//FROM PS_JOB RJ
		//WHERE RJ.EMPLID = $PSEmplid
		//		AND RJ.EMPL_CLASS = 'R'
		//		AND RJ.EFFDT = 
		//				(SELECT MAX(EFFDT) FROM PS_JOB RJ2
		//					WHERE RJ2.EMPLID = RJ.EMPLID
		//						AND RJ2.EMPL_RCD = RJ.EMPL_RCD
		//						AND RJ2.EFFDT <= $AsOfToday)
		//		AND RJ.EFFSEQ = 
		//				(SELECT MAX(EFFSEQ) FROM PS_JOB RJ3
		//					WHERE RJ3.EMPLID = RJ.EMPLID
		//						AND RJ3.EMPL_RCD = RJ.EMPL_RCD
		//						AND RJ3.EFFDT = RJ.EFFDT)
		//END-SELECT
		//END-PROCEDURE CHECK-IF-CONTRACTOR
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<Date> resultList = em.createQuery("SELECT p FROM PsJob p "
	    			+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    			+ "AND p.employeeClass = 'R' "
	    			+ "AND p.effectiveDate = "
	    			+ "		(SELECT MAX(p2.effectiveDate) FROM PsJob p2 "
	    			+ "			WHERE p2.employeeId = p.employeeId "
	    			+ "				AND p2.employmentRecordNumber = p.employmentRecordNumber "
	    			+ "				AND p2.effectiveDate <= :asOfToday) "
	    			+ "AND p.effectiveSequence = "
	    			+ "		(SELECT MAX(p3.effectiveSequence) FROM PsJob p3 "
	    			+ "			WHERE p3.employeeId = p.employeeId "
	    			+ "				AND p3.employmentRecordNumber = p.employmentRecordNumber "
	    			+ "				AND p3.effectiveDate = p.effectiveDate) ",
	    				Date.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("asOfToday", DateUtil.asOfToday(), TemporalType.DATE)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return true;
	    	}
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	    return false;	
	}
		   
	/**
	 * Replaces Check-If-Contractor from ZHRI100A.SQR
	 * Checks to see if the employee is a contractor       
	 * @param employeeId
	 * @param effectiveDate
	 * @param effectiveSequence
	 * @return
	 */
	public static boolean employeeIsContractor(String employeeId, Date effectiveDate, Integer effectiveSequence) {
		//BEGIN-PROCEDURE CHECK-IF-CONTRACTOR
		//LET $Found = 'N'
		//BEGIN-SELECT
		//'X'
		//LET $Found = 'Y'
		//FROM PS_JOB RJ
		//WHERE RJ.EMPLID = $PSEmplid
		//		AND RJ.EMPL_CLASS = 'R'
		//		AND RJ.EFFDT = 
		//				(SELECT MAX(EFFDT) FROM PS_JOB RJ2
		//					WHERE RJ2.EMPLID = RJ.EMPLID
		//						AND RJ2.EMPL_RCD = RJ.EMPL_RCD
		//						AND RJ2.EFFDT <= $AsOfToday)
		//		AND RJ.EFFSEQ = 
		//				(SELECT MAX(EFFSEQ) FROM PS_JOB RJ3
		//					WHERE RJ3.EMPLID = RJ.EMPLID
		//						AND RJ3.EMPL_RCD = RJ.EMPL_RCD
		//						AND RJ3.EFFDT = RJ.EFFDT)
		//END-SELECT
		//END-PROCEDURE CHECK-IF-CONTRACTOR
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsJob> resultList = em.createQuery("SELECT p FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    				+ "AND p.effectiveDate <= :effectiveDate "
	    				+ "AND p.effectiveSequence = :effectiveSequence "
	    				+ "AND p.employeeClass = :employeeClass ", PsJob.class)
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
	    finally {
	    	em.close();
	    }
	    return false;	
	}

	/**
	 * Check-If-Correct102A from ZHRI100A.SQR
	 * Checks to see if 102A process has JOB row
	 * @param psEmplId
	 * @param psEffectiveDate
	 * @param processName
	 * @return true if corresponding PsJob record found
	 */
	public static Boolean ZHRI100A_checkIfCorrect102A(String employeeId, Date effectiveDate, String processName) {
		System.out.println("********** ZHRI100A_checkIfCorrect102A");
//		System.out.println("************** employeeId: " + employeeId);
//		System.out.println("************** effectiveDate: " + effectiveDate);
//		System.out.println("************** processName: " + processName);
		//BEGIN-PROCEDURE CHECK-IF-CORRECT102A
		//LET $OK-To-Process = 'N'
		//IF $WrkProcess = 'ZHRI102A'
		Date dt102 = effectiveDate;
		if("ZHRI102A".equalsIgnoreCase(processName)) {
			//DO DTU-ADD-DAYS($PSEffDt, 1, $Dt102)
			//add a day to current effective date
			dt102 = DateUtil.addDays(effectiveDate, 1);
		}
		//!ELSE
			//!LET $Dt102 = $PSEffdt
			//*do nothing*
		//END-IF
		//BEGIN-SELECT
		//'XX'
		//LET $OK-To-Process = 'Y'
		//FROM PS_JOB PS_JOB
		//WHERE PS_JOB.EMPLID = $PSEmplId
		//		AND TO_CHAR(PS_JOB.EFFDT, 'YYYY-MM-DD') = $Dt102
		//END-SELECT
		//END-PROCEDURE CHECK-IF-CORRECT102A
//		System.out.println("************** Dt102: " + dt102);
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsJob> resultList = em.createQuery("SELECT p FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
	    				+ "AND p.effectiveDate <= :dt102 ", PsJob.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("dt102", dt102, TemporalType.DATE)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return true;
	    	}
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	    return false;	
	}
}
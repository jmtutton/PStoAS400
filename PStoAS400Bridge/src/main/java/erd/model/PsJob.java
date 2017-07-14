package erd.model;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.config.QueryHints;

import erd.ErdUtils;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the PS_JOB database table.
 * Employee Job History
 * Contains job records along with other data. Effective dated someone's job history will be stored in this table.
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_JOB")
@NamedQuery(name="PsJob.findAll", query="SELECT p FROM PsJob p")
public class PsJob implements Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="EMPL_RCD", nullable=false, precision=38)
	private BigInteger employeeRecordNumber;

	@Column(name="ABSENCE_SYSTEM_CD", nullable=false, length=3)
	private String absenceSystem;

	@Column(name="ACCDNT_CD_FRA", nullable=false, length=1)
	private String accidentCode;

	@Column(name="ACCT_CD", nullable=false, length=25)
	private String accountCode;

	@Column(name="\"ACTION\"", nullable=false, length=3)
	private String action;

	@Column(name="ACTION_DT")
	@Temporal(TemporalType.DATE)
	private Date actionDate;

	@Column(name="ACTION_REASON", nullable=false, length=3)
	private String actionReason;

	@Column(name="ADDS_TO_FTE_ACTUAL", nullable=false, length=1)
	private String addsToFteActualCount;

	@Column(name="ANNL_BENEF_BASE_RT", nullable=false, precision=18, scale=3)
	private BigDecimal annualBenefitsBaseRate;

	@Column(name="ANNUAL_RT", nullable=false, precision=18, scale=3)
	private BigDecimal annualRate;

	@Column(name="APPT_TYPE", nullable=false, length=1)
	private String appointmentType;

	@Column(name="ASGN_END_DT")
	@Temporal(TemporalType.DATE)
	private Date assignmentEndDate;

	@Column(name="ASGN_START_DT")
	@Temporal(TemporalType.DATE)
	private Date assignmentStartDate;

	@Column(name="AUTO_END_FLG", nullable=false, length=1)
	private String autoEndJob;

	@Column(name="BARG_UNIT", nullable=false, length=4)
	private String bargainingUnit;

	@Column(name="BAS_ACTION", nullable=false, length=3)
	private String benefitsAdministrationAction;

	@Column(name="BAS_GROUP_ID", nullable=false, length=3)
	private String benefitsAdministrationGroupId;

	@Column(name="BEN_STATUS", nullable=false, length=4)
	private String benefitsEmployeeStatus;

	@Column(name="BENEFIT_SYSTEM", nullable=false, length=2)
	private String benefitsSystem;

	@Column(name="BORDER_WALKER", nullable=false, length=1)
	private String crossBorderWorker;

	@Column(name="BUSINESS_UNIT", nullable=false, length=5)
	private String businessUnit;

	@Column(name="CHANGE_AMT", nullable=false, precision=18, scale=6)
	private BigDecimal changeAmount;

	@Column(name="CHANGE_PCT", nullable=false, precision=6, scale=3)
	private BigDecimal changePercent;

	@Column(name="CLASS_INDC", nullable=false, length=1)
	private String classifiedOrUnclassified;

	@Column(name="COBRA_ACTION", nullable=false, length=3)
	private String cobraAction;

	@Column(name="COMP_FREQUENCY", nullable=false, length=5)
	private String compensationFrequency;

	@Column(name="COMPANY", nullable=false, length=3)
	private String company;

	@Column(name="COMPRATE", nullable=false, precision=18, scale=6)
	private BigDecimal compensationRate;

	@Column(name="CONTRACT_NUM", nullable=false, length=25)
	private String contractNumber;

	@Column(name="CTG_RATE", nullable=false, precision=38)
	private BigInteger categoryRate;

	@Column(name="CUR_RT_TYPE", nullable=false, length=5)
	private String currencyRateType;

	@Column(name="CURRENCY_CD", nullable=false, length=3)
	private String currencyCode;

	@Column(name="CURRENCY_CD1", nullable=false, length=3)
	private String currencyCode1;

	@Column(name="DAILY_RT", nullable=false, precision=18, scale=3)
	private BigDecimal dailyRate;

	@Column(name="DEPT_ENTRY_DT")
	@Temporal(TemporalType.DATE)
	private Date departmentEntryDate;

	@Column(name="DEPTID", nullable=false, length=10)
	private String departmentId;

	@Column(name="DIRECTLY_TIPPED", nullable=false, length=1)
	private String directlyTipped;

	@Column(name="EARNS_DIST_TYPE", nullable=false, length=1)
	private String earningsDistributionType;

	@Column(name="EEO_CLASS", nullable=false, length=1)
	private String eeoClass;

	@Column(name="EFFDT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="EFFSEQ", nullable=false, precision=38)
	private BigInteger effectiveSequence;

	@Column(name="ELIG_CONFIG1", nullable=false, length=10)
	private String eligibilityConfiguration1;

	@Column(name="ELIG_CONFIG2", nullable=false, length=10)
	private String eligibilityConfiguration2;

	@Column(name="ELIG_CONFIG3", nullable=false, length=10)
	private String eligibilityConfiguration3;

	@Column(name="ELIG_CONFIG4", nullable=false, length=10)
	private String eligibilityConfiguration4;

	@Column(name="ELIG_CONFIG5", nullable=false, length=10)
	private String eligibilityConfiguration5;

	@Column(name="ELIG_CONFIG6", nullable=false, length=10)
	private String eligibilityConfiguration6;

	@Column(name="ELIG_CONFIG7", nullable=false, length=10)
	private String eligibilityConfiguration7;

	@Column(name="ELIG_CONFIG8", nullable=false, length=10)
	private String eligibilityConfiguration8;

	@Column(name="ELIG_CONFIG9", nullable=false, length=10)
	private String eligibilityConfiguration9;

	@Column(name="EMPL_CLASS", nullable=false, length=3)
	private String employeeClass;

	@Column(name="EMPL_CTG", nullable=false, length=6)
	private String employeeCategory;

	@Column(name="EMPL_CTG_L1", nullable=false, length=6)
	private String employeeSubcategory;

	@Column(name="EMPL_CTG_L2", nullable=false, length=6)
	private String employeeSubcategory2;

	@Column(name="EMPL_STATUS", nullable=false, length=1)
	private String employeeStatus;

	@Column(name="EMPL_TYPE", nullable=false, length=1)
	private String employeeType;

	@Column(name="ENCUMB_OVERRIDE", nullable=false, length=1)
	private String encumbranceOverride;

	@Column(name="ENTRY_DATE")
	@Temporal(TemporalType.DATE)
	private Date entryDate;

	@Column(name="ESTABID", nullable=false, length=12)
	private String establishmentId;

	@Column(name="EXEMPT_HOURS_MONTH", nullable=false, precision=38)
	private BigInteger exemptedHoursMonth;

	@Column(name="EXEMPT_JOB_LBR", nullable=false, length=1)
	private String exempted;

	@Column(name="EXPECTED_END_DATE")
	@Temporal(TemporalType.DATE)
	private Date expectedEndDate;

	@Column(name="EXPECTED_RETURN_DT")
	@Temporal(TemporalType.DATE)
	private Date expectedReturnDate;

	@Column(name="FICA_STATUS_EE", nullable=false, length=1)
	private String ficaStatus;

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
	private String functionCode;

	@Column(name="GL_PAY_TYPE", nullable=false, length=6)
	private String glPayType;

	@Column(name="GP_ASOF_DT_EXG_RT", nullable=false, length=1)
	private String useRateAsOf;

	@Column(name="GP_DFLT_CURRTTYP", nullable=false, length=1)
	private String usePayGroupRateType;

	@Column(name="GP_DFLT_ELIG_GRP", nullable=false, length=1)
	private String usePayGroupEligibility;

	@Column(name="GP_DFLT_EXRTDT", nullable=false, length=1)
	private String usePayGroupAsOfDate;

	@Column(name="GP_ELIG_GRP", nullable=false, length=10)
	private String eligibilityGroup;

	@Column(name="GP_PAYGROUP", nullable=false, length=10)
	private String gpPayGroup;

	@Column(name="GRADE", nullable=false, length=3)
	private String grade;

	@Column(name="GRADE_ENTRY_DT")
	@Temporal(TemporalType.DATE)
	private Date gradeEntryDate;

	@Column(name="HIRE_DT")
	@Temporal(TemporalType.DATE)
	private Date hireDate;

	@Column(name="HOLIDAY_SCHEDULE", nullable=false, length=6)
	private String holidaySchedule;

	@Column(name="HOURLY_RT", nullable=false, precision=18, scale=6)
	private BigDecimal hourlyRate;

	@Column(name="HOURLY_RT_FRA", nullable=false, length=3)
	private String hourlyRateFrance;

	@Column(name="HR_STATUS", nullable=false, length=1)
	private String hrStatus;

	@Column(name="INTERCTR_WRKS_CNCL", nullable=false, length=1)
	private String worksCouncilRole;

	@Column(name="JOB_DATA_SRC_CD", nullable=false, length=3)
	private String jobSorceCode;

	@Column(name="JOB_ENTRY_DT")
	@Temporal(TemporalType.DATE)
	private Date jobEntryDate;

	@Column(name="JOB_INDICATOR", nullable=false, length=1)
	private String jobIndicator;

	@Column(name="JOBCODE", nullable=false, length=6)
	private String jobCode;

	@Column(name="LABOR_AGREEMENT", nullable=false, length=6)
	private String laborAgreement;

	@Column(name="LABOR_FACILITY_ID", nullable=false, length=10)
	private String laborFacilityId;

	@Column(name="LABOR_TYPE_GER", nullable=false, length=1)
	private String laborType;

	@Column(name="LAST_DATE_WORKED")
	@Temporal(TemporalType.DATE)
	private Date lastDateWorked;

	@Column(name="LAST_HIRE_DT")
	@Temporal(TemporalType.DATE)
	private Date lastHireDate;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="LAYOFF_EXEMPT_FLAG", nullable=false, length=1)
	private String layoffExemptFlag;

	@Column(name="LAYOFF_EXEMPT_RSN", nullable=false, length=11)
	private String layoffExemptReason;

	@Column(name="LBR_FAC_ENTRY_DT")
	@Temporal(TemporalType.DATE)
	private Date laborFacilityEntryDate;

	@Column(name="LDW_OVR", nullable=false, length=1)
	private String overrideLastDateWorked;

	@Column(name="LOCATION", nullable=false, length=10)
	private String location;

	@Column(name="LST_ASGN_START_DT")
	@Temporal(TemporalType.DATE)
	private Date lastAssignmentStartDate;

	@Column(name="LUMP_SUM_PAY", nullable=false, length=1)
	private String lumpSumPay;

	@Column(name="MAIN_APPT_NUM_JPN", nullable=false, precision=38)
	private BigInteger mainAppointmentNumber;

	@Column(name="MATRICULA_NBR", nullable=false, precision=38)
	private BigInteger matriculaNumber;

	@Column(name="MONTHLY_RT", nullable=false, precision=18, scale=3)
	private BigDecimal monthlyRate;

	@Column(name="OFFICER_CD", nullable=false, length=1)
	private String officerCode;

	@Column(name="PAID_FTE", nullable=false, precision=7, scale=6)
	private BigDecimal paidFte;

	@Column(name="PAID_HOURS", nullable=false, precision=6, scale=2)
	private BigDecimal paidHours;

	@Column(name="PAID_HRS_FREQUENCY", nullable=false, length=5)
	private String paidHoursFrequency;

	@Column(name="PAY_SYSTEM_FLG", nullable=false, length=2)
	private String paySystemFlag;

	@Column(name="PAY_UNION_FEE", nullable=false, length=1)
	private String payUnionFee;

	@Column(name="PAYGROUP", nullable=false, length=3)
	private String payGroup;

	@Column(name="PER_ORG", nullable=false, length=3)
	private String organizationalRelationship;

	@Column(name="PERFORM_GROUP_GER", nullable=false, length=2)
	private String performanceGroup;

	@Column(name="POI_TYPE", nullable=false, length=5)
	private String personOfInterestType;

	@Column(name="POSITION_ENTRY_DT")
	@Temporal(TemporalType.DATE)
	private Date positionEntryDate;

	@Column(name="POSITION_NBR", nullable=false, length=8)
	private String positionNumber;

	@Column(name="POSITION_OVERRIDE", nullable=false, length=1)
	private String positionOverride;

	@Column(name="POSN_CHANGE_RECORD", nullable=false, length=1)
	private String positionChangeRecord;

	@Column(name="PRORATE_CNT_AMT", nullable=false, length=1)
	private String prorateContractChangeAmount;

	@Column(name="REG_REGION", nullable=false, length=5)
	private String region;

	@Column(name="REG_TEMP", nullable=false, length=1)
	private String regularOrTemporary;

	@Column(name="REPORTS_TO", nullable=false, length=8)
	private String reportsTo;

	@Column(name="SAL_ADMIN_PLAN", nullable=false, length=4)
	private String salaryAdministrationPlan;

	@Column(name="SETID_DEPT", nullable=false, length=5)
	private String setIdDepartment;

	@Column(name="SETID_JOBCODE", nullable=false, length=5)
	private String setIdJobCode;

	@Column(name="SETID_LBR_AGRMNT", nullable=false, length=5)
	private String setIdLaborAgreement;

	@Column(name="SETID_LOCATION", nullable=false, length=5)
	private String setIdLocation;

	@Column(name="SETID_SALARY", nullable=false, length=5)
	private String setIdSalary;

	@Column(name="SETID_SUPV_LVL", nullable=false, length=5)
	private String setIdSupervisorLevel;

	@Column(name="SHIFT", nullable=false, length=1)
	private String shift;

	@Column(name="SHIFT_FACTOR", nullable=false, precision=4, scale=3)
	private BigDecimal shiftFactor;

	@Column(name="SHIFT_RT", nullable=false, precision=18, scale=6)
	private BigDecimal shiftRate;

	@Column(name="SOC_SEC_RISK_CODE", nullable=false, length=3)
	private String socSecRiskCode;

	@Column(name="SPK_COMM_ID_GER", nullable=false, length=9)
	private String spokesmanCommitteeId;

	@Column(name="STD_HOURS", nullable=false, precision=6, scale=2)
	private BigDecimal standardHours;

	@Column(name="STD_HRS_FREQUENCY", nullable=false, length=5)
	private String standardHoursFrequency;

	@Column(name="STEP", nullable=false, precision=38)
	private BigInteger frequencyStep;

	@Column(name="STEP_ENTRY_DT")
	@Temporal(TemporalType.DATE)
	private Date stepEntryDate;

	@Column(name="SUPERVISOR_ID", nullable=false, length=11)
	private String supervisorId;

	@Column(name="SUPV_LVL_ID", nullable=false, length=8)
	private String supervisorLevelId;

	@Column(name="TARIFF_AREA_GER", nullable=false, length=3)
	private String tariffArea;

	@Column(name="TARIFF_GER", nullable=false, length=2)
	private String tariff;

	@Column(name="TAX_LOCATION_CD", nullable=false, length=10)
	private String taxLocationCode;

	@Column(name="TERMINATION_DT")
	@Temporal(TemporalType.DATE)
	private Date terminationDate;

	@Column(name="UNION_CD", nullable=false, length=3)
	private String unionCode;

	@Column(name="UNION_FEE_AMOUNT", nullable=false, precision=8, scale=2)
	private BigDecimal unionFeeAmount;

	@Column(name="UNION_FEE_END_DT")
	@Temporal(TemporalType.DATE)
	private Date unionFeeEndDate;

	@Column(name="UNION_FEE_START_DT")
	@Temporal(TemporalType.DATE)
	private Date unionFeeStartDate;

	@Column(name="UNION_FULL_PART", nullable=false, length=1)
	private String unionParticipation;

	@Column(name="UNION_POS", nullable=false, length=1)
	private String unionPosition;

	@Column(name="UNION_SENIORITY_DT")
	@Temporal(TemporalType.DATE)
	private Date unionSeniorityDate;

	@Column(name="VALUE_1_FRA", nullable=false, length=5)
	private String value1;

	@Column(name="VALUE_2_FRA", nullable=false, length=5)
	private String value2;

	@Column(name="VALUE_3_FRA", nullable=false, length=5)
	private String value3;

	@Column(name="VALUE_4_FRA", nullable=false, length=5)
	private String value4;

	@Column(name="VALUE_5_FRA", nullable=false, length=5)
	private String value5;

	@Column(name="WORK_DAY_HOURS", nullable=false, precision=6, scale=2)
	private BigDecimal workDayHours;

	@Column(name="WPP_STOP_FLAG", nullable=false, length=1)
	private String stopWageProgression;

	@Column(name="WRKS_CNCL_FUNCTION", nullable=false, length=1)
	private String workCouncilFunction;

	@Column(name="WRKS_CNCL_ROLE_CHE", nullable=false, length=30)
	private String workCouncilRole;

	public PsJob() {
	}

	public String getAbsenceSystem() {
		return absenceSystem != null ? absenceSystem.trim() : absenceSystem;
	}

	public void setAbsenceSystem(String absenceSystem) {
		this.absenceSystem = absenceSystem != null ? absenceSystem.trim() : absenceSystem;
	}

	public String getAccidentCode() {
		return accidentCode != null ? accidentCode.trim() : accidentCode;
	}

	public void setAccidentCode(String accidentCode) {
		this.accidentCode = accidentCode != null ? accidentCode.trim() : accidentCode;
	}

	public String getAccountCode() {
		return accountCode != null ? accountCode.trim() : accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode != null ? accountCode.trim() : accountCode;
	}

	public String getAction() {
		return action != null ? action.trim() : action;
	}

	public Date getActionDate() {
		return this.actionDate;
	}

	public String getActionReason() {
		return actionReason != null ? actionReason.trim() : actionReason;
	}

	public void setActionReason(String actionReason) {
		this.actionReason = actionReason != null ? actionReason.trim() : actionReason;
	}

	public String getAddsToFteActualCount() {
		return addsToFteActualCount != null ? addsToFteActualCount.trim() : addsToFteActualCount;
	}

	public BigDecimal getAnnualBenefitsBaseRate() {
		return this.annualBenefitsBaseRate;
	}

	public BigDecimal getAnnualRate() {
		return this.annualRate;
	}

	public String getAppointmentType() {
		return appointmentType != null ? appointmentType.trim() : appointmentType;
	}

	public Date getAssignmentEndDate() {
		return this.assignmentEndDate;
	}

	public Date getAssignmentStartDate() {
		return this.assignmentStartDate;
	}

	public String getAutoEndJob() {
		return autoEndJob != null ? autoEndJob.trim() : autoEndJob;
	}

	public String getBargainingUnit() {
		return bargainingUnit != null ? bargainingUnit.trim() : bargainingUnit;
	}

	public String getBenefitsAdministrationAction() {
		return benefitsAdministrationAction != null ? benefitsAdministrationAction.trim() : benefitsAdministrationAction;
	}

	public String getBenefitsAdministrationGroupId() {
		return benefitsAdministrationGroupId != null ? benefitsAdministrationGroupId.trim() : benefitsAdministrationGroupId;
	}

	public String getBenefitsEmployeeStatus() {
		return benefitsEmployeeStatus != null ? benefitsEmployeeStatus.trim() : benefitsEmployeeStatus;
	}

	public String getBenefitsSystem() {
		return benefitsSystem != null ? benefitsSystem.trim() : benefitsSystem;
	}

	public String getCrossBorderWorker() {
		return crossBorderWorker != null ? crossBorderWorker.trim() : crossBorderWorker;
	}

	public String getBusinessUnit() {
		return businessUnit != null ? businessUnit.trim() : businessUnit;
	}

	public BigDecimal getChangeAmount() {
		return this.changeAmount;
	}

	public BigDecimal getChangePercent() {
		return this.changePercent;
	}

	public String getClassifiedOrUnclassified() {
		return classifiedOrUnclassified != null ? classifiedOrUnclassified.trim() : classifiedOrUnclassified;
	}

	public String getCobraAction() {
		return cobraAction != null ? cobraAction.trim() : cobraAction;
	}

	public String getCompensationFrequency() {
		return compensationFrequency != null ? compensationFrequency.trim() : compensationFrequency;
	}

	public String getCompany() {
		return company != null ? company.trim() : company;
	}

	public BigDecimal getCompensationRate() {
		return this.compensationRate;
	}

	public String getContractNumber() {
		return contractNumber != null ? contractNumber.trim() : contractNumber;
	}

	public BigInteger getCategoryRate() {
		return this.categoryRate;
	}

	public String getCurrencyRateType() {
		return currencyRateType != null ? currencyRateType.trim() : currencyRateType;
	}

	public String getCurrencyCode() {
		return currencyCode != null ? currencyCode.trim() : currencyCode;
	}

	public String getCurrencyCode1() {
		return currencyCode1 != null ? currencyCode1.trim() : currencyCode1;
	}

	public BigDecimal getDailyRate() {
		return this.dailyRate;
	}

	public Date getDepartmentEntryDate() {
		return this.departmentEntryDate;
	}

	public String getDepartmentId() {
		return departmentId != null ? departmentId.trim() : departmentId;
	}

	public String getDirectlyTipped() {
		return directlyTipped != null ? directlyTipped.trim() : directlyTipped;
	}

	public String getEarningsDistributionType() {
		return earningsDistributionType != null ? earningsDistributionType.trim() : earningsDistributionType;
	}

	public String getEeoClass() {
		return eeoClass != null ? eeoClass.trim() : eeoClass;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public BigInteger getEffseq() {
		return this.effectiveSequence;
	}

	public String getEligibilityConfiguration1() {
		return eligibilityConfiguration1 != null ? eligibilityConfiguration1.trim() : eligibilityConfiguration1;
	}

	public String getEligibilityConfiguration2() {
		return eligibilityConfiguration2 != null ? eligibilityConfiguration2.trim() : eligibilityConfiguration2;
	}

	public String getEligibilityConfiguration3() {
		return eligibilityConfiguration3 != null ? eligibilityConfiguration3.trim() : eligibilityConfiguration3;
	}

	public String getEligibilityConfiguration4() {
		return eligibilityConfiguration4 != null ? eligibilityConfiguration4.trim() : eligibilityConfiguration4;
	}

	public String getEligibilityConfiguration5() {
		return eligibilityConfiguration5 != null ? eligibilityConfiguration5.trim() : eligibilityConfiguration5;
	}

	public String getEligibilityConfiguration6() {
		return eligibilityConfiguration6 != null ? eligibilityConfiguration6.trim() : eligibilityConfiguration6;
	}

	public String getEligibilityConfiguration7() {
		return eligibilityConfiguration7 != null ? eligibilityConfiguration7.trim() : eligibilityConfiguration7;
	}

	public String getEligibilityConfiguration8() {
		return eligibilityConfiguration8 != null ? eligibilityConfiguration8.trim() : eligibilityConfiguration8;
	}

	public String getEligibilityConfiguration9() {
		return eligibilityConfiguration9 != null ? eligibilityConfiguration9.trim() : eligibilityConfiguration9;
	}

	public String getEmployeeClass() {
		return employeeClass != null ? employeeClass.trim() : employeeClass;
	}

	public String getEmployeeCategory() {
		return employeeCategory != null ? employeeCategory.trim() : employeeCategory;
	}

	public String getEmployeeSubcategory() {
		return employeeSubcategory != null ? employeeSubcategory.trim() : employeeSubcategory;
	}

	public String getEmployeeSubcategory2() {
		return employeeSubcategory2 != null ? employeeSubcategory2.trim() : employeeSubcategory2;
	}

	public BigInteger getEmployeeRecordNumber() {
		return this.employeeRecordNumber;
	}

	public String getEmployeeStatus() {
		return employeeStatus != null ? employeeStatus.trim() : employeeStatus;
	}

	public String getEmployeeType() {
		return employeeType != null ? employeeType.trim() : employeeType;
	}

	public String getEmployeeId() {
		return employeeId != null ? employeeId.trim() : employeeId;
	}

	public String getEncumbranceOverride() {
		return encumbranceOverride != null ? encumbranceOverride.trim() : encumbranceOverride;
	}

	public Date getEntryDate() {
		return this.entryDate;
	}

	public String getEstablishmentId() {
		return establishmentId != null ? establishmentId.trim() : establishmentId;
	}

	public BigInteger getExemptedHoursMonth() {
		return this.exemptedHoursMonth;
	}

	public String getExempted() {
		return exempted != null ? exempted.trim() : exempted;
	}

	public Date getExpectedEndDate() {
		return this.expectedEndDate;
	}

	public Date getExpectedReturnDate() {
		return this.expectedReturnDate;
	}

	public String getFicaStatus() {
		return ficaStatus != null ? ficaStatus.trim() : ficaStatus;
	}

	public String getFlsaStatus() {
		return flsaStatus != null ? flsaStatus.trim() : flsaStatus;
	}

	public Date getForcePublish() {
		return this.forcePublish;
	}

	public BigDecimal getFte() {
		return this.fte;
	}

	public String getFullPartTime() {
		return fullOrPartTime != null ? fullOrPartTime.trim() : fullOrPartTime;
	}

	public String getFunctionCode() {
		return functionCode != null ? functionCode.trim() : functionCode;
	}

	public String getGlPayType() {
		return glPayType != null ? glPayType.trim() : glPayType;
	}

	public String getUseRateAsOf() {
		return useRateAsOf != null ? useRateAsOf.trim() : useRateAsOf;
	}

	public String getUsePayGroupRateType() {
		return usePayGroupRateType != null ? usePayGroupRateType.trim() : usePayGroupRateType;
	}

	public String getUsePayGroupEligibility() {
		return usePayGroupEligibility != null ? usePayGroupEligibility.trim() : usePayGroupEligibility;
	}

	public String getUsePayGroupAsOfDate() {
		return usePayGroupAsOfDate != null ? usePayGroupAsOfDate.trim() : usePayGroupAsOfDate;
	}

	public String getEligibilityGroup() {
		return eligibilityGroup != null ? eligibilityGroup.trim() : eligibilityGroup;
	}

	public String getPayGroup() {
		return payGroup != null ? payGroup.trim() : payGroup;
	}

	public String getGrade() {
		return grade != null ? grade.trim() : grade;
	}

	public Date getGradeEntryDate() {
		return this.gradeEntryDate;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public String getHolidaySchedule() {
		return holidaySchedule != null ? holidaySchedule.trim() : holidaySchedule;
	}

	public BigDecimal getHourlyRate() {
		return this.hourlyRate;
	}

	public String getHourlyRateFrance() {
		return hourlyRateFrance != null ? hourlyRateFrance.trim() : hourlyRateFrance;
	}

	public String getHrStatus() {
		return hrStatus != null ? hrStatus.trim() : hrStatus;
	}

	public String getWorksCouncilRole() {
		return worksCouncilRole != null ? worksCouncilRole.trim() : worksCouncilRole;
	}

	public String getJobSorceCode() {
		return jobSorceCode != null ? jobSorceCode.trim() : jobSorceCode;
	}

	public Date getJobEntryDate() {
		return this.jobEntryDate;
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

	public String getLaborType() {
		return laborType != null ? laborType.trim() : laborType;
	}

	public Date getLastDateWorked() {
		return this.lastDateWorked;
	}

	public Date getLastHireDate() {
		return this.lastHireDate;
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

	public String getLayoffExemptReason() {
		return layoffExemptReason != null ? layoffExemptReason.trim() : layoffExemptReason;
	}

	public Date getLaborFacilityEntryDate() {
		return this.laborFacilityEntryDate;
	}

	public String getOverrideLastDateWorked() {
		return overrideLastDateWorked != null ? overrideLastDateWorked.trim() : overrideLastDateWorked;
	}

	public void setOverrideLastDateWorked(String overrideLastDateWorked) {
		this.overrideLastDateWorked = overrideLastDateWorked != null ? overrideLastDateWorked.trim() : overrideLastDateWorked;
	}

	public String getLocation() {
		return location != null ? location.trim() : location;
	}

	public void setLocation(String location) {
		this.location = location != null ? location.trim() : location;
	}

	public Date getLastAssignmentStartDate() {
		return this.lastAssignmentStartDate;
	}

	public void setLastAssignmentStartDate(Date lastAssignmentStartDate) {
		this.lastAssignmentStartDate = lastAssignmentStartDate;
	}

	public String getLumpSumPay() {
		return lumpSumPay != null ? lumpSumPay.trim() : lumpSumPay;
	}

	public void setLumpSumPay(String lumpSumPay) {
		this.lumpSumPay = lumpSumPay != null ? lumpSumPay.trim() : lumpSumPay;
	}

	public BigInteger getMainAppointmentNumber() {
		return this.mainAppointmentNumber;
	}

	public void setMainAppointmentNumber(BigInteger mainAppointmentNumber) {
		this.mainAppointmentNumber = mainAppointmentNumber;
	}

	public BigInteger getMatriculaNumber() {
		return this.matriculaNumber;
	}

	public void setMatriculaNumber(BigInteger matriculaNumber) {
		this.matriculaNumber = matriculaNumber;
	}

	public BigDecimal getMonthlyRate() {
		return this.monthlyRate;
	}

	public void setMonthlyRate(BigDecimal monthlyRate) {
		this.monthlyRate = monthlyRate;
	}

	public String getOfficerCode() {
		return officerCode != null ? officerCode.trim() : officerCode;
	}

	public void setOfficerCode(String officerCode) {
		this.officerCode = officerCode != null ? officerCode.trim() : officerCode;
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

	public String getPaidHoursFrequency() {
		return paidHoursFrequency != null ? paidHoursFrequency.trim() : paidHoursFrequency;
	}

	public void setPaidHoursFrequency(String paidHoursFrequency) {
		this.paidHoursFrequency = paidHoursFrequency != null ? paidHoursFrequency.trim() : paidHoursFrequency;
	}

	public String getPaySystemFlag() {
		return paySystemFlag != null ? paySystemFlag.trim() : paySystemFlag;
	}

	public void setPaySystemFlag(String paySystemFlag) {
		this.paySystemFlag = paySystemFlag != null ? paySystemFlag.trim() : paySystemFlag;
	}

	public String getPayUnionFee() {
		return payUnionFee != null ? payUnionFee.trim() : payUnionFee;
	}

	public void setPayUnionFee(String payUnionFee) {
		this.payUnionFee = payUnionFee != null ? payUnionFee.trim() : payUnionFee;
	}

	public String getPaygroup() {
		return payGroup != null ? payGroup.trim() : payGroup;
	}

	public void setPaygroup(String payGroup) {
		this.payGroup = payGroup != null ? payGroup.trim() : payGroup;
	}

	public String getOrganizationalRelationship() {
		return organizationalRelationship != null ? organizationalRelationship.trim() : organizationalRelationship;
	}

	public void setOrganizationalRelationship(String organizationalRelationship) {
		this.organizationalRelationship = organizationalRelationship != null ? organizationalRelationship.trim() : organizationalRelationship;
	}

	public String getPerformanceGroup() {
		return performanceGroup != null ? performanceGroup.trim() : performanceGroup;
	}

	public void setPerformanceGroup(String performanceGroup) {
		this.performanceGroup = performanceGroup != null ? performanceGroup.trim() : performanceGroup;
	}

	public String getPersonOfInterestType() {
		return personOfInterestType != null ? personOfInterestType.trim() : personOfInterestType;
	}

	public void setPersonOfInterestType(String personOfInterestType) {
		this.personOfInterestType = personOfInterestType != null ? personOfInterestType.trim() : personOfInterestType;
	}

	public Date getPositionEntryDate() {
		return this.positionEntryDate;
	}

	public void setPositionEntryDate(Date positionEntryDate) {
		this.positionEntryDate = positionEntryDate;
	}

	public String getPositionNumber() {
		return positionNumber != null ? positionNumber.trim() : positionNumber;
	}

	public void setPositionNumber(String positionNumber) {
		this.positionNumber = positionNumber != null ? positionNumber.trim() : positionNumber;
	}

	public String getPositionOverride() {
		return positionOverride != null ? positionOverride.trim() : positionOverride;
	}

	public void setPositionOverride(String positionOverride) {
		this.positionOverride = positionOverride != null ? positionOverride.trim() : positionOverride;
	}

	public String getPositionChangeRecord() {
		return positionChangeRecord != null ? positionChangeRecord.trim() : positionChangeRecord;
	}

	public void setPositionChangeRecord(String positionChangeRecord) {
		this.positionChangeRecord = positionChangeRecord != null ? positionChangeRecord.trim() : positionChangeRecord;
	}

	public String getProrateContractChangeAmount() {
		return prorateContractChangeAmount != null ? prorateContractChangeAmount.trim() : prorateContractChangeAmount;
	}

	public void setProrateContractChangeAmount(String prorateContractChangeAmount) {
		this.prorateContractChangeAmount = prorateContractChangeAmount != null ? prorateContractChangeAmount.trim() : prorateContractChangeAmount;
	}

	public String getRegion() {
		return region != null ? region.trim() : region;
	}

	public void setRegion(String region) {
		this.region = region != null ? region.trim() : region;
	}

	public String getRegularOrTemporary() {
		return regularOrTemporary != null ? regularOrTemporary.trim() : regularOrTemporary;
	}

	public void setRegularOrTemporary(String regularOrTemporary) {
		this.regularOrTemporary = regularOrTemporary != null ? regularOrTemporary.trim() : regularOrTemporary;
	}

	public String getReportsTo() {
		return reportsTo != null ? reportsTo.trim() : reportsTo;
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo != null ? reportsTo.trim() : reportsTo;
	}

	public String getSalaryAdministrationPlan() {
		return salaryAdministrationPlan != null ? salaryAdministrationPlan.trim() : salaryAdministrationPlan;
	}

	public void setSalaryAdministrationPlan(String salaryAdministrationPlan) {
		this.salaryAdministrationPlan = salaryAdministrationPlan != null ? salaryAdministrationPlan.trim() : salaryAdministrationPlan;
	}

	public String getSetIdDepartment() {
		return setIdDepartment != null ? setIdDepartment.trim() : setIdDepartment;
	}

	public void setSetIdDepartment(String setIdDepartment) {
		this.setIdDepartment = setIdDepartment != null ? setIdDepartment.trim() : setIdDepartment;
	}

	public String getSetIdJobCode() {
		return setIdJobCode != null ? setIdJobCode.trim() : setIdJobCode;
	}

	public void setSetidJobCode(String setIdJobCode) {
		this.setIdJobCode = setIdJobCode != null ? setIdJobCode.trim() : setIdJobCode;
	}

	public String getSetIdLaborAgreement() {
		return setIdLaborAgreement != null ? setIdLaborAgreement.trim() : setIdLaborAgreement;
	}

	public void setSetidLbrAgrmnt(String setIdLaborAgreement) {
		this.setIdLaborAgreement = setIdLaborAgreement != null ? setIdLaborAgreement.trim() : setIdLaborAgreement;
	}

	public String getSetIdLocation() {
		return setIdLocation != null ? setIdLocation.trim() : setIdLocation;
	}

	public void setSetIdLocation(String setIdLocation) {
		this.setIdLocation = setIdLocation != null ? setIdLocation.trim() : setIdLocation;
	}

	public String getSetIdSalary() {
		return setIdSalary != null ? setIdSalary.trim() : setIdSalary;
	}

	public void setSetIdSalary(String setIdSalary) {
		this.setIdSalary = setIdSalary != null ? setIdSalary.trim() : setIdSalary;
	}

	public String getSetIdSupervisorLevel() {
		return setIdSupervisorLevel != null ? setIdSupervisorLevel.trim() : setIdSupervisorLevel;
	}

	public void setSetIdSupervisorLevel(String setIdSupervisorLevel) {
		this.setIdSupervisorLevel = setIdSupervisorLevel != null ? setIdSupervisorLevel.trim() : setIdSupervisorLevel;
	}

	public String getShift() {
		return shift != null ? shift.trim() : shift;
	}

	public void setShift(String shift) {
		this.shift = shift != null ? shift.trim() : shift;
	}

	public BigDecimal getShiftFactor() {
		return this.shiftFactor;
	}

	public void setShiftFactor(BigDecimal shiftFactor) {
		this.shiftFactor = shiftFactor;
	}

	public BigDecimal getShiftRate() {
		return this.shiftRate;
	}

	public void setShiftRate(BigDecimal shiftRate) {
		this.shiftRate = shiftRate;
	}

	public String getSocSecRiskCode() {
		return socSecRiskCode != null ? socSecRiskCode.trim() : socSecRiskCode;
	}

	public void setSocSecRiskCode(String socSecRiskCode) {
		this.socSecRiskCode = socSecRiskCode != null ? socSecRiskCode.trim() : socSecRiskCode;
	}

	public String getSpokesmanCommitteeId() {
		return spokesmanCommitteeId != null ? spokesmanCommitteeId.trim() : spokesmanCommitteeId;
	}

	public void setSpokesmanCommitteeId(String spokesmanCommitteeId) {
		this.spokesmanCommitteeId = spokesmanCommitteeId != null ? spokesmanCommitteeId.trim() : spokesmanCommitteeId;
	}

	public BigDecimal getStandardHours() {
		return this.standardHours;
	}

	public void setStandardHours(BigDecimal standardHours) {
		this.standardHours = standardHours;
	}

	public String getStandardHoursFrequency() {
		return standardHoursFrequency != null ? standardHoursFrequency.trim() : standardHoursFrequency;
	}

	public void setStandardHoursFrequency(String standardHoursFrequency) {
		this.standardHoursFrequency = standardHoursFrequency != null ? standardHoursFrequency.trim() : standardHoursFrequency;
	}

	public BigInteger getFrequencyStep() {
		return this.frequencyStep;
	}

	public void setFrequencyStep(BigInteger frequencyStep) {
		this.frequencyStep = frequencyStep;
	}

	public Date getStepEntryDate() {
		return this.stepEntryDate;
	}

	public void setStepEntryDate(Date stepEntryDate) {
		this.stepEntryDate = stepEntryDate;
	}

	public String getSupervisorId() {
		return supervisorId != null ? supervisorId.trim() : supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId != null ? supervisorId.trim() : supervisorId;
	}

	public String getSupervisorLevelId() {
		return supervisorLevelId != null ? supervisorLevelId.trim() : supervisorLevelId;
	}

	public void setSupervisorLevelId(String supervisorLevelId) {
		this.supervisorLevelId = supervisorLevelId != null ? supervisorLevelId.trim() : supervisorLevelId;
	}

	public String getTariffArea() {
		return tariffArea != null ? tariffArea.trim() : tariffArea;
	}

	public void setTariffArea(String tariffArea) {
		this.tariffArea = tariffArea != null ? tariffArea.trim() : tariffArea;
	}

	public String getTariff() {
		return tariff != null ? tariff.trim() : tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff != null ? tariff.trim() : tariff;
	}

	public String getTaxLocationCode() {
		return taxLocationCode != null ? taxLocationCode.trim() : taxLocationCode;
	}

	public void setTaxLocationCode(String taxLocationCode) {
		this.taxLocationCode = taxLocationCode != null ? taxLocationCode.trim() : taxLocationCode;
	}

	public Date getTerminationDate() {
		return this.terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getUnionCode() {
		return unionCode != null ? unionCode.trim() : unionCode;
	}

	public void setUnionCode(String unionCode) {
		this.unionCode = unionCode != null ? unionCode.trim() : unionCode;
	}

	public BigDecimal getUnionFeeAmount() {
		return this.unionFeeAmount;
	}

	public void setUnionFeeAmount(BigDecimal unionFeeAmount) {
		this.unionFeeAmount = unionFeeAmount;
	}

	public Date getUnionFeeEndDate() {
		return this.unionFeeEndDate;
	}

	public void setUnionFeeEndDate(Date unionFeeEndDate) {
		this.unionFeeEndDate = unionFeeEndDate;
	}

	public Date getUnionFeeStartDate() {
		return this.unionFeeStartDate;
	}

	public void setUnionFeeStartDate(Date unionFeeStartDate) {
		this.unionFeeStartDate = unionFeeStartDate;
	}

	public String getUnionParticipation() {
		return unionParticipation != null ? unionParticipation.trim() : unionParticipation;
	}

	public void setUnionParticipation(String unionParticipation) {
		this.unionParticipation = unionParticipation != null ? unionParticipation.trim() : unionParticipation;
	}

	public String getUnionPosition() {
		return unionPosition != null ? unionPosition.trim() : unionPosition;
	}

	public void setUnionPosition(String unionPosition) {
		this.unionPosition = unionPosition != null ? unionPosition.trim() : unionPosition;
	}

	public Date getUnionSeniorityDate() {
		return this.unionSeniorityDate;
	}

	public void setUnionSeniorityDate(Date unionSeniorityDate) {
		this.unionSeniorityDate = unionSeniorityDate;
	}

	public String getValue1() {
		return value1 != null ? value1.trim() : value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1 != null ? value1.trim() : value1;
	}

	public String getValue2() {
		return value2 != null ? value2.trim() : value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2 != null ? value2.trim() : value2;
	}

	public String getValue3() {
		return value3 != null ? value3.trim() : value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3 != null ? value3.trim() : value3;
	}

	public String getValue4() {
		return value4 != null ? value4.trim() : value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4 != null ? value4.trim() : value4;
	}

	public String getValue5() {
		return value5 != null ? value5.trim() : value5;
	}

	public void setValue5(String value5) {
		this.value5 = value5 != null ? value5.trim() : value5;
	}

	public BigDecimal getWorkDayHours() {
		return this.workDayHours;
	}

	public void setWorkDayHours(BigDecimal workDayHours) {
		this.workDayHours = workDayHours;
	}

	public String getStopWageProgression() {
		return stopWageProgression != null ? stopWageProgression.trim() : stopWageProgression;
	}

	public void setStopWageProgression(String stopWageProgression) {
		this.stopWageProgression = stopWageProgression != null ? stopWageProgression.trim() : stopWageProgression;
	}

	public String getWorkCouncilFunction() {
		return workCouncilFunction != null ? workCouncilFunction.trim() : workCouncilFunction;
	}

	public void setWrksCnclFunction(String workCouncilFunction) {
		this.workCouncilFunction = workCouncilFunction != null ? workCouncilFunction.trim() : workCouncilFunction;
	}

	public String getWorkCouncilRole() {
		return workCouncilRole != null ? workCouncilRole.trim() : workCouncilRole;
	}

	public void setWorkCouncilRole(String workCouncilRole) {
		this.workCouncilRole = workCouncilRole != null ? workCouncilRole.trim() : workCouncilRole;
	}

	/**
	 * Finds PsJob records by employeeId, effectiveSequence, effectiveDate, and where employeeRecordNumber = 0
	 * @see HR02-Get-Job in ZHRI102A.SQC
	 * @see HR05-Get-Job in ZHRI105A.SQC
	 * @param employeeId
	 * @param effectiveDate
	 * @param effectiveSequence
	 * @return PsJob record
	 */
	public static PsJob findByEmployeeIdAndEffectiveDateAndEffectiveSequence(String employeeId, Date effectiveDate, BigInteger effectiveSequence) {
		logger.debug("findByEmployeeIdAndEffectiveDateAndEffectiveSequence() ***");
		//SELECT FROM PS_JOB P
		//WHERE P.EMPLID = $EmplId
		//AND TO_CHAR(P.EFFDT, 'YYYY-MM-DD') = $EffDt
		//AND P.EFFSEQ = #EffSeq
		//AND P.EMPL_RCD = 0
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsJob> resultList = em.createQuery(
	    			"SELECT p FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
	    				+ "AND p.effectiveDate = :effectiveDate "
	    				+ "AND p.effectiveSequence = :effectiveSequence "
	    				+ "AND p.employeeRecordNumber = 0 "
	    				, PsJob.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("effectiveDate", effectiveDate, TemporalType.DATE)
	    		    .setParameter("effectiveSequence", effectiveSequence)
	    		    .setHint(QueryHints.REFRESH, true)
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
	 * Gets the employees data from the job table that needs to be interfaced to the legacy system
	 * HR04-Get-Job-Data
	 * @param employeeId
	 * @param effectiveDate
	 * @return PsJob records
	 */
	public static PsJob hr04GetJobData(String employeeId, Date effectiveDate) {
		logger.debug("hr04GetJobData() ***");
		//SELECT FROM PS_JOB P
		//WHERE P.EMPLID = $EmplId
		//AND TO_CHAR(P.EFFDT, 'YYYY-MM-DD') = $PSEffDt
		//AND P.EFFSEQ =
				//(SELECT MAX(P2.EFFSEQ) FROM  PS_JOB P2
				//WHERE P2.EMPLID   = P.EmplId
				//AND P2.EMPL_RCD = P.Empl_Rcd
				//AND P2.EFFDT    = P.EffDt)
		//AND P.Empl_Rcd = 0
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsJob> resultList = em.createQuery(
	    		    "SELECT pj FROM PsJob pj "
	    		    		+ "WHERE UPPER(TRIM(pj.employeeId)) = UPPER(TRIM(:employeeId)) "
	    		    		+ "AND pj.effectiveDate <= :effectiveDate "
	    		    		+ "AND pj.effectiveSequence = "
	    		    				+ "(SELECT MAX(pj2.effectiveSequence) FROM PsJob pj2 "
	    		    				+ "WHERE UPPER(TRIM(pj2.employeeId)) = UPPER(TRIM(pj.employeeId)) "
	    		    				+ "AND pj2.employeeRecordNumber = pj.employeeRecordNumber "
	    		    				+ "AND pj2.effectiveDate = pj.effectiveDate) "
	    		    		+ "AND pj.employeeRecordNumber = 0"
	    			, PsJob.class)
	    		    .setParameter("employeeId", employeeId)
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
	 * This procedure will get REG_REGION from PS_JOB
	 * @see HR05-Get-Region
	 * @param employeeId
	 * @return region
	 */
	public static String findRegionByEmployeeId(String employeeId) {
		logger.debug("findRegulatoryRegionByEmployeeId() ***");
		//SELECT FROM PS_JOB P
		//WHERE P.EmplId = $PsEmplId
		//AND P.EffDt = 
			 //(SELECT MAX(EffDt) FROM PS_JOB P2
			 //WHERE P2.EmplId = P.EmplId
			 //AND P2.Empl_Rcd = P.Empl_Rcd)
		//AND P.EffSeq = 
			 //(SELECT MAX(EffSeq) FROM PS_JOB P3
			 //WHERE P3.EmplId = P.EmplId
			 //AND P3.Empl_Rcd = P.Empl_Rcd
			 //AND P3.EffDt = P.EffDt)                     
		//AND P.Empl_Rcd = 0                   
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    			"SELECT TRIM(UPPER(p.region)) FROM PsJob p "
	    				+ "WHERE TRIM(UPPER(p.employeeId)) = TRIM(UPPER(:employeeId)) "
	    					+ "AND p.effectiveDate = "
	    						+ "(SELECT MAX(p2.effectiveDate) FROM PsJob p2 "
	    						+ "WHERE TRIM(UPPER(p2.employeeId)) = TRIM(UPPER(p.employeeId)) "
	    							+ "AND p2.employeeRecordNumber = p.employeeRecordNumber) "
	    					+ "AND p.effectiveSequence = "
	    						+ "(SELECT MAX(p3.effectiveSequence) FROM PsJob p3 "
	    						+ "WHERE TRIM(UPPER(p3.employeeId)) = TRIM(UPPER(p.employeeId)) "
	    							+ "AND p3.employeeRecordNumber = p.employeeRecordNumber "
	    							+ "AND p3.effectiveDate = p.effectiveDate) "
	    					+ "AND p.employeeRecordNumber = 0 "
	    			, String.class)
	    		    .setParameter("employeeId", employeeId)
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
	 * 
	 * @param employeeId
	 * @param employeeRecordNumber
	 * @param effectiveDate
	 * @return max effective sequence
	 */
	public static BigInteger findMaxEffectiveSequenceByEmployeeIdAndemployeeRecordNumberAndEffectiveDate(String employeeId, BigInteger employeeRecordNumber, Date effectiveDate) {
		logger.debug("findMaxEffectiveSequenceByEmployeeIdAndemployeeRecordNumberAndEffectiveDate() ***");
		//SELECT MAX(EFFSEQ) FROM PS_JOB P
		//WHERE P.EMPLID = $EmplId
		//AND P.EMPL_RCD = $Empl_Rcd
		//AND P.EFFDT = $EffDt
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<BigInteger> resultList = em.createQuery(
	    			"SELECT MAX(p.effectiveSequence) FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
	    				+ "AND p.employeeRecordNumber = :employeeRecordNumber "
	    				+ "AND p.effectiveDate <= :effectiveDate ",
	    				BigInteger.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("employeeRecordNumber", employeeRecordNumber)
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
	 * @param employeeRecordNumber
	 * @return max effective date
	 */
	public static Date findMaxEffectiveDateByEmployeeIdAndemployeeRecordNumber(String employeeId, BigInteger employeeRecordNumber) {
		logger.debug("findMaxEffectiveDateByEmployeeIdAndemployeeRecordNumber() ***");
		Date asOfToday = ErdUtils.asOfToday();
		//SELECT MAX(EFFDT) FROM PS_JOB P
		//WHERE P.EMPLID = $EmplId
		//AND P.EMPL_RCD = $Empl_Rcd
		//AND P.EFFDT <= $AsOfToday
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<Date> resultList = em.createQuery(
	    			"SELECT MAX(p.effectiveDate) FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
	    				+ "AND p.employeeRecordNumber = :employeeRecordNumber "
	    				+ "AND p.effectiveDate <= :asOfToday ",
	    				Date.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("employeeRecordNumber", employeeRecordNumber)
	    		    .setParameter("asOfToday", asOfToday, TemporalType.DATE)
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
	 * @param employeeId
	 * @param effectiveDate
	 * @return PsJob records
	 */
	public static List<PsJob> findByEmployeeIdAndEffectiveDate(String employeeId, Date effectiveDate) {
		logger.debug("findByEmployeeIdAndEffectiveDate() ***");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsJob> resultList = em.createQuery(
	    			"SELECT p FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
	    				+ "AND p.effectiveDate <= :effectiveDate ",
	    				PsJob.class)
	    		    .setParameter("employeeId", employeeId)
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
	 * 
	 * @param employeeId
	 * @return employeeRecordNumber
	 */
	public static BigInteger findEmployeeRecordNumberByEmployeeId(String employeeId) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<BigInteger> resultList = em.createQuery(
	    			"SELECT p.employeeRecordNumber FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
	    				+ "AND p.employeeRecordNumber = :employeeRecordNumber "
	    				+ "AND p.effectiveDate <= CURRENT_DATE ",
	    				BigInteger.class)
	    		    .setParameter("employeeId", employeeId)
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
	 * Checks to see if the employee is a contractor       
	 * @see Check-If-Contractor in ZHRI100A.SQR
	 * @param employeeId
	 * @return true if employee is a contractor
	 */
	public static Boolean isContractor(String employeeId) {
		logger.debug("isContractor() ***");
		//SELECT FROM PS_JOB P
		//WHERE P.EmplId = $PsEmplId
		//AND P.Empl_Class = 'R'
		//AND P.EffDt = 
		//			(SELECT MAX(EffDt) FROM PS_JOB P2 WHERE P2.EmplId = P.EmplId
		//			AND P2.Empl_Rcd = P.Empl_Rcd
		//			AND P2.EffDt <= $AsOfToday)
		//AND P.EffSeq = 
		//			(SELECT MAX(EffSeq) FROM PS_JOB P3 WHERE P3.EmplId = P.EmplId
		//			AND P3.Empl_Rcd = P.Empl_Rcd
		//			AND P3.EffDt = P.EffDt)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<Date> resultList = em.createQuery(
	    			"SELECT p FROM PsJob p "
		    			+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
		    			+ "AND UPPER(TRIM(p.employeeClass)) = 'R' "
		    			+ "AND p.effectiveDate = "
				    			+ "(SELECT MAX(p2.effectiveDate) FROM PsJob p2 "
				    			+ "WHERE UPPER(TRIM(p2.employeeId)) = UPPER(TRIM(p.employeeId)) "
				    			+ "AND p2.employeeRecordNumber = p.employeeRecordNumber "
				    			+ "AND p2.effectiveDate <= :asOfToday) "
		    			+ "AND p.effectiveSequence = "
				    			+ "(SELECT MAX(p3.effectiveSequence) FROM PsJob p3 "
				    			+ "WHERE UPPER(TRIM(p3.employeeId)) = UPPER(TRIM(p.employeeId)) "
				    			+ "AND p3.employeeRecordNumber = p.employeeRecordNumber "
				    			+ "AND p3.effectiveDate = p.effectiveDate) ",
	    				Date.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("asOfToday", ErdUtils.asOfToday(), TemporalType.DATE)
	    		    .getResultList();
	    	return (resultList != null && resultList.size() > 0);
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
	 * Checks to see if 102A process has JOB row
	 * @see Check-If-Correct102A in ZHRI100A.SQR
	 * @param psEmplId
	 * @param psEffectiveDate
	 * @param processName
	 * @return true if corresponding PsJob record found
	 */
	public static Boolean correspondingJobRecordExists(String employeeId, Date effectiveDate, String processName) {
		logger.debug("correspondingJobRecordExists() ***");
		//add a day to current effective date
		Date effectiveDatePlusOne = ErdUtils.addDays(effectiveDate, 1);
		//SELECT FROM PS_JOB P
		//WHERE P.EMPLID = $PsEmplId
			//AND TO_CHAR(P.EFFDT, 'YYYY-MM-DD') = $effectiveDatePlusOne
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsJob> resultList = em.createQuery(
	    			"SELECT p FROM PsJob p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
	    				+ "AND p.effectiveDate <= :effectiveDatePlusOne ", PsJob.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("effectiveDatePlusOne", effectiveDatePlusOne, TemporalType.DATE)
	    		    .getResultList();
	    	return (resultList != null && resultList.size() > 0);
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
	 * @see HR01-Get-Job-Data
	 * @param employeeId
	 * @param effectiveDate
	 * @return PsJob record
	 */
	public static PsJob findJobData(String employeeId, Date effectiveDate) {
		logger.debug("findJobData() ***");
		//SELECT CJ.REG_REGION FROM PS_JOB CJ
		//WHERE CJ.EMPLID = $EmplId
		//AND CJ.EMPL_RCD = 0
		//AND CJ.EFFDT = 
				//(SELECT MAX(EFFDT) FROM PS_JOB CJ2
				//WHERE CJ2.EMPLID = CJ.EMPLID
				//AND CJ2.EMPL_RCD = CJ.EMPL_RCD
				//AND (CJ2.ACTION IN ('HIR','REH') OR (CJ2.ACTION = 'DTA' AND CJ2.ACTION_REASON = 'CNV') OR (CJ2.ACTION = 'TER' AND CJ2.ACTION_REASON = 'CNV'))     
				//AND TO_CHAR(CJ2.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
		//AND CJ.EFFSEQ = 
				//(SELECT MAX(EFFSEQ) FROM PS_JOB CJ3
				//WHERE CJ3.EMPLID = CJ.EMPLID
				//AND CJ3.EMPL_RCD = CJ.EMPL_RCD
				//AND (CJ3.ACTION IN ('HIR','REH') OR (CJ3.ACTION = 'DTA' AND CJ3.ACTION_REASON = 'CNV') OR (CJ3.ACTION = 'TER' AND CJ3.ACTION_REASON = 'CNV'))    
				//AND CJ3.EFFDT = CJ.EFFDT)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsJob> resultList = em.createQuery(
					"SELECT p FROM PsJob p "
					+ "WHERE TRIM(UPPER(p.employeeId)) = UPPER(TRIM(:employeeId)) "
					+ "AND p.employeeRecordNumber = 0 "
					+ "AND p.effectiveDate = "
							+ "(SELECT MAX(p2.effectiveDate) FROM PsJob p2 "
							+ "WHERE UPPER(TRIM(p2.employeeId)) = UPPER(TRIM(p.employeeId)) "
							+ "AND p2.employeeRecordNumber = p.employeeRecordNumber "
							+ "AND (p2.action IN ('HIR','REH') "
									+ "OR (p2.action = 'DTA' AND p2.actionReason = 'CNV') "
									+ "OR (p2.action = 'TER' AND p2.actionReason = 'CNV')) "
							+ "AND p2.effectiveDate <= :effectiveDate) "
					+ "AND p.effectiveSequence = "
							+ "(SELECT MAX(p3.effectiveSequence) FROM PsJob p3 "
							+ "WHERE UPPER(TRIM(p3.employeeId)) = UPPER(TRIM(p.employeeId)) "
							+ "AND p3.employeeRecordNumber = p.employeeRecordNumber "
							+ "AND (p3.action IN ('HIR','REH') "
									+ "OR (p3.action = 'DTA' AND p3.actionReason = 'CNV') "
									+ "OR (p3.action = 'TER' AND p3.actionReason = 'CNV')) "
							+ "AND p3.effectiveDate = p.effectiveDate) "
	    			, PsJob.class)
	    		    .setParameter("employeeId", employeeId)
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
	
}
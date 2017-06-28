package erd.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProcessParameters {

	public class CommonParameters {
		private String processName;
		private String completionStatus;
		private String auditOperatorId; //$PSAuditOperId
		private String operatorId; //$PSOperId
		private String employeeId; //PSEmpl
//		private BigDecimal indexNumber;
		private Date effectiveDate;
		
		private Boolean poiFlag; //$PoiFlag
//		private Boolean runFlag; //#run_flag
		private Boolean criticalFlag = false; //$WrkCriticalFlag
		
		//from ZHRI100A.Call-Programs  //probably not used
		private String actionCode;
		private String legacyOperatorId;
		
		private String errorProgramParameter;
		private String errorMessageParameter;
		private String errorDateParameter;
		private String errorTimeParameter;
		private BigDecimal effectiveSequence;
		
		public String getAuditOperatorId() {
			return auditOperatorId != null ? auditOperatorId.trim() : auditOperatorId;
		}
		public void setAuditOperatorId(String auditOperatorId) {
			this.auditOperatorId = auditOperatorId != null ? auditOperatorId.trim() : auditOperatorId;
		}
		public String getOperatorId() {
			return operatorId != null ? operatorId.trim() : operatorId;
		}
		public void setOperatorId(String operatorId) {
			this.operatorId = operatorId != null ? operatorId.trim() : operatorId;
		}
		public String getEmployeeId() {
			return employeeId != null ? employeeId.trim() : employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId != null ? employeeId.trim() : employeeId;
		}
//		public BigDecimal getIndexNumber() {
//			return indexNumber;
//		}
//		public void setIndexNumber(BigDecimal indexNumber) {
//			this.indexNumber = indexNumber;
//		}
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		public void setEffectiveDate(Date effectiveDate) {
			this.effectiveDate = effectiveDate;
		}
		public Boolean getPoiFlag() {
			return poiFlag;
		}
		public void setPoiFlag(Boolean poiFlag) {
			this.poiFlag = poiFlag;
		}
		public Boolean getCriticalFlag() {
			return criticalFlag;
		}
		public void setCriticalFlag(Boolean criticalFlag) {
			this.criticalFlag = criticalFlag;
		}
		public String getActionCode() {
			return actionCode != null ? actionCode.trim() : actionCode;
		}
		public void setActionCode(String actionCode) {
			this.actionCode = actionCode != null ? actionCode.trim() : actionCode;
		}
		public String getLegacyOperatorId() {
			return legacyOperatorId != null ? legacyOperatorId.trim() : legacyOperatorId;
		}
		public void setLegacyOperatorId(String legacyOperatorId) {
			this.legacyOperatorId = legacyOperatorId != null ? legacyOperatorId.trim() : legacyOperatorId;
		}
		public String getErrorProgramParameter() {
			return errorProgramParameter != null ? errorProgramParameter.trim() : errorProgramParameter;
		}
		public void setErrorProgramParameter(String errorProgramParameter) {
			this.errorProgramParameter = errorProgramParameter != null ? errorProgramParameter.trim() : errorProgramParameter;
		}
		public String getErrorMessageParameter() {
			return errorMessageParameter;
		}
		public void setErrorMessageParameter(String errorMessageParameter) {
			this.errorMessageParameter = errorMessageParameter;
		}
		public String getProcessName() {
			return processName != null ? processName.trim() : processName;
		}
		public void setProcessName(String processName) {
			this.processName = processName != null ? processName.trim() : processName;
		}
		public String getCompletionStatus() {
			return completionStatus != null ? completionStatus.trim() : completionStatus;
		}
		public void setCompletionStatus(String completionStatus) {
			this.completionStatus = completionStatus != null ? completionStatus.trim() : completionStatus;
		}
		public String getErrorDateParameter() {
			return errorDateParameter;
		}
		public void setErrorDateParameter(String errorDateParameter) {
			this.errorDateParameter = errorDateParameter;
		}
		public String getErrorTimeParameter() {
			return errorTimeParameter;
		}
		public void setErrorTimeParameter(String errorTimeParameter) {
			this.errorTimeParameter = errorTimeParameter;
		}
		public void setEffectiveSequence(BigDecimal effectiveSequence) {
			this.effectiveSequence = effectiveSequence;
		}
		public BigDecimal getEffectiveSequence() {
			return this.effectiveSequence;
		}
		
		@Override
		public String toString() {
			return "\nZhri100aFields:\n"
					+ "auditOperatorId: " + auditOperatorId + "\n" //$PSAuditOperId
					+ "operatorId: " + operatorId + "\n" //$PSOperId
					+ "employeeId: " + employeeId + "\n" //PSEmpl
					+ "effectiveSequence: " + effectiveSequence + "\n"
					+ "effectiveDate: " + effectiveDate + "\n"
					+ "poiFlag: " + poiFlag + "\n"
//					+ "runFlag: " + runFlag + "\n" //#run_flag
					+ "criticalFlag: " + criticalFlag + "\n" //$WrkCriticalFlag
					+ "actionCode: " + actionCode + "\n"
					+ "legacyOperatorId: " + legacyOperatorId + "\n"
					+ "errorProgramParameter: " + errorProgramParameter + "\n"
					+ "errorMessageParameter: " + errorMessageParameter + "\n";
		}
	}
	
	public class TerminationProcessParameters {
		private String employeeId = ""; //$PSEmpl
		private String terminationMonth = ""; //$PSTermMnth
		private String terminationDay = ""; //$PsTermDay
		private String terminationYear = ""; //$PSTermYr
		private String rehireDay = ""; //$PSReHireMnth
		private String rehireMonth = ""; //$PSReHireDay
		private String rehireYear = ""; //$PSReHireYr
		private String voluntaryOrInvoluntary = ""; //$PSVolInvol
		private String terminationCode = ""; //$PSTermCode
		private String auditOperatorId = ""; //$PSAuditOperId
		private String terminationReason = ""; //$PSTermReason is at most 30 positions long, but HRZ102A receives it with a length of 35 positions; needs to be padded to 35
		private Date terminationDate;
		private Date rehireDate;

		public TerminationProcessParameters() {
		}
	
		public String getVoluntaryOrInvoluntary() {
			return voluntaryOrInvoluntary != null ? voluntaryOrInvoluntary.trim() : voluntaryOrInvoluntary;
		}
		public void setVoluntaryOrInvoluntary(String voluntaryOrInvoluntary) {
			this.voluntaryOrInvoluntary = voluntaryOrInvoluntary != null ? voluntaryOrInvoluntary.trim() : voluntaryOrInvoluntary;
		}
		public String getTerminationCode() {
			return terminationCode != null ? terminationCode.trim() : terminationCode;
		}
		public void setTerminationCode(String terminationCode) {
			this.terminationCode = terminationCode != null ? terminationCode.trim() : terminationCode;
		}
		public String getTerminationReason() {
			return terminationReason != null ? terminationReason.trim() : terminationReason;
		}
		public void setTerminationReason(String terminationReason) {
			this.terminationReason = terminationReason != null ? terminationReason.trim() : terminationReason;
		}
		public String getTerminationMonth() {
			return terminationMonth != null ? terminationMonth.trim() : terminationMonth;
		}
		public void setTerminationMonth(String terminationMonth) {
			this.terminationMonth = terminationMonth != null ? terminationMonth.trim() : terminationMonth;
		}
		public String getTerminationDay() {
			return terminationDay != null ? terminationDay.trim() : terminationDay;
		}
		public void setTerminationDay(String terminationDay) {
			this.terminationDay = terminationDay != null ? terminationDay.trim() : terminationDay;
		}
		public String getTerminationYear() {
			return terminationYear != null ? terminationYear.trim() : terminationYear;
		}
		public void setTerminationYear(String terminationYear) {
			this.terminationYear = terminationYear != null ? terminationYear.trim() : terminationYear;
		}
		public String getRehireMonth() {
			return rehireMonth != null ? rehireMonth.trim() : rehireMonth;
		}
		public void setRehireMonth(String rehireMonth) {
			this.rehireMonth = rehireMonth != null ? rehireMonth.trim() : rehireMonth;
		}
		public String getRehireDay() {
			return rehireDay != null ? rehireDay.trim() : rehireDay;
		}
		public void setRehireDay(String rehireDay) {
			this.rehireDay = rehireDay != null ? rehireDay.trim() : rehireDay;
		}
		public String getRehireYear() {
			return rehireYear != null ? rehireYear.trim() : rehireYear;
		}
		public void setRehireYear(String rehireYear) {
			this.rehireYear = rehireYear != null ? rehireYear.trim() : rehireYear;
		}
		public Date getTerminationDate() {
			return terminationDate;
		}
		public void setTerminationDate(Date terminationDate) {
			this.terminationDate = terminationDate;
		}
		public Date getRehireDate() {
			return rehireDate;
		}
		public void setRehireDate(Date rehireDate) {
			this.rehireDate = rehireDate;
		}
		public String getAuditOperatorId() {
			return auditOperatorId;
		}
		public void setAuditOperatorId(String auditOperatorId) {
			this.auditOperatorId = auditOperatorId != null ? auditOperatorId.trim() : auditOperatorId;
		}
		public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId != null ? employeeId.trim() : employeeId;
		}
	}
	
	
	public class DateChangeProcessParameters {
		private String legacyEmployeeId = ""; //$LegacyEmplid
		private String legacyUserEmployeeId = ""; //$LegacyUserEmplId
		private String legacyHireDay = ""; //$LegHireDtDay
		private String legacyHireMonth = ""; //$LegHireDtMonth
		private String legacyHireYear = ""; //$LegHireDtYear
		private String legacyTerminationMonth = ""; //$LegTermDtMonth
		private String legacyTerminationDay = ""; //$LegTermDtDay
		private String legacyTerminationYear = ""; //$LegTermDtYear
		private String legacyLastReviewMonth = ""; //$LegLstRevDtMonth
		private String legacyLastReviewDay = ""; //$LegLstRevDtDay
		private String legacyLastReviewYear = ""; //$LegLstRevDtYear
		private String legacyNextReviewMonth = ""; //$LegNxtRevDtMonth
		private String legacyNextReviewDay = ""; //$LegNxtRevDtDay
		private String legacyNextReviewYear = ""; //$LegNxtRevDtYear
		private String legacyNegDrugTestMonth = ""; //$LegNegDrugTstMonth
		private String legacyNegDrugTestDay = ""; //$LegNegDrugTstDay
		private String legacyNegDrugTestYear = ""; //$LegNegDrugTstYear
		private String legacyPhysTestMonth = ""; //$LegPhysTstMonth
		private String legacyPhysTestDay = ""; //$LegPhysTstDay
		private String legacyPhysTestYear = ""; //$LegPhysTstYear
		private String legacyContractMonth = ""; //$LegContractDtMonth
		private String legacyContractDay = ""; //$LegContractDtDay
		private String legacyContractYear = ""; //$LegContractDtYear
		private String legacyCompanySeniorityMonth = ""; //$LegCompanySeniorityMonth
		private String legacyCompanySeniorityDay = ""; //$LegCompanySeniorityDay
		private String legacyCompanySeniorityYear = ""; //$LegCompanySeniorityYear
		public String getLegacyEmployeeId() {
			return legacyEmployeeId;
		}
		public void setLegacyEmployeeId(String legacyEmployeeId) {
			this.legacyEmployeeId = legacyEmployeeId;
		}
		public String getLegacyUserEmployeeId() {
			return legacyUserEmployeeId;
		}
		public void setLegacyUserEmployeeId(String legacyUserEmployeeId) {
			this.legacyUserEmployeeId = legacyUserEmployeeId;
		}
		public String getLegacyHireDay() {
			return legacyHireDay;
		}
		public void setLegacyHireDay(String legacyHireDay) {
			this.legacyHireDay = legacyHireDay;
		}
		public String getLegacyHireMonth() {
			return legacyHireMonth;
		}
		public void setLegacyHireMonth(String legacyHireMonth) {
			this.legacyHireMonth = legacyHireMonth;
		}
		public String getLegacyHireYear() {
			return legacyHireYear;
		}
		public void setLegacyHireYear(String legacyHireYear) {
			this.legacyHireYear = legacyHireYear;
		}
		public String getLegacyTerminationMonth() {
			return legacyTerminationMonth;
		}
		public void setLegacyTerminationMonth(String legacyTerminationMonth) {
			this.legacyTerminationMonth = legacyTerminationMonth;
		}
		public String getLegacyTerminationDay() {
			return legacyTerminationDay;
		}
		public void setLegacyTerminationDay(String legacyTerminationDay) {
			this.legacyTerminationDay = legacyTerminationDay;
		}
		public String getLegacyTerminationYear() {
			return legacyTerminationYear;
		}
		public void setLegacyTerminationYear(String legacyTerminationYear) {
			this.legacyTerminationYear = legacyTerminationYear;
		}
		public String getLegacyLastReviewMonth() {
			return legacyLastReviewMonth;
		}
		public void setLegacyLastReviewMonth(String legacyLastReviewMonth) {
			this.legacyLastReviewMonth = legacyLastReviewMonth;
		}
		public String getLegacyLastReviewDay() {
			return legacyLastReviewDay;
		}
		public void setLegacyLastReviewDay(String legacyLastReviewDay) {
			this.legacyLastReviewDay = legacyLastReviewDay;
		}
		public String getLegacyLastReviewYear() {
			return legacyLastReviewYear;
		}
		public void setLegacyLastReviewYear(String legacyLastReviewYear) {
			this.legacyLastReviewYear = legacyLastReviewYear;
		}
		public String getLegacyNextReviewMonth() {
			return legacyNextReviewMonth;
		}
		public void setLegacyNextReviewMonth(String legacyNextReviewMonth) {
			this.legacyNextReviewMonth = legacyNextReviewMonth;
		}
		public String getLegacyNextReviewDay() {
			return legacyNextReviewDay;
		}
		public void setLegacyNextReviewDay(String legacyNextReviewDay) {
			this.legacyNextReviewDay = legacyNextReviewDay;
		}
		public String getLegacyNextReviewYear() {
			return legacyNextReviewYear;
		}
		public void setLegacyNextReviewYear(String legacyNextReviewYear) {
			this.legacyNextReviewYear = legacyNextReviewYear;
		}
		public String getLegacyNegDrugTestMonth() {
			return legacyNegDrugTestMonth;
		}
		public void setLegacyNegDrugTestMonth(String legacyNegDrugTestMonth) {
			this.legacyNegDrugTestMonth = legacyNegDrugTestMonth;
		}
		public String getLegacyNegDrugTestDay() {
			return legacyNegDrugTestDay;
		}
		public void setLegacyNegDrugTestDay(String legacyNegDrugTestDay) {
			this.legacyNegDrugTestDay = legacyNegDrugTestDay;
		}
		public String getLegacyNegDrugTestYear() {
			return legacyNegDrugTestYear;
		}
		public void setLegacyNegDrugTestYear(String legacyNegDrugTestYear) {
			this.legacyNegDrugTestYear = legacyNegDrugTestYear;
		}
		public String getLegacyPhysTestMonth() {
			return legacyPhysTestMonth;
		}
		public void setLegacyPhysTestMonth(String legacyPhysTestMonth) {
			this.legacyPhysTestMonth = legacyPhysTestMonth;
		}
		public String getLegacyPhysTestDay() {
			return legacyPhysTestDay;
		}
		public void setLegacyPhysTestDay(String legacyPhysTestDay) {
			this.legacyPhysTestDay = legacyPhysTestDay;
		}
		public String getLegacyPhysTestYear() {
			return legacyPhysTestYear;
		}
		public void setLegacyPhysTestYear(String legacyPhysTestYear) {
			this.legacyPhysTestYear = legacyPhysTestYear;
		}
		public String getLegacyContractMonth() {
			return legacyContractMonth;
		}
		public void setLegacyContractMonth(String legacyContractMonth) {
			this.legacyContractMonth = legacyContractMonth;
		}
		public String getLegacyContractDay() {
			return legacyContractDay;
		}
		public void setLegacyContractDay(String legacyContractDay) {
			this.legacyContractDay = legacyContractDay;
		}
		public String getLegacyContractYear() {
			return legacyContractYear;
		}
		public void setLegacyContractYear(String legacyContractYear) {
			this.legacyContractYear = legacyContractYear;
		}
		public String getLegacyCompanySeniorityMonth() {
			return legacyCompanySeniorityMonth;
		}
		public void setLegacyCompanySeniorityMonth(String legacyCompanySeniorityMonth) {
			this.legacyCompanySeniorityMonth = legacyCompanySeniorityMonth;
		}
		public String getLegacyCompanySeniorityDay() {
			return legacyCompanySeniorityDay;
		}
		public void setLegacyCompanySeniorityDay(String legacyCompanySeniorityDay) {
			this.legacyCompanySeniorityDay = legacyCompanySeniorityDay;
		}
		public String getLegacyCompanySeniorityYear() {
			return legacyCompanySeniorityYear;
		}
		public void setLegacyCompanySeniorityYear(String legacyCompanySeniorityYear) {
			this.legacyCompanySeniorityYear = legacyCompanySeniorityYear;
		}
	}
	
	public class DemographicChangeParameters {
		private String employeeId = ""; //$PSEmpl
		private String employeeGroup = ""; //$PSGroup
		private String employeeRegion = ""; //$PSReg
		private String employeeBranch = ""; //$PSbranch
		private String emp = ""; //$PSEMP
		private String nationalId = ""; //$PSNational_Id
		private String nationalIdCountry = ""; //$PS_NID_COUNTRY5
		private String healthStat = ""; //$PSHealth_Stat
		private String healthStatDescription = ""; //$PSHealth_Stat_Desc
		private String vehicleReport = ""; //$PSVehRpt
		private String name = ""; //$PSName
		private String namePrefix = ""; //$PSName_Prefix
		private String nickname = ""; //$PSNickname
		private String homeAddress = ""; //$PSAddress
		private String homeCity = ""; //$PSCity
		private String homeState = ""; //$PSState
		private String homePostalCode = ""; //$PSZip
		private String homeCountry = ""; //$PSCountry
		private String homePhone = ""; //$PSPhone
		private String businessPhone = ""; //$PSBusiness_Phone
		private String gender = ""; //$PSGender
		private String maritalStatus = ""; //$PSMarITAL_StatUS
		private String ethnicGroup = ""; //$PSEthnic_Group
		private String changeDate = ""; //$PSChg_Dt
		private String birthDate = ""; //$PSBirthDate
		private String partTimeEffectiveDate = ""; //$PSStat_date
		private String startDate = ""; //$PSStart_date  //unused??
		private String driversLicenseNumber = ""; //$PSDriver_Lic
		private String driversLicenseState = ""; //$PSDlstate
		private String emergencyContactName = ""; //$PSContact_Name
		private String emergencyContactPhone = ""; //$PSEmer_Phn
		private String emergencyContactRelation = ""; //$PSRelation
		private String employeeSpouse = ""; //$PSEmpl_Spouse
		private String referralSource = ""; //$PSReferral_Source
		private String referralSourceId = ""; //$PSSpecific_Refer_Src
		private String recruiterGroup = ""; //$PSRecruit_Gp
		private String recruiterId = ""; //$PSRecruiter_Id
		private String collegeName = ""; //$PSCollege
		private String collegeGradYear = ""; //$PSGradYr
		private String collegeMajor = ""; //$PSMajor
	}

	public class GroupTransferParameters {
		private String operatorId = ""; //$LegUserEmplid
		private String employeeId = ""; //$LegEmplid
		private String employeeGroup = ""; //$LegGroup
		private String employeeBranch = ""; //$LegBranch
		private String nationalId = ""; //$LegNid
		private String nationalIdCountry = ""; //$LegCountryCode
		private Date effectiveDate; //$LegEffdt
		public String getOperatorId() {
			return operatorId;
		}
		public void setOperatorId(String operatorId) {
			this.operatorId = operatorId;
		}
		public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
		public String getEmployeeGroup() {
			return employeeGroup;
		}
		public void setEmployeeGroup(String employeeGroup) {
			this.employeeGroup = employeeGroup;
		}
		public String getEmployeeBranch() {
			return employeeBranch;
		}
		public void setEmployeeBranch(String employeeBranch) {
			this.employeeBranch = employeeBranch;
		}
		public String getNationalId() {
			return nationalId;
		}
		public void setNationalId(String nationalId) {
			this.nationalId = nationalId;
		}
		public String getNationalIdCountry() {
			return nationalIdCountry;
		}
		public void setNationalIdCountry(String nationalIdCountry) {
			this.nationalIdCountry = nationalIdCountry;
		}
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		public void setEffectiveDate(Date effectiveDate) {
			this.effectiveDate = effectiveDate;
		}
	}
	
	public class NewHireParameters {
	}
	
	public class JobProfileParameters {
	}

}

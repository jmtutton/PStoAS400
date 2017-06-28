package erd.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProcessParameters {

	public class CommonParameters {
		private String processName;
		private String completionStatus;
//		private String auditOperatorId; //$PSAuditOperId
		private String operatorId; //$PSOperId
		private String employeeId; //PSEmpl
//		private BigDecimal indexNumber;
		private Date effectiveDate;
		
		private Boolean poiFlag; //$PoiFlag
//		private Boolean runFlag; //#run_flag
		private Boolean criticalFlag = false; //$WrkCriticalFlag
		
		//from ZHRI100A.Call-Programs  //probably not used
//		private String actionCode; //$ADAction_Code
//		private String legacyOperatorId; //$ADLegOprid
		
		private String errorProgramParameter;
		private String errorMessageParameter;
		private String errorDateParameter;
		private String errorTimeParameter;
		private BigDecimal effectiveSequence;
		
//		public String getAuditOperatorId() {
//			return auditOperatorId != null ? auditOperatorId.trim() : auditOperatorId;
//		}
//		public void setAuditOperatorId(String auditOperatorId) {
//			this.auditOperatorId = auditOperatorId != null ? auditOperatorId.trim() : auditOperatorId;
//		}
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
//		public String getActionCode() {
//			return actionCode != null ? actionCode.trim() : actionCode;
//		}
//		public void setActionCode(String actionCode) {
//			this.actionCode = actionCode != null ? actionCode.trim() : actionCode;
//		}
//		public String getLegacyOperatorId() {
//			return legacyOperatorId != null ? legacyOperatorId.trim() : legacyOperatorId;
//		}
//		public void setLegacyOperatorId(String legacyOperatorId) {
//			this.legacyOperatorId = legacyOperatorId != null ? legacyOperatorId.trim() : legacyOperatorId;
//		}
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
			return "\nCommonParameters values:\n"
//					+ "auditOperatorId: " + auditOperatorId + "\n" //$PSAuditOperId
					+ "operatorId: " + operatorId + "\n" //$PSOperId
					+ "employeeId: " + employeeId + "\n" //PSEmpl
					+ "effectiveSequence: " + effectiveSequence + "\n"
					+ "effectiveDate: " + effectiveDate + "\n"
					+ "poiFlag: " + poiFlag + "\n"
//					+ "runFlag: " + runFlag + "\n" //#run_flag
					+ "criticalFlag: " + criticalFlag + "\n" //$WrkCriticalFlag
//					+ "actionCode: " + actionCode + "\n"
//					+ "legacyOperatorId: " + legacyOperatorId + "\n"
					+ "errorProgramParameter: " + errorProgramParameter + "\n"
					+ "errorMessageParameter: " + errorMessageParameter + "\n";
		}
	}
	
	public class TerminationProcessParameters {
		private String employeeId = ""; //$PSEmpl
		private String terminationMonth = ""; //$PSTermMnth
		private String terminationDay = ""; //$PsTermDay
		private String terminationYear = ""; //$PSTermYr
		private String rehireMonth = ""; //$PSRehireMnth
		private String rehireDay = ""; //$PSRehireDay
		private String rehireYear = ""; //$PSRehireYr
		private String voluntaryOrInvoluntary = ""; //$PSVolInvol
		private String terminationCode = ""; //$PSTermCode
		private String operatorId = ""; //$PSAuditOperId
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
		public String getOperatorId() {
			return operatorId;
		}
		public void setOperatorId(String operatorId) {
			this.operatorId = operatorId != null ? operatorId.trim() : operatorId;
		}
		public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId != null ? employeeId.trim() : employeeId;
		}
		@Override
		public String toString() {
			return "\nZhri100aFields:\n"
					+ "employeeId: " + employeeId + "\n" //$PSEmpl
					+ "terminationMonth: " + terminationMonth + "\n" //$PSTermMnth
					+ "terminationDay: " + terminationDay + "\n" //$PsTermDay
					+ "terminationYear: " + terminationYear + "\n" //$PSTermYr
					+ "rehireMonth: " + rehireMonth + "\n" //$PSRehireMnth
					+ "rehireDay: " + rehireDay + "\n" //$PSRehireDay
					+ "rehireYear: " + rehireYear + "\n" //$PSRehireYr
					+ "voluntaryOrInvoluntary: " + voluntaryOrInvoluntary + "\n" //$PSVolInvol
					+ "terminationCode: " + terminationCode + "\n" //$PSTermCode
					+ "operatorId: " + operatorId + "\n" //$PSAuditOperId
					+ "terminationReason: " + terminationReason + "\n"; //$PSTermReason
		}
	}
	
	public class DateChangeProcessParameters {
		private String employeeId = ""; //$LegacyEmplid
		private String userEmployeeId = ""; //$LegacyUserEmplId
		private String hireDay = ""; //$LegHireDtDay
		private String hireMonth = ""; //$LegHireDtMonth
		private String hireYear = ""; //$LegHireDtYear
		private String terminationMonth = ""; //$LegTermDtMonth
		private String terminationDay = ""; //$LegTermDtDay
		private String terminationYear = ""; //$LegTermDtYear
		private String lastReviewMonth = ""; //$LegLstRevDtMonth
		private String lastReviewDay = ""; //$LegLstRevDtDay
		private String lastReviewYear = ""; //$LegLstRevDtYear
		private String nextReviewMonth = ""; //$LegNxtRevDtMonth
		private String nextReviewDay = ""; //$LegNxtRevDtDay
		private String nextReviewYear = ""; //$LegNxtRevDtYear
		private String negDrugTestMonth = ""; //$LegNegDrugTstMonth
		private String negDrugTestDay = ""; //$LegNegDrugTstDay
		private String negDrugTestYear = ""; //$LegNegDrugTstYear
		private String physTestMonth = ""; //$LegPhysTstMonth
		private String physTestDay = ""; //$LegPhysTstDay
		private String physTestYear = ""; //$LegPhysTstYear
		private String contractMonth = ""; //$LegContractDtMonth
		private String contractDay = ""; //$LegContractDtDay
		private String contractYear = ""; //$LegContractDtYear
		private String companySeniorityMonth = ""; //$LegCompanySeniorityMonth
		private String companySeniorityDay = ""; //$LegCompanySeniorityDay
		private String companySeniorityYear = ""; //$LegCompanySeniorityYear
		public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
		public String getUserEmployeeId() {
			return userEmployeeId;
		}
		public void setUserEmployeeId(String userEmployeeId) {
			this.userEmployeeId = userEmployeeId;
		}
		public String getHireDay() {
			return hireDay;
		}
		public void setHireDay(String hireDay) {
			this.hireDay = hireDay;
		}
		public String getHireMonth() {
			return hireMonth;
		}
		public void setHireMonth(String hireMonth) {
			this.hireMonth = hireMonth;
		}
		public String getHireYear() {
			return hireYear;
		}
		public void setHireYear(String hireYear) {
			this.hireYear = hireYear;
		}
		public String getTerminationMonth() {
			return terminationMonth;
		}
		public void setTerminationMonth(String terminationMonth) {
			this.terminationMonth = terminationMonth;
		}
		public String getTerminationDay() {
			return terminationDay;
		}
		public void setTerminationDay(String terminationDay) {
			this.terminationDay = terminationDay;
		}
		public String getTerminationYear() {
			return terminationYear;
		}
		public void setTerminationYear(String terminationYear) {
			this.terminationYear = terminationYear;
		}
		public String getLastReviewMonth() {
			return lastReviewMonth;
		}
		public void setLastReviewMonth(String lastReviewMonth) {
			this.lastReviewMonth = lastReviewMonth;
		}
		public String getLastReviewDay() {
			return lastReviewDay;
		}
		public void setLastReviewDay(String lastReviewDay) {
			this.lastReviewDay = lastReviewDay;
		}
		public String getLastReviewYear() {
			return lastReviewYear;
		}
		public void setLastReviewYear(String lastReviewYear) {
			this.lastReviewYear = lastReviewYear;
		}
		public String getNextReviewMonth() {
			return nextReviewMonth;
		}
		public void setNextReviewMonth(String nextReviewMonth) {
			this.nextReviewMonth = nextReviewMonth;
		}
		public String getNextReviewDay() {
			return nextReviewDay;
		}
		public void setNextReviewDay(String nextReviewDay) {
			this.nextReviewDay = nextReviewDay;
		}
		public String getNextReviewYear() {
			return nextReviewYear;
		}
		public void setNextReviewYear(String nextReviewYear) {
			this.nextReviewYear = nextReviewYear;
		}
		public String getNegDrugTestMonth() {
			return negDrugTestMonth;
		}
		public void setNegDrugTestMonth(String negDrugTestMonth) {
			this.negDrugTestMonth = negDrugTestMonth;
		}
		public String getNegDrugTestDay() {
			return negDrugTestDay;
		}
		public void setNegDrugTestDay(String negDrugTestDay) {
			this.negDrugTestDay = negDrugTestDay;
		}
		public String getNegDrugTestYear() {
			return negDrugTestYear;
		}
		public void setNegDrugTestYear(String negDrugTestYear) {
			this.negDrugTestYear = negDrugTestYear;
		}
		public String getPhysTestMonth() {
			return physTestMonth;
		}
		public void setPhysTestMonth(String physTestMonth) {
			this.physTestMonth = physTestMonth;
		}
		public String getPhysTestDay() {
			return physTestDay;
		}
		public void setPhysTestDay(String physTestDay) {
			this.physTestDay = physTestDay;
		}
		public String getPhysTestYear() {
			return physTestYear;
		}
		public void setPhysTestYear(String physTestYear) {
			this.physTestYear = physTestYear;
		}
		public String getContractMonth() {
			return contractMonth;
		}
		public void setContractMonth(String contractMonth) {
			this.contractMonth = contractMonth;
		}
		public String getContractDay() {
			return contractDay;
		}
		public void setContractDay(String contractDay) {
			this.contractDay = contractDay;
		}
		public String getContractYear() {
			return contractYear;
		}
		public void setContractYear(String contractYear) {
			this.contractYear = contractYear;
		}
		public String getCompanySeniorityMonth() {
			return companySeniorityMonth;
		}
		public void setCompanySeniorityMonth(String companySeniorityMonth) {
			this.companySeniorityMonth = companySeniorityMonth;
		}
		public String getCompanySeniorityDay() {
			return companySeniorityDay;
		}
		public void setCompanySeniorityDay(String companySeniorityDay) {
			this.companySeniorityDay = companySeniorityDay;
		}
		public String getCompanySeniorityYear() {
			return companySeniorityYear;
		}
		public void setCompanySeniorityYear(String companySeniorityYear) {
			this.companySeniorityYear = companySeniorityYear;
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

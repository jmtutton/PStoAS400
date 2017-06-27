package erd.model;

import java.util.Date;

public class ProcessParameters {
	
	TerminationProcessParameters terminationProcessParameters;
	
	public TerminationProcessParameters getTerminationProcessParameters() {
		return terminationProcessParameters;
	}
	public void setTerminationProcessParameters(TerminationProcessParameters terminationProcessParameters) {
		this.terminationProcessParameters = terminationProcessParameters;
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
}

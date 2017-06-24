package erd.model;

import java.util.Date;

public class ActionFields {
	
	TerminationFields terminationFields;
	
	public TerminationFields getTerminationFields() {
		return terminationFields;
	}
	public void setTerminationFields(TerminationFields terminationFields) {
		this.terminationFields = terminationFields;
	}
	
	public class TerminationFields {
		private String voluntaryOrInvoluntary = ""; //$PSVolInvol
		private String terminationCode = ""; //$PSTermCode
		private String terminationReason = ""; //$PSTermReason is at most 30 positions long, but HRZ102A receives it with a length of 35 positions; needs to be padded to 35
		private String terminationMonth = "";
		private String terminationDay = "";
		private String terminationYear = "";
		private Date terminationDate;
		private String rehireMonth = "";
		private String rehireDay = "";
		private String rehireYear = "";
		private Date rehireDate;

		public TerminationFields() {
		}
	
		public String getVoluntaryOrInvoluntary() {
			return voluntaryOrInvoluntary != null ? voluntaryOrInvoluntary.trim() : voluntaryOrInvoluntary;
		}
		public void setVoluntaryOrInvoluntary(String voluntaryOrInvoluntary) {
			this.voluntaryOrInvoluntary = voluntaryOrInvoluntary.trim();
		}
		public String getTerminationCode() {
			return terminationCode != null ? terminationCode.trim() : terminationCode;
		}
		public void setTerminationCode(String terminationCode) {
			this.terminationCode = terminationCode.trim();
		}
		public String getTerminationReason() {
			return terminationReason != null ? terminationReason.trim() : terminationReason;
		}
		public void setTerminationReason(String terminationReason) {
			this.terminationReason = terminationReason.trim();
		}
		public String getTerminationMonth() {
			return terminationMonth != null ? terminationMonth.trim() : terminationMonth;
		}
		public void setTerminationMonth(String terminationMonth) {
			this.terminationMonth = terminationMonth.trim();
		}
		public String getTerminationDay() {
			return terminationDay != null ? terminationDay.trim() : terminationDay;
		}
		public void setTerminationDay(String terminationDay) {
			this.terminationDay = terminationDay.trim();
		}
		public String getTerminationYear() {
			return terminationYear != null ? terminationYear.trim() : terminationYear;
		}
		public void setTerminationYear(String terminationYear) {
			this.terminationYear = terminationYear.trim();
		}
		public String getRehireMonth() {
			return rehireMonth != null ? rehireMonth.trim() : rehireMonth;
		}
		public void setRehireMonth(String rehireMonth) {
			this.rehireMonth = rehireMonth.trim();
		}
		public String getRehireDay() {
			return rehireDay != null ? rehireDay.trim() : rehireDay;
		}
		public void setRehireDay(String rehireDay) {
			this.rehireDay = rehireDay.trim();
		}
		public String getRehireYear() {
			return rehireYear != null ? rehireYear.trim() : rehireYear;
		}
		public void setRehireYear(String rehireYear) {
			this.rehireYear = rehireYear.trim();
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
	}
}

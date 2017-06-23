package erd.model;

public class ActionFields {
	
	private String voluntaryOrInvoluntary; //$PSVolInvol
	private String terminationCode; //$PSTermCode
	private String terminationReason; //$PSTermReason is at most 30 positions long, but HRZ102A receives it with a length of 35 positions; needs to be padded to 35
	private String terminationMonth;
	private String terminationDay;
	private String terminationYear;
	private String rehireMonth;
	private String rehireDay;
	private String rehireYear;

	public String getVoluntaryOrInvoluntary() {
		return voluntaryOrInvoluntary.trim();
	}
	public void setVoluntaryOrInvoluntary(String voluntaryOrInvoluntary) {
		this.voluntaryOrInvoluntary = voluntaryOrInvoluntary.trim();
	}
	public String getTerminationCode() {
		return terminationCode.trim();
	}
	public void setTerminationCode(String terminationCode) {
		this.terminationCode = terminationCode.trim();
	}
	public String getTerminationReason() {
		return terminationReason.trim();
	}
	public void setTerminationReason(String terminationReason) {
		this.terminationReason = terminationReason.trim();
	}
	public String getTerminationMonth() {
		return terminationMonth.trim();
	}
	public void setTerminationMonth(String terminationMonth) {
		this.terminationMonth = terminationMonth.trim();
	}
	public String getTerminationDay() {
		return terminationDay.trim();
	}
	public void setTerminationDay(String terminationDay) {
		this.terminationDay = terminationDay.trim();
	}
	public String getTerminationYear() {
		return terminationYear.trim();
	}
	public void setTerminationYear(String terminationYear) {
		this.terminationYear = terminationYear.trim();
	}
	public String getRehireMonth() {
		return rehireMonth.trim();
	}
	public void setRehireMonth(String rehireMonth) {
		this.rehireMonth = rehireMonth.trim();
	}
	public String getRehireDay() {
		return rehireDay.trim();
	}
	public void setRehireDay(String rehireDay) {
		this.rehireDay = rehireDay.trim();
	}
	public String getRehireYear() {
		return rehireYear.trim();
	}
	public void setRehireYear(String rehireYear) {
		this.rehireYear = rehireYear.trim();
	}
}

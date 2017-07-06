package erd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity superclass for trigger table entities
 * @author	John Tutton john@tutton.net
 * @see		TriggerEmployee, TriggerNonPerson
 */
@MappedSuperclass
@Inheritance(strategy  = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class PszTriggerSuperclass implements Serializable {
	public static final long serialVersionUID = 1L;
	
	protected static String ENTITY_NAME = "";

	@Id
	@Column(name="SEQ_NBR", nullable=false, precision=15)
	protected BigDecimal sequenceNumber;
	   
	@Column(name="OPRID", nullable=false, length=30)
	protected String operatorId;

	@Column(name="EMPLID", nullable=false, length=11)
	protected String employeeId;

	@Column(name="EFFDT")
	@Temporal(TemporalType.DATE)
	protected Date effectiveDate;
	   
	@Column(name="EFFSEQ", nullable=false, precision=38)
	protected BigDecimal effectiveSequence;
	   
	@Column(name="PROC_NAME", nullable=false, length=10)
	protected String processName;  //TODO: make enum
	   
	@Column(name="TASK_FLAG", nullable=false, length=1)
	protected String completionStatus;  //TODO: make enum with values 'P', 'C', 'E', 'W'

	public BigDecimal getSequenceNumber() {
		return sequenceNumber;
	}
	public String getCompletionStatus() {
		return completionStatus != null ? completionStatus.trim() :  completionStatus;
	}
	public String getOperatorId() {
		return operatorId != null ? operatorId.trim() :  operatorId;
	}
	public String getEmployeeId() {
		return employeeId != null ? employeeId.trim() :  employeeId;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public BigDecimal getEffectiveSequence() {
		return effectiveSequence;
	}
	public String getProcessName() {
		return processName != null ? processName.trim() :  processName;
	}
	
	public void setSequenceNumber(BigDecimal sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId != null ? operatorId.trim() :  operatorId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId != null ? employeeId.trim() :  employeeId;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public void setEffectiveSequence(BigDecimal effectiveSequence) {
		this.effectiveSequence = effectiveSequence;
	}
	public void setProcessName(String processName) {
		this.processName = processName != null ? processName.trim() :  processName;
	}
	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus != null ? completionStatus.trim() :  completionStatus;
	}
	
	@Override
	public String toString() {
		return "ENTITY_NAME: " + ENTITY_NAME + "\n" +
				"SequenceNumber: " + getSequenceNumber() + "\n" +
				"OperatorId: " + getOperatorId() + "\n" +
				"EmployeeId: " + getEmployeeId() + "\n" +
				"EffectiveDate: " + getEffectiveDate() + "\n" +
				"EffectiveSequence: " + getEffectiveSequence() + "\n" +
				"ProcessName: " + getProcessName() + "\n" +
				"CompletionStatus: " + getCompletionStatus();
	}
	
}

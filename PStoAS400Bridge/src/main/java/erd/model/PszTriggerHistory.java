package erd.model;
//package erd.model;
//
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.persistence.*;
//
/**
 * The persistent class for the PS_ZHRT_HISTRIGGER database table.
 * 
 */
//TODO: Can't find this table in database
@Entity
@Table(name="PS_ZHRT_HISTRIGGER")
@NamedQuery(name="PszTriggerHistory.findAll", query="SELECT p FROM PszTriggerHistory p")
public class PszTriggerHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SEQ_NBR", nullable=false, precision=15)
	protected BigInteger sequenceNumber;
	   
	@Column(name="OPRID", nullable=false, length=30)
	protected String operatorId;

	@Column(name="EMPLID", nullable=false, length=11)
	protected String employeeId;

	@Column(name="EFFDT")
	@Temporal(TemporalType.DATE)
	protected Date effectiveDate;
	   
	@Column(name="EFFSEQ", nullable=false, precision=38)
	protected BigInteger effectiveSequence;
	   
	@Column(name="PROC_NAME", nullable=false, length=10)
	protected String processName;  //TODO: make enum
	   
	@Column(name="TASK_FLAG", nullable=false, length=1)
	protected String completionStatus;  //TODO: make enum with values 'P', 'C', 'E', 'W'

	@Column(name="ARCHIVE_DT")
	@Temporal(TemporalType.DATE)
	protected Date archiveDate;

	public PszTriggerHistory() {
	}

}
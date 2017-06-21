package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the PS_EMPL_CHECKLIST database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_EMPL_CHECKLIST")
@NamedQuery(name="PsEmployeeChecklist.findAll", query="SELECT p FROM PsEmployeeChecklist p")
public class PsEmployeeChecklist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="CHECKLIST_CD", nullable=false, length=6)
	private String checklistCode;

	@Column(name="CHECKLIST_DT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date checklistDate;

	@Lob
	@Column(name="COMMENTS")
	private String comments;

	@Column(name="EMPL_RCD", nullable=false, precision=38)
	private BigDecimal employmentRecordNumber;

	@Column(name="RESPONSIBLE_ID", nullable=false, length=11)
	private String responsibleId;

	public PsEmployeeChecklist() {
	}

	public String getChecklistCode() {
		return this.checklistCode;
	}

	public void setChecklistCode(String checklistCode) {
		this.checklistCode = checklistCode;
	}

	public Date getChecklistDate() {
		return this.checklistDate;
	}

	public void setChecklistDate(Date checklistDate) {
		this.checklistDate = checklistDate;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getEmploymentRecordNumber() {
		return this.employmentRecordNumber;
	}

	public void setEmploymentRecordNumber(BigDecimal employmentRecordNumber) {
		this.employmentRecordNumber = employmentRecordNumber;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getResponsibleId() {
		return this.responsibleId;
	}

	public void setResponsibleId(String responsibleId) {
		this.responsibleId = responsibleId;
	}

	public PsEmployeeChecklist findByEmployeeIdAndChecklistDate(String employeeId, Date checklistDate) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR05-Get-Employee-Checklist
//		! Desc:  This routine gets the Responsible Id from the Employee checklist
//		!        table.  This field is used to populate the Recruiter Id field
//		!        in AAHR05.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR05-Get-Employee-Checklist
//		begin-select
//		CECT.Responsible_Id
//		  If &CECT.Responsible_Id > '           '
//		     Let $PSResponsible_Id = &CECT.Responsible_Id
//		     Do HR05-Get-Next-Opid
//		  End-if    !&CECT.Responsible_Id > '           '
//		from PS_Empl_Checklist CECT
//		where CECT.Emplid = $PSEmplid
//		  and to_char(CECT.Checklist_Dt,'YYYY-MM-DD') = $PSEffdt
//		end-select
//		End-Procedure HR05-Get-Employee-Checklist
		return null;
	}


	
}
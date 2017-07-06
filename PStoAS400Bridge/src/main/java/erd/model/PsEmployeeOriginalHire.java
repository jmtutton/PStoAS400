package erd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the PS_ORIG_HIR_EMP_VW database table.
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ORIG_HIR_EMP_VW")
@NamedQuery(name="PsEmployeeOriginalHire.findAll", query="SELECT p FROM PsEmployeeOriginalHire p")
public class PsEmployeeOriginalHire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="ORIG_HIRE_DT")
	@Temporal(TemporalType.DATE)
	private Date originalHireDate;

}

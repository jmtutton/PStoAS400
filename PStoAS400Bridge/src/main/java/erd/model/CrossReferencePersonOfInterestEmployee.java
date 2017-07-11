package erd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the PS_ZHRR_POI_EMP_VW database table.
 * Cross-Reference of Company and Business Unit to Legacy Group and Legacy Region
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRR_POI_EMP_VW")
@NamedQuery(name="CrossReferencePersonOfInterestEmployee.findAll", query="SELECT p FROM CrossReferencePersonOfInterestEmployee p")
public class CrossReferencePersonOfInterestEmployee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="NAME", nullable=false, length=50)
	private String name;

	@Column(name="Z_PER_TYPE", length=1)
	private String zPerType;

}

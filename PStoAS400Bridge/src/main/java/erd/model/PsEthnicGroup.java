package erd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the PS_ETHNIC_GRP_TBL database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ETHNIC_GRP_TBL")
@NamedQuery(name="PsEthnicGroup.findAll", query="SELECT p FROM PsEthnicGroup p")
public class PsEthnicGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SETID", nullable=false, length=5)
	private String setId;

	@Column(name="DESCR50", nullable=false, length=50)
	private String description;

	@Column(name="DESCRSHORT", nullable=false, length=10)
	private String shortDescription;

	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String statusAsOfEffectiveDate;

	@Column(name="EFFDT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="ETHNIC_CATEGORY", nullable=false, length=1)
	private String ethnicCategory;

	@Column(name="ETHNIC_GROUP", nullable=false, length=1)
	private String ethnicGroup;

	@Column(name="ETHNIC_GRP_CD", nullable=false, length=8)
	private String ethnicGroupCode;

	public PsEthnicGroup() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getStatusAsOfEffectiveDate() {
		return this.statusAsOfEffectiveDate;
	}

	public void setStatusAsOfEffectiveDate(String statusAsOfEffectiveDate) {
		this.statusAsOfEffectiveDate = statusAsOfEffectiveDate;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getEthnicCategory() {
		return this.ethnicCategory;
	}

	public void setEthnicCategory(String ethnicCategory) {
		this.ethnicCategory = ethnicCategory;
	}

	public String getEthnicGroup() {
		return this.ethnicGroup;
	}

	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}

	public String getEthnicGroupCode() {
		return this.ethnicGroupCode;
	}

	public void setEthnicGroupCode(String ethnicGroupCode) {
		this.ethnicGroupCode = ethnicGroupCode;
	}

	public String getSetId() {
		return this.setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	public PsEthnicGroup findByEthnicGroupCode(String ethnicGroupCode) {
//		!----------------------------------------------------------------------
//		! Procedure: GET-ETHNIC-CODE
//		! Desc:  This procedure will get the ethnic code value to send to medstat
//		!----------------------------------------------------------------------
//		Begin-procedure GET-ETHNIC-CODE
//		begin-select
//		EG.ETHNIC_GROUP
//		  Let $PS_Ethnic_Group = &EG.ETHNIC_GROUP
//		  Let $PSEthnicCode = $PS_Ethnic_Group
//		FROM PS_ETHNIC_GRP_TBL EG
//		WHERE ETHNIC_GRP_CD = $PS_Ethnic_Group_CD
//		END-SELECT
//		End-procedure GET-ETHNIC-CODE
		return null;
	}

	public PsEthnicGroup findByEthnicGroupCode1(String ethnicGroupCode) {
//		!----------------------------------------------------------------------
//		! Procedure: GET-ETHNIC-CODE1
//		! Desc:  This procedure will get the ethnic code value
//		!----------------------------------------------------------------------
//		Begin-procedure GET-ETHNIC-CODE1
//		begin-select
//		EG1.ETHNIC_GROUP
//		  Let $PSEthnic_Group = &EG1.ETHNIC_GROUP
//		FROM PS_ETHNIC_GRP_TBL EG1
//		WHERE EG1.ETHNIC_GRP_CD = $PSETHNIC_GROUP1
//		END-SELECT
//		 Do HR05-Get-Ethnic-Group
//		End-procedure GET-ETHNIC-CODE1
		return null;
	}
	
}
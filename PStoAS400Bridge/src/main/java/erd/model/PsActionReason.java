package erd.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_ACTN_REASON_TBL database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ACTN_REASON_TBL")
@NamedQuery(name="PsActionReason.findAll", query="SELECT p FROM PsActionReason p")
public class PsActionReason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACTION_REASON", nullable=false, length=3)
	private String actionReasonCode;

	@Column(name="\"ACTION\"", nullable=false, length=3)
	private String actionCode;

	@Column(name="DESCR", nullable=false, length=30)
	private String description;

	@Column(name="DESCRSHORT", nullable=false, length=10)
	private String shortDescription;

	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String statusAsOfEffectiveDate; //status as of the effective date

	@Column(name="EFFDT", nullable=false)
	private Date effectiveDate;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="OBJECTOWNERID", nullable=false, length=4)
	private String objectOwnerId;

	@Column(name="SYSTEM_DATA_FLG", nullable=false, length=1)
	private String systemDataFlag;

	public PsActionReason() {
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionReasonCode() {
		return this.actionReasonCode;
	}

	public void setActionReasonCode(String actionReasonCode) {
		this.actionReasonCode = actionReasonCode;
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

	public Timestamp getLastUpdatedDateAndTime() {
		return this.lastUpdatedDateAndTime;
	}

	public void setLastUpdatedDateAndTime(Timestamp lastUpdatedDateAndTime) {
		this.lastUpdatedDateAndTime = lastUpdatedDateAndTime;
	}

	public String getLastUpdatedUserId() {
		return this.lastUpdatedUserId;
	}

	public void setLastUpdatedUserId(String lastUpdatedUserId) {
		this.lastUpdatedUserId = lastUpdatedUserId;
	}

	public String getObjectOwnerId() {
		return this.objectOwnerId;
	}

	public void setObjectOwnerId(String objectOwnerId) {
		this.objectOwnerId = objectOwnerId;
	}

	public String getSystemDataFlag() {
		return this.systemDataFlag;
	}

	public void setSystemDataFlag(String systemDataFlag) {
		this.systemDataFlag = systemDataFlag;
	}
	
	
	/**
	 * This procedure retrieves the Negative Drug Test Date and Physical Test 
	 * Date from the PeopleSoft Accomplishments Table to send back to 
	 * Option 7 of AAHR01.
	 * 
	 */
	public PsActionReason findByActionCodeAndActionReason(String actionCode, String actionReasonCode) {
//	!----------------------------------------------------------------------
//	! Procedure:  HR02-Get-Reason-Description
//	! Desc:  This routine gets the description field from the Action
//	!        Reason table when Action = Termination and Action Code equals
//	!        Other.
//	!----------------------------------------------------------------------
//	Begin-Procedure HR02-Get-Reason-Description
//	begin-select
//	CART.Action
//	CART.Action_Reason
//	CART.Descr
//	   Let $PSTermReason = &CART.Descr
//	   Uppercase $PSTermReason
//	from PS_Actn_Reason_Tbl CART
//	where CART.Action = $PSAction
//	  and CART.Action_Reason = $PSAction_Reason
//	END-SELECT
//	End-Procedure HR02-Get-Reason-Description
		return null;
	}

	/**
	 * Replaces SQC procedure HR02-Get-Reason-Description from ZHRI102A.SQC
	 * This procedure retrieves a record from the Action Reason table 
	 * with matching Action Code and Action Reason Code
	 */
	public static PsActionReason findByActionCodeAndActionReasonCode(String actionCode, String actionReasonCode) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsActionReason> resultList = (List<PsActionReason>) em.createQuery("SELECT p FROM PsActionReason p "
	    				+ "WHERE UPPER(TRIM(p.actionCode)) = :actionCode "
	    				+ "AND UPPER(TRIM(p.actionReasonCode)) = :actionReasonCode", PsActionReason.class)
	    		    .setParameter("actionCode", actionCode.toUpperCase())
	    		    .setParameter("actionReasonCode", actionReasonCode.toUpperCase())
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0);
	    	}
	    	else 
	    		return null;
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
	}

}
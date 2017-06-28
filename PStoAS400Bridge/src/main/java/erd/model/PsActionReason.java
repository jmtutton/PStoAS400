package erd.model;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_ACTN_REASON_TBL database table.
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ACTN_REASON_TBL")
@NamedQuery(name="PsActionReason.findAll", query="SELECT p FROM PsActionReason p")
public class PsActionReason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACTION_REASON", nullable=false, length=3)
	private String actionReason;

	@Column(name="\"ACTION\"", nullable=false, length=3)
	private String action;

	@Column(name="DESCR", nullable=false, length=30)
	private String description;

	@Column(name="DESCRSHORT", nullable=false, length=10)
	private String shortDescription;

	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String statusAsOfEffectiveDate; //status as of the effective date

	@Column(name="EFFDT", nullable=false)
	@Temporal(TemporalType.DATE)
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

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionReason() {
		return this.actionReason;
	}

	public void setActionReason(String actionReason) {
		this.actionReason = actionReason;
	}

	public String getDescription() {
		return this.description;
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

	public Timestamp getLastUpdatedDateAndTime() {
		return this.lastUpdatedDateAndTime;
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
//		!----------------------------------------------------------------------
//		! ERAC
//		! Procedure:  HR02-Get-Reason-Description
//		! Desc:  This routine gets the description field from the Action
//		!        Reason table when Action = Termination and Action Code equals
//		!        Other.
//		!----------------------------------------------------------------------
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
	    finally {
	    	em.close();
	    }
	    return null;	
	}

	public static String findReasonDescriptionByActionAndActionReason(String action, String actionReason) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static List<String> findAllActionReasonCode() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    			"SELECT p.actionReasonCode FROM PsActionReason p ", String.class)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList;
	    	}
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	    return null;	
	}
	
	public static List<PsActionReason> findAll() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsActionReason> resultList = em.createQuery(
	    			"SELECT p FROM PsActionReason p ", PsActionReason.class)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList;
	    	}
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	    return null;	
	}

	@Override
	public String toString() {
		return "actionReason = " + actionReason + "\n"
				+ "action = " + action + "\n"
				+ "description = " + description + "\n"
				+ "shortDescription = " + shortDescription + "\n"
				+ "statusAsOfEffectiveDate = " + statusAsOfEffectiveDate + "\n"
				+ "effectiveDate = " + effectiveDate + "\n"
				+ "lastUpdatedDateAndTime = " + lastUpdatedDateAndTime + "\n"
				+ "lastUpdatedUserId = " + lastUpdatedUserId + "\n"
				+ "objectOwnerId = " + objectOwnerId + "\n"
				+ "systemDataFlag = " + systemDataFlag + "\n";
	}

}
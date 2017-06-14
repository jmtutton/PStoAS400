package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_ZHRT_TRMRS_CREF database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRT_TRMRS_CREF")
@NamedQuery(name="CrossReferenceTerminationReason.findAll", query="SELECT p FROM CrossReferenceTerminationReason p")
public class CrossReferenceTerminationReason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACTION_REASON", nullable=false, length=3)
	private String actionReasonCode;

	@Column(name="\"ACTION\"", nullable=false, length=3)
	private String actionCode;

	@Column(name="STATUS", nullable=false, length=1)
	private String status;

	@Column(name="ZHRF_LEGTERMCD", nullable=false, length=1)
	private String legacyTerminationCode;

	@Column(name="ZHRF_LEGTERMRSN", nullable=false, length=1)
	private String legacyTerminationReason;

	public CrossReferenceTerminationReason() {
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLegacyTerminationCode() {
		return this.legacyTerminationCode;
	}

	public void setLegacyTerminationCode(String legacyTerminationCode) {
		this.legacyTerminationCode = legacyTerminationCode;
	}

	public String getLegacyTerminationReason() {
		return this.legacyTerminationReason;
	}

	public void setLegacyTerminationReason(String legacyTerminationReason) {
		this.legacyTerminationReason = legacyTerminationReason;
	}

	public CrossReferenceTerminationReason HR02GetActionReason(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR02-Get-Action-Reason
//		! Desc:  This routine will determine if a termination was voluntary or
//		!        involuntary based on Action and Action Reason codes.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR02-Get-Action-Reason
//		Let $Found = 'N'   !Initialize the record found variable
//		Begin-Select
//		CPT16.ZHRF_LEGTERMCD
//		    Let $PSVolInvol = &CPT16.ZHRF_LEGTERMCD
//		CPT16.ZHRF_LEGTERMRSN
//		    Let $PSTermCode = &CPT16.ZHRF_LEGTERMRSN
//		     If $PSTermCode = 'O' and $PSAction = 'TER'
//		        Do HR02-Get-Reason-Description
//		     End-If    !$PSTermCode = 'O'
//		    Let $Found = 'Y'     !Mark that a record was found
//		from PS_ZHRT_TRMRS_CREF CPT16
//		where CPT16.STATUS = 'A'
//		  and CPT16.ACTION = $PSAction
//		  and CPT16.ACTION_REASON = $PSAction_Reason
//		End-Select
//		If $Found = 'N'
//		     Let $ErrorMessageParm = 'Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF'
//		     Do Call-Error-Routine       !From ZHRI100A.SQR
//		     !Default the Action and reason in the legacy system
//		     Let $PSVolInvol = 'V'
//		     Let $PSTermCode = 'O'
//		     Let $PSTermReason = 'ACTION REASON NOT SELECTED IN PS'
//		End-If    !$Found = 'N'
//		End-Procedure HR02-Get-Action-Reason
		return null;
	}
	
	/**
	 * Replaces SQC procedure HR02-Get-Action-Reason from ZHRI102A.SQC
	 * This routine will determine if a termination was voluntary or involuntary based on Action and Action Reason codes.
	 * @see ZHRI102A.SQC
	 */
	public static List<CrossReferenceTerminationReason> findActionReason(String actionCode, String actionReasonCode) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<CrossReferenceTerminationReason> resultList = (List<CrossReferenceTerminationReason>) em.createQuery("SELECT p FROM CrossReferenceTerminationReason p "
	    				+ "WHERE p.status = :status AND  p.actionCode = :actionCode AND  p.actionReasonCode = :actionReasonCode ", CrossReferenceTerminationReason.class)
	    		    .setParameter("status", "A")
	    		    .setParameter("actionCode", actionCode)
	    		    .setParameter("actionReasonCode", actionReasonCode)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList;
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
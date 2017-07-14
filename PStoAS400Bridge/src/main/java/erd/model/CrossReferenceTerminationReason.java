package erd.model;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The persistent class for the PS_ZHRT_TRMRS_CREF database table.
 * Cross-Reference of Action and Action Reason to Legacy Termination Code and Termination Reason
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRT_TRMRS_CREF")
@NamedQuery(name="CrossReferenceTerminationReason.findAll", query="SELECT p FROM CrossReferenceTerminationReason p")
public class CrossReferenceTerminationReason implements Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	@Id
	@Column(name="ACTION_REASON", nullable=false, length=3)
	private String actionReason;

	@Column(name="\"ACTION\"", nullable=false, length=3)
	private String action;

	@Column(name="STATUS", nullable=false, length=1)
	private String status;

	@Column(name="ZHRF_LEGTERMCD", nullable=false, length=1)
	private String legacyTerminationCode;

	@Column(name="ZHRF_LEGTERMRSN", nullable=false, length=1)
	private String legacyTerminationReason;

	public CrossReferenceTerminationReason() {
	}

	public String getAction() {
		return this.action;
	}

	public String getActionReason() {
		return this.actionReason;
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

	/**
	 * Replaces SQC procedure HR02-Get-Action-Reason from ZHRI102A.SQC
	 * This routine will determine if a termination was voluntary or involuntary based on Action and Action Reason codes.
	 * @see HR02-Get-Action-Reason in ZHRI102A.SQC
	 */
	public static CrossReferenceTerminationReason findByActionAndActionReasonAndStatus(String action, String actionReason, String status) {
		logger.debug("findByActionAndActionReasonAndStatus()");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<CrossReferenceTerminationReason> resultList = (List<CrossReferenceTerminationReason>) em.createQuery(
	    			"SELECT p FROM CrossReferenceTerminationReason p "
	    				+ "WHERE UPPER(TRIM(p.status)) = UPPER(TRIM(:status)) "
	    				+ "AND UPPER(TRIM(p.action)) = UPPER(TRIM(:action)) "
	    				+ "AND UPPER(TRIM(p.actionReason)) = UPPER(TRIM(:actionReason)) "
	    				, CrossReferenceTerminationReason.class)
	    		    .setParameter("action", action)
	    		    .setParameter("actionReason", actionReason)
	    		    .setParameter("status", status)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0);
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

}
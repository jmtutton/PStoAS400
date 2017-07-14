package erd.model;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The persistent class for the PS_ZHRT_RFSRC_CREF database table.
 * Cross-Reference of Referral Source to Legacy Recruiter Source
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRT_RFSRC_CREF")
@NamedQuery(name="CrossReferenceReferralSource.findAll", query="SELECT p FROM CrossReferenceReferralSource p")
public class CrossReferenceReferralSource implements Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	@Id
	@Column(name="REFERRAL_SOURCE", nullable=false, length=2)
	private String referralSource;

	@Column(name="STATUS", nullable=false, length=1)
	private String status;

	@Column(name="ZHRF_LEGRECRUITSRC", nullable=false, length=1)
	private String legacyRecruiterSource;

	public CrossReferenceReferralSource() {
	}

	public String getReferralSource() {
		return this.referralSource;
	}

	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLegacyRecruiterSource() {
		return this.legacyRecruiterSource;
	}

	public void setLegacyRecruiterSource(String legacyRecruiterSource) {
		this.legacyRecruiterSource = legacyRecruiterSource;
	}

	/**
	 * @see HR01-Get-Referral-Source in ZHRI101A.SQC
	 * @see HR05-Format-Referal-Source in ZHRI105A.SQC
	 * @param parameterMap
	 * @return legacyRecruiterSource
	 */
	public static String findActiveLegacyRecruiterSourceByReferralSource(String referralSource) {
		logger.debug("findActiveLegacyRecruiterSourceByReferralSource() ***");
		logger.debug("referralSource: " + referralSource);
		//SELECT CPT101.ZHRF_LEGRECRUITSRC
		//FROM PS_ZHRT_RFSRC_CREF CPT101
		//WHERE CPT101.REFERRAL_SOURCE = $PSReferralSource
		//AND CPT101.STATUS = 'A'
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<String> resultList = em.createQuery(
					"SELECT UPPER(TRIM(p.legacyRecruiterSource)) FROM CrossReferenceReferralSource p "
							+ "WHERE UPPER(TRIM(p.referralSource)) = UPPER(TRIM(:referralSource)) "
							+ "AND p.status = 'A' "
					, String.class)
					.setParameter("referralSource", referralSource)
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
package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the PS_HRS_SOURCE_I database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_HRS_SOURCE_I")
@NamedQuery(name="PsRecruitmentSource.findAll", query="SELECT p FROM PsRecruitmentSource p")
public class PsRecruitmentSource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HRS_SOURCE_ID", nullable=false, precision=15)
	private BigInteger sourceId;

	@Column(name="EFFDT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="EFF_STATUS", length=1)
	private String status;

	@Column(name="HRS_SOURCE_STATUS")
	@Temporal(TemporalType.DATE)
	private Date sourceStatusDate;

	@Column(name="HRS_SOURCE_NAME", nullable=false, length=30)
	private String sourceName;

	@Column(name="HRS_SOURCE_DESCR", nullable=false, length=254)
	private String sourceDescription;

	@Column(name="HRS_SOURCE_TYPE", nullable=false, length=3)
	private String sourceType;

	public PsRecruitmentSource() {
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getSourceDescription() {
		return this.sourceDescription != null ? this.sourceDescription.trim() : this.sourceDescription;
	}

	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}

	public BigInteger getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(BigInteger sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return this.sourceName != null ? this.sourceName.trim() : this.sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Date getSourceStatusDate() {
		return this.sourceStatusDate;
	}

	public void setSourceStatusDate(Date sourceStatusDate) {
		this.sourceStatusDate = sourceStatusDate;
	}

	public String getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @see HR05-Get-Referral-Source
	 * @param recruitmentSourceId
	 * @return
	 */
	public static PsRecruitmentSource findBySourceId(BigInteger sourceId) {
		//SELECT FROM PS_HRS_SOURCE_I P
		//WHERE P.HRS_SOURCE_ID = $SourceId
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsRecruitmentSource> resultList = em.createQuery(
	    			"SELECT p FROM PsRecruitmentSource p "
	    					+ "WHERE p.sourceId = :sourceId ",
	    					PsRecruitmentSource.class)
	    		    .setParameter("sourceId", sourceId)
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
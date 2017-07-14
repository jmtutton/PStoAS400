package erd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

	/**
	 * This procedure will get the ethnic code value.
	 * @see Get-Ethnic-Code1 in ZHRI105A.SQC
	 * @param ethnicGroupCode
	 * @return
	 */
	public static String findEthnicGroupByEthnicGroupCode(String ethnicGroupCode) {
		//SELECT EG1.ETHNIC_GROUP
		//FROM PS_ETHNIC_GRP_TBL EG1
		//WHERE EG1.ETHNIC_GRP_CD = $PSETHNIC_GROUP1
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = (List<String>) em.createQuery(
	    			"SELECT UPPER(TRIM(p.ethnicGroup)) "
	    				+ "FROM PsEthnicGroup p "
	    				+ "WHERE UPPER(TRIM(p.ethnicGroupCode)) = UPPER(TRIM(:ethnicGroupCode)) "
	    				, String.class)
	    		    .setParameter("ethnicGroupCode", ethnicGroupCode)
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
	
}
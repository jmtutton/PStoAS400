package erd.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Table;

/**
 * The persistent class for the ZPS_ZGLT_PT12P_CREF database table.
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="ZPS_ZGLT_PT12P_CREF")
@NamedQuery(name="CrossReferencePt12p.findAll", query="SELECT p FROM CrossReferencePt12p p")
public class CrossReferencePt12p implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ZGLF_PT2DPT", nullable=false, length=10)
	private String department;

	@Column(name="ZGLF_PT2REN", nullable=false, precision=38)
	private BigInteger ren;

	@Column(name="ZGLF_PT2PRD", nullable=false, length=5)
	private String prd;

	@Column(name="ZGLF_PT2OBR", nullable=false, length=2)
	private String branch;

	@Column(name="ZGLF_PT2OGP", nullable=false, length=2)
	private String group;

	@Column(name="ZGLF_PT2STS", nullable=false, length=1)
	private String status;
	   
	public String getDepartment() {
		return department != null ? department.trim().toUpperCase() : department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public BigInteger getRen() {
		return ren;
	}
	public void setRen(BigInteger ren) {
		this.ren = ren;
	}
	public String getPrd() {
		return prd != null ? prd.trim().toUpperCase() : prd;
	}
	public void setPrd(String prd) {
		this.prd = prd;
	}
	public String getBranch() {
		return branch != null ? branch.trim().toUpperCase() : branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getGroup() {
		return group != null ? group.trim().toUpperCase() : group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getStatus() {
		return status != null ? status.trim().toUpperCase() : status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @see HR05-Get-Location in ZHRI105A.SQC
	 * @param department
	 * @return CrossReferencePt12p record
	 */
	public static CrossReferencePt12p findByDepartment(String department) {
		//SELECT FROM ZPS_ZGLT_PT12P_CREF C
		//WHERE C.ZGLF_PT2DPT = $PSLocation
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		
		try {
			List<CrossReferencePt12p> resultList = em.createQuery("SELECT CrossReferencePt12p FROM CrossReferencePt12p c "
						+ "WHERE UPPER(TRIM(c.department)) = :department "
					, CrossReferencePt12p.class)
					.setParameter("department", department.trim().toUpperCase())
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
	
	/**
	 * Gets the branch from the cross reference file using location.
	 * @see HR01-Get-Branch
	 * @param department
	 * @return branch
	 */
	public static String findActiveBranchByDepartment(String department) {
		//SELECT P.ZGLF_PT2OBR
		//FROM ZPS_ZGLT_PT12P_CREF C
		//WHERE C.ZGLF_PT2DPT = $WrkSrchLoc AND C.ZGLF_PT2STS = 'A'
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = (List<String>) em.createQuery(
	    			"SELECT UPPER(TRIM(p.branch)) "
	    				+ "FROM CrossReferencePt12p p "
	    				+ "WHERE UPPER(TRIM(p.department)) = :department "
	    				+ "AND UPPER(TRIM(p.status)) = 'A' "
	    				, String.class)
	    		    .setParameter("department", department.trim().toUpperCase())
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

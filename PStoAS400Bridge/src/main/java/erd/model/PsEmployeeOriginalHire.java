package erd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_ORIG_HIR_EMP_VW database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ORIG_HIR_EMP_VW")
@NamedQuery(name="PsEmployeeOriginalHire.findAll", query="SELECT p FROM PsEmployeeOriginalHire p")
public class PsEmployeeOriginalHire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="ORIG_HIRE_DT")
	@Temporal(TemporalType.DATE)
	private Date originalHireDate;

	public PsEmployeeOriginalHire() {
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getOriginalHireDate() {
		return this.originalHireDate;
	}

	public void setOriginalHireDate(Date originalHireDate) {
		this.originalHireDate = originalHireDate;
	}

	/**
	 * @see HR05-Get-Personal-Data
	 * @param employeeId
	 * @return originalHireDate
	 */
	public static Date findOriginalHireDateByEmployeeId(String employeeId) {
		//SELECT P.ORIG_HIRE_DT
		//FROM PS_ORIG_HIR_EMP_VW P
		//WHERE P.EMPLID = $EmplId
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<Date> resultList = (List<Date>) em.createQuery(
	    			"SELECT p.originalHireDate FROM PsOriginalHire p "
	    					+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
	    					, Date.class)
	    		    .setParameter("employeeId", employeeId)
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
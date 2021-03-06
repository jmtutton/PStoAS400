package erd.model;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The persistent class for the HR036P database table.
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="HR036P")
@NamedQuery(name="HR036P.findAll", query="SELECT h FROM HR036P h")
public class HR036P implements Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	
	@Id
	@Column(name="H36EMP", length=5, unique=true, nullable=false) //five digit string left padded with zeros
	private String employeeId;
	
	@Column(name="H36NAM", length=33)
	private String employeeName;
	
	@Column(name="H36EM#", precision=9)
	private Integer employeeNumber;
	
	@Column(name="H36INX", precision=5)
	private Integer indexNumber;

	public HR036P() {
		super();
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;		
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;		
	}
	
	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;		
	}
	
	public Integer getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(Integer indexNumber) {
		this.indexNumber = indexNumber;		
	}
	
	@Override
	public String toString() {
		return "employeeId = " + employeeId + "\n"
				+ "employeeName = " + employeeName + "\n"
				+ "employeeNumber = " + employeeNumber + "\n"
				+ "indexNumber = " + indexNumber + "\n";
	}

	/**
	 */
	public static HR036P findByEmployeeNumberAndIndexNumber(Integer employeeNumber, Integer indexNumber) {
		logger.debug("findByEmployeeNumberAndIndexNumber() ***");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<HR036P> resultList = em.createQuery(
	    		    "SELECT h FROM HR036P h "
	    		    		+ "WHERE h.employeeNumber = :employeeNumber "
	    		    		+ "AND h.indexNumber = :indexNumber ", HR036P.class)
	    		    .setParameter("employeeNumber", employeeNumber)
	    		    .setParameter("indexNumber", indexNumber)
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
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

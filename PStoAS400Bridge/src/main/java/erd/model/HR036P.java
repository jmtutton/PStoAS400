package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: HR036P
 *
 */
@Entity
@Table(name="HR036P")
@NamedQuery(name="HR036P.findAll", query="SELECT h FROM HR036P h")
public class HR036P implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "H36EMP") //five digit string left padded with zeros
	private String employeeId;
	
	@Column(name = "H36NAM")
	private String employeeName;
	
	@Column(name = "H36EM#")
	private Integer employeeNumber;
	
	@Column(name = "H36INX")
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
	
	@Override
	public String toString() {
		return "employeeId = " + employeeId + "\n"
				+ "employeeName = " + employeeName + "\n"
				+ "employeeNumber = " + employeeNumber + "\n"
				+ "indexNumber = " + indexNumber + "\n";
	}

	/**
	 */
	public static HR036P findByEmployeeIdAndIndexNumber(String employeeId, Integer indexNumber) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<HR036P> resultList = em.createQuery(
	    		    "SELECT h FROM HR036P h "
	    		    		+ "WHERE h.employeeId = :employeeId "
	    		    		+ "AND h.indexNumber = :indexNumber", HR036P.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("indexNumber", indexNumber)
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		return resultList.get(0);
	    	}
	    } 
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
		return null;
	}
   
}

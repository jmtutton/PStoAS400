package erd.model;

import java.io.Serializable;
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
 * The persistent class for the PS_ZHRR_POI_EMP_VW database table.
 * Cross-Reference of Company and Business Unit to Legacy Group and Legacy Region
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRR_POI_EMP_VW")
@NamedQuery(name="CrossReferencePersonOfInterestEmployee.findAll", query="SELECT p FROM CrossReferencePersonOfInterestEmployee p")
public class CrossReferencePersonOfInterestEmployee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="NAME", nullable=false, length=50)
	private String name;

	@Column(name="Z_PER_TYPE", length=1)
	private String type;

	/**
	 * @see HR201-Get-Emp-POI
	 * @param employeeId
	 * @return type
	 */
	public static String findTypeByEmployeeId(String employeeId) {
		//SELECT MUL.Z_PER_TYPE
		//FROM PS_ZHRR_POI_EMP_VW MUL
		//WHERE MUL.EMPLID = $EmplId         
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    			"SELECT c.type FROM CrossReferencePersonOfInterestEmployee c "
	    					+ "WHERE UPPER(TRIM(c.employeeId)) = :employeeId "
	    			, String.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
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

package erd.model;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The persistent class for the PS_ZHRT_EMPID_CREF database table.
 * Cross-Reference of Employee ID to Legacy Employee ID
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRT_EMPID_CREF")
@NamedQuery(name="CrossReferenceEmployeeId.findAll", query="SELECT c FROM CrossReferenceEmployeeId c")
public class CrossReferenceEmployeeId implements Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="ZHRF_LEG_EMPL_ID", nullable=false, length=5)
	private String legacyEmployeeId;

	public CrossReferenceEmployeeId() {
	}

	public CrossReferenceEmployeeId(String employeeId, String legacyEmployeeId) {
		super();
		this.employeeId = employeeId;
		this.legacyEmployeeId = legacyEmployeeId;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getLegacyEmployeeId() {
		return this.legacyEmployeeId;
	}

	public void setLegacyEmployeeId(String legacyEmployeeId) {
		this.legacyEmployeeId = legacyEmployeeId;
	}

	/**
	 * This routine finds the Legacy Employee ID from Employee CREF Table for the given Employee ID
	 * @see Get-LegId-For-Seq0 procedure in ZHRI100A.SQR
	 * @see HR05-Get-Next-OpId in ZHRI105A.SQC
	 * @see AD-Get-LegSupervisorId procedure in ZHRI100A.SQR
	 * @param employeeId
	 * @return legacyEmployeeId
	 */
	public static String findLegacyEmployeeIdByEmployeeId(String employeeId) {
		logger.debug("*** CrossReferenceEmployeeId.()");
		//SELECT ZHRF_LEG_EMPL_ID
		//FROM PS_ZHRT_EMPID_CREF
		//WHERE RPOD.Emplid = $Wrk_Emplid         
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    			"SELECT UPPER(TRIM(c.legacyEmployeeId)) FROM CrossReferenceEmployeeId c "
	    					+ "WHERE UPPER(TRIM(c.employeeId)) = UPPER(TRIM(:employeeId)) "
	    					, String.class)
	    		    .setParameter("employeeId", employeeId)
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

	/**
	 * @see Insert-OprId procedure in ZHRI100A.SQR
	 * @param employeeId
	 * @param legacyEmployeeId
	 */
	public static void insert(String employeeId, String legacyEmployeeId) {
		logger.debug("*** CrossReferenceEmployeeId.()");
		//INSERT INTO PS_ZHRT_EMPID_CREF (EMPLID, ZHRF_LEG_EMPL_ID) VALUES ($Wrk_Emplid, $LegEmplId)
		employeeId = employeeId != null ? employeeId.trim().toUpperCase() : employeeId;
		legacyEmployeeId = legacyEmployeeId != null ? legacyEmployeeId.trim().toUpperCase() : legacyEmployeeId;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	CrossReferenceEmployeeId xrefEmployeeId = new CrossReferenceEmployeeId(employeeId, legacyEmployeeId);
	    	em.getTransaction().begin();
	    	em.persist(xrefEmployeeId);
	    	em.flush();
	    	em.getTransaction().commit();	   
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	}

}
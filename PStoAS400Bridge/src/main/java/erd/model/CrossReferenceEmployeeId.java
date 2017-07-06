package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

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
	 * @see Get-LegId-For-Seq0 procedure in ZHRI100A.SQR
	 * This routine gets the Legacy ID from Employee CREF Table for Primary EIDs
	 */
	public static String ZHRI100A_getLegIdForSeq0(String employeeId) {
//		BEGIN-SELECT
//		RPOD.ZHRF_LEG_EMPL_ID
//		    LET $PSOprid = &RPOD.ZHRF_LEG_EMPL_ID
//		    LET $Found = 'Y'
//		FROM PS_ZHRT_EMPID_CREF RPOD
//		WHERE RPOD.Emplid = $Wrk_Emplid         
//		END-SELECT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    			"SELECT UPPER(TRIM(c.legacyEmployeeId)) FROM CrossReferenceEmployeeId c "
	    					+ "WHERE UPPER(TRIM(c.employeeId)) = :employeeId ", String.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
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
	 * This routine will insert a row into the PS_ZHRT_EMPID_CREF table for the employee if the employee has a record in HR006P
	 */
	public static void ZHRI100A_insertOprId(String employeeId, String legacyEmployeeId) {
//		LET $Insert-Error-Flag = 'N'
//		!Add to the PS_ZHRT_EMPID_CREF table
//		Begin-SQL On-Error = Insert-Error
//			INSERT INTO PS_ZHRT_EMPID_CREF (EMPLID, ZHRF_LEG_EMPL_ID) VALUES ($Wrk_Emplid, $LegEmplId)
//		End-Sql
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	CrossReferenceEmployeeId xrefEmployeeId = new CrossReferenceEmployeeId(employeeId, legacyEmployeeId);
	    	em.getTransaction().begin();
	    	em.persist(xrefEmployeeId);
	    	em.getTransaction().commit();	   
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	}

	/**
	 * @see AD-Get-LegSupervisorId procedure in ZHRI100A.SQR
	 * Gets ZHRF_LEG_EMPL_ID from PS_ZHRT_EMPID_CREF
	 */
	public static String ZHRI100A_getLegSupervisorID(String employeeId) {
//		begin-select
//		PS_ZHRT_EMPID_CREF.ZHRF_LEG_EMPL_ID
//		LET $ADLegSupervisorID = &PS_ZHRT_EMPID_CREF.ZHRF_LEG_EMPL_ID
//		FROM PS_ZHRT_EMPID_CREF PS_ZHRT_EMPID_CREF
//		WHERE PS_ZHRT_EMPID_CREF.Emplid = $ADSupervisorID
//		end-select
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    			"SELECT UPPER(TRIM(c.legacyEmployeeId)) FROM CrossReferenceEmployeeId c "
	    					+ "WHERE UPPER(TRIM(c.employeeId)) = :employeeId ", String.class)
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
	
	/**
	 * This routine gets the legacyEmployeeId for the given employeeIdr.
	 * @see HR05-Get-Next-OpId in ZHRI105A.SQC
	 * @return legacyEmployeeId
	 */
	public static String findLegacyEmployeeIdByEmployeeId(String employeeId) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    			"SELECT UPPER(TRIM(c.legacyEmployeeId)) FROM CrossReferenceEmployeeId c "
	    					+ "WHERE UPPER(TRIM(c.employeeId)) = :employeeId ", String.class)
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
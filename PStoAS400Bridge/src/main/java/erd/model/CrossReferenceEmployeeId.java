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
@NamedQuery(name="CrossReferenceEmployeeId.findAll", query="SELECT p FROM CrossReferenceEmployeeId p")
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
	 * HR05-Get-Next-OpId from ZHRI105A.SQC
	 * This routine gets the operator id for the Recruiter.
	 */
	public CrossReferenceEmployeeId zhri105AGetNextOpId(String employeeId) {
//		Let $Found = 'N'
//		begin-select
//		COD.ZHRF_LEG_EMPL_ID
//		COD.Emplid
//		  Let $PSRecruiter_Id = &COD.ZHRF_LEG_EMPL_ID
//		  Let $Found = 'Y'
//		from PS_ZHRT_EMPID_CREF COD
//		where COD.Emplid = $PSResponsible_Id
//		end-select
//		 if ($Found = 'N')
//		     Let $Hld_Wrk_Emplid = $Wrk_Emplid
//		     Let $Hld_LegEmplid = $LegEmplid
//		     Let $Wrk_Emplid = $PSResponsible_Id
//		     Let $LegEmplid = ''
//		     Do Get-Legacy-Oprid                              !From ZHRI100A.SQR
//		     Let $PSRecruiter_ID = $LegEmplid
//		     Let $Wrk_Emplid = $Hld_Wrk_Emplid
//		     Let $LegEmplid = $Hld_LegEmplid
//		 end-if         !Found = 'N'
		return null;
	}

	/**
	 * Get-LegId-For-Seq0 - from ZHRI100A.SQR
	 * This routine gets the Legacy ID from Employee CREF Table for Primary EIDs
	 */
	public static String zhri100AGetLegIdForSeq0(String employeeId) {
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
	    					+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId ", String.class)
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
	 * Insert-OprId from ZHRI100A.SQR
	 * This routine will insert a row into the PS_ZHRT_EMPID_CREF table for the employee if the employee has a record in HR006P
	 */
	public void insert(String employeeId, String legacyEmployeeId) {
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
	}

	/**
	 * AD-Get-LegSupervisorId from ZHRI100A.SQR
	 * Gets ZHRF_LEG_EMPL_ID from PS_ZHRT_EMPID_CREF
	 */
	public static String zhri100AADGetLegSupervisorID(String employeeId) {
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
	    					+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId ", String.class)
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
	
	public String findLegacyEmployeeIdByEmployeeId(String employeeId) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    			"SELECT UPPER(TRIM(c.legacyEmployeeId)) FROM CrossReferenceEmployeeId c "
	    					+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId ", String.class)
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

}
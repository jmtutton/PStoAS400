package erd.model;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.math.BigInteger;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the PS_ZHRR_MULTPL_EID database table.
 * Cross-Reference of Non-Person Employee data to Legacy Non-Person Employee data
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRR_MULTPL_EID")
@NamedQuery(name="CrossReferenceMultipleEmployeeId.findAll", query="SELECT c FROM CrossReferenceMultipleEmployeeId c")
public class CrossReferenceMultipleEmployeeId implements Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="\"SEQUENCE\"", nullable=false, precision=38)
	private BigInteger eidIndexNumber;

	@Column(name="ZHRF_LEG_EMPL_ID", nullable=false, length=5)
	private String legacyEmployeeId;

	@Column(name="BUSINESS_UNIT", nullable=false, length=5)
	private String businessUnit;

	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String status;

	@Column(name="EFFDT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="EFFSEQ", nullable=false, precision=38)
	private BigInteger effectiveSequence;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="ZHRF_ALT_EID_TYPE", nullable=false, length=2)
	private String altEidType;

	@Column(name="ZHRF_BRANCH", nullable=false, length=2)
	private String branch;

	@Column(name="ZHRF_GRP_NBR", nullable=false, length=2)
	private String group;

	public CrossReferenceMultipleEmployeeId() {
	}

	public String getBusinessUnit() {
		return this.businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
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

	public BigInteger getEffectiveSequence() {
		return this.effectiveSequence;
	}

	public void setEffectiveSequence(BigInteger effectiveSequence) {
		this.effectiveSequence = effectiveSequence;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Timestamp getLastUpdatedDateAndTime() {
		return this.lastUpdatedDateAndTime;
	}

	public void setLastUpdatedDateAndTime(Timestamp lastUpdatedDateAndTime) {
		this.lastUpdatedDateAndTime = lastUpdatedDateAndTime;
	}

	public String getLastUpdatedUserId() {
		return this.lastUpdatedUserId;
	}

	public void setLastUpdatedUserId(String lastUpdatedUserId) {
		this.lastUpdatedUserId = lastUpdatedUserId;
	}

	public BigInteger getEidIndexNumber() {
		return this.eidIndexNumber;
	}

	public void setEidIndexNumber(BigInteger eidIndexNumber) {
		this.eidIndexNumber = eidIndexNumber;
	}

	public String getAltEidType() {
		return this.altEidType;
	}

	public void setAltEidType(String altEidType) {
		this.altEidType = altEidType;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getLegacyEmployeeId() {
		return this.legacyEmployeeId;
	}

	public void setLegacyEmployeeId(String legacyEmployeeId) {
		this.legacyEmployeeId = legacyEmployeeId;
	}


	/**
	 * @see HR205-Get-Emp-Data in ZHRI205A.SQC
	 * Gets the employees data from the POI table that needs to be interfaced to the legacy system
	 * @param employeeId
	 */
	public CrossReferenceMultipleEmployeeId HR205_getEmpData(String employeeId, BigInteger eidIndexNumber, Date effectiveDate) {
		logger.debug("HR205_getEmpData() ***");
		//SELECT
		//FROM PS_ZHRR_MULTPL_EID C
		//WHERE C.EMPLID = $EmplId
		//AND C.SEQUENCE = #IndexNum
		//AND C.EFFDT = (SELECT MAX(C2.EFFDT) FROM PS_ZHRR_MULTPL_EID C2
		//                WHERE C2.EMPLID = C.EMPLID
		//                AND to_char(C2.EFFDT,'YYYY-MM-DD') <= $EffDt)
		return null;
	}

	public CrossReferenceMultipleEmployeeId getMinEffDtEmp(String employeeId, BigInteger eidIndexNumber, Date effectiveDate) {
		logger.debug("getMinEffDtEmp() ***");
		//SELECT MIN(C.EFFDT)
		//FROM PS_ZHRR_MULTPL_EID C
		//WHERE C.EMPLID = $EmplId
		//AND C.SEQUENCE = #IndexNum
		//AND C.EFFDT > $EffDt
		return null;
	}

	public CrossReferenceMultipleEmployeeId getLastInactiveDtEmp(String employeeId, BigInteger eidIndexNumber, Date maxEffectiveDate) {
		logger.debug("getLastInactiveDtEmp() ***");
		//SELECT C.EFFDT
		//FROM PS_ZHRR_MULTPL_EID C
		//WHERE C.emplid = $EmplId
		//AND C.SEQUENCE = #IndexNum
		//AND C.EFFDT = 
				//(SELECT MAX(C2.EFFDT) FROM PS_ZHRR_MULTPL_EID C2 
				//WHERE C2.EMPLID = C.EMPLID AND C2.SEQUENCE = C.SEQUENCE 
				//AND C2.EFF_STATUS = 'I' AND to_char(C2.EFFDT,'YYYY-MM-DD') < $MaxEffDt)
		return null;
	}

	/**
	 * @see HR202-Get-Term-Date in ZHRI202A.SQC
	 * Gets the term date for POI/EMP
	 * @param employeeId
	 */
	public CrossReferenceMultipleEmployeeId HR202_getTermDate(String employeeId, Date effectiveDate) {
		logger.debug("HR202_getTermDate() ***");
		//SELECT P.EFFDT
		//FROM PS_ZHRT_PER_POI_TR P
		//WHERE P.EMPLID =  $EmplId
		//AND P.EFFDT = (SELECT MAX(P2.EFFDT) FROM PS_ZHRT_PER_POI_TR P2
		//WHERE P2.EMPLID = P.EMPLID
		//AND P2.POI_TYPE = P.POI_TYPE
		//AND to_char(P2.EFFDT,'YYYY-MM-DD') <= $EffDt)
		//
		//SELECT C.EFFDT
		//FROM PS_ZHRR_MULTPL_EID C
		//WHERE C.EMPLID = $EmplId
		//AND C.SEQUENCE = #IndexNum
		//AND C.EFFDT = (SELECT MAX(C2.EFFDT) FROM PS_ZHRR_MULTPL_EID C2
		//WHERE C2.EMPLID = C.EMPLID
		//AND TO_CHAR(C2.EFFDT,'YYYY-MM-DD') <= $EffDt)
		return null;
	}

	/**
	 * @see HR201-Get-Emp-Data in ZHRI201A.SQC
	 * Gets the employees data from the POI table that needs to be interfaced to the legacy system
	 * @param employeeId
	 */
	public CrossReferenceMultipleEmployeeId HR201_getEmpData(String employeeId, BigInteger eidIndexNumber, Date effectiveDate) {
		logger.debug("HR201_getEmpData() ***");
		//BEGIN-SELECT
		//FROM PS_ZHRR_MULTPL_EID C
		//WHERE C.EMPLID = $EmplId
		//AND C.SEQUENCE = #IndexNum
		//AND C.EFFDT = (SELECT MAX(C2.EFFDT) FROM PS_ZHRR_MULTPL_EID C2
		//WHERE C2.EMPLID = C.EMPLID
		//AND TO_CHAR(C2.EFFDT,'YYYY-MM-DD') <= $EffDt)
		return null;
	}


	/**
	 * @see Get-LegId-For-SeqNum in ZHRI102A.SQR
	 * This routine gets the Legacy ID from Alternate EID Table
	 * @param employeeId
	 * @param eidIndexNumber
	 */
	public static String findLegacyEmployeeIdByEmployeeIdAndEidIndexNumber(String employeeId, Integer eidIndexNumber) {
		logger.debug("findLegacyEmployeeIdByEmployeeIdAndSequence() ***");
		//SELECT C.ZHRF_LEG_EMPL_ID
		//FROM PS_ZHRR_MULTPL_EID C
		//WHERE C.EMPLID = $EmplId AND C.SEQUENCE = #IndexNum      
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    			"SELECT UPPER(TRIM(c.legacyEmployeeId)) FROM CrossReferenceMultipleEmployeeId c "
	    					+ "WHERE UPPER(TRIM(c.employeeId)) = UPPER(TRIM(:employeeId)) "
	    					+ "AND c.eidIndexNumber = :eidIndexNumber"
	    			, String.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("eidIndexNumber", eidIndexNumber)
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
	 * @see Update-OprId in ZHRI100A.SQR
	 * This routine will UPDATE table PS_ZHRR_MULTPL_EID for the Non Employees and Multiple EIDs if the employee has a record in HR036P
	 * @param employeeId
	 * @param legacyEmployeeId
	 * @param eidIndexNumber
	 */
	public static void ZHRI100A_updateOprId(String employeeId, String legacyEmployeeId, Integer eidIndexNumber) {
		logger.debug("ZHRI100A_updateOprId() ***");
		//UPDATE PS_ZHRR_MULTPL_EID
		//SET ZHRF_LEG_EMPL_ID = $LegEmplId 
		//WHERE EMPLID = $EmplId AND SEQUENCE = #IndexNum
		legacyEmployeeId = legacyEmployeeId != null ? legacyEmployeeId.trim().toUpperCase() : legacyEmployeeId;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<CrossReferenceMultipleEmployeeId> resultList = em.createQuery(
	    			"SELECT c FROM CrossReferenceMultipleEmployeeId c "
	    					+ "WHERE UPPER(TRIM(c.employeeId)) = UPPER(TRIM(:employeeId)) "
	    					+ "AND c.eidIndexNumber = :eidIndexNumber", CrossReferenceMultipleEmployeeId.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("eidIndexNumber", eidIndexNumber)
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		CrossReferenceMultipleEmployeeId xrefEmployeeId = resultList.get(0);
		    	xrefEmployeeId.setLegacyEmployeeId(legacyEmployeeId);
		    	EntityTransaction entityTransaction = em.getTransaction();
		    	entityTransaction.begin();
		    	em.persist(xrefEmployeeId);
		    	entityTransaction.commit();
	    	}
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
	}

	public static Date findEffectiveDateByEmployeeIdAndEidIndexNumber(String employeeId, Integer eidIndexNumber) {
		logger.debug("findEffectiveDateByEmployeeIdAndSequence() ***");
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Insert-OprId in ZHRI100A.SQR
	 * This routine will insert a row into the PS_ZHRT_EMPID_CREF table for the employee if the employee has a record in HR006P
	 * @param employeeId
	 * @param legacyEmployeeId
	 */
	public static void ZHRI100A_insertOprId(String employeeId, String legacyEmployeeId) {
		logger.debug("ZHRI100A_insertOprId() ***");
		//INSERT INTO PS_ZHRT_EMPID_CREF (EMPLID, ZHRF_LEG_EMPL_ID) VALUES ($EmplId, $LegEmplId)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		employeeId = employeeId != null ? employeeId.toUpperCase().trim() : employeeId;
		legacyEmployeeId = legacyEmployeeId != null ? legacyEmployeeId.toUpperCase().trim() : legacyEmployeeId;
	    try {
    		CrossReferenceMultipleEmployeeId xrefEmployeeId = new CrossReferenceMultipleEmployeeId();
	    	xrefEmployeeId.setEmployeeId(employeeId);
	    	xrefEmployeeId.setLegacyEmployeeId(legacyEmployeeId);
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
	 * Inserts or updates an operator ID record in the legacy table, for the case where a legacy ID was not found. 
	 * @see Get-Legacy-OprId in ZHRI100A.SQR
	 * @param employeeId
	 * @param legacyEmployeeId
	 * @param indexNumber
	 */
	public static void saveNewLegacyEmployeeId(String employeeId, String legacyEmployeeId, Integer eidIndexNumber) {
		logger.debug("saveNewLegacyEmployeeId() ***");
    	if(eidIndexNumber == 0) {
    		CrossReferenceMultipleEmployeeId.ZHRI100A_insertOprId(employeeId, legacyEmployeeId);
    	}
    	else {
    		CrossReferenceMultipleEmployeeId.ZHRI100A_updateOprId(employeeId, legacyEmployeeId, eidIndexNumber);
    	}
	}

	public static CrossReferenceMultipleEmployeeId findByEmployeeIdAndEidIndexNumberAndEffectiveDate(String employeeId, BigInteger eidIndexNumber, Date effectiveDate) {
		//SELECT FROM PS_ZHRR_MULTPL_EID C
		//WHERE C.EMPLID = $EmplId
		//AND C.SEQUENCE = #IndexNum
		//AND C.EFFDT = 
				//(SELECT MAX(C2.EFFDT) FROM PS_ZHRR_MULTPL_EID C2
				//WHERE C2.EMPLID = C.EMPLID
				//AND to_char(C2.EFFDT,'YYYY-MM-DD') <= $EffDt)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<CrossReferenceMultipleEmployeeId> resultList = em.createQuery(
	    			"SELECT c FROM CrossReferenceMultipleEmployeeId c "
	    					+ "WHERE UPPER(TRIM(c.employeeId)) = UPPER(TRIM(:employeeId)) "
	    					+ "AND c.eidIndexNumber = :eidIndexNumber "
	    					+ "AND c.effectiveDate = "
	    							+ "(SELECT MAX(c2.effectiveDate) FROM CrossReferenceMultipleEmployeeId c2 "
	    							+ "WHERE UPPER(TRIM(c2.employeeId)) = UPPER(TRIM(c.employeeId)) "
	    							+ "AND c2.effectiveDate <= :effectiveDate) "
	    			, CrossReferenceMultipleEmployeeId.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("eidIndexNumber", eidIndexNumber)
					.setParameter("effectiveDate", effectiveDate, TemporalType.DATE)
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
	 * @see ErdUtil
	 * @see Main-Sql-Poi in ZHRISTDT.SQC PeopleCode file
	 * @param employeeId
	 * @param eidIndexNumber
	 * @return maximum effective date for employee ID and index.
	 */
	public static Date findMaxEffectiveDateByEmployeeIdAndEidIndexNumber(String employeeId, BigInteger eidIndexNumber) {
		//SELECT C.EFFDT
		//FROM PS_ZHRR_MULTPL_EID C
		//WHERE C.EMPLID = $EmplId
		//AND C.SEQUENCE = #IndexNum
		//AND C.EFFDT = 
				//(SELECT MAX(C2.EFFDT) FROM PS_ZHRR_MULTPL_EID C2
				//WHERE C2.EMPLID = C.EMPLID AND C2.SEQUENCE = C.SEQUENCE AND C2.EFFDT <= SYSDATE)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<Date> resultList = em.createQuery(
	    			"SELECT c.effectiveDate FROM CrossReferenceMultipleEmployeeId c "
	    					+ "WHERE UPPER(TRIM(c.employeeId)) = UPPER(TRIM(:employeeId)) "
	    					+ "AND c.eidIndexNumber = :eidIndexNumber "
	    					+ "AND c.effectiveDate = "
	    							+ "(SELECT MAX(c2.effectiveDate) FROM CrossReferenceMultipleEmployeeId c2 "
	    							+ "WHERE UPPER(TRIM(c2.employeeId)) = UPPER(TRIM(c.employeeId)) "
	    							+ "WHERE c2.eidIndexNumber = c.eidIndexNumber "
	    							+ "AND c2.effectiveDate <= CURRENT_DATE) "
	    			, Date.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("eidIndexNumber", eidIndexNumber)
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
	 * @see ErdUtil
	 * @see Get-Last-Inactive-Dt-Emp in ZHRISTDT.SQC PeopleCode file
	 * @param employeeId
	 * @param eidIndexNumber
	 * @param maxEffectiveDate
	 * @return last inactive effective date for employee ID and index.
	 */
	public static Date findLastInactiveDateByEmployeeIdAndEidIndexNumberAndMaxEffectiveDate(String employeeId, BigInteger eidIndexNumber, Date maxEffectiveDate) {
		//SELECT C.EFFDT
		//FROM PS_ZHRR_MULTPL_EID C
		//WHERE C.EMPLID = $EmplId
		//AND C.SEQUENCE = #IndexNum
		//AND C.EFFDT = 
				//(SELECT MAX(C2.EFFDT) FROM PS_ZHRR_MULTPL_EID C2 
				//WHERE C2.EMPLID = C.EMPLID AND C2.SEQUENCE = C.SEQUENCE 
				//AND C2.EFF_STATUS = 'I' AND to_char(C2.EFFDT,'YYYY-MM-DD') < $MaxEffDt)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<Date> resultList = em.createQuery(
	    			"SELECT c.effectiveDate FROM CrossReferenceMultipleEmployeeId c "
	    					+ "WHERE UPPER(TRIM(c.employeeId)) = UPPER(TRIM(:employeeId)) "
	    					+ "AND c.eidIndexNumber = :eidIndexNumber "
	    					+ "AND c.effectiveDate = "
	    							+ "(SELECT MAX(c2.effectiveDate) FROM CrossReferenceMultipleEmployeeId c2 "
	    							+ "WHERE UPPER(TRIM(c2.employeeId)) = UPPER(TRIM(c.employeeId)) "
	    							+ "WHERE c2.eidIndexNumber = c.eidIndexNumber "
	    							+ "AND c2.status = 'I' "
	    							+ "AND c2.effectiveDate < :maxEffectiveDate) "
	    			, Date.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("eidIndexNumber", eidIndexNumber)
	    		    .setParameter("maxEffectiveDate", maxEffectiveDate, TemporalType.DATE)
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
	 * @see ErdUtil
	 * @see Get-Min-EffDt-Emp in ZHRISTDT.SQC PeopleCode file
	 * @param employeeId
	 * @param eidIndexNumber
	 * @param lastInactiveDate
	 * @return minimum effective date for last inactive effective date, employee ID, and index.
	 */
	public static Date findMinEffectiveDateByEmployeeIdAndEidIndexNumberAndLastInactiveDate(String employeeId,
			BigInteger eidIndexNumber, Date lastInactiveDate) {
		//SELECT MIN(C.EFFDT) FROM PS_ZHRR_MULTPL_EID C
		//WHERE C.EMPLID = $EmplId AND C.SEQUENCE = #IndexNum AND C.EFFDT > $EffDt
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<Date> resultList = em.createQuery(
	    			"SELECT MIN(c.effectiveDate) FROM CrossReferenceMultipleEmployeeId c "
	    					+ "WHERE UPPER(TRIM(c.employeeId)) = UPPER(TRIM(:employeeId)) "
	    					+ "AND c.eidIndexNumber = :eidIndexNumber "
	    					+ "AND c.effectiveDate > :lastInactiveDate "
	    			, Date.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("eidIndexNumber", eidIndexNumber)
	    		    .setParameter("lastInactiveDate", lastInactiveDate, TemporalType.DATE)
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
package erd.model;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entity implementation class for Entity: HR100P
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="HR100P")
@NamedQuery(name="HR100P.findAll", query="SELECT h FROM HR100P h")
public class HR100P implements Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	
	@Id
	@Column(name="HR0EMP", length=5, unique=true, nullable=false) //five digit string left padded with zeros
	private String employeeId;

	@Column(name="HR0PGM", length=10, unique=true)
	private String progWithError;
	
	@Column(name="HR0EM#", precision=11, unique=true)
	private Integer alternateEmployeeId;
	
	@Column(name="HR0MSG", length=75, unique=true)
	private String actualErroressage;
	
	@Column(name="HR0CRF", length=1)
//	private Boolean isCriticalField;
	private String isCriticalField;

	@Column(name="HR0ADT", unique=true, length=8) //YYYYMMDD
//	@Temporal(TemporalType.DATE)
//	private Date addedDate;
	private String addedDate;
	
	@Column(name="HR0ATM", precision=6, unique=true)
	private Integer addedTime;
	
	@Column(name="HR0EAD", length=5)
	private String addedByEmployeeId; //??

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getProgWithError() {
		return progWithError;
	}

	public void setProgWithError(String progWithError) {
		this.progWithError = progWithError;
	}

	public Integer getAlternateEmployeeId() {
		return alternateEmployeeId;
	}

	public void setAlternateEmployeeId(Integer alternateEmployeeId) {
		this.alternateEmployeeId = alternateEmployeeId;
	}

	public String getActualErroressage() {
		return actualErroressage;
	}

	public void setActualErroressage(String actualErroressage) {
		this.actualErroressage = actualErroressage;
	}

	public String getIsCriticalField() {
		return isCriticalField;
	}

	public void setIsCriticalField(String isCriticalField) {
		this.isCriticalField = isCriticalField;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public Integer getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(Integer addedTime) {
		this.addedTime = addedTime;
	}

	public String getAddedByEmployeeId() {
		return addedByEmployeeId;
	}

	public void setAddedByEmployeeId(String addedByEmployeeId) {
		this.addedByEmployeeId = addedByEmployeeId;
	}

	/**
	 * 
	 * @param employeeId
	 * @param changedDate
	 * @param changedByProgramJob
	 * @param changedByEmployeeId
	 * @return TempMast record
	 */
	public static HR100P findByEmployeeIdAndAddedDateAndChangedByProgWithErrorAndAddedByEmployeeId(
			String employeeId, String addedDate, String progWithError, String addedByEmployeeId) {
		logger.debug("findByEmployeeIdAndAddedDateAndChangedByProgWithErrorAndAddedByEmployeeId() ***");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<HR100P> resultList = em.createQuery(
	    		    "SELECT h FROM HR100P h "
	    		    		+ "WHERE TRIM(UPPER(h.employeeId)) = TRIM(UPPER(:employeeId)) "
	    		    		+ "AND TRIM(UPPER(h.addedDate)) = TRIM(UPPER(:addedDate)) "
	    		    		+ "AND TRIM(UPPER(h.progWithError)) = TRIM(UPPER(:progWithError)) "
	    		    		+ "AND TRIM(UPPER(h.addedByEmployeeId)) = TRIM(UPPER(:addedByEmployeeId)) "
	    		    		, HR100P.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("addedDate", addedDate)
	    		    .setParameter("progWithError", progWithError)
	    		    .setParameter("addedByEmployeeId", addedByEmployeeId)
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

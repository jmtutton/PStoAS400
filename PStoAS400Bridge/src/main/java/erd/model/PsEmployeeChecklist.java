package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the PS_EMPL_CHECKLIST database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_EMPL_CHECKLIST")
@NamedQuery(name="PsEmployeeChecklist.findAll", query="SELECT p FROM PsEmployeeChecklist p")
public class PsEmployeeChecklist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="CHECKLIST_CD", nullable=false, length=6)
	private String checklistCode;

	@Column(name="CHECKLIST_DT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date checklistDate;

	@Lob
	@Column(name="COMMENTS")
	private String comments;

	@Column(name="EMPL_RCD", nullable=false, precision=38)
	private BigInteger employmentRecordNumber;

	@Column(name="RESPONSIBLE_ID", nullable=false, length=11)
	private String responsibleId;

	public PsEmployeeChecklist() {
	}

	public String getChecklistCode() {
		return this.checklistCode;
	}

	public void setChecklistCode(String checklistCode) {
		this.checklistCode = checklistCode;
	}

	public Date getChecklistDate() {
		return this.checklistDate;
	}

	public void setChecklistDate(Date checklistDate) {
		this.checklistDate = checklistDate;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigInteger getEmploymentRecordNumber() {
		return this.employmentRecordNumber;
	}

	public void setEmploymentRecordNumber(BigInteger employmentRecordNumber) {
		this.employmentRecordNumber = employmentRecordNumber;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getResponsibleId() {
		return this.responsibleId != null ? this.responsibleId.trim().toUpperCase() : this.responsibleId;
	}

	public void setResponsibleId(String responsibleId) {
		this.responsibleId = responsibleId;
	}

	/**
	 * This routine gets the Responsible Id from the Employee checklist table.
	 * This field is used to populate the Recruiter Id field in AAHR05.
	 * @see  HR05-Get-Employee-Checklist in ZHRI105A.SQC
	 * @return responsibleId
	 */
	public static String findByEmployeeIdAndChecklistDate(String employeeId, Date checklistDate) {
		//BEGIN-SELECT
		//CECT.Responsible_Id
		//FROM PS_Empl_Checklist CECT
		//WHERE CECT.Emplid = $PSEmplid
		//AND TO_CHAR(CECT.Checklist_Dt,'YYYY-MM-DD') = $PSEffdt
		//END-SELECT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    			"SELECT UPPER(TRIM(c.responsibleId)) FROM PsEmployeeChecklist c "
	    					+ "WHERE UPPER(TRIM(c.employeeId)) = :employeeId "
	    					+ "AND checklistDate = :checklistDate "
	    					, String.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .setParameter("checklistDate", checklistDate, TemporalType.DATE)
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
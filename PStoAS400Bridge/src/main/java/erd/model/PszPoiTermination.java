package erd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the PS_ZHRT_POI_TERM database table.
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRT_POI_TERM")
@NamedQuery(name="PszPoiTermination.findAll", query="SELECT p FROM PszPoiTermination p")
public class PszPoiTermination implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="PROC_NAME", nullable=false, length=11)
	private String processName;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_DATETIME", nullable=false, length=11)
	private Date updatedDateTime;

	public PszPoiTermination() {
	}

	private void setEmployeeId(String employeeId) {
		this.employeeId = employeeId != null ? employeeId.toUpperCase().trim() : employeeId;
	}
	private void setProcessName(String processName) {
		this.processName = processName != null ? processName.toUpperCase().trim() : processName;
	}
	private void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	/**
	 * HR202-Insert-Timestamp from ZHRI202A.SQC
	 * Inserts the EMP and Timestamp for the POI Term
	 */
	public static void HR202_insertTimestamp(String employeeId) {
		//BEGIN-PROCEDURE HR202-INSERT-TIMESTAMP
		//DO GET-CURRENT-DATETIME  !Gets the current date and time using curdttim.sqc
		//BEGIN-SQL  
		//INSERT INTO PS_ZHRT_POI_TERM (EMPLID, PROC_NAME, UPDATED_DATETIME)
		//VALUES ( $Wrk_Emplid, 'TERM', $SysDateTime)
		//END-SQL
		//END-PROCEDURE HR202-INSERT-TIMESTAMP		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	PszPoiTermination poiTermination = new PszPoiTermination();
	    	poiTermination.setEmployeeId(employeeId.toUpperCase().trim());
	    	poiTermination.setProcessName("TERM");
	    	poiTermination.setUpdatedDateTime(new Date());
	    	em.getTransaction().begin();
	    	em.persist(poiTermination);
	    	em.getTransaction().commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	}
}
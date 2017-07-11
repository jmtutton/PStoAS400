package erd.model;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.Date;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The persistent class for the PS_ZHRT_POI_TERM database table.
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRT_POI_TERM")
@NamedQuery(name="PszPoiTermination.findAll", query="SELECT p FROM PszPoiTermination p")
public class PszPoiTermination implements Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="PROC_NAME", nullable=false, length=10)
	private String processName;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_DATETIME")
	private Date updatedDateTime;

	public PszPoiTermination() {
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId != null ? employeeId.toUpperCase().trim() : employeeId;
	}
	public void setProcessName(String processName) {
		this.processName = processName != null ? processName.toUpperCase().trim() : processName;
	}
	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	/**
	 * Inserts the EMP and Timestamp for the POI Term
	 * @see HR202-Insert-Timestamp in ZHRI202A.SQC
	 * @param employeeId
	 */
	public static void insertTimestamp(String employeeId) {
		logger.debug("*** PszPoiTermination.insertTimestamp()");
		//INSERT INTO PS_ZHRT_POI_TERM (EMPLID, PROC_NAME, UPDATED_DATETIME)
		//VALUES ( $Wrk_Emplid, 'TERM', $SysDateTime)
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
	    finally {
	    	em.close();
	    }
	}
}
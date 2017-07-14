package erd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_PERSON database table.
 * Employee Record
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_PERSON")
@NamedQuery(name="PsPerson.findAll", query="SELECT p FROM PsPerson p")
public class PsPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="BIRTHCOUNTRY", nullable=false, length=3)
	private String birthCountry;

	@Column(name="BIRTHDATE", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column(name="BIRTHPLACE", nullable=false, length=30)
	private String birthPlace;

	@Column(name="BIRTHSTATE", nullable=false, length=6)
	private String birthState;

	@Column(name="DT_OF_DEATH")
	@Temporal(TemporalType.DATE)
	private Date dateOfDeath;

	@Column(name="LAST_CHILD_UPDDTM")
	@Temporal(TemporalType.DATE)
	private Date lastChildDataObjectUpdateDate;

	public PsPerson() {
	}

	public String getBirthCountry() {
		return this.birthCountry;
	}

	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getBirthState() {
		return this.birthState;
	}

	public void setBirthState(String birthState) {
		this.birthState = birthState;
	}

	public Date getDateOfDeath() {
		return this.dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getLastChildDataObjectUpdateDate() {
		return this.lastChildDataObjectUpdateDate;
	}

	public void setLastChildDataObjectUpdateDate(Date lastChildDataObjectUpdateDate) {
		this.lastChildDataObjectUpdateDate = lastChildDataObjectUpdateDate;
	}

	public static PsPerson findByEmployeeId(String employeeId) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsPerson> resultList = (List<PsPerson>) em.createQuery(
	    			"SELECT p "
	    				+ "FROM PsPerson p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
	    				, PsPerson.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
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
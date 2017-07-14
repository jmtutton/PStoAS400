package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the PS_DRIVERS_LIC database table.
 * Employee Driver’s License Information
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_DRIVERS_LIC")
@NamedQuery(name="PsDriversLicense.findAll", query="SELECT p FROM PsDriversLicense p")
public class PsDriversLicense implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Lob
	@Column(name="COMMENTS")
	private String comments;

	@Column(name="COUNTRY", nullable=false, length=3)
	private String countryIsoAlpha3Code;

	@Column(name="DRIVERS_LIC_NBR", nullable=false, length=20)
	private String driversLicenseNumber;

	@Column(name="EXPIRATN_DT")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	@Column(name="ISSUE_DEST_FRA", nullable=false, length=20)
	private String issueLocation;

	@Column(name="ISSUED_BY_FRA", nullable=false, length=20)
	private String issuedBy;

	@Column(name="LIC_SUSPENDED_SW", nullable=false, length=1)
	private String licenseIsSuspended;

	@Column(name="NUMBER_OF_PTS", nullable=false, precision=38)
	private BigInteger numberOfPoints;

	@Column(name="\"STATE\"", nullable=false, length=6)
	private String state;

	@Column(name="VALID_FROM_DT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date validFromDate;

	@Column(name="VIOLATIONS", nullable=false, precision=38)
	private BigInteger numberOfViolations;

	public PsDriversLicense() {
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCountryIsoAlpha3Code() {
		return this.countryIsoAlpha3Code;
	}

	public void setCountryIsoAlpha3Code(String countryIsoAlpha3Code) {
		this.countryIsoAlpha3Code = countryIsoAlpha3Code;
	}

	public String getDriversLicenseNumber() {
		return this.driversLicenseNumber;
	}

	public void setDriversLicenseNumber(String driversLicenseNumber) {
		this.driversLicenseNumber = driversLicenseNumber;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getIssueLocation() {
		return this.issueLocation;
	}

	public void setIssueLocation(String issueLocation ) {
		this.issueLocation  = issueLocation;
	}

	public String getIssuedBy() {
		return this.issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public String getLicenseIsSuspended() {
		return this.licenseIsSuspended;
	}

	public void setLicenseIsSuspended(String licenseIsSuspended) {
		this.licenseIsSuspended = licenseIsSuspended;
	}

	public BigInteger getNumberOfPoints() {
		return this.numberOfPoints;
	}

	public void setNumberOfPoints(BigInteger numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}

	public String getState() {
		return this.state != null ? this.state.toUpperCase().trim() : this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getValidFromDate() {
		return this.validFromDate;
	}

	public void setValidFromDate(Date validFromDate) {
		this.validFromDate = validFromDate;
	}

	public BigInteger getNumberOfViolations() {
		return this.numberOfViolations;
	}

	public void setNumberOfViolations(BigInteger numberOfViolations) {
		this.numberOfViolations = numberOfViolations;
	}

	/**
	 * @see HR05-Get-Drivers-Lic in ZHRI105A.SQC
	 * @param employeeId
	 * @return PsDriversLicense record
	 */
	public static PsDriversLicense findByEmployeeId(String employeeId) {
		//SELECT FROM PS_DRIVERS_LIC CDL
		//WHERE CDL.EMPLID = $PSEmplid
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsDriversLicense> resultList = em.createQuery(
	    		    "SELECT p FROM PsDriversLicense p "
	    		    		+ "WHERE TRIM(UPPER(p.employeeId)) = UPPER(TRIM(:employeeId)) ", PsDriversLicense.class)
	    		    .setParameter("employeeId", employeeId)
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
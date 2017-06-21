package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the PS_DRIVERS_LIC database table.
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
	private BigDecimal numberOfPoints;

	@Column(name="\"STATE\"", nullable=false, length=6)
	private String state;

	@Column(name="VALID_FROM_DT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date validFromDate;

	@Column(name="VIOLATIONS", nullable=false, precision=38)
	private BigDecimal numberOfViolations;

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

	public BigDecimal getNumberOfPoints() {
		return this.numberOfPoints;
	}

	public void setNumberOfPoints(BigDecimal numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}

	public String getState() {
		return this.state;
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

	public BigDecimal getNumberOfViolations() {
		return this.numberOfViolations;
	}

	public void setNumberOfViolations(BigDecimal numberOfViolations) {
		this.numberOfViolations = numberOfViolations;
	}

	public PsDriversLicense findByEmployeeId(String employeeId) {
//	!----------------------------------------------------------------------
//	! Procedure:  HR05-Get-Drivers-Lic
//	! Desc:  This routine gets the driver license number and state and
//	!        stores them in the legacy system format.
//	!----------------------------------------------------------------------
//	Begin-Procedure HR05-Get-Drivers-Lic
//	begin-select
//	CDL.DRIVERS_LIC_NBR
//	CDL.State
//	   Let $PSDRIVER_LIC = RTRIM(LTRIM(&CDL.DRIVERS_LIC_NBR,' '),' ')
//	   Do Replace-Character($PSDriver_Lic,'''','''''',$PSDriver_Lic)    !From ZRmvSpcChr.sqc
//	   Let $PSDLState = &CDL.State
//	   Uppercase $PSDLState
//	from PS_Drivers_Lic CDL
//	where CDL.Emplid = $PSEmplid
//	End-select
//	End-Procedure HR05-Get-Drivers-Lic
	return null;
}

}
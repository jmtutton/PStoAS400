package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * The persistent class for the PS_EMPLOYMENT database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_EMPLOYMENT")
@NamedQuery(name="PsEmployment.findAll", query="SELECT p FROM PsEmployment p")
public class PsEmployment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="BENEFIT_RCD_NBR", nullable=false, precision=38)
	private BigDecimal benefitRcdNbr;

	@Column(name="BUSINESS_TITLE", nullable=false, length=30)
	private String businessTitle;

	@Column(name="CMPNY_SENIORITY_DT")
	private Date cmpnySeniorityDt;

	@Column(name="EMPL_RCD", nullable=false, precision=38)
	private BigDecimal employmentRecordNumber;

	@Column(name="EXPECTED_RETURN_DT")
	private Date expectedReturnDt;

	@Column(name="HIRE_DT")
	private Date hireDt;

	@Column(name="HOME_HOST_CLASS", nullable=false, length=1)
	private String homeHostClass;

	@Column(name="LAST_DATE_WORKED")
	private Date lastDateWorked;

	@Column(name="LAST_INCREASE_DT")
	private Date lastIncreaseDt;

	@Column(name="LAST_VERIFICATN_DT")
	private Date lastVerificatnDt;

	@Column(name="NEE_PROVIDER_ID", nullable=false, length=10)
	private String neeProviderId;

	@Column(name="OWN_5PERCENT_CO", nullable=false, length=1)
	private String own5percentCo;

	@Column(name="PER_ORG", nullable=false, length=3)
	private String perOrg;

	@Column(name="POSITION_PHONE", nullable=false, length=24)
	private String positionPhone;

	@Column(name="PROBATION_DT")
	private Date probationDt;

	@Column(name="PROF_EXPERIENCE_DT")
	private Date profExperienceDt;

	@Column(name="REHIRE_DT")
	private Date rehireDt;

	@Column(name="REPORTS_TO", nullable=false, length=8)
	private String reportsTo;

	@Column(name="SENIORITY_PAY_DT")
	private Date seniorityPayDt;

	@Column(name="SERVICE_DT")
	private Date serviceDt;

	@Column(name="SUPERVISOR_ID", nullable=false, length=11)
	private String supervisorId;

	@Column(name="TERMINATION_DT")
	private Date terminationDt;

	public PsEmployment() {
	}

	public BigDecimal getBenefitRcdNbr() {
		return this.benefitRcdNbr;
	}

	public void setBenefitRcdNbr(BigDecimal benefitRcdNbr) {
		this.benefitRcdNbr = benefitRcdNbr;
	}

	public String getBusinessTitle() {
		return this.businessTitle;
	}

	public void setBusinessTitle(String businessTitle) {
		this.businessTitle = businessTitle;
	}

	public Date getCmpnySeniorityDt() {
		return this.cmpnySeniorityDt;
	}

	public void setCmpnySeniorityDt(Date cmpnySeniorityDt) {
		this.cmpnySeniorityDt = cmpnySeniorityDt;
	}

	public BigDecimal getEmploymentRecordNumber() {
		return this.employmentRecordNumber;
	}

	public void setEmploymentRecordNumber(BigDecimal employmentRecordNumber) {
		this.employmentRecordNumber = employmentRecordNumber;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getExpectedReturnDt() {
		return this.expectedReturnDt;
	}

	public void setExpectedReturnDt(Date expectedReturnDt) {
		this.expectedReturnDt = expectedReturnDt;
	}

	public Date getHireDt() {
		return this.hireDt;
	}

	public void setHireDt(Date hireDt) {
		this.hireDt = hireDt;
	}

	public String getHomeHostClass() {
		return this.homeHostClass;
	}

	public void setHomeHostClass(String homeHostClass) {
		this.homeHostClass = homeHostClass;
	}

	public Date getLastDateWorked() {
		return this.lastDateWorked;
	}

	public void setLastDateWorked(Date lastDateWorked) {
		this.lastDateWorked = lastDateWorked;
	}

	public Date getLastIncreaseDt() {
		return this.lastIncreaseDt;
	}

	public void setLastIncreaseDt(Date lastIncreaseDt) {
		this.lastIncreaseDt = lastIncreaseDt;
	}

	public Date getLastVerificatnDt() {
		return this.lastVerificatnDt;
	}

	public void setLastVerificatnDt(Date lastVerificatnDt) {
		this.lastVerificatnDt = lastVerificatnDt;
	}

	public String getNeeProviderId() {
		return this.neeProviderId;
	}

	public void setNeeProviderId(String neeProviderId) {
		this.neeProviderId = neeProviderId;
	}

	public String getOwn5percentCo() {
		return this.own5percentCo;
	}

	public void setOwn5percentCo(String own5percentCo) {
		this.own5percentCo = own5percentCo;
	}

	public String getPerOrg() {
		return this.perOrg;
	}

	public void setPerOrg(String perOrg) {
		this.perOrg = perOrg;
	}

	public String getPositionPhone() {
		return this.positionPhone;
	}

	public void setPositionPhone(String positionPhone) {
		this.positionPhone = positionPhone;
	}

	public Date getProbationDt() {
		return this.probationDt;
	}

	public void setProbationDt(Date probationDt) {
		this.probationDt = probationDt;
	}

	public Date getProfExperienceDt() {
		return this.profExperienceDt;
	}

	public void setProfExperienceDt(Date profExperienceDt) {
		this.profExperienceDt = profExperienceDt;
	}

	public Date getRehireDt() {
		return this.rehireDt;
	}

	public void setRehireDt(Date rehireDt) {
		this.rehireDt = rehireDt;
	}

	public String getReportsTo() {
		return this.reportsTo;
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}

	public Date getSeniorityPayDt() {
		return this.seniorityPayDt;
	}

	public void setSeniorityPayDt(Date seniorityPayDt) {
		this.seniorityPayDt = seniorityPayDt;
	}

	public Date getServiceDt() {
		return this.serviceDt;
	}

	public void setServiceDt(Date serviceDt) {
		this.serviceDt = serviceDt;
	}

	public String getSupervisorId() {
		return this.supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	public Date getTerminationDt() {
		return this.terminationDt;
	}

	public void setTerminationDt(Date terminationDt) {
		this.terminationDt = terminationDt;
	}

	/**
	 * AD-Get-Employment-Data from ZHRI100A.SQR
	 * This routine will get the Termination Data row for Active Directory File Build.
	 */
	public static PsEmployment findByEmployeeId(String employeeId) {
//		BEGIN-SELECT
//			TO_CHAR(PS_Employment.HIRE_DT,'YYYY-MM-DD') &ADEHire_Dt
//		  	LET $PSHireYr = SUBSTR(&ADEHire_Dt,1,4)
//		  	LET $PSHireMnth = SUBSTR(&ADEHire_Dt,6,2)
//		  	LET $PSHireDay = SUBSTR(&ADEHire_Dt,9,2)
//		  	LET $PSHireDt = $PSHireYr || $PSHireMnth || $PSHireDay
//			TO_CHAR(PS_Employment.REHIRE_DT,'YYYY-MM-DD') &ADERehire_Dt
//		  	LET $PSRehireYr = SUBSTR(&ADERehire_Dt,1,4)
//		  	LET $PSRehireMnth = SUBSTR(&ADERehire_Dt,6,2)
//		  	LET $PSRehireDay = SUBSTR(&ADERehire_Dt,9,2)
//		  	LET $PSRehireDt = $PSRehireYr || $PSRehireMnth || $PSRehireDay
//			TO_CHAR(PS_Employment.TERMINATION_DT,'YYYY-MM-DD')  &ADETermination_Dt
//		  	LET $PSTermYr = SUBSTR(&ADETermination_Dt,1,4)
//		  	LET $PSTermMnth = SUBSTR(&ADETermination_Dt,6,2)
//		  	LET $PSTermDay = SUBSTR(&ADETermination_Dt,9,2)
//		  	LET $PSTerminationDt =  $PSTermYr || $PSTermMnth || $PSTermDay
//		  	LET $ADSupervisorID = &PS_Employment.SUPERVISOR_ID
//		FROM PS_Employment PS_Employment
//		WHERE PS_Employment.Emplid = $PSEmplid
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsEmployment> resultList = (List<PsEmployment>) em.createQuery("SELECT p FROM PsEmployment p "
	    				+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId ", PsEmployment.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0);
	    	}
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
	}
	
	@Override
	public String toString() {
		return "employeeId: " + getEmployeeId() + "\n" +
				"benefitRcdNbr: " + getBenefitRcdNbr() + "\n" +
				"businessTitle: " + getBusinessTitle() + "\n" +
				"cmpnySeniorityDt: " + getCmpnySeniorityDt() + "\n" +
				"employmentRecordNumber: " + getEmploymentRecordNumber() + "\n" +
				"expectedReturnDt: " + getExpectedReturnDt() + "\n" +
				"hireDt: " + getHireDt() + "\n" +
				"homeHostClass: " + getHomeHostClass() + "\n" +
				"lastDateWorked: " + getLastDateWorked() + "\n" +
				"lastIncreaseDt: " + getLastIncreaseDt() + "\n" +
				"lastVerificatnDt: " + getLastVerificatnDt() + "\n" +
				"neeProviderId: " + getNeeProviderId() + "\n" +
				"own5percentCo: " + getOwn5percentCo() + "\n" +
				"perOrg: " + getPerOrg() + "\n" +
				"positionPhone: " + getPositionPhone() + "\n" +
				"probationDt: " + getProbationDt() + "\n" +
				"profExperienceDt: " + getProfExperienceDt() + "\n" +
				"rehireDt: " + getRehireDt() + "\n" +
				"reportsTo: " + getReportsTo() + "\n" +
				"seniorityPayDt: " + getSeniorityPayDt() + "\n" +
				"serviceDt: " + getServiceDt() + "\n" +
				"supervisorId: " + getSupervisorId() + "\n" +
				"terminationDt: " + getTerminationDt();
	}

}
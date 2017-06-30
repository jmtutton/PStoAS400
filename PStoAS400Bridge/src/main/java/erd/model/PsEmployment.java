package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.text.SimpleDateFormat;
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
	@Temporal(TemporalType.DATE)
	private Date cmpnySeniorityDt;

	@Column(name="EMPL_RCD", nullable=false, precision=38)
	private BigDecimal employmentRecordNumber;

	@Column(name="EXPECTED_RETURN_DT")
	@Temporal(TemporalType.DATE)
	private Date expectedReturnDt;

	@Column(name="HIRE_DT")
	@Temporal(TemporalType.DATE)
	private Date hireDt;

	@Column(name="HOME_HOST_CLASS", nullable=false, length=1)
	private String homeHostClass;

	@Column(name="LAST_DATE_WORKED")
	@Temporal(TemporalType.DATE)
	private Date lastDateWorked;

	@Column(name="LAST_INCREASE_DT")
	@Temporal(TemporalType.DATE)
	private Date lastIncreaseDt;

	@Column(name="LAST_VERIFICATN_DT")
	@Temporal(TemporalType.DATE)
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
	@Temporal(TemporalType.DATE)
	private Date probationDt;

	@Column(name="PROF_EXPERIENCE_DT")
	@Temporal(TemporalType.DATE)
	private Date profExperienceDt;

	@Column(name="REHIRE_DT")
	@Temporal(TemporalType.DATE)
	private Date rehireDt;

	@Column(name="REPORTS_TO", nullable=false, length=8)
	private String reportsTo;

	@Column(name="SENIORITY_PAY_DT")
	@Temporal(TemporalType.DATE)
	private Date seniorityPayDt;

	@Column(name="SERVICE_DT")
	@Temporal(TemporalType.DATE)
	private Date serviceDt;

	@Column(name="SUPERVISOR_ID", nullable=false, length=11)
	private String supervisorId;

	@Column(name="TERMINATION_DT")
	@Temporal(TemporalType.DATE)
	private Date terminationDt;
	
	@Transient
	private String strHiredDt;
	@Transient
	private String strRehiredDt;
	@Transient
	private String strTerminationDtStr;

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
	 * This routine will get the Termination Data row for Active Directory File Build
	 * and convert hiredt, rehiredt, terminationdt to YYYYMMDD format
	 * @see AD-Get-Employment-Data in ZHRI100A.SQR
	 * @param employeeId
	 * @return
	 */
	public PsEmployment findByEmployeeId(String employeeId) {
//		Let $PSHiredt = ' '
//		Let $PSRehiredt = ' '
//		Let $PSTerminationdt = ' '
//		Let $ADSupervisorID = ' '
//		begin-select
//		to_char(ADE.HIRE_DT,'YYYY-MM-DD')       &ADEHire_Dt
//		  Let $PSHireYr = substr(&ADEHire_Dt,1,4)
//		  Let $PSHireMnth = substr(&ADEHire_Dt,6,2)
//		  Let $PSHireDay = substr(&ADEHire_Dt,9,2)
//		  Let $PSHiredt = $PSHireYr || $PSHireMnth || $PSHireDay
//		to_char(ADE.REHIRE_DT,'YYYY-MM-DD')       &ADERehire_Dt
//		  Let $PSRehireYr = substr(&ADERehire_Dt,1,4)
//		  Let $PSRehireMnth = substr(&ADERehire_Dt,6,2)
//		  Let $PSRehireDay = substr(&ADERehire_Dt,9,2)
//		  Let $PSRehiredt = $PSRehireYr || $PSRehireMnth || $PSRehireDay
//		to_char(ADE.TERMINATION_DT,'YYYY-MM-DD')  &ADETermination_Dt
//		  Let $PSTermYr = substr(&ADETermination_Dt,1,4)
//		  Let $PSTermMnth = substr(&ADETermination_Dt,6,2)
//		  Let $PSTermDay = substr(&ADETermination_Dt,9,2)
//		  Let $PSTerminationdt =  $PSTermYr || $PSTermMnth || $PSTermDay
//		ADE.SUPERVISOR_ID
//		  Let $ADSupervisorID = &ADE.SUPERVISOR_ID
//		from PS_Employment ADE
//		where ADE.Emplid = $PSEmplid
//		end-select
//		end-procedure AD-Get-Employment-Data
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsEmployment> resultList = em.createQuery(
	    		    "SELECT p FROM PsEmployment p WHERE p.employeeId = :employeeId ", PsEmployment.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		PsEmployment result = resultList.get(0);
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyymmdd"); 
	    		result.strHiredDt = sdf.format(result.getHireDt());
	    		result.strRehiredDt = sdf.format(result.getRehireDt());
	    		result.strTerminationDtStr = sdf.format(result.getTerminationDt());
	    		return result;
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
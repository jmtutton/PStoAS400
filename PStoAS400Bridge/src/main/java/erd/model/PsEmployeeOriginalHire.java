package erd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the PS_ORIG_HIR_EMP_VW database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ORIG_HIR_EMP_VW")
@NamedQuery(name="PsEmployeeOriginalHire.findAll", query="SELECT p FROM PsEmployeeOriginalHire p")
public class PsEmployeeOriginalHire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="ORIG_HIRE_DT")
	@Temporal(TemporalType.DATE)
	private Date originalHireDate;

	public PsEmployeeOriginalHire() {
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getOriginalHireDate() {
		return this.originalHireDate;
	}

	public void setOriginalHireDate(Date originalHireDate) {
		this.originalHireDate = originalHireDate;
	}

	public static Date findOriginalHireDateByEmployeeId(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR05-Get-Personal-Data
//		! Desc:  This routine will get the Personal Data row for each of the
//		!        employee numbers entered in the trigger file.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR05-Get-Personal-Data
//		begin-select
//		to_char(COHE.ORIG_HIRE_DT, 'YYYY-MM-DD')  &CPD2Orig_Hire_Dt
//		CPD2.Sex
//		to_char(CPD2.Birthdate, 'YYYY-MM-DD')  &CPD2Birthdate
//		CPD2.Lang_Cd
//		  Let $PSGender = &CPD2.Sex
//		  Let $PSBDate = &CPD2Birthdate
//		  Unstring $PSBDate by '-' into $first $second $Third
//		  Let $PSBirthdate = $Second || $Third || $first
//		  IF &CPD2Orig_Hire_Dt = ''
//		    Let $PSStart_Dt = '00000000'
//		  else
//		     Unstring &CPD2Orig_Hire_dt by '-' into $first $second $Third
//		     Let $PSStart_dt = $First || $Second || $Third
//		  end-if
//		  Let $PSLangCd = RTRIM(LTRIM(&CPD2.Lang_Cd,' '),' ')
//		  Let $Wrk_AD_PersDataBuild = 'Y'
//		from PS_Personal_Data CPD2
//		    ,PS_ORIG_HIR_EMP_VW COHE
//		where CPD2.Emplid = $PSEmplid
//		  and COHE.EMPLID = CPD2.EMPLID
//		end-select
//		End-Procedure HR05-Get-Personal-Data
		return null;
	}
	
}
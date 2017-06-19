package erd.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the PS_ZHRT_RFSRC_CREF database table.
 * Cross-Reference of Referral Source to Legacy Recruiter Source
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRT_RFSRC_CREF")
@NamedQuery(name="CrossReferenceReferralSource.findAll", query="SELECT p FROM CrossReferenceReferralSource p")
public class CrossReferenceReferralSource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REFERRAL_SOURCE", nullable=false, length=2)
	private String referralSource;

	@Column(name="STATUS", nullable=false, length=1)
	private String status;

	@Column(name="ZHRF_LEGRECRUITSRC", nullable=false, length=1)
	private String legacyRecruiterSource;

	public CrossReferenceReferralSource() {
	}

	public String getReferralSource() {
		return this.referralSource;
	}

	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLegacyRecruiterSource() {
		return this.legacyRecruiterSource;
	}

	public void setLegacyRecruiterSource(String legacyRecruiterSource) {
		this.legacyRecruiterSource = legacyRecruiterSource;
	}

	public CrossReferenceReferralSource HR01GetReferralSource(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR01-Get-Referral-Source
//		! Desc:  Gets the legacy referral source from the cross reference file
//		!        using the PeopleSoft Referral Source code
//		!----------------------------------------------------------------------
//		Begin-Procedure HR01-Get-Referral-Source
//		Let $Found = 'N'
//		Begin-Select
//		CPT10.ZHRF_LEGRECRUITSRC
//		    Let $LegReferralSource = &CPT10.ZHRF_LEGRECRUITSRC         !Move to a program field
//		    Let $Found = 'Y'
//		from PS_ZHRT_RFSRC_CREF CPT10
//		where CPT10.REFERRAL_SOURCE = $PSReferralSource
//		  and CPT10.STATUS = 'A'
//		End-Select
//		if ($Found = 'N')
//		    If $PSReferralSource = ' '   !If the Referral Source code was not entered in PS
//		       Let $LegReferralSource = ' '
//		       Let $ErrorMessageParm = 'Referral source not selected in PeopleSoft.'
//		       Do Call-Error-Routine
//		    Else
//		       Let $LegReferralSource = 'O'   !If not found default to O (Other)
//		       Let $PSSpecific_Refer_Src = substr($PSRefSourceDescr,1,35)
//		    End-If    !$PSReferralSource = ' '
//		end-if    !$Found = 'N'
//		Let $PSSpecific_Refer_Src = Rpad($PSSpecific_Refer_Src,35,' ')  !Make sure not less than 35 long
//		End-Procedure HR01-Get-Referral-Source
		return null;
	}

	public CrossReferenceReferralSource HR05FormatReferralSource(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR05-Format-Referal-Source
//		! Desc:  This routine converts referral codes from Peoplesoft to legacy
//		!        system.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR05-Format-Referral-Source
//		  Let $Found = 'N'  !Initialize the found indicator
//		 !Based on the value in the Peoplesoft Recruit Source Code assign the
//		 !corresponding legacy system code that will be passed.
//		begin-select
//		CPT101.ZHRF_LEGRECRUITSRC
//		   Let $PSReferral_Source = &CPT101.ZHRF_LEGRECRUITSRC
//		   Let $Found = 'Y'
//		From PS_ZHRT_RFSRC_CREF CPT101
//		Where CPT101.REFERRAL_SOURCE = $PSRecruit_Source_Code
//		  and CPT101.STATUS = 'A'
//		end-select
//		 if ($Found = 'N')
//		     If $PSRecruit_Source_Code = ' '   !If the Referral Source code was not entered in PS
//		        Let $PSReferral_Source = ' '
//		        Let $ErrorMessageParm = 'Referral source not selected in PeopleSoft.'
//		        Do Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//		        Do Call-Error-Routine                 !From ZHRI100A.SQR
//		     Else
//		         Let $PSSpecific_Refer_Src = substr($PSRefSourceDescr,1,35)
//		     End-If    !$PSRecruit_Source_Code = ' '
//		 end-if    !$Found = 'N'
//		Let $PSSpecific_Refer_Src = Rpad($PSSpecific_Refer_Src,35,' ')  !Make sure not less than 35 long
//		End-Procedure HR05-Format-Referral-Source
		return null;
	}

}
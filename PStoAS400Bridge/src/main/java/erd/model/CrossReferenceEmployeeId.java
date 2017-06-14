package erd.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the PS_ZHRT_EMPID_CREF database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZHRT_EMPID_CREF")
@NamedQuery(name="CrossReferenceEmployeeId.findAll", query="SELECT p FROM CrossReferenceEmployeeId p")
public class CrossReferenceEmployeeId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="ZHRF_LEG_EMPL_ID", nullable=false, length=5)
	private String legacyEmployeeId;

	public CrossReferenceEmployeeId() {
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getLegacyEmployeeId() {
		return this.legacyEmployeeId;
	}

	public void setLegacyEmployeeId(String legacyEmployeeId) {
		this.legacyEmployeeId = legacyEmployeeId;
	}

	public CrossReferenceEmployeeId findByEmployeeId(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR05-Get-Next-Opid
//		! Desc:  This routine gets the operator id for the Recruiter.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR05-Get-Next-Opid
//		Let $Found = 'N'
//		begin-select
//		COD.ZHRF_LEG_EMPL_ID
//		COD.Emplid
//		  Let $PSRecruiter_Id = &COD.ZHRF_LEG_EMPL_ID
//		  Let $Found = 'Y'
//		from PS_ZHRT_EMPID_CREF COD
//		where COD.Emplid = $PSResponsible_Id
//		end-select
//		 if ($Found = 'N')
//		     Let $Hld_Wrk_Emplid = $Wrk_Emplid
//		     Let $Hld_LegEmplid = $LegEmplid
//		     Let $Wrk_Emplid = $PSResponsible_Id
//		     Let $LegEmplid = ''
//		     Do Get-Legacy-Oprid                              !From ZHRI100A.SQR
//		     Let $PSRecruiter_ID = $LegEmplid
//		     Let $Wrk_Emplid = $Hld_Wrk_Emplid
//		     Let $LegEmplid = $Hld_LegEmplid
//		 end-if         !Found = 'N'
//		End-Procedure HR05-Get-Next-Opid
		return null;
	}

	public CrossReferenceEmployeeId GetLegIdForSeq0(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  get-LegID-for-seq0
//		! Desc:  This routine gets the Legacy ID from Employee CREF Table for 
//		! Primary EIDs
//		!----------------------------------------------------------------------
//		Begin-Procedure get-LegID-for-seq0
//		begin-select
//		RPOD.ZHRF_LEG_EMPL_ID
//		    Let $PSOprid = &RPOD.ZHRF_LEG_EMPL_ID
//		    Let $Found = 'Y'
//		    show 'CREF $PSOprid: ' $PSOprid
//		from PS_ZHRT_EMPID_CREF RPOD
//		where RPOD.Emplid = $Wrk_Emplid         
//		end-select
//		    show 'CREF $Found: ' $Found
//		End-Procedure get-LegID-for-seq0		
		return null;
	}

	public CrossReferenceEmployeeId InsertOprId(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  Insert-Oprid
//		! Desc:  This routine will insert a row into the PS_ZHRT_EMPID_CREF table for the
//		!        employee if the employee has a record in HR006P
//		!----------------------------------------------------------------------
//		Begin-Procedure Insert-Oprid
//		Let $Insert-Error-Flag = 'N'
//		!Add to the PS_ZHRT_EMPID_CREF table
//		Begin-SQL  On-Error= Insert-Error
//		INSERT INTO PS_ZHRT_EMPID_CREF (
//		EMPLID,
//		ZHRF_LEG_EMPL_ID)
//		VALUES (
//		$Wrk_Emplid,
//		$LegEmplid
//		)
//		End-Sql
//		End-Procedure Insert-Oprid
		return null;
	}

	public CrossReferenceEmployeeId ADGetLegSuperviorID(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  AD-Get-LegSuperviorID
//		!----------------------------------------------------------------------
//		Begin-Procedure AD-Get-LegSuperviorID
//		begin-select
//		RPOD1.ZHRF_LEG_EMPL_ID
//		  Let $ADLegSupervisorID = &RPOD1.ZHRF_LEG_EMPL_ID
//		from PS_ZHRT_EMPID_CREF RPOD1
//		where RPOD1.Emplid = $ADSupervisorID
//		end-select
//		end-procedure AD-Get-LegSuperviorID		
		return null;
	}
	
	public String findLegacyEmployeeIdByEmployeeId(String employeeId) {
		return null;
	}

}
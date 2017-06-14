package erd.controller;

import erd.model.PsActionReason;

/**
 * ZHRI102A – Termination
 * Update the Legacy files which where modified by the HR program ZHRI102A.  
 * Only specific fields will be updated, those which were changed on the AAHR02 screen.
 * Tables Used:
 *        PsJob									PS_Job                       Job Table
 *        PsActionReason						PS_Action Reason             Action Reason Table
 *        CrossReferenceTerminationReason		PS_ZHRT_TRMRS_CREF           Termination Reason Cross Reference Table
 * @author John Tutton john@tutton.net
 *
 */

public class EmployeeTermination {
	String action = ""; //$ACTION
	String actionReason = ""; //$PSAction_Reason = ' '
	String auditOperId = ""; //$PSAuditOperId = substr($PSAuditOperId,2,5)
	String businessUnit = ""; //$Business_Unit
	String command = ""; //$Command
	String company = ""; //$Company
	String completionStatus = ""; //$CompletionStatus
	String date = ""; //$PSDate
	String deptartmentId = ""; //$DeptId
	String employeeClass = ""; //$Empl_Class
	String employeeId = ""; //$PSEmpl = ' '
	String employeeStatus = ""; //$Empl_Status
	String errorProgramParm = ""; //$ErrorProgramParm = 'HRZ102A'
	String fullOrPartTime = ""; //$Full_Part_Time
	String jobCode = ""; //$JobCode
	String library = ""; //$Library
	String location = ""; //$Location
	String rehireDay = ""; //$PSReHireDay = ' '
	String rehireMonth = ""; //$PSReHireMnth = ' '
	String rehireYear = ""; //$PSReHireYr = ' '
	String state = ""; //$PSState
	String status = ""; //#Status
	String terminationCode = ""; //$PSTermCode = ' '
	String terminationDay = ""; //$PsTermDay = ' '
	String terminationMonth = ""; //$PSTermMnth = ' '
	String terminationReason = ""; //$PSTermReason = ' '
	String terminationYear = ""; //$PSTermYr = ' '
	String voluntaryOrInvoluntary = ""; //$PSVolInvol = ' '

	//HR02-Initialize-Fields				//Initialize the fields to ensure that that they all start out blank.
	//HR02-Process-Main				//This is the main processing procedure
	//HR02-Get-Job				//This routine will the Job Data row for each of the employee numbers entered in the trigger file.
	//HR02-Process-Data				//This routine moves 'N' to change address parameter and calls the RPG program.
	//HR02-Trim-Parameters				//This routine trims all leading and trailing blanks from the data.
	//HR02-Get-Action-Reason				//This routine will determine if a termination was voluntary or involuntary basedd on Action and Action Reason codes.
	//HR02-Get-Reason-Description				//This routine gets the description field from the Action Reason table when Action = Termination and Action Code equals Other.

	/**
	 * Procedure:  HR02-Initialize-Fields
	 * Desc:  Initialize the fields to ensure that that they all start out blank.
	 */
	private void initializeFields() {
		employeeId = ""; //$PSEmpl = ' '
		terminationMonth = ""; //$PSTermMnth = ' '
		terminationDay = ""; //$PsTermDay = ' '
		terminationYear = ""; //$PSTermYr = ' '
		rehireMonth = ""; //$PSReHireMnth = ' '
		rehireDay = ""; //$PSReHireDay = ' '
		rehireYear = ""; //$PSReHireYr = ' '
		voluntaryOrInvoluntary = ""; //$PSVolInvol = ' '
		terminationCode = ""; //$PSTermCode = ' '
		terminationReason = ""; //$PSTermReason = ' '
		actionReason = ""; //$PSAction_Reason = ' '
		auditOperId = ""; //$PSAuditOperId = substr($PSAuditOperId,2,5)
		errorProgramParm = ""; //$ErrorProgramParm = 'HRZ102A'
	}

	private void ProcessMain() {
		//HR02-Initialize-Fields
//		Move 1 to #NumberOfDays   !Set the number of days to add to the passed date
//		Do dtu-add-days($PSDateIn, #NumberOfDays, $PSDate)    !Add one day to the date using DateMath.sqc
//		show 'zhri102a.sqc $PSDate: ' $PSDate
//		//HR02-Get-Job
//		do Get-Oprid
//		    Let $ADLegOprid = $PSOprid
//		Let $PSEmpl = $PSOprid
//		show 'zhri102a.sqc $PSEmpl: ' $PSEmpl
//		If $PSEmpl <> '' and $PSEmpl <> ' '  !If the new oprid is not blank and it is not null on return
//		    Let $PSDate = $PSDateIn    !Move the original date back to psdate.
//		    do HR02-Process-Data
//		End-if    !$PSEmpl <> '' and $PSEmpl <> ' '
		//HR02-Get-Action-Reason
		//HR02-Get-Reason-Description
		terminationReason = PsActionReason.findReasonDescriptionByActionAndActionReason(action, actionReason);
	}

	private void GetJob() {
//		begin-select
//		CJ5.ACTION
//		CJ5.ACTION_REASON
//		CJ5.Location
//		CJ5.Full_Part_Time
//		CJ5.Company
//		CJ5.Business_Unit
//		CJ5.Empl_Class
//		CJ5.Empl_Status
//		CJ5.DeptId
//		CJ5.JobCode
//		   Let $PSAction = &CJ5.ACTION
//		   Let $PSAction_Reason = &CJ5.ACTION_REASON
//		   Let $PSLocation = &CJ5.Location
//		   Let $PSCompany = &CJ5.Company
//		   Let $PSBusinessUnit = &CJ5.Business_Unit
//		   Let $PSEmplClass = &CJ5.Empl_Class
//		   Let $PSEmplStatus = &CJ5.Empl_Status
//		   Let $PSDeptId = &CJ5.DeptId
//		   Let $PSJobCode = &CJ5.JobCode
//		   Let $Wrk_AD_JobDataBuild = 'Y'                 !sree-UAAMOD
//		   Let $PSAction = rtrim($PSAction,' ')                                             ! ALS-08/03/2009
//		   Let $PSAction_Reason = rtrim($PSAction_Reason,' ')                               ! ALS-08/03/2009
//		   If $PSAction <> 'REH'
//		//HR02-Get-Action-Reason
//		        do HR02-Get-Action-Reason
//		   End-If    !$PSAction <> 'REH'
//		from PS_Job CJ5
//		where CJ5.Emplid = $PSEmplid
//		  and TO_CHAR(CJ5.EFFDT, 'YYYY-MM-DD') = $PSDate
//		  and CJ5.EFFSEQ = #PSEFFSEQ
//		  and CJ5.EMPL_RCD = 0             ! changed for v8.3
//		end-select
	}

	private void GetActionReason() {
//		Let $Found = 'N'   !Initialize the record found variable
//				Begin-Select
//				CPT16.ZHRF_LEGTERMCD
//				    Let $PSVolInvol = &CPT16.ZHRF_LEGTERMCD
//				CPT16.ZHRF_LEGTERMRSN
//				    Let $PSTermCode = &CPT16.ZHRF_LEGTERMRSN
//				     If $PSTermCode = 'O' and $PSAction = 'TER'
//				        Do HR02-Get-Reason-Description
		//HR02-Get-Reason-Description
		terminationReason = PsActionReason.findReasonDescriptionByActionAndActionReason(action, actionReason);
//				     End-If    !$PSTermCode = 'O'
//				    Let $Found = 'Y'     !Mark that a record was found
//				from PS_ZHRT_TRMRS_CREF CPT16
//				where CPT16.STATUS = 'A'
//				  and CPT16.ACTION = $PSAction
//				  and CPT16.ACTION_REASON = $PSAction_Reason
//				End-Select
//				If $Found = 'N'
//				     Let $ErrorMessageParm = 'Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF'
//				     Do Call-Error-Routine       !From ZHRI100A.SQR
//				     !Default the Action and reason in the legacy system
//				     Let $PSVolInvol = 'V'
//				     Let $PSTermCode = 'O'
//				     Let $PSTermReason = 'ACTION REASON NOT SELECTED IN PS'
//				End-If    !$Found = 'N'
	}
	
	
	
	
	
}

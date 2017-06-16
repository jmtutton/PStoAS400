package erd.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import erd.model.AdWrkFields;
import erd.model.CrossReferenceTerminationReason;
import erd.model.PsActionReason;
import erd.model.PsJob;
import erd.model.TriggerEmployee;

/**
 * ZHRI102A – Termination
 * PeopleCode filename ZHRI102A.SQC
 * Update the Legacy files which where modified by the HR program ZHRI102A.  
 * Only specific fields will be updated, those which were changed on the AAHR02 screen.
 * Tables Used:
 *        PsJob									PS_Job                       Job Table
 *        PsActionReason						PS_Action Reason             Action Reason Table
 *        CrossReferenceTerminationReason		PS_ZHRT_TRMRS_CREF           Termination Reason Cross Reference Table
 * @author John Tutton john@tutton.net
 */

public class EmployeeTermination {
	
	TriggerEmployee trigger;
	AdWrkFields adWrkFields;
	
	public EmployeeTermination(TriggerEmployee trigger, AdWrkFields adWrkFields) {
		this.trigger = trigger;
		this.adWrkFields = adWrkFields;
	}
	
	/**
	 * ZHRI102A – Termination - Call to AS400
	 * "Call " + AS400LibraryPath +  /HRZ102A + Parameters
	 * PARAMETERS:
             Employee ID
             Termination Month
             Termination Day
             Termination Year
             Rehire Month
             Rehire Day
             Rehire Year
             Voluntary or Involuntary
             Termination Code
             Audit Operator ID
             Termination Reason
	 */
	String psTerminationMonth; //$PSTermMnth  //Two digit representation of month
	String psTerminationDay; //$PsTermDay  //Two digit representation of day
	String psTerminationYear; //$PSTermYr  //Four digit representation of year
	String psRehireMonth; //$PSReHireMnth  //Two digit representation of month
	String psRehireDay; //$PSReHireDay  //Two digit representation of day
	String psRehireYear; //$PSReHireYr  //Four digit representation of year
	String psVoluntaryOrInvoluntary; //$PSVolInvol
	String psTerminationCode; //$PSTermCode
	String psTerminationReason; //$PSTermReason is at most 30 positions long, but HRZ102A receives it with a length of 35 positions; needs to be padded to 35

	Date terminationDate;
	Date rehireDate;
	
	String psAction; //$PSAction
	String psActionReason; //$PSAction_Reason
	String psBusinessUnit; //$PSBusinessUnit = &PS_Job.Business_Unit
	String psCompany; //$PSCompany = &PS_Job.Company
	Date psDate; //$PSDate
	String psDeptId; //$PSDeptId = &PS_Job.DeptId
	String psEmplClass; //$PSEmplClass = &PS_Job.Empl_Class
	String psEmplStatus; //$PSEmplStatus = &PS_Job.Empl_Status
	String psJobCode; //$PSJobCode = &PS_Job.JobCode
	String psLocation; //$PSLocation
	String psState; //$PSState

	String businessUnit; //$Business_Unit
	String company; //$Company
	
	String deptartmentId; //$DeptId
	String employeeClass; //$Empl_Class
	String employeeStatus; //$Empl_Status
	String fullOrPartTime; //$Full_Part_Time
	String jobCode; //$JobCode
	String library; //$Library
	String location; //$Location
	Boolean isContractor;
	
	Boolean wrkAdJobDataBuild; //$Wrk_AD_JobDataBuild

	//begin-program
	//Get-Current-DateTime  //queries the database to get the current time. It also initializes a slew of string variables ($AsOfToday, $AsOfNow, $CurrentCentury, $ReportDate
	//Init-DateTime  //sets a collection of variables that can be used by the other procedures in datetime.sqc that format dates or do date arithmetic
	//ZHRI100A.Process-Main
	//	ZHRI100A.Get-Variable(RMTSVR)
	//	ZHRI100A.Get-Variable(AS400library)
	//	ZHRI100A.Get-Variable(RMTNTADSVR)
	//	ZHRI100A.Check-Interface-Runfile
	//	ZHRI100A.Get-Trigger-Data
	//		ZHRI100A.Check-If-Contractor
	//		ZHRI100A.Check-If-Correct102A
	//		ZHRI100A.Call-Programs
	//			ZHRI100A.Intialize-AD-WrkFields
	//			HR02-Process-Main  //This is the main processing procedure
	//				HR02-Initialize-Fields  //Initialize the fields to ensure that that they all start out blank.
	//				HR02-Get-Job  //This routine will the Job Data row for each of the employee numbers entered in the trigger file.
	//					HR02-Get-Action-Reason  //This routine will determine if a termination was voluntary or involuntary basedd on Action and Action Reason codes.
	//						HR02-Get-Reason-Description  //This routine gets the description field from the Action Reason table when Action = Termination and Action Code equals Other.
	//				ZHRI100A.Get-Oprid
	//				HR02-Process-Data  //This routine moves 'N' to change address parameter and calls the RPG program.
	//					HR02-Trim-Parameters  //This routine trims all leading and trailing blanks from the data.
	//					ZHRI100A.Call-System
	//		ZHRI100A.Update-Trigger-Row
	//	Call System Using $Command
	//Reset.sqc
	//end-program
	
	/**
	 * HR02-Initialize-Fields - from ZHRI102A.SQC
	 * Initialize the fields to ensure that that they all start out blank.
	 */
	private void hr02InitializeFields() {
//		zhri100A.psEmpl = " "; //$PSEmpl = ' '
		psTerminationMonth = " "; //$PSTermMnth = ' '
		psTerminationDay = " "; //$PsTermDay = ' '
		psTerminationYear = " "; //$PSTermYr = ' '
		psRehireMonth = " "; //$PSReHireMnth = ' '
		psRehireDay = " "; //$PSReHireDay = ' '
		psRehireYear = " "; //$PSReHireYr = ' '
		psVoluntaryOrInvoluntary = " "; //$PSVolInvol = ' '
		psTerminationCode = " "; //$PSTermCode = ' '
		psTerminationReason = " "; //$PSTermReason = ' '
		psActionReason = " "; //$PSAction_Reason = ' '
		//$PSAuditOperId = substr($PSAuditOperId,2,5)
//		zhri100A.psAuditOperatorId = trigger.getOperatorId().trim().substring(1); //strips the 'E' off of the employee id
		//$ErrorProgramParm = 'HRZ102A'
//		zhri100A.errorProgramParm = "HRZ102A";
	}

	/**
	 * HR02-Process-Main - from ZHRI102A.SQC
	 * This is the main processing procedure
	 */
	public void hr02ProcessMain() {
		psDate = trigger.getEffectiveDate();
//		DO HR02-Initialize-Fields
		hr02InitializeFields();
//		MOVE 1 to #NumberOfDays   !Set the number of days to add to the passed date
		Integer numberOfDays = 1;
//		DO dtu-add-days($PSDateIn, #NumberOfDays, $PSDate)    !Add one day to the date using DateMath.sqc
//		psDate = (java.sql.Date) DateUtil.addDays(zhri100A.psDateIn, numberOfDays);
		psDate = (java.sql.Date) erd.DateUtil.addDays(trigger.getEffectiveDate(), numberOfDays);
//		DO HR02-Get-Job
//		hr02GetJob(zhri100A.psEmplId, psDate, zhri100A.psEffectiveSequence);
		hr02GetJob(trigger.getEmployeeId().trim(), psDate, trigger.getEffectiveSequence());
//		DO ZHRI100A.Get-OprId
		String psOprid = ZHRI100A.getOprId(ZHRI100A.psEmpl, ZHRI100A.indexNumber, ZHRI100A.poiFlag);
		ZHRI100A.psOprid = psOprid;
//		LET $ADLegOprid = $PSOprid
		adWrkFields.adLegOprId = psOprid;
//		LET $PSEmpl = $PSOprid
		ZHRI100A.psEmpl = psOprid;
//		IF $PSEmpl <> '' AND $PSEmpl <> ' '  !If the new oprid is not blank and it is not null on return
		if(ZHRI100A.psEmpl != null && !(ZHRI100A.psEmpl.trim()).isEmpty()) {
//		    LET $PSDate = $PSDateIn    !Move the original date back to psdate.
			psDate = trigger.getEffectiveDate();
// 			DO HR02-Process-Data
			HR02ProcessData();
//		END-IF    !$PSEmpl <> '' and $PSEmpl <> ' '
		}
	}
	
	/**
	 * HR02-Process-Data - from ZHRI102A.SQC
	 * This routine moves 'N' to change address parameter and calls the RPG program.
	 */
	private void HR02ProcessData() {
//		LET $PSChange = 'N'
//		Boolean psChange = false; //TODO: where is this used???
		//Rehire
//		IF ($PSAction = 'REH') AND ($PSAction_Reason = 'REH')
		if("REH".equals(psAction) && "REH".equals(psActionReason)) {
//		  	LET $PSRehireYr = substr($PSDate,1,4)
			psRehireYear = new SimpleDateFormat("yyyy").format(psDate);
//		  	LET $PSRehireMnth = substr($PSDate,6,2)
			psRehireMonth = new SimpleDateFormat("mm").format(psDate);
//		  	LET $PSRehireDay = substr($PSDate,9,2)
			psRehireDay = new SimpleDateFormat("dd").format(psDate);
//		END-IF
		}
		//Termination
//		IF ($psAction = 'TER' OR $psAction = 'RET' OR $psAction = 'TWP' OR $psAction = 'TWB')
		if("TER".equals(psAction) || "RET".equals(psAction) || "TWP".equals(psAction) || "TWB".equals(psAction)) {
//		  	LET $PSTermYr = substr($PSDate,1,4)
			psTerminationYear = new SimpleDateFormat("yyyy").format(psDate);
//		  	LET $PSTermMnth = substr($PSDate,6,2)
			psTerminationMonth = new SimpleDateFormat("mm").format(psDate);
//		  	LET $PSTermDay = substr($PSDate,9,2)
			psTerminationDay = new SimpleDateFormat("dd").format(psDate);
//		END-IF
		}
//		DO Remove-Non-Letters-Numbers ($PSTermReason,$PSTermReason)        !ZRmvSpcChr.sqc
		psTerminationReason = psTerminationReason.replaceAll("[^a-zA-Z0-9]", "");
//		DO HR02-Trim-Parameters     !Routine to trim the parameters to insure that there are not a larger number of blanks being passed
		HR02TrimParameters();
//		LET $Part2 = 'Parm('''         ||
//        $PSEmpl             ||
//        ''' '''         ||
//        $PSTermMnth         ||
//        ''' '''         ||
//        $PsTermDay          ||
//        ''' '''         ||
//        $PSTermYr           ||
//        ''' '''         ||
//        $PSReHireMnth       ||
//        ''' '''         ||
//        $PSReHireDay        ||
//        ''' '''         ||
//        $PSReHireYr         ||
//        ''' '''         ||
//        $PSVolInvol         ||
//        ''' '''         ||
//        $PSTermCode         ||
//        ''' '''         ||
//        $PSAuditOperId      ||
//        ''' '''         ||
//        $PSTermReason       ||
//        ''')" '
		String part2 = "Parm('" + ZHRI100A.psEmpl + "' '"
				+ psTerminationMonth + "' '"
        		+ psTerminationDay + "' '"
        		+ psTerminationYear + "' '"
        		+ psRehireMonth + "' '"
        		+ psRehireDay + "' '"
        		+ psRehireYear + "' '"
        		+ psVoluntaryOrInvoluntary + "' '"
        		+ psTerminationCode + "' '"
        		+ ZHRI100A.psAuditOperatorId + "' '"
        		+ psTerminationReason + "'";
//		LET $Part1 = '"CALL ' || $Library ||'/HRZ102A '
		String part1 = "\"CALL " + library + "/HRZ102A ";
//		LET $Command = $Part1||$Part2
		ZHRI100A.command = part1 + part2;
//		DO Call-System             !From ZHRI100A.SQR
//		status = ZHRI100A.callSystem(command);
//		IF (#Status = 0)
		if(ZHRI100A.status == 0) {
//			LET $CompletionStatus = 'C'   !Completed Normally
			ZHRI100A.completionStatus = "C";
//		END-IF    !#Status = 0
		}
	}
	
	/**
	 * HR02-Trim-Parameters - from ZHRI102A.SQC
	 * This routine trims all leading and trailing blanks from the data.
	 */
	private void HR02TrimParameters() {
//		LET $PSAuditOperId= RTRIM(LTRIM($PSAuditOperId,' '),' ')
//		LET $PSEmpl = RTRIM(LTRIM($PSEmpl,' '),' ')
//		LET $PSTermMnth = RTRIM(LTRIM($PSTermMnth,' '),' ')
//		LET $PsTermDay = RTRIM(LTRIM($PsTermDay,' '),' ')
//		LET $PSTermYr = RTRIM(LTRIM($PSTermYr,' '),' ')
//		LET $PSReHireMnth = RTRIM(LTRIM($PSReHireMnth,' '),' ')
//		LET $PSReHireDay = RTRIM(LTRIM($PSReHireDay,' '),' ')
//		LET $PSReHireYr = RTRIM(LTRIM($PSReHireYr,' '),' ')
//		LET $PSState = RTRIM(LTRIM($PSState,' '),' ')
//		LET $PSZip = RTRIM(LTRIM($PSZip,' '),' ')
//		LET $PSVolInvol = RTRIM(LTRIM($PSVolInvol,' '),' ')
//		LET $PSTermCode = RTRIM(LTRIM($PSTermCode,' '),' ')
//		LET $PSTermReason = RTRIM(LTRIM($PSTermReason,' '),' ')
//		! $PSTermReason is at most 30 positions long, but HRZ102A receives it with a length of 35 positons.
//		LET $PSTermReason = Rpad($PSTermReason,35,' ')  !Make sure not less than 35 long
	}
	
	/**
	 * HR02-Get-Job - from ZHRI102A.SQC
	 * This routine will the Job Data row for each of the employee numbers entered in the trigger file.
	 */
	private void hr02GetJob(String employeeId, Date effectiveDate, BigDecimal effectiveSequence) {
		PsJob psJob = PsJob.hr02GetJob(employeeId, new java.sql.Date(effectiveDate.getTime()), effectiveSequence);
//		LET $PSAction = &PS_Job.ACTION
		psAction = psJob.getAction();
//	   	LET $PSAction_Reason = &PS_Job.ACTION_REASON
		psActionReason = psJob.getActionReason();
//	   	LET $PSLocation = &PS_Job.Location
		psLocation = psJob.getLocation();
//	   	LET $PSCompany = &PS_Job.Company
		psCompany = psJob.getCompany();
//	   	LET $PSBusinessUnit = &PS_Job.Business_Unit
		psBusinessUnit = psJob.getBusinessUnit();
//	   	LET $PSEmplClass = &PS_Job.Empl_Class
		psEmplClass = psJob.getEmplClass();
//	   	LET $PSEmplStatus = &PS_Job.Empl_Status
		psEmplStatus = psJob.getEmplStatus();
//	   	LET $PSDeptId = &PS_Job.DeptId
		psDeptId = psJob.getDeptId();
//	   	LET $PSJobCode = &PS_Job.JobCode
		psJobCode = psJob.getJobCode();
//	   	LET $Wrk_AD_JobDataBuild = 'Y'
		wrkAdJobDataBuild = true;
//	   	IF $PSAction <> 'REH'
		if(!"REH".equalsIgnoreCase(psAction)) {
//	        DO HR02-Get-Action-Reason
			HR02GetActionReason(psAction, psActionReason);
//	   	END-IF    !$PSAction <> 'REH'
		}
	}

	/**
	 * HR02-Get-Action-Reason - from ZHRI102A.SQC
	 * This routine will determine if a termination was voluntary or involuntary based on Action and Action Reason codes.
	 */
	private void HR02GetActionReason(String psAction, String psActionReason) {
//		LET $Found = 'N'   !Initialize the record found variable
		Boolean found = false;
//		BEGIN-SELECT
		CrossReferenceTerminationReason terminationReason = CrossReferenceTerminationReason.findByActionAndActionReasonAndStatus(psAction, psActionReason, "A");
		if(terminationReason != null) {
	//		PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMCD
	//		LET $PSVolInvol = &PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMCD
			psVoluntaryOrInvoluntary = terminationReason.getLegacyTerminationCode();
	//		PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMRSN
	//		LET $PSTermCode = &PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMRSN
			psTerminationCode = terminationReason.getLegacyTerminationReason();
	//		IF $PSTermCode = 'O' AND $PSAction = 'TER'
			if("O".equalsIgnoreCase(psTerminationCode) && "TER".equalsIgnoreCase(psAction)) {
	//			DO HR02-Get-Reason-Description
				psTerminationReason = PsActionReason.findReasonDescriptionByActionAndActionReason(psAction, psActionReason);
	//		END-IF    !$PSTermCode = 'O'
			}
	//		LET $Found = 'Y'     !Mark that a record was found
	//		FROM PS_ZHRT_TRMRS_CREF PS_ZHRT_TRMRS_CREF
	//		WHERE PS_ZHRT_TRMRS_CREF.STATUS = 'A'
	//			AND PS_ZHRT_TRMRS_CREF.ACTION = $PSAction
	//			AND PS_ZHRT_TRMRS_CREF.ACTION_REASON = $PSAction_Reason
	//		END-SELECT
			found = true;
		}
//		IF $Found = 'N'
		if(!found) {
//			LET $ErrorMessageParm = 'Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF'
			ZHRI100A.errorMessageParm = "Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF";
//			DO Call-Error-Routine       !From ZHRI100A.SQR
			ZHRI100A.callErrorRoutine();
//			!Default the Action and reason in the legacy system
//			LET $PSVolInvol = 'V'
			psVoluntaryOrInvoluntary = "V";
//			LET $PSTermCode = 'O'
			psTerminationCode = "O";
//			LET $PSTermReason = 'ACTION REASON NOT SELECTED IN PS'
			psTerminationReason = "ACTION REASON NOT SELECTED IN PS";
//		END-IF    !$Found = 'N'
		}
	}
	
}

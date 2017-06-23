package erd.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import erd.model.AS400Package;
import erd.model.ActionFields;
import erd.model.CrossReferenceTerminationReason;
import erd.model.PsActionReason;
import erd.model.PsJob;
import erd.model.PszTriggerEmployee;
import erd.model.Zhri100aFields;

/**
 * ZHRI102A � Termination
 * PeopleCode filename ZHRI102A.SQC
 * Update the Legacy files which where modified by the HR program ZHRI102A.  
 * Only specific fields will be updated, those which were changed on the AAHR02 screen.
 * @author John Tutton john@tutton.net
 */

public class EmployeeTermination {
	
	PszTriggerEmployee trigger;
	PsJob psJob;
	Zhri100aFields zhri100aFields;
	ActionFields actionFields;
	
	public EmployeeTermination(PszTriggerEmployee trigger, Zhri100aFields zhri100aFields) {
		this.trigger = trigger;
		this.zhri100aFields = zhri100aFields;
	}
	
	/**
	 * ZHRI102A � Termination - Call to AS400
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
//	String psTerminationMonth; //$PSTermMnth  //Two digit representation of month
//	String psTerminationDay; //$PsTermDay  //Two digit representation of day
//	String psTerminationYear; //$PSTermYr  //Four digit representation of year
//	String psRehireMonth; //$PSReHireMnth  //Two digit representation of month
//	String psRehireDay; //$PSReHireDay  //Two digit representation of day
//	String psRehireYear; //$PSReHireYr  //Four digit representation of year
//	
//	String psVoluntaryOrInvoluntary; //$PSVolInvol
//	String psTerminationCode; //$PSTermCode
//	String psTerminationReason; //$PSTermReason is at most 30 positions long, but HRZ102A receives it with a length of 35 positions; needs to be padded to 35

//	Date terminationDate;
//	Date rehireDate;
	
//	String psAction; //$PSAction
//	String psActionReason; //$PSAction_Reason
//	String psBusinessUnit; //$PSBusinessUnit = &PS_Job.Business_Unit
//	String psCompany; //$PSCompany = &PS_Job.Company
//	Date psDate; //$PSDate
//	String psDeptId; //$PSDeptId = &PS_Job.DeptId
//	String psEmplClass; //$PSEmplClass = &PS_Job.Empl_Class
//	String psEmplStatus; //$PSEmplStatus = &PS_Job.Empl_Status
//	String psJobCode; //$PSJobCode = &PS_Job.JobCode
//	String psLocation; //$PSLocation
//	String psState; //$PSState

//	String businessUnit; //$Business_Unit
//	String company; //$Company
	
//	String deptartmentId; //$DeptId
//	String employeeClass; //$Empl_Class
//	String employeeStatus; //$Empl_Status
//	String fullOrPartTime; //$Full_Part_Time
//	String jobCode; //$JobCode
//	String library; //$Library
//	String location; //$Location
//	Boolean isContractor;
	
//	Boolean wrkAdJobDataBuild; //$Wrk_AD_JobDataBuild
	//begin-program
	//Get-Current-DateTime  //queries the database to get the current time. It also initializes a slew of string variables ($AsOfToday, $AsOfNow, $CurrentCentury, $ReportDate
	//Init-DateTime  //sets a collection of variables that can be used by the other procedures in datetime.sqc that format dates or do date arithmetic
	//ZHRI100A.Process-Main
	//	ZHRI100A.Get-Variable()
	//	ZHRI100A.Check-Interface-Runfile
	//  ZHRI100A.Built-Where-Clause-Delay
	//	ZHRI100A.Get-Trigger-Data
	//    	Begin-Select loops=150
	//		ZHRI100A.Check-If-Contractor
	//		ZHRI100A.Check-If-Correct102A
	//		ZHRI100A.Call-Programs
	//			ZHRI100A.Intialize-AD-WrkFields
	//			HR02-Process-Main  //This is the main processing procedure
	//				HR02-Initialize-Fields  //Initialize the fields to ensure that that they all start out blank.
	//				HR02-Get-Job  //This routine will the Job Data row for each of the employee numbers entered in the trigger file.
	//					HR02-Get-Action-Reason  //This routine will determine if a termination was voluntary or involuntary basedd on Action and Action Reason codes.
	//						HR02-Get-Reason-Description  //This routine gets the description field from the Action Reason table when Action = Termination and Action Code equals Other.
	//				ZHRI100A.Get-OprId
	//				HR02-Process-Data  //This routine moves 'N' to change address parameter and calls the RPG program.
	//					Remove-Non-Letters-Numbers
	//					HR02-Trim-Parameters
	//					ZHRI100A.Call-System
	//						Get-Current-DateTime
	//						Call System Using $Command
	//			End-Procedure HR02-Process-Main
	//		ZHRI100A.Update-Trigger-Row
	//	End-Procedure Get-Trigger-Data
	//	Commit-Transaction
	//	Call System Using $Command #status Wait
	//Reset.sqc
	//end-program
	
	/**
	 * HR02-Initialize-Fields - from ZHRI102A.SQC
	 * Initialize the fields to ensure that that they all start out blank.
	 */
	private void HR02_initializeFields() {
		//$PSAuditOperId = substr($PSAuditOperId,2,5)
		zhri100aFields.auditOperatorId = trigger.getOperatorId().substring(1); //strips the 'E' off of the employee id
		//$ErrorProgramParm = 'HRZ102A'
		zhri100aFields.errorProgramParameter = "HRZ102A";
	}

	/**
	 * HR02-Process-Main - from ZHRI102A.SQC
	 * This is the main processing procedure
	 */
	public String HR02_processMain() {
		zhri100aFields.poiFlag = false;
		String completionStatus = "";
		Date psDate = trigger.getEffectiveDate();
		//DO HR02-Initialize-Fields
		HR02_initializeFields();
		//MOVE 1 to #NumberOfDays   !Set the number of days to add to the passed date
		Integer numberOfDays = 1;
		//DO DTU-ADD-DAYS($PSDateIn, #NumberOfDays, $PSDate)    !Add one day to the date using DateMath.sqc
		psDate = erd.DateUtil.addDays(trigger.getEffectiveDate(), numberOfDays);
		//DO HR02-Get-Job
		PsJob psJob = HR02_getJob(trigger.getEmployeeId(), psDate, trigger.getEffectiveSequence());
		//DO ZHRI100A.Get-OprId
		String psOprid = ZHRI100A.ZHRI100A_getOprId(trigger.getEmployeeId(), trigger.getEffectiveSequence(), zhri100aFields);
		//ZHRI100A.psOprid = psOprid;
		zhri100aFields.operatorId = psOprid;
		//LET $ADLegOprid = $PSOprid
		zhri100aFields.adLegacyOperatorId = psOprid;
		//LET $PSEmpl = $PSOprid
		zhri100aFields.employeeId = psOprid;
		//IF $PSEmpl <> '' AND $PSEmpl <> ' '  !If the new oprid is not blank and it is not null on return
		if(zhri100aFields.employeeId != null && !(zhri100aFields.employeeId).isEmpty()) {
			//LET $PSDate = $PSDateIn    !Move the original date back to psdate.
			psDate = trigger.getEffectiveDate();
			//DO HR02-Process-Data
			completionStatus = HR02_processData(trigger.getEmployeeId(), psJob);
		//END-IF    !$PSEmpl <> '' and $PSEmpl <> ' '
		}
		makeAS400PackageForHRZ102AProcess(zhri100aFields, actionFields);
		makeAS400PackageForErrorProcess(trigger.getProcessName(), zhri100aFields);
		return completionStatus;
	}
	
	/**
	 * HR02-Process-Data from ZHRI102A.SQC
	 * This routine moves 'N' to change address parameter and calls the RPG program.
	 * @param employeeId
	 * @param psJob
	 * @return
	 */
	private String HR02_processData(String employeeId, PsJob psJob) {
		String completionStatus = "E";
		//LET $PSChange = 'N'
		//Boolean psChange = false; //not used

		//Rehire
		//IF ($PSAction = 'REH') AND ($PSAction_Reason = 'REH')
		if("REH".equals(psJob.getAction()) && "REH".equals(psJob.getActionReason())) {
			//LET $PSRehireYr = substr($PSDate,1,4)
			actionFields.setRehireYear(new SimpleDateFormat("yyyy").format(psJob.getEffectiveDate()));
			//LET $PSRehireMnth = substr($PSDate,6,2)
			actionFields.setRehireMonth(new SimpleDateFormat("mm").format(psJob.getEffectiveDate()));
			//LET $PSRehireDay = substr($PSDate,9,2)
			actionFields.setRehireDay(new SimpleDateFormat("dd").format(psJob.getEffectiveDate()));
		//END-IF
		}
		
		//Termination
		//IF ($psAction = 'TER' OR $psAction = 'RET' OR $psAction = 'TWP' OR $psAction = 'TWB')
		if("TER".equals(psJob.getAction()) || "RET".equals(psJob.getAction()) || "TWP".equals(psJob.getAction()) || "TWB".equals(psJob.getAction())) {
			//LET $PSTermYr = substr($PSDate,1,4)
			actionFields.setTerminationYear(new SimpleDateFormat("yyyy").format(psJob.getEffectiveDate()));
			//LET $PSTermMnth = substr($PSDate,6,2)
			actionFields.setTerminationMonth(new SimpleDateFormat("mm").format(psJob.getEffectiveDate()));
			//LET $PSTermDay = substr($PSDate,9,2)
			actionFields.setTerminationDay(new SimpleDateFormat("dd").format(psJob.getEffectiveDate()));
		//END-IF
		}
		
		//DO Remove-Non-Letters-Numbers ($PSTermReason, $PSTermReason)        !ZRmvSpcChr.sqc
		actionFields.setTerminationReason(actionFields.getTerminationReason().replaceAll("[^a-zA-Z0-9]", ""));
		//DO HR02-Trim-Parameters     !Routine to trim the parameters to insure that there are not a larger number of blanks being passed
		HR02_trimParameters();
		String command = makeAS400PackageForHRZ102AProcess(zhri100aFields, actionFields);
		//DO Call-System   !From ZHRI100A.SQR
		Integer status = ZHRI100A.ZHRI100A_callSystem(command, zhri100aFields);
		//IF (#Status = 0)
		if(status == 0) { //no error returned from process
			//LET $CompletionStatus = 'C'   !Completed Normally
			completionStatus = "C";
		//END-IF    !#Status = 0
		}
		return completionStatus;
	}
	
	/**
	 * HR02-Trim-Parameters - from ZHRI102A.SQC
	 * This routine trims all leading and trailing blanks from the data.
	 */
	private void HR02_trimParameters() {
		//LET $PSAuditOperId= RTRIM(LTRIM($PSAuditOperId,' '),' ')
		//LET $PSEmpl = RTRIM(LTRIM($PSEmpl,' '),' ')
		//LET $PSTermMnth = RTRIM(LTRIM($PSTermMnth,' '),' ')
		//LET $PsTermDay = RTRIM(LTRIM($PsTermDay,' '),' ')
		//LET $PSTermYr = RTRIM(LTRIM($PSTermYr,' '),' ')
		//LET $PSReHireMnth = RTRIM(LTRIM($PSReHireMnth,' '),' ')
		//LET $PSReHireDay = RTRIM(LTRIM($PSReHireDay,' '),' ')
		//LET $PSReHireYr = RTRIM(LTRIM($PSReHireYr,' '),' ')
		//LET $PSState = RTRIM(LTRIM($PSState,' '),' ')
		//LET $PSZip = RTRIM(LTRIM($PSZip,' '),' ')
		//LET $PSVolInvol = RTRIM(LTRIM($PSVolInvol,' '),' ')
		//LET $PSTermCode = RTRIM(LTRIM($PSTermCode,' '),' ')
		//LET $PSTermReason = RTRIM(LTRIM($PSTermReason,' '),' ')
		//! $PSTermReason is at most 30 positions long, but HRZ102A receives it with a length of 35 positons.
		//LET $PSTermReason = Rpad($PSTermReason,35,' ')  !Make sure not less than 35 long
	}
	
	/**
	 * HR02-Get-Job - from ZHRI102A.SQC
	 * This routine will the Job Data row for each of the employee numbers entered in the trigger file.
	 * @param employeeId
	 * @param effectiveDate
	 * @param effectiveSequence
	 * @return
	 */
	private PsJob HR02_getJob(String employeeId, Date effectiveDate, Integer effectiveSequence) {
		PsJob psJob = PsJob.HR02_getJob(employeeId, new java.util.Date(effectiveDate.getTime()), effectiveSequence);
		//LET $PSAction = &PS_Job.ACTION
		//psAction = psJob.getAction();
		//LET $PSAction_Reason = &PS_Job.ACTION_REASON
		//psActionReason = psJob.getActionReason();
		//LET $PSLocation = &PS_Job.Location
		//psLocation = psJob.getLocation();
		//LET $PSCompany = &PS_Job.Company
		//psCompany = psJob.getCompany();
		//LET $PSBusinessUnit = &PS_Job.Business_Unit
		//psBusinessUnit = psJob.getBusinessUnit();
		//LET $PSEmplClass = &PS_Job.Empl_Class
		//psEmplClass = psJob.getEmplClass();
		//LET $PSEmplStatus = &PS_Job.Empl_Status
		//psEmplStatus = psJob.getEmplStatus();
		//LET $PSDeptId = &PS_Job.DeptId
		//psDeptId = psJob.getDeptId();
		//LET $PSJobCode = &PS_Job.JobCode
		//psJobCode = psJob.getJobCode();
		//LET $Wrk_AD_JobDataBuild = 'Y'
		//wrkAdJobDataBuild = true;
		//IF $PSAction <> 'REH'
		if(!"REH".equalsIgnoreCase(psJob.getAction())) {
			//DO HR02-Get-Action-Reason
			actionFields = HR02_getActionReason(psJob);
		//END-IF    !$PSAction <> 'REH'
		}
		return psJob;
	}

	/**
	 * HR02-Get-Action-Reason from ZHRI102A.SQC
	 * This routine will determine if a termination was voluntary or involuntary based on Action and Action Reason codes.
	 * @param psJob
	 * @return
	 */
	private ActionFields HR02_getActionReason(PsJob psJob) {
		ActionFields actionFields = new ActionFields();
		//LET $Found = 'N'   !Initialize the record found variable
		Boolean found = false;
		//BEGIN-SELECT
		CrossReferenceTerminationReason terminationReason = CrossReferenceTerminationReason.findByActionAndActionReasonAndStatus(psJob.getAction(), psJob.getActionReason(), "A");
		if(terminationReason != null) {
			//PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMCD
			//LET $PSVolInvol = &PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMCD
			actionFields.setVoluntaryOrInvoluntary(terminationReason.getLegacyTerminationCode());
			//PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMRSN
			//LET $PSTermCode = &PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMRSN
			actionFields.setTerminationCode(terminationReason.getLegacyTerminationReason());
			//IF $PSTermCode = 'O' AND $PSAction = 'TER'
			if("O".equalsIgnoreCase(actionFields.getTerminationCode()) && "TER".equalsIgnoreCase(psJob.getAction())) {
				//DO HR02-Get-Reason-Description
				actionFields.setTerminationReason(PsActionReason.findReasonDescriptionByActionAndActionReason(psJob.getAction(), psJob.getActionReason()));
			//END-IF    !$PSTermCode = 'O'
			}
			//LET $Found = 'Y'     !Mark that a record was found
			//FROM PS_ZHRT_TRMRS_CREF PS_ZHRT_TRMRS_CREF
			//WHERE PS_ZHRT_TRMRS_CREF.STATUS = 'A'
			//	AND PS_ZHRT_TRMRS_CREF.ACTION = $PSAction
			//	AND PS_ZHRT_TRMRS_CREF.ACTION_REASON = $PSAction_Reason
			//END-SELECT
			found = true;
		}
		//IF $Found = 'N'
		if(!found) {
			//LET $ErrorMessageParm = 'Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF'
			zhri100aFields.errorMessageParameter = "Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF";
			//DO Call-Error-Routine       !From ZHRI100A.SQR
			ZHRI100A.ZHRI100A_callErrorRoutine("HRZ102A", zhri100aFields);
			//!Default the Action and reason in the legacy system
			//LET $PSVolInvol = 'V'
			actionFields.setVoluntaryOrInvoluntary("V");
			//LET $PSTermCode = 'O'
			actionFields.setTerminationCode("O");
			//LET $PSTermReason = 'ACTION REASON NOT SELECTED IN PS'
			actionFields.setTerminationReason("ACTION REASON NOT SELECTED IN PS");
		//END-IF    !$Found = 'N'
		}
		return actionFields;
	}
	
	/**
	 * 
	 * @param zhri100aFields
	 * @param actionFields
	 */
	private String makeAS400PackageForHRZ102AProcess(Zhri100aFields zhri100aFields, ActionFields actionFields) {
		String processName = "HRZ102A";
		List<String> parameterList = new ArrayList<String>();
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		
		String command = "\"CALL " + zhri100aFields.as400Library + "/" + processName + " " 
				+ "PARM('" + zhri100aFields.employeeId + "' "
				+ "'" + actionFields.getTerminationMonth() + "' "
				+ "'" + actionFields.getTerminationDay() + "' "
				+ "'" + actionFields.getTerminationYear() + "' "
				+ "'" + actionFields.getRehireMonth() + "' "
				+ "'" + actionFields.getRehireDay() + "' "
				+ "'" + actionFields.getRehireYear() + "' "
				+ "'" + actionFields.getVoluntaryOrInvoluntary() + "' "
				+ "'" + actionFields.getTerminationCode() + "' "
				+ "'" + zhri100aFields.auditOperatorId + "' "
				+ "'" + actionFields.getTerminationReason() + "')\" ";

		parameterList.add("employeeId");
		parameterList.add("terminationMonth");
		parameterList.add("terminationDay");
		parameterList.add("terminationYear");
		parameterList.add("rehireMonth");
		parameterList.add("rehireDay");
		parameterList.add("rehireYear");
		parameterList.add("voluntaryOrInvoluntary");
		parameterList.add("terminationCode");
		parameterList.add("auditOperatorId");
		parameterList.add("terminationReason");

		parameterMap.put("employeeId", zhri100aFields.employeeId);
		parameterMap.put("terminationMonth", actionFields.getTerminationMonth());
		parameterMap.put("terminationDay", actionFields.getTerminationDay());
		parameterMap.put("terminationYear", actionFields.getTerminationYear());
		parameterMap.put("rehireMonth", actionFields.getRehireMonth());
		parameterMap.put("rehireDay", actionFields.getRehireDay());
		parameterMap.put("rehireYear", actionFields.getRehireYear());
		parameterMap.put("voluntaryOrInvoluntary", actionFields.getVoluntaryOrInvoluntary());
		parameterMap.put("terminationCode", actionFields.getTerminationCode());
		parameterMap.put("auditOperatorId", zhri100aFields.auditOperatorId);
		parameterMap.put("terminationReason", actionFields.getTerminationReason());
		
//		AS400Package as400Package = new AS400Package(processName, parameterList, parameterMap);
//		return as400Package;
		return command;
	}
	
	/**
	 * 
	 * @param processName
	 * @param zhri100aFields
	 * @return
	 */
	private AS400Package  makeAS400PackageForErrorProcess(String processName, Zhri100aFields zhri100aFields) {
		List<String> parameterList = new ArrayList<String>();
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		String errorProgramParameter = zhri100aFields.errorProgramParameter;
		String employeeId = zhri100aFields.employeeId;
		String blankSpaceParameter = " ";
		String errorMessageParameter = zhri100aFields.errorMessageParameter;
		String criticalFlag = zhri100aFields.criticalFlag ? "Y" : "N";
		Calendar now = Calendar.getInstance();
		String errorDateParameter =  now.get(Calendar.MONTH) + "/" + now.get(Calendar.DATE) + "/" + now.get(Calendar.YEAR);
		String errorTimeParameter = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);
		String opridErrorParameter = zhri100aFields.operatorId;
		String yesOrNoParameter = "Y"; //TODO: What should this value really be called
		//!Make Sure that the ErrorMessageParm is always 75 Characters long
		errorMessageParameter = String.format("%75s", errorMessageParameter);
		String command = "\"CALL " + zhri100aFields.as400Library + "/" + processName + " "
					+ "PARM('"
					+ "'" + errorProgramParameter + "' "
					+ "'" + employeeId + "' "
					+ "'" + blankSpaceParameter + "' "
					+ "'" + errorMessageParameter + "' "
					+ "'" + criticalFlag + "' "
					+ "'" + errorDateParameter + "' "
					+ "'" + errorTimeParameter + "' "
					+ "'" + opridErrorParameter + "' "
					+ "'" + yesOrNoParameter + "')\" ";
		System.out.println(command);

		parameterList.add("errorProgramParameter");
		parameterList.add("employeeId");
		parameterList.add("blankSpaceParameter");
		parameterList.add("errorMessageParameter");
		parameterList.add("criticalFlag");
		parameterList.add("errorDateParameter");
		parameterList.add("errorTimeParameter");
		parameterList.add("opridErrorParameter");
		parameterList.add("yesOrNoParameter");
		
		parameterMap.put("errorProgramParameter", errorProgramParameter);
		parameterMap.put("employeeId", employeeId);
		parameterMap.put("blankSpaceParameter", blankSpaceParameter);
		parameterMap.put("errorMessageParameter", errorMessageParameter);
		parameterMap.put("criticalFlag", criticalFlag);
		parameterMap.put("errorDateParameter", errorDateParameter);
		parameterMap.put("errorTimeParameter", errorTimeParameter);
		parameterMap.put("opridErrorParameter", opridErrorParameter);
		parameterMap.put("yesOrNoParm", yesOrNoParameter);
		
		AS400Package as400Package = new AS400Package(processName, parameterList, parameterMap);
		return as400Package;
	}
	
}

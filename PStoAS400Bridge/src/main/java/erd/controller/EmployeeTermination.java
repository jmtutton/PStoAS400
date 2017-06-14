package erd.controller;

import java.sql.Date;

import erd.model.PsActionReason;
import erd.model.TriggerEmployee;
import erd.DateUtil;

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
	
	EmployeeTermination(TriggerEmployee trigger) {
		this.trigger = trigger;
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
	String employeeId; //$PSEmpl
	String terminationMonth; //$PSTermMnth  //Two digit representation of month
	String terminationDay; //$PsTermDay  //Two digit representation of day
	String terminationYear; //$PSTermYr  //Four digit representation of year
	String rehireMonth; //$PSReHireMnth  //Two digit representation of month
	String rehireDay; //$PSReHireDay  //Two digit representation of day
	String rehireYear; //$PSReHireYr  //Four digit representation of year
	String voluntaryOrInvoluntary; //$PSVolInvol
	String terminationCode; //$PSTermCode
	String auditOperatorId; //$PSAuditOperId
	String terminationReason; //$PSTermReason is at most 30 positions long, but HRZ102A receives it with a length of 35 positions; needs to be padded to 35

	Date terminationDate;
	Date rehireDate;
	
	String action; //$ACTION
	String actionReason; //$PSAction_Reason
	String businessUnit; //$Business_Unit
	String command; //$Command
	String company; //$Company
	String completionStatus; //$CompletionStatus
	Date date; //$PSDate
	Date dateIn; //$PSDateIn
	Date effectiveDate; //$PSEffdt
	Integer effectiveSequence; //$PSEffSeq
	String deptartmentId; //$DeptId
	String employeeClass; //$Empl_Class
	String employeeStatus; //$Empl_Status
	String errorProgramParm = "HRZ102A"; //$ErrorProgramParm = 'HRZ102A'
	String fullOrPartTime; //$Full_Part_Time
	String jobCode; //$JobCode
	String library; //$Library
	String location; //$Location
	String operatorId; //$PSOprid
	boolean poiFlag = false;  //$PoiFlag  //Person of Interest
	
	String processName;  //$WrkProcess
	String state; //$PSState
	String status; //#Status
	String wrkEmployeeId; //$Wrk_Emplid
	
	String actionCode; //$ADAction_Code
	String legacyOperatorId; //$ADLegOprid
	
	Integer wrkSequence; //#Wrk_Sequence
	Integer wrkSeqNbr; //#WrkSeqNbr

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
	 * Get-Trigger-Data - from ZHRI100A.SQR
	 * This procedure will get the trigger data that needs to be interfaced
	 */
	private void ZHRI100AGetTriggerData() {
		wrkSequence = trigger.getSequenceNumber();
		auditOperatorId = trigger.getOperatorID().trim();
		employeeId = trigger.getEmployeeId().trim();
//		Move $PSEmplid to #Wrk_EmplID1
//		LET $Wrk_Emplid2 =  edit(#Wrk_EmplID1,'099999999') //this value is used as a parameter in the error routine call //a 9-digit number left padded with zeros.
		effectiveDate = trigger.getEffectiveDate();
		effectiveSequence = trigger.getEffectiveSequence();
		processName = trigger.getProcessName().trim();
//		DO Check-If-Contractor
//		LET $PoiFlag = 'N'
		poiFlag = false;
	}
	
	/**
	 * Call-Programs - from ZHRI100A.SQR
	 * Subroutine will call appropriate programs
	 */
	private void ZHRI100ACallPrograms() {
		//WHEN = 'ZHRI102A'
		//!Move fields to be used in the called SQC
		//MOVE #Wrk_Sequence to #WrkSeqNbr
		wrkSeqNbr = wrkSequence;  //TODO: find where #Wrk_Sequence is set
		//LET $PSAuditOperId = $AuditOprid
		auditOperatorId = trigger.getOperatorID();
        //LET $PSDateIn = $PSEffdt
		dateIn = trigger.getEffectiveDate();
        //LET $Wrk_Emplid = $PSEmplid
		wrkEmployeeId = trigger.getEmployeeId();
        //LET $ADAction_Code = 'T'
		actionCode = "T";
        //LET $ADLegOprid = ''
		legacyOperatorId = "";
        //DO HR02-Process-Main    !ZHRI102A.SQC
	}
	
	/**
	 * HR02-Initialize-Fields - from ZHRI102A.SQC
	 * Initialize the fields to ensure that that they all start out blank.
	 */
	private void HR02InitializeFields() {
		employeeId = " "; //$PSEmpl = ' '
		terminationMonth = " "; //$PSTermMnth = ' '
		terminationDay = " "; //$PsTermDay = ' '
		terminationYear = " "; //$PSTermYr = ' '
		rehireMonth = " "; //$PSReHireMnth = ' '
		rehireDay = " "; //$PSReHireDay = ' '
		rehireYear = " "; //$PSReHireYr = ' '
		voluntaryOrInvoluntary = " "; //$PSVolInvol = ' '
		terminationCode = " "; //$PSTermCode = ' '
		terminationReason = " "; //$PSTermReason = ' '
		actionReason = " "; //$PSAction_Reason = ' '
		//$PSAuditOperId = substr($PSAuditOperId,2,5)
		auditOperatorId = trigger.getOperatorID().trim().substring(1); //strips the 'E' off of the employee id
		//$ErrorProgramParm = 'HRZ102A'
		errorProgramParm = "HRZ102A";
	}

	/**
	 * HR02-Process-Main - from ZHRI102A.SQC
	 * This is the main processing procedure
	 */
	private void HR02ProcessMain() {
		dateIn = trigger.getEffectiveDate();
//		DO HR02-Initialize-Fields
//		MOVE 1 to #NumberOfDays   !Set the number of days to add to the passed date
		Integer numberOfDays = 1;
//		DO dtu-add-days($PSDateIn, #NumberOfDays, $PSDate)    !Add one day to the date using DateMath.sqc
		date = (java.sql.Date) DateUtil.addDays(dateIn, numberOfDays);
//		show 'zhri102a.sqc $PSDate: ' $PSDate
//		DO HR02-Get-Job
//		DO ZHRI100A.Get-Oprid
//		LET $ADLegOprid = $PSOprid
		legacyOperatorId = operatorId;
//		LET $PSEmpl = $PSOprid
		employeeId = operatorId;
//		IF $PSEmpl <> '' AND $PSEmpl <> ' '  !If the new oprid is not blank and it is not null on return
		if(employeeId != null && !(employeeId.trim()).isEmpty()) {
//		    LET $PSDate = $PSDateIn    !Move the original date back to psdate.
			date = trigger.getEffectiveDate();
// 			DO HR02-Process-Data
//		END-IF    !$PSEmpl <> '' and $PSEmpl <> ' '
		}
		terminationReason = PsActionReason.findReasonDescriptionByActionAndActionReason(action, actionReason);
	}
	
	/**
	 * Get-Oprid - from ZHRI100A.SQR
	 * This routine gets the operator id from the operator definition table
	 */
	private void ZHRI100AGetOprid() {
//		LET $Found = 'N'
//		LET $PSOprid = ''
//		IF (#indexNum = 0 and $PoiFlag = 'Y')  or $PoiFlag = 'N'
//			DO get-LegID-for-seq0
//		ELSE 
//		  	IF (#indexNum <> 0 and $PoiFlag = 'Y')
//		    	DO get-LegID-for-seqnum
//		  	END-IF
//		END-IF
//		!If an oprid does not exist for the employee, create one
//		IF ($Found = 'N')
//		     DO Get-Legacy-Oprid
//		     LET $PSOprid = $LegEmplid
//		END-IF    !$Found = 'N'
	}
	
	/**
	 * HR02-Process-Data - from ZHRI102A.SQC
	 * This routine moves 'N' to change address parameter and calls the RPG program.
	 */
	private void HR02ProcessData() {
//		LET $PSChange = 'N'
		//Rehire
//		IF ($PSAction = 'REH') AND ($PSAction_Reason = 'REH')
//		  	LET $PSRehireYr = substr($PSDate,1,4)
//		  	LET $PSRehireMnth = substr($PSDate,6,2)
//		  	LET $PSRehireDay = substr($PSDate,9,2)
//		END-IF
		//Termination
//		IF ($psAction = 'TER' OR $psAction = 'RET' OR $psAction = 'TWP' OR $psAction = 'TWB')
//		  	LET $PSTermYr = substr($PSDate,1,4)
//		  	LET $PSTermMnth = substr($PSDate,6,2)
//		  	LET $PSTermDay = substr($PSDate,9,2)
//		END-IF
//		DO Remove-Non-Letters-Numbers ($PSTermReason,$PSTermReason)        !ZRmvSpcChr.sqc
//		DO HR02-Trim-Parameters     !Routine to trim the parameters to insure that there are not a larger number of blanks being passed
//		LET $Part2 = 'Parm('''         ||
//		             $PSEmpl             ||
//		             ''' '''         ||
//		             $PSTermMnth         ||
//		             ''' '''         ||
//		             $PsTermDay          ||
//		             ''' '''         ||
//		             $PSTermYr           ||
//		             ''' '''         ||
//		             $PSReHireMnth       ||
//		             ''' '''         ||
//		             $PSReHireDay        ||
//		             ''' '''         ||
//		             $PSReHireYr         ||
//		             ''' '''         ||
//		             $PSVolInvol         ||
//		             ''' '''         ||
//		             $PSTermCode         ||
//		             ''' '''         ||
//		             $PSAuditOperId      ||
//		             ''' '''         ||
//		             $PSTermReason       ||
//		             ''')" '
//		LET $Part1 = '"CALL ' || $Library ||'/HRZ102A '
//		LET $Command = $Part1||$Part2
//		DO Call-System             !From ZHRI100A.SQR
//		IF (#Status = 0)
//			LET $CompletionStatus = 'C'   !Completed Normally
//		END-IF    !#Status = 0
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
	private void HR02GetJob() {
//		BEGIN-SELECT
//			PS_Job.ACTION
//			PS_Job.ACTION_REASON
//			PS_Job.Location
//			PS_Job.Full_Part_Time
//			PS_Job.Company
//			PS_Job.Business_Unit
//			PS_Job.Empl_Class
//			PS_Job.Empl_Status
//			PS_Job.DeptId
//			PS_Job.JobCode
//		   	LET $PSAction = &PS_Job.ACTION
//		   	LET $PSAction_Reason = &PS_Job.ACTION_REASON
//		   	LET $PSLocation = &PS_Job.Location
//		   	LET $PSCompany = &PS_Job.Company
//		   	LET $PSBusinessUnit = &PS_Job.Business_Unit
//		   	LET $PSEmplClass = &PS_Job.Empl_Class
//		   	LET $PSEmplStatus = &PS_Job.Empl_Status
//		   	LET $PSDeptId = &PS_Job.DeptId
//		   	LET $PSJobCode = &PS_Job.JobCode
//		   	LET $Wrk_AD_JobDataBuild = 'Y'
//		   	LET $PSAction = rtrim($PSAction,' ')
//		   	LET $PSAction_Reason = rtrim($PSAction_Reason,' ')
//		   	IF $PSAction <> 'REH'
//		        DO HR02-Get-Action-Reason
//		   	END-IF    !$PSAction <> 'REH'
//			FROM PS_Job PS_Job
//			WHERE PS_Job.Emplid = $PSEmplid
//		  		AND TO_CHAR(PS_Job.EFFDT, 'YYYY-MM-DD') = $PSDate
//		  		AND PS_Job.EFFSEQ = #PSEFFSEQ
//		  		AND PS_Job.EMPL_RCD = 0
//		END-SELECT
	}

	/**
	 * HR02-Get-Action-Reason - from ZHRI102A.SQC
	 * This routine will determine if a termination was voluntary or involuntary based on Action and Action Reason codes.
	 */
	private void HR02GetActionReason() {
//		LET $Found = 'N'   !Initialize the record found variable
//		BEGIN-SELECT
//			PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMCD
//			LET $PSVolInvol = &PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMCD
//			PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMRSN
//			LET $PSTermCode = &PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMRSN
//			IF $PSTermCode = 'O' and $PSAction = 'TER'
//				DO HR02-Get-Reason-Description
				terminationReason = PsActionReason.findReasonDescriptionByActionAndActionReason(action, actionReason);
//			END-IF    !$PSTermCode = 'O'
//			LET $Found = 'Y'     !Mark that a record was found
//			FROM PS_ZHRT_TRMRS_CREF PS_ZHRT_TRMRS_CREF
//			WHERE PS_ZHRT_TRMRS_CREF.STATUS = 'A'
//				AND PS_ZHRT_TRMRS_CREF.ACTION = $PSAction
//				AND PS_ZHRT_TRMRS_CREF.ACTION_REASON = $PSAction_Reason
//		END-SELECT
//		IF $Found = 'N'
//			LET $ErrorMessageParm = 'Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF'
//			DO Call-Error-Routine       !From ZHRI100A.SQR
//			!Default the Action and reason in the legacy system
//			LET $PSVolInvol = 'V'
//			LET $PSTermCode = 'O'
//			LET $PSTermReason = 'ACTION REASON NOT SELECTED IN PS'
//		END-IF    !$Found = 'N'
	}

	/**
	 * ZHRI100A.Check-If-Contractor
	 * Checks to see if the employee is a contractor
	 */
	private void ZHRI100ACheckIfContractor() {
//		LET $Found = 'N'
//		BEGIN-SELECT
//			'X'
//			LET $Found = 'Y'
//			FROM PS_JOB RJ
//			WHERE RJ.EMPLID = $PSEmplid
//				AND RJ.EMPL_CLASS = 'R'
//		  		AND RJ.EFFDT = (SELECT MAX(EFFDT) FROM  PS_JOB RJ2 WHERE  RJ2.EMPLID = RJ.EMPLID
//		                     	AND  RJ2.EMPL_RCD = RJ.EMPL_RCD AND  RJ2.EFFDT <= $AsOfToday)
//		  		AND RJ.EFFSEQ = (SELECT MAX(EFFSEQ) FROM PS_JOB RJ3 WHERE RJ3.EMPLID = RJ.EMPLID
//		                     	AND  RJ3.EMPL_RCD = RJ.EMPL_RCD AND RJ3.EFFDT = RJ.EFFDT)
//		END-SELECT
	}

	/**
	 * ZHRI100A.Check-Interface-Runfile
	 * This procedure will check the existence run file
	 */
	private void ZHRI100ACheckInterfaceRunfile() {
//		LET $RUN_FILEPATH = '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'  //concatenate
//		LET #file_exists = exists($RUN_FILEPATH)
//		IF #file_exists = 0
//		   LET #run_flag = 1
//		ELSE
//		   LET #run_flag = 0
//		END-IF
	}
	
	/**
	 * ZHRI100A.Check-If-Correct102A
	 * Checks to see if 102A process has JOB row
	 */
	private void ZHRI100ACheckIfCorrect102A() {
//		LET $OK-to-process = 'N'
//		IF $WrkProcess = 'ZHRI102A'
//			DO dtu-add-days($PSEffdt,1,$dt102)
//		END-IF
//		BEGIN-SELECT
//			'XX'
//			LET $OK-to-process = 'Y'
//			FROM PS_JOB RD WHERE RD.EMPLID = $PSEmplid AND to_char(RD.EFFDT, 'YYYY-MM-DD') = $dt102
//		END-SELECT
	}
	
	/**
	 * ZHRI100A.Get-Variable
	 * This new procedure will get the variables from PS_ZPTT_VARIABLES
	 */
	private void ZHRI100AGetVariable() {
//		BEGIN-SELECT
//			VAR.ZPTF_VARIABLE_VAL
//			MOVE &VAR.ZPTF_VARIABLE_VAL to $PSZPTT_VARIABLE_VAL
//			FROM  PS_ZPTT_VARIABLES VAR WHERE VAR.PRCSNAME = 'ZHRI100A'
//				AND VAR.DBNAME = (SELECT dbname FROM PSDBOWNER)
//				AND VAR.VARIABLE_NAME = $Variable_Needed
//		END-SELECT
	}

	/**
	 * ZHRI100A.Intialize-AD-WrkFields
	 * 
	 */
	private void ZHRI100AIntializeADWrkFields() {
//		LET $Wrk_AD_NamesBuild = 'N'
//		LET $Wrk_AD_PersDataBuild = 'N'
//		LET $Wrk_AD_PersDataEffdtBuild = 'N'
//		LET $Wrk_AD_JobDataBuild = 'N'
//		LET $Wrk_AD_CountryCdBuild = 'N'
//		LET $Wrk_AD_Getbusinessphone = 'N'
//		LET $ADAction_Code = ''
//		LET $ADLegOprid = ''
//		LET $ADEmplid = ''
//		LET $ADFirstName = ''
//		LET $ADLastName = ''
//		LET $ADLocation = ''
//		LET $ADJobCd  = ''
//		LET $ADJobDescr = ''
//		LET $ADEmplStatus = ''
//		LET $ADEmplStatusDescr = ''
//		LET $ADHireDt = 0
//		LET $ADTermDt = 0
//		LET $ADNamesuffix = ''
//		LET $ADCountry = ''
//		LET $ADJobStartDt = 0
//		LET $ADJobStartYr = 0
//		LET $ADJobStartMnth = 0
//		LET $ADJobStartDay = 0
//		LET $ADPrfName = ''
//		LET $ADFullPartTime = ''
//		LET $ADEmplClass = ''
//		LET $ADDeptID  = ''
//		LET $ADBusinessPhone = ''
//		LET $ADEmployeeFax = ''
//		LET $ADSupervisorID = ''
//		LET $ADLegSupervisorID = ''
//		LET $ADLangCd = ''
//		LET $WrkCreateFile = 'N'
//		LET $WrkADEffdt = ''
	}
	
	/**
	 * ZHRI100A.Process-Main
	 * This is the process controlling procedure.
	 */
	private void ZHRI100AProcessMain() {
//		! This gets the oracle_sid
//		LET $PS_HOME = getenv('PS_HOME')
//		LET $AD_HOME = $PS_HOME || '/data/activedir/'    !Path for Active Directory
//		LET $ORACLE_SID = getenv('ORACLE_SID')
//		UPPERCASE $ORACLE_SID
//		!Returns name of AS/400 machine for use in zbas002b.sh
//		LET $Variable_Needed = ' '
//		LET $Variable_Needed = 'RMTSVR'
//		DO  Get-Variable
//		LET $RMTSVR = $PSZPTT_VARIABLE_VAL
//		LET $RexecScript = '/usr/local/barch/' || $ORACLE_SID || '/scripts/zbas002b.sh'
//		!Returns library name on AS/400 where programs reside
//		LET $Variable_Needed = ' '
//		LET $Variable_Needed = 'AS400library'
//		DO  Get-Variable
//		LET $Library = $PSZPTT_VARIABLE_VAL
//		!Returns IP address of NT server
//		LET $Variable_Needed = ' '
//		LET $Variable_Needed = 'RMTNTADSVR'
//		DO Get-Variable
//		LET $RMTNTADSVR = $PSZPTT_VARIABLE_VAL
//		LET $WrkCriticalFlag = 'N'
//		LET #run_flag = 1
//		WHILE #run_flag = 1        !Never ending loop
//		   	DO Check-interface-runfile
//		   	DO Get-Trigger-Data       !Process the interface requests
//		   	DO Commit-Transaction
//		   	LET $Command = 'sleep 15'  !After interface run wait 15 seconds and do it again  !sree**rehost        !ZHR_MOD_ZHRI100A_sleep
//		   	CALL System Using $Command #status Wait            !sree**rehost     !ZHR_MOD_ZHRI100A_sleep
//		   	DO Get-Trigger-Data-NonEmp  !calls the procesdure for POIs ad multiple EIDs
//		   	DO Commit-Transaction
//		   	LET $Command_non = 'sleep 15'  !after the main trigger table wait for 15 secs
//		   	CALL System Using $Command_non #status Wait
//		   	IF $file_open = 'Y'                        !ZHR_MOD_ZHRI100A_sleep
//		   		CLOSE 1                               !ZHR_MOD_ZHRI100A_sleep
//		   	END-IF                                     !ZHR_MOD_ZHRI100A_sleep
//		   	LET $file_open = 'N'                       !ZHR_MOD_ZHRI100A_sleep
//		END-WHILE   !1=1
//		LET $Command = 'mv' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.stop' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'
//		CALL System Using $Command #status Wait           !ZHR_MOD_ZHRI100A_sleep       
	}
	
	/**
	 * ZHRI100A.Call-System
	 * Executes a command line statement stored in the $Command Variable
	 */
	private void ZHRI100ACallSystem() {
//		LET #CommandLength = length($Command)             !Get the length of the command
//		LET #SubstrStartPos = 1    !Initiate the starting positions to show the first 100 positions
//		WHILE #SubstrStartPos <= #CommandLength
//			LET $ShowCommand = substr($Command,#SubstrStartPos,100)   !Substring 100 positions to show
//		    LET #SubstrStartPos = #SubstrStartPos + 100    !Increase the starting position by 100
//		END-WHILE   !#SubstrStartPos <= #CommandLength
//		LET $Command = $RexecScript || ' ' || $Command || ' ' || $RMTSVR  !changed for v8.3
//		DO GET-CURRENT-DATETIME  !Gets the current date and time using curdttim.sqc
//		CALL System Using $Command #Status Wait      !Execute the command that was built on the command waiting until completion
//		IF #status != 0
//			!error
//		  	LET $ErrorProgramParm = 'ZHRI100A'
//		  	LET $ErrorMessageParm = ' '
//		  	LET $ErrorMessageParm = 'Error executing Call System command, contact HR-PeopleSoft Oncall'
//		  	LET $WrkCriticalFlag  = 'Y'
//		  	DO Prepare-Error-Parms
//		  	IF $PoiFlag = 'N'
//		  		Do Call-Error-Routine
//		  	ELSE
//		    	Do Call-Error-Routine-NonEmp
//		  	END-IF
//		  	LET $WrkCriticalFlag  = 'N'
//		END-IF
	}
	
}

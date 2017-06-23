package erd.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import erd.DateUtil;
import erd.model.AS400Package;
import erd.model.CrossReferenceEmployeeId;
import erd.model.CrossReferenceMultipleEmployeeId;
import erd.model.HR036P;
import erd.model.PsDbOwner;
import erd.model.PsJob;
import erd.model.PszTriggerEmployee;
import erd.model.PszVariable;
import erd.model.PszXlat;
import erd.model.Zhri100aFields;

public class ZHRI100A {

//	public static String psEmpl; //$PSEmpl
//	public static String psOprid; //$PSOprid
//	public static String errorProgramParm = "HRZ102A"; //$ErrorProgramParm = 'HRZ102A'
	
	public static void main() {
		//begin-program
		//Get-Current-DateTime  //queries the database to get the current time. It also initializes a slew of string variables ($AsOfToday, $AsOfNow, $CurrentCentury, $ReportDate
		//Init-DateTime  //sets a collection of variables that can be used by the other procedures in datetime.sqc that format dates or do date arithmetic
		//Process-Main
		try {
			processMain();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Reset          !Reset.sqc
		//end-program
	}
	
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
	 * Process-Main from ZHRI100A,SQR
	 * This is the process controlling procedure.
	 * @throws InterruptedException 
	 */
	public static void processMain() throws InterruptedException {
		Zhri100aFields zhri100aFields;
		//Begin-Procedure Process-Main
		//ZHRI100A.Get-Variable
		zhri100aFields = initializeMainProperties();
		//LET $WrkCriticalFlag = 'N'
		zhri100aFields.criticalFlag = false;
		//LET #run_flag = 1
		Boolean runFlag = true;
		//while #run_flag = 1        !Never ending loop
		while(runFlag == true) {
			//ZHRI100A.Check-Interface-Runfile
//			runFlag = ZHRI100A_CheckInterfaceRunFile(zhri100aFields.oracleSystemId);
			//ZHRI100A.Get-Trigger-Data       !Process the interface requests
			ZHRI100A_getTriggerData(zhri100aFields);
			//TRANCTRL.Commit-Transaction
			//LET $Command = 'sleep 15'  !After interface run wait 15 seconds and do it again  !sree**rehost  !ZHR_MOD_ZHRI100A_sleep
			//Call System Using $Command #status Wait  !sree**rehost  !ZHR_MOD_ZHRI100A_sleep
			//sleep for 15 seconds (15000 milliseconds)
			Thread.sleep(15000);
			//IF $file_open = 'Y'
				//CLOSE 1
				//TODO:
			//END-IF
			//LET $file_open = 'N'
			runFlag = false; //*** I put this here for testing, so it stops after one iteration.
		//end-while   !1=1
		}
		//LET $Command = 'mv' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.stop' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'
		String command = "mv" + " " + "/usr/local/barch/" + zhri100aFields.oracleSystemId + "/work/hrinterface.stop" + " " + "/usr/local/barch/" + zhri100aFields.oracleSystemId + "/work/hrinterface.run";
		//Show  + $Command in Process-Main: ' $Command
		System.out.println("$Command in Process-Main: " + command);
		//Call System Using $Command #status Wait   !ZHR_MOD_ZHRI100A_sleep       
		//End-Procedure Process-Main
	}

	/**
	 * FTP-File from ZHRI100A.SQR
	 * This procedure will transfer the file from UNIX to NT server
	 */
	public static void ZHRI100A_ftpFile() {
		//BEGIN-PROCEDURE FTP-FILE !LJM-04/03/01-Rehost
		//IF #status != 0
		//END-IF
		//END-PROCEDURE
		//*** do nothing ***
	}

	/**
	 * Call-Programs from ZHRI100A.SQR
	 * Subroutine will call appropriate programs
	 * @param trigger
	 * @param zhri100aFields
	 * @return
	 */
	public static String ZHRI100A_callPrograms(PszTriggerEmployee trigger, Zhri100aFields zhri100aFields) {
		String completionStatus;
		//WHEN = 'ZHRI102A'
		//!Move fields to be used in the called SQC
		//MOVE #Wrk_Sequence to #WrkSeqNbr
//		wrkSeqNbr = wrkSequence;  //TODO: find where #Wrk_Sequence is set
		//LET $PSAuditOperId = $AuditOprId
		zhri100aFields.auditOperatorId = trigger.getOperatorId();
        //LET $PSDateIn = $PSEffDt
		zhri100aFields.effectiveDate = trigger.getEffectiveDate();
        //LET $Wrk_Emplid = $PSEmplId
		zhri100aFields.employeeId = trigger.getEmployeeId();
        //LET $ADAction_Code = 'T'
		zhri100aFields.adActionCode = "T";
        //LET $ADLegOprid = ''
		zhri100aFields.adLegacyOperatorId = "";
        //DO HR02-Process-Main    !ZHRI102A.SQC
		EmployeeTermination employeeTermination = new EmployeeTermination(trigger, zhri100aFields);
		completionStatus = employeeTermination.HR02_processMain();

		//Begin-Procedure Call-Programs
		//DO Intialize-AD-WrkFields
		//LET $TrigTaskFlag = ''
		//evaluate $WrkProcess
		//    when = 'ZHRI101A'
		//        !Move fields to be used in the called SQC
		//        LET $Wrk_Oprid = $AuditOprid
		//        LET $Wrk_Emplid = $PSEmplid
		//        LET $Wrk_Effdt = $PSEffdt
		//        move #PSEffseq to #Wrk_Effseq
		//        LET $Wrk_Process_Name = $WrkProcess
		//        LET $TrigTaskFlag = $WrkTaskFlag      !Surya Added - TEMPMAST 
		//        LET $Wrk_Inf_ = ' '
		//        LET $ADAction_Code = 'H'
		//        LET $ADLegOprid = ''
		//        DO HR01-Process-Main    !ZHRI101A.SQC
		//        break
		//    when = 'ZHRI102A'
		//        !Move fields to be used in the called SQC
		//        Move #Wrk_Sequence to #WrkSeqNbr
		//        LET $PSAuditOperId = $AuditOprid
		//        LET $PSDateIn = $PSEffdt
		//        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
		//        LET $ADAction_Code = 'T'
		//        LET $ADLegOprid = ''
		//        DO HR02-Process-Main    !ZHRI102A.SQC
		//        break
		//    when = 'ZHRI104A'
		//        !Move fields to be used in the called SQC
		//        LET $PSUserOprid = $AuditOprid
		//        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
		//        Move #PSEffseq to #WrkEffseq
		//        LET $ADAction_Code = 'C'
		//        LET $ADLegOprid = ''
		//        DO HR04-Process-Main    !ZHRI104A.SQC
		//        break
		//    when = 'ZHRI105A'
		//        !Move fields to be used in the called SQC
		//        LET $PSemp = $AuditOprid
		//        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
		//        LET $ADAction_Code = 'C'
		//        LET $ADLegOprid = ''
		//        LET $Wrk_ADCountryCdBuild = 'Y'                 !sree-UAAMOD
		//        DO HR05-Process-Main    !ZHRI105A.SQC
		//        break
		//    when = 'ZHRI106A'
		//        !Move fields to be used in the called SQC
		//        LET $Wrk_Oprid = $AuditOprid
		//        LET $Wrk_Emplid = $PSEmplid
		//        LET $Wrk_Effdt = $PSEffdt
		//        move #PSEffseq to #Wrk_Effseq
		//        LET $Wrk_Process_Name = $WrkProcess
		//        LET $ADAction_Code = 'R'
		//        DO HR01-Process-Main       !ZHRI101A.SQC
		//        break
		//    when = 'ZHRI107A'
		//        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
		//        LET $ADAction_Code = ''
		//        LET $ADLegOprid = ''
		//        DO HR07-Process-Main
		//        break
		//    when = 'ZHRI109A'
		//        !Move fields to be used in the called SQC
		//        LET $PSUserOprid = $AuditOprid
		//        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
		//        Move #PSEffseq to #WrkEffseq
		//        LET $ADAction_Code = 'C'
		//        LET $ADLegOprid = ''
		//        DO HR09-Process-Main        !ZHRI100A.SQC
		//        break
		//    when = 'ZHRI101D'     !Row deleted on hire
		//        LET $ErrorProgramParm = 'HRZ101A'
		//        LET $ErrorMessageParm = 'A row was deleted on the hire process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when = 'ZHRI102D'     !Row deleted on term
		//        LET $ErrorProgramParm = 'HRZ102A'
		//        LET $ErrorMessageParm = 'A row was deleted on the termination process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when = 'ZHRI104D'     !Row deleted on jobstatus change
		//        LET $ErrorProgramParm = 'HRZ104A'
		//        LET $ErrorMessageParm = 'A row was deleted on the job-profile process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when = 'ZHRI105D'     !Row deleted on demographis change
		//        LET $ErrorProgramParm = 'HRZ105A'
		//        LET $ErrorMessageParm = 'A row was deleted on the demographics process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when = 'ZHRI106D'     !Row deleted on rehire
		//        LET $ErrorProgramParm = 'HRZ101A'
		//        LET $ErrorMessageParm = 'A row was deleted on the re-hire process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when = 'ZHRI107D'     !Row deleted on
		//        LET $ErrorProgramParm = 'HRZ107A'
		//        LET $ErrorMessageParm = 'A row was deleted on the dates process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when = 'ZHRI109D'
		//        LET $ErrorProgramParm = 'HRZ109A'
		//        LET $ErrorMessageParm = 'A row was deleted on the group transfer process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when-other
		//        LET $CompletionStatus = 'E'     !update to an E to prevent looping and to mark the record in error
		//        DO Update-Trigger-Row
		//        break
		//end-evaluate
	//End-Procedure Call-Programs
		return completionStatus;
	}

	/**
	 * Check-If-POI-Termed from ZHRI100A.SQR
	 * This routine checks if it is a POI to EMP transfer. If it is then the the flag is changed to W and wait for Hire
	 * @param 
	 * @param 
	 * @return
	 */
	public static Boolean ZHRI100A_checkIfPoiTermed() {
		return null;

	}

	/**
	 * Get-Trigger-Data-NonEmp from ZHRI100A.SQR
	 * This procedure will get the trigger data for non employees and multiple EIDs that needs to be interfaced
	 * @param 
	 * @param 
	 * @return
	 */
	public static void ZHRI100A_getTriggerDataNonEmp() {
		
	}

	/**
	 * Call-Programs-NonEmp from ZHRI100A.SQR
	 * Subroutine will call appropriate programs for Non Emp
	 * @param 
	 * @param 
	 * @return
	 */
	public static void ZHRI100A_callProgramsNonEmp() {
		
	}

	/**
	 * Get-Trigger-Data-POI-Emp-Convert from ZHRI100A.SQR
	 * This procedure will get the trigger data that needs to be interfaced
	 * @param 
	 * @param 
	 * @return
	 */
	public static void ZHRI100A_getTriggerDataPoiEmpConvert() {
		
	}

	/**
	 * Update-Trigger-Row-NonEmp from ZHRI100A.SQR
	 * This routine update the trigger file flag switch for Non Emp
	 * @param 
	 * @param 
	 * @return
	 */
	public static void ZHRI100A_updateTriggerRowNonEmp() {
		
	}

	/**
	 * ZHRI100ABuiltWhereClauseDelay from ZHRI100A.SQR
	 * Get the WHERE Clause for the delay hours in POI to EMP Transfer
	 */
	public static String ZHRI100A_buildWhereClauseDelay() {
		String xWhere = PszXlat.findOutput01ByInput01AndInput02("TEMPMAST", "LEGACY-DELAY-HRS");
		String where1 = "AND SYSTIMESTAMP  >= TR.UPDATED_DATETIME + INTERVAL " + "'" + xWhere + "'" + " HOUR";
		//XWER.ZPTF_OUTPUT_01
		//LET $XWHERE = LTRIM(RTRIM(&XWER.ZPTF_OUTPUT_01,' '),' ')
		//LET $WHERE1 = 'AND SYSTIMESTAMP  >= TR.UPDATED_DATETIME + INTERVAL '|| '''' || $XWHERE || '''' || '  HOUR'
		//from PS_ZPTT_XLAT_TBL XWER
		//where XWER.ZPTF_INPUT_01 = 'TEMPMAST'
		//and XWER.ZPTF_INPUT_02 = 'LEGACY-DELAY-HRS'       
		//end-select
		return where1;
	}

	/**
	 * Call-System from ZHRI100A.SQR
	 * Executes a command line statement stored in the $Command Variable
	 * @param command
	 * @param zhri100aFields
	 * @return 0 if success, non-zero if error
	 */
	public static Integer ZHRI100A_callSystem(String command, Zhri100aFields zhri100aFields) {
		Integer status; //#Status
//		String showCommand;
//		Integer commandLength = command.length();
//		Integer substringStartPosition = 1;
		//BEGIN-PROCEDURE CALL-SYSTEM
		//LET #CommandLength = length($Command)   !Get the length of the command
//		commandLength = command.length();
		//LET #SubstrStartPos = 1  !Initiate the starting positions to show the first 100 positions
//		substringStartPosition = 1;
		//WHILE #SubstrStartPos <= #CommandLength
//		while(substringStartPosition <= commandLength) {
			//LET $ShowCommand = SUBSTR($Command, #SubstrStartPos, 100)  !Substring 100 positions to show
//			showCommand = command.substring(substringStartPosition, 100);
			//LET #SubstrStartPos = #SubstrStartPos + 100  !Increase the starting position by 100
//			substringStartPosition += 100;
			//SHOW $ShowCommand  !ZHR_MOD_ZHRI100A_110B
			System.out.println("command: " + command);
		//END-WHILE   !#SubstrStartPos <= #CommandLength
//		}
		//LET $Command = $RexecScript || ' ' || $Command || ' ' || $RMTSVR
		command = zhri100aFields.remoteExecScript + " " +  command + " " + zhri100aFields.remoteServerName;
		//SHOW '$Command=> ' $Command
		System.out.println("command: " + command);
		//DO Get-Current-Datetime  !Gets the current date and time using curdttim.sqc
		java.util.Date currentDate = new java.util.Date();
		//SHOW 'Calling Command at: ' $SysDateTime  !Surya Added - TEMPMAST 
		System.out.println("Calling Command at: " + currentDate);
		//Call System Using $Command #Status Wait  !Execute the command that was built on the command waiting until completion
		status = callSystemUsingCommand(command, zhri100aFields);
		//IF #status != 0
		if(!"0".equals(status)) {
			//!error
			//LET $ErrorProgramParm = 'ZHRI100A'
			zhri100aFields.errorProgramParameter = "ZHRI100A";
			//LET $ErrorMessageParm = ' '
			//LET $ErrorMessageParm = 'Error executing Call System command, contact HR-PeopleSoft On-Call'
			zhri100aFields.errorMessageParameter = "Error executing Call System command, contact HR-PeopleSoft Oncall";
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.criticalFlag = true;
			//DO Prepare-Error-Parms
			ZHRI100A_prepareErrorParms();
			//IF $PoiFlag = 'N'
			if(!zhri100aFields.poiFlag) {
				//DO Call-Error-Routine
				//TODO: 
				command = ZHRI100A_callErrorRoutine("HRZ100A", zhri100aFields);
			//ELSE
			}
			else {
				//DO Call-Error-Routine-NonEmp
				//TODO: 
				command = ZHRI100A_callErrorRoutineNonEmp("HRZ100A", zhri100aFields);
			//END-IF
			}
			//LET $WrkCriticalFlag  = 'N'
			zhri100aFields.criticalFlag = false;
		}
		//END-IF
		return status;
		//END-PROCEDURE CALL-SYSTEM
	}

	/**
	 * Prepare-Error-Parms from ZHRI100A.SQR
	 * Makes sure that the parms are the correct length for the error routine RPG program to recieve them
	 * @param 
	 * @param 
	 * @return 
	 */
	private static void ZHRI100A_prepareErrorParms() {
		//Begin-Procedure Prepare-Error-Parms
		//!Prepare the date and time parms
		//Do Get-Current-DateTime                                 !Get the current date and time
		//Let $AddDateErrorParm = datetostr(strtodate($AsOfToday,'DD-MON-YYYY'),'YYYYMMDD') !sree**rehost
		//Let $AddTimeErrorParm =    substr($Out,10,2)    ||    substr($Out,13,2)    ||    substr($Out,16,2)
		//Let $OpridErrorParm   =    Substr($AuditOprid,2,5)
		//End-Procedure Prepare-Error-Parms
	}

	/**
	 * Call System Using $Command #Status Wait
	 * Execute the command that was built on the command waiting until completion
	 * @param command
	 * @param zhri100aFields
	 * @return 0 if success, non-zero if error
	 */
	//TODO: build it
	private static Integer callSystemUsingCommand(String command, Zhri100aFields zhri100aFields) {
		// TODO Auto-generated method stub
		System.out.println("command: " + command);
		System.out.println("zhri100aFields: " + zhri100aFields.toString());
		return 0;
	}

	/**
	 * Call-Error-Routine-NonEmp from ZHRI100A.SQR
	 * Builds the command and calls the error routine
	 * @param processName
	 * @param zhri100aFields
	 * @return
	 */
	private static String ZHRI100A_callErrorRoutineNonEmp(String processName, Zhri100aFields zhri100aFields) {
		String blankSpaceParameter = " ";
		String criticalFlag = zhri100aFields.criticalFlag ? "Y" : "N";
		Calendar now = Calendar.getInstance();
		String errorDateParameter =  now.get(Calendar.MONTH) + "/" + now.get(Calendar.DATE) + "/" + now.get(Calendar.YEAR);
		String errorTimeParameter = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);
		String yesOrNoParameter = "Y"; //TODO: What should this value really be called
		//!Make Sure that the ErrorMessageParm is always 75 Characters long
		String errorMessageParameter = String.format("%75s", zhri100aFields.errorMessageParameter);
		String command = "\"CALL " + zhri100aFields.as400Library + "/" + processName + " "
					+ "PARM('"
					+ "'" + zhri100aFields.errorProgramParameter + "' "
					+ "'" + zhri100aFields.employeeId + "' "
					+ "'" + zhri100aFields.indexNumber + "' "
					+ "'" + blankSpaceParameter + "' "
					+ "'" + errorMessageParameter + "' "
					+ "'" + criticalFlag + "' "
					+ "'" + errorDateParameter + "' "
					+ "'" + errorTimeParameter + "' "
					+ "'" + zhri100aFields.operatorId + "' "
					+ "'" + yesOrNoParameter + "')\" ";
		System.out.println(command);
		return command;
	}

	/**
	 * Call-Error-Routine from ZHRI100A.SQR
	 * @param processName
	 * @param zhri100aFields
	 * @return
	 */
	public static String ZHRI100A_callErrorRoutine(String processName, Zhri100aFields zhri100aFields) {
		List<String> parameterList = new ArrayList<String>();
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		String blankSpaceParameter = " ";
		String criticalFlag = zhri100aFields.criticalFlag ? "Y" : "N";
		Calendar now = Calendar.getInstance();
		String errorDateParameter =  now.get(Calendar.MONTH) + "/" + now.get(Calendar.DATE) + "/" + now.get(Calendar.YEAR);
		String errorTimeParameter = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);
		String yesOrNoParameter = "Y"; //TODO: What should this value really be called
		//!Make Sure that the ErrorMessageParm is always 75 Characters long
		String errorMessageParameter = String.format("%75s", zhri100aFields.errorMessageParameter);
		String command = "\"CALL " + zhri100aFields.as400Library + "/" + processName + " "
					+ "PARM('"
					+ "'" + zhri100aFields.errorProgramParameter + "' "
					+ "'" + zhri100aFields.employeeId + "' "
					+ "'" + blankSpaceParameter + "' "
					+ "'" + errorMessageParameter + "' "
					+ "'" + criticalFlag + "' "
					+ "'" + errorDateParameter + "' "
					+ "'" + errorTimeParameter + "' "
					+ "'" + zhri100aFields.operatorId + "' "
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
		
		parameterMap.put("errorProgramParameter", zhri100aFields.errorProgramParameter);
		parameterMap.put("employeeId", zhri100aFields.employeeId);
		parameterMap.put("blankSpaceParameter", blankSpaceParameter);
		parameterMap.put("errorMessageParameter", errorMessageParameter);
		parameterMap.put("criticalFlag", criticalFlag);
		parameterMap.put("errorDateParameter", errorDateParameter);
		parameterMap.put("errorTimeParameter", errorTimeParameter);
		parameterMap.put("opridErrorParameter", zhri100aFields.operatorId);
		parameterMap.put("yesOrNoParm", yesOrNoParameter);
		
		AS400Package as400Package = new AS400Package(processName, parameterList, parameterMap);
//		return as400Package;
		return command;
	}

	/**
	 * Check-If-Correct102A from ZHRI100A.SQR
	 * Checks to see if 102A process has JOB row
	 * @param psEmplId
	 * @param psEffectiveDate
	 * @param processName
	 * @return true if corresponding PsJob record found
	 */
	public static Boolean ZHRI100A_checkIfCorrect102A(String employeeId, Date effectiveDate, String processName) {
		//BEGIN-PROCEDURE CHECK-IF-CORRECT102A
		//LET $OK-To-Process = 'N'
		Boolean isOkToProcess = false;
		//IF $WrkProcess = 'ZHRI102A'
		if("ZHRI102A".equalsIgnoreCase(processName)) {
			//DO DTU-ADD-DAYS($PSEffDt, 1, $Dt102)
			//add a day to current effective date
			effectiveDate = DateUtil.addDays(effectiveDate, 1);
		}
		//END-IF
		//BEGIN-SELECT
		//'XX'
		//LET $OK-To-Process = 'Y'
		//FROM PS_JOB PS_JOB WHERE PS_JOB.EMPLID = $PSEmplId AND TO_CHAR(PS_JOB.EFFDT, 'YYYY-MM-DD') = $Dt102
		List<PsJob> psJobList = PsJob.findByEmployeeIdAndEffectiveDate(employeeId, effectiveDate);
    	if(psJobList != null && !psJobList.isEmpty()) {
    		isOkToProcess = true;
    	}
    	//END-SELECT
		return isOkToProcess;
		//END-PROCEDURE CHECK-IF-CORRECT102A
	}


	/**
	 * Build-Group-Where-Clause from ZHRI100A.SQR
	 * This routine will build the where clause that will select the correct Run-ID to use.
	 * @param whereClause
	 * @return SQL where clause
	 */
	public static String ZHRI100A_buildGroupWhereClause(String whereClause) {
		String alias = ""; //TODO: I can't find where this value is set
		String selectGroup = ""; //TODO: I can't find where this value is set
		//BEGIN-PROCEDURE BUILD-GROUP-WHERE-CLAUSE
		//LET $WhereClause = LTRIM(RTRIM($WhereClause,' '),' ')  !Remove leading and trailing blanks
		if(whereClause != null) {
			whereClause = whereClause.trim();
			//IF ($WhereClause = '')  !If the where clause is empty
			if(whereClause.isEmpty()) {
				//LET $WhereClause = 'WHERE ((' || $Alias || '.HMRGP = ' || '''' || $SelectGroup || ''''  !Add the first statement to the where clause
				whereClause = "WHERE ((" + alias + ".HMRGP = " + "'" + selectGroup + "'";
			}
			//else  !The where clause is not empty
			else {
				//LET $WhereClause = $WhereClause || ' OR ' || $Alias || '.HMRGP = ' || '''' ||$SelectGroup || ''''!append a condition to the where clause
				whereClause = whereClause+ " OR " + alias + ".HMRGP = " + "'" + selectGroup + "'";
			//END-IF  !$WhereClause = ''
			}
		}
		//END-PROCEDURE   BUILD-GROUP-WHERE-CLAUSE
		return whereClause;
	}

	/**
	 * Build-EmplId-Where-Clause from ZHRI100A.SQR
	 * Builds a where clause based on an employee id entered by the user.
	 * @return SQL where clause
	 */
	public static String ZHRI100A_buildEmplIdWhereClause() {
		String alias = ""; //TODO: I can't find where this value is set
		String runId = ""; //TODO: I can't find where this value is set
		//BEGIN-PROCEDURE BUILD-EMPLID-WHERE-CLAUSE
		//LET $WhereClause = 'WHERE ((' || $Alias || '.HMREMP = ' || '''' || $Run-Id || ''''  !Create the where clause
		String whereClause = "WHERE ((" + alias + ".HMREMP = " + "'" + runId + "'";
		//END-PROCEDURE BUILD-EMPLID-WHERE-CLAUSE
		return whereClause;
	}

	/**
	 * Get-OprId from ZHRI100A.SQR
	 * This routine gets the operator id from the operator definition table
	 * @param employeeId
	 * @param indexNumber
	 * @param zhri100aFields
	 * @return
	 */
	public static String ZHRI100A_getOprId(String employeeId, Integer indexNumber, Zhri100aFields zhri100aFields) {
		return ZHRI100A_getOprId(employeeId, zhri100aFields, indexNumber, null); 
	}
	public static String ZHRI100A_getOprId(String employeeId, Zhri100aFields zhri100aFields, Integer indexNumber, Integer eidIndexNumber) {
		//BEGIN-PROCEDURE GET-OPRID
		//LET $Found = 'N'
		Boolean found = false;
		//LET $PSOprId = ''
		String psOprId;
		//IF $PoiFlag = 'N'
		if(!zhri100aFields.poiFlag) {
			//MOVE 0 TO #indexNum
			indexNumber = 0;
		//END-IF
		}
		//IF #indexNum = 0
		if(indexNumber.equals(0)) {
//			DO GET-LEGID-FOR-SEQ0
			psOprId = CrossReferenceEmployeeId.ZHRI100A_getLegIdForSeq0(employeeId);
		}
		//ELSE
		else {
			//DO GET-LEGID-FOR-SEQNUM
			psOprId = CrossReferenceMultipleEmployeeId.ZHRI100A_getLegIdForSeqNum(employeeId, eidIndexNumber);
		//END-IF
		}
		if(psOprId != null && !psOprId.isEmpty()) {
			found = true;
		}
		//!If an OprId does not exist for the employee, create one
		//IF ($Found = 'N')
		if(!found) {
			//DO GET-LEGACY-OPRID                  !sree**10/04/01
			//LET $PSOprId = $LegEmplid
			psOprId = ZHRI100A_getLegacyOprId(employeeId, indexNumber, zhri100aFields.poiFlag);
		//END-IF  !$Found = 'N'
		}
		//END-PROCEDURE GET-OPRID
		return psOprId;
	}

	//!----------------------------------------------------------------------
	//! Procedure:  Insert-Error
	//! Desc:  This is an error routine to keep the program from abending when
	//!        an insert fails
	//!----------------------------------------------------------------------

	//!----------------------------------------------------------------------
	//! Procedure:  Update-Error
	//! Desc:  This is an error routine to keep the program from abending when
	//!        an insert fails
	//!----------------------------------------------------------------------
	
	//!----------------------------------------------------------------------
	//! Procedure:  Build-Active-Dir-Output-File
	//!----------------------------------------------------------------------
	
	//!----------------------------------------------------------------------
	//! Procedure:  AD-Get-Job-Data
	//! Desc:  Gets the Job data from the job table.
	//!----------------------------------------------------------------------
	
	//!----------------------------------------------------------------------
	//! Procedure: AD-Get-Job-Description
	//! Desc:  This routine will get the Job description for Active Directory File Build
	//!----------------------------------------------------------------------

	//	!----------------------------------------------------------------------
	//! Procedure: AD-Get-EmplStatus-Description
	//! Desc:  This routine will get the Employee Status description for Active Directory File Build
	//!----------------------------------------------------------------------

	//!----------------------------------------------------------------------
	//! Procedure:  AD-Get-JobStart-Date
	//! Desc:  Gets the Job Start date from the job table.
	//!----------------------------------------------------------------------

	//!----------------------------------------------------------------------
	//! Procedure:  AD-Get-Pers-Data-Effdt
	//! Desc:  This routine will get the Personal Data row for each of the
	//!        employee numbers entered in the trigger file.  Pers_Data_Effdt table
	//!        no longer has name info, so are using Names table.
	//!----------------------------------------------------------------------

	//!----------------------------------------------------------------------
	//! Procedure:  AD-Get-NameSuffix
	//! Desc:  This routine will get the Name Suffix row for each of the
	//!        employee numbers entered in the trigger file.
	//!----------------------------------------------------------------------

	//!----------------------------------------------------------------------
	//! Procedure:  AD-Get-Personal-Data
	//! Desc:  This routine will get the Personal Data row for each of the
	//!        employee numbers entered in the trigger file.
	//!----------------------------------------------------------------------

	//!----------------------------------------------------------------------
	//! Procedure: AD-Get-Country-Code
	//! Desc:  This routine will get the Country Code for Active Directory File Build
	//!----------------------------------------------------------------------

	//!----------------------------------------------------------------------
	//! Procedure:  AD-Get-Business-Phone
	//! Desc:  This routine gets the business phone number from the Peoplesoft
	//!        tables.
	//!----------------------------------------------------------------------

	//!----------------------------------------------------------------------
	//! Procedure:  AD-Get-Employee-Fax
	//! Desc:  This routine gets the business phone number from the Peoplesoft
	//!        tables.
	//!----------------------------------------------------------------------

	//!----------------------------------------------------------------------
	//! Procedure:  AD-Get-LegSuperviorID
	//!----------------------------------------------------------------------

	//!---------------------------------------------------------------------------------------
	//! Procedure:  AD-Get-Employment-Data
	//! Desc:  This routine will get the Termination Data row for Active Directory File Build
	//!---------------------------------------------------------------------------------------

	//!----------------------------------------------------------------------
	//! Procedure:  AD-Get-Names
	//! Desc:  This routine gets the Preferred Name from PS_Names for Active Directory File Build
	//!----------------------------------------------------------------------

	//!----------------------------------------------------------------------
	//! Procedure:  Write-Active-Dir-Output-File
	//!----------------------------------------------------------------------

	//!----------------------------------------------------------------------
	//! Procedure:  Intialize-AD-WrkFields
	//!----------------------------------------------------------------------
	/**
	 * 
	 * @return
	 */
	private static Zhri100aFields initializeMainProperties() {
		Zhri100aFields zhri100aFields = new Zhri100aFields();
		String wrkProcessName = "ZHRI100A";
		zhri100aFields.dbName = PsDbOwner.findDbName();
		//LET $PS_HOME = getenv('PS_HOME')  !This gets the oracle_sid
//		zhri100aFields.peopleSoftHomePath = System.getenv("PS_HOME");
		zhri100aFields.peopleSoftHomePath = "C:/people soft/";
		//LET $AD_HOME = $PS_HOME || '/data/activedir/'  !Path for Active Directory
		zhri100aFields.activeDirectoryHomePath = zhri100aFields.peopleSoftHomePath + "/data/activedir/"; //TODO: where is this used???
		//LET $ORACLE_SID = getenv('ORACLE_SID') 
//		zhri100aFields.oracleSystemId = System.getenv("ORACLE_SID");
		zhri100aFields.oracleSystemId = "PS90HRQA";
		if(zhri100aFields.oracleSystemId != null) {
			//UPPERCASE $ORACLE_SID                  
			zhri100aFields.oracleSystemId = zhri100aFields.oracleSystemId.toUpperCase();
		}
		//!Returns name of AS/400 machine for use in zbas002b.sh
		//LET $Variable_Needed = ' '            
		//LET $Variable_Needed = 'RMTSVR'       
		//DO  Get-Variable                      
		//LET $RMTSVR = $PSZPTT_VARIABLE_VAL      
		//Show '$RMTSVR: ' $RMTSVR                                                            
		zhri100aFields.remoteServerName = PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, zhri100aFields.dbName, "RMTSVR"); //TODO: where is this used???
		//LET $RexecScript = '/usr/local/barch/' || $ORACLE_SID || '/scripts/zbas002b.sh'     
		//Show '$RexecScript: ' $RexecScript                                                  
		zhri100aFields.remoteExecScript = "/usr/local/barch/" + zhri100aFields.oracleSystemId + "/scripts/zbas002b.sh"; //TODO: where is this used???
		//!Returns library name on AS/400 where programs reside
		//LET $Variable_Needed = ' '            
		//LET $Variable_Needed = 'AS400library' 
		//DO  Get-Variable                      
		//LET $Library = $PSZPTT_VARIABLE_VAL   
		//Show '$Library: ' $Library                                                          
		zhri100aFields.as400Library = PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, zhri100aFields.dbName, "AS400library");
		//!Returns IP address of NT server
		//LET $Variable_Needed = ' '             
		//LET $Variable_Needed = 'RMTNTADSVR'    
		//DO Get-Variable                        
		//LET $RMTNTADSVR = $PSZPTT_VARIABLE_VAL 
		//Show '$RMTNTADSVR: ' $RMTNTADSVR                                                    
		zhri100aFields.remoteAdServerName = PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, zhri100aFields.dbName, "RMTNTADSVR"); //TODO: where is this used???
		return zhri100aFields;
	}

	/**
	 * ZHRI100A.Check-Interface-Runfile
	 * This procedure will check the existence run file
	 * @param oracleSystemId
	 * @return true is run file does not exist
	 */
	public static Boolean ZHRI100A_checkInterfaceRunFile(String oracleSystemId) {
		Boolean runFlag = false;
		String runFilePath;
		//LET $RUN_FILEPATH = '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'  //concatenate
		runFilePath = "/usr/local/barch/" + oracleSystemId + "/work/hrinterface.run";
		//LET #file_exists = exists($RUN_FILEPATH)
		Boolean fileExists = (new File(runFilePath)).exists();
		//IF #file_exists = 0
		if(fileExists == false) {
			//LET #run_flag = 1
			runFlag = true;		}
		//ELSE
		else {
			//LET #run_flag = 0
			runFlag = false;
		//END-IF
		}
		return runFlag;
	}
	
	/**
	 * Get-Trigger-Data from ZHRI100A.SQR
	 * This procedure will get the trigger data that needs to be interfaced
	 * @param zhri100aFields
	 */
	//TODO
	//TODO
	//TODO
	public static void ZHRI100A_getTriggerData(Zhri100aFields zhri100aFields) {
		//asOfToday
		//sysDate
		//fileOpen
		PszTriggerEmployee trigger = PszTriggerEmployee.createMockTriggerForEmployeeTermination();
		Boolean adFound = false; //$AdFound  //TODO: where should this be set???
		//BEGIN-PROCEDURE GET-TRIGGER-DATA
		//LET $CompletionStatus = 'P'   !Initialize the CompletionStatus field
		String completionStatus = "P";
		//LET $PoiFlag = ''  !Initialize the POIFlag -- Surya Added - TEMPMAST 
		//LET #WrkSequence = 0
		//LET $AuditOprid = ''
		//LET $PSEmplid = ''
		//LET #Wrk_EmplID1 = 0
		//LET $Wrk_Emplid2 = ''
		//LET $PSEffdt = ''
		//LET #PSEffSeq = 0
		//LET $WrkProcess= ''
		//LET $WrkTaskFlag = ''
		//BEGIN-SELECT LOOPS=150
		//	RZ.SEQ_NBR
		//	MOVE &RZ.SEQ_NBR TO #WrkSequence
		//	RZ.OPRID
		//	LET $AuditOprid = LTRIM(RTRIM(&RZ.OPRID,' '),' ')
		//	RZ.EMPLID
		//	LET $PSEmplid = LTRIM(RTRIM(&RZ.EMPLID,' '),' ')
		//	MOVE $PSEmplid TO #Wrk_EmplID1
		//	LET $Wrk_Emplid2 =  EDIT(#Wrk_EmplID1,'099999999')
		//	TO_CHAR(RZ.EFFDT, 'YYYY-MM-DD') &RZEFFDT
		//	LET $PSEffdt = &RZEFFDT
		//	RZ.EFFSEQ
		//	MOVE &RZ.EFFSEQ TO #PSEffSeq
		//	RZ.PROC_NAME
		//	LET $WrkProcess = LTRIM(RTRIM(&RZ.PROC_NAME,' '),' ')           !Remove leading and trailing blanks
		//	RZ.TASK_FLAG                                                        !Surya Added - TEMPMAST 
		//	LET $WrkTaskFlag = LTRIM(RTRIM(&RZ.TASK_FLAG,' '),' ')          !Surya Added - TEMPMAST 
		//IF $file_open = 'N'
		Boolean fileIsOpen = false;
		if(!fileIsOpen) {
			//!open $open_file1 as 1 for-append record=337
			//!LET $file_open = 'Y'
			//do nothing
		//END-IF
		}
		//LET $PoiFlag = 'N'  !Surya Added - TEMPMAST 
		zhri100aFields.poiFlag = false;
		//DO Check-If-Contractor
		Boolean isContractor = PsJob.employeeIsContractor(trigger.getEmployeeId());
		//IF $Found = 'N' AND  $PSEmplid <> ''  !Not a contractor and not a blank EmplId   !ZHR_MOD_ZHRI100A_110A
		if(!isContractor && trigger.getEmployeeId() != null && !trigger.getEmployeeId().isEmpty()) {
			//Added a check for 'ZHRI102A' - to see if corresponding row on JOB 
			//IF $WrkProcess = 'ZHRI102A'
			if("ZHRI102A".equalsIgnoreCase(trigger.getProcessName())) {
				//DO Check-If-Correct102A
				Boolean isOkToProcess = ZHRI100A_checkIfCorrect102A(trigger.getEmployeeId(), trigger.getEffectiveDate(), trigger.getProcessName());
				//IF $OK-to-process = 'Y'
				if(isOkToProcess) {
					//DO Call-Programs
					ZHRI100A_callPrograms(trigger, zhri100aFields);
				}
				//ELSE                                                           
				else {
					//LET $CompletionStatus = 'C'                                 
					completionStatus = "C";
					//DO Update-Trigger-Row                                       
					int numberOfRecordsUpdated = PszTriggerEmployee.updateCompletionStatus(trigger.getSequenceNumber(), completionStatus);
					System.out.println("numberOfRecordsUpdated: " + numberOfRecordsUpdated);
					completionStatus = "P";     //!Reset the completion Status for next pass
				//END-IF                                                         
				}
			}
		}
		//ELSE
		else {
			//IF $Found = 'Y'
			if(isContractor) {
				//LET $CompletionStatus = 'C'
				completionStatus = "C";
			//END-IF
			}
			//IF  $PSEmplid = ''
			if(trigger.getEmployeeId() == null || trigger.getEmployeeId().isEmpty()) {
				//LET $CompletionStatus = 'E'
				completionStatus = "E";
			//END-IF
			}
		//END-IF  !$Found = 'N'
		}
		//IF $CompletionStatus <> 'P'
		if(!"P".equalsIgnoreCase(completionStatus)) {
		//	IF ($ADAction_Code <> '') AND ($ADLegOprid <> '')
			if(!zhri100aFields.adActionCode.isEmpty() && !zhri100aFields.adLegacyOperatorId.isEmpty()) {
				//!DO Check-EffDt-Transaction
				//do nothing
				//IF $AdFound = 'N'
				if(!adFound) {
					//!DO Build-Active-Dir-Output-File	!ZHR_ZHRI100A_REMOVE_AD
					//do nothing
				//END-IF
				}
			//END-IF
			}
			//DO Update-Trigger-Row
			int numberOfRecordsUpdated = PszTriggerEmployee.updateCompletionStatus(trigger.getSequenceNumber(), completionStatus);
			System.out.println("numberOfRecordsUpdated: " + numberOfRecordsUpdated);
		//END-IF  !$CompletionStatus <> 'P'
		}
		//FROM PS_ZHRT_INTTRIGGER RZ ,PS_JOB JB
		//WHERE RZ.TASK_FLAG = 'P'
		//	AND (RZ.EFFDT <= $AsOfToday or RZ.PROC_NAME='ZHRI101A' OR  RZ.PROC_NAME='ZHRI106A')
		//  AND (CASE WHEN PROC_NAME IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END) = 
		//  		(SELECT MIN(CASE WHEN PROC_NAME IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END)  
		//      			FROM  PS_ZHRT_INTTRIGGER RZ2
		//      			WHERE RZ2.EMPLID = RZ.EMPLID
		//      				AND RZ2.TASK_FLAG = 'P'
		//        				AND (RZ2.EFFDT <= SYSDATE
		//								OR RZ2.PROC_NAME='ZHRI101A'
		//								OR RZ2.PROC_NAME='ZHRI106A'))
		//!Surya - TEMPMAST Added the below NOT IN to restrict the rows other than 101 to process when there is a delay
		//	AND RZ.EMPLID NOT IN (SELECT I.EMPLID FROM PS_ZHRT_INTTRIGGER I WHERE I.EMPLID = RZ.EMPLID AND I.TASK_FLAG = 'W') 
		//  AND JB.EMPLID = RZ.EMPLID
		//  AND JB.EFFDT = 
		//			(SELECT MAX(JB2.EFFDT) FROM  PS_JOB JB2
		//         			WHERE  JB2.EMPLID = JB.EMPLID
		//          			AND  JB2.EMPL_RCD = JB.EMPL_RCD)
		//  AND JB.EFFSEQ = 
		//			(SELECT MAX(JB3.EFFSEQ) FROM PS_JOB JB3
		//                	WHERE JB3.EMPLID = JB.EMPLID
		//                 		AND  JB3.EMPL_RCD = JB.EMPL_RCD
		//                 		AND  JB3.EFFDT = JB.EFFDT)
		//END-SELECT
		//END-PROCEDURE GET-TRIGGER-DATA
	}
	
	/**
	 * Get-Legacy-OprId from ZHRI100A.SQR
	 * Gets the new OprId from the legacy system
	 * Formulates legacy OprId from HR036P where HR036P.H36EM# = #wrk_emplid and HR036P.H36INX = #indexNum UNION
	 * @param employeeId
	 * @param indexNumber
	 * @param poiFlag
	 * @return
	 */
	public static String ZHRI100A_getLegacyOprId(String employeeId, Integer indexNumber, Boolean poiFlag) {
		//Begin-Procedure Get-Legacy-Oprid                !sree**10/04/01
		//Let $LegEmplid = ''
		String legacyEmployeeId;
		String legacyEmployeeName;
		//MOVE $Wrk_Emplid TO #wrk_emplid                 !sree***10/04/01
		Integer employeeNumber = -1;
		try {
			employeeNumber = Integer.parseInt(employeeId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//!Surya Added - TEMPMAST Start
		//IF $PoiFlag = 'N'
		if(!poiFlag) {
			indexNumber = 0;
			//MOVE 0 TO #indexNum
		//END-IF
		}
		//!Surya Added - TEMPMAST End
		//Begin-Select
		//CHR36.H36NAM                                               !Rakesh***08/02/2004
		//CHR36.H36EMP                                               !Rakesh***08/02/2004
		//CHR36.H36EM#                                               !Rakesh***08/02/2004
		//from HR036P CHR36 where CHR36.H36EM# = #wrk_emplid and CHR36.H36INX = #indexNum 
		//UNION SELECT ' ', ' ' , 9999999999 FROM DUAL
		//END-SELECT
		HR036P hr036P = HR036P.findByEmployeeNumberAndIndexNumber(employeeNumber, indexNumber);
    	if(hr036P != null) {
			//!This IF statement and OR part of Select is a workaround to some problem in v8
			//!(gateway and new version of SQR).  The Select was hanging if it couldn't find a
			//! match in HR036P, so the Select assures that the Select always finds a match.
			//LET #WRK_CHR36_H36EM_NUM = &CHR36.H36EM#   !sm 07/12/02  !Rakesh***08/02/2004
			//IF #WRK_CHR36_H36EM_NUM = #wrk_emplid                    !Rakesh***08/02/2004
			if(hr036P.getEmployeeNumber() == employeeNumber) {
				//LET $LegEmpName = &CHR36.H36NAM                        !Rakesh***08/02/2004
				legacyEmployeeName = hr036P.getEmployeeName();
				//DO Format-Employee-Name
		    	if(legacyEmployeeName != null && !legacyEmployeeName.isEmpty()) {
		    		legacyEmployeeName = erd.StringUtil.formatLegacyEmployeeNameToPeopleSoftEmployeeName(hr036P.getEmployeeName());
		    	}
				//LET $LegEmplid = &CHR36.H36EMP                         !Rakesh***08/02/2004
	    		legacyEmployeeId = hr036P.getEmployeeId();
				//LET $LegEmplid = substr($LegEmplid,1,5)
		    	if(legacyEmployeeId != null && !legacyEmployeeId.isEmpty()) {
		    		legacyEmployeeId = legacyEmployeeId.substring(0, 5);
		    	}
				//!DO Insert-OprId  !Surya Added - TEMPMAST 
				//!Surya Added - TEMPMAST Start
				//IF #indexNum = 0
		    	if(indexNumber == 0) {
					//DO INSERT-OPRID
		    		CrossReferenceMultipleEmployeeId.ZHRI100A_insertOprId(employeeId, legacyEmployeeId);
		    	}
				//ELSE
		    	else {
					//DO UPDATE-OPRID
		    		CrossReferenceMultipleEmployeeId.ZHRI100A_updateOprId(employeeId, legacyEmployeeId, indexNumber);
				//END-IF  
		    	}
				//!Surya Added - TEMPMAST End
			//END-IF  !#WRK_CHR36_H36EM_NUM = #wrk_emplid
			}
			return hr036P.getEmployeeId();
    	}
    	return null;
		//END-PROCEDURE GET-LEGACY-OPRID
	}
}

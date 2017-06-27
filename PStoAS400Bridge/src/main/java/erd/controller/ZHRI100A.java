package erd.controller;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
		System.out.println("********** ZHRI100A.main");
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
		System.out.println("********** ZHRI100A.processMain");
		Zhri100aFields zhri100aFields;
		//Begin-Procedure Process-Main
		//ZHRI100A.Get-Variable
		zhri100aFields = initializeMainProperties();
		//LET $WrkCriticalFlag = 'N'
		zhri100aFields.setCriticalFlag(false);
		//LET #run_flag = 1
		Boolean runFlag = true;
		//while #run_flag = 1        !Never ending loop
		while(runFlag == true) {
			//ZHRI100A.Check-Interface-Runfile
			runFlag = ZHRI100A_checkInterfaceRunFile(zhri100aFields.getOracleSystemId());
			//ZHRI100A.Get-Trigger-Data       !Process the interface requests
			ZHRI100A_getTriggerData(zhri100aFields);
			//TRANCTRL.Commit-Transaction
			//LET $Command = 'sleep 15'  !After interface run wait 15 seconds and do it again  !sree**rehost  !ZHR_MOD_ZHRI100A_sleep
			//Call System Using $Command #status Wait  !sree**rehost  !ZHR_MOD_ZHRI100A_sleep
			//sleep for 15 seconds (15000 milliseconds)
			Thread.sleep(15000);
			//IF $file_open = 'Y'
//			//TODO:
//			if((new File("runFilePath")).isFile()) {
//				//CLOSE 1
//				(new File("runFilePath")).close();
//			//END-IF
//			}
			//LET $file_open = 'N'
			runFlag = false; //*** I put this here for testing, so it stops after one iteration.
		//end-while   !1=1
		}
		//LET $Command = 'mv' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.stop' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'
		String command = "mv" + " " + "/usr/local/barch/" + zhri100aFields.getOracleSystemId() + "/work/hrinterface.stop" + " " + "/usr/local/barch/" + zhri100aFields.getOracleSystemId() + "/work/hrinterface.run";
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
		System.out.println("********** ZHRI100A_ftpFile");
		//BEGIN-PROCEDURE FTP-FILE
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
		System.out.println("********** ZHRI100A_callPrograms");
		String commandString = "";
		//BEGIN-PROCEDURE CALL-PROGRAMS
		//DO Initialize-AD-WrkFields
		//LET $TrigTaskFlag = ''
		
		switch(trigger.getProcessName()) {
		
		case "ZHRI101A": //Process to hire employee
			//WHEN = 'ZHRI101A'
			//!Move fields to be used in the called SQC
			//LET $Wrk_Oprid = $AuditOprid
			zhri100aFields.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
			//LET $Wrk_Effdt = $PSEffdt
			zhri100aFields.setEffectiveDate(trigger.getEffectiveDate());
			//move #PSEffseq to #Wrk_Effseq
			zhri100aFields.setIndexNumber(trigger.getEffectiveSequence());
			//LET $Wrk_Process_Name = $WrkProcess
			zhri100aFields.setProcessName(trigger.getProcessName());
			//LET $TrigTaskFlag = $WrkTaskFlag      !Surya Added - TEMPMAST
			zhri100aFields.setCompletionStatus(trigger.getCompletionStatus());
			//LET $Wrk_Inf_ = ' '
			//LET $ADAction_Code = 'H'
			zhri100aFields.setActionCode("H");
			//LET $ADLegOprid = ''
			zhri100aFields.setLegacyOperatorId("");
			//DO HR01-Process-Main    !ZHRI101A.SQC
			EmployeeNewHire employeeNewHire = new EmployeeNewHire(trigger, zhri100aFields);
			zhri100aFields.setCompletionStatus(employeeNewHire.HR01_processMain());
			//BREAK
			break;
		case "ZHRI102A": //Process to terminate an employee
			//WHEN = 'ZHRI102A'
			//!Move fields to be used in the called SQC
			//MOVE #Wrk_Sequence to #WrkSeqNbr
			zhri100aFields.setIndexNumber(trigger.getEffectiveSequence());
			//LET $PSAuditOperId = $AuditOprId
			zhri100aFields.setAuditOperatorId(trigger.getOperatorId());
	        //LET $PSDateIn = $PSEffDt
			zhri100aFields.setEffectiveDate(trigger.getEffectiveDate());
	        //LET $Wrk_Emplid = $PSEmplId
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
	        //LET $ADAction_Code = 'T'
			zhri100aFields.setActionCode("T");
	        //LET $ADLegOprid = ''
			zhri100aFields.setLegacyOperatorId("");
	        //DO HR02-Process-Main    !ZHRI102A.SQC
			EmployeeTermination employeeTermination = new EmployeeTermination(trigger, zhri100aFields);
			zhri100aFields.setCompletionStatus(employeeTermination.HR02_processMain());
			//BREAK
			break;
		case "ZHRI104A": //Process for job status change
			//WHEN = 'ZHRI104A'
			//!Move fields to be used in the called SQC
			//LET $PSUserOprid = $AuditOprid
			zhri100aFields.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
			//Move #PSEffseq to #WrkEffseq
			zhri100aFields.setIndexNumber(trigger.getEffectiveSequence());
			//LET $ADAction_Code = 'C'
			zhri100aFields.setActionCode("C");
			//LET $ADLegOprid = ''
			zhri100aFields.setLegacyOperatorId("");
			//DO HR04-Process-Main    !ZHRI104A.SQC
			EmployeeJobProfileChange employeeJobProfileChange = new EmployeeJobProfileChange(trigger, zhri100aFields);
			zhri100aFields.setCompletionStatus(employeeJobProfileChange.HR04_processMain());
			//BREAK
			break;
		case "ZHRI105A": //Process for demographics change
			//WHEN = 'ZHRI105A'
			//!Move fields to be used in the called SQC
			//LET $PSemp = $AuditOprid
			zhri100aFields.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
			//LET $ADAction_Code = 'C'
			zhri100aFields.setActionCode("C");
			//LET $ADLegOprid = ''
			zhri100aFields.setLegacyOperatorId("");
			//LET $Wrk_ADCountryCdBuild = 'Y'                 !sree-UAAMOD
			//DO HR05-Process-Main    !ZHRI105A.SQC
			EmployeeDemographicChange employeeDemographicChange = new EmployeeDemographicChange(trigger, zhri100aFields);
			zhri100aFields.setCompletionStatus(employeeDemographicChange.HR05_processMain());
			//BREAK
			break;
		case "ZHRI106A": 
			//WHEN = 'ZHRI106A'
			//!Move fields to be used in the called SQC
			//LET $Wrk_Oprid = $AuditOprid
			zhri100aFields.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
			//LET $Wrk_Effdt = $PSEffdt
			zhri100aFields.setEffectiveDate(trigger.getEffectiveDate());
			//move #PSEffseq to #Wrk_Effseq
			zhri100aFields.setIndexNumber(trigger.getEffectiveSequence());
			//LET $Wrk_Process_Name = $WrkProcess
			zhri100aFields.setProcessName(trigger.getProcessName());
			//LET $ADAction_Code = 'R'
			zhri100aFields.setActionCode("R");
			//DO HR01-Process-Main       !ZHRI101A.SQC
			//BREAK
			break;
		case "ZHRI107A": //Process for converting dates
			//WHEN = 'ZHRI107A'
			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
			//LET $ADAction_Code = ''
			zhri100aFields.setActionCode("");
			//LET $ADLegOprid = ''
			zhri100aFields.setLegacyOperatorId("");
			//DO HR07-Process-Main
			EmployeeDateChange employeeDateChange = new EmployeeDateChange(trigger, zhri100aFields);
			zhri100aFields.setCompletionStatus(employeeDateChange.HR07_processMain());
			//BREAK
			break;
		case "ZHRI109A": //Process for group transfer
			//WHEN = 'ZHRI109A'
			//!Move fields to be used in the called SQC
			//LET $PSUserOprid = $AuditOprid
			zhri100aFields.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
			//Move #PSEffseq to #WrkEffseq
			zhri100aFields.setIndexNumber(trigger.getEffectiveSequence());
			//LET $ADAction_Code = 'C'
			zhri100aFields.setActionCode("C");
			//LET $ADLegOprid = ''
			zhri100aFields.setLegacyOperatorId("");
			//DO HR09-Process-Main        !ZHRI100A.SQC
			EmployeeGroupTransfer employeeGroupTransfer = new EmployeeGroupTransfer(trigger, zhri100aFields);
			zhri100aFields.setCompletionStatus(employeeGroupTransfer.HR09_processMain());
			//BREAK
			break;
		case "ZHRI101D": 
			//WHEN = 'ZHRI101D'     !Row deleted on hire
			//LET $ErrorProgramParm = 'HRZ101A'
			zhri100aFields.setErrorProgramParameter("HRZ101A");
			//LET $ErrorMessageParm = 'A row was deleted on the hire process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the hire process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			zhri100aFields.setProcessName(trigger.getProcessName());
			commandString = ZHRI100A_callErrorRoutine(zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		case "ZHRI102D": 
			//WHEN = 'ZHRI102D'     !Row deleted on term
			//LET $ErrorProgramParm = 'HRZ102A'
			zhri100aFields.setErrorProgramParameter("HRZ102A");
			//LET $ErrorMessageParm = 'A row was deleted on the termination process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the termination process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			zhri100aFields.setProcessName(trigger.getProcessName());
			commandString = ZHRI100A_callErrorRoutine(zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		case "ZHRI104D": 
			//WHEN = 'ZHRI104D'     !Row deleted on job status change
			//LET $ErrorProgramParm = 'HRZ104A'
			zhri100aFields.setErrorProgramParameter("HRZ104A");
			//LET $ErrorMessageParm = 'A row was deleted on the job profile process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the job profile process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			zhri100aFields.setProcessName(trigger.getProcessName());
			commandString = ZHRI100A_callErrorRoutine(zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		case "ZHRI105D": 
			//WHEN = 'ZHRI105D'     !Row deleted on demographics change
			//LET $ErrorProgramParm = 'HRZ105A'
			zhri100aFields.setErrorProgramParameter("HRZ105A");
			//LET $ErrorMessageParm = 'A row was deleted on the demographics process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the demographics process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			zhri100aFields.setProcessName(trigger.getProcessName());
			commandString = ZHRI100A_callErrorRoutine(zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		case "ZHRI106D": 
			//WHEN = 'ZHRI106D'     !Row deleted on rehire
			//LET $ErrorProgramParm = 'HRZ101A'
			zhri100aFields.setErrorProgramParameter("HRZ101A");
			//LET $ErrorMessageParm = 'A row was deleted on the re-hire process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the re-hire process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			zhri100aFields.setProcessName(trigger.getProcessName());
			commandString = ZHRI100A_callErrorRoutine(zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		case "ZHRI107D": 
			//WHEN = 'ZHRI107D'     !Row deleted on
			//LET $ErrorProgramParm = 'HRZ107A'
			zhri100aFields.setErrorProgramParameter("HRZ107A");
			//LET $ErrorMessageParm = 'A row was deleted on the dates process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the dates process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			zhri100aFields.setProcessName(trigger.getProcessName());
			commandString = ZHRI100A_callErrorRoutine(zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		case "ZHRI109D": 
			//WHEN = 'ZHRI109D'
			//LET $ErrorProgramParm = 'HRZ109A'
			zhri100aFields.setErrorProgramParameter("HRZ109A");
			//LET $ErrorMessageParm = 'A row was deleted on the group transfer process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the group transfer process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			zhri100aFields.setProcessName(trigger.getProcessName());
			commandString = ZHRI100A_callErrorRoutine(zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		default:
			//WHEN-OTHER
			//LET $CompletionStatus = 'E'     !update to an E to prevent looping and to mark the record in error
			zhri100aFields.setCompletionStatus("E");
			//DO UPDATE-TRIGGER-ROW
			//BREAK
			break;

		//END-EVALUATE
		}
	//END-PROCEDURE CALL-PROGRAMS
		return zhri100aFields.getCompletionStatus();
	}

	/**
	 * Check-If-POI-Termed from ZHRI100A.SQR
	 * This routine checks if it is a POI to EMP transfer. If it is then the the flag is changed to W and wait for Hire
	 * @param 
	 * @param 
	 * @return
	 */
	public static Boolean ZHRI100A_checkIfPoiTermed() {
		System.out.println("********** ZHRI100A_checkIfPoiTermed");
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
		System.out.println("********** ZHRI100A_getTriggerDataNonEmp");
		
	}

	/**
	 * Call-Programs-NonEmp from ZHRI100A.SQR
	 * Subroutine will call appropriate programs for Non Emp
	 * @param 
	 * @param 
	 * @return
	 */
	public static void ZHRI100A_callProgramsNonEmp() {
		System.out.println("********** ZHRI100A_callProgramsNonEmp");
		
	}

	/**
	 * Get-Trigger-Data-POI-Emp-Convert from ZHRI100A.SQR
	 * This procedure will get the trigger data that needs to be interfaced
	 * @param 
	 * @param 
	 * @return
	 */
	public static void ZHRI100A_getTriggerDataPoiEmpConvert() {
		System.out.println("********** ZHRI100A_getTriggerDataPoiEmpConvert");
		
	}

	/**
	 * Update-Trigger-Row-NonEmp from ZHRI100A.SQR
	 * This routine update the trigger file flag switch for Non Emp
	 * @param 
	 * @param 
	 * @return
	 */
	public static void ZHRI100A_updateTriggerRowNonEmp() {
		System.out.println("********** ZHRI100A_updateTriggerRowNonEmp");
		
	}

	/**
	 * ZHRI100ABuiltWhereClauseDelay from ZHRI100A.SQR
	 * Get the WHERE Clause for the delay hours in POI to EMP Transfer
	 */
	public static String ZHRI100A_buildWhereClauseDelay() {
		System.out.println("********** ZHRI100A_buildWhereClauseDelay");
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
	public static Integer ZHRI100A_callSystem(String commandString, Zhri100aFields zhri100aFields) {
		System.out.println("********** ZHRI100A_callSystem");
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
//			System.out.println("command: " + command);
		//END-WHILE   !#SubstrStartPos <= #CommandLength
//		}
		//LET $Command = $RexecScript || ' ' || $Command || ' ' || $RMTSVR
//		command = zhri100aFields.getRemoteExecScript() + " " +  command + " " + zhri100aFields.getRemoteServerName();
		//Call System Using $Command #Status Wait  !Execute the command that was built on the command waiting until completion
		status = callSystemUsingCommand(commandString, zhri100aFields);
		//IF #status != 0
		if(status != 0) {
			//!error
			//LET $ErrorProgramParm = 'ZHRI100A'
			zhri100aFields.setErrorProgramParameter("ZHRI100A");
			//LET $ErrorMessageParm = ' '
			//LET $ErrorMessageParm = 'Error executing Call System command, contact HR-PeopleSoft On-Call'
			zhri100aFields.setErrorMessageParameter("Error executing Call System command, contact HR-PeopleSoft On-Call");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms
			ZHRI100A_prepareErrorParms(zhri100aFields);
			//IF $PoiFlag = 'N'
			if(!zhri100aFields.getPoiFlag()) {
				//DO Call-Error-Routine
				//TODO: 
				zhri100aFields.setProcessName("HRZ100A");
				commandString = ZHRI100A_callErrorRoutine(zhri100aFields);
			//ELSE
			}
			else {
				//DO Call-Error-Routine-NonEmp
				//TODO: 
				zhri100aFields.setProcessName("HRZ100A");
				commandString = ZHRI100A_callErrorRoutineNonEmp(zhri100aFields);
			//END-IF
			}
			//LET $WrkCriticalFlag  = 'N'
			zhri100aFields.setCriticalFlag(false);
		}
		//END-IF
		return status;
		//END-PROCEDURE CALL-SYSTEM
	}

	/**
	 * Prepare-Error-Parms from ZHRI100A.SQR
	 * Makes sure that the parms are the correct length for the error routine RPG program to recieve them
	 * @param zhri100aFields
	 */
	private static void ZHRI100A_prepareErrorParms(Zhri100aFields zhri100aFields) {
		System.out.println("********** ZHRI100A_prepareErrorParms");
		//BEGIN-PROCEDURE PREPARE-ERROR-PARMS
		//!Prepare the date and time parms
		//DO Get-Current-DateTime                                 !Get the current date and time
		Calendar now = Calendar.getInstance();
		//LET $AddDateErrorParm = DATETOSTR(STRTODATE($AsOfToday,'DD-MON-YYYY'),'YYYYMMDD') !sree**rehost
		zhri100aFields.setErrorDateParameter(now.get(Calendar.MONTH) + "/" + now.get(Calendar.DATE) + "/" + now.get(Calendar.YEAR));
		//LET $AddTimeErrorParm =    substr($Out,10,2)    ||    substr($Out,13,2)    ||    substr($Out,16,2)
		zhri100aFields.setErrorTimeParameter(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND));
		//LET $OprIdErrorParm   =    Substr($AuditOprid,2,5)
		//END-PROCEDURE PREPARE-ERROR-PARMS
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
		System.out.println("********** callSystemUsingCommand");
		//SHOW '$Command=> ' $Command
		System.out.println("************************************************************");
		System.out.println("$Command=> " + command);
		System.out.println("************************************************************");
		//DO Get-Current-Datetime  !Gets the current date and time using curdttim.sqc
		//SHOW 'Calling Command at: ' $SysDateTime  !Surya Added - TEMPMAST
		//"20-JUN-2017_12:01:06.000000_AM"
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss.SSSSSS_a");
		String currentDate = sdf.format(new Date()).toUpperCase();
		System.out.println("Calling Command at: " + currentDate);
//		System.out.println(zhri100aFields.toString());
		return 0;
	}

	/**
	 * Call-Error-Routine-NonEmp from ZHRI100A.SQR
	 * Builds the command and calls the error routine
	 * @param processName
	 * @param zhri100aFields
	 * @return command text
	 */
	private static String ZHRI100A_callErrorRoutineNonEmp(Zhri100aFields zhri100aFields) {
		System.out.println("********** ZHRI100A_callErrorRoutineNonEmp");
		String commandString = composeCommandString(zhri100aFields, composeErrorParameterString(zhri100aFields), zhri100aFields.getProcessName());
		System.out.println(commandString);
		return commandString;
	}

	/**
	 * Call-Error-Routine from ZHRI100A.SQR
	 * @param processName
	 * @param zhri100aFields
	 * @return command text
	 */
	public static String ZHRI100A_callErrorRoutine(Zhri100aFields zhri100aFields) {
		System.out.println("********** ZHRI100A_callErrorRoutine");
		String commandString = composeCommandString(zhri100aFields, composeErrorParameterString(zhri100aFields), zhri100aFields.getProcessName());
		System.out.println(commandString);
		return commandString;
//		List<String> parameterList = new ArrayList<String>();
//		HashMap<String, String> parameterMap = new HashMap<String, String>();
//		String blankSpaceParameter = " ";
//		String criticalFlag = zhri100aFields.getCriticalFlag() != null && zhri100aFields.getCriticalFlag() ? "Y" : "N";
//		Calendar now = Calendar.getInstance();
//		String errorDateParameter =  now.get(Calendar.MONTH) + "/" + now.get(Calendar.DATE) + "/" + now.get(Calendar.YEAR);
//		String errorTimeParameter = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);
//		String yesOrNoParameter = "Y"; //TODO: What should this value really be called
//		//!Make Sure that the ErrorMessageParm is always 75 Characters long
//		String errorMessageParameter = String.format("%1$-75s", zhri100aFields.getErrorMessageParameter());
//		String command = "\"CALL " + zhri100aFields.getAs400Library() + "/" + processName + " "
//					+ "PARM("
//					+ "'" + zhri100aFields.getErrorProgramParameter() + "' "
//					+ "'" + zhri100aFields.getEmployeeId() + "' "
//					+ "'" + blankSpaceParameter + "' "
//					+ "'" + errorMessageParameter + "' "
//					+ "'" + criticalFlag + "' "
//					+ "'" + errorDateParameter + "' "
//					+ "'" + errorTimeParameter + "' "
//					+ "'" + zhri100aFields.getOperatorId() + "' "
//					+ "'" + yesOrNoParameter + "')\" ";
//		System.out.println(command);
//
//		parameterList.add("errorProgramParameter");
//		parameterList.add("employeeId");
//		parameterList.add("blankSpaceParameter");
//		parameterList.add("errorMessageParameter");
//		parameterList.add("criticalFlag");
//		parameterList.add("errorDateParameter");
//		parameterList.add("errorTimeParameter");
//		parameterList.add("opridErrorParameter");
//		parameterList.add("yesOrNoParameter");
//		
//		parameterMap.put("errorProgramParameter", zhri100aFields.getErrorProgramParameter());
//		parameterMap.put("employeeId", zhri100aFields.getEmployeeId());
//		parameterMap.put("blankSpaceParameter", blankSpaceParameter);
//		parameterMap.put("errorMessageParameter", errorMessageParameter);
//		parameterMap.put("criticalFlag", criticalFlag);
//		parameterMap.put("errorDateParameter", errorDateParameter);
//		parameterMap.put("errorTimeParameter", errorTimeParameter);
//		parameterMap.put("opridErrorParameter", zhri100aFields.getOperatorId());
//		parameterMap.put("yesOrNoParm", yesOrNoParameter);
//		
//		AS400Package as400Package = new AS400Package(processName, parameterList, parameterMap);
//		return as400Package;
	}


	/**
	 * Build-Group-Where-Clause from ZHRI100A.SQR
	 * This routine will build the where clause that will select the correct Run-ID to use.
	 * @param whereClause
	 * @return SQL where clause
	 */
	public static String ZHRI100A_buildGroupWhereClause(String whereClause) {
		System.out.println("********** ZHRI100A_buildGroupWhereClause");
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
		System.out.println("********** ZHRI100A_buildEmplIdWhereClause");
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
	 * @return legacyEmployeeId
	 */
	public static String ZHRI100A_getOprId(String employeeId, BigDecimal indexNumber, Zhri100aFields zhri100aFields) {
		return ZHRI100A_getOprId(employeeId, zhri100aFields, indexNumber, null); 
	}
	public static String ZHRI100A_getOprId(String employeeId, Zhri100aFields zhri100aFields, BigDecimal indexNumber, BigDecimal eidIndexNumber) {
		System.out.println("********** ZHRI100A_getOprId");
		//BEGIN-PROCEDURE GET-OPRID
		//LET $Found = 'N'
		Boolean found = false;
		//LET $PSOprId = ''
		String psOprId;
		//IF $PoiFlag = 'N'
		if(!zhri100aFields.getPoiFlag()) {
			//MOVE 0 TO #indexNum
			indexNumber = new BigDecimal(0);
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
			psOprId = ZHRI100A_getLegacyOprId(employeeId, indexNumber, zhri100aFields.getPoiFlag());
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
	public static Zhri100aFields initializeMainProperties() {
		System.out.println("********** initializeMainProperties");
		Zhri100aFields zhri100aFields = new Zhri100aFields();
		String processName = "ZHRI100A";
		zhri100aFields.setDbName(PsDbOwner.findDbName());
		//LET $PS_HOME = getenv('PS_HOME')  !This gets the oracle_sid
//		zhri100aFields.setPeopleSoftHomePath(System.getenv("PS_HOME")); //TODO: ********
		zhri100aFields.setPeopleSoftHomePath("C:/people soft/");
		//LET $AD_HOME = $PS_HOME || '/data/activedir/'  !Path for Active Directory
//		zhri100aFields.setActiveDirectoryHomePath(zhri100aFields.peopleSoftHomePath + "/data/activedir/"); //TODO: where is this used???
		//LET $ORACLE_SID = getenv('ORACLE_SID') 
//		zhri100aFields.setOracleSystemId(System.getenv("ORACLE_SID")); //TODO: ********
		zhri100aFields.setOracleSystemId("PS90HRQA");
		if(zhri100aFields.getOracleSystemId() != null) {
			//UPPERCASE $ORACLE_SID                  
			zhri100aFields.setOracleSystemId(zhri100aFields.getOracleSystemId().toUpperCase());
		}
		//!Returns name of AS/400 machine for use in zbas002b.sh
		//LET $Variable_Needed = ' '            
		//LET $Variable_Needed = 'RMTSVR'       
		//DO  Get-Variable                      
		//LET $RMTSVR = $PSZPTT_VARIABLE_VAL      
		//Show '$RMTSVR: ' $RMTSVR                                                            
		zhri100aFields.setRemoteServerName(PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, zhri100aFields.getDbName(), "RMTSVR")); //TODO: where is this used???
		//LET $RexecScript = '/usr/local/barch/' || $ORACLE_SID || '/scripts/zbas002b.sh'     
		//Show '$RexecScript: ' $RexecScript                                                  
		zhri100aFields.setRemoteExecScript("/usr/local/barch/" + zhri100aFields.getOracleSystemId() + "/scripts/zbas002b.sh"); //TODO: where is this used???
		//!Returns library name on AS/400 where programs reside
		//LET $Variable_Needed = ' '            
		//LET $Variable_Needed = 'AS400library' 
		//DO  Get-Variable                      
		//LET $Library = $PSZPTT_VARIABLE_VAL   
		//Show '$Library: ' $Library                                                          
		zhri100aFields.setAs400Library(PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, zhri100aFields.getDbName(), "AS400library"));
		//!Returns IP address of NT server
		//LET $Variable_Needed = ' '             
		//LET $Variable_Needed = 'RMTNTADSVR'    
		//DO Get-Variable                        
		//LET $RMTNTADSVR = $PSZPTT_VARIABLE_VAL 
		//Show '$RMTNTADSVR: ' $RMTNTADSVR                                                    
		zhri100aFields.setRemoteAdServerName(PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, zhri100aFields.getDbName(), "RMTNTADSVR")); //TODO: where is this used???
		return zhri100aFields;
	}

	/**
	 * ZHRI100A.Check-Interface-Runfile
	 * This procedure will check the existence run file
	 * @param oracleSystemId
	 * @return true if run file does not exist
	 */
	public static Boolean ZHRI100A_checkInterfaceRunFile(String oracleSystemId) {
		System.out.println("********** ZHRI100A_checkInterfaceRunFile");
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
//		return runFlag;
		return true;
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
		System.out.println("********** ZHRI100A_getTriggerData");
		//asOfToday
		//sysDate
		//fileOpen
//		PszTriggerEmployee trigger = PszTriggerEmployee.createMockTriggerForEmployeeTermination();
		List<PszTriggerEmployee> triggerList = PszTriggerEmployee.findByCompletionStatusAndProcessName("P", "ZHRI102A");
		PszTriggerEmployee trigger = triggerList != null && !triggerList.isEmpty() ? triggerList.get(0) : PszTriggerEmployee.createMockTriggerForEmployeeTermination();
		System.out.println("************************************************** trigger: \n" + trigger.toString());
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
			//!OPEN $open_file1 AS 1 FOR-APPEND RECORD=337
			//!LET $file_open = 'Y'
			fileIsOpen = true;
			//do nothing
		//END-IF
		}
		//LET $PoiFlag = 'N'  !Surya Added - TEMPMAST 
		zhri100aFields.setPoiFlag(false);
		//DO Check-If-Contractor
		Boolean isContractor = PsJob.ZHRI100A_checkIfContractor(trigger.getEmployeeId());
		//IF $Found = 'N' AND  $PSEmplid <> ''  
		//!Not a contractor and not a blank EmplId   !ZHR_MOD_ZHRI100A_110A
		System.out.println("************** !isContractor && trigger.getEmployeeId() != null && !trigger.getEmployeeId().isEmpty(): " + (!isContractor && trigger.getEmployeeId() != null && !trigger.getEmployeeId().isEmpty()));
		if(!isContractor && trigger.getEmployeeId() != null && !trigger.getEmployeeId().isEmpty()) {
			//Added a check for 'ZHRI102A' - to see if corresponding row on JOB 
			//IF $WrkProcess = 'ZHRI102A'
			if("ZHRI102A".equalsIgnoreCase(trigger.getProcessName())) {
				//DO Check-If-Correct102A
				Boolean isOkToProcess = PsJob.ZHRI100A_checkIfCorrect102A(trigger.getEmployeeId(), trigger.getEffectiveDate(), trigger.getProcessName());
				System.out.println("************** isOkToProcess: " + isOkToProcess);
				//IF $OK-to-process = 'Y'
				if(isOkToProcess) {
					//DO Call-Programs
					ZHRI100A_callPrograms(trigger, zhri100aFields);
				}
				//ELSE                                                           
				else {
					//LET $CompletionStatus = 'C'                                 
					completionStatus = "C";
					//DO UPDATE-TRIGGER-ROW                                       
					int numberOfRecordsUpdated = PszTriggerEmployee.setCompletionStatusBySequenceNumber(completionStatus, trigger.getSequenceNumber());
					System.out.println("************** numberOfRecordsUpdated: " + numberOfRecordsUpdated);
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
			if(!zhri100aFields.getActionCode().isEmpty() && !zhri100aFields.getLegacyOperatorId().isEmpty()) {
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
			//DO UPDATE-TRIGGER-ROW
			int numberOfRecordsUpdated = PszTriggerEmployee.setCompletionStatusBySequenceNumber(completionStatus, trigger.getSequenceNumber());
			System.out.println("numberOfRecordsUpdated: " + numberOfRecordsUpdated);
		//END-IF  !$CompletionStatus <> 'P'
		}
		//FROM PS_ZHRT_INTTRIGGER RZ ,PS_JOB JB
		//WHERE RZ.TASK_FLAG = 'P'
		//		AND (RZ.EFFDT <= $AsOfToday or RZ.PROC_NAME='ZHRI101A' OR  RZ.PROC_NAME='ZHRI106A')
		//  	AND (CASE WHEN PROC_NAME IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END) = 
		//  		(SELECT MIN(CASE WHEN PROC_NAME IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END)  
		//      			FROM  PS_ZHRT_INTTRIGGER RZ2
		//      			WHERE RZ2.EMPLID = RZ.EMPLID
		//      				AND RZ2.TASK_FLAG = 'P'
		//        				AND (RZ2.EFFDT <= SYSDATE
		//								OR RZ2.PROC_NAME='ZHRI101A'
		//								OR RZ2.PROC_NAME='ZHRI106A'))
		//!Surya - TEMPMAST Added the below NOT IN to restrict the rows other than 101 to process when there is a delay
		//		AND RZ.EMPLID NOT IN (SELECT I.EMPLID FROM PS_ZHRT_INTTRIGGER I WHERE I.EMPLID = RZ.EMPLID AND I.TASK_FLAG = 'W') 
		//  	AND JB.EMPLID = RZ.EMPLID
		//  	AND JB.EFFDT = 
		//			(SELECT MAX(JB2.EFFDT) FROM  PS_JOB JB2
		//         			WHERE  JB2.EMPLID = JB.EMPLID
		//          			AND  JB2.EMPL_RCD = JB.EMPL_RCD)
		//  	AND JB.EFFSEQ = 
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
	 * @return legacyEmployeeId
	 */
	public static String ZHRI100A_getLegacyOprId(String employeeId, BigDecimal indexNumber, Boolean poiFlag) {
		System.out.println("********** ZHRI100A_getLegacyOprId");
		//Begin-Procedure Get-Legacy-Oprid                !sree**10/04/01
		//LET $LegEmplid = ''
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
			indexNumber = new BigDecimal(0);
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
		    	if(new BigDecimal(0).equals(indexNumber)) {
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
	
	public static String composeCommandString(Zhri100aFields zhri100aFields, String parameterString, String processName) {
		System.out.println("********** composeCommand()");
		//LET $Part1 = '"CALL ' || $Library ||'/HRZ202A '
		//LET $Part2 = 'Parm(''' || $PSauditEmpl || ''' ''' || $PSOprid || ''' ''' || $PSTermDate || ''')" '
		//LET $Command = $Part1||$Part2
		//LET $Command = $RexecScript || ' ' || $Command || ' ' || $RMTSVR
		String commandString = 
						zhri100aFields.getRemoteExecScript() + " " 
						+  "\"CALL " + zhri100aFields.getAs400Library() 
						+ "/" + processName + " " 
						+ "Parm(" + parameterString + ")\""
						+ zhri100aFields.getRemoteServerName();
		System.out.println("$Command=> " + commandString);
		return commandString;
	}
	
	public static String composeErrorParameterString(Zhri100aFields zhri100aFields) {
		System.out.println("********** composeErrorParameterString()");
		String blankSpaceParameter = " ";
		String criticalFlag = zhri100aFields.getCriticalFlag() != null && zhri100aFields.getCriticalFlag() ? "Y" : "N";
		Calendar now = Calendar.getInstance();
		String errorDateParameter =  now.get(Calendar.MONTH) + "/" + now.get(Calendar.DATE) + "/" + now.get(Calendar.YEAR);
		String errorTimeParameter = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);
		String yesOrNoParameter = "Y"; //TODO: What should this value really be called
		//!Make Sure that the ErrorMessageParm is always 75 Characters long
		String errorMessageParameter = String.format("%1$-75s", zhri100aFields.getErrorMessageParameter());
		String parameterString = "'" + zhri100aFields.getErrorProgramParameter() + "' "
					+ "'" + zhri100aFields.getEmployeeId() + "' "
					+ "'" + zhri100aFields.getIndexNumber() + "' "
					+ "'" + blankSpaceParameter + "' "
					+ "'" + errorMessageParameter + "' "
					+ "'" + criticalFlag + "' "
					+ "'" + errorDateParameter + "' "
					+ "'" + errorTimeParameter + "' "
					+ "'" + zhri100aFields.getOperatorId() + "' "
					+ "'" + yesOrNoParameter + "'";
//		System.out.println(parameterString);
		return parameterString;
	}
}

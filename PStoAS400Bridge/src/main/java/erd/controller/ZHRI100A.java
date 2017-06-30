package erd.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import erd.model.CrossReferenceEmployeeId;
import erd.model.CrossReferenceMultipleEmployeeId;
import erd.model.HR036P;
import erd.model.ProcessParameters;
import erd.model.ProcessParameters.CommonParameters;
import erd.model.PsDbOwner;
import erd.model.PsJob;
import erd.model.PszTriggerEmployee;
import erd.model.PszTriggerNonPerson;
import erd.model.PszTriggerSuperclass;
import erd.model.PszVariable;
import erd.model.PszXlat;
import erd.model.ServerProperties;

import java.io.IOException;
import org.apache.commons.net.bsd.RExecClient;
import org.apache.commons.io.IOUtils;


public class ZHRI100A {

	public static void main() {
		System.out.println("*** ZHRI100A.main() ***");
		//begin-program
		//Get-Current-DateTime  //queries the database to get the current time. It also initializes a slew of string variables ($AsOfToday, $AsOfNow, $CurrentCentury, $ReportDate
		//Init-DateTime  //sets a collection of variables that can be used by the other procedures in datetime.sqc that format dates or do date arithmetic
		//Process-Main
		try {
			initializeServerProperties();
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
	 * @see Process-Main in ZHRI100A.SQR
	 * This is the process controlling procedure.
	 * @throws InterruptedException 
	 */
	public static void processMain() throws InterruptedException {
		System.out.println("*** ZHRI100A.processMain() ***");
		//LET #run_flag = 1
		Boolean runFlag = true;
		//WHILE #run_flag = 1
		while(runFlag == true) { //never ending loop
			//****************************************************************************************************
			//TODO
			//ZHRI100A.Check-Interface-RunFile
			runFlag = ZHRI100A_checkInterfaceRunFile(ServerProperties.getOracleSystemId());
			//TODO
			//****************************************************************************************************
			//ZHRI100A.Get-Trigger-Data       !Process the interface requests
			//****************************************************************************************************
			//TODO
//			List<PszTriggerEmployee> triggerList = PszTriggerEmployee.findPending();
			List<PszTriggerEmployee> triggerList = PszTriggerEmployee.findByCompletionStatusAndProcessName("P", "ZHRI102A");
			triggerList = (triggerList != null && !triggerList.isEmpty()) ? Arrays.asList(triggerList.get(0)) : Arrays.asList(PszTriggerEmployee.createMockTriggerForEmployeeTermination());
			//TODO
			//****************************************************************************************************
			for(PszTriggerSuperclass trigger : triggerList) {
				String completionStatus = checkTriggerRecord(trigger);
				if("P".equalsIgnoreCase(completionStatus)) {
					completionStatus = processTrigger(trigger);
				}
				if(!"P".equalsIgnoreCase(completionStatus)) {
					if(trigger instanceof PszTriggerEmployee) {
						PszTriggerEmployee.setCompletionStatusBySequenceNumber(completionStatus, trigger.getSequenceNumber());
					}
					else {
						PszTriggerNonPerson.setCompletionStatusBySequenceNumber(completionStatus, trigger.getSequenceNumber());
					}
				}
			}
			//TRANCTRL.Commit-Transaction
			//LET $Command = 'sleep 15'  !After interface run wait 15 seconds and do it again  !sree**rehost  !ZHR_MOD_ZHRI100A_sleep
			//Call System Using $Command #status Wait  !sree**rehost  !ZHR_MOD_ZHRI100A_sleep
			//sleep for 15 seconds (15000 milliseconds)
			Thread.sleep(15000);
			//****************************************************************************************************
			//TODO
			//IF $file_open = 'Y'
//			if((new File("runFilePath")).isFile()) {
//				//CLOSE 1
//				(new File("runFilePath")).close();
//			//END-IF
//			}
			//LET $file_open = 'N'
			//TODO
			//****************************************************************************************************
			//****************************************************************************************************
			//TODO:
			runFlag = false; //*** I put this here for testing, so it stops after one iteration.
			//TODO:
			//****************************************************************************************************
		//end-while   !1=1
		}
		//****************************************************************************************************
		//TODO
		//LET $Command = 'mv' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.stop' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'
//		String commandString = "mv" + " " + "/usr/local/barch/" + ServerProperties.getOracleSystemId() + "/work/hrinterface.stop" + " " 
//					+ "/usr/local/barch/" + ServerProperties.getOracleSystemId() + "/work/hrinterface.run";
		//Show  + $Command in Process-Main: ' $Command
//		System.out.println("$Command in Process-Main: " + commandString);
		//Call System Using $Command #status Wait   !ZHR_MOD_ZHRI100A_sleep       
		//TODO
		//****************************************************************************************************
		//End-Procedure Process-Main
	}

	/**
	 * @see FTP-File in ZHRI100A.SQR
	 * This procedure will transfer the file from UNIX to NT server
	 */
	public static void ZHRI100A_ftpFile() {
		System.out.println("*** ZHRI100A.ZHRI100A_ftpFile() ***");
		//BEGIN-PROCEDURE FTP-FILE
		//IF #status != 0
		//END-IF
		//END-PROCEDURE
		//*** do nothing ***
	}
	
	/**
	 * Sets the values common to all processes class that shares the values across the application. 
	 * @param trigger
	 * @return
	 */
	public static CommonParameters initializeCommonParameters(PszTriggerSuperclass trigger) {
		System.out.println("*** ZHRI100A.initializeCommonParameters() ***");
		CommonParameters commonParameters = new ProcessParameters().new CommonParameters();
		commonParameters.setCriticalFlag(false); //from Process-Main
		commonParameters.setProcessName(trigger.getProcessName());
		commonParameters.setEmployeeId(trigger.getEmployeeId());
		commonParameters.setOperatorId(trigger.getOperatorId());
		commonParameters.setEffectiveDate(trigger.getEffectiveDate());
		commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
		commonParameters.setCompletionStatus(trigger.getCompletionStatus());
		commonParameters.setPoiFlag(false);
		return commonParameters;
	}

	/**
	 * processTrigger
	 * Execute appropriate process based on the process name in the trigger record.
	 * @param trigger
	 * @return completionStatus
	 */
	public static String processTrigger(PszTriggerSuperclass trigger) {
		System.out.println("*** ZHRI100A.processTrigger() ***");
		CommonParameters commonParameters = initializeCommonParameters(trigger);
		String completionStatus = trigger.getCompletionStatus();
		switch(trigger.getProcessName()) {
		case "ZHRI101A": //Process to hire employee
			EmployeeNewHire employeeNewHire = new EmployeeNewHire();
			completionStatus = employeeNewHire.HR01_processMain((PszTriggerEmployee) trigger, commonParameters);
			break;
		case "ZHRI102A": //Process to terminate an employee
			EmployeeTermination employeeTermination = new EmployeeTermination();
			completionStatus = employeeTermination.HR02_processMain((PszTriggerEmployee) trigger, commonParameters);
			break;
		case "ZHRI104A": //Process for job status change
			EmployeeJobProfileChange employeeJobProfileChange = new EmployeeJobProfileChange();
			completionStatus = employeeJobProfileChange.HR04_processMain((PszTriggerEmployee) trigger, commonParameters);
			break;
		case "ZHRI105A": //Process for demographics change
			EmployeeDemographicChange employeeDemographicChange = new EmployeeDemographicChange();
			completionStatus = employeeDemographicChange.HR05_processMain((PszTriggerEmployee) trigger, commonParameters);
			break;
		case "ZHRI106A": 
			//DO HR01-Process-Main       !ZHRI101A.SQC
			EmployeeNewHire employeeNewHire2 = new EmployeeNewHire();
			completionStatus = employeeNewHire2.HR01_processMain((PszTriggerEmployee) trigger, commonParameters);
			break;
		case "ZHRI107A": //Process for converting dates
			EmployeeDateChange employeeDateChange = new EmployeeDateChange();
			completionStatus = employeeDateChange.HR07_processMain((PszTriggerEmployee) trigger, commonParameters);
			break;
		case "ZHRI109A": //Process for group transfer
			EmployeeGroupTransfer employeeGroupTransfer = new EmployeeGroupTransfer();
			completionStatus = employeeGroupTransfer.HR09_processMain((PszTriggerEmployee) trigger, commonParameters);
			break;
		case "ZHRI101D": //Row deleted on hire
			commonParameters.setErrorProgramParameter("HRZ101A");
			commonParameters.setErrorMessageParameter("A row was deleted on the hire process");
			commonParameters.setCriticalFlag(true);
			executeErrorCommand(commonParameters);
			commonParameters.setCriticalFlag(false);
			completionStatus = "C";
			break;
		case "ZHRI102D": //Row deleted on term
			commonParameters.setErrorProgramParameter("HRZ102A");
			commonParameters.setErrorMessageParameter("A row was deleted on the termination process");
			commonParameters.setCriticalFlag(true);
			executeErrorCommand(commonParameters);
			commonParameters.setCriticalFlag(false);
			completionStatus = "C";
			break;
		case "ZHRI104D": //Row deleted on job status change
			commonParameters.setErrorProgramParameter("HRZ104A");
			commonParameters.setErrorMessageParameter("A row was deleted on the job profile process");
			commonParameters.setCriticalFlag(true);
			executeErrorCommand(commonParameters);
			commonParameters.setCriticalFlag(false);
			completionStatus = "C";
			break;
		case "ZHRI105D": //Row deleted on demographics change
			commonParameters.setErrorProgramParameter("HRZ105A");
			commonParameters.setErrorMessageParameter("A row was deleted on the demographics process");
			commonParameters.setCriticalFlag(true);
			executeErrorCommand(commonParameters);
			commonParameters.setCriticalFlag(false);
			completionStatus = "C";
			break;
		case "ZHRI106D": //Row deleted on rehire
			commonParameters.setErrorProgramParameter("HRZ101A");
			commonParameters.setErrorMessageParameter("A row was deleted on the re-hire process");
			commonParameters.setCriticalFlag(true);
			executeErrorCommand(commonParameters);
			commonParameters.setCriticalFlag(false);
			completionStatus = "C";
			break;
		case "ZHRI107D": //Row deleted on the dates process
			commonParameters.setErrorProgramParameter("HRZ107A");
			commonParameters.setErrorMessageParameter("A row was deleted on the dates process");
			commonParameters.setCriticalFlag(true);
			executeErrorCommand(commonParameters);
			commonParameters.setCriticalFlag(false);
			completionStatus = "C";
			break;
		case "ZHRI109D": //Row deleted on the group transfer process
			commonParameters.setErrorProgramParameter("HRZ109A");
			commonParameters.setErrorMessageParameter("A row was deleted on the group transfer process");
			commonParameters.setCriticalFlag(true);
			commonParameters.setProcessName(trigger.getProcessName());
			executeErrorCommand(commonParameters);
			commonParameters.setCriticalFlag(false);
			completionStatus = "C";
			break;
		case "ZHRI201A": //Non-person new hire
			commonParameters.setPoiFlag(true);
			NonPersonNewHire nonPersonNewHire = new NonPersonNewHire();
			completionStatus = nonPersonNewHire.HR201_processMain((PszTriggerNonPerson) trigger, commonParameters);
			break;
		case "ZHRI202A": //Non-person termination
			commonParameters.setPoiFlag(true);
			NonPersonTermination nonPersonTermination = new NonPersonTermination();
			completionStatus = nonPersonTermination.HR202_processMain((PszTriggerNonPerson) trigger, commonParameters);
			break;
		case "ZHRI205A":
			commonParameters.setPoiFlag(true);
			NonPersonDemographicChange nonPersonDemographicChange = new NonPersonDemographicChange();
			completionStatus = nonPersonDemographicChange.HR205_processMain((PszTriggerNonPerson) trigger, commonParameters);
			break;
		case "ZHRI206A": //Non-person 
			commonParameters.setPoiFlag(true);
			//DO HR201-Process-Main       !ZHRI201A.SQC
			NonPersonNewHire nonPersonNewHire2 = new NonPersonNewHire();
			completionStatus = nonPersonNewHire2.HR201_processMain((PszTriggerNonPerson) trigger, commonParameters);
			break;
		default: //ERROR
			//!update to an E to prevent looping and to mark the record in error
			completionStatus = "E";
			break;
		}
//		System.out.println("Command => " + commandString);
		return completionStatus;
	}

//	/**
//	 * @see Call-Programs in ZHRI100A.SQR
//	 * Subroutine will call appropriate programs
//	 * @param trigger
//	 * @param commonParameters
//	 * @return
//	 */
//	public static String ZHRI100A_callPrograms(PszTriggerSuperclass trigger) {
//		System.out.println("*** ZHRI100A_callPrograms");
//		CommonParameters commonParameters = new ProcessParameters().new CommonParameters();
//		//LET $WrkCriticalFlag = 'N'
//		commonParameters.setCriticalFlag(false); //from Process-Main
//		commonParameters.setProcessName(trigger.getProcessName());
//		commonParameters.setEmployeeId(trigger.getEmployeeId());
//		commonParameters.setOperatorId(trigger.getOperatorId());
//		commonParameters.setEffectiveDate(trigger.getEffectiveDate());
//		commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
//		commonParameters.setCompletionStatus(trigger.getCompletionStatus());
//		//BEGIN-PROCEDURE CALL-PROGRAMS
//		//LET $TrigTaskFlag = ''
//		String completionStatus = trigger.getCompletionStatus();
//		switch(trigger.getProcessName()) {
//		case "ZHRI101A": //Process to hire employee
//			commonParameters.setPoiFlag(false);
//			//WHEN = 'ZHRI101A'
//			//!Move fields to be used in the called SQC
//			//LET $Wrk_Oprid = $AuditOprid
////			commonParameters.setOperatorId(trigger.getOperatorId());
//			//LET $Wrk_Emplid = $PSEmplid
////			commonParameters.setEmployeeId(trigger.getEmployeeId());
//			//LET $Wrk_Effdt = $PSEffdt
////			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
//			//move #PSEffseq to #Wrk_Effseq
////			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
//			//LET $Wrk_Process_Name = $WrkProcess
////			commonParameters.setProcessName(trigger.getProcessName());
//			//LET $TrigTaskFlag = $WrkTaskFlag      !Surya Added - TEMPMAST
////			commonParameters.setCompletionStatus(trigger.getCompletionStatus());
//			//LET $Wrk_Inf_ = ' ' //this value is not used
//			//LET $ADAction_Code = 'H'
////			commonParameters.setActionCode("H");
//			//LET $ADLegOprid = ''
////			commonParameters.setLegacyOperatorId("");
//			//DO HR01-Process-Main    !ZHRI101A.SQC
//			EmployeeNewHire employeeNewHire = new EmployeeNewHire();
//			completionStatus = employeeNewHire.HR01_processMain((PszTriggerEmployee) trigger, commonParameters);
//			//BREAK
//			break;
//		case "ZHRI102A": //Process to terminate an employee
//			commonParameters.setPoiFlag(false);
//			//WHEN = 'ZHRI102A'
//			//!Move fields to be used in the called SQC
//			//MOVE #Wrk_Sequence to #WrkSeqNbr
////			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
//			//LET $PSAuditOperId = $AuditOprId
////			commonParameters.setOperatorId(trigger.getOperatorId());
//	        //LET $PSDateIn = $PSEffDt
////			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
//	        //LET $Wrk_Emplid = $PSEmplId
////			commonParameters.setEmployeeId(trigger.getEmployeeId());
//	        //LET $ADAction_Code = 'T'
////			commonParameters.setActionCode("T");
//	        //LET $ADLegOprid = ''
////			commonParameters.setLegacyOperatorId("");
//	        //DO HR02-Process-Main    !ZHRI102A.SQC
//			EmployeeTermination employeeTermination = new EmployeeTermination();
//			completionStatus = employeeTermination.HR02_processMain((PszTriggerEmployee) trigger, commonParameters);
//			//BREAK
//			break;
//		case "ZHRI104A": //Process for job status change
//			commonParameters.setPoiFlag(false);
//			//WHEN = 'ZHRI104A'
//			//!Move fields to be used in the called SQC
//			//LET $PSUserOprid = $AuditOprid
////			commonParameters.setOperatorId(trigger.getOperatorId());
//			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
////			commonParameters.setEmployeeId(trigger.getEmployeeId());
//			//Move #PSEffseq to #WrkEffseq
////			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
//			//LET $ADAction_Code = 'C'
////			commonParameters.setActionCode("C");
//			//LET $ADLegOprid = ''
////			commonParameters.setLegacyOperatorId("");
//			//DO HR04-Process-Main    !ZHRI104A.SQC
//			EmployeeJobProfileChange employeeJobProfileChange = new EmployeeJobProfileChange();
//			completionStatus = employeeJobProfileChange.HR04_processMain((PszTriggerEmployee) trigger, commonParameters);
//			//BREAK
//			break;
//		case "ZHRI105A": //Process for demographics change
//			commonParameters.setPoiFlag(false);
//			//WHEN = 'ZHRI105A'
//			//!Move fields to be used in the called SQC
//			//LET $PSemp = $AuditOprid
////			commonParameters.setOperatorId(trigger.getOperatorId());
//			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
////			commonParameters.setEmployeeId(trigger.getEmployeeId());
//			//LET $ADAction_Code = 'C'
////			commonParameters.setActionCode("C");
//			//LET $ADLegOprid = ''
////			commonParameters.setLegacyOperatorId("");
//			//LET $Wrk_ADCountryCdBuild = 'Y'                 !sree-UAAMOD
//			//DO HR05-Process-Main    !ZHRI105A.SQC
//			EmployeeDemographicChange employeeDemographicChange = new EmployeeDemographicChange();
//			completionStatus = employeeDemographicChange.HR05_processMain((PszTriggerEmployee) trigger, commonParameters);
//			//BREAK
//			break;
//		case "ZHRI106A": 
//			commonParameters.setPoiFlag(false);
//			//WHEN = 'ZHRI106A'
//			//!Move fields to be used in the called SQC
//			//LET $Wrk_Oprid = $AuditOprid
////			commonParameters.setOperatorId(trigger.getOperatorId());
//			//LET $Wrk_Emplid = $PSEmplid
////			commonParameters.setEmployeeId(trigger.getEmployeeId());
//			//LET $Wrk_Effdt = $PSEffdt
////			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
//			//move #PSEffseq to #Wrk_Effseq
////			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
//			//LET $Wrk_Process_Name = $WrkProcess
////			commonParameters.setProcessName(trigger.getProcessName());
//			//LET $ADAction_Code = 'R'
////			commonParameters.setActionCode("R");
//			//DO HR01-Process-Main       !ZHRI101A.SQC
//			//BREAK
//			break;
//		case "ZHRI107A": //Process for converting dates
//			commonParameters.setPoiFlag(false);
//			//WHEN = 'ZHRI107A'
//			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
////			commonParameters.setEmployeeId(trigger.getEmployeeId());
//			//LET $ADAction_Code = ''
////			commonParameters.setActionCode("");
//			//LET $ADLegOprid = ''
////			commonParameters.setLegacyOperatorId("");
//			//DO HR07-Process-Main
//			EmployeeDateChange employeeDateChange = new EmployeeDateChange();
//			completionStatus = employeeDateChange.HR07_processMain((PszTriggerEmployee) trigger, commonParameters);
//			//BREAK
//			break;
//		case "ZHRI109A": //Process for group transfer
//			commonParameters.setPoiFlag(false);
//			//WHEN = 'ZHRI109A'
//			//!Move fields to be used in the called SQC
//			//LET $PSUserOprid = $AuditOprid
////			commonParameters.setOperatorId(trigger.getOperatorId());
//			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
////			commonParameters.setEmployeeId(trigger.getEmployeeId());
//			//Move #PSEffseq to #WrkEffseq
////			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
//			//LET $ADAction_Code = 'C'
////			commonParameters.setActionCode("C");
//			//LET $ADLegOprid = ''
////			commonParameters.setLegacyOperatorId("");
//			//DO HR09-Process-Main        !ZHRI100A.SQC
//			EmployeeGroupTransfer employeeGroupTransfer = new EmployeeGroupTransfer();
//			completionStatus = employeeGroupTransfer.HR09_processMain((PszTriggerEmployee) trigger, commonParameters);
//			//BREAK
//			break;
//		case "ZHRI101D": //Row deleted on hire
//			//WHEN = 'ZHRI101D'     !Row deleted on hire
//			//LET $ErrorProgramParm = 'HRZ101A'
//			commonParameters.setErrorProgramParameter("HRZ101A");
//			//LET $ErrorMessageParm = 'A row was deleted on the hire process'
//			commonParameters.setErrorMessageParameter("A row was deleted on the hire process");
//			//LET $WrkCriticalFlag = 'Y'
//			commonParameters.setCriticalFlag(true);
//			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//			//DO Call-Error-Routine
//			executeErrorCommand(commonParameters);
//			//LET $WrkCriticalFlag = 'N'
//			commonParameters.setCriticalFlag(false);
//			//LET $CompletionStatus = 'C'
////			completionStatus = "C");
//			//DO UPDATE-TRIGGER-ROW
//			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
//			break;
//		case "ZHRI102D": //Row deleted on term
//			//WHEN = 'ZHRI102D'     !Row deleted on term
//			//LET $ErrorProgramParm = 'HRZ102A'
//			commonParameters.setErrorProgramParameter("HRZ102A");
//			//LET $ErrorMessageParm = 'A row was deleted on the termination process'
//			commonParameters.setErrorMessageParameter("A row was deleted on the termination process");
//			//LET $WrkCriticalFlag = 'Y'
//			commonParameters.setCriticalFlag(true);
//			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//			//DO Call-Error-Routine
//			executeErrorCommand(commonParameters);
//			//LET $WrkCriticalFlag = 'N'
//			commonParameters.setCriticalFlag(false);
//			//LET $CompletionStatus = 'C'
//			completionStatus = "C";
//			//DO UPDATE-TRIGGER-ROW
//			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
//			break;
//		case "ZHRI104D": //Row deleted on job status change
//			//WHEN = 'ZHRI104D'     !Row deleted on job status change
//			//LET $ErrorProgramParm = 'HRZ104A'
//			commonParameters.setErrorProgramParameter("HRZ104A");
//			//LET $ErrorMessageParm = 'A row was deleted on the job profile process'
//			commonParameters.setErrorMessageParameter("A row was deleted on the job profile process");
//			//LET $WrkCriticalFlag = 'Y'
//			commonParameters.setCriticalFlag(true);
//			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//			//DO Call-Error-Routine
//			executeErrorCommand(commonParameters);
//			//LET $WrkCriticalFlag = 'N'
//			commonParameters.setCriticalFlag(false);
//			//LET $CompletionStatus = 'C'
//			completionStatus = "C";
//			//DO UPDATE-TRIGGER-ROW
//			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
//			break;
//		case "ZHRI105D": //Row deleted on demographics change
//			//WHEN = 'ZHRI105D'     !Row deleted on demographics change
//			//LET $ErrorProgramParm = 'HRZ105A'
//			commonParameters.setErrorProgramParameter("HRZ105A");
//			//LET $ErrorMessageParm = 'A row was deleted on the demographics process'
//			commonParameters.setErrorMessageParameter("A row was deleted on the demographics process");
//			//LET $WrkCriticalFlag = 'Y'
//			commonParameters.setCriticalFlag(true);
//			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//			//DO Call-Error-Routine
//			executeErrorCommand(commonParameters);
//			//LET $WrkCriticalFlag = 'N'
//			commonParameters.setCriticalFlag(false);
//			//LET $CompletionStatus = 'C'
//			completionStatus = "C";
//			//DO UPDATE-TRIGGER-ROW
//			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
//			break;
//		case "ZHRI106D": //Row deleted on rehire
//			//WHEN = 'ZHRI106D'     !Row deleted on rehire
//			//LET $ErrorProgramParm = 'HRZ101A'
//			commonParameters.setErrorProgramParameter("HRZ101A");
//			//LET $ErrorMessageParm = 'A row was deleted on the re-hire process'
//			commonParameters.setErrorMessageParameter("A row was deleted on the re-hire process");
//			//LET $WrkCriticalFlag = 'Y'
//			commonParameters.setCriticalFlag(true);
//			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//			//DO Call-Error-Routine
//			executeErrorCommand(commonParameters);
//			//LET $WrkCriticalFlag = 'N'
//			commonParameters.setCriticalFlag(false);
//			//LET $CompletionStatus = 'C'
//			completionStatus = "C";
//			//DO UPDATE-TRIGGER-ROW
//			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
//			break;
//		case "ZHRI107D": //Row deleted on the dates process
//			//WHEN = 'ZHRI107D'     !Row deleted on the dates process
//			//LET $ErrorProgramParm = 'HRZ107A'
//			commonParameters.setErrorProgramParameter("HRZ107A");
//			//LET $ErrorMessageParm = 'A row was deleted on the dates process'
//			commonParameters.setErrorMessageParameter("A row was deleted on the dates process");
//			//LET $WrkCriticalFlag = 'Y'
//			commonParameters.setCriticalFlag(true);
//			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//			//DO Call-Error-Routine
//			executeErrorCommand(commonParameters);
//			//LET $WrkCriticalFlag = 'N'
//			commonParameters.setCriticalFlag(false);
//			//LET $CompletionStatus = 'C'
//			completionStatus = "C";
//			//DO UPDATE-TRIGGER-ROW
//			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
//			break;
//		case "ZHRI109D": //Row deleted on the group transfer process
//			//WHEN = 'ZHRI109D'
//			//LET $ErrorProgramParm = 'HRZ109A'
//			commonParameters.setErrorProgramParameter("HRZ109A");
//			//LET $ErrorMessageParm = 'A row was deleted on the group transfer process'
//			commonParameters.setErrorMessageParameter("A row was deleted on the group transfer process");
//			//LET $WrkCriticalFlag = 'Y'
//			commonParameters.setCriticalFlag(true);
//			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//			//DO Call-Error-Routine
//			commonParameters.setProcessName(trigger.getProcessName());
//			executeErrorCommand(commonParameters);
//			//LET $WrkCriticalFlag = 'N'
//			commonParameters.setCriticalFlag(false);
//			//LET $CompletionStatus = 'C'
//			completionStatus = "C";
//			//DO UPDATE-TRIGGER-ROW
//			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
//			break;
//		case "ZHRI201A": //Non-person new hire
//			commonParameters.setPoiFlag(true);
//			//WHEN = 'ZHRI201A'
//			//!Move fields to be used in the called SQC
//			//LET $Wrk_Oprid = $NAuditOprid
////			commonParameters.setOperatorId(trigger.getOperatorId());
//			//LET $Wrk_Emplid = $NPSEmplid
////			commonParameters.setEmployeeId(trigger.getEmployeeId());
//			//LET $Wrk_Effdt = $NPSEffdt
////			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
//			//MOVE #NPSEffseq to #Wrk_Effseq
////			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
//			//LET $Wrk_indexNum = to_char(#indexNum)
//			//LET $Wrk_Process_Name = $NWrkProcess
////			commonParameters.setProcessName(trigger.getProcessName());
//			//DO HR201-Process-Main    !ZHRI201A.SQC
//			NonPersonNewHire nonPersonNewHire = new NonPersonNewHire();
//			completionStatus = nonPersonNewHire.HR201_processMain((PszTriggerNonPerson) trigger, commonParameters);
//			//BREAK
//			break;
//		case "ZHRI202A": //Non-person termination
//			commonParameters.setPoiFlag(true);
//			//WHEN = 'ZHRI202A'
//			//!Move fields to be used in the called SQC
//			//LET $PSAuditOperId = $NAuditOprid
////			commonParameters.setAuditOperatorId(trigger.getOperatorId());
////			commonParameters.setOperatorId(trigger.getOperatorId());
//			//LET $PSDateIn = $NPSEffdt
////			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
//			//LET $Wrk_Emplid = $NPSEmplid 
////			commonParameters.setEmployeeId(trigger.getEmployeeId());
//			//LET $Wrk_indexNum = to_char(#indexNum)                   
////			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
//			//DO HR202-Process-Main    !ZHRI202A.SQC
//			NonPersonTermination nonPersonTermination = new NonPersonTermination();
//			completionStatus = nonPersonTermination.HR202_processMain((PszTriggerNonPerson) trigger, commonParameters);
//			//BREAK
//			break;
//		case "ZHRI205A":
//			commonParameters.setPoiFlag(true);
//			//WHEN = 'ZHRI205A'
//			//!Move fields to be used in the called SQC
//			//LET $PSAuditemp = $NAuditOprId
////			commonParameters.setAuditOperatorId(trigger.getOperatorId());
////			commonParameters.setOperatorId(trigger.getOperatorId());
//			//LET $Wrk_Emplid = $NPSEmplid                              
////			commonParameters.setEmployeeId(trigger.getEmployeeId());
//			//LET $Wrk_indexNum = to_char(#indexNum)
////			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
//			//LET $PSEffdt =  $NPSEffdt     
////			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
//			//DO HR205-Process-Main    !ZHRI105A.SQC
//			NonPersonDemographicChange nonPersonDemographicChange = new NonPersonDemographicChange();
//			completionStatus = nonPersonDemographicChange.HR205_processMain((PszTriggerNonPerson) trigger, commonParameters);
//			//BREAK
//			break;
//		case "ZHRI206A": //Non-person 
//			commonParameters.setPoiFlag(true);
//			//WHEN = 'ZHRI206A'
//			//!Move fields to be used in the called SQC
//			//LET $Wrk_Oprid = $NAuditOprid
////			commonParameters.setOperatorId(trigger.getOperatorId());			
//			//LET $Wrk_Emplid = $NPSEmplid
////			commonParameters.setEmployeeId(trigger.getEmployeeId());
//			//LET $Wrk_Effdt = $NPSEffdt
////			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
//			//MOVE #NPSEffseq to #Wrk_Effseq
////			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
//			//LET $Wrk_indexNum = to_char(#indexNum)
////			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
//			//LET $Wrk_Process_Name = $NWrkProcess
////			commonParameters.setProcessName(trigger.getProcessName());
//			//DO HR201-Process-Main       !ZHRI201A.SQC
//			NonPersonNewHire nonPersonNewHire2 = new NonPersonNewHire();
//			completionStatus = nonPersonNewHire2.HR201_processMain((PszTriggerNonPerson) trigger, commonParameters);
//			//BREAK
//			break;
//		default: //ERROR
//			//WHEN-OTHER
//			//LET $CompletionStatus = 'E'
//			//!update to an E to prevent looping and to mark the record in error
//			completionStatus = "E";
//			if(trigger instanceof PszTriggerEmployee) {
//				//DO UPDATE-TRIGGER-ROW
//				PszTriggerEmployee.setCompletionStatusBySequenceNumber("E", trigger.getSequenceNumber());
//			}
//			else {
//				//DO UPDATE-TRIGGER-ROW-NONEMP  !Surya Added - TEMPMAST 
//				PszTriggerNonPerson.setCompletionStatusBySequenceNumber("E", trigger.getSequenceNumber());
//			}
//			//BREAK
//			break;
//		//END-EVALUATE
//		}
////		System.out.println("Command => " + commandString);
//		return completionStatus;
//		//END-PROCEDURE CALL-PROGRAMS
//	}

	/**
	 * This procedure will get the trigger data for non employees and multiple EIDs that needs to be interfaced
	 * @see Get-Trigger-Data-NonEmp in ZHRI100A.SQR
	 */
	//TODO: 
	public static void ZHRI100A_getTriggerDataNonEmp() {
		System.out.println("*** ZHRI100A.ZHRI100A_getTriggerDataNonEmp() ***");
		
	}

	/**
	 * Subroutine will call appropriate programs for Non Emp
	 * @see Call-Programs-NonEmp in ZHRI100A.SQR
	 */
	//TODO: 
	public static void ZHRI100A_callProgramsNonEmp() {
		System.out.println("*** ZHRI100A.ZHRI100A_callProgramsNonEmp() ***");
		
	}

	/**
	 * This procedure will get the trigger data that needs to be interfaced
	 * @see Get-Trigger-Data-POI-Emp-Convert in ZHRI100A.SQR
	 */
	//TODO: 
	public static void ZHRI100A_getTriggerDataPoiEmpConvert() {
		System.out.println("*** ZHRI100A.ZHRI100A_getTriggerDataPoiEmpConvert()");
		
	}

	/**
	 * This routine update the trigger file flag switch for Non Emp
	 * @see Update-Trigger-Row-NonEmp in ZHRI100A.SQR
	 */
	//TODO: 
	public static void ZHRI100A_updateTriggerRowNonEmp() {
		System.out.println("*** ZHRI100A.ZHRI100A_updateTriggerRowNonEmp()");
		
	}

	/**
	 * Get the WHERE Clause for the delay hours in POI to EMP Transfer
	 * @see ZHRI100ABuiltWhereClauseDelay in ZHRI100A.SQR
	 */
	public static String ZHRI100A_buildWhereClauseDelay() {
		System.out.println("*** ZHRI100A.ZHRI100A_buildWhereClauseDelay() ***");
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
	 * Remotely executes a command on the AS400.
	 * @see Call-System in ZHRI100A.SQR
	 * @param commandString
	 * @param commonParameters
	 * @return 0 if success, non-zero if error
	 */
	public static Integer executeRemoteCommand(String commandString, CommonParameters commonParameters) {
		System.out.println("*** ZHRI100A.ZHRI100A_callSystem() ***");
		Integer status = executeCommandRexec(commandString);
		if(status != 0) { //!error
			commonParameters.setErrorProgramParameter("ZHRI100A");
			commonParameters.setErrorMessageParameter("Error executing Call System command, contact HR-PeopleSoft On-Call");
			commonParameters.setCriticalFlag(true);
			commandString = composeAs400RexecCommandString("HRZ110A", composeErrorParameterString(commonParameters));
			commonParameters.setCriticalFlag(false);
		}
		return status;
	}

//	/**
//	 * @see Call-System in ZHRI100A.SQR
//	 * Executes a command line statement stored in the $Command Variable
//	 * @param command
//	 * @param commonParameters
//	 * @return 0 if success, non-zero if error
//	 */
//	public static Integer ZHRI100A_callSystem(String commandString, CommonParameters commonParameters) {
//		System.out.println("*** ZHRI100A_callSystem");
//		Integer status; //#Status
////		String showCommand;
////		Integer commandLength = command.length();
////		Integer substringStartPosition = 1;
//		//BEGIN-PROCEDURE CALL-SYSTEM
//		//LET #CommandLength = length($Command)   !Get the length of the command
////		commandLength = command.length();
//		//LET #SubstrStartPos = 1  !Initiate the starting positions to show the first 100 positions
////		substringStartPosition = 1;
//		//WHILE #SubstrStartPos <= #CommandLength
////		while(substringStartPosition <= commandLength) {
//			//LET $ShowCommand = SUBSTR($Command, #SubstrStartPos, 100)  !Substring 100 positions to show
////			showCommand = command.substring(substringStartPosition, 100);
//			//LET #SubstrStartPos = #SubstrStartPos + 100  !Increase the starting position by 100
////			substringStartPosition += 100;
//			//SHOW $ShowCommand  !ZHR_MOD_ZHRI100A_110B
////			System.out.println("command: " + command);
//		//END-WHILE   !#SubstrStartPos <= #CommandLength
////		}
//		//LET $Command = $RexecScript || ' ' || $Command || ' ' || $RMTSVR
////		command = commonParameters.getRemoteExecScript() + " " +  command + " " + commonParameters.getRemoteServerName();
//		//Call System Using $Command #Status Wait  !Execute the command that was built on the command waiting until completion
//		status = executeCommand(commandString);
//		//IF #status != 0
//		if(status != 0) {
//			//!error
//			//LET $ErrorProgramParm = 'ZHRI100A'
//			commonParameters.setErrorProgramParameter("ZHRI100A");
//			//LET $ErrorMessageParm = ' '
//			//LET $ErrorMessageParm = 'Error executing Call System command, contact HR-PeopleSoft On-Call'
//			commonParameters.setErrorMessageParameter("Error executing Call System command, contact HR-PeopleSoft On-Call");
//			//LET $WrkCriticalFlag = 'Y'
//			commonParameters.setCriticalFlag(true);
//			//DO Prepare-Error-Parms
//			//IF $PoiFlag = 'N'
//			if(!commonParameters.getPoiFlag()) {
//				//DO Call-Error-Routine
//				//TODO: 
//				commandString = composeAs400RexecCommandString("HRZ110A", composeErrorParameterString(commonParameters));
//			//ELSE
//			}
//			else {
//				//DO Call-Error-Routine-NonEmp
//				//TODO: 
//				commandString = composeAs400RexecCommandString("HRZ110A", composeErrorParameterString(commonParameters));
//			//END-IF
//			}
//			//LET $WrkCriticalFlag  = 'N'
//			commonParameters.setCriticalFlag(false);
//		}
//		//END-IF
//		return status;
//		//END-PROCEDURE CALL-SYSTEM
//	}

//	/**
//	 * @see Prepare-Error-Parms in ZHRI100A.SQR
//	 * Makes sure that the parms are the correct length for the error routine RPG program to receive them
//	 * @param commonParameters
//	 */
//	private static void ZHRI100A_prepareErrorParms(CommonParameters commonParameters) {
//		System.out.println("*** ZHRI100A_prepareErrorParms");
//		//BEGIN-PROCEDURE PREPARE-ERROR-PARMS
//		//!Prepare the date and time parms
//		//DO Get-Current-DateTime                                 !Get the current date and time
//		Calendar now = Calendar.getInstance();
//		//LET $AddDateErrorParm = DATETOSTR(STRTODATE($AsOfToday,'DD-MON-YYYY'),'YYYYMMDD')
//		commonParameters.setErrorDateParameter(
//				String.format("%04d", now.get(Calendar.YEAR)) 
//				+ String.format("%02d", now.get(Calendar.MONTH)) 
//				+ String.format("%02d", now.get(Calendar.DATE)));
//		//LET $AddTimeErrorParm = substr($Out,10,2) || substr($Out,13,2) || substr($Out,16,2)
//		commonParameters.setErrorTimeParameter(
//				String.format("%02d", now.get(Calendar.HOUR_OF_DAY)) 
//				+ String.format("%02d", now.get(Calendar.MINUTE)) 
//				+ String.format("%02d", now.get(Calendar.SECOND)));
//		//LET $OprIdErrorParm = Substr($AuditOprid,2,5)
//		String operatorIdParameter = commonParameters.getOperatorId();
//		if(operatorIdParameter != null) {
//			operatorIdParameter = operatorIdParameter.toUpperCase();
//			if(operatorIdParameter.startsWith("E")) {
//				operatorIdParameter = operatorIdParameter.substring(1);
//			}
//		}
//		//END-PROCEDURE PREPARE-ERROR-PARMS
//	}

//	/**
//	 * @see Call-Error-Routine-NonEmp in ZHRI100A.SQR
//	 * Builds the command and calls the error routine
//	 * @param processName
//	 * @param commonParameters
//	 * @return command text
//	 */
//	private static String executeErrorCommandNonEmp(CommonParameters commonParameters) {
//		System.out.println("*** executeErrorCommandNonEmp()");
//		String commandString = composeAs400RexecCommandString("HRZ110A", composeErrorParameterString(commonParameters));
////		System.out.println(commandString);
//		return commandString;
//	}

	/**
	 * executeErrorCommand
	 * Composes the error command and executes it on the AS400.
	 * @see Call-Error-Routine in ZHRI100A.SQR
	 * @param processName
	 * @param commonParameters
	 */
	public static void executeErrorCommand(CommonParameters commonParameters) {
		System.out.println("*** executeErrorCommand() ***");
		String processName = "HRZ110A";
		String commandString = composeAs400RexecCommandString(processName, composeErrorParameterString(commonParameters));
		executeRemoteCommand(commandString, commonParameters);
	}


	/**
	 * This routine will build the where clause that will select the correct Run-ID to use.
	 * @see Build-Group-Where-Clause in ZHRI100A.SQR
	 * @param whereClause
	 * @return SQL where clause
	 */
	public static String ZHRI100A_buildGroupWhereClause(String whereClause) {
		System.out.println("*** ZHRI100A_buildGroupWhereClause() ***");
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
	 * Builds a where clause based on an employee id entered by the user.
	 * @see Build-EmplId-Where-Clause in ZHRI100A.SQR
	 * @return SQL where clause
	 */
	public static String ZHRI100A_buildEmplIdWhereClause() {
		System.out.println("*** ZHRI100A_buildEmplIdWhereClause() ***");
		String alias = ""; //TODO: I can't find where this value is set
		String runId = ""; //TODO: I can't find where this value is set
		//BEGIN-PROCEDURE BUILD-EMPLID-WHERE-CLAUSE
		//LET $WhereClause = 'WHERE ((' || $Alias || '.HMREMP = ' || '''' || $Run-Id || ''''  !Create the where clause
		String whereClause = "WHERE ((" + alias + ".HMREMP = " + "'" + runId + "'";
		//END-PROCEDURE BUILD-EMPLID-WHERE-CLAUSE
		return whereClause;
	}

	/**
	 * This routine gets the legacy employee ID from the cross reference table
	 * @see Get-OprId in ZHRI100A.SQR
	 * @param commonParameters
	 * @param eidIndexNumber
	 * @return legacyEmployeeId
	 */
	public static String findLegacyEmployeeId(CommonParameters commonParameters) {
		System.out.println("*** findLegacyEmployeeId() ***");
		String employeeId;
		if(commonParameters.getPoiFlag()) {
			employeeId = CrossReferenceMultipleEmployeeId.ZHRI100A_getLegIdForSeqNum(commonParameters.getEmployeeId(), commonParameters.getEidIndexNumber());
		}
		else {
			employeeId = CrossReferenceEmployeeId.ZHRI100A_getLegIdForSeq0(commonParameters.getEmployeeId());
		}
		if(employeeId == null || employeeId.isEmpty()) {
			BigDecimal indexNumber = commonParameters.getPoiFlag() ? commonParameters.getEffectiveSequence() : new BigDecimal(0);
			employeeId = findNewLegacyEmployeeId(commonParameters.getEmployeeId(), indexNumber);
		}
		return employeeId;
	}

//	/**
//	 * This routine gets the operator id from the operator definition table
//	 * @see Get-OprId in ZHRI100A.SQR
//	 * @param employeeId
//	 * @param indexNumber
//	 * @param commonParameters
//	 * @return legacyEmployeeId
//	 */
//	public static String ZHRI100A_getOprId(CommonParameters commonParameters) {
//		return ZHRI100A_getOprId(commonParameters,  null); 
//	}
//	public static String ZHRI100A_getOprId(CommonParameters commonParameters, BigDecimal eidIndexNumber) {
//		System.out.println("*** ZHRI100A_getOprId()");
//		BigDecimal indexNumber = commonParameters.getEffectiveSequence();
//		//BEGIN-PROCEDURE GET-OPRID
//		//LET $Found = 'N'
//		Boolean found = false;
//		//LET $PSOprId = ''
//		String employeeId;
//		//IF $PoiFlag = 'N'
//		if(!commonParameters.getPoiFlag()) {
//			//MOVE 0 TO #indexNum
//			indexNumber = new BigDecimal(0);
//		//END-IF
//		}
//		//IF #indexNum = 0
//		if(indexNumber.equals(0)) {
////			DO GET-LEGID-FOR-SEQ0
//			employeeId = CrossReferenceEmployeeId.ZHRI100A_getLegIdForSeq0(commonParameters.getEmployeeId());
//		}
//		//ELSE
//		else {
//			//DO GET-LEGID-FOR-SEQNUM
//			employeeId = CrossReferenceMultipleEmployeeId.ZHRI100A_getLegIdForSeqNum(commonParameters.getEmployeeId(), eidIndexNumber);
//		//END-IF
//		}
//		if(employeeId != null && !employeeId.isEmpty()) {
//			found = true;
//		}
//		//!If an OprId does not exist for the employee, create one
//		//IF ($Found = 'N')
//		if(!found) {
//			//DO GET-LEGACY-OPRID
//			//LET $PSOprId = $LegEmplid
//			employeeId = ZHRI100A_getLegacyOprId(commonParameters.getEmployeeId(), indexNumber, commonParameters.getPoiFlag());
//		//END-IF  !$Found = 'N'
//		}
//		//END-PROCEDURE GET-OPRID
//		return employeeId;
//	}

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
	//! Procedure:  Initialize-AD-WrkFields
	//!----------------------------------------------------------------------
//	/**
//	 * 
//	 * @return
//	 */
//	public static CommonParameters initializeCommonParameters() {
//		System.out.println("*** initializeCommonParameters()");
//		CommonParameters commonParameters = new ProcessParameters().new CommonParameters();
//		return commonParameters;
//	}

	/**
	 * initializeServerProperties
	 * Sets the values for the remote AS400 server in a static class that shares the values across the application. 
	 */
	public static void initializeServerProperties() {
		System.out.println("*** initializeServerProperties() ***");
		String processName = "ZHRI100A";
		ServerProperties.setDbName(PsDbOwner.findDbName());
//		commonParameters.setPeopleSoftHomePath(System.getenv("PS_HOME")); //TODO: ********
		ServerProperties.setPeopleSoftHomePath("C:/people soft/");
//		commonParameters.setOracleSystemId(System.getenv("ORACLE_SID")); //TODO: ********
		ServerProperties.setOracleSystemId("PS90HRQA");
		if(ServerProperties.getOracleSystemId() != null) {
			ServerProperties.setOracleSystemId(ServerProperties.getOracleSystemId().toUpperCase());
		}
		ServerProperties.setRemoteServerUsername("PSHRINT");
		ServerProperties.setRemoteServerPassword("SMRHET01");
		//get server property values from the variables table
		ServerProperties.setRemoteServerHostName(PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, ServerProperties.getDbName(), "RMTSVR"));
		ServerProperties.setAs400Library(PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, ServerProperties.getDbName(), "AS400library"));
	}

//	/**
//	 * 
//	 * @return
//	 */
//	public static void initializeServerProperties() {
//		System.out.println("*** initializeServerProperties()");
//		String processName = "ZHRI100A";
//		ServerProperties.setDbName(PsDbOwner.findDbName());
//		//LET $PS_HOME = getenv('PS_HOME')  !This gets the oracle_sid
////		commonParameters.setPeopleSoftHomePath(System.getenv("PS_HOME")); //TODO: ********
//		ServerProperties.setPeopleSoftHomePath("C:/people soft/");
//		//LET $AD_HOME = $PS_HOME || '/data/activedir/'  !Path for Active Directory
////		commonParameters.setActiveDirectoryHomePath(commonParameters.peopleSoftHomePath + "/data/activedir/"); //TODO: where is this used???
//		//LET $ORACLE_SID = getenv('ORACLE_SID') 
////		commonParameters.setOracleSystemId(System.getenv("ORACLE_SID")); //TODO: ********
//		ServerProperties.setOracleSystemId("PS90HRQA");
//		if(ServerProperties.getOracleSystemId() != null) {
//			//UPPERCASE $ORACLE_SID                  
//			ServerProperties.setOracleSystemId(ServerProperties.getOracleSystemId().toUpperCase());
//		}
//		//!Returns name of AS/400 machine for use in zbas002b.sh
//		//LET $Variable_Needed = ' '            
//		//LET $Variable_Needed = 'RMTSVR'       
//		//DO  Get-Variable                      
//		//LET $RMTSVR = $PSZPTT_VARIABLE_VAL      
//		//Show '$RMTSVR: ' $RMTSVR                                                            
//		ServerProperties.setRemoteServerName(PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, ServerProperties.getDbName(), "RMTSVR")); //TODO: where is this used???
//		//LET $RexecScript = '/usr/local/barch/' || $ORACLE_SID || '/scripts/zbas002b.sh'     
//		//Show '$RexecScript: ' $RexecScript                                                  
//		ServerProperties.setRemoteExecScript("/usr/local/barch/" + ServerProperties.getOracleSystemId() + "/scripts/zbas002b.sh"); //TODO: where is this used???
//		//!Returns library name on AS/400 where programs reside
//		//LET $Variable_Needed = ' '            
//		//LET $Variable_Needed = 'AS400library' 
//		//DO  Get-Variable                      
//		//LET $Library = $PSZPTT_VARIABLE_VAL   
//		//Show '$Library: ' $Library                                                          
//		ServerProperties.setAs400Library(PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, ServerProperties.getDbName(), "AS400library"));
//		//!Returns IP address of NT server
//		//LET $Variable_Needed = ' '             
//		//LET $Variable_Needed = 'RMTNTADSVR'    
//		//DO Get-Variable                        
//		//LET $RMTNTADSVR = $PSZPTT_VARIABLE_VAL 
//		//Show '$RMTNTADSVR: ' $RMTNTADSVR                                                    
//		ServerProperties.setRemoteAdServerName(PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, ServerProperties.getDbName(), "RMTNTADSVR")); //TODO: where is this used???
//	}

	/**
	 * This procedure will check the existence run file
	 * @see Check-Interface-Runfile in ZHRI100A.SQR
	 * @param oracleSystemId
	 * @return true if run file does not exist
	 */
	public static Boolean ZHRI100A_checkInterfaceRunFile(String oracleSystemId) {
		System.out.println("*** ZHRI100A_checkInterfaceRunFile() ***");
		Boolean runFlag = false;
//		String runFilePath;
		//LET $RUN_FILEPATH = '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'  //concatenate
//		runFilePath = "/usr/local/barch/" + oracleSystemId + "/work/hrinterface.run";
		//LET #file_exists = exists($RUN_FILEPATH)
//		Boolean fileExists = (new File(runFilePath)).exists();
		//****************************************************************************************************
		//TODO: 
		Boolean fileExists = true;
		//****************************************************************************************************
		//TODO: 
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
	
//	/**
//	 * @see Get-Trigger-Data in ZHRI100A.SQR
//	 * This procedure will get the trigger data that needs to be interfaced
//	 * @param commonParameters
//	 */
//	public static void ZHRI100A_getTriggerData_OLD() {
//		System.out.println("*** ZHRI100A_getTriggerData()");
//		//asOfToday
//		//sysDate
//		//fileOpen
////		PszTriggerEmployee trigger = PszTriggerEmployee.createMockTriggerForEmployeeTermination();
//		List<PszTriggerEmployee> triggerList = PszTriggerEmployee.findByCompletionStatusAndProcessName("P", "ZHRI102A");
////		System.out.println("triggerList == null: " + (triggerList == null));
////		System.out.println("triggerList.isEmpty(): " + triggerList.isEmpty());
//		PszTriggerEmployee trigger = triggerList != null && !triggerList.isEmpty() ? triggerList.get(0) : PszTriggerEmployee.createMockTriggerForEmployeeTermination();
//		System.out.println("******** trigger: \n" + trigger.toString());
////		Boolean adFound = false; //$AdFound  //TODO: where should this be set???
//		//BEGIN-PROCEDURE GET-TRIGGER-DATA
//		//LET $CompletionStatus = 'P'   !Initialize the CompletionStatus field
//		String completionStatus = "P";
//		//LET $PoiFlag = ''  !Initialize the POIFlag -- Surya Added - TEMPMAST 
//		//LET #WrkSequence = 0
//		//LET $AuditOprid = ''
//		//LET $PSEmplid = ''
//		//LET #Wrk_EmplID1 = 0
//		//LET $Wrk_Emplid2 = ''
//		//LET $PSEffdt = ''
//		//LET #PSEffSeq = 0
//		//LET $WrkProcess= ''
//		//LET $WrkTaskFlag = ''
//		//BEGIN-SELECT LOOPS=150
//		//RZ.SEQ_NBR
//		//MOVE &RZ.SEQ_NBR TO #WrkSequence
//		//RZ.OPRID
//		//LET $AuditOprid = LTRIM(RTRIM(&RZ.OPRID,' '),' ')
//		//RZ.EMPLID
//		//LET $PSEmplid = LTRIM(RTRIM(&RZ.EMPLID,' '),' ')
//		//MOVE $PSEmplid TO #Wrk_EmplID1
//		//LET $Wrk_Emplid2 =  EDIT(#Wrk_EmplID1,'099999999')
//		//TO_CHAR(RZ.EFFDT, 'YYYY-MM-DD') &RZEFFDT
//		//LET $PSEffdt = &RZEFFDT
//		//RZ.EFFSEQ
//		//MOVE &RZ.EFFSEQ TO #PSEffSeq
//		//RZ.PROC_NAME
//		//LET $WrkProcess = LTRIM(RTRIM(&RZ.PROC_NAME,' '),' ')           !Remove leading and trailing blanks
//		//RZ.TASK_FLAG                                                        !Surya Added - TEMPMAST 
//		//LET $WrkTaskFlag = LTRIM(RTRIM(&RZ.TASK_FLAG,' '),' ')          !Surya Added - TEMPMAST 
//		//IF $file_open = 'N'
//		Boolean fileIsOpen = false;
//		if(!fileIsOpen) {
//			//!OPEN $open_file1 AS 1 FOR-APPEND RECORD=337
//			//!LET $file_open = 'Y'
//			fileIsOpen = true;
//			//do nothing
//		//END-IF
//		}
//		//LET $PoiFlag = 'N'  !Surya Added - TEMPMAST 
////		commonParameters.setPoiFlag(false);
//		//DO Check-If-Contractor
//		Boolean isContractor = PsJob.ZHRI100A_checkIfContractor(trigger.getEmployeeId());
//		//IF $Found = 'N' AND  $PSEmplid <> ''  
//		//!Not a contractor and not a blank EmplId   !ZHR_MOD_ZHRI100A_110A
//		if(!isContractor && trigger.getEmployeeId() != null && !trigger.getEmployeeId().isEmpty()) {
//			//Added a check for 'ZHRI102A' - to see if corresponding row on JOB 
//			//IF $WrkProcess = 'ZHRI102A'
//			if("ZHRI102A".equalsIgnoreCase(trigger.getProcessName())) {
//				//DO Check-If-Correct102A
//				Boolean isOkToProcess = PsJob.ZHRI100A_checkIfCorrect102A(trigger.getEmployeeId(), trigger.getEffectiveDate(), trigger.getProcessName());
////				System.out.println("******* isOkToProcess: " + isOkToProcess);
//				//IF $OK-to-process = 'Y'
//				if(isOkToProcess) {
//					//DO Call-Programs
//					completionStatus = ZHRI100A_callPrograms(trigger);
//				}
//				//ELSE                                                           
//				else {
//					//LET $CompletionStatus = 'C'                                 
//					completionStatus = "C";
//					//DO UPDATE-TRIGGER-ROW                                       
////					int numberOfRecordsUpdated = PszTriggerEmployee.setCompletionStatusBySequenceNumber(completionStatus, trigger.getSequenceNumber());
//					PszTriggerEmployee.setCompletionStatusBySequenceNumber(completionStatus, trigger.getSequenceNumber());
//					completionStatus = "P";     //!Reset the completion Status for next pass //from Update-Trigger-Row
//				//END-IF                                                         
//				}
//			}
//			//ELSE IF $WrkProcess = 'ZHRI101A' 
//			else if("ZHRI101A".equalsIgnoreCase(trigger.getProcessName())) {
//				//DO Check-If-POI-Termed
////				String poiTermedCompletionStatus = PszTriggerNonPerson.ZHRI100A_checkIfPoiTermed(trigger.getEmployeeId());
////				//IF ($taskflag = 'C' OR $taskflag ='P')
////				if("C".equalsIgnoreCase(poiTermedCompletionStatus) || "P".equalsIgnoreCase(poiTermedCompletionStatus)) {
////					//LET $CompletionStatus = 'W'
////					completionStatus = "W";
////					//DO UPDATE-TRIGGER-ROW
////					PszTriggerEmployee.setCompletionStatusBySequenceNumber(completionStatus, trigger.getSequenceNumber());
////					completionStatus = "P";     //!Reset the completion Status for next pass //from Update-Trigger-Row
////				}
////				//ELSE
////				else {
////					//DO CALL-PROGRAMS                              
////					completionStatus = ZHRI100A_callPrograms(trigger);
////				//END-IF 
////				}
//			}
//			//ELSE
//			else {
//				//DO CALL-PROGRAMS               
//				completionStatus = ZHRI100A_callPrograms(trigger);
//			//ELSE IF
//			}
//		}
//		//ELSE
//		else {
//			//IF $Found = 'Y'
//			if(isContractor) {
//				//LET $CompletionStatus = 'C'
//				completionStatus = "C";
//			//END-IF
//			}
//			//IF  $PSEmplid = ''
//			if(trigger.getEmployeeId() == null || trigger.getEmployeeId().isEmpty()) {
//				//LET $CompletionStatus = 'E'
//				completionStatus = "E";
//			//END-IF
//			}
//		//END-IF  !$Found = 'N'
//		}
//		//IF $CompletionStatus <> 'P'
//		if(!"P".equalsIgnoreCase(completionStatus)) {
//			//DO UPDATE-TRIGGER-ROW
////			int numberOfRecordsUpdated = PszTriggerEmployee.setCompletionStatusBySequenceNumber(completionStatus, trigger.getSequenceNumber());
//			PszTriggerEmployee.setCompletionStatusBySequenceNumber(completionStatus, trigger.getSequenceNumber());
//			completionStatus = "P";     //!Reset the completion Status for next pass //from Update-Trigger-Row
////			System.out.println("numberOfRecordsUpdated: " + numberOfRecordsUpdated);
//		//END-IF  !$CompletionStatus <> 'P'
//		}
//		//FROM PS_ZHRT_INTTRIGGER RZ ,PS_JOB JB
//		//WHERE RZ.TASK_FLAG = 'P'
//		//		AND (RZ.EFFDT <= $AsOfToday or RZ.PROC_NAME='ZHRI101A' OR  RZ.PROC_NAME='ZHRI106A')
//		//  	AND (CASE WHEN PROC_NAME IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END) = 
//		//  		(SELECT MIN(CASE WHEN PROC_NAME IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END)  
//		//      			FROM  PS_ZHRT_INTTRIGGER RZ2
//		//      			WHERE RZ2.EMPLID = RZ.EMPLID
//		//      				AND RZ2.TASK_FLAG = 'P'
//		//        				AND (RZ2.EFFDT <= SYSDATE
//		//								OR RZ2.PROC_NAME='ZHRI101A'
//		//								OR RZ2.PROC_NAME='ZHRI106A'))
//		//!Surya - TEMPMAST Added the below NOT IN to restrict the rows other than 101 to process when there is a delay
//		//		AND RZ.EMPLID NOT IN (SELECT I.EMPLID FROM PS_ZHRT_INTTRIGGER I WHERE I.EMPLID = RZ.EMPLID AND I.TASK_FLAG = 'W') 
//		//  	AND JB.EMPLID = RZ.EMPLID
//		//  	AND JB.EFFDT = 
//		//			(SELECT MAX(JB2.EFFDT) FROM  PS_JOB JB2
//		//         			WHERE  JB2.EMPLID = JB.EMPLID
//		//          			AND  JB2.EMPL_RCD = JB.EMPL_RCD)
//		//  	AND JB.EFFSEQ = 
//		//			(SELECT MAX(JB3.EFFSEQ) FROM PS_JOB JB3
//		//                	WHERE JB3.EMPLID = JB.EMPLID
//		//                 		AND  JB3.EMPL_RCD = JB.EMPL_RCD
//		//                 		AND  JB3.EFFDT = JB.EFFDT)
//		//END-SELECT
//		//END-PROCEDURE GET-TRIGGER-DATA
////		System.out.println("completionStatus: " + completionStatus);
//	}
	
	/**
	 * This procedure will get the trigger data that needs to be interfaced
	 * @see Get-Trigger-Data in ZHRI100A.SQR
	 * @param trigger
	 * @return completionStatus - "P" if trigger record is good to process
	 */
	public static String checkTriggerRecord(PszTriggerSuperclass trigger) {
		System.out.println("*** checkTriggerRecord() ***");
		String completionStatus = trigger.getCompletionStatus();
		//****************************************************************************************************
		//TODO
		Boolean fileIsOpen = false;
		if(!fileIsOpen) { //TODO: check for file open
			//!OPEN $open_file1 AS 1 FOR-APPEND RECORD=337
			//!LET $file_open = 'Y'
			fileIsOpen = true;
			//do nothing
		//END-IF
		}
		//TODO
		//****************************************************************************************************
		//set status to error
		if(trigger.getEmployeeId() == null || trigger.getEmployeeId().isEmpty()) {
			completionStatus = "E";
		}
		else if(PsJob.ZHRI100A_checkIfContractor(trigger.getEmployeeId())) {
			//set status to complete, so this event doesn't come up again
			completionStatus = "C";
		}
		else { //not a contractor and not a blank EmplId
			//Added a check for 'ZHRI102A' - to see if corresponding row on JOB 
			if("ZHRI102A".equalsIgnoreCase(trigger.getProcessName())) {
				if(!PsJob.ZHRI100A_checkIfCorrect102A(trigger.getEmployeeId(), trigger.getEffectiveDate(), trigger.getProcessName())) {
					//set status to complete, so this event doesn't come up again
					completionStatus = "C";
				}
			}
			else if("ZHRI101A".equalsIgnoreCase(trigger.getProcessName())) {
				if(!PszTriggerNonPerson.ZHRI100A_checkIfPoiTermed(trigger.getEmployeeId())) {
					//set status to wait
					completionStatus = "W";
				}
			}
		}
		return completionStatus;
	}
	
	/**
	 * Formulates legacy OprId from HR036P where HR036P.H36EM# = #wrk_emplid and HR036P.H36INX = #indexNum UNION
	 * @see Get-Legacy-OprId in ZHRI100A.SQR
	 * @param employeeId
	 * @param indexNumber - value is 0 if employee is false
	 * @return legacyEmployeeId
	 */
	public static String findNewLegacyEmployeeId(String employeeId, BigDecimal indexNumber) {
		System.out.println("*** findNewLegacyEmployeeId() ***");
		String legacyEmployeeId = null;
		String legacyEmployeeName;
		Integer employeeNumber = -1;
		try {
			employeeNumber = Integer.parseInt(employeeId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		HR036P hr036P = HR036P.findByEmployeeNumberAndIndexNumber(employeeNumber, indexNumber);
    	if(hr036P != null) {
			if(hr036P.getEmployeeNumber() == employeeNumber) {
				legacyEmployeeName = hr036P.getEmployeeName();
		    	if(legacyEmployeeName != null && !legacyEmployeeName.isEmpty()) {
		    		legacyEmployeeName = erd.StringUtil.formatLegacyEmployeeNameToPeopleSoftEmployeeName(hr036P.getEmployeeName());
		    	}
	    		legacyEmployeeId = hr036P.getEmployeeId();
		    	if(legacyEmployeeId != null && legacyEmployeeId.length() > 5) {
		    		legacyEmployeeId = legacyEmployeeId.substring(0, 5);
		    	}
		    	CrossReferenceMultipleEmployeeId.saveNewLegacyEmployeeId(employeeId, legacyEmployeeId, indexNumber);
			}
			return legacyEmployeeId;
    	}
    	return null;
	}
	
//	/**
//	 * @see Get-Legacy-OprId in ZHRI100A.SQR
//	 * Gets the new OprId from the legacy system
//	 * Formulates legacy OprId from HR036P where HR036P.H36EM# = #wrk_emplid and HR036P.H36INX = #indexNum UNION
//	 * @param employeeId
//	 * @param indexNumber
//	 * @param poiFlag
//	 * @return legacyEmployeeId
//	 */
//	public static String ZHRI100A_getLegacyOprId(String employeeId, BigDecimal indexNumber, Boolean poiFlag) {
//		System.out.println("*** ZHRI100A_getLegacyOprId()");
//		//Begin-Procedure Get-Legacy-Oprid                !sree**10/04/01
//		//LET $LegEmplid = ''
//		String legacyEmployeeId;
//		String legacyEmployeeName;
//		//MOVE $Wrk_Emplid TO #wrk_emplid                 !sree***10/04/01
//		Integer employeeNumber = -1;
//		try {
//			employeeNumber = Integer.parseInt(employeeId);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		//!Surya Added - TEMPMAST Start
//		//IF $PoiFlag = 'N'
//		if(!poiFlag) {
//			indexNumber = new BigDecimal(0);
//			//MOVE 0 TO #indexNum
//		//END-IF
//		}
//		//!Surya Added - TEMPMAST End
//		//Begin-Select
//		//CHR36.H36NAM                                               !Rakesh***08/02/2004
//		//CHR36.H36EMP                                               !Rakesh***08/02/2004
//		//CHR36.H36EM#                                               !Rakesh***08/02/2004
//		//FROM HR036P CHR36 WHERE CHR36.H36EM# = #wrk_emplid AND CHR36.H36INX = #indexNum 
//		//UNION SELECT ' ', ' ' , 9999999999 FROM DUAL
//		//END-SELECT
//		HR036P hr036P = HR036P.findByEmployeeNumberAndIndexNumber(employeeNumber, indexNumber);
//    	if(hr036P != null) {
//			//!This IF statement and OR part of Select is a workaround to some problem in v8
//			//!(gateway and new version of SQR).  The Select was hanging if it couldn't find a
//			//! match in HR036P, so the Select assures that the Select always finds a match.
//			//LET #WRK_CHR36_H36EM_NUM = &CHR36.H36EM#   !sm 07/12/02  !Rakesh***08/02/2004
//			//IF #WRK_CHR36_H36EM_NUM = #wrk_emplid                    !Rakesh***08/02/2004
//			if(hr036P.getEmployeeNumber() == employeeNumber) {
//				//LET $LegEmpName = &CHR36.H36NAM                        !Rakesh***08/02/2004
//				legacyEmployeeName = hr036P.getEmployeeName();
//				//DO Format-Employee-Name
//		    	if(legacyEmployeeName != null && !legacyEmployeeName.isEmpty()) {
//		    		legacyEmployeeName = erd.StringUtil.formatLegacyEmployeeNameToPeopleSoftEmployeeName(hr036P.getEmployeeName());
//		    	}
//				//LET $LegEmplid = &CHR36.H36EMP                         !Rakesh***08/02/2004
//	    		legacyEmployeeId = hr036P.getEmployeeId();
//				//LET $LegEmplid = substr($LegEmplid,1,5)
//		    	if(legacyEmployeeId != null && !legacyEmployeeId.isEmpty()) {
//		    		legacyEmployeeId = legacyEmployeeId.substring(0, 5);
//		    	}
//				//!DO Insert-OprId  !Surya Added - TEMPMAST 
//				//!Surya Added - TEMPMAST Start
//				//IF #indexNum = 0
//		    	if(new BigDecimal(0).equals(indexNumber)) {
//					//DO INSERT-OPRID
//		    		CrossReferenceMultipleEmployeeId.ZHRI100A_insertOprId(employeeId, legacyEmployeeId);
//		    	}
//				//ELSE
//		    	else {
//					//DO UPDATE-OPRID
//		    		CrossReferenceMultipleEmployeeId.ZHRI100A_updateOprId(employeeId, legacyEmployeeId, indexNumber);
//				//END-IF  
//		    	}
//				//!Surya Added - TEMPMAST End
//			//END-IF  !#WRK_CHR36_H36EM_NUM = #wrk_emplid
//			}
//			return hr036P.getEmployeeId();
//    	}
//    	return null;
//		//END-PROCEDURE GET-LEGACY-OPRID
//	}

	/**
	 * Composes a command string to remotely execute on the AS400 server
	 * @param processName
	 * @param parameterString
	 * @return commandString to be used in regex call to AS400
	 */
	public static String composeAs400RexecCommandString(String processName, String parameterString) {
		System.out.println("*** composeAs400RexecCommandString() ***");
		String commandString = 
//						"CALL " + ServerProperties.getAs400Library() + "/" + processName + " " 
//						"CALL " + "EHRHRMS05#" + "/" + processName + " " 
						"CALL " + "" + "" + processName + " " 
						+ "PARM(" + parameterString + ")";
		return commandString;
	}

//	public static String composeAs400RexecCommandString(String processName, String parameterString) {
//		System.out.println("*** composeAs400RexecCommandString()");
//		//LET $Part1 = '"CALL ' || $Library ||'/HRZ202A '
//		//LET $Part2 = 'PARM(''' || $PSauditEmpl || ''' ''' || $PSOprid || ''' ''' || $PSTermDate || ''')" '
//		//LET $Command = $Part1||$Part2
//		//LET $Command = $RexecScript || ' ' || $Command || ' ' || $RMTSVR
//		String commandString = 
////				ServerProperties.getRemoteExecScript() + " " 
////						+  "\"CALL " + ServerProperties.getAs400Library() 
////						+ "/" + commonParameters.getProcessName() + " " 
////						+ "PARM(" + parameterString + ")\" "
////						+ ServerProperties.getRemoteServerName();
//						"CALL " + ServerProperties.getAs400Library() 
//						+ "/" + processName + " " 
//						+ "PARM(" + parameterString + ")";
////		System.out.println("$Command=> " + commandString);
//		return commandString;
//	}

	/**
	 * Composes a parameter string for the AS400 error program.
	 * Makes sure that the parameters are the correct format for the error routine RPG program to receive them
	 * @see Prepare-Error-Parms in ZHRI100A.SQR
	 * @param commonParameters
	 * @return errorParameterString
	 */
	public static String composeErrorParameterString(CommonParameters commonParameters) {
		System.out.println("*** composeErrorParameterString() ***");
		String blankSpaceParameter = " ";
		String criticalFlagYN = commonParameters.getCriticalFlag() != null && commonParameters.getCriticalFlag() ? "Y" : "N";
		Calendar now = Calendar.getInstance();
		//LET $AddDateErrorParm = DATETOSTR(STRTODATE($AsOfToday,'DD-MON-YYYY'),'YYYYMMDD')
		String errorDateParameter =
				String.format("%04d", now.get(Calendar.YEAR)) + String.format("%02d", now.get(Calendar.MONTH)) + String.format("%02d", now.get(Calendar.DATE));
		//LET $AddTimeErrorParm = substr($Out,10,2) || substr($Out,13,2) || substr($Out,16,2)
		String errorTimeParameter = 
				String.format("%02d", now.get(Calendar.HOUR_OF_DAY)) + String.format("%02d", now.get(Calendar.MINUTE)) + String.format("%02d", now.get(Calendar.SECOND));
		String yesOrNoParameter = "Y"; //TODO: What should this value really be called, and when is it not 'Y'??
		//!Make Sure that the ErrorMessageParm is always 75 Characters long
		String errorMessageParameter = String.format("%1$-75s", commonParameters.getErrorMessageParameter());
		//LET $OprIdErrorParm = Substr($AuditOprid,2,5)
		String operatorIdParameter = commonParameters.getOperatorId();
		if(operatorIdParameter != null) {
			operatorIdParameter = operatorIdParameter.toUpperCase();
			if(operatorIdParameter.startsWith("E")) {
				operatorIdParameter = operatorIdParameter.substring(1);
			}
		}
		String errorParameterString = "'" + commonParameters.getErrorProgramParameter() + "' "
					+ "'" + commonParameters.getEmployeeId() + "' '" + commonParameters.getEffectiveSequence() + "' "
					+ "'" + blankSpaceParameter + "' '" + errorMessageParameter + "' "
					+ "'" + criticalFlagYN + "' '" + errorDateParameter + "' "
					+ "'" + errorTimeParameter + "' '" + operatorIdParameter + "' "
					+ "'" + yesOrNoParameter + "'";
		return errorParameterString;
	}

//	/**
//	 * Call System Using $Command #Status Wait
//	 * Execute the command that was built on the command waiting until completion
//	 * @param command
//	 * @param commonParameters
//	 * @return 0 if success, non-zero if error
//	 */
//	//TODO: build it
//	public static Integer callSystemUsingCommand(String commandString, CommonParameters commonParameters) {
//		System.out.println("*** callSystemUsingCommand");
//		//SHOW '$Command=> ' $Command
////		System.out.println("****");
//		System.out.println("$Command=> " + commandString);
////		System.out.println(ServerProperties.print());
////		System.out.println(commonParameters.toString());
////		System.out.println("****");
//		//DO Get-Current-Datetime  !Gets the current date and time using curdttim.sqc
//		//SHOW 'Calling Command at: ' $SysDateTime  !Surya Added - TEMPMAST
//		//"20-JUN-2017_12:01:06.000000_AM"
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss.SSSSSS_a");
//		String currentDate = sdf.format(new Date()).toUpperCase();
//		System.out.println("Calling Command at: " + currentDate);
//		
//		String remoteServerUsername = ServerProperties.getRemoteServerUsername(); // username for remote host
//		String remoteServerPassword = ServerProperties.getRemoteServerPassword(); // password of the remote host
//		String remoteServerName = ServerProperties.getRemoteServerName(); // remote host address
//		Integer remoteServerPort = ServerProperties.getRemoteServerPort();
////	    List<String> result = new ArrayList<String>();
//	    try {
//	    	/*
//	    	 * Create a new Jsch (Java Secure Channel) object
//             * This object will execute shell commands or scripts on server
//             */
//	    	JSch javaSecureChannel = new JSch();
//	        /*
//	        * Open a new session, with username, host and port
//	        * Set the password and call connect.
//	        * session.connect() opens a new connection to remote SSH server.
//	        * Once the connection is established, you can initiate a new channel.
//	        * this channel is needed to connect to remotely execution program
//	        */
//	        Session session = javaSecureChannel.getSession(remoteServerUsername, remoteServerName, remoteServerPort);
//	        session.setConfig("StrictHostKeyChecking", "no");
//	        session.setPassword(remoteServerPassword);
//	        session.connect();
//	        //create the execution channel over the session
//	        ChannelExec channelExec = (ChannelExec)session.openChannel("exec");
//	        // Gets an InputStream for this channel. All data arriving in as messages from the remote side can be read from this stream.
//	        InputStream in = channelExec.getInputStream();
//	        // Set the command that you want to execute
//	        // In our case its the remote shell script
////	        channelExec.setCommand("sh "+ scriptFileName);
////	        channelExec.setCommand("ls -l");
//	        channelExec.setCommand(commandString);
//	        // Execute the command
//	        channelExec.connect();
//	        // Read the output from the input stream we set above
//	        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//	        String line;
//	        //Read each line from the buffered reader and add it to result list
//	        // You can also simple print the result here
//	        while ((line = reader.readLine()) != null) {
////	            result.add(line);
//	            System.out.println(line);
//	        }
//	        //retrieve the exit status of the remote command corresponding to this channel
//	        int exitStatus = channelExec.getExitStatus();
//	        //Safely disconnect channel and disconnect session. If not done then it may cause resource leak
//	        channelExec.disconnect();
//	        session.disconnect();
//	        if(exitStatus < 0) {
//	            System.out.println("Done, but exit status not set!");
//	        }
//	        else if(exitStatus > 0) {
//	            System.out.println("Done, but with error!");
//	        }
//	        else {
//	            System.out.println("Done!");
//	        }
//		}
//		catch(Exception e) {
//			System.err.println("Error: " + e);
//		}
//	    return 0;
//	}

	/**
	 * Executes a rexec command on the AS400.
	 * Call System Using $Command #Status Wait
	 * Execute the command that was built on the command waiting until completion //TODO
	 * @param commandString
	 * @return 0 if success, non-zero if error
	 */
	//TODO: build it
	public static Integer executeCommandRexec(String commandString) {
		System.out.println("*** executeRexec ***");
		//DO Get-Current-Datetime  !Gets the current date and time using curdttim.sqc
		//"20-JUN-2017_12:01:06.000000_AM"
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss.SSSSSS_a");
		String currentDate = sdf.format(new Date()).toUpperCase();
		System.out.println("$Command=> " + commandString);
		System.out.println("Calling Command at: " + currentDate);
		as400Rexec(commandString);
	    return 0;
	}

	/**
	 * Opens a rexec connection to the AS400 server and execute a command.
	 * @param commandString
	 * @return
	 */
	public static void as400Rexec(String commandString) {
		System.out.println("*** callSystemUsingCommand ***");
	    RExecClient client = new RExecClient();
//		String remoteServerHostName = ServerProperties.getRemoteServerHostName(); // remote host name
//		String remoteServerUsername = ServerProperties.getRemoteServerUsername(); // username for remote host
//		String remoteServerPassword = ServerProperties.getRemoteServerPassword(); // password of the remote host
		String remoteServerHostName = "dev.corp.erac.com";
		String remoteServerUsername = "PSHRINT";
		String remoteServerPassword = "SMRHET01";
		///QSYS.LIB/EHRHRMS06#.LIB/HRZ*.PGM

//	    commandString = "CALL EHRHRMS06#/HRZ102A PARM('840S4' '06' '20' '2017' '' '' '' 'V' 'O' '349NV' 'VOLUN  DISSATISFIED WHOURS         ')";
//	    commandString = "CALL EHRHRMS06#/HRZ100A PARM('HRZ102A' '859V1' '0' ' ' 'Error Message                                                              ' 'N' '20170529' '112649' '859V1' 'Y')";
		InputStream inputStream = null;
        try {
//          client.connect(remoteServerHostName, portNumber);
			client.connect(remoteServerHostName);
            if (!client.isConnected()) {
                System.err.println("The RLogin client is not connected to " + remoteServerHostName);
            }
            System.out.println("client.isConnected() = " + client.isConnected());
            try {
                client.rexec(remoteServerUsername, remoteServerPassword, commandString);
            }
            catch(IOException e) {
                e.printStackTrace();
                System.err.println("Could not execute command.");
            }
            catch (Exception e) {
    			e.printStackTrace();
    		}
            readWrite(client.getInputStream(), client.getOutputStream(), System.in, System.out);
        } 
        catch (SocketException e1) {
			e1.printStackTrace();
		} 
        catch (IOException e1) {
			e1.printStackTrace();
		}
        catch (Exception e1) {
			e1.printStackTrace();
		}
        finally {
            IOUtils.closeQuietly(inputStream);
            try {
				client.disconnect();
			} 
            catch (IOException e) {
				e.printStackTrace();
			}
            catch (Exception e) {
    			e.printStackTrace();
    		}
        }
	}
	
	/**
	 * Copies from a remote input stream and output stream to a local input stream and output stream.
	 * @param remoteInput
	 * @param remoteOutput
	 * @param localInput
	 * @param localOutput
	 */
	public static final void readWrite(InputStream remoteInput, OutputStream remoteOutput, InputStream localInput, OutputStream localOutput) {
			Thread readerThread, writerThread;
	        readerThread = new Thread() {
	        	public void run() {
	        		int byteCharacter;
	        		try {
	        			while (!interrupted() && (byteCharacter = localInput.read()) != -1) {
	        				remoteOutput.write(byteCharacter);
	        				remoteOutput.flush();
	        			}
	        		}
	        		catch (IOException e) {
	        			e.printStackTrace();
	        		}
	                catch (Exception e) {
	        			e.printStackTrace();
	        		}
	        	}
	        };
	        writerThread = new Thread() {
	        	public void run() {
	        		try {
	        			org.apache.commons.net.io.Util.copyStream(remoteInput, localOutput);
	        		}
	        		catch (IOException e) {
	        			e.printStackTrace();
	        			System.exit(1);
	        		}
	                catch (Exception e) {
	        			e.printStackTrace();
	        		}
	        	}
	        };
	        writerThread.setPriority(Thread.currentThread().getPriority() + 1);
	        writerThread.start();
	        readerThread.setDaemon(true);
	        readerThread.start();
	        try {
	        	writerThread.join();
	        	readerThread.interrupt();
	        }
	        catch (InterruptedException e) {
				e.printStackTrace();
	        }
            catch (Exception e) {
    			e.printStackTrace();
    		}
	}
}

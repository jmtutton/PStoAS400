package erd.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.SocketException;
import java.text.SimpleDateFormat;
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

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.IOException;
import org.apache.commons.net.bsd.RExecClient;
import org.apache.commons.io.IOUtils;


public class ZHRI100A {

	public static void main() {
		System.out.println("********** ZHRI100A.main");
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
	 * Process-Main from ZHRI100A,SQR
	 * This is the process controlling procedure.
	 * @throws InterruptedException 
	 */
	public static void processMain() throws InterruptedException {
		System.out.println("********** ZHRI100A.processMain");
		//Begin-Procedure Process-Main
		//ZHRI100A.Get-Variable
		//LET $WrkCriticalFlag = 'N'
//		commonParameters.setCriticalFlag(false);
		//LET #run_flag = 1
		Boolean runFlag = true;
		//while #run_flag = 1        !Never ending loop
		while(runFlag == true) {
			//ZHRI100A.Check-Interface-Runfile
			runFlag = ZHRI100A_checkInterfaceRunFile(ServerProperties.getOracleSystemId());
			//ZHRI100A.Get-Trigger-Data       !Process the interface requests
			ZHRI100A_getTriggerData();
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
//		String commandString = "mv" + " " + "/usr/local/barch/" + ServerProperties.getOracleSystemId() + "/work/hrinterface.stop" + " " 
//					+ "/usr/local/barch/" + ServerProperties.getOracleSystemId() + "/work/hrinterface.run";
		//Show  + $Command in Process-Main: ' $Command
//		System.out.println("$Command in Process-Main: " + commandString);
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
	 * @param commonParameters
	 * @return
	 */
	public static String ZHRI100A_callPrograms(PszTriggerSuperclass trigger) {
		System.out.println("********** ZHRI100A_callPrograms");
//		String commandString = "";
		CommonParameters commonParameters = new ProcessParameters().new CommonParameters();
		commonParameters.setProcessName(trigger.getProcessName());
		commonParameters.setEmployeeId(trigger.getEmployeeId());
		commonParameters.setOperatorId(trigger.getOperatorId());
		commonParameters.setEffectiveDate(trigger.getEffectiveDate());
		commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
		commonParameters.setCompletionStatus(trigger.getCompletionStatus());
		//BEGIN-PROCEDURE CALL-PROGRAMS
		//DO Initialize-AD-WrkFields
		//LET $TrigTaskFlag = ''
		
		switch(trigger.getProcessName()) {
		case "ZHRI101A": //Process to hire employee
			commonParameters.setPoiFlag(false);
			//WHEN = 'ZHRI101A'
			//!Move fields to be used in the called SQC
			//LET $Wrk_Oprid = $AuditOprid
//			commonParameters.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid
//			commonParameters.setEmployeeId(trigger.getEmployeeId());
			//LET $Wrk_Effdt = $PSEffdt
//			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
			//move #PSEffseq to #Wrk_Effseq
//			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
			//LET $Wrk_Process_Name = $WrkProcess
//			commonParameters.setProcessName(trigger.getProcessName());
			//LET $TrigTaskFlag = $WrkTaskFlag      !Surya Added - TEMPMAST
//			commonParameters.setCompletionStatus(trigger.getCompletionStatus());
			//LET $Wrk_Inf_ = ' ' //this value is not used
			//LET $ADAction_Code = 'H'
//			commonParameters.setActionCode("H");
			//LET $ADLegOprid = ''
//			commonParameters.setLegacyOperatorId("");
			//DO HR01-Process-Main    !ZHRI101A.SQC
			EmployeeNewHire employeeNewHire = new EmployeeNewHire();
			commonParameters.setCompletionStatus(employeeNewHire.HR01_processMain((PszTriggerEmployee) trigger, commonParameters));
			//BREAK
			break;
		case "ZHRI102A": //Process to terminate an employee
			commonParameters.setPoiFlag(false);
			//WHEN = 'ZHRI102A'
			//!Move fields to be used in the called SQC
			//MOVE #Wrk_Sequence to #WrkSeqNbr
//			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
			//LET $PSAuditOperId = $AuditOprId
//			commonParameters.setOperatorId(trigger.getOperatorId());
	        //LET $PSDateIn = $PSEffDt
//			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
	        //LET $Wrk_Emplid = $PSEmplId
//			commonParameters.setEmployeeId(trigger.getEmployeeId());
	        //LET $ADAction_Code = 'T'
//			commonParameters.setActionCode("T");
	        //LET $ADLegOprid = ''
//			commonParameters.setLegacyOperatorId("");
	        //DO HR02-Process-Main    !ZHRI102A.SQC
			EmployeeTermination employeeTermination = new EmployeeTermination();
			commonParameters.setCompletionStatus(employeeTermination.HR02_processMain((PszTriggerEmployee) trigger, commonParameters));
			//BREAK
			break;
		case "ZHRI104A": //Process for job status change
			commonParameters.setPoiFlag(false);
			//WHEN = 'ZHRI104A'
			//!Move fields to be used in the called SQC
			//LET $PSUserOprid = $AuditOprid
//			commonParameters.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
//			commonParameters.setEmployeeId(trigger.getEmployeeId());
			//Move #PSEffseq to #WrkEffseq
//			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
			//LET $ADAction_Code = 'C'
//			commonParameters.setActionCode("C");
			//LET $ADLegOprid = ''
//			commonParameters.setLegacyOperatorId("");
			//DO HR04-Process-Main    !ZHRI104A.SQC
			EmployeeJobProfileChange employeeJobProfileChange = new EmployeeJobProfileChange();
			commonParameters.setCompletionStatus(employeeJobProfileChange.HR04_processMain((PszTriggerEmployee) trigger, commonParameters));
			//BREAK
			break;
		case "ZHRI105A": //Process for demographics change
			commonParameters.setPoiFlag(false);
			//WHEN = 'ZHRI105A'
			//!Move fields to be used in the called SQC
			//LET $PSemp = $AuditOprid
//			commonParameters.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
//			commonParameters.setEmployeeId(trigger.getEmployeeId());
			//LET $ADAction_Code = 'C'
//			commonParameters.setActionCode("C");
			//LET $ADLegOprid = ''
//			commonParameters.setLegacyOperatorId("");
			//LET $Wrk_ADCountryCdBuild = 'Y'                 !sree-UAAMOD
			//DO HR05-Process-Main    !ZHRI105A.SQC
			EmployeeDemographicChange employeeDemographicChange = new EmployeeDemographicChange();
			commonParameters.setCompletionStatus(employeeDemographicChange.HR05_processMain((PszTriggerEmployee) trigger, commonParameters));
			//BREAK
			break;
		case "ZHRI106A": 
			commonParameters.setPoiFlag(false);
			//WHEN = 'ZHRI106A'
			//!Move fields to be used in the called SQC
			//LET $Wrk_Oprid = $AuditOprid
//			commonParameters.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid
//			commonParameters.setEmployeeId(trigger.getEmployeeId());
			//LET $Wrk_Effdt = $PSEffdt
//			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
			//move #PSEffseq to #Wrk_Effseq
//			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
			//LET $Wrk_Process_Name = $WrkProcess
//			commonParameters.setProcessName(trigger.getProcessName());
			//LET $ADAction_Code = 'R'
//			commonParameters.setActionCode("R");
			//DO HR01-Process-Main       !ZHRI101A.SQC
			//BREAK
			break;
		case "ZHRI107A": //Process for converting dates
			commonParameters.setPoiFlag(false);
			//WHEN = 'ZHRI107A'
			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
//			commonParameters.setEmployeeId(trigger.getEmployeeId());
			//LET $ADAction_Code = ''
//			commonParameters.setActionCode("");
			//LET $ADLegOprid = ''
//			commonParameters.setLegacyOperatorId("");
			//DO HR07-Process-Main
			EmployeeDateChange employeeDateChange = new EmployeeDateChange();
			commonParameters.setCompletionStatus(employeeDateChange.HR07_processMain((PszTriggerEmployee) trigger, commonParameters));
			//BREAK
			break;
		case "ZHRI109A": //Process for group transfer
			commonParameters.setPoiFlag(false);
			//WHEN = 'ZHRI109A'
			//!Move fields to be used in the called SQC
			//LET $PSUserOprid = $AuditOprid
//			commonParameters.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
//			commonParameters.setEmployeeId(trigger.getEmployeeId());
			//Move #PSEffseq to #WrkEffseq
//			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
			//LET $ADAction_Code = 'C'
//			commonParameters.setActionCode("C");
			//LET $ADLegOprid = ''
//			commonParameters.setLegacyOperatorId("");
			//DO HR09-Process-Main        !ZHRI100A.SQC
			EmployeeGroupTransfer employeeGroupTransfer = new EmployeeGroupTransfer();
			commonParameters.setCompletionStatus(employeeGroupTransfer.HR09_processMain((PszTriggerEmployee) trigger, commonParameters));
			//BREAK
			break;
		case "ZHRI101D": //Row deleted on hire
			//WHEN = 'ZHRI101D'     !Row deleted on hire
			//LET $ErrorProgramParm = 'HRZ101A'
			commonParameters.setErrorProgramParameter("HRZ101A");
			//LET $ErrorMessageParm = 'A row was deleted on the hire process'
			commonParameters.setErrorMessageParameter("A row was deleted on the hire process");
			//LET $WrkCriticalFlag = 'Y'
			commonParameters.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(commonParameters);
			//DO Call-Error-Routine
			ZHRI100A_callErrorRoutine(commonParameters);
			//LET $WrkCriticalFlag = 'N'
			commonParameters.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
//			commonParameters.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
			break;
		case "ZHRI102D": //Row deleted on term
			//WHEN = 'ZHRI102D'     !Row deleted on term
			//LET $ErrorProgramParm = 'HRZ102A'
			commonParameters.setErrorProgramParameter("HRZ102A");
			//LET $ErrorMessageParm = 'A row was deleted on the termination process'
			commonParameters.setErrorMessageParameter("A row was deleted on the termination process");
			//LET $WrkCriticalFlag = 'Y'
			commonParameters.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(commonParameters);
			//DO Call-Error-Routine
			ZHRI100A_callErrorRoutine(commonParameters);
			//LET $WrkCriticalFlag = 'N'
			commonParameters.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			commonParameters.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
			break;
		case "ZHRI104D": //Row deleted on job status change
			//WHEN = 'ZHRI104D'     !Row deleted on job status change
			//LET $ErrorProgramParm = 'HRZ104A'
			commonParameters.setErrorProgramParameter("HRZ104A");
			//LET $ErrorMessageParm = 'A row was deleted on the job profile process'
			commonParameters.setErrorMessageParameter("A row was deleted on the job profile process");
			//LET $WrkCriticalFlag = 'Y'
			commonParameters.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(commonParameters);
			//DO Call-Error-Routine
			ZHRI100A_callErrorRoutine(commonParameters);
			//LET $WrkCriticalFlag = 'N'
			commonParameters.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			commonParameters.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
			break;
		case "ZHRI105D": //Row deleted on demographics change
			//WHEN = 'ZHRI105D'     !Row deleted on demographics change
			//LET $ErrorProgramParm = 'HRZ105A'
			commonParameters.setErrorProgramParameter("HRZ105A");
			//LET $ErrorMessageParm = 'A row was deleted on the demographics process'
			commonParameters.setErrorMessageParameter("A row was deleted on the demographics process");
			//LET $WrkCriticalFlag = 'Y'
			commonParameters.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(commonParameters);
			//DO Call-Error-Routine
			ZHRI100A_callErrorRoutine(commonParameters);
			//LET $WrkCriticalFlag = 'N'
			commonParameters.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			commonParameters.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
			break;
		case "ZHRI106D": //Row deleted on rehire
			//WHEN = 'ZHRI106D'     !Row deleted on rehire
			//LET $ErrorProgramParm = 'HRZ101A'
			commonParameters.setErrorProgramParameter("HRZ101A");
			//LET $ErrorMessageParm = 'A row was deleted on the re-hire process'
			commonParameters.setErrorMessageParameter("A row was deleted on the re-hire process");
			//LET $WrkCriticalFlag = 'Y'
			commonParameters.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(commonParameters);
			//DO Call-Error-Routine
			ZHRI100A_callErrorRoutine(commonParameters);
			//LET $WrkCriticalFlag = 'N'
			commonParameters.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			commonParameters.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
			break;
		case "ZHRI107D": //Row deleted on the dates process
			//WHEN = 'ZHRI107D'     !Row deleted on the dates process
			//LET $ErrorProgramParm = 'HRZ107A'
			commonParameters.setErrorProgramParameter("HRZ107A");
			//LET $ErrorMessageParm = 'A row was deleted on the dates process'
			commonParameters.setErrorMessageParameter("A row was deleted on the dates process");
			//LET $WrkCriticalFlag = 'Y'
			commonParameters.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(commonParameters);
			//DO Call-Error-Routine
			ZHRI100A_callErrorRoutine(commonParameters);
			//LET $WrkCriticalFlag = 'N'
			commonParameters.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			commonParameters.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
			break;
		case "ZHRI109D": //Row deleted on the group transfer process
			//WHEN = 'ZHRI109D'
			//LET $ErrorProgramParm = 'HRZ109A'
			commonParameters.setErrorProgramParameter("HRZ109A");
			//LET $ErrorMessageParm = 'A row was deleted on the group transfer process'
			commonParameters.setErrorMessageParameter("A row was deleted on the group transfer process");
			//LET $WrkCriticalFlag = 'Y'
			commonParameters.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A_prepareErrorParms(commonParameters);
			//DO Call-Error-Routine
			commonParameters.setProcessName(trigger.getProcessName());
			ZHRI100A_callErrorRoutine(commonParameters);
			//LET $WrkCriticalFlag = 'N'
			commonParameters.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			commonParameters.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			PszTriggerEmployee.setCompletionStatusBySequenceNumber("C", trigger.getSequenceNumber());
			break;
		case "ZHRI201A": //Non-person new hire
			commonParameters.setPoiFlag(true);
			//WHEN = 'ZHRI201A'
			//!Move fields to be used in the called SQC
			//LET $Wrk_Oprid = $NAuditOprid
//			commonParameters.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $NPSEmplid
//			commonParameters.setEmployeeId(trigger.getEmployeeId());
			//LET $Wrk_Effdt = $NPSEffdt
//			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
			//MOVE #NPSEffseq to #Wrk_Effseq
//			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
			//LET $Wrk_indexNum = to_char(#indexNum)
			//LET $Wrk_Process_Name = $NWrkProcess
//			commonParameters.setProcessName(trigger.getProcessName());
			//DO HR201-Process-Main    !ZHRI201A.SQC
			NonPersonNewHire nonPersonNewHire = new NonPersonNewHire();
			commonParameters.setCompletionStatus(nonPersonNewHire.HR201_processMain((PszTriggerNonPerson) trigger, commonParameters));
			//BREAK
			break;
		case "ZHRI202A": //Non-person termination
			commonParameters.setPoiFlag(true);
			//WHEN = 'ZHRI202A'
			//!Move fields to be used in the called SQC
			//LET $PSAuditOperId = $NAuditOprid
//			commonParameters.setAuditOperatorId(trigger.getOperatorId());
//			commonParameters.setOperatorId(trigger.getOperatorId());
			//LET $PSDateIn = $NPSEffdt
//			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
			//LET $Wrk_Emplid = $NPSEmplid 
//			commonParameters.setEmployeeId(trigger.getEmployeeId());
			//LET $Wrk_indexNum = to_char(#indexNum)                   
//			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
			//DO HR202-Process-Main    !ZHRI202A.SQC
			NonPersonTermination nonPersonTermination = new NonPersonTermination();
			commonParameters.setCompletionStatus(nonPersonTermination.HR202_processMain((PszTriggerNonPerson) trigger, commonParameters));
			//BREAK
			break;
		case "ZHRI205A":
			commonParameters.setPoiFlag(true);
			//WHEN = 'ZHRI205A'
			//!Move fields to be used in the called SQC
			//LET $PSAuditemp = $NAuditOprId
//			commonParameters.setAuditOperatorId(trigger.getOperatorId());
//			commonParameters.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $NPSEmplid                              
//			commonParameters.setEmployeeId(trigger.getEmployeeId());
			//LET $Wrk_indexNum = to_char(#indexNum)
//			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
			//LET $PSEffdt =  $NPSEffdt     
//			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
			//DO HR205-Process-Main    !ZHRI105A.SQC
			NonPersonDemographicChange nonPersonDemographicChange = new NonPersonDemographicChange();
			commonParameters.setCompletionStatus(nonPersonDemographicChange.HR205_processMain((PszTriggerNonPerson) trigger, commonParameters));
			//BREAK
			break;
		case "ZHRI206A": //Non-person 
			commonParameters.setPoiFlag(true);
			//WHEN = 'ZHRI206A'
			//!Move fields to be used in the called SQC
			//LET $Wrk_Oprid = $NAuditOprid
//			commonParameters.setOperatorId(trigger.getOperatorId());			
			//LET $Wrk_Emplid = $NPSEmplid
//			commonParameters.setEmployeeId(trigger.getEmployeeId());
			//LET $Wrk_Effdt = $NPSEffdt
//			commonParameters.setEffectiveDate(trigger.getEffectiveDate());
			//MOVE #NPSEffseq to #Wrk_Effseq
//			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
			//LET $Wrk_indexNum = to_char(#indexNum)
//			commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
			//LET $Wrk_Process_Name = $NWrkProcess
//			commonParameters.setProcessName(trigger.getProcessName());
			//DO HR201-Process-Main       !ZHRI201A.SQC
			NonPersonNewHire nonPersonNewHire2 = new NonPersonNewHire();
			commonParameters.setCompletionStatus(nonPersonNewHire2.HR201_processMain((PszTriggerNonPerson) trigger, commonParameters));
			//BREAK
			break;
		default: //ERROR
			//WHEN-OTHER
			//LET $CompletionStatus = 'E'
			//!update to an E to prevent looping and to mark the record in error
			commonParameters.setCompletionStatus("E");
			if(trigger instanceof PszTriggerEmployee) {
				//DO UPDATE-TRIGGER-ROW
				PszTriggerEmployee.setCompletionStatusBySequenceNumber("E", trigger.getSequenceNumber());
			}
			else {
				//DO UPDATE-TRIGGER-ROW-NONEMP  !Surya Added - TEMPMAST 
				PszTriggerNonPerson.setCompletionStatusBySequenceNumber("E", trigger.getSequenceNumber());
			}
			//BREAK
			break;
		//END-EVALUATE
		}
//		System.out.println("Command => " + commandString);
		return commonParameters.getCompletionStatus();
		//END-PROCEDURE CALL-PROGRAMS
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
	 * @param commonParameters
	 * @return 0 if success, non-zero if error
	 */
	public static Integer ZHRI100A_callSystem(String commandString, CommonParameters commonParameters) {
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
//		command = commonParameters.getRemoteExecScript() + " " +  command + " " + commonParameters.getRemoteServerName();
		//Call System Using $Command #Status Wait  !Execute the command that was built on the command waiting until completion
		status = callSystemUsingCommand(commandString, commonParameters);
		//IF #status != 0
		if(status != 0) {
			//!error
			//LET $ErrorProgramParm = 'ZHRI100A'
			commonParameters.setErrorProgramParameter("ZHRI100A");
			//LET $ErrorMessageParm = ' '
			//LET $ErrorMessageParm = 'Error executing Call System command, contact HR-PeopleSoft On-Call'
			commonParameters.setErrorMessageParameter("Error executing Call System command, contact HR-PeopleSoft On-Call");
			//LET $WrkCriticalFlag = 'Y'
			commonParameters.setCriticalFlag(true);
			//DO Prepare-Error-Parms
			ZHRI100A_prepareErrorParms(commonParameters);
			//IF $PoiFlag = 'N'
			if(!commonParameters.getPoiFlag()) {
				//DO Call-Error-Routine
				//TODO: 
				commonParameters.setProcessName("HRZ100A");
				commandString = ZHRI100A_callErrorRoutine(commonParameters);
			//ELSE
			}
			else {
				//DO Call-Error-Routine-NonEmp
				//TODO: 
				commonParameters.setProcessName("HRZ100A");
				commandString = ZHRI100A_callErrorRoutineNonEmp(commonParameters);
			//END-IF
			}
			//LET $WrkCriticalFlag  = 'N'
			commonParameters.setCriticalFlag(false);
		}
		//END-IF
		return status;
		//END-PROCEDURE CALL-SYSTEM
	}

	/**
	 * Prepare-Error-Parms from ZHRI100A.SQR
	 * Makes sure that the parms are the correct length for the error routine RPG program to recieve them
	 * @param commonParameters
	 */
	private static void ZHRI100A_prepareErrorParms(CommonParameters commonParameters) {
		System.out.println("********** ZHRI100A_prepareErrorParms");
		//BEGIN-PROCEDURE PREPARE-ERROR-PARMS
		//!Prepare the date and time parms
		//DO Get-Current-DateTime                                 !Get the current date and time
		Calendar now = Calendar.getInstance();
		//LET $AddDateErrorParm = DATETOSTR(STRTODATE($AsOfToday,'DD-MON-YYYY'),'YYYYMMDD') !sree**rehost
		commonParameters.setErrorDateParameter(now.get(Calendar.MONTH) + "/" + now.get(Calendar.DATE) + "/" + now.get(Calendar.YEAR));
		//LET $AddTimeErrorParm =    substr($Out,10,2)    ||    substr($Out,13,2)    ||    substr($Out,16,2)
		commonParameters.setErrorTimeParameter(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND));
		//LET $OprIdErrorParm   =    Substr($AuditOprid,2,5)
		//END-PROCEDURE PREPARE-ERROR-PARMS
	}

	/**
	 * Call-Error-Routine-NonEmp from ZHRI100A.SQR
	 * Builds the command and calls the error routine
	 * @param processName
	 * @param commonParameters
	 * @return command text
	 */
	private static String ZHRI100A_callErrorRoutineNonEmp(CommonParameters commonParameters) {
		System.out.println("********** ZHRI100A_callErrorRoutineNonEmp()");
		String commandString = composeCommandString(commonParameters, composeErrorParameterString(commonParameters));
//		System.out.println(commandString);
		return commandString;
	}

	/**
	 * Call-Error-Routine from ZHRI100A.SQR
	 * @param processName
	 * @param commonParameters
	 * @return command text
	 */
	public static String ZHRI100A_callErrorRoutine(CommonParameters commonParameters) {
		System.out.println("********** ZHRI100A_callErrorRoutine()");
		String commandString = composeCommandString(commonParameters, composeErrorParameterString(commonParameters));
//		System.out.println(commandString);
		ZHRI100A_callSystem(commandString, commonParameters);
		return commandString;
//		List<String> parameterList = new ArrayList<String>();
//		HashMap<String, String> parameterMap = new HashMap<String, String>();
//		String blankSpaceParameter = " ";
//		String criticalFlag = commonParameters.getCriticalFlag() != null && commonParameters.getCriticalFlag() ? "Y" : "N";
//		Calendar now = Calendar.getInstance();
//		String errorDateParameter =  now.get(Calendar.MONTH) + "/" + now.get(Calendar.DATE) + "/" + now.get(Calendar.YEAR);
//		String errorTimeParameter = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);
//		String yesOrNoParameter = "Y"; //TODO: What should this value really be called
//		//!Make Sure that the ErrorMessageParm is always 75 Characters long
//		String errorMessageParameter = String.format("%1$-75s", commonParameters.getErrorMessageParameter());
//		String command = "\"CALL " + commonParameters.getAs400Library() + "/" + processName + " "
//					+ "PARM("
//					+ "'" + commonParameters.getErrorProgramParameter() + "' "
//					+ "'" + commonParameters.getEmployeeId() + "' "
//					+ "'" + blankSpaceParameter + "' "
//					+ "'" + errorMessageParameter + "' "
//					+ "'" + criticalFlag + "' "
//					+ "'" + errorDateParameter + "' "
//					+ "'" + errorTimeParameter + "' "
//					+ "'" + commonParameters.getOperatorId() + "' "
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
//		parameterMap.put("errorProgramParameter", commonParameters.getErrorProgramParameter());
//		parameterMap.put("employeeId", commonParameters.getEmployeeId());
//		parameterMap.put("blankSpaceParameter", blankSpaceParameter);
//		parameterMap.put("errorMessageParameter", errorMessageParameter);
//		parameterMap.put("criticalFlag", criticalFlag);
//		parameterMap.put("errorDateParameter", errorDateParameter);
//		parameterMap.put("errorTimeParameter", errorTimeParameter);
//		parameterMap.put("opridErrorParameter", commonParameters.getOperatorId());
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
		System.out.println("********** ZHRI100A_buildGroupWhereClause()");
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
	 * @param commonParameters
	 * @return legacyEmployeeId
	 */
	public static String ZHRI100A_getOprId(CommonParameters commonParameters) {
		return ZHRI100A_getOprId(commonParameters,  null); 
	}
	public static String ZHRI100A_getOprId(CommonParameters commonParameters, BigDecimal eidIndexNumber) {
		System.out.println("********** ZHRI100A_getOprId()");
		BigDecimal indexNumber = commonParameters.getEffectiveSequence();
		//BEGIN-PROCEDURE GET-OPRID
		//LET $Found = 'N'
		Boolean found = false;
		//LET $PSOprId = ''
		String employeeId;
		//IF $PoiFlag = 'N'
		if(!commonParameters.getPoiFlag()) {
			//MOVE 0 TO #indexNum
			indexNumber = new BigDecimal(0);
		//END-IF
		}
		//IF #indexNum = 0
		if(indexNumber.equals(0)) {
//			DO GET-LEGID-FOR-SEQ0
			employeeId = CrossReferenceEmployeeId.ZHRI100A_getLegIdForSeq0(commonParameters.getEmployeeId());
		}
		//ELSE
		else {
			//DO GET-LEGID-FOR-SEQNUM
			employeeId = CrossReferenceMultipleEmployeeId.ZHRI100A_getLegIdForSeqNum(commonParameters.getEmployeeId(), eidIndexNumber);
		//END-IF
		}
		if(employeeId != null && !employeeId.isEmpty()) {
			found = true;
		}
		//!If an OprId does not exist for the employee, create one
		//IF ($Found = 'N')
		if(!found) {
			//DO GET-LEGACY-OPRID
			//LET $PSOprId = $LegEmplid
			employeeId = ZHRI100A_getLegacyOprId(commonParameters.getEmployeeId(), indexNumber, commonParameters.getPoiFlag());
		//END-IF  !$Found = 'N'
		}
		//END-PROCEDURE GET-OPRID
		return employeeId;
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
	//! Procedure:  Initialize-AD-WrkFields
	//!----------------------------------------------------------------------
//	/**
//	 * 
//	 * @return
//	 */
//	public static CommonParameters initializeCommonParameters() {
//		System.out.println("********** initializeCommonParameters()");
//		CommonParameters commonParameters = new ProcessParameters().new CommonParameters();
//		return commonParameters;
//	}

	/**
	 * 
	 * @return
	 */
	public static void initializeServerProperties() {
		System.out.println("********** initializeServerProperties()");
		String processName = "ZHRI100A";
		ServerProperties.setDbName(PsDbOwner.findDbName());
		//LET $PS_HOME = getenv('PS_HOME')  !This gets the oracle_sid
//		commonParameters.setPeopleSoftHomePath(System.getenv("PS_HOME")); //TODO: ********
		ServerProperties.setPeopleSoftHomePath("C:/people soft/");
		//LET $AD_HOME = $PS_HOME || '/data/activedir/'  !Path for Active Directory
//		commonParameters.setActiveDirectoryHomePath(commonParameters.peopleSoftHomePath + "/data/activedir/"); //TODO: where is this used???
		//LET $ORACLE_SID = getenv('ORACLE_SID') 
//		commonParameters.setOracleSystemId(System.getenv("ORACLE_SID")); //TODO: ********
		ServerProperties.setOracleSystemId("PS90HRQA");
		if(ServerProperties.getOracleSystemId() != null) {
			//UPPERCASE $ORACLE_SID                  
			ServerProperties.setOracleSystemId(ServerProperties.getOracleSystemId().toUpperCase());
		}
		//!Returns name of AS/400 machine for use in zbas002b.sh
		//LET $Variable_Needed = ' '            
		//LET $Variable_Needed = 'RMTSVR'       
		//DO  Get-Variable                      
		//LET $RMTSVR = $PSZPTT_VARIABLE_VAL      
		//Show '$RMTSVR: ' $RMTSVR                                                            
		ServerProperties.setRemoteServerName(PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, ServerProperties.getDbName(), "RMTSVR")); //TODO: where is this used???
		//LET $RexecScript = '/usr/local/barch/' || $ORACLE_SID || '/scripts/zbas002b.sh'     
		//Show '$RexecScript: ' $RexecScript                                                  
		ServerProperties.setRemoteExecScript("/usr/local/barch/" + ServerProperties.getOracleSystemId() + "/scripts/zbas002b.sh"); //TODO: where is this used???
		//!Returns library name on AS/400 where programs reside
		//LET $Variable_Needed = ' '            
		//LET $Variable_Needed = 'AS400library' 
		//DO  Get-Variable                      
		//LET $Library = $PSZPTT_VARIABLE_VAL   
		//Show '$Library: ' $Library                                                          
		ServerProperties.setAs400Library(PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, ServerProperties.getDbName(), "AS400library"));
		//!Returns IP address of NT server
		//LET $Variable_Needed = ' '             
		//LET $Variable_Needed = 'RMTNTADSVR'    
		//DO Get-Variable                        
		//LET $RMTNTADSVR = $PSZPTT_VARIABLE_VAL 
		//Show '$RMTNTADSVR: ' $RMTNTADSVR                                                    
		ServerProperties.setRemoteAdServerName(PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, ServerProperties.getDbName(), "RMTNTADSVR")); //TODO: where is this used???
	}

	/**
	 * ZHRI100A.Check-Interface-Runfile
	 * This procedure will check the existence run file
	 * @param oracleSystemId
	 * @return true if run file does not exist
	 */
	public static Boolean ZHRI100A_checkInterfaceRunFile(String oracleSystemId) {
		System.out.println("********** ZHRI100A_checkInterfaceRunFile()");
		Boolean runFlag = false;
//		String runFilePath;
		//LET $RUN_FILEPATH = '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'  //concatenate
//		runFilePath = "/usr/local/barch/" + oracleSystemId + "/work/hrinterface.run";
		//LET #file_exists = exists($RUN_FILEPATH)
//		Boolean fileExists = (new File(runFilePath)).exists();
		Boolean fileExists = true;  //TODO: 
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
	 * @param commonParameters
	 */
	//TODO
	//TODO
	//TODO
	public static void ZHRI100A_getTriggerData() {
		System.out.println("********** ZHRI100A_getTriggerData()");
		//asOfToday
		//sysDate
		//fileOpen
//		PszTriggerEmployee trigger = PszTriggerEmployee.createMockTriggerForEmployeeTermination();
		List<PszTriggerEmployee> triggerList = PszTriggerEmployee.findByCompletionStatusAndProcessName("P", "ZHRI102A");
//		System.out.println("triggerList == null: " + (triggerList == null));
//		System.out.println("triggerList.isEmpty(): " + triggerList.isEmpty());
		PszTriggerEmployee trigger = triggerList != null && !triggerList.isEmpty() ? triggerList.get(0) : PszTriggerEmployee.createMockTriggerForEmployeeTermination();
		System.out.println("************************************************** trigger: \n" + trigger.toString());
//		Boolean adFound = false; //$AdFound  //TODO: where should this be set???
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
		//RZ.SEQ_NBR
		//MOVE &RZ.SEQ_NBR TO #WrkSequence
		//RZ.OPRID
		//LET $AuditOprid = LTRIM(RTRIM(&RZ.OPRID,' '),' ')
		//RZ.EMPLID
		//LET $PSEmplid = LTRIM(RTRIM(&RZ.EMPLID,' '),' ')
		//MOVE $PSEmplid TO #Wrk_EmplID1
		//LET $Wrk_Emplid2 =  EDIT(#Wrk_EmplID1,'099999999')
		//TO_CHAR(RZ.EFFDT, 'YYYY-MM-DD') &RZEFFDT
		//LET $PSEffdt = &RZEFFDT
		//RZ.EFFSEQ
		//MOVE &RZ.EFFSEQ TO #PSEffSeq
		//RZ.PROC_NAME
		//LET $WrkProcess = LTRIM(RTRIM(&RZ.PROC_NAME,' '),' ')           !Remove leading and trailing blanks
		//RZ.TASK_FLAG                                                        !Surya Added - TEMPMAST 
		//LET $WrkTaskFlag = LTRIM(RTRIM(&RZ.TASK_FLAG,' '),' ')          !Surya Added - TEMPMAST 
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
//		commonParameters.setPoiFlag(false);
		//DO Check-If-Contractor
		Boolean isContractor = PsJob.ZHRI100A_checkIfContractor(trigger.getEmployeeId());
		//IF $Found = 'N' AND  $PSEmplid <> ''  
		//!Not a contractor and not a blank EmplId   !ZHR_MOD_ZHRI100A_110A
		if(!isContractor && trigger.getEmployeeId() != null && !trigger.getEmployeeId().isEmpty()) {
			//Added a check for 'ZHRI102A' - to see if corresponding row on JOB 
			//IF $WrkProcess = 'ZHRI102A'
			if("ZHRI102A".equalsIgnoreCase(trigger.getProcessName())) {
				//DO Check-If-Correct102A
				Boolean isOkToProcess = PsJob.ZHRI100A_checkIfCorrect102A(trigger.getEmployeeId(), trigger.getEffectiveDate(), trigger.getProcessName());
//				System.out.println("************** isOkToProcess: " + isOkToProcess);
				//IF $OK-to-process = 'Y'
				if(isOkToProcess) {
					//DO Call-Programs
					completionStatus = ZHRI100A_callPrograms(trigger);
				}
				//ELSE                                                           
				else {
					//LET $CompletionStatus = 'C'                                 
					completionStatus = "C";
					//DO UPDATE-TRIGGER-ROW                                       
					int numberOfRecordsUpdated = PszTriggerEmployee.setCompletionStatusBySequenceNumber(completionStatus, trigger.getSequenceNumber());
					System.out.println("isOkToProcess = " + isOkToProcess);
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
			//DO UPDATE-TRIGGER-ROW
			int numberOfRecordsUpdated = PszTriggerEmployee.setCompletionStatusBySequenceNumber(completionStatus, trigger.getSequenceNumber());
//			System.out.println("numberOfRecordsUpdated: " + numberOfRecordsUpdated);
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
//		System.out.println("completionStatus: " + completionStatus);
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
		System.out.println("********** ZHRI100A_getLegacyOprId()");
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
		//FROM HR036P CHR36 WHERE CHR36.H36EM# = #wrk_emplid AND CHR36.H36INX = #indexNum 
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

	public static String composeCommandString(CommonParameters commonParameters, String parameterString) {
		System.out.println("********** composeCommandString()");
		//LET $Part1 = '"CALL ' || $Library ||'/HRZ202A '
		//LET $Part2 = 'PARM(''' || $PSauditEmpl || ''' ''' || $PSOprid || ''' ''' || $PSTermDate || ''')" '
		//LET $Command = $Part1||$Part2
		//LET $Command = $RexecScript || ' ' || $Command || ' ' || $RMTSVR
		String commandString = 
//				ServerProperties.getRemoteExecScript() + " " 
//						+  "\"CALL " + ServerProperties.getAs400Library() 
//						+ "/" + commonParameters.getProcessName() + " " 
//						+ "PARM(" + parameterString + ")\" "
//						+ ServerProperties.getRemoteServerName();
						"CALL " + ServerProperties.getAs400Library() 
						+ "/" + commonParameters.getProcessName() + " " 
						+ "PARM(" + parameterString + ")";
//		System.out.println("$Command=> " + commandString);
		return commandString;
	}

	public static String composeErrorParameterString(CommonParameters commonParameters) {
		System.out.println("********** composeErrorParameterString()");
		String blankSpaceParameter = " ";
		String criticalFlag = commonParameters.getCriticalFlag() != null && commonParameters.getCriticalFlag() ? "Y" : "N";
		Calendar now = Calendar.getInstance();
		String errorDateParameter =  now.get(Calendar.MONTH) + "/" + now.get(Calendar.DATE) + "/" + now.get(Calendar.YEAR);
		String errorTimeParameter = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);
		String yesOrNoParameter = "Y"; //TODO: What should this value really be called
		//!Make Sure that the ErrorMessageParm is always 75 Characters long
		String errorMessageParameter = String.format("%1$-75s", commonParameters.getErrorMessageParameter());
		String parameterString = "'" + commonParameters.getErrorProgramParameter() + "' "
					+ "'" + commonParameters.getEmployeeId() + "' "
					+ "'" + commonParameters.getEffectiveSequence() + "' "
					+ "'" + blankSpaceParameter + "' "
					+ "'" + errorMessageParameter + "' "
					+ "'" + criticalFlag + "' "
					+ "'" + errorDateParameter + "' "
					+ "'" + errorTimeParameter + "' "
					+ "'" + commonParameters.getOperatorId() + "' "
					+ "'" + yesOrNoParameter + "'";
//		System.out.println(parameterString);
		return parameterString;
	}

	/**
	 * Call System Using $Command #Status Wait
	 * Execute the command that was built on the command waiting until completion
	 * @param command
	 * @param commonParameters
	 * @return 0 if success, non-zero if error
	 */
	//TODO: build it
	public static Integer callSystemUsingCommand(String commandString, CommonParameters commonParameters) {
		System.out.println("********** callSystemUsingCommand");
		//SHOW '$Command=> ' $Command
//		System.out.println("************************************************************");
		System.out.println("$Command=> " + commandString);
//		System.out.println(ServerProperties.print());
//		System.out.println(commonParameters.toString());
//		System.out.println("************************************************************");
		//DO Get-Current-Datetime  !Gets the current date and time using curdttim.sqc
		//SHOW 'Calling Command at: ' $SysDateTime  !Surya Added - TEMPMAST
		//"20-JUN-2017_12:01:06.000000_AM"
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss.SSSSSS_a");
		String currentDate = sdf.format(new Date()).toUpperCase();
		System.out.println("Calling Command at: " + currentDate);
		
		String remoteServerUsername = ServerProperties.getRemoteServerUsername(); // username for remote host
		String remoteServerPassword = ServerProperties.getRemoteServerPassword(); // password of the remote host
		String remoteServerName = ServerProperties.getRemoteServerName(); // remote host address
		Integer remoteServerPort = ServerProperties.getRemoteServerPort();
//	    List<String> result = new ArrayList<String>();
	    try {
	    	/*
	    	 * Create a new Jsch (Java Secure Channel) object
             * This object will execute shell commands or scripts on server
             */
	    	JSch javaSecureChannel = new JSch();
	        /*
	        * Open a new session, with username, host and port
	        * Set the password and call connect.
	        * session.connect() opens a new connection to remote SSH server.
	        * Once the connection is established, you can initiate a new channel.
	        * this channel is needed to connect to remotely execution program
	        */
	        Session session = javaSecureChannel.getSession(remoteServerUsername, remoteServerName, remoteServerPort);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.setPassword(remoteServerPassword);
	        session.connect();
	        //create the execution channel over the session
	        ChannelExec channelExec = (ChannelExec)session.openChannel("exec");
	        // Gets an InputStream for this channel. All data arriving in as messages from the remote side can be read from this stream.
	        InputStream in = channelExec.getInputStream();
	        // Set the command that you want to execute
	        // In our case its the remote shell script
//	        channelExec.setCommand("sh "+ scriptFileName);
//	        channelExec.setCommand("ls -l");
	        channelExec.setCommand(commandString);
	        // Execute the command
	        channelExec.connect();
	        // Read the output from the input stream we set above
	        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	        String line;
	        //Read each line from the buffered reader and add it to result list
	        // You can also simple print the result here
	        while ((line = reader.readLine()) != null) {
//	            result.add(line);
	            System.out.println(line);
	        }
	        //retrieve the exit status of the remote command corresponding to this channel
	        int exitStatus = channelExec.getExitStatus();
	        //Safely disconnect channel and disconnect session. If not done then it may cause resource leak
	        channelExec.disconnect();
	        session.disconnect();
	        if(exitStatus < 0) {
	            System.out.println("Done, but exit status not set!");
	        }
	        else if(exitStatus > 0) {
	            System.out.println("Done, but with error!");
	        }
	        else {
	            System.out.println("Done!");
	        }
		}
		catch(Exception e) {
			System.err.println("Error: " + e);
		}
	    return 0;
	}
	
	public static void javaRexec(String commandString) {
        String hostname, username, password;
        RExecClient client;
        String res = null;
        InputStream is = null;

        client = new RExecClient();

        hostname = "dev.corp.erac.com";
        username = "PSHRINT";
        password = "SMRHET01";

        try {
            client.connect(hostname);
        }
        catch (IOException e) {
            System.err.println("Could not connect to server.");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            client.rexec(username, password, commandString);
        }
        catch (IOException e) {
            try  {
                client.disconnect();
            }
            catch (IOException f) {
            	
            }
            e.printStackTrace();
            System.err.println("Could not execute command.");
            System.exit(1);
        }
//        IOUtil.readWrite(client.getInputStream(), client.getOutputStream(),
//                         System.in, System.out);

        try {
            client.disconnect();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
	}
	
	public static void javaRexec2(String commandString) {
//	    final int portNumber = 512;
//	    final int portNumber = 22;
	    RExecClient client = new RExecClient();
	    String hostname = "dev.corp.erac.com";
	    String username = "PSHRINT";
	    String password = "SMRHET01";

//	    commandString = "CALL HRZ102A";
	    commandString = "CALL EHRHRMS06#/HRZ101A Parm('840S4' '06' '20' '2017' '' '' '' 'V' 'O' '349NV' 'VOLUN  DISSATISFIED WHOURS         ')";
        String results = null;
        InputStream inputStream = null;
        try {
//          client.connect(hostname, portNumber);
			client.connect(hostname);
            if (!client.isConnected()) {
                System.err.println("The RLogin client is not connected to " + hostname);
            }
//            System.out.println("client.isConnected() = " + client.isConnected());
            try {
                client.rexec(username, password, commandString);
            }
            catch(IOException e) {
                e.printStackTrace();
                System.err.println("Could not execute command.");
            }
            catch (Exception e) {
    			e.printStackTrace();
    		}
            readWrite(client.getInputStream(), client.getOutputStream(), System.in, System.out);
            inputStream = client.getInputStream();
            if (inputStream != null && inputStream.available() > 0) {
            	results = IOUtils.toString(inputStream);
                System.out.println("results:\n" + results);
            } 
            else {
                System.err.println("InputStream is not available!");
            }
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
	
	public static final  void readWrite(InputStream remoteInput, OutputStream remoteOutput, InputStream localInput, OutputStream localOutput) {
			Thread reader, writer;
	        reader = new Thread() {
	        	public void run() {
	        		int ch;
	        		try {
	        			while (!interrupted() && (ch = localInput.read()) != -1) {
	        				remoteOutput.write(ch);
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
	        writer = new Thread() {
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
	        writer.setPriority(Thread.currentThread().getPriority() + 1);
	        writer.start();
	        reader.setDaemon(true);
	        reader.start();
	        try {
	        	writer.join();
	            reader.interrupt();
	        }
	        catch (InterruptedException e) {
				e.printStackTrace();
	        }
            catch (Exception e) {
    			e.printStackTrace();
    		}
	}
}

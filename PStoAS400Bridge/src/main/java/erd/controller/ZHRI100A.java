package erd.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import erd.DateUtil;
import erd.model.AdWrkFields;
import erd.model.CrossReferenceEmployeeId;
import erd.model.CrossReferenceMultipleEmployeeId;
import erd.model.HR036P;
import erd.model.PsDbOwner;
import erd.model.PsJob;
import erd.model.PsVariable;
import erd.model.TriggerEmployee;
import erd.model.Zhri100aFields;

public class ZHRI100A {
	
	public AdWrkFields adWrkFields;
	
//	private static String adActionCode; //$ADAction_Code
//	private Boolean adFound; //$AdFound
//	private Boolean fileIsOpen; //$file_open
//	public static Integer indexNumber;
//	private Boolean isOkToProcess; //$OK-To-Process
//	public static Boolean poiFlag;  //$PoiFlag  //Person of Interest
//	private static String wrkProcessName;  //$WrkProcess

//	public static String adLegacyOperatorId; //$ADLegOprid
//	public static String command; //$Command
//	public static String completionStatus; //$CompletionStatus
//	public static String errorMessageParm; //$ErrorMessageParm
//	public static Date psDateIn; //$PSDateIn
//	public static int status; //#Status

//	private static Boolean wrkCriticalFlag; //$WrkCriticalFlag
//	private static Boolean runFlag; //#run_flag
//	private static String remoteExecScript; //$RexecScript
//	private static String remoteAdServerName; //$RMTNTADSVR
//	private static String activeDirectoryHomePath; //$AD_HOME 
//	private static String remoteServerName; //$RMTSVR
//	private String runFilePath; //$RUN_FILEPATH
//	private static String oracleSystemId; //$ORACLE_SID
//	private static String peopleSoftHomePath; //$PS_HOME
//	private static String dbName;
//	private static String as400Library; //$Library
	
	
	public static String psEmpl; //$PSEmpl
//	private static String psEmplId; //$PSEmplId
	public static String psOprid; //$PSOprid

	public static String errorProgramParm = "HRZ102A"; //$ErrorProgramParm = 'HRZ102A'
//	public static String psAuditOperatorId; //$PSAuditOperId
//	private static String wrkEmployeeId1; //#Wrk_EmplId1
//	private static String wrkEmployeeId2; //#Wrk_EmplID2
//	private static Date psEffectiveDate; //$PSEffDt
//	public static BigDecimal psEffectiveSequence; //$PSEffSeq
	
//	private String commandNonPerson; //$Command_non

//	private static Integer wrkSequence; //#Wrk_Sequence
//	private static Integer wrkSeqNbr; //#WrkSeqNbr
//	private static String wrkEmployeeId; //$Wrk_Emplid
	
	public static void main() {
		Zhri100aFields zhri100aFields = new Zhri100aFields();
		TriggerEmployee trigger;
		//begin-program
		//Get-Current-DateTime  //queries the database to get the current time. It also initializes a slew of string variables ($AsOfToday, $AsOfNow, $CurrentCentury, $ReportDate
		//Init-DateTime  //sets a collection of variables that can be used by the other procedures in datetime.sqc that format dates or do date arithmetic
		//ZHRI100A.Process-Main
		//ZHRI100A.Get-Variable(RMTSVR)
		//ZHRI100A.Get-Variable(AS400library)
		//ZHRI100A.Get-Variable(RMTNTADSVR)
		initializeMainProperties(zhri100aFields);
		//ZHRI100A.Check-Interface-Runfile
		//ZHRI100A.Get-Trigger-Data
		trigger = TriggerEmployee.createMockTriggerForEmployeeTermination();
//		initializeLocalTriggerProperties(trigger);
		//ZHRI100A.Check-If-Contractor
		Boolean isContractor = checkIfContractor();
		//ZHRI100A.Check-If-Correct102A
//		Boolean isCorrect102A = checkIfCorrect102A(trigger.getEmployeeId().trim(), trigger.getEffectiveDate(), trigger.getProcessName().trim());
		Boolean isOkToProcess = checkIfCorrect102A(trigger.getEmployeeId().trim(), trigger.getEffectiveDate(), trigger.getProcessName().trim());
		//ZHRI100A.Call-Programs
		callPrograms(trigger);
		//ZHRI100A.Intialize-AD-WrkFields
		AdWrkFields adWrkFields = new AdWrkFields();
		adWrkFields.intializeAdWrkFields();
		//HR02-Process-Main  //This is the main processing procedure
		EmployeeTermination employeeTermination = new EmployeeTermination(trigger, adWrkFields, zhri100aFields);
		//HR02-Initialize-Fields  //Initialize the fields to ensure that that they all start out blank.
//		psAuditOperatorId = trigger.getOperatorId().trim().substring(1); //strips the 'E' off of the employee id
		psEmpl = " "; //$PSEmpl = ' '
		errorProgramParm = "HRZ102A";
//		employeeTermination.hr02InitializeFields();
		//HR02-Get-Job  //This routine will the Job Data row for each of the employee numbers entered in the trigger file.
		//HR02-Get-Action-Reason  //This routine will determine if a termination was voluntary or involuntary basedd on Action and Action Reason codes.
		//HR02-Get-Reason-Description  //This routine gets the description field from the Action Reason table when Action = Termination and Action Code equals Other.
		//ZHRI100A.Get-OprId
		
//		psOprid = getOprId(psEmpl, indexNumber, poiFlag); //TODO:

		//HR02-Process-Data  //This routine moves 'N' to change address parameter and calls the RPG program.
		//HR02-Trim-Parameters  //This routine trims all leading and trailing blanks from the data.
		//ZHRI100A.Call-System
		//ZHRI100A.Update-Trigger-Row
		//Call System Using $Command
		//Reset.sqc
		//end-program
	}
	
	private static void initializeMainProperties(Zhri100aFields zhri100aFields) {
		zhri100aFields.dbName = PsDbOwner.findDbName();
		String wrkProcessName = "ZHRI100A";
		zhri100aFields.peopleSoftHomePath = System.getenv("PS_HOME");
		zhri100aFields.activeDirectoryHomePath = zhri100aFields.peopleSoftHomePath + "/data/activedir/"; //TODO: where is this used???
		zhri100aFields.oracleSystemId = System.getenv("ORACLE_SID");
		if(zhri100aFields.oracleSystemId != null) {
			zhri100aFields.oracleSystemId = zhri100aFields.oracleSystemId.toUpperCase();
		}
		zhri100aFields.remoteServerName = PsVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, zhri100aFields.dbName, "RMTSVR"); //TODO: where is this used???
		zhri100aFields.remoteExecScript = "/usr/local/barch/" + zhri100aFields.oracleSystemId + "/scripts/zbas002b.sh"; //TODO: where is this used???
		zhri100aFields.as400Library = PsVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, zhri100aFields.dbName, "AS400library");
		zhri100aFields.remoteAdServerName = PsVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, zhri100aFields.dbName, "RMTNTADSVR"); //TODO: where is this used???
		zhri100aFields.wrkCriticalFlag = false;
//		zhri100aFields.runFlag = true;
	}
	
//	private static void initializeLocalTriggerProperties(TriggerEmployee trigger) {
//		LET $CompletionStatus = 'P'	!Initialize the CompletionStatus field
//	    MOVE &PS_ZHRT_INTTRIGGER.SEQ_NBR TO #WrkSequence
//		wrkSequence = trigger.getSequenceNumber();
//	    LET $AuditOprId = LTRIM(RTRIM(&PS_ZHRT_INTTRIGGER.OPRID,' '),' ')
//		psAuditOperatorId = trigger.getOperatorId().trim();
//		LET $PSEmplId = LTRIM(RTRIM(&PS_ZHRT_INTTRIGGER.EMPLID,' '),' ')
//		psEmplId = trigger.getEmployeeId().trim();
//		MOVE $PSEmplId TO #Wrk_EmplId1
//		wrkEmployeeId1 = psEmplId;
//		wrkEmployeeId1 = trigger.getEmployeeId().trim();
//		LET $Wrk_EmplId2 = EDIT(#Wrk_EmplId1,'099999999') //this value is used as a parameter in the error routine call //a 9-digit number left padded with zeros.
//		wrkEmployeeId2 = String.format("%9s", wrkEmployeeId1).replace(' ', '0'); //TODO: where is this used???
//		LET $PSEffDt = &PS_ZHRT_INTTRIGGEREFFDT
//		psEffectiveDate = trigger.getEffectiveDate();
//		MOVE &PS_ZHRT_INTTRIGGER.EFFSEQ TO #PSEffSeq
//		psEffectiveSequence = trigger.getEffectiveSequence();
//		LET $WrkProcess = LTRIM(RTRIM(&PS_ZHRT_INTTRIGGER.PROC_NAME,' '),' ')	!Remove leading and trailing blanks
//		wrkProcessName = trigger.getProcessName().trim();
//	}

	/**
	 * ZHRI100A.Process-Main
	 * This is the process controlling procedure.
	 */
	public void processMain(TriggerEmployee trigger, Boolean poiFlag, Boolean isOkToProcess, String completionStatus, Zhri100aFields zhri100aFields) {
		String commandNonPerson; //$Command_non
		Boolean fileIsOpen = false; //$file_open  //TODO: where should this get set???
		String command; //$Command
		Boolean runFlag = true; //#run_flag
//		dbName = PsDbOwner.findDbName();
//		wrkProcessName = "ZHRI100A";
//		! This gets the oracle_sid
//		LET $PS_HOME = getenv('PS_HOME')
//		peopleSoftHomePath = System.getenv("PS_HOME");
//		LET $AD_HOME = $PS_HOME || '/data/activedir/'    !Path for Active Directory
//		activeDirectoryHomePath = peopleSoftHomePath + "/data/activedir/"; //TODO: where is this used???
//		LET $ORACLE_SID = getenv('ORACLE_SID')
//		oracleSystemId = System.getenv("ORACLE_SID");
//		if(oracleSystemId != null) {
//			oracleSystemId = oracleSystemId.toUpperCase();
//		}
//		UPPERCASE $ORACLE_SID
//		!Returns name of AS/400 machine for use in zbas002b.sh
//		remoteServerName = PsVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, dbName, "RMTSVR"); //TODO: where is this used???
//		LET $RexecScript = '/usr/local/barch/' || $ORACLE_SID || '/scripts/zbas002b.sh'
//		remoteExecScript = "/usr/local/barch/" + oracleSystemId + "/scripts/zbas002b.sh"; //TODO: where is this used???
//		!Returns library name on AS/400 where programs reside
//		as400Library = PsVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, dbName, "AS400library");
//		!Returns IP address of NT server
//		remoteAdServerName = PsVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, dbName, "RMTNTADSVR"); //TODO: where is this used???
//		LET $WrkCriticalFlag = 'N'
//		wrkCriticalFlag = false;
//		LET #run_flag = 1
//		runFlag = true;
		while(runFlag == true) {
//		WHILE #run_flag = 1        !Never ending loop
//		   	DO ZHRI100A.Check-Interface-Runfile
			runFlag = checkInterfaceRunfile(zhri100aFields);
//		   	DO ZHRI100A.Get-Trigger-Data       !Process the interface requests
			String adLegacyOperatorId = "";
			String adActionCode = "T";
			getTriggerData(trigger, adLegacyOperatorId, adActionCode, poiFlag, isOkToProcess, completionStatus);
//		   	DO ZHRI100A.Commit-Transaction  //TODO: what's going on here??
//		   	LET $Command = 'sleep 15'  !After interface run wait 15 seconds and do it again  !sree**rehost        !ZHR_MOD_ZHRI100A_sleep
			command = "sleep 15";
//		   	CALL System Using $Command #status Wait            !sree**rehost     !ZHR_MOD_ZHRI100A_sleep
//		   	DO ZHRI100A.Get-Trigger-Data-NonEmp  !calls the procesdure for POIs ad multiple EIDs
//		   	DO ZHRI100A.Commit-Transaction
//		   	LET $Command_non = 'sleep 15'  !after the main trigger table wait for 15 secs
			commandNonPerson = "sleep 15";
//		   	CALL System Using $Command_non #status Wait
//		   	IF $file_open = 'Y'                        !ZHR_MOD_ZHRI100A_sleep
			if(fileIsOpen) {
//		   		CLOSE 1                               !ZHR_MOD_ZHRI100A_sleep
				//close file
//		   	END-IF                                     !ZHR_MOD_ZHRI100A_sleep
			}
//		   	LET $file_open = 'N'                       !ZHR_MOD_ZHRI100A_sleep
		fileIsOpen = false;
//		END-WHILE   !1=1
		}
//		LET $Command = 'mv' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.stop' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'
		command = "mv" + " " + "/usr/local/barch/" + zhri100aFields.oracleSystemId + "/work/hrinterface.stop" + " " + "/usr/local/barch/" + zhri100aFields.oracleSystemId + "/work/hrinterface.run";
//		CALL System Using $Command #status Wait           !ZHR_MOD_ZHRI100A_sleep
//		ZHRI100A.callSystem(command);
	}
	
	/**
	 * ZHRI100A.Call-Error-Routine
	 * Builds the command and calls the error routine
	 */
	public static void callErrorRoutine(String employeeId, String errorMessageParm, Zhri100aFields zhri100aFields) {
//		LET $Wrk_EmplId2 = EDIT(#Wrk_EmplId1,'099999999') //this value is used as a parameter in the error routine call //a 9-digit number left padded with zeros.
//		String wrkEmployeeId2 = String.format("%9s", employeeId.trim()).replace(' ', '0');
		employeeId = String.format("%9s", employeeId.trim()).replace(' ', '0');
		String addDateErrorParm = "";
		String addTimeErrorParm = "";
		String opridErrorParm = "";
		String command; //$Command
		//!Make Sure that the ErrorMessageParm is always 75 Characters long
//		LET $ErrorMessageParm = Substr($ErrorMessageParm,1,75)  !Make sure not more than 75 long
//		LET $ErrorMessageParm = Rpad($ErrorMessageParm,75,' ')  !Make sure not less than 75 long
		errorMessageParm = errorMessageParm = String.format("%75s", errorMessageParm);
//		LET $Command =   '"CALL '       ||
//		                 $Library                     ||
//		                 '/HRZ110A '                  ||
//		                 'PARM('''                    ||
//		                 $ErrorProgramParm            ||
//		                 ''' '''                      ||
//		                 $Wrk_EmplId2                 ||
//		                 ''' '''                      ||
//		                 ' '                          ||
//		                 ''' '''                      ||
//		                 $ErrorMessageParm            ||
//		                 ''' '''                      ||
//		                 $WrkCriticalFlag             ||
//		                 ''' '''                      ||
//		                 $AddDateErrorParm            ||
//		                 ''' '''                      ||
//		                 $AddTimeErrorParm            ||
//		                 ''' '''                      ||
//		                 $OpridErrorParm              ||
//		                 ''' '''                      ||
//		                 'Y'                          ||
//		                 ''')" '
		command = "CALL '" + zhri100aFields.as400Library + "/HRZ110A PARM('"
				+ errorProgramParm + "' '"
//        		+ wrkEmployeeId2 + "' ' ' '"
        		+ employeeId + "' ' ' '"
        		+ errorMessageParm + "' '"
        		+ zhri100aFields.wrkCriticalFlag + "' '"
        		+ addDateErrorParm + "' '"
        		+ addTimeErrorParm + "' '"
        		+ opridErrorParm + "' '"
        		+ "' 'Y')";
//		Do Call-System                                              
	}
	
	/**
	 * Call-Programs - from ZHRI100A.SQR
	 * Subroutine will call appropriate programs
	 */
	public static void callPrograms(TriggerEmployee trigger) {
		//WHEN = 'ZHRI102A'
		//!Move fields to be used in the called SQC
		//MOVE #Wrk_Sequence to #WrkSeqNbr
//		wrkSeqNbr = wrkSequence;  //TODO: find where #Wrk_Sequence is set
//		wrkSeqNbr = trigger.getSequenceNumber();  //TODO: find where #Wrk_Sequence is set
		//LET $PSAuditOperId = $AuditOprId
//		psAuditOperatorId = trigger.getOperatorId();
        //LET $PSDateIn = $PSEffDt
//		psDateIn = trigger.getEffectiveDate();
        //LET $Wrk_Emplid = $PSEmplId
//		wrkEmployeeId = trigger.getEmployeeId();
        //LET $ADAction_Code = 'T'
//		adActionCode = "T";
        //LET $ADLegOprid = ''
//		adLegacyOperatorId = "";
        //DO HR02-Process-Main    !ZHRI102A.SQC
//		HR02ProcessMain();
	}
	
	/**
	 * ZHRI100A.Call-System
	 * Executes a command line statement stored in the $Command Variable
	 */
	public static Integer callSystem(String employeeId, String command, Boolean poiFlag, Zhri100aFields zhri100aFields) {
		int status; //#Status
		String showCommand;
//		LET #CommandLength = length($Command)             !Get the length of the command
		int commandLength = command.length();
//		LET #SubstrStartPos = 1    !Initiate the starting positions to show the first 100 positions
		int substringStartPosition = 1;
//		WHILE #SubstrStartPos <= #CommandLength
		while(substringStartPosition <= commandLength) {
//			LET $ShowCommand = substr($Command,#SubstrStartPos,100)   !Substring 100 positions to show
			showCommand = command.substring(substringStartPosition, 100);
//		    LET #SubstrStartPos = #SubstrStartPos + 100    !Increase the starting position by 100
			substringStartPosition += 100;
//		END-WHILE   !#SubstrStartPos <= #CommandLength
		}
//		LET $Command = $RexecScript || ' ' || $Command || ' ' || $RMTSVR  !changed for v8.3
		command = zhri100aFields.remoteExecScript + " " +  command + " " + zhri100aFields.remoteServerName;
//		DO GET-CURRENT-DATETIME  !Gets the current date and time using curdttim.sqc
		java.util.Date currentDate = new java.util.Date();
//		CALL System Using $Command #Status Wait      !Execute the command that was built on the command waiting until completion
		status = callSystemUsingCommand();
//		IF #status != 0
		if(!"0".equals(status)) {
//			!error
//		  	LET $ErrorProgramParm = 'ZHRI100A'
			errorProgramParm = "ZHRI100A";
//		  	LET $ErrorMessageParm = ' '
//		  	LET $ErrorMessageParm = 'Error executing Call System command, contact HR-PeopleSoft Oncall'
			String errorMessageParm = "Error executing Call System command, contact HR-PeopleSoft Oncall";
//		  	LET $WrkCriticalFlag  = 'Y'
			zhri100aFields.wrkCriticalFlag = true;
//		  	DO Prepare-Error-Parms
//		  	IF $PoiFlag = 'N'
			if(!poiFlag) {
//		  		DO Call-Error-Routine
				callErrorRoutine(employeeId, errorMessageParm, zhri100aFields);
//		  	ELSE
			}
			else {
//		    	DO Call-Error-Routine-NonEmp
				callErrorRoutineNonEmp(errorMessageParm);
//		  	END-IF
			}
//		  	LET $WrkCriticalFlag  = 'N'
			zhri100aFields.wrkCriticalFlag = false;
//		END-IF
		}
		return status;
	}
	
	private static void callErrorRoutineNonEmp(String errorMessageParm) {
		// TODO Auto-generated method stub
		
	}

	public static int callSystemUsingCommand() {
		return 0;
	}
	
	/**
	 * ZHRI100A.Check-If-Contractor
	 * Checks to see if the employee is a contractor
	 */
	public static Boolean checkIfContractor() {
		Boolean isContractor = false;
//		LET $Found = 'N'
//		BEGIN-SELECT
//			'X'
//			LET $Found = 'Y'
//			FROM PS_JOB RJ
//			WHERE RJ.EMPLID = $PSEmplId
//				AND RJ.EMPL_CLASS = 'R'
//		  		AND RJ.EFFDT = (SELECT MAX(EFFDT) FROM  PS_JOB RJ2 WHERE  RJ2.EMPLID = RJ.EMPLID
//		                     	AND  RJ2.EMPL_RCD = RJ.EMPL_RCD AND  RJ2.EFFDT <= $AsOfToday)
//		  		AND RJ.EFFSEQ = (SELECT MAX(EFFSEQ) FROM PS_JOB RJ3 WHERE RJ3.EMPLID = RJ.EMPLID
//		                     	AND  RJ3.EMPL_RCD = RJ.EMPL_RCD AND RJ3.EFFDT = RJ.EFFDT)
//		END-SELECT
		return isContractor;
	}
	
	/**
	 * ZHRI100A.Check-If-Correct102A
	 * Checks to see if 102A process has JOB row
	 */
	public static Boolean checkIfCorrect102A(String psEmplId, Date psEffectiveDate, String processName) {
//		LET $OK-To-Process = 'N'
		Boolean isOkToProcess = false;
//		IF $WrkProcess = 'ZHRI102A'
//		if("ZHRI102A".equalsIgnoreCase(wrkProcessName)) {
		if("ZHRI102A".equalsIgnoreCase(processName)) {
//			DO DTU-ADD-DAYS($PSEffDt, 1, $Dt102)
			//add a day to current effective date
			psEffectiveDate = DateUtil.addDays(psEffectiveDate, 1);
//		END-IF
		}
//		BEGIN-SELECT
//		'XX'
//		LET $OK-To-Process = 'Y'
//		FROM PS_JOB PS_JOB WHERE PS_JOB.EMPLID = $PSEmplId AND TO_CHAR(PS_JOB.EFFDT, 'YYYY-MM-DD') = $Dt102
		List<PsJob> psJobList = PsJob.findByEmployeeIdAndEffectiveDate(psEmplId, psEffectiveDate);
    	if(psJobList != null && !psJobList.isEmpty()) {
    		isOkToProcess = true;
    	}
//		END-SELECT
		return isOkToProcess;
	}
	
	/**
	 * ZHRI100A.Check-Interface-Runfile
	 * This procedure will check the existence run file
	 */
	public Boolean checkInterfaceRunfile(Zhri100aFields zhri100aFields) {
		Boolean runFlag = false;
		String runFilePath;
//		LET $RUN_FILEPATH = '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'  //concatenate
		runFilePath = "/usr/local/barch/" + zhri100aFields.oracleSystemId + "/work/hrinterface.run";
//		LET #file_exists = exists($RUN_FILEPATH)
		Boolean fileExists = (new File(runFilePath)).exists();
//		IF #file_exists = 0
		if(fileExists == false) {
//			LET #run_flag = 1
			runFlag = true;		}
//		ELSE
		else {
//			LET #run_flag = 0
			runFlag = false;
//		END-IF
		}
		return runFlag;
	}

	/**
	 * Get-Legacy-OprId from ZHRI100A.SQR
	 * Formulates legacy OprId from HR036P where HR036P.H36EM# = #wrk_emplid and HR036P.H36INX = #indexNum UNION
	 */
	public static String getLegacyOprId(String l_employeeId, Integer l_indexNumber, Boolean l_isPoi) {
//		LET $LegEmplId = ''
//		MOVE $Wrk_Emplid to #wrk_emplid
//		IF $PoiFlag = 'N'
		if(!l_isPoi) {
//			MOVE 0 to #indexNum
			l_indexNumber = 0;
//		END-IF
		}
//		Begin-Select
//		HR036P.H36NAM
//		HR036P.H36EMP
//		HR036P.H36EM#
//	  	!This IF statement and OR part of Select is a workaround to some problem in v8 (gateway and new version of SQR). 
//		!The Select was hanging if it couldn't find a match in HR036P, so the Select assures that the Select always finds a match.
//	  	LET #WRK_CHR36_H36EM_NUM = &HR036P.H36EM#
//	  	IF #WRK_CHR36_H36EM_NUM = #wrk_emplid
//	    	LET $LegEmpName = &HR036P.H36NAM
//	    	DO Format-Employee-Name
//	    	LET $LegEmplid = &HR036P.H36EMP
//	    	LET $LegEmplid = substr($LegEmplid,1,5)
//	    	IF (#indexNum = 0 and $PoiFlag = 'Y') OR $PoiFlag = 'N'
//	    		DO Insert-OprId
//	    	ELSE
//	    		IF (#indexNum <> 0 AND $PoiFlag = 'Y')
//	        		DO Update-OprId
//	      		END-IF
//	    	END-IF  
//		END-IF    !#WRK_CHR36_H36EM_NUM = #wrk_emplid
//		FROM HR036P
//		WHERE HR036P.H36EM# = #wrk_emplid
//		AND HR036P.H36INX = #indexNum 
//		UNION
//		SELECT
//			' ', ' ' , 9999999999
//		FROM DUAL
//		End-Select
		HR036P hr036P = HR036P.findByEmployeeIdAndIndexNumber(l_employeeId, l_indexNumber);
    	if(hr036P != null) {
	    	if(hr036P.getEmployeeName() != null && !hr036P.getEmployeeName().isEmpty()) {
	    		hr036P.setEmployeeName(erd.StringUtil.formatLegacyEmployeeNameToPeopleSoftEmployeeName(hr036P.getEmployeeName()));
    		}
//	    	LET $LegEmplid = substr($LegEmplid,1,5)
	    	if(hr036P.getEmployeeId() != null && !hr036P.getEmployeeId().isEmpty()) {
	    		hr036P.setEmployeeId(hr036P.getEmployeeId().substring(0,5));
    		}
//	    	IF (#indexNum = 0 and $PoiFlag = 'Y') OR $PoiFlag = 'N'
//    			DO Insert-OprId
//    		ELSE
//    			IF (#indexNum <> 0 AND $PoiFlag = 'Y')
//        			DO Update-OprId
//      			END-IF
//    		END-IF  
			return hr036P.getEmployeeId();
		}
    	return null;
	}

	/**
	 * Get-OprId from ZHRI100A.SQR
	 * This routine gets the operator id from the operator definition table
	 */
	public static String getOprId(String l_psEmpl, Integer l_indexNumber, Boolean l_poiFlag) {
		return getOprId(l_psEmpl, l_indexNumber, l_poiFlag, null);
	}
	public static String getOprId(String l_psEmpl, Integer l_indexNumber, Boolean l_poiFlag, BigDecimal l_eidIndexNumber) {
//		LET $Found = 'N'
//		boolean found = false;
//		LET $PSOprid = ''
		String psOprId = "";
//		IF (#indexNum = 0 AND $PoiFlag = 'Y') OR $PoiFlag = 'N'
		if((l_indexNumber == 0 && l_poiFlag) || !l_poiFlag) {
//			DO Get-LegId-For-Seq0
			psOprId = CrossReferenceEmployeeId.zhri100AGetLegIdForSeq0(l_psEmpl);
		}
//		ELSE
		else {
//			IF (#indexNum <> 0 and $PoiFlag = 'Y')
			if(l_indexNumber != 0 && l_poiFlag) {
//				BigDecimal eidIndexNumber = trigger.getEidIndexNumber();
//		    	DO Get-LegId-For-SeqNum
				psOprId = CrossReferenceMultipleEmployeeId.getLegIdForSeqNum(l_psEmpl, l_eidIndexNumber);
//			END-IF
			}
//		END-IF
		}
//		!If an oprid does not exist for the employee, create one
//		IF ($Found = 'N')
//		if(!found) {
		if(psOprId == null || psOprId.isEmpty()) {
//			DO Get-Legacy-OprId
//			LET $PSOprid = $LegEmplid
			psOprId = getLegacyOprId(l_psEmpl, l_indexNumber, l_poiFlag);
//		END-IF    !$Found = 'N'
		}
		return psOprId;
	}
	
	/**
	 * Get-Trigger-Data - from ZHRI100A.SQR
	 * This procedure will get the trigger data that needs to be interfaced
	 */
	public void getTriggerData(TriggerEmployee trigger, String adLegacyOperatorId, String adActionCode, Boolean poiFlag, Boolean isOkToProcess, String completionStatus) {
		Boolean adFound = false; //$AdFound  //TODO: where should this be set???
//		LET $CompletionStatus = 'P'	!Initialize the CompletionStatus field
//	    MOVE &PS_ZHRT_INTTRIGGER.SEQ_NBR TO #WrkSequence
//		wrkSequence = trigger.getSequenceNumber();
//	    LET $AuditOprId = LTRIM(RTRIM(&PS_ZHRT_INTTRIGGER.OPRID,' '),' ')
//		psAuditOperatorId = trigger.getOperatorId().trim();
//		LET $PSEmplId = LTRIM(RTRIM(&PS_ZHRT_INTTRIGGER.EMPLID,' '),' ')
//		psEmplId = trigger.getEmployeeId().trim();
//		MOVE $PSEmplId TO #Wrk_EmplId1
//		wrkEmployeeId1 = psEmplId;
//		wrkEmployeeId1 = trigger.getEmployeeId().trim();
//		LET $Wrk_EmplId2 = EDIT(#Wrk_EmplId1,'099999999') //this value is used as a parameter in the error routine call //a 9-digit number left padded with zeros.
//		wrkEmployeeId2 = String.format("%9s", trigger.getEmployeeId().trim()).replace(' ', '0'); //TODO: where is this used???
//		LET $PSEffDt = &PS_ZHRT_INTTRIGGEREFFDT
//		psEffectiveDate = trigger.getEffectiveDate();
//		MOVE &PS_ZHRT_INTTRIGGER.EFFSEQ TO #PSEffSeq
//		psEffectiveSequence = trigger.getEffectiveSequence();
//		LET $WrkProcess = LTRIM(RTRIM(&PS_ZHRT_INTTRIGGER.PROC_NAME,' '),' ')	!Remove leading and trailing blanks
//		wrkProcessName = trigger.getProcessName().trim();
//		DO Check-If-Contractor
//		Boolean isContractor = PsJob.employeeIsContractor(psEmplId);
		Boolean isContractor = PsJob.employeeIsContractor(trigger.getEmployeeId().trim());
//		LET $PoiFlag = 'N'
		poiFlag = false;
//		IF $Found = 'N'     !Not a contractor
//		        AND  $PSEmplId <> ''	!not a blank emplid		!ZHR_MOD_ZHRI100A_110A
//		if(!isContractor && psEmplId != null && !psEmplId.isEmpty()) {
		if(!isContractor && trigger.getEmployeeId().trim() != null && !trigger.getEmployeeId().trim().isEmpty()) {
//		    !CQ 103011 Added a check for 'ZHRI102A' - to see IF corresponding row on JOB 
//		    IF $WrkProcess = 'ZHRI102A'
			if("ZHRI102A".equalsIgnoreCase(trigger.getProcessName().trim())) {
// 	        	DO Check-If-Correct102A                
// 	          	IF $OK-To-Process = 'Y'
				if(isOkToProcess) {
// 	             	DO Call-Programs
					callPrograms(trigger);
				}
//	 	        ELSE
				else {
// 	             	LET $CompletionStatus = 'C'
					completionStatus = "C";
// 	             	DO Update-Trigger-Row
// 	          	END-IF
				}
			}
// 	       	ELSE
			else {
// 	          	DO Call-Programs
				callPrograms(trigger);
// 	       	END-IF
			}
		}
//	    ELSE
		else {
//	 	    IF $Found = 'Y'
			if(isContractor) {
//		        LET $CompletionStatus = 'C'
				completionStatus = "C";
//			END-IF
			}
//	       	IF  $PSEmplId = ''	!ZHR_MOD_ZHRI100A_110A //Error Routine
//			if(psEmplId ==  null || psEmplId.isEmpty()) {
			if(trigger.getEmployeeId().trim() ==  null || trigger.getEmployeeId().trim().isEmpty()) {
//	       		LET $CompletionStatus = 'E'
				completionStatus = "E";
//	       	END-IF
			}
//	    END-IF	!$Found = 'N'
		}
//	    IF $CompletionStatus <> 'P'
		if("P".equalsIgnoreCase(completionStatus)) {
// 	       IF ($ADAction_Code <> '') AND ($ADLegOprid <> '')
			if(!adActionCode.isEmpty() && !adLegacyOperatorId.isEmpty()) {
//				!DO Check-Effdt-Transaction
				//do nothing
//	 	        IF $AdFound = 'N'
				if(!adFound) {
//	 	  			!DO Build-Active-Dir-Output-File	!ZHR_ZHRI100A_REMOVE_AD
					//do nothing
//		 	    END-IF
				}
//			END-IF
			}
// 	     	DO Update-Trigger-Row
// 	    END-IF    !$CompletionStatus <> 'P'
		}
//	    FROM PS_ZHRT_INTTRIGGER PS_ZHRT_INTTRIGGER, PS_JOB PS_JOB
//	    	WHERE PS_ZHRT_INTTRIGGER.TASK_FLAG = 'P'
//	    	!************************************************************************************
//	    	! Start : ZHR_MOD_INTERFACE_PREHIRE
//	    	!************************************************************************************
//	    	AND (PS_ZHRT_INTTRIGGER.EFFDT <= $AsOfToday OR PS_ZHRT_INTTRIGGER.PROC_NAME='ZHRI101A' OR  PS_ZHRT_INTTRIGGER.PROC_NAME='ZHRI106A')
//	    	!************************************************************************************
//	    	! End : ZHR_MOD_INTERFACE_PREHIRE
//	    	!************************************************************************************
//	    	!************************************************************************************
//	    	! PeopleCode 8.0 fires in different order than 8.3, need to insure the new hire and rehire appear before the other changes even if they don't show up first in the sequence.
//	    	!************************************************************************************
//	    	AND (CASE WHEN proc_name IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END) = 
//	    	      		(SELECT MIN(CASE WHEN proc_name IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END)  
//	    	                 FROM PS_ZHRT_INTTRIGGER PS_ZHRT_INTTRIGGER2 
//								WHERE PS_ZHRT_INTTRIGGER2.EMPLID = PS_ZHRT_INTTRIGGER.EMPLID
//	    	                    	AND PS_ZHRT_INTTRIGGER2.TASK_FLAG = 'P'
//	    	                    	AND (PS_ZHRT_INTTRIGGER2.EFFDT <= SYSDATE OR PS_ZHRT_INTTRIGGER2.PROC_NAME='ZHRI101A' 
//									OR PS_ZHRT_INTTRIGGER2.PROC_NAME='ZHRI106A'))
//	    	!********************************************************************************** 
//	    	! Join in JOB record to ensure that only employees that have a JOB record get processed.
//	    	!**********************************************************************************
//	    	AND PS_JOB.EMPLID = PS_ZHRT_INTTRIGGER.EMPLID
//	    	AND PS_JOB.EFFDT = (SELECT MAX(PS_JOB2.EFFDT)
//	    		FROM PS_JOB PS_JOB2
//	    	    	WHERE PS_JOB2.EMPLID = PS_JOB.EMPLID
//	    	    		AND PS_JOB2.EMPL_RCD = PS_JOB.EMPL_RCD)
//	    	  			AND PS_JOB.EFFSEQ = 
//							(SELECT MAX(PS_JOB3.EFFSEQ)
//	    	        			FROM PS_JOB PS_JOB3
//	    	        				WHERE PS_JOB3.EMPLID = PS_JOB.EMPLID
//	    	            				AND PS_JOB3.EMPL_RCD = PS_JOB.EMPL_RCD
//	    	            				AND PS_JOB3.EFFDT = PS_JOB.EFFDT)
//		END-SELECT
	}

}

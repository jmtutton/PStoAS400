package erd.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import erd.model.CrossReferenceEmployeeId;
import erd.model.CrossReferenceMultipleEmployeeId;
import erd.model.HR036P;
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
		try {
			initializeServerProperties();
			processMain();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Reset   !Reset.sqc  //TODO
	}

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
					HashMap<String, Object> parameterMap = initializeParameterMap(trigger);
					completionStatus = callPrograms(parameterMap);
				}
				else {
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
	public static HashMap<String, Object> initializeParameterMap(PszTriggerSuperclass trigger) {
		System.out.println("********** ZHRI100A.initializeParameterMap");
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("criticalFlag", false);
		parameterMap.put("processName", trigger.getProcessName());
		parameterMap.put("employeeId", trigger.getEmployeeId());
		parameterMap.put("operatorId", trigger.getOperatorId());
		parameterMap.put("effectiveDate", trigger.getEffectiveDate());
		parameterMap.put("effectiveSequence", trigger.getEffectiveSequence());
		parameterMap.put("completionStatus", trigger.getCompletionStatus());
		if(trigger instanceof PszTriggerEmployee) {
			parameterMap.put("poiFlag", false);
		}
		else {
			parameterMap.put("poiFlag", true);
		}
		return parameterMap;
	}

	/**
	 * Call-Programs from ZHRI100A.SQR
	 * Subroutine will call appropriate programs
	 * @param trigger
	 * @return
	 */
	public static String callPrograms(HashMap<String, Object> parameterMap) {
		System.out.println("********** ZHRI100A.callPrograms");
		String completionStatus = (String)parameterMap.get("completionStatus");
		switch((String)parameterMap.get("processName")) {
		case "ZHRI101A": //Process to hire employee
			parameterMap.put("hireRehireFlag",  "H");
			completionStatus = new EmployeeNewHire().doProcess(parameterMap);
			break;
		case "ZHRI102A": //Process to terminate an employee
			completionStatus = new EmployeeTermination().doProcess(parameterMap);
			break;
		case "ZHRI104A": //Process for employee job profile change
			completionStatus = new EmployeeJobProfileChange().doProcess(parameterMap);
			break;
		case "ZHRI105A": //Process for employee demographics change
			completionStatus = new EmployeeDemographicChange().doProcess(parameterMap);
			break;
		case "ZHRI106A": //Process for employee rehire
			//DO HR01-Process-Main       !ZHRI101A.SQC
			parameterMap.put("hireRehireFlag",  "R");
			completionStatus =  new EmployeeNewHire().doProcess(parameterMap);
			break;
		case "ZHRI107A": //Process for converting employee dates
			completionStatus = new EmployeeDateChange().doProcess(parameterMap);
			break;
		case "ZHRI109A": //Process for employee group transfer
			completionStatus = new EmployeeGroupTransfer().doProcess(parameterMap);
			break;
		case "ZHRI201A": //Process for non-person new hire
			parameterMap.put("hireRehireFlag",  "H");
			completionStatus = new NonPersonNewHire().doProcess(parameterMap);
			break;
		case "ZHRI202A": //Process for non-person termination
			completionStatus = new NonPersonTermination().doProcess(parameterMap);
			break;
		case "ZHRI205A": //Process for non-person demographics change
			completionStatus = new NonPersonDemographicChange().doProcess(parameterMap);
			break;
		case "ZHRI206A": //Process for non-person rehire
			//DO HR201-Process-Main       !ZHRI201A.SQC
			parameterMap.put("hireRehireFlag",  "R");
			completionStatus = new NonPersonNewHire().doProcess(parameterMap);
			break;
		case "ZHRI101D": //Row deleted on hire
			parameterMap.put("errorProgramParameter", "HRZ101A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the hire process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		case "ZHRI102D": //Row deleted on term
			parameterMap.put("errorProgramParameter", "HRZ102A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the termination process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		case "ZHRI104D": //Row deleted on job status change
			parameterMap.put("errorProgramParameter", "HRZ104A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the job profile process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		case "ZHRI105D": //Row deleted on demographics change
			parameterMap.put("errorProgramParameter", "HRZ105A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the demographics process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		case "ZHRI106D": //Row deleted on rehire
			parameterMap.put("errorProgramParameter", "HRZ101A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the re-hire process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		case "ZHRI107D": //Row deleted on the dates process
			parameterMap.put("errorProgramParameter", "HRZ107A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the dates process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		case "ZHRI109D": //Row deleted on the group transfer process
			parameterMap.put("errorProgramParameter", "HRZ109A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the group transfer process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		default: //ERROR
			//set to E to prevent re-processing and to mark the record as error
			completionStatus = "E";
			break;
		}
		return completionStatus;
	}

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
//	 * Remotely executes a command on the AS400.
//	 * @see Call-System in ZHRI100A.SQR
//	 * @param commandString
//	 * @param commonParameters
//	 * @return 0 if success, non-zero if error
//	 */
//	public static Integer executeRemoteCommand(String commandString, HashMap<String, Object> parameterMap) {
//		System.out.println("*** ZHRI100A.executeRemoteCommand() ***");
//		Integer status = executeCommandRexec(commandString);
//		if(status != 0) { //!error
//			parameterMap.put("errorProgramParameter", "ZHRI100A");
//			parameterMap.put("errorMessageParameter", "Error executing Call System command, contact HR-PeopleSoft On-Call");
//			parameterMap.put("criticalFlag", true);
//			commandString = composeRexecCommandString("HRZ110A", composeErrorParameterString(parameterMap));
//			ZHRI100A.executeRemoteCommand(commandString, parameterMap);
//			parameterMap.put("criticalFlag", false);
//		}
//		return status;
//	}

	/**
	 * Composes the error command and executes it on the AS400.
	 * @see Call-Error-Routine in ZHRI100A.SQR
	 * @param processName
	 * @param commonParameters
	 */
	public static void doErrorCommand(HashMap<String, Object> parameterMap) {
		System.out.println("*** ZHRI100A.doErrorCommand() ***");
		parameterMap.put("parameterString", composeErrorParameterString(parameterMap));
		parameterMap.put("processName", "HRZ110A");
		ZHRI100A.doCommand(parameterMap);
	}

	/**
	 * This routine will build the where clause that will select the correct Run-ID to use.
	 * @see Build-Group-Where-Clause in ZHRI100A.SQR
	 * @param whereClause
	 * @return SQL where clause
	 */
	public static String ZHRI100A_buildGroupWhereClause(String whereClause) {
		System.out.println("*** ZHRI100A.ZHRI100A_buildGroupWhereClause() ***");
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
		System.out.println("*** ZHRI100A.ZHRI100A_buildEmplIdWhereClause() ***");
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
	public static String fetchLegacyEmployeeId(HashMap<String, Object> parameterMap) {
		System.out.println("*** ZHRI100A.fetchLegacyEmployeeId() ***");
		String employeeId;
		if((Boolean)parameterMap.get("poiFlag")) {
			employeeId = CrossReferenceMultipleEmployeeId.ZHRI100A_getLegIdForSeqNum((String)parameterMap.get("employeeId"), (BigDecimal)parameterMap.get("eidIndexNumber"));
		}
		else {
			employeeId = CrossReferenceEmployeeId.ZHRI100A_getLegIdForSeq0((String)parameterMap.get("employeeId"));
		}
		if(employeeId == null || employeeId.isEmpty()) {
			BigDecimal indexNumber = (Boolean)parameterMap.get("poiFlag") ? (BigDecimal)parameterMap.get("effectiveSequence") : new BigDecimal(0);
			employeeId = fetchNewLegacyEmployeeId((String)parameterMap.get("employeeId"), indexNumber);
		}
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

	/**
	 * initializeServerProperties
	 * Sets the values for the remote AS400 server in a static class that shares the values across the application. 
	 */
	public static void initializeServerProperties() {
		System.out.println("*** ZHRI100A.initializeServerProperties() ***");
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

	/**
	 * This procedure will check the existence run file
	 * @see Check-Interface-Runfile in ZHRI100A.SQR
	 * @param oracleSystemId
	 * @return true if run file does not exist
	 */
	public static Boolean ZHRI100A_checkInterfaceRunFile(String oracleSystemId) {
		System.out.println("*** ZHRI100A.ZHRI100A_checkInterfaceRunFile() ***");
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
	
	/**
	 * This procedure will get the trigger data that needs to be interfaced
	 * @see Get-Trigger-Data in ZHRI100A.SQR
	 * @param trigger
	 * @return completionStatus - "P" if trigger record is good to process
	 */
	public static String checkTriggerRecord(PszTriggerSuperclass trigger) {
		System.out.println("*** ZHRI100A.checkTriggerRecord() ***");
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
	public static String fetchNewLegacyEmployeeId(String employeeId, BigDecimal indexNumber) {
		System.out.println("*** ZHRI100A.fetchNewLegacyEmployeeId() ***");
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
	
	/**
	 * Composes a command string to remotely execute on the AS400 server
	 * @param processName
	 * @param parameterString
	 * @return commandString to be used in regex call to AS400
	 */
	public static String composeCommandString(HashMap<String, Object> parameterMap) {
		System.out.println("*** ZHRI100A.composeRexecCommandString() ***");
		String commandString = 
						"CALL " + ServerProperties.getAs400Library() + "/" 
								+ (String)parameterMap.get("processName") + " " 
								+ "PARM(" + (String)parameterMap.get("paramterString") + ")";
		return commandString;
	}

	/**
	 * Composes a parameter string for the AS400 error program.
	 * Makes sure that the parameters are the correct format for the error routine RPG program to receive them
	 * @see Prepare-Error-Parms in ZHRI100A.SQR
	 * @param commonParameters
	 * @return errorParameterString
	 */
	public static String composeErrorParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("*** ZHRI100A.composeErrorParameterString() ***");
		String blankSpaceParameter = " ";
		String criticalFlagYN = (Boolean)parameterMap.get("criticalFlag") != null && (Boolean)parameterMap.get("criticalFlag") ? "Y" : "N";
		Calendar now = Calendar.getInstance();
		//format date to YYYYMMDD
		String errorDateParameter =
				String.format("%04d", now.get(Calendar.YEAR)) + String.format("%02d", now.get(Calendar.MONTH)) + String.format("%02d", now.get(Calendar.DATE));
		//format time to HHMMSS
		String errorTimeParameter = 
				String.format("%02d", now.get(Calendar.HOUR_OF_DAY)) + String.format("%02d", now.get(Calendar.MINUTE)) + String.format("%02d", now.get(Calendar.SECOND));
		//TODO: What should this value really be called, and when is it not 'Y'??
		String yesOrNoParameter = "Y";
		//error message parameter must be 75 characters long
		String errorMessageParameter = String.format("%1$-75s", (String)parameterMap.get("errorMessageParameter"));
		String operatorIdParameter = (String)parameterMap.get("operatorId");
		if(operatorIdParameter != null) {
			operatorIdParameter = operatorIdParameter.toUpperCase();
			if(operatorIdParameter.startsWith("E")) {
				operatorIdParameter = operatorIdParameter.substring(1); //strip the E off of the front of the employee ID
			}
		}
		String errorParameterString = "'" + (String)parameterMap.get("errorProgramParameter") + "' "
					+ "'" + (String)parameterMap.get("employeeId") + "' " 
					+ "'" + (BigDecimal)parameterMap.get("effectiveSequence") + "' "
					+ "'" + blankSpaceParameter + "' " 
					+ "'" + errorMessageParameter + "' "
					+ "'" + criticalFlagYN + "' " 
					+ "'" + errorDateParameter + "' "
					+ "'" + errorTimeParameter + "' " 
					+ "'" + operatorIdParameter + "' "
					+ "'" + yesOrNoParameter + "'";
		return errorParameterString;
	}

//	/**
//	 * Executes a rexec command on the AS400.
//	 * Call System Using $Command #Status Wait
//	 * Execute the command that was built on the command waiting until completion //TODO
//	 * @param commandString
//	 * @return 0 if success, non-zero if error
//	 */
//	public static Integer doRexec(String commandString) {
//		System.out.println("*** ZHRI100A.executeCommandRexec ***");
//		System.out.println("$Command=> " + commandString);
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss.SSSSSS_a");
//		String currentDate = sdf.format(new Date()).toUpperCase();
//		System.out.println("Calling Command at: " + currentDate);
//		as400Rexec(commandString);
//	    return 0;
//	}

	/**
	 * Opens a rexec connection to the AS400 server and executes a command.
	 * @param commandString
	 * @return
	 */
	public static Integer doRexec(String commandString) {
		System.out.println("*** ZHRI100A.doRexec ***");		
	    RExecClient client = new RExecClient();
//		String remoteServerHostName = ServerProperties.getRemoteServerHostName();
//		String remoteServerUsername = ServerProperties.getRemoteServerUsername();
//		String remoteServerPassword = ServerProperties.getRemoteServerPassword();
		String remoteServerHostName = "dev.corp.erac.com";
		String remoteServerUsername = "PSHRINT";
		String remoteServerPassword = "SMRHET01";
		///QSYS.LIB/EHRHRMS06#.LIB/HRZ*.PGM

//	    commandString = "CALL EHRHRMS06#/HRZ102A PARM('840S4' '06' '20' '2017' '' '' '' 'V' 'O' '349NV' 'VOLUN  DISSATISFIED WHOURS         ')";
//	    commandString = "CALL EHRHRMS06#/HRZ100A PARM('HRZ102A' '859V1' '0' ' ' 'Error Message                                                              ' 'N' '20170529' '112649' '859V1' 'Y')";
		InputStream inputStream = null;
        try {
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
	    return 0;
	}
	
	/**
	 * Copies from a remote input stream and output stream to a local input stream and output stream.
	 * @param remoteInput
	 * @param remoteOutput
	 * @param localInput
	 * @param localOutput
	 */
	public static final void readWrite(InputStream remoteInput, OutputStream remoteOutput, InputStream localInput, OutputStream localOutput) {
		System.out.println("********** ZHRI100A.readWrite");
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
        
	/**
	 * 
	 * @param parameterMap
	 * @return completionStatus
	 */
    public static String doCommand(HashMap<String, Object> parameterMap) {
		System.out.println("********** ZHRI100A.doCommand");
		String commandString = ZHRI100A.composeCommandString(parameterMap);
		System.out.println("$Command=> " + commandString);
		System.out.println("Calling Command at: " + (new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss.SSSSSS_a")).format(new Date()).toUpperCase());
		Integer status = doRexec(commandString);
		//IF (#Status = 0)  //TODO
		if(status == 0) { //no error returned from process
			//completed normally
			return "C";
		}
		else { //!error
			parameterMap.put("errorProgramParameter", "ZHRI100A");
			parameterMap.put("errorMessageParameter", "Error executing Call System command, contact HR-PeopleSoft On-Call");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
		}
		return "E";
    }
	
}

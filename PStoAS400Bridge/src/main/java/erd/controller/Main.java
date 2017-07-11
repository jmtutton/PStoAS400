package erd.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
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
import erd.model.ServerProperties;
import erd.model.TempMast;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.net.bsd.RExecClient;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.commons.io.IOUtils;
/**
 * This is the main process controlling class for the PeopleSoft to AS400 Data Bridge
 * @see ZHRI100A.SQR peoplecode file
 * @author John Tutton john@tutton.net
 */
public class Main {
	public static final Boolean DEBUG = true;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	public static void main() {
		Configurator.setRootLevel(Level.DEBUG);
		logger.debug("main() ***");

		try {
			initializeServerProperties();
			processMain();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is the main process controlling method.
	 * @see Process-Main in ZHRI100A.SQR
	 * @throws InterruptedException 
	 */
	public static void processMain() throws InterruptedException {
		logger.debug("processMain() ***");
		Boolean runFlag = true;
		while(runFlag == true) { //never ending loop
			List<PszTriggerSuperclass> triggerList = PszTriggerSuperclass.findPending();
			//****************************************************************************************************
			//TODO: remove, just for testing
//			List<PszTriggerEmployee> triggerList = PszTriggerEmployee.findByCompletionStatusAndProcessName("P", "ZHRI102A");
//			List<PszTriggerNonPerson> triggerList = PszTriggerNonPerson.findByCompletionStatusAndProcessName("P", "ZHRI102A");
//			List<PszTriggerEmployee> triggerList = Arrays.asList(PszTriggerEmployee.createMockTriggerForNonPersonTermination());
			triggerList = Arrays.asList(PszTriggerNonPerson.createMockTriggerForNonPersonTermination());
			//****************************************************************************************************
			for(PszTriggerSuperclass trigger : triggerList) {
				trigger = checkTriggerRecord(trigger);
				if("P".equalsIgnoreCase(trigger.getCompletionStatus())) {
					trigger.setCompletionStatus(callPrograms(trigger));
				}
				//update trigger record
				int numberOfRecordsUpdated;
				if(trigger instanceof PszTriggerEmployee) {
					numberOfRecordsUpdated = PszTriggerEmployee.setCompletionStatusBySequenceNumber(trigger.getCompletionStatus(), trigger.getSequenceNumber());
				}
				else {
					numberOfRecordsUpdated = PszTriggerNonPerson.setCompletionStatusBySequenceNumber(trigger.getCompletionStatus(), trigger.getSequenceNumber());
				}
				logger.debug("numberOfRecordsUpdated: " + numberOfRecordsUpdated);
			}
			//sleep for 15 seconds (15000 milliseconds)
			Thread.sleep(15000);
			//****************************************************************************************************
			//TODO: REMOVE
			if(DEBUG) runFlag = false; //*** I put this here for testing, so it stops after one iteration.
		}
	}
	
	/**
	 * Sets the values common to all processes in a HashMap that shares the values across the application. 
	 * @param trigger
	 * @return parameterMap
	 */
	public static HashMap<String, Object> parameterizeTriggerFields(PszTriggerSuperclass trigger) {
		logger.debug("parameterizeTriggerFields() ***");
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
			logger.info("poiFlag: " + true);
			logger.info("eidIndexNumber: " + ((PszTriggerNonPerson)trigger).getEidIndexNumber());
			parameterMap.put("poiFlag", true);
			parameterMap.put("eidIndexNumber", ((PszTriggerNonPerson)trigger).getEidIndexNumber());
			logger.info("parameterMap.eidIndexNumber: " + parameterMap.get("eidIndexNumber"));
		}
		return parameterMap;
	}

	/**
	 * Call-Programs from ZHRI100A.SQR
	 * Subroutine will call appropriate programs
	 * @param trigger
	 * @return
	 */
	public static String callPrograms(PszTriggerSuperclass trigger) {
		HashMap<String, Object> parameterMap = parameterizeTriggerFields(trigger);
		logger.debug("callPrograms() ***");
		String completionStatus = (String)parameterMap.get("completionStatus");
		switch((String)parameterMap.get("processName")) {
		case "ZHRI101A": //process to hire employee
			parameterMap.put("processName", "HRZ101A");
			parameterMap.put("hireRehireFlag",  "H");
			completionStatus = new EmployeeNewHireRehire().doProcess(parameterMap);
			break;
		case "ZHRI102A": //process to terminate an employee
			parameterMap.put("processName", "HRZ102A");
			completionStatus = new EmployeeTermination().doProcess(parameterMap);
			break;
		case "ZHRI104A": //process for employee job profile change
			parameterMap.put("processName", "HRZ104A");
			completionStatus = new EmployeeJobProfileChange().doProcess(parameterMap);
			break;
		case "ZHRI105A": //process for employee demographics change
			parameterMap.put("processName", "HRZ105A");
			completionStatus = new EmployeeDemographicChange().doProcess(parameterMap);
			break;
		case "ZHRI106A": //process for employee rehire
			//process combined with new hire process, ZHRI101A
			parameterMap.put("processName", "HRZ101A");
			parameterMap.put("hireRehireFlag",  "R");
			completionStatus =  new EmployeeNewHireRehire().doProcess(parameterMap);
			break;
		case "ZHRI107A": //process for converting employee dates
			parameterMap.put("processName", "HRZ107A");
			completionStatus = new EmployeeDateChange().doProcess(parameterMap);
			break;
		case "ZHRI109A": //process for employee group transfer
			parameterMap.put("processName", "HRZ109A");
			completionStatus = new EmployeeGroupTransfer().doProcess(parameterMap);
			break;
		case "ZHRI201A": //process for non-person new hire
			parameterMap.put("processName", "HRZ201A");
			parameterMap.put("hireRehireFlag",  "H");
			completionStatus = new NonPersonNewHireRehire().doProcess(parameterMap);
			break;
		case "ZHRI202A": //process for non-person termination
			parameterMap.put("processName", "HRZ202A");
			completionStatus = new NonPersonTermination().doProcess(parameterMap);
			break;
		case "ZHRI205A": //process for non-person demographics change
			parameterMap.put("processName", "HRZ205A");
			completionStatus = new NonPersonDemographicChange().doProcess(parameterMap);
			break;
		case "ZHRI206A": //process for non-person rehire
			//process combined with new hire process, ZHRI201A
			parameterMap.put("processName", "HRZ201A");
			parameterMap.put("hireRehireFlag",  "R");
			completionStatus = new NonPersonNewHireRehire().doProcess(parameterMap);
			break;
		case "ZHRI101D": //row deleted on hire
			parameterMap.put("errorProgramParameter", "HRZ101A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the hire process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		case "ZHRI102D": //row deleted on term
			parameterMap.put("errorProgramParameter", "HRZ102A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the termination process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		case "ZHRI104D": //row deleted on job status change
			parameterMap.put("errorProgramParameter", "HRZ104A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the job profile process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		case "ZHRI105D": //row deleted on demographics change
			parameterMap.put("errorProgramParameter", "HRZ105A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the demographics process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		case "ZHRI106D": //row deleted on rehire
			parameterMap.put("errorProgramParameter", "HRZ101A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the re-hire process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		case "ZHRI107D": //row deleted on the dates process
			parameterMap.put("errorProgramParameter", "HRZ107A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the dates process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		case "ZHRI109D": //row deleted on the group transfer process
			parameterMap.put("errorProgramParameter", "HRZ109A");
			parameterMap.put("errorMessageParameter", "A row was deleted on the group transfer process");
			parameterMap.put("criticalFlag", true);
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", false);
			completionStatus = "C";
			break;
		default: //ERROR
			//set completion status to E to prevent re-processing and to mark the record as error
			completionStatus = "E";
			break;
		}
		return completionStatus;
	}

	/**
	 * Composes the error command and executes it on the AS400.
	 * @see Call-Error-Routine in ZHRI100A.SQR
	 * @param processName
	 * @param commonParameters
	 */
	public static void doErrorCommand(HashMap<String, Object> parameterMap) {
		logger.debug("doErrorCommand() ***");
		String originalProcessName = (String)parameterMap.get("processName");
		parameterMap.put("parameterString", composeErrorParameterString(parameterMap));
		if((Boolean)parameterMap.get("poiFlag")) {
			parameterMap.get("poiFlag");
			parameterMap.put("processName", "HRZ210A");
		}
		else {
			parameterMap.put("processName", "HRZ110A");
		}
		doCommand(parameterMap);
		parameterMap.put("processName", originalProcessName);
	}

	/**
	 * This routine gets the legacy employee ID from the cross reference table
	 * @see Get-OprId in ZHRI100A.SQR
	 * @param commonParameters
	 * @param eidIndexNumber
	 * @return legacyEmployeeId
	 */
	public static String fetchLegacyEmployeeId(HashMap<String, Object> parameterMap) {
		logger.debug("fetchLegacyEmployeeId()");
		String employeeId;
		if((Boolean)parameterMap.get("poiFlag") == false) {
			parameterMap.put("eidIndexNumber", 0);
		}
		if((Integer)parameterMap.get("eidIndexNumber") == 0) {
			employeeId = CrossReferenceEmployeeId.findLegacyEmployeeIdByEmployeeId((String)parameterMap.get("employeeId"));
		}
		else {
			employeeId = CrossReferenceMultipleEmployeeId.findLegacyEmployeeIdByEmployeeIdAndSequence((String)parameterMap.get("employeeId"), (Integer)parameterMap.get("eidIndexNumber"));
		}
		if(employeeId == null || employeeId.isEmpty()) {
			employeeId = fetchNewLegacyEmployeeId(parameterMap);
		}
		return employeeId;
	}

	/**
	 * initializeServerProperties
	 * Sets the values for the remote AS400 server in a static class that shares the values across the application. 
	 */
	public static void initializeServerProperties() {
		logger.debug("initializeServerProperties() ***");
		String processName = "ZHRI100A";
		ServerProperties.setDbName(PsDbOwner.findDbName());
//		commonParameters.setOracleSystemId(System.getenv("ORACLE_SID")); //TODO: get IT Help to set this env var on dev and test machines
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
	 * This procedure will get the trigger data that needs to be interfaced
	 * @see Get-Trigger-Data in ZHRI100A.SQR
	 * @param trigger
	 * @return trigger with completionStatus set to "P" if trigger record is good to process
	 */
	public static PszTriggerSuperclass checkTriggerRecord(PszTriggerSuperclass trigger) {
		logger.debug("checkTriggerRecord() ***");
		if(trigger.getEmployeeId() == null || trigger.getEmployeeId().isEmpty()) {
			//set status to error
			trigger.setCompletionStatus("E");
		}
		else if(PsJob.employeeIsContractor(trigger.getEmployeeId())) {
			//don't process, but set status to complete, so this event doesn't come up again
			trigger.setCompletionStatus("C");
		}
		else { //not a contractor and not a blank EmplId
			//if this is an employee termination, check to see if there is a corresponding row in PsJob table
			if("ZHRI102A".equalsIgnoreCase(trigger.getProcessName())) {
				if(!PsJob.correspondingJobRecordExists(trigger.getEmployeeId(), trigger.getEffectiveDate(), trigger.getProcessName())) {
					//set status to complete, so this event doesn't come up again
					trigger.setCompletionStatus("C");
				}
			}
			else if("ZHRI101A".equalsIgnoreCase(trigger.getProcessName())) {
				if(!PszTriggerNonPerson.isPoiToEmpTransfer(trigger.getEmployeeId())) {
					//set status to wait
					trigger.setCompletionStatus("W");
				}
			}
		}
		return trigger;
	}
	
	/**
	 * Formulates legacy OprId from HR036P where HR036P.H36EM# = #wrk_emplid and HR036P.H36INX = #indexNum UNION
	 * @see Get-Legacy-OprId in ZHRI100A.SQR
	 * @param parameterMap
	 * @return legacyEmployeeId
	 */
	public static String fetchNewLegacyEmployeeId(HashMap<String, Object> parameterMap) {
		logger.debug("fetchNewLegacyEmployeeId() ***");
		String legacyEmployeeId = null;
		Integer employeeNumber = -1;
		try {
			employeeNumber = Integer.parseInt((String)parameterMap.get("employeeId"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if((Boolean)parameterMap.get("poiFlag") == false) {
			parameterMap.put("eidIndexNumber", 0);
		}
		logger.info("employeeNumber: " + employeeNumber);
		logger.info("eidIndexNumber: " + (Integer)parameterMap.get("eidIndexNumber"));
		HR036P hr036P = HR036P.findByEmployeeNumberAndIndexNumber(employeeNumber, (Integer)parameterMap.get("eidIndexNumber"));
    	if(hr036P != null) {
    		legacyEmployeeId = hr036P.getEmployeeId();
	    	if(legacyEmployeeId != null && legacyEmployeeId.length() > 5) {
	    		legacyEmployeeId = legacyEmployeeId.substring(0, 5);
	    	}
	    	CrossReferenceMultipleEmployeeId.saveNewLegacyEmployeeId((String)parameterMap.get("employeeId"), legacyEmployeeId, (Integer)parameterMap.get("eidIndexNumber"));
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
		logger.debug("composeCommandString() ***");
		String commandString = 
						"CALL " + ServerProperties.getAs400Library() + "/" 
								+ (String)parameterMap.get("processName") + " " 
								+ "PARM(" + (String)parameterMap.get("parameterString") + ")";
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
		logger.debug("composeErrorParameterString() ***");
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
					+ "'" + (BigInteger)parameterMap.get("effectiveSequence") + "' "
					+ "'" + blankSpaceParameter + "' " 
					+ "'" + errorMessageParameter + "' "
					+ "'" + criticalFlagYN + "' " 
					+ "'" + errorDateParameter + "' "
					+ "'" + errorTimeParameter + "' " 
					+ "'" + operatorIdParameter + "' "
					+ "'" + yesOrNoParameter + "'";
		return errorParameterString;
	}

	/**
	 * Opens a rexec connection to the AS400 server and executes a command.
	 * @param commandString
	 * @return status
	 */
	public static Integer doRexec(String commandString) {
		logger.debug("doRexec() ***");
		Integer status = 0;
	    RExecClient client = new RExecClient();
		String remoteServerHostName = ServerProperties.getRemoteServerHostName();
		String remoteServerUsername = ServerProperties.getRemoteServerUsername();
		String remoteServerPassword = ServerProperties.getRemoteServerPassword();

		InputStream inputStream = null;
        try {
			client.connect(remoteServerHostName);
            if (!client.isConnected()) {
                System.err.println("The client is not connected to " + remoteServerHostName);
                status = -1; //TODO: enumeration of status codes? 
            }
            try {
                client.rexec(remoteServerUsername, remoteServerPassword, commandString);
            }
            catch(IOException e) {
                e.printStackTrace();
                System.err.println("Could not execute command.");
                status = -2;
            }
            catch (Exception e) {
    			e.printStackTrace();
                status = -3;
            }
            readWrite(client.getInputStream(), client.getOutputStream(), System.in, System.out);
        } 
        catch (SocketException e1) {
			e1.printStackTrace();
            status = -4;
		} 
        catch (IOException e1) {
			e1.printStackTrace();
            status = -5;
		}
        catch (Exception e1) {
			e1.printStackTrace();
            status = -6;
		}
        finally {
            IOUtils.closeQuietly(inputStream);
            try {
				client.disconnect();
			} 
            catch (IOException e) {
				e.printStackTrace();
	            status = -7;
			}
            catch (Exception e) {
    			e.printStackTrace();
                status = -8;
    		}
        }
	    return status;
	}
	
	/**
	 * Copies from a remote input stream and output stream to a local input stream and output stream.
	 * @param remoteInput
	 * @param remoteOutput
	 * @param localInput
	 * @param localOutput
	 */
	public static final Integer readWrite(InputStream remoteInput, OutputStream remoteOutput, InputStream localInput, OutputStream localOutput) {
		logger.debug("readWrite() ***");
		Integer status = 0;
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
			status = -10;
		}
        catch (Exception e) {
			e.printStackTrace();
			status = -11;
		}
		return status;
	}
        
	/**
	 * @see Call-System in ZHRI100A.SQR
	 * @param parameterMap
	 * @return completionStatus
	 */
    @SuppressWarnings("unchecked")
	public static String doCommand(HashMap<String, Object> parameterMap) {
		logger.debug("doCommand() ***");
		String commandString = Main.composeCommandString(parameterMap);
		//open log file for append  //TODO
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("command.log", true)))) {
			//write to log file  //TODO
			logger.info("$Command=> " + commandString);
			logger.info("Calling Command at: " + (new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss.SSSSSS_a")).format(new Date()).toUpperCase());
		}
		catch (IOException e) {
		    System.err.println(e);
		}
		finally {
			//close log file
		}
		Integer status = doRexec(commandString);
		if(status == 0) { //no error returned from process; completed normally
			//write to log file
			logger.info("$Command=> " + commandString);
			logger.info((List<String>)parameterMap.get("parameterNameList"));
			Calendar today = Calendar.getInstance();
			logger.info("Tempmast Record: " + TempMast.findByEmployeeIdAndChangedDateAndChangedByProgramAndChangedByEmployeeId(
					(String)parameterMap.get("employeeId"), 
					String.format("%04d", today.get(Calendar.YEAR)) + String.format("%02d", today.get(Calendar.MONTH)) + String.format("%02d", today.get(Calendar.DATE)), 
					(String)parameterMap.get("processName"), 
					(String)parameterMap.get("operatorId")));
			logger.info("HR100P Record: " + TempMast.findByEmployeeIdAndChangedDateAndChangedByProgramAndChangedByEmployeeId(
					(String)parameterMap.get("employeeId"), 
					String.format("%04d", today.get(Calendar.YEAR)) + String.format("%02d", today.get(Calendar.MONTH)) + String.format("%02d", today.get(Calendar.DATE)), 
					(String)parameterMap.get("processName"), 
					(String)parameterMap.get("operatorId")));
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
	
	/**
	 * Composes the string of process parameter values.
     * @param parameterMap
     * @return parameterString
     */
	public static String composeParameterString(HashMap<String, Object> parameterMap) {
		logger.debug("composeParameterString() ***");
		String parameterString = "";
		List<?> parameterNameList = (List<?>)parameterMap.get("parameterNameList");
		for(Object key : parameterNameList) {
			String value = (String)parameterMap.get((String)key);
			value = value != null ? value : "";
			parameterString += "'" + value + "' ";
		}
		return parameterString.trim();
	}
	
}

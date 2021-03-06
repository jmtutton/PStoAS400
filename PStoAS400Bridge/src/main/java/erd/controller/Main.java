package erd.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

import java.io.IOException;
import org.apache.commons.net.bsd.RExecClient;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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
		String completionStatus = null;
		while(runFlag == true) { //never ending loop
			List<PszTriggerSuperclass> triggerList = PszTriggerSuperclass.findPending();
			//****************************************************************************************************
			//TODO: remove, just for testing
//			List<PszTriggerEmployee> triggerList = PszTriggerEmployee.findByCompletionStatusAndProcessName("P", "ZHRI102A");
//			List<PszTriggerNonPerson> triggerList = PszTriggerNonPerson.findByCompletionStatusAndProcessName("P", "ZHRI102A");
//			List<PszTriggerEmployee> triggerList = Arrays.asList(PszTriggerEmployee.createMockTriggerForNonPersonTermination());
//			triggerList = Arrays.asList(PszTriggerNonPerson.createMockTriggerForNonPersonTermination());
			triggerList = Arrays.asList(PszTriggerEmployee.createMockTriggerForEmployeeNewHire());
			//****************************************************************************************************
			for(PszTriggerSuperclass trigger : triggerList) {
				trigger = checkTriggerRecord(trigger);
				if("P".equalsIgnoreCase(trigger.getCompletionStatus())) {
					completionStatus = callPrograms(trigger);
					trigger.setCompletionStatus(completionStatus);
				}
				//update trigger record
				trigger.update();
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
		parameterMap.put("blankSpace", "");
		parameterMap.put("errorMessage", "");
		parameterMap.put("criticalFlag", "N");
		String processName = trigger.getProcessName() != null ? trigger.getProcessName().trim().toUpperCase() : trigger.getProcessName();
		parameterMap.put("processName", processName);
		String employeeId = trigger.getEmployeeId();
		employeeId = employeeId != null ? employeeId.trim().toUpperCase() : employeeId;
		parameterMap.put("employeeId", employeeId);
		String operatorId = trigger.getOperatorId();
		operatorId = operatorId != null ? operatorId.trim().toUpperCase() : operatorId;
		parameterMap.put("operatorId", operatorId);
		parameterMap.put("effectiveDate", trigger.getEffectiveDate());
		parameterMap.put("effectiveSequence", trigger.getEffectiveSequence());
		String completionStatus = trigger.getCompletionStatus();
		completionStatus = completionStatus != null ? completionStatus.trim().toUpperCase() : completionStatus;
		parameterMap.put("completionStatus", completionStatus);
		if(trigger instanceof PszTriggerEmployee) {
			parameterMap.put("poiFlag", false);
		}
		else if(trigger instanceof PszTriggerNonPerson) {
			parameterMap.put("poiFlag", true);
			parameterMap.put("eidIndexNumber", ((PszTriggerNonPerson)trigger).getEidIndexNumber());
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
			parameterMap.put("errorProgram", "HRZ101A");
			parameterMap.put("errorMessage", "A row was deleted on the hire process");
			parameterMap.put("criticalFlag", "Y");
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", "N");
			completionStatus = "C";
			break;
		case "ZHRI102D": //row deleted on term
			parameterMap.put("errorProgram", "HRZ102A");
			parameterMap.put("errorMessage", "A row was deleted on the termination process");
			parameterMap.put("criticalFlag", "Y");
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", "N");
			completionStatus = "C";
			break;
		case "ZHRI104D": //row deleted on job status change
			parameterMap.put("errorProgram", "HRZ104A");
			parameterMap.put("errorMessage", "A row was deleted on the job profile process");
			parameterMap.put("criticalFlag", "Y");
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", "N");
			completionStatus = "C";
			break;
		case "ZHRI105D": //row deleted on demographics change
			parameterMap.put("errorProgram", "HRZ105A");
			parameterMap.put("errorMessage", "A row was deleted on the demographics process");
			parameterMap.put("criticalFlag", "Y");
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", "N");
			completionStatus = "C";
			break;
		case "ZHRI106D": //row deleted on rehire
			parameterMap.put("errorProgram", "HRZ101A");
			parameterMap.put("errorMessage", "A row was deleted on the re-hire process");
			parameterMap.put("criticalFlag", "Y");
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", "N");
			completionStatus = "C";
			break;
		case "ZHRI107D": //row deleted on the dates process
			parameterMap.put("errorProgram", "HRZ107A");
			parameterMap.put("errorMessage", "A row was deleted on the dates process");
			parameterMap.put("criticalFlag", "Y");
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", "N");
			completionStatus = "C";
			break;
		case "ZHRI109D": //row deleted on the group transfer process
			parameterMap.put("errorProgram", "HRZ109A");
			parameterMap.put("errorMessage", "A row was deleted on the group transfer process");
			parameterMap.put("criticalFlag", "Y");
			doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", "N");
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
		Calendar now = Calendar.getInstance();
		String errorDate = //format date to YYYYMMDD
				String.format("%04d", now.get(Calendar.YEAR)) + String.format("%02d", now.get(Calendar.MONTH)) + String.format("%02d", now.get(Calendar.DATE));
		parameterMap.put("errorDate", errorDate);
		String errorTime =  //format time to HHMMSS
				String.format("%02d", now.get(Calendar.HOUR_OF_DAY)) + String.format("%02d", now.get(Calendar.MINUTE)) + String.format("%02d", now.get(Calendar.SECOND));
		parameterMap.put("errorTime", errorTime);
		parameterMap.put("yesOrNo", "Y"); //TODO: What should this value really be called, and when is it not 'Y'??
		String originalProcessName = (String)parameterMap.get("processName");
		parameterMap.put("processName", (Boolean)parameterMap.get("poiFlag") ? "HRZ210A" : "HRZ110A");
		parameterMap.put("parameterNameList", getErrorParameterNameList());
		parameterMap.put("parameterString", composeParameterString(parameterMap));
		doCommand(parameterMap);
		parameterMap.put("processName", originalProcessName);
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
		//get server property values from the variables table
		ServerProperties.setRemoteServerHostName(PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, ServerProperties.getDbName(), "RMTSVR"));
		ServerProperties.setAs400Library(PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, ServerProperties.getDbName(), "AS400library"));
		ServerProperties.setRemoteServerUsername("PSHRINT");
		ServerProperties.setRemoteServerPassword("SMRHET01");
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
		else if(PsJob.isContractor(trigger.getEmployeeId())) {
			//if employee is a contractor, set status to complete so this event doesn't get processed
			trigger.setCompletionStatus("C");
		}
		else if("ZHRI102A".equalsIgnoreCase(trigger.getProcessName())) {
			//if there is not a corresponding row in PsJob table, set status to complete so this event doesn't doesn't get processed
			if(!PsJob.correspondingJobRecordExists(trigger.getEmployeeId(), trigger.getEffectiveDate(), trigger.getProcessName())) {
				trigger.setCompletionStatus("C");
			}
		}
		else if("ZHRI101A".equalsIgnoreCase(trigger.getProcessName())) {
			//if this is a POI to EMP transfer, set status to wait
			if(PszTriggerNonPerson.isPoiTermed(trigger.getEmployeeId())) {
				trigger.setCompletionStatus("W");
			}
		}
		return trigger;
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
	 * @return status
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
		//open log file for append
//		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("command.log", true)))) {
		try {
		    final Path path = Paths.get("command.log");
			//write to log file
			logger.info("$Command=> " + commandString);
		    Files.write(path, Arrays.asList("$Command=> " + commandString), StandardCharsets.UTF_8, Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
			logger.info("Calling Command at: " + (new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss.SSSSSS_a")).format(new Date()).toUpperCase());
		    Files.write(path, Arrays.asList("Calling Command at: " + (new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss.SSSSSS_a")).format(new Date()).toUpperCase()), StandardCharsets.UTF_8, Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
		}
		catch (IOException e) {
		    System.err.println(e);
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
			parameterMap.put("errorProgram", "ZHRI100A");
			parameterMap.put("errorMessage", "Error executing Call System command, contact HR-PeopleSoft On-Call");
			parameterMap.put("criticalFlag", "Y");
			doErrorCommand(parameterMap);
		}
		return "E";
    }
	
	/**
	 * Composes the string of parameter values.
     * @param parameterMap
     * @return parameterString
     */
	public static String composeParameterString(HashMap<String, Object> parameterMap) {
		logger.debug("composeParameterString() ***");
		parameterMap = formatParameters(parameterMap);
		String parameterString = "";
		List<?> parameterNameList = (List<?>)parameterMap.get("parameterNameList");
		for(Object key : parameterNameList) {
			String value = (String)parameterMap.get((String)key);
			value = value != null ? value : "";
			parameterString += "'" + value + "' ";
		}
		return parameterString.trim();
	}
	
	/**
	 * Makes sure that the parameters are the correct format RPG program to receive them
     * @param parameterMap
     * @return parameterMap
     */
	public static HashMap<String, Object> formatParameters(HashMap<String, Object> parameterMap) {
		logger.debug("formatParameter() ***");
		parameterMap.put("errorMessage", String.format("%1$-75s", (String)parameterMap.get("errorMessage")));
		parameterMap.put("employeeId", StringUtils.leftPad((String)parameterMap.get("employeeId"), 5, "0"));//TODO: verify this is the correct format for the legacy employee ID
		BigInteger effectiveSequence = (BigInteger)parameterMap.get("effectiveSequence");
		parameterMap.put("effectiveSequence", effectiveSequence != null ? effectiveSequence.toString() : effectiveSequence);
		String operatorId = (String)parameterMap.get("operatorId");
		operatorId = operatorId != null && operatorId.startsWith("E") ? operatorId.substring(1).trim().toUpperCase() : operatorId;
		parameterMap.put("operatorId", operatorId);
		return parameterMap;
	}

	/**
	 * @see HR201-Build-Call-Statement in ZHRI201A.SQC
	 * @return list of parameter names for this process
	 */
	private static List<String> getErrorParameterNameList() {
		return Arrays.asList("errorProgram", "employeeId", "effectiveSequence", "blankSpace", 
				"errorMessage", "criticalFlag", "errorDate", "errorTime", "operatorId", "yesOrNo");
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
	    //if ID doesn't already exists, add the new employee as a PeopleSoft Operator
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
	 * This routine gets the legacy employee ID from the cross reference table.
	 * @see Get-OprId in ZHRI100A.SQR
	 * @param parameterMap
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
			employeeId = CrossReferenceMultipleEmployeeId.findLegacyEmployeeIdByEmployeeIdAndEidIndexNumber((String)parameterMap.get("employeeId"), (Integer)parameterMap.get("eidIndexNumber"));
		}
		if(employeeId == null || employeeId.isEmpty()) {
			employeeId = fetchNewLegacyEmployeeId(parameterMap);
		}
		employeeId = employeeId != null ? employeeId.trim().toUpperCase() : employeeId;
		return employeeId;
	}

}

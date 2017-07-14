package erd.controller;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Job Profile Change Process
 * @see ZHRI104A.SQC
 * @author John Tutton john@tutton.net
 */
public class EmployeeJobProfileChange {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	/**
	 * 
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("*** EmployeeJobProfileChange.doProcess()");
		//HR04-Initialize-Fields
		//HR04-Process-Main
		//HR04-Get-Job-Data
		//HR04-Massage-Data
		//HR04-Call-RPG
		//HR04-Get-Position
		//HR04-Get-Branch
		//HR04-Get-Group
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", Main.composeParameterString(parameterMap));
		return Main.doCommand(parameterMap);
	}
	
	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("*** EmployeeJobProfileChange.fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ109A");
		parameterMap.put("parameterNameList", getParameterNameList());
		return parameterMap;
		
	}
	
	/**
	 * @see HR04-Call-RPG in ZHRI104A.SQC
	 * @return list of parameter names for this process
	 */
	private static List<String> getParameterNameList() {
		return Arrays.asList("employeeId","operatorId",
				"branch", "department", "subdepartment",
				"position", "jobStatus", "workStatus",
				"unionFlag", "timeCardFlag", "effectiveDate");
	}

}

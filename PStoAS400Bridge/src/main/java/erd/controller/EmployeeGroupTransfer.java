package erd.controller;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Group Transfer Process
 * @see ZHRI109A.SQC
 * @author John Tutton john@tutton.net
 */
public class EmployeeGroupTransfer {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	/**
	 * 
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("*** EmployeeGroupTransfer.doProcess()");
		//HR09-Initialize-Fields
		//HR09-Process-Main
		//HR09-Get-Personal-Data
		//HR09-Get-job-data
		//HR09-Massage-Data
		//HR09-Call-RPG
		//HR09-Get-Group
		//HR09-Get-Branch
		//HR09-Get-Country
		//HR09-Get-Region
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", Main.composeParameterString(parameterMap));
		return Main.doCommand(parameterMap);
	}
	
	/**
	 * 
	 * @param parameterMap
	 * @return parameterMap
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("*** EmployeeGroupTransfer.fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ109A");
		parameterMap.put("parameterNameList", getParameterNameList());
		return parameterMap;
	}
	
	/**
	 * @see HR09-Call-RPG
	 * @return list of parameter names for this process
	 */
	private static List<String> getParameterNameList() {
		return Arrays.asList("employeeId", "operatorId", "employeeGroup", "employeeBranch", 
				"nationalIdCountry", "nationalId", "effectiveDate");
	}

}

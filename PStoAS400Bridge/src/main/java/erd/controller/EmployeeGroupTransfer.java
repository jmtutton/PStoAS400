package erd.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Group Transfer Process
 * @see ZHRI109A.SQC
 * @author John Tutton john@tutton.net
 */
public class EmployeeGroupTransfer {

	/**
	 * 
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeGroupTransfer.doProcess()");
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", ZHRI100A.composeParameterString(parameterMap));
		return ZHRI100A.doCommand(parameterMap);
	}
	
	/**
	 * 
	 * @param parameterMap
	 * @return parameterMap
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeGroupTransfer.fetchProcessParameters()");
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

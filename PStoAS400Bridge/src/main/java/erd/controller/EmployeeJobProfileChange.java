package erd.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * ZHRI104A - Job Profile Change
 * @author John Tutton john@tutton.net
 *
 */

public class EmployeeJobProfileChange {

	/**
	 * 
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeJobProfileChange.doProcess()");
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", ZHRI100A.composeParameterString(parameterMap));
		return ZHRI100A.doCommand(parameterMap);
	}
	
	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeJobProfileChange.fetchProcessParameters()");
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
				"employeeBranch", "employeeDepartment", "employeeSubDepartment",
				"employeePosition", "employeeJobStatus", "employeeWorkStatus",
				"employeeUnionFlag", "employeeTimeCardFlag", "effectiveDate");
	}

}

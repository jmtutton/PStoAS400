package erd.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * ZHRI205A - Contingent Employee and Multiple EID Demographic Change Process
 * @author John Tutton john@tutton.net
 *
 */

public class NonPersonDemographicChange {
	
	/**
	 * 
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonDemographicChange.doProcess()");
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
		System.out.println("*** NonPersonDemographicChange.fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ205A");
		parameterMap.put("parameterNameList", getParameterNameList());
		return parameterMap;
		
	}

	/**
	 * @see HR205-Call-RPG-Program
	 * @return list of parameter names for this process
	 */
	private static List<String> getParameterNameList() {
		return Arrays.asList("operatorId", "employeeId",
				"employeeGroup", "employeeBranch",
				"employeeLastName", "employeeFirstName", "employeeMiddleName", "employeeNickname",
				"employeeGender", "serviceDate", "employeeDepartmentId",
				"employeePosition", "referralSource", "employeeAddress", "employeeCity");
	}
	
}

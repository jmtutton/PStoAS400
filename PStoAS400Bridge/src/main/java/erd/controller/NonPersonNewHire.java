package erd.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Contingent Employee and Multiple EID New Hire Process
 * @see ZHRI201A.SQC
 * @author John Tutton john@tutton.net
 */
public class NonPersonNewHire {
	
	/**
	 * 
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonNewHire.doProcess()");
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", ZHRI100A.composeParameterString(parameterMap));
		return ZHRI100A.doCommand(parameterMap);
	}
	
	/**
	 * 
	 * @param parameterMap
	 * @return appended parameterMap
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonNewHire.fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ201A");
		parameterMap.put("parameterNameList", getParameterNameList());
		return parameterMap;
	}

	/**
	 * @see HR201-Build-Call-Statement in ZHRI201A.SQC
	 * @return list of parameter names for this process
	 */
	private static List<String> getParameterNameList() {
		return Arrays.asList("operatorId", "employeeId", "indexNumber", 
				"group", "branch",
				"lastName", "firstName", "middleInitial", "nickname",
				"gender", "serviceDate", "department", "position",
				"referralSource", "address", "city", "hireRehireFlag");
	}

}

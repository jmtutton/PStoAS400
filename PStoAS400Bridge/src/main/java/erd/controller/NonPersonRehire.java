package erd.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Contingent Employee and Multiple EID Rehire
 * @see ZHRI206A.SQC
 * @author John Tutton john@tutton.net
 *
 */
public class NonPersonRehire {
	
	/**
	 * 
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonRehire.doProcess()");
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", ZHRI100A.composeParameterString(parameterMap));
		return null;
	}
	
	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonRehire.fetchProcessParameters()");
		parameterMap.put("parameterNameList", getParameterNameList());
		return null;
	}

	/**
	 * @see ZHRI206A.SQC
	 * @return list of parameter names for this process
	 */
	//TODO
	private static List<String> getParameterNameList() {
		System.out.println("*** NonPersonRehire.getParameterNameList()");
		return Arrays.asList("operatorId", "employeeId", "indexNumber", 
				"group", "branch",
				"lastName", "firstName", "middleInitial", "nickname",
				"gender", "serviceDate", "department", "position",
				"referralSource", "address", "city", "hireRehireFlag");
	}
}

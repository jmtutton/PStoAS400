package erd.controller;

import java.util.HashMap;

/**
 * ZHRI109A - Group Transfer
 * @author John Tutton john@tutton.net
 *
 */
public class EmployeeGroupTransfer {

	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeGroupTransfer.doProcess() ***");
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", composeParameterString(parameterMap));
		return ZHRI100A.doCommand(parameterMap);
	}
	
	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeGroupTransfer.fetchProcessParameters() ***");
		parameterMap.put("errorProgramParameter", "HRZ109A");
		return parameterMap;
	}
	
	/**
	 * 
	 * @param parameterMap
	 */
	private String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeGroupTransfer.composeParameterString() ***");
		String paramaterString = "'" + parameterMap.get("employeeId") + "' " //$LegEmplid 
				+ "'" + parameterMap.get("operatorId") + "' " //$LegUserEmplid
				+ "'" + parameterMap.get("employeeGroup") + "' " //$LegGroup
				+ "'" + parameterMap.get("employeeBranch") + "' " //$LegBranch
				+ "'" + parameterMap.get("nationalIdCountry") + "' " //$LegCountryCode
				+ "'" + parameterMap.get("nationalId") + "' " //$LegNid
				+ "'" + parameterMap.get("effectiveDate") + "'"; //$LegEffdt
		return paramaterString;
	}

}

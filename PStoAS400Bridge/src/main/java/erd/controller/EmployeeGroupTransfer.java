package erd.controller;

import java.util.HashMap;

/**
 * ZHRI109A - Group Transfer
 * @author John Tutton john@tutton.net
 *
 */
public class EmployeeGroupTransfer {

	public String processEmployeeGroupTransfer(HashMap<String, Object> parameterMap) {
		parameterMap = fetchProcessParameters(parameterMap);
		composeParameterString(parameterMap);
		return null;
	}
	
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		return null;
	}
	
	/**
	 * 
	 * @param processParameters
	 */
	private String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("********** composeParameterStringForHrz109AProcess");
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

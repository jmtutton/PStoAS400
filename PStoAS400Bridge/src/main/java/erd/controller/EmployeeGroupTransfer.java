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
		//'PARM('''                     ||
		//$LegEmplid                    ||
		//''' '''                       ||
		//$LegUserEmplid                ||
		//''' '''                       ||
		//$LegGroup                     ||
		//''' '''                       ||
		//$LegBranch                    ||
		//''' '''                       ||
		//$LegCountryCode               ||
		//''' '''                       ||
		//$LegNid                       ||
		//''' '''                       ||
		//$LegEffdt                     ||
		//''')"' 
		String paramaterString = "'" + parameterMap.get("employeeId") + "' "
				+ "'" + parameterMap.get("operatorId") + "' "
				+ "'" + parameterMap.get("employeeGroup") + "' "
				+ "'" + parameterMap.get("employeeBranch") + "' "
				+ "'" + parameterMap.get("nationalIdCountry") + "' "
				+ "'" + parameterMap.get("nationalId") + "' "
				+ "'" + parameterMap.get("effectiveDate") + "'";
		return paramaterString;
	}

}

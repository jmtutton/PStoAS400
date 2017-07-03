package erd.controller;

import java.util.HashMap;

/**
 * ZHRI104A - Job Profile Change
 * @author John Tutton john@tutton.net
 *
 */

public class EmployeeJobProfileChange {

	public String processEmployeeJobProfileChange(HashMap<String, Object> parameterMap) {
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
		//'PARM('''                   ||
		//$LegEmplid                    ||
		//''' '''                   ||
		//$LegUserEmplid                ||
		//''' '''                   ||
		//$LegBranch                    ||
		//''' '''                   ||
		//$LegDepartment                ||
		//''' '''                   ||
		//$LegSubDepartment             ||
		//''' '''                   ||
		//$LegPosition                  ||
		//''' '''                   ||
		//$LegJobStatus                 ||
		//''' '''                   ||
		//$LegWorkStatus                ||
		//''' '''                   ||
		//$LegUnionFlag                 ||
		//''' '''                   ||
		//$LegTimeCardFlag              ||
		//''' '''                   ||
		//$LegEffdt                     ||
		//''')"'
//		String paramaterString = "'" + processParameters.getEmployeeId() + "' "
//				+ "'" + processParameters.getOperatorId() + "' "
//				+ "'" + processParameters.getEmployeeGroup() + "' "
//				+ "'" + processParameters.getEmployeeBranch() + "' "
//				+ "'" + processParameters.getNationalIdCountry() + "' "
//				+ "'" + processParameters.getNationalId() + "' "
//				+ "'" + processParameters.getEffectiveDate() + "'";
//		return paramaterString;
		return null;
	}

}

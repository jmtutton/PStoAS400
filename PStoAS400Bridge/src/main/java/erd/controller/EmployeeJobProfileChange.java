package erd.controller;

import java.util.HashMap;

/**
 * ZHRI104A - Job Profile Change
 * @author John Tutton john@tutton.net
 *
 */

public class EmployeeJobProfileChange {

	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeJobProfileChange.doProcess() ***");
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
		System.out.println("*** EmployeeJobProfileChange.fetchProcessParameters() ***");
		parameterMap.put("errorProgramParameter", "HRZ109A");
		return parameterMap;
		
	}
	
	/**
	 * 
	 * @param parameterMap
	 */
	private String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeJobProfileChange.composeParameterString() ***");
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

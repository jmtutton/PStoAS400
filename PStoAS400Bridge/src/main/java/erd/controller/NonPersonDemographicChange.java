package erd.controller;

import java.util.HashMap;

/**
 * ZHRI205A - Contingent Employee and Multiple EID Demographic Change Process
 * @author John Tutton john@tutton.net
 *
 */

public class NonPersonDemographicChange {
	
	public String processNonPersonDemographicChange(HashMap<String, Object> parameterMap) {
		System.out.println("********** HR205_processMain()");
		parameterMap = fetchProcessParameters(parameterMap);
		composeParameterString(parameterMap);
		return null;
	}
	
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("********** fetchProcessParameters");
		return null;
		
	}

	/**
	 * 
	 * @param parameterMap
	 */
	private String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("********** composeParameterString");
		//Let $Part2 = 'Parm('''                 ||
		// $PSAuditemp               ||
		// ''' '''                   ||       
		// $PSEmpl                   ||
		// ''' '''                   ||
		// $PSGroup                  ||
		// ''' '''                   ||
		// $PSbranch                 ||
		// ''' '''                   ||
		// $PSLName                  ||
		// ''' '''                   ||
		// $PSFName                  ||
		// ''' '''                   ||
		// $PSMName                  ||
		// ''' '''                   ||
		// $PSNickname               ||
		// ''' '''                   ||
		// $PSGender                 ||
		// ''' '''                   ||
		// $LegServiceDate           ||
		// ''' '''                   ||
		// $PSDeptid                 ||
		// ''' '''                   ||
		// $PSPosition               ||
		// ''' '''                   ||
		// $PSReferral_Source        ||
		// ''' '''                   ||
		// $PSAddress                ||
		// ''' '''                   ||
		// $PSCity                   ||
		// ''')" '
//		String paramaterString = "'" + parameterMap.getEmployeeId() + "' "
//				+ "'" + parameterMap.getOperatorId() + "' "
//				+ "'" + parameterMap.getEmployeeGroup() + "' "
//				+ "'" + parameterMap.getEmployeeBranch() + "' "
//				+ "'" + parameterMap.getNationalIdCountry() + "' "
//				+ "'" + parameterMap.getNationalId() + "' "
//				+ "'" + parameterMap.getEffectiveDate() + "'";
//		return paramaterString;
		return null;
	}
	
}

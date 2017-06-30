package erd.controller;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.DemographicChangeProcessParameters;

/**
 * ZHRI205A - Contingent Employee and Multiple EID Demographic Change Process
 * @author John Tutton john@tutton.net
 *
 */

public class NonPersonDemographicChange {
	
	public String processNonPersonDemographicChange(CommonParameters commonParameters) {
		System.out.println("********** HR205_processMain()");
		DemographicChangeProcessParameters processParameters = fetchProcessParameters(commonParameters);
		composeParameterString(processParameters);
		return null;
	}
	
	private DemographicChangeProcessParameters fetchProcessParameters(CommonParameters commonParameters) {
		System.out.println("********** fetchProcessParameters");
		return null;
		
	}

	/**
	 * 
	 * @param processParameters
	 */
	private String composeParameterString(DemographicChangeProcessParameters processParameters) {
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

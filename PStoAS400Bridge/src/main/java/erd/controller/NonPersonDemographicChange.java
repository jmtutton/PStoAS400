package erd.controller;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.DemographicChangeParameters;
import erd.model.PszTriggerNonPerson;

/**
 * ZHRI205A - Contingent Employee and Multiple EID Demographic Change
 * @author John Tutton john@tutton.net
 *
 */

public class NonPersonDemographicChange {
	
	public String HR205_processMain(PszTriggerNonPerson trigger, CommonParameters commonParameters) {
		System.out.println("********** HR205_processMain()");
		commonParameters.setPoiFlag(true);
		HR205_initializeFields();
		HR205_callSystem(commonParameters);
		return null;
	}
	
	private void HR205_initializeFields() {
		System.out.println("********** HR205_initializeFields");
		
	}

	private String HR205_callSystem(CommonParameters commonParameters) {
		System.out.println("********** HR205_callSystem()");
		return null;
		
	}

	/**
	 * 
	 * @param groupTransferParameters
	 */
	private String composeParameterStringForHrz205AProcess(DemographicChangeParameters demographicChangeParameters) {
		System.out.println("********** composeParameterStringForHrz109AProcess");
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
//		String paramaterString = "'" + demographicChangeParameters.getEmployeeId() + "' "
//				+ "'" + demographicChangeParameters.getOperatorId() + "' "
//				+ "'" + demographicChangeParameters.getEmployeeGroup() + "' "
//				+ "'" + groupTransferParameters.getEmployeeBranch() + "' "
//				+ "'" + groupTransferParameters.getNationalIdCountry() + "' "
//				+ "'" + groupTransferParameters.getNationalId() + "' "
//				+ "'" + groupTransferParameters.getEffectiveDate() + "'";
//		return paramaterString;
		return null;
	}
	
}

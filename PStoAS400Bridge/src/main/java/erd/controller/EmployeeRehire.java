package erd.controller;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.NewHireProcessParameters;

/**
 * ZHRI106A - Rehire 
 * @author John Tutton john@tutton.net
 *
 */

public class EmployeeRehire {

	public String processEmployeeRehire(CommonParameters commonParameters) {
		NewHireProcessParameters processParameters = fetchProcessParameters(commonParameters);
		composeParameterString(processParameters);
		return null;
	}
	
	private NewHireProcessParameters fetchProcessParameters(CommonParameters commonParameters) {
		return null;
		
	}
	
	/**
	 * 
	 * @param processParameters
	 */
	private String composeParameterString(NewHireProcessParameters newHireProcessParameters) {
		System.out.println("********** composeParameterString");
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

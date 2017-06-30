package erd.controller;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.GroupTransferProcessParameters;

/**
 * ZHRI109A - Group Transfer
 * @author John Tutton john@tutton.net
 *
 */
public class EmployeeGroupTransfer {

	public String processEmployeeGroupTransfer(CommonParameters commonParameters) {
		GroupTransferProcessParameters processParameters = fetchProcessParameters(commonParameters);
		composeParameterString(processParameters);
		return null;
	}
	
	private GroupTransferProcessParameters fetchProcessParameters(CommonParameters commonParameters) {
		return null;
	}
	
	/**
	 * 
	 * @param processParameters
	 */
	private String composeParameterString(GroupTransferProcessParameters processParameters) {
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
		String paramaterString = "'" + processParameters.getEmployeeId() + "' "
				+ "'" + processParameters.getOperatorId() + "' "
				+ "'" + processParameters.getEmployeeGroup() + "' "
				+ "'" + processParameters.getEmployeeBranch() + "' "
				+ "'" + processParameters.getNationalIdCountry() + "' "
				+ "'" + processParameters.getNationalId() + "' "
				+ "'" + processParameters.getEffectiveDate() + "'";
		return paramaterString;
	}

}

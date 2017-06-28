package erd.controller;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.GroupTransferParameters;
import erd.model.PszTriggerEmployee;

/**
 * ZHRI109A - Group Transfer
 * @author John Tutton john@tutton.net
 *
 */
public class EmployeeGroupTransfer {

	public String HR09_processMain(PszTriggerEmployee trigger, CommonParameters commonParameters) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * @param groupTransferParameters
	 */
	private String composeParameterStringForHrz109AProcess(GroupTransferParameters groupTransferParameters) {
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
		String paramaterString = "'" + groupTransferParameters.getEmployeeId() + "' "
				+ "'" + groupTransferParameters.getOperatorId() + "' "
				+ "'" + groupTransferParameters.getEmployeeGroup() + "' "
				+ "'" + groupTransferParameters.getEmployeeBranch() + "' "
				+ "'" + groupTransferParameters.getNationalIdCountry() + "' "
				+ "'" + groupTransferParameters.getNationalId() + "' "
				+ "'" + groupTransferParameters.getEffectiveDate() + "'";
		return paramaterString;
	}

}

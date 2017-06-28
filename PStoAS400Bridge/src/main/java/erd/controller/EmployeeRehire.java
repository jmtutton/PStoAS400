package erd.controller;

import erd.model.PszTriggerEmployee;
import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.NewHireParameters;

/**
 * ZHRI106A - Rehire 
 * @author John Tutton john@tutton.net
 *
 */

public class EmployeeRehire {

	public String HR06_processMain(PszTriggerEmployee trigger, CommonParameters commonParameters) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * @param groupTransferParameters
	 */
	private String composeParameterStringForHrz109AProcess(NewHireParameters newHireParameters) {
		System.out.println("********** composeParameterStringForHrz109AProcess");
//		String paramaterString = "'" + groupTransferParameters.getEmployeeId() + "' "
//				+ "'" + groupTransferParameters.getOperatorId() + "' "
//				+ "'" + groupTransferParameters.getEmployeeGroup() + "' "
//				+ "'" + groupTransferParameters.getEmployeeBranch() + "' "
//				+ "'" + groupTransferParameters.getNationalIdCountry() + "' "
//				+ "'" + groupTransferParameters.getNationalId() + "' "
//				+ "'" + groupTransferParameters.getEffectiveDate() + "'";
//		return paramaterString;
		return null;
	}
}

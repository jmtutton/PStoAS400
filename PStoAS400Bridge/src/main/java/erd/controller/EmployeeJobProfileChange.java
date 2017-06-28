package erd.controller;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.JobProfileParameters;
import erd.model.PszTriggerEmployee;

/**
 * ZHRI104A - Job Profile Change
 * @author John Tutton john@tutton.net
 *
 */

public class EmployeeJobProfileChange {

	public String HR04_processMain(PszTriggerEmployee trigger, CommonParameters commonParameters) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * @param jobProfileParameters
	 */
	private String composeParameterStringForHrz109AProcess(JobProfileParameters jobProfileParameters) {
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
//		String paramaterString = "'" + jobProfileParameters.getEmployeeId() + "' "
//				+ "'" + jobProfileParameters.getOperatorId() + "' "
//				+ "'" + jobProfileParameters.getEmployeeGroup() + "' "
//				+ "'" + jobProfileParameters.getEmployeeBranch() + "' "
//				+ "'" + jobProfileParameters.getNationalIdCountry() + "' "
//				+ "'" + jobProfileParameters.getNationalId() + "' "
//				+ "'" + jobProfileParameters.getEffectiveDate() + "'";
//		return paramaterString;
		return null;
	}

}

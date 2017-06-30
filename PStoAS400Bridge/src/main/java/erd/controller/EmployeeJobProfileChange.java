package erd.controller;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.JobProfileProcessParameters;

/**
 * ZHRI104A - Job Profile Change
 * @author John Tutton john@tutton.net
 *
 */

public class EmployeeJobProfileChange {

	public String processEmployeeJobProfileChange(CommonParameters commonParameters) {
		JobProfileProcessParameters processParameters = fetchProcessParameters(commonParameters);
		composeParameterString(processParameters);
		return null;
	}
	
	private JobProfileProcessParameters fetchProcessParameters(CommonParameters commonParameters) {
		return null;
		
	}
	
	/**
	 * 
	 * @param processParameters
	 */
	private String composeParameterString(JobProfileProcessParameters processParameters) {
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

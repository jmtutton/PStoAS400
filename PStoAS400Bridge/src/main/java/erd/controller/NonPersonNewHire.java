package erd.controller;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.NewHireProcessParameters;

/**
 * ZHRI201A - Contingent Employee and Multiple EID New Hire
 * @author John Tutton john@tutton.net
 *
 */
public class NonPersonNewHire {
	
	public String processNonPersonNewHire(CommonParameters commonParameters) {
		System.out.println("********** HR201_processMain()");
		commonParameters.setPoiFlag(true);
		NewHireProcessParameters processParameters = fetchProcessParameters(commonParameters);
		composeParameterString(processParameters);
		return null;
	}
	
	private NewHireProcessParameters fetchProcessParameters(CommonParameters commonParameters) {
		System.out.println("********** HR201_initializeFields");
		return null;
	}

	/**
	 * 
	 * @param processParameters
	 */
	private String composeParameterString(NewHireProcessParameters processParameters) {
		System.out.println("********** composeParameterString");
		//'PARM('''                              ||
		//$LegAuditEmplid                        ||     !Legacy Emplid for audit field
		//''' '''                                ||
		//$LegAddEmplid                          ||       !Legacy Alternate Employee Id for the employee being hired
		//''' '''                                ||
		//$Legindex                              ||           !Legacy Index Number
		//''' '''                                ||
		//$LegGroup                              ||           !Legacy Group
		//''' '''                                ||
		//$LegBranch                             ||          !Legacy Branch
		//''' '''                                ||
		//$LegLastName                           ||        !Legacy Last Name
		//''' '''                                ||
		//$LegFirstName                          ||       !Legacy First Name
		//''' '''                                ||
		//$LegMiddleInit                         ||      !Legacy Middle Initial
		//''' '''                                ||
		//$LegNickName                           ||        !Legacy Nickname
		//''' '''                                ||
		//$LegGender                             ||          !Legacy Gender
		//''' '''                                ||
		//$LegServiceDate                        ||     !Legacy Start Date
		//''' '''                                ||
		//$LegDepartment                         ||      !Legacy Department
		//''' '''                                ||
		//$LegPosition                           ||       !Legacy Position
		//''' '''                                ||
		//$LegReferralSource                     || !Legacy Referral Source Code
		//''' '''                                ||
		//$LegAddress1                           ||        !Legacy Address1
		//''' '''                                ||
		//$LegCity                               ||            !Legacy City
		//''' '''                                ||
		//$HireRehireFlag                        ||     !Flag that indicates if this is a hire or a rehire
		// ''')"'
		String paramaterString = ""
//				+ "'" + processParameters.getEmployeeId() + "' "
//				+ "'" + processParameters.getOperatorId() + "' "
//				+ "'" + processParameters.getEmployeeGroup() + "' "
//				+ "'" + processParameters.getEmployeeBranch() + "' "
//				+ "'" + processParameters.getNationalIdCountry() + "' "
//				+ "'" + processParameters.getNationalId() + "' "
//				+ "'" + processParameters.getEffectiveDate() + "'"
		;
		return paramaterString;
	}

}

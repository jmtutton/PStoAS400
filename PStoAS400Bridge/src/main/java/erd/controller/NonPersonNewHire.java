package erd.controller;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.GroupTransferParameters;
import erd.model.PszTriggerNonPerson;

/**
 * ZHRI201A - Contingent Employee and Multiple EID New Hire
 * @author John Tutton john@tutton.net
 *
 */
public class NonPersonNewHire {
	
	public String HR201_processMain(PszTriggerNonPerson trigger, CommonParameters commonParameters) {
		System.out.println("********** HR201_processMain()");
		commonParameters.setPoiFlag(true);
		commonParameters = HR201_initializeFields(commonParameters);
		HR201_callSystem(commonParameters);
		return null;
	}
	
	private CommonParameters HR201_initializeFields(CommonParameters commonParameters) {
		System.out.println("********** HR201_initializeFields");
		return commonParameters;
		
	}

	private String HR201_callSystem(CommonParameters commonParameters) {
		System.out.println("********** HR201_callSystem()");
		return null;
		
	}

	public String HR205_processMain(PszTriggerNonPerson trigger, CommonParameters commonParameters) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @param groupTransferParameters
	 */
	private String composeParameterStringForHrz201AProcess(GroupTransferParameters groupTransferParameters) {
		System.out.println("********** composeParameterStringForHrz109AProcess");
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

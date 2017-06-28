package erd.controller;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.NewHireParameters;
import erd.model.PszTriggerEmployee;

/**
 * ZHRI101A - Employee New Hire Process
 * @author John Tutton john@tutton.net
 *
 */

public class EmployeeNewHire {

	public String HR01_processMain(PszTriggerEmployee trigger, CommonParameters commonParameters) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * @param newHireParameters
	 */
	private String composeParameterStringForHrz109AProcess(NewHireParameters newHireParameters) {
		System.out.println("********** composeParameterStringForHrz109AProcess");
		//'PARM('''                              ||
		//$LegAuditEmplid                        ||     !Legacy Emplid for audit field
		//''' '''                                ||
		//$LegAddEmplid                          ||       !Legacy Alternate Employee Id for the employee being hired
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
		//$LegMaritalStatus                      ||   !Legacy Marital Status
		//''' '''                                ||
		//$LegNickName                           ||        !Legacy Nickname
		//''' '''                                ||
		//$LegJobStatus                          ||       !Legacy Job Status
		//''' '''                                ||
		//$LegGender                             ||          !Legacy Gender
		//''' '''                                ||
		//$LegBirthYear                          ||       !Legacy Birth Date
		//''' '''                                ||
		//$LegBirthMonth                         ||      !Legacy Birth Month
		//''' '''                                ||
		//$LegBirthDay                           ||        !Legacy Birth Day
		//''' '''                                ||
		//$LegServiceYear                        ||     !Legacy Service Year
		//''' '''                                ||
		//$LegServiceMonth                       ||    !Legacy Service Month
		//''' '''                                ||
		//$LegServiceDay                         ||      !Legacy Service Day
		//''' '''                                ||
		//$LegUnionFlag                          ||       !Legacy Union Flag
		//''' '''                                ||
		//$LegRace                               ||            !Legacy Race
		//''' '''                                ||
		//$LegTimeCardFlag                       ||    !Legacy Time Card Flag
		//''' '''                                ||
		//$LegNid                                ||             !Legacy National Id (Social Security Number)
		//''' '''                                ||
		//$LegDepartment                         ||      !Legacy Department
		//''' '''                                ||
		//$LegSubDept                            ||         !Legacy Sub Dept
		//''' '''                                ||
		//$LegPosition                           ||       !Legacy Position
		//''' '''                                ||
		//$LegReferralSource                     || !Legacy Referral Source Code
		//''' '''                                ||
		//$PSSpecific_Refer_Src                  || !Legacy Referral Source Code
		//''' '''                                ||
		//$LegAddress1                           ||        !Legacy Address1
		//''' '''                                ||
		//$LegCity                               ||            !Legacy City
		//''' '''                                ||
		//$LegState                              ||           !Legacy State
		//''' '''                                ||
		//$LegZip                                ||             !Legacy Zip
		//''' '''                                ||
		//$LegHomeAreaCode                       ||    !Legacy Home Area code
		//''' '''                                ||
		//$LegHomePhone                          ||       !Legacy Employee Home phone number
		//''' '''                                ||
		//$LegHomePhoneError                     ||  !Flag to mark that the home phone number was too long
		//''' '''                                ||
		//$LegWrkAreaCode                        ||    !Legacy Work Area code
		//''' '''                                ||
		//$LegWorkPhone                          ||       !Legacy Employee Work phone number
		//''' '''                                ||
		//$LegWrkPhoneError                      ||  !Flag to mark that the Work phone number was too long
		//''' '''                                ||
		//$PSNAMEPREFIX                          ||  !Flag to mark that the Work phone number was too long
		//''' '''                                ||
		//$HireRehireFlag                        ||     !Flag that indicates if this is a hire or a rehire
		//''' '''                                ||      !dshen 01/11/2012
		//$PS_NID_COUNTRY                        ||         !dshen 01/11/2012
		// ''')"'
//		String paramaterString = "'" + newHireParameters.getEmployeeId() + "' "
//				+ "'" + newHireParameters.getOperatorId() + "' "
//				+ "'" + newHireParameters.getEmployeeGroup() + "' "
//				+ "'" + newHireParameters.getEmployeeBranch() + "' "
//				+ "'" + newHireParameters.getNationalIdCountry() + "' "
//				+ "'" + newHireParameters.getNationalId() + "' "
//				+ "'" + newHireParameters.getEffectiveDate() + "'";
//		return paramaterString;
		return null;
	}

}

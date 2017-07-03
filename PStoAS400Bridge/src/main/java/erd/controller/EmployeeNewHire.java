package erd.controller;

import java.util.HashMap;

/**
 * ZHRI101A - Employee New Hire Process
 * @author John Tutton john@tutton.net
 *
 */

public class EmployeeNewHire {

	public String processEmployeeNewHire(HashMap<String, Object> parameterMap) {
		System.out.println("********** processEmployeeNewHire()");
		parameterMap = fetchProcessParameters(parameterMap);
		composeParameterString(parameterMap);
		return null;
	}
	
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		return null;
		
	}
	
	/**
	 * 
	 * @param parameterMap
	 */
	private String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("********** composeParameterString()");
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
		String paramaterString = "'" + parameterMap.get("legacyAuditEmplid") + "' "     //Legacy Emplid for audit field
		+ "'" + parameterMap.get("legacyAddEmplid") + "' "      //Legacy Alternate Employee Id for the employee being hired
		+ "'" + parameterMap.get("legacyGroup") + "' "          //Legacy Group
		+ "'" + parameterMap.get("legacyBranch") + "' "         //Legacy Branch
		+ "'" + parameterMap.get("legacyLastName") + "' "       //Legacy Last Name
		+ "'" + parameterMap.get("legacyFirstName") + "' "      //Legacy First Name
		+ "'" + parameterMap.get("legacyMiddleInit") + "' "     //Legacy Middle Initial
		+ "'" + parameterMap.get("legacyMaritalStatus") + "' "  //Legacy Marital Status
		+ "'" + parameterMap.get("legacyNickName") + "' "       //Legacy Nickname
		+ "'" + parameterMap.get("legacyJobStatus") + "' "      //Legacy Job Status
		+ "'" + parameterMap.get("legacyGender") + "' "         //Legacy Gender
		+ "'" + parameterMap.get("legacyBirthYear") + "' "      //Legacy Birth Date
		+ "'" + parameterMap.get("legacyBirthMonth") + "' "     //Legacy Birth Month
		+ "'" + parameterMap.get("legacyBirthDay") + "' "       //Legacy Birth Day
		+ "'" + parameterMap.get("legacyServiceYear") + "' "    //Legacy Service Year
		+ "'" + parameterMap.get("legacyServiceMonth") + "' "   //Legacy Service Month
		+ "'" + parameterMap.get("legacyServiceDay") + "' "     //Legacy Service Day
		+ "'" + parameterMap.get("legacyUnionFlag") + "' "      //Legacy Union Flag
		+ "'" + parameterMap.get("legacyRace") + "' "           //Legacy Race
		+ "'" + parameterMap.get("legacyTimeCardFlag") + "' "   //Legacy Time Card Flag
		+ "'" + parameterMap.get("legacyNid") + "' "            //Legacy National Id (Social Security Number)
		+ "'" + parameterMap.get("legacyDepartment") + "' "     //Legacy Department
		+ "'" + parameterMap.get("legacySubDept") + "' "        //Legacy Sub Dept
		+ "'" + parameterMap.get("legacyPosition") + "' "       //Legacy Position
		+ "'" + parameterMap.get("legacyReferralSource") + "' " //Legacy Referral Source Code
		+ "'" + parameterMap.get("psSpecificReferSrc") + "' "	//Legacy Referral Source Code
		+ "'" + parameterMap.get("legacyAddress1") + "' "       //Legacy Address1
		+ "'" + parameterMap.get("legacyCity") + "' "           //Legacy City
		+ "'" + parameterMap.get("legacyState") + "' "          //Legacy State
		+ "'" + parameterMap.get("legacyZip") + "' "            //Legacy Zip
		+ "'" + parameterMap.get("legacyHomeAreaCode") + "' "   //Legacy Home Area code
		+ "'" + parameterMap.get("legacyHomePhone") + "' "      //Legacy Employee Home phone number
		+ "'" + parameterMap.get("legacyHomePhoneError") + "' " //Flag to mark that the home phone number was too long
		+ "'" + parameterMap.get("legacyWrkAreaCode") + "' "    //Legacy Work Area code
		+ "'" + parameterMap.get("legacyWorkPhone") + "' "      //Legacy Employee Work phone number
		+ "'" + parameterMap.get("legacyWrkPhoneError") + "' "  //Flag to mark that the Work phone number was too long
		+ "'" + parameterMap.get("psNamePrefix") + "' "  	  	//Flag to mark that the Work phone number was too long
		+ "'" + parameterMap.get("hireRehireFlag") + "' "     	//Flag that indicates if this is a hire or a rehire
		+ "'" + parameterMap.get("psNidCountry") + "' ";     	//dshen 01/11/2012
		return paramaterString;
	}

}

package erd.controller;

import java.util.HashMap;

/**
 * ZHRI201A - Contingent Employee and Multiple EID New Hire
 * @author John Tutton john@tutton.net
 *
 */
public class NonPersonNewHire {
	
	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonNewHire.doProcess() ***");
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", composeParameterString(parameterMap));
		return ZHRI100A.doCommand(parameterMap);
	}
	
	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonNewHire.fetchProcessParameters() ***");
		parameterMap.put("errorProgramParameter", "HRZ201A");
		return parameterMap;
	}

	/**
	 * 
	 * @param processParameters
	 */
	private String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonNewHire.composeParameterString() ***");
		String paramaterString = ""
		+ "'" + parameterMap.get("LegAuditEmplid") + "' "     //Legacy Emplid for audit field
		+ "'" + parameterMap.get("LegAddEmplid") + "' "       //Legacy Alternate Employee Id for the employee being hired
		+ "'" + parameterMap.get("Legindex") + "' "           //Legacy Index Number
		+ "'" + parameterMap.get("LegGroup") + "' "           //Legacy Group
		+ "'" + parameterMap.get("LegBranch") + "' "          //Legacy Branch
		+ "'" + parameterMap.get("LegLastName") + "' "        //Legacy Last Name
		+ "'" + parameterMap.get("LegFirstName") + "' "       //Legacy First Name
		+ "'" + parameterMap.get("LegMiddleInit") + "' "      //Legacy Middle Initial
		+ "'" + parameterMap.get("LegNickName") + "' "        //Legacy Nickname
		+ "'" + parameterMap.get("LegGender") + "' "          //Legacy Gender
		+ "'" + parameterMap.get("LegServiceDate") + "' "     //Legacy Start Date
		+ "'" + parameterMap.get("LegDepartment") + "' "      //Legacy Department
		+ "'" + parameterMap.get("LegPosition") + "' "        //Legacy Position
		+ "'" + parameterMap.get("LegReferralSource") + "' "  //Legacy Referral Source Code
		+ "'" + parameterMap.get("LegAddress1") + "' "        //Legacy Address1
		+ "'" + parameterMap.get("LegCity") + "' "            //Legacy City
		+ "'" + parameterMap.get("HireRehireFlag") + "' ";    //Flag that indicates if this is a hire or a rehire
		return paramaterString;
	}

}

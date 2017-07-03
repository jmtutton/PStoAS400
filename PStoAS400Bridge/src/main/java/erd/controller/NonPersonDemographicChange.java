package erd.controller;

import java.util.HashMap;

/**
 * ZHRI205A - Contingent Employee and Multiple EID Demographic Change Process
 * @author John Tutton john@tutton.net
 *
 */

public class NonPersonDemographicChange {
	
	public String processNonPersonDemographicChange(HashMap<String, Object> parameterMap) {
		System.out.println("********** HR205_processMain()");
		parameterMap = fetchProcessParameters(parameterMap);
		composeParameterString(parameterMap);
		return null;
	}
	
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("********** fetchProcessParameters");
		return null;
		
	}

	/**
	 * 
	 * @param parameterMap
	 */
	private String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("********** composeParameterString");
		String paramaterString = ""
		+ "'" + parameterMap.get("PSAuditemp") + "' " //$PSAuditEmp
		+ "'" + parameterMap.get("PSEmpl") + "' " //$PSEmpl
		+ "'" + parameterMap.get("PSGroup") + "' " //$PSGroup
		+ "'" + parameterMap.get("PSBranch") + "' " //$PSBranch
		+ "'" + parameterMap.get("PSLName") + "' " //$PSLName
		+ "'" + parameterMap.get("PSFName") + "' " //$PSFName
		+ "'" + parameterMap.get("PSMName") + "' " //$PSMName
		+ "'" + parameterMap.get("PSNickname") + "' " //$PSNickname
		+ "'" + parameterMap.get("PSGender") + "' " //$PSGender
		+ "'" + parameterMap.get("LegServiceDate") + "' " //$LegServiceDate
		+ "'" + parameterMap.get("PSDeptId") + "' " //$PSDeptId
		+ "'" + parameterMap.get("PSPosition") + "' " //$PSPosition
		+ "'" + parameterMap.get("PSReferralSource") + "' " //$PSReferral_Source
		+ "'" + parameterMap.get("PSAddress") + "' " //$PSAddress
		+ "'" + parameterMap.get("PSCity") + "' "; //$PSCity
		return paramaterString;
	}
	
}

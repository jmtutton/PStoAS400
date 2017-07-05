package erd.controller;

import java.util.HashMap;

/**
 * ZHRI205A - Contingent Employee and Multiple EID Demographic Change Process
 * @author John Tutton john@tutton.net
 *
 */

public class NonPersonDemographicChange {
	
	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonDemographicChange.doProcess() ***");
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
		System.out.println("*** NonPersonDemographicChange.fetchProcessParameters() ***");
		parameterMap.put("errorProgramParameter", "HRZ205A");
		return parameterMap;
		
	}

	/**
	 * 
	 * @param parameterMap
	 */
	private String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonDemographicChange.composeParameterString() ***");
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

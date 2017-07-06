package erd.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import erd.model.PszPoiTermination;

/**
 * Non-Person Termination Process
 * @see ZHRI202A.SQC
 * @author John Tutton john@tutton.net
 */

public class NonPersonTermination {

	/**
	 * This is the main processing procedure
	 * @see HR202-Process-Main in ZHRI202A.SQC
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonTermination.doProcess()");
		String completionStatus = "E";
		parameterMap = fetchProcessParameters(parameterMap);
		System.out.println("********************* parameterMap.get(\"employeeId\") = " + parameterMap.get("employeeId"));
		if(parameterMap.get("employeeId") != null && !((String)parameterMap.get("employeeId")).isEmpty()) {
			parameterMap.put("parameterString", ZHRI100A.composeParameterString(parameterMap));
			completionStatus = ZHRI100A.doCommand(parameterMap);
			if("C".equalsIgnoreCase(completionStatus)) {
				//insert POI termination record
				if(new BigDecimal(0).equals((BigDecimal)parameterMap.get("effectiveSequence"))) {
					PszPoiTermination.insertTimestamp((String)parameterMap.get("employeeId"));
				}
			}
		}
		return completionStatus;
	}
	
	/**
	 * Collects the values for the parameters needed for this process.
	 * @see HR202-Initialize-Fields in ZHRI202A.SQC
	 * @param parameterMap
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonTermination.fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ202A");
		if(parameterMap.get("operatorId") != null && ((String)parameterMap.get("operatorId")).length() > 1) {
			////strip the 'E' off of the operator id to format the legacy employee ID from the PeopleSoft OprId for audit field
			parameterMap.put("operatorId", ((String)parameterMap.get("operatorId")).substring(1).toUpperCase()); 
		}
		//datetostr($PSDateIn,'YYYYMMDD')
		String terminationDate = new SimpleDateFormat("yyyyMMdd").format((Date)parameterMap.get("effectiveDate"));
		parameterMap.put("terminationDate", terminationDate);
		parameterMap.put("employeeId", ZHRI100A.fetchLegacyEmployeeId(parameterMap));
		parameterMap.put("parameterNameList", getParameterNameList());
		return parameterMap;
	}
	
	/**
	 * Returns a list of parameter names specific to this process.
	 * @see HR202-Call-System in ZHRI202A.SQC
	 * @return parameterNameList
	 */
	private static List<String> getParameterNameList() {
		System.out.println("*** NonPersonTermination.getParameterNameList()");
		return Arrays.asList("operatorId", "employeeId", "terminationDate");
	}
	
}

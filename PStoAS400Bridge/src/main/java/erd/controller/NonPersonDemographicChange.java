package erd.controller;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contingent Employee and Multiple EID Demographic Change Process
 * @see ZHRI205A.SQC
 * @author John Tutton john@tutton.net
 */
public class NonPersonDemographicChange {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	
	/**
	 * 
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("*** NonPersonDemographicChange.doProcess()");
		//HR205-Initialize-Fields
		//HR205-Process-Main
		//HR205-Get-Personal-Data
		//HR205-Get-Emp-Poi
		//HR205-Get-Poi-Data
		//HR205-Get-Emp-Data
		//HR205-Get-PrimeId-PoiData
		//HR205-Get-Poi-LegPosNo
		//HR205-Get-Alternate_Type
		//HR205-Massage-Data
		//HR205-Call-Rpg-Program
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", Main.composeParameterString(parameterMap));
		return Main.doCommand(parameterMap);
	}
	
	/**
	 * 
	 * @param parameterMap
	 * @return parameterMap
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("*** NonPersonDemographicChange.fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ205A");
		parameterMap.put("parameterNameList", getParameterNameList());
		return parameterMap;
		
	}

	/**
	 * @see HR205-Call-RPG-Program
	 * @return list of parameter names for this process
	 */
	private static List<String> getParameterNameList() {
		return Arrays.asList("operatorId", "employeeId", "group", "branch",
				"lastName", "firstName", "middleName", "nickname",
				"gender", "serviceDate", "departmentId",
				"position", "referralSource", "address", "city");
	}
	
}

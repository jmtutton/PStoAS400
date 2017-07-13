package erd.controller;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import erd.model.PszPoiTermination;

/**
 * Non-Person Termination Process
 * @see ZHRI202A.SQC
 * @author John Tutton john@tutton.net
 */
public class NonPersonTermination {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	/**
	 * This is the main processing procedure
	 * @see HR202-Process-Main in ZHRI202A.SQC
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		logger.debug("*** NonPersonTermination.doProcess()");
		String completionStatus = "E";
		parameterMap = fetchProcessParameters(parameterMap);
		if(parameterMap.get("employeeId") != null && !((String)parameterMap.get("employeeId")).isEmpty()) {
			parameterMap.put("parameterString", Main.composeParameterString(parameterMap));
			completionStatus = Main.doCommand(parameterMap);
			if("C".equalsIgnoreCase(completionStatus)) { //completed normally
				//insert POI termination record
				if(new BigInteger("0").equals((BigInteger)parameterMap.get("effectiveSequence"))) {
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
		logger.debug("*** NonPersonTermination.fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ202A");
		parameterMap.put("errorProgramParameter", "HRZ202A");
		if(parameterMap.get("operatorId") != null && ((String)parameterMap.get("operatorId")).startsWith("E")) {
			parameterMap.put("operatorId", ((String)parameterMap.get("operatorId")).substring(1).trim().toUpperCase()); 
		}
		String terminationDate = new SimpleDateFormat("yyyyMMdd").format((Date)parameterMap.get("effectiveDate"));
		parameterMap.put("terminationDate", terminationDate);
		parameterMap.put("employeeId", Main.fetchLegacyEmployeeId(parameterMap));
		parameterMap.put("parameterNameList", getParameterNameList());
		return parameterMap;
	}
	
	/**
	 * Returns a list of parameter names specific to this process.
	 * @see HR202-Call-System in ZHRI202A.SQC
	 * @return parameterNameList
	 */
	public static List<String> getParameterNameList() {
		logger.debug("*** NonPersonTermination.getParameterNameList()");
		return Arrays.asList("operatorId", "employeeId", "terminationDate");
	}
	
}

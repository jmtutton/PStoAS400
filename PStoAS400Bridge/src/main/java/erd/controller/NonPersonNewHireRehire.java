package erd.controller;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contingent Employee and Multiple EID New Hire / Rehire Process
 * @see ZHRI201A.SQC PeopleCode file
 * @author John Tutton john@tutton.net
 */
public class NonPersonNewHireRehire extends NonPerson {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    /**
	 * @see HR201-Process-Main in ZHRI201A.SQC
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("*** NonPersonNewHireRehire.doProcess()");
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", Main.composeParameterString(parameterMap));
		String completionStatus = Main.doCommand(parameterMap);
	    //if operatorId doesn't already exists, add the new employee as a PeopleSoft Operator
		Main.fetchLegacyEmployeeId(parameterMap);
		return completionStatus;
	}

	/**
	 * @see HR201-Initialize-Fields in ZHRI201A.SQC
	 * @see HR201-Massage-Data in ZHRI201A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("*** NonPersonNewHireRehire.fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ201A");
		parameterMap.put("parameterNameList", getParameterNameList());
		//DO HR201-Initialize-Fields
		if("0".equals((BigInteger)parameterMap.get("eidIndexNumber"))) {
			//DO HR201-Get-POI-Data
			parameterMap = fetchPersonOfInterestData(parameterMap);
			//DO Main-Sql-Poi
			parameterMap.put("serviceDate", mainSqlPoi((String)parameterMap.get("employeeId")));
		}
		else {
			//DO HR201-Get-Emp-Data
			parameterMap = fetchMultipleEmployeeIdData(parameterMap);
			//DO Main-Sql-Emp
			parameterMap.put("serviceDate", mainSqlEmp((String)parameterMap.get("employeeId"), (BigInteger)parameterMap.get("eidIndexNumber")));
		}
		//DO HR201-Get-Personal-Data
		parameterMap = fetchPersonalData(parameterMap);
		//DO HR201-Massage-Data
		parameterMap = massageData(parameterMap);
		return parameterMap;
	}

	/**
	 * @see HR201-Get-Personal-Data
	 * @param parameterMap
	 * @return parameterMap
	 */
	private HashMap<String, Object> fetchPersonalData(HashMap<String, Object> parameterMap) {
		parameterMap = fetchEffectiveDatedPersonalData(parameterMap);
		parameterMap = fetchPsNamesData(parameterMap);
		return parameterMap;
	}

	/**
	 * @see HR201-Build-Call-Statement in ZHRI201A.SQC
	 * @return list of parameter names for this process
	 */
	private static List<String> getParameterNameList() {
		return Arrays.asList("operatorId", "employeeId", "eidIndexNumber", 
				"group", "branch", "lastName", "firstName", "middleInitial", "nickname",
				"gender", "serviceDate", "department", "position",
				"referralSource", "address", "city", "hireRehireFlag");
	}

}

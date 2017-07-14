package erd.controller;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Employee New Hire / Rehire Process
 * @see ZHRI101A.SQC PeopleCode file
 * @author John Tutton john@tutton.net
 */
public class EmployeeNewHireRehire extends Employee {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	/**
	 * @see HR01-Process-Main in ZHRI101A.SQC
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("doProcess()");
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", Main.composeParameterString(parameterMap));
		String completionStatus = Main.doCommand(parameterMap);
	    //if operatorId doesn't already exists, add the new employee as a PeopleSoft Operator
		Main.fetchLegacyEmployeeId(parameterMap);
		return completionStatus;
	}

	/**
	 * @param parameterMap
	 * @return parameterMap
	 */
	private static HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ101A");
		parameterMap.put("parameterNameList", getParameterNameList());
		parameterMap.put("operatorId", formatOperatorId((String)parameterMap.get("operatorId")));
		parameterMap = fetchJobData(parameterMap);
		parameterMap = fetchEffectiveDatedPersonalData(parameterMap);
		parameterMap = fetchPersonData(parameterMap);
		parameterMap = fetchPersonalData(parameterMap);
		parameterMap = fetchPersonalNationalIdData(parameterMap);
		parameterMap = fetchReferralSourceData(parameterMap);
		parameterMap = fetchNameData(parameterMap);
		parameterMap = fetchAddressData(parameterMap);
		parameterMap = fetchPhoneData(parameterMap);
		parameterMap = fetchDiversityEthnicityData(parameterMap);
		parameterMap = fetchServiceDateData(parameterMap);
		parameterMap = fetchJobProfileData(parameterMap);
		return parameterMap;
	}
	
	/**
	 * @see HR01-Build-Call-Statement in ZHRI101A.SQC
	 * @return list of parameter names for this process
	 */
	private static List<String> getParameterNameList() {
		return Arrays.asList("operatorId", "employeeId", "group", "branch",
						"lastName", "firstName", "middleInitial", "maritalStatus", "nickname",
						"jobStatus", "gender", "birthYear", "birthMonth", "birthDay",
						"serviceYear", "serviceMonth", "serviceDay",
						"unionFlag", "race", "timeCardFlag", 
						"nationalId", "department", "subdepartment", "position",
						"referralSource", "referralSourceId", 
						"address", "city", "state", "postalCode",
						"homePhoneAreaCode", "homePhoneNumber","homePhoneError",
						"businessPhoneAreaCode","businessPhoneNumber", "businessPhoneError",
						"namePrefix", "hireRehireFlag", "nationalIdCountry");
	}

}

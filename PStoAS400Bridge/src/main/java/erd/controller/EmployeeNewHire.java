package erd.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Employee New Hire Process
 * @see ZHRI101A.SQC
 * @author John Tutton john@tutton.net
 */
public class EmployeeNewHire {

	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeNewHire.doProcess()");
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", ZHRI100A.composeParameterString(parameterMap));
		return ZHRI100A.doCommand(parameterMap);
	}
	
	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeNewHire.fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ101A");
		parameterMap.put("parameterNameList", getParameterNameList());
		return parameterMap;
	}
	
	/**
	 * @see HR01-Build-Call-Statement in ZHRI101A.SQC
	 * @return list of parameter names for this process
	 */
	private static List<String> getParameterNameList() {
		return Arrays.asList("operatorId", "employeeId",
						"group", "branch",
						"lastName", "firstName", "middleInitial", "maritalStatus", "nickname",
						"jobStatus", "gender", "birthYear", "birthMonth", "birthDay",
						"serviceYear", "serviceMonth", "serviceDay",
						"unionFlag", "race", "timeCardFlag", 
						"nationalId", "department", "subDepartment", "position",
						"referralSource", "referralSourceId", 
						"address", "city", "state", "postalCode",
						"homePhoneAreaCode", "homePhoneNumber","homePhoneError",
						"businessPhoneAreaCode","businessPhoneNumber", "businessPhoneError",
						"namePrefix", "hireRehireFlag", "nationalIdCountry");
	}

}

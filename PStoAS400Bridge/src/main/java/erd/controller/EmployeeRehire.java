package erd.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Employee Rehire Process
 * @see ZHRI101A.SQC
 * @author John Tutton john@tutton.net
 */
public class EmployeeRehire {

	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeRehire.doProcess()");
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", ZHRI100A.composeParameterString(parameterMap));
		return null;
	}
	
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeRehire.fetchProcessParameters()");
		parameterMap.put("parameterNameList", getParameterNameList());
		return null;
		
	}
	
	/**
	 * @see ZHRI106A.SQC
	 * @return list of parameter names for this process
	 */
	//TODO
	private static List<String> getParameterNameList() {
		System.out.println("*** EmployeeRehire.getParameterNameList()");
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

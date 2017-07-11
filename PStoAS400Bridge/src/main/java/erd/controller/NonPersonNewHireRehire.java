package erd.controller;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contingent Employee and Multiple EID New Hire Process
 * @see ZHRI201A.SQC
 * @author John Tutton john@tutton.net
 */
public class NonPersonNewHireRehire {
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
		return Main.doCommand(parameterMap);
		
		//HR201-Massage-Data
		//massageData
		//HR201-Get-Personal-Data
		//fetchPersonalData
			//PsEffectiveDatedPersonalData.findByEmployeeIdAndEffectiveDate
			//PsNames.findByEmployeeIdAndNameTypeAndEffectiveDate
		//HR201-Get-POI-Data
		//fetchPoiData
			//CrossReferencePersonOfInterest.findByEmployeeIdAndEffectiveDate
		//HR201-Get-Emp-Data
		//fetchEmployeeData
			//CrossReferenceMultipleEmployeeId.findByEmployeeIdAndSequenceAndEffectiveDate
		//HR201-GET-Emp-POI
		//fetchEmployeePoi
			//CrossReferencePersonOfInterestEmployee.findByEmployeeId
		//HR201-Get-PrimEid-POIdata
		//fetchPrimaryEidPoi
			//CrossReferencePersonOfInterest.findActiveByEmployeeId
		//HR201-Get-POI-LegPosNo
		//fetchPoiLegacyPositionNumber
			//PszXlat.findActiveByInput01AndInput02AndInput03
		//HR201-Get-Alternate_Type
		//fetchAlternateType
			//PszXlat.findActiveByInput01AndInput02AndInput03
	}
	
	/**
	 * @see HR201-Initialize-Fields in ZHRI201A.SQC
	 * @see HR201-Massage-Data in ZHRI201A.SQC
	 * @param parameterMap
	 * @return appended parameterMap
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("*** NonPersonNewHireRehire.fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ201A");
		parameterMap.put("parameterNameList", getParameterNameList());
		return parameterMap;
	}

	/**
	 * @see HR201-Build-Call-Statement in ZHRI201A.SQC
	 * @return list of parameter names for this process
	 */
	private static List<String> getParameterNameList() {
		return Arrays.asList("operatorId", "employeeId", "indexNumber", 
				"group", "branch",
				"lastName", "firstName", "middleInitial", "nickname",
				"gender", "serviceDate", "department", "position",
				"referralSource", "address", "city", "hireRehireFlag");
	}

}

package erd.controller;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contingent Employee and Multiple EID New Hire / Rehire Process
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
			//fetchEffectiveDatedPersonalData
				//PsEffectiveDatedPersonalData.findByEmployeeIdAndEffectiveDate  //PS_PERS_DATA_EFFDT
			//fetchPsNamesData
				//PsNames.findByEmployeeIdAndNameTypeAndEffectiveDate  //PS_NAMES
		//HR201-Get-POI-Data
		//fetchPersonOfInterestData
			//CrossReferencePersonOfInterest.findByEmployeeIdAndEffectiveDate  //PS_ZHRT_PER_POI_TR
		//HR201-Get-Emp-Data
		//fetchMultipleEmployeeIdData
			//CrossReferenceMultipleEmployeeId.findByEmployeeIdAndSequenceAndEffectiveDate  //PS_ZHRR_MULTPL_EID
		//HR201-Get-Emp-POI
		//fetchPersonOfInterestEmployeeData
			//CrossReferencePersonOfInterestEmployee.findByEmployeeId  //PS_ZHRR_POI_EMP_VW
		//HR201-Get-PrimEid-POIdata
		//fetchPersonOfInterestData
			//CrossReferencePersonOfInterest.findActiveByEmployeeId  //PS_ZHRT_PER_POI_TR
		//HR201-Get-POI-LegPosNo
		//fetchPoiLegacyPositionNumberData
			//PszXlat.findActiveByInput01AndInput02AndInput03
		//HR201-Get-Alternate_Type
		//fetchAlternateTypeData
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

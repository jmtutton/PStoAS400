package erd.controller;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Employee New Hire Process
 * @see ZHRI101A.SQC
 * @author John Tutton john@tutton.net
 */
public class EmployeeNewHireRehire {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	/**
	 * @see 
	 * @param parameterMap
	 * @return
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("*** EmployeeNewHireRehire.doProcess()");
		//initializeFields
		//processMain
		//massageData
		parameterMap = fetchProcessParameters(parameterMap);
		//buildCallStatement
		parameterMap.put("parameterString", Main.composeParameterString(parameterMap));
		return Main.doCommand(parameterMap);
		
		//CrossReferenceEthnicGroup.findActiveLegacyEthnicCodeByEthnicGroup
		//fetchPosition
			//CrossReferenceJobCode.findActiveBySetIdJobCodeAndJobCodeAndEmplClassAndFullPartTimeAndRegTempAndDeptId
		//fetchReferralSource
			//CrossReferenceReferralSource.findLActiveegacyRecruiterSourceByReferralSource
		//CrossReferencePt12p.findActiveBranchByDepartment
		//fetchGroup
			//CrossReferenceCompany.findActiveLegacyGroupByCompany
		//fetchJobData
			//PsJob.findByEmployeeIdEAndmplyeeRecordAndActionAndActionReason
		//fetchPersonalData
			//PsEffectiveDatedPersonalData.findByEmployeeIdAndEffectiveDate
			//PsPerson.findPirthDateByEmployeeId
			//PsPersonalData.findLanguageCodeByEmployeeId
			//PsPersonalNationalId.findPrimaryByEmployeeIdAndRegion
			//PsRecruitmentSource.findByEmployeeIdAndEffectiveDateAndSourceId
			//PsReferralSource.findSourceIdByEmployeeIdAndEffectiveDate
			//PsName.findByEmployeeIdAndNameTypeAndEffectiveDate
			//PsAddress.findByEmployeeIdAndAddressTypeAndEffectiveDate
		//PsLocation.findCountryByLocation
		//fetchOfficePhone
			//PsPersonalPhone.findPhoneByEmployeeIdAndPhoneType
		//fetchMainPhone
			//PsPersonalPhone.findPhoneByEmployeeIdAndPhoneType
		//fetchEthnicGroup
			//PsDiversityEthnicity.findEthnicGroupCodeByEmployeeId
		//fetchEthnicCode
			//PsEthnicGroup.findEthnicGroupByEthnicGroupCode
		//fetch2CharCountry
			//PsCountry.findCountry2CharByCountry
	}
	
	/**
	 * @see 
	 * @param parameterMap
	 * @return
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("*** EmployeeNewHireRehire.fetchProcessParameters()");
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

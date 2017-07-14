package erd.controller;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import erd.ErdUtils;
import erd.ErdUtils.SplitDate;
import erd.model.CrossReferenceCompany;
import erd.model.CrossReferenceEthnicGroup;
import erd.model.CrossReferenceJobCode;
import erd.model.CrossReferencePt12p;
import erd.model.CrossReferenceReferralSource;
import erd.model.PsAddress;
import erd.model.PsCountry;
import erd.model.PsDiversityEthnicity;
import erd.model.PsEffectiveDatedPersonalData;
import erd.model.PsEthnicGroup;
import erd.model.PsJob;
import erd.model.PsName;
import erd.model.PsPerson;
import erd.model.PsPersonalApplicantReferral;
import erd.model.PsPersonalData;
import erd.model.PsPersonalNationalId;
import erd.model.PsPersonalPhone;
import erd.model.PsRecruitmentSource;

public class Employee {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    /**
	 * This routine will get the address info for the employee number.
	 * @see HR05-Get-Info in ZHRI105A.SQC
	 * @precondition parameterMap must contain key="employeeId"
	 * @precondition parameterMap must contain key="effectiveDate"
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchAddressData(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("fetchAddressData()");
		PsAddress psAddress = PsAddress.findHomeByEmployeeIdAndEffectiveDate((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate"));
		if(psAddress != null) {
			String address = psAddress.getAddress1();
			parameterMap.put("address", address != null ? address.trim().toUpperCase().replaceAll("'", "''") : address);
			String city = psAddress.getCity();
			parameterMap.put("city", city != null ? city.trim().toUpperCase().replaceAll("'", "''") : city);
			String state = psAddress.getState();
			parameterMap.put("state", state != null ? state.trim().toUpperCase() : state);
			String postalCode = psAddress.getPostal();
			parameterMap.put("postalCode", postalCode != null ? postalCode.trim().toUpperCase() : postalCode);
		}
		return parameterMap;
	}

	/**
	 * @see HR01-Get-Personal-Data
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchNameData(HashMap<String, Object> parameterMap) {
		logger.debug("fetchNameData() ***");
		logger.debug("employeeId: " + parameterMap.get("employeeId"));
		logger.debug("effectiveDate: " + parameterMap.get("effectiveDate"));
		PsName psPrimaryName = PsName.findByEmployeeIdAndNameTypeAndEffectiveDate((String)parameterMap.get("employeeId"), "PRI", (Date)parameterMap.get("effectiveDate"));
		if(psPrimaryName != null) {
			String middleName = psPrimaryName.getMiddleName() != null ? psPrimaryName.getMiddleName().trim() : psPrimaryName.getMiddleName();
			parameterMap.put("middleInitial", middleName != null && !middleName.isEmpty() ? middleName.substring(0, 1).toUpperCase() : middleName);
			String firstName = psPrimaryName.getFirstName();
			parameterMap.put("firstName", firstName != null ? firstName.trim().toUpperCase().replaceAll("'", "''") : firstName);
			String namePrefix = psPrimaryName.getNamePrefix();
			namePrefix = namePrefix != null ? namePrefix.trim().toUpperCase().replaceAll("'", "''") : namePrefix;
			namePrefix = namePrefix != null && namePrefix.length() > 3 ? namePrefix.substring(0, 3) : namePrefix;
			parameterMap.put("namePrefix", namePrefix);
			String nameSuffix = psPrimaryName.getNameSuffix();
			nameSuffix = nameSuffix != null ? nameSuffix.trim().toUpperCase().replaceAll("'", "''") : nameSuffix;
			String lastName = psPrimaryName.getLastName();
			lastName = nameSuffix != null ? lastName + nameSuffix : lastName;
			parameterMap.put("lastName", lastName != null ? lastName.trim().toUpperCase().replaceAll("'", "''") : lastName);
		}
		PsName psPreferredName = PsName.findByEmployeeIdAndNameTypeAndEffectiveDate((String)parameterMap.get("employeeId"), "PRF", (Date)parameterMap.get("effectiveDate"));
		if(psPreferredName != null) {
			String nickname = psPreferredName.getFirstName();
			parameterMap.put("nickname", nickname != null ? nickname.trim().toUpperCase().replaceAll("'", "''") : nickname);
		}
		return parameterMap;
	}

	/**
	 * @see HR01-Get-Job-Data
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchJobData(HashMap<String, Object> parameterMap) {
		logger.debug("fetchJobData() ***");
		PsJob psJob = PsJob.findJobData((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate"));
		if(psJob != null) {
			parameterMap = setJobParamters(psJob, parameterMap);
		}
		return parameterMap;
	}

	/**
	 * @see HR01-Get-Job-Data
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> setJobParamters(PsJob psJob, HashMap<String, Object> parameterMap) {
		logger.debug("setJobParamters() ***");
		if(psJob != null) {
			parameterMap.put("company", psJob.getCompany() != null ? psJob.getCompany().trim().toUpperCase() : psJob.getCompany());
			parameterMap.put("businessUnit", psJob.getBusinessUnit() != null ? psJob.getBusinessUnit().trim().toUpperCase() : psJob.getBusinessUnit());
			parameterMap.put("departmentId", psJob.getDepartmentId() != null ? psJob.getDepartmentId().trim().toUpperCase() : psJob.getDepartmentId());
			parameterMap.put("effectiveDate", psJob.getEffectiveDate());
			parameterMap.put("employeeClass", psJob.getEmployeeClass() != null ? psJob.getEmployeeClass().trim().toUpperCase() : psJob.getEmployeeClass());
			parameterMap.put("employeeStatus", psJob.getEmployeeStatus() != null ? psJob.getEmployeeStatus().trim().toUpperCase() : psJob.getEmployeeStatus());
			parameterMap.put("flsaStatus", psJob.getFlsaStatus() != null ? psJob.getFlsaStatus().trim().toUpperCase() : psJob.getFlsaStatus());
			parameterMap.put("fullOrPartTime", psJob.getFullPartTime() != null ? psJob.getFullPartTime().trim().toUpperCase() : psJob.getFullPartTime());
			parameterMap.put("jobCode", psJob.getJobCode() != null ? psJob.getJobCode().trim().toUpperCase() : psJob.getJobCode());
			parameterMap.put("location", psJob.getLocation() != null ? psJob.getLocation().trim().toUpperCase() : psJob.getLocation());
			parameterMap.put("region", psJob.getRegion() != null ? psJob.getRegion().trim().toUpperCase() : psJob.getRegion());
			parameterMap.put("regularOrTemporary", psJob.getRegularOrTemporary() != null ? psJob.getRegularOrTemporary().trim().toUpperCase() : psJob.getRegularOrTemporary());
			parameterMap.put("setIdJobCode", psJob.getSetIdJobCode() != null ? StringUtils.left(psJob.getSetIdJobCode().trim().toUpperCase(), 5) : psJob.getSetIdJobCode());
		}
		return parameterMap;
	}
	
	/**
	 * @see HR01-Get-Main-Phone
	 * @see HR01-Get-Office-Phone
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchPhoneData(HashMap<String, Object> parameterMap) {
		logger.debug("fetchPhoneData() ***");
		String homePhoneNumber = PsPersonalPhone.findPhoneByEmployeeIdAndPhoneType((String)parameterMap.get("employeeId"), "");
		String businessPhoneNumber = PsPersonalPhone.findPhoneByEmployeeIdAndPhoneType((String)parameterMap.get("employeeId"), "");
		String homePhoneAreaCode = "";
		String businessPhoneAreaCode = "";
		String homePhoneError = "N";
		String businessPhoneError = "N";
		if(homePhoneNumber != null) {
			//remove non-alphanumeric characters
			homePhoneNumber = homePhoneNumber.trim().replaceAll("[^a-zA-Z0-9]", "");
			homePhoneError = homePhoneNumber.length() > 10 ? "Y" : "N";
			if(homePhoneNumber.length() == 10) {
				//get area code
				homePhoneAreaCode = homePhoneNumber.substring(0, 3);
				//get the rest of the number
				homePhoneNumber = homePhoneNumber.substring(3);
			}
		}
		parameterMap.put("homePhoneError", homePhoneError);
		parameterMap.put("homePhoneNumber", homePhoneNumber);
		parameterMap.put("homePhoneAreaCode", homePhoneAreaCode);
		if(businessPhoneNumber != null) {
			//remove non-alphanumeric characters
			businessPhoneNumber = businessPhoneNumber.trim().replaceAll("[^a-zA-Z0-9]", "");
			businessPhoneError = businessPhoneNumber.length() > 10 ? "Y" : "N";
			if(businessPhoneNumber.length() == 10) {
				businessPhoneAreaCode = businessPhoneNumber.substring(0, 3);
				businessPhoneNumber = businessPhoneNumber.substring(3);
			}
		}
		parameterMap.put("businessPhoneError", businessPhoneError);
		parameterMap.put("businessPhoneNumber", businessPhoneNumber);
		parameterMap.put("businessPhoneAreaCode", businessPhoneAreaCode);
		return parameterMap;
	}

	/**
	 * @see HR01-Get-Ethnic-Group in ZHRI101A.SQC
	 * @see Get-Ethnic-Code in ZHRI101A.SQC
	 * @see HR01-Get-Legacy-Ethnic-Code in ZHRI101A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchDiversityEthnicityData(HashMap<String, Object>  parameterMap) {
		logger.debug("fetchDiversityEthnicityData() ***");
		String ethnicGroupCode = PsDiversityEthnicity.findEthnicGroupCodeByEmployeeId((String)parameterMap.get("employeeId"));
		if(!"O".equalsIgnoreCase(ethnicGroupCode)) {
			ethnicGroupCode = PsEthnicGroup.findEthnicGroupByEthnicGroupCode(ethnicGroupCode);
		}
		String legacyEthnicCode = CrossReferenceEthnicGroup.findActiveLegacyEthnicCodeByEthnicGroup(ethnicGroupCode);
		legacyEthnicCode = legacyEthnicCode != null ? legacyEthnicCode.trim().toUpperCase() : legacyEthnicCode;
		parameterMap.put("race", legacyEthnicCode);
		return parameterMap;
	}
	
	public static String formatOperatorId(String operatorId) {
		logger.debug("checkTriggerRecord() ***");
		if(operatorId != null) {
			//strip the E off of the front of the ID
			operatorId = operatorId != null && operatorId.startsWith("E") ? operatorId.substring(1).trim().toUpperCase() : operatorId;
		}
		return operatorId;
	}
	
	/**
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchJobProfileData(HashMap<String, Object> parameterMap) {
		logger.debug("fetchJobProfileData() ***");
		parameterMap = fetchCompanyData(parameterMap);
		parameterMap.put("subDepartment", "03"); //Default sub department to 03
		parameterMap = fetchJobCodeData(parameterMap);
		if("O".equalsIgnoreCase((String)parameterMap.get("employeeClass"))) {
			parameterMap.put("unionFlag", "Y");
		}
		else {
			parameterMap.put("unionFlag", "N");
		}
		if("N".equalsIgnoreCase((String)parameterMap.get("flsaStatus"))) {
			parameterMap.put("timeCardFlag", "Y");
		}
		else {
			parameterMap.put("timeCardFlag", "N");
		}
		return parameterMap;
	}

	/**
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchEffectiveDatedPersonalData(HashMap<String, Object> parameterMap) {
		logger.debug("fetchEffectiveDatedPersonalData() ***");
		PsEffectiveDatedPersonalData psEffectiveDatedPersonalData = PsEffectiveDatedPersonalData.findByEmployeeIdAndEffectiveDate((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate"));
		if(psEffectiveDatedPersonalData != null) {
			String maritalStatus = psEffectiveDatedPersonalData.getMaritalStatus();
			maritalStatus = maritalStatus != null ? maritalStatus.trim().toUpperCase() : maritalStatus;
			parameterMap.put("maritalStatus", maritalStatus);
			String gender = psEffectiveDatedPersonalData.getGender();
			gender = gender != null ? gender.trim().toUpperCase() : gender;
			parameterMap.put("gender", gender);
		}
		return parameterMap;
	}

	/**
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchPersonData(HashMap<String, Object> parameterMap) {
		logger.debug("fetchPersonData() ***");
		PsPerson psPerson = PsPerson.findByEmployeeId((String)parameterMap.get("employeeId"));
		if(psPerson != null && psPerson.getBirthDate() != null) {
			SplitDate splitDate = new ErdUtils().new SplitDate(psPerson.getBirthDate());
			parameterMap.put("birthYear", splitDate.getYear());
			parameterMap.put("birthMonth", splitDate.getMonth());
			parameterMap.put("birthDay", splitDate.getDay());
		}
		return parameterMap;
	}

	/**
	 * @see HR01-Get-Personal-Data
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchPersonalData(HashMap<String, Object> parameterMap) {
		logger.debug("fetchPersonalData() ***");
		PsPersonalData psPersonalData = PsPersonalData.findByEmployeeId((String)parameterMap.get("employeeId"));
		if(psPersonalData != null) {
			String languageCode = psPersonalData.getLanguageCode();
			languageCode = languageCode != null ? languageCode.trim().toUpperCase() : languageCode;
			parameterMap.put("languageCode", languageCode);
		}
		return parameterMap;
	}

	/**
	 * @see HR01-Get-Group
	 * @see HR01-Get-Branch
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchCompanyData(HashMap<String, Object> parameterMap) {
		logger.debug("fetchCompanyData() ***");
		String group = CrossReferenceCompany.findActiveLegacyGroupByCompany((String)parameterMap.get("company"));
		parameterMap.put("group", group != null ? group.trim().toUpperCase() : group);
		if(group == null || group.isEmpty()) {
			parameterMap.put("errorMessageParameter", "The Company entered is not in the cross reference table PS_ZHRT_CMPNY_CREF");
			parameterMap.put("criticalFlag", "Y");
			Main.doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", "N");
		}
		String branch = CrossReferencePt12p.findActiveBranchByDepartment((String)parameterMap.get("department"));
		parameterMap.put("branch", branch != null ? branch.trim().toUpperCase() : branch);
		return parameterMap;
	}

	/**
	 * Get the Legacy position from the cross reference table using the jobcode setid, jobcode, empl_class, full_part_time, reg_temp, and deptid from PeopleSoft
	 * @see HR01-Get-Position
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchJobCodeData(HashMap<String, Object> parameterMap) {
		logger.debug("fetchJobCodeData() ***");
		CrossReferenceJobCode crossReferenceJobCode = CrossReferenceJobCode.findActiveBySetIdJobCodeAndJobCodeAndEmployeeClassAndFullOrPartTimeAndRegularOrTemporaryAndDepartment(
				(String)parameterMap.get("setIdJobCode"), (String)parameterMap.get("jobCode"), 
				(String)parameterMap.get("employeeClass"), (String)parameterMap.get("fullOrPartTime"), 
				(String)parameterMap.get("regularOrTemporary"), (String)parameterMap.get("departmentId"));
		if(crossReferenceJobCode != null) {
			String position = crossReferenceJobCode.getLegacyPositionCode();
			position = position != null ? position.trim().toUpperCase() : position;
			parameterMap.put("position", position);
			String department = crossReferenceJobCode.getLegacyDepartmentCode();
			department = department != null ? department.trim().toUpperCase() : department;
			parameterMap.put("department", department);
			String jobStatus = crossReferenceJobCode.getLegacyJobStatusCode();
			jobStatus = jobStatus != null ? jobStatus.trim().toUpperCase() : jobStatus;
			parameterMap.put("jobStatus", jobStatus);
		}
		return parameterMap;
	}

	/**
	 * 
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchServiceDateData(HashMap<String, Object> parameterMap) {
		logger.debug("fetchServiceDateData() ***");
		if((Date)parameterMap.get("effectiveDate") != null) {
			SplitDate splitDate = new ErdUtils().new SplitDate((Date)parameterMap.get("effectiveDate"));
			parameterMap.put("serviceYear", splitDate.getYear());
			parameterMap.put("serviceMonth", splitDate.getMonth());
			parameterMap.put("serviceDay", splitDate.getDay());
		}
		return parameterMap;
	}
	
	public static HashMap<String, Object> fetchPersonalNationalIdData(HashMap<String, Object> parameterMap) {
		logger.debug("fetchPersonalNationalIdData() ***");
		String countryIsoAlpha3Code = (String)parameterMap.get("region");
		String nationalId = PsPersonalNationalId.findPrimaryNationalIdByEmployeeIdAndCountry((String)parameterMap.get("employeeId"), countryIsoAlpha3Code);
		parameterMap.put("nationalId", nationalId != null ? nationalId.trim().toUpperCase() : nationalId);
		String nationalIdCountry = PsCountry.findCountryIsoAlpha2CodeByCountryIsoAlpha3Code(countryIsoAlpha3Code);
		parameterMap.put("nationalIdCountry", nationalIdCountry != null ? nationalIdCountry.trim().toUpperCase() : nationalIdCountry);
		return parameterMap;
	}

	/**
	 * @see HR01-Get-Referral-Source
	 * @param parameterMap
	 * @return parameterMap
	 */
	//TODO: not finished, check against original
	public static HashMap<String, Object> fetchReferralSourceData(HashMap<String, Object> parameterMap) {
		logger.debug("fetchReferralSourceData() ***");
		BigInteger sourceId = PsPersonalApplicantReferral.findByEmployeeIdAndEffectiveDate((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate")).getSourceId(); //PS_PERS_APPL_REF
		PsRecruitmentSource psRecruitmentSource = PsRecruitmentSource.findBySourceId(sourceId); //PS_HRS_SOURCE_I
		String psReferralSource = psRecruitmentSource.getSourceName();
//		psReferralSource = psReferralSource != null ? psReferralSource.trim().toUpperCase() : psReferralSource;
		String psReferralSourceDescription = psRecruitmentSource.getSourceDescription();
		String legReferralSource = CrossReferenceReferralSource.findActiveLegacyRecruiterSourceByReferralSource(psReferralSource);
		String referralSourceId = "";
		legReferralSource = legReferralSource != null ? legReferralSource.trim().toUpperCase() : legReferralSource;
		if(legReferralSource == null || legReferralSource.isEmpty()) {
			if(psReferralSource == null || psReferralSource.isEmpty()) {
				legReferralSource = "";
				parameterMap.put("errorMessageParameter", "Referral source not selected in PeopleSoft.");
				Main.doErrorCommand(parameterMap);
			}
			else {
				legReferralSource = "O";  //if not found default to O (Other)
				referralSourceId = psReferralSourceDescription;
			}
		}
		parameterMap.put("referralSource", legReferralSource);
		//make referral source ID value 35 characters long
		parameterMap.put("referralSourceId", String.format("%1$-35s", referralSourceId));
		return parameterMap;
	}
	
	
}

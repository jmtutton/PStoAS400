package erd.controller;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import erd.model.PsPersonalData;
import erd.model.PsPersonalNationalId;
import erd.model.PsPersonalPhone;
import erd.model.PsRecruitmentSource;
import erd.model.PsReferralSource;

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
			if(psAddress.getAddress1() != null) {
				parameterMap.put("address", psAddress.getAddress1().trim().toUpperCase().replaceAll("'", "''"));
			}
			if(psAddress.getCity() != null) {
				parameterMap.put("city", psAddress.getCity().trim().toUpperCase().replaceAll("'", "''"));
			}
			if(psAddress.getState() != null) {
				parameterMap.put("state", psAddress.getState().trim().toUpperCase());
			}
			if(psAddress.getPostal() != null) {
				parameterMap.put("postalCode", psAddress.getPostal().trim());
			}
		}
		return parameterMap;
	}

	/**
	 * @see HR01-Get-Personal-Data
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchNameData(HashMap<String, Object> parameterMap) {
		PsName psPrimaryName = PsName.findByEmployeeIdAndNameTypeAndEffectiveDate((String)parameterMap.get("employeeId"), "PRI", (Date)parameterMap.get("effectiveDate"));
		String middleName = psPrimaryName.getMiddleName() != null ? psPrimaryName.getMiddleName().trim() : psPrimaryName.getMiddleName();
		String middleInitial = middleName!= null && !middleName.isEmpty() ? middleName.substring(0, 1).toUpperCase() : middleName;
		String firstName = psPrimaryName.getFirstName();
		if(firstName != null) {
			firstName = firstName.trim().toUpperCase();
			firstName = firstName.replaceAll("'", "''");
		}
		String lastName = psPrimaryName.getLastName();
		if(lastName != null) {
			lastName = lastName.trim().toUpperCase();
			lastName = lastName.replaceAll("'", "''");
		}
		String namePrefix = psPrimaryName.getNamePrefix();
		if(namePrefix != null) {
			namePrefix = psPrimaryName.getNamePrefix().trim().toUpperCase();
			if(namePrefix.length() > 3) { 
				namePrefix = namePrefix.substring(0, 3);
			}
		}
		String nameSuffix = psPrimaryName.getNameSuffix();
		if(nameSuffix != null && !nameSuffix.isEmpty()) {
			nameSuffix = psPrimaryName.getNameSuffix().trim().toUpperCase();
			lastName = lastName + nameSuffix;
		}
		parameterMap.put("lastName", lastName);
		parameterMap.put("firstName", firstName);
		parameterMap.put("middleInitial", middleInitial);
		parameterMap.put("namePrefix", namePrefix);
		PsName psPreferredName = PsName.findByEmployeeIdAndNameTypeAndEffectiveDate((String)parameterMap.get("employeeId"), "PRF", (Date)parameterMap.get("effectiveDate"));
		String nickname = psPreferredName.getFirstName();
		if(nickname != null) {
			nickname = nickname.trim().toUpperCase();
			nickname = nickname.replaceAll("'", "''");
		}
		parameterMap.put("nickname", nickname);
		return parameterMap;
	}

	/**
	 * Gets the employees data from the job table that needs to be interfaced to the legacy system.
	 * @see HR01-Get-Job-Data
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchJobData(HashMap<String, Object> parameterMap) {
		PsJob psJob = PsJob.findJobData((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate"));
		String company = psJob.getCompany();
		company = company != null ? company.trim().toUpperCase() : company;
		parameterMap.put("company", company);
		String location = psJob.getLocation();
		location = location != null ? location.trim().toUpperCase() : location;
		parameterMap.put("location", location);
		String regularOrTemporary = psJob.getRegTemp();
		regularOrTemporary = regularOrTemporary != null ? regularOrTemporary.trim().toUpperCase() : regularOrTemporary;
		parameterMap.put("regularOrTemporary", regularOrTemporary);
		String fullOrPartTime = psJob.getFullPartTime();
		fullOrPartTime = fullOrPartTime != null ? fullOrPartTime.trim().toUpperCase() : fullOrPartTime;
		parameterMap.put("fullOrPartTime", fullOrPartTime);
		String employeeClass = psJob.getEmplClass();
		employeeClass = employeeClass != null ? employeeClass.trim().toUpperCase() : employeeClass;
		parameterMap.put("employeeClass", employeeClass);
		String flsaStatus = psJob.getFlsaStatus();
		flsaStatus = flsaStatus != null ? flsaStatus.trim().toUpperCase() : flsaStatus;
		parameterMap.put("flsaStatus", flsaStatus);
		String departmentId = psJob.getDeptId();
		departmentId = departmentId != null ? departmentId : departmentId;
		parameterMap.put("departmentId", departmentId);
		String jobCode = psJob.getJobCode();
		jobCode = jobCode != null ? jobCode : jobCode;
		parameterMap.put("jobCode", jobCode);
		String setIdJobCode = psJob.getSetIdJobCode();
		if(setIdJobCode != null) {
			setIdJobCode = setIdJobCode.trim().toUpperCase();
			if(setIdJobCode.length() > 5) {
				setIdJobCode = setIdJobCode.substring(0, 5);
			}
		}
		parameterMap.put("setIdJobCode", setIdJobCode);
		String region = psJob.getRegulatoryRegion();
		region = region != null ? region.trim().toUpperCase() : region;
		parameterMap.put("region", region);
		parameterMap.put("effectiveDate", psJob.getEffectiveDate());
		return parameterMap;
	}
	
	/**
	 * @see HR01-Get-Main-Phone
	 * @see HR01-Get-Office-Phone
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchPhoneData(HashMap<String, Object> parameterMap) {
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
		PsEffectiveDatedPersonalData psEffectiveDatedPersonalData = PsEffectiveDatedPersonalData.findByEmployeeIdAndEffectiveDate((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate"));
		String maritalStatus = psEffectiveDatedPersonalData.getMaritalStatus();
		maritalStatus = maritalStatus != null ? maritalStatus.trim().toUpperCase() : maritalStatus;
		parameterMap.put("maritalStatus", maritalStatus);
		String gender = psEffectiveDatedPersonalData.getGender();
		gender = gender != null ? gender.trim().toUpperCase() : gender;
		parameterMap.put("gender", gender);
		return parameterMap;
	}

	/**
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchPersonData(HashMap<String, Object> parameterMap) {
		PsPerson psPerson = PsPerson.findByEmployeeId((String)parameterMap.get("employeeId"));
		Date birthDate = psPerson.getBirthDate();
		if(birthDate != null) {
			parameterMap.put("birthYear", new SimpleDateFormat("yyyy").format(birthDate));
			parameterMap.put("birthMonth", new SimpleDateFormat("mm").format(birthDate));
			parameterMap.put("birthDay", new SimpleDateFormat("dd").format(birthDate));
		}
		return parameterMap;
	}

	/**
	 * @see HR01-Get-Personal-Data
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchPersonalData(HashMap<String, Object> parameterMap) {
		PsPersonalData psPersonalData = PsPersonalData.findByEmployeeId((String)parameterMap.get("employeeId"));
		String languageCode = psPersonalData.getLanguageCode();
		if(languageCode != null) {
			languageCode = languageCode.trim().toUpperCase();
		}
		parameterMap.put("languageCode", languageCode);
		return parameterMap;
	}

	/**
	 * @see HR01-Get-Group
	 * @see HR01-Get-Branch
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchCompanyData(HashMap<String, Object> parameterMap) {
		String group = CrossReferenceCompany.findActiveLegacyGroupByCompany((String)parameterMap.get(""));
		if(group == null || group.isEmpty()) {
			parameterMap.put("errorMessageParameter", "The Company entered is not in the cross reference table PS_ZHRT_CMPNY_CREF");
			parameterMap.put("criticalFlag", "Y");
			Main.doErrorCommand(parameterMap);
			parameterMap.put("criticalFlag", "N");
		}
		group = group != null ? group.trim().toUpperCase() : group;
		parameterMap.put("group", group);
		String branch = CrossReferencePt12p.findActiveBranchByDepartment((String)parameterMap.get("department"));
		branch = branch != null ? branch.trim().toUpperCase() : branch;
		parameterMap.put("branch", branch);
		return parameterMap;
	}

	/**
	 * Get the Legacy position from the cross reference table using the jobcode setid, jobcode, empl_class, full_part_time, reg_temp, and deptid from PeopleSoft
	 * @see HR01-Get-Position
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchJobCodeData(HashMap<String, Object> parameterMap) {
		CrossReferenceJobCode crossReferenceJobCode = CrossReferenceJobCode.findActiveBySetIdJobCodeAndJobCodeAndEmployeeClassAndFullOrPartTimeAndRegularOrTemporaryAndDepartment(
				(String)parameterMap.get("setIdJobCode"), (String)parameterMap.get("jobCode"), 
				(String)parameterMap.get("employeeClass"), (String)parameterMap.get("fullOrPartTime"), 
				(String)parameterMap.get("regularOrTemporary"), (String)parameterMap.get("departmentId"));
		String position = crossReferenceJobCode.getLegacyPositionCode();
		position = position != null ? position.trim().toUpperCase() : position;
		parameterMap.put("position", position);
		String department = crossReferenceJobCode.getLegacyDepartmentCode();
		department = department != null ? department.trim().toUpperCase() : department;
		parameterMap.put("department", department);
		String jobStatus = crossReferenceJobCode.getLegacyJobStatusCode();
		jobStatus = jobStatus != null ? jobStatus.trim().toUpperCase() : jobStatus;
		parameterMap.put("jobStatus", jobStatus);
		return parameterMap;
	}

	/**
	 * 
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchServiceDateData(HashMap<String, Object> parameterMap) {
		Date serviceDate = (Date)parameterMap.get("effectiveDate");
		if(serviceDate != null) {
			parameterMap.put("serviceYear", new SimpleDateFormat("yyyy").format(serviceDate));
			parameterMap.put("serviceMonth", new SimpleDateFormat("mm").format(serviceDate));
			parameterMap.put("serviceDay", new SimpleDateFormat("dd").format(serviceDate));
		}
		return parameterMap;
	}
	
	public static HashMap<String, Object> fetchPersonalNationalIdData(HashMap<String, Object> parameterMap) {
		PsPersonalNationalId psPersonalNationalId = PsPersonalNationalId.findPrimaryByEmployeeIdAndCountry((String)parameterMap.get("employeeId"), (String)parameterMap.get("region"));
		String nationalId = psPersonalNationalId.getNationalId();
		nationalId = nationalId != null ? nationalId.trim().toUpperCase() : nationalId;
		parameterMap.put("nationalId", nationalId);
		String countryIsoAlpha3Code = psPersonalNationalId.getCountry();
		String nationalIdCountry = PsCountry.findCountryIsoAlpha2CodeByCountryIsoAlpha3Code(countryIsoAlpha3Code);
		nationalIdCountry = nationalIdCountry != null ? nationalIdCountry.trim().toUpperCase() : nationalIdCountry;
		parameterMap.put("nationalIdCountry", nationalIdCountry);
		return parameterMap;
	}

	/**
	 * @see HR01-Get-Referral-Source
	 * @param parameterMap
	 * @return parameterMap
	 */
	public static HashMap<String, Object> fetchReferralSourceData(HashMap<String, Object> parameterMap) {
		BigInteger sourceId = PsReferralSource.findByEmployeeIdAndEffectiveDate((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate")).getSourceId(); //PS_PERS_APPL_REF
		PsRecruitmentSource psRecruitmentSource = PsRecruitmentSource.findBySourceId(sourceId); //PS_HRS_SOURCE_I
		String psReferralSource = psRecruitmentSource.getSourceName();
		String psReferralSourceDescription = psRecruitmentSource.getSourceDescription();
		String legReferralSource = CrossReferenceReferralSource.findActiveLegacyRecruiterSourceByReferralSource((String)parameterMap.get(""));
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
		return null;
	}
	
	
}

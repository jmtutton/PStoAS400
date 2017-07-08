package erd.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import erd.DateUtil;
import erd.StringUtil;
import erd.model.CrossReferenceCompany;
import erd.model.CrossReferenceEmployeeId;
import erd.model.CrossReferenceEthnicGroup;
import erd.model.CrossReferencePt12p;
import erd.model.CrossReferenceReferralSource;
import erd.model.PsAddress;
import erd.model.PsCountry;
import erd.model.PsDiversityEthnicity;
import erd.model.PsDriversLicense;
import erd.model.PsEffectiveDatedPersonalData;
import erd.model.PsEmergencyContact;
import erd.model.PsEmployeeChecklist;
import erd.model.PsEthnicGroup;
import erd.model.PsJob;
import erd.model.PsName;
import erd.model.PsOriginalHire;
import erd.model.PsPersonalData;
import erd.model.PsPersonalNationalId;
import erd.model.PsPersonalPhone;
import erd.model.PsRecruitmentSource;
import erd.model.PsReferralSource;

/**
 * Employee Demographic Change Process
 * @see ZHRI105A.SQC
 * @author John Tutton john@tutton.net
 */
public class EmployeeDemographicChange {

	/**
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		//DO HR05-Initialize-Fields
		parameterMap = setUnusedParameters(parameterMap);
		//DO HR05-Get-Info
		parameterMap.put("maritalStatus", PsEffectiveDatedPersonalData.findMaritalStatusByEmployeeIdAndEffectiveDate((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate")));
		parameterMap = fetchAddress(parameterMap);
		//DO HR05-Get-Personal-Data
		parameterMap = fetchPersonalData(parameterMap);
		//DO HR05-Get-Referral-Source
		parameterMap = fetchReferralSource(parameterMap);
		//DO HR05-Get-Job
		parameterMap = fetchJob(parameterMap);
		//DO HR05-Get-Region
		parameterMap.put("regulatoryRegion", PsJob.findRegulatoryRegionByEmployeeId((String)parameterMap.get("employeeId")));
		//DO HR05-Get-Location-Country
//		parameterMap.put("regulatoryRegion", PsLocation.findCountryByLocation(location));
		//DO HR05-GET-2CHAR-COUNTRY
		//DO Get-OprId
		String operatorId = (String)parameterMap.get("operatorId");
		if(operatorId != null && operatorId.length() > 5) {
			parameterMap.put("operatorId", operatorId.substring(1, 5).trim());
		}
		//LET $PSEmpl = $PSOprid
		//IF $PSEmpl <> '' and $PSEmpl <> ' '
		//LET $PSNational_Id = ' '
		//DO HR05-Get-Business-Phone
		//DO HR05-Get-Main-Phone
		//DO HR05-Get-Names-Table
		//DO HR05-Get-Citizenship
		//DO HR05-Get-Diversity
		//DO HR05-Get-Drivers-Lic
		//DO HR05-Get-Emergency-Cntct
		//DO HR05-Get-Employee-Checklist
		//DO HR05-Process-Data
		
		System.out.println("*** EmployeeDemographicChange.doProcess()");
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", ZHRI100A.composeParameterString(parameterMap));
		return ZHRI100A.doCommand(parameterMap);
	}
	
	/**
	 * @param parameterMap
	 * @return parameterMap
	 */
	private static HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDemographicChange.fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ105A");
		parameterMap = fetchDriversLicense(parameterMap);
		parameterMap = fetchEmergencyContact(parameterMap);
		parameterMap = fetchName(parameterMap);
		parameterMap.put("changeDate", new SimpleDateFormat("yyyyMMdd").format(DateUtil.asOfToday()).toUpperCase());
		parameterMap.put("nationalId", PsPersonalNationalId.findNationalIdByEmployeeIdAndCountry((String)parameterMap.get("employeeId"), (String)parameterMap.get("region")));
		parameterMap.put("homePhone", PsPersonalPhone.findPhoneByEmployeeIdAndPhoneType((String)parameterMap.get("employeeId"), "MAIN"));
		parameterMap.put("businessPhone", PsPersonalPhone.findPhoneByEmployeeIdAndPhoneType((String)parameterMap.get("employeeId"), "BUSN"));
		parameterMap.put("citizenshipCountry", PsCountry.findCountryIsoAlpha2CodeByEmployeeId((String)parameterMap.get("employeeId")));
		parameterMap.put("nationalIdCountry", PsCountry.findCountryIsoAlpha2CodeByCountryCode((String)parameterMap.get("region")));
		parameterMap.put("recruiterId", fetchRecruiterId(parameterMap));
		parameterMap.put("nickname", fetchNickname(parameterMap));
		parameterMap.put("ethnicGroup", fetchLegacyEthnicCode(parameterMap));
		parameterMap.put("parameterNameList", getParameterNameList());
		return parameterMap;
	}
	
	/**
	 * @param parameterMap
	 * @return parameterMap
	 */
	private static HashMap<String, Object> setUnusedParameters(HashMap<String, Object> parameterMap) {
		//set unused parameters
		parameterMap.put("healthStat", "");
		parameterMap.put("healthStatDescription", "");
		parameterMap.put("vehicleReport", "0000");
		parameterMap.put("employeeSpouse", "");
		parameterMap.put("collegeName", "");
		parameterMap.put("collegeGradYear", "0000");
		parameterMap.put("collegeMajor", "");
		parameterMap.put("recruiterGroup", "");
		parameterMap.put("partTimeEffectiveDate", "");
		parameterMap.put("group", "");
		parameterMap.put("branch", "");
		parameterMap.put("startDate", "00000000");
		return parameterMap;
	}
	
	/**
	 * This routine Gets the nickname for employee and formats it in the form acceptable to the legacy system.
	 * @see HR05-Get-Names-Table in ZHRI105A.SQC
	 * @param employeeId
	 * @return nickname
	 */
	private static String fetchNickname(HashMap<String, Object> parameterMap) {
		String nickname = "";
		PsName psName = PsName.findByEmployeeIdAndEffectiveDateAndNameType((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate"), "PRF");
		if(psName != null && psName.getFirstName() != null) {
			nickname = psName.getFirstName().trim().toUpperCase().replaceAll("'", "''");
			if(nickname.length() > 20) {
				nickname.substring(0, 20);
			}
		}
		return nickname;
	}
	
	/**
	 * This routine gets the driver license number and state and stores them in the legacy system format.
	 * @see HR05-Get-Drivers-Lic procedure in ZHRI105A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	private static HashMap<String, Object> fetchDriversLicense(HashMap<String, Object> parameterMap) {
		PsDriversLicense psDriversLicense = PsDriversLicense.findByEmployeeId((String)parameterMap.get("employeeId"));
		if(psDriversLicense != null) {
			if(psDriversLicense.getDriversLicenseNumber() != null) {
				psDriversLicense.setDriversLicenseNumber(psDriversLicense.getDriversLicenseNumber().trim().toUpperCase().replaceAll("'", "''"));
			}
			if(psDriversLicense.getState() != null) {
				psDriversLicense.setState(psDriversLicense.getState().trim().toUpperCase());
			}
			parameterMap.put("driversLicenseNumber", psDriversLicense.getDriversLicenseNumber());
			parameterMap.put("driversLicenseState", psDriversLicense.getState());
		}
		return parameterMap;
	}
	
	/**
	 * This routine gets the emergency contact information from the PS_EMERGENCY_CNTCT table and converts it to the legacy system format.
	 * @see HR05-Get-Emergency-Cntct procedure in ZHRI105A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	private static HashMap<String, Object> fetchEmergencyContact(HashMap<String, Object> parameterMap) {
		PsEmergencyContact psEmergencyContact = PsEmergencyContact.findByEmployeeIdAndPrimaryContact((String)parameterMap.get("employeeId"));
		if(psEmergencyContact != null) {
    		if(psEmergencyContact.getContactName() != null) {
    			psEmergencyContact.setContactName(psEmergencyContact.getContactName().trim().toUpperCase().replaceAll("'", "''"));
    		}
    		psEmergencyContact.setPhone(psEmergencyContact.getPhone() != null ? psEmergencyContact.getPhone().trim().replaceAll("[^a-zA-Z0-9] ", "") : psEmergencyContact.getPhone());
    		psEmergencyContact.setRelationship(PsEmergencyContact.formatRelationship(psEmergencyContact.getRelationship()));
    		parameterMap.put("emergencyContactName", psEmergencyContact.getContactName());
    		parameterMap.put("emergencyContactPhone", psEmergencyContact.getPhone());
    		parameterMap.put("emergencyContactRelation", psEmergencyContact.getRelationship());
		}
		return parameterMap;
	}
	
	/**
	 * This routine will get the address info for the employee number.
	 * @see HR05-Get-Info in ZHRI105A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	private static HashMap<String, Object> fetchAddress(HashMap<String, Object> parameterMap) {
		PsAddress psAddress = PsAddress.findByEmployeeIdAndEffectiveDateAndAddressType((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate"), "HOME");
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
	 * This routine will get the name info for the employee number.
	 * @see HR05-Get-Info in ZHRI105A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	private static HashMap<String, Object> fetchName(HashMap<String, Object> parameterMap) {
		PsName psName = PsName.findByEmployeeIdAndEffectiveDateAndNameType((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate"), "PRI");
		if(psName != null) {
			if(psName.getName() != null) {
			    String name = StringUtil.formatPeopleSoftEmployeeNameToLegacyEmployeeName(psName.getFirstName(), psName.getMiddleName(), psName.getLastName(), psName.getNameSuffix());
				parameterMap.put("name", name.replaceAll("'", "''"));
			}
			if(psName.getNamePrefix() != null) {
				parameterMap.put("namePrefix", psName.getNamePrefix().trim().toUpperCase().replaceAll("'", "''"));
			}
		}
		return parameterMap;
	}
	
	/**
	 * This routine converts referral codes from PeopleSoft to legacy system.
	 * @see HR05-Get-Referral-Source in ZHRI105A.SQC
	 * @see HR05-Format-Referal-Source in ZHRI105A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	//TODO: not sure if this is 100% correct
	private static HashMap<String, Object> fetchReferralSource(HashMap<String, Object> parameterMap) {
		//BEGIN-PROCEDURE HR05-GET-REFERRAL-SOURCE
		String recruitmentSourceId = PsReferralSource.findRecruitmentSourceIdByEmployeeIdAndEffectiveDate((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate")); //PS_PERS_APPL_REF
		PsRecruitmentSource psRecruitmentSource = PsRecruitmentSource.findByRecruitmentSourceId(recruitmentSourceId); //PS_HRS_SOURCE_I
		//LET $PSRecruit_Source_Code = LTRIM(RTRIM(&CHSI2.HRS_SOURCE_NAME,' '),' ')       
		String referralSourceName = psRecruitmentSource.getSourceName();
		//DO HR05-FORMAT-REFERRAL-SOURCE                                                  
			//BEGIN-PROCEDURE HR05-FORMAT-REFERRAL-SOURCE
			//Let $Found = 'N'  !Initialize the found indicator
			//!Based on the value in the PeopleSoft Recruit Source Code assign the
			//!corresponding legacy system code that will be passed.
			//Let $PSReferral_Source = &CPT101.ZHRF_LEGRECRUITSRC
			String referralSource = CrossReferenceReferralSource.findLegacyRecruiterSourceByReferralSource(referralSourceName); //PS_ZHRT_RFSRC_CREF
			//IF ($Found = 'N')
			if(referralSource == null) {
				//LET $PSReferral_Source = ' '
				referralSource = "";
				//IF $PSRecruit_Source_Code = ' '   !If the Referral Source code was not entered in PS
				if(referralSourceName == null || referralSourceName.isEmpty()) {
					parameterMap.put("errorProgramParameter", "ZHRI105A");
					//LET $ErrorMessageParm = 'Referral source not selected in PeopleSoft.'
					parameterMap.put("errorMessageParameter", "Referral source not selected in PeopleSoft.");
					//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
					//DO Call-Error-Routine                 !From ZHRI100A.SQR
					ZHRI100A.doErrorCommand(parameterMap);
				}
				//ELSE
				else {
					//LET $PSSpecific_Refer_Src = SUBSTR($PSRefSourceDescr,1,35)                 
//					PSSpecific_Refer_Src = PSRefSourceDescr;
				//END-IF    !$PSRecruit_Source_Code = ' '
				}
			//END-IF    !$Found = 'N'
			}
			//!Make sure not less than 35 long
			//LET $PSSpecific_Refer_Src = SUBSTR($PSRefSourceDescr,1,35)                 
			//LET $PSSpecific_Refer_Src = RPAD($PSSpecific_Refer_Src,35,' ')
			String referralSourceId  = psRecruitmentSource.getSourceDescription();
			if(referralSourceId != null) {
				referralSourceId = referralSourceId.trim();
				if(referralSourceId.length() > 35) {
					referralSourceId = referralSourceId.substring(0, 35);
				}
				else if(referralSourceId.length() < 35) {
					referralSourceId = String.format("%1$-35s", referralSourceId);
				}
			}
			parameterMap.put("referralSourceId", referralSourceId);
			//END-PROCEDURE HR05-FORMAT-REFERRAL-SOURCE
		//Let $PSReferralSource = LTRIM(RTRIM(&CHSI2.HRS_SOURCE_NAME,' '),' ')            
//		String PSReferralSource = psRecruitmentSource.getSourceName();
		//END-PROCEDURE HR05-GET-REFERRAL-SOURCE
		referralSource = referralSource.trim();
		parameterMap.put("referralSource", referralSource);
		return parameterMap;
	}
	
	/**
	 * @see HR05-Call-RPG-Program in ZHRI105A.SQC
	 * @return list of parameter names for this process
	 */
	private static List<String> getParameterNameList() {
		return Arrays.asList("employeeId", "group", "region", "branch", "operatorId",
				"nationalId", "healthStat", "healthStatDescription",
				"vehicleReport", "name", "namePrefix", "nickname",
				"address", "city", "state", "postalCode",
				"homePhone", "businessPhone", "citizenshipCountry",
				"gender", "maritalStatus", "ethnicGroup",
				"changeDate", "birthDate", "partTimeEffectiveDate", "startDate",
				"driversLicenseNumber", "driversLicenseState",
				"emergencyContactName", "emergencyContactPhone", "emergencyContactRelation", "employeeSpouse",
				"referralSource", "recruiterGroup", "recruiterId", "referralSourceId",
				"collegeName","collegeGradYear","collegeMajor", "nationalIdCountry");
	}
	
	/**
	 * This routine gets the operator id for the Recruiter.
	 * @see HR05-Get-Next-OpId in ZHRI105A.SQC
	 * @return recruiterId
	 */
	private static String fetchRecruiterId(HashMap<String, Object> parameterMap) {
		String responsibleId = PsEmployeeChecklist.findByEmployeeIdAndChecklistDate((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate"));
		String recruiterId = CrossReferenceEmployeeId.findLegacyEmployeeIdByEmployeeId(responsibleId);
		//BEGIN-PROCEDURE HR05-GET-NEXT-OPID
		//LET $Found = 'N'
		//BEGIN-SELECT
		//COD.ZHRF_LEG_EMPL_ID
		//COD.Emplid
		//LET $PSRecruiter_Id = &COD.ZHRF_LEG_EMPL_ID
		//LET $Found = 'Y'
		//FROM PS_ZHRT_EMPID_CREF COD
		//WHERE COD.Emplid = $PSResponsible_Id
		//END-SELECT
		//IF ($Found = 'N')
		if(recruiterId == null) {
			//LET $Hld_Wrk_Emplid = $Wrk_Emplid
			//LET $Hld_LegEmplid = $LegEmplid
			//LET $Wrk_Emplid = $PSResponsible_Id
			//LET $LegEmplid //
			//DO Get-Legacy-OprId                              !From ZHRI100A.SQR
			recruiterId = ZHRI100A.fetchNewLegacyEmployeeId(parameterMap);
			//LET $PSRecruiter_ID = $LegEmplid
			//LET $Wrk_Emplid = $Hld_Wrk_Emplid
			//LET $LegEmplid = $Hld_LegEmplid
			//END-IF         !Found = 'N'
		}
		//END-PROCEDURE HR05-GET-NEXT-OPID
		return recruiterId;
	}
	
	/**
	 * This routine will get the Personal Data row for each of the employee numbers entered in the trigger file.
	 * @see HR05-Get-Personal-Data in ZHRI105A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	private static HashMap<String, Object> fetchPersonalData (HashMap<String, Object> parameterMap) {
		//BEGIN-PROCEDURE HR05-GET-PERSONAL-DATA
		PsPersonalData psPersonalData = PsPersonalData.findByEmployeeId((String)parameterMap.get("employeeId"));
		if(psPersonalData != null) {
			//TO_CHAR(COHE.ORIG_HIRE_DT, 'YYYY-MM-DD')  &CPD2Orig_Hire_Dt                         
			//CPD2.SEX
			//TO_CHAR(CPD2.Birthdate, 'YYYY-MM-DD')  &CPD2Birthdate
			//LET $PSGender = &CPD2.SEX
			parameterMap.put("gender", psPersonalData.getSex());
			//LET $PSBDate = &CPD2Birthdate
			//UNSTRING $PSBDate by '-' into $first $second $Third
			//LET $PSBirthdate = $Second || $Third || $first
			parameterMap.put("birthDate", new SimpleDateFormat("yyyyMMdd").format(psPersonalData.getBirthdate()).toUpperCase());
		}
		Date originalHireDate = PsOriginalHire.findOriginalHireDateByEmployeeId((String)parameterMap.get("employeeId"));
		//IF &CPD2Orig_Hire_Dt = ''
		if(originalHireDate != null) {
			//UNSTRING &CPD2Orig_Hire_dt by '-' into $first $second $Third
			//LET $PSStart_dt = $First || $Second || $Third
			parameterMap.put("startDate", new SimpleDateFormat("yyyyMMdd").format(originalHireDate).toUpperCase());
		//END-IF
		}
		//END-PROCEDURE HR05-GET-PERSONAL-DATA
		return parameterMap;
	}
	
	/**
	 * This routine gets the diversity information about an employee and converts it to the legacy system codes.
	 * @see HR05-Get-Diversity in ZHRI105A.SQC
	 * @return legacyEthnicCode
	 */
	private static String fetchLegacyEthnicCode(HashMap<String, Object> parameterMap) {
		String ethnicGroupCode = PsDiversityEthnicity.findEthnicGroupCodeByEmployeeId((String)parameterMap.get("employeeId"));
		String ethnicGroup = PsEthnicGroup.findEthnicGroupByEthnicGroupCode(ethnicGroupCode);
		String legacyEthnicCode = CrossReferenceEthnicGroup.findLegacyEthnicCodeByEthnicGroup(ethnicGroup);
		if(legacyEthnicCode == null || legacyEthnicCode.isEmpty()) {
			parameterMap.put("errorProgramParameter", "ZHRI105A");
			parameterMap.put("errorMessageParameter", "Ethnic Group is not found in XRef table PS_ZHRT_ETHCD_CREF");
			ZHRI100A.doErrorCommand(parameterMap);
		}
		return legacyEthnicCode;
	}
	
	/**
	 * @see HR05-Get-Job
	 * @param parameterMap
	 * @return
	 */
	private static HashMap<String, Object> fetchJob(HashMap<String, Object> parameterMap) {
		PsJob psJob = PsJob.findByEmployeeIdAndEffectiveDateAndEffectiveSequence((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate"), (BigInteger)parameterMap.get("effectiveSequence"));
		if(psJob != null) {
			parameterMap.put("PSDate", psJob.getLocation());
			parameterMap.put("PSLocation", psJob.getLocation());
			parameterMap.put("PSBusiness_unit", psJob.getLocation());
			parameterMap.put("PSCompany", psJob.getLocation());
			parameterMap.put("PSFullPartTime", psJob.getLocation());
			parameterMap.put("PSEmplClass", psJob.getLocation());
			parameterMap.put("PSEmplStatus", psJob.getLocation());
			parameterMap.put("PSDeptid", psJob.getLocation());
			parameterMap.put("PSJobcode", psJob.getLocation());
			CrossReferencePt12p crossReferencePt12p = CrossReferencePt12p.findByDepartment(psJob.getLocation()); //location == department
			if(crossReferencePt12p != null) {
				parameterMap.put("group", crossReferencePt12p.getGroup());
				parameterMap.put("branch", crossReferencePt12p.getBranch());
			}
			else {
				parameterMap.put("errorProgramParameter", "ZHRI105A");
				parameterMap.put("errorMessageParameter", "Location not in XRef table");
				ZHRI100A.doErrorCommand(parameterMap);
			}
			parameterMap.put("region", CrossReferenceCompany.findLegacyRegionByBusinessUnit(psJob.getBusinessUnit()));
			if("P".equalsIgnoreCase(psJob.getFullPartTime())) {
				parameterMap.put("partTimeEffectiveDate", new SimpleDateFormat("yyyyMMdd").format((Date)parameterMap.get("effectiveDate")).toUpperCase());
			}
		    //Do HR05-Get-Location
		    //Do HR05-Get-Business-Unit
		}
		return parameterMap;
	}

	
}

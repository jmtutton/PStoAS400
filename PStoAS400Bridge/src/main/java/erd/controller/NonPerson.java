package erd.controller;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import erd.model.CrossReferenceMultipleEmployeeId;
import erd.model.CrossReferencePersonOfInterest;
import erd.model.CrossReferencePersonOfInterestEmployee;
import erd.model.PsEffectiveDatedPersonalData;
import erd.model.PsName;
import erd.model.PszPeopleToolsTranslation;

public class NonPerson {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	/**
	 * @see HR201-Get-Personal-Data
	 * @see "PS_NAMES"
	 * @param parameterMap
	 * @return parameterMap
	 */
	public HashMap<String, Object> fetchPsNamesData(HashMap<String, Object> parameterMap) {
		if(Main.DEBUG) logger.debug("fetchPsNamesData()");
		PsName psName = PsName.findByEmployeeIdAndNameTypeAndEffectiveDate((String)parameterMap.get("employeeId"), "PRI", (Date)parameterMap.get("effectiveDate"));  //PS_NAMES
		//LET $PSFirstName = LTRIM(RTRIM(&PS_NAMES.FIRST_NAME,' '),' ')
		String firstName = psName.getFirstName();
		firstName = firstName != null ? firstName.trim().toUpperCase().replaceAll("'", "''") : firstName;
		parameterMap.put("firstName", firstName);
		//LET $PSMiddleName = LTRIM(RTRIM(&PS_NAMES.MIDDLE_NAME,' '),' ')
		String middleName = psName.getMiddleName();
		middleName = middleName != null ? middleName.trim().toUpperCase().replaceAll("'", "''") : middleName;
		//LET $LegMiddleInit = SUBSTR($PSMiddleName, 1, 1)
		String middleInitial = middleName.length() > 0 ? middleName.substring(0, 1) : middleName;
		parameterMap.put("middleInitial", middleInitial);
		//LET $PSLastName = LTRIM(RTRIM(&PS_NAMES.LAST_NAME,' '),' ')
		String lastName = psName.getLastName();
		lastName = lastName != null ? lastName.trim().toUpperCase().replaceAll("'", "''") : lastName;
		parameterMap.put("lastName", (String)parameterMap.get("preferredLastName") + lastName);
		return parameterMap;
	}

	/**
	 * @see HR201-Get-Emp-Data
	 * @see "PS_ZHRR_MULTPL_EID"
	 * @param parameterMap
	 * @return parameterMap
	 * @precondition parameterMap must contain key="employeeId"
	 * @precondition parameterMap must contain key="eidIndexNumber"
	 * @precondition parameterMap must contain key="effectiveDate"
	 */
	public HashMap<String, Object> fetchMultipleEmployeeIdData(HashMap<String, Object> parameterMap) {
		CrossReferenceMultipleEmployeeId crMultEmp = CrossReferenceMultipleEmployeeId.findByEmployeeIdAndEidIndexNumberAndEffectiveDate((String)parameterMap.get("employeeId"), (BigInteger)parameterMap.get("eidIndexNumber"), (Date)parameterMap.get("effectiveDate"));
		String group = crMultEmp.getGroup();
		group = group != null ? group.trim().toUpperCase() : group;
		parameterMap.put("group", group);
		String branch = crMultEmp.getBranch();
		branch = branch != null ? branch.trim().toUpperCase() : branch;
		parameterMap.put("branch", branch);
		parameterMap.put("nickname", "");
		parameterMap.put("department", "A");
		parameterMap.put("referralSource", "");
		parameterMap.put("address", "");
		parameterMap.put("city", "");
		String altEidType = crMultEmp.getAltEidType();
		altEidType = altEidType != null ? altEidType.trim().toUpperCase() : altEidType;
		parameterMap.put("altEidType", altEidType);
		String position = "";
		if("E".equalsIgnoreCase(CrossReferencePersonOfInterestEmployee.findTypeByEmployeeId((String)parameterMap.get("employeeId")))) {
			//DO HR201-Get-PrimEid-PoiData
			parameterMap = fetchPrimeEidPersonOfInterestData(parameterMap);
		}
		else {
			position = "98";  //!for Alternate EIDs who are employees, Leg Position will be 98
			//DO HR201-Get-Alternate_Type
			parameterMap = fetchPeopleToolsTranslationData(parameterMap);
		}
		parameterMap.put("position", position);
		return parameterMap;
	}

	/**
	 * @see HR201-Get-PrimEid-PoiData
	 * @see "PS_ZHRT_PER_POI_TR"
	 * @precondition parameterMap must contain key="employeeId"
	 * @param parameterMap
	 * @return parameterMap
	 */
	public HashMap<String, Object> fetchPrimeEidPersonOfInterestData(HashMap<String, Object> parameterMap) {
		CrossReferencePersonOfInterest crefPoi = CrossReferencePersonOfInterest.findActiveByEmployeeId((String)parameterMap.get("employeeId"));
		String poiCategory = crefPoi.getCategory();  //LTRIM(RTRIM(&PED.ZHRF_POI_CATEGORY,' '),' ')
		poiCategory = poiCategory != null ? poiCategory.trim().toUpperCase() : poiCategory;
		parameterMap.put("poiCategory", poiCategory);
		String poiAffil = crefPoi.getAffil();  //LTRIM(RTRIM(&PED.Z_POI_AFFIL_CD ,' '),' ') 
		poiAffil = poiAffil != null ? poiAffil.trim().toUpperCase() : poiAffil;
		parameterMap.put("poiAffil", poiAffil);
		String nickname = new SimpleDateFormat("dd-MMM-yyyy").format(crefPoi.getExpectedEndDate()).trim().toUpperCase();  //LTRIM(RTRIM(&POI.EXPECTED_END_DATE,' '),' ') 
		parameterMap.put("nickname", nickname);
		String referralSource  = crefPoi.getManagerId();  //LTRIM(RTRIM(&PED.MANAGER_ID,' '),' ') 
		referralSource = referralSource != null ? referralSource.trim().toUpperCase().replaceAll("'", "''") : referralSource;
		parameterMap.put("referralSource", referralSource);
		String address = crefPoi.getPassword1();  //LTRIM(RTRIM(&PED.ZHRF_PWORD_1,' '),' ')                      
		address = address != null ? address.trim().toUpperCase().replaceAll("'", "''") : address;
		parameterMap.put("address", address);
		String city = crefPoi.getPassword2(); //LTRIM(RTRIM(&PED.ZHRF_PWORD_2,' '),' ') 
		city = city != null ? city.trim().toUpperCase().replaceAll("'", "''") : city;
		parameterMap.put("city", city);
		//DO HR201-Get-POI-LegPosNo
		parameterMap = fetchPoiLegacyPositionNumberData(parameterMap);
		return parameterMap;
	}

	/**
	 * @see HR201-Get-Alternate_Type
	 * @see "PS_ZPTT_XLAT_TBL"
	 * @precondition parameterMap must contain key="employeeType"
	 * @param parameterMap
	 * @return parameterMap
	 */
	public HashMap<String, Object> fetchPeopleToolsTranslationData(HashMap<String, Object> parameterMap) {
		PszPeopleToolsTranslation pszPtt = PszPeopleToolsTranslation.findActiveByInput01AndInput02AndInput03("TEMPMAST", "Alternate EID Type", (String)parameterMap.get("employeeType"));
		//LET $PrefLastName = LTRIM(RTRIM(&Z1.ZPTF_OUTPUT_01,' '),' ')
		String preferredLastName = pszPtt.getOutput01();
		preferredLastName = preferredLastName != null ? preferredLastName.trim().toUpperCase().replaceAll("'", "''") : preferredLastName;
		//IF $PrefLastName <> ''
		if(preferredLastName != null && !preferredLastName.isEmpty()) {
			//let $PrefLastName = $PrefLastName || ' '
			preferredLastName = preferredLastName + " ";
		}
		parameterMap.put("preferredLastName", preferredLastName);
		return parameterMap;
	}

	/**
	 * @see HR201-Get-POI-LegPosNo
	 * @see "PS_ZPTT_XLAT_TBL"
	 * @param parameterMap
	 * @return parameterMap
	 * @precondition parameterMap must contain key="poiCategory"
	 * @precondition parameterMap must contain key="poiAffil"
	 */
	public HashMap<String, Object> fetchPoiLegacyPositionNumberData(HashMap<String, Object> parameterMap) {
		PszPeopleToolsTranslation pszPtt = PszPeopleToolsTranslation.findActiveByInput01AndInput02AndInput03("TEMPMAST", "POSITION", (String)parameterMap.get("poiCategory"));
		String position = pszPtt.getOutput01();  //LTRIM(RTRIM(&Z.ZPTF_OUTPUT_01,' '),' ')
		position = position != null ? position.trim().toUpperCase() : position;
		String preferredLastName = pszPtt.getOutput02(); //LTRIM(RTRIM(&Z.ZPTF_OUTPUT_02,' '),' ')
		preferredLastName = preferredLastName != null ? preferredLastName.trim().toUpperCase().replaceAll("'", "''") : preferredLastName;
		//IF $PrefLastname <> ''
		if(preferredLastName != null && !preferredLastName.isEmpty()) {
		    //LET $PrefLastname = $PrefLastname || ' '
			preferredLastName = preferredLastName + " ";
		}
		String affMailbox = pszPtt.getOutput03();  //LTRIM(RTRIM(&Z.ZPTF_OUTPUT_03,' '),' ')
		affMailbox = affMailbox != null ? affMailbox.trim().toUpperCase() : affMailbox;
		//IF $POICat = 'OTH' AND $AffMailbox = $POIAffCd
		if("OTH".equalsIgnoreCase((String)parameterMap.get("poiCategory")) 
				&& (affMailbox != null && affMailbox.equalsIgnoreCase((String)parameterMap.get("poiAffil")))) {
			position = pszPtt.getOutput04();  //LTRIM(RTRIM(&Z.ZPTF_OUTPUT_04,' '),' ')   !for OTH and MailBox, Leg Position will be different
		}
		position = position != null ? position.trim().toUpperCase() : position;
		parameterMap.put("position", position);
		parameterMap.put("preferredLastName", preferredLastName);
		return parameterMap;
	}

	/**
	 * @see HR201-Get-POI-Data
	 * @see "PS_ZHRT_PER_POI_TR"
	 * @param parameterMap
	 * @return parameterMap
	 */
	public HashMap<String, Object> fetchPersonOfInterestData(HashMap<String, Object> parameterMap) {
		CrossReferencePersonOfInterest crefPoi = CrossReferencePersonOfInterest.findByEmployeeIdAndEffectiveDate((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate"));
		String poiCategory = crefPoi.getCategory();
		poiCategory = poiCategory != null ? poiCategory.trim().toUpperCase() : poiCategory;
		parameterMap.put("poiCategory", poiCategory);
		String poiAffil = crefPoi.getAffil();
		poiAffil = poiAffil != null ? poiAffil.trim().toUpperCase() : poiAffil;
		parameterMap.put("poiAffil", poiAffil);
		String group = crefPoi.getGroup();
		group = group != null ? group.trim().toUpperCase() : group;
		parameterMap.put("group", group);
		String branch = crefPoi.getBranch();
		branch = branch != null ? branch.trim().toUpperCase() : branch;
		parameterMap.put("branch", branch);
		String nickname = new SimpleDateFormat("dd-MMM-yyyy").format(crefPoi.getExpectedEndDate()).trim().toUpperCase();
		parameterMap.put("nickname", nickname);
		parameterMap.put("department", "A");
		String referralSource = crefPoi.getManagerId();
		referralSource = referralSource != null ? referralSource.trim().toUpperCase().replaceAll("'", "''") : referralSource;
		parameterMap.put("referralSource", referralSource);
		String address = crefPoi.getPassword1();                   
		address = address != null ? address.trim().toUpperCase().replaceAll("'", "''") : address;
		parameterMap.put("address", address);
		String city = crefPoi.getPassword2(); 
		city = city != null ? city.trim().toUpperCase().replaceAll("'", "''") : city;
		parameterMap.put("city", city);
		parameterMap = fetchPoiLegacyPositionNumberData(parameterMap);
		return parameterMap;
	}
	
	/**
	 * @see HR201-Get-Personal-Data
	 * @see "PS_PERS_DATA_EFFDT"
	 * @param parameterMap
	 * @return parameterMap
	 */
	public HashMap<String, Object> fetchEffectiveDatedPersonalData(HashMap<String, Object> parameterMap) {
		PsEffectiveDatedPersonalData psEffectiveDatedPersonalData = PsEffectiveDatedPersonalData.findByEmployeeIdAndEffectiveDate((String)parameterMap.get("employeeId"), (Date)parameterMap.get("effectiveDate"));
		String gender = psEffectiveDatedPersonalData.getGender();
		gender = gender != null ? gender.trim().toUpperCase() : gender;
		gender = "U".equalsIgnoreCase(gender) ? "" : gender;
		parameterMap.put("gender", gender);
		return parameterMap;
	}

    /**
     * Gets the minimum active effective date for every an employee ID.
     * 		1 Gets the maximum effective date.
     * 		2 Gets the last inactive date before the maximum effective date. If null, uses dummy date.
     * 		3 Gets the minimum effective date after the last inactive
     * @see Main-Sql-Poi in ZHRISTDT.SQC PeopleCode file
     * @see PS_ZHRT_PER_POI_TR table
     * @param employeeId
     * @return
     */
    public static Date mainSqlPoi(String employeeId) {
    	Date maxEffectiveDate = CrossReferencePersonOfInterest.findMaxEffectiveDateByEmployeeId(employeeId);
		//DO Get-Last-Inactive-Dt-Poi
    	Date lastInactiveDate = CrossReferencePersonOfInterest.findLastInactiveDateByEmployeeIdAndMaxEffectiveDate(employeeId, maxEffectiveDate);
		//if there are no inactive rows before, assign dummy date
    	if(lastInactiveDate == null) {
    		//LET $S_Dt = STRTODATE('01-JAN-1900','DD-MON-YYYY')
    		try {
    			lastInactiveDate = (new SimpleDateFormat("dd-MMM-yyyy")).parse("01-JAN-1900");
			} 
    		catch (ParseException e) {
				e.printStackTrace();
			}
    	}
		//DO Get-Min-EffDt-Poi
    	Date minEffectiveDate = CrossReferencePersonOfInterest.findMinEffectiveDateByEmployeeIdAndLastInactiveDate(employeeId, lastInactiveDate);
		return minEffectiveDate;
    }
    
    /**
     * Gets the minimum active effective date for every an employee ID and index.
     * 		1 Gets the maximum effective date.
     * 		2 Gets the last inactive date before the maximum effective date. If null, uses dummy date.
     * 		3 Gets the minimum effective date after the last inactive
     * @see Main-Sql-Emp in ZHRISTDT.SQC PeopleCode file
     * @see PS_ZHRR_MULTPL_EID table
     * @param employeeId
     * @param employeeId
     * @return
     */
    public static Date mainSqlEmp(String employeeId, BigInteger eidIndexNumber) {
    	Date maxEffectiveDate = CrossReferenceMultipleEmployeeId.findMaxEffectiveDateByEmployeeIdAndEidIndexNumber(employeeId, eidIndexNumber);
		//DO Get-Last-Inactive-Dt-Emp
    	Date lastInactiveDate = CrossReferenceMultipleEmployeeId.findLastInactiveDateByEmployeeIdAndEidIndexNumberAndMaxEffectiveDate(employeeId, eidIndexNumber, maxEffectiveDate);
		//if there are no inactive rows before, assign dummy date
    	if(lastInactiveDate == null) {
    		try {
        		//LET $S_Dt = STRTODATE('01-JAN-1900','DD-MON-YYYY')
    			lastInactiveDate = (new SimpleDateFormat("dd-MMM-yyyy")).parse("01-JAN-1900");
			} 
    		catch (ParseException e) {
				e.printStackTrace();
			}
    	}
		//DO Get-Min-EffDt-Emp
    	Date minEffectiveDate = CrossReferenceMultipleEmployeeId.findMinEffectiveDateByEmployeeIdAndEidIndexNumberAndLastInactiveDate(employeeId, eidIndexNumber, lastInactiveDate);
		return minEffectiveDate;
    }
	
	/**
	 * @see HR201-Massage-Data
	 * @param parameterMap
	 * @return parameterMap
	 */
    public HashMap<String, Object> massageData(HashMap<String, Object> parameterMap) {
		return parameterMap;
	}
	
	
}

package erd.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import erd.model.PsAccomplishment;
import erd.model.PsContractData;
import erd.model.PsEmployeeReview;

/**
 * Employee Date Change Process
 * @see ZHRI107A.SQC
 * Employment Review, Accomplishment and Contract Data change
 * @author John Tutton john@tutton.net
 *
 */
public class EmployeeDateChange {

	/**
	 * @see HR07-Process-Main in ZHRI107A.SQC
	 * @param parameterMap
	 * @return
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDateChange.doProcess() ***");
		parameterMap = fetchProcessParameters(parameterMap);
		if(parameterMap.get("employeeId") != null && !((String)parameterMap.get("employeeId")).isEmpty()) {
			parameterMap.put("parameterString", ZHRI100A.composeParameterString(parameterMap));
			return ZHRI100A.doCommand(parameterMap);
		}
		return "E";
	}

	/**
	 * This will initialize the fields at each call.
	 * @see HR07-Initialize-Fields in ZHRI107A.SQC
	 * @param parameterMap
	 */
	public HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDateChange.fetchProcessParameters() ***");
		parameterMap.put("errorProgramParameter", "HRZ107A");
		parameterMap = setUnusedParameters(parameterMap);
		if(parameterMap.get("operatorId") != null && ((String)parameterMap.get("operatorId")).length() > 1) {
			//!Remove leading 'E' from the PS employee operator ID to comply with the 5 long legacy format
			parameterMap.put("operatorId", ((String)parameterMap.get("operatorId")).substring(1).trim().toUpperCase()); //strips the 'E' off of the id
		}
		parameterMap.put("employeeId", ZHRI100A.fetchLegacyEmployeeId(parameterMap));
		if(parameterMap.get("employeeId") != null) {
			parameterMap.put("employeeId", ((String)parameterMap.get("employeeId")).trim().toUpperCase());  //TODO
			parameterMap = fetchEmployeeReviewDates(parameterMap);
			parameterMap = fetchAccomplishmentDates(parameterMap);
			parameterMap = fetchContractDate(parameterMap);
		}
		parameterMap.put("parameterNameList", getParameterNameList());
		return parameterMap;
	}

	private HashMap<String, Object> setUnusedParameters(HashMap<String, Object> parameterMap) {
		parameterMap.put("hireYear", "");
		parameterMap.put("hireMonth", "");
		parameterMap.put("hireDay", "");
		parameterMap.put("terminationYear", "");
		parameterMap.put("terminationMonth", "");
		parameterMap.put("terminationDay", "");
		parameterMap.put("companySeniorityYear", "");
		parameterMap.put("companySeniorityMonth", "");
		parameterMap.put("companySeniorityDay", "");
		parameterMap.put("nextReviewYear", "");
		parameterMap.put("nextReviewMonth", "");
		parameterMap.put("nextReviewDay", "");
		parameterMap.put("lastReviewYear", "");
		parameterMap.put("lastReviewMonth", "");
		parameterMap.put("lastReviewDay", "");
		parameterMap.put("negDrugTestYear", "");
		parameterMap.put("negDrugTestMonth", "");
		parameterMap.put("negDrugTestDay", "");
		parameterMap.put("physTestYear", "");
		parameterMap.put("physTestMonth", "");
		parameterMap.put("physTestDay", "");
		parameterMap.put("contractYear", "");
		parameterMap.put("contractMonth", "");
		parameterMap.put("contractDay", "");
		return parameterMap;
	}

	/**
	 * This procedure retrieves the Next Review Date and Last Review Date from the 
	 * PeopleSoft Employee Review Table to send back to Option 7 of AAHR01 in legacy.
	 * @see HR07-Get-Employee-Review in ZHRI107A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	public HashMap<String, Object> fetchEmployeeReviewDates(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDateChange.fetchEmployeeReviewDates() ***");
		PsEmployeeReview psEmployeeReview = 
				PsEmployeeReview.findByEmployeeIdAndEffectiveDateAndEmploymentRecordNumber(
						(String)parameterMap.get("employeeId"), 
						(Date)parameterMap.get("effectiveDate"), new BigDecimal(0));
		//!Format next review date and last review date so legacy will accept it (MM field, DD field and CCYY field)
		if(psEmployeeReview.getNextReviewDt() != null) {
			parameterMap.put("nextReviewYear", new SimpleDateFormat("yyyy").format(psEmployeeReview.getNextReviewDt()));
			parameterMap.put("nextReviewMonth", new SimpleDateFormat("mm").format(psEmployeeReview.getNextReviewDt()));
			parameterMap.put("nextReviewDay", new SimpleDateFormat("dd").format(psEmployeeReview.getNextReviewDt()));
		}
		if(psEmployeeReview.getNextReviewDt() != null) {
			parameterMap.put("lastReviewYear", new SimpleDateFormat("yyyy").format(psEmployeeReview.getEffectiveDate()));
			parameterMap.put("lastReviewMonth", new SimpleDateFormat("mm").format(psEmployeeReview.getEffectiveDate()));
			parameterMap.put("lastReviewDay", new SimpleDateFormat("dd").format(psEmployeeReview.getEffectiveDate()));
		}
		return parameterMap;
	}
	
	/**
	 * This procedure retrieves the Negative Drug Test Date and Physical Test Date from the 
	 * PeopleSoft Accomplishments Table to send back to Option 7 of AAHR01.
	 * @see HR07-Get-Accomplishments in ZHRI107A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	public HashMap<String, Object> fetchAccomplishmentDates(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDateChange.fetchAccomplishmentDates() ***");
		List<String> accomplishmentCodeList = Arrays.asList("DRUGTST", "PHYS L3", "PHYS L4");
		PsAccomplishment psAccomplishment = PsAccomplishment.findByEmployeeIdAndAccomplishmentCodes((String)parameterMap.get("employeeId"), accomplishmentCodeList);
		if(psAccomplishment != null) {
			switch(psAccomplishment.getAccomplishmentCode().trim().toUpperCase()) {
				//!when accomplishment is equal to negative drug test
				case "DRUGTST":
					if(psAccomplishment.getDateIssued() != null) {
						//!Format negative drug test date so legacy will accept it (MM field, DD field and CCYY field)
						parameterMap.put("negDrugTestYear", new SimpleDateFormat("yyyy").format(psAccomplishment.getDateIssued()));
						parameterMap.put("negDrugTestMonth", new SimpleDateFormat("mm").format(psAccomplishment.getDateIssued()));
						parameterMap.put("negDrugTestDay", new SimpleDateFormat("dd").format(psAccomplishment.getDateIssued()));
					}
				break;
				//!when accomplishment is equal to physical test date for level 3 or level 4
				case "PHYS L3":
				case "PHYS L4":
					if(psAccomplishment.getDateIssued() != null) {
						//!Format physical test date so legacy will accept it (MM field, DD field and CCYY field)
						parameterMap.put("physTestYear", new SimpleDateFormat("yyyy").format(psAccomplishment.getDateIssued()));
						parameterMap.put("physTestMonth", new SimpleDateFormat("mm").format(psAccomplishment.getDateIssued()));
						parameterMap.put("physTestDay", new SimpleDateFormat("dd").format(psAccomplishment.getDateIssued()));
					}
				break;
				//!when equal to anything else, do not get issue date
				default:
				break;
			}
		}
		return parameterMap;
	}

	/**
	 * This procedure retrieves the contract date from the PeopleSoft Contract Data Table to send back to Option 7 of AAHR01.
	 * @see HR07-Get-Contract-Data in ZHRI107A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	public HashMap<String, Object> fetchContractDate(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDateChange.fetchContractDate() ***");
		PsContractData psContractData = PsContractData.findByEmployeeIdAndMaxBeginDate((String)parameterMap.get("employeeId"));
		parameterMap.put("contractYear", new SimpleDateFormat("yyyy").format(psContractData.getContractBeginDt()));
		parameterMap.put("contractMonth", new SimpleDateFormat("mm").format(psContractData.getContractBeginDt()));
		parameterMap.put("contractDay", new SimpleDateFormat("dd").format(psContractData.getContractBeginDt()));
		return null;
	}

	/**
	 * @see HR07-Call-RPG in ZHRI107A.SQC
	 * @return list of parameter names for this process
	 */
	private static List<String> getParameterNameList() {
		return Arrays.asList("employeeId","operatorId",
				"hireMonth", "hireDay", "hireYear",
				"terminationMonth", "terminationDay", "terminationYear",
				"lastReviewMonth", "lastReviewDay", "lastReviewYear",
				"nextReviewMonth", "nextReviewDay", "nextReviewYear",
				"negDrugTestMonth", "negDrugTestDay", "negDrugTestYear",
				"physTestMonth", "physTestDay","physTestYear",
				"contractMonth","contractDay", "contractYear",
				"companySeniorityMonth","companySeniorityDay","companySeniorityYear");
	}

}

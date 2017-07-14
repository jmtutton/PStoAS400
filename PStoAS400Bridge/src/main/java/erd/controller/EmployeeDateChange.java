package erd.controller;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import erd.ErdUtils;
import erd.ErdUtils.SplitDate;
import erd.model.PsAccomplishment;
import erd.model.PsContractData;
import erd.model.PsEmployeeReview;

/**
 * Employee Date Change Process
 * Employment Review, Accomplishment and Contract Data change
 * @see ZHRI107A.SQC
 * @author John Tutton john@tutton.net
 */
public class EmployeeDateChange {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	/**
	 * @see HR07-Process-Main in ZHRI107A.SQC
	 * @param parameterMap
	 * @return
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		logger.debug("doProcess()");
		parameterMap = fetchProcessParameters(parameterMap);
		if(parameterMap.get("employeeId") != null && !((String)parameterMap.get("employeeId")).isEmpty()) {
			parameterMap.put("parameterString", Main.composeParameterString(parameterMap));
			return Main.doCommand(parameterMap);
		}
		return "E";
	}

	/**
	 * @see HR07-Initialize-Fields in ZHRI107A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	public HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		logger.debug("fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ107A");
		parameterMap.put("parameterNameList", getParameterNameList());
		parameterMap = initializeParameters(parameterMap);
		parameterMap.put("employeeId", Main.fetchLegacyEmployeeId(parameterMap));
		parameterMap = fetchEmployeeReviewDates(parameterMap);
		parameterMap = fetchAccomplishmentDate(parameterMap);
		parameterMap = fetchContractDate(parameterMap);
		return parameterMap;
	}

	/**
	 * @see HR07-Initialize-Fields in ZHRI107A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	private HashMap<String, Object> initializeParameters(HashMap<String, Object> parameterMap) {
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
	 * PeopleSoft Employee Review Table.
	 * @see HR07-Get-Employee-Review in ZHRI107A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	public HashMap<String, Object> fetchEmployeeReviewDates(HashMap<String, Object> parameterMap) {
		logger.debug("fetchEmployeeReviewDates()");
		PsEmployeeReview psEmployeeReview = 
				PsEmployeeReview.findByEmployeeIdAndEffectiveDateAndEmploymentRecordNumber(
						(String)parameterMap.get("employeeId"), 
						(Date)parameterMap.get("effectiveDate"), new BigInteger("0"));
		if(psEmployeeReview != null ) {
			if(psEmployeeReview.getNextReviewDt() != null) {
				SplitDate splitDate = new ErdUtils().new SplitDate(psEmployeeReview.getNextReviewDt());
				parameterMap.put("nextReviewYear", splitDate.getYear());
				parameterMap.put("nextReviewMonth", splitDate.getMonth());
				parameterMap.put("nextReviewDay", splitDate.getDay());
			}
			if(psEmployeeReview.getEffectiveDate() != null) {
				SplitDate splitDate = new ErdUtils().new SplitDate(psEmployeeReview.getEffectiveDate());
				parameterMap.put("lastReviewYear", splitDate.getYear());
				parameterMap.put("lastReviewMonth", splitDate.getMonth());
				parameterMap.put("lastReviewDay", splitDate.getDay());
			}
		}
		return parameterMap;
	}
	
	/**
	 * This procedure retrieves the Negative Drug Test Date and Physical Test Date from the 
	 * PeopleSoft Accomplishments Table.
	 * @see HR07-Get-Accomplishments in ZHRI107A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	public HashMap<String, Object> fetchAccomplishmentDate(HashMap<String, Object> parameterMap) {
		logger.debug("fetchAccomplishmentDate()");
		List<String> accomplishmentCodeList = Arrays.asList("DRUGTST", "PHYS L3", "PHYS L4");
		PsAccomplishment psAccomplishment = PsAccomplishment.findByEmployeeIdAndAccomplishmentCodes((String)parameterMap.get("employeeId"), accomplishmentCodeList);
		if(psAccomplishment != null && psAccomplishment.getDateIssued() != null) {
			String accomplishmentCode = psAccomplishment.getAccomplishmentCode();
			accomplishmentCode = accomplishmentCode != null ? accomplishmentCode.trim() : accomplishmentCode;
			if("DRUGTST".equalsIgnoreCase(accomplishmentCode)) {
				SplitDate splitDate = new ErdUtils().new SplitDate(psAccomplishment.getDateIssued());
				parameterMap.put("negDrugTestYear", splitDate.getYear());
				parameterMap.put("negDrugTestMonth", splitDate.getMonth());
				parameterMap.put("negDrugTestDay", splitDate.getDay());
			}
			else if("PHYS L3".equalsIgnoreCase(accomplishmentCode) || "PHYS L4".equalsIgnoreCase(accomplishmentCode)) {
				SplitDate splitDate = new ErdUtils().new SplitDate(psAccomplishment.getDateIssued());
				parameterMap.put("physTestYear", splitDate.getYear());
				parameterMap.put("physTestMonth", splitDate.getMonth());
				parameterMap.put("physTestDay", splitDate.getDay());
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
		logger.debug("fetchContractDate()");
		PsContractData psContractData = PsContractData.findByEmployeeIdAndMaxBeginDate((String)parameterMap.get("employeeId"));
		if(psContractData != null && psContractData.getContractBeginDt() != null) {
			SplitDate splitDate = new ErdUtils().new SplitDate(psContractData.getContractBeginDt());
			parameterMap.put("contractYear", splitDate.getYear());
			parameterMap.put("contractMonth", splitDate.getMonth());
			parameterMap.put("contractDay", splitDate.getDay());
		}
		return parameterMap;
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

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
import erd.model.CrossReferenceTerminationReason;
import erd.model.PsActionReason;
import erd.model.PsJob;

/**
 * Employee Termination Process
 * Update the legacy tables associated with the HR Employee Termination Process.  
 * Only specific fields will be updated, those which were changed on the AAHR02 screen.
 * @see ZHRI102A.SQC
 * @author John Tutton john@tutton.net
 */
public class EmployeeTermination {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    
    /**
	 * This is the main processing procedure.
	 * @see HR02-Process-Main in ZHRI102A.SQC
	 * @param parameterMap
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		logger.debug("*** EmployeeTermination.doProcess()");
		String completionStatus = "E";
		if(parameterMap.get("employeeId") != null  && !((String)parameterMap.get("employeeId")).isEmpty()) {
			parameterMap = fetchProcessParameters(parameterMap);
			parameterMap.put("parameterString", Main.composeParameterString(parameterMap));
			completionStatus = Main.doCommand(parameterMap);
		}
		return completionStatus;
	}

	/**
	 * Fetches the termination reason by querying the PsActionReason table.
	 * @see HR02-Get-Action-Reason in ZHRI102A.SQC
	 * @param parameterMap
	 * @return parameterMap
	 */
	private static HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		logger.debug("*** EmployeeTermination.fetchProcessParameters()");
		parameterMap.put("errorProgramParameter", "HRZ102A");
		Date effectiveTerminationDate = ErdUtils.addDays((Date)parameterMap.get("effectiveDate"), 1);
		parameterMap.put("employeeId", Main.fetchLegacyEmployeeId(parameterMap));
		PsJob psJob = PsJob.findByEmployeeIdAndEffectiveDateAndEffectiveSequence(
				(String)parameterMap.get("employeeId"), effectiveTerminationDate, 
				(BigInteger)parameterMap.get("effectiveSequence"));
		if(psJob != null) {
			parameterMap = fetchDates(psJob, parameterMap);
			parameterMap = fetchTerminationCodes(psJob, parameterMap);
			parameterMap.put("terminationReason", fetchTerminationReason(psJob, (String)parameterMap.get("terminationCode")));
			if(parameterMap.get("terminationReason") == null) {
				parameterMap.put("errorMessageParameter", "Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF");
				Main.doErrorCommand(parameterMap);
				parameterMap.put("voluntaryOrInvoluntary", "V");
				parameterMap.put("terminationCode", "O");
				parameterMap.put("terminationReason", "ACTION REASON NOT SELECTED IN PS");
			}
		}
		parameterMap.put("parameterNameList", getParameterNameList());
		return parameterMap;
	}

	/**
	 * Fetches the termination reason by querying the PsActionReason table.
	 * @see HR02-Get-Action-Reason in ZHRI102A.SQC
	 * @param psJob
	 * @param processParameters
	 * @return terminationReason
	 */
	private static String fetchTerminationReason(PsJob psJob, String terminationCode) {
		logger.debug("*** EmployeeTermination.fetchTerminationReason()");
		String terminationReason = null;
		if("O".equalsIgnoreCase(terminationCode) && "TER".equalsIgnoreCase(psJob.getAction())) {
			terminationReason = PsActionReason.findReasonDescriptionByActionAndActionReason(psJob.getAction(), psJob.getActionReason());
		}
		if(terminationReason != null) {
			//remove non-alphanumeric characters, except for space
			terminationReason = terminationReason.replaceAll("[^a-zA-Z0-9] ", "");
		}
		return terminationReason;
	}

	/**
	 * Finds the termination code and voluntary/involuntary value by querying the CrossReferenceTerminationReason table.
	 * @see HR02-Get-Action-Reason in ZHRI102A.SQC
	 * @param psJob
	 * @param processParameters
	 * @return processParameters
	 */
	private static HashMap<String, Object> fetchTerminationCodes(PsJob psJob, HashMap<String, Object> parameterMap) {
		logger.debug("*** EmployeeTermination.fetchTerminationCodes()");
		CrossReferenceTerminationReason crossReferenceTerminationReason = CrossReferenceTerminationReason.findByActionAndActionReasonAndStatus(psJob.getAction(), psJob.getActionReason(), "A");
		if(crossReferenceTerminationReason != null) {
			parameterMap.put("voluntaryOrInvoluntary", crossReferenceTerminationReason.getLegacyTerminationCode());
			parameterMap.put("terminationCode", crossReferenceTerminationReason.getLegacyTerminationReason());
		}
		return parameterMap;
	}
	
	/**
	 * Splits the termination date and rehire date into YYYY, MM, and DD format.
	 * @param psJob
	 * @param processParameters
	 * @return processParameters
	 */
	private static HashMap<String, Object> fetchDates(PsJob psJob, HashMap<String, Object> parameterMap) {
		logger.debug("*** EmployeeTermination.fetchDates()");
		if(psJob.getEffectiveDate() != null) {
			SplitDate splitDate = new ErdUtils().new SplitDate(psJob.getEffectiveDate());
			if("REH".equals(psJob.getAction()) && "REH".equals(psJob.getActionReason())) {
				parameterMap.put("rehireYear", splitDate.getYear());
				parameterMap.put("rehireMonth", splitDate.getMonth());
				parameterMap.put("rehireDay", splitDate.getDay());
			}
			if("TER".equals(psJob.getAction()) || "RET".equals(psJob.getAction()) || "TWP".equals(psJob.getAction()) || "TWB".equals(psJob.getAction())) {
				parameterMap.put("terminationYear", splitDate.getYear());
				parameterMap.put("terminationMonth", splitDate.getMonth());
				parameterMap.put("terminationDay", splitDate.getDay());
			}
		}
		return parameterMap;
	}
	
	/**
	 * @see HR02-Process-Data in ZHRI102A.SQC
	 * @return list of parameter names for this process
	 */
	private static List<String> getParameterNameList() {
		return Arrays.asList("employeeId","terminationMonth","terminationDay", "terminationYear",
					"rehireMonth","rehireDay","rehireYear","voluntaryOrInvoluntary",
					"terminationCode","operatorId","terminationReason");
	}

}

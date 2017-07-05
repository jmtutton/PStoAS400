package erd.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
	
	/**
	 * This is the main processing procedure.
	 * @see HR02-Process-Main in ZHRI102A.SQC
	 * @param commonParameters
	 * @return completionStatus
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeTermination.doProcess() ***");
		String completionStatus = "E";
		Date effectiveTerminationDate = erd.DateUtil.addDays((Date)parameterMap.get("effectiveDate"), 1);
		PsJob psJob = PsJob.findJobByEmployeeIdAndEffectiveDateAndEffectiveSequence(
				(String)parameterMap.get("employeeId"), effectiveTerminationDate, 
				(BigDecimal)parameterMap.get("effectiveSequence"));
		if(psJob != null) {
			String employeeId = ZHRI100A.fetchLegacyEmployeeId(parameterMap);
			if(employeeId != null  && !employeeId.isEmpty()) {
				parameterMap.put("employeeId", employeeId);
				parameterMap = fetchProcessParameters(psJob, parameterMap);
				parameterMap.put("parameterString", composeParameterString(parameterMap));
				completionStatus = ZHRI100A.doCommand(parameterMap);
			}
		}
		return completionStatus;
	}

	/**
	 * Fetches the termination reason by querying the PsActionReason table.
	 * @see HR02-Get-Action-Reason in ZHRI102A.SQC
	 * @param psJob
	 * @return processParameters
	 */
	private static HashMap<String, Object> fetchProcessParameters(PsJob psJob, HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeTermination.fetchProcessParameters() ***");
		parameterMap.put("parameterNameList", getParameterNameList());
		parameterMap.put("errorProgramParameter", "HRZ102A");
		parameterMap.put("terminationReason", fetchTerminationReason(psJob, parameterMap));
		if(parameterMap.get("terminationReason") == null) {
			parameterMap.put("errorMessageParameter", "Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF");
			ZHRI100A.doErrorCommand(parameterMap);
			parameterMap.put("voluntaryOrInvoluntary", "V");
			parameterMap.put("terminationCode", "O");
			parameterMap.put("terminationReason", "ACTION REASON NOT SELECTED IN PS");
		}
		parameterMap = fetchDates(psJob, parameterMap);
		parameterMap = fetchTerminationCodes(psJob, parameterMap);
		return parameterMap;
	}

	/**
	 * Fetches the termination reason by querying the PsActionReason table.
	 * @see HR02-Get-Action-Reason in ZHRI102A.SQC
	 * @param psJob
	 * @param processParameters
	 * @return terminationReason
	 */
	private static String fetchTerminationReason(PsJob psJob, HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeTermination.fetchTerminationReason() ***");
		String terminationReason = null;
		if("O".equalsIgnoreCase((String)parameterMap.get("terminationCode")) && "TER".equalsIgnoreCase(psJob.getAction())) {
			terminationReason = PsActionReason.findReasonDescriptionByActionAndActionReason(psJob.getAction(), psJob.getActionReason());
		}
		if(terminationReason != null) {
			//remove non-alphanumeric characters
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
		System.out.println("*** EmployeeTermination.fetchTerminationCodes() ***");
		CrossReferenceTerminationReason crossReferenceTerminationReason = CrossReferenceTerminationReason.findByActionAndActionReasonAndStatus(psJob.getAction(), psJob.getActionReason(), "A");
		if(crossReferenceTerminationReason != null) {
			parameterMap.put("voluntaryOrInvoluntary", crossReferenceTerminationReason.getLegacyTerminationCode());
			parameterMap.put("terminationCode", crossReferenceTerminationReason.getLegacyTerminationReason());
		}
		return parameterMap;
	}

	/**
	 * Composes the string of termination process parameters
	 * @param processParameters
	 * @return string of single-quoted parameter
	 */
	private static String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeTermination.composeParameterString() ***");
		//the HRZ102A program requires termination reason parameter to be character long
		parameterMap.put("terminationReason", String.format("%1$-35s", parameterMap.get("terminationReason")));
		String parameterString = "'" + parameterMap.get("employeeId") + "' "
				+ "'" + parameterMap.get("terminationMonth") + "' "
				+ "'" + parameterMap.get("terminationDay") + "' "
				+ "'" + parameterMap.get("terminationYear") + "' "
				+ "'" + parameterMap.get("rehireMonth") + "' "
				+ "'" + parameterMap.get("rehireDay") + "' "
				+ "'" + parameterMap.get("rehireYear") + "' "
				+ "'" + parameterMap.get("voluntaryOrInvoluntary") + "' "
				+ "'" + parameterMap.get("terminationCode") + "' "
				+ "'" + parameterMap.get("operatorId") + "' "
				+ "'" + parameterMap.get("terminationReason") + "'";
		@SuppressWarnings("unchecked")
		List<String> parameterNameList = (List<String>)parameterMap.get("parameterNameList");
		parameterString = "";
		for(String parameterName : parameterNameList) {
			parameterString += "'" + (String)parameterMap.get(parameterName) + "' ";
		}
		return parameterString;
	}
	
	private static List<String> getParameterNameList() {
		return Arrays.asList("employeeId","terminationMonth","terminationDay", "terminationYear",
					"rehireMonth","rehireDay","rehireYear","voluntaryOrInvoluntary",
					"terminationCode","operatorId","terminationReason");
	}
	
	/**
	 * Splits the termination date and rehire date into YYYY, MM, and DD format.
	 * @param psJob
	 * @param processParameters
	 * @return processParameters
	 */
	private static HashMap<String, Object> fetchDates(PsJob psJob, HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeTermination.fetchDates() ***");
		if("REH".equals(psJob.getAction()) && "REH".equals(psJob.getActionReason())) {
			parameterMap.put("rehireYear", new SimpleDateFormat("yyyy").format(psJob.getEffectiveDate()));
			parameterMap.put("rehireMonth", new SimpleDateFormat("mm").format(psJob.getEffectiveDate()));
			parameterMap.put("rehireDay", new SimpleDateFormat("dd").format(psJob.getEffectiveDate()));
		}
		if("TER".equals(psJob.getAction()) || "RET".equals(psJob.getAction()) || "TWP".equals(psJob.getAction()) || "TWB".equals(psJob.getAction())) {
			parameterMap.put("terminationYear", new SimpleDateFormat("yyyy").format(psJob.getEffectiveDate()));
			parameterMap.put("terminationMonth", new SimpleDateFormat("MM").format(psJob.getEffectiveDate()));
			parameterMap.put("terminationDay", new SimpleDateFormat("dd").format(psJob.getEffectiveDate()));
		}
		return parameterMap;

	}

}

package erd.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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
	public String processEmployeeTermination(HashMap<String, Object> parameterMap) {
		System.out.println("********** processEmployeeTermination() ***");
		parameterMap.put("errorProgramParameter", "HRZ102A");
		String completionStatus = "E";
		Date effectiveTerminationDate = erd.DateUtil.addDays((Date)parameterMap.get("effectiveDate"), 1);
		PsJob psJob = PsJob.findJobByEmployeeIdAndEffectiveDateAndEffectiveSequence(
				(String)parameterMap.get("employeeId"), effectiveTerminationDate, 
				(BigDecimal)parameterMap.get("effectiveSequence"));
		if(psJob != null) {
			String employeeId = ZHRI100A.fetchLegacyEmployeeId(parameterMap);
			if(employeeId != null  && !employeeId.isEmpty()) {
				parameterMap = fetchProcessParameters(psJob, parameterMap);
				parameterMap.put("employeeId", employeeId);
				parameterMap.put("operatorId", (String)parameterMap.get("operatorId"));
				parameterMap = formatDates(psJob, parameterMap);
				String commandString = ZHRI100A.composeRexecCommandString((String)parameterMap.get("processName"), 
						composeParameterString(parameterMap));
				Integer status = ZHRI100A.executeRemoteCommand(commandString, parameterMap);
				//IF (#Status = 0)  //TODO
				if(status == 0) { //no error returned from process
					//completed normally
					completionStatus = "C";
				}
			}
		}
		return completionStatus;
	}

	/**
	 * Fetchs the termination reason by querying the PsActionReason table.
	 * @see HR02-Get-Action-Reason in ZHRI102A.SQC
	 * @param psJob
	 * @return processParameters
	 */
	private HashMap<String, Object> fetchProcessParameters(PsJob psJob, HashMap<String, Object> parameterMap) {
		System.out.println("*** findTerminationProcessParameters() ***");
		String terminationReason = fetchTerminationReason(psJob, parameterMap);
		parameterMap.put("terminationReason", terminationReason);
		if(terminationReason == null) {
			parameterMap.put("errorMessageParameter", "Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF");
			ZHRI100A.executeErrorCommand(parameterMap);
			parameterMap.put("voluntaryOrInvoluntary", "V");
			parameterMap.put("terminationCode", "O");
			parameterMap.put("terminationReason", "ACTION REASON NOT SELECTED IN PS");
		}
		parameterMap = fetchTerminationCodes(psJob, parameterMap);
		return parameterMap;
	}

	/**
	 * Fetchs the termination reason by querying the PsActionReason table.
	 * @see HR02-Get-Action-Reason in ZHRI102A.SQC
	 * @param psJob
	 * @param processParameters
	 * @return terminationReason
	 */
	private String fetchTerminationReason(PsJob psJob, HashMap<String, Object> parameterMap) {
		System.out.println("*** findTerminationReason() ***");
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
	private HashMap<String, Object> fetchTerminationCodes(PsJob psJob, HashMap<String, Object> parameterMap) {
		System.out.println("*** fetchTerminationCodes() ***");
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
	public static String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("*** composeParameterString() ***");
		//the HRZ102A program requires termination reason parameter to be character long
		String terminationReason = String.format("%1$-35s", parameterMap.get("terminationReason"));
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
				+ "'" + terminationReason + "'";
		return parameterString;
	}
	
	/**
	 * Splits the termination date and rehire date into YYYY, MM, and DD format.
	 * @param psJob
	 * @param processParameters
	 * @return processParameters
	 */
	public static HashMap<String, Object> formatDates(PsJob psJob, HashMap<String, Object> parameterMap) {
		System.out.println("*** formatDates() ***");
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

package erd.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import erd.model.ProcessParameters;
import erd.model.ProcessParameters.TerminationProcessParameters;
import erd.model.CrossReferenceTerminationReason;
import erd.model.PsActionReason;
import erd.model.PsJob;
import erd.model.ProcessParameters.CommonParameters;

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
	public String processEmployeeTermination(CommonParameters commonParameters) {
		System.out.println("********** processEmployeeTermination() ***");
		commonParameters.setErrorProgramParameter("HRZ102A");
		String completionStatus = "E";
		Date effectiveTerminationDate = erd.DateUtil.addDays(commonParameters.getEffectiveDate(), 1);
		PsJob psJob = PsJob.findJobByEmployeeIdAndEffectiveDateAndEffectiveSequence(
				commonParameters.getEmployeeId(), effectiveTerminationDate, 
				commonParameters.getEffectiveSequence());
		if(psJob != null) {
			String employeeId = ZHRI100A.fetchLegacyEmployeeId(commonParameters);
			if(employeeId != null  && !employeeId.isEmpty()) {
				TerminationProcessParameters processParameters = fetchProcessParameters(psJob, commonParameters);
				processParameters.setEmployeeId(employeeId);
				processParameters.setOperatorId(commonParameters.getOperatorId());
				processParameters = formatDates(psJob, processParameters);
				String commandString = ZHRI100A.composeRexecCommandString(commonParameters.getProcessName(), 
						composeParameterString(processParameters));
				Integer status = ZHRI100A.executeRemoteCommand(commandString, commonParameters);
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
	private TerminationProcessParameters fetchProcessParameters(PsJob psJob, CommonParameters commonParameters) {
		System.out.println("*** findTerminationProcessParameters() ***");
		TerminationProcessParameters processParameters = new ProcessParameters().new TerminationProcessParameters();
		String terminationReason = fetchTerminationReason(psJob, processParameters);
		processParameters.setTerminationReason(terminationReason);
		if(terminationReason == null) {
			commonParameters.setErrorMessageParameter("Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF");
			ZHRI100A.executeErrorCommand(commonParameters);
			processParameters.setVoluntaryOrInvoluntary("V");
			processParameters.setTerminationCode("O");
			processParameters.setTerminationReason("ACTION REASON NOT SELECTED IN PS");
		}
		processParameters = fetchTerminationCodes(psJob, processParameters);
		return processParameters;
	}

	/**
	 * Fetchs the termination reason by querying the PsActionReason table.
	 * @see HR02-Get-Action-Reason in ZHRI102A.SQC
	 * @param psJob
	 * @param processParameters
	 * @return terminationReason
	 */
	private String fetchTerminationReason(PsJob psJob, TerminationProcessParameters processParameters) {
		System.out.println("*** findTerminationReason() ***");
		String terminationReason = null;
		if("O".equalsIgnoreCase(processParameters.getTerminationCode()) && "TER".equalsIgnoreCase(psJob.getAction())) {
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
	private TerminationProcessParameters fetchTerminationCodes(PsJob psJob, TerminationProcessParameters processParameters) {
		System.out.println("*** fetchTerminationCodes() ***");
		CrossReferenceTerminationReason crossReferenceTerminationReason = CrossReferenceTerminationReason.findByActionAndActionReasonAndStatus(psJob.getAction(), psJob.getActionReason(), "A");
		if(crossReferenceTerminationReason != null) {
			processParameters.setVoluntaryOrInvoluntary(crossReferenceTerminationReason.getLegacyTerminationCode());
			processParameters.setTerminationCode(crossReferenceTerminationReason.getLegacyTerminationReason());
		}
		return processParameters;
	}

	/**
	 * Composes the string of termination process parameters
	 * @param processParameters
	 * @return string of single-quoted parameter
	 */
	public static String composeParameterString(TerminationProcessParameters processParameters) {
		System.out.println("*** composeParameterString() ***");
		//the HRZ102A program requires termination reason parameter to be character long
		String terminationReason = String.format("%1$-35s", processParameters.getTerminationReason());
		String parameterString = "'" + processParameters.getEmployeeId() + "' "
				+ "'" + processParameters.getTerminationMonth() + "' "
				+ "'" + processParameters.getTerminationDay() + "' "
				+ "'" + processParameters.getTerminationYear() + "' "
				+ "'" + processParameters.getRehireMonth() + "' "
				+ "'" + processParameters.getRehireDay() + "' "
				+ "'" + processParameters.getRehireYear() + "' "
				+ "'" + processParameters.getVoluntaryOrInvoluntary() + "' "
				+ "'" + processParameters.getTerminationCode() + "' "
				+ "'" + processParameters.getOperatorId() + "' "
				+ "'" + terminationReason + "'";
		return parameterString;
	}
	
	/**
	 * Splits the termination date and rehire date into YYYY, MM, and DD format.
	 * @param psJob
	 * @param processParameters
	 * @return processParameters
	 */
	public static TerminationProcessParameters formatDates(PsJob psJob, TerminationProcessParameters processParameters) {
		System.out.println("*** formatDates() ***");
		if("REH".equals(psJob.getAction()) && "REH".equals(psJob.getActionReason())) {
			processParameters.setRehireYear(new SimpleDateFormat("yyyy").format(psJob.getEffectiveDate()));
			processParameters.setRehireMonth(new SimpleDateFormat("mm").format(psJob.getEffectiveDate()));
			processParameters.setRehireDay(new SimpleDateFormat("dd").format(psJob.getEffectiveDate()));
		}
		if("TER".equals(psJob.getAction()) || "RET".equals(psJob.getAction()) || "TWP".equals(psJob.getAction()) || "TWB".equals(psJob.getAction())) {
			processParameters.setTerminationYear(new SimpleDateFormat("yyyy").format(psJob.getEffectiveDate()));
			processParameters.setTerminationMonth(new SimpleDateFormat("MM").format(psJob.getEffectiveDate()));
			processParameters.setTerminationDay(new SimpleDateFormat("dd").format(psJob.getEffectiveDate()));
		}
		return processParameters;

	}

}

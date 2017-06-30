package erd.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import erd.model.ProcessParameters;
import erd.model.ProcessParameters.TerminationProcessParameters;
import erd.model.CrossReferenceTerminationReason;
import erd.model.PsActionReason;
import erd.model.PsJob;
import erd.model.PszTriggerEmployee;
import erd.model.ProcessParameters.CommonParameters;

/**
 * Employee Termination Process
 * Update the Legacy files which where modified by the HR program ZHRI102A.  
 * Only specific fields will be updated, those which were changed on the AAHR02 screen.
 * @see ZHRI102A.SQC
 * @author John Tutton john@tutton.net
 */

public class EmployeeTermination {
	
	/**
	 * This is the main processing procedure
	 * @see HR02-Process-Main in ZHRI102A.SQC
	 * @param trigger
	 * @param commonParameters
	 * @return completionStatus
	 */
	public String HR02_processMain(PszTriggerEmployee trigger, CommonParameters commonParameters) {
		System.out.println("********** HR02_processMain");
		commonParameters.setErrorProgramParameter("HRZ102A");
		TerminationProcessParameters terminationProcessParameters = new ProcessParameters().new TerminationProcessParameters();
		terminationProcessParameters.setEmployeeId(trigger.getEmployeeId());
		terminationProcessParameters.setOperatorId(trigger.getOperatorId());
		String completionStatus = "";
		Date effectiveTerminationDate = erd.DateUtil.addDays(commonParameters.getEffectiveDate(), 1);
		PsJob psJob = PsJob.findJobByEmployeeIdAndEffectiveDateAndEffectiveSequence(
				commonParameters.getEmployeeId(), effectiveTerminationDate, 
				commonParameters.getEffectiveSequence());
		String terminationReason = findTerminationReason(psJob, terminationProcessParameters);
		terminationProcessParameters.setTerminationReason(terminationReason);
		if(terminationReason == null) {
			commonParameters.setErrorMessageParameter("Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF");
			ZHRI100A.executeErrorCommand(commonParameters);
			terminationProcessParameters.setVoluntaryOrInvoluntary("V");
			terminationProcessParameters.setTerminationCode("O");
			terminationProcessParameters.setTerminationReason("ACTION REASON NOT SELECTED IN PS");
		}
		terminationProcessParameters = findTerminationCodes(psJob, terminationProcessParameters);
		String employeeId = ZHRI100A.findLegacyEmployeeId(commonParameters);
		commonParameters.setEmployeeId(employeeId);
		if(commonParameters.getEmployeeId() != null 
				&& !commonParameters.getEmployeeId().isEmpty() && psJob != null) {
			completionStatus = processTermination(psJob, terminationProcessParameters, commonParameters);
		}
		return completionStatus;
	}

//	/**
//	 * @see HR02-Process-Main in ZHRI102A.SQC
//	 * This is the main processing procedure
//	 */
//	public String HR02_processMain(PszTriggerEmployee trigger, CommonParameters commonParameters) {
//		System.out.println("********** HR02_processMain");
//		TerminationProcessParameters terminationProcessParameters = new ProcessParameters().new TerminationProcessParameters();
//		terminationProcessParameters.setEmployeeId(trigger.getEmployeeId());
//		terminationProcessParameters.setOperatorId(trigger.getOperatorId());
//		String completionStatus = "";
//		Date effectiveTerminationDate = erd.DateUtil.addDays(commonParameters.getEffectiveDate(), 1);
//		PsJob psJob = PsJob.findJobByEmployeeIdAndEffectiveDateAndEffectiveSequence(
//				commonParameters.getEmployeeId(), effectiveTerminationDate, 
//				commonParameters.getEffectiveSequence());
//		String terminationReason = findTerminationReason(psJob, terminationProcessParameters);
//		terminationProcessParameters.setTerminationReason(terminationReason);
//		if(terminationReason == null) {
//			commonParameters.setErrorMessageParameter("Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF");
//			ZHRI100A.executeErrorCommand(commonParameters);
//			terminationProcessParameters.setVoluntaryOrInvoluntary("V");
//			terminationProcessParameters.setTerminationCode("O");
//			terminationProcessParameters.setTerminationReason("ACTION REASON NOT SELECTED IN PS");
//		}
//		//DO ZHRI100A.Get-OprId
//		String employeeId = ZHRI100A.findLegacyEmployeeId(commonParameters);
//		//ZHRI100A.psOprId = psOprId;
////		commonParameters.setOperatorId(psOprId);
////		//LET $ADLegOprid = $psOprId
////		commonParameters.setLegacyOperatorId(psOprId);
//		//LET $PSEmpl = $psOprId
//		commonParameters.setEmployeeId(employeeId);
//		//IF $PSEmpl <> '' AND $PSEmpl <> ' '  
//		//!If the new OprId is not blank and it is not null on return
//		if(commonParameters.getEmployeeId() != null 
//				&& !commonParameters.getEmployeeId().isEmpty()
//				&& psJob != null) {
//			//LET $PSDate = $PSDateIn
//			//!Move the original date back to PSDate.
////			psDate = trigger.getEffectiveDate();
//			//DO HR02-Process-Data
//			completionStatus = HR02_processData(psJob, terminationProcessParameters, commonParameters);
//		//END-IF    !$PSEmpl <> '' and $PSEmpl <> ' '
//		}
////		makeAS400PackageForHRZ102AProcess(commonParameters, terminationProcessParameters);
////		makeAS400PackageForErrorProcess(trigger.getProcessName(), commonParameters);
//		return completionStatus;
//	}
	
	/**
	 * @see HR02-Process-Data in ZHRI102A.SQC
	 * This routine moves 'N' to change address parameter and calls the RPG program.
	 * @param employeeId
	 * @param psJob
	 * @return
	 */
	private String processTermination(PsJob psJob, TerminationProcessParameters terminationProcessParameters, CommonParameters commonParameters) {
		System.out.println("********** processTermination()");
		String completionStatus = "E";
		terminationProcessParameters = formatDates(psJob, terminationProcessParameters);
		String terminationReason = terminationProcessParameters.getTerminationReason();
		if(terminationReason != null) {
			//remove non-alphanumeric characters
			terminationReason = terminationReason.replaceAll("[^a-zA-Z0-9] ", "");
			terminationProcessParameters.setTerminationReason(terminationReason);
		}
		String commandString = ZHRI100A.composeAs400RexecCommandString(commonParameters.getProcessName(), 
				composeParameterStringForTerminationProcess(terminationProcessParameters));
		Integer status = ZHRI100A.executeRemoteCommand(commandString, commonParameters);
		//IF (#Status = 0)  //TODO
		if(status == 0) { //no error returned from process
			//completed normally
			completionStatus = "C";
		}
		return completionStatus;
	}
	
//	/**
//	 * @see HR02-Process-Data in ZHRI102A.SQC
//	 * This routine moves 'N' to change address parameter and calls the RPG program.
//	 * @param employeeId
//	 * @param psJob
//	 * @return
//	 */
//	private String HR02_processData(PsJob psJob, TerminationProcessParameters terminationProcessParameters, CommonParameters commonParameters) {
//		System.out.println("********** HR02_processData");
//		String completionStatus = "E";
//		//LET $PSChange = 'N'
//		//Boolean psChange = false; //not used
//
//		//Rehire
//		//IF ($PSAction = 'REH') AND ($PSAction_Reason = 'REH')
//		if("REH".equals(psJob.getAction()) && "REH".equals(psJob.getActionReason())) {
//			//LET $PSRehireYr = substr($PSDate,1,4)
//			terminationProcessParameters.setRehireYear(new SimpleDateFormat("yyyy").format(psJob.getEffectiveDate()));
//			//LET $PSRehireMnth = substr($PSDate,6,2)
//			terminationProcessParameters.setRehireMonth(new SimpleDateFormat("mm").format(psJob.getEffectiveDate()));
//			//LET $PSRehireDay = substr($PSDate,9,2)
//			terminationProcessParameters.setRehireDay(new SimpleDateFormat("dd").format(psJob.getEffectiveDate()));
//		//END-IF
//		}
//		
//		//Termination
//		//IF ($psAction = 'TER' OR $psAction = 'RET' OR $psAction = 'TWP' OR $psAction = 'TWB')
//		if("TER".equals(psJob.getAction()) || "RET".equals(psJob.getAction()) || "TWP".equals(psJob.getAction()) || "TWB".equals(psJob.getAction())) {
//			//LET $PSTermYr = substr($PSDate,1,4)
//			terminationProcessParameters.setTerminationYear(new SimpleDateFormat("yyyy").format(psJob.getEffectiveDate()));
//			//LET $PSTermMnth = substr($PSDate,6,2)
//			terminationProcessParameters.setTerminationMonth(new SimpleDateFormat("MM").format(psJob.getEffectiveDate()));
//			//LET $PSTermDay = substr($PSDate,9,2)
//			terminationProcessParameters.setTerminationDay(new SimpleDateFormat("dd").format(psJob.getEffectiveDate()));
////			System.out.println("************** psJob.getEffectiveDate(): " + psJob.getEffectiveDate());
//		//END-IF
//		}
//		
//		//DO Remove-Non-Letters-Numbers ($PSTermReason, $PSTermReason)        !ZRmvSpcChr.sqc
//		String terminationReason = terminationProcessParameters.getTerminationReason();
//		if(terminationReason != null) {
////			System.out.println("************** terminationReason: " + terminationReason);
//			terminationReason = terminationReason.replaceAll("[^a-zA-Z0-9] ", "");
//			terminationProcessParameters.setTerminationReason(terminationReason);
////			value.replaceAll("[\\W]|_", "");
////			System.out.println("************** terminationReason: " + terminationReason);
//		}
//		//DO HR02-Trim-Parameters     !Routine to trim the parameters to insure that there are not a larger number of blanks being passed
////		HR02_trimParameters(terminationProcessParameters);
////		String commandString = makeAS400PackageForHRZ102AProcess(commonParameters, terminationProcessParameters);
//		String commandString = ZHRI100A.composeAs400RexecCommandString(commonParameters.getProcessName(), 
//				composeParameterStringForTerminationProcess(terminationProcessParameters));
////		System.out.println(terminationProcessParameters.toString());
//		//DO Call-System   !From ZHRI100A.SQR
//		Integer status = ZHRI100A.executeRemoteCommand(commandString, commonParameters);
//		//IF (#Status = 0)
//		if(status == 0) { //no error returned from process
//			//LET $CompletionStatus = 'C'   !Completed Normally
//			completionStatus = "C";
//		//END-IF    !#Status = 0
//		}
//		return completionStatus;
//	}
	
//	/**
//	 * @see HR02-Trim-Parameters in ZHRI102A.SQC
//	 * This routine trims all leading and trailing blanks from the data.
//	 */
//	private TerminationProcessParameters HR02_trimParameters(TerminationProcessParameters terminationProcessParameters) {
//		System.out.println("********** HR02_trimParameters");
//		//LET $PSAuditOperId= RTRIM(LTRIM($PSAuditOperId,' '),' ')
//		//LET $PSEmpl = RTRIM(LTRIM($PSEmpl,' '),' ')
//		//LET $PSTermMnth = RTRIM(LTRIM($PSTermMnth,' '),' ')
//		//LET $PsTermDay = RTRIM(LTRIM($PsTermDay,' '),' ')
//		//LET $PSTermYr = RTRIM(LTRIM($PSTermYr,' '),' ')
//		//LET $PSReHireMnth = RTRIM(LTRIM($PSReHireMnth,' '),' ')
//		//LET $PSReHireDay = RTRIM(LTRIM($PSReHireDay,' '),' ')
//		//LET $PSReHireYr = RTRIM(LTRIM($PSReHireYr,' '),' ')
//		//LET $PSState = RTRIM(LTRIM($PSState,' '),' ')
//		//LET $PSZip = RTRIM(LTRIM($PSZip,' '),' ')
//		//LET $PSVolInvol = RTRIM(LTRIM($PSVolInvol,' '),' ')
//		//LET $PSTermCode = RTRIM(LTRIM($PSTermCode,' '),' ')
//		//LET $PSTermReason = RTRIM(LTRIM($PSTermReason,' '),' ')
//		//! $PSTermReason is at most 30 positions long, but HRZ102A receives it with a length of 35 positions.
//		//LET $PSTermReason = Rpad($PSTermReason,35,' ')  !Make sure not less than 35 long
//		return terminationProcessParameters;
//	}
	
//	/**
//	 * Retrieve the PsJob table data for this event.
//	 * @see HR02-Get-Job in ZHRI102A.SQC
//	 * @param terminationProcessParameters
//	 * @param commonParameters
//	 * @return
//	 */
//	private PsJob findJob(TerminationProcessParameters terminationProcessParameters, CommonParameters commonParameters) {
//		System.out.println("********** HR02_getJob");
//		PsJob psJob = PsJob.findJobByEmployeeIdAndEffectiveDateAndEffectiveSequence(
//				commonParameters.getEmployeeId(), 
//				erd.DateUtil.addDays(commonParameters.getEffectiveDate(), 1), 
//				commonParameters.getEffectiveSequence());
//		if(psJob != null && !"REH".equalsIgnoreCase(psJob.getAction())) {
//			String terminationReason = findTerminationReason(psJob, terminationProcessParameters);
//			terminationProcessParameters.setTerminationReason(terminationReason);
//			if(terminationReason == null) {
//				commonParameters.setErrorMessageParameter("Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF");
//				ZHRI100A.executeErrorCommand(commonParameters);
//				terminationProcessParameters.setVoluntaryOrInvoluntary("V");
//				terminationProcessParameters.setTerminationCode("O");
//				terminationProcessParameters.setTerminationReason("ACTION REASON NOT SELECTED IN PS");
//			}
//		}
//		return psJob;
//	}

	/**
	 * Find the termination reason by querying the PsActionReason table.
	 * @see HR02-Get-Action-Reason in ZHRI102A.SQC
	 * @param psJob
	 * @param terminationProcessParameters
	 * @return terminationReason
	 */
	private String findTerminationReason(PsJob psJob, TerminationProcessParameters terminationProcessParameters) {
		System.out.println("********** findTerminationReason()");
		String terminationReason = null;
		if("O".equalsIgnoreCase(terminationProcessParameters.getTerminationCode()) && "TER".equalsIgnoreCase(psJob.getAction())) {
			terminationReason = PsActionReason.findReasonDescriptionByActionAndActionReason(psJob.getAction(), psJob.getActionReason());
		}
		if(terminationReason != null) {
			//remove non-alphanumeric characters
			terminationReason = terminationReason.replaceAll("[^a-zA-Z0-9] ", "");
		}
		return terminationReason;
	}

	/**
	 * Find the termination code and voluntary/involuntary value by querying the CrossReferenceTerminationReason table.
	 * @see HR02-Get-Action-Reason in ZHRI102A.SQC
	 * @param psJob
	 * @param terminationProcessParameters
	 * @return terminationProcessParameters
	 */
	private TerminationProcessParameters findTerminationCodes(PsJob psJob, TerminationProcessParameters terminationProcessParameters) {
		System.out.println("********** findTerminationCodes()");
		CrossReferenceTerminationReason crossReferenceTerminationReason = CrossReferenceTerminationReason.findByActionAndActionReasonAndStatus(psJob.getAction(), psJob.getActionReason(), "A");
		if(crossReferenceTerminationReason != null) {
			terminationProcessParameters.setVoluntaryOrInvoluntary(crossReferenceTerminationReason.getLegacyTerminationCode());
			terminationProcessParameters.setTerminationCode(crossReferenceTerminationReason.getLegacyTerminationReason());
		}
		return terminationProcessParameters;
	}

//	/**
//	 * @see HR02-Get-Action-Reason in ZHRI102A.SQC
//	 * This routine will determine if a termination was voluntary or involuntary based on Action and Action Reason codes.
//	 * @param psJob
//	 * @return
//	 */
//	private TerminationProcessParameters HR02_getActionReason(PsJob psJob, TerminationProcessParameters terminationProcessParameters, CommonParameters commonParameters) {
//		System.out.println("********** HR02_getActionReason");
//		//LET $Found = 'N'   !Initialize the record found variable
//		Boolean found = false;
//		//BEGIN-SELECT
////		System.out.println("********** psJob.getAction(): " + psJob.getAction());
////		System.out.println("********** psJob.getActionReason(): " + psJob.getActionReason());
//		CrossReferenceTerminationReason crossReferenceTerminationReason = CrossReferenceTerminationReason.findByActionAndActionReasonAndStatus(psJob.getAction(), psJob.getActionReason(), "A");
////		System.out.println("********** crossReferenceTerminationReason == null: " + crossReferenceTerminationReason == null);
//		if(crossReferenceTerminationReason != null) {
////			System.out.println("********** crossReferenceTerminationReason != null");
//			//PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMCD
//			//LET $PSVolInvol = &PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMCD
//			terminationProcessParameters.setVoluntaryOrInvoluntary(crossReferenceTerminationReason.getLegacyTerminationCode());
//			//PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMRSN
//			//LET $PSTermCode = &PS_ZHRT_TRMRS_CREF.ZHRF_LEGTERMRSN
//			terminationProcessParameters.setTerminationCode(crossReferenceTerminationReason.getLegacyTerminationReason());
//			//IF $PSTermCode = 'O' AND $PSAction = 'TER'
////			System.out.println("************** processParameters.getTerminationCode(): " + terminationProcessParameters.getTerminationCode());
////			System.out.println("************** psJob.getAction(): " + psJob.getAction());
//			if("O".equalsIgnoreCase(terminationProcessParameters.getTerminationCode()) && "TER".equalsIgnoreCase(psJob.getAction())) {
//				//DO HR02-Get-Reason-Description
//				String terminationReason = PsActionReason.findReasonDescriptionByActionAndActionReason(psJob.getAction(), psJob.getActionReason());
//				terminationProcessParameters.setTerminationReason(terminationReason);
////				System.out.println("************** terminationReason: " + terminationReason);
//			//END-IF    !$PSTermCode = 'O'
//			}
//			//LET $Found = 'Y'     !Mark that a record was found
//			//FROM PS_ZHRT_TRMRS_CREF PS_ZHRT_TRMRS_CREF
//			//WHERE PS_ZHRT_TRMRS_CREF.STATUS = 'A'
//			//	AND PS_ZHRT_TRMRS_CREF.ACTION = $PSAction
//			//	AND PS_ZHRT_TRMRS_CREF.ACTION_REASON = $PSAction_Reason
//			//END-SELECT
//			found = true;
//		}
//		//IF $Found = 'N'
//		if(!found) {
//			//LET $ErrorMessageParm = 'Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF'
//			commonParameters.setErrorMessageParameter("Action Reason Code not found in XRef Tbl PS_ZHRT_TRMRS_CREF");
//			//DO Call-Error-Routine       !From ZHRI100A.SQR
////			String commandString = ZHRI100A.ZHRI100A_callErrorRoutine(commonParameters);
//			ZHRI100A.executeErrorCommand(commonParameters);
//			//!Default the Action and reason in the legacy system
//			//LET $PSVolInvol = 'V'
//			terminationProcessParameters.setVoluntaryOrInvoluntary("V");
//			//LET $PSTermCode = 'O'
//			terminationProcessParameters.setTerminationCode("O");
//			//LET $PSTermReason = 'ACTION REASON NOT SELECTED IN PS'
//			terminationProcessParameters.setTerminationReason("ACTION REASON NOT SELECTED IN PS");
//		//END-IF    !$Found = 'N'
//		}
//		return terminationProcessParameters;
//	}
	
//	/**
//	 * 
//	 * @param commonParameters
//	 * @param actionFields
//	 */
//	private String makeAS400PackageForHRZ102AProcess(CommonParameters commonParameters, TerminationProcessParameters terminationProcessParameters) {
//		System.out.println("********** makeAS400PackageForHRZ102AProcess");
//		String processName = "HRZ102A";
//		List<String> parameterList = new ArrayList<String>();
//		HashMap<String, String> parameterMap = new HashMap<String, String>();
//
//		String command = "\"CALL " + commonParameters.getAs400Library() + "/" + processName + " " 
//				+ "PARM('" + commonParameters.getEmployeeId() + "' "
//				+ "'" + terminationProcessParameters.getTerminationMonth() + "' "
//				+ "'" + terminationProcessParameters.getTerminationDay() + "' "
//				+ "'" + terminationProcessParameters.getTerminationYear() + "' "
//				+ "'" + terminationProcessParameters.getRehireMonth() + "' "
//				+ "'" + terminationProcessParameters.getRehireDay() + "' "
//				+ "'" + terminationProcessParameters.getRehireYear() + "' "
//				+ "'" + terminationProcessParameters.getVoluntaryOrInvoluntary() + "' "
//				+ "'" + terminationProcessParameters.getTerminationCode() + "' "
//				+ "'" + commonParameters.getAuditOperatorId() + "' "
//				+ "'" + terminationProcessParameters.getTerminationReason() + "')\" ";
//
//		parameterList.add("employeeId");
//		parameterList.add("terminationMonth");
//		parameterList.add("terminationDay");
//		parameterList.add("terminationYear");
//		parameterList.add("rehireMonth");
//		parameterList.add("rehireDay");
//		parameterList.add("rehireYear");
//		parameterList.add("voluntaryOrInvoluntary");
//		parameterList.add("terminationCode");
//		parameterList.add("auditOperatorId");
//		parameterList.add("terminationReason");
//
//		parameterMap.put("employeeId", commonParameters.getEmployeeId());
//		parameterMap.put("terminationMonth", terminationProcessParameters.getTerminationMonth());
//		parameterMap.put("terminationDay", terminationProcessParameters.getTerminationDay());
//		parameterMap.put("terminationYear", terminationProcessParameters.getTerminationYear());
//		parameterMap.put("rehireMonth", terminationProcessParameters.getRehireMonth());
//		parameterMap.put("rehireDay", terminationProcessParameters.getRehireDay());
//		parameterMap.put("rehireYear", terminationProcessParameters.getRehireYear());
//		parameterMap.put("voluntaryOrInvoluntary", terminationProcessParameters.getVoluntaryOrInvoluntary());
//		parameterMap.put("terminationCode", terminationProcessParameters.getTerminationCode());
//		parameterMap.put("auditOperatorId", commonParameters.getAuditOperatorId());
//		parameterMap.put("terminationReason", terminationProcessParameters.getTerminationReason());
//
////		AS400Package as400Package = new AS400Package(processName, parameterList, parameterMap);
////		return as400Package;
//		return command;
//	}

//	/**
//	 * 
//	 * @param processName
//	 * @param commonParameters
//	 * @return
//	 */
//	private AS400Package  makeAS400PackageForErrorProcess(String processName, CommonParameters commonParameters) {
//		System.out.println("********** makeAS400PackageForErrorProcess");
//		List<String> parameterList = new ArrayList<String>();
//		HashMap<String, String> parameterMap = new HashMap<String, String>();
//		String errorProgramParameter = commonParameters.getErrorProgramParameter();
//		String employeeId = commonParameters.getEmployeeId();
//		String blankSpaceParameter = " ";
//		String errorMessageParameter = commonParameters.getErrorMessageParameter();
//		String criticalFlag = commonParameters.getCriticalFlag() ? "Y" : "N";
//		Calendar now = Calendar.getInstance();
//		String errorDateParameter =  now.get(Calendar.MONTH) + "" + now.get(Calendar.DATE) + "" + now.get(Calendar.YEAR);
//		String errorTimeParameter = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);
//		String opridErrorParameter = commonParameters.getOperatorId();
//		String yesOrNoParameter = "Y"; //TODO: What should this value really be called
//		//!Make Sure that the ErrorMessageParm is always 75 Characters long
//		errorMessageParameter = String.format("%1$-75s", errorMessageParameter);
//		String command = "\"CALL " + commonParameters.getAs400Library() + "/" + processName + " "
//					+ "PARM("
//					+ "'" + errorProgramParameter + "' "
//					+ "'" + employeeId + "' "
//					+ "'" + blankSpaceParameter + "' "
//					+ "'" + errorMessageParameter + "' "
//					+ "'" + criticalFlag + "' "
//					+ "'" + errorDateParameter + "' "
//					+ "'" + errorTimeParameter + "' "
//					+ "'" + opridErrorParameter + "' "
//					+ "'" + yesOrNoParameter + "')\" ";
//		System.out.println(command);
//
//		parameterList.add("errorProgramParameter");
//		parameterList.add("employeeId");
//		parameterList.add("blankSpaceParameter");
//		parameterList.add("errorMessageParameter");
//		parameterList.add("criticalFlag");
//		parameterList.add("errorDateParameter");
//		parameterList.add("errorTimeParameter");
//		parameterList.add("opridErrorParameter");
//		parameterList.add("yesOrNoParameter");
//
//		parameterMap.put("errorProgramParameter", errorProgramParameter);
//		parameterMap.put("employeeId", employeeId);
//		parameterMap.put("blankSpaceParameter", blankSpaceParameter);
//		parameterMap.put("errorMessageParameter", errorMessageParameter);
//		parameterMap.put("criticalFlag", criticalFlag);
//		parameterMap.put("errorDateParameter", errorDateParameter);
//		parameterMap.put("errorTimeParameter", errorTimeParameter);
//		parameterMap.put("opridErrorParameter", opridErrorParameter);
//		parameterMap.put("yesOrNoParm", yesOrNoParameter);
//
//		AS400Package as400Package = new AS400Package(processName, parameterList, parameterMap);
//		return as400Package;
//	}
	
	/**
	 * Composes the string of termination process parameters
	 * @param terminationProcessParameters
	 * @return string of single-quoted parameter
	 */
	public static String composeParameterStringForTerminationProcess(TerminationProcessParameters terminationProcessParameters) {
		System.out.println("********** composeParameterStringForHrz102AProcess");
		//the HRZ102A program requires termination reason parameter to be character long
		String terminationReason = String.format("%1$-35s", terminationProcessParameters.getTerminationReason());
		String parameterString = "'" + terminationProcessParameters.getEmployeeId() + "' "
				+ "'" + terminationProcessParameters.getTerminationMonth() + "' "
				+ "'" + terminationProcessParameters.getTerminationDay() + "' "
				+ "'" + terminationProcessParameters.getTerminationYear() + "' "
				+ "'" + terminationProcessParameters.getRehireMonth() + "' "
				+ "'" + terminationProcessParameters.getRehireDay() + "' "
				+ "'" + terminationProcessParameters.getRehireYear() + "' "
				+ "'" + terminationProcessParameters.getVoluntaryOrInvoluntary() + "' "
				+ "'" + terminationProcessParameters.getTerminationCode() + "' "
				+ "'" + terminationProcessParameters.getOperatorId() + "' "
				+ "'" + terminationReason + "'";
		return parameterString;
	}
	
	/**
	 * Splits the termination date and rehire date into YYYY, MM, and DD format.
	 * @param psJob
	 * @param terminationProcessParameters
	 * @return terminationProcessParameters
	 */
	public static TerminationProcessParameters formatDates(PsJob psJob, TerminationProcessParameters terminationProcessParameters) {
		if("REH".equals(psJob.getAction()) && "REH".equals(psJob.getActionReason())) {
			terminationProcessParameters.setRehireYear(new SimpleDateFormat("yyyy").format(psJob.getEffectiveDate()));
			terminationProcessParameters.setRehireMonth(new SimpleDateFormat("mm").format(psJob.getEffectiveDate()));
			terminationProcessParameters.setRehireDay(new SimpleDateFormat("dd").format(psJob.getEffectiveDate()));
		}
		if("TER".equals(psJob.getAction()) || "RET".equals(psJob.getAction()) || "TWP".equals(psJob.getAction()) || "TWB".equals(psJob.getAction())) {
			terminationProcessParameters.setTerminationYear(new SimpleDateFormat("yyyy").format(psJob.getEffectiveDate()));
			terminationProcessParameters.setTerminationMonth(new SimpleDateFormat("MM").format(psJob.getEffectiveDate()));
			terminationProcessParameters.setTerminationDay(new SimpleDateFormat("dd").format(psJob.getEffectiveDate()));
		}
		return terminationProcessParameters;

	}

}

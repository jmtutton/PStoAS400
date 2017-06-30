package erd.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import erd.model.ProcessParameters;
import erd.model.PszPoiTermination;
import erd.model.PszTriggerNonPerson;
import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.TerminationProcessParameters;

/**
 * Non-Person Termination Process
 * @see ZHRI202A.SQC
 * @author John Tutton john@tutton.net
 */

public class NonPersonTermination {
	
	/**
	 * @see HR02-Initialize-Fields in ZHRI202A.SQC
	 * Initialize the fields to ensure that that they all start out blank.
	 */
	private void HR202_initializeFields(CommonParameters commonParameters) {
		System.out.println("********** HR202_initializeFields()");
		//Begin-Procedure HR202-Initialize-Fields
		//LET $PSEmpl = ' '
		//LET $PSTermDate = ' '
		//Let $ErrorProgramParm = 'HRZ202A'
		commonParameters.setErrorProgramParameter("HRZ202A");
		//End-Procedure HR202-Initialize-Fields
	}
	
	/**
	 * @see HR202-Call-System in ZHRI202A.SQC
	 * This routine calls the Legacy system
	 * @return
	 */
	private String HR202_callSystem(CommonParameters commonParameters, TerminationProcessParameters terminationProcessParameters) {
		System.out.println("********** HR202_callSystem()");
		String completionStatus = "E";
		//BEGIN-PROCEDURE HR202-CALL-SYSTEM
		String commandString = ZHRI100A.composeAs400RexecCommandString(commonParameters.getProcessName(), composeParameterStringForHrz202AProcess(terminationProcessParameters));
		//DO Call-System   !From ZHRI100A.SQR
		Integer status = ZHRI100A.executeRemoteCommand(commandString, commonParameters);
		//!SHOW 'Command : ' $Command
		System.out.println("$Command=> " + commandString);
		//IF (#Status = 0)
		if(status == 0) {
			//LET $NCompletionStatus = 'C'   !Completed Normally
			completionStatus = "C";
			//IF $Wrk_indexNum = '0'   !Insert only if it is a POI Term
			if(new BigDecimal(0).equals(commonParameters.getEffectiveSequence())) {
				//DO HR202-Insert-Timestamp
				PszPoiTermination.HR202_insertTimestamp(commonParameters.getEmployeeId());
			//END-IF
			}
		//END-IF    !#Status = 0
		}
		//END-PROCEDURE HR202-CALL-SYSTEM
		return completionStatus;
	}

	/**
	 * @see HR202-Process-Main in ZHRI202A.SQC
	 * This is the main processing procedure
	 * @param trigger
	 * @param commonParameters
	 * @return
	 */
	public String HR202_processMain(PszTriggerNonPerson trigger, CommonParameters commonParameters) {
		System.out.println("********** HR202_processMain()");
		commonParameters.setEmployeeId(trigger.getEmployeeId());
		commonParameters.setEffectiveDate(trigger.getEffectiveDate());
		commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
		TerminationProcessParameters terminationProcessParameters = new ProcessParameters().new TerminationProcessParameters();
		//BEGIN-PROCEDURE HR202-PROCESS-MAIN
		//!SHOW '$PSAuditOperId: ' $PSAuditOperId
		//!SHOW '$PSDateIn: '  $PSDateIn
		//!SHOW '$PSDateIn: ' $PSDateIn
		//!SHOW '#indexNum: ' #indexNum
		//DO HR202-Initialize-Fields   !Execute a routine that will move blanks to all of the parms on the SBMRMTCMD
		HR202_initializeFields(commonParameters);
		//!Format the legacy employee ID from the PeopleSoft Oprid for audit field
		//LET $PSauditEmpl = LTRIM(RTRIM($PSAuditOperId,' '),' ')  !Remove leading and trailing Blanks
		//LET $PSauditEmpl = LTRIM($PSauditEmpl,'E')  !Remove the leading 'E' from the employee ID
		//UPPERCASE $PSauditEmpl  !Be sure in all CAPS
		if(trigger.getOperatorId() != null && trigger.getOperatorId().length() > 1) {
			commonParameters.setOperatorId(trigger.getOperatorId().substring(1).toUpperCase()); //strips the 'E' off of the employee id
		}
		terminationProcessParameters.setOperatorId(commonParameters.getOperatorId());
		//!SHOW '$PSauditEmpl: ' $PSauditEmpl
		//!DO HR202-Get-Term-Date
		//LET $PSTermDate = DATETOSTR(STRTODATE($PSDateIn,'YYYY-MM-DD'),'YYYYMMDD')
		terminationProcessParameters.setTerminationDate(trigger.getEffectiveDate());
		//!SHOW '$PSTermDate: ' $PSTermDate
		//DO Get-OprId
		String psOprId = ZHRI100A.findLegacyEmployeeId(commonParameters);
		//LET $PSEmpl = $PSOprid
		commonParameters.setEmployeeId(psOprId);
		terminationProcessParameters.setEmployeeId(psOprId);
		//IF $PSEmpl <> '' AND $PSEmpl <> ' '  !If the new oprid is not blank and it is not null on return
		if(commonParameters.getEmployeeId() != null && !(commonParameters.getEmployeeId()).isEmpty()) {
			//DO HR202-Call-System
			HR202_callSystem(commonParameters, terminationProcessParameters);
		//END-IF   !$PSEmpl <> '' and $PSEmpl <> ' '
		}
		//END-PROCEDURE HR202-PROCESS-MAIN
		String completionStatus = "";
		return completionStatus;
	}
	
	/**
	 * 
	 * @param terminationProcessParameters
	 */
	private String composeParameterStringForHrz202AProcess(TerminationProcessParameters terminationProcessParameters) {
		System.out.println("********** composeParameterStringForHrz202AProcess");
		//LET $PSTermDate = DATETOSTR(STRTODATE($PSDateIn,'YYYY-MM-DD'),'YYYYMMDD')
		//LET $Part2 = 'Parm('''       ||
		//				$PSauditEmpl    ||
		//				''' '''         ||
		//				$PSOprid        ||
		//				''' '''         ||
		//				$PSTermDate     ||
		//				''')" '
		String terminationDate = new SimpleDateFormat("yyyyMMdd").format(terminationProcessParameters.getTerminationDate());
		String parameterString = "'" + terminationProcessParameters.getOperatorId() + "' "
				+ "'" + terminationProcessParameters.getEmployeeId() + "' "
				+ "'" + terminationDate + "'";
		return parameterString;
	}
	
}

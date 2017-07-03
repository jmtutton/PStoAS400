package erd.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import erd.model.PszPoiTermination;

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
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("********** HR202_initializeFields()");
		//Begin-Procedure HR202-Initialize-Fields
		//LET $PSEmpl = ' '
		//LET $PSTermDate = ' '
		//Let $ErrorProgramParm = 'HRZ202A'
		parameterMap.put("ErrorProgramParameter", "HRZ202A");
		//End-Procedure HR202-Initialize-Fields
		return parameterMap;
	}
	
	/**
	 * @see HR202-Call-System in ZHRI202A.SQC
	 * This routine calls the Legacy system
	 * @return
	 */
	private String HR202_callSystem(HashMap<String, Object> parameterMap) {
		System.out.println("********** HR202_callSystem()");
		String completionStatus = "E";
		//BEGIN-PROCEDURE HR202-CALL-SYSTEM
		String commandString = ZHRI100A.composeRexecCommandString((String)parameterMap.get("ProcessName"), composeParameterString(parameterMap));
		//DO Call-System   !From ZHRI100A.SQR
		Integer status = ZHRI100A.executeRemoteCommand(commandString, parameterMap);
		//!SHOW 'Command : ' $Command
		System.out.println("$Command=> " + commandString);
		//IF (#Status = 0)
		if(status == 0) {
			//LET $NCompletionStatus = 'C'   !Completed Normally
			completionStatus = "C";
			//IF $Wrk_indexNum = '0'   !Insert only if it is a POI Term
			if(new BigDecimal(0).equals(parameterMap.get("EffectiveSequence"))) {
				//DO HR202-Insert-Timestamp
				PszPoiTermination.HR202_insertTimestamp((String)parameterMap.get("EmployeeId"));
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
	public String processNonPersonTermination(HashMap<String, Object> parameterMap) {
		System.out.println("********** HR202_processMain()");
		//BEGIN-PROCEDURE HR202-PROCESS-MAIN
		//!SHOW '$PSAuditOperId: ' $PSAuditOperId
		//!SHOW '$PSDateIn: '  $PSDateIn
		//!SHOW '$PSDateIn: ' $PSDateIn
		//!SHOW '#indexNum: ' #indexNum
		//DO HR202-Initialize-Fields   !Execute a routine that will move blanks to all of the parms on the SBMRMTCMD
		parameterMap = fetchProcessParameters(parameterMap);
		//!Format the legacy employee ID from the PeopleSoft Oprid for audit field
		//LET $PSauditEmpl = LTRIM(RTRIM($PSAuditOperId,' '),' ')  !Remove leading and trailing Blanks
		//LET $PSauditEmpl = LTRIM($PSauditEmpl,'E')  !Remove the leading 'E' from the employee ID
		//UPPERCASE $PSauditEmpl  !Be sure in all CAPS
		if(parameterMap.get("OperatorId") != null && ((String)parameterMap.get("OperatorId")).length() > 1) {
			parameterMap.put("OperatorId", ((String)parameterMap.get("OperatorId")).substring(1).toUpperCase()); //strips the 'E' off of the employee id
		}
		parameterMap.put("peratorId", parameterMap.get("OperatorId"));
		//!SHOW '$PSauditEmpl: ' $PSauditEmpl
		//!DO HR202-Get-Term-Date
		//LET $PSTermDate = DATETOSTR(STRTODATE($PSDateIn,'YYYY-MM-DD'),'YYYYMMDD')
		parameterMap.put("erminationDate", parameterMap.get("EffectiveDate"));
		//!SHOW '$PSTermDate: ' $PSTermDate
		//DO Get-OprId
		String psOprId = ZHRI100A.fetchLegacyEmployeeId(parameterMap);
		//LET $PSEmpl = $PSOprid
		parameterMap.put("EmployeeId", psOprId);
		parameterMap.put("mployeeId", psOprId);
		//IF $PSEmpl <> '' AND $PSEmpl <> ' '  !If the new oprid is not blank and it is not null on return
		if(parameterMap.get("EmployeeId") != null && !((String)parameterMap.get("mployeeId")).isEmpty()) {
			//DO HR202-Call-System
			HR202_callSystem(parameterMap);
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
	private String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("********** composeParameterStringForHrz202AProcess");
		//LET $PSTermDate = DATETOSTR(STRTODATE($PSDateIn,'YYYY-MM-DD'),'YYYYMMDD')
		//LET $Part2 = 'Parm('''       ||
		//				$PSauditEmpl    ||
		//				''' '''         ||
		//				$PSOprid        ||
		//				''' '''         ||
		//				$PSTermDate     ||
		//				''')" '
		String terminationDate = new SimpleDateFormat("yyyyMMdd").format(parameterMap.get("TerminationDate"));
		String parameterString = "'" + parameterMap.get("OperatorId") + "' "
				+ "'" + parameterMap.get("EmployeeId") + "' "
				+ "'" + terminationDate + "'";
		return parameterString;
	}
	
}

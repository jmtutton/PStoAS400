package erd.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import erd.model.PszPoiTermination;

/**
 * Non-Person Termination Process
 * @see ZHRI202A.SQC
 * @author John Tutton john@tutton.net
 */

public class NonPersonTermination {

	/**
	 * This is the main processing procedure
	 * @see HR202-Process-Main in ZHRI202A.SQC
	 * @param parameterMap
	 * @return
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonTermination.doProcess() ***");
		String completionStatus = "E";
		//BEGIN-PROCEDURE HR202-PROCESS-MAIN
		//DO HR202-Initialize-Fields   !Execute a routine that will move blanks to all of the parms on the SBMRMTCMD
		parameterMap = fetchProcessParameters(parameterMap);
		//DO Get-OprId
		//LET $PSEmpl = $PSOprid
		parameterMap.put("employeeId", ZHRI100A.fetchLegacyEmployeeId(parameterMap));
		//IF $PSEmpl <> '' AND $PSEmpl <> ' '  !If the new oprid is not blank and it is not null on return
		if(parameterMap.get("employeeId") != null && !((String)parameterMap.get("employeeId")).isEmpty()) {
			//DO HR202-Call-System
			parameterMap.put("parameterString", composeParameterString(parameterMap));
			completionStatus = ZHRI100A.doCommand(parameterMap);
			//BEGIN-PROCEDURE HR202-CALL-SYSTEM
			//DO Call-System   !From ZHRI100A.SQR
			//!SHOW 'Command : ' $Command
			//IF (#Status = 0)
			//LET $NCompletionStatus = 'C'   !Completed Normally
			if("C".equalsIgnoreCase(completionStatus)) {
				//IF $Wrk_indexNum = '0'   !Insert only if it is a POI Term
				if(new BigDecimal(0).equals((BigDecimal)parameterMap.get("effectiveSequence"))) {
					//DO HR202-Insert-Timestamp
					PszPoiTermination.HR202_insertTimestamp((String)parameterMap.get("employeeId"));
				//END-IF
				}
			//END-IF    !#Status = 0
			}
		//END-IF   !$PSEmpl <> '' and $PSEmpl <> ' '
		}
		//END-PROCEDURE HR202-PROCESS-MAIN
		return completionStatus;
	}
	
	/**
	 * Initialize the fields to ensure that that they all start out blank.
	 * @see HR02-Initialize-Fields in ZHRI202A.SQC
	 * @param parameterMap
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonTermination.fetchProcessParameters() ***");
		//Begin-Procedure HR202-Initialize-Fields
		//LET $PSEmpl = ' '
		//LET $PSTermDate = ' '
		//Let $ErrorProgramParm = 'HRZ202A'
		parameterMap.put("errorProgramParameter", "HRZ202A");
		//!Format the legacy employee ID from the PeopleSoft Oprid for audit field
		//LET $PSauditEmpl = LTRIM(RTRIM($PSAuditOperId,' '),' ')  !Remove leading and trailing Blanks
		//LET $PSauditEmpl = LTRIM($PSauditEmpl,'E')  !Remove the leading 'E' from the employee ID
		//UPPERCASE $PSauditEmpl  !Be sure in all CAPS
		if(parameterMap.get("operatorId") != null && ((String)parameterMap.get("operatorId")).length() > 1) {
			parameterMap.put("operatorId", ((String)parameterMap.get("operatorId")).substring(1).toUpperCase()); //strips the 'E' off of the employee id
		}
		//End-Procedure HR202-Initialize-Fields
		return parameterMap;
	}
	
//	/**
//	 * This routine calls the Legacy system
//	 * @see HR202-Call-System in ZHRI202A.SQC
//	 * @return
//	 */
//	private String HR202_callSystem(HashMap<String, Object> parameterMap) {
//		System.out.println("********** HR202_callSystem()");
//		String completionStatus = "E";
//		parameterMap.put("parameterString", composeParameterString(parameterMap));
//		completionStatus = ZHRI100A.doCommand(parameterMap);
//		//BEGIN-PROCEDURE HR202-CALL-SYSTEM
//		//DO Call-System   !From ZHRI100A.SQR
//		//!SHOW 'Command : ' $Command
//		//IF (#Status = 0)
//		//LET $NCompletionStatus = 'C'   !Completed Normally
//		if("C".equalsIgnoreCase(completionStatus)) {
//			//IF $Wrk_indexNum = '0'   !Insert only if it is a POI Term
//			if(new BigDecimal(0).equals(parameterMap.get("effectiveSequence"))) {
//				//DO HR202-Insert-Timestamp
//				PszPoiTermination.HR202_insertTimestamp((String)parameterMap.get("employeeId"));
//			//END-IF
//			}
//		//END-IF    !#Status = 0
//		}
//		//END-PROCEDURE HR202-CALL-SYSTEM
//		return completionStatus;
//	}
	
	/**
	 * 
	 * @param parameterMap
	 */
	private String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("*** NonPersonTermination.composeParameterString() ***");
		//LET $PSTermDate = DATETOSTR(STRTODATE($PSDateIn,'YYYY-MM-DD'),'YYYYMMDD')
		String terminationDate = new SimpleDateFormat("yyyyMMdd").format((Date)parameterMap.get("effectiveDate"));
		String parameterString = "'" + parameterMap.get("operatorId") + "' " //$PSauditEmpl
				+ "'" + parameterMap.get("employeeId") + "' " //$PSOprid
				+ "'" + terminationDate + "'"; //$PSTermDate
		return parameterString;
	}
	
}

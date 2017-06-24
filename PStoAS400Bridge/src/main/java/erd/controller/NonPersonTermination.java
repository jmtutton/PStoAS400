package erd.controller;

import erd.model.ActionFields;
import erd.model.PszPoiTermination;
import erd.model.PszTriggerNonPerson;
import erd.model.Zhri100aFields;

/**
 * ZHRI202A Non-Person Termination
 * PeopleCode filename ZHRI202A.SQC
 * @author John Tutton john@tutton.net
 */

public class NonPersonTermination {
	
	PszTriggerNonPerson trigger;
	Zhri100aFields zhri100aFields;
	ActionFields actionFields;
	
	public NonPersonTermination(PszTriggerNonPerson trigger, Zhri100aFields zhri100aFields) {
		System.out.println("********** EmployeeTermination");
		this.trigger = trigger;
		this.zhri100aFields = zhri100aFields;
		System.out.println("\n" + trigger.toString() + "\n");
		System.out.println(zhri100aFields.toString() + "\n");
	}
	
	/**
	 * HR02-Initialize-Fields from ZHRI202A.SQC
	 * Initialize the fields to ensure that that they all start out blank.
	 */
	private void HR202_initializeFields() {
		System.out.println("********** HR202_initializeFields");
		//Begin-Procedure HR202-Initialize-Fields
		//LET $PSEmpl = ' '
		//LET $PSTermDate = ' '
		//Let $ErrorProgramParm = 'HRZ202A'
		zhri100aFields.setErrorProgramParameter("HRZ102A");
		//End-Procedure HR202-Initialize-Fields
	}
	/**
	 * HR202-Call-System from ZHRI202A.SQC
	 * This routine calls the Legacy system
	 * @return
	 */
	private String HR202_callSystem(Zhri100aFields zhri100aFields) {
		String completionStatus = "E";
		//BEGIN-PROCEDURE HR202-CALL-SYSTEM
		//LET $Part1 = '"CALL ' || $Library ||'/HRZ202A '
		//LET $Part2 = 'Parm(''' || $PSauditEmpl || ''' ''' || $PSOprid || ''' ''' || $PSTermDate || ''')" '
		//LET $Command = $Part1||$Part2
		String command = "\"CALL " + zhri100aFields.getAs400Library() + "/HRZ202A "
				+ "Parm('" + zhri100aFields.getAuditOperatorId() + "' '" 
				+ zhri100aFields.getOperatorId() + "' '" 
				+ zhri100aFields.getEffectiveDate() + "')" ;
		//DO Call-System   !From ZHRI100A.SQR
		Integer status = ZHRI100A.ZHRI100A_callSystem(command, zhri100aFields);
		//!SHOW 'Command : ' $Command
		System.out.println("Command : " + command);
		//IF (#Status = 0)
		if(status == 0) {
			//LET $NCompletionStatus = 'C'   !Completed Normally
			completionStatus = "C";
			//IF $Wrk_indexNum = '0'   !Insert only if it is a POI Term
			if(zhri100aFields.getIndexNumber() == 0) {
				//DO HR202-Insert-Timestamp
				PszPoiTermination.HR202_insertTimestamp(zhri100aFields.getEmployeeId());
			//END-IF
			}
		//END-IF    !#Status = 0
		}
		//END-PROCEDURE HR202-CALL-SYSTEM
		return completionStatus;
	}

	/**
	 * HR202-Process-Main from ZHRI202A.SQC
	 * This is the main processing procedure
	 */
	public String HR202_processMain() {
		System.out.println("********** HR202_processMain");
		zhri100aFields.setPoiFlag(true);
		//BEGIN-PROCEDURE HR202-PROCESS-MAIN
		//DO HR202-Initialize-Fields   !Execute a routine that will move blanks to all of the parms on the SBMRMTCMD
		HR202_initializeFields();
		//!Format the legacy employee ID from the PeopleSoft OprId for audit field
		//LET $PSauditEmpl = LTRIM(RTRIM($PSAuditOperId,' '),' ')   !Remove leading and trailing Blanks
		//LET $PSauditEmpl = LTRIM($PSauditEmpl,'E')  !Remove the leading 'E' from the employee ID
		//UPPERCASE $PSauditEmpl    !Be sure in all CAPS
		zhri100aFields.setAuditOperatorId(trigger.getOperatorId().substring(1).toUpperCase()); //strips the 'E' off of the employee id
		//!DO HR202-get-term-date
		//LET $PSTermDate = DATETOSTR(STRTODATE($PSDateIn,'YYYY-MM-DD'),'YYYYMMDD')
		actionFields.getTerminationFields().setTerminationDate(trigger.getEffectiveDate());
		//DO ZHRI100A.Get-OprId
		String psOprId = ZHRI100A.ZHRI100A_getOprId(trigger.getEmployeeId(), trigger.getEffectiveSequence(), zhri100aFields);
		//LET $PSEmpl = $PSOprid
		zhri100aFields.setEmployeeId(psOprId);
		//IF $PSEmpl <> '' AND $PSEmpl <> ' '   !If the new OprId is not blank and it is not null on return
		if(zhri100aFields.getEmployeeId() != null && !(zhri100aFields.getEmployeeId()).isEmpty()) {
			//DO HR202-Call-System
			HR202_callSystem(zhri100aFields);
		//END-IF   !$PSEmpl <> '' and $PSEmpl <> ' '
		}
		//END-PROCEDURE HR202-PROCESS-MAIN
		String completionStatus = "";
		return completionStatus;
	}
	
}

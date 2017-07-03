package erd.controller;

import java.util.Date;

import erd.model.CrossReferenceMultipleEmployeeId;
import erd.model.CrossReferencePersonOfInterest;

/**
 * ZHRI202A - Contingent Employee and Multiple EID Terminate
 * @author John Tutton john@tutton.net
 *
 */

public class NonPersonTerminate {
	static String errorProgramParm = "HRZ202A";
	static Boolean poiFlag = true;
	
//	HR202-Initialize-Fields
//	HR202-Process-Main
//	HR202-Get-Term-Date
//	HR202-Call-System
	
	/**
	 * HR202-Process-Main from ZHRI202A.SQC
	 * This is the main processing procedure.
	 */
	public static void HR202_processMain(String psAuditOperId, Integer indexNum, Date psDateIn) {
//		String errorProgramParm = "HRZ202A";
//		!show '$PSAuditOperId: ' $PSAuditOperId
//		!show '$PSDateIn: '  $PSDateIn
//		!show '#indexNum: ' #indexNum
		System.out.println("psAuditOperId: " + psAuditOperId);
		System.out.println("psDateIn: " + psDateIn);
		System.out.println("indexNum: " + indexNum);
//		Do HR202-Initialize-Fields   !Execute a routine that will move blanks to all of the parms on the SBMRMTCMD
		HR202_initializeFields();
//		!Format the legacy employee ID from the PeopleSoft Oprid for audit field
//		Let $PSAuditEmpl = ltrim(rtrim($PSAuditOperId,' '),' ')   !Remove leading and trailing Blanks
//		Let $PSAuditEmpl = ltrim($PSAuditEmpl,'E')  !Remove the leading 'E' from the employee ID
//		Uppercase $PSauditEmpl    !Be sure in all CAPS
		String psAuditEmpl = psAuditOperId.trim().substring(1).toUpperCase();
//		!show '$PSAuditEmpl: ' $PSauditEmpl
		System.out.println("psAuditEmpl: " + psAuditEmpl);
//		!do HR202-Get-Term-Date
//		Let $PSTermDate = datetostr(strtodate($PSDateIn,'YYYY-MM-DD'),'YYYYMMDD')
		String wrkEmplId = "?";
		Integer wrkIndexNum = 0;
		Date psTermDate = HR202_getTermDate(wrkEmplId, wrkIndexNum, psDateIn);
//		!show '$PSTermDate: ' $PSTermDate
		System.out.println("psTermDate: " + psTermDate);
//		do Get-Oprid
		String psEmpl = "?";
//		Integer eidIndexNumber = 0;
//		String psOprId = ZHRI100A.getOprId(psEmpl, indexNum, poiFlag, eidIndexNumber);
//		Let $PSEmpl = $PSOprid
//		psEmpl = psOprId;
//		If $PSEmpl <> '' and $PSEmpl <> ' '  !If the new oprid is not blank and it is not null on return
		if(psEmpl != null && !psEmpl.isEmpty() && !" ".equalsIgnoreCase(psEmpl)) {
//		    do HR202-Call-System
//			hr202CallSystem(psAuditEmpl, psOprId, psTermDate);
//		End-if    !$PSEmpl <> '' and $PSEmpl <> ' '
		}
	}

//	/**
//	 * HR202-Call-System from ZHRI202A.SQC
//	 * This routine calls the Legacy system.
//	 */
//	private static Integer HR202_callSystem(String psAuditEmpl, String psOprId, Date psTermDate) {
//		Integer status = 0;
//		String library = "library";
//		String command = "CALL " + library + "/HRZ202A Parm('" + psAuditEmpl + "' '" + psOprId + "' '" + psTermDate + "')";
////		Let $Part2 = 'Parm('''       ||
////		             $PSauditEmpl    ||
////		             ''' '''         ||
////		             $PSOprid        ||
////		             ''' '''         ||
////		             $PSTermDate     ||
////		             ''')" '
////		Let $Part1 = '"CALL ' || $Library ||'/HRZ202A '
////		Let $Command = $Part1||$Part2
////		Do Call-System             !From ZHRI100A.SQR
////		!show 'Command : ' $Command
////		if (#Status = 0)
////		    let $NCompletionStatus = 'C'   !Completed Normally
////		end-if    !#Status = 0
//		System.out.println("command: " + command);
//		return status;
//	}

	/**
	 * HR202-Get-Term-Date from ZHRI202A.SQC
	 * Gets the term date for POI/EMP.
	 */
	private static Date HR202_getTermDate(String wrkEmplId, Integer wrkIndexNum, Date psDateIn) {
		Date psTermDate;
//		LET $PSTermDate = LTRIM(RTRIM(&PS_ZHRT_PER_POI_TR POI.EFFDT,' '),' ')
//			FROM PS_ZHRT_PER_POI_TR PS_ZHRT_PER_POI_TR
//			WHERE PS_ZHRT_PER_POI_TR.EMPLID =  $Wrk_Emplid
//			AND PS_ZHRT_PER_POI_TR.EFFDT = 
//				(SELECT MAX(PS_ZHRT_PER_POI_TR2.EFFDT) FROM PS_ZHRT_PER_POI_TR PS_ZHRT_PER_POI_TR2
//						WHERE PS_ZHRT_PER_POI_TR2.EMPLID = PS_ZHRT_PER_POI_TR.EMPLID
//							AND PS_ZHRT_PER_POI_TR2.POI_TYPE = PS_ZHRT_PER_POI_TR.POI_TYPE
//			                AND to_char(PS_ZHRT_PER_POI_TR2.EFFDT,'YYYY-MM-DD') <= $PSDateIn)
		psTermDate = CrossReferencePersonOfInterest.findEffectiveDateByEmployeeId(wrkEmplId, psDateIn);
//
//		LET $PSTermDate = LTRIM(RTRIM(&PS_ZHRR_MULTPL_EID.EFFDT,' '),' ')
//			PS_ZHRR_MULTPL_EID.EFFDT
//			FROM PS_ZHRR_MULTPL_EID PS_ZHRR_MULTPL_EID
//			WHERE PS_ZHRR_MULTPL_EID.EMPLID = $Wrk_Emplid
//			AND PS_ZHRR_MULTPL_EID.SEQUENCE = $Wrk_indexNum
//			AND PS_ZHRR_MULTPL_EID.EFFDT = 
//				(SELECT MAX(PS_ZHRR_MULTPL_EID2.EFFDT) FROM PS_ZHRR_MULTPL_EID PS_ZHRR_MULTPL_EID2
//						WHERE PS_ZHRR_MULTPL_EID2.EMPLID = PS_ZHRR_MULTPL_EID.EMPLID
//			                AND to_char(PS_ZHRR_MULTPL_EID2.EFFDT,'YYYY-MM-DD') <= $PSDateIn)
		psTermDate = CrossReferenceMultipleEmployeeId.findEffectiveDateByEmployeeIdAndSequence(wrkEmplId, wrkIndexNum, psDateIn);
		return psTermDate;
	}

	/**
	 * HR202-Initialize-Fields from ZHRI202A.SQC
	 * Initialize the fields to ensure that that they all start out blank.
	 */
	private static void HR202_initializeFields() {
//		LET $PSEmpl = ' '
//		LET $PSTermDate = ' '
//		Let $ErrorProgramParm = 'HRZ202A'
		errorProgramParm = "HRZ202A";
		
	}

}

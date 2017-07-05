package erd.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import erd.model.PsAccomplishment;
import erd.model.PsContractData;
import erd.model.PsEmployeeReview;

/**
 * Employee Date Change Process
 * @see ZHRI107A.SQC
 * Employment Review, Accomplishment and Contract Data change
 * @author John Tutton john@tutton.net
 *
 */
public class EmployeeDateChange {

	/**
	 * @see HR07-Process-Main in ZHRI107A.SQC
	 * @param parameterMap
	 * @return
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDateChange.doProcess() ***");
		//BEGIN-PROCEDURE HR07-PROCESS-MAIN
		//DO HR07-Initialize-Fields
		parameterMap = fetchProcessParameters(parameterMap);
		//IF $LegacyEmplid <> '' AND $LegacyEmplid <> ' '  !New OprId not null or blank on return
		if(parameterMap.get("employeeId") != null && !((String)parameterMap.get("employeeId")).isEmpty()) {
			//DO HR07-Call-RPG
			parameterMap.put("parameterString", composeParameterString(parameterMap));
			return ZHRI100A.doCommand(parameterMap);
			//LET $CompletionStatus = 'C'
			//END-IF    !#STATUS = 0
		//END-IF    !$LegacyEmplid <> '' and $LegacyEmplid <> ' '
		}
		//END-PROCEDURE HR07-PROCESS-MAIN
		return "E";
	}

	/**
	 * This will initialize the fields at each call.
	 * @see HR07-Initialize-Fields in ZHRI107A.SQC
	 * @param parameterMap
	 */
	public HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDateChange.fetchProcessParameters() ***");
		parameterMap.put("errorProgramParameter", "HRZ107A");
		//BEGIN-PROCEDURE HR07-INITIALIZE-FIELDS
		//LET $LegEffdt = $PSEffdt
		//!Remove leading 'E' from the PS employee operator ID to comply with the 5 long legacy format
		//LET $LegacyUserEmplId = SUBSTR($AuditOprId,2,5)
		//DO Get-OprId   !gets valid operator ID using code from ZHRI100A.sqr
		if(parameterMap.get("operatorId") != null && ((String)parameterMap.get("operatorId")).length() > 1) {
			parameterMap.put("operatorId", ((String)parameterMap.get("operatorId")).substring(1).toUpperCase()); //strips the 'E' off of the id
		}
		//LET $LegacyEmplid = $PSOprid
		parameterMap.put("employeeId", ZHRI100A.fetchLegacyEmployeeId(parameterMap));
		//IF $LegacyEmplid <> '' AND $LegacyEmplid <> ' '  !New OprId not null or blank on return
		if(parameterMap.get("employeeId") != null && !((String)parameterMap.get("employeeId")).isEmpty()) {
			//UPPERCASE $LegacyUserEmplId !Make sure it is in all caps in case ID has a letter in it
			parameterMap.put("employeeId", ((String)parameterMap.get("operatorId")).toUpperCase());
			//DO HR07-Get-Employee-Review
			parameterMap = fetchEmployeeReviewDates(parameterMap);
			//DO HR07-Get-Accomplishments
			parameterMap = fetchAccomplishmentDates(parameterMap);
			//DO HR07-Get-Contract-Data
			parameterMap = fetchContractDate(parameterMap);
		}
		return parameterMap;
		//END-PROCEDURE HR07-INITIALIZE-FIELDS
	}

	/**
	 * This procedure retrieves the Next Review Date and Last Review Date from the 
	 * PeopleSoft Employee Review Table to send back to Option 7 of AAHR01 in legacy.
	 * @see HR07-Get-Employee-Review in ZHRI107A.SQC
	 * @param parameterMap
	 * @return
	 */
	public HashMap<String, Object> fetchEmployeeReviewDates(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDateChange.fetchEmployeeReviewDates() ***");
		//BEGIN-PROCEDURE HR07-GET-EMPLOYEE-REVIEW
		//BEGIN-SELECT
		PsEmployeeReview psEmployeeReview = 
				PsEmployeeReview.findByEmployeeIdAndEffectiveDateAndEmploymentRecordNumber(
						(String)parameterMap.get("employeeId"), 
						(Date)parameterMap.get("effectiveDate"), new BigDecimal(0));
		//TO_CHAR(CER7.NEXT_REVIEW_DT, 'YYYY-MM-DD')  &CER7NEXT_REVIEW_DT
		//LET $PSNextReviewDate = &CER7NEXT_REVIEW_DT
		//LET $LegNextReviewDate = $PSNextReviewDate
		//CER7.EFFDT
		//LET $LegLastReviewDate = $LegEffdt
		//!Format next review date and last review date so legacy will accept it (MM field, DD field and CCYY field)
		//UNSTRING $PSNextReviewDate BY '-' INTO $LegNxtRevDtYear $LegNxtRevDtMonth $LegNxtRevDtDay
		if(psEmployeeReview.getNextReviewDt() != null) {
			parameterMap.put("nextReviewYear", new SimpleDateFormat("yyyy").format(psEmployeeReview.getNextReviewDt()));
			parameterMap.put("nextReviewMonth", new SimpleDateFormat("mm").format(psEmployeeReview.getNextReviewDt()));
			parameterMap.put("nextReviewDay", new SimpleDateFormat("dd").format(psEmployeeReview.getNextReviewDt()));
		}
		//UNSTRING $LegLastReviewDate BY '-' INTO $LegLstRevDtYear $LegLstRevDtMonth $LegLstRevDtDay
		if(psEmployeeReview.getNextReviewDt() != null) {
			parameterMap.put("lastReviewYear", new SimpleDateFormat("yyyy").format(psEmployeeReview.getEffectiveDate()));
			parameterMap.put("lastReviewMonth", new SimpleDateFormat("mm").format(psEmployeeReview.getEffectiveDate()));
			parameterMap.put("lastReviewDay", new SimpleDateFormat("dd").format(psEmployeeReview.getEffectiveDate()));
		}
		//END-PROCEDURE HR07-GET-EMPLOYEE-REVIEW
		return parameterMap;
	}
	
	/**
	 * This procedure retrieves the Negative Drug Test Date and Physical Test Date from the 
	 * PeopleSoft Accomplishments Table to send back to Option 7 of AAHR01.
	 * @see HR07-Get-Accomplishments in ZHRI107A.SQC
	 * @param parameterMap
	 * @return
	 */
	public HashMap<String, Object> fetchAccomplishmentDates(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDateChange.fetchAccomplishmentDates() ***");
		List<String> accomplishmentCodeList = Arrays.asList("DRUGTST", "PHYS L3", "PHYS L4");
		PsAccomplishment psAccomplishment = PsAccomplishment.findByEmployeeIdAndAccomplishmentCodes((String)parameterMap.get("employeeId"), accomplishmentCodeList);
		//BEGIN-PROCEDURE HR07-GET-ACCOMPLISHMENTS
		//LET $SelectAccomplishment = LTRIM(RTRIM(&CA7.ACCOMPLISHMENT,' '),' ')
		//!select the correct accomplishment to pull in the correct date
		//EVALUATE $SelectAccomplishment
		switch(psAccomplishment.getAccomplishmentCode()) {
			//!when accomplishment is equal to negative drug test
			//WHEN = 'DRUGTST'
			case "DRUGTST":
				//LET $LegNegDrugTestDate = $PSIssueDate
				if(psAccomplishment.getDateIssued() != null) {
					parameterMap.put("negDrugTestYear", new SimpleDateFormat("yyyy").format(psAccomplishment.getDateIssued()));
					parameterMap.put("negDrugTestMonth", new SimpleDateFormat("mm").format(psAccomplishment.getDateIssued()));
					parameterMap.put("negDrugTestDay", new SimpleDateFormat("dd").format(psAccomplishment.getDateIssued()));
					//!Format negative drug test date so legacy will accept it (MM field, DD field and CCYY field)
					//UNSTRING $PSIssueDate BY '-' INTO $LegNegDrugTstYear $LegNegDrugTstMonth $LegNegDrugTstDay
					//BREAK                 !Exit the evaluate statement
				}
			break;
			//!when accomplishment is equal to physical test date for level 3 or level 4
			//WHEN = 'PHYS L3' OR WHEN = 'PHYS L4'
			case "PHYS L3":
			case "PHYS L4":
				//LET $LegPhysical3TestDate = $PSIssueDate
				//LET $LegPhysical4TestDate = $PSIssueDate
				if(psAccomplishment.getDateIssued() != null) {
					parameterMap.put("physTestYear", new SimpleDateFormat("yyyy").format(psAccomplishment.getDateIssued()));
					parameterMap.put("physTestMonth", new SimpleDateFormat("mm").format(psAccomplishment.getDateIssued()));
					parameterMap.put("physTestDay", new SimpleDateFormat("dd").format(psAccomplishment.getDateIssued()));
					//!Format physical test date so legacy will accept it (MM field, DD field and CCYY field)
					//UNSTRING $PSIssueDate BY '-' INTO $LegPhysTstYear $LegPhysTstMonth $LegPhysTstDay
				}
				//BREAK    !Exit the evaluate statement
			break;
			//!when equal to anything else, do not get issue date
			//WHEN-OTHER
			default:
				//BREAK    !Exit the evaluate statement
			break;
		//END-EVALUATE    !$SelectAccomplishment
		}
		return null;
	}

	/**
	 * This procedure retrieves the contract date from the PeopleSoft Contract Data Table to send back to Option 7 of AAHR01.
	 * @see HR07-Get-Contract-Data in ZHRI107A.SQC
	 * @param parameterMap
	 * @return
	 */
	public HashMap<String, Object> fetchContractDate(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDateChange.fetchContractDate() ***");
		//BEGIN-PROCEDURE HR07-GET-CONTRACT-DATA
		//BEGIN-SELECT
		PsContractData psContractData = PsContractData.findByEmployeeId((String)parameterMap.get("employeeId"));
		//TO_CHAR(CCD7.CONTRACT_BEGIN_DT, 'YYYY-MM-DD')  &CCD7EFFDT
		//LET $LegContractDate = &CCD7EFFDT
		//!Format contract date so legacy will accept it (MM field, DD field and CCYY field)
		//UNSTRING $LegContractDate BY '-' INTO $LegContractDtYear $LegContractDtMonth $LegContractDtDay
		parameterMap.put("contractYear", new SimpleDateFormat("yyyy").format(psContractData.getContractBeginDt()));
		parameterMap.put("contractMonth", new SimpleDateFormat("mm").format(psContractData.getContractBeginDt()));
		parameterMap.put("contractDay", new SimpleDateFormat("dd").format(psContractData.getContractBeginDt()));
		//!select the maximum contract date to get the most current one because an employee can have more than 1 active contract
		//FROM PS_CONTRACT_DATA CCD7
		//WHERE CCD7.Emplid = $PSEmplid
		//		AND CCD7.CONTRACT_BEGIN_DT = 
		//			(SELECT MAX(CONTRACT_BEGIN_DT) FROM PS_CONTRACT_DATA CCD8
		//				WHERE CCD8.EMPLID = CCD7.EMPLID
		//					AND CCD8.CONTRACT_BEGIN_DT <= $AsofToday)
		//END-SELECT
		//END-PROCEDURE HR07-GET-CONTRACT-DATA
		return null;
	}

//	/**
//	 * @see HR07-Call-RPG in ZHRI107A.SQC
//	 * This procedure calls the RPG program to update the legacy files/fields needed by Option 7 of AAHR01.
//	 * @param parameterMap
//	 * @return
//	 */
//	public String HR07_callRpg(HashMap<String, Object> parameterMap) {
//		System.out.println("*** EmployeeDateChange.HR07_callRpg() ***");
//		//BEGIN-PROCEDURE HR07-CALL-RPG
//		//!Setup the parameter list with what needs to be passed to the RPG program to update the legacy system
//		String parameterString = composeParameterString(parameterMap);
//		String commandString = ZHRI100A.composeRexecCommandString((String)parameterMap.get("processName"), parameterString);
//		//DO Call-System    !Do a remote call to the RPG program, HRZ107A, in order to pass the parms from code in ZHRI100A.sqr
//		Integer status = ZHRI100A.executeRemoteCommand(commandString, parameterMap);
//		//IF (#STATUS = 0)
//		if(status == 0) {
//			//LET $CompletionStatus = 'C'
//		//END-IF    !#STATUS = 0
//		}
//		//END-PROCEDURE HR07-CALL-RPG
//		return null;
//	}
	
	/**
	 * Setup the parameter list with what needs to be passed to the RPG program to update the legacy system
	 * @param parameterMap
	 * @return
	 */
	public String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDateChange.composeParameterString() ***");
		//'PARM('''                           ||
		//$LegacyEmplid                       ||
		//''' '''                             ||
		//$LegacyUserEmplid                   ||
		//''' '''                             ||
		//$LegHireDtMonth                     ||
		//''' '''                             ||
		//$LegHireDtDay                       ||
		//''' '''                             ||
		//$LegHireDtYear                      ||
		//''' '''                             ||
		//$LegTermDtMonth                     ||
		//''' '''                             ||
		//$LegTermDtDay                       ||
		//''' '''                             ||
		//$LegTermDtYear                      ||
		//''' '''                             ||
		//$LegLstRevDtMonth                   ||
		//''' '''                             ||
		//$LegLstRevDtDay                     ||
		//''' '''                             ||
		//$LegLstRevDtYear                    ||
		//''' '''                             ||
		//$LegNxtRevDtMonth                   ||
		//''' '''                             ||
		//$LegNxtRevDtDay                     ||
		//''' '''                             ||
		//$LegNxtRevDtYear                    ||
		//''' '''                             ||
		//$LegNegDrugTstMonth                 ||
		//''' '''                             ||
		//$LegNegDrugTstDay                   ||
		//''' '''                             ||
		//$LegNegDrugTstYear                  ||
		//''' '''                             ||
		//$LegPhysTstMonth                    ||
		//''' '''                             ||
		//$LegPhysTstDay                      ||
		//''' '''                             ||
		//$LegPhysTstYear                     ||
		//''' '''                             ||
		//$LegContractDtMonth                 ||
		//''' '''                             ||
		//$LegContractDtDay                   ||
		//''' '''                             ||
		//$LegContractDtYear                  ||
		//''' '''                             ||
		//$LegCompanySeniorityMonth           ||
		//''' '''                             ||
		//$LegCompanySeniorityDay             ||
		//''' '''                             ||
		//$LegCompanySeniorityYear            ||
		//''')" '
		String parameterString = "'" + parameterMap.get("employeeId") + "' "
				+ "'" + parameterMap.get("operatorId") + "' "
				+ "'" + parameterMap.get("hireMonth") + "' "
				+ "'" + parameterMap.get("hireYear") + "' "
				+ "'" + parameterMap.get("terminationMonth") + "' "
				+ "'" + parameterMap.get("terminationDay") + "' "
				+ "'" + parameterMap.get("terminationYear") + "' "
				+ "'" + parameterMap.get("lastReviewMonth") + "' "
				+ "'" + parameterMap.get("lastReviewDay") + "' "
				+ "'" + parameterMap.get("lastReviewYear") + "' "
				+ "'" + parameterMap.get("nextReviewMonth") + "' "
				+ "'" + parameterMap.get("nextReviewDay") + "' "
				+ "'" + parameterMap.get("nextReviewYear") + "' "
				+ "'" + parameterMap.get("negDrugTestMonth") + "' "
				+ "'" + parameterMap.get("negDrugTestDay") + "' "
				+ "'" + parameterMap.get("negDrugTestYear") + "' "
				+ "'" + parameterMap.get("physTestMonth") + "' "
				+ "'" + parameterMap.get("physTestDay") + "' "
				+ "'" + parameterMap.get("contractMonth") + "' "
				+ "'" + parameterMap.get("contractDay") + "' "
				+ "'" + parameterMap.get("contractYear") + "' "
				+ "'" + parameterMap.get("companySeniorityMonth") + "' "
				+ "'" + parameterMap.get("companySeniorityDay") + "' "
				+ "'" + parameterMap.get("companySeniorityYear") + "' ";
		return parameterString;
	}

}

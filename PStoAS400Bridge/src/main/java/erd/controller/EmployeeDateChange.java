package erd.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.DateChangeProcessParameters;
import erd.model.ProcessParameters;
import erd.model.PsAccomplishment;
import erd.model.PsContractData;
import erd.model.PsEmployeeReview;
import erd.model.PszTriggerEmployee;

/**
 * ZHRI107A - Employee Date Change Process 
 * Employment Review, Accomplishment and Contract Data change
 * @author John Tutton john@tutton.net
 *
 */
public class EmployeeDateChange {

	/**
	 * HR07-Initialize-Fields from ZHRI107A.SQC
	 * This will initialize the fields at each call.
	 * @param commonParameters
	 * @param trigger
	 * @param dateChangeProcessParameters
	 */
	public void HR07_initializeFields(CommonParameters commonParameters, PszTriggerEmployee trigger, DateChangeProcessParameters dateChangeProcessParameters) {
		//BEGIN-PROCEDURE HR07-INITIALIZE-FIELDS
		//LET $LegEffdt = $PSEffdt
		//LET $ErrorProgramParm = 'HRZ107A'
		commonParameters.setErrorProgramParameter("HRZ107A");
		//!Remove leading 'E' from the PS employee operator ID to comply with the 5 long legacy format
		//LET $LegacyUserEmplId = SUBSTR($AuditOprId,2,5)
		//UPPERCASE $LegacyUserEmplId !Make sure it is in all caps in case ID has a letter in it
		//END-PROCEDURE HR07-INITIALIZE-FIELDS
		commonParameters.setOperatorId(trigger.getOperatorId());
		if(trigger.getOperatorId() != null && trigger.getOperatorId().length() > 1) {
			dateChangeProcessParameters.setUserEmployeeId(trigger.getOperatorId().substring(1).toUpperCase()); //strips the 'E' off of the employee id
		}
	}

	/**
	 * HR07-Process-Main from ZHRI107A.SQC
	 * @param trigger
	 * @param commonParameters
	 * @return
	 */
	public String HR07_processMain(PszTriggerEmployee trigger, CommonParameters commonParameters) {
		commonParameters.setEmployeeId(trigger.getEmployeeId());
		commonParameters.setEffectiveDate(trigger.getEffectiveDate());
		commonParameters.setEffectiveSequence(trigger.getEffectiveSequence());
		//BEGIN-PROCEDURE HR07-PROCESS-MAIN
		//DO HR07-Initialize-Fields
		DateChangeProcessParameters dateChangeProcessParameters = new ProcessParameters().new DateChangeProcessParameters();
		commonParameters.setErrorProgramParameter("HRZ107A");
		commonParameters.setOperatorId(trigger.getOperatorId());
		if(trigger.getOperatorId() != null && trigger.getOperatorId().length() > 1) {
			//!Remove leading 'E' from the PS employee operator ID to comply with the 5 long legacy format
			dateChangeProcessParameters.setUserEmployeeId(trigger.getOperatorId().substring(1).toUpperCase()); //strips the 'E' off of the employee id
		}
		HR07_initializeFields(commonParameters, trigger, dateChangeProcessParameters);
		//!call procedures to get necessary PeopleSoft changed values for legacy
		//DO Get-OprId   !gets valid operator ID using code from ZHRI100A.sqr
		String oprId = ZHRI100A.ZHRI100A_getOprId(commonParameters);
		//LET $LegacyEmplid = $PSOprid
		dateChangeProcessParameters.setEmployeeId(oprId);
		//IF $LegacyEmplid <> '' AND $LegacyEmplid <> ' '  !New OprId not null or blank on return
		if(dateChangeProcessParameters.getEmployeeId() != null && !dateChangeProcessParameters.getEmployeeId().isEmpty()) {
			//DO HR07-Get-Employee-Review
			dateChangeProcessParameters = HR07_getEmployeeReview(dateChangeProcessParameters, commonParameters);
			//DO HR07-Get-Accomplishments
			dateChangeProcessParameters = HR07_getAccomplishments(dateChangeProcessParameters, commonParameters);
			//DO HR07-Get-Contract-Data
			dateChangeProcessParameters = HR07_getContractData(dateChangeProcessParameters, commonParameters);
			//DO HR07-Call-RPG
			HR07_callRpg(commonParameters, dateChangeProcessParameters);
		//END-IF    !$LegacyEmplid <> '' and $LegacyEmplid <> ' '
		}
		//END-PROCEDURE HR07-PROCESS-MAIN
		return null;
	}

	/**
	 * HR07-Get-Employee-Review from ZHRI107A.SQC
	 * This procedure retrieves the Next Review Date and Last Review Date from the 
	 * PeopleSoft Employee Review Table to send back to Option 7 of AAHR01 in legacy.
	 * @param dateChangeProcessParameters
	 * @param commonParameters
	 * @return
	 */
	public DateChangeProcessParameters HR07_getEmployeeReview(DateChangeProcessParameters dateChangeProcessParameters, CommonParameters commonParameters) {
		//BEGIN-PROCEDURE HR07-GET-EMPLOYEE-REVIEW
		//BEGIN-SELECT
		PsEmployeeReview psEmployeeReview = 
				PsEmployeeReview.findByEmployeeIdAndEffectiveDateAndEmploymentRecordNumber(
						commonParameters.getEmployeeId(), commonParameters.getEffectiveDate(), new BigDecimal(0));
		//TO_CHAR(CER7.NEXT_REVIEW_DT, 'YYYY-MM-DD')  &CER7NEXT_REVIEW_DT
		//LET $PSNextReviewDate = &CER7NEXT_REVIEW_DT
		//LET $LegNextReviewDate = $PSNextReviewDate
		//CER7.EFFDT
		//LET $LegLastReviewDate = $LegEffdt
		//!Format next review date and last review date so legacy will accept it (MM field, DD field and CCYY field)
		//UNSTRING $PSNextReviewDate BY '-' INTO $LegNxtRevDtYear $LegNxtRevDtMonth $LegNxtRevDtDay
		dateChangeProcessParameters.setNextReviewYear(new SimpleDateFormat("yyyy").format(psEmployeeReview.getNextReviewDt()));
		dateChangeProcessParameters.setNextReviewMonth(new SimpleDateFormat("mm").format(psEmployeeReview.getNextReviewDt()));
		dateChangeProcessParameters.setNextReviewDay(new SimpleDateFormat("dd").format(psEmployeeReview.getNextReviewDt()));
		//UNSTRING $LegLastReviewDate BY '-' INTO $LegLstRevDtYear $LegLstRevDtMonth $LegLstRevDtDay
		dateChangeProcessParameters.setLastReviewYear(new SimpleDateFormat("yyyy").format(psEmployeeReview.getEffectiveDate()));
		dateChangeProcessParameters.setLastReviewMonth(new SimpleDateFormat("mm").format(psEmployeeReview.getEffectiveDate()));
		dateChangeProcessParameters.setLastReviewDay(new SimpleDateFormat("dd").format(psEmployeeReview.getEffectiveDate()));
		//FROM PS_Employee_Review CER7
		//WHERE CER7.EmplId = $PSEMPLID
			//AND CER7.EMPL_RCD = 0
			//AND TO_CHAR(CER7.EFFDT, 'YYYY-MM-DD') = $PSEFFDT
		//END-SELECT
		//END-PROCEDURE HR07-GET-EMPLOYEE-REVIEW
		return dateChangeProcessParameters;
	}

	/**
	 * HR07-Get-Accomplishments from ZHRI107A.SQC
	 * This procedure retrieves the Negative Drug Test Date and Physical Test Date from the 
	 * PeopleSoft Accomplishments Table to send back to Option 7 of AAHR01.
	 * @param dateChangeProcessParameters
	 * @param commonParameters
	 * @return
	 */
	public DateChangeProcessParameters HR07_getAccomplishments(DateChangeProcessParameters dateChangeProcessParameters, CommonParameters commonParameters) {
		List<String> accomplishmentCodeList = Arrays.asList("DRUGTST", "PHYS L3", "PHYS L4");
		PsAccomplishment psAccomplishment = PsAccomplishment.findByEmployeeIdAndAccomplishmentCodes(commonParameters.getEmployeeId(), accomplishmentCodeList);
		//BEGIN-PROCEDURE HR07-GET-ACCOMPLISHMENTS
		//LET $SelectAccomplishment = LTRIM(RTRIM(&CA7.ACCOMPLISHMENT,' '),' ')
		//!select the correct accomplishment to pull in the correct date
		//EVALUATE $SelectAccomplishment
		switch(psAccomplishment.getAccomplishmentCode()) {
			//!when accomplishment is equal to negative drug test
			//WHEN = 'DRUGTST'
			case "DRUGTST":
				//LET $LegNegDrugTestDate = $PSIssueDate
				dateChangeProcessParameters.setNegDrugTestYear(new SimpleDateFormat("yyyy").format(psAccomplishment.getDateIssued()));
				dateChangeProcessParameters.setNegDrugTestMonth(new SimpleDateFormat("mm").format(psAccomplishment.getDateIssued()));
				dateChangeProcessParameters.setNegDrugTestDay(new SimpleDateFormat("dd").format(psAccomplishment.getDateIssued()));
				//!Format negative drug test date so legacy will accept it (MM field, DD field and CCYY field)
				//UNSTRING $PSIssueDate BY '-' INTO $LegNegDrugTstYear $LegNegDrugTstMonth $LegNegDrugTstDay
				//BREAK                 !Exit the evaluate statement
			break;
			//!when accomplishment is equal to physical test date for level 3 or level 4
			//WHEN = 'PHYS L3' OR WHEN = 'PHYS L4'
			case "PHYS L3":
			case "PHYS L4":
				//LET $LegPhysical3TestDate = $PSIssueDate
				//LET $LegPhysical4TestDate = $PSIssueDate
				dateChangeProcessParameters.setPhysTestYear(new SimpleDateFormat("yyyy").format(psAccomplishment.getDateIssued()));
				dateChangeProcessParameters.setPhysTestMonth(new SimpleDateFormat("mm").format(psAccomplishment.getDateIssued()));
				dateChangeProcessParameters.setPhysTestDay(new SimpleDateFormat("dd").format(psAccomplishment.getDateIssued()));
				//!Format physical test date so legacy will accept it (MM field, DD field and CCYY field)
				//UNSTRING $PSIssueDate BY '-' INTO $LegPhysTstYear $LegPhysTstMonth $LegPhysTstDay
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
	 * HR07-Get-Contract-Data from ZHRI107A.SQC
	 * This procedure retrieves the contract date from the PeopleSoft Contract Data Table to send back to Option 7 of AAHR01.
	 * @param dateChangeProcessParameters
	 * @param commonParameters
	 * @return
	 */
	public DateChangeProcessParameters HR07_getContractData(DateChangeProcessParameters dateChangeProcessParameters, CommonParameters commonParameters) {
		//BEGIN-PROCEDURE HR07-GET-CONTRACT-DATA
		//BEGIN-SELECT
		PsContractData psContractData = PsContractData.findByEmployeeId(commonParameters.getEmployeeId());
		//TO_CHAR(CCD7.CONTRACT_BEGIN_DT, 'YYYY-MM-DD')  &CCD7EFFDT
		//LET $LegContractDate = &CCD7EFFDT
		//!Format contract date so legacy will accept it (MM field, DD field and CCYY field)
		//UNSTRING $LegContractDate BY '-' INTO $LegContractDtYear $LegContractDtMonth $LegContractDtDay
		dateChangeProcessParameters.setContractYear(new SimpleDateFormat("yyyy").format(psContractData.getContractBeginDt()));
		dateChangeProcessParameters.setContractMonth(new SimpleDateFormat("mm").format(psContractData.getContractBeginDt()));
		dateChangeProcessParameters.setContractDay(new SimpleDateFormat("dd").format(psContractData.getContractBeginDt()));
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

	/**
	 * HR07-Call-RPG from ZHRI107A.SQC
	 * This procedure calls the RPG program to update the legacy files/fields needed by Option 7 of AAHR01.
	 * @param dateChangeProcessParameters
	 * @return
	 */
	public String HR07_callRpg(CommonParameters commonParameters, DateChangeProcessParameters dateChangeProcessParameters) {
		//BEGIN-PROCEDURE HR07-CALL-RPG
		//!Setup the parameter list with what needs to be passed to the RPG program to update the legacy system
		String parameterString = composeParameterStringForHrz107AProcess(dateChangeProcessParameters);
		commonParameters.setProcessName("HRZ107A");
		String commandString = ZHRI100A.composeCommandString(commonParameters, parameterString);
		//DO Call-System    !Do a remote call to the RPG program, HRZ107A, in order to pass the parms from code in ZHRI100A.sqr
		Integer status = ZHRI100A.ZHRI100A_callSystem(commandString, commonParameters);
		//IF (#STATUS = 0)
		if(status == 0) {
			//LET $CompletionStatus = 'C'
		//END-IF    !#STATUS = 0
		}
		//END-PROCEDURE HR07-CALL-RPG
		return null;
	}
	
	/**
	 * composeParameterStringForHrz107AProcess
	 * Setup the parameter list with what needs to be passed to the RPG program to update the legacy system
	 * @param dateChangeProcessParameters
	 * @return
	 */
	public String composeParameterStringForHrz107AProcess(DateChangeProcessParameters dateChangeProcessParameters) {
		System.out.println("********** composeParameterStringForHrz107AProcess");
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
		String parameterString = "'" + dateChangeProcessParameters.getEmployeeId() + "' "
				+ "'" + dateChangeProcessParameters.getEmployeeId() + "' "
				+ "'" + dateChangeProcessParameters.getUserEmployeeId() + "' "
				+ "'" + dateChangeProcessParameters.getHireMonth() + "' "
				+ "'" + dateChangeProcessParameters.getHireYear() + "' "
				+ "'" + dateChangeProcessParameters.getTerminationMonth() + "' "
				+ "'" + dateChangeProcessParameters.getTerminationDay() + "' "
				+ "'" + dateChangeProcessParameters.getTerminationYear() + "' "
				+ "'" + dateChangeProcessParameters.getLastReviewMonth() + "' "
				+ "'" + dateChangeProcessParameters.getLastReviewDay() + "' "
				+ "'" + dateChangeProcessParameters.getLastReviewYear() + "' "
				+ "'" + dateChangeProcessParameters.getNextReviewMonth() + "' "
				+ "'" + dateChangeProcessParameters.getNextReviewDay() + "' "
				+ "'" + dateChangeProcessParameters.getNextReviewYear() + "' "
				+ "'" + dateChangeProcessParameters.getNegDrugTestMonth() + "' "
				+ "'" + dateChangeProcessParameters.getNegDrugTestDay() + "' "
				+ "'" + dateChangeProcessParameters.getNegDrugTestYear()+ "' "
				+ "'" + dateChangeProcessParameters.getPhysTestMonth() + "' "
				+ "'" + dateChangeProcessParameters.getPhysTestDay() + "' "
				+ "'" + dateChangeProcessParameters.getContractMonth() + "' "
				+ "'" + dateChangeProcessParameters.getContractDay() + "' "
				+ "'" + dateChangeProcessParameters.getContractYear() + "' "
				+ "'" + dateChangeProcessParameters.getCompanySeniorityMonth() + "' "
				+ "'" + dateChangeProcessParameters.getCompanySeniorityDay() + "' "
				+ "'" + dateChangeProcessParameters.getCompanySeniorityYear() + "' "
				+ "'" + dateChangeProcessParameters.getEmployeeId() + "'";
		return parameterString;
	}

}

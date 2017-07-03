package erd;

import java.util.Calendar;
import java.util.Queue;

import erd.controller.EmployeeDateChange;
import erd.controller.EmployeeDemographicChange;
import erd.controller.EmployeeGroupTransfer;
import erd.controller.EmployeeJobProfileChange;
import erd.controller.EmployeeNewHire;
import erd.controller.EmployeeTermination;
import erd.controller.ZHRI100A;
import erd.model.PsDbOwner;
import erd.model.PsJob;
import erd.model.PszTriggerEmployee;
import erd.model.PszTriggerInterface;
import erd.model.PszTriggerNonPerson;
import erd.model.PszVariable;
import erd.model.Zhri100aFields;

/*
 * Imports Data
 * @Emem Isaac
 * */
public class DataImporter implements DataImportInterface  {

	/* (non-Javadoc)
	 * @see erd.DataImportInterface#Import(erd.model.PszTriggerEmployee)
	 */
	@Override
	public Object Import(PszTriggerEmployee employeeTrigger){
		
		if(employeeTrigger == null){
			return null;
		}
		
		//Gather data
		Object gatheredData = new Object();
		
		//Hand data over to Exporter
		DataExportInterface exporter = new DataExporter();
		exporter.Export(gatheredData, employeeTrigger);
				
		return employeeTrigger;
	}
	
	/* (non-Javadoc)
	 * @see erd.DataImportInterface#Import(erd.model.PszTriggerNonPerson)
	 */
	@Override
	public Object Import(PszTriggerNonPerson nonPersonTrigger){
		Object importedData = null;
		
		return importedData;
	}
	
	public void processTrigger(PszTriggerInterface trigger, Zhri100aFields zhri100aFields){
		
		if(trigger == null || zhri100aFields == null){
			return;
		}
		
		//LET $CompletionStatus = 'P'   !Initialize the CompletionStatus field
		String completionStatus = "P";
				
		//LET $PoiFlag = 'N'  !Surya Added - TEMPMAST 
		zhri100aFields.setPoiFlag(false);
		
		//DO Check-If-Contractor
		Boolean isContractor = PsJob.ZHRI100A_checkIfContractor(trigger.getEmployeeId());
		
		//IF $Found = 'N' AND  $PSEmplid <> ''  !Not a contractor and not a blank EmplId   !ZHR_MOD_ZHRI100A_110A
		System.out.println("************** !isContractor && trigger.getEmployeeId() != null && !trigger.getEmployeeId().isEmpty(): " + (!isContractor && trigger.getEmployeeId() != null && !trigger.getEmployeeId().isEmpty()));
		
		if(!isContractor && trigger.getEmployeeId() != null && !trigger.getEmployeeId().isEmpty()) {
			//Added a check for 'ZHRI102A' - to see if corresponding row on JOB 
			//IF $WrkProcess = 'ZHRI102A'
			if("ZHRI102A".equalsIgnoreCase(trigger.getProcessName())) {
				//DO Check-If-Correct102A
				Boolean isOkToProcess = PsJob.ZHRI100A_checkIfCorrect102A(trigger.getEmployeeId(), trigger.getEffectiveDate(), trigger.getProcessName());
				System.out.println("************** isOkToProcess: " + isOkToProcess);
		
				//IF $OK-to-process = 'Y'
				if(isOkToProcess) {
				
					//DO Call-Programs
					ZHRI100A_callPrograms(trigger, zhri100aFields);
				}
				//ELSE                                                           
				else {
					//LET $CompletionStatus = 'C'                                 
					completionStatus = "C";
			
					//DO UPDATE-TRIGGER-ROW                                       
					int numberOfRecordsUpdated = PszTriggerEmployee.setCompletionStatusBySequenceNumber(completionStatus, trigger.getSequenceNumber());
					System.out.println("************** numberOfRecordsUpdated: " + numberOfRecordsUpdated);
					completionStatus = "P";     //!Reset the completion Status for next pass
					
					//END-IF                                                         
				}
			}
		}
		//ELSE
		else {
			//IF $Found = 'Y'
			if(isContractor) {
				//LET $CompletionStatus = 'C'
				completionStatus = "C";
				//END-IF
			}
			//IF  $PSEmplid = ''
			if(trigger.getEmployeeId() == null || trigger.getEmployeeId().isEmpty()) {
				//LET $CompletionStatus = 'E'
				completionStatus = "E";
				//END-IF
			}
			//END-IF  !$Found = 'N'
		}
		
		Boolean adFound = false; //$AdFound  //TODO: where should this be set???
		//IF $CompletionStatus <> 'P'
		if(!"P".equalsIgnoreCase(completionStatus)) {
			//	IF ($ADAction_Code <> '') AND ($ADLegOprid <> '')
			if(!zhri100aFields.getActionCode().isEmpty() && !zhri100aFields.getLegacyOperatorId().isEmpty()) {
				//!DO Check-EffDt-Transaction
				//do nothing
				//IF $AdFound = 'N'
				if(!adFound) {
					//!DO Build-Active-Dir-Output-File	!ZHR_ZHRI100A_REMOVE_AD
					//do nothing
					//END-IF
				}
			//END-IF
			}
			//DO UPDATE-TRIGGER-ROW
			int numberOfRecordsUpdated = PszTriggerEmployee.setCompletionStatusBySequenceNumber(completionStatus, trigger.getSequenceNumber());
			System.out.println("numberOfRecordsUpdated: " + numberOfRecordsUpdated);
			//END-IF  !$CompletionStatus <> 'P'
		}
	}
	
	public Object Import(Queue<PszTriggerInterface> triggers){
		
		Zhri100aFields zhri100aFields = ZHRI100A.initializeMainProperties();
		
		//LET $WrkCriticalFlag = 'N'
		zhri100aFields.setCriticalFlag(false);
		
		for(int i=0; i< triggers.size(); i++){
			processTrigger(triggers.poll(), zhri100aFields);
		}
		return null;
	}
	
	
	public static String ZHRI100A_callPrograms(PszTriggerInterface trigger, Zhri100aFields zhri100aFields) {
		System.out.println("********** ZHRI100A_callPrograms");
		String command = "";
		//BEGIN-PROCEDURE CALL-PROGRAMS
		//DO Initialize-AD-WrkFields
		//LET $TrigTaskFlag = ''
		
		switch(trigger.getProcessName()) {
		
		case "ZHRI101A": //Process to hire employee
			//WHEN = 'ZHRI101A'
			//!Move fields to be used in the called SQC
			//LET $Wrk_Oprid = $AuditOprid
			zhri100aFields.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
			//LET $Wrk_Effdt = $PSEffdt
			zhri100aFields.setEffectiveDate(trigger.getEffectiveDate());
			//move #PSEffseq to #Wrk_Effseq
			zhri100aFields.setIndexNumber(trigger.getEffectiveSequence());
			//LET $Wrk_Process_Name = $WrkProcess
			zhri100aFields.setProcessName(trigger.getProcessName());
			//LET $TrigTaskFlag = $WrkTaskFlag      !Surya Added - TEMPMAST
			zhri100aFields.setCompletionStatus(trigger.getCompletionStatus());
			//LET $Wrk_Inf_ = ' '
			//LET $ADAction_Code = 'H'
			zhri100aFields.setActionCode("H");
			//LET $ADLegOprid = ''
			zhri100aFields.setLegacyOperatorId("");
			//DO HR01-Process-Main    !ZHRI101A.SQC
			EmployeeNewHire employeeNewHire = new EmployeeNewHire(trigger, zhri100aFields);
			zhri100aFields.setCompletionStatus(employeeNewHire.HR01_processMain());
			//BREAK
			break;
		case "ZHRI102A": //Process to terminate an employee
			//WHEN = 'ZHRI102A'
			//!Move fields to be used in the called SQC
			//MOVE #Wrk_Sequence to #WrkSeqNbr
			zhri100aFields.setIndexNumber(trigger.getEffectiveSequence());
			//LET $PSAuditOperId = $AuditOprId
			zhri100aFields.setAuditOperatorId(trigger.getOperatorId());
	        //LET $PSDateIn = $PSEffDt
			zhri100aFields.setEffectiveDate(trigger.getEffectiveDate());
	        //LET $Wrk_Emplid = $PSEmplId
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
	        //LET $ADAction_Code = 'T'
			zhri100aFields.setActionCode("T");
	        //LET $ADLegOprid = ''
			zhri100aFields.setLegacyOperatorId("");
	        //DO HR02-Process-Main    !ZHRI102A.SQC
			EmployeeTermination employeeTermination = new EmployeeTermination(trigger, zhri100aFields);
			zhri100aFields.setCompletionStatus(employeeTermination.HR02_processMain());
			//BREAK
			break;
		case "ZHRI104A": //Process for job status change
			//WHEN = 'ZHRI104A'
			//!Move fields to be used in the called SQC
			//LET $PSUserOprid = $AuditOprid
			zhri100aFields.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
			//Move #PSEffseq to #WrkEffseq
			zhri100aFields.setIndexNumber(trigger.getEffectiveSequence());
			//LET $ADAction_Code = 'C'
			zhri100aFields.setActionCode("C");
			//LET $ADLegOprid = ''
			zhri100aFields.setLegacyOperatorId("");
			//DO HR04-Process-Main    !ZHRI104A.SQC
			EmployeeJobProfileChange employeeJobProfileChange = new EmployeeJobProfileChange(trigger, zhri100aFields);
			zhri100aFields.setCompletionStatus(employeeJobProfileChange.HR04_processMain());
			//BREAK
			break;
		case "ZHRI105A": //Process for demographics change
			//WHEN = 'ZHRI105A'
			//!Move fields to be used in the called SQC
			//LET $PSemp = $AuditOprid
			zhri100aFields.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
			//LET $ADAction_Code = 'C'
			zhri100aFields.setActionCode("C");
			//LET $ADLegOprid = ''
			zhri100aFields.setLegacyOperatorId("");
			//LET $Wrk_ADCountryCdBuild = 'Y'                 !sree-UAAMOD
			//DO HR05-Process-Main    !ZHRI105A.SQC
			EmployeeDemographicChange employeeDemographicChange = new EmployeeDemographicChange(trigger, zhri100aFields);
			zhri100aFields.setCompletionStatus(employeeDemographicChange.HR05_processMain());
			//BREAK
			break;
		case "ZHRI106A": 
			//WHEN = 'ZHRI106A'
			//!Move fields to be used in the called SQC
			//LET $Wrk_Oprid = $AuditOprid
			zhri100aFields.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
			//LET $Wrk_Effdt = $PSEffdt
			zhri100aFields.setEffectiveDate(trigger.getEffectiveDate());
			//move #PSEffseq to #Wrk_Effseq
			zhri100aFields.setIndexNumber(trigger.getEffectiveSequence());
			//LET $Wrk_Process_Name = $WrkProcess
			zhri100aFields.setProcessName(trigger.getProcessName());
			//LET $ADAction_Code = 'R'
			zhri100aFields.setActionCode("R");
			//DO HR01-Process-Main       !ZHRI101A.SQC
			//BREAK
			break;
		case "ZHRI107A": //Process for converting dates
			//WHEN = 'ZHRI107A'
			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
			//LET $ADAction_Code = ''
			zhri100aFields.setActionCode("");
			//LET $ADLegOprid = ''
			zhri100aFields.setLegacyOperatorId("");
			//DO HR07-Process-Main
			EmployeeDateChange employeeDateChange = new EmployeeDateChange(trigger, zhri100aFields);
			zhri100aFields.setCompletionStatus(employeeDateChange.HR07_processMain());
			//BREAK
			break;
		case "ZHRI109A": //Process for group transfer
			//WHEN = 'ZHRI109A'
			//!Move fields to be used in the called SQC
			//LET $PSUserOprid = $AuditOprid
			zhri100aFields.setOperatorId(trigger.getOperatorId());
			//LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
			zhri100aFields.setEmployeeId(trigger.getEmployeeId());
			//Move #PSEffseq to #WrkEffseq
			zhri100aFields.setIndexNumber(trigger.getEffectiveSequence());
			//LET $ADAction_Code = 'C'
			zhri100aFields.setActionCode("C");
			//LET $ADLegOprid = ''
			zhri100aFields.setLegacyOperatorId("");
			//DO HR09-Process-Main        !ZHRI100A.SQC
			EmployeeGroupTransfer employeeGroupTransfer = new EmployeeGroupTransfer(trigger, zhri100aFields);
			zhri100aFields.setCompletionStatus(employeeGroupTransfer.HR09_processMain());
			//BREAK
			break;
		case "ZHRI101D": 
			//WHEN = 'ZHRI101D'     !Row deleted on hire
			//LET $ErrorProgramParm = 'HRZ101A'
			zhri100aFields.setErrorProgramParameter("HRZ101A");
			//LET $ErrorMessageParm = 'A row was deleted on the hire process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the hire process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A.ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			command = ZHRI100A.ZHRI100A_callErrorRoutine(trigger.getProcessName(), zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		case "ZHRI102D": 
			//WHEN = 'ZHRI102D'     !Row deleted on term
			//LET $ErrorProgramParm = 'HRZ102A'
			zhri100aFields.setErrorProgramParameter("HRZ102A");
			//LET $ErrorMessageParm = 'A row was deleted on the termination process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the termination process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A.ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			command = ZHRI100A.ZHRI100A_callErrorRoutine(trigger.getProcessName(), zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		case "ZHRI104D": 
			//WHEN = 'ZHRI104D'     !Row deleted on job status change
			//LET $ErrorProgramParm = 'HRZ104A'
			zhri100aFields.setErrorProgramParameter("HRZ104A");
			//LET $ErrorMessageParm = 'A row was deleted on the job profile process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the job profile process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A.ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			command = ZHRI100A.ZHRI100A_callErrorRoutine(trigger.getProcessName(), zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		case "ZHRI105D": 
			//WHEN = 'ZHRI105D'     !Row deleted on demographics change
			//LET $ErrorProgramParm = 'HRZ105A'
			zhri100aFields.setErrorProgramParameter("HRZ105A");
			//LET $ErrorMessageParm = 'A row was deleted on the demographics process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the demographics process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A.ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			command = ZHRI100A.ZHRI100A_callErrorRoutine(trigger.getProcessName(), zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		case "ZHRI106D": 
			//WHEN = 'ZHRI106D'     !Row deleted on rehire
			//LET $ErrorProgramParm = 'HRZ101A'
			zhri100aFields.setErrorProgramParameter("HRZ101A");
			//LET $ErrorMessageParm = 'A row was deleted on the re-hire process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the re-hire process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A.ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			command = ZHRI100A.ZHRI100A_callErrorRoutine(trigger.getProcessName(), zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		case "ZHRI107D": 
			//WHEN = 'ZHRI107D'     !Row deleted on
			//LET $ErrorProgramParm = 'HRZ107A'
			zhri100aFields.setErrorProgramParameter("HRZ107A");
			//LET $ErrorMessageParm = 'A row was deleted on the dates process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the dates process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A.ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			command = ZHRI100A.ZHRI100A_callErrorRoutine(trigger.getProcessName(), zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		case "ZHRI109D": 
			//WHEN = 'ZHRI109D'
			//LET $ErrorProgramParm = 'HRZ109A'
			zhri100aFields.setErrorProgramParameter("HRZ109A");
			//LET $ErrorMessageParm = 'A row was deleted on the group transfer process'
			zhri100aFields.setErrorMessageParameter("A row was deleted on the group transfer process");
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.setCriticalFlag(true);
			//DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
			ZHRI100A.ZHRI100A_prepareErrorParms(zhri100aFields);
			//DO Call-Error-Routine
			command = ZHRI100A.ZHRI100A_callErrorRoutine(trigger.getProcessName(), zhri100aFields);
			//LET $WrkCriticalFlag = 'N'
			zhri100aFields.setCriticalFlag(false);
			//LET $CompletionStatus = 'C'
			zhri100aFields.setCompletionStatus("C");
			//DO UPDATE-TRIGGER-ROW
			break;
		default:
			//WHEN-OTHER
			//LET $CompletionStatus = 'E'     !update to an E to prevent looping and to mark the record in error
			zhri100aFields.setCompletionStatus("E");
			//DO UPDATE-TRIGGER-ROW
			//BREAK
			break;

		//END-EVALUATE
		}
	//END-PROCEDURE CALL-PROGRAMS
		return zhri100aFields.getCompletionStatus();
	}


}

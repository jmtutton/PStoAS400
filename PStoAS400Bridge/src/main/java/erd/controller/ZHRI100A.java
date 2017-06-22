package erd.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import erd.DateUtil;
import erd.model.AS400Package;
import erd.model.CrossReferenceEmployeeId;
import erd.model.CrossReferenceMultipleEmployeeId;
import erd.model.HR036P;
import erd.model.PsDbOwner;
import erd.model.PsJob;
import erd.model.PszTriggerEmployee;
import erd.model.PszVariable;
import erd.model.PszXlat;
import erd.model.Zhri100aFields;

public class ZHRI100A {

//	public static String psEmpl; //$PSEmpl
//	public static String psOprid; //$PSOprid
//	public static String errorProgramParm = "HRZ102A"; //$ErrorProgramParm = 'HRZ102A'
	
	public static void main() {
		//begin-program
		//Get-Current-DateTime  //queries the database to get the current time. It also initializes a slew of string variables ($AsOfToday, $AsOfNow, $CurrentCentury, $ReportDate
		//Init-DateTime  //sets a collection of variables that can be used by the other procedures in datetime.sqc that format dates or do date arithmetic
		//Process-Main
		try {
			processMain();
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Reset          !Reset.sqc
		//end-program
	}
	
	
	//begin-program
	//Get-Current-DateTime  //queries the database to get the current time. It also initializes a slew of string variables ($AsOfToday, $AsOfNow, $CurrentCentury, $ReportDate
	//Init-DateTime  //sets a collection of variables that can be used by the other procedures in datetime.sqc that format dates or do date arithmetic
	//ZHRI100A.Process-Main
	//	ZHRI100A.Get-Variable()
	//	ZHRI100A.Check-Interface-Runfile
	//  ZHRI100A.Built-Where-Clause-Delay
	//	ZHRI100A.Get-Trigger-Data
	//    	Begin-Select loops=150
	//		ZHRI100A.Check-If-Contractor
	//		ZHRI100A.Check-If-Correct102A
	//		ZHRI100A.Call-Programs
	//			ZHRI100A.Intialize-AD-WrkFields
	//			HR02-Process-Main  //This is the main processing procedure
	//				HR02-Initialize-Fields  //Initialize the fields to ensure that that they all start out blank.
	//				HR02-Get-Job  //This routine will the Job Data row for each of the employee numbers entered in the trigger file.
	//					HR02-Get-Action-Reason  //This routine will determine if a termination was voluntary or involuntary basedd on Action and Action Reason codes.
	//						HR02-Get-Reason-Description  //This routine gets the description field from the Action Reason table when Action = Termination and Action Code equals Other.
	//				ZHRI100A.Get-OprId
	//				HR02-Process-Data  //This routine moves 'N' to change address parameter and calls the RPG program.
	//					Remove-Non-Letters-Numbers
	//					HR02-Trim-Parameters
	//					ZHRI100A.Call-System
	//						Get-Current-DateTime
	//						Call System Using $Command
	//			End-Procedure HR02-Process-Main
	//		ZHRI100A.Update-Trigger-Row
	//	End-Procedure Get-Trigger-Data
	//	Commit-Transaction
	//	Call System Using $Command #status Wait
	//Reset.sqc
	//end-program
	
	
	
	/**
	 * Process-Main from ZHRI100A,SQR
	 * This is the process controlling procedure.
	 * @throws InterruptedException 
	 */
	public static void processMain() throws InterruptedException {
		Zhri100aFields zhri100aFields;
		//Begin-Procedure Process-Main
		//ZHRI100A.Get-Variable
		zhri100aFields = initializeMainProperties();
		//LET $WrkCriticalFlag = 'N'
		zhri100aFields.wrkCriticalFlag = false;
		//LET #run_flag = 1
		Boolean runFlag = true;
		//while #run_flag = 1        !Never ending loop
		while(runFlag == true) {
			//ZHRI100A.Check-Interface-Runfile
//			runFlag = ZHRI100A_CheckInterfaceRunFile(zhri100aFields.oracleSystemId);
			//ZHRI100A.Get-Trigger-Data       !Process the interface requests
			ZHRI100A_getTriggerData(zhri100aFields);
			//TRANCTRL.Commit-Transaction
			//LET $Command = 'sleep 15'  !After interface run wait 15 seconds and do it again  !sree**rehost  !ZHR_MOD_ZHRI100A_sleep
			//Call System Using $Command #status Wait  !sree**rehost  !ZHR_MOD_ZHRI100A_sleep
			//sleep for 15 seconds (15000 milliseconds)
			Thread.sleep(15000);
			//IF $file_open = 'Y'
				//CLOSE 1
				//TODO:
			//END-IF
			//LET $file_open = 'N'
			runFlag = false; //*** I put this here for testing, so it stops after one iteration.
		//end-while   !1=1
		}
		//LET $Command = 'mv' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.stop' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'
		String command = "mv" + " " + "/usr/local/barch/" + zhri100aFields.oracleSystemId + "/work/hrinterface.stop" + " " + "/usr/local/barch/" + zhri100aFields.oracleSystemId + "/work/hrinterface.run";
		//Show  + $Command in Process-Main: ' $Command
		System.out.println("$Command in Process-Main: " + command);
		//Call System Using $Command #status Wait   !ZHR_MOD_ZHRI100A_sleep       
		//End-Procedure Process-Main
	}

	/**
	 * FTP-File from ZHRI100A.SQR
	 * This procedure will transfer the file from UNIX to NT server
	 */
	public static void ZHRI100A_ftpFile() {
		//BEGIN-PROCEDURE FTP-FILE !LJM-04/03/01-Rehost
		//IF #status != 0
		//END-IF
		//END-PROCEDURE
		//*** do nothing ***
	}

	/**
	 * Call-Programs from ZHRI100A.SQR
	 * Subroutine will call appropriate programs
	 * @param trigger 
	 * @param zhri100aFields 
	 */
	public static void ZHRI100A_callPrograms(PszTriggerEmployee trigger, Zhri100aFields zhri100aFields) {
		//WHEN = 'ZHRI102A'
		//!Move fields to be used in the called SQC
		//MOVE #Wrk_Sequence to #WrkSeqNbr
//		wrkSeqNbr = wrkSequence;  //TODO: find where #Wrk_Sequence is set
		//LET $PSAuditOperId = $AuditOprId
		zhri100aFields.auditOperatorId = trigger.getOperatorId();
        //LET $PSDateIn = $PSEffDt
		zhri100aFields.effectiveDate = trigger.getEffectiveDate();
        //LET $Wrk_Emplid = $PSEmplId
		zhri100aFields.employeeId = trigger.getEmployeeId();
        //LET $ADAction_Code = 'T'
		zhri100aFields.adActionCode = "T";
        //LET $ADLegOprid = ''
		zhri100aFields.adLegacyOperatorId = "";
        //DO HR02-Process-Main    !ZHRI102A.SQC
		EmployeeTermination employeeTermination = new EmployeeTermination(trigger, zhri100aFields);
		employeeTermination.HR02_processMain();

		//Begin-Procedure Call-Programs
		//DO Intialize-AD-WrkFields
		//LET $TrigTaskFlag = ''
		//evaluate $WrkProcess
		//    when = 'ZHRI101A'
		//        !Move fields to be used in the called SQC
		//        LET $Wrk_Oprid = $AuditOprid
		//        LET $Wrk_Emplid = $PSEmplid
		//        LET $Wrk_Effdt = $PSEffdt
		//        move #PSEffseq to #Wrk_Effseq
		//        LET $Wrk_Process_Name = $WrkProcess
		//        LET $TrigTaskFlag = $WrkTaskFlag      !Surya Added - TEMPMAST 
		//        LET $Wrk_Inf_ = ' '
		//        LET $ADAction_Code = 'H'
		//        LET $ADLegOprid = ''
		//        DO HR01-Process-Main    !ZHRI101A.SQC
		//        break
		//    when = 'ZHRI102A'
		//        !Move fields to be used in the called SQC
		//        Move #Wrk_Sequence to #WrkSeqNbr
		//        LET $PSAuditOperId = $AuditOprid
		//        LET $PSDateIn = $PSEffdt
		//        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
		//        LET $ADAction_Code = 'T'
		//        LET $ADLegOprid = ''
		//        DO HR02-Process-Main    !ZHRI102A.SQC
		//        break
		//    when = 'ZHRI104A'
		//        !Move fields to be used in the called SQC
		//        LET $PSUserOprid = $AuditOprid
		//        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
		//        Move #PSEffseq to #WrkEffseq
		//        LET $ADAction_Code = 'C'
		//        LET $ADLegOprid = ''
		//        DO HR04-Process-Main    !ZHRI104A.SQC
		//        break
		//    when = 'ZHRI105A'
		//        !Move fields to be used in the called SQC
		//        LET $PSemp = $AuditOprid
		//        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
		//        LET $ADAction_Code = 'C'
		//        LET $ADLegOprid = ''
		//        LET $Wrk_ADCountryCdBuild = 'Y'                 !sree-UAAMOD
		//        DO HR05-Process-Main    !ZHRI105A.SQC
		//        break
		//    when = 'ZHRI106A'
		//        !Move fields to be used in the called SQC
		//        LET $Wrk_Oprid = $AuditOprid
		//        LET $Wrk_Emplid = $PSEmplid
		//        LET $Wrk_Effdt = $PSEffdt
		//        move #PSEffseq to #Wrk_Effseq
		//        LET $Wrk_Process_Name = $WrkProcess
		//        LET $ADAction_Code = 'R'
		//        DO HR01-Process-Main       !ZHRI101A.SQC
		//        break
		//    when = 'ZHRI107A'
		//        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
		//        LET $ADAction_Code = ''
		//        LET $ADLegOprid = ''
		//        DO HR07-Process-Main
		//        break
		//    when = 'ZHRI109A'
		//        !Move fields to be used in the called SQC
		//        LET $PSUserOprid = $AuditOprid
		//        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
		//        Move #PSEffseq to #WrkEffseq
		//        LET $ADAction_Code = 'C'
		//        LET $ADLegOprid = ''
		//        DO HR09-Process-Main        !ZHRI100A.SQC
		//        break
		//    when = 'ZHRI101D'     !Row deleted on hire
		//        LET $ErrorProgramParm = 'HRZ101A'
		//        LET $ErrorMessageParm = 'A row was deleted on the hire process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when = 'ZHRI102D'     !Row deleted on term
		//        LET $ErrorProgramParm = 'HRZ102A'
		//        LET $ErrorMessageParm = 'A row was deleted on the termination process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when = 'ZHRI104D'     !Row deleted on jobstatus change
		//        LET $ErrorProgramParm = 'HRZ104A'
		//        LET $ErrorMessageParm = 'A row was deleted on the job-profile process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when = 'ZHRI105D'     !Row deleted on demographis change
		//        LET $ErrorProgramParm = 'HRZ105A'
		//        LET $ErrorMessageParm = 'A row was deleted on the demographics process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when = 'ZHRI106D'     !Row deleted on rehire
		//        LET $ErrorProgramParm = 'HRZ101A'
		//        LET $ErrorMessageParm = 'A row was deleted on the re-hire process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when = 'ZHRI107D'     !Row deleted on
		//        LET $ErrorProgramParm = 'HRZ107A'
		//        LET $ErrorMessageParm = 'A row was deleted on the dates process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when = 'ZHRI109D'
		//        LET $ErrorProgramParm = 'HRZ109A'
		//        LET $ErrorMessageParm = 'A row was deleted on the group transfer process'
		//        LET $WrkCriticalFlag = 'Y'
		//        DO Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
		//        DO Call-Error-Routine
		//        LET $WrkCriticalFlag = 'N'
		//        LET $CompletionStatus = 'C'
		//        DO Update-Trigger-Row
		//    when-other
		//        LET $CompletionStatus = 'E'     !update to an E to prevent looping and to mark the record in error
		//        DO Update-Trigger-Row
		//        break
		//end-evaluate
	//End-Procedure Call-Programs
	}

//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Check-if-POI-Termed
//	! Desc:  This routine checks if it is a POI to EMP transfer
//	!        If it is then the the flag is changed to W and wait for Hire
//	!----------------------------------------------------------------------
//	Begin-Procedure Check-if-POI-Termed
//	LET $taskflag = ' '
//	begin-select
//	SEC.TASK_FLAG
//	    LET $taskflag = LTRIM(RTRIM(&SEC.TASK_FLAG,' '),' ')
//	from PS_ZHRT_ALTTRIGGER SEC
//	WHERE SEC.PROC_NAME = 'ZHRI202A'
//	AND SEC.EMPLID = $PSEmplid
//	AND SEC.SEQUENCE = 0
//	AND SEC.SEQ_NBR = (SELECT MAX(SEC1.SEQ_NBR) 
//	                   FROM PS_ZHRT_ALTTRIGGER SEC1
//	                   WHERE SEC1.PROC_NAME = 'ZHRI202A'
//	                   AND SEC1.EMPLID = $PSEmplid
//	                   AND SEC1.SEQUENCE = 0)     
//	end-select
//	End-Procedure Check-if-POI-Termed

//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Get-Trigger-Data-NonEmp
//	! Desc:  This procedure will get the trigger data for non employees and multiple 
//	! EIDs that needs to be interfaced
//	!----------------------------------------------------------------------
//	Begin-Procedure Get-Trigger-Data-NonEmp
//	LET $NCompletionStatus = 'P'   !Initialize the CompletionStatus field
//	LET $PoiFlag = ''  !Initialize the POIFlag -- Surya Added - TEMPMAST 
//	Begin-Select loops=150
//	RN.SEQ_NBR
//	    MOVE &RN.SEQ_NBR TO #NWrkSequence
//	RN.OPRID
//	    LET $NAuditOprid = LTRIM(RTRIM(&RN.OPRID,' '),' ')
//	RN.EMPLID
//	    LET $NPSEmplid = LTRIM(RTRIM(&RN.EMPLID,' '),' ')
//	    Move $NPSEmplid to #NWrk_EmplID1
//	    LET $NWrk_Emplid2 =  edit(#NWrk_EmplID1,'099999999')
//	to_char(RN.EFFDT, 'YYYY-MM-DD') &RNEFFDT
//	    LET $NPSEffdt = &RNEFFDT
//	RN.EFFSEQ
//	    Move &RN.EFFSEQ to #NPSEffSeq
//	RN.PROC_NAME
//	    LET $NWrkProcess = LTRIM(RTRIM(&RN.PROC_NAME,' '),' ')           !Remove leading and trailing blanks
//	RN.SEQUENCE 
//	    move &RN.SEQUENCE TO #indexNum 
//	   LET $PoiFlag = 'Y'
//	   DO Call-Programs-NonEmp  
//	   IF  $NPSEmplid = ''             
//	      LET $NCompletionStatus = 'E'
//	   END-IF
//	   IF $NCompletionStatus <> 'P'
//	     DO Update-Trigger-Row-NonEmp
//	   END-IF  
//	from PS_ZHRT_ALTTRIGGER RN
//	where RN.TASK_FLAG = 'P'
//	and (RN.EFFDT <= $AsOfToday or RN.PROC_NAME='ZHRI201A' or  RN.PROC_NAME='ZHRI206A')
//	and (case when proc_name in ('ZHRI201A', 'ZHRI206A') then SEQ_NBR else SEQ_NBR*10 END) = 
//	      (select min(case when proc_name in ('ZHRI201A', 'ZHRI206A') then SEQ_NBR else SEQ_NBR*10 END)  
//	                 from  PS_ZHRT_ALTTRIGGER RN2
//	                  where RN2.EMPLID = RN.EMPLID
//	                    AND RN2.SEQUENCE = RN.SEQUENCE
//	                    AND RN2.PROC_NAME = RN.PROC_NAME 
//	                    and RN2.TASK_FLAG = 'P'                                         
//	                    and (RN2.EFFDT <= SYSDATE or RN2.PROC_NAME='ZHRI201A' or        
//	                         RN2.PROC_NAME='ZHRI206A'))                                 
//	End-Select
//	End-Procedure Get-Trigger-Data-NonEmp


//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Call-Programs-NonEmp
//	! Desc:  Subroutine will call appropriate programs for Non Emp
//	!----------------------------------------------------------------------
//	Begin-Procedure Call-Programs-NonEmp
//	evaluate $NWrkProcess
//	    when = 'ZHRI201A'
//	        !Move fields to be used in the called SQC
//	        LET $Wrk_Oprid = $NAuditOprid
//	        LET $Wrk_Emplid = $NPSEmplid
//	        LET $Wrk_Effdt = $NPSEffdt
//	        move #NPSEffseq to #Wrk_Effseq
//	        LET $Wrk_indexNum = to_char(#indexNum)
//	        LET $Wrk_Process_Name = $NWrkProcess
//	        DO HR201-Process-Main    !ZHRI201A.SQC
//	        break
//	    when = 'ZHRI202A'
//	        !Move fields to be used in the called SQC
//	        LET $PSAuditOperId = $NAuditOprid
//	        LET $PSDateIn = $NPSEffdt
//	        LET $Wrk_Emplid = $NPSEmplid 
//	        LET $Wrk_indexNum = to_char(#indexNum)                   
//	        DO HR202-Process-Main    !ZHRI202A.SQC
//	        break
//	    when = 'ZHRI205A'
//	        !Move fields to be used in the called SQC
//	        LET $PSAuditemp = $NAuditOprid
//	        LET $Wrk_Emplid = $NPSEmplid                              
//	        LET $Wrk_indexNum = to_char(#indexNum)
//	        LET $PSEffdt =  $NPSEffdt     
//	        DO HR205-Process-Main    !ZHRI105A.SQC
//	        break
//	    when = 'ZHRI206A'
//	        !Move fields to be used in the called SQC
//	        LET $Wrk_Oprid = $NAuditOprid
//	        LET $Wrk_Emplid = $NPSEmplid
//	        LET $Wrk_Effdt = $NPSEffdt
//	        move #NPSEffseq to #Wrk_Effseq
//	        LET $Wrk_indexNum = to_char(#indexNum)
//	        LET $Wrk_Process_Name = $NWrkProcess
//	        DO HR201-Process-Main       !ZHRI201A.SQC
//	        break
//	    when-other
//	        LET $CompletionStatus = 'E'     !update to an E to prevent looping and to mark the record in error
//	        DO Update-Trigger-Row-NonEmp  !Surya Added - TEMPMAST 
//	        break
//	end-evaluate
//	End-Procedure Call-Programs-NonEmp



//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Update-Trigger-Row-NonEmp
//	! Desc:  This routine update the trigger file flag switch for Non Emp
//	!----------------------------------------------------------------------
//	Begin-Procedure Update-Trigger-Row-NonEmp
//	begin-sql
//	Update PS_ZHRT_ALTTRIGGER
//	     set Task_Flag = $NCompletionStatus
//	where SEQ_NBR = #NWrkSequence
//	end-sql
//	LET $NCompletionStatus = 'P'     !Reset the completion Status for next pass
//	End-Procedure Update-Trigger-Row-NonEmp

	/**
	 * ZHRI100ABuiltWhereClauseDelay from ZHRI100A.SQR
	 * Get the WHERE Clause for the delay hours in POI to EMP Transfer
	 */
	public static String ZHRI100A_buildWhereClauseDelay() {
		String xWhere = PszXlat.findOutput01ByInput01AndInput02("TEMPMAST", "LEGACY-DELAY-HRS");
		String where1 = "AND SYSTIMESTAMP  >= TR.UPDATED_DATETIME + INTERVAL " + "'" + xWhere + "'" + " HOUR";
		//XWER.ZPTF_OUTPUT_01
		//LET $XWHERE = LTRIM(RTRIM(&XWER.ZPTF_OUTPUT_01,' '),' ')
		//LET $WHERE1 = 'AND SYSTIMESTAMP  >= TR.UPDATED_DATETIME + INTERVAL '|| '''' || $XWHERE || '''' || '  HOUR'
		//from PS_ZPTT_XLAT_TBL XWER
		//where XWER.ZPTF_INPUT_01 = 'TEMPMAST'
		//and XWER.ZPTF_INPUT_02 = 'LEGACY-DELAY-HRS'       
		//end-select
		return where1;
	}


//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Get-Trigger-Data-POI-EMP-Convert
//	! Desc:  This procedure will get the trigger data that needs to be interfaced
//	!----------------------------------------------------------------------
//	Begin-Procedure Get-Trigger-Data-POI-EMP-Convert
//	LET $CompletionStatus = 'W'   !Initialize the CompletionStatus field
//	LET $PoiFlag = ''  !Initialize the POIFlag -- Surya Added - TEMPMAST 
//	LET #WrkSequence = 0
//	LET $AuditOprid = ''
//	LET $PSEmplid = ''
//	LET #Wrk_EmplID1 = 0
//	LET $Wrk_Emplid2 = ''
//	LET $PSEffdt = ''
//	LET #PSEffSeq = 0
//	LET $WrkProcess= ''
//	LET $WrkTaskFlag = ''
//	Begin-Select loops=150
//	RA.SEQ_NBR
//	    MOVE &RA.SEQ_NBR TO #WrkSequence
//	RA.OPRID
//	    LET $AuditOprid = LTRIM(RTRIM(&RA.OPRID,' '),' ')
//	Ra.EMPLID
//	    LET $PSEmplid = LTRIM(RTRIM(&RA.EMPLID,' '),' ')
//	    Move $PSEmplid to #Wrk_EmplID1
//	    LET $Wrk_Emplid2 =  edit(#Wrk_EmplID1,'099999999')
//	to_char(RA.EFFDT, 'YYYY-MM-DD') &RAEFFDT
//	    LET $PSEffdt = &RAEFFDT
//	RA.EFFSEQ
//	    Move &RA.EFFSEQ to #PSEffSeq
//	RA.PROC_NAME
//	    LET $WrkProcess = LTRIM(RTRIM(&RA.PROC_NAME,' '),' ')           !Remove leading and trailing blanks
//	RA.TASK_FLAG
//	    LET $WrkTaskFlag = LTRIM(RTRIM(&RA.TASK_FLAG,' '),' ')
//	   LET $PoiFlag = 'N'       
//	   IF  $PSEmplid = ''             
//	      LET $CompletionStatus = 'E'
//	   END-IF
//	   DO Call-Programs 
//	   IF $CompletionStatus <> 'W'
//	      DO Update-Trigger-Row
//	   END-IF       
//	FROM PS_ZHRT_INTTRIGGER RA,
//	     PS_ZHRT_POI_TERM TR
//	WHERE RA.TASK_FLAG = 'W'
//	AND RA.EMPLID = TR.EMPLID
//	AND RA.PROC_NAME='ZHRI101A' 
//	AND TR.PROC_NAME = 'TERM'
//	[$WHERE1]
//	AND TR.UPDATED_DATETIME = (SELECT MAX(T.UPDATED_DATETIME) FROM PS_ZHRT_POI_TERM T
//	                      WHERE T.EMPLID = TR.EMPLID AND T.PROC_NAME = 'TERM' ) 
//	End-Select
//	End-Procedure Get-Trigger-Data-POI-EMP-Convert
//
//	!------------Surya Added - TEMPMAST END-------------------------!
//
	/**
	 * ZHRI100A.Call-System
	 * Executes a command line statement stored in the $Command Variable
	 * @param command
	 * @param zhri100aFields
	 * @return 0 if success, non-zero if error
	 */
	public static Integer ZHRI100A_callSystem(String command, Zhri100aFields zhri100aFields) {
		Integer status; //#Status
//		String showCommand;
//		Integer commandLength = command.length();
//		Integer substringStartPosition = 1;
		//BEGIN-PROCEDURE CALL-SYSTEM
		//LET #CommandLength = length($Command)   !Get the length of the command
//		commandLength = command.length();
		//LET #SubstrStartPos = 1  !Initiate the starting positions to show the first 100 positions
//		substringStartPosition = 1;
		//WHILE #SubstrStartPos <= #CommandLength
//		while(substringStartPosition <= commandLength) {
			//LET $ShowCommand = SUBSTR($Command, #SubstrStartPos, 100)  !Substring 100 positions to show
//			showCommand = command.substring(substringStartPosition, 100);
			//LET #SubstrStartPos = #SubstrStartPos + 100  !Increase the starting position by 100
//			substringStartPosition += 100;
			//SHOW $ShowCommand  !ZHR_MOD_ZHRI100A_110B
			System.out.println("command: " + command);
		//END-WHILE   !#SubstrStartPos <= #CommandLength
//		}
		//LET $Command = $RexecScript || ' ' || $Command || ' ' || $RMTSVR
		command = zhri100aFields.remoteExecScript + " " +  command + " " + zhri100aFields.remoteServerName;
		//SHOW '$Command=> ' $Command
		System.out.println("command: " + command);
		//DO Get-Current-Datetime  !Gets the current date and time using curdttim.sqc
		java.util.Date currentDate = new java.util.Date();
		//SHOW 'Calling Command at: ' $SysDateTime  !Surya Added - TEMPMAST 
		System.out.println("Calling Command at: " + currentDate);
		//Call System Using $Command #Status Wait  !Execute the command that was built on the command waiting until completion
		status = callSystemUsingCommand(command, zhri100aFields);
		//IF #status != 0
		if(!"0".equals(status)) {
			//!error
			//LET $ErrorProgramParm = 'ZHRI100A'
			zhri100aFields.errorProgramParameter = "ZHRI100A";
			//LET $ErrorMessageParm = ' '
			//LET $ErrorMessageParm = 'Error executing Call System command, contact HR-PeopleSoft On-Call'
			zhri100aFields.errorMessageParameter = "Error executing Call System command, contact HR-PeopleSoft Oncall";
			//LET $WrkCriticalFlag = 'Y'
			zhri100aFields.wrkCriticalFlag = true;
			//DO Prepare-Error-Parms
			prepareErrorParms();
			//IF $PoiFlag = 'N'
			if(!zhri100aFields.poiFlag) {
				//DO Call-Error-Routine
				ZHRI100A_callErrorRoutine(zhri100aFields);
			//ELSE
			}
			else {
				//DO Call-Error-Routine-NonEmp
				callErrorRoutineNonEmp(zhri100aFields);
			//END-IF
			}
			//LET $WrkCriticalFlag  = 'N'
			zhri100aFields.wrkCriticalFlag = false;
		}
		//END-IF
		return status;
		//END-PROCEDURE CALL-SYSTEM
	}
//
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Prepare-Error-Parms
//	! Desc:  Makes sure that the parms are the correct length for the error
//	!        routine RPG program to recieve them
//	!----------------------------------------------------------------------
//	Begin-Procedure Prepare-Error-Parms
//	!Prepare the date and time parms
//	DO Get-Current-DateTime                                 !Get the current date and time
//	LET $AddDateErrorParm = datetostr(strtodate($AsOfToday,'DD-MON-YYYY'),'YYYYMMDD') !sree**rehost
//	LET $AddTimeErrorParm =    SUBSTR($Out,10,2)    ||    SUBSTR($Out,13,2)    ||    SUBSTR($Out,16,2)
//	LET $OpridErrorParm   =    SUBSTR($AuditOprid,2,5)
//	End-Procedure Prepare-Error-Parms
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Call-Error-Routine
//	! Desc:  Builds the command and calls the error routine
//	!----------------------------------------------------------------------
//	Begin-Procedure Call-Error-Routine
//	!Make Sure that the ErrorMessageParm is always 75 Characters long
//	LET $ErrorMessageParm = SUBSTR($ErrorMessageParm,1,75)  !Make sure not more than 75 long
//	LET $ErrorMessageParm = Rpad($ErrorMessageParm,75,' ')  !Make sure not less than 75 long
//	LET $Command =   '"CALL '       ||
//	                 $Library                     ||
//	                 '/HRZ110A '                  ||
//	                 'PARM('''                    ||
//	                 $ErrorProgramParm            ||
//	                 ''' '''                      ||
//	                 $Wrk_Emplid2                 ||
//	                 ''' '''                      ||
//	                 ' '                          ||
//	                 ''' '''                      ||
//	                 $ErrorMessageParm            ||
//	                 ''' '''                      ||
//	                 $WrkCriticalFlag             ||
//	                 ''' '''                      ||
//	                 $AddDateErrorParm            ||
//	                 ''' '''                      ||
//	                 $AddTimeErrorParm            ||
//	                 ''' '''                      ||
//	                 $OpridErrorParm              ||
//	                 ''' '''                      ||
//	                 'Y'                          ||
//	                 ''')" '
//
//	DO Call-System                                              
//	public static Integer callSystem(String employeeId, String command, Zhri100aFields zhri100aFields);
//	End-Procedure Call-Error-Routine

		
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Call-Error-Routine-NonEmp
//	! Desc:  Builds the command and calls the error routine
//	!----------------------------------------------------------------------
//	Begin-Procedure Call-Error-Routine-NonEmp
//	!Make Sure that the ErrorMessageParm is always 75 Characters long
//	LET $ErrorMessageParm = SUBSTR($ErrorMessageParm,1,75)  !Make sure not more than 75 long
//	LET $ErrorMessageParm = Rpad($ErrorMessageParm,75,' ')  !Make sure not less than 75 long
//	LET $Command =   '"CALL '       ||
//	                 $Library                     ||
//	                 '/HRZ210A '                  ||
//	                 'PARM('''                    ||
//	                 $ErrorProgramParm            ||
//	                 ''' '''                      ||
//	                 $NWrk_Emplid2                ||
//	                 ''' '''                      ||
//	                 $Wrk_indexNum                ||
//	                 ''' '''                      ||
//	                 ' '                          ||
//	                 ''' '''                      ||
//	                 $ErrorMessageParm            ||
//	                 ''' '''                      ||
//	                 $WrkCriticalFlag             ||
//	                 ''' '''                      ||
//	                 $AddDateErrorParm            ||
//	                 ''' '''                      ||
//	                 $AddTimeErrorParm            ||
//	                 ''' '''                      ||
//	                 $OpridErrorParm              ||
//	                 ''' '''                      ||
//	                 'Y'                          ||
//	                 ''')" '
//	DO Call-System                                              
//	End-Procedure Call-Error-Routine-NonEmp
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Check-If-Contractor
//	! Desc:  Checks to see if the employee is a contractor
//	!----------------------------------------------------------------------
//	Begin-Procedure Check-If-Contractor
//	LET $Found = 'N'
//	Begin-Select
//	'X'
//	    LET $Found = 'Y'
//	From PS_JOB RJ
//	where RJ.EMPLID = $PSEmplid
//	  and RJ.EMPL_CLASS = 'R'
//	  and RJ.EFFDT = (SELECT MAX(EFFDT)
//	                    FROM  PS_JOB RJ2
//	                   WHERE  RJ2.EMPLID = RJ.EMPLID
//	                     AND  RJ2.EMPL_RCD = RJ.EMPL_RCD   !changed for v8.3
//	                     AND  RJ2.EFFDT <= $AsOfToday)
//	  and RJ.EFFSEQ = (SELECT MAX(EFFSEQ)
//	                     FROM PS_JOB RJ3
//	                    WHERE RJ3.EMPLID = RJ.EMPLID
//	                     AND  RJ3.EMPL_RCD = RJ.EMPL_RCD   !changed for v8.3
//	                      AND RJ3.EFFDT = RJ.EFFDT)
//	End-Select
//	End-Procedure Check-If-Contractor
//
//
	
	private static void prepareErrorParms() {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Call System Using $Command #Status Wait
	 * Execute the command that was built on the command waiting until completion
	 * @param command
	 * @param zhri100aFields
	 * @return 0 if success, non-zero if error
	 */
	private static Integer callSystemUsingCommand(String command, Zhri100aFields zhri100aFields) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Call-Error-Routine-NonEmp from ZHRI100A.SQR
	 * @param zhri100aFields
	 */
	private static void callErrorRoutineNonEmp(Zhri100aFields zhri100aFields) {
		// TODO Auto-generated method stub
	}

	/**
	 * Call-Error-Routine from ZHRI100A.SQR
	 * @param zhri100aFields
	 */
	public static void ZHRI100A_callErrorRoutine(Zhri100aFields zhri100aFields) {
		//LET $Wrk_EmplId2 = EDIT(#Wrk_EmplId1,'099999999') //this value is used as a parameter in the error routine call //a 9-digit number left padded with zeros.
		//Begin-Procedure Call-Error-Routine
		String employeeId = String.format("%9s", zhri100aFields.employeeId).replace(' ', '0');
		String addDateErrorParm = "";
		String addTimeErrorParm = "";
		String opridErrorParm = "";
		String command; //$Command
		//!Make Sure that the ErrorMessageParm is always 75 Characters long
		//Let $ErrorMessageParm = SUBSTR($ErrorMessageParm,1,75)  !Make sure not more than 75 long
		//let $ErrorMessageParm = Rpad($ErrorMessageParm,75,' ')  !Make sure not less than 75 long
		String errorMessageParameter = String.format("%75s", zhri100aFields.errorMessageParameter);
		//Let $Command =   '"CALL '       ||
		// $Library                     ||
		// '/HRZ110A '                  ||
		// 'PARM('''                    ||
		// $ErrorProgramParm            ||
		// ''' '''                      ||
		// $Wrk_Emplid2                 ||
		// ''' '''                      ||
		// ' '                          ||
		// ''' '''                      ||
		// $ErrorMessageParm            ||
		// ''' '''                      ||
		// $WrkCriticalFlag             ||
		// ''' '''                      ||
		// $AddDateErrorParm            ||
		// ''' '''                      ||
		// $AddTimeErrorParm            ||
		// ''' '''                      ||
		// $OpridErrorParm              ||
		// ''' '''                      ||
		// 'Y'                          ||
		// ''')" '
		command = "CALL '" + zhri100aFields.as400Library + "/HRZ110A PARM('"
				+ zhri100aFields.errorProgramParameter + "' '"
        		+ employeeId + "' ' ' '"
        		+ errorMessageParameter + "' '"
        		+ zhri100aFields.wrkCriticalFlag + "' '"
        		+ addDateErrorParm + "' '"
        		+ addTimeErrorParm + "' '"
        		+ opridErrorParm + "' '"
        		+ "' 'Y')";
		//Do Call-System                                              
		ZHRI100A_callSystem(command, zhri100aFields);
		//End-Procedure Call-Error-Routine
	}


	/**
	 * Check-If-Correct102A from ZHRI100A.SQR
	 * Checks to see if 102A process has JOB row
	 * @param psEmplId
	 * @param psEffectiveDate
	 * @param processName
	 * @return true if corresponding PsJob record found
	 */
	public static Boolean ZHRI100A_checkIfCorrect102A(String employeeId, Date effectiveDate, String processName) {
		//BEGIN-PROCEDURE CHECK-IF-CORRECT102A
		//LET $OK-To-Process = 'N'
		Boolean isOkToProcess = false;
		//IF $WrkProcess = 'ZHRI102A'
		if("ZHRI102A".equalsIgnoreCase(processName)) {
			//DO DTU-ADD-DAYS($PSEffDt, 1, $Dt102)
			//add a day to current effective date
			effectiveDate = DateUtil.addDays(effectiveDate, 1);
		}
		//END-IF
		//BEGIN-SELECT
		//'XX'
		//LET $OK-To-Process = 'Y'
		//FROM PS_JOB PS_JOB WHERE PS_JOB.EMPLID = $PSEmplId AND TO_CHAR(PS_JOB.EFFDT, 'YYYY-MM-DD') = $Dt102
		List<PsJob> psJobList = PsJob.findByEmployeeIdAndEffectiveDate(employeeId, effectiveDate);
    	if(psJobList != null && !psJobList.isEmpty()) {
    		isOkToProcess = true;
    	}
    	//END-SELECT
		return isOkToProcess;
		//END-PROCEDURE CHECK-IF-CORRECT102A
	}


	/**
	 * Build-Group-Where-Clause from ZHRI100A.SQR
	 * This routine will build the where clause that will select the correct Run-ID to use.
	 * @return SQL where clause
	 */
	public static String ZHRI100A_buildGroupWhereClause(String whereClause) {
		String alias = ""; //TODO: I can't find where this value is set
		String selectGroup = ""; //TODO: I can't find where this value is set
		//BEGIN-PROCEDURE BUILD-GROUP-WHERE-CLAUSE
		//LET $WhereClause = LTRIM(RTRIM($WhereClause,' '),' ')  !Remove leading and trailing blanks
		if(whereClause != null) {
			whereClause = whereClause.trim();
			//IF ($WhereClause = '')  !If the where clause is empty
			if(whereClause.isEmpty()) {
				//LET $WhereClause = 'WHERE ((' || $Alias || '.HMRGP = ' || '''' || $SelectGroup || ''''  !Add the first statement to the where clause
				whereClause = "WHERE ((" + alias + ".HMRGP = " + "'" + selectGroup + "'";
			}
			//else  !The where clause is not empty
			else {
				//LET $WhereClause = $WhereClause || ' OR ' || $Alias || '.HMRGP = ' || '''' ||$SelectGroup || ''''!append a condition to the where clause
				whereClause = whereClause+ " OR " + alias + ".HMRGP = " + "'" + selectGroup + "'";
			//END-IF  !$WhereClause = ''
			}
		}
		//END-PROCEDURE   BUILD-GROUP-WHERE-CLAUSE
		return whereClause;
	}

	/**
	 * Build-EmplId-Where-Clause from ZHRI100A.SQR
	 * Builds a where clause based on an employee id entered by the user.
	 * @return SQL where clause
	 */
	public static String ZHRI100A_buildEmplIdWhereClause() {
		String alias = ""; //TODO: I can't find where this value is set
		String runId = ""; //TODO: I can't find where this value is set
		//BEGIN-PROCEDURE BUILD-EMPLID-WHERE-CLAUSE
		//LET $WhereClause = 'WHERE ((' || $Alias || '.HMREMP = ' || '''' || $Run-Id || ''''  !Create the where clause
		String whereClause = "WHERE ((" + alias + ".HMREMP = " + "'" + runId + "'";
		//END-PROCEDURE BUILD-EMPLID-WHERE-CLAUSE
		return whereClause;
	}

	/**
	 * Get-OprId from ZHRI100A.SQR
	 * This routine gets the operator id from the operator definition table
	 */
	public static String ZHRI100A_getOprId(String employeeId, Integer indexNumber, Zhri100aFields zhri100aFields) {
		return ZHRI100A_getOprId(employeeId, zhri100aFields, indexNumber, null); 
	}
	public static String ZHRI100A_getOprId(String employeeId, Zhri100aFields zhri100aFields, Integer indexNumber, Integer eidIndexNumber) {
		//BEGIN-PROCEDURE GET-OPRID
		//LET $Found = 'N'
		Boolean found = false;
		//LET $PSOprId = ''
		String psOprId;
		//IF $PoiFlag = 'N'
		if(!zhri100aFields.poiFlag) {
			//MOVE 0 TO #indexNum
			indexNumber = 0;
		//END-IF
		}
		//IF #indexNum = 0
		if(indexNumber.equals(0)) {
//			DO GET-LEGID-FOR-SEQ0
			psOprId = CrossReferenceEmployeeId.ZHRI100A_getLegIdForSeq0(employeeId);
		}
		//ELSE
		else {
			//DO GET-LEGID-FOR-SEQNUM
			psOprId = CrossReferenceMultipleEmployeeId.ZHRI100A_getLegIdForSeqNum(employeeId, eidIndexNumber);
		//END-IF
		}
		if(psOprId != null && !psOprId.isEmpty()) {
			found = true;
		}
		//!If an OprId does not exist for the employee, create one
		//IF ($Found = 'N')
		if(!found) {
			//DO GET-LEGACY-OPRID                  !sree**10/04/01
			//LET $PSOprId = $LegEmplid
			psOprId = ZHRI100A_getLegacyOprId(employeeId, indexNumber, zhri100aFields.poiFlag);
		//END-IF  !$Found = 'N'
		}
		//END-PROCEDURE GET-OPRID
		return psOprId;
	}

//
//
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Get-Legacy-Oprid
//	! Desc:  Gets the new oprid from the legacy system
//	!----------------------------------------------------------------------
//	Begin-Procedure Get-Legacy-Oprid                !sree**10/04/01
//	LET $LegEmplid = ''
//	Move $Wrk_Emplid to #wrk_emplid                 !sree***10/04/01
//	!Surya Added - TEMPMAST Start
//	IF $PoiFlag = 'N'
//	  move 0 to #indexNum
//	END-IF
//	!Surya Added - TEMPMAST End
//	Begin-Select
//	CHR36.H36NAM                                               !Rakesh***08/02/2004
//	CHR36.H36EMP                                               !Rakesh***08/02/2004
//	CHR36.H36EM#                                               !Rakesh***08/02/2004
//	  ! This IF statement and OR part of Select is a workaround to some problem in v8
//	  !(gateway and new version of SQR).  The Select was hanging if it couldn't find a
//	  ! match in HR036P, so the Select assures that the Select always finds a match.
//	  LET #WRK_CHR36_H36EM_NUM = &CHR36.H36EM#   !sm 07/12/02  !Rakesh***08/02/2004
//	  IF #WRK_CHR36_H36EM_NUM = #wrk_emplid                    !Rakesh***08/02/2004
//	    LET $LegEmpName = &CHR36.H36NAM                        !Rakesh***08/02/2004
//	    DO Format-Employee-Name
//	    LET $LegEmplid = &CHR36.H36EMP                         !Rakesh***08/02/2004
//	    LET $LegEmplid = SUBSTR($LegEmplid,1,5)
//	    !DO Insert-Oprid  !Surya Added - TEMPMAST 
//	    !Surya Added - TEMPMAST Start
//	    If #indexNum = 0
//	      DO Insert-Oprid
//	    else
//	      DO Update-Oprid
//	    END-IF  
//	    !Surya Added - TEMPMAST End
//	  END-IF    !#WRK_CHR36_H36EM_NUM = #wrk_emplid            !Rakesh***08/02/2004
//	from HR036P CHR36                                          !Rakesh***08/02/2004
//	where CHR36.H36EM# = #wrk_emplid                           !Rakesh***08/02/2004
//	and CHR36.H36INX = #indexNum 
//	UNION
//	SELECT
//	' ',
//	' ' ,
//	9999999999
//	FROM DUAL
//	End-Select
//	End-Procedure Get-Legacy-Oprid
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Insert-Oprid
//	! Desc:  This routine will insert a row into the PS_ZHRT_EMPID_CREF table for the
//	!        employee if the employee has a record in HR006P
//	!----------------------------------------------------------------------
//	Begin-Procedure Insert-Oprid
//	LET $Insert-Error-Flag = 'N'
//	!Add to the PS_ZHRT_EMPID_CREF table
//	Begin-SQL  On-Error= Insert-Error
//	INSERT INTO PS_ZHRT_EMPID_CREF (
//	EMPLID,
//	ZHRF_LEG_EMPL_ID)
//	VALUES (
//	$Wrk_Emplid,
//	$LegEmplid
//	)
//	End-Sql
//	End-Procedure Insert-Oprid
//
//	!Surya Added the below procesdure- TEMPMAST 
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Update-Oprid
//	! Desc:  This routine will UPDATE table PS_ZHRR_MULTPL_EID for the
//	!        Non Employees and Multiple EIDs if the employee has a record in HR036P
//	!----------------------------------------------------------------------
//	Begin-Procedure Update-Oprid
//	LET $Update-Error-Flag = 'N'
//	!Update the PS_ZHRR_MULTPL_EID table for Multiple EIDs 
//	Begin-SQL  On-Error= Update-Error
//	UPDATE PS_ZHRR_MULTPL_EID
//	SET ZHRF_LEG_EMPL_ID = $LegEmplid
//	WHERE EMPLID = $Wrk_Emplid
//	AND SEQUENCE = #indexNum
//	End-Sql
//	End-Procedure Update-Oprid
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Insert-Error
//	! Desc:  This is an error routine to keep the program from abending when
//	!        an insert fails
//	!----------------------------------------------------------------------
//	Begin-Procedure Insert-Error
//	LET $ErrorMessageParm = $Sql-error
//	DO Call-Error-Routine
//	LET $Insert-Error-Flag = 'Y'
//	End-Procedure Insert-Error
//
//	!Surya Added the below procedure- TEMPMAST 
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Update-Error
//	! Desc:  This is an error routine to keep the program from abending when
//	!        an insert fails
//	!----------------------------------------------------------------------
//
//	Begin-Procedure Update-Error
//	!#ifdef debugx
//	!    show 'inside Update-Error'
//	!#END-IF !debugx
//
//	LET $ErrorMessageParm = $Sql-error
//	DO Call-Error-Routine
//	LET $Update-Error-Flag = 'Y'
//
//	End-Procedure Update-Error
//
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Format-Employee-Name
//	! Desc:  This routine will change the format of the employee's name
//	! from Last*First MI* (legacy system) to Last, First MI (PeopleSoft Format)
//	!----------------------------------------------------------------------
//
//	Begin-Procedure Format-Employee-Name
//	!#ifdef debugx
//	!    show 'inside format employee name'
//	!#END-IF !debugx
//
//
//	! Break apart the legacy system name field at the *
//	Unstring $LegEmpName by '*' into $PSEmp_Last_Name_Search $PSEmp_First_Name_Search $dummy   ! dummy is for the ending *
//
//	!Concatenate the Last name and the first name (separated by a comma(,) that were retrieved from the legacy system, and
//	!Unstringed above, into a work variable ($work_a) that will be used as input by the Convert Case routine
//	LET $_work_a = $PSEmp_Last_Name_Search || ',' || $PSEmp_First_Name_Search
//
//	!Execute a routine that will change the case of the $work_a variable to mixed case and return the result
//	!in another variable $New
//	DO M800-Convert-Case   !ZCvtCaseM.sqc
//	!Move the result from the convert-case routine to the PeopleSoft Employee Name field that will be inserted
//	LET $PSEmpName = $_New
//
//	End-Procedure  Format-Employee-Name
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Build-Active-Dir-Output-File
//	!----------------------------------------------------------------------
//
//	Begin-Procedure Check-Effdt-Transaction
//	!#ifdef debugx
//	 !   show 'inside Check-Effdt-Transaction'
//	!#END-IF !debugx
//
//	LET $WrkADEffdt = ''
//
//	IF $Wrkprocess = 'ZHRI102A'
//	  DO dtu-add-days($PSEffdt,1,$WrkADEffdt)
//	 ELSE
//	  LET $WrkADEffdt = $PSEffdt
//	END-IF
//
//	LET $AdFound = 'N'
//
//	BEGIN-SELECT
//	'XA'
//	   LET $AdFound = 'Y'
//
//	FROM  PS_JOB AD01
//	WHERE AD01.EMPLID = $PSEmplid
//	      AND to_char(AD01.EFFDT,'YYYY-MM-DD') > $WrkADEffdt
//
//	END-SELECT
//
//	!-----------------------------!
//
//	BEGIN-SELECT
//	'XB'
//	   LET $AdFound = 'Y'
//
//	FROM  PS_PERS_DATA_EFFDT AD02
//	WHERE AD02.EMPLID = $PSEmplid
//	    AND  to_char(AD02.EFFDT,'YYYY-MM-DD') > $Wrk_ADEffdt
//
//	END-SELECT
//
//	End-Procedure Check-Effdt-Transaction
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Build-Active-Dir-Output-File
//	!----------------------------------------------------------------------
//
//	Begin-Procedure Build-Active-Dir-Output-File
//	!#ifdef debugx
//	!    show 'inside build active dir output file'
//	!#END-IF !debugx
//
//	LET $Wrkcreatefile = 'Y'
//	IF $Wrk_AD_JobDataBuild = 'N'
//	   DO AD-Get-Job-Data
//	END-IF
//
//	DO AD-Get-Job-Description
//	DO AD-Get-EmplStatus-Description
//
//	IF $Wrk_AD_PersdataEffdtBuild = 'N'
//	 DO AD-Get-Pers-Data-Effdt
//	END-IF
//
//	IF $Wrk_AD_Getbusinessphone = 'N'
//	 DO AD-Get-Business-Phone
//	END-IF
//
//	IF $Wrk_AD_PersDataBuild = 'N'
//	 DO AD-Get-Personal-Data
//	END-IF
//
//	IF $Wrk_AD_CountryCdBuild = 'N'
//	 DO AD-Get-Country-Code
//	END-IF
//
//	DO AD-Get-Employee-Fax
//	DO AD-Get-Namesuffix
//	DO AD-Get-JobStart-Date
//	DO AD-Get-Employment-Data
//
//	IF $ADSupervisorID <> ''
//	DO AD-Get-LegSuperviorID
//	END-IF
//
//	IF $Wrk_AD_NamesBuild = 'N'
//	 DO AD-Get-Names
//	END-IF
//
//	LET $ADEmplid = $PSEmplid
//
//	LET $ADFirstName = $ADPSFirstName || ' ' || $ADPSMiddleName       !changed for v8.3
//	LET $ADLastName = $ADPSLastName
//
//	LET $ADLocation = $PSLocation
//	LET $ADJobCd  = $PSJobcode
//	LET $ADJobDescr = $PSJobDescription
//	LET $ADEmplStatus = $PSEmplStatus
//	  IF $PSRehireDt <> ''
//	    LET $ADHireDt = $PSRehiredt
//	  else
//	    LET $ADHireDt = $PSHiredt
//	  END-IF
//	IF ($PSAction = 'REH')
//	and ($PSAction_Reason = 'REH')
//	and ($WrkProcess = 'ZHRI102A')
//	 LET $ADAction_Code = 'R'
//	END-IF
//
//	!  IF $ADAction_Code = 'T'
//	    LET $ADTermDt = $PSTerminationdt
//	!  END-IF
//
//	LET $ADCountry = $PSLoc_Country
//	LET $ADFullPartTime = $PSFullPartTime
//	LET $ADEmplClass = $PSEmplClass
//	LET $ADDeptID  = $PSDeptId
//	LET $ADBusinessPhone = $PSBusiness_Phone
//	LET $ADLangCd = $PSLangCd
//	LET $ADPrfName = $PSPrfName
//
//	DO Write-Active-Dir-Output-File
//	End-Procedure Build-Active-Dir-Output-File
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  AD-Get-Job-Data
//	! Desc:  Gets the Job data from the job table.
//	!----------------------------------------------------------------------
//
//	Begin-Procedure AD-Get-Job-Data
//	!#ifdef debugx
//	!    show 'inside ad get job data'
//	!#END-IF !debugx
//
//	Begin-Select
//
//	AD.LOCATION
//	    LET $PSLocation = LTRIM(RTRIM(&AD.LOCATION,' '),' ')            !Remove leading and trailing blanks
//	AD.FULL_PART_TIME
//	    LET $PSFullPartTime = LTRIM(RTRIM(&AD.FULL_PART_TIME,' '),' ')  !Remove leading and trailing blanks
//	AD.EMPL_CLASS
//	    LET $PSEmplClass = LTRIM(RTRIM(&AD.EMPL_CLASS,' '),' ')         !Remove leading and trailing blanks
//	AD.EMPL_STATUS
//	    LET $PSEmplStatus = LTRIM(RTRIM(&AD.EMPL_STATUS,' '),' ')       !Remove leading and trailing blanks
//	AD.DEPTID
//	    LET $PSDeptid = LTRIM(RTRIM(&AD.DEPTID,' '),' ')                !Remove leading and trailing blanks
//	AD.JOBCODE
//	    LET $PSJobcode = LTRIM(RTRIM(&AD.JOBCODE,' '),' ')              !Remove leading and trailing blanks
//
//	from PS_JOB AD
//	where AD.EMPLID = $Wrk_Emplid
//	  and AD.EFFDT = (SELECT MAX(EFFDT)
//	                    FROM  PS_JOB AD1
//	                   WHERE  AD1.EMPLID = AD.EMPLID
//	                     AND  AD1.EMPL_RCD = AD.EMPL_RCD   !changed for v8.3
//	                     AND  to_char(AD1.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//
//	  and AD.EFFSEQ = (SELECT MAX(EFFSEQ)
//	                     FROM PS_JOB AD2
//	                    WHERE AD2.EMPLID = AD.EMPLID
//	                      AND AD2.EMPL_RCD = AD.EMPL_RCD  !changed for v8.3
//	                      AND AD2.EFFDT = AD.EFFDT)
//
//	End-Select
//
//	End-Procedure AD-Get-Job-Data
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure: AD-Get-Job-Description
//	! Desc:  This routine will get the Job description for Active Directory File Build
//	!----------------------------------------------------------------------
//
//	Begin-Procedure AD-Get-Job-Description
//	!#ifdef debugx
//	 !   show 'inside ad get job description'
//	!#END-IF !debugx
//
//	LET $PSJobDescription = ''
//	Begin-Select
//
//	AD9.JOBCODE
//	AD9.DESCR
//
//	 LET $PSJobDescription = LTRIM(RTRIM(&AD9.DESCR,' '),' ')
//
//	from PS_JOBCODE_TBL AD9
//	where AD9.JOBCODE = $PSJobcode
//
//	End-select
//
//	End-Procedure AD-Get-Job-Description
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure: AD-Get-EmplStatus-Description
//	! Desc:  This routine will get the Employee Status description for Active Directory File Build
//	!----------------------------------------------------------------------
//
//	Begin-Procedure AD-Get-EmplStatus-Description
//	!#ifdef debugx
//	!    show 'inside ad get emplstatus description'
//	!#END-IF !debugx
//
//	Begin-Select
//
//	AD10.XLATLONGNAME
//
//	 LET $ADEmplStatusDescr = LTRIM(RTRIM(&AD10.XLATLONGNAME,' '),' ')
//
//	! from XLATTABLE AD10                                                                - Commented Out
//	from PSXLATITEM AD10                                                                
//
//	where AD10.FIELDNAME = 'EMPL_STATUS'
//	and AD10.FIELDVALUE = $PSEmplstatus
//	! and AD10.LANGUAGE_CD = 'ENG'                                                       - Commented Out
//	! and AD10.EFFDT = (Select max(AD11.EFFDT) from XLATTABLE AD11                       - Commented Out
//	and AD10.EFFDT = (Select max(AD11.EFFDT) from PSXLATITEM AD11                       
//	                  where AD10.FIELDNAME = AD11.FIELDNAME
//	                  and AD10.FIELDVALUE = AD11.FIELDVALUE
//	!                  and AD10.LANGUAGE_CD = AD11.LANGUAGE_CD                           - Commented Out
//	                  and AD11.EFFDT <= SYSDATE                                         
//	                                  )
//	End-Select
//	End-Procedure AD-Get-EmplStatus-Description
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  AD-Get-JobStart-Date
//	! Desc:  Gets the Job Start date from the job table.
//	!----------------------------------------------------------------------
//
//	Begin-Procedure AD-Get-JobStart-Date
//	!#ifdef debugx
//	 !   show 'inside ad get job start date'
//	!#END-IF !debugx
//
//	Begin-Select
//
//	to_char(AD4.EFFDT,'YYYY-MM-DD') &AD4.EFFDT
//
//	  LET $ADJobStartYr   = SUBSTR(&AD4.EFFDT,1,4)
//	  LET $ADJobStartMnth = SUBSTR(&AD4.EFFDT,6,2)
//	  LET $ADJobStartDay  = SUBSTR(&AD4.EFFDT,9,2)
//	  LET $ADJobStartdt   = $ADJobStartYr || $ADJobStartMnth || $ADJobStartDay
//
//	    LET $PSJobStartdt = &AD4.EFFDT
//	from PS_JOB AD4
//	where AD4.EMPLID = $Wrk_Emplid
//	  and AD4.JOBCODE = $PSJobcode
//	  and AD4.EFFDT = (SELECT MIN(EFFDT)
//	                    FROM  PS_JOB AD5
//	                   WHERE  AD4.EMPLID = AD5.EMPLID
//	                     AND  AD4.JOBCODE = AD5.JOBCODE
//	                     AND  AD4.EMPL_RCD = AD5.EMPL_RCD     !changed for v8.3
//	                     AND  to_char(AD5.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//
//	  and AD4.EFFSEQ = (SELECT MIN(EFFSEQ)
//	                     FROM PS_JOB AD2
//	                    WHERE AD2.EMPLID = AD4.EMPLID
//	                      AND AD2.EMPL_RCD = AD4.EMPL_RCD     !changed for v8.3
//	                      AND AD2.JOBCODE = AD4.JOBCODE
//	                      AND AD2.EFFDT = AD4.EFFDT)
//
//	end-select
//
//	End-Procedure AD-Get-JobStart-Date
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  AD-Get-Pers-Data-Effdt
//	! Desc:  This routine will get the Personal Data row for each of the
//	!        employee numbers entered in the trigger file.  Pers_Data_Effdt table
//	!        no longer has name info, so are using Names table.
//	!----------------------------------------------------------------------
//
//	Begin-Procedure AD-Get-Pers-Data-effdt
//	!#ifdef debugx
//	 !   show 'inside ad get pers data effdt'
//	!#END-IF !debugx
//
//	begin-select
//
//	ADPDE2A.First_Name
//	ADPDE2A.Last_Name
//	ADPDE2A.Middle_Name    
//
//	  LET $ADPSLastName   = RTRIM(LTRIM(&ADPDE2A.Last_Name,' '),' ')
//	  LET $ADPSFirstName  = RTRIM(LTRIM(&ADPDE2A.First_Name,' '),' ')
//	  LET $ADPSMiddleName = RTRIM(LTRIM(&ADPDE2A.Middle_Name,' '),' ')
//	  LET $ADPSMiddleName = SUBSTR($ADPSMiddleName,1,1)
//
//	from PS_NAMES ADPDE2A     !Changed for v8.3
//	where ADPDE2A.Emplid = $PSEmplid
//	  and ADPDE2A.NAME_TYPE = 'PRI'                                    
//	  and ADPDE2A.Effdt = (select max(ADPDE2B.effdt) from  PS_NAMES ADPDE2B  !Changed for v8.3
//	                       where ADPDE2B.emplid    = ADPDE2A.emplid
//	                       and   ADPDE2B.NAME_TYPE = ADPDE2A.NAME_TYPE
//	                       and   to_char(ADPDE2B.EFFDT,'YYYY-MM-DD') <= $PSEffdt) 
//
//	end-select
//
//	end-procedure AD-Get-Pers_Data-Effdt
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  AD-Get-NameSuffix
//	! Desc:  This routine will get the Name Suffix row for each of the
//	!        employee numbers entered in the trigger file.
//	!----------------------------------------------------------------------
//
//	Begin-Procedure AD-Get-NameSuffix
//	!#ifdef debugx
//	 !   show 'inside ad get name suffix'
//	!#END-IF !debugx
//
//	begin-select
//
//	ANAME.Name_Suffix
//
//	  LET $ADNameSuffix  = RTRIM(LTRIM(&ANAME.Name_Suffix,' '),' ')
//	  LET $ADNameSuffix  = SUBSTR($ADNameSuffix,1,5)
//
//	from PS_NAMES ANAME     !Changed for v8.3
//	where ANAME.Emplid = $PSEmplid
//	  and ANAME.NAME_TYPE = 'PRI'                                    
//	  and ANAME.Effdt = (select max(ANAME2.effdt) from  PS_NAMES ANAME2  !Changed for v8.3
//	                       where ANAME2.emplid     = ANAME.emplid
//	                       and   ANAME2.NAME_TYPE  = ANAME.NAME_TYPE
//	                       and   to_char(ANAME2.EFFDT,'YYYY-MM-DD') <= $PSEffdt) 
//
//	end-select
//
//	end-procedure AD-Get-NameSuffix
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  AD-Get-Personal-Data
//	! Desc:  This routine will get the Personal Data row for each of the
//	!        employee numbers entered in the trigger file.
//	!----------------------------------------------------------------------
//
//	Begin-Procedure AD-Get-Personal-Data
//	!#ifdef debugx
//	 !   show 'inside ad get personal data '
//	!#END-IF !debugx
//
//	LET $PSLangCd = ''
//
//	begin-select
//
//	ADPD.LANG_CD
//
//	  LET $PSLangCd = RTRIM(LTRIM(&ADPD.LANG_CD,' '),' ')
//
//	from PS_Personal_Data ADPD
//	where ADPD.Emplid = $PSEmplid
//
//	end-select
//
//	end-procedure AD-Get-Personal-Data
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure: AD-Get-Country-Code
//	! Desc:  This routine will get the Country Code for Active Directory File Build
//	!----------------------------------------------------------------------
//
//	Begin-Procedure AD-Get-Country-Code
//	!#ifdef debugx
//	 !   show 'inside ad get country code '
//	!#END-IF !debugx
//
//	LET $PSLoc_Country = ''
//
//	Begin-Select
//
//	ADL2.COUNTRY
//	    LET $PSLoc_Country = LTRIM(RTRIM(&ADL2.COUNTRY,' '),' ')         !Remove leading and trailing blanks
//
//	from PS_LOCATION_TBL ADL2
//	where ADL2.LOCATION = $PSLOCATION
//
//	end-select
//
//	End-Procedure AD-Get-Country-Code
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  AD-Get-Business-Phone
//	! Desc:  This routine gets the business phone number from the Peoplesoft
//	!        tables.
//	!----------------------------------------------------------------------
//
//	Begin-Procedure AD-Get-Business-Phone
//	!#ifdef debugx
//	 !   show 'inside ad get business phone '
//	!#END-IF !debugx
//
//	LET $PSBusiness_Phone = ''
//	begin-select
//	ADPP2.Phone
//
//	 DO Remove-Non-Letters-Numbers (&ADPP2.Phone, $PSBusiness_Phone)   !From ZRmvSpcChr.sqc
//
//	from PS_Personal_Phone ADPP2
//	where ADPP2.Phone_Type = 'BUSN'
//	  and ADPP2.Emplid = $PSEmplid
//
//	end-select
//
//	end-procedure AD-Get-Business-Phone
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  AD-Get-Employee-Fax
//	! Desc:  This routine gets the business phone number from the Peoplesoft
//	!        tables.
//	!----------------------------------------------------------------------
//
//	Begin-Procedure AD-Get-Employee-Fax
//	!#ifdef debugx
//	 !   show 'inside ad get employee fax'
//	!#END-IF !debugx
//
//	begin-select
//	ADPP3.Phone
//	 DO Remove-Non-Letters-Numbers (&ADPP3.Phone, $ADEmployeeFax)   !From ZRmvSpcChr.sqc
//
//	from PS_Personal_Phone ADPP3
//	where ADPP3.Phone_Type = 'FAX'
//	  and ADPP3.Emplid = $PSEmplid
//
//	end-select
//
//	end-procedure AD-Get-Employee-Fax
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  AD-Get-LegSuperviorID
//	!----------------------------------------------------------------------
//
//	Begin-Procedure AD-Get-LegSuperviorID
//	!#ifdef debugx
//	 !   show 'inside ad get leg supervisor id '
//	!#END-IF !debugx
//
//	begin-select
//	RPOD1.ZHRF_LEG_EMPL_ID
//
//	  LET $ADLegSupervisorID = &RPOD1.ZHRF_LEG_EMPL_ID
//
//	from PS_ZHRT_EMPID_CREF RPOD1
//	where RPOD1.Emplid = $ADSupervisorID
//
//	end-select
//
//	end-procedure AD-Get-LegSuperviorID
//
//	!---------------------------------------------------------------------------------------
//	! ERAC
//	! Procedure:  AD-Get-Employment-Data
//	! Desc:  This routine will get the Termination Data row for Active Directory File Build
//	!---------------------------------------------------------------------------------------
//	Begin-Procedure AD-Get-Employment-Data
//	LET $PSHiredt = ' '
//	LET $PSRehiredt = ' '
//	LET $PSTerminationdt = ' '
//	LET $ADSupervisorID = ' '
//	begin-select
//	to_char(ADE.HIRE_DT,'YYYY-MM-DD')       &ADEHire_Dt
//	  LET $PSHireYr = SUBSTR(&ADEHire_Dt,1,4)
//	  LET $PSHireMnth = SUBSTR(&ADEHire_Dt,6,2)
//	  LET $PSHireDay = SUBSTR(&ADEHire_Dt,9,2)
//	  LET $PSHiredt = $PSHireYr || $PSHireMnth || $PSHireDay
//	to_char(ADE.REHIRE_DT,'YYYY-MM-DD')       &ADERehire_Dt
//	  LET $PSRehireYr = SUBSTR(&ADERehire_Dt,1,4)
//	  LET $PSRehireMnth = SUBSTR(&ADERehire_Dt,6,2)
//	  LET $PSRehireDay = SUBSTR(&ADERehire_Dt,9,2)
//	  LET $PSRehiredt = $PSRehireYr || $PSRehireMnth || $PSRehireDay
//	to_char(ADE.TERMINATION_DT,'YYYY-MM-DD')  &ADETermination_Dt
//	  LET $PSTermYr = SUBSTR(&ADETermination_Dt,1,4)
//	  LET $PSTermMnth = SUBSTR(&ADETermination_Dt,6,2)
//	  LET $PSTermDay = SUBSTR(&ADETermination_Dt,9,2)
//	  LET $PSTerminationdt =  $PSTermYr || $PSTermMnth || $PSTermDay
//	ADE.SUPERVISOR_ID
//	  LET $ADSupervisorID = &ADE.SUPERVISOR_ID
//	from PS_Employment ADE
//	where ADE.Emplid = $PSEmplid
//	end-select
//	end-procedure AD-Get-Employment-Data
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  AD-Get-Names
//	! Desc:  This routine gets the Preferred Name from PS_Names for Active Directory File Build
//	!----------------------------------------------------------------------
//	Begin-Procedure AD-Get-Names
//	!#ifdef debugx
//	 !   show 'inside ad get names '
//	!#END-IF !debugx
//	LET $PSPrfName = ''
//	begin-select
//	ADN.First_Name   !changed for v8.3 and defect 993
//	  LET $PSPrfName = RTRIM(LTRIM(&ADN.First_Name,' '),' ')
//	from PS_Names ADN
//	where ADN.Emplid = $PSEmplid
//	  and ADN.NAME_TYPE = 'PRF'
//	  and ADN.EFFDT     = (SELECT MAX(EFFDT) FROM PS_Names ADN2   
//	                      WHERE ADN2.EMPLID   = ADN.EMPLID        
//	                      AND ADN2.NAME_TYPE  = ADN.NAME_TYPE     
//	                      AND to_char(ADN2.EFFDT,'YYYY-MM-DD') <= $PSEffdt) 
//	end-select
//	end-procedure AD-Get-Names
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Write-Active-Dir-Output-File
//	!----------------------------------------------------------------------
//	Begin-Procedure Write-Active-Dir-Output-File
//	    Write 1 from
//	 $ADAction_Code:1          ! Action Code (H=Hire, C=Change, T=Termination)
//	 '|':1
//	 $ADLegOprid:5             ! Legacy Employee Id
//	 '|':1
//	 $ADEmplid:11              ! Employee Id
//	 '|':1
//	 $ADFirstName:30           ! First Name
//	 '|':1
//	 $ADlastName:30            ! Last Name
//	 '|':1
//	 $ADPrfName:50             ! Preferred Name
//	 '|':1
//	 $ADNamesuffix:5           ! Name Suffix Text
//	 '|':1
//	 $ADLocation:10            ! HR Location
//	 '|':1
//	 $ADDeptID:5               ! Department ID
//	 '|':1
//	 $ADJobCd:6                ! Job Title Code
//	 '|':1
//	 $ADJobDescr:30            ! Job Title Description
//	 '|':1
//	 $ADEmplStatus:1           ! Work Status
//	 '|':1
//	 $ADEmplStatusDescr:30     ! Employee Status Description
//	 '|':1
//	 $ADHireDt:8               ! Hire Date / Rehire Date
//	 '|':1
//	 $ADTermDt:8               ! Termination Date
//	 '|':1
//	 $ADJobStartdt:8           ! Current Job Start Date
//	 '|':1
//	 $ADFullPartTime:1         ! Full/Part Time Flag (F OR P)
//	 '|':1
//	 $ADEmplClass:1            ! Employee Class
//	 '|':1
//	 $ADBusinessPhone:24       ! Business Phone Number
//	 '|':1
//	 $ADEmployeeFax:24         ! Fax Number
//	 '|':1
//	 $ADSupervisorID:11        ! Supervisor ID
//	 '|':1
//	 $ADLegSupervisorID:5      ! Leg Supervisor ID
//	 '|':1
//	 $ADCountry:3              ! Country Code
//	 '|':1
//	 $ADLangCd:3               ! Language Preference
//	End-Procedure Write-Active-Dir-Output-File
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Intialize-AD-WrkFields
//	!----------------------------------------------------------------------
//	Begin-Procedure Intialize-AD-WrkFields
//	LET $Wrk_AD_NamesBuild = 'N'
//	LET $Wrk_AD_PersDataBuild = 'N'
//	LET $Wrk_AD_PersDataEffdtBuild = 'N'
//	LET $Wrk_AD_JobDataBuild = 'N'
//	LET $Wrk_AD_CountryCdBuild = 'N'
//	LET $Wrk_AD_Getbusinessphone = 'N'
//	LET $ADAction_Code = ''
//	LET $ADLegOprid = ''
//	LET $ADEmplid = ''
//	LET $ADFirstName = ''
//	LET $ADLastName = ''
//	LET $ADLocation = ''
//	LET $ADJobCd  = ''
//	LET $ADJobDescr = ''
//	LET $ADEmplStatus = ''
//	LET $ADEmplStatusDescr = ''
//	LET $ADHireDt = 0
//	LET $ADTermDt = 0
//	LET $ADNamesuffix = ''
//	LET $ADCountry = ''
//	LET $ADJobStartDt = 0
//	LET $ADJobStartYr = 0
//	LET $ADJobStartMnth = 0
//	LET $ADJobStartDay = 0
//	LET $ADPrfName = ''
//	LET $ADFullPartTime = ''
//	LET $ADEmplClass = ''
//	LET $ADDeptID  = ''
//	LET $ADBusinessPhone = ''
//	LET $ADEmployeeFax = ''
//	LET $ADSupervisorID = ''
//	LET $ADLegSupervisorID = ''
//	LET $ADLangCd = ''
//	LET $Wrkcreatefile = 'N'
//	LET $WrkADEffdt = ''
//	End-Procedure Intialize-AD-WrkFields
//
//	!**********************************************************************
//	! SQC Files for called procedures
//	!**********************************************************************
//	#Include 'curdttim.sqc'  !Get-Current-DateTime procedure
//	#Include 'datemath.sqc'  !Performs arithmetic operations on dates
//	#Include 'zdatetim.sqc'  !Routines for date and time formatting
//	#Include 'datetime.sqc'  !Routines for date and time formatting
//	#Include 'number.sqc'    !Routines to format numbers
//	#Include 'readxlat.sqc'  !Read Translate Table
//	#Include 'reset.sqc'     !End of Program
//	#INCLUDE 'stdapi.sqc'    !STANDARD PROGRAM INTERFACE
//	#Include 'zcvtcasem.sqc' !Routine to convert a fields case to mixed case.
//	#Include 'zrmvspcchr.sqc'!Routine to format text strings
//	#Include 'tranctrl.sqc'  !Common transaction control procedures
//	#Include 'zhri101a.sqc'  !Process to hire employee
//	#Include 'zhri102a.sqc'  !Process to terminate an employee
//	#Include 'zhri104a.sqc'  !Process for job status change
//	#Include 'zhri105a.sqc'  !Process for demographics change
//	#Include 'zhri109a.sqc'  !Process for group transfer
//	#Include 'zhri107a.sqc'  !Process for converting dates
//	#Include 'zhri201a.sqc'  !Process POI/Alt EMP hire/rehire 
//	#Include 'zhri202a.sqc'  !Process POI/Alt EMP term
//	#Include 'zhri205a.sqc'  !Process POI/Alt EMP changes

	
	private static Zhri100aFields initializeMainProperties() {
		Zhri100aFields zhri100aFields = new Zhri100aFields();
		String wrkProcessName = "ZHRI100A";
		zhri100aFields.dbName = PsDbOwner.findDbName();
		//LET $PS_HOME = getenv('PS_HOME')  !This gets the oracle_sid
//		zhri100aFields.peopleSoftHomePath = System.getenv("PS_HOME");
		zhri100aFields.peopleSoftHomePath = "C:/people soft/";
		//LET $AD_HOME = $PS_HOME || '/data/activedir/'  !Path for Active Directory
		zhri100aFields.activeDirectoryHomePath = zhri100aFields.peopleSoftHomePath + "/data/activedir/"; //TODO: where is this used???
		//LET $ORACLE_SID = getenv('ORACLE_SID') 
//		zhri100aFields.oracleSystemId = System.getenv("ORACLE_SID");
		zhri100aFields.oracleSystemId = "PS90HRQA";
		if(zhri100aFields.oracleSystemId != null) {
			//UPPERCASE $ORACLE_SID                  
			zhri100aFields.oracleSystemId = zhri100aFields.oracleSystemId.toUpperCase();
		}
		//!Returns name of AS/400 machine for use in zbas002b.sh
		//LET $Variable_Needed = ' '            
		//LET $Variable_Needed = 'RMTSVR'       
		//DO  Get-Variable                      
		//LET $RMTSVR = $PSZPTT_VARIABLE_VAL      
		//Show '$RMTSVR: ' $RMTSVR                                                            
		zhri100aFields.remoteServerName = PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, zhri100aFields.dbName, "RMTSVR"); //TODO: where is this used???
		//LET $RexecScript = '/usr/local/barch/' || $ORACLE_SID || '/scripts/zbas002b.sh'     
		//Show '$RexecScript: ' $RexecScript                                                  
		zhri100aFields.remoteExecScript = "/usr/local/barch/" + zhri100aFields.oracleSystemId + "/scripts/zbas002b.sh"; //TODO: where is this used???
		//!Returns library name on AS/400 where programs reside
		//LET $Variable_Needed = ' '            
		//LET $Variable_Needed = 'AS400library' 
		//DO  Get-Variable                      
		//LET $Library = $PSZPTT_VARIABLE_VAL   
		//Show '$Library: ' $Library                                                          
		zhri100aFields.as400Library = PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, zhri100aFields.dbName, "AS400library");
		//!Returns IP address of NT server
		//LET $Variable_Needed = ' '             
		//LET $Variable_Needed = 'RMTNTADSVR'    
		//DO Get-Variable                        
		//LET $RMTNTADSVR = $PSZPTT_VARIABLE_VAL 
		//Show '$RMTNTADSVR: ' $RMTNTADSVR                                                    
		zhri100aFields.remoteAdServerName = PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, zhri100aFields.dbName, "RMTNTADSVR"); //TODO: where is this used???
		return zhri100aFields;
	}

	/**
	 * ZHRI100A.Check-Interface-Runfile
	 * This procedure will check the existence run file
	 * @param oracleSystemId
	 * @return true is run file does not exist
	 */
	public static Boolean ZHRI100A_checkInterfaceRunFile(String oracleSystemId) {
		Boolean runFlag = false;
		String runFilePath;
		//LET $RUN_FILEPATH = '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'  //concatenate
		runFilePath = "/usr/local/barch/" + oracleSystemId + "/work/hrinterface.run";
		//LET #file_exists = exists($RUN_FILEPATH)
		Boolean fileExists = (new File(runFilePath)).exists();
		//IF #file_exists = 0
		if(fileExists == false) {
			//LET #run_flag = 1
			runFlag = true;		}
		//ELSE
		else {
			//LET #run_flag = 0
			runFlag = false;
		//END-IF
		}
		return runFlag;
	}
	
	/**
	 * Get-Trigger-Data from ZHRI100A.SQR
	 * This procedure will get the trigger data that needs to be interfaced
	 * @param zhri100aFields
	 */
	//TODO
	//TODO
	//TODO
	public static void ZHRI100A_getTriggerData(Zhri100aFields zhri100aFields) {
		//asOfToday
		//sysDate
		//fileOpen
		PszTriggerEmployee trigger = PszTriggerEmployee.createMockTriggerForEmployeeTermination();
		Boolean adFound = false; //$AdFound  //TODO: where should this be set???
		//BEGIN-PROCEDURE GET-TRIGGER-DATA
		//LET $CompletionStatus = 'P'   !Initialize the CompletionStatus field
		String completionStatus = "P";
		//LET $PoiFlag = ''  !Initialize the POIFlag -- Surya Added - TEMPMAST 
		//LET #WrkSequence = 0
		//LET $AuditOprid = ''
		//LET $PSEmplid = ''
		//LET #Wrk_EmplID1 = 0
		//LET $Wrk_Emplid2 = ''
		//LET $PSEffdt = ''
		//LET #PSEffSeq = 0
		//LET $WrkProcess= ''
		//LET $WrkTaskFlag = ''
		//BEGIN-SELECT LOOPS=150
		//	RZ.SEQ_NBR
		//	MOVE &RZ.SEQ_NBR TO #WrkSequence
		//	RZ.OPRID
		//	LET $AuditOprid = LTRIM(RTRIM(&RZ.OPRID,' '),' ')
		//	RZ.EMPLID
		//	LET $PSEmplid = LTRIM(RTRIM(&RZ.EMPLID,' '),' ')
		//	MOVE $PSEmplid TO #Wrk_EmplID1
		//	LET $Wrk_Emplid2 =  EDIT(#Wrk_EmplID1,'099999999')
		//	TO_CHAR(RZ.EFFDT, 'YYYY-MM-DD') &RZEFFDT
		//	LET $PSEffdt = &RZEFFDT
		//	RZ.EFFSEQ
		//	MOVE &RZ.EFFSEQ TO #PSEffSeq
		//	RZ.PROC_NAME
		//	LET $WrkProcess = LTRIM(RTRIM(&RZ.PROC_NAME,' '),' ')           !Remove leading and trailing blanks
		//	RZ.TASK_FLAG                                                        !Surya Added - TEMPMAST 
		//	LET $WrkTaskFlag = LTRIM(RTRIM(&RZ.TASK_FLAG,' '),' ')          !Surya Added - TEMPMAST 
		//IF $file_open = 'N'
		Boolean fileIsOpen = false;
		if(!fileIsOpen) {
			//!open $open_file1 as 1 for-append record=337
			//!LET $file_open = 'Y'
			//do nothing
		//END-IF
		}
		//LET $PoiFlag = 'N'  !Surya Added - TEMPMAST 
		zhri100aFields.poiFlag = false;
		//DO Check-If-Contractor
		Boolean isContractor = PsJob.employeeIsContractor(trigger.getEmployeeId());
		//IF $Found = 'N' AND  $PSEmplid <> ''  !Not a contractor and not a blank EmplId   !ZHR_MOD_ZHRI100A_110A
		if(!isContractor && trigger.getEmployeeId() != null && !trigger.getEmployeeId().isEmpty()) {
			//Added a check for 'ZHRI102A' - to see if corresponding row on JOB 
			//IF $WrkProcess = 'ZHRI102A'
			if("ZHRI102A".equalsIgnoreCase(trigger.getProcessName())) {
				//DO Check-If-Correct102A
				Boolean isOkToProcess = ZHRI100A_checkIfCorrect102A(trigger.getEmployeeId(), trigger.getEffectiveDate(), trigger.getProcessName());
				//IF $OK-to-process = 'Y'
				if(isOkToProcess) {
					//DO Call-Programs
					ZHRI100A_callPrograms(trigger, zhri100aFields);
				}
				//ELSE                                                           
				else {
					//LET $CompletionStatus = 'C'                                 
					completionStatus = "C";
					//DO Update-Trigger-Row                                       
					int numberOfRecordsUpdated = PszTriggerEmployee.updateCompletionStatus(trigger.getSequenceNumber(), completionStatus);
					System.out.println("numberOfRecordsUpdated: " + numberOfRecordsUpdated);
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
		//IF $CompletionStatus <> 'P'
		if(!"P".equalsIgnoreCase(completionStatus)) {
		//	IF ($ADAction_Code <> '') AND ($ADLegOprid <> '')
			if(!zhri100aFields.adActionCode.isEmpty() && !zhri100aFields.adLegacyOperatorId.isEmpty()) {
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
			//DO Update-Trigger-Row
			int numberOfRecordsUpdated = PszTriggerEmployee.updateCompletionStatus(trigger.getSequenceNumber(), completionStatus);
			System.out.println("numberOfRecordsUpdated: " + numberOfRecordsUpdated);
		//END-IF  !$CompletionStatus <> 'P'
		}
		//FROM PS_ZHRT_INTTRIGGER RZ ,PS_JOB JB
		//WHERE RZ.TASK_FLAG = 'P'
		//	AND (RZ.EFFDT <= $AsOfToday or RZ.PROC_NAME='ZHRI101A' OR  RZ.PROC_NAME='ZHRI106A')
		//  AND (CASE WHEN PROC_NAME IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END) = 
		//  		(SELECT MIN(CASE WHEN PROC_NAME IN ('ZHRI101A', 'ZHRI106A') THEN SEQ_NBR ELSE SEQ_NBR*10 END)  
		//      			FROM  PS_ZHRT_INTTRIGGER RZ2
		//      			WHERE RZ2.EMPLID = RZ.EMPLID
		//      				AND RZ2.TASK_FLAG = 'P'
		//        				AND (RZ2.EFFDT <= SYSDATE
		//								OR RZ2.PROC_NAME='ZHRI101A'
		//								OR RZ2.PROC_NAME='ZHRI106A'))
		//!Surya - TEMPMAST Added the below NOT IN to restrict the rows other than 101 to process when there is a delay
		//	AND RZ.EMPLID NOT IN (SELECT I.EMPLID FROM PS_ZHRT_INTTRIGGER I WHERE I.EMPLID = RZ.EMPLID AND I.TASK_FLAG = 'W') 
		//  AND JB.EMPLID = RZ.EMPLID
		//  AND JB.EFFDT = 
		//			(SELECT MAX(JB2.EFFDT) FROM  PS_JOB JB2
		//         			WHERE  JB2.EMPLID = JB.EMPLID
		//          			AND  JB2.EMPL_RCD = JB.EMPL_RCD)
		//  AND JB.EFFSEQ = 
		//			(SELECT MAX(JB3.EFFSEQ) FROM PS_JOB JB3
		//                	WHERE JB3.EMPLID = JB.EMPLID
		//                 		AND  JB3.EMPL_RCD = JB.EMPL_RCD
		//                 		AND  JB3.EFFDT = JB.EFFDT)
		//END-SELECT
		//END-PROCEDURE GET-TRIGGER-DATA
	}
	
	/**
	 * Get-Legacy-OprId from ZHRI100A.SQR
	 * Gets the new OprId from the legacy system
	 * Formulates legacy OprId from HR036P where HR036P.H36EM# = #wrk_emplid and HR036P.H36INX = #indexNum UNION
	 */
	public static String ZHRI100A_getLegacyOprId(String employeeId, Integer indexNumber, Boolean poiFlag) {
		//Begin-Procedure Get-Legacy-Oprid                !sree**10/04/01
		//Let $LegEmplid = ''
		String legacyEmployeeId;
		String legacyEmployeeName;
		//MOVE $Wrk_Emplid TO #wrk_emplid                 !sree***10/04/01
		Integer employeeNumber = -1;
		try {
			employeeNumber = Integer.parseInt(employeeId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//!Surya Added - TEMPMAST Start
		//IF $PoiFlag = 'N'
		if(!poiFlag) {
			indexNumber = 0;
			//MOVE 0 TO #indexNum
		//END-IF
		}
		//!Surya Added - TEMPMAST End
		//Begin-Select
		//CHR36.H36NAM                                               !Rakesh***08/02/2004
		//CHR36.H36EMP                                               !Rakesh***08/02/2004
		//CHR36.H36EM#                                               !Rakesh***08/02/2004
		//from HR036P CHR36 where CHR36.H36EM# = #wrk_emplid and CHR36.H36INX = #indexNum 
		//UNION SELECT ' ', ' ' , 9999999999 FROM DUAL
		//END-SELECT
		HR036P hr036P = HR036P.findByEmployeeNumberAndIndexNumber(employeeNumber, indexNumber);
    	if(hr036P != null) {
			//!This IF statement and OR part of Select is a workaround to some problem in v8
			//!(gateway and new version of SQR).  The Select was hanging if it couldn't find a
			//! match in HR036P, so the Select assures that the Select always finds a match.
			//LET #WRK_CHR36_H36EM_NUM = &CHR36.H36EM#   !sm 07/12/02  !Rakesh***08/02/2004
			//IF #WRK_CHR36_H36EM_NUM = #wrk_emplid                    !Rakesh***08/02/2004
			if(hr036P.getEmployeeNumber() == employeeNumber) {
				//LET $LegEmpName = &CHR36.H36NAM                        !Rakesh***08/02/2004
				legacyEmployeeName = hr036P.getEmployeeName();
				//DO Format-Employee-Name
		    	if(legacyEmployeeName != null && !legacyEmployeeName.isEmpty()) {
		    		legacyEmployeeName = erd.StringUtil.formatLegacyEmployeeNameToPeopleSoftEmployeeName(hr036P.getEmployeeName());
		    	}
				//LET $LegEmplid = &CHR36.H36EMP                         !Rakesh***08/02/2004
	    		legacyEmployeeId = hr036P.getEmployeeId();
				//LET $LegEmplid = substr($LegEmplid,1,5)
		    	if(legacyEmployeeId != null && !legacyEmployeeId.isEmpty()) {
		    		legacyEmployeeId = legacyEmployeeId.substring(0, 5);
		    	}
				//!DO Insert-Oprid  !Surya Added - TEMPMAST 
				//!Surya Added - TEMPMAST Start
				//IF #indexNum = 0
		    	if(indexNumber == 0) {
					//DO INSERT-OPRID
		    		CrossReferenceMultipleEmployeeId.ZHRI100A_updateOprId(employeeId, indexNumber, employeeId);
		    	}
				//ELSE
		    	else {
					//DO UPDATE-OPRID
		    		CrossReferenceMultipleEmployeeId.ZHRI100A_updateOprId(employeeId, indexNumber, employeeId);
				//END-IF  
		    	}
				//!Surya Added - TEMPMAST End
			//END-IF  !#WRK_CHR36_H36EM_NUM = #wrk_emplid
			}
			return hr036P.getEmployeeId();
    	}
    	return null;
		//END-PROCEDURE GET-LEGACY-OPRID
//		
////		LET $LegEmplId = ''
////		MOVE $Wrk_Emplid to #wrk_emplid
////		IF $PoiFlag = 'N'
//		if(!poiFlag) {
////			MOVE 0 to #indexNum
//			indexNumber = 0;
////		END-IF
//		}
////		Begin-Select
////		HR036P.H36NAM
////		HR036P.H36EMP
////		HR036P.H36EM#
//		HR036P hr036P = HR036P.findByEmployeeIdAndIndexNumber(employeeId, indexNumber);
////	  	!This IF statement and OR part of Select is a workaround to some problem in v8 (gateway and new version of SQR). 
////		!The Select was hanging if it couldn't find a match in HR036P, so the Select assures that the Select always finds a match.
////	  	LET #WRK_CHR36_H36EM_NUM = &HR036P.H36EM#
////	  	IF #WRK_CHR36_H36EM_NUM = #wrk_emplid
////	    	LET $LegEmpName = &HR036P.H36NAM
////	    	DO Format-Employee-Name
////	    	LET $LegEmplid = &HR036P.H36EMP
////	    	LET $LegEmplid = SUBSTR($LegEmplid,1,5)
////	    	IF (#indexNum = 0 and $PoiFlag = 'Y') OR $PoiFlag = 'N'
////	    		DO Insert-OprId
//		CrossReferenceMultipleEmployeeId.ZHRI100A_updateOprId(employeeId, indexNumber, employeeId);
////	    	ELSE
////	    		IF (#indexNum <> 0 AND $PoiFlag = 'Y')
////	        		DO Update-OprId
//					CrossReferenceMultipleEmployeeId.ZHRI100A_updateOprId(employeeId, indexNumber, employeeId);
////	      		END-IF
////	    	END-IF  
////		END-IF    !#WRK_CHR36_H36EM_NUM = #wrk_emplid
////		FROM HR036P
////		WHERE HR036P.H36EM# = #wrk_emplid
////		AND HR036P.H36INX = #indexNum 
////		UNION
////		SELECT
////			' ', ' ' , 9999999999
////		FROM DUAL
////		End-Select
////	  	LET #WRK_CHR36_H36EM_NUM = &HR036P.H36EM#
//		HR036P hr036P = HR036P.findByEmployeeIdAndIndexNumber(employeeId, indexNumber);
////	  	IF #WRK_CHR36_H36EM_NUM = #wrk_emplid
//    	if(hr036P != null) {
//	    	if(hr036P.getEmployeeName() != null && !hr036P.getEmployeeName().isEmpty()) {
//	    		//LET $LegEmpName = &HR036P.H36NAM
//	    		legacyEmployeeName = erd.StringUtil.formatLegacyEmployeeNameToPeopleSoftEmployeeName(hr036P.getEmployeeName());
//    		}
//	    	//LET $LegEmplid = SUBSTR($LegEmplid,1,5)
//	    	if(hr036P.getEmployeeId() != null && !hr036P.getEmployeeId().isEmpty()) {
//	    		legacyEmployeeId = hr036P.getEmployeeId().substring(0, 5);
//	    		
//    		}
//	    	//IF (#indexNum = 0 AND $PoiFlag = 'Y') OR $PoiFlag = 'N'
//	    	if((indexNumber == 0 && poiFlag) || !poiFlag) {
//	    		//DO Insert-OprId
//	    		CrossReferenceMultipleEmployeeId.ZHRI100A_updateOprId(employeeId, indexNumber, employeeId);
//	    	//ELSE
//	    	}
//	    	else {
//		    	//IF (#indexNum <> 0 AND $PoiFlag = 'Y')
//		    	if(indexNumber != 0 && poiFlag) {
//	    			//DO Update-OprId
//	    		//END-IF
//		    	}
//		    //END-IF  
//	    	}
//			return hr036P.getEmployeeId();
//		}
//    	return null;
	}

}

package erd.controller;

import java.io.File;

import erd.model.AdWrkFields;
import erd.model.PsDbOwner;
import erd.model.PszTriggerEmployee;
import erd.model.PszVariable;
import erd.model.Zhri100aFields;

public class ZHRI100A {

	public static String psEmpl; //$PSEmpl
	public static String psOprid; //$PSOprid
	public static String errorProgramParm = "HRZ102A"; //$ErrorProgramParm = 'HRZ102A'
	
	public static void main() {
//		#include 'setenv.sqc' !Set environment
//		begin-program
//		Do GET-CURRENT-DATETIME  !Gets the current date and time using curdttim.sqc
//		Do Init-DateTime      !  Converts UNIX months to numeric     JHV  07/21/01
//		do Process-Main
		processMain();
//		do Reset          !Reset.sqc
//		end-program
	}
	/**
	 * Process-Main from ZHRI100A,SQR
	 * This is the process controlling procedure.
	 */
	public static void processMain() {
		Zhri100aFields zhri100aFields;
		PszTriggerEmployee trigger;
		//Begin-Procedure Process-Main
		zhri100aFields = initializeMainProperties();
		//LET $WrkCriticalFlag = 'N'
		zhri100aFields.wrkCriticalFlag = false;
		//LET #run_flag = 1
		Boolean runFlag = true;
		//while #run_flag = 1        !Never ending loop
		while(runFlag == true) {
			//do Check-interface-runfile
			runFlag = checkInterfaceRunfile(zhri100aFields);
			//do Built-Where-Clause-Delay    !Surya Added - TEMPMAST 
			//do Get-Trigger-Data       !Process the interface requests
			//trigger = getTriggerData(adLegacyOperatorId, adActionCode, poiFlag, isOkToProcess, completionStatus);
			//do Commit-Transaction
			//LET $Command = 'sleep 15'  !After interface run wait 15 seconds and do it again  !sree**rehost        !ZHR_MOD_ZHRI100A_sleep
			//Call System Using $Command #status Wait            !sree**rehost     !ZHR_MOD_ZHRI100A_sleep
			//do Get-Trigger-Data-NonEmp  !Surya Added - TEMPMAST - 12/29 - calls the procedure for POIs ad multiple EIDs 
			//do Commit-Transaction !Surya Added - TEMPMAST  - 12/29
			//LET $Command_non = 'sleep 15'  !Surya Added - TEMPMAST  after the main trigger table wait for 15 secs
			//Call System Using $Command_non #status Wait !Surya Added - TEMPMAST - 12/29
			//do Get-Trigger-Data-POI-EMP-Convert  !Surya Added - TEMPMAST  - 03/16- calls the procesdure for POI to EMP Hires after a delay
			//do Commit-Transaction !Surya Added - TEMPMAST - 03/16
			//LET $Command_W = 'sleep 15'  !Surya Added - TEMPMAST  after the main trigger table wait for 15 secs
			//Call System Using $Command_W #status Wait !Surya Added - TEMPMAST  - 03/16
			//If $file_open = 'Y'                        !ZHR_MOD_ZHRI100A_sleep
				//close 1                                   !ZHR_MOD_ZHRI100A_sleep
			//End-If                                     !ZHR_MOD_ZHRI100A_sleep
			//LET $file_open = 'N'                       !ZHR_MOD_ZHRI100A_sleep
		//end-while   !1=1
		}
		//LET $Command = 'mv' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.stop' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'  ! ALS-10/08/2008
		//Show '$Command in Process-Main: ' $Command                                        ! ALS-10/08/2008
		//Call System Using $Command #status Wait           !ZHR_MOD_ZHRI100A_sleep       
		//End-Procedure Process-Main
	}



//	!----------------------------------------------------------------------!                       !LJM-04/03/01-Rehost
//	!   Procedure:    FTP-File                                             !   !LJM-04/03/01-Rehost
//	!   Description:  This procedure will transfer the file from UNIX      !                       !LJM-04/03/01-Rehost
//	!                 to NT server                                         !                       !LJM-04/03/01-Rehost
//	!----------------------------------------------------------------------!                       !LJM-04/03/01-Rehost
//	Begin-Procedure FTP-File                                                                       !LJM-04/03/01-Rehost
//	If #status != 0
//	End-If
//	End-Procedure

//	!----------------------------------------------------------------------!                       !LJM-04/03/01-Rehost
//	!   Procedure:    Check-Interface-Runfile                              !                       !LJM-04/03/01-Rehost
//	!   Description:  This procedure will check the existance run file     !                       !LJM-04/03/01-Rehost
//	!----------------------------------------------------------------------!                       !LJM-04/03/01-Rehost
//	Begin-Procedure Check-interface-runfile
//	LET $RUN_FILEPATH = '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'   ! ALS-10/08/2008
//	Show '$RUN_FILEPATH: ' $RUN_FILEPATH                                                ! ALS-10/08/2008
//	LET #file_exists = exists($RUN_FILEPATH)
//	If #file_exists = 0
//LET #run_flag = 1
//	Else
//LET #run_flag = 0
//	End-If
//	End-Procedure Check-interface-runfile


//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Call-Programs
//	! Desc:  Subroutine will call appropriate programs
//	!----------------------------------------------------------------------
//	Begin-Procedure Call-Programs
//	do Intialize-AD-WrkFields
//	LET $TrigTaskFlag = ''
//	evaluate $WrkProcess
//	    when = 'ZHRI101A'
//	        !Move fields to be used in the called SQC
//	        LET $Wrk_Oprid = $AuditOprid
//	        LET $Wrk_Emplid = $PSEmplid
//	        LET $Wrk_Effdt = $PSEffdt
//	        move #PSEffseq to #Wrk_Effseq
//	        LET $Wrk_Process_Name = $WrkProcess
//	        LET $TrigTaskFlag = $WrkTaskFlag      !Surya Added - TEMPMAST 
//	        LET $Wrk_Inf_ = ' '
//	        LET $ADAction_Code = 'H'
//	        LET $ADLegOprid = ''
//	        Do HR01-Process-Main    !ZHRI101A.SQC
//	        break
//	    when = 'ZHRI102A'
//	        !Move fields to be used in the called SQC
//	        Move #Wrk_Sequence to #WrkSeqNbr
//	        LET $PSAuditOperId = $AuditOprid
//	        LET $PSDateIn = $PSEffdt
//	        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
//	        LET $ADAction_Code = 'T'
//	        LET $ADLegOprid = ''
//	        Do HR02-Process-Main    !ZHRI102A.SQC
//	        break
//	    when = 'ZHRI104A'
//	        !Move fields to be used in the called SQC
//	        LET $PSUserOprid = $AuditOprid
//	        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
//	        Move #PSEffseq to #WrkEffseq
//	        LET $ADAction_Code = 'C'
//	        LET $ADLegOprid = ''
//	        Do HR04-Process-Main    !ZHRI104A.SQC
//	        break
//	    when = 'ZHRI105A'
//	        !Move fields to be used in the called SQC
//	        LET $PSemp = $AuditOprid
//	        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
//	        LET $ADAction_Code = 'C'
//	        LET $ADLegOprid = ''
//	        LET $Wrk_ADCountryCdBuild = 'Y'                 !sree-UAAMOD
//	        Do HR05-Process-Main    !ZHRI105A.SQC
//	        break
//	    when = 'ZHRI106A'
//	        !Move fields to be used in the called SQC
//	        LET $Wrk_Oprid = $AuditOprid
//	        LET $Wrk_Emplid = $PSEmplid
//	        LET $Wrk_Effdt = $PSEffdt
//	        move #PSEffseq to #Wrk_Effseq
//	        LET $Wrk_Process_Name = $WrkProcess
//	        LET $ADAction_Code = 'R'
//	        Do HR01-Process-Main       !ZHRI101A.SQC
//	        break
//	    when = 'ZHRI107A'
//	        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
//	        LET $ADAction_Code = ''
//	        LET $ADLegOprid = ''
//	        Do HR07-Process-Main
//	        break
//	    when = 'ZHRI109A'
//	        !Move fields to be used in the called SQC
//	        LET $PSUserOprid = $AuditOprid
//	        LET $Wrk_Emplid = $PSEmplid                              !sree**10/04/01
//	        Move #PSEffseq to #WrkEffseq
//	        LET $ADAction_Code = 'C'
//	        LET $ADLegOprid = ''
//	        Do HR09-Process-Main        !ZHRI100A.SQC
//	        break
//	    when = 'ZHRI101D'     !Row deleted on hire
//	        LET $ErrorProgramParm = 'HRZ101A'
//	        LET $ErrorMessageParm = 'A row was deleted on the hire process'
//	        LET $WrkCriticalFlag = 'Y'
//	        Do Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//	        Do Call-Error-Routine
//	        LET $WrkCriticalFlag = 'N'
//	        LET $CompletionStatus = 'C'
//	        Do Update-Trigger-Row
//	    when = 'ZHRI102D'     !Row deleted on term
//	        LET $ErrorProgramParm = 'HRZ102A'
//	        LET $ErrorMessageParm = 'A row was deleted on the termination process'
//	        LET $WrkCriticalFlag = 'Y'
//	        Do Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//	        Do Call-Error-Routine
//	        LET $WrkCriticalFlag = 'N'
//	        LET $CompletionStatus = 'C'
//	        Do Update-Trigger-Row
//	    when = 'ZHRI104D'     !Row deleted on jobstatus change
//	        LET $ErrorProgramParm = 'HRZ104A'
//	        LET $ErrorMessageParm = 'A row was deleted on the job-profile process'
//	        LET $WrkCriticalFlag = 'Y'
//	        Do Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//	        Do Call-Error-Routine
//	        LET $WrkCriticalFlag = 'N'
//	        LET $CompletionStatus = 'C'
//	        Do Update-Trigger-Row
//	    when = 'ZHRI105D'     !Row deleted on demographis change
//	        LET $ErrorProgramParm = 'HRZ105A'
//	        LET $ErrorMessageParm = 'A row was deleted on the demographics process'
//	        LET $WrkCriticalFlag = 'Y'
//	        Do Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//	        Do Call-Error-Routine
//	        LET $WrkCriticalFlag = 'N'
//	        LET $CompletionStatus = 'C'
//	        Do Update-Trigger-Row
//	    when = 'ZHRI106D'     !Row deleted on rehire
//	        LET $ErrorProgramParm = 'HRZ101A'
//	        LET $ErrorMessageParm = 'A row was deleted on the re-hire process'
//	        LET $WrkCriticalFlag = 'Y'
//	        Do Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//	        Do Call-Error-Routine
//	        LET $WrkCriticalFlag = 'N'
//	        LET $CompletionStatus = 'C'
//	        Do Update-Trigger-Row
//	    when = 'ZHRI107D'     !Row deleted on
//	        LET $ErrorProgramParm = 'HRZ107A'
//	        LET $ErrorMessageParm = 'A row was deleted on the dates process'
//	        LET $WrkCriticalFlag = 'Y'
//	        Do Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//	        Do Call-Error-Routine
//	        LET $WrkCriticalFlag = 'N'
//	        LET $CompletionStatus = 'C'
//	        Do Update-Trigger-Row
//	    when = 'ZHRI109D'
//	        LET $ErrorProgramParm = 'HRZ109A'
//	        LET $ErrorMessageParm = 'A row was deleted on the group transfer process'
//	        LET $WrkCriticalFlag = 'Y'
//	        Do Prepare-Error-Parms           ! JHV  09/11/02  fix Date Mask error  ZHR_PRDSPT_INTF_ERROR
//	        Do Call-Error-Routine
//	        LET $WrkCriticalFlag = 'N'
//	        LET $CompletionStatus = 'C'
//	        Do Update-Trigger-Row
//	    when-other
//	        LET $CompletionStatus = 'E'     !update to an E to prevent looping and to mark the record in error
//	        Do Update-Trigger-Row
//	        break
//	end-evaluate
//	End-Procedure Call-Programs

//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Update-Trigger-Row
//	! Desc:  This routine update the trigger file flag switch
//	!----------------------------------------------------------------------
//	Begin-Procedure Update-Trigger-Row
//	begin-sql
//	Update PS_ZHRT_INTTRIGGER
//	     set Task_Flag = $CompletionStatus
//	where SEQ_NBR = #WRKSEQUENCE
//	end-sql
//	LET $CompletionStatus = 'P'     !Reset the completion Status for next pass
//	End-Procedure Update-Trigger-Row
//	!----------------------Surya Added - TEMPMAST Start --------------------!

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
//	    LET $taskflag = Ltrim(Rtrim(&SEC.TASK_FLAG,' '),' ')
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
//	    LET $NAuditOprid = Ltrim(Rtrim(&RN.OPRID,' '),' ')
//	RN.EMPLID
//	    LET $NPSEmplid = Ltrim(Rtrim(&RN.EMPLID,' '),' ')
//	    Move $NPSEmplid to #NWrk_EmplID1
//	    LET $NWrk_Emplid2 =  edit(#NWrk_EmplID1,'099999999')
//	to_char(RN.EFFDT, 'YYYY-MM-DD') &RNEFFDT
//	    LET $NPSEffdt = &RNEFFDT
//	RN.EFFSEQ
//	    Move &RN.EFFSEQ to #NPSEffSeq
//	RN.PROC_NAME
//	    LET $NWrkProcess = ltrim(rtrim(&RN.PROC_NAME,' '),' ')           !Remove leading and trailing blanks
//	RN.SEQUENCE 
//	    move &RN.SEQUENCE TO #indexNum 
//	   LET $PoiFlag = 'Y'
//	   DO Call-Programs-NonEmp  
//	   IF  $NPSEmplid = ''             
//	      LET $NCompletionStatus = 'E'
//	   End-if
//	   If $NCompletionStatus <> 'P'
//	     Do Update-Trigger-Row-NonEmp
//	   End-If  
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
//	        Do HR201-Process-Main    !ZHRI201A.SQC
//	        break
//	    when = 'ZHRI202A'
//	        !Move fields to be used in the called SQC
//	        LET $PSAuditOperId = $NAuditOprid
//	        LET $PSDateIn = $NPSEffdt
//	        LET $Wrk_Emplid = $NPSEmplid 
//	        LET $Wrk_indexNum = to_char(#indexNum)                   
//	        Do HR202-Process-Main    !ZHRI202A.SQC
//	        break
//	    when = 'ZHRI205A'
//	        !Move fields to be used in the called SQC
//	        LET $PSAuditemp = $NAuditOprid
//	        LET $Wrk_Emplid = $NPSEmplid                              
//	        LET $Wrk_indexNum = to_char(#indexNum)
//	        LET $PSEffdt =  $NPSEffdt     
//	        Do HR205-Process-Main    !ZHRI105A.SQC
//	        break
//	    when = 'ZHRI206A'
//	        !Move fields to be used in the called SQC
//	        LET $Wrk_Oprid = $NAuditOprid
//	        LET $Wrk_Emplid = $NPSEmplid
//	        LET $Wrk_Effdt = $NPSEffdt
//	        move #NPSEffseq to #Wrk_Effseq
//	        LET $Wrk_indexNum = to_char(#indexNum)
//	        LET $Wrk_Process_Name = $NWrkProcess
//	        Do HR201-Process-Main       !ZHRI201A.SQC
//	        break
//	    when-other
//	        LET $CompletionStatus = 'E'     !update to an E to prevent looping and to mark the record in error
//	        Do Update-Trigger-Row-NonEmp  !Surya Added - TEMPMAST 
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


//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Built-Where-Clause-Delay
//	! Desc:  Get the WHERE Clause for the delay hours in POI to EMP Transfer 
//	!----------------------------------------------------------------------
//	Begin-Procedure Built-Where-Clause-Delay
//	LET $XWHERE = ''
//	begin-select
//	XWER.ZPTF_OUTPUT_01
//	    LET $XWHERE = Ltrim(Rtrim(&XWER.ZPTF_OUTPUT_01,' '),' ')
//	    LET $WHERE1 = 'AND SYSTIMESTAMP  >= TR.UPDATED_DATETIME + INTERVAL '|| '''' || $XWHERE || '''' || '  HOUR'
//	from PS_ZPTT_XLAT_TBL XWER
//	where XWER.ZPTF_INPUT_01 = 'TEMPMAST'
//	and XWER.ZPTF_INPUT_02 = 'LEGACY-DELAY-HRS'       
//	end-select
//	End-Procedure Built-Where-Clause-Delay


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
//	    LET $AuditOprid = Ltrim(Rtrim(&RA.OPRID,' '),' ')
//	Ra.EMPLID
//	    LET $PSEmplid = Ltrim(Rtrim(&RA.EMPLID,' '),' ')
//	    Move $PSEmplid to #Wrk_EmplID1
//	    LET $Wrk_Emplid2 =  edit(#Wrk_EmplID1,'099999999')
//	to_char(RA.EFFDT, 'YYYY-MM-DD') &RAEFFDT
//	    LET $PSEffdt = &RAEFFDT
//	RA.EFFSEQ
//	    Move &RA.EFFSEQ to #PSEffSeq
//	RA.PROC_NAME
//	    LET $WrkProcess = ltrim(rtrim(&RA.PROC_NAME,' '),' ')           !Remove leading and trailing blanks
//	RA.TASK_FLAG
//	    LET $WrkTaskFlag = ltrim(rtrim(&RA.TASK_FLAG,' '),' ')
//	   LET $PoiFlag = 'N'       
//	   IF  $PSEmplid = ''             
//	      LET $CompletionStatus = 'E'
//	   End-if
//	   Do Call-Programs 
//	   If $CompletionStatus <> 'W'
//	      Do Update-Trigger-Row
//	   End-If       
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
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Call-System
//	! Desc:  Executes a command line statement stored in the $Command Variable
//	!----------------------------------------------------------------------
//	Begin-Procedure Call-System
//	!#ifdef debugx
//	!    show 'inside call system procedure'
//	!#end-if !debugx
//	LET #CommandLength = length($Command)             !Get the length of the command
//	LET #SubstrStartPos = 1    !Initiate the starting positions to show the first 100 positions
//	while #SubstrStartPos <= #CommandLength
//	    LET $ShowCommand = substr($Command,#SubstrStartPos,100)   !Substring 100 positions to show
//	    LET #SubstrStartPos = #SubstrStartPos + 100    !Increase the starting position by 100
//	    Show $ShowCommand        !ZHR_MOD_ZHRI100A_110B
//	end-while   !#SubstrStartPos <= #CommandLength
//	!#ifdef debugx
//	!    show 'before call system Rexec'
//	!#end-if !debugx
//	LET $Command = $RexecScript || ' ' || $Command || ' ' || $RMTSVR  !changed for v8.3
//	show '$Command=> ' $Command
//	Do GET-CURRENT-DATETIME  !Gets the current date and time using curdttim.sqc
//	show 'Calling Command at: ' $SysDateTime   !Surya Added - TEMPMAST 
//	Call System Using $Command #Status Wait      !Execute the command that was built on the command waiting until completion
//	!#ifdef debugx
//	!    show 'after call system Rexec'
//	!#end-if !debugx
//	If #status != 0
//	  !error
//	  LET $ErrorProgramParm = 'ZHRI100A'
//	  LET $ErrorMessageParm = ' '
//	  LET $ErrorMessageParm = 'Error executing Call System command, contact HR-PeopleSoft Oncall'
//	  LET $WrkCriticalFlag  = 'Y'
//	  Do Prepare-Error-Parms
//	  if $PoiFlag = 'N'
//	    Do Call-Error-Routine
//	  else
//	    Do Call-Error-Routine-NonEmp
//	  end-if
//	  LET $WrkCriticalFlag  = 'N'
//	End-If
//	End-Procedure Call-System
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
//	Do Get-Current-DateTime                                 !Get the current date and time
//	LET $AddDateErrorParm = datetostr(strtodate($AsOfToday,'DD-MON-YYYY'),'YYYYMMDD') !sree**rehost
//	LET $AddTimeErrorParm =    substr($Out,10,2)    ||    substr($Out,13,2)    ||    substr($Out,16,2)
//	LET $OpridErrorParm   =    Substr($AuditOprid,2,5)
//	End-Procedure Prepare-Error-Parms
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Call-Error-Routine
//	! Desc:  Builds the command and calls the error routine
//	!----------------------------------------------------------------------
//	Begin-Procedure Call-Error-Routine
//	!Make Sure that the ErrorMessageParm is always 75 Characters long
//	LET $ErrorMessageParm = Substr($ErrorMessageParm,1,75)  !Make sure not more than 75 long
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
//	Do Call-System                                              
//	End-Procedure Call-Error-Routine
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Call-Error-Routine-NonEmp
//	! Desc:  Builds the command and calls the error routine
//	!----------------------------------------------------------------------
//	Begin-Procedure Call-Error-Routine-NonEmp
//	!Make Sure that the ErrorMessageParm is always 75 Characters long
//	LET $ErrorMessageParm = Substr($ErrorMessageParm,1,75)  !Make sure not more than 75 long
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
//	Do Call-System                                              
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
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Check-If-Correct102A
//	! Desc:  Checks to see if 102A process has JOB row
//	!----------------------------------------------------------------------
//	Begin-Procedure Check-If-Correct102A                         !CQ 103011
//	LET $OK-to-process = 'N'
//	if $WrkProcess = 'ZHRI102A'
//	 do dtu-add-days($PSEffdt,1,$dt102)
//	end-if
//	Begin-Select
//	'XX'
//	LET $OK-to-process = 'Y'
//	From PS_JOB RD
//	where RD.EMPLID = $PSEmplid
//	  and to_char(RD.EFFDT, 'YYYY-MM-DD') = $dt102
//	End-Select
//	End-Procedure Check-If-Correct102A
//
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Build-Group-Where-Clause
//	! Desc:  This routine will build the where clause that will select the
//	!        correct Run-ID to use.
//	!----------------------------------------------------------------------
//	Begin-Procedure Build-Group-Where-Clause
//	LET $WhereClause = ltrim(rtrim($WhereClause,' '),' ')  !Remove leading and trailing blanks
//	if ($WhereClause = '')  !If the where clause is empty
//	    LET $WhereClause = 'Where ((' || $Alias || '.HMRGP = ' || '''' || $SelectGroup || ''''  !Add the first statement to the where clause
//	else  !The where clause is not empty
//	    LET $WhereClause = $WhereClause || ' or ' || $Alias || '.HMRGP = ' || '''' ||$SelectGroup || ''''!append a condition to the where clause
//	end-if      !$WhereClause = ''
//	End-Procedure   Build-Group-Where-Clause
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Build-Emplid-Where-Clause
//	! Desc:  Builds a where clause based on an employee id entered by the user
//	!----------------------------------------------------------------------
//
//	Begin-Procedure Build-Emplid-Where-Clause
//	    LET $WhereClause = 'Where ((' || $Alias || '.HMREMP = ' || '''' || $Run-Id || ''''  !Create the where clause
//	End-Procedure Build-Emplid-Where-Clause
//

//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  Get-Oprid
//	! Desc:  This routine gets the operator id from the operator definition
//	!        table
//	!----------------------------------------------------------------------
//
//	Begin-Procedure Get-Oprid
//	LET $Found = 'N'
//	LET $PSOprid = ''
//	if $PoiFlag = 'N'
//	  move 0 to #indexNum
//	end-if
//	if #indexNum = 0
//	    do get-LegID-for-seq0
//	else 
//	    do get-LegID-for-seqnum
//	end-if
//	!If an oprid does not exist for the employee, create one
//	If ($Found = 'N')
//	     do Get-Legacy-Oprid                  !sree**10/04/01
//	     LET $PSOprid = $LegEmplid
//	End-if    !$Found = 'N'
//	End-Procedure Get-Oprid
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  get-LegID-for-seq0
//	! Desc:  This routine gets the Legacy ID from Employee CREF Table for 
//	! Primary EIDs
//	!----------------------------------------------------------------------
//	Begin-Procedure get-LegID-for-seq0
//	begin-select
//	RPOD.ZHRF_LEG_EMPL_ID
//	    LET $PSOprid = &RPOD.ZHRF_LEG_EMPL_ID
//	    LET $Found = 'Y'
//	from PS_ZHRT_EMPID_CREF RPOD
//	where RPOD.Emplid = $Wrk_Emplid         
//	end-select
//	End-Procedure get-LegID-for-seq0
//
//	!----------------------------------------------------------------------
//	! ERAC
//	! Procedure:  get-LegID-for-seqnum
//	! Desc:  This routine gets the Legacy ID from Alternate EID Table
//	!----------------------------------------------------------------------
//	Begin-Procedure get-LegID-for-seqnum
//	!check if the multiple EID table has the EID!
//	begin-select
//	MULT.ZHRF_LEG_EMPL_ID
//
//	    LET $PSOprid = Ltrim(Rtrim(&MULT.ZHRF_LEG_EMPL_ID,' '),' ')
//	    if $PSOprid <> ''
//	      LET $Found = 'Y'
//	    end-if
//	from PS_ZHRR_MULTPL_EID MULT
//	where MULT.Emplid = $Wrk_Emplid  
//	and MULT.Sequence = #indexNum      
//	end-select
//	End-Procedure get-LegID-for-seqnum
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
//	if $PoiFlag = 'N'
//	  move 0 to #indexNum
//	end-if
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
//	    Do Format-Employee-Name
//	    LET $LegEmplid = &CHR36.H36EMP                         !Rakesh***08/02/2004
//	    LET $LegEmplid = substr($LegEmplid,1,5)
//	    !DO Insert-Oprid  !Surya Added - TEMPMAST 
//	    !Surya Added - TEMPMAST Start
//	    If #indexNum = 0
//	      DO Insert-Oprid
//	    else
//	      DO Update-Oprid
//	    end-if  
//	    !Surya Added - TEMPMAST End
//	  End-if    !#WRK_CHR36_H36EM_NUM = #wrk_emplid            !Rakesh***08/02/2004
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
//	Do Call-Error-Routine
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
//	!#end-if !debugx
//
//	LET $ErrorMessageParm = $Sql-error
//	Do Call-Error-Routine
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
//	!#end-if !debugx
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
//	!#end-if !debugx
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
//	!#end-if !debugx
//
//	LET $Wrkcreatefile = 'Y'
//	If $Wrk_AD_JobDataBuild = 'N'
//	   do AD-Get-Job-Data
//	End-If
//
//	do AD-Get-Job-Description
//	do AD-Get-EmplStatus-Description
//
//	If $Wrk_AD_PersdataEffdtBuild = 'N'
//	 do AD-Get-Pers-Data-Effdt
//	End-If
//
//	If $Wrk_AD_Getbusinessphone = 'N'
//	 do AD-Get-Business-Phone
//	End-If
//
//	If $Wrk_AD_PersDataBuild = 'N'
//	 do AD-Get-Personal-Data
//	End-If
//
//	If $Wrk_AD_CountryCdBuild = 'N'
//	 do AD-Get-Country-Code
//	End-If
//
//	do AD-Get-Employee-Fax
//	do AD-Get-Namesuffix
//	do AD-Get-JobStart-Date
//	do AD-Get-Employment-Data
//
//	If $ADSupervisorID <> ''
//	do AD-Get-LegSuperviorID
//	End-If
//
//	If $Wrk_AD_NamesBuild = 'N'
//	 do AD-Get-Names
//	End-If
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
//	  If $PSRehireDt <> ''
//	    LET $ADHireDt = $PSRehiredt
//	  else
//	    LET $ADHireDt = $PSHiredt
//	  End-If
//	If ($PSAction = 'REH')
//	and ($PSAction_Reason = 'REH')
//	and ($WrkProcess = 'ZHRI102A')
//	 LET $ADAction_Code = 'R'
//	End-If
//
//	!  If $ADAction_Code = 'T'
//	    LET $ADTermDt = $PSTerminationdt
//	!  End-If
//
//	LET $ADCountry = $PSLoc_Country
//	LET $ADFullPartTime = $PSFullPartTime
//	LET $ADEmplClass = $PSEmplClass
//	LET $ADDeptID  = $PSDeptId
//	LET $ADBusinessPhone = $PSBusiness_Phone
//	LET $ADLangCd = $PSLangCd
//	LET $ADPrfName = $PSPrfName
//
//	do Write-Active-Dir-Output-File
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
//	!#end-if !debugx
//
//	Begin-Select
//
//	AD.LOCATION
//	    LET $PSLocation = ltrim(rtrim(&AD.LOCATION,' '),' ')            !Remove leading and trailing blanks
//	AD.FULL_PART_TIME
//	    LET $PSFullPartTime = ltrim(rtrim(&AD.FULL_PART_TIME,' '),' ')  !Remove leading and trailing blanks
//	AD.EMPL_CLASS
//	    LET $PSEmplClass = ltrim(rtrim(&AD.EMPL_CLASS,' '),' ')         !Remove leading and trailing blanks
//	AD.EMPL_STATUS
//	    LET $PSEmplStatus = ltrim(rtrim(&AD.EMPL_STATUS,' '),' ')       !Remove leading and trailing blanks
//	AD.DEPTID
//	    LET $PSDeptid = ltrim(rtrim(&AD.DEPTID,' '),' ')                !Remove leading and trailing blanks
//	AD.JOBCODE
//	    LET $PSJobcode = ltrim(rtrim(&AD.JOBCODE,' '),' ')              !Remove leading and trailing blanks
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
//	!#end-if !debugx
//
//	LET $PSJobDescription = ''
//	Begin-Select
//
//	AD9.JOBCODE
//	AD9.DESCR
//
//	 LET $PSJobDescription = ltrim(rtrim(&AD9.DESCR,' '),' ')
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
//	!#end-if !debugx
//
//	Begin-Select
//
//	AD10.XLATLONGNAME
//
//	 LET $ADEmplStatusDescr = ltrim(rtrim(&AD10.XLATLONGNAME,' '),' ')
//
//	! from XLATTABLE AD10                                                               ! ALS-10/08/2008 - Commented Out
//	from PSXLATITEM AD10                                                                ! ALS-10/08/2008
//
//	where AD10.FIELDNAME = 'EMPL_STATUS'
//	and AD10.FIELDVALUE = $PSEmplstatus
//	! and AD10.LANGUAGE_CD = 'ENG'                                                      ! ALS-10/08/2008 - Commented Out
//	! and AD10.EFFDT = (Select max(AD11.EFFDT) from XLATTABLE AD11                      ! ALS-10/08/2008 - Commented Out
//	and AD10.EFFDT = (Select max(AD11.EFFDT) from PSXLATITEM AD11                       ! ALS-10/08/2008
//	                  where AD10.FIELDNAME = AD11.FIELDNAME
//	                  and AD10.FIELDVALUE = AD11.FIELDVALUE
//	!                  and AD10.LANGUAGE_CD = AD11.LANGUAGE_CD                          ! ALS-10/08/2008 - Commented Out
//	                  and AD11.EFFDT <= SYSDATE                                         ! ALS-10/08/2008
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
//	!#end-if !debugx
//
//	Begin-Select
//
//	to_char(AD4.EFFDT,'YYYY-MM-DD') &AD4.EFFDT
//
//	  LET $ADJobStartYr   = substr(&AD4.EFFDT,1,4)
//	  LET $ADJobStartMnth = substr(&AD4.EFFDT,6,2)
//	  LET $ADJobStartDay  = substr(&AD4.EFFDT,9,2)
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
//	!#end-if !debugx
//
//	begin-select
//
//	ADPDE2A.First_Name
//	ADPDE2A.Last_Name
//	ADPDE2A.Middle_Name    !added for v8.3
//
//	  LET $ADPSLastName   = RTRIM(LTRIM(&ADPDE2A.Last_Name,' '),' ')
//	  LET $ADPSFirstName  = RTRIM(LTRIM(&ADPDE2A.First_Name,' '),' ')
//	  LET $ADPSMiddleName = RTRIM(LTRIM(&ADPDE2A.Middle_Name,' '),' ')
//	  LET $ADPSMiddleName = SUBSTR($ADPSMiddleName,1,1)
//
//	from PS_NAMES ADPDE2A     !Changed for v8.3
//	where ADPDE2A.Emplid = $PSEmplid
//	  and ADPDE2A.NAME_TYPE = 'PRI'                                    !added for v8.3
//	  and ADPDE2A.Effdt = (select max(ADPDE2B.effdt) from  PS_NAMES ADPDE2B  !Changed for v8.3
//	                       where ADPDE2B.emplid    = ADPDE2A.emplid
//	                       and   ADPDE2B.NAME_TYPE = ADPDE2A.NAME_TYPE
//	                       and   to_char(ADPDE2B.EFFDT,'YYYY-MM-DD') <= $PSEffdt) !added for v8.3
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
//	!#end-if !debugx
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
//	  and ANAME.NAME_TYPE = 'PRI'                                    !added for v8.3
//	  and ANAME.Effdt = (select max(ANAME2.effdt) from  PS_NAMES ANAME2  !Changed for v8.3
//	                       where ANAME2.emplid     = ANAME.emplid
//	                       and   ANAME2.NAME_TYPE  = ANAME.NAME_TYPE
//	                       and   to_char(ANAME2.EFFDT,'YYYY-MM-DD') <= $PSEffdt) !added for v8.3
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
//	!#end-if !debugx
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
//	!#end-if !debugx
//
//	LET $PSLoc_Country = ''
//
//	Begin-Select
//
//	ADL2.COUNTRY
//	    LET $PSLoc_Country = ltrim(rtrim(&ADL2.COUNTRY,' '),' ')         !Remove leading and trailing blanks
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
//	!#end-if !debugx
//
//	LET $PSBusiness_Phone = ''
//	begin-select
//	ADPP2.Phone
//
//	 do Remove-Non-Letters-Numbers (&ADPP2.Phone, $PSBusiness_Phone)   !From ZRmvSpcChr.sqc
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
//	!#end-if !debugx
//
//	begin-select
//	ADPP3.Phone
//	 do Remove-Non-Letters-Numbers (&ADPP3.Phone, $ADEmployeeFax)   !From ZRmvSpcChr.sqc
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
//	!#end-if !debugx
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
//	  LET $PSHireYr = substr(&ADEHire_Dt,1,4)
//	  LET $PSHireMnth = substr(&ADEHire_Dt,6,2)
//	  LET $PSHireDay = substr(&ADEHire_Dt,9,2)
//	  LET $PSHiredt = $PSHireYr || $PSHireMnth || $PSHireDay
//	to_char(ADE.REHIRE_DT,'YYYY-MM-DD')       &ADERehire_Dt
//	  LET $PSRehireYr = substr(&ADERehire_Dt,1,4)
//	  LET $PSRehireMnth = substr(&ADERehire_Dt,6,2)
//	  LET $PSRehireDay = substr(&ADERehire_Dt,9,2)
//	  LET $PSRehiredt = $PSRehireYr || $PSRehireMnth || $PSRehireDay
//	to_char(ADE.TERMINATION_DT,'YYYY-MM-DD')  &ADETermination_Dt
//	  LET $PSTermYr = substr(&ADETermination_Dt,1,4)
//	  LET $PSTermMnth = substr(&ADETermination_Dt,6,2)
//	  LET $PSTermDay = substr(&ADETermination_Dt,9,2)
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
//	!#end-if !debugx
//	LET $PSPrfName = ''
//	begin-select
//	ADN.First_Name   !changed for v8.3 and defect 993
//	  LET $PSPrfName = RTRIM(LTRIM(&ADN.First_Name,' '),' ')
//	from PS_Names ADN
//	where ADN.Emplid = $PSEmplid
//	  and ADN.NAME_TYPE = 'PRF'
//	  and ADN.EFFDT     = (SELECT MAX(EFFDT) FROM PS_Names ADN2   !added for v8.3
//	                      WHERE ADN2.EMPLID   = ADN.EMPLID        !added for v8.3
//	                      AND ADN2.NAME_TYPE  = ADN.NAME_TYPE     !added for v8.3
//	                      AND to_char(ADN2.EFFDT,'YYYY-MM-DD') <= $PSEffdt) !added for v8.3
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
//		! This gets the oracle_sid
//		LET $PS_HOME = getenv('PS_HOME')
		zhri100aFields.peopleSoftHomePath = System.getenv("PS_HOME");
//		LET $AD_HOME = $PS_HOME || '/data/activedir/'                   !Path for Active Directory
		zhri100aFields.activeDirectoryHomePath = zhri100aFields.peopleSoftHomePath + "/data/activedir/"; //TODO: where is this used???
//		LET $ORACLE_SID = getenv('ORACLE_SID') !added for v8.3
//		UPPERCASE $ORACLE_SID                  !added for v8.3
		zhri100aFields.oracleSystemId = System.getenv("ORACLE_SID");
		if(zhri100aFields.oracleSystemId != null) {
			zhri100aFields.oracleSystemId = zhri100aFields.oracleSystemId.toUpperCase();
		}
//		!Returns name of AS/400 machine for use in zbas002b.sh
//		LET $Variable_Needed = ' '            !added for v8.3
//		LET $Variable_Needed = 'RMTSVR'       !added for v8.3
//		Do  Get-Variable                      !added for v8.3
//		LET $RMTSVR = $PSZPTT_VARIABLE_VAL    !added for v8.3  
//		Show '$RMTSVR: ' $RMTSVR                                                            ! ALS-10/08/2008
		zhri100aFields.remoteServerName = PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, zhri100aFields.dbName, "RMTSVR"); //TODO: where is this used???
//		LET $RexecScript = '/usr/local/barch/' || $ORACLE_SID || '/scripts/zbas002b.sh'     ! ALS-10/08/2008
//		Show '$RexecScript: ' $RexecScript                                                  ! ALS-10/08/2008
		zhri100aFields.remoteExecScript = "/usr/local/barch/" + zhri100aFields.oracleSystemId + "/scripts/zbas002b.sh"; //TODO: where is this used???
//		!Returns library name on AS/400 where programs reside
//		LET $Variable_Needed = ' '            !added for v8.3
//		LET $Variable_Needed = 'AS400library' !added for v8.3
//		Do  Get-Variable                      !added for v8.3
//		LET $Library = $PSZPTT_VARIABLE_VAL   !added for v8.3
//		Show '$Library: ' $Library                                                          ! ALS-10/08/2008
		zhri100aFields.as400Library = PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, zhri100aFields.dbName, "AS400library");
//		!Returns IP address of NT server
//		LET $Variable_Needed = ' '             !added for v8.3
//		LET $Variable_Needed = 'RMTNTADSVR'    !added for v8.3
//		Do Get-Variable                        !added for v8.3
//		LET $RMTNTADSVR = $PSZPTT_VARIABLE_VAL !added for v8.3
//		Show '$RMTNTADSVR: ' $RMTNTADSVR                                                    ! ALS-10/08/2008
		zhri100aFields.remoteAdServerName = PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(wrkProcessName, zhri100aFields.dbName, "RMTNTADSVR"); //TODO: where is this used???
		return zhri100aFields;
	}

	/**
	 * ZHRI100A.Check-Interface-Runfile
	 * This procedure will check the existence run file
	 */
	public static Boolean checkInterfaceRunfile(Zhri100aFields zhri100aFields) {
		Boolean runFlag = false;
		String runFilePath;
//		LET $RUN_FILEPATH = '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'  //concatenate
		runFilePath = "/usr/local/barch/" + zhri100aFields.oracleSystemId + "/work/hrinterface.run";
//		LET #file_exists = exists($RUN_FILEPATH)
		Boolean fileExists = (new File(runFilePath)).exists();
//		IF #file_exists = 0
		if(fileExists == false) {
//			LET #run_flag = 1
			runFlag = true;		}
//		ELSE
		else {
//			LET #run_flag = 0
			runFlag = false;
//		END-IF
		}
		return runFlag;
	}
	
	/**
	 * Get-Trigger-Data - from ZHRI100A.SQR
	 * This procedure will get the trigger data that needs to be interfaced
	 */
	public static PszTriggerEmployee getTriggerData(String adLegacyOperatorId, String adActionCode, Boolean poiFlag, Boolean isOkToProcess, String completionStatus) {
		//asOfToday
		//sysDate
		//adActionCode
		//adLegOprId
		//fileOpen
		PszTriggerEmployee trigger = PszTriggerEmployee.createMockTriggerForEmployeeTermination();
		Boolean adFound = false; //$AdFound  //TODO: where should this be set???
		//Begin-Procedure Get-Trigger-Data
		//LET $CompletionStatus = 'P'   !Initialize the CompletionStatus field
		completionStatus = "P";
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
		//Begin-Select loops=150
		//RZ.SEQ_NBR
		//MOVE &RZ.SEQ_NBR TO #WrkSequence
		//RZ.OPRID
		//LET $AuditOprid = Ltrim(Rtrim(&RZ.OPRID,' '),' ')
		//RZ.EMPLID
		//LET $PSEmplid = Ltrim(Rtrim(&RZ.EMPLID,' '),' ')
		//Move $PSEmplid to #Wrk_EmplID1
		//LET $Wrk_Emplid2 =  edit(#Wrk_EmplID1,'099999999')
		//to_char(RZ.EFFDT, 'YYYY-MM-DD') &RZEFFDT
		//LET $PSEffdt = &RZEFFDT
		//RZ.EFFSEQ
		//Move &RZ.EFFSEQ to #PSEffSeq
		//RZ.PROC_NAME
		//LET $WrkProcess = ltrim(rtrim(&RZ.PROC_NAME,' '),' ')           !Remove leading and trailing blanks
		//RZ.TASK_FLAG                                                        !Surya Added - TEMPMAST 
		//LET $WrkTaskFlag = ltrim(rtrim(&RZ.TASK_FLAG,' '),' ')          !Surya Added - TEMPMAST 
		//If $file_open = 'N'
		//    !open $open_file1 as 1 for-append record=337
		//    !LET $file_open = 'Y'
		//End-If
		//LET $PoiFlag = 'N'    !Surya Added - TEMPMAST 
		//Do Check-If-Contractor
		//If $Found = 'N'     !Not a contractor
		//		and  $PSEmplid <> ''    ! not a blank emplid   ZHR_MOD_ZHRI100A_110A
		//!CQ 103011 Added a check for 'ZHRI102A' - to see if corresponding row on JOB 
		//	if $WrkProcess = 'ZHRI102A'                                       !CQ 103011
		//  	Do Check-If-Correct102A                                        !CQ 103011                 
		//      if $OK-to-process = 'Y'                                        !CQ 103011
		//   		Do Call-Programs                                            !CQ 103011
		//      else                                                           !CQ 103011
		//         LET $CompletionStatus = 'C'                                 !CQ 103011
		//         Do Update-Trigger-Row                                       !CQ 103011
		//      end-if                                                         !CQ 103011
		//	else
		//      !Do Call-Programs        !Surya  - TEMPMAST  commented
		//      !Surya Added - TEMPMAST - Start
		//      if $WrkProcess = 'ZHRI101A' 
		//    		do Check-if-POI-Termed
		//        	if ($taskflag = 'C' OR $taskflag ='P')
		//          	LET $CompletionStatus = 'W'
		//          	Do Update-Trigger-Row
		//        	else 
		//          	Do Call-Programs                              
		//        	end-if 
		// 		else 
		//    		Do Call-Programs               
		//      end-if
		//      !Surya Added - TEMPMAST - End
		//	end-if
		//Else
		//	If $Found = 'Y'
		// 		LET $CompletionStatus = 'C'
		//	End-if
		// 	IF  $PSEmplid = ''             !ZHR_MOD_ZHRI100A_110A
		// 		LET $CompletionStatus = 'E'
		// 	End-if
		//End-If    !$Found = 'N'
		//If $CompletionStatus <> 'P'
		//	If ($ADAction_Code <> '') AND ($ADLegOprid <> '')
		//   	!DO Check-Effdt-transaction
		//     	IF $AdFound = 'N'
		//  		!Do Build-Active-Dir-Output-File      10/14/2005  Jane Parks / ZHR_ZHRI100A_REMOVE_AD
		//     	End-If
		//	End-If
		// 	Do Update-Trigger-Row
		//End-If    !$CompletionStatus <> 'P'
		//from PS_ZHRT_INTTRIGGER RZ ,PS_JOB JB
		//where RZ.TASK_FLAG = 'P'
		//  and (RZ.EFFDT <= $AsOfToday or RZ.PROC_NAME='ZHRI101A' or  RZ.PROC_NAME='ZHRI106A')
		//  and (case when proc_name in ('ZHRI101A', 'ZHRI106A') then SEQ_NBR else SEQ_NBR*10 END) = 
		//  (select min(case when proc_name in ('ZHRI101A', 'ZHRI106A') then SEQ_NBR else SEQ_NBR*10 END)  
		//             from  PS_ZHRT_INTTRIGGER RZ2
		//              where RZ2.EMPLID = RZ.EMPLID
		//                and RZ2.TASK_FLAG = 'P'
		//                and (RZ2.EFFDT <= SYSDATE or RZ2.PROC_NAME='ZHRI101A' or
		//                     RZ2.PROC_NAME='ZHRI106A'))
		//!                    and RZ2.TASK_FLAG = 'P'
		//!                    and RZ2.EFFDT <= trunc(sysdate))
		//!Surya  - TEMPMAST  Added the below NOT IN to restrict the rows other than 101 to process when there is a delay
		// and RZ.EMPLID NOT IN (SELECT I.EMPLID FROM PS_ZHRT_INTTRIGGER I WHERE I.EMPLID = RZ.EMPLID AND I.TASK_FLAG = 'W') 
		//  and JB.EMPLID = RZ.EMPLID
		//  and JB.EFFDT = (SELECT MAX(JB2.EFFDT)
		//                FROM  PS_JOB JB2
		//               WHERE  JB2.EMPLID = JB.EMPLID
		//                 AND  JB2.EMPL_RCD = JB.EMPL_RCD)
		//  and JB.EFFSEQ = (SELECT MAX(JB3.EFFSEQ)
		//                 FROM PS_JOB JB3
		//                WHERE JB3.EMPLID = JB.EMPLID
		//                 AND  JB3.EMPL_RCD = JB.EMPL_RCD
		//                 AND  JB3.EFFDT = JB.EFFDT)
		//End-Select
		//End-Procedure Get-Trigger-Data
		return trigger;
	}

}

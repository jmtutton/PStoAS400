package erd.controller;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

import erd.model.PszVariable;

/**
 * Replaces ZHRI100A.SQR
 * Interfaces the PeopleSoft data to the Legacy data
 * @author John Tutton john@tutton.net
 *
 */

public class Main {

//    Let $PSAuditemp = $NAuditOprid
//    Let $Wrk_Emplid = $NPSEmplid                              
//    let $Wrk_indexNum = to_char(#indexNum)
//    Let $PSEffdt =  $NPSEffdt     
	
	String Wrk_Emplid = " "; //$Wrk_Emplid
	String indexNumber = " "; //#indexNum
	String effectiveDate = " "; //$PSEffdt

	//AS400 Stored Procedure Parameters
	String auditEmployeeId = "e859v1"; //$PSAuditemp
	String employeeId = "e859v1"; //$PSEmpl
	String employeeGroup = "98"; //$PSGroup
	String employeeBranch = "41"; //$PSbranch
	String employeeLastName = "Tutton"; //$PSLName
	String employeeFirstName = "John"; //$PSFName
	String employeeMiddleName = "Michael"; //$PSMName
	String employeeNickname = "Johnny"; //$PSNickname
	String employeeGender = "male"; //$PSGender
	Date employeeLegacyServiceDate; //$LegServiceDate
	String employeeDeptartmentId  = "ERD"; //$PSDeptid
	String employeePosition = "Java Engineer 3"; //$PSPosition
	String employeeReferralSource = ""; //$PSReferral_Source
	String employeeAddress = "6720 Edison Ave"; //$PSAddress
	String employeeCity = "St Louis"; //$PSCity

	String employeeMiddleInitial = "m"; //$PS_HOME
	String employeePrefixLastName = "Mr"; //PrefLastname
	String PsEmp = "";  //PsEMP = PsEMP.substring(2,5);
	String poi = "N"; //PsPoi
	String poiCat = " "; //POICat
	String errorProgramParm = "HRZ205A"; //ErrorProgramParm
	String completionStatus = "C";
	
	String psHome = " "; //
	String activeDirectoryPath = " "; //$AD_HOME
	String oracleSid = " "; //$ORACLE_SID
	String remoteServerName = " "; //$RMTSVR
	String rexecScript = " "; //$RexecScript
	String remoteLibraryName = " "; //$Library
	String remoteNtServerIpAddress = " "; //$RMTNTADSVR
	
	String dbName = "PS90HRQA"; //VAR.DBNAME = (Select dbname from PSDBOWNER)
	String wrkCriticalFlag = ""; //$WrkCriticalFlag
	boolean runFlag = false; //#run_flag
	String commandEmployee = ""; //$Command
	String commandNonPerson = ""; //$Command_non
	String fileOpen = "N"; //$file_open
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
//	try {
//		employeeLegacyServiceDate = simpleDateFormat.parse("05/08/2017"); //$LegServiceDate
//	}
//	catch {
//		
//	}

	/**
	 * Replaces Process-Main from ZHRI100A.SQR
	 */
	public Main() {
//		//This gets the oracle_sid
//		LET $PS_HOME = getenv('PS_HOME')
		psHome = System.getenv("PS_HOME");
//		LET $AD_HOME = $PS_HOME || '/data/activedir/'    !Path for Active Directory
		activeDirectoryPath = System.getenv("PS_HOME") + "/data/activedir/";
//		LET $ORACLE_SID = getenv('ORACLE_SID')
		oracleSid = System.getenv("ORACLE_SID");
//		UPPERCASE $ORACLE_SID
		if(oracleSid != null) oracleSid = oracleSid.toUpperCase();
//
//		!Returns name of AS/400 machine for use in zbas002b.sh
//		Let $Variable_Needed = ' '
//		Let $Variable_Needed = 'RMTSVR'
//		Do  Get-Variable
//		Let $RMTSVR = $PSZPTT_VARIABLE_VAL
		remoteServerName = PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName("ZHRI100A", dbName, "RMTSVR");
//
//		Let $RexecScript = '/usr/local/barch/' || $ORACLE_SID || '/scripts/zbas002b.sh'
		rexecScript = "/usr/local/barch/" + oracleSid + "/scripts/zbas002b.sh";
//
//		//Returns library name on AS/400 where programs reside
//		Let $Variable_Needed = ' '
//		Let $Variable_Needed = 'AS400library'
//		Do  Get-Variable
//		Let $Library = $PSZPTT_VARIABLE_VAL
//		Show '$Library: ' $Library
		remoteLibraryName = PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName("ZHRI100A", dbName, "AS400library");
//
//		//Returns IP address of NT server
//		Let $Variable_Needed = ' '
//		Let $Variable_Needed = 'RMTNTADSVR'
//		Do Get-Variable
//		Let $RMTNTADSVR = $PSZPTT_VARIABLE_VAL
//		Show '$RMTNTADSVR: ' $RMTNTADSVR
		remoteNtServerIpAddress = PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName("ZHRI100A", dbName, "RMTNTADSVR");
//
//		  Let $WrkCriticalFlag = 'N'
		wrkCriticalFlag = "N";
//		  Let #run_flag = 1
//		  while #run_flag = 1        //Never ending loop
		//***
//		   do Check-interface-runfile
		runFlag = interfaceRunFileExists();
//		   do Get-Trigger-Data       //Process the interface requests
		GetEmployeeTriggerData();
//		   do Commit-Transaction
		CommitTransaction();
//		   let $Command = 'sleep 15'  //After interface run wait 15 seconds and do it again  //sree**rehost        //ZHR_MOD_ZHRI100A_sleep
		commandEmployee = "sleep 15";
//		   Call System Using $Command #status Wait            //sree**rehost     //ZHR_MOD_ZHRI100A_sleep
//		  
		//Get-Trigger-Data-NonEmp
//		   do Get-Trigger-Data-NonEmp  //calls the procedure for POIs ad multiple EIDs
		GetNonPersonTriggerData();
//		   do Commit-Transaction
		CommitTransaction();
//		   let $Command_non = 'sleep 15'  //after the main trigger table wait for 15 secs
		commandNonPerson = "sleep 15";
//		   Call System Using $Command_non #status Wait
//		   If $file_open = 'Y'                        //ZHR_MOD_ZHRI100A_sleep
//		    close 1                                   //ZHR_MOD_ZHRI100A_sleep
//		   End-If                                     //ZHR_MOD_ZHRI100A_sleep
//		   Let $file_open = 'N'                       //ZHR_MOD_ZHRI100A_sleep
//		  end-while   //1=1
//		  let $Command = 'mv' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.stop' || ' ' || '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'
//		  Show '$Command in Process-Main: ' $Command
//		  Call System Using $Command #status Wait           //ZHR_MOD_ZHRI100A_sleep       
	}

	/**
	 * Replaces Commit-Transaction
	 */
	private void CommitTransaction() {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Replaces HR205-Massage-Data from ZHRI205A.SQC
	 * Will massage the data to get it in the form that the AS400 RPG program needs it in to process
	 */
	public void formatAS400ProgramParameters() {
		//Format the legacy employee ID from the PeopleSoft Oprid for audit field
		//Remove leading and trailing blanks
		auditEmployeeId = auditEmployeeId.trim();
		//Make characters upper case
		auditEmployeeId = auditEmployeeId.toUpperCase();
		//Remove the leading 'E' from the employee ID
		auditEmployeeId = auditEmployeeId.replaceFirst("E","");

		//Format the employee group
		employeeGroup = employeeGroup.toUpperCase();
		//Format the employee branch
		employeeBranch = employeeBranch.toUpperCase();

		//Format the employee last name
//		Let $PSLName = $PrefLastname || $PSLName
//				Uppercase $PSLName  !Be sure in all CAPS
//				!Do Replace-Character($PSLName,'''','''''',$PSLName) !Replace all single apostrophe with four apostrophes ZRmvSpcChr.sqc
		if(employeePrefixLastName == null || employeePrefixLastName.isEmpty()) {
			employeeLastName = employeeLastName.toUpperCase();
		}
		else {
			employeeLastName = employeePrefixLastName.toUpperCase() + " " + employeeLastName.toUpperCase();
		}
		//Replace all single apostrophes with four apostrophes ZRmvSpcChr.sqc
		employeeLastName = employeeLastName.replaceAll("'","''''");

		//Format the employee first name
		employeeFirstName = employeeFirstName.toUpperCase();
		//Replace all single apostrophes with four apostrophes ZRmvSpcChr.sqc
		employeeFirstName = employeeFirstName.replaceAll("'","''''");

		//Format the employee middle initial
		employeeMiddleInitial = employeeMiddleName.substring(0,1);
		//Make characters upper case
		employeeMiddleInitial = employeeMiddleInitial.toUpperCase();

		//Format the employee gender
		employeeGender = employeeGender.toUpperCase();

		//Format Position and Department
		employeePosition = employeePosition.toUpperCase();
		employeeDeptartmentId = employeeDeptartmentId.toUpperCase();

		//Format Recruit Source Information
		employeeReferralSource = employeeReferralSource.toUpperCase();
	}
	
	@Override
	public String toString() {
		return "auditEmployeeId = " + auditEmployeeId + "\n"
				+ "employeeId = " + employeeId + "\n"
				+ "employeeGroup = " + employeeGroup + "\n"
				+ "employeeBranch = " + employeeBranch + "\n"
				+ "employeeLastName = " + employeeLastName + "\n"
				+ "employeeFirstName = " + employeeFirstName + "\n"
				+ "employeeMiddleName = " + employeeMiddleName + "\n"
				+ "employeeMiddleInitial = " + employeeMiddleInitial + "\n"
				+ "employeeNickname = " + employeeNickname + "\n"
				+ "employeePreferedLastname = " + employeePrefixLastName + "\n"
				+ "employeeGender = " + employeeGender + "\n"
				+ "employeeAddress = " + employeeAddress + "\n"
				+ "employeeCity = " + employeeCity + "\n"
				+ "employeeLegacyServiceDate = " + employeeLegacyServiceDate + "\n"
				+ "employeeDeptartmentId  = " + employeeDeptartmentId + "\n"
				+ "employeePosition = " + employeePosition + "\n"
				+ "employeeReferralSource = " + employeeReferralSource + "\n"
				+ "PsEmp = " + PsEmp + "\n"
				+ "poi = " + poi + "\n"
				+ "poiCat = " + poiCat + "\n"
				+ "errorProgramParm = " + errorProgramParm + "\n"
				+ "completionStatus = " + completionStatus + "\n"
				+ "psHome = " + psHome + "\n"
				+ "activeDirectoryPath = " + activeDirectoryPath + "\n"
				+ "oracleSid = " + oracleSid + "\n"
				+ "remoteServerName = " + remoteServerName + "\n"
				+ "rexecScript = " + rexecScript + "\n"
				+ "remoteLibraryName = " + remoteLibraryName + "\n"
				+ "ntServerIpAddress = " + remoteNtServerIpAddress + "\n";
	}
	
	/**
	 * Replaces Get-Trigger-Data from ZHRI100A.SQR
	 * Gets the trigger data that needs to be interfaced
	 */
	private void GetEmployeeTriggerData() {
//		Let $CompletionStatus = 'P'   !Initialize the CompletionStatus field
//
//				Begin-Select loops=150
//
//				RZ.SEQ_NBR
//				    MOVE &RZ.SEQ_NBR TO #WrkSequence
//				RZ.OPRID
//				    LET $AuditOprid = Ltrim(Rtrim(&RZ.OPRID,' '),' ')
//				RZ.EMPLID
//				    LET $PSEmplid = Ltrim(Rtrim(&RZ.EMPLID,' '),' ')
//				    Move $PSEmplid to #Wrk_EmplID1
//				    LET $Wrk_Emplid2 =  edit(#Wrk_EmplID1,'099999999')
//
//				to_char(RZ.EFFDT, 'YYYY-MM-DD') &RZEFFDT
//				    LET $PSEffdt = &RZEFFDT
//				RZ.EFFSEQ
//				    Move &RZ.EFFSEQ to #PSEffSeq
//				RZ.PROC_NAME
//				    LET $WrkProcess = ltrim(rtrim(&RZ.PROC_NAME,' '),' ')           !Remove leading and trailing blanks
//
//				 If $file_open = 'N'
//				 !   open $open_file1 as 1 for-append record=337
//				 !   let $file_open = 'Y'
//				 End-If
//
//				    Do Check-If-Contractor
//				    Let $PoiFlag = 'N'    !Surya Added
//
//				    If $Found = 'N'     !Not a contractor
//				     and  $PSEmplid <> ''    ! not a blank emplid   ZHR_MOD_ZHRI100A_110A
//				!CQ 103011 Added a check for 'ZHRI102A' - to see if corresponding row on JOB 
//				       if $WrkProcess = 'ZHRI102A'                                       !CQ 103011
//				          Do Check-If-Correct102A                                        !CQ 103011                 
//				          if $OK-to-process = 'Y'                                        !CQ 103011
//				             Do Call-Programs                                            !CQ 103011
//				          else                                                           !CQ 103011
//				             Let $CompletionStatus = 'C'                                 !CQ 103011
//				             Do Update-Trigger-Row                                       !CQ 103011
//				          end-if                                                         !CQ 103011
//				       else
//				          Do Call-Programs
//				       end-if
//				    Else
//
//				       If $Found = 'Y'
//				        Let $CompletionStatus = 'C'
//				       End-if
//				       IF  $PSEmplid = ''             !ZHR_MOD_ZHRI100A_110A
//				       Let $CompletionStatus = 'E'
//				       End-if
//
//				    End-If    !$Found = 'N'
//
//				    If $CompletionStatus <> 'P'
//
//				       If ($ADAction_Code <> '') AND ($ADLegOprid <> '')
//				   !     DO Check-Effdt-transaction
//				         IF $AdFound = 'N'
//				  !          Do Build-Active-Dir-Output-File      10/14/2005  Jane Parks / ZHR_ZHRI100A_REMOVE_AD
//				         End-If
//				       End-If
//				     Do Update-Trigger-Row
//				    End-If    !$CompletionStatus <> 'P'
//
//				from PS_ZHRT_INTTRIGGER RZ
//				    ,PS_JOB JB
//				where RZ.TASK_FLAG = 'P'
//
//				!************************************************************************************
//				! Start : ZHR_MOD_INTERFACE_PREHIRE
//				!************************************************************************************
//				  and (RZ.EFFDT <= $AsOfToday or RZ.PROC_NAME='ZHRI101A' or  RZ.PROC_NAME='ZHRI106A')
//
//				!************************************************************************************
//				! End : ZHR_MOD_INTERFACE_PREHIRE
//				!************************************************************************************
//				!************************************************************************************
//				! Brent Martin 2/16/2009 - 
//				!        PeopleCode 8.0 fires in different order than 8.3, need to insure the new hire and rehire
//				!        appear before the other changes even if they don't show up first in the sequence.
//				!************************************************************************************
//				  and (case when proc_name in ('ZHRI101A', 'ZHRI106A') then SEQ_NBR else SEQ_NBR*10 END) = 
//				      (select min(case when proc_name in ('ZHRI101A', 'ZHRI106A') then SEQ_NBR else SEQ_NBR*10 END)  
//				                 from  PS_ZHRT_INTTRIGGER RZ2
//				                  where RZ2.EMPLID = RZ.EMPLID
//				                    and RZ2.TASK_FLAG = 'P'                                         ! ALS-03/11/2009
//				                    and (RZ2.EFFDT <= SYSDATE or RZ2.PROC_NAME='ZHRI101A' or        ! ALS-08/03/2009
//				                         RZ2.PROC_NAME='ZHRI106A'))                                 ! ALS-08/03/2009
//				!********************************************************************************** ! ALS-10/08/2008
//				! Andy Lee-Sue  10/08/2008 - Join in JOB record to ensure that only employees that  ! ALS-10/08/2008
//				!                            have a JOB record get processed.                       ! ALS-10/08/2008
//				!********************************************************************************** ! ALS-10/08/2008
//				  and JB.EMPLID = RZ.EMPLID                                                         ! ALS-10/08/2008
//				  and JB.EFFDT = (SELECT MAX(JB2.EFFDT)                                             ! ALS-10/08/2008
//				                    FROM  PS_JOB JB2                                                ! ALS-10/08/2008
//				                   WHERE  JB2.EMPLID = JB.EMPLID                                    ! ALS-10/08/2008
//				                     AND  JB2.EMPL_RCD = JB.EMPL_RCD)                               ! ALS-03/11/2009
//				  and JB.EFFSEQ = (SELECT MAX(JB3.EFFSEQ)                                           ! ALS-10/08/2008
//				                     FROM PS_JOB JB3                                                ! ALS-10/08/2008
//				                    WHERE JB3.EMPLID = JB.EMPLID                                    ! ALS-10/08/2008
//				                     AND  JB3.EMPL_RCD = JB.EMPL_RCD                                ! ALS-10/08/2008
//				                     AND  JB3.EFFDT = JB.EFFDT)                                     ! ALS-10/08/2008
//				End-Select
	}
		
	/**
	 * Replaces Get-Trigger-Data-NonEmp from ZHRI100A.SQR
	 * Gets the trigger data for non employees and multiple EIDs that needs to be interfaced
	 */
	private void GetNonPersonTriggerData() {
//		Let $NCompletionStatus = 'P'   !Initialize the CompletionStatus field
//		Begin-Select loops=150
//		RN.SEQ_NBR
//		    MOVE &RN.SEQ_NBR TO #NWrkSequence
//		RN.OPRID
//		    LET $NAuditOprid = Ltrim(Rtrim(&RN.OPRID,' '),' ')
//		    LET $AuditOprid = Ltrim(Rtrim(&RZ.OPRID,' '),' ')
//		RN.EMPLID
//		    LET $NPSEmplid = Ltrim(Rtrim(&RN.EMPLID,' '),' ')
//		    Move $NPSEmplid to #NWrk_EmplID1
//		    LET $NWrk_Emplid2 =  edit(#NWrk_EmplID1,'099999999')
//		to_char(RN.EFFDT, 'YYYY-MM-DD') &RNEFFDT
//		    LET $NPSEffdt = &RNEFFDT
//		RN.EFFSEQ
//		    Move &RN.EFFSEQ to #NPSEffSeq
//		RN.PROC_NAME
//		    LET $NWrkProcess = ltrim(rtrim(&RN.PROC_NAME,' '),' ')           !Remove leading and trailing blanks
//		RN.SEQUENCE 
//		    move &RN.SEQUENCE TO #indexNum 
//		   DO Call-Programs-NonEmp  
//		   IF  $NPSEmplid = ''             !ZHR_MOD_ZHRI100A_110A
//		      Let $NCompletionStatus = 'E'
//		   End-if
//		   Let $PoiFlag = 'Y'
//		   
//		   If $NCompletionStatus <> 'P'
//		     Do Update-Trigger-Row_NonEmp
//		   End-If  
//		from PS_ZHRT_ALTTRIGGER RN
//		where RN.TASK_FLAG = 'P'
//		and (RN.EFFDT <= $AsOfToday or RN.PROC_NAME='ZHRI201A' or  RN.PROC_NAME='ZHRI206A')
//		and (case when proc_name in ('ZHRI201A', 'ZHRI206A') then SEQ_NBR else SEQ_NBR*10 END) = 
//		      (select min(case when proc_name in ('ZHRI201A', 'ZHRI206A') then SEQ_NBR else SEQ_NBR*10 END)  
//		                 from  PS_ZHRT_ALTTRIGGER RN2
//		                  where RN2.EMPLID = RN.EMPLID
//		                    AND RN2.SEQUENCE = RN.SEQUENCE
//		                    AND RN2.PROC_NAME = RN.PROC_NAME 
//		                    and RN2.TASK_FLAG = 'P'                                         
//		                    and (RN2.EFFDT <= SYSDATE or RN2.PROC_NAME='ZHRI201A' or        
//		                         RN2.PROC_NAME='ZHRI206A'))                                 
//		End-Select
	}

	/**
	 * Replaces Check-Interface-Runfile from ZHRI100A.SQR
	 * Checks the existence run file
	 */
	public boolean interfaceRunFileExists() {
		String runFilePath = "/usr/local/barch/" + oracleSid + "/work/hrinterface.run";
//		let $RUN_FILEPATH = '/usr/local/barch/' || $ORACLE_SID || '/work/hrinterface.run'
//		Show '$RUN_FILEPATH: ' $RUN_FILEPATH
		if(new File(runFilePath).isFile()) {
			return true;
		}
		else {
			return false;
		}
//		LET #file_exists = exists($RUN_FILEPATH)
//		If #file_exists = 0
//		   Let #run_flag = 1
//		Else
//		   Let #run_flag = 0
//		End-If
	}
	
}

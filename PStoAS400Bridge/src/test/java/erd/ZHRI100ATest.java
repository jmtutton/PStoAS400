package erd;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import erd.controller.EmployeeTermination;
import erd.controller.ZHRI100A;
import erd.model.ProcessParameters;
import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.TerminationProcessParameters;

public class ZHRI100ATest {

//	@Test
//	public void testZHRI100ABuiltWhereClauseDelay() {
//		String result = ZHRI100A.ZHRI100ABuiltWhereClauseDelay();
//		Assert.assertNotNull(result);
//		System.out.println(result);
//	}

//	@Test
//	public void testZHRI100A_callErrorRoutine() {
//		String processName = "ZHRI100A";
//		CommonParameters commonParameters = ZHRI100A.initializeCommonParameters();
//		commonParameters.setErrorMessageParameter("Error executing Call System command, contact HR-PeopleSoft Oncall");
//		commonParameters.setProcessName(processName);
//		ZHRI100A.ZHRI100A_callErrorRoutine(commonParameters);
//	}

//	@Test
//	public void testZHRI100A_for_termination() {
//		ZHRI100A.main();
//	}
//	
//	@Test
//	public void testCallSystemUsingCommand() {
//		String commandString = null;
//		CommonParameters commonParameters = null;
//		ZHRI100A.callSystemUsingCommand(commandString, commonParameters);
//		
//	}
	
	@Test
	public void testJavaRexec() {
		ZHRI100A.initializeServerProperties();
		///QSYS.LIB/EHRHRMS06#.LIB/HRZ*.PGM
		String commandString = null;
//		commandString = composeMockErrorCommandString();
		commandString = composeMockEmployeeTerminationCommandString();
//		commandString = "DSPPGM PGM(EHRHRMS06#/HR100P00)";
//		commandString = "DSPPGM PGM(EHRHRMS06#/HRZ102A)";
//		commandString = "RTVCLSRC PGM(EHRHRMS06#/HRZ102A)";
//		commandString = "RUNSQL SQL('CREATE VIEW TUTTON AS SELECT * FROM EHRHRMS06/TEMPMAST')";
//		commandString = "RUNSQL SQL('SELECT * FROM EHRHRMS06#/TEMPMAST LIMIT 10')";
//		commandString = commandString.replace("''", "' '");
//		commandString = "CALL EHRHRMS06#/HRZ110A PARM('HRZ104A' '000339921' ' ' 'Company not found in XRef Table PS_ZHRT_CMPNY_CREF                         ' 'Y' '20170629' '' '155TG' 'Y')";
//		commandString = "CALL EHRHRMS06#/HRZ110A PARM('HRZ999A' '123456' ' ' 'Company not found in XRef Table PS_ZHRT_CMPNY_CREF                         ' 'Y' '20170629' '124301' '999X9' 'Y')";
//		commandString = "CALL EHRHRMS06#/HRZ101A PARM('352FS' '000352585' 'U6' '20' 'HOWARD' 'MARCUS' '' 'S' ' ' 'F' 'M' '1993' '10' '01' '2017' '06' '19' 'N' ' ' 'Y' ' ' 'D' '03' 'MGTRN' ' ' '                                   ' '38 ANGLEIA WAY' 'FAREHAM' 'HANTS' 'PO15 7HZ' '44 ' '7823557' 'Y' '' '' 'N' 'MR' 'H' 'GB')";
//		commandString = "CALL EHRHRMS06/HRZ101A PARM('999X9' '999999'    'U6' '20' 'JOHN' 'JOHNSSON' '' 'S' ' ' 'F' 'M' '1993' '10' '01' '2017' '06' '30' 'N' ' ' 'Y' ' ' 'D' '03' 'MGTRN' ' ' '                                   ' '99 ANGLEIA WAY' 'FAREHAM' 'HANTS' 'PO15 7HZ' '44 ' '7823557' 'Y' '' '' 'N' 'MR' 'H' 'GB')";
//		commandString = "CALL EHRHRMS06#/HRZ110A PARM('HRZ123A' '12345' '0' ' ' 'This is a test.                                                            ' 'N' '20170530' '132027' '999X9' 'Y')";
		System.out.println(commandString);
		ZHRI100A.as400Rexec(commandString);
	}
	
	@Test
	public void testJavaRexec2() {
		List<String> commandStringList = Arrays.asList("RUNSQL SQL('Insert into MyResults (SELECT * FROM EHRHRMS06/TEMPMAST)')"
			//RunSQL SQL('Insert into QTemp/MyResults (Select * from  Sample where EffDate < ''2012-05-01'')') Commit(*None)
			//OvrDBF File(Sample) ToFile(QTemp/MyResults)
			//ChgVar Var(&EOF) Value('0')
			//DoUntil Cond(&EOF = '1')
			//RcvF OpnID(MyResults)
			//MonMsg MsgID(CPF0864) Exec(ChgVar Var(&EOF) Value('1'))
			//If Cond(&EOF *EQ '0') Then(SndPgmMsg Msg('Class' *BCat &MyResults_Class *BCat 'is effective on' *BCat &MyResults_EffDate) ToPgmQ(*Ext))
			//Else Cmd(SndPgmMsg Msg('End of file') ToPgmQ(*Ext))
			//EndDo
			//Close OpnID(MyResults)
			//DltOvr File(Sample)				
//				Commit(*None) "
//				,
//				"OvrDBF File(Sample) ToFile(/temp/MyResults) ",
//				"ChgVar Var(&EOF) Value('0') ",
//				"DoUntil Cond(&EOF = '1') ",
//				"RcvF OpnID(MyResults) ",
//				"MonMsg MsgID(CPF0864) Exec(ChgVar Var(&EOF) Value('1')) ",
//				"If Cond(&EOF *EQ '0') Then(SndPgmMsg Msg('Class' *BCat &MyResults_Class *BCat 'is effective on' *BCat &MyResults_EffDate) ToPgmQ(*Ext)) ",
//				"Else Cmd(SndPgmMsg Msg('End of file') ToPgmQ(*Ext)) ",
//				"EndDo ",
//				"Close OpnID(MyResults) ",
//				"DltOvr File(Sample) "
				);
		ZHRI100A.initializeServerProperties();
		for(String commandString : commandStringList) {
			System.out.println(commandString);
			ZHRI100A.as400Rexec(commandString);
		}
	}
	
	public static String composeMockErrorCommandString() {
		CommonParameters commonParameters = new ProcessParameters().new CommonParameters();
		commonParameters.setErrorProgramParameter("HRZ999A");
		commonParameters.setEmployeeId("12345");
		commonParameters.setErrorMessageParameter("This is a test. ");
		commonParameters.setCriticalFlag(false);
		commonParameters.setEffectiveSequence(new BigDecimal(0));
		commonParameters.setOperatorId("999X9");
		String parameterString = ZHRI100A.composeErrorParameterString(commonParameters);
		return ZHRI100A.composeRexecCommandString("HRZ110A", parameterString);
	}
	
	public static String composeMockEmployeeTerminationCommandString() {
		TerminationProcessParameters terminationProcessParameters = new ProcessParameters().new TerminationProcessParameters();
		terminationProcessParameters.setEmployeeId("352FS");
		terminationProcessParameters.setTerminationMonth("06");
		terminationProcessParameters.setTerminationDay("30");
		terminationProcessParameters.setTerminationYear("2017");
		terminationProcessParameters.setRehireMonth("");
		terminationProcessParameters.setRehireDay("");
		terminationProcessParameters.setRehireYear("");
		terminationProcessParameters.setVoluntaryOrInvoluntary("V");
		terminationProcessParameters.setTerminationCode("O");
		terminationProcessParameters.setOperatorId("352FS");
		terminationProcessParameters.setTerminationReason("VOLUN  DISSATISFIED WHOURS");
		String parameterString = EmployeeTermination.composeParameterString(terminationProcessParameters);
		return ZHRI100A.composeRexecCommandString("HRZ102A", parameterString);
	}

//	@Test
//	public void test() {
//		System.out.println("testJUnit");
//	}

}

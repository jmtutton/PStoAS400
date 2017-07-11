package erd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import erd.controller.NonPersonTermination;
import erd.controller.Main;

public class _MainTest {

	@Test
	public void testMain() {
		Main.main();
	}

	@Test
	public void testJavaRexec() {
		Main.initializeServerProperties();
		///QSYS.LIB/EHRHRMS06#.LIB/HRZ*.PGM
		String commandString = null;
//	    commandString = "CALL EHRHRMS06#/HRZ102A PARM('840S4' '06' '20' '2017' '' '' '' 'V' 'O' '349NV' 'VOLUN  DISSATISFIED WHOURS         ')";
//	    commandString = "CALL EHRHRMS06#/HRZ100A PARM('HRZ102A' '859V1' '0' ' ' 'Error Message                                                              ' 'N' '20170529' '112649' '859V1' 'Y')";
//		commandString = composeMockErrorCommandString();
//		commandString = composeMockEmployeeTerminationCommandString();
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
//		String remoteServerHostName = "dev.corp.erac.com";
//		String remoteServerUsername = "PSHRINT";
//		String remoteServerPassword = "SMRHET01";
		System.out.println(commandString);
		Main.doRexec(commandString);
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
		Main.initializeServerProperties();
		for(String commandString : commandStringList) {
			System.out.println(commandString);
			Main.doRexec(commandString);
		}
	}
	
//	public static String composeMockErrorCommandString() {
//		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
//		CommonParameters commonParameters = new ProcessParameters().new CommonParameters();
//		commonParameters.setErrorProgramParameter("HRZ999A");
//		commonParameters.setEmployeeId("12345");
//		commonParameters.setErrorMessageParameter("This is a test. ");
//		commonParameters.setCriticalFlag(false);
//		commonParameters.setEffectiveSequence(new BigInteger("0"));
//		commonParameters.setOperatorId("999X9");
//		String parameterString = Main.composeErrorParameterString(parameterMap);
//		parameterMap.put("processName", "HRZ110A");
//		parameterMap.put("parameterString", parameterString);
//		return Main.composeCommandString(parameterMap);
//	}
	
	@Test
	public void testComposeParameterString() {
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("processName", "processName");
		parameterMap.put("parameterNameList", NonPersonTermination.getParameterNameList());
		parameterMap.put("parameterString", Main.composeParameterString(parameterMap));
		String result = Main.composeCommandString(parameterMap);
		System.out.println(result);
	}

}

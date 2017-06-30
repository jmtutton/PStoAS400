package erd;

import java.math.BigDecimal;

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
		String commandString = null;
//		commandString = composeMockErrorCommandString();
//		commandString = composeMockEmployeeTerminationCommandString();
//		commandString = "DSPPGM PGM(EHRHRMS06#/HRZ102A)";
		commandString = "RTVCLSRC PGM(EHRHRMS06#/HRZ102A)";
//		commandString = commandString.replace("''", "' '");
//		commandString = "CALL EHRHRMS06#/HRZ110A PARM('HRZ104A' '000339921' ' ' 'Company not found in XRef Table PS_ZHRT_CMPNY_CREF                         ' 'Y' '20170629' '' '155TG' 'Y')";
//		commandString = "CALL EHRHRMS06#/HRZ101A PARM('352FS' '000352585' 'U6' '20' 'HOWARD' 'MARCUS' '' 'S' ' ' 'F' 'M' '1993' '10' '01' '2017' '06' '19' 'N' ' ' 'Y' ' ' 'D' '03' 'MGTRN' ' ' '                                   ' '38 ANGLEIA WAY' 'FAREHAM' 'HANTS' 'PO15 7HZ' '44 ' '7823557' 'Y' '' '' 'N' 'MR' 'H' 'GB')";
		ZHRI100A.as400Rexec(commandString);
	}
	
	public static String composeMockErrorCommandString() {
		CommonParameters commonParameters = new ProcessParameters().new CommonParameters();
		commonParameters.setErrorProgramParameter("HRZ102A");
		commonParameters.setEmployeeId("859V1");
		commonParameters.setErrorMessageParameter("Error Message");
		commonParameters.setCriticalFlag(false);
		commonParameters.setEffectiveSequence(new BigDecimal(0));
		commonParameters.setOperatorId("859V1");
		String parameterString = ZHRI100A.composeErrorParameterString(commonParameters);
		return ZHRI100A.composeAs400RexecCommandString("HRZ110A", parameterString);
	}
	
	public static String composeMockEmployeeTerminationCommandString() {
		TerminationProcessParameters terminationProcessParameters = new ProcessParameters().new TerminationProcessParameters();
		terminationProcessParameters.setEmployeeId("352FS");
		terminationProcessParameters.setTerminationMonth("06");
		terminationProcessParameters.setTerminationDay("29");
		terminationProcessParameters.setTerminationYear("2017");
		terminationProcessParameters.setRehireMonth("");
		terminationProcessParameters.setRehireDay("");
		terminationProcessParameters.setRehireYear("");
		terminationProcessParameters.setVoluntaryOrInvoluntary("V");
		terminationProcessParameters.setTerminationCode("O");
		terminationProcessParameters.setOperatorId("859V1");
		terminationProcessParameters.setTerminationReason("VOLUN  DISSATISFIED WHOURS");
		String parameterString = EmployeeTermination.composeParameterStringForHrz102AProcess(terminationProcessParameters);
		return ZHRI100A.composeAs400RexecCommandString("HRZ102A", parameterString);
	}

	@Test
	public void test() {
		System.out.println("testJUnit");
	}

}

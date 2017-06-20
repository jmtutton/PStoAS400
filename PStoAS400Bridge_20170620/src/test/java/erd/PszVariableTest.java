package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.model.PszVariable;

public class PszVariableTest {

	@Test
	public void testFindVariableValueByProcessNameAndDbNameAndVariableName() {
		String processName = "ZHRI100A";
		String dbName = "PS90HRQA";
		String variableName = "RMTSVR";
		String variableValue = PszVariable.findVariableValueByProcessNameAndDbNameAndVariableName(processName, dbName, variableName);
		assertNotNull(variableValue);
		System.out.println("variableValue = " + variableValue);
	}

}

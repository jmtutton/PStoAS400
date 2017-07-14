package erd;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import erd.model.PsName;

public class PsNameTest {

	@Test
	public void testFindByEmployeeIdAndNameTypeAndEffectiveDate() {
		String employeeId = "323506";
		String nameType = "";
		Date effectiveDate = new Date();
		PsName result = PsName.findByEmployeeIdAndNameTypeAndEffectiveDate(employeeId, nameType, effectiveDate);
		assertNotNull(result);
	}

}

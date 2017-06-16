package erd;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testFormatLegacy() {
		String legacyEmployeeName = "LAST*FIRST MI*";
		String peopleSoftEmployeeName = erd.StringUtil.formatLegacyEmployeeNameToPeopleSoftEmployeeName(legacyEmployeeName);
		System.out.println(peopleSoftEmployeeName);
	}

}

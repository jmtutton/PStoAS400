package erd;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilTest {

	@Test
	public void testFormatLegacy() {
		String legacyEmployeeName = "LAST*FIRST MI*";
		String peopleSoftEmployeeName = ErdUtils.formatLegacyEmployeeNameToPeopleSoftEmployeeName(legacyEmployeeName);
		System.out.println(peopleSoftEmployeeName);
	}

	@Test
	public void testJoin() {
		List<String> accomplishmentCodeList = Arrays.asList("DRUGTST", "PHYS L3", "PHYS L4");
		accomplishmentCodeList.replaceAll(String::toUpperCase);
		String csvCodeList = "'" + String.join("','", accomplishmentCodeList) + "'";
		System.out.println(csvCodeList);
	}

}

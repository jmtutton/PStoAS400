package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.model.CrossReferenceEthnicGroup;

public class CrossReferenceEthnicGroupTest {

	@Test
	public void testPsDiversEthnic() {
		String ethnicGroup = "1";
		String result = CrossReferenceEthnicGroup.findActiveLegacyEthnicCodeByEthnicGroup(ethnicGroup);
		assertNotNull(result);
		System.out.println("result: " + result);
	}

}

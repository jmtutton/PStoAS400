package erd;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import erd.model.PsActionReason;

/**
 * Unit tests for PsActionReason entity class.
 * @author John Tutton john@tutton.net
 */
public class PsActionReasonTest {

	@Test
	public void testPsActionReason() {
		String actionCode = "TER";
		String actionReasonCode = "OTH";
		PsActionReason result = PsActionReason.findByActionCodeAndActionReasonCode(actionCode, actionReasonCode);
		assertNotNull(result);
		System.out.println(result);
	}

	@Test
	public void testFindAllActionReasonCode() {
		List<String> resultList = PsActionReason.findAllActionReasonCode();
		assertNotNull(resultList);
		for(String result : resultList) {
			System.out.println(result);
		}
	}

	@Test
	public void testFindAll() {
		List<PsActionReason> resultList = PsActionReason.findAll();
		assertNotNull(resultList);
		for(PsActionReason result : resultList) {
			System.out.println(result);
		}
	}

}

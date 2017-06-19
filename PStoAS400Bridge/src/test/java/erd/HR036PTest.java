package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.model.HR036P;

/**
 * Unit tests for HR036P entity class.
 * @author John Tutton john@tutton.net
 */
public class HR036PTest {

	@Test
	public void testGetLegacyOprId() {
		String employeeId = "01137";
		Integer indexNumber = 0;
		HR036P result = HR036P.findByEmployeeIdAndIndexNumber(employeeId, indexNumber);
		assertNotNull(result);
//		System.out.println(result.toString());
	}

}

package erd;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import erd.model.PsXlatItem;

public class PsXlatItemTest {

	@Test
	public void testFindMaxEffectiveDateByFieldNameAndFieldValue() {
		String fieldName = "EMPL_STATUS";
		String fieldValue = "W";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
//		Date effectiveDate;
//		try {
//			effectiveDate = (Date)");
//		}
//		catch(Exception name) {
//		}
		Date result = PsXlatItem.findMaxEffectiveDateByFieldNameAndFieldValue(fieldName, fieldValue);
		assertNotNull(result);
		System.out.println(result);
	}

}

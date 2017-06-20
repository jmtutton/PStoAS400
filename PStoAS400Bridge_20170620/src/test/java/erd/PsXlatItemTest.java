package erd;

import static org.junit.Assert.*;

import java.sql.Date;

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
//			effectiveDate = (Date) simpleDateFormat.parse("01/01/2000");
//		}
//		catch(Exception name) {
//		}
		Date result = PsXlatItem.findMaxEffectiveDateByFieldNameAndFieldValue(fieldName, fieldValue);
		assertNotNull(result);
		System.out.println(result);
	}

}

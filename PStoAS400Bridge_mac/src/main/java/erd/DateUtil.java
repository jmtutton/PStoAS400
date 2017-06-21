package erd;

import java.util.Calendar;
import java.util.Date;

/**
 * Date utilities to support ERD applications
 * @author John Tutton john@tutton.net
 */
public class DateUtil {
	
	/**
	 * From https://stackoverflow.com/questions/428918/how-can-i-increment-a-date-by-one-day-in-java
	 */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}

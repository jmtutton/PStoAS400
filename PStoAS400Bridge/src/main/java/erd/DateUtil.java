package erd;

import java.util.Calendar;
import java.util.Date;

/**
 * From https://stackoverflow.com/questions/428918/how-can-i-increment-a-date-by-one-day-in-java
 * @author John Tutton john@tutton.net
 *
 */
public class DateUtil {
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}

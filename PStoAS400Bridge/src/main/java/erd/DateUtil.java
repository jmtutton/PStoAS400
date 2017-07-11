package erd;

import java.util.Calendar;
import java.util.Date;

/**
 * Date utilities to support ERD applications
 * @author John Tutton john@tutton.net
 */
public class DateUtil {
	
	/**
	 * 
	 * @param date
	 * @param days
	 * @return date + days
	 */
	public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date asOfToday() {
    	Date asOfToday = new Date();
		//SELECT
		//{DateTimeOut-Prefix}SYSDATE{DateTimeOut-Suffix} &_SysDateTime
		//FROM PSCLOCK
		//LET $tempDate = STRTODATE(&_SysDateTime,{Native-DateTimeMask})
		//LET $_AsOfToday = DATETOSTR($tempDate,{Native-DateMask})
    	return asOfToday;
    }

}

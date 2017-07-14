package erd;

import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import erd.controller.Main;

/**
 * @see 
 * Date utilities to support ERD applications
 * @author John Tutton john@tutton.net
 */
public class ErdUtils {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	
	/**
	 * @param date
	 * @param days
	 * @return date + days
	 */
	public static Date addDays(Date date, int days) {
		if(Main.DEBUG) logger.debug("addDays()");
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
	
	/**
	 * Converts a name from legacy format (LAST*FIRST MI*) to PeopleSoft format (Last, First MI)
	 * @see Format-Employee-Name procedure in ZHRI100A.SQR
	 * @param legacyEmployeeName
	 * @return peopleSoftEmployeeName
	 */
    public static String formatLegacyEmployeeNameToPeopleSoftEmployeeName(String legacyEmployeeName) {
    	String psEmployeeName = "";
    	String lastName = "";
    	String firstName = "";
    	String middleInitial = "";
    	String[] nameSplit = legacyEmployeeName.split("\\*");
    	if(nameSplit != null && nameSplit.length > 0) { //last name exists
        	lastName = nameSplit[0].trim();
        	lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
        	psEmployeeName = lastName;
        	if(nameSplit.length > 1) { //first name exists
        		nameSplit = nameSplit[1].split(" ");
            	if(nameSplit != null && nameSplit.length > 0) {
                	firstName = nameSplit[0].trim();
                	firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
                	psEmployeeName = lastName + ", " + firstName;
               	if(nameSplit.length > 1) { //middle initial exists
                    	middleInitial = nameSplit[1].replace("\\*","").trim().toUpperCase();
                    	psEmployeeName = lastName + ", " + firstName + " " + middleInitial;
                	}
            	}
        	}
    	}
    	return psEmployeeName;
	}
	
	/**
	 * Converts from  First Name, Middle Name, Last Name, Suffix to legacy format (LAST*FIRST MI*)
	 * @see HR05-Format-Name procedure in ZHRI105A.SQC
	 * @param peopleSoftEmployeeName
	 * @return legacyEmployeeName
	 */
    public static String formatPeopleSoftEmployeeNameToLegacyEmployeeName(String firstName, String middleName, String lastName, String suffix) {
    	lastName = lastName != null ? lastName.trim() : lastName;
    	firstName = firstName != null ? firstName.trim() : firstName;
    	if(middleName != null) {
        	middleName = middleName.trim();
        	if(middleName.length() > 0) {
        		firstName = firstName + " " + middleName.substring(0, 1);
        	}
    	}
    	if(suffix != null) {
    		suffix = suffix.trim();
        	if(suffix.length() > 0) {
        		lastName = lastName + " " + suffix;
        	}
    	}
    	String legacyEmployeeName = lastName + "*" + firstName;
		//Legacy system only holds 33 characters in the name field.  If the name field is longer than
		//33 characters then remove characters from the end of the first and last name in order to fit into the
		//legacy name field.
    	if(legacyEmployeeName.length() > 32) {
    		legacyEmployeeName = lastName.substring(0, 16) + "*" + firstName.substring(0, 15);
    	}
    	legacyEmployeeName = legacyEmployeeName.toUpperCase() + "*";
    	return legacyEmployeeName;
	}
	
	/**
	 * Splits the into YYYY, MM, and DD format and stores in separate strings attributes.
	 */
    public class SplitDate {
		String year;
    	String month;
    	String day;
    	
    	public SplitDate(Date date) {
    		this.year = new SimpleDateFormat("yyyy").format(date).toUpperCase();
    		this.month = new SimpleDateFormat("mm").format(date).toUpperCase();
    		this.day = new SimpleDateFormat("dd").format(date).toUpperCase();
    	}
		public String getYear() {
			return year;
		}
		public String getMonth() {
			return month;
		}
		public String getDay() {
			return day;
		}
    }

    
}

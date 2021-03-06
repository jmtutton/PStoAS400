package erd;

/**
 * String utilities to support ERD applications
 * @author John Tutton john@tutton.net
 */
public class StringUtil {
	
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

}

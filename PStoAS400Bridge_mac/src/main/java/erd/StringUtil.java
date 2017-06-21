package erd;

/**
 * String utilities to support ERD applications
 * @author John Tutton john@tutton.net
 */
public class StringUtil {
	
	/**
	 * Format-Employee-Name from ZHRI100A.SQR
	 * Converts from legacy format (LAST*FIRST MI*) to PeopleSoft format (Last, First MI)
	 */
    public static String formatLegacyEmployeeNameToPeopleSoftEmployeeName(String legacyEmployeeName) {
		// ! Break apart the legacy system name field at the *
		// Unstring $LegEmpName by '*' into $PSEmp_Last_Name_Search $PSEmp_First_Name_Search $dummy   ! dummy is for the ending *
		// !Concatenate the Last name and the first name (separated by a comma(,) that were retrieved FROM the legacy system, and
		// !Unstringed above, into a work variable ($work_a) that will be used as input by the Convert Case routine
		// LET $_work_a = $PSEmp_Last_Name_Search || ',' || $PSEmp_First_Name_Search
		// !Execute a routine that will change the case of the $work_a variable to mixed case and return the result
		// !in another variable $New
		// DO M800-Convert-Case   !ZCvtCaseM.sqc
		// !Move the result from the convert-case routine to the PeopleSoft Employee Name field that will be inserted
		// LET $PSEmpName = $_New
    	String psEmployeeName = "";
    	String lastName = "";
    	String firstName = "";
    	String middleInitial = "";
    	String[] nameSplit = legacyEmployeeName.split("\\*");
    	if(nameSplit != null && nameSplit.length > 0) {
        	lastName = nameSplit[0].trim();
        	lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
        	psEmployeeName = lastName;
        	if(nameSplit.length > 1) {
        		nameSplit = nameSplit[1].split(" ");
            	if(nameSplit != null && nameSplit.length > 0) {
                	firstName = nameSplit[0].trim();
                	firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
                	psEmployeeName = lastName + ", " + firstName;
               	if(nameSplit.length > 1) {
                    	middleInitial = nameSplit[1].replace("\\*","").trim().toUpperCase();
                    	psEmployeeName = lastName + ", " + firstName + " " + middleInitial;
                	}
            	}
        	}
    	}
    	return psEmployeeName;
	}

}

package erd;

/**
 * String utilities to support ERD applications
 * @author John Tutton john@tutton.net
 */
public class StringUtil {
	
	/**
	 * Converts from legacy format (LAST*FIRST MI*) to PeopleSoft format (Last, First MI)
	 * @see Format-Employee-Name procedure in ZHRI100A.SQR
	 * @param legacyEmployeeName
	 * @return peopleSoftEmployeeName
	 */
    public static String formatLegacyEmployeeNameToPeopleSoftEmployeeName(String legacyEmployeeName) {
		//!Concatenate the Last name and the first name (separated by a comma(,) that were retrieved FROM the legacy system, and
		//!Unstringed above, into a work variable ($work_a) that will be used as input by the Convert Case routine
		//LET $_work_a = $PSEmp_Last_Name_Search || ',' || $PSEmp_First_Name_Search
		//!Execute a routine that will change the case of the $work_a variable to mixed case and return the result
		//!in another variable $New
		//!Move the result from the convert-case routine to the PeopleSoft Employee Name field that will be inserted
		//LET $PSEmpName = $_New
    	String psEmployeeName = "";
    	String lastName = "";
    	String firstName = "";
    	String middleInitial = "";
		//!Break apart the legacy system name field at the *
		//Unstring $LegEmpName by '*' into $PSEmp_Last_Name_Search $PSEmp_First_Name_Search $dummy   ! dummy is for the ending *
    	String[] nameSplit = legacyEmployeeName.split("\\*");
    	if(nameSplit != null && nameSplit.length > 0) {
        	lastName = nameSplit[0].trim();
    		//DO M800-Convert-Case   !ZCvtCaseM.sqc
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
	
	/**
	 * Converts from  First Name, Middle Name, Last Name, Suffix to legacy format (LAST*FIRST MI*)
	 * @see HR05-Format-Name procedure in ZHRI105A.SQC
	 * @param peopleSoftEmployeeName
	 * @return legacyEmployeeName
	 */
    public static String formatPeopleSoftEmployeeNameToLegacyEmployeeName(String firstName, String middleName, String lastName, String suffix) {
		//Let $PSLastName =  RTRIM(LTRIM(&CN3.Last_Name,' '),' ')
    	lastName = lastName != null ? lastName.trim() : lastName;
		//Let $PSFirstName = RTRIM(LTRIM(&CN3.First_Name,' '),' ')
    	firstName = firstName != null ? firstName.trim() : firstName;
		//If &CN3.Middle_Name <> ' '
    	if(middleName != null) {
    		//Let $PSMidInit = RTRIM(LTRIM(&CN3.Middle_Name,' '),' ')
        	middleName = middleName.trim();
        	if(middleName.length() > 0) {
        		//Let $PSMidInit = SUBSTR($PSMidInit,1,1)
        		//Let $PSFirstName = $PSFirstName || ' ' || $PSMidInit
        		firstName = firstName + " " + middleName.substring(0, 1);
        	}
    	}
		//If &CN3.Name_Suffix <> ' '
    	if(suffix != null) {
    		//Let $PSSuffix = RTRIM(LTRIM(&CN3.Name_Suffix,' '),' ')
    		suffix = suffix.trim();
        	if(suffix.length() > 0) {
        		//Let $PSLastName = $PSLastName || ' ' || $PSSuffix
        		lastName = lastName + " " + suffix;
        	}
    	}
		//Let $PSName = $PSLastName || ',' || $PSFirstName
    	String legacyEmployeeName = lastName + "*" + firstName;
		//!Legacy system only holds 33 characters in the name field.  If the name field is longer than
		//!33 characters then remove characters from the end of the first and last name in order to fit into the
		//!legacy name field.  32 is used because there is one comma in PSName that will be replaced
		//!by two asterisks when written to legacy 33 - (2 - 1) = 32
		//Let #NameLength = length($PSName)
		//If #NameLength > 32
    	if(legacyEmployeeName.length() > 32) {
    		//Let $PSLastName = substr($PSLastName,1,16)
    		//Let $PSfirstName = substr($PSLastName,1,15)
    		legacyEmployeeName = lastName.substring(0, 16) + "*" + firstName.substring(0, 15);
    	}
		//!Convert first name and last name into upper case and then concatenate the last
		//!name (separated by '*') followed by first name and then '*"
		//Let $PSName = $PSLastName || '*' || $PSFirstName || '*'
		//UPPERCASE $PSName
    	legacyEmployeeName = legacyEmployeeName.toUpperCase() + "*";
    	return legacyEmployeeName;
	}

}

package erd.controller;

import java.util.HashMap;

import erd.model.PsDriversLicense;

/**
 * Employee Demographic Change Process
 * @see ZHRI105A.SQC
 * @author John Tutton john@tutton.net
 *
 */

public class EmployeeDemographicChange {

	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	public String doProcess(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDemographicChange.doProcess() ***");
		parameterMap = fetchProcessParameters(parameterMap);
		parameterMap.put("parameterString", composeParameterString(parameterMap));
		return ZHRI100A.doCommand(parameterMap);
	}
	
	/**
	 * 
	 * @param parameterMap
	 * @return
	 */
	private HashMap<String, Object> fetchProcessParameters(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDemographicChange.fetchProcessParameters() ***");
		parameterMap.put("errorProgramParameter", "HRZ105A");
		return parameterMap;
	}
	
	/**
	 * 
	 * @param parameterMap
	 */
	private String composeParameterString(HashMap<String, Object> parameterMap) {
		System.out.println("*** EmployeeDemographicChange.composeParameterString() ***");
		// $PSbranch || ''' ''' || $PSEMP || ''' ''' || $PSNational_Id || ''' ''' ||
		// $PSHealth_Stat || ''' ''' || $PSHealth_Stat_Desc || ''' ''' || $PSVehRpt || ''' ''' ||
		// $PSName || ''' ''' || $PSName_Prefix || ''' ''' || $PSNickname ||
		// $PSAddress ||
		// $PSCity ||
		// $PSState||
		// $PSZip ||
		// $PSPhone||
		// $PSBusiness_Phone ||
		// $PSCountry ||
		// $PSGender ||
		// $PSMarITAL_StatUS ||
		// $PSEthnic_Group ||
		// $PSChg_Dt ||
		// $PSBirthDate ||
		// $PSStat_date ||
		// $PSStart_date ||
		// $PSDriver_Lic ||
		// $PSDlstate ||
		// $PSContact_Name ||
		// $PSEmer_Phn ||
		// $PSRelation ||
		// $PSEmpl_Spouse ||
		// $PSReferral_Source ||
		// $PSRecruit_Gp ||
		// $PSRecruiter_Id ||
		// $PSSpecific_Refer_Src ||
		// $PSCollege ||
		// $PSGradYr ||
		// $PSMajor||
		// $PS_NID_COUNTRY5 || !dshen 01/12/2012
		String paramaterString = ""
        + "'" + parameterMap.get("PSEmpl") + "' "
        + "'" + parameterMap.get("PSGroup") + "' "
        + "'" + parameterMap.get("PSreg") + "' "
        + "'" + parameterMap.get("PSbranch") + "' "
        + "'" + parameterMap.get("PSEMP") + "' "
        + "'" + parameterMap.get("PSNational_Id") + "' "
        + "'" + parameterMap.get("PSHealth_Stat") + "' "
        + "'" + parameterMap.get("PSHealth_Stat_Desc") + "' "
        + "'" + parameterMap.get("PSVehRpt") + "' "
        + "'" + parameterMap.get("PSName") + "' "
        + "'" + parameterMap.get("PSName_Prefix") + "' "
        + "'" + parameterMap.get("PSNickname") + "' "
        + "'" + parameterMap.get("PSAddress") + "' "
        + "'" + parameterMap.get("PSCity") + "' "
        + "'" + parameterMap.get("PSState") + "' "
        + "'" + parameterMap.get("PSZip") + "' "
        + "'" + parameterMap.get("PSPhone") + "' "
        + "'" + parameterMap.get("PSBusiness_Phone") + "' "
        + "'" + parameterMap.get("PSCountry") + "' "
        + "'" + parameterMap.get("PSGender") + "' "
        + "'" + parameterMap.get("PSMarITAL_StatUS") + "' "
        + "'" + parameterMap.get("PSEthnic_Group") + "' "
        + "'" + parameterMap.get("PSChg_Dt") + "' "
        + "'" + parameterMap.get("PSBirthDate") + "' "
        + "'" + parameterMap.get("PSStat_date") + "' "
        + "'" + parameterMap.get("PSStart_date") + "' "
        + "'" + parameterMap.get("PSDriver_Lic") + "' "
        + "'" + parameterMap.get("PSDlstate") + "' "
        + "'" + parameterMap.get("PSContact_Name") + "' "
        + "'" + parameterMap.get("PSEmer_Phn") + "' "
        + "'" + parameterMap.get("PSRelation") + "' "
        + "'" + parameterMap.get("PSEmpl_Spouse") + "' "
        + "'" + parameterMap.get("PSReferral_Source") + "' "
        + "'" + parameterMap.get("PSRecruit_Gp") + "' "
        + "'" + parameterMap.get("PSRecruiter_Id") + "' "
        + "'" + parameterMap.get("PSSpecific_Refer_Src") + "' "
        + "'" + parameterMap.get("PSCollege") + "' "
        + "'" + parameterMap.get("PSGradYr") + "' "
        + "'" + parameterMap.get("PSMajor") + "' "
        + "'" + parameterMap.get("PS_NID_COUNTRY5") + "' ";
		return paramaterString;
	}

	/**
	 * This routine gets the driver license number and state and stores them in the legacy system format.
	 * @see HR05-Get-Drivers-Lic procedure in ZHRI105A.SQC
	 * @param employeeId
	 * @return PsDriversLicense record
	 */
	public PsDriversLicense fetchDriversLicense(String employeeId) {
		//BEGIN-PROCEDURE HR05-GET-DRIVERS-LIC
		//BEGIN-SELECT
		PsDriversLicense psDriversLicense = PsDriversLicense.findByEmployeeId(employeeId);
		//CDL.DRIVERS_LIC_NBR
		//CDL.STATE
		if(psDriversLicense != null) {
			if(psDriversLicense.getDriversLicenseNumber() != null) {
				String driversLicenseNumber = psDriversLicense.getDriversLicenseNumber();
				//DO REPLACE-CHARACTER($PSDriver_Lic, '''', '''''', $PSDriver_Lic)    !From ZRmvSpcChr.sqc
				driversLicenseNumber = driversLicenseNumber.replace("'", "''");
				//LET $PSDRIVER_LIC = RTRIM(LTRIM(&CDL.DRIVERS_LIC_NBR, ' '), ' ')
				psDriversLicense.setDriversLicenseNumber(driversLicenseNumber.trim());
			}
			if(psDriversLicense.getState() != null) {
				//UPPERCASE $PSDLState
				psDriversLicense.setState(psDriversLicense.getState().trim().toUpperCase());
			}
		}
		//LET $PSDLState = &CDL.State
		//UPPERCASE $PSDLState
		//FROM PS_Drivers_Lic CDL
		//WHERE CDL.Emplid = $PSEmplid
		//END-SELECT
		//END-PROCEDURE HR05-GET-DRIVERS-LIC
		return psDriversLicense;
	}
}

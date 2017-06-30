package erd.controller;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.DemographicChangeProcessParameters;

import erd.model.PsDriversLicense;

/**
 * Employee Demographic Change Process
 * @see ZHRI105A.SQC
 * @author John Tutton john@tutton.net
 *
 */

public class EmployeeDemographicChange {

	public String processEmployeeDemographicChange(CommonParameters commonParameters) {
		DemographicChangeProcessParameters demographicChangeProcessParameters = fetchProcessParameters(commonParameters);
		composeParameterString(demographicChangeProcessParameters);
		return null;
	}
	
	private DemographicChangeProcessParameters fetchProcessParameters(CommonParameters commonParameters) {
		return null;
	}
	
	/**
	 * 
	 * @param processParameters
	 */
	private String composeParameterString(DemographicChangeProcessParameters demographicChangeProcessParameters) {
		System.out.println("********** composeParameterStringForHrz102AProcess");
		//Let $Part2 = 'Parm(''' || $PSEmpl || ''' ''' || $PSGroup|| ''' ''' || $PSreg || ''' ''' ||
		// $PSbranch || ''' ''' || $PSEMP || ''' ''' || $PSNational_Id || ''' ''' ||
		// $PSHealth_Stat || ''' ''' || $PSHealth_Stat_Desc || ''' ''' || $PSVehRpt || ''' ''' ||
		// $PSName || ''' ''' || $PSName_Prefix || ''' ''' || $PSNickname ||
		// ''' ''' ||
		// $PSAddress ||
		// ''' ''' ||
		// $PSCity ||
		// ''' ''' ||
		// $PSState||
		// ''' ''' ||
		// $PSZip ||
		// ''' ''' ||
		// $PSPhone||
		// ''' ''' ||
		// $PSBusiness_Phone ||
		// ''' ''' ||
		// $PSCountry ||
		// ''' ''' ||
		// $PSGender ||
		// ''' ''' ||
		// $PSMarITAL_StatUS ||
		// ''' ''' ||
		// $PSEthnic_Group ||
		// ''' ''' ||
		// $PSChg_Dt ||
		// ''' ''' ||
		// $PSBirthDate ||
		// ''' ''' ||
		// $PSStat_date ||
		// ''' ''' ||
		// $PSStart_date ||
		// ''' ''' ||
		// $PSDriver_Lic ||
		// ''' ''' ||
		// $PSDlstate ||
		// ''' ''' ||
		// $PSContact_Name ||
		// ''' ''' ||
		// $PSEmer_Phn ||
		// ''' ''' ||
		// $PSRelation ||
		// ''' ''' ||
		// $PSEmpl_Spouse ||
		// ''' ''' ||
		// $PSReferral_Source ||
		// ''' ''' ||
		// $PSRecruit_Gp ||
		// ''' ''' ||
		// $PSRecruiter_Id ||
		// ''' ''' ||
		// $PSSpecific_Refer_Src ||
		// ''' ''' ||
		// $PSCollege ||
		// ''' ''' ||
		// $PSGradYr ||
		// ''' ''' ||
		// $PSMajor||
		// ''' ''' || !dshen 01/12/2012
		// $PS_NID_COUNTRY5 || !dshen 01/12/2012
		// ''')" '
//		String paramaterString = "'" + processParameters.getEmployeeId() + "' "
//				+ "'" + processParameters.getTerminationMonth() + "' "
//				+ "'" + processParameters.getTerminationDay() + "' "
//				+ "'" + processParameters.getTerminationYear() + "' "
//				+ "'" + processParameters.getRehireMonth() + "' "
//				+ "'" + processParameters.getRehireDay() + "' "
//				+ "'" + processParameters.getRehireYear() + "' "
//				+ "'" + processParameters.getVoluntaryOrInvoluntary() + "' "
//				+ "'" + processParameters.getTerminationCode() + "' "
//				+ "'" + processParameters.getAuditOperatorId() + "' "
//				+ "'" + processParameters.getTerminationReason() + "'";
//		return paramaterString;
		return null;
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

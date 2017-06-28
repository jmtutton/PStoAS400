package erd.controller;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.DemographicChangeParameters;

import erd.model.PsDriversLicense;
import erd.model.PszTriggerEmployee;

/**
 * ZHRI105A - Demographic Change
 * @author John Tutton john@tutton.net
 *
 */

public class EmployeeDemographicChange {

	public String HR05_processMain(PszTriggerEmployee trigger, CommonParameters commonParameters) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * @param demographicChangeParameters
	 */
	private String composeParameterStringForHrz105AProcess(DemographicChangeParameters demographicChangeParameters) {
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
//		String paramaterString = "'" + demographicChangeParameters.getEmployeeId() + "' "
//				+ "'" + demographicChangeParameters.getTerminationMonth() + "' "
//				+ "'" + demographicChangeParameters.getTerminationDay() + "' "
//				+ "'" + demographicChangeParameters.getTerminationYear() + "' "
//				+ "'" + demographicChangeParameters.getRehireMonth() + "' "
//				+ "'" + demographicChangeParameters.getRehireDay() + "' "
//				+ "'" + demographicChangeParameters.getRehireYear() + "' "
//				+ "'" + demographicChangeParameters.getVoluntaryOrInvoluntary() + "' "
//				+ "'" + demographicChangeParameters.getTerminationCode() + "' "
//				+ "'" + demographicChangeParameters.getAuditOperatorId() + "' "
//				+ "'" + demographicChangeParameters.getTerminationReason() + "'";
//		return paramaterString;
		return null;
	}

	/**
	 * HR05-Get-Drivers-Lic from ZHRI105A.SQC
	 * This routine gets the driver license number and state and stores them in the legacy system format.
	 * @param employeeId
	 * @return PsDriversLicense record
	 */
	public PsDriversLicense HR05_getDriversLic(String employeeId) {
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

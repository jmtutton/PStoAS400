package erd.controller;

import erd.model.ProcessParameters.CommonParameters;
import erd.model.ProcessParameters.NewHireProcessParameters;

/**
 * ZHRI206A - Contingent Employee and Multiple EID Rehire
 * @author John Tutton john@tutton.net
 *
 */
public class NonPersonRehire {
	
	public String processNonPersonRehire(CommonParameters commonParameters) {
		System.out.println("********** HR206_processMain()");
		commonParameters.setPoiFlag(true);
		NewHireProcessParameters processParameters = fetchProcessParameters(commonParameters);
		composeParameterString(processParameters);
		return null;
	}
	
	private NewHireProcessParameters fetchProcessParameters(CommonParameters commonParameters) {
		return null;
	}
		
	private String composeParameterString(NewHireProcessParameters commonParameters) {
		System.out.println("********** composeParameterString()");
		return null;
		
	}

}

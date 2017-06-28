package erd.controller;

import erd.model.PszTriggerNonPerson;
import erd.model.ProcessParameters.CommonParameters;

/**
 * ZHRI206A - Contingent Employee and Multiple EID Rehire
 * @author John Tutton john@tutton.net
 *
 */
public class NonPersonRehire {
	
	public String HR206_processMain(PszTriggerNonPerson trigger, CommonParameters commonParameters) {
		System.out.println("********** HR206_processMain()");
		commonParameters.setPoiFlag(true);
		commonParameters = HR206_initializeFields(commonParameters);
		HR206_callSystem(commonParameters);
		return null;
	}
	
	private CommonParameters HR206_initializeFields(CommonParameters commonParameters) {
		System.out.println("********** HR206_initializeFields");
		return commonParameters;
		
	}

	private String HR206_callSystem(CommonParameters commonParameters) {
		System.out.println("********** HR206_callSystem()");
		return null;
		
	}

}

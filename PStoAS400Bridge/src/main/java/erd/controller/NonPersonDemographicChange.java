package erd.controller;

import erd.model.ProcessParameters;
import erd.model.PszTriggerNonPerson;
import erd.model.Zhri100aFields;

/**
 * ZHRI205A - Contingent Employee and Multiple EID Demographic Change
 * @author John Tutton john@tutton.net
 *
 */

public class NonPersonDemographicChange {
	
	PszTriggerNonPerson trigger;
	Zhri100aFields zhri100aFields;
	ProcessParameters processParameters;

	public NonPersonDemographicChange(PszTriggerNonPerson trigger, Zhri100aFields zhri100aFields) {
		System.out.println("********** NonPersonDemographicChange()");
		this.trigger = trigger;
		this.zhri100aFields = zhri100aFields;
		System.out.println("\n" + trigger.toString() + "\n");
		System.out.println(zhri100aFields.toString() + "\n");
	}

	public String HR205_processMain() {
		System.out.println("********** HR205_processMain()");
		zhri100aFields.setPoiFlag(true);
		HR205_initializeFields();
		HR205_callSystem(zhri100aFields);
		return null;
	}
	
	private void HR205_initializeFields() {
		System.out.println("********** HR205_initializeFields");
		
	}

	private String HR205_callSystem(Zhri100aFields zhri100aFields) {
		System.out.println("********** HR205_callSystem()");
		return null;
		
	}

}

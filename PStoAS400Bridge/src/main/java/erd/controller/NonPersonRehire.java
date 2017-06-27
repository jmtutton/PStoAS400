package erd.controller;

import erd.model.ProcessParameters;
import erd.model.PszTriggerNonPerson;
import erd.model.Zhri100aFields;

/**
 * ZHRI206A - Contingent Employee and Multiple EID Rehire
 * @author John Tutton john@tutton.net
 *
 */
public class NonPersonRehire {
	
	PszTriggerNonPerson trigger;
	Zhri100aFields zhri100aFields;
	ProcessParameters processParameters;

	public NonPersonRehire(PszTriggerNonPerson trigger, Zhri100aFields zhri100aFields) {
		System.out.println("********** NonPersonRehire()");
		this.trigger = trigger;
		this.zhri100aFields = zhri100aFields;
		System.out.println("\n" + trigger.toString() + "\n");
		System.out.println(zhri100aFields.toString() + "\n");
	}

	public String HR206_processMain() {
		System.out.println("********** HR206_processMain()");
		zhri100aFields.setPoiFlag(true);
		HR206_initializeFields();
		HR206_callSystem(zhri100aFields);
		return null;
	}
	
	private void HR206_initializeFields() {
		System.out.println("********** HR206_initializeFields");
		
	}

	private String HR206_callSystem(Zhri100aFields zhri100aFields) {
		System.out.println("********** HR206_callSystem()");
		return null;
		
	}

}

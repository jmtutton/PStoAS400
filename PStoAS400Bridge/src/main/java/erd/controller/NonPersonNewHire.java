package erd.controller;

import erd.model.ProcessParameters;
import erd.model.PszTriggerNonPerson;
import erd.model.Zhri100aFields;

/**
 * ZHRI201A - Contingent Employee and Multiple EID New Hire
 * @author John Tutton john@tutton.net
 *
 */
public class NonPersonNewHire {
	
	PszTriggerNonPerson trigger;
	Zhri100aFields zhri100aFields;
	ProcessParameters processParameters;

	public NonPersonNewHire(PszTriggerNonPerson trigger, Zhri100aFields zhri100aFields) {
		System.out.println("********** NonPersonNewHire()");
		this.trigger = trigger;
		this.zhri100aFields = zhri100aFields;
		System.out.println("\n" + trigger.toString() + "\n");
		System.out.println(zhri100aFields.toString() + "\n");
	}

	public String HR201_processMain() {
		System.out.println("********** HR201_processMain()");
		zhri100aFields.setPoiFlag(true);
		HR201_initializeFields();
		HR201_callSystem(zhri100aFields);
		return null;
	}
	
	private void HR201_initializeFields() {
		System.out.println("********** HR201_initializeFields");
		
	}

	private String HR201_callSystem(Zhri100aFields zhri100aFields) {
		System.out.println("********** HR201_callSystem()");
		return null;
		
	}

}

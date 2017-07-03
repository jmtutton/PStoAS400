package erd;

import java.util.Queue;

import erd.model.PszTriggerEmployee;
import erd.model.PszTriggerInterface;
import erd.model.PszTriggerNonPerson;

public interface DataExportInterface {

	/*
	 * Handles the exporting of gathered Employee data 
	 * **/
	Object Export(Object exportData, PszTriggerEmployee employeeTrigger);

	/*
	 * Handles the exporting of gathered NonPerson data
	 * **/
	Object Export(Object exportData, PszTriggerNonPerson nonPersonTrigger);

	/*
	 * Handles the exporting of gathered NonPerson data
	 * **/
	Object Export(Queue<PszTriggerInterface> triggers);

}
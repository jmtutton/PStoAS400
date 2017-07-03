package erd;

import java.util.Queue;

import erd.model.PszTriggerEmployee;
import erd.model.PszTriggerInterface;
import erd.model.PszTriggerNonPerson;

public interface DataImportInterface {

	Object Import(PszTriggerEmployee employeeTrigger);

	Object Import(PszTriggerNonPerson nonPersonTrigger);
	
	Object Import(Queue<PszTriggerInterface> triggers);

}
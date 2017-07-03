package erd;

import java.util.Queue;

import erd.model.PszTriggerEmployee;
import erd.model.PszTriggerInterface;
import erd.model.PszTriggerNonPerson;

/*
 * Data Export Module
 * 
 * */
public class DataExporter implements DataExportInterface {

	/* (non-Javadoc)
	 * @see erd.DataExportInterface#Export(java.lang.Object, erd.model.PszTriggerEmployee)
	 */
	@Override
	public Object Export(Object exportData, PszTriggerEmployee employeeTrigger){
		if(exportData == null){
			return null;
		}
		
		
		try{
			//Attempt Export
			
			//Update trigger mark Export Successful
			
		}catch(Exception ex){
			
		}
					
		//return result
		return employeeTrigger;
	}
	
	/* (non-Javadoc)
	 * @see erd.DataExportInterface#Export(erd.model.PszTriggerNonPerson)
	 */
	@Override
	public Object Export(Object exportData,PszTriggerNonPerson nonPersonTrigger){
		if(exportData == null){
			return null;
		}
		
		
		try{
			//Attempt Export
			
			//Update trigger mark Export Successful
			
		}catch(Exception ex){
			
		}
					
		//return result
		return nonPersonTrigger;
	}
	
	public Object Export(Queue<PszTriggerInterface> triggers){
		return null;
	}
}

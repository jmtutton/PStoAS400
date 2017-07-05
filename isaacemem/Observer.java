package erd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import erd.model.PszTriggerEmployee;
import erd.model.PszTriggerNonPerson;
import erd.model.PszTriggerInterface;

/*
 * Monitors the two trigger tables for changes and kickstarts the appropriate process for each change found
 * */
public class Observer {
	
	int BATCH_SIZE = 50;
	
	private static Observer instance;
	
	@Autowired
	private DataImportInterface dataImporter;
	
	@Autowired
	private DataExportInterface dataExporter;
	
	private List<String> getEmployeeProcessNames(){
		List<String> array = new ArrayList<String>();
		array.add("ZHRI101A"); //Process to hire employee
		array.add("ZHRI102A"); //Process to terminate an employee
		array.add("ZHRI104A"); //Process for job status change
		array.add("ZHRI105A"); //Process for demographics change
		array.add("ZHRI109A"); //Process for group transfer
		array.add("ZHRI107A"); //Process for converting dates
		return array;
	}
	
	private List<String> getNonEmployeeProcessNames(){
		List<String> array = new ArrayList<String>();		
		array.add("ZHRI201A"); //Process POI/Alt EMP hire/rehire
		array.add("ZHRI202A"); //Process POI/Alt EMP term
		array.add("ZHRI205A"); //Process POI/Alt EMP changes
		return array;
	}
	
	private List<String> getTriggerTables(){
		List<String> array = new ArrayList<String>();		
		array.add("PS_ZHRT_INTTRIGGER"); 
		array.add("PS_ZHRT_ALTTRIGGER");
		return array;
	}
	
	private Observer(DataImportInterface dataImporter, DataExportInterface dataExporter) {
        System.out.println("Observer started");
        
        //initialize key fields
        this.dataImporter = dataImporter;
        this.dataExporter = dataExporter;
        
        //Start trigger check        
        Start(getTriggerTables());
		
	}
	

	public void CheckForAndProcessEmployeeTriggers(){
		
	    //Connect to the trigger db
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
				
		//Get number of new triggers
		Long noOfNewTriggers = em.createQuery("SELECT COUNT(*) FROM PS_ZHRT_INTTRIGGER WHERE TASK_FLAG ='P'", Long.class).getSingleResult();
		
		if(noOfNewTriggers == 0){
			//No new trigger, end
			return;
		}
		
		long batches = noOfNewTriggers/BATCH_SIZE;
		
		//Get triggers in batches
		for(long i=0; i < batches; i++){
			
			try {
		    	List<PszTriggerEmployee> resultList = em.createQuery(
		    		    "SELECT t FROM PszTriggerEmployee t WHERE t.sequenceNumber > :sequenceNumber LIMIT :noOfRows", PszTriggerEmployee.class)
		    		    .setParameter("TASK_FLAG", "P")
		    		    .setParameter("noOfRows", BATCH_SIZE)
		    		    .getResultList();		    	
		    	
		    	//Process each trigger
				for(int y=0; y<resultList.size(); y++){
					PszTriggerEmployee trigger = resultList.get(y);
					
					if(trigger == null){
						break;
					}
					
					DataImportInterface di = new DataImporter();
					di.Import(trigger);
				}
		    } 
		    catch (Exception e) {
		       e.printStackTrace();
		    }
			
			
			
		}
	}
	

	public void Start(List<String> tableNames){
		
	    //Connect to the trigger db
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		
		if(tableNames== null || tableNames.size() < 0){
			return;
		}
		
		Queue<PszTriggerInterface> q = new LinkedList<PszTriggerInterface>();
		
		for(int counter =0; counter <tableNames.size(); counter ++){			
		
			String tableName = tableNames.get(counter);
			
			if(tableName == null)
			{
				break;
			}
			
			//Get number of new triggers
			Long noOfNewTriggers = em.createQuery("SELECT COUNT(*) FROM " + tableName + " WHERE TASK_FLAG ='P'", Long.class).getSingleResult();
		
			if(noOfNewTriggers == 0){
				//No new trigger, end
				break;
			}
		
			long batches = 1;
		
			if(BATCH_SIZE>noOfNewTriggers){
				batches = noOfNewTriggers/BATCH_SIZE;
			}
		
			//Get triggers in batches
			for(long i=0; i < batches; i++){
			
				try 
				{
					List<PszTriggerNonPerson> resultList = em.createQuery(
		    		    "SELECT t FROM PS_ZHRT_ALTTRIGGER t WHERE t.sequenceNumber > :sequenceNumber LIMIT :noOfRows", PszTriggerNonPerson.class)
		    		    .setParameter("TASK_FLAG", "P")
		    		    .setParameter("noOfRows", BATCH_SIZE)
		    		    .getResultList();		    	
					
					q.addAll(resultList);
					
					//Hand over the triggers to the Data Importer					
					this.dataImporter.Import(q);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
     * Create a static method to get instance.
     */
    public static Observer getInstance(DataImportInterface dataImporter, DataExportInterface dataExporter) {
        if (instance == null) {
        	instance = new Observer(dataImporter, dataExporter);
        }
        return instance;
    }
}

package erd.model;

import java.util.HashMap;
import java.util.List;

public class AS400Package {

	String processName;
	List<String> parameterList; //ordered list of parameter names
	HashMap<String, String> parameterMap; //name value pairs of parameters
	
	public AS400Package(String processName) {
		super();
		this.processName = processName;
	}
	
	public AS400Package(String processName, List<String> parameterList, HashMap<String, String> parameterMap) {
		super();
		this.processName = processName;
		this.parameterList = parameterList;
		this.parameterMap = parameterMap;
	}
	
}

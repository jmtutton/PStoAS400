package erd.model;

import java.util.Date;

public class Zhri100aFields {
	public String activeDirectoryHomePath; //$AD_HOME 
	public String as400Library; //$Library
	public String dbName;
	public String oracleSystemId; //$ORACLE_SID
	public String peopleSoftHomePath; //$PS_HOME
	public String remoteAdServerName; //$RMTNTADSVR
	public String remoteExecScript; //$RexecScript
	public String remoteServerName; //$RMTSVR

	public String auditOperatorId; //$PSAuditOperId
	public String operatorId; //$PSOperId
	public String employeeId; //PSEmpl
	public Integer indexNumber;
	public Date effectiveDate;
	
	public Boolean poiFlag; //$PoiFlag
//	public Boolean runFlag; //#run_flag
	public Boolean wrkCriticalFlag; //$WrkCriticalFlag
	
	//from ZHRI100A.Call-Programs  //probably not used
	public String adActionCode;
	public String adLegacyOperatorId;
	
	public String errorProgramParameter;
	public String errorMessageParameter;
	
	public static AS400Package as400Package;
}

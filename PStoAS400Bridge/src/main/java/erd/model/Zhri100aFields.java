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
	public Boolean criticalFlag; //$WrkCriticalFlag
	
	//from ZHRI100A.Call-Programs  //probably not used
	public String adActionCode;
	public String adLegacyOperatorId;
	
	public String errorProgramParameter;
	public String errorMessageParameter;
	
	public static AS400Package as400Package;
	
	@Override
	public String toString() {
		return "/nZhri100aFields:/n"
				+ "activeDirectoryHomePath: " + activeDirectoryHomePath + "\n" //$AD_HOME 
				+ "as400Library: " + as400Library + "\n" //$Library
				+ "dbName: " + dbName + "\n"
				+ "oracleSystemId: " + oracleSystemId + "\n" //$ORACLE_SID
				+ "peopleSoftHomePath: " + peopleSoftHomePath + "\n" //$PS_HOME
				+ "remoteAdServerName: " + remoteAdServerName + "\n" //$RMTNTADSVR
				+ "remoteExecScript: " + remoteExecScript + "\n" //$RexecScript
				+ "remoteServerName: " + remoteServerName + "\n" //$RMTSVR
				+ "auditOperatorId: " + auditOperatorId + "\n" //$PSAuditOperId
				+ "operatorId: " + operatorId + "\n" //$PSOperId
				+ "employeeId: " + employeeId + "\n" //PSEmpl
				+ "indexNumber: " + indexNumber + "\n"
				+ "effectiveDate: " + effectiveDate + "\n"
				+ "poiFlag: " + poiFlag + "\n"
//				+ "runFlag: " + runFlag + "\n" //#run_flag
				+ "criticalFlag: " + criticalFlag + "\n" //$WrkCriticalFlag
				+ "adActionCode: " + adActionCode + "\n"
				+ "adLegacyOperatorId: " + adLegacyOperatorId + "\n"
				+ "errorProgramParameter: " + errorProgramParameter + "\n"
				+ "errorMessageParameter: " + errorMessageParameter + "\n";
		
	}
}

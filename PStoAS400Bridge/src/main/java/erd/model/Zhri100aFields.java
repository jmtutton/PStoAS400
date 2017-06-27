package erd.model;

import java.math.BigDecimal;
import java.util.Date;

public class Zhri100aFields {
//	private String activeDirectoryHomePath; //$AD_HOME 
	private String as400Library; //$Library
	private String dbName;
	private String oracleSystemId; //$ORACLE_SID
	private String peopleSoftHomePath; //$PS_HOME
	private String remoteAdServerName; //$RMTNTADSVR
	private String remoteExecScript; //$RexecScript
	private String remoteServerName; //$RMTSVR

	private String processName;
	private String completionStatus;
	private String auditOperatorId; //$PSAuditOperId
	private String operatorId; //$PSOperId
	private String employeeId; //PSEmpl
	private BigDecimal indexNumber;
	private Date effectiveDate;
	
	private Boolean poiFlag; //$PoiFlag
//	private Boolean runFlag; //#run_flag
	private Boolean criticalFlag = false; //$WrkCriticalFlag
	
	//from ZHRI100A.Call-Programs  //probably not used
	private String actionCode;
	private String legacyOperatorId;
	
	private String errorProgramParameter;
	private String errorMessageParameter;
	private String errorDateParameter;
	private String errorTimeParameter;
	
	private static AS400Package as400Package;
	
//	public String getActiveDirectoryHomePath() {
//		return activeDirectoryHomePath != null ? activeDirectoryHomePath.trim() : activeDirectoryHomePath;
//	}
//	public void setActiveDirectoryHomePath(String activeDirectoryHomePath) {
//		this.activeDirectoryHomePath = activeDirectoryHomePath != null ? activeDirectoryHomePath.trim() : activeDirectoryHomePath;
//	}
	public String getAs400Library() {
		return as400Library != null ? as400Library.trim() : as400Library;
	}
	public void setAs400Library(String as400Library) {
		this.as400Library = as400Library != null ? as400Library.trim() : as400Library;
	}
	public String getDbName() {
		return dbName != null ? dbName : dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName != null ? dbName : dbName;
	}
	public String getOracleSystemId() {
		return oracleSystemId != null ? oracleSystemId.trim() : oracleSystemId;
	}
	public void setOracleSystemId(String oracleSystemId) {
		this.oracleSystemId = oracleSystemId != null ? oracleSystemId.trim() : oracleSystemId;
	}
	public String getPeopleSoftHomePath() {
		return peopleSoftHomePath != null ? peopleSoftHomePath.trim() : peopleSoftHomePath;
	}
	public void setPeopleSoftHomePath(String peopleSoftHomePath) {
		this.peopleSoftHomePath = peopleSoftHomePath != null ? peopleSoftHomePath.trim() : peopleSoftHomePath;
	}
	public String getRemoteAdServerName() {
		return remoteAdServerName != null ? remoteAdServerName.trim() : remoteAdServerName;
	}
	public void setRemoteAdServerName(String remoteAdServerName) {
		this.remoteAdServerName = remoteAdServerName != null ? remoteAdServerName.trim() : remoteAdServerName;
	}
	public String getRemoteExecScript() {
		return remoteExecScript;
	}
	public void setRemoteExecScript(String remoteExecScript) {
		this.remoteExecScript = remoteExecScript;
	}
	public String getRemoteServerName() {
		return remoteServerName != null ? remoteServerName.trim() : remoteServerName;
	}
	public void setRemoteServerName(String remoteServerName) {
		this.remoteServerName = remoteServerName != null ? remoteServerName.trim() : remoteServerName;
	}
	public String getAuditOperatorId() {
		return auditOperatorId != null ? auditOperatorId.trim() : auditOperatorId;
	}
	public void setAuditOperatorId(String auditOperatorId) {
		this.auditOperatorId = auditOperatorId != null ? auditOperatorId.trim() : auditOperatorId;
	}
	public String getOperatorId() {
		return operatorId != null ? operatorId.trim() : operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId != null ? operatorId.trim() : operatorId;
	}
	public String getEmployeeId() {
		return employeeId != null ? employeeId.trim() : employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId != null ? employeeId.trim() : employeeId;
	}
	public BigDecimal getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(BigDecimal indexNumber) {
		this.indexNumber = indexNumber;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Boolean getPoiFlag() {
		return poiFlag;
	}
	public void setPoiFlag(Boolean poiFlag) {
		this.poiFlag = poiFlag;
	}
	public Boolean getCriticalFlag() {
		return criticalFlag;
	}
	public void setCriticalFlag(Boolean criticalFlag) {
		this.criticalFlag = criticalFlag;
	}
	public String getActionCode() {
		return actionCode != null ? actionCode.trim() : actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode != null ? actionCode.trim() : actionCode;
	}
	public String getLegacyOperatorId() {
		return legacyOperatorId != null ? legacyOperatorId.trim() : legacyOperatorId;
	}
	public void setLegacyOperatorId(String legacyOperatorId) {
		this.legacyOperatorId = legacyOperatorId != null ? legacyOperatorId.trim() : legacyOperatorId;
	}
	public String getErrorProgramParameter() {
		return errorProgramParameter != null ? errorProgramParameter.trim() : errorProgramParameter;
	}
	public void setErrorProgramParameter(String errorProgramParameter) {
		this.errorProgramParameter = errorProgramParameter != null ? errorProgramParameter.trim() : errorProgramParameter;
	}
	public String getErrorMessageParameter() {
		return errorMessageParameter;
	}
	public void setErrorMessageParameter(String errorMessageParameter) {
		this.errorMessageParameter = errorMessageParameter;
	}
	public static AS400Package getAs400Package() {
		return as400Package;
	}
	public static void setAs400Package(AS400Package as400Package) {
		Zhri100aFields.as400Package = as400Package;
	}
	public String getProcessName() {
		return processName != null ? processName.trim() : processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName != null ? processName.trim() : processName;
	}
	public String getCompletionStatus() {
		return completionStatus != null ? completionStatus.trim() : completionStatus;
	}
	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus != null ? completionStatus.trim() : completionStatus;
	}

	public String getErrorDateParameter() {
		return errorDateParameter;
	}
	public void setErrorDateParameter(String errorDateParameter) {
		this.errorDateParameter = errorDateParameter;
	}
	public String getErrorTimeParameter() {
		return errorTimeParameter;
	}
	public void setErrorTimeParameter(String errorTimeParameter) {
		this.errorTimeParameter = errorTimeParameter;
	}
	@Override
	public String toString() {
		return "\nZhri100aFields:\n"
//				+ "activeDirectoryHomePath: " + activeDirectoryHomePath + "\n" //$AD_HOME 
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
				+ "actionCode: " + actionCode + "\n"
				+ "legacyOperatorId: " + legacyOperatorId + "\n"
				+ "errorProgramParameter: " + errorProgramParameter + "\n"
				+ "errorMessageParameter: " + errorMessageParameter + "\n";
		
	}
}

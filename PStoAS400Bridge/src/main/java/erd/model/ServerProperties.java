package erd.model;

public class ServerProperties {
	private static String activeDirectoryHomePath; //$AD_HOME 
	private static String as400Library; //$Library
	private static String dbName;
	private static String oracleSystemId; //$ORACLE_SID
	private static String peopleSoftHomePath; //$PS_HOME
	private static String remoteAdServerName; //$RMTNTADSVR
	private static String remoteExecScript; //$RexecScript
	private static String remoteServerName; //$RMTSVR
	private static Integer remoteServerPort = 22;
	private static String remoteServerUsername = "PSHRINT";
	private static String remoteServerPassword = "SMRHET01";
	
	public static String getActiveDirectoryHomePath() {
		return ServerProperties.activeDirectoryHomePath != null ? ServerProperties.activeDirectoryHomePath.trim() : ServerProperties.activeDirectoryHomePath;
	}
	public static void setActiveDirectoryHomePath(String activeDirectoryHomePath) {
		ServerProperties.activeDirectoryHomePath = activeDirectoryHomePath != null ? activeDirectoryHomePath.trim() : activeDirectoryHomePath;
	}
	public static String getAs400Library() {
		return ServerProperties.as400Library != null ? ServerProperties.as400Library.trim() : ServerProperties.as400Library;
	}
	public static void setAs400Library(String as400Library) {
		ServerProperties.as400Library = as400Library != null ? as400Library.trim() : as400Library;
	}
	public static String getDbName() {
		return dbName != null ? dbName : dbName;
	}
	public static void setDbName(String dbName) {
		ServerProperties.dbName = dbName != null ? dbName : dbName;
	}
	public static String getOracleSystemId() {
		return oracleSystemId != null ? oracleSystemId.trim() : oracleSystemId;
	}
	public static void setOracleSystemId(String oracleSystemId) {
		ServerProperties.oracleSystemId = oracleSystemId != null ? oracleSystemId.trim() : oracleSystemId;
	}
	public static String getPeopleSoftHomePath() {
		return peopleSoftHomePath != null ? peopleSoftHomePath.trim() : peopleSoftHomePath;
	}
	public static void setPeopleSoftHomePath(String peopleSoftHomePath) {
		ServerProperties.peopleSoftHomePath = peopleSoftHomePath != null ? peopleSoftHomePath.trim() : peopleSoftHomePath;
	}
	public static String getRemoteAdServerName() {
		return remoteAdServerName != null ? remoteAdServerName.trim() : remoteAdServerName;
	}
	public static void setRemoteAdServerName(String remoteAdServerName) {
		ServerProperties.remoteAdServerName = remoteAdServerName != null ? remoteAdServerName.trim() : remoteAdServerName;
	}
	public static String getRemoteExecScript() {
		return remoteExecScript;
	}
	public static void setRemoteExecScript(String remoteExecScript) {
		ServerProperties.remoteExecScript = remoteExecScript;
	}
	public static String getRemoteServerName() {
		return remoteServerName != null ? remoteServerName.trim() : remoteServerName;
	}
	public static void setRemoteServerName(String remoteServerName) {
		ServerProperties.remoteServerName = remoteServerName != null ? remoteServerName.trim() : remoteServerName;
	}
	
	public static String getRemoteServerUsername() {
		return remoteServerUsername;
	}
	public static void setRemoteServerUsername(String remoteServerUsername) {
		ServerProperties.remoteServerUsername = remoteServerUsername;
	}
	public static String getRemoteServerPassword() {
		return remoteServerPassword;
	}
	public static void setRemoteServerPassword(String remoteServerPassword) {
		ServerProperties.remoteServerPassword = remoteServerPassword;
	}
	public static String print() {
		return "\nServerProperties:\n"
	//			+ "activeDirectoryHomePath: " + ServerProperties.activeDirectoryHomePath + "\n" //$AD_HOME 
				+ "as400Library: " + ServerProperties.as400Library + "\n" //$Library
				+ "dbName: " + ServerProperties.dbName + "\n"
				+ "oracleSystemId: " + ServerProperties.oracleSystemId + "\n" //$ORACLE_SID
				+ "peopleSoftHomePath: " + ServerProperties.peopleSoftHomePath + "\n" //$PS_HOME
				+ "remoteAdServerName: " + ServerProperties.remoteAdServerName + "\n" //$RMTNTADSVR
				+ "remoteServerName: " + ServerProperties.remoteServerName + "\n" //$RexecScript
				+ "remoteServerUserName: " + ServerProperties.remoteServerUsername + "\n" //$RexecScript
				+ "remoteServerPassword: " + ServerProperties.remoteServerPassword + "\n"; //$RMTSVR
	}
	public static Integer getRemoteServerPort() {
		return remoteServerPort;
	}
	public static void setRemoteServerPort(Integer remoteServerPort) {
		ServerProperties.remoteServerPort = remoteServerPort;
	}

}

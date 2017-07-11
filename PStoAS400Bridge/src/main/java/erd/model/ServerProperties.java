package erd.model;

public class ServerProperties {
	private static String as400Library; //EHRHRMS06#
	private static String dbName; //PS90HRQA
	private static String dbUser; //ERDBRIDGE
	private static String dbPassword; //Tak3S0n$
	private static String oracleSystemId; //PS90HRQA
	private static String remoteServerHostName; //dev.corp.erac.com
	private static String remoteServerUsername; //PSHRINT
	private static String remoteServerPassword; //SMRHET01
	
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
	public static String getRemoteServerHostName() {
		return remoteServerHostName != null ? remoteServerHostName.trim() : remoteServerHostName;
	}
	public static void setRemoteServerHostName(String remoteServerHostName) {
		ServerProperties.remoteServerHostName = remoteServerHostName != null ? remoteServerHostName.trim() : remoteServerHostName;
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
	public static String getDbUser() {
		return dbUser;
	}
	public static void setDbUser(String dbUser) {
		ServerProperties.dbUser = dbUser;
	}
	public static String getDbPassword() {
		return dbPassword;
	}
	public static void setDbPassword(String dbPassword) {
		ServerProperties.dbPassword = dbPassword;
	}
	
	@Override
	public String toString() {
		return "\nServerProperties:\n"
				+ "as400Library: " + ServerProperties.as400Library + "\n"
				+ "dbName: " + ServerProperties.dbName + "\n"
				+ "oracleSystemId: " + ServerProperties.oracleSystemId + "\n"
				+ "remoteServerHostName: " + ServerProperties.remoteServerHostName + "\n"
				+ "remoteServerUserName: " + ServerProperties.remoteServerUsername + "\n"
				+ "remoteServerPassword: " + ServerProperties.remoteServerPassword + "\n";
	}
}

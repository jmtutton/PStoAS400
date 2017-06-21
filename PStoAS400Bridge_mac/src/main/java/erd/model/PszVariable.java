package erd.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_ZPTT_VARIABLES database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZPTT_VARIABLES")
@NamedQuery(name="PszVariable.findAll", query="SELECT p FROM PszVariable p")
public class PszVariable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PRCSNAME", nullable=false, length=12)
	private String processName;

	@Column(name="DBNAME", nullable=false, length=8)
	private String dbName;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="VARIABLE_NAME", nullable=false, length=15)
	private String variableName;

	@Column(name="ZPTF_VARIABLE_VAL", nullable=false, length=100)
	private String variableValue;

	public PszVariable() {
	}

	public String getDbName() {
		return this.dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public Timestamp getLastUpdatedDateAndTime() {
		return this.lastUpdatedDateAndTime;
	}

	public void setLastUpdatedDateAndTime(Timestamp lastUpdatedDateAndTime) {
		this.lastUpdatedDateAndTime = lastUpdatedDateAndTime;
	}

	public String getLastUpdatedUserId() {
		return this.lastUpdatedUserId;
	}

	public void setLastUpdatedUserId(String lastUpdatedUserId) {
		this.lastUpdatedUserId = lastUpdatedUserId;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getVariableName() {
		return this.variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableValue() {
		return this.variableValue;
	}

	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

	/**
	 * Replaces Get-Variable from ZHRI100A.SQR
	 * Gets the variables from PS_ZPTT_VARIABLES       
	 */
	public static String findVariableValueByProcessNameAndDbNameAndVariableName(String processName, String dbName, String variableName) {
//		BEGIN-SELECT
//			VAR.ZPTF_VARIABLE_VAL
//			MOVE &VAR.ZPTF_VARIABLE_VAL to $PSZPTT_VARIABLE_VAL
//			FROM  PS_ZPTT_VARIABLES VAR WHERE VAR.PRCSNAME = 'ZHRI100A'
//				AND VAR.DBNAME = (SELECT dbname FROM PSDBOWNER)
//				AND VAR.VARIABLE_NAME = $Variable_Needed
//		END-SELECT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		
	    try {
	    	List<String> resultList = em.createQuery("SELECT p.variableValue FROM PszVariable p "
	    				+ "WHERE UPPER(TRIM(p.processName)) = :processName "
	    				+ "AND UPPER(TRIM(p.dbName)) = :dbName "
	    				+ "AND UPPER(TRIM(p.variableName)) = :variableName ",
	    				String.class)
	    		    .setParameter("processName", processName.toUpperCase())
	    		    .setParameter("dbName", dbName.toUpperCase())
	    		    .setParameter("variableName", variableName.toUpperCase())
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
	    		return resultList.get(0).trim();
	    	}
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	    } 
	    return null;	
	}
}
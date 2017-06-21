package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PSDBOWNER database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PSDBOWNER")
@NamedQuery(name="PsDbOwner.findAll", query="SELECT p FROM PsDbOwner p")
public class PsDbOwner implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "DBNAME")
	private String dbName;
	
	@Column(name = "OWNERID")
	private String ownerId;

	public PsDbOwner() {
		super();
	}

	public String getDbName() {
		return dbName;
	}

	public String getOwnerId() {
		return ownerId;
	}
	
	public static String findDbName() {
		//VAR.DBNAME = (SELECT dbname FROM PSDBOWNER)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		
	    try {
	    	List<String> resultList = em.createQuery("SELECT p.dbName FROM PsDbOwner p", String.class)
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

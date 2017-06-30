package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: HR036P
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="HR006P")
@NamedQuery(name="HR006P.findAll", query="SELECT h FROM HR006P h")
public class HR006P implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "HMREMP") //five digit string left padded with zeros
	private String employeeId;
	
	@Column(name = "HMRNAM")
	private String employeeName;
	
	@Column(name = "HMREM#")
	private Integer employeeNumber;
	
//"HMRCCD", "HMRNID", "HMREMP", "HMRNAM", "HMRATN", "HMRSTR", "HMRCTY", "HMRST", "HMRWRK", "HMRMGE", "HMRENM", "HMREPH", "HMRERL", "HMRTTL", "HMRGPS", "HMRRSC", "HMRCCL", "HMRTSC", "HMRTSR", "HMRTRC", "HMRRSL", "HMRVOI", "HMROVR", "HMRNXA", "HMRDLN", "HMRDLS", "HMRGND", "HMRBDT", "HMRGP", "HMRBRN", "HMRRGN", "HMRHPH", "HMROPH", "HMRRSN", "HMRGYR", "HMRMJR", "HMRRCE", "HMRZIP", "HMRNKN", "HMRHDT", "HMRLTD", "HMRCTZ", "HMRHIR", "HMRSPS", "HMRRHR", "HMRREI", "HMRCTC", "HMRDPT", "HMRPOS", "HMRLVL", "HMRJSC", "HMRWKS", "HMRUNF", "HMREM#", "HMRADT", "HMRPGM", "HMREMC"	
	
	public HR006P() {
		super();
	}

	private String getEmployeeName() {
		return employeeName;
	}

	private void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;		
	}

	private String getEmployeeId() {
		return employeeId;
	}

	private void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;		
	}

	/**
	 * ZHRI100A.Get-Legacy-OprId
	 * Formulates legacy OprId from HR036P where HR036P.H36EM# = #wrk_emplid and HR036P.H36INX = #indexNum UNION
	 */
	public static HR006P zhri100AGetLegacyOprId(String employeeId, Boolean isPoi, Integer indexNumber) {
//		LET $LegEmplId = ''
//		String legacyEmployeeId = "";
//		MOVE $Wrk_Emplid to #wrk_emplid
//		IF $PoiFlag = 'N'
		if(!isPoi) {
//			MOVE 0 to #indexNum
			indexNumber = 0;
//		END-IF
		}
//		Begin-Select
//		HR036P.H36NAM
//		HR036P.H36EMP
//		HR036P.H36EM#
//	  	!This IF statement and OR part of Select is a workaround to some problem in v8 (gateway and new version of SQR). 
//		!The Select was hanging if it couldn't find a match in HR036P, so the Select assures that the Select always finds a match.
//	  	LET #WRK_CHR36_H36EM_NUM = &HR036P.H36EM#
//		Integer WRK_CHR36_H36EM_NUM;
//	  	IF #WRK_CHR36_H36EM_NUM = #wrk_emplid
//	    	LET $LegEmpName = &HR036P.H36NAM
//	    	DO Format-Employee-Name
//	    	LET $LegEmplid = &HR036P.H36EMP
//	    	LET $LegEmplid = substr($LegEmplid,1,5)
//	    	IF (#indexNum = 0 and $PoiFlag = 'Y') OR $PoiFlag = 'N'
//	    		DO Insert-OprId
//	    	ELSE
//	    		IF (#indexNum <> 0 AND $PoiFlag = 'Y')
//	        		DO Update-OprId
//	      		END-IF
//	    	END-IF  
//		END-IF    !#WRK_CHR36_H36EM_NUM = #wrk_emplid
//		FROM HR036P
//		WHERE HR036P.H36EM# = #wrk_emplid
//		AND HR036P.H36INX = #indexNum 
//		UNION
//		SELECT
//			' ', ' ' , 9999999999
//		FROM DUAL
//		End-Select
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<HR006P> resultList = em.createQuery(
	    		    "SELECT h FROM HR036P t "
	    		    		+ "WHERE h.employeeId = :employeeId"
	    		    		+ "AND h.indexNumber = :indexNumber", HR006P.class)
	    		    .setParameter("employeeId", employeeId)
	    		    .setParameter("indexNumber", indexNumber)
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		HR006P hr036P = resultList.get(0);
		    	if(hr036P.getEmployeeName() != null && !hr036P.getEmployeeName().isEmpty()) {
		    		hr036P.setEmployeeName(erd.StringUtil.formatLegacyEmployeeNameToPeopleSoftEmployeeName(hr036P.getEmployeeName()));
	    		}
//		    	LET $LegEmplid = substr($LegEmplid,1,5)
		    	if(hr036P.getEmployeeId() != null && !hr036P.getEmployeeId().isEmpty()) {
		    		hr036P.setEmployeeId(hr036P.getEmployeeId().substring(0,5));
	    		}
//		    	IF (#indexNum = 0 and $PoiFlag = 'Y') OR $PoiFlag = 'N'
//	    			DO Insert-OprId
//	    		ELSE
//	    			IF (#indexNum <> 0 AND $PoiFlag = 'Y')
//	        			DO Update-OprId
//	      			END-IF
//	    		END-IF  
	    		return hr036P;
	    	}
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	    	em.close();
	    }
		return null;
	}
   
}

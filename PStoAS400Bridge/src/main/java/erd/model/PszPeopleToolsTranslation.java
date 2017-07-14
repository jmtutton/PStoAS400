package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the PS_ZPTT_XLAT_TBL database table.
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ZPTT_XLAT_TBL")
@NamedQuery(name="PszPeopleToolsTranslation.findAll", query="SELECT p FROM PszPeopleToolsTranslation p")
public class PszPeopleToolsTranslation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ZPTF_INPUT_01")
	private String input01;

	@Column(name="ZPTF_INPUT_02")
	private String input02;

	@Column(name="ZPTF_INPUT_03")
	private String input03;

	@Column(name="ZPTF_INPUT_04")
	private String input04;

	@Column(name="ZPTF_INPUT_05")
	private String input05;

	@Column(name="ZPTF_INPUT_06")
	private String input06;

	@Column(name="ZPTF_OUTPUT_01")
	private String output01;

	@Column(name="ZPTF_OUTPUT_02")
	private String output02;

	@Column(name="ZPTF_OUTPUT_03")
	private String output03;

	@Column(name="ZPTF_OUTPUT_04")
	private String output04;

	@Column(name="ZPTF_OUTPUT_05")
	private String output05;

	@Column(name="ZPTF_OUTPUT_06")
	private String output06;

	@Column(name="ZPTF_OUTPUT_07")
	private String output07;

	@Column(name="ZPTF_OUTPUT_08")
	private String output08;

	@Column(name="COMMENTS_254")
	private String comments;

	@Column(name="STATUS")
	private String status;

	public PszPeopleToolsTranslation() {
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInput01() {
		return this.input01;
	}

	public void setInput01(String input01) {
		this.input01 = input01;
	}

	public String getInput02() {
		return this.input02;
	}

	public void setInput02(String input02) {
		this.input02 = input02;
	}

	public String getInput03() {
		return this.input03;
	}

	public void setInput03(String input03) {
		this.input03 = input03;
	}

	public String getInput04() {
		return this.input04;
	}

	public void setInput04(String input04) {
		this.input04 = input04;
	}

	public String getInput05() {
		return this.input05;
	}

	public void setInput05(String input05) {
		this.input05 = input05;
	}

	public String getInput06() {
		return this.input06;
	}

	public void setInput06(String input06) {
		this.input06 = input06;
	}

	public String getOutput01() {
		return this.output01;
	}

	public void setOutput01(String output01) {
		this.output01 = output01;
	}

	public String getOutput02() {
		return this.output02;
	}

	public void setOutput02(String output02) {
		this.output02 = output02;
	}

	public String getOutput03() {
		return this.output03;
	}

	public void setOutput03(String output03) {
		this.output03 = output03;
	}

	public String getOutput04() {
		return this.output04;
	}

	public void setOutput04(String output04) {
		this.output04 = output04;
	}

	public String getOutput05() {
		return this.output05;
	}

	public void setOutput05(String output05) {
		this.output05 = output05;
	}

	public String getOutput06() {
		return this.output06;
	}

	public void setOutput06(String output06) {
		this.output06 = output06;
	}

	public String getOutput07() {
		return this.output07;
	}

	public void setOutput07(String output07) {
		this.output07 = output07;
	}

	public String getOutput08() {
		return this.output08;
	}

	public void setOutput08(String output08) {
		this.output08 = output08;
	}

	/**
	 */
	public static String findOutput01ByInput01AndInput02(String input01, String input02) {
		//begin-select
		//XWER.ZPTF_OUTPUT_01
		//from PS_ZPTT_XLAT_TBL XWER
		//where XWER.ZPTF_INPUT_01 = 'TEMPMAST'
		//and XWER.ZPTF_INPUT_02 = 'LEGACY-DELAY-HRS'       
		//end-select
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<String> resultList = em.createQuery(
	    		    "SELECT UPPER(TRIM(p.output01)) FROM PszPeopleToolsTranslation p "
	    		    		+ "WHERE UPPER(TRIM(p.input01)) = TRIM(UPPER(:input01)) "
	    		    		+ "AND UPPER(TRIM(p.input02)) = TRIM(UPPER(:input02)) "
	    		    , String.class)
	    		    .setParameter("input01", input01)
	    		    .setParameter("input02", input02)
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		return resultList.get(0);
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

	public static PszPeopleToolsTranslation findActiveTempMastPositionByInput03(String input03) {
		//SELECT FROM PS_ZPTT_XLAT_TBL Z
		//WHERE Z.ZPTF_INPUT_01 = 'TEMPMAST' 
		//AND Z.ZPTF_INPUT_02 = 'POSITION' AND Z.STATUS = 'A' AND Z.ZPTF_INPUT_03 = $PoiCat
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PszPeopleToolsTranslation> resultList = em.createQuery(
	    		    "SELECT p FROM PszPeopleToolsTranslation p "
	    		    		+ "WHERE UPPER(TRIM(p.input01)) = 'TEMPMAST' "
	    		    		+ "AND UPPER(TRIM(p.input02)) = 'POSITION' "
	    		    		+ "AND p.status = 'A' "
	    		    		+ "AND UPPER(TRIM(p.input03)) = TRIM(UPPER(:input03)) "
	    		    , PszPeopleToolsTranslation.class)
	    		    .setParameter("input03", input03)
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		return resultList.get(0);
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

	/**
	 * @see HR201-Get-POI-LegPosNo
	 * @see HR201-Get-Alternate_Type
	 * @param input01
	 * @param input02
	 * @param input03
	 * @return PszPeopleToolsTranslation record
	 */
	public static PszPeopleToolsTranslation findActiveByInput01AndInput02AndInput03(String input01, String input02, String input03) {
		//SELECT FROM PS_ZPTT_XLAT_TBL Z
		//WHERE Z.ZPTF_INPUT_01 = 'TEMPMAST' 
		//AND Z.ZPTF_INPUT_02 = 'POSITION' AND Z.STATUS = 'A' AND Z.ZPTF_INPUT_03 = $PoiCat
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PszPeopleToolsTranslation> resultList = em.createQuery(
	    		    "SELECT p FROM PszPeopleToolsTranslation p "
	    		    		+ "WHERE UPPER(TRIM(p.input01)) = TRIM(UPPER(:input01)) "
	    		    		+ "AND UPPER(TRIM(p.input02)) = TRIM(UPPER(:input02)) "
	    		    		+ "AND UPPER(TRIM(p.input03)) = TRIM(UPPER(:input03)) "
	    		    		+ "AND p.status = 'A' "
	    		    , PszPeopleToolsTranslation.class)
	    		    .setParameter("input01", input01)
	    		    .setParameter("input02", input02)
	    		    .setParameter("input03", input03)
	    		    .getResultList();
	    	if(resultList != null && !resultList.isEmpty()) {
	    		return resultList.get(0);
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
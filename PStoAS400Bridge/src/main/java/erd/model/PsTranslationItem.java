package erd.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PSXLATITEM database table.
 * Translate Values
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="PSXLATITEM")
@NamedQuery(name="PsTranslationItem.findAll", query="SELECT p FROM PsTranslationItem p")
public class PsTranslationItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SYNCID", nullable=false)
	private Integer syncId;
	
	@Column(name="FIELDNAME", nullable=false, length=18)
	private String fieldName;
	
	@Column(name="FIELDVALUE", nullable=false, length=4)
	private String fieldValue;
	
	@Column(name="EFFDT")
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;
	
	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String statusAsOfEffectiveDate;

	@Column(name="XLATLONGNAME", nullable=false, length=30)
	private String xlatLongName;
	
	@Column(name="XLATSHORTNAME", nullable=false, length=10)
	private String xlatShortName;
	
	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;
	
	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	public PsTranslationItem() {
		super();
	}   
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}   
	public String getFieldValue() {
		return this.fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}   
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}   
	public String getXlatLongName() {
		return this.xlatLongName;
	}

	public void setXlatLongName(String xlatLongName) {
		this.xlatLongName = xlatLongName;
	}   
	public String getXlatShortName() {
		return this.xlatShortName;
	}

	public void setXlatShortName(String xlatShortName) {
		this.xlatShortName = xlatShortName;
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
	public Integer getSyncId() {
		return this.syncId;
	}

	public void setSyncId(Integer syncId) {
		this.syncId = syncId;
	}
	   
	/**
	 * Gets the Employee Status description for Active Directory File Build       
	 * @see AD-Get-EmplStatus-Description in ZHRI100A.SQR
	 * @param fieldName
	 * @param fieldValue
	 * @param effectiveDate
	 * @return
	 */
	public static String findFieldDescriptionByFieldNameAndFieldValueAndEffectiveDate(String fieldName, String fieldValue, Date effectiveDate) {
//			Begin-Select
//			AD10.XLATLONGNAME
//			let $ADEmplStatusDescr = ltrim(rtrim(&AD10.XLATLONGNAME,' '),' ')
//			from PSXLATITEM AD10
//			where AD10.FIELDNAME = 'EMPL_STATUS'
//			and AD10.FIELDVALUE = $PSEmplstatus
//			and AD10.EFFDT = (Select max(AD11.EFFDT) from PSXLATITEM AD11
//			                  where AD10.FIELDNAME = AD11.FIELDNAME
//			                  and AD10.FIELDVALUE = AD11.FIELDVALUE
//			                  and AD11.EFFDT <= SYSDATE
//			                  )
//			End-Select
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		
		try {
			List<String> resultList = em.createQuery(
					"SELECT UPPER(TRIM(p.xlatLongName)) FROM PsTranslationItem p "
					+ "WHERE UPPER(TRIM(p.fieldName)) = TRIM(UPPER(:fieldName)) "
					+ "AND UPPER(TRIM(p.fieldValue)) = TRIM(UPPER(:fieldValue)) "
					+ "AND p.effectiveDate = :effectiveDate ",
					String.class)
			    .setParameter("processName", fieldName)
			    .setParameter("fieldName", fieldValue)
			    .setParameter("effectiveDate", effectiveDate, TemporalType.DATE)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
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
	 * Gets the Employee Status description for Active Directory File Build       
	 * @see AD-Get-EmplStatus-Description in ZHRI100A.SQR
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public static Date findMaxEffectiveDateByFieldNameAndFieldValue(String fieldName, String fieldValue) {
//			and AD10.EFFDT = (Select max(AD11.EFFDT) from PSXLATITEM AD11
//			                  where AD10.FIELDNAME = AD11.FIELDNAME
//			                  and AD10.FIELDVALUE = AD11.FIELDVALUE
//			                  and AD11.EFFDT <= SYSDATE)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		
	    try {
	    	List<Date> resultList = em.createQuery(
	    			"SELECT MAX(p.effectiveDate) FROM PsTranslationItem p "
	    				+ "WHERE UPPER(TRIM(p.fieldName)) = UPPER(TRIM(:fieldName)) "
	    				+ "AND UPPER(TRIM(p.fieldValue)) = UPPER(TRIM(:fieldValue)) "
	    				+ "AND p.effectiveDate <= CURRENT_DATE ",
	    				Date.class)
	    		    .setParameter("fieldName", fieldName)
	    		    .setParameter("fieldValue", fieldValue)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
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
	 * @see Read-Translate-Table in ZREADXLT.SQC
	 * @param fieldName
	 * @param fieldValue
	 * @return PsXlatItem record
	 */
	public static PsTranslationItem findByFieldNameAndFieldValueAndEffectiveDate(String fieldName, String fieldValue, Date effectiveDate) {
		//BEGIN-SELECT
		//XLATSHORTNAME
		//XLATLONGNAME
		//  move &XlatShortName to $ShortName
		//  move &XlatLongName  to $LongName
		//    FROM  PSXLATITEM
		//    WHERE FIELDNAME   = $FldName
		//      AND FIELDVALUE  = $FldVal
		//      AND EFFDT =
		//          (SELECT MAX(EFFDT)
		//             FROM   PSXLATITEM
		//             WHERE  FIELDNAME  = $_FieldName
		//               AND  FIELDVALUE = $_FieldValue
		//               AND  EFFDT     <= to_date($_AsOfDate,'YYYY-MM-DD'))
		// END-SELECT
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		
		try {
			List<PsTranslationItem> resultList = em.createQuery(
					"SELECT p FROM PsTranslationItem p "
						+ "WHERE UPPER(TRIM(p.fieldName)) = UPPER(TRIM(:fieldName)) "
						+ "AND UPPER(TRIM(p.fieldValue)) = UPPER(TRIM(:fieldValue)) "
						+ "AND p.effectiveDate = "
								+ "(SELECT MAX(p2.effectiveDate) FROM PsTranslationItem p2 "
									+ "WHERE UPPER(TRIM(p2.fieldName)) = UPPER(TRIM(:fieldName)) "
									+ "AND UPPER(TRIM(p2.fieldValue)) = UPPER(TRIM(:fieldValue)) "
									+ "AND p2.effectiveDate <= :effectiveDate) "
					, PsTranslationItem.class)
			    .setParameter("processName", fieldName)
			    .setParameter("fieldName", fieldValue)
			    .setParameter("effectiveDate", effectiveDate, TemporalType.DATE)
	    		    .getResultList();
	    	if(resultList != null && resultList.size() > 0) {
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

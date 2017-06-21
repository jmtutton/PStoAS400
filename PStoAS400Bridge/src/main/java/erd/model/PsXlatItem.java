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
@NamedQuery(name="PsXlatItem.findAll", query="SELECT p FROM PsXlatItem p")
public class PsXlatItem implements Serializable {
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

	public PsXlatItem() {
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
		 * Replaces AD-Get-EmplStatus-Description from ZHRI100A.SQR
		 * Gets the Employee Status description for Active Directory File Build       
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
		    	List<String> resultList = em.createQuery("SELECT p.xlatLongName FROM PsXlatItem p "
		    				+ "WHERE UPPER(TRIM(p.fieldName)) = :fieldName "
		    				+ "AND UPPER(TRIM(p.fieldValue)) = :fieldValue "
		    				+ "AND p.effectiveDate = :effectiveDate ",
		    				String.class)
		    		    .setParameter("processName", fieldName.toUpperCase())
		    		    .setParameter("fieldName", fieldValue.toUpperCase())
		    		    .setParameter("effectiveDate", effectiveDate)
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
		   
		/**
		 * Replaces AD-Get-EmplStatus-Description from ZHRI100A.SQR
		 * Gets the Employee Status description for Active Directory File Build       
		 */
		public static Date findMaxEffectiveDateByFieldNameAndFieldValue(String fieldName, String fieldValue) {
//			and AD10.EFFDT = (Select max(AD11.EFFDT) from PSXLATITEM AD11
//			                  where AD10.FIELDNAME = AD11.FIELDNAME
//			                  and AD10.FIELDVALUE = AD11.FIELDVALUE
//			                  and AD11.EFFDT <= SYSDATE)
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
			EntityManager em = emfactory.createEntityManager();
			
		    try {
		    	List<Date> resultList = em.createQuery("SELECT MAX(p.effectiveDate) FROM PsXlatItem p "
		    				+ "WHERE UPPER(TRIM(p.fieldName)) = :fieldName "
		    				+ "AND UPPER(TRIM(p.fieldValue)) = :fieldValue "
		    				+ "AND p.effectiveDate <= CURRENT_DATE ",
		    				Date.class)
		    		    .setParameter("fieldName", fieldName.toUpperCase())
		    		    .setParameter("fieldValue", fieldValue.toUpperCase())
		    		    .getResultList();
		    	if(resultList != null && resultList.size() > 0) {
		    		return resultList.get(0);
		    	}
		    }
		    catch (Exception e) {
		       e.printStackTrace();
		    } 
		    return null;	
		}
}

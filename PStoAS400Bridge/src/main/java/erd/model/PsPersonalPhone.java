package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_PERSONAL_PHONE database table.
 * Employee Personal Phone Numbers
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_PERSONAL_PHONE")
@NamedQuery(name="PsPersonalPhone.findAll", query="SELECT p FROM PsPersonalPhone p")
public class PsPersonalPhone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="COUNTRY_CODE", nullable=false, length=3)
	private String countryCode;

	@Column(name="EXTENSION", nullable=false, length=6)
	private String extension;

	@Column(name="PHONE", nullable=false, length=24)
	private String phone;

	@Column(name="PHONE_TYPE", nullable=false, length=4)
	private String phoneType;

	@Column(name="PREF_PHONE_FLAG", nullable=false, length=1)
	private String isPreferredPhone;

	public PsPersonalPhone() {
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneType() {
		return this.phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getIsPreferredPhone() {
		return this.isPreferredPhone;
	}

	public void setIsPreferredPhone(String isPreferredPhone) {
		this.isPreferredPhone = isPreferredPhone;
	}

	public PsPersonalPhone findByEmployeeIdAndPhoneType(String employeeId, String phoneType) {
		return null;
	}

	/**
	 * @see HR05-Get-Business-Phone in ZHRI105A.SQC
	 * @param employeeId
	 * @param phoneType
	 * @return phone
	 */
	public static String findPhoneByEmployeeIdAndPhoneType(String employeeId, String phoneType) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<String> resultList = em.createQuery(
					"SELECT p.phone FROM PsPersonalPhone p "
					+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
					+ "AND UPPER(TRIM(p.phoneType)) = UPPER(TRIM(:phoneType)) "
					, String.class)
					.setParameter("employeeId", employeeId)
					.setParameter("phoneType", phoneType)
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

	
	public enum PhoneType { MAIN, BUSN, FAX }

}
package erd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PS_PERSONAL_PHONE database table.
 * @author	John Tutton john@tutton.net
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

	public static PsPersonalPhone findMainPhone(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR01-Get-Main-Phone
//		! Desc:  This routine gets the main phone number from the Peoplesoft
//		!        tables and formats them for the legacy system
//		!----------------------------------------------------------------------
//		Begin-Procedure HR01-Get-Main-Phone
//		begin-select
//		CPPM3.Phone
//		  Let $PSHomePhone = ltrim(rtrim(&CPPM3.PHONE,' '),' ')
//		from  PS_Personal_Phone CPPM3
//		where CPPM3.Phone_Type = 'MAIN'
//		  and CPPM3.Emplid = $Wrk_Emplid
//		end-select
//		end-procedure HR01-Get-Main-Phone		
		return null;
	}

	public static PsPersonalPhone HR05GetMainPhone(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR05-Get-Main-Phone
//		! Desc:  This routine gets the main phone number from the Peoplesoft
//		!        tables and formats them for the legacy system
//		!----------------------------------------------------------------------
//		Begin-Procedure HR05-Get-Main-Phone
//		begin-select
//		CPPM2.Emplid
//		CPPM2.Phone_Type
//		CPPM2.Phone
//		 Do Remove-Non-Letters-Numbers (&CPPM2.Phone, $PSPhone)        !From ZRmvSpcChr.sqc
//		 Let $Wrk_AD_MainPhoneBuild = 'Y'
//		from PS_Personal_Phone CPPM2
//		where CPPM2.Phone_Type = 'MAIN'
//		  and CPPM2.Emplid = $PSEmplid
//		end-select
//		end-procedure HR05-Get-Main-Phone
		return null;
	}

	public static PsPersonalPhone findBusinessPhone(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  AD-Get-Business-Phone
//		! Desc:  This routine gets the business phone number from the Peoplesoft
//		!        tables.
//		!----------------------------------------------------------------------
//		Begin-Procedure AD-Get-Business-Phone
//		Let $PSBusiness_Phone = ''
//		begin-select
//		ADPP2.Phone
//		 do Remove-Non-Letters-Numbers (&ADPP2.Phone, $PSBusiness_Phone)   !From ZRmvSpcChr.sqc
//		from PS_Personal_Phone ADPP2
//		where ADPP2.Phone_Type = 'BUSN'
//		  and ADPP2.Emplid = $PSEmplid
//		end-select
//		end-procedure AD-Get-Business-Phone
		return null;
	}

	public static PsPersonalPhone findEmployeeFax(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  AD-Get-Employee-Fax
//		! Desc:  This routine gets the business phone number from the Peoplesoft
//		!        tables.
//		!----------------------------------------------------------------------
//		Begin-Procedure AD-Get-Employee-Fax
//		begin-select
//		ADPP3.Phone
//		 do Remove-Non-Letters-Numbers (&ADPP3.Phone, $ADEmployeeFax)   !From ZRmvSpcChr.sqc
//		from PS_Personal_Phone ADPP3
//		where ADPP3.Phone_Type = 'FAX'
//		  and ADPP3.Emplid = $PSEmplid
//		end-select
//		end-procedure AD-Get-Employee-Fax
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
					+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
					+ "AND UPPER(TRIM(p.phoneType)) = :phoneType "
					, String.class)
					.setParameter("employeeId", employeeId.trim().toUpperCase())
					.setParameter("phoneType", phoneType.trim().toUpperCase())
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
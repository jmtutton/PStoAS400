package erd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import erd.DateUtil;

/**
 * The persistent class for the PS_EMERGENCY_CNTCT database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_EMERGENCY_CNTCT")
@NamedQuery(name="PsEmergencyContact.findAll", query="SELECT p FROM PsEmergencyContact p")
public class PsEmergencyContact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="ADDR_FIELD1", nullable=false, length=2)
	private String addrField1;

	@Column(name="ADDR_FIELD2", nullable=false, length=4)
	private String addrField2;

	@Column(name="ADDR_FIELD3", nullable=false, length=4)
	private String addrField3;

	@Column(name="ADDRESS_TYPE", nullable=false, length=4)
	private String addressType;

	@Column(name="ADDRESS1", nullable=false, length=55)
	private String address1;

	@Column(name="ADDRESS2", nullable=false, length=55)
	private String address2;

	@Column(name="ADDRESS3", nullable=false, length=55)
	private String address3;

	@Column(name="ADDRESS4", nullable=false, length=55)
	private String address4;

	@Column(name="CITY", nullable=false, length=30)
	private String city;

	@Column(name="CONTACT_NAME", nullable=false, length=50)
	private String contactName;

	@Column(name="COUNTRY", nullable=false, length=3)
	private String countryIsoAlpha3Code;

	@Column(name="COUNTRY_CODE", nullable=false, length=3)
	private String countryCode;

	@Column(name="COUNTY", nullable=false, length=30)
	private String county;

	@Column(name="EXTENSION", nullable=false, length=6)
	private String extension;

	@Column(name="GEO_CODE", nullable=false, length=11)
	private String geoCode;

	@Column(name="HOUSE_TYPE", nullable=false, length=2)
	private String houseType;

	@Column(name="IN_CITY_LIMIT", nullable=false, length=1)
	private String isInCityLimit;

	@Column(name="NUM1", nullable=false, length=6)
	private String num1;

	@Column(name="NUM2", nullable=false, length=6)
	private String num2;

	@Column(name="PHONE", nullable=false, length=24)
	private String phone;

	@Column(name="PHONE_TYPE", nullable=false, length=4)
	private String phoneType;

	@Column(name="POSTAL", nullable=false, length=12)
	private String postal;

	@Column(name="PRIMARY_CONTACT", nullable=false, length=1)
	private String isPrimaryContact;

	@Column(name="RELATIONSHIP", nullable=false, length=2)
	private String relationship;

	@Column(name="SAME_ADDRESS_EMPL", nullable=false, length=1)
	private String sameAddressEmpl;

	@Column(name="SAME_PHONE_EMPL", nullable=false, length=1)
	private String samePhoneEmpl;

	@Column(name="\"STATE\"", nullable=false, length=6)
	private String state;

	public PsEmergencyContact() {
	}

	public String getAddrField1() {
		return this.addrField1;
	}

	public void setAddrField1(String addrField1) {
		this.addrField1 = addrField1;
	}

	public String getAddrField2() {
		return this.addrField2;
	}

	public void setAddrField2(String addrField2) {
		this.addrField2 = addrField2;
	}

	public String getAddrField3() {
		return this.addrField3;
	}

	public void setAddrField3(String addrField3) {
		this.addrField3 = addrField3;
	}

	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return this.address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactName() {
		return this.contactName != null ? this.contactName.trim() : this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getCountryIsoAlpha3Code() {
		return this.countryIsoAlpha3Code;
	}

	public void setCountryIsoAlpha3Code(String countryIsoAlpha3Code) {
		this.countryIsoAlpha3Code = countryIsoAlpha3Code;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
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

	public String getGeoCode() {
		return this.geoCode;
	}

	public void setGeoCode(String geoCode) {
		this.geoCode = geoCode;
	}

	public String getHouseType() {
		return this.houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getIsInCityLimit() {
		return this.isInCityLimit;
	}

	public void setIsInCityLimit(String isInCityLimit) {
		this.isInCityLimit = isInCityLimit;
	}

	public String getNum1() {
		return this.num1;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}

	public String getNum2() {
		return this.num2;
	}

	public void setNum2(String num2) {
		this.num2 = num2;
	}

	public String getPhone() {
		return this.phone != null ? this.phone.trim() : this.phone;
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

	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getIsPrimaryContact() {
		return this.isPrimaryContact;
	}

	public void setIsPrimaryContact(String isPrimaryContact) {
		this.isPrimaryContact = isPrimaryContact;
	}

	public String getRelationship() {
		return this.relationship != null ? this.relationship.trim() : this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getSameAddressEmpl() {
		return this.sameAddressEmpl;
	}

	public void setSameAddressEmpl(String sameAddressEmpl) {
		this.sameAddressEmpl = sameAddressEmpl;
	}

	public String getSamePhoneEmpl() {
		return this.samePhoneEmpl;
	}

	public void setSamePhoneEmpl(String samePhoneEmpl) {
		this.samePhoneEmpl = samePhoneEmpl;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * This routine gets the emergency contact information from the PS_EMERGENCY_CNTCT table and converts it to the legacy system format.
	 * @see HR05-Get-Emergency-Cntct in ZHRI105A.SQC
	 * @param employeeId
	 * @return PsEmergencyContact record
	 */
	public static PsEmergencyContact findByEmployeeIdAndPrimaryContact(String employeeId) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
	    try {
	    	List<PsEmergencyContact> resultList = em.createQuery(
	    			"SELECT PsEmergencyContact FROM PsEmergencyContact c "
	    					+ "WHERE UPPER(TRIM(c.employeeId)) = :employeeId "
	    					+ "AND UPPER(TRIM(isPrimaryContact)) = 'Y' "
	    					, PsEmergencyContact.class)
	    		    .setParameter("employeeId", employeeId.toUpperCase().trim())
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
	 * This procedure converts the PeopleSoft relationship codes to legacy system relationship descriptions.
	 * @see HR05-Format-Relationships in ZHRI105A.SQC
	 * @param relationship
	 * @return relationship
	 */
	public static String formatRelationship(String relationship) {
		Date asofToday = DateUtil.asOfToday();
		//BEGIN-PROCEDURE HR05-FORMAT-RELATIONSHIPS
		//LET $FieldName = 'RELATIONSHIP'
		//LET $FieldValue = $Relations
		//LET $AsOfDate = $AsOfToday
		//DO Read-Translate-Table  !From ZREADXLT.SQC
		PsXlatItem psXlatItem = PsXlatItem.findByFieldNameAndFieldValueAndEffectiveDate("RELATIONSHIP", relationship, asofToday);
		relationship = psXlatItem.getXlatLongName();
		//LET $PSRelation = SUBSTR($XlatLongName, 1, 20)
		//UPPERCASE $PSRelation
		if(relationship != null && relationship.length() > 20) {
			relationship = relationship.substring(0, 20).toUpperCase();
		}
		//END-PROCEDURE HR05-FORMAT-RELATIONSHIPS
		return relationship;
	}

}
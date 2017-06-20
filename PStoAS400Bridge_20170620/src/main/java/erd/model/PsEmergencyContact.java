package erd.model;

import java.io.Serializable;
import javax.persistence.*;

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
		return this.contactName;
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
		return this.relationship;
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
	
	public PsEmergencyContact findByEmployeeIdAndIsPrimaryContact(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR05-Get-Emergency-Cntct
//		! Desc:  This routine gets the emergency contact information from the
//		!        Emergecny contact table and converts it to the legay system
//		!        format.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR05-Get-Emergency-Cntct
//		begin-select
//		CEC.Contact_Name
//		CEC.Phone
//		CEC.Relationship
//		  Let $PSContact_Name = RTRIM(LTRIM(&CEC.Contact_Name,' '),' ')
//		  uppercase $PSContact_Name
//		  Do Replace-Character($PSContact_Name,'''','''''',$PSContact_Name)  !From ZRmvSpcChr.sqc
//		  Let $Relations = &CEC.Relationship
//		  Do Remove-Non-Letters-Numbers (&CEC.Phone, $PSEmer_Phn)      !From ZRmvSpcChr.sqc
//		  Do HR05-Format-Relationships
//		from PS_Emergency_Cntct CEC
//		where CEC.Emplid = $PSEmplid
//		  and CEC.Primary_Contact = 'Y'
//		end-select
//		End-Procedure HR05-Get-Emergency-Cntct
	return null;
	}

}
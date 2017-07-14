package erd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The persistent class for the PS_ADDRESSES database table.
 * Personal Addresses
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_ADDRESSES")
@NamedQuery(name="PsAddress.findAll", query="SELECT p FROM PsAddress p")
public class PsAddress implements Serializable {
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

	@Column(name="ADDRESS1_AC", nullable=false, length=55)
	private String address1AlternateCharacter;

	@Column(name="ADDRESS2", nullable=false, length=55)
	private String address2;

	@Column(name="ADDRESS2_AC", nullable=false, length=55)
	private String address2AlternateCharacter;

	@Column(name="ADDRESS3", nullable=false, length=55)
	private String address3;

	@Column(name="ADDRESS3_AC", nullable=false, length=55)
	private String address3AlternateCharacter;

	@Column(name="ADDRESS4", nullable=false, length=55)
	private String address4;

	@Column(name="CITY", nullable=false, length=30)
	private String city;

	@Column(name="CITY_AC", nullable=false, length=30)
	private String cityAlternateCharacter;

	@Column(name="COUNTRY", nullable=false, length=3)
	private String countryIsoAlpha3Code;

	@Column(name="COUNTY", nullable=false, length=30)
	private String county;

	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String statusAsOfEffectiveDate;

	@Column(name="EFFDT",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="GEO_CODE", nullable=false, length=11)
	private String geoCode;

	@Column(name="HOUSE_TYPE", nullable=false, length=2)
	private String houseType;

	@Column(name="IN_CITY_LIMIT", nullable=false, length=1)
	private String isInCityLimit;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="NUM1", nullable=false, length=6)
	private String num1;

	@Column(name="NUM2", nullable=false, length=6)
	private String num2;

	@Column(name="POSTAL", nullable=false, length=12)
	private String postal;

	@Column(name="REG_REGION", nullable=false, length=5)
	private String regulatoryRegion;

	@Column(name="\"STATE\"", nullable=false, length=6)
	private String state;

	public PsAddress() {
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
		return this.address1 != null ? this.address1.trim() : this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress1Ac() {
		return this.address1AlternateCharacter;
	}

	public void setAddress1Ac(String address1AlternateCharacter) {
		this.address1AlternateCharacter = address1AlternateCharacter;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress2Ac() {
		return this.address2AlternateCharacter;
	}

	public void setAddress2Ac(String address2AlternateCharacter) {
		this.address2AlternateCharacter = address2AlternateCharacter;
	}

	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress3Ac() {
		return this.address3AlternateCharacter;
	}

	public void setAddress3Ac(String address3AlternateCharacter) {
		this.address3AlternateCharacter = address3AlternateCharacter;
	}

	public String getAddress4() {
		return this.address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getCity() {
		return this.city != null ? this.city.trim() : this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityAc() {
		return this.cityAlternateCharacter;
	}

	public void setCityAc(String cityAlternateCharacter) {
		this.cityAlternateCharacter = cityAlternateCharacter;
	}

	public String getCountryCode() {
		return this.countryIsoAlpha3Code;
	}

	public void setCountryCode(String countryIsoAlpha3Code) {
		this.countryIsoAlpha3Code = countryIsoAlpha3Code;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStatusAsOfEffectiveDate() {
		return this.statusAsOfEffectiveDate;
	}

	public void setStatusAsOfEffectiveDate(String statusAsOfEffectiveDate) {
		this.statusAsOfEffectiveDate = statusAsOfEffectiveDate;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public String getPostal() {
		return this.postal != null ? this.postal.trim() : this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getRegulatoryRegion() {
		return this.regulatoryRegion;
	}

	public void setRegulatoryRegion(String regulatoryRegion) {
		this.regulatoryRegion = regulatoryRegion;
	}

	public String getState() {
		return this.state != null ? this.state.trim() : this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @see HR01-Get-Personal-Data in ZHRI101A.SQC
	 * @see HR05-Get-Info in ZHRI105A.SQC
	 * @param employeeId
	 * @param addressType
	 * @param effectiveDate
	 * @return PsAddress record
	 */
	public static PsAddress findHomeByEmployeeIdAndEffectiveDate(String employeeId, Date effectiveDate) {
		//SELECT FROM PS_ADDRESSES P
		//WHERE P.EmplId = $PSEmplId
		//AND P.ADDRESS_TYPE = 'HOME'
		//AND P.EFFDT  = 
				//(SELECT MAX(P2.EFFDT) FROM PS_ADDRESSES P2
				//WHERE P2.EMPLID   = P.EMPLID
				//AND P2.ADDRESS_TYPE  = P.ADDRESS_TYPE
				//AND TO_CHAR(P2.EFFDT,'YYYY-MM-DD') <= $PsEffdt)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<PsAddress> resultList = em.createQuery(
					"SELECT p FROM PsAddress p "
							+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
							+ "AND UPPER(TRIM(p.addressType)) = 'HOME' "
							+ "AND p.effectiveDate = "
									+ "(SELECT MAX(p2.effectiveDate) FROM PsAddress p2 "
									+ "WHERE UPPER(TRIM(p2.employeeId)) = UPPER(TRIM(p.employeeId)) "
									+ "AND UPPER(TRIM(p2.addressType)) = UPPER(TRIM(p.addressType)) "
									+ "AND p2.effectiveDate <= :effectiveDate) "
					, PsAddress.class)
					.setParameter("employeeId", employeeId)
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
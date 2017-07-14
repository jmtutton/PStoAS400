package erd.model;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The persistent class for the PS_NAMES database table.
 * All Names for an Individual
 * @author John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_NAMES")
@NamedQuery(name="PsName.findAll", query="SELECT p FROM PsName p")
public class PsName implements Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="COUNTRY_NM_FORMAT", nullable=false, length=3)
	private String supportedNameFormatTypes;

	@Column(name="EFF_STATUS", nullable=false, length=1)
	private String statusAsOfEffectiveDate;

	@Column(name="EFFDT", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name="FIRST_NAME", nullable=false, length=30)
	private String firstName;

	@Column(name="FIRST_NAME_SRCH", nullable=false, length=30)
	private String firstNameSpecialCharactersRemoved;

	@Column(name="LAST_NAME", nullable=false, length=30)
	private String lastName;

	@Column(name="LAST_NAME_PREF_NLD", nullable=false, length=1)
	private String lastNamePreferenceCode;

	@Column(name="LAST_NAME_SRCH", nullable=false, length=30)
	private String lastNameSpecialCharactersRemoved;

	@Column(name="LASTUPDDTTM")
	private Timestamp lastUpdatedDateAndTime;

	@Column(name="LASTUPDOPRID", nullable=false, length=30)
	private String lastUpdatedUserId;

	@Column(name="MIDDLE_NAME", nullable=false, length=30)
	private String middleName;

	@Column(name="NAME", nullable=false, length=50)
	private String name;

	@Column(name="NAME_AC", nullable=false, length=50)
	private String alternateCharacterName;

	@Column(name="NAME_DISPLAY", nullable=false, length=50)
	private String displayName;

	@Column(name="NAME_DISPLAY_SRCH", nullable=false, length=50)
	private String displayNameSpecialCharactersRemoved;

	@Column(name="NAME_FORMAL", nullable=false, length=60)
	private String formalName;

	@Column(name="NAME_INITIALS", nullable=false, length=6)
	private String nameInitials;

	@Column(name="NAME_PREFIX", nullable=false, length=4)
	private String namePrefix;

	@Column(name="NAME_ROYAL_PREFIX", nullable=false, length=15)
	private String nameRoyalPrefix;

	@Column(name="NAME_ROYAL_SUFFIX", nullable=false, length=15)
	private String nameRoyalSuffix;

	@Column(name="NAME_SUFFIX", nullable=false, length=15)
	private String nameSuffix;

	@Column(name="NAME_TITLE", nullable=false, length=30)
	private String nameTitle;

	@Column(name="NAME_TYPE", nullable=false, length=3)
	private String nameType;

	@Column(name="PARTNER_LAST_NAME", nullable=false, length=30)
	private String partnerLastName;

	@Column(name="PARTNER_ROY_PREFIX", nullable=false, length=15)
	private String partnerRoyalPrefix;

	@Column(name="PREF_FIRST_NAME", nullable=false, length=30)
	private String preferredFirstName;

	@Column(name="SECOND_LAST_NAME", nullable=false, length=30)
	private String secondLastName;

	@Column(name="SECOND_LAST_SRCH", nullable=false, length=30)
	private String secondLastNameSpecialCharactersRemoved;

	public PsName() {
	}

	public String getSupportedNameFormatTypes() {
		return this.supportedNameFormatTypes;
	}

	public void setSupportedNameFormatTypes(String supportedNameFormatTypes) {
		this.supportedNameFormatTypes = supportedNameFormatTypes;
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstNameSpecialCharactersRemoved() {
		return this.firstNameSpecialCharactersRemoved;
	}

	public void setFirstNameSpecialCharactersRemoved(String firstNameSpecialCharactersRemoved) {
		this.firstNameSpecialCharactersRemoved = firstNameSpecialCharactersRemoved;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastNamePrefNld() {
		return this.lastNamePreferenceCode;
	}

	public void setLastNamePrefNld(String lastNamePreferenceCode) {
		this.lastNamePreferenceCode = lastNamePreferenceCode;
	}

	public String getLastNameSrch() {
		return this.lastNameSpecialCharactersRemoved;
	}

	public void setLastNameSrch(String lastNameSpecialCharactersRemoved) {
		this.lastNameSpecialCharactersRemoved = lastNameSpecialCharactersRemoved;
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

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAc() {
		return this.alternateCharacterName;
	}

	public void setNameAc(String alternateCharacterName) {
		this.alternateCharacterName = alternateCharacterName;
	}

	public String getNameDisplay() {
		return this.displayName;
	}

	public void setNameDisplay(String displayName) {
		this.displayName = displayName;
	}

	public String getNameDisplaySrch() {
		return this.displayNameSpecialCharactersRemoved;
	}

	public void setNameDisplaySrch(String displayNameSpecialCharactersRemoved) {
		this.displayNameSpecialCharactersRemoved = displayNameSpecialCharactersRemoved;
	}

	public String getNameFormal() {
		return this.formalName;
	}

	public void setNameFormal(String formalName) {
		this.formalName = formalName;
	}

	public String getNameInitials() {
		return this.nameInitials;
	}

	public void setNameInitials(String nameInitials) {
		this.nameInitials = nameInitials;
	}

	public String getNamePrefix() {
		return this.namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getNameRoyalPrefix() {
		return this.nameRoyalPrefix;
	}

	public void setNameRoyalPrefix(String nameRoyalPrefix) {
		this.nameRoyalPrefix = nameRoyalPrefix;
	}

	public String getNameRoyalSuffix() {
		return this.nameRoyalSuffix;
	}

	public void setNameRoyalSuffix(String nameRoyalSuffix) {
		this.nameRoyalSuffix = nameRoyalSuffix;
	}

	public String getNameSuffix() {
		return this.nameSuffix;
	}

	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	public String getNameTitle() {
		return this.nameTitle;
	}

	public void setNameTitle(String nameTitle) {
		this.nameTitle = nameTitle;
	}

	public String getNameType() {
		return this.nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	public String getPartnerLastName() {
		return this.partnerLastName;
	}

	public void setPartnerLastName(String partnerLastName) {
		this.partnerLastName = partnerLastName;
	}

	public String getPartnerRoyPrefix() {
		return this.partnerRoyalPrefix;
	}

	public void setPartnerRoyPrefix(String partnerRoyalPrefix) {
		this.partnerRoyalPrefix = partnerRoyalPrefix;
	}

	public String getPrefFirstName() {
		return this.preferredFirstName;
	}

	public void setPrefFirstName(String preferredFirstName) {
		this.preferredFirstName = preferredFirstName;
	}

	public String getSecondLastName() {
		return this.secondLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	public String getSecondLastSrch() {
		return this.secondLastNameSpecialCharactersRemoved;
	}

	public void setSecondLastSrch(String secondLastNameSpecialCharactersRemoved) {
		this.secondLastNameSpecialCharactersRemoved = secondLastNameSpecialCharactersRemoved;
	}

	/**
	 * @see HR01-Get-Personal-Data in ZHRI101A.SQC
	 * @see HR05-Get-Names-Table in ZHRI105A.SQC
	 * @see HR05-Get-Info in ZHRI105A.SQC
	 * @see HR201-Get-Personal-Data
	 * @param employeeId
	 * @param nameType
	 * @param effectiveDate
	 * @return PsName record
	 */
	public static PsName findByEmployeeIdAndNameTypeAndEffectiveDate(String employeeId, String nameType, Date effectiveDate) {
		logger.debug("findByEmployeeIdAndNameTypeAndEffectiveDate() ***");
		//SELECT FROM PS_Names P
		//WHERE P.Emplid = $PsEmplId
		//AND P.NAME_TYPE = 'PRF'
		//AND P.EFFDT = 
				//(SELECT MAX(EFFDT) FROM PS_Names P2
				//WHERE P2.EMPLID = P.EMPLID
				//AND P2.NAME_TYPE = P.NAME_TYPE
				//AND TO_CHAR(P2.EFFDT,'YYYY-MM-DD') <= $PsEffDt)
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		try {
			List<PsName> resultList = em.createQuery(
					"SELECT p FROM PsName p "
							+ "WHERE UPPER(TRIM(p.employeeId)) = UPPER(TRIM(:employeeId)) "
							+ "AND UPPER(TRIM(p.nameType)) = UPPER(TRIM(:nameType)) "
							+ "AND p.effectiveDate = "
							+ "(SELECT MAX(p2.effectiveDate) FROM PsName p2 "
								+ "WHERE UPPER(TRIM(p2.employeeId)) = UPPER(TRIM(p.employeeId)) "
								+ "AND UPPER(TRIM(p2.nameType)) = UPPER(TRIM(p.nameType)) "
								+ "AND p2.effectiveDate <= :effectiveDate) "
					, PsName.class)
					.setParameter("employeeId", employeeId)
					.setParameter("nameType", nameType)
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
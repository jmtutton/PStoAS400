package erd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The persistent class for the PS_NAMES database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_NAMES")
@NamedQuery(name="PsName.findAll", query="SELECT p FROM PsName p")
public class PsName implements Serializable {
	private static final long serialVersionUID = 1L;

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
		//SELECT
		//FROM PS_Names P
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
					"SELECT PsName FROM PsName p "
							+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
							+ "WHERE UPPER(TRIM(p.nameType)) = :nameType "
							+ "AND p.effectiveDate = "
							+ "(SELECT MAX(p2.effectiveDate) FROM PsName p2 "
								+ "WHERE UPPER(TRIM(p2.employeeId)) = UPPER(TRIM(p.employeeId)) "
								+ "AND UPPER(TRIM(p2.nameType)) = UPPER(TRIM(p.nameType)) "
								+ "AND p2.effectiveDate <= :effectiveDate) "
					, PsName.class)
					.setParameter("employeeId", employeeId.trim().toUpperCase())
					.setParameter("nameType", nameType.trim().toUpperCase())
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

	
	public static PsName findByEmployeeId205(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR205-Get-Personal-Data
//		! Desc:  This routine will get the Personal Data row for each of the
//		!        employee numbers entered in the trigger file.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR205-Get-Personal-Data
//		Begin-Select  
//		PER5.SEX            
//		     Let $PSGender = ltrim(rtrim(&PER5.Sex,' '),' ') 
//		     IF  $PSGender = 'U' 
//		      LET  $PSGender = ''
//		     END-IF            
//		from  PS_PERS_DATA_EFFDT PER5
//		where PER5.EMPLID = $Wrk_Emplid
//		  and PER5.EFFDT = (SELECT MAX(PER52.EFFDT)
//		                      FROM PS_PERS_DATA_EFFDT PER52
//		                     WHERE PER52.EMPLID = PER5.EMPLID
//		                       AND  to_char(PER52.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		End-Select
//		Begin-Select  
//		N5.MIDDLE_NAME
//		    Let $PSMName = ltrim(rtrim(&N5.MIDDLE_NAME,' '),' ')
//		N5.FIRST_NAME
//		    Let $PSFName = ltrim(rtrim(&N5.FIRST_NAME,' '),' ')
//		N5.LAST_NAME
//		    Let $PSLName = ltrim(rtrim(&N5.LAST_NAME,' '),' ')
//		from  PS_NAMES N5
//		where N5.EMPLID = $Wrk_Emplid
//		and N5.NAME_TYPE = 'PRI'
//		and N5.EFFDT     = (SELECT MAX(N52.EFFDT) FROM PS_NAMES N52
//		                      WHERE N52.EMPLID   = N5.EMPLID
//		                      AND   N52.NAME_TYPE  = N5.NAME_TYPE
//		                      AND   to_char(N52.EFFDT,'YYYY-MM-DD') <= $PSEffdt)
//		End-Select
//		End-Procedure HR205-Get-Personal-Data
		return null;
	}

	public static PsName findByEmployeeId201(String employeeId) {
//		!----------------------------------------------------------------------
//		! Procedure:  HR201-Get-Personal-Data
//		! Desc:  Gets the employees data from the personal data effdt table that
//		!        needs to be interfaced to the legacy system.
//		!        All this data used to come from pers_data_effdt.
//		!----------------------------------------------------------------------
//		Begin-Procedure HR201-Get-Personal-Data
//		Begin-Select  
//		PER1.SEX            
//		     Let $PSSex = ltrim(rtrim(&PER1.SEX,' '),' ') 
//		     IF  $PSSex = 'U' 
//		      LET  $PSSex = ''
//		     END-IF            
//		from  PS_PERS_DATA_EFFDT PER1
//		where PER1.EMPLID = $Wrk_Emplid
//		  and PER1.EFFDT = (SELECT MAX(PER12.EFFDT)
//		                      FROM PS_PERS_DATA_EFFDT PER12
//		                     WHERE PER12.EMPLID = PER1.EMPLID
//		                       AND  to_char(PER12.EFFDT,'YYYY-MM-DD') <= $Wrk_Effdt)
//		End-Select 
//		Begin-Select  
//		N1.MIDDLE_NAME
//		    Let $PSMiddleName = ltrim(rtrim(&N1.MIDDLE_NAME,' '),' ')
//		N1.FIRST_NAME
//		    Let $PSFirstName = ltrim(rtrim(&N1.FIRST_NAME,' '),' ')
//		N1.LAST_NAME
//		    Let $PSLastName = ltrim(rtrim(&N1.LAST_NAME,' '),' ')
//		from  PS_NAMES N1
//		where N1.EMPLID = $Wrk_Emplid
//		and N1.NAME_TYPE = 'PRI'
//		and N1.EFFDT     = (SELECT MAX(N12.EFFDT) FROM PS_NAMES N12
//		                      WHERE N12.EMPLID   = N1.EMPLID
//		                      AND   N12.NAME_TYPE  = N1.NAME_TYPE
//		                      AND   to_char(N12.EFFDT,'YYYY-MM-DD') <= $Wrk_Effdt)
//		End-Select
//		End-Procedure HR201-Get-Personal-Data		
		return null;
	}


	/**
	 * This routine Gets the nickname for employee and formats it in the form acceptable to the legacy system.
	 * @see HR05-Get-Names-Table in ZHRI105A.SQC
	 * @param employeeId
	 * @return nickname
	 */
	public static String findNicknameByEmployeeIdAndEffectiveDate(String employeeId, Date effectiveDate) {
//		//Begin-Procedure HR05-Get-Names-Table
//		//Let $Found = 'N'
//		//Let $PSNickname = ' '  !Initialize the nickname field to blanks
//		//begin-select
//		//CN5.Name_Type
//		//CN5.FIRST_NAME            !changed for v8.3
//		//Let $PSNickname = ltrim(rtrim(&CN5.FIRST_NAME,' '),' ') !changed for v8.3
//		//Let $PSPrfName = $PSNickname                                 !sree-UAAMOD
//		//Let $PSNickname = substr($PSNickName,1,20)
//		//uppercase $PSNickName
//		//Do Replace-Character($PSNickName,'''','''''',$PSNickName)   !From ZRmvSpcChr.sqc
//		//Let $Found = 'Y'
//		//Let $Wrk_AD_NamesBuild = 'Y'
//		//from PS_Names CN5
//		//where CN5.Emplid = $PSEmplid
//		//and CN5.NAME_TYPE = 'PRF'    !changed for v8.3
//		//and CN5.EFFDT = 
//		//	(SELECT MAX(EFFDT) FROM PS_Names CN6   !added for v8.3
//		//                      WHERE CN6.EMPLID     = CN5.EMPLID      !added for v8.3
//		//                      AND   CN6.NAME_TYPE  = CN5.NAME_TYPE
//		//                      AND   to_char(CN6.EFFDT,'YYYY-MM-DD') <= $PSEffdt) !added for v8.3
//		//end-select
//		//If $Found = 'N'
//		//Let $PSNickName = ' '
//		//End-if    !$Found = 'N'
//		//End-Procedure HR05-Get-Names-Table
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
//		EntityManager em = emfactory.createEntityManager();
//		try {
//			List<PsName> resultList = em.createQuery(
//					"SELECT p.phone FROM PsPersonalPhone p "
//					+ "WHERE UPPER(TRIM(p.employeeId)) = :employeeId "
//					+ "AND UPPER(TRIM(p.phoneType)) = :phoneType "
//					, String.class)
//					.setParameter("employeeId", employeeId.trim().toUpperCase())
//					.setParameter("phoneType", phoneType.trim().toUpperCase())
//	    		    .getResultList();
//	    	if(resultList != null && resultList.size() > 0) {
//	    		return resultList.get(0);
//	    	}
//	    }
//	    catch (Exception e) {
//	    	e.printStackTrace();
//	    } 
//	    finally {
//	    	em.close();
//	    }
	    return null;	
	}

}
package erd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * The persistent class for the PS_EMPLOYEE_REVIEW database table.
 * @author	John Tutton john@tutton.net
 */
@Entity
@Table(name="PS_EMPLOYEE_REVIEW")
@NamedQuery(name="PsEmployeeReview.findAll", query="SELECT p FROM PsEmployeeReview p")
public class PsEmployeeReview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLID", nullable=false, length=11)
	private String employeeId;

	@Column(name="EFFDT", nullable=false)
	private Date effectiveDate;

	@Column(name="EMPL_RCD", nullable=false, precision=38)
	private BigDecimal employmentRecordNumber;

	@Column(name="EMPL_REVW_STATUS", nullable=false, length=1)
	private String emplRevwStatus;

	@Column(name="FINAL_RVW_BAND_CD", nullable=false, length=1)
	private String finalRvwBandCd;

	@Column(name="FP_REV_RATING", nullable=false, precision=5, scale=2)
	private BigDecimal fpRevRating;

	@Column(name="GB_GROUP_ID", nullable=false, length=15)
	private String gbGroupId;

	@Column(name="GVT_OPM_RATING", nullable=false, length=1)
	private String gvtOpmRating;

	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LOADED_TO_JOB", nullable=false, length=1)
	private String loadedToJob;

	@Column(name="NEXT_REVIEW_DT", nullable=false)
	private Date nextReviewDate;

	@Column(name="RATING_SCALE", nullable=false, length=4)
	private String ratingScale;

	@Column(name="RES_RVW_BAND_CD", nullable=false, length=1)
	private String resRvwBandCd;

	@Column(name="REVIEW_FROM_DT")
	private Date reviewFromDt;

	@Column(name="REVIEW_RATING", nullable=false, length=1)
	private String reviewRating;

	@Column(name="REVIEW_THRU_DT")
	private Date reviewThruDt;

	@Column(name="REVIEW_TYPE", nullable=false, length=1)
	private String reviewType;

	@Column(name="TOTAL_EE_AMOUNT", nullable=false, precision=18, scale=6)
	private BigDecimal totalEeAmount;

	@Column(name="TOTAL_EE_PERCENT", nullable=false, precision=5, scale=2)
	private BigDecimal totalEePercent;

	@Column(name="TOTAL_EE_POINTS", nullable=false, precision=38)
	private BigDecimal totalEePoints;

	@Column(name="TOTAL_EE_SAL_PTS", nullable=false, precision=38)
	private BigDecimal totalEeSalPts;

	public PsEmployeeReview() {
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public BigDecimal getEmploymentRecordNumber() {
		return this.employmentRecordNumber;
	}

	public void setEmploymentRecordNumber(BigDecimal employmentRecordNumber) {
		this.employmentRecordNumber = employmentRecordNumber;
	}

	public String getEmplRevwStatus() {
		return this.emplRevwStatus;
	}

	public void setEmplRevwStatus(String emplRevwStatus) {
		this.emplRevwStatus = emplRevwStatus;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFinalRvwBandCd() {
		return this.finalRvwBandCd;
	}

	public void setFinalRvwBandCd(String finalRvwBandCd) {
		this.finalRvwBandCd = finalRvwBandCd;
	}

	public BigDecimal getFpRevRating() {
		return this.fpRevRating;
	}

	public void setFpRevRating(BigDecimal fpRevRating) {
		this.fpRevRating = fpRevRating;
	}

	public String getGbGroupId() {
		return this.gbGroupId;
	}

	public void setGbGroupId(String gbGroupId) {
		this.gbGroupId = gbGroupId;
	}

	public String getGvtOpmRating() {
		return this.gvtOpmRating;
	}

	public void setGvtOpmRating(String gvtOpmRating) {
		this.gvtOpmRating = gvtOpmRating;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLoadedToJob() {
		return this.loadedToJob;
	}

	public void setLoadedToJob(String loadedToJob) {
		this.loadedToJob = loadedToJob;
	}

	public Date getNextReviewDt() {
		return this.nextReviewDate;
	}

	public void setNextReviewDt(Date nextReviewDate) {
		this.nextReviewDate = nextReviewDate;
	}

	public String getRatingScale() {
		return this.ratingScale;
	}

	public void setRatingScale(String ratingScale) {
		this.ratingScale = ratingScale;
	}

	public String getResRvwBandCd() {
		return this.resRvwBandCd;
	}

	public void setResRvwBandCd(String resRvwBandCd) {
		this.resRvwBandCd = resRvwBandCd;
	}

	public Date getReviewFromDt() {
		return this.reviewFromDt;
	}

	public void setReviewFromDt(Date reviewFromDt) {
		this.reviewFromDt = reviewFromDt;
	}

	public String getReviewRating() {
		return this.reviewRating;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}

	public Date getReviewThruDt() {
		return this.reviewThruDt;
	}

	public void setReviewThruDt(Date reviewThruDt) {
		this.reviewThruDt = reviewThruDt;
	}

	public String getReviewType() {
		return this.reviewType;
	}

	public void setReviewType(String reviewType) {
		this.reviewType = reviewType;
	}

	public BigDecimal getTotalEeAmount() {
		return this.totalEeAmount;
	}

	public void setTotalEeAmount(BigDecimal totalEeAmount) {
		this.totalEeAmount = totalEeAmount;
	}

	public BigDecimal getTotalEePercent() {
		return this.totalEePercent;
	}

	public void setTotalEePercent(BigDecimal totalEePercent) {
		this.totalEePercent = totalEePercent;
	}

	public BigDecimal getTotalEePoints() {
		return this.totalEePoints;
	}

	public void setTotalEePoints(BigDecimal totalEePoints) {
		this.totalEePoints = totalEePoints;
	}

	public BigDecimal getTotalEeSalPts() {
		return this.totalEeSalPts;
	}

	public void setTotalEeSalPts(BigDecimal totalEeSalPts) {
		this.totalEeSalPts = totalEeSalPts;
	}

	public PsEmployeeReview findByEmployeeIdAndEmploymentRecordNumberAndEffectiveDate(String employeeId, Date effectiveDate) {
//	!----------------------------------------------------------------------
//	! Procedure:  HR07-Get-Employee-Review
//	! Desc:  This procedure retrieves the Next Review Date and Last Review Date
//	!        from the PeopleSoft Employee Review Table to send back to
//	!        Option 7 of AAHR01 in legacy.
//	!----------------------------------------------------------------------
//	Begin-Procedure HR07-Get-Employee-Review
//	begin-select
//	to_char(CER7.NEXT_REVIEW_DT, 'YYYY-MM-DD')  &CER7NEXT_REVIEW_DT
//	  Let $PSNextReviewDate = &CER7NEXT_REVIEW_DT
//	  Let $LegNextReviewDate = $PSNextReviewDate
//	CER7.EFFDT
//	    Let $LegLastReviewDate = $LegEffdt
//	  !Format next review date and last review date so legacy will accept it (MM field, DD field and CCYY field)
//	  Unstring $PSNextReviewDate by '-' into $LegNxtRevDtYear $LegNxtRevDtMonth $LegNxtRevDtDay
//	  Unstring $LegLastReviewDate by '-' into $LegLstRevDtYear $LegLstRevDtMonth $LegLstRevDtDay
//	from PS_Employee_Review CER7
//	where CER7.Emplid = $PSEMPLID
//	  and CER7.EMPL_RCD = 0     !changed for v8.3
//	  and to_char(CER7.EFFDT, 'YYYY-MM-DD') = $PSEFFDT
//	end-select
//	end-procedure HR07-Get-Employee-Review
    	return null;
	}

}
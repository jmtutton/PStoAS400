CREATE TABLE PS_EMPLOYEE_REVIEW (
    EMPLID            NOT NULL VARCHAR2(11 CHAR),
    EMPL_RCD          NOT NULL NUMBER(38),
    NEXT_REVIEW_DT    NOT NULL DATE,
    EFFDT             NOT NULL DATE,
    REVIEW_FROM_DT             DATE,
    REVIEW_THRU_DT             DATE,
    REVIEW_TYPE       NOT NULL VARCHAR2(1 CHAR),
    RATING_SCALE      NOT NULL VARCHAR2(4 CHAR),
    REVIEW_RATING     NOT NULL VARCHAR2(1 CHAR),
    TOTAL_EE_AMOUNT   NOT NULL NUMBER(18,6),
    TOTAL_EE_PERCENT  NOT NULL NUMBER(5,2),
    TOTAL_EE_POINTS   NOT NULL NUMBER(38),
    TOTAL_EE_SAL_PTS  NOT NULL NUMBER(38),
    EMPL_REVW_STATUS  NOT NULL VARCHAR2(1 CHAR),
    LOADED_TO_JOB     NOT NULL VARCHAR2(1 CHAR),
    RES_RVW_BAND_CD   NOT NULL VARCHAR2(1 CHAR),
    FINAL_RVW_BAND_CD NOT NULL VARCHAR2(1 CHAR),
    GB_GROUP_ID       NOT NULL VARCHAR2(15 CHAR),
    GVT_OPM_RATING    NOT NULL VARCHAR2(1 CHAR),
    FP_REV_RATING     NOT NULL NUMBER(5,2),
    LAST_UPDATE_DATE           DATE
);
DROP TABLE IF EXISTS PS_PERSONAL_DATA;
CREATE TABLE PS_PERSONAL_DATA (
    EMPLID             VARCHAR(11) NOT NULL,
    COUNTRY_NM_FORMAT  VARCHAR(3) NOT NULL,
    NAME               VARCHAR(50) NOT NULL,
    NAME_INITIALS      VARCHAR(6) NOT NULL,
    NAME_PREFIX        VARCHAR(4) NOT NULL,
    NAME_SUFFIX        VARCHAR(15) NOT NULL,
    NAME_ROYAL_PREFIX  VARCHAR(15) NOT NULL,
    NAME_ROYAL_SUFFIX  VARCHAR(15) NOT NULL,
    NAME_TITLE         VARCHAR(30) NOT NULL,
    LAST_NAME_SRCH     VARCHAR(30) NOT NULL,
    FIRST_NAME_SRCH    VARCHAR(30) NOT NULL,
    LAST_NAME          VARCHAR(30) NOT NULL,
    FIRST_NAME         VARCHAR(30) NOT NULL,
    MIDDLE_NAME        VARCHAR(30) NOT NULL,
    SECOND_LAST_NAME   VARCHAR(30) NOT NULL,
    SECOND_LAST_SRCH   VARCHAR(30) NOT NULL,
    NAME_AC            VARCHAR(50) NOT NULL,
    PREF_FIRST_NAME    VARCHAR(30) NOT NULL,
    PARTNER_LAST_NAME  VARCHAR(30) NOT NULL,
    PARTNER_ROY_PREFIX VARCHAR(15) NOT NULL,
    LAST_NAME_PREF_NLD VARCHAR(1) NOT NULL,
    NAME_DISPLAY       VARCHAR(50) NOT NULL,
    NAME_FORMAL        VARCHAR(60) NOT NULL,
    COUNTRY            VARCHAR(3) NOT NULL,
    ADDRESS1           VARCHAR(55) NOT NULL,
    ADDRESS2           VARCHAR(55) NOT NULL,
    ADDRESS3           VARCHAR(55) NOT NULL,
    ADDRESS4           VARCHAR(55) NOT NULL,
    CITY               VARCHAR(30) NOT NULL,
    NUM1               VARCHAR(6) NOT NULL,
    NUM2               VARCHAR(6) NOT NULL,
    HOUSE_TYPE         VARCHAR(2) NOT NULL,
    ADDR_FIELD1        VARCHAR(2) NOT NULL,
    ADDR_FIELD2        VARCHAR(4) NOT NULL,
    ADDR_FIELD3        VARCHAR(4) NOT NULL,
    COUNTY             VARCHAR(30) NOT NULL,
    STATE              VARCHAR(6) NOT NULL,
    POSTAL             VARCHAR(12) NOT NULL,
    GEO_CODE           VARCHAR(11) NOT NULL,
    IN_CITY_LIMIT      VARCHAR(1) NOT NULL,
    SEX                VARCHAR(1) NOT NULL,
    MAR_STATUS         VARCHAR(1) NOT NULL,
    MAR_STATUS_DT      DATE,
    BIRTHDATE          DATE,
    BIRTHPLACE         VARCHAR(30) NOT NULL,
    BIRTHCOUNTRY       VARCHAR(3) NOT NULL,
    BIRTHSTATE         VARCHAR(6) NOT NULL,
    DT_OF_DEATH        DATE,
    HIGHEST_EDUC_LVL   VARCHAR(2) NOT NULL,
    FT_STUDENT         VARCHAR(1) NOT NULL,
    LANG_CD            VARCHAR(3) NOT NULL,
    ALTER_EMPLID       VARCHAR(11) NOT NULL,
    ADDRESS1_AC        VARCHAR(55) NOT NULL,
    ADDRESS2_AC        VARCHAR(55) NOT NULL,
    ADDRESS3_AC        VARCHAR(55) NOT NULL,
    CITY_AC            VARCHAR(30) NOT NULL,
    COUNTRY_OTHER      VARCHAR(3) NOT NULL,
    ADDRESS1_OTHER     VARCHAR(55) NOT NULL,
    ADDRESS2_OTHER     VARCHAR(55) NOT NULL,
    ADDRESS3_OTHER     VARCHAR(55) NOT NULL,
    ADDRESS4_OTHER     VARCHAR(55) NOT NULL,
    CITY_OTHER         VARCHAR(30) NOT NULL,
    COUNTY_OTHER       VARCHAR(30) NOT NULL,
    STATE_OTHER        VARCHAR(6) NOT NULL,
    POSTAL_OTHER       VARCHAR(12) NOT NULL,
    NUM1_OTHER         VARCHAR(6) NOT NULL,
    NUM2_OTHER         VARCHAR(6) NOT NULL,
    HOUSE_TYPE_OTHER   VARCHAR(2) NOT NULL,
    ADDR_FIELD1_OTHER  VARCHAR(2) NOT NULL,
    ADDR_FIELD2_OTHER  VARCHAR(4) NOT NULL,
    ADDR_FIELD3_OTHER  VARCHAR(4) NOT NULL,
    IN_CITY_LMT_OTHER  VARCHAR(1) NOT NULL,
    GEO_CODE_OTHER     VARCHAR(11) NOT NULL,
    COUNTRY_CODE       VARCHAR(3) NOT NULL,
    PHONE              VARCHAR(24) NOT NULL,
    EXTENSION          VARCHAR(6) NOT NULL,
    VA_BENEFIT         VARCHAR(1) NOT NULL,
    CAMPUS_ID          VARCHAR(16) NOT NULL,
    DEATH_CERTIF_NBR   VARCHAR(10) NOT NULL,
    FERPA              VARCHAR(1) NOT NULL,
    PLACE_OF_DEATH     VARCHAR(30) NOT NULL,
    US_WORK_ELIGIBILTY VARCHAR(1) NOT NULL,
    MILITARY_STATUS    VARCHAR(1) NOT NULL,
    CITIZEN_PROOF1     VARCHAR(10) NOT NULL,
    CITIZEN_PROOF2     VARCHAR(10) NOT NULL,
    MEDICARE_ENTLD_DT  DATE,
    HONSEKI_JPN        VARCHAR(2) NOT NULL,
    MILITARY_STAT_ITA  VARCHAR(1) NOT NULL,
    MILITARY_TYPE_ITA  VARCHAR(2) NOT NULL,
    MILITARY_RANK_ITA  VARCHAR(50) NOT NULL,
    MILITARY_END_ITA   DATE,
    ENTRY_DT_FRA       DATE,
    MILIT_SITUATN_FRA  VARCHAR(3) NOT NULL,
    CPAMID             VARCHAR(6) NOT NULL,
    BILINGUALISM_CODE  VARCHAR(1) NOT NULL,
    HEALTH_CARE_NBR    VARCHAR(12) NOT NULL,
    HEALTH_CARE_STATE  VARCHAR(6) NOT NULL,
    MILIT_SITUATN_ESP  VARCHAR(3) NOT NULL,
    SOC_SEC_AFF_DT     DATE,
    MILITARY_STAT_GER  VARCHAR(1) NOT NULL,
    EXPCTD_MILITARY_DT DATE,
    HR_RESPONSIBLE_ID  VARCHAR(11) NOT NULL,
    SMOKER             VARCHAR(1) NOT NULL,
    SMOKER_DT          DATE,
    GVT_CRED_MIL_SVCE  VARCHAR(4) NOT NULL,
    GVT_MILITARY_COMP  VARCHAR(1) NOT NULL,
    GVT_MIL_GRADE      VARCHAR(3) NOT NULL,
    GVT_MIL_RESRVE_CAT VARCHAR(1) NOT NULL,
    GVT_MIL_SEP_RET    VARCHAR(1) NOT NULL,
    GVT_MIL_SVCE_END   DATE,
    GVT_MIL_SVCE_START DATE,
    GVT_MIL_VERIFY     VARCHAR(1) NOT NULL,
    GVT_PAR_NBR_LAST   DECIMAL(38,0) NOT NULL,
    GVT_UNIF_SVC_CTR   VARCHAR(1) NOT NULL,
    GVT_VET_PREF_APPT  VARCHAR(1) NOT NULL,
    GVT_VET_PREF_RIF   VARCHAR(1) NOT NULL,
    GVT_CHANGE_FLAG    VARCHAR(1) NOT NULL,
    GVT_DRAFT_STATUS   VARCHAR(1) NOT NULL,
    GVT_YR_ATTAINED    DATE,
    DISABLED_VET       VARCHAR(1) NOT NULL,
    DISABLED           VARCHAR(1) NOT NULL,
    GVT_DISABILITY_CD  VARCHAR(2) NOT NULL,
    GRADE              VARCHAR(3) NOT NULL,
    SAL_ADMIN_PLAN     VARCHAR(4) NOT NULL,
    GVT_CURR_AGCY_EMPL VARCHAR(1) NOT NULL,
    GVT_CURR_FED_EMPL  VARCHAR(1) NOT NULL,
    GVT_HIGH_PAY_PLAN  VARCHAR(2) NOT NULL,
    GVT_HIGH_GRADE     VARCHAR(3) NOT NULL,
    GVT_PREV_AGCY_EMPL VARCHAR(1) NOT NULL,
    GVT_PREV_FED_EMPL  VARCHAR(1) NOT NULL,
    GVT_SEP_INCENTIVE  VARCHAR(1) NOT NULL,
    GVT_SEP_INCENT_DT  DATE,
    GVT_TENURE         VARCHAR(1) NOT NULL,
    GVT_PAY_PLAN       VARCHAR(2) NOT NULL,
    BARG_UNIT          VARCHAR(4) NOT NULL,
    LASTUPDDTTM        TIMESTAMP
);

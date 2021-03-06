DROP TABLE IF EXISTS PS_ZHRR_MULTPL_EID;
CREATE TABLE PS_ZHRR_MULTPL_EID (
    EMPLID            VARCHAR(11) NOT NULL,
    BUSINESS_UNIT     VARCHAR(5) NOT NULL,
    EFFDT             DATE NOT NULL,
    EFFSEQ            DECIMAL(38,0) NOT NULL,
    EFF_STATUS        VARCHAR(1) NOT NULL,
    ZHRF_GRP_NBR      VARCHAR(2) NOT NULL,
    ZHRF_BRANCH       VARCHAR(2) NOT NULL,
    SEQUENCE          DECIMAL(38,0) NOT NULL,
    ZHRF_LEG_EMPL_ID  VARCHAR(5) NOT NULL,
    ZHRF_ALT_EID_TYPE VARCHAR(2) NOT NULL,
    LASTUPDDTTM       TIMESTAMP,
    LASTUPDOPRID      VARCHAR(30) NOT NULL
);

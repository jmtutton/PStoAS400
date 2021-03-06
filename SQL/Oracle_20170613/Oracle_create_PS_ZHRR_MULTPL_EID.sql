CREATE TABLE PS_ZHRR_MULTPL_EID (
    EMPLID            NOT NULL VARCHAR2(11 CHAR),
    BUSINESS_UNIT     NOT NULL VARCHAR2(5 CHAR),
    EFFDT             NOT NULL DATE,
    EFFSEQ            NOT NULL NUMBER(38),
    EFF_STATUS        NOT NULL VARCHAR2(1 CHAR),
    ZHRF_GRP_NBR      NOT NULL VARCHAR2(2 CHAR),
    ZHRF_BRANCH       NOT NULL VARCHAR2(2 CHAR),
    SEQUENCE          NOT NULL NUMBER(38),
    ZHRF_LEG_EMPL_ID  NOT NULL VARCHAR2(5 CHAR),
    ZHRF_ALT_EID_TYPE NOT NULL VARCHAR2(2 CHAR),
    LASTUPDDTTM                TIMESTAMP(6),
    LASTUPDOPRID      NOT NULL VARCHAR2(30 CHAR)
);
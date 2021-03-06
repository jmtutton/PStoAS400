CREATE TABLE PS_ACTN_REASON_TBL (
    ACTION          NOT NULL VARCHAR2(3 CHAR),
    ACTION_REASON   NOT NULL VARCHAR2(3 CHAR),
    EFFDT           NOT NULL DATE,
    EFF_STATUS      NOT NULL VARCHAR2(1 CHAR),
    DESCR           NOT NULL VARCHAR2(30 CHAR),
    DESCRSHORT      NOT NULL VARCHAR2(10 CHAR),
    OBJECTOWNERID   NOT NULL VARCHAR2(4 CHAR),
    SYSTEM_DATA_FLG NOT NULL VARCHAR2(1 CHAR),
    LASTUPDDTTM              TIMESTAMP(6),
    LASTUPDOPRID    NOT NULL VARCHAR2(30 CHAR)
);

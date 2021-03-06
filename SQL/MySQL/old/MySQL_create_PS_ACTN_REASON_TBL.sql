DROP TABLE IF EXISTS PS_ACTN_REASON_TBL;
CREATE TABLE PS_ACTN_REASON_TBL (
    ACTION          VARCHAR(3) NOT NULL,
    ACTION_REASON   VARCHAR(3) NOT NULL,
    EFFDT           DATE NOT NULL,
    EFF_STATUS      VARCHAR(1) NOT NULL,
    DESCR           VARCHAR(30) NOT NULL,
    DESCRSHORT      VARCHAR(10) NOT NULL,
    OBJECTOWNERID   VARCHAR(4) NOT NULL,
    SYSTEM_DATA_FLG VARCHAR(1) NOT NULL,
    LASTUPDDTTM     TIMESTAMP(6),
    LASTUPDOPRID    VARCHAR(30) NOT NULL
);

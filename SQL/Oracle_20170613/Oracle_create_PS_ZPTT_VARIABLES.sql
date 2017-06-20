CREATE TABLE PS_ZPTT_VARIABLES (
    PRCSNAME          NOT NULL VARCHAR2(12 CHAR),
    DBNAME            NOT NULL VARCHAR2(8 CHAR),
    VARIABLE_NAME     NOT NULL VARCHAR2(15 CHAR),
    ZPTF_VARIABLE_VAL NOT NULL VARCHAR2(100 CHAR),
    LASTUPDOPRID      NOT NULL VARCHAR2(30 CHAR),
    LASTUPDDTTM                TIMESTAMP(6)
);
CREATE TABLE PS_PERS_NID (
    EMPLID           NOT NULL VARCHAR2(11 CHAR),
    COUNTRY          NOT NULL VARCHAR2(3 CHAR),
    NATIONAL_ID_TYPE NOT NULL VARCHAR2(6 CHAR),
    NATIONAL_ID      NOT NULL VARCHAR2(20 CHAR),
    SSN_KEY_FRA      NOT NULL VARCHAR2(2 CHAR),
    PRIMARY_NID      NOT NULL VARCHAR2(1 CHAR),
    TAX_REF_ID_SGP   NOT NULL VARCHAR2(1 CHAR),
    LASTUPDDTTM      NOT NULL TIMESTAMP(6),
    LASTUPDOPRID     NOT NULL VARCHAR2(30 CHAR)
);
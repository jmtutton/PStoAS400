CREATE TABLE PS_PERS_APPL_REF (
    EMPLID             NOT NULL VARCHAR2(11 CHAR),
    EFFDT              NOT NULL DATE,
    HRS_SOURCE_ID      NOT NULL NUMBER(15),
    HRS_SUBSOURCE_ID   NOT NULL NUMBER(15),
    SPECIFIC_REFER_SRC NOT NULL VARCHAR2(50 CHAR),
    EMPL_REFERRAL_ID   NOT NULL VARCHAR2(11 CHAR),
    HRS_PERSON_ID      NOT NULL NUMBER(15),
    HRS_PROFILE_SEQ    NOT NULL NUMBER(38),
    PREV_EMPL_BY_COMPY NOT NULL VARCHAR2(1 CHAR),
    APP_IS_FAMILY      NOT NULL VARCHAR2(1 CHAR),
    RESUME_TEXT_FILE   NOT NULL VARCHAR2(64 CHAR)
);
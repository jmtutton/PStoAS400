CREATE TABLE PS_ADDRESSES (
    EMPLID        NOT NULL VARCHAR2(11 CHAR),
    ADDRESS_TYPE  NOT NULL VARCHAR2(4 CHAR),
    EFFDT         NOT NULL DATE,
    EFF_STATUS    NOT NULL VARCHAR2(1 CHAR),
    COUNTRY       NOT NULL VARCHAR2(3 CHAR),
    ADDRESS1      NOT NULL VARCHAR2(55 CHAR),
    ADDRESS2      NOT NULL VARCHAR2(55 CHAR),
    ADDRESS3      NOT NULL VARCHAR2(55 CHAR),
    ADDRESS4      NOT NULL VARCHAR2(55 CHAR),
    CITY          NOT NULL VARCHAR2(30 CHAR),
    NUM1          NOT NULL VARCHAR2(6 CHAR),
    NUM2          NOT NULL VARCHAR2(6 CHAR),
    HOUSE_TYPE    NOT NULL VARCHAR2(2 CHAR),
    ADDR_FIELD1   NOT NULL VARCHAR2(2 CHAR),
    ADDR_FIELD2   NOT NULL VARCHAR2(4 CHAR),
    ADDR_FIELD3   NOT NULL VARCHAR2(4 CHAR),
    COUNTY        NOT NULL VARCHAR2(30 CHAR),
    STATE         NOT NULL VARCHAR2(6 CHAR),
    POSTAL        NOT NULL VARCHAR2(12 CHAR),
    GEO_CODE      NOT NULL VARCHAR2(11 CHAR),
    IN_CITY_LIMIT NOT NULL VARCHAR2(1 CHAR),
    ADDRESS1_AC   NOT NULL VARCHAR2(55 CHAR),
    ADDRESS2_AC   NOT NULL VARCHAR2(55 CHAR),
    ADDRESS3_AC   NOT NULL VARCHAR2(55 CHAR),
    CITY_AC       NOT NULL VARCHAR2(30 CHAR),
    REG_REGION    NOT NULL VARCHAR2(5 CHAR),
    LASTUPDDTTM            TIMESTAMP(6),
    LASTUPDOPRID  NOT NULL VARCHAR2(30 CHAR)
);

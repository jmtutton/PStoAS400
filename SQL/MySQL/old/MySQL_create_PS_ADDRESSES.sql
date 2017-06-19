DROP TABLE IF EXISTS PS_ADDRESSES;
CREATE TABLE PS_ADDRESSES (
    EMPLID        VARCHAR(11) NOT NULL,
    ADDRESS_TYPE  VARCHAR(4) NOT NULL,
    EFFDT         DATE NOT NULL,
    EFF_STATUS    VARCHAR(1) NOT NULL,
    COUNTRY       VARCHAR(3) NOT NULL,
    ADDRESS1      VARCHAR(55) NOT NULL,
    ADDRESS2      VARCHAR(55) NOT NULL,
    ADDRESS3      VARCHAR(55) NOT NULL,
    ADDRESS4      VARCHAR(55) NOT NULL,
    CITY          VARCHAR(30) NOT NULL,
    NUM1          VARCHAR(6) NOT NULL,
    NUM2          VARCHAR(6) NOT NULL,
    HOUSE_TYPE    VARCHAR(2) NOT NULL,
    ADDR_FIELD1   VARCHAR(2) NOT NULL,
    ADDR_FIELD2   VARCHAR(4) NOT NULL,
    ADDR_FIELD3   VARCHAR(4) NOT NULL,
    COUNTY        VARCHAR(30) NOT NULL,
    STATE         VARCHAR(6) NOT NULL,
    POSTAL        VARCHAR(12) NOT NULL,
    GEO_CODE      VARCHAR(11) NOT NULL,
    IN_CITY_LIMIT VARCHAR(1) NOT NULL,
    ADDRESS1_AC   VARCHAR(55) NOT NULL,
    ADDRESS2_AC   VARCHAR(55) NOT NULL,
    ADDRESS3_AC   VARCHAR(55) NOT NULL,
    CITY_AC       VARCHAR(30) NOT NULL,
    REG_REGION    VARCHAR(5) NOT NULL,
    LASTUPDDTTM   TIMESTAMP,
    LASTUPDOPRID  VARCHAR(30) NOT NULL
);
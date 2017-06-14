DROP TABLE IF EXISTS PS_DIVERS_ETHNIC;
CREATE TABLE PS_DIVERS_ETHNIC (
    EMPLID            VARCHAR(11) NOT NULL PRIMARY KEY,
    REG_REGION        VARCHAR(5) NOT NULL,
    ETHNIC_GRP_CD     VARCHAR(8) NOT NULL,
    SETID             VARCHAR(5) NOT NULL,
    APS_EC_NDS_AUS    VARCHAR(1) NOT NULL,
    PRIMARY_INDICATOR VARCHAR(1) NOT NULL
);

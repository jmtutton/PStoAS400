DROP TABLE IF EXISTS PS_DRIVERS_LIC;
CREATE TABLE PS_DRIVERS_LIC (
    EMPLID           VARCHAR(11) NOT NULL,
    DRIVERS_LIC_NBR  VARCHAR(20) NOT NULL,
    STATE            VARCHAR(6) NOT NULL,
    COUNTRY          VARCHAR(3) NOT NULL,
    VALID_FROM_DT    DATE NOT NULL,
    EXPIRATN_DT      DATE,
    VIOLATIONS       DECIMAL(38,0) NOT NULL,
    NUMBER_OF_PTS    DECIMAL(38,0) NOT NULL,
    LIC_SUSPENDED_SW VARCHAR(1) NOT NULL,
    ISSUED_BY_FRA    VARCHAR(20) NOT NULL,
    ISSUE_DEST_FRA   VARCHAR(20) NOT NULL,
    COMMENTS         BLOB
);

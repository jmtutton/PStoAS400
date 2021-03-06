DROP TABLE IF EXISTS PSXLATITEM;
CREATE TABLE PSXLATITEM (
    FIELDNAME     VARCHAR(18) NOT NULL,
    FIELDVALUE    VARCHAR(4) NOT NULL,
    EFFDT         DATE,
    EFF_STATUS    VARCHAR(1) NOT NULL,
    XLATLONGNAME  VARCHAR(30) NOT NULL,
    XLATSHORTNAME VARCHAR(10) NOT NULL,
    LASTUPDDTTM   TIMESTAMP,
    LASTUPDOPRID  VARCHAR(30) NOT NULL,
    SYNCID        DECIMAL(38,0) NOT NULL
);

DROP TABLE IF EXISTS PSDBOWNER;
CREATE TABLE PSDBOWNER (
    DBNAME VARCHAR(8) NOT NULL,
    OWNERID VARCHAR(8) NOT NULL
);
Insert into PSDBOWNER (DBNAME,OWNERID) values ('PS90HRQA','SYSADM');

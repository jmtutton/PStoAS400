DROP TABLE IF EXISTS PS_ZHRR_MULTPL_EID;
CREATE TABLE PS_ZHRR_MULTPL_EID (
    EMPLID            VARCHAR(11) NOT NULL,
    BUSINESS_UNIT     VARCHAR(5) NOT NULL,
    EFFDT             DATE NOT NULL,
    EFFSEQ            DECIMAL(38,0) NOT NULL,
    EFF_STATUS        VARCHAR(1) NOT NULL,
    ZHRF_GRP_NBR      VARCHAR(2) NOT NULL,
    ZHRF_BRANCH       VARCHAR(2) NOT NULL,
    SEQUENCE          DECIMAL(38,0) NOT NULL,
    ZHRF_LEG_EMPL_ID  VARCHAR(5) NOT NULL,
    ZHRF_ALT_EID_TYPE VARCHAR(2) NOT NULL,
    LASTUPDDTTM       TIMESTAMP,
    LASTUPDOPRID      VARCHAR(30) NOT NULL
);
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('38117','ERA99',STR_TO_DATE('27-APR-17','%d-%b-%y'),0,'A','RD','99',1,'327V2',' ',STR_TO_DATE('27-APR-17 11.15.57.000000000 AM','%d-%b-%y %h.%i.%s.%f %p'),'E5979Z');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343525','SCA99',STR_TO_DATE('19-MAY-17','%d-%b-%y'),1,'A','07','07',2,'99998','FL',STR_TO_DATE('19-MAY-17 11.46.46.000000000 AM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343532',' ',STR_TO_DATE('19-MAY-17','%d-%b-%y'),0,'A','01','01',1,'1005B',' ',STR_TO_DATE('19-MAY-17 03.38.06.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343534',' ',STR_TO_DATE('19-MAY-17','%d-%b-%y'),0,'A','07','07',1,'1008B',' ',STR_TO_DATE('19-MAY-17 05.16.56.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343534',' ',STR_TO_DATE('19-MAY-17','%d-%b-%y'),3,'A','03','01',2,'1009B',' ',STR_TO_DATE('19-MAY-17 05.16.56.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343534',' ',STR_TO_DATE('19-MAY-17','%d-%b-%y'),2,'A','03','01',2,'1009B',' ',STR_TO_DATE('19-MAY-17 05.16.56.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343534',' ',STR_TO_DATE('19-MAY-17','%d-%b-%y'),1,'A','01','01',2,'1009B',' ',STR_TO_DATE('19-MAY-17 05.16.56.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343528','HTNCC',STR_TO_DATE('22-MAY-17','%d-%b-%y'),1,'A','01','03',2,'113V8','VS',STR_TO_DATE('22-MAY-17 03.46.17.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('758798','ERA99',STR_TO_DATE('04-APR-17','%d-%b-%y'),0,'A','F9','IT',1,'295VZ',' ',STR_TO_DATE('04-APR-17 03.05.02.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E370B7');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('819624','ABT99',STR_TO_DATE('25-APR-17','%d-%b-%y'),0,'A','CZ','06',1,'867V1',' ',STR_TO_DATE('25-APR-17 10.50.26.000000000 AM','%d-%b-%y %h.%i.%s.%f %p'),'E93615');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343525','SCA99',STR_TO_DATE('18-MAY-17','%d-%b-%y'),0,'A','01','07',1,'99996',' ',STR_TO_DATE('19-MAY-17 11.46.46.000000000 AM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343527',' ',STR_TO_DATE('18-MAY-17','%d-%b-%y'),0,'A','01','01',1,'99996',' ',STR_TO_DATE('18-MAY-17 12.20.58.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343536',' ',STR_TO_DATE('22-MAY-17','%d-%b-%y'),0,'A','01','03',1,'107V8',' ',STR_TO_DATE('22-MAY-17 03.31.10.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343536',' ',STR_TO_DATE('22-MAY-17','%d-%b-%y'),1,'A','07','03',2,'108V8','VS',STR_TO_DATE('22-MAY-17 03.31.10.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343536',' ',STR_TO_DATE('22-MAY-17','%d-%b-%y'),2,'A','07','07',3,'109V8','FL',STR_TO_DATE('22-MAY-17 03.31.10.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343536',' ',STR_TO_DATE('22-MAY-17','%d-%b-%y'),4,'A','07','09',4,'110V8',' ',STR_TO_DATE('22-MAY-17 03.31.10.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343528','HTNCC',STR_TO_DATE('22-MAY-17','%d-%b-%y'),0,'A','01','01',1,'112V8','FL',STR_TO_DATE('22-MAY-17 03.46.17.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('545061','ERA99',STR_TO_DATE('27-APR-17','%d-%b-%y'),0,'A','RD','99',1,'326V2',' ',STR_TO_DATE('27-APR-17 11.17.47.000000000 AM','%d-%b-%y %h.%i.%s.%f %p'),'E5979Z');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('735862','CCP99',STR_TO_DATE('02-MAY-17','%d-%b-%y'),0,'A','66','06',1,'166V3',' ',STR_TO_DATE('03-MAY-17 10.41.56.000000000 AM','%d-%b-%y %h.%i.%s.%f %p'),'E370B7');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('618352','FSC99',STR_TO_DATE('11-APR-17','%d-%b-%y'),0,'A','1K','99',1,'949VZ',' ',STR_TO_DATE('11-APR-17 11.03.16.000000000 AM','%d-%b-%y %h.%i.%s.%f %p'),'E93615');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343531',' ',STR_TO_DATE('19-MAY-17','%d-%b-%y'),0,'A','09','07',1,'1010B',' ',STR_TO_DATE('19-MAY-17 05.19.07.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343531',' ',STR_TO_DATE('19-MAY-17','%d-%b-%y'),1,'A','01','09',2,'1011B',' ',STR_TO_DATE('19-MAY-17 05.19.07.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
Insert into PS_ZHRR_MULTPL_EID (EMPLID,BUSINESS_UNIT,EFFDT,EFFSEQ,EFF_STATUS,ZHRF_GRP_NBR,ZHRF_BRANCH,SEQUENCE,ZHRF_LEG_EMPL_ID,ZHRF_ALT_EID_TYPE,LASTUPDDTTM,LASTUPDOPRID) values ('343528','HTNCC',STR_TO_DATE('22-MAY-17','%d-%b-%y'),2,'A','01','07',3,'114V8',' ',STR_TO_DATE('22-MAY-17 03.46.17.000000000 PM','%d-%b-%y %h.%i.%s.%f %p'),'E208T1');
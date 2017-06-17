DROP TABLE IF EXISTS PS_COUNTRY_TBL;
CREATE TABLE PS_COUNTRY_TBL (
    COUNTRY          VARCHAR(3) NOT NULL,
    DESCR            VARCHAR(30) NOT NULL,
    DESCRSHORT       VARCHAR(10) NOT NULL,
    COUNTRY_2CHAR    VARCHAR(2) NOT NULL,
    EU_MEMBER_STATE  VARCHAR(1) NOT NULL,
    POST_SRCH_AVAIL  VARCHAR(1) NOT NULL,
    ADDR_VALIDAT     VARCHAR(1) NOT NULL,
    EO_SEC_PAGE_NAME VARCHAR(18) NOT NULL
);

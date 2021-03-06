DROP TABLE IF EXISTS PS_PERS_DATA_EFFDT;
CREATE TABLE PS_PERS_DATA_EFFDT (
    EMPLID           VARCHAR(11) NOT NULL,
    EFFDT            DATE NOT NULL,
    MAR_STATUS       VARCHAR(1) NOT NULL,
    MAR_STATUS_DT    DATE,
    SEX              VARCHAR(1) NOT NULL,
    HIGHEST_EDUC_LVL VARCHAR(2) NOT NULL,
    FT_STUDENT       VARCHAR(1) NOT NULL,
    LANG_CD          VARCHAR(3) NOT NULL,
    ALTER_EMPLID     VARCHAR(11) NOT NULL,
    LASTUPDDTTM      TIMESTAMP,
    LASTUPDOPRID     VARCHAR(30) NOT NULL
);

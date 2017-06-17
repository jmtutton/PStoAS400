DROP TABLE IF EXISTS PS_ZHRT_ALTTRIGGER;
CREATE TABLE PS_ZHRT_ALTTRIGGER (
    SEQ_NBR   DECIMAL(15,0) NOT NULL,
    OPRID     VARCHAR(30) NOT NULL,
    EMPLID    VARCHAR(11) NOT NULL,
    EFFDT     DATE,
    EFFSEQ    DECIMAL(38,0) NOT NULL,
    PROC_NAME VARCHAR(10) NOT NULL,
    TASK_FLAG VARCHAR(1) NOT NULL,
    SEQUENCE  DECIMAL(38,0)
);

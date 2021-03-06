CREATE TABLE PS_JOB (
    EMPLID             NOT NULL VARCHAR2(11 CHAR),
    EMPL_RCD           NOT NULL NUMBER(38),
    EFFDT              NOT NULL DATE,
    EFFSEQ             NOT NULL NUMBER(38),
    PER_ORG            NOT NULL VARCHAR2(3 CHAR),
    DEPTID             NOT NULL VARCHAR2(10 CHAR),
    JOBCODE            NOT NULL VARCHAR2(6 CHAR),
    POSITION_NBR       NOT NULL VARCHAR2(8 CHAR),
    SUPERVISOR_ID      NOT NULL VARCHAR2(11 CHAR),
    HR_STATUS          NOT NULL VARCHAR2(1 CHAR),
    APPT_TYPE          NOT NULL VARCHAR2(1 CHAR),
    MAIN_APPT_NUM_JPN  NOT NULL NUMBER(38),
    POSITION_OVERRIDE  NOT NULL VARCHAR2(1 CHAR),
    POSN_CHANGE_RECORD NOT NULL VARCHAR2(1 CHAR),
    EMPL_STATUS        NOT NULL VARCHAR2(1 CHAR),
    ACTION             NOT NULL VARCHAR2(3 CHAR),
    ACTION_DT                   DATE,
    ACTION_REASON      NOT NULL VARCHAR2(3 CHAR),
    LOCATION           NOT NULL VARCHAR2(10 CHAR),
    TAX_LOCATION_CD    NOT NULL VARCHAR2(10 CHAR),
    JOB_ENTRY_DT                DATE,
    DEPT_ENTRY_DT               DATE,
    POSITION_ENTRY_DT           DATE,
    SHIFT              NOT NULL VARCHAR2(1 CHAR),
    REG_TEMP           NOT NULL VARCHAR2(1 CHAR),
    FULL_PART_TIME     NOT NULL VARCHAR2(1 CHAR),
    COMPANY            NOT NULL VARCHAR2(3 CHAR),
    PAYGROUP           NOT NULL VARCHAR2(3 CHAR),
    BAS_GROUP_ID       NOT NULL VARCHAR2(3 CHAR),
    ELIG_CONFIG1       NOT NULL VARCHAR2(10 CHAR),
    ELIG_CONFIG2       NOT NULL VARCHAR2(10 CHAR),
    ELIG_CONFIG3       NOT NULL VARCHAR2(10 CHAR),
    ELIG_CONFIG4       NOT NULL VARCHAR2(10 CHAR),
    ELIG_CONFIG5       NOT NULL VARCHAR2(10 CHAR),
    ELIG_CONFIG6       NOT NULL VARCHAR2(10 CHAR),
    ELIG_CONFIG7       NOT NULL VARCHAR2(10 CHAR),
    ELIG_CONFIG8       NOT NULL VARCHAR2(10 CHAR),
    ELIG_CONFIG9       NOT NULL VARCHAR2(10 CHAR),
    BEN_STATUS         NOT NULL VARCHAR2(4 CHAR),
    BAS_ACTION         NOT NULL VARCHAR2(3 CHAR),
    COBRA_ACTION       NOT NULL VARCHAR2(3 CHAR),
    EMPL_TYPE          NOT NULL VARCHAR2(1 CHAR),
    HOLIDAY_SCHEDULE   NOT NULL VARCHAR2(6 CHAR),
    STD_HOURS          NOT NULL NUMBER(6,2),
    STD_HRS_FREQUENCY  NOT NULL VARCHAR2(5 CHAR),
    OFFICER_CD         NOT NULL VARCHAR2(1 CHAR),
    EMPL_CLASS         NOT NULL VARCHAR2(3 CHAR),
    SAL_ADMIN_PLAN     NOT NULL VARCHAR2(4 CHAR),
    GRADE              NOT NULL VARCHAR2(3 CHAR),
    GRADE_ENTRY_DT              DATE,
    STEP               NOT NULL NUMBER(38),
    STEP_ENTRY_DT               DATE,
    GL_PAY_TYPE        NOT NULL VARCHAR2(6 CHAR),
    ACCT_CD            NOT NULL VARCHAR2(25 CHAR),
    EARNS_DIST_TYPE    NOT NULL VARCHAR2(1 CHAR),
    COMP_FREQUENCY     NOT NULL VARCHAR2(5 CHAR),
    COMPRATE           NOT NULL NUMBER(18,6),
    CHANGE_AMT         NOT NULL NUMBER(18,6),
    CHANGE_PCT         NOT NULL NUMBER(6,3),
    ANNUAL_RT          NOT NULL NUMBER(18,3),
    MONTHLY_RT         NOT NULL NUMBER(18,3),
    DAILY_RT           NOT NULL NUMBER(18,3),
    HOURLY_RT          NOT NULL NUMBER(18,6),
    ANNL_BENEF_BASE_RT NOT NULL NUMBER(18,3),
    SHIFT_RT           NOT NULL NUMBER(18,6),
    SHIFT_FACTOR       NOT NULL NUMBER(4,3),
    CURRENCY_CD        NOT NULL VARCHAR2(3 CHAR),
    BUSINESS_UNIT      NOT NULL VARCHAR2(5 CHAR),
    SETID_DEPT         NOT NULL VARCHAR2(5 CHAR),
    SETID_JOBCODE      NOT NULL VARCHAR2(5 CHAR),
    SETID_LOCATION     NOT NULL VARCHAR2(5 CHAR),
    SETID_SALARY       NOT NULL VARCHAR2(5 CHAR),
    REG_REGION         NOT NULL VARCHAR2(5 CHAR),
    DIRECTLY_TIPPED    NOT NULL VARCHAR2(1 CHAR),
    FLSA_STATUS        NOT NULL VARCHAR2(1 CHAR),
    EEO_CLASS          NOT NULL VARCHAR2(1 CHAR),
    FUNCTION_CD        NOT NULL VARCHAR2(2 CHAR),
    TARIFF_GER         NOT NULL VARCHAR2(2 CHAR),
    TARIFF_AREA_GER    NOT NULL VARCHAR2(3 CHAR),
    PERFORM_GROUP_GER  NOT NULL VARCHAR2(2 CHAR),
    LABOR_TYPE_GER     NOT NULL VARCHAR2(1 CHAR),
    SPK_COMM_ID_GER    NOT NULL VARCHAR2(9 CHAR),
    HOURLY_RT_FRA      NOT NULL VARCHAR2(3 CHAR),
    ACCDNT_CD_FRA      NOT NULL VARCHAR2(1 CHAR),
    VALUE_1_FRA        NOT NULL VARCHAR2(5 CHAR),
    VALUE_2_FRA        NOT NULL VARCHAR2(5 CHAR),
    VALUE_3_FRA        NOT NULL VARCHAR2(5 CHAR),
    VALUE_4_FRA        NOT NULL VARCHAR2(5 CHAR),
    VALUE_5_FRA        NOT NULL VARCHAR2(5 CHAR),
    CTG_RATE           NOT NULL NUMBER(38),
    PAID_HOURS         NOT NULL NUMBER(6,2),
    PAID_FTE           NOT NULL NUMBER(7,6),
    PAID_HRS_FREQUENCY NOT NULL VARCHAR2(5 CHAR),
    UNION_FULL_PART    NOT NULL VARCHAR2(1 CHAR),
    UNION_POS          NOT NULL VARCHAR2(1 CHAR),
    MATRICULA_NBR      NOT NULL NUMBER(38),
    SOC_SEC_RISK_CODE  NOT NULL VARCHAR2(3 CHAR),
    UNION_FEE_AMOUNT   NOT NULL NUMBER(8,2),
    UNION_FEE_START_DT          DATE,
    UNION_FEE_END_DT            DATE,
    EXEMPT_JOB_LBR     NOT NULL VARCHAR2(1 CHAR),
    EXEMPT_HOURS_MONTH NOT NULL NUMBER(38),
    WRKS_CNCL_FUNCTION NOT NULL VARCHAR2(1 CHAR),
    INTERCTR_WRKS_CNCL NOT NULL VARCHAR2(1 CHAR),
    CURRENCY_CD1       NOT NULL VARCHAR2(3 CHAR),
    PAY_UNION_FEE      NOT NULL VARCHAR2(1 CHAR),
    UNION_CD           NOT NULL VARCHAR2(3 CHAR),
    BARG_UNIT          NOT NULL VARCHAR2(4 CHAR),
    UNION_SENIORITY_DT          DATE,
    ENTRY_DATE                  DATE,
    LABOR_AGREEMENT    NOT NULL VARCHAR2(6 CHAR),
    EMPL_CTG           NOT NULL VARCHAR2(6 CHAR),
    EMPL_CTG_L1        NOT NULL VARCHAR2(6 CHAR),
    EMPL_CTG_L2        NOT NULL VARCHAR2(6 CHAR),
    SETID_LBR_AGRMNT   NOT NULL VARCHAR2(5 CHAR),
    WPP_STOP_FLAG      NOT NULL VARCHAR2(1 CHAR),
    LABOR_FACILITY_ID  NOT NULL VARCHAR2(10 CHAR),
    LBR_FAC_ENTRY_DT            DATE,
    LAYOFF_EXEMPT_FLAG NOT NULL VARCHAR2(1 CHAR),
    LAYOFF_EXEMPT_RSN  NOT NULL VARCHAR2(11 CHAR),
    GP_PAYGROUP        NOT NULL VARCHAR2(10 CHAR),
    GP_DFLT_ELIG_GRP   NOT NULL VARCHAR2(1 CHAR),
    GP_ELIG_GRP        NOT NULL VARCHAR2(10 CHAR),
    GP_DFLT_CURRTTYP   NOT NULL VARCHAR2(1 CHAR),
    CUR_RT_TYPE        NOT NULL VARCHAR2(5 CHAR),
    GP_DFLT_EXRTDT     NOT NULL VARCHAR2(1 CHAR),
    GP_ASOF_DT_EXG_RT  NOT NULL VARCHAR2(1 CHAR),
    ADDS_TO_FTE_ACTUAL NOT NULL VARCHAR2(1 CHAR),
    CLASS_INDC         NOT NULL VARCHAR2(1 CHAR),
    ENCUMB_OVERRIDE    NOT NULL VARCHAR2(1 CHAR),
    FICA_STATUS_EE     NOT NULL VARCHAR2(1 CHAR),
    FTE                NOT NULL NUMBER(7,6),
    PRORATE_CNT_AMT    NOT NULL VARCHAR2(1 CHAR),
    PAY_SYSTEM_FLG     NOT NULL VARCHAR2(2 CHAR),
    BORDER_WALKER      NOT NULL VARCHAR2(1 CHAR),
    LUMP_SUM_PAY       NOT NULL VARCHAR2(1 CHAR),
    CONTRACT_NUM       NOT NULL VARCHAR2(25 CHAR),
    JOB_INDICATOR      NOT NULL VARCHAR2(1 CHAR),
    WRKS_CNCL_ROLE_CHE NOT NULL VARCHAR2(30 CHAR),
    BENEFIT_SYSTEM     NOT NULL VARCHAR2(2 CHAR),
    WORK_DAY_HOURS     NOT NULL NUMBER(6,2),
    REPORTS_TO         NOT NULL VARCHAR2(8 CHAR),
    FORCE_PUBLISH               DATE,
    JOB_DATA_SRC_CD    NOT NULL VARCHAR2(3 CHAR),
    ESTABID            NOT NULL VARCHAR2(12 CHAR),
    SUPV_LVL_ID        NOT NULL VARCHAR2(8 CHAR),
    SETID_SUPV_LVL     NOT NULL VARCHAR2(5 CHAR),
    ABSENCE_SYSTEM_CD  NOT NULL VARCHAR2(3 CHAR),
    POI_TYPE           NOT NULL VARCHAR2(5 CHAR),
    HIRE_DT                     DATE,
    LAST_HIRE_DT                DATE,
    TERMINATION_DT              DATE,
    ASGN_START_DT               DATE,
    LST_ASGN_START_DT           DATE,
    ASGN_END_DT                 DATE,
    LDW_OVR            NOT NULL VARCHAR2(1 CHAR),
    LAST_DATE_WORKED            DATE,
    EXPECTED_RETURN_DT          DATE,
    EXPECTED_END_DATE           DATE,
    AUTO_END_FLG       NOT NULL VARCHAR2(1 CHAR),
    LASTUPDDTTM                 TIMESTAMP(6),
    LASTUPDOPRID       NOT NULL VARCHAR2(30 CHAR)
);
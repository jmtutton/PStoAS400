DROP TABLE IF EXISTS PS_PERSON;
CREATE TABLE PS_PERSON (
    EMPLID            VARCHAR(11) NOT NULL,
    BIRTHDATE         DATE NOT NULL,
    BIRTHPLACE        VARCHAR(30) NOT NULL,
    BIRTHCOUNTRY      VARCHAR(3) NOT NULL,
    BIRTHSTATE        VARCHAR(6) NOT NULL,
    DT_OF_DEATH       DATE,
    LAST_CHILD_UPDDTM DATE
);
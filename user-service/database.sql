DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS(
                      ID SERIAL,
                      NAME VARCHAR(25) NOT NULL,
                      SURNAME VARCHAR(25),
                      USERNAME VARCHAR(50) NOT NULL UNIQUE,
                      PASSWORD VARCHAR(100) NOT NULL,
                      EMAIL VARCHAR(100) NOT NULL,
                      PHONE VARCHAR(25) NOT NULL,
                      ROL VARCHAR(25) NOT NULL,
                      CATEGORY VARCHAR(50),
                      ADDRESS_ID BIGINT,
                      CONSTRAINT PK_USERS PRIMARY KEY(id),
                      CONSTRAINT FK_USER_ADDRESS
                          FOREIGN KEY(ADDRESS_ID)
                              REFERENCES ADDRESS(id));
SELECT * FROM USERS;


DROP TABLE IF EXISTS STUDENT;
CREATE TABLE STUDENT(
                        id SMALLINT NOT NULL,
                        SEX VARCHAR(10) NOT NULL,
                        BIRTHDAY DATE  NOT NULL,
                        DESCRIPTION VARCHAR(500),
                        UNIVERSITY VARCHAR(50),
                        STUDENT_ID VARCHAR(25),
                        ADDRESS_ID BIGINT,
                        CONSTRAINT PK_USER PRIMARY KEY(ID),
                        CONSTRAINT FK_STUDENT_USER
                            FOREIGN KEY(ID) REFERENCES USERS(ID));
SELECT * FROM STUDENT;


DROP TABLE IF EXISTS EMPLOYER;
CREATE TABLE EMPLOYER(
                 ID SMALLINT NOT NULL,
                 IS_COMPANY BOOLEAN NOT NULL DEFAULT FALSE;
                 CONSTRAINT PK_EMPLOYER PRIMARY KEY(id),
                 CONSTRAINT FK_EMPLOYER_USER FOREIGN KEY(id)  REFERENCES USERS(id));
COMMIT;
SELECT * FROM EMPLOYER;

DROP TABLE IF EXISTS ADDRESS;
CREATE TABLE ADDRESS(
                        id SMALLINT NOT NULL,
                        CITY VARCHAR(50),
                        ZIPCODE VARCHAR(50),
                        PROVINCE VARCHAR(50),
                        COUNTRY VARCHAR(50),
                        CONSTRAINT PK_ADDRESS PRIMARY KEY(id));

COMMIT;
SELECT * FROM ADDRESS;

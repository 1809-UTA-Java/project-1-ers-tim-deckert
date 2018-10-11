CREATE TABLE ERS_USERS (
    U_ID NUMBER(*,0) PRIMARY KEY,
    U_USERNAME VARCHAR2(40 BYTE) UNIQUE,
    U_PASSWORD VARCHAR2 (40 BYTE),
    U_FIRSTNAME VARCHAR2 (30 BYTE),
    U_LASTNAME VARCHAR2 (30 BYTE),
    U_EMAIL VARCHAR (100 BYTE) UNIQUE,
    UR_ID NUMBER (*,0)
);

CREATE TABLE ERS_USER_ROLES (
    UR_ID NUMBER (*,0) PRIMARY KEY,
    UR_ROLE VARCHAR2 (40 BYTE)
);

CREATE TABLE ERS_REIMBURSEMENTS (
    R_ID NUMBER (*, 0) PRIMARY KEY,
    R_AMOUNT NUMBER (22,2),
    R_DESCRIPTION VARCHAR2 (100 BYTE),
    R_RECIEPT BLOB,
    R_SUMBITTED TIMESTAMP,
    R_RESEOLVED TIMESTAMP,
    U_ID_AUTHOR NUMBER (*, 0),
    U_ID_RESOLVER NUMBER (*, 0),
    RT_TYPE NUMBER (*, 0),
    RT_STATUS NUMBER (*, 0)
);

CREATE TABLE ERS_REIMBURSEMENT_STATUS (
    RS_ID NUMBER (*, 0) PRIMARY KEY,
    RS_STATUS VARCHAR2 (30 BYTE),
);

CREATE TABLE ERS_REIMBURSEMENT_TYPE (
    RT_ID NUMBER (*, 0),
    RT_TYPE VARCHAR2 (30 BYTE)
);

ALTER TABLE ERS_USERS ADD CONSTRAINT ERS_USERS_FK_UR_ID 
FOREIGN KEY (UR_ID) REFERENCES ERS_USER_ROLES (UR_ID);

ALTER TABLE ERS_REIMBURSEMENTS ADD CONSTRAINT ERS_REIMBURSEMENTS_FK_AUTHOR
FOREIGN KEY (U_ID_AUTHOR) REFERENCES ERS_USERS (U_ID);

ALTER TABLE ERS_REIMBURSEMENTS ADD CONSTRAINT ERS_REIMBURSEMENTS_FK_RESOLVER
FOREIGN KEY (U_ID_RESOLVER) REFERENCES ERS_USERS (U_ID);

ALTER TABLE ERS_REIMBURSEMENTS ADD CONSTRAINT ERS_REIMBURSEMENTS_FK_TYPE
FOREIGN KEY (RT_TYPE) REFERENCES ERS_REIMBURSEMENT_TYPE (RT_ID);

ALTER TABLE ERS_REIMBURSEMENTS ADD CONSTRAINT ERS_REIMBURSEMENTS_FK_STATUS
FOREIGN KEY (RT_STATUS) REFERENCES ERS_REIMBURSEMENT_STATUS (RS_ID);
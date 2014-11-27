SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS POST;
DROP TABLE IF EXISTS QUERY;
DROP TABLE IF EXISTS MEDICAL_INFORMATION;
DROP TABLE IF EXISTS PATIENT;
DROP TABLE IF EXISTS CORP_ACCOUNT;
DROP TABLE IF EXISTS ACCOUNT;
DROP TABLE IF EXISTS ADMIN;
DROP TABLE IF EXISTS CORPORATION;
DROP TABLE IF EXISTS DOCTOR_SPECIALITY;
DROP TABLE IF EXISTS DOCTOR;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS CONTACT_INFO;
DROP TABLE IF EXISTS ROLE;
DROP TABLE IF EXISTS SERVICE_PLAN;
DROP TABLE IF EXISTS SPECIALITIES;




/* Create Tables */

CREATE TABLE ACCOUNT
(
	ACCOUNT_ID int(10) unsigned NOT NULL AUTO_INCREMENT,
	ADMIN_ID int unsigned,
	SERVICE_PLAN_ID int(10) unsigned NOT NULL,
	CREATED_ON_DATE datetime DEFAULT NOW() NOT NULL,
	UPDATED_ON_DATE datetime NOT NULL,
	PRIMARY KEY (ACCOUNT_ID)
);


CREATE TABLE ADMIN
(
	ADMIN_ID int unsigned NOT NULL UNIQUE,
	PRIMARY KEY (ADMIN_ID)
);


CREATE TABLE CONTACT_INFO
(
	CONTACT_ID int(10) unsigned NOT NULL AUTO_INCREMENT,
	EMAIL varchar(100) NOT NULL,
	PRIMARY_PHONE char(10) NOT NULL,
	SECONDARY_PHONE char(10),
	CREATED_ON_DATE datetime DEFAULT NOW() NOT NULL,
	UPDATED_ON_DATE datetime NOT NULL,
	PRIMARY KEY (CONTACT_ID)
);


CREATE TABLE CORPORATION
(
	CORP_ID int(10) unsigned NOT NULL AUTO_INCREMENT,
	Name varchar(100) NOT NULL,
	CONTACT_ID int(10) unsigned NOT NULL,
	DOMAIN varchar(30),
	ADMIN int unsigned,
	CREATED_ON_DATE datetime DEFAULT NOW() NOT NULL,
	UPDATED_ON_DATE datetime NOT NULL,
	PRIMARY KEY (CORP_ID)
);


CREATE TABLE CORP_ACCOUNT
(
	CORP_MAIL_ID varchar(50) NOT NULL,
	CORP_ID int(10) unsigned NOT NULL,
	ACCOUNT_ID int(10) unsigned NOT NULL,
	PRIMARY KEY (CORP_MAIL_ID)
);


CREATE TABLE DOCTOR
(
	USER_ID int unsigned NOT NULL,
	PRIMARY KEY (USER_ID)
);


CREATE TABLE DOCTOR_SPECIALITY
(
	DOCTOR_ID int unsigned NOT NULL,
	SPECIALITY varchar(100) NOT NULL,
	PRIMARY KEY (DOCTOR_ID, SPECIALITY)
);


CREATE TABLE MEDICAL_INFORMATION
(
	AGE tinyint(3) unsigned NOT NULL,
	GENDER enum('MALE', 'FEMALE','OTHER') NOT NULL,
	WEIGHT tinyint(3) unsigned,
	ALLERGIES varchar(1000),
	USER_ID int unsigned NOT NULL,
	PRIMARY KEY (USER_ID)
);


CREATE TABLE PATIENT
(
	USER_ID int unsigned NOT NULL,
	ACCOUNT_ID int(10) unsigned NOT NULL,
	PRIMARY KEY (USER_ID),
	UNIQUE (USER_ID)
);


CREATE TABLE POST
(
	ID int unsigned NOT NULL AUTO_INCREMENT,
	TEXT longtext NOT NULL,
	QUERY_ID int NOT NULL,
	CREATED_ON_DATE datetime DEFAULT NOW() NOT NULL,
	UPDATED_ON_DATE datetime NOT NULL,
	PARENT_ID int unsigned NOT NULL,
	USER_ID int unsigned,
	POST_BY enum('DOCTOR', 'PATIENT', 'ADMIN') NOT NULL,
	PRIMARY KEY (ID)
);


CREATE TABLE QUERY
(
	ID int NOT NULL AUTO_INCREMENT,
	DOCTOR_ID int unsigned,
	PATIENT_ID int unsigned NOT NULL,
	CREATED_ON_DATE datetime DEFAULT NOW() NOT NULL,
	UPDATED_ON_DATE datetime NOT NULL,
	PRIMARY KEY (ID)
);


CREATE TABLE ROLE
(
	ROLE_NAME enum('DOCTOR', 'PATIENT', 'ADMIN') NOT NULL,
	PRIMARY KEY (ROLE_NAME)
);


CREATE TABLE SERVICE_PLAN
(
	SERVICE_PLAN_ID int(10) unsigned NOT NULL AUTO_INCREMENT,
	DURATION enum('HALF-YEARLY', 'YEARLY') NOT NULL,
	DEPENDENTS tinyint(2) unsigned NOT NULL,
	FEE int(5) unsigned,
	CREATED_ON_DATE datetime DEFAULT NOW() NOT NULL,
	UPDATED_ON_DATE datetime NOT NULL,
	PRIMARY KEY (SERVICE_PLAN_ID)
);


CREATE TABLE SPECIALITIES
(
	SPECIALITY varchar(100) NOT NULL,
	PRIMARY KEY (SPECIALITY)
);


-- Users of Econsult
CREATE TABLE USER
(
	USER_ID int unsigned NOT NULL UNIQUE AUTO_INCREMENT,
	FIRST_NAME varchar(50) NOT NULL,
	LAST_NAME varchar(50),
	PASSWORD varchar(100),
	CREATED_ON_DATE datetime DEFAULT NOW() NOT NULL,
	UPDATED_ON_DATE datetime NOT NULL,
	ROLE_NAME enum('DOCTOR', 'PATIENT', 'ADMIN') NOT NULL,
	CONTACT_ID int(10) unsigned NOT NULL,
	LOGIN_NAME varchar(50) UNIQUE,
	LOGIN_TYPE enum('FB','GOOGLE','BASIC'),
	PRIMARY KEY (USER_ID)
) COMMENT = 'Users of Econsult';



/* Create Foreign Keys */

ALTER TABLE PATIENT
	ADD FOREIGN KEY (ACCOUNT_ID)
	REFERENCES ACCOUNT (ACCOUNT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CORP_ACCOUNT
	ADD FOREIGN KEY (ACCOUNT_ID)
	REFERENCES ACCOUNT (ACCOUNT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CORPORATION
	ADD FOREIGN KEY (CONTACT_ID)
	REFERENCES CONTACT_INFO (CONTACT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE USER
	ADD FOREIGN KEY (CONTACT_ID)
	REFERENCES CONTACT_INFO (CONTACT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CORP_ACCOUNT
	ADD FOREIGN KEY (CORP_ID)
	REFERENCES CORPORATION (CORP_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE DOCTOR_SPECIALITY
	ADD FOREIGN KEY (DOCTOR_ID)
	REFERENCES DOCTOR (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE QUERY
	ADD FOREIGN KEY (DOCTOR_ID)
	REFERENCES DOCTOR (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE QUERY
	ADD FOREIGN KEY (PATIENT_ID)
	REFERENCES PATIENT (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MEDICAL_INFORMATION
	ADD FOREIGN KEY (USER_ID)
	REFERENCES PATIENT (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POST
	ADD FOREIGN KEY (PARENT_ID)
	REFERENCES POST (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POST
	ADD FOREIGN KEY (QUERY_ID)
	REFERENCES QUERY (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE USER
	ADD FOREIGN KEY (ROLE_NAME)
	REFERENCES ROLE (ROLE_NAME)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POST
	ADD FOREIGN KEY (POST_BY)
	REFERENCES ROLE (ROLE_NAME)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ACCOUNT
	ADD FOREIGN KEY (SERVICE_PLAN_ID)
	REFERENCES SERVICE_PLAN (SERVICE_PLAN_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE DOCTOR_SPECIALITY
	ADD FOREIGN KEY (SPECIALITY)
	REFERENCES SPECIALITIES (SPECIALITY)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ACCOUNT
	ADD FOREIGN KEY (ADMIN_ID)
	REFERENCES USER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE DOCTOR
	ADD FOREIGN KEY (USER_ID)
	REFERENCES USER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PATIENT
	ADD FOREIGN KEY (USER_ID)
	REFERENCES USER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CORPORATION
	ADD FOREIGN KEY (ADMIN)
	REFERENCES USER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE POST
	ADD FOREIGN KEY (USER_ID)
	REFERENCES USER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ADMIN
	ADD FOREIGN KEY (ADMIN_ID)
	REFERENCES USER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;




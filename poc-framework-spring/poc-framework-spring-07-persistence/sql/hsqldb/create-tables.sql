DROP table PERSON if exists

CREATE table PERSON
  (ID integer identity primary key,
  FIRSTNAME varchar(40) NOT NULL,
  LASTNAME varchar(40) NOT NULL,
  BIRTHDATE DATE NOT NULL);

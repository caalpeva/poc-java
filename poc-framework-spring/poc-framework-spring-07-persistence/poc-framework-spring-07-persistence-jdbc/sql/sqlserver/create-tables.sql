create table PERSON
  (ID  integer NOT NULL IDENTITY,
  FIRSTNAME varchar(50) NOT NULL,
  LASTNAME varchar(50) NOT NULL,
  BIRTHDATE date null,
  PRIMARY KEY(ID));

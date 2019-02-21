SET IDENTITY_INSERT PERSON ON
INSERT PERSON (id, name, surname, email, password) VALUES (1, 'administrator', '1', 'admin', 'admin');
INSERT PERSON (id, name, surname, email, password) VALUES (2, 'administrator', '2', 'admin2', 'bbaa77225460b8d3ed8e7a560068051b'); --// admin2 encrypted with md5
INSERT PERSON (id, name, surname, birthDate, email) VALUES (3, 'Dan', 'Aykroyd', null, 'daykroyd@mailinator.com');
INSERT PERSON (id, name, surname, birthDate, email) VALUES (4, 'Bill', 'Murray', null, 'bmurray@mailinator.com');
INSERT PERSON (id, name, surname, birthDate, email) VALUES (5, 'Harold', 'Ramis', null, 'hramis@mailinator.com');
INSERT PERSON (id, name, surname, birthDate, email) VALUES (6, 'Ernie', 'Hudson', null, 'ehudson@mailinator.com');
INSERT PERSON (id, name, surname, birthDate, email) VALUES (7, 'Sigourney', 'Weaver', null, 'sweaver@mailinator.com');
SET IDENTITY_INSERT PERSON OFF

SET IDENTITY_INSERT PRIVILEGE ON
INSERT INTO PRIVILEGE (id, person_id, permission) VALUES (1, 1, 'ROLE_ADMIN');
INSERT INTO PRIVILEGE (id, person_id, permission) VALUES (2, 2, 'ROLE_ADMIN');
SET IDENTITY_INSERT PRIVILEGE OFF
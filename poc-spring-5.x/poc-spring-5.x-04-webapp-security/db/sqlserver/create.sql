--// http://www.sqlines.com/online
--// Tabla de usuarios y autoridades por defecto de Spring Security
-- Crear tabla de usuarios
CREATE TABLE users (
	[username] varchar(50) NOT NULL,
	[password] varchar(50) NOT NULL,
	[enabled] smallint NOT NULL,
	PRIMARY KEY ([username])
) ;

-- Crear tabla de roles
CREATE TABLE authorities (
	[username] varchar(50) NOT NULL,
	[authority] varchar(50) NOT NULL,
	CONSTRAINT [authorities_idx_1] UNIQUE  ([username],[authority]),
	CONSTRAINT [authorities_ibfk_1]
	FOREIGN KEY ([username])
	REFERENCES users ([username])
) ;

-- Insertamos nuestros usuarios
INSERT INTO users VALUES ('luis','{noop}luis123',1);
INSERT INTO users VALUES ('marisol','{noop}mari123',1);

-- Insertamos (asignamos roles) a nuestros usuarios.
INSERT INTO authorities VALUES ('luis','EDITOR');
INSERT INTO authorities VALUES ('marisol','EDITOR');
INSERT INTO authorities VALUES ('marisol','MANAGER');

--// Tabla de usuarios y autoridades personalizada
-- Crear tabla de Usuarios
CREATE TABLE USERS (
	[account] varchar(100) NOT NULL,
	[password] varchar(100) NOT NULL,
	[status] varchar(100) NOT NULL,
	[phone] varchar(50) NOT NULL,
	PRIMARY KEY ([account])
) ;

-- Crear tabla de Perfiles
CREATE TABLE PROFILES (
	[account] varchar(100) NOT NULL,
	[profile] varchar(70) NOT NULL,
	CONSTRAINT [authorities_idx_2] UNIQUE  ([account],[profile]),
	CONSTRAINT [authorities_ibfk_2]
	FOREIGN KEY ([account])
	REFERENCES USERS ([account])
) ;

-- Insertamos nuestros usuarios
INSERT INTO USERS VALUES ('luis','{noop}luis123', 'ACTIVE','9856523');
INSERT INTO USERS VALUES ('marisol','{noop}mari123', 'ACTIVE','9856482');

-- Insertamos (asignamos roles) a nuestros usuarios.
INSERT INTO PROFILES VALUES ('luis','EDITOR');
INSERT INTO PROFILES VALUES ('marisol','EDITOR');
INSERT INTO PROFILES VALUES ('marisol','MANAGER');
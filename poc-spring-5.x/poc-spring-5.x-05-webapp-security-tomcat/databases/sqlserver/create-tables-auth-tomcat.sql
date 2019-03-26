--// Tabla de usuarios y autoridades por defecto para Tomcat Jdbc Realm-- Crear tabla de usuariosCREATE TABLE tomcat_users (	[username] varchar(50) NOT NULL,	[password] varchar(50) NOT NULL,	[enabled] smallint NOT NULL,	PRIMARY KEY ([username])) ;-- Crear tabla de rolesCREATE TABLE tomcat_authorities (	[username] varchar(50) NOT NULL,	[authority] varchar(50) NOT NULL,	CONSTRAINT [authorities_idx_1] UNIQUE  ([username],[authority]),	CONSTRAINT [authorities_ibfk_1]	FOREIGN KEY ([username])	REFERENCES tomcat_users ([username])) ;-- Insertamos nuestros usuariosINSERT INTO tomcat_users VALUES ('sorsha','A8B84619BBEEA18F2275F4D2C9240225',1);INSERT INTO tomcat_users VALUES ('raziel','6390AB7B006D48A5491A950B768D55FE',1);-- Insertamos (asignamos roles) a nuestros usuarios.INSERT INTO tomcat_authorities VALUES ('sorsha','EDITOR');INSERT INTO tomcat_authorities VALUES ('raziel','EDITOR');INSERT INTO tomcat_authorities VALUES ('raziel','MANAGER');
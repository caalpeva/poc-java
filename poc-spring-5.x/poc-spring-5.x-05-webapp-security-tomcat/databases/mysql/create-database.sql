# DROP DATABASE IF EXISTS CINEMA_TOMCAT;
CREATE DATABASE CINEMA_TOMCAT;

USE CINEMA_TOMCAT;

# Host: 93.189.94.106  (Version 5.7.25-0ubuntu0.18.04.2)
# Date: 2019-04-04 18:01:08
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "BANNER"
#

CREATE TABLE `BANNER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for table "MOVIE_DETAILS"
#

CREATE TABLE `MOVIE_DETAILS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actors` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `synopsis` text,
  `trailer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for table "MOVIES"
#

CREATE TABLE `MOVIES` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classification` varchar(255) DEFAULT NULL,
  `duration` int(11) NOT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `releaseDate` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `detail_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ragi6whancb4l1qs8pkqrkq1f` (`detail_id`),
  CONSTRAINT `FKj6vj1rbic4diyi5388490oqdq` FOREIGN KEY (`detail_id`) REFERENCES `MOVIE_DETAILS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for table "NEWS"
#

CREATE TABLE `NEWS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `publicationDate` datetime DEFAULT NULL,
  `detail` text,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for table "ROLES"
#

CREATE TABLE `ROLES` (
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for table "SHOWTIMES"
#

CREATE TABLE `SHOWTIMES` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `price` double NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjgtqe2xus31j1c12wytq2110g` (`movie_id`),
  CONSTRAINT `FKjgtqe2xus31j1c12wytq2110g` FOREIGN KEY (`movie_id`) REFERENCES `MOVIES` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for table "USERS"
#

CREATE TABLE `USERS` (
  `username` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for table "USERS_ROLES"
#

CREATE TABLE `USERS_ROLES` (
  `username` varchar(255) NOT NULL,
  `rolename` varchar(255) NOT NULL,
  UNIQUE KEY `UKtmha71m6iunbqcvx1atifmkxl` (`username`,`rolename`),
  KEY `FKo47ql066k4pi977m6wnp68d5u` (`rolename`),
  CONSTRAINT `FK138713b6sdp2pjbn1ujksibsn` FOREIGN KEY (`username`) REFERENCES `USERS` (`username`),
  CONSTRAINT `FKo47ql066k4pi977m6wnp68d5u` FOREIGN KEY (`rolename`) REFERENCES `ROLES` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

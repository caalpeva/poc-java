# DROP DATABASE IF EXISTS CINEMA;
CREATE DATABASE CINEMA;

USE CINEMA;

# Host: 46.183.115.17  (Version 5.7.25-0ubuntu0.18.04.2)
# Date: 2019-04-01 19:15:49
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

#
# Structure for table "ROLES"
#

CREATE TABLE `ROLES` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1s6p3xpt8owdb603jky0mo815` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=latin1;

#
# Structure for table "USERS"
#

CREATE TABLE `USERS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kby09mn7e5oe95v5ykdm0c0lq` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

#
# Structure for table "USERS_ROLES"
#

CREATE TABLE `USERS_ROLES` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  UNIQUE KEY `UKki8x30j3ar7btq9nt2h8kpsmv` (`user_id`,`role_id`),
  KEY `FKs9jph45raq0bh9bihmviuubd3` (`role_id`),
  CONSTRAINT `FKnoseerj3xr32i73kjox69kgs1` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`),
  CONSTRAINT `FKs9jph45raq0bh9bihmviuubd3` FOREIGN KEY (`role_id`) REFERENCES `ROLES` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

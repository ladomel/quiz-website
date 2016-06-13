/* source this file into MySQL */

/* for siplicity we create everything from scratch */
DROP SCHEMA IF EXISTS oop;

CREATE DATABASE oop;

USE oop;

CREATE TABLE user (
	user_name 		VARCHAR(255) NOT NULL PRIMARY KEY,
	hex_password 	VARCHAR(255) NOT NULL,
	salt 			VARCHAR(255) NOT NULL
);
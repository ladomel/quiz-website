DROP SCHEMA IF EXISTS oop;
CREATE SCHEMA oop;
USE oop;

create table loggedinusers(
	user_id 			int(11) 		not null,
	primary 			key(user_id)
);

create table users(
	id 					int(11) 		not null auto_increment,
	username 			varchar(255) 	not null unique,
	hash_password 		varchar(255) 	not null,
	salt 				varchar(255) 	not null,
	description 		longtext,
	image 				varchar(255),
	primary key(id)
);

insert into users(username, hash_password, salt) values('vaja', '1234', 'salt');

create table friends(
	first_user_id 		int(11) 		not null,
	second_user_id 		int(11) 		not null,
	primary key(first_user_id,second_user_id)
);

create table status(
	user_id 			int(11) 		not null,
	status 				varchar(255) 	not null,
	primary key(user_id,status)
);

create table achievements(
	id 					int(11) 		not null,
	name 				varchar(256) 	not null,
	icon 				varchar(256) 	not null,
	description 		longtext,
	primary key(id)
);

create table user_achievements(
	user_id 			int(11) 		not null,
	achievement_id 		int(11) 		not null,
	primary key(user_id,achievement_id)
);

create table questions(
	id 					int(11) 		not null auto_increment,
	quiz_id 			int(11) 		not null,
	problem 			longtext 		not null,
	type 				varchar(50) 	not null,
	grade 				int(11) 		not null,
	primary key(id)
);

insert into questions (quiz_id, problem, type, grade) values(1, 'whatsup', 'QR', 5);

create table answers(
	question_id 		int(11) 		not null,
	answer 				varchar(255) 	not null,
	field_id 			int(11) 		default 0
);

create table answers_wrong(
	question_id 		int(11) 		not null,
	answer_wrong		varchar(255) 	not null,
	field_id 			int(11) 		default 0
);

insert into answers (question_id, answer) values(1, 'good'), (1, 'bad');

create table quizzes(
	id int(11) 			not null 		auto_increment,
	creator_id 			int(11) 		not null,
	name 				varchar(250) 	not null,
	description 		longtext,
	is_random 			boolean 		not null default 0,
	is_one_page 		boolean 		not null default 1,
	immediate_correction boolean 		not null default 0,
	practice_mode 		boolean 		not null default 0, 
	creation_time 		bigint 			not null,
	time 				bigint 			not null,
	max_score 			int(11) 		not null,
	avg_rating			double,
	avg_score			double,
	avg_time			bigint,
	tries				int(11),
	category 			varchar(250),
	primary key(id)
);

create table tags(
	quiz_id				int(11)			not null,
	tag					varchar(255)	not null
);

insert into quizzes (creator_id, name, creation_time, time, max_score) values(1, 'birds', 1945, 30, 100);

create table quiz_problems(
	quiz_id 			int(11) 		not null,
	`index` 			int(11) 		not null,
	question_id 		int(11) 		not null,
	primary key(quiz_id, `index`)
);

create table friendrequests(
	id 					int(11) 		not null auto_increment,
	sender_username 	varchar(256) 		not null,
	receiver_username 	varchar(256) 		not null,
	status 				varchar(50), /* Active, Declined, Accepted */
	time				bigint,
	seen				boolean,
    primary key(id)
);

create table challenges(
	id 					int(11) 		not null auto_increment,
	sender_username 	varchar(256)	not null,
	receiver_username 	varchar(256)  	not null,
	quiz 				int(11) 		not null,
	status 				varchar(50), /* Active, Declined, Accepted */
	time 				bigint,
	seen				boolean,
    primary key(id)
);

create table announcements(
	id 					int(11) 		not null auto_increment,
	announcer_username 	varchar(256)	not null, 
	announcement		longtext,
	time 				bigint,
    primary key(id)
);

create table notes(
	id 					int(11) 		not null auto_increment,
	sender_username 	varchar(256)	not null,
	receiver_username 	varchar(256) 	not null,
	note 				longtext,
	time 				bigint,
	seen				boolean,
    primary key(id)
);

# result related tables below

create table results(
	id 					int(11) 		not null auto_increment,
	user_id 			int(11) 		not null,
	quiz_id				int(11) 		not null,
	final_grade 		int(11) 		not null,
	start_time 			bigint 			not null,
	time_taken 			bigint 			not null,
	primary key(id)
);

create table answers_of_question(
	result_id 			int(11) 		not null,
	question_id			int(11)  		not null,
	grade 				int(11)
);

create table  user_answers(
	question_id 		int(11) 		not null,
	field_id 			int(11) 		not null,
	user_answer 		longtext
);
drop schema if exists oop;
create schema oop;
use oop;

create table loggedinusers(
	user_id int(11) not null,
	primary key(id)
);

create table users(
	id int(11) not null auto_increment,
	image varchar(255) not null,
	username varchar(255) not null unique,
	hash_password varchar(255) not null,
	salt varchar(255) not null,
	description longtext,
	primary key(id)
);

create table friends(
	first_user_id int(11) not null,
	second_user_id int(11) not null,
	primary key(first_user_id,second_user_id)
);

create table status(
	user_id int(11) not null,
	status varchar(255) not null,
	primary key(user_id,status)
);

create table achievements(
	id int(11) not null,
	name varchar(256) not null,
	icon varchar(256) not null,
	description longtext,
	primary key(id)
);

create table user_achievements(
	user_id int(11) not null,
	achievement_id int(11) not null,
	primary key(user_id,achievement_id)
);

create table question(
	id int(11) not null,
	problem longtext not null,
	type varchar(50) not null,
	primary key(id)
);

create table quizzes(
	id int(11) not null auto_increment,
	creator_id int(11) not null,
	name varchar(250) not null,
	description longtext,
	is_random boolean not null,
	is_one_page boolean not null,
	immediate_correction boolean not null,
	practice_mode boolean not null, 
	creation_time bigint not null,
	time bigint,
	max_score int(11) not null,
	category varchar(250),
	primary key(id)		
);

create table quiz_problems(
	quiz_id int(11) not null,
	index int(11) not null,
	question_id int(11) not null,
	primary key(quiz_id,index)
);

create table friendrequests(
	sender_id int(11) not null,
	receiver_id int(11) not null,
	status varchar(50), /* Active, Declined, Accepted */
	time bigint
);

create table challenges(
	sender_id int(11) not null,
	receiver_id int(11) not null,
	quiz int(11) not null,
	status varchar(50), /* Active, Declined, Accepted */
	time bigint
);

create table notes(
	sender_id int(11) not null,
	receiver_id int(11) not null,
	note longtext,
	status varchar(50), /* Seen, New */
	time bigint
);

create table results(
	id int(11) not null,
	user_id int(11) not null,
	quiz_id	int(11) not null,
	score int(11) not null,
	start_time bigint not null,
	time_taken bigint not null,
	primary key(id)
);

create table  user_answers(
	result_id int(11) not null,
	question_id int(11) not null,
	user_answer longtext,
	primary key(history_id,problem_id)
);
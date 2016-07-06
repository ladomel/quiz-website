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
	image 				varchar(255)    default  'http://live.warthunder.com/style/img/no_avatar.jpg',
	primary key(id)
);

create table admins(
	user_id 			varchar(255) 	not null,
	unique(user_id)
);

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

create table questions(
	id 					int(11) 		not null auto_increment,
	q_id				int(11),
	quiz_id 			int(11) 		not null,
	problem 			longtext 		not null,
	type 				varchar(50) 	not null,
	grade 				int(11) 		not null,
	primary key(id)
);

create table question_lines(
	text				varchar(255)	not null,
	idx					int(11)			not null default 0,
	question_id			int(11)			not null
);

create table images(
	question_id			int(11)			not null,
	image				varchar(255)
);

create table multiple_choice_metadata(
	question_id			int(11)			not null,
	nfields				int(11)			not null,
	ordered				boolean			not null
);

create table answers(
	question_id 		int(11) 		not null,
	answer 				varchar(255) 	not null,
	field_id 			int(11) 		default 0,
	idx_in_field		int(11)			default 0
);

create table answers_wrong(
	question_id 		int(11) 		not null,
	answer_wrong		varchar(255) 	not null,
	field_id 			int(11) 		default 0,
	idx_in_field		int(11)			default 0
);

create table quizzes(
	id 					int(11) 		not null auto_increment,
	creator_id 			int(11) 		not null,
	name 				varchar(250),
	description 		longtext,
	is_random 			boolean 		not null default 0,
	is_one_page 		boolean 		not null default 1,
	immediate_correction boolean 		not null default 0,
	practice_mode 		boolean 		not null default 0, 
	creation_time 		bigint 			not null,
	time 				bigint 			not null,
	max_score 			int(11) 		not null,
	category_id 		int(11),
	primary key(id)
);

create table tags(
	quiz_id				int(11)			not null,
	tag					varchar(255)	not null
);

create table categories(
	id					int(11)			not null auto_increment,
	category			varchar(50)		not null,
	primary key(id)
);

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

create table earned_achievements
(
	username 			varchar(256)	not null,
	achievement_id 		int(11)			not null, 
	unlock_time 		bigint
);

create table reviews(
	user_id				int(11)			not null,
	text				longtext		not null,
	quiz_id				int(11)			not null,
	date				long			
);

insert into users(username, hash_password, salt, description, image)
values('god', 'f240f5d7013e156ab27332c1a91ad0449231a3b4', 'aaaaaaaaaaaaaaaaaaaa', 'This user has god-mode enabled.', 'http://3.bp.blogspot.com/-o8eeIO82GZE/TnasVVFNcjI/AAAAAAAAAcs/vk26j6xbpEc/s1600/pantocrator.jpg');
insert into admins(user_id) values(1);
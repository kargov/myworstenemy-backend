CREATE TABLE Event
(
		id INTEGER IDENTITY PRIMARY KEY,
		name varchar(25)
)
;

CREATE TABLE User
(
	id INTEGER IDENTITY PRIMARY KEY,
	username varchar(25),
	password VARCHAR(256),
	role varchar(25)
)
;

CREATE TABLE Ticket
(
		id INTEGER IDENTITY PRIMARY KEY,
		event_id int,
		user_id int,
	  status varchar(25)
)
;

CREATE TABLE OrderHistory
(
	id INTEGER IDENTITY PRIMARY KEY,
	event_id int,
	event_name varchar(25),
	user_id int,
	user_name varchar(25),
	ticket_id int,
	ticket_status varchar(25)
)
;
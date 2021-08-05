BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Film" (
	"id"	INTEGER,
	"name"	TEXT,
	"duration"	INTEGER,
	"directorid"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Genre" (
	"id"	INTEGER,
	"name"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "FilmGenre" (
	"filmid"	INTEGER,
	"genreid"	INTEGER
);
CREATE TABLE IF NOT EXISTS "ActorFilm" (
	"actorid"	INTEGER,
	"filmid"	INTEGER
);
CREATE TABLE IF NOT EXISTS "DirectorFilm" (
	"directorid"	INTEGER,
	"filmid"	INTEGER
);
CREATE TABLE IF NOT EXISTS "Language" (
	"id"	INTEGER,
	"name"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Director" (
	"id"	INTEGER,
	"name"	TEXT,
	"lastname"	TEXT,
	"dateofbirth"	INTEGER,
	"genderid"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Actor" (
	"id"	INTEGER,
	"name"	TEXT,
	"lastname"	TEXT,
	"genderid"	INTEGER,
	"dateofbirth"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Seat" (
	"id"	INTEGER,
	"hallid"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Ticket" (
	"id"	INTEGER,
	"seatid"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "FilmLanguage" (
	"filmid"	INTEGER,
	"languageid"	INTEGER
);
CREATE TABLE IF NOT EXISTS "Food" (
	"id"	INTEGER,
	"name"	TEXT,
	"weight"	INTEGER,
	"price"	REAL,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Beverage" (
	"id"	INTEGER,
	"name"	TEXT,
	"quantity"	INTEGER,
	"price"	REAL,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Hall" (
	"id"	INTEGER,
	PRIMARY KEY("id")
);
INSERT INTO "Genre" VALUES (1,'Action');
INSERT INTO "Genre" VALUES (2,'Adventure');
INSERT INTO "Genre" VALUES (3,'Animation');
INSERT INTO "Genre" VALUES (4,'Biography');
INSERT INTO "Genre" VALUES (5,'Comedy');
INSERT INTO "Genre" VALUES (6,'Crime');
INSERT INTO "Genre" VALUES (7,'Documentary');
INSERT INTO "Genre" VALUES (8,'Drama');
INSERT INTO "Genre" VALUES (9,'Family');
INSERT INTO "Genre" VALUES (10,'Fantasy');
INSERT INTO "Genre" VALUES (11,'Film-noir');
INSERT INTO "Genre" VALUES (12,'History');
INSERT INTO "Genre" VALUES (13,'Horror');
INSERT INTO "Genre" VALUES (14,'Music');
INSERT INTO "Genre" VALUES (15,'Musical');
INSERT INTO "Genre" VALUES (16,'Mystery');
INSERT INTO "Genre" VALUES (17,'Romance');
INSERT INTO "Genre" VALUES (18,'Sci-fi');
INSERT INTO "Genre" VALUES (19,'Sport');
INSERT INTO "Genre" VALUES (20,'Thriller');
INSERT INTO "Genre" VALUES (21,'War');
INSERT INTO "Genre" VALUES (22,'Western');
INSERT INTO "Language" VALUES (1,'English');
INSERT INTO "Language" VALUES (2,'Spanish');
INSERT INTO "Language" VALUES (3,'French');
INSERT INTO "Language" VALUES (4,'Italian');
INSERT INTO "Language" VALUES (5,'German');
INSERT INTO "Language" VALUES (6,'Bosnian');
INSERT INTO "Food" VALUES (1,'Small popcorn',NULL,NULL);
INSERT INTO "Food" VALUES (2,'Medium popcorn',NULL,NULL);
INSERT INTO "Food" VALUES (3,'Large popcorn',NULL,NULL);
INSERT INTO "Food" VALUES (4,'Nachos',NULL,NULL);
INSERT INTO "Beverage" VALUES (1,'Coca Cola',NULL,NULL);
INSERT INTO "Beverage" VALUES (2,'Fanta',NULL,NULL);
INSERT INTO "Beverage" VALUES (3,'Sprite',NULL,NULL);
COMMIT;

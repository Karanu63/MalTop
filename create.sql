CREATE DATABASE maltop;

\c maltop;
CREATE TABLE  analysts(
    id serial PRIMARY KEY,
    analystName VARCHAR,
    analystEmail VARCHAR,
    trainerId INT,
    analystPassword VARCHAR

);

CREATE TABLE trainers(
   id serial PRIMARY KEY,
   trainerName VARCHAR,
   trainerEmail VARCHAR,
   trainerPassword VARCHAR,
   classSize INT

);

CREATE TABLE exercises(
 id serial PRIMARY KEY,
 exerciseName VARCHAR,
 exerciseDescription VARCHAR,
 exercisePoints INT,
 exerciseUrl VARCHAR

);

CREATE TABLE solutions(
id serial PRIMARY KEY,
analystEmail VARCHAR,
solution VARCHAR,
exerciseId INT,
createdAt VARCHAR

);

CREATE DATABASE maltop_test WITH TEMPLATE maltop;
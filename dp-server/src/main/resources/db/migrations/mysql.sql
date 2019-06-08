CREATE TABLE metric
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name        TEXT                  NOT NULL,
  rating      DOUBLE                NOT NULL,
  description TEXT                  NOT NULL
);

CREATE TABLE skill
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name        TEXT                  NOT NULL,
  rating      DOUBLE                NOT NULL,
  description TEXT                  NOT NULL
);

CREATE TABLE event
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  typeId      BIGINT                NOT NULL,
  name        TEXT                  NOT NULL,
  rating      DOUBLE                NOT NULL,
  description TEXT                  NOT NULL
);

CREATE TABLE event_type
(
  id   BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name TEXT                  NOT NULL
);

CREATE TABLE event_skill
(
  eventId BIGINT,
  skillId BIGINT
);

CREATE TABLE vacancy
(
  id     BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name   TEXT                  NOT NULL,
  rating DOUBLE                NOT NULL
);

CREATE TABLE vacancy_skill
(
  vacancyId BIGINT,
  skillId   BIGINT
);

CREATE TABLE education
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name        TEXT                  NOT NULL,
  rating      DOUBLE                NOT NULL,
  description TEXT                  NOT NULL
);

CREATE TABLE education_program
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  educationId BIGINT                NOT NULL,
  name        TEXT                  NOT NULL,
  rating      DOUBLE                NOT NULL,
  description TEXT                  NOT NULL
);

CREATE TABLE education_faculty
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  educationId BIGINT                NOT NULL,
  name        TEXT                  NOT NULL,
  rating      TEXT                  NOT NULL,
  description TEXT                  NOT NULL
);

CREATE TABLE company
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name        TEXT                  NOT NULL,
  rating      DOUBLE                NOT NULL,
  description TEXT                  NOT NULL
);

CREATE TABLE company_vacancy
(
  companyId    BIGINT,
  id           BIGINT,
  name         TEXT NOT NULL,
  salary       TEXT,
  currencyType TEXT,
  description  TEXT NOT NULL
);

CREATE TABLE user
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  username    TEXT                  NOT NULL,
  password    TEXT                  NOT NULL,
  email       TEXT                  NOT NULL,
  banned      BOOLEAN               NOT NULL DEFAULT FALSE,
  createdAt   TIMESTAMP             NOT NULL DEFAULT NOW(),
  lastVisitAt TIMESTAMP             NOT NULL DEFAULT NOW()
);

CREATE TABLE user_info
(
  userId     BIGINT NOT NULL,
  firstName  TEXT   NOT NULL,
  secondName TEXT   NOT NULL,
  middleName TEXT   NOT NULL
);

CREATE TABLE user_identity
(
  userId    BIGINT  NOT NULL,
  idIndex   INTEGER NOT NULL,
  idNumber  INTEGER NOT NULL,
  idAddress TEXT    NOT NULL
);

CREATE TABLE user_event
(
  userId   BIGINT NOT NULL,
  eventId  TEXT   NOT NULL,
  rating   DOUBLE NOT NULL
);

CREATE TABLE user_metric
(
  userId   BIGINT  NOT NULL,
  metricId INTEGER NOT NULL,
  value    DOUBLE  NOT NULL
);

INSERT INTO event_type (id, name)
VALUES ('university program'),
       ('college program'),
       ('olympiad'),
       ('courses');

INSERT INTO event (typeId, name, description)
VALUES (3, 'Java', 'Java courses'),
       (3, 'SQL', 'SQL courses'),
       (0, 'Biology', 'Biology university program'),
       (1, 'Java', 'Biology college program'),
       (3, 'Java course work 1', 'simple course work');

INSERT INTO user (username, password, email)
VALUES ('admin', 'password', 'admin@mail.ru'),
       ('guest', 'password', 'guest@mail.ru'),
       ('valentin', 'password', 'valentin@mail.ru');

INSERT INTO user_info (userId, firstName, secondName, middleName)
VALUES (2, 'Valentin', 'Bashkirov', 'Victorovich');

INSERT INTO user_identity (userId, idNumber, idAddress)
VALUES (2, '556661116', 'SPb yl. Zamshina');

INSERT INTO user_event (userId, eventId, rating)
VALUES (2, 0, 0.8);

INSERT INTO company (name, rating, description)
VALUES ('Google'),
 ('Yandex'),
 ('JetBrains'),
 ('Faulty inc.');



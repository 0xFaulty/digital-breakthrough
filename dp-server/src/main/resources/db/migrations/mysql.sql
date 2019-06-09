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
  eventType   TEXT                  NOT NULL,
  name        TEXT                  NOT NULL,
  rating      DOUBLE                NOT NULL,
  description TEXT                  NOT NULL
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

CREATE TABLE institution
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  userId      BIGINT,
  name        TEXT                  NOT NULL,
  rating      DOUBLE                NOT NULL,
  description TEXT                  NOT NULL
);

CREATE TABLE education_program
(
  id            BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  institutionId BIGINT                NOT NULL,
  name          TEXT                  NOT NULL,
  rating        DOUBLE                NOT NULL,
  description   TEXT                  NOT NULL
);

CREATE TABLE company
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  userId      BIGINT,
  name        TEXT                  NOT NULL,
  rating      DOUBLE                NOT NULL,
  description TEXT                  NOT NULL
);

CREATE TABLE company_vacancy
(
  companyId    BIGINT,
  vacancyId    BIGINT,
  salary       TEXT,
  currencyType TEXT,
  description  TEXT NOT NULL
);

CREATE TABLE user_type
(
  id   BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name TEXT                  NOT NULL
);

CREATE TABLE user
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  password    TEXT                  NOT NULL,
  email       TEXT                  NOT NULL,
  userType    TEXT                  NOT NULL,
  banned      BOOLEAN               NOT NULL DEFAULT FALSE,
  createdAt   TIMESTAMP             NOT NULL DEFAULT NOW(),
  lastVisitAt TIMESTAMP             NOT NULL DEFAULT NOW()
);

CREATE TABLE student_info
(
  userId     BIGINT NOT NULL PRIMARY KEY,
  firstName  TEXT   NOT NULL,
  secondName TEXT   NOT NULL,
  middleName TEXT   NOT NULL,
  photo      TEXT   NOT NULL,
  gender     TEXT   NOT NULL,
  birthDate  DATE   NOT NULL
);

CREATE TABLE user_event
(
  userId    BIGINT NOT NULL,
  eventId   TEXT   NOT NULL,
  eventDate DATE   NOT NULL,
  rating    DOUBLE NOT NULL
);

CREATE TABLE user_metric
(
  userId   BIGINT  NOT NULL,
  metricId INTEGER NOT NULL,
  value    DOUBLE  NOT NULL
);
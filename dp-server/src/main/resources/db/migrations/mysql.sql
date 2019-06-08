CREATE TABLE user
(
  id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  username    TEXT                  NOT NULL,
  password    TEXT                  NOT NULL,
  email       TEXT                  NOT NULL,
  banned      BOOLEAN               NOT NULL,
  createdAt   TIMESTAMP             NOT NULL DEFAULT NOW(),
  lastVisitAt TIMESTAMP             NOT NULL DEFAULT NOW()
);

CREATE TABLE user_info
(
  id         BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  firstName  TEXT                  NOT NULL,
  secondName TEXT                  NOT NULL,
  middleName TEXT                  NOT NULL,
  idNumber   BOOLEAN               NOT NULL
);

CREATE TABLE user_identity
(
  userId         BIGINT  NOT NULL,
  firstName  TEXT                  NOT NULL,
  secondName TEXT                  NOT NULL,
  middleName TEXT                  NOT NULL,
  idNumber   BOOLEAN               NOT NULL
);

INSERT INTO user (username, password, email, active)
VALUES ('admin', 'password', 'admin@mail.ru', false),
       ('guest', 'password', 'guest@mail.ru', false);

INSERT INTO user_info (firstName, secondName, middleName, idNumber)
VALUES ('Valentin', 'Bashkirov', 'Victorovich', '556661116');
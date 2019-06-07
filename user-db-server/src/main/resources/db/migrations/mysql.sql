CREATE TABLE users
(
    id           BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    username     TEXT      NOT NULL,
    password     TEXT      NOT NULL,
    email        TEXT      NOT NULL,
    active       BOOLEAN   NOT NULL,
    created      TIMESTAMP NOT NULL DEFAULT NOW(),
    last_session TIMESTAMP NOT NULL DEFAULT NOW()
);
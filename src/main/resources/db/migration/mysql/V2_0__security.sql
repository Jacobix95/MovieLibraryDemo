CREATE TABLE users
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(200) NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL
);

CREATE TABLE role
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_role
(
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES role(id),
    UNIQUE (user_id, role_id)
);
INSERT INTO users(email, password)
VALUES ('user@mail.com', '$2a$10$2jRyqPvsm44WKOfd7wCjIeji9K8VCCqn8vTOESaHVXP3G4/ZOlkm.'), -- usersecret
       ('admin@mail.com', '$2a$10$rwIDT/UGbwRldxoK6Ig66eaMzWzwMLgWUXV0vhqNu/gvXdcdq5MiO'); -- adminsecret

INSERT INTO role(name)
VALUES ('DEVELOPER_READ'),
       ('DEVELOPER_WRITE');
INSERT INTO user_role(user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (2, 2);
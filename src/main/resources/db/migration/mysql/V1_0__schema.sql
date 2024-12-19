CREATE TABLE movie (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(200) NOT NULL,
                       director VARCHAR(200),
                       release_year INT,
                       genre VARCHAR(200),
                       rating INT,
                       version INT
);
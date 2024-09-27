CREATE TABLE movie
(
    id           UUID PRIMARY KEY,
    title        VARCHAR(200),
    director     VARCHAR(200),
    release_year INT,
    genre        VARCHAR(200),
    rating       INT CHECK (rating >= 1 AND rating <= 10),
    version      INT
);
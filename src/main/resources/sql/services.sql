CREATE TABLE services (
    id INTEGER PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(6,2) NOT NULL,
    duration_min INTEGER NOT NULL
);
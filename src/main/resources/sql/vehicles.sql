CREATE TABLE vehicles (
    id INTEGER NOT NULL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    plate TEXT NOT NULL,
    make TEXT NOT NULL,
    colour TEXT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);
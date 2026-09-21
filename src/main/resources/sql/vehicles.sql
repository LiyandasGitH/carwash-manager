CREATE TABLE vehicles (
    id INTEGER NOT NULL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    plate VARCHAR NOT NULL,
    make VARCHAR NOT NULL,
    colour VARCHAR NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);
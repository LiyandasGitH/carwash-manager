CREATE TABLE vehicles (
    id INTEGER NOT NULL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    plate VARCHAR(20) NOT NULL,
    make VARCHAR(50) NOT NULL,
    colour VARCHAR(50) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);
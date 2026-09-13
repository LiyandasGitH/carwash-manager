CREATE TABLE memberships (
    id INTEGER NOT NULL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    plan TEXT NOT NULL,
    monthly_fee NUMERIC(12, 2) NOT NULL,
    active BOOLEAN NOT NULL,
    renewing DATE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);
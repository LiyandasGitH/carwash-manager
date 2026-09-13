CREATE TABLE payments (
    id INTEGER NOT NULL PRIMARY KEY,
    ticket_id INTEGER NOT NULL,
    amount NUMERIC(12, 2) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    payment_status VARCHAR(50) NOT NULL,
    paid DATE NOT NULL,
    FOREIGN KEY (ticket_id) REFERENCES tickets(id)
);
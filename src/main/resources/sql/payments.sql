CREATE TABLE payments (
    id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    ticket_id INTEGER NOT NULL,
    amount DECIMAL(6, 2) NOT NULL,
    payment_method VARCHAR(20) NOT NULL,
    payment_status VARCHAR(20) NOT NULL,
    paid_at DATETIME NOT NULL DEFAULT (CURRENT_TIMESTAMP),
    FOREIGN KEY (ticket_id) REFERENCES tickets(id)
);
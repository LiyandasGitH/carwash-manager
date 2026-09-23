CREATE TABLE memberships (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    plan VARCHAR(20) NOT NULL, -- BASIC, PREMIUM
    monthly_fee DECIMAL(12, 2) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    renewing DATE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
);
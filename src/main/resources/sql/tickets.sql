CREATE TABLE tickets (
    id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    vehicle_id INTEGER NOT NULL,
    service_id INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'QUEUED',
    created DATETIME NOT NULL DEFAULT (CURRENT_TIMESTAMP),
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id),
    FOREIGN KEY (service_id) REFERENCES services(id),
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);
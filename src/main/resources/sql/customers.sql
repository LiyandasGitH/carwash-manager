CREATE TABLE customers (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    member_status VARCHAR(20) DEFAULT 'NONE',
    join_date DATE NOT NULL DEFAULT (CURRENT_DATE)
);
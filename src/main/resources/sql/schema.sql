CREATE DATABASE IF NOT EXISTS carwash;
USE carwash;

DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS memberships;
DROP TABLE IF EXISTS vehicles;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS services;
DROP TABLE IF EXISTS customers;

-- no foreign key dependencies
SOURCE src/main/resources/sql/customers.sql
SOURCE src/main/resources/sql/services.sql;
SOURCE src/main/resources/sql/employees.sql;

-- dependent on foreign keys
SOURCE src/main/resources/sql/vehicles.sql;
SOURCE src/main/resources/sql/memberships.sql;
SOURCE src/main/resources/sql/tickets.sql;
SOURCE src/main/resources/sql/payments.sql;

-- Seed Data

INSERT INTO services (name, price, duration_min)
    VALUES
        ('Basic Wash', 12.00, 15),
        ('Deluxe Wash', 22.00, 25),
        ('Full Detail', 65.00, 90),
        ('Interior Only', 30.00, 40),
        ('Wax & Shine', 18.00, 20);

INSERT INTO employees (name, role)
    VALUES
       ('Harwin', 'WASHER'),
       ('Hullen', 'WASHER'),
       ('Vayon Poole', 'MANAGER');

INSERT INTO customers (name, phone, email, member_status)
VALUES
    ('Robert Baratheon', '555-0101', 'bobbybaratheon@proton.me', 'NONE'),
    ('Cersei Lannister', '555-0102', 'cerseilannister@proton.me', 'PREMIUM'),
    ('Jon Arryn', '555-0103', 'jonarryn@proton.me', 'BASIC');

INSERT INTO vehicles (customer_id, plate, make, model, colour)
VALUES
      (1, 'KL 123-467', 'VW', 'Golf', 'Silver'),
      (2, 'KL 232-111', 'Porsche', 'Carrera', 'Gold'),
      (3, 'VE 667-898', 'Ford', 'Escape', 'Blue');


INSERT INTO memberships (customer_id, plan, monthly_price, active, renews_at)
VALUES
      (1, 'PREMIUM', 40.00, TRUE, DATE_ADD(CURRENT_DATE, INTERVAL 1 MONTH)),
      (3, 'BASIC', 20.00, TRUE, DATE_ADD(CURRENT_DATE, INTERVAL 1 MONTH));
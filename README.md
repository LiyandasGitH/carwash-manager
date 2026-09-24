# Car Wash Manager

A Java Swing desktop app for running a car wash: customer & vehicle tracking, a ticket board for the wash queue, simulated in-house payment processing, and revenue reports.

Built as a Java/JDBC/MySQL take on the classic "members + dues + tickets + dashboard" pattern used by HOA/club management apps, adapted to a car wash business.

## Stack 

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Build Tool](https://img.shields.io/badge/Maven-3.6%2B-red.svg)](https://maven.apache.org/)
[![GUI Framework](https://img.shields.io/badge/GUI-Java%20Swing-blue)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![Database](https://img.shields.io/badge/Database-MySQL%208.0%2B-4479A1?logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Driver](https://img.shields.io/badge/Driver-MySQL%20Connector%2FJ%208.4.x-blue?logo=mysql&logoColor=white)](https://dev.mysql.com/downloads/connector/j/)
[![Persistence](https://img.shields.io/badge/Persistence-Pure%20JDBC%20%28ORM--Free%29-yellow)](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)
[![Packaging](https://img.shields.io/badge/Plugin-Maven%20Shade%203.6.0-red?logo=apachemaven&logoColor=white)](https://maven.apache.org/plugins/maven-shade-plugin/)
[![Testing Framework](https://img.shields.io/badge/Testing-JUnit%205-25A162?logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![Test Runner](https://img.shields.io/badge/Runner-Maven%20Surefire-red?logo=apachemaven&logoColor=white)](https://maven.apache.org/plugins/maven-surefire-plugin/)

## Project Overview 

A Java based car wash management system, that keeps track of services rendered, customers, and in the future payment services, payment systems, incorporating databases into the system.

## Demo 

**[![Watch the Demo](https://img.shields.io/badge/YouTube-Watch%20Demo-red?style=for-the-badge&logo=youtube)](YOUR_YOUTUBE_LINK_HERE)**

## Project Structure 

```
Carwash Manager/
├── .gitignore
├── ./run.sh
├── ./test.sh
├── env.txt
├── pom.xml
├── project.txt
├── README.md
├── research.md
├── 
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── Main.java
│   │   │   └── com/carwash/
│   │   │       ├── dao/
│   │   │       │   ├── CustomerDAO.java
│   │   │       │   ├── EmployeeDAO.java
│   │   │       │   ├── MembershipDAO.java
│   │   │       │   ├── PaymentDAO.java
│   │   │       │   ├── ServiceDAO.java
│   │   │       │   ├── TicketDAO.java
│   │   │       │   └── VehicleDAO.java
│   │   │       ├── db/
│   │   │       │   └── DBConnection.java
│   │   │       ├── dummy/
│   │   │       │   └── AwtExample.java
│   │   │       ├── model/
│   │   │       │   ├── Customer.java
│   │   │       │   ├── Employee.java
│   │   │       │   ├── Membership.java
│   │   │       │   ├── Method.java
│   │   │       │   ├── Payment.java
│   │   │       │   ├── PaymentStatus.java
│   │   │       │   ├── Service.java
│   │   │       │   ├── Ticket.java
│   │   │       │   ├── TicketStatus.java
│   │   │       │   └── Vehicle.java
│   │   │       ├── service/
│   │   │       │   ├── PaymentService.java
│   │   │       │   ├── ReportService.java
│   │   │       │   └── TicketService.java
│   │   │       └── ui/
│   │   │           ├── CheckoutPanel.java
│   │   │           ├── CustomerPanel.java
│   │   │           ├── MainFrame.java
│   │   │           ├── ReportsPanel.java
│   │   │           └── TicketBoardPanel.java
│   │   └── resources/
|   |       ├── SQL-Cheat-Sheet.pdf
│   │       └── sql/
│   │           ├── customers.sql
│   │           ├── employees.sql
│   │           ├── memberships.sql
│   │           ├── payments.sql
│   │           ├── schema.sql
│   │           ├── services.sql
│   │           ├── tickets.sql
│   │           └── vehicles.sql
│   └── test/
│       └── java/
│           └── com/carwash/
│               ├── daotests/
│               │   ├── CustomerDAOTest.java
│               │   ├── EmployeeDAOTest.java
│               │   ├── MembershipDAOTest.java
│               │   ├── PaymentDAOTest.java
│               │   ├── ServiceDAOTest.java
│               │   ├── TicketDAOTest.java
│               │   └── VehicleDAOTest.java
│               ├── dbtests/
│               │   └── DBConnectionTest.java
│               ├── modeltests/
│               │   ├── CustomerTest.java
│               │   ├── EmployeeTest.java
│               │   ├── MembershipTest.java
│               │   ├── PaymentTest.java
│               │   ├── ServiceTest.java
│               │   ├── TicketTest.java
│               │   └── VehicleTest.java
│               ├── servicetests/
│               │   ├── PaymentServiceTest.java
│               │   ├── ReportServiceTest.java
│               │   └── TicketServiceTest.java
│               └── uitests/
│                   ├── CheckoutPanelTest.java
│                   ├── CustomerPanelTest.java
│                   ├── MainFrameTest.java
│                   ├── ReportsPanelTest.java
│                   └── TicketBoardPanelTest.java
└── target/            (build output: jars, classes, test reports)
```

## Features 

1. **Customer & Fleet Management**
2. **Live Wash Ticket Board**
3. **Payment Processing & Point of Sale**
4. **Business Intelligence & Revenue Analytics**

## Requirements

- **Java Development Kit (JDK):** Version 17 or higher
- **Build Automation Tool:** Apache Maven 3.6+
- **Database:** MySQL Server 8.0+
- **Operating System Environment:** Linux (Pop!_OS, Ubuntu/Debian), macOS, or Windows.

## Installation & Setup 

1. **Database Configuration**

    #### Ensure MySQL 8 is installed & running, create the database, and run the consolidated schema scripts.
    ```text
    # Log in with administrative privileges and load the schema
    sudo mysql < src/main/resources/sql/schema.sql
    ```
   **Tip:** Refer to **[View the SQL Cheat Sheet](src/main/resources/SQL-Cheat-Sheet.pdf)** for quick reference on SQL commands.
    
    #### Ensure your MySQL application user exists and has CRUD privileges on the database.
    ```sql
    CREATE USER IF NOT EXISTS 'carwash_app'@'localhost' IDENTIFIED BY 'YOUR_PASSWORD';
    GRANT ALL PRIVILEGES ON carwash.* TO 'carwash_app'@'localhost';
    FLUSH PRIVILEGES;
    ```

2. **Environment Variable(.env)**

   #### Copy the environment template or create a .env file in the project root to manage database connection parameters.
    ```text
    cp .env.example .env
    ```

   #### Ensure the parameters match your local MySQL server setup.
    ```text
    CARWASH_DB_HOST=localhost
    CARWASH_DB_PORT=3306
    CARWASH_DB_NAME=carwash
    CARWASH_DB_USER=carwash_app
    CARWASH_DB_PASSWORD=YOUR_PASSWORD
    ```

## SQL Reference & Cheat Sheet

A comprehensive SQL quick-reference guide covering DDL, DML, DQL joins, user privileges, and diagnostic queries used throughout this project is included in the resources directory:

**[View the SQL Cheat Sheet](src/main/resources/SQL-Cheat-Sheet.pdf)**

It includes:
* Schema definitions and foreign key constraints (`CREATE`, `ALTER`, `DROP`)
* Ticket queue and payment transaction queries
* Multi-table `JOIN` statements and string concatenations (`CONCAT`)
* User privileges and MySQL authentication commands (`GRANT`, `FLUSH PRIVILEGES`)

## Application Execution

Make sure the launcher scripts have execute permissions
```text
chmod +x run.sh test.sh
```

## Running the Application 

The ```run.sh``` script automatically sources .env variables, compiles and packages the shaded JAR if missing, and boots the Swing desktop UI
```text
./run.sh
```

## Running the Tests

Execute the entire test suite (unit and DAO/service integration tests) with injected .env variables using ```test.sh```
```text
./test.sh
```

## WTC Tracking 

WTC-Q4QETHF7

## Author/s
Liyanda Tonisi

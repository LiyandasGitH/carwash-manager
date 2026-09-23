# Car Wash Manager 


A Java Swing desktop app for running a car wash: customer & vehicle tracking, a ticket board for the wash queue, simulated in-house payment processing, and revenue reports.

Built as a Java/JDBC/MySQL take on the classic "members + dues + tickets + dashboard" pattern used by HOA/club management apps, adapted to a car wash business.

## Stack 

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Build Tool](https://img.shields.io/badge/Maven-3.6%2B-red.svg)](https://maven.apache.org/)


## Project Overview 

A Java based car wash management system, that keeps track of services rendered, customers, and in the future payment services, payment systems, incorporating databases into the system.

## Demo 

**[![Watch the Demo](https://img.shields.io/badge/YouTube-Watch%20Demo-red?style=for-the-badge&logo=youtube)](YOUR_YOUTUBE_LINK_HERE)**

## Project Structure 

```
Carwash Manager/
├── dependency-reduced-pom.xml
├── .gitignore
├── pom.xml
├── project.txt
├── README.md
├── research.md
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

## Requirements

- **Java Development Kit (JDK):** Version 17 or higher
- **Build Automation Tool:** Apache Maven 3.6+
- **Database:** MySQL Server 8.0+
- **Operating System Environment:** Linux (Pop!_OS, Ubuntu/Debian), macOS, or Windows.

## Installation & Setup 

1. **Database Configuration**

Ensure MySQL 8 is installed & running, create the database, and run the consolidated schema scripts:

```text
Log in to MySQL and run your setup schema 
sudo mysql < src/main/resources/sql/schema.sql
```



## Application Execution

## WTC Tracking 

WTC-Q4QETHF7

## LICENSE

## Author/s
Liyanda Tonisi

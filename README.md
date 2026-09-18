# Carwash Manager 

A Java Swing desktop app for running a car wash: customer & vehicle tracking, a ticket board for the wash queue, simulated in-house payment processing, and revenue reports. 

Built as a Java/JDBC/MySQL take on the classic "members + dues + tickets + dashboard" pattern used by HOA/club management apps, adapted to a car wash business.
 


## Project Structure 

```
Carwash Manager/
├── .gitignore
├── .idea/
├── .mvn/
├── dependency-reduced-pom.xml
├── pom.xml
├── plans.txt
├── README.md
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
│   │   │           └── MainFrame.java
│   │   └── resources/
│   │       └── sql/
│   │           ├── customers.sql
│   │           ├── employees.sql
│   │           ├── memberships.sql
│   │           ├── payments.sql
│   │           ├── services.sql
│   │           ├── tickets.sql
│   │           └── vehicles.sql
│   └── test/
│       └── java/com/carwash/
│           ├── daotests/
│           │   ├── CustomerDAOTest.java
│           │   ├── EmployeeDAOTest.java
│           │   ├── MembershipDAOTest.java
│           │   ├── PaymentDAOTest.java
│           │   ├── ServiceDAOTest.java
│           │   ├── TicketDAOTest.java
│           │   └── VehicleDAOTest.java
│           ├── dbtests/
│           │   └── DBConnectionTest.java
│           ├── modeltests/
│           │   ├── CustomerTest.java
│           │   ├── EmployeeTest.java
│           │   ├── MembershipTest.java
│           │   ├── PaymentTest.java
│           │   ├── ServiceTest.java
│           │   ├── TicketTest.java
│           │   └── VehicleTest.java
│           ├── servicetests/
│           │   ├── PaymentServiceTest.java
│           │   ├── ReportServiceTest.java
│           │   └── TicketServiceTest.java
│           └── uitests/
│               └── MainFrameTest.java
└── target/
```

## WTC Tracking 

WTC-Q4QETHF7

## Author/s
Liyanda Tonisi

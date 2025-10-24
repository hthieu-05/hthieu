# HRMS WebApp (JSP/Servlet + H2)

A small Human Resources Management System built with JSP/Servlets, H2 embedded DB, Bootstrap UI. Suitable for NetBeans with Apache Tomcat 10.

## Features
- Employees: CRUD basic fields per assignment
- Salary grades: manage min/max salary
- Attendance: monthly records (Present/Absent/Leave)
- Payroll: base salary, bonus, deduction, net
- Rewards/Discipline entries per employee

## Requirements
- Java 11+
- Maven 3.8+
- Apache Tomcat 10 (Jakarta Servlet 5)

## Run in NetBeans
1. Open NetBeans, File > Open Project, select the project folder.
2. Ensure Tomcat 10 is registered in NetBeans (Services > Servers).
3. Right click project > Run. NetBeans builds WAR and deploys to Tomcat.

If running Maven locally:
```bash
mvn package
```
The generated WAR is at `target/hrms-webapp.war`.

## App URLs
- `/` Home
- `/employees` Employees list and create
- `/grades` Salary grades
- `/attendance?year=2025&month=10` Attendance list
- `/payroll?year=2025&month=10` Payroll list
- `/discipline?employeeId=1` Reward/Discipline of employee

## Notes
- Database is H2 file stored under `./data/hrms` relative to the server working directory. Tables are created automatically on startup.

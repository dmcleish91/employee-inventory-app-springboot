# Spring Boot, MySQL, JPA, Rest API

Build an Employee/Inventory CRUD API for a store dashboard using Spring Boot, MySQL, JPA and Hibernate.

[Demo application using endpoint](https://reactjs-frontend-employeestock.herokuapp.com/)

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/dmcleish91/employee-inventory-app-springboot.git
```

**2. Create Mysql database**
```bash
create database employee_info;
```
- run `src/main/resources/sql scripts/sql queries.sql`

**3. Update mysql username and password as per your installation**

+ open `src/main/resources/application.properties`
+ update `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

## Explore Rest APIs

The app defines following CRUD APIs.

### Employees

| Method | Url | Description |
| ------ | --- | ----------- |
| GET    | /api/employees/all | Get a list of all employees |
| GET    | /api/employees/page/{pageNum} | Get a page of sorted employees |
| GET    | /api/employees/total | Get total number of employees |
| GET    | /api/employees/firstName/{name} | Get employee by searching first name |
| GET    | /api/employees/{theId}| Get employee by id number |
| POST   | /api/employees/ | Add employee |
| PUT    | /api/employees/{theId} | Update employee by id |
| DELETE | /api/employees/{theId} | Delete employee |

### Stock

| Method | Url | Description |
| ------ | --- | ----------- |
| GET    | /api/stock/all | Get a list of all stocks |
| GET    | /api/stock/page/{pageNum} | Get a page of sorted stocks |
| GET    | /api/stock/availableSum | Get total number of all available stock units |
| GET    | /api/stock/soldSum | Get total number of sold stock units |
| GET    | /api/stock/{theId}| Get stock by id |
| POST   | /api/stock/ | Add stock |
| PUT    | /api/stock/{theId} | Update stock by id |
| DELETE | /api/stock/{theId} | Delete stock |

Test endpoints using Postman or preferred method.

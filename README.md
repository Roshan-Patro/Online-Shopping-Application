# Get & Go Bazar...!!

A backend system for an online shopping application with APIs for all relevant functionalities including user and admin registration, log in and CRUD functionalities for both admin and customer as per user eligibility. 

## Tech Stack and Tools:

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* MySQL
* Postman
* Swagger

<!-- <p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="java" />
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" alt="mysql" />
</p> -->



## More about the project -
<!-- ![Get_Go_Bazar !!](https://user-images.githubusercontent.com/107461385/216571082-cddace21-988d-4cba-bafc-545b2553337d.jpeg) -->
<p align="center">
<img src="https://user-images.githubusercontent.com/107461385/216571082-cddace21-988d-4cba-bafc-545b2553337d.jpeg" width="800px" />
</p>
<br />

## ER Diagram -

<!-- ![ER Diagram](https://user-images.githubusercontent.com/107461385/216570084-222248a1-6c27-4f12-a05b-b7e67b327c31.png) -->
<p align="center">
<img src="https://user-images.githubusercontent.com/107461385/216570084-222248a1-6c27-4f12-a05b-b7e67b327c31.png" width="800px" />
</p>

## Modules -

This project has following modules:

- Admin Module
- Customer Module
- Cart and Cart-Product Module
- Category Module
- Product Module
- Orders Module
- Address Module

### 🚀 Admin Features -
- Admin Login
- Admin Logout
- Add an admin
- Update admin details
- View admin details by admin id
- Delete admin by admin id
- View all admin details
- Update admin password

### 🚀 Customer Features -
- Customer Login
- Customer Logout
- Add a customer
- View customer details by id
- Update customer details 
- Delete customer by customer id
- View all customer details
- Update customer password

### 🚀 Cart and Cart-Product Features -
- Add a product to cart
- Remove a product from cart
- Update the quantity of a product in cart
- Remove all products form a cart
- View all products in a cart
- View total cart price for a cart of a customer by customer id

### 🚀 Category Features -
- Add a category
- Remove a category
- Get all categories
- Get a category by id

### 🚀 Product Features -
- View all products
- Add a product
- Update a product
- View a product by product id
- View all products by category
- Remove a product
- Add a product to a category

### 🚀 Orders Features -
- Add an order with an existing address
- Add an order with a new address
- Update order status
- Remove an order
- View an order by order id
- View all orders by date
- View all orders by city name
- View all orders of a customer
- View all orders in ascending order by any specific property
- View all orders in descending order by any specific property
- View all orders with pagination and in ascending order by any specific property
- View all orders with pagination and in descending order by any specific property

### 🚀 Address Features -
- Add an address to a customer
- Update an address
- Remove an address
- View all addresses
- View an address by address id
- View all addresses by pincode
- View all addresses by building name
- View all addresses of orders by pincode
- View all addresses of customers by pincode  
- View all addresses of orders by country and state name
- View all addresses of customers by country and state name 
- View all addresses in ascending order by any specific property
- View all addresses in descending order by any specific property
- View all addresses with pagination and in ascending order by any specific property
- View all addresses with pagination and in descending order by any specific property

## Installation & Run -

* Before running the API server, you should update the database config inside the [application.properties](https://github.com/Roshan-Patro/lazy-jeans-3588/blob/main/OnlineShoppingApplication/src/main/resources/application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8877
    spring.datasource.url=jdbc:mysql://localhost:3306/db22
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root

```

## API Root Endpoint -
```
https://localhost:8877/
```
```
http://localhost:8877/swagger-ui/index.html
```

## Contributors -

* [T Roshan Kumar Patro](https://github.com/Roshan-Patro)
* [Nikhil Kharat](https://github.com/nikhilkharat)
* [Shiv Kumar](https://github.com/Shiv-96)
* [Hemachandran J](https://github.com/hemachandran-4)
* [Rajashekhar Mallayya Sambalad](https://github.com/rajashekharms369)

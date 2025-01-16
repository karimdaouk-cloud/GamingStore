# Gaming Store E-Commerce Project

A Java web application for an online gaming peripherals store, featuring user and admin functionalities, shopping cart management, and order processing.

## Table of Contents
- [Complete Setup Guide](#complete-setup-guide)
- [Features](#features)
- [Database Structure](#database-structure)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [Technologies Used](#technologies-used)
- [Common Issues](#common-issues-and-solutions)

## Complete Setup Guide

### 1. Prerequisites Installation
- Install Java JDK 8 or higher
- Install Eclipse IDE (preferably Eclipse IDE for Enterprise Java Developers)
- Install MySQL Server and MySQL Workbench
- Install Apache Tomcat 9.0

### 2. Database Setup
- Open MySQL Workbench
- Connect to MySQL Server
- Copy the code from `database_setup.sql`
- Execute the script to create database and tables

### 3. Project Setup in Eclipse
1. Import Project:
   - Open Eclipse
   - Go to File → Import → Git → Projects from Git → Clone URI
   - Paste this repository URL
   - Follow the import wizard steps

2. Configure Project:
   - Right-click project → Properties → Project Facets 
   - Check "Dynamic Web Module", "Java", "JavaScript"
   - Click Apply

### 4. Add MySQL Connector
- Right-click project
- Build Path → Configure Build Path
- Libraries → Add External JARs
- Select mysql-connector-java-8.0.28.jar
- Apply and Close

### 5. Configure Tomcat
- Window → Preferences → Server → Runtime Environments
- Add new server → Apache Tomcat 9.0
- Select Tomcat installation directory

### 6. Run the Project
- Right-click project → Run As → Run on Server
- Select Tomcat 9.0
- Access through: http://localhost:8080/GamingStore/

### 7. Test Account Access
- Admin Account:
  - Username: admin
  - Password: admin123
- Or register a new user account

## Features

### User Features
- User registration and login
- Browse products
- Shopping cart management
- Checkout process
- Order history

### Admin Features
- Product management (Add/Edit/Delete)
- User management
- Stock management
- View all orders

## Database Structure

### Tables
1. users
   - Stores user accounts and credentials
   - Fields: id, username, password, email

2. products
   - Stores product information
   - Fields: id, name, price, description, stock_quantity

3. cart_items
   - Manages shopping cart
   - Fields: id, user_id, product_id, quantity, date_added

4. orders
   - Stores order information
   - Fields: id, user_id, total_amount, status, order_date

5. order_items
   - Stores items within each order
   - Fields: id, order_id, product_id, quantity, price

## Project Structure

### Source Files (src/main/java/com/gaming/)
- **beans/**
  - Cart.java
  - CartItem.java
  - Order.java
  - OrderItem.java
  - Product.java
  - User.java
- **dao/**
  - CartItemDAO.java
  - OrderDAO.java
  - ProductDAO.java
  - UserDAO.java
- **servlets/**
  - AdminProductServlet.java
  - CartServlet.java
  - CheckoutServlet.java
  - LoginServlet.java
  - LogoutServlet.java
  - RegisterServlet.java
- **util/**
  - DBUtil.java

### Web Files (src/main/webapp/)
- **WEB-INF/**
  - web.xml
- admin.jsp
- cart.jsp
- login.jsp
- orderConfirmation.jsp
- orders.jsp
- products.jsp
- register.jsp
- styles.css

## Dependencies
Current dependencies:
- MySQL Connector/J (8.0.28)
- Jakarta Servlet API (included with Tomcat)
- Jakarta JSP API (included with Tomcat)

## Technologies Used
- Java Servlets & JSP
- MySQL Database
- HTML/CSS
- JavaScript
- JDBC

## Common Issues and Solutions

### Port Issues
If port 8080 is already in use:
1. Open Command Prompt as administrator
2. Run: `netstat -ano | findstr 8080`
3. Kill the process: `taskkill /PID [PID] /F`

### Database Connection Issues
- Verify MySQL service is running
- Check database credentials in DBUtil.java
- Ensure MySQL Connector is properly added to build path

### Tomcat Issues
- Clean Tomcat work directory
- Verify Tomcat version (9.0 recommended)
- Check Tomcat server settings in Eclipse

## Version Information
- Java Version: 8 or higher
- MySQL Version: 8.0
- Tomcat Version: 9.0
- MySQL Connector Version: 8.0.28

## Author
- Karim Daouk
- Project developed for American University of Science and Technology
- Course: CSI 409
- Year: 2024-2025

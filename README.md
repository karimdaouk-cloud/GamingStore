# Gaming Store E-Commerce Project

A Java web application for an online gaming peripherals store with user and admin functionalities.

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
- Open Eclipse
- Go to File → Import → Git → Projects from Git → Clone URI
- Paste this repository URL
- Follow the import wizard steps
- Right-click project → Properties → Project Facets 
- Check "Dynamic Web Module", "Java", "JavaScript"

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

## Technologies Used
- Java Servlets & JSP
- MySQL Database
- HTML/CSS
- JavaScript
- JDBC

## Common Issues and Solutions
- If you get a port error (8080 already in use), kill the process or change Tomcat's port
- If database connection fails, verify MySQL service is running
- Make sure all required dependencies are properly added to the build path

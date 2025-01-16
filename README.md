# Gaming Store E-Commerce Project

A Java web application for an online gaming peripherals store with user and admin functionalities.

## Setup Instructions

### Prerequisites
- Java JDK 8 or higher
- Eclipse IDE
- MySQL Server
- Apache Tomcat 9.0
- MySQL Connector/J

### Database Setup
1. Open MySQL Workbench
2. Run the provided `database_setup.sql` script
3. Default admin credentials:
   - Username: admin
   - Password: admin123

### Project Setup in Eclipse
1. Import Project
   - File → Import → Existing Projects into Workspace
   - Select the project folder
   - Click Finish

2. Add MySQL Connector
   - Right-click on project
   - Build Path → Configure Build Path
   - Add External JARs
   - Select mysql-connector-java-*.jar
   - Apply and Close

3. Configure Tomcat
   - Window → Preferences → Server → Runtime Environments
   - Add Apache Tomcat 9.0
   - Select Tomcat installation directory

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

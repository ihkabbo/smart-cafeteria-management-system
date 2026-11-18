# Smart Cafeteria Management System

A Java-based Smart Cafeteria Management System designed to simplify cafeteria operations through a graphical user interface and database integration.

## Project Overview

This project is developed using the Java programming language to manage cafeteria activities efficiently.

The system provides separate modules for students and administrators. It allows students to register, log in, browse food menus, place orders, manage their carts, and handle payments. Administrators can manage students, food categories, menu items, orders, payments, and stock.

The application uses Java Swing for its graphical user interface and MySQL for database management.

## Features

- Separate Admin and Student modules.
- Student registration and login.
- Admin login and dashboard.
- Food category management.
- Food menu management.
- Shopping cart management.
- Food ordering system.
- Order history and management.
- Payment management.
- Student profile management.
- Stock and quantity management.
- MySQL database integration.
- Graphical user interface using Java Swing.

## Modules

| Module | Description |
|---|---|
| Admin | Manages cafeteria operations and system activities. |
| Student | Allows students to browse menus and place orders. |
| Menu Management | Manages food items, categories, prices, and availability. |
| Order Management | Handles customer orders and order history. |
| Payment Management | Manages payment-related operations. |
| Stock Management | Manages food stock and quantities. |

## Software and Technologies

- Programming Language: Java
- GUI Framework: Java Swing
- Database: MySQL
- Database Connectivity: JDBC
- Development Environment: NetBeans IDE

## How It Works

1. The application starts with the login interface.
2. Students can register or log in to their accounts.
3. Administrators log in to access the admin dashboard.
4. Students can browse available food items and categories.
5. Students can add food items to their cart and place orders.
6. The system manages order and payment information.
7. Administrators can manage students, menus, categories, orders, payments, and stock.
8. MySQL is used to store and retrieve application data.

## Database Configuration

The project uses MySQL as its database management system.

Database connection settings are configured in `DatabaseConnection.java`.

Before running the application, users need to configure their own MySQL database credentials and ensure that the required database and tables are available.

## Limitations

- Requires a configured MySQL database.
- Requires a compatible Java development environment.
- Database connection settings must be configured before running the application.

## Future Improvements

- Implement secure password hashing.
- Add advanced sales and order reports.
- Improve payment integration.
- Add more advanced inventory management features.
- Improve user experience and interface design.
- Introduce additional cafeteria management features.

## Project Files

- [Main Java Class](src/cafeteriamanagementsystem/CafeteriaManagementSystem.java) – Application entry point.
- [GUI Package](src/cafeteriamanagementsystem/gui/) – Graphical user interface files.
- [DAO Package](src/cafeteriamanagementsystem/dao/) – Database access classes.
- [Service Package](src/cafeteriamanagementsystem/service/) – Application service classes.
- [Smart_Cafeteria_Management_System.pdf](Smart_Cafeteria_Management_System.pdf) – Project documentation and report.

## Author

**Developed by:** Md. Imran Hossen

Academic project focused on Java programming, object-oriented programming, GUI development, database connectivity, and cafeteria management.

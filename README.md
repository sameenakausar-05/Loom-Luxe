# 🛍️ Loom & Luxe

Loom & Luxe is a full-stack E-Commerce Web Application developed using Java, JSP, Servlets, JDBC, MySQL, HTML, CSS, and JavaScript following the MVC (Model-View-Controller) Architecture. The application enables users to browse products, manage shopping carts, place orders, and securely manage their accounts.

---

## 🚀 Features

### 👤 User Module
- User Registration
- User Login & Authentication
- Session Management
- Profile Management

### 🛒 Shopping Module
- Browse Product Catalog
- View Product Details
- Add Products to Cart
- Update Cart Quantity
- Remove Products from Cart

### 📦 Order Module
- Place Orders
- View Order Summary
- Order Tracking Status

### 🔐 Admin Module
- Manage Products
- Manage Orders
- Update Product Information
- Delete Products

---

## 🏗️ Architecture

The project follows the MVC Architecture Pattern:

```text
View Layer (JSP Pages)
        ↓
Controller Layer (Servlets)
        ↓
DAO Layer
        ↓
MySQL Database
```

### Model
- User.java
- Product.java
- Cart.java
- CartItem.java
- Order.java
- OrderItem.java

### View
- index.jsp
- login.jsp
- register.jsp
- products.jsp
- cart.jsp
- checkout.jsp

### Controller
- LoginServlet
- RegisterServlet
- ProductServlet
- CartServlet
- OrderServlet

### DAO Layer
- UserDAO / UserDAOImpl
- ProductDAO / ProductDAOImpl
- OrderDAO / OrderDAOImpl

---

## 🛠️ Technologies Used

### Frontend
- HTML5
- CSS3
- JavaScript
- JSP

### Backend
- Java
- Servlets
- JDBC

### Database
- MySQL

### Server
- Apache Tomcat 10

### Build Tool
- Maven

### IDE
- Eclipse IDE

---

## 🗄️ Database Design

### User Table
Stores user registration and login details.

### Product Table
Stores product information including name, description, image, and price.

### Cart Table
Maintains shopping cart information during user sessions.

### Order Table
Stores order details.

### OrderItem Table
Stores individual products associated with an order.

---

## 📂 Project Structure

```text
LoomAndLuxe/
│
├── src/main/java
│   ├── com.loomandluxe.model
│   ├── com.loomandluxe.dao
│   ├── com.loomandluxe.daoimpl
│   ├── com.loomandluxe.controller
│   └── com.loomandluxe.util
│
├── src/main/webapp
│   ├── css
│   ├── images
│   ├── index.jsp
│   ├── login.jsp
│   ├── register.jsp
│   ├── products.jsp
│   ├── cart.jsp
│   └── checkout.jsp
│
└── pom.xml
```

---

## ⚙️ Installation & Setup

### Clone Repository

```bash
git clone https://github.com/yourusername/LoomAndLuxe.git
```

### Import Project

- Open Eclipse IDE
- Import Existing Maven Project
- Select Project Folder

### Configure Database

Create MySQL Database:

```sql
CREATE DATABASE loom_luxe_db;
```

Update database credentials in:

```java
DBConnection.java
```

```java
private static final String URL =
"jdbc:mysql://localhost:3306/loom_luxe_db";

private static final String USERNAME =
"root";

private static final String PASSWORD =
"your_password";
```

### Run Application

- Start Apache Tomcat Server
- Deploy Project
- Open Browser

```text
http://localhost:8080/LoomAndLuxe
```

---

## 📸 Screenshots

### Home Page
- Product Categories
- Featured Products
- Responsive UI

### Login Page
- Secure Authentication

### Product Catalog
- Dynamic Product Listing

### Shopping Cart
- Add/Remove Products
- Quantity Management

### Checkout
- Order Confirmation

---

## 🎯 Learning Outcomes

Through this project, I gained hands-on experience in:

- MVC Architecture
- Java Web Development
- JSP & Servlets
- JDBC Connectivity
- MySQL Database Design
- Session Management
- CRUD Operations
- DAO Design Pattern
- Maven Project Management
- Apache Tomcat Deployment

---

## 🔮 Future Enhancements

- Payment Gateway Integration
- Wishlist Functionality
- Product Search & Filtering
- Email Notifications
- Product Reviews & Ratings
- Cloud Deployment (AWS/Azure)
- REST API Integration

---

## 👨‍💻 Author

**Sameena Kausar Magami**

Java Full Stack Developer

LinkedIn: https://www.linkedin.com/in/sameena-kausar-magami-215a08333?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app

GitHub: https://github.com/sameenakausar-05

---

⭐ If you found this project useful, please consider giving it a star on GitHub.

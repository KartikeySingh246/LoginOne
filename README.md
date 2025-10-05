# Java Login System - Setup Guide

## How to Run This Project on Your System

This guide will help you set up and run the Java Login System on your computer.

---

## PREREQUISITES

Before you begin, make sure you have:

### 1. Java Development Kit (JDK)
- **Version Required:** JDK 8 or higher
- **Download:** [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)
- **Check if installed:** Open Command Prompt/Terminal and type:
  ```bash
  java -version
  ```

### 2. MySQL Database
- **Version Required:** MySQL 8.0 or higher
- **Download:** [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/)
- **Check if installed:** Open Command Prompt/Terminal and type:
  ```bash
  mysql --version
  ```

### 3. Eclipse IDE (or any Java IDE)
- **Download:** [https://www.eclipse.org/downloads/](https://www.eclipse.org/downloads/)
- **Alternative IDEs:** IntelliJ IDEA, NetBeans, VS Code

### 4. MySQL JDBC Driver
- **Download:** [https://dev.mysql.com/downloads/connector/j/](https://dev.mysql.com/downloads/connector/j/)
- Select "Platform Independent" and download the ZIP file

---

## INSTALLATION STEPS

### STEP 1: Install MySQL Database

1. **Download MySQL Installer**
   - Go to [MySQL Downloads](https://dev.mysql.com/downloads/installer/)
   - Choose "Windows" (or your OS)
   - Download the larger installer (~400 MB)

2. **Run MySQL Installer**
   - Choose "Developer Default" installation
   - Click "Execute" to install all components
   - Set a **root password** (remember this!)
   - Example: `root123` or `admin`
   - Complete the installation

3. **Verify MySQL is Running**
   - Open **MySQL Workbench**
   - Connect to "Local instance MySQL80"
   - Enter your root password

---

### STEP 2: Set Up Database

1. **Open MySQL Workbench**
   - Double-click on "Local instance MySQL80"
   - Enter your root password

2. **Create Database and Table**
   - Click on the SQL editor (or File → New Query Tab)
   - Copy and paste this SQL code:

```sql
-- Create Database
CREATE DATABASE LoginOne;

-- Use the Database
USE LoginOne;

-- Create Users Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Verify Table Created
SHOW TABLES;
DESCRIBE users;
```

3. **Execute the SQL**
   - Click the lightning bolt icon ⚡ to run
   - You should see success messages

4. **Verify Database Created**
   - Look at "Schemas" panel on left
   - Click refresh icon 🔄
   - You should see "LoginOne" database
   - Expand it to see "users" table

---

### STEP 3: Download MySQL JDBC Driver

1. **Download the Driver**
   - Go to: [MySQL Connector/J Downloads](https://dev.mysql.com/downloads/connector/j/)
   - Select "Platform Independent"
   - Download the ZIP file
   - Click "No thanks, just start my download"

2. **Extract the ZIP**
   - Extract the downloaded ZIP file
   - Find the JAR file: `mysql-connector-j-x.x.x.jar`
   - Remember this location!

---

### STEP 4: Download the Project

**Option A: Download from GitHub**
1. Go to the project repository
2. Click the green "Code" button
3. Select "Download ZIP"
4. Extract the ZIP file to your desired location

**Option B: Clone with Git**
```bash
git clone https://github.com/yourusername/Java-Login-System.git
```

---

### STEP 5: Import Project in Eclipse

1. **Open Eclipse IDE**
   - Launch Eclipse
   - Select your workspace

2. **Import Project**
   - Go to: **File → Import**
   - Expand "General"
   - Select "Existing Projects into Workspace"
   - Click "Next"

3. **Select Project Folder**
   - Click "Browse"
   - Navigate to the downloaded project folder
   - Select the "LoginOne" folder
   - Click "Finish"

4. **Project Should Appear**
   - Check Package Explorer (left side)
   - You should see "LoginOne" project with all files

---

### STEP 6: Add MySQL JDBC Driver to Project

1. **Add External JAR**
   - In Package Explorer, **right-click** on "LoginOne" project
   - Select **Build Path → Configure Build Path**
   - Click the **"Libraries"** tab
   - Click **"Add External JARs..."**
   - Browse to your MySQL Connector JAR file
   - Select `mysql-connector-j-x.x.x.jar`
   - Click **"Open"**
   - Click **"Apply and Close"**

2. **Verify JAR Added**
   - Expand "Referenced Libraries" in Package Explorer
   - You should see the MySQL JAR file listed

---

### STEP 7: Configure Database Connection

1. **Open DatabaseConnection.java**
   - Navigate to: `src → databaseConnection → DatabaseConnection.java`
   - Double-click to open

2. **Update Database Password**
   - Find these lines:
```java
private static final String URL = "jdbc:mysql://localhost:3306/LoginOne";
private static final String USER = "root";
private static final String PASSWORD = "your_password";  // CHANGE THIS!
```

3. **Replace Password**
   - Change `"your_password"` to your actual MySQL root password
   - Example:
```java
private static final String PASSWORD = "root123";  // Your MySQL password
```

4. **Save the File** (Ctrl + S)

---

### STEP 8: Run the Application

1. **Locate LoginPage.java**
   - Navigate to: `src → loginPage → LoginPage.java`

2. **Run the Application**
   - **Right-click** on LoginPage.java
   - Select **Run As → Java Application**

3. **Application Should Launch**
   - A login window should appear
   - If you see the GUI, congratulations! ✅

---

## USING THE APPLICATION

### Create a New Account

1. Click the **"Register"** button on login page
2. Fill in the registration form:
   - Username: `testuser`
   - Email: `test@example.com`
   - Password: `password123`
   - Confirm Password: `password123`
3. Click **"Register"**
4. You should see "Registration Successful!" message

### Login

1. Enter your credentials on login page:
   - Username: `testuser`
   - Password: `password123`
2. Click **"Login"**
3. Dashboard should open with welcome message

### Verify in Database

1. Open MySQL Workbench
2. Run this query:
```sql
USE LoginOne;
SELECT * FROM users;
```
3. You should see your registered user!

---

## TROUBLESHOOTING

### Problem 1: "ClassNotFoundException: com.mysql.cj.jdbc.Driver"

**Solution:**
- MySQL JDBC driver not added properly
- Go back to Step 6 and add the JAR file again
- Make sure you clicked "Apply and Close"

---

### Problem 2: "Access denied for user 'root'@'localhost'"

**Solution:**
- Incorrect password in DatabaseConnection.java
- Open DatabaseConnection.java
- Update the PASSWORD variable with your correct MySQL password
- Save and run again

---

### Problem 3: "Unknown database 'LoginOne'"

**Solution:**
- Database not created in MySQL
- Go back to Step 2
- Run the SQL script to create database and table
- Refresh MySQL Workbench

---

### Problem 4: "Communications link failure"

**Solution:**
- MySQL service is not running
- **Windows:** Press Win + R, type `services.msc`, find "MySQL80", click "Start"
- **Mac/Linux:** Run `sudo systemctl start mysql`

---

### Problem 5: Login window doesn't appear

**Solution:**
- Check Console for error messages
- Make sure all 6 Java files are present
- Clean and rebuild project: **Project → Clean**
- Try running again

---

### Problem 6: "Port 3306 already in use"

**Solution:**
- Another MySQL instance is running
- Stop other MySQL services
- Or change port in DatabaseConnection.java

---

## PROJECT STRUCTURE

After setup, your project should look like this:

```
LoginOne/
├── src/
│   ├── databaseConnection/
│   │   └── DatabaseConnection.java
│   ├── user/
│   │   └── User.java
│   ├── userDAO/
│   │   └── UserDAO.java
│   ├── loginPage/
│   │   └── LoginPage.java
│   ├── registerPage/
│   │   └── RegisterPage.java
│   └── dashboardPage/
│       └── DashboardPage.java
├── Referenced Libraries/
│   └── mysql-connector-j-x.x.x.jar
└── JRE System Library
```

---

## SYSTEM REQUIREMENTS

### Minimum Requirements
- **OS:** Windows 10/11, macOS, or Linux
- **RAM:** 4 GB
- **Storage:** 500 MB free space
- **Processor:** Intel Core i3 or equivalent
- **Java:** JDK 8 or higher
- **MySQL:** 8.0 or higher

### Recommended Requirements
- **RAM:** 8 GB or more
- **Processor:** Intel Core i5 or better
- **SSD:** For faster performance

---

## TESTING THE SETUP

Run these tests to verify everything works:

### Test 1: Database Connection
```java
// Create a test file to check connection
public class TestDB {
    public static void main(String[] args) {
        java.sql.Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("✓ Database connected!");
        } else {
            System.out.println("✗ Connection failed!");
        }
    }
}
```

### Test 2: Registration
- Try registering a new user
- Check if data appears in MySQL

### Test 3: Login
- Login with registered credentials
- Verify dashboard opens

### Test 4: Error Handling
- Try empty fields - should show error
- Try duplicate username - should show error
- Try wrong password - should show error

---

## ADDITIONAL NOTES

### Security Warning
⚠️ This project stores passwords in **plain text** for educational purposes. In production:
- Use password hashing (BCrypt, SHA-256)
- Never commit database passwords to GitHub
- Use environment variables for credentials

### Database Credentials
🔒 **Important:** Never share your database password publicly!

### Support
If you encounter issues:
1. Check the Troubleshooting section
2. Verify all steps were followed correctly
3. Check Eclipse Console for error messages
4. Contact the project maintainer

---

## VIDEO TUTORIAL (Optional)

For visual learners, you can record a setup video showing:
1. MySQL installation
2. Database creation
3. Eclipse project import
4. Running the application

---

## FAQ

**Q: Do I need internet connection to run this?**
A: No, once everything is installed, it runs offline.

**Q: Can I use a different IDE?**
A: Yes! Works with IntelliJ IDEA, NetBeans, or VS Code with Java extensions.

**Q: Can I change the database name?**
A: Yes, but update it in DatabaseConnection.java and create the database with the new name.

**Q: How do I reset the database?**
A: Run `DROP DATABASE LoginOne;` in MySQL, then recreate it.

**Q: Can multiple users login simultaneously?**
A: Yes, the database supports multiple users.

---

## CONTACT

For issues or questions:
- **GitHub Issues:** [Create an issue on the repository]
- **Email:** [your-email@example.com]

---

## LICENSE

This project is for educational purposes.

---

**Created by:** [Your Name]  
**Last Updated:** January 2025  
**Version:** 1.0

---

*Happy Coding! 🚀*

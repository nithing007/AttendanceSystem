# 📚 Attendance Management System

A simple and efficient **console-based Attendance Management System** built with **Java, JDBC, and MySQL**. The application allows users to add students, record daily attendance, and generate attendance reports with automatically calculated attendance percentages.

The project demonstrates how a Java application can interact with a MySQL database using **JDBC**, execute SQL queries safely with **PreparedStatement**, and process database results using **ResultSet**.

---

## ✨ Features

* 👨‍🎓 **Add Students** — Add new students to the attendance database.
* 🔒 **Duplicate Prevention** — Prevent duplicate student entries.
* 📝 **Mark Attendance** — Record daily attendance for all registered students.
* 📅 **Track Working Days** — Automatically maintain the total number of working days.
* 📊 **Attendance Reports** — Display each student's attendance and percentage.
* 🧮 **Automatic Calculation** — Calculate attendance percentages based on present days and total working days.
* ⚠️ **Input Handling** — Handle invalid menu choices without crashing the application.
* 🛡️ **Safe Database Operations** — Use JDBC `PreparedStatement` for database queries.
* 🚫 **Zero-Division Protection** — Safely handle reports when no attendance has been recorded.

---

## 🎯 Why This Project?

Managing attendance manually can become difficult when the number of students increases.

This project provides a simple database-driven solution where:

**Students → Attendance → Database → Attendance Report**

Instead of maintaining attendance only in memory, the application stores student and attendance information in **MySQL**, allowing the data to persist between program executions.

---

## 🛠️ Tech Stack

| Technology           | Purpose                          |
| -------------------- | -------------------------------- |
| ☕ Java               | Application development          |
| 🔌 JDBC              | Java–MySQL database connectivity |
| 🗄️ MySQL            | Data storage                     |
| 📜 SQL               | Database operations              |
| 🔐 PreparedStatement | Safer parameterized SQL queries  |
| 📊 ResultSet         | Reading database results         |

---

## 🏗️ Project Structure

```text
AttendanceSystem/
│
├── .vscode/
│
├── bin/
│   └── Compiled Java files
│
├── lib/
│   └── Required libraries / dependencies
│
├── src/
│   └── Java source files
│
├── README.md
│
└── attendance_db.sql
```

> The exact contents of `bin`, `lib`, and `src` may vary depending on the development environment and project version.

---

## 🗄️ Database

The application uses a MySQL database named:

```text
attendance_db
```

### Database Tables

#### `students`

Stores registered student information and their total number of present days.

```sql
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    present_days INT DEFAULT 0
);
```

#### `attendance_days`

Stores the total number of working/attendance days.

```sql
CREATE TABLE attendance_days (
    total_days INT DEFAULT 0
);
```

Initialize the attendance day counter:

```sql
INSERT INTO attendance_days VALUES (0);
```

---

## ⚙️ Prerequisites

Before running the project, make sure you have:

* ☕ **Java JDK** installed
* 🗄️ **MySQL Server** installed and running
* 🔌 **MySQL JDBC Driver** available to the project
* 💻 A Java-compatible IDE such as VS Code, IntelliJ IDEA, or Eclipse

You can verify Java installation with:

```bash
java -version
```

and:

```bash
javac -version
```

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/nithing007/AttendanceSystem.git
```

Move into the project directory:

```bash
cd AttendanceSystem
```

---

### 2. Set Up MySQL

Start your MySQL server and create the database:

```sql
CREATE DATABASE attendance_db;
```

Select the database:

```sql
USE attendance_db;
```

Create the required tables:

```sql
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    present_days INT DEFAULT 0
);

CREATE TABLE attendance_days (
    total_days INT DEFAULT 0
);
```

Initialize the working-day counter:

```sql
INSERT INTO attendance_days VALUES (0);
```

You can also use the SQL script included in the repository:

```text
attendance_db.sql
```

---

### 3. Configure Database Connection

Make sure the Java application is configured with your local MySQL credentials.

Typical connection details include:

```text
Host: localhost
Port: 3306
Database: attendance_db
Username: your_mysql_username
Password: your_mysql_password
```

> Do not commit real database passwords or other sensitive credentials to GitHub.

---

### 4. Compile the Application

If the Java source file is directly accessible from the current directory:

```bash
javac AttendanceJDBC.java
```

---

### 5. Run the Application

```bash
java AttendanceJDBC
```

If you are using an IDE, you can also run the main Java class directly from the IDE.

---

## 🖥️ Application Menu

When the application starts, the user is presented with a menu similar to:

```text
===== Attendance Management System =====

1. Add Student
2. Mark Attendance
3. View Report
4. Exit
```

---

## 📋 How It Works

### 1️⃣ Add Student

Select:

```text
1. Add Student
```

Enter the student's name.

Example:

```text
Enter choice: 1
Enter student name: John
Student added successfully.
```

The student is stored in the MySQL `students` table.

---

### 2️⃣ Mark Attendance

Select:

```text
2. Mark Attendance
```

The application asks whether each student is present.

Example:

```text
Enter choice: 2
Is John present? (yes/no): yes
Attendance marked successfully.
```

The system updates the student's present-day count and increments the total working-day count.

---

### 3️⃣ View Attendance Report

Select:

```text
3. View Report
```

The application calculates and displays attendance information.

Example:

```text
===== Attendance Report =====

--------------------------------
Name          : John
Present Days  : 5
Total Days    : 6
Attendance %  : 83%
```

---

### 4️⃣ Exit

Select:

```text
4. Exit
```

The application closes safely.

---

## 🔄 Application Workflow

```text
              ┌─────────────────┐
              │     Start       │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │ Connect to      │
              │ MySQL Database  │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │  Display Menu   │
              └────────┬────────┘
                       │
          ┌────────────┼────────────┐
          │            │            │
          ▼            ▼            ▼
     Add Student   Mark Attendance  View Report
          │            │            │
          ▼            ▼            ▼
       MySQL DB      Update DB    Calculate %
          │            │            │
          └────────────┼────────────┘
                       │
                       ▼
                 Return to Menu
```

---

## 🧠 Attendance Calculation

The attendance percentage is calculated using:

```text
Attendance % = (Present Days / Total Working Days) × 100
```

For example:

```text
Present Days = 5
Total Working Days = 6

Attendance = (5 / 6) × 100
           ≈ 83%
```

---

## 📚 Concepts Demonstrated

This project demonstrates several important Java and database concepts:

* Java fundamentals
* Menu-driven programming
* JDBC connectivity
* MySQL database integration
* SQL queries
* `PreparedStatement`
* `ResultSet`
* Database CRUD operations
* Exception handling
* Input validation
* Conditional logic
* Attendance percentage calculation
* Persistent data storage

---

## 🔐 Database Security

The project uses JDBC `PreparedStatement` for database operations instead of directly constructing SQL queries from user input.

For a production application, additional security measures such as:

* Environment variables
* Password hashing
* User authentication
* Role-based access control
* Connection pooling
* Input validation

would be recommended.

---

## 🚧 Future Improvements

The current version focuses on the core attendance workflow. Possible future enhancements include:

* 👤 Student ID / registration number support
* ✏️ Update student details
* 🗑️ Delete students
* 📅 Date-wise attendance history
* 📊 Monthly and semester attendance reports
* 📄 Export reports to PDF or Excel
* 🔐 Administrator authentication
* 🖥️ Java Swing / JavaFX graphical interface
* 🌐 Web-based attendance dashboard
* 📱 Mobile-friendly interface
* 📈 Attendance analytics and visualizations

---

## 🤝 Contributing

Contributions and improvements are welcome.

To contribute:

```bash
# Fork the repository

# Clone your fork
git clone https://github.com/<your-username>/AttendanceSystem.git

# Create a new branch
git checkout -b feature/your-feature

# Make your changes

# Commit your changes
git add .
git commit -m "Add: your feature"

# Push your branch
git push origin feature/your-feature
```

Then open a Pull Request on GitHub.

---

## 🐛 Issues

If you find a bug or have an idea for improvement, please open an issue in the repository:

[Report an Issue](https://github.com/nithing007/AttendanceSystem/issues)

When reporting a bug, include:

* Description of the problem
* Steps to reproduce it
* Expected behavior
* Actual behavior
* Relevant error message or screenshot

---

## 👨‍💻 Author

**Nithin G**

GitHub: [@nithing007](https://github.com/nithing007)

---

## 📄 License

This project currently does not specify a license.

If you intend to allow others to use, modify, and distribute the project, consider adding an appropriate open-source license such as the MIT License.

---

⭐ If you find this project useful, consider giving the repository a star!

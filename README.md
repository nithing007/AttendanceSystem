# Attendance Management System

A console-based **Attendance Management System** developed using **Java, JDBC, and MySQL**. The application allows administrators to add students, mark attendance, and generate attendance reports with attendance percentages.

## 🚀 Features

* Add new students to the database.
* Prevent duplicate student entries.
* Mark daily attendance for all students.
* Automatically update total working days.
* Generate attendance reports with percentage calculations.
* Handle invalid menu choices gracefully.
* Prevent division-by-zero errors when no attendance has been marked.

---

## 🛠️ Tech Stack

* **Java**
* **JDBC**
* **MySQL**
* **SQL**
* **PreparedStatement**
* **ResultSet**

---

## 📂 Project Structure

```text
AttendanceManagementSystem/
│
├── AttendanceJDBC.java
├── README.md
└── attendance_db.sql
```

---

## 🗄️ Database Setup

### Create Database

```sql
CREATE DATABASE attendance_db;
USE attendance_db;
```

### Create `students` Table

```sql
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    present_days INT DEFAULT 0
);
```

### Create `attendance_days` Table

```sql
CREATE TABLE attendance_days (
    total_days INT DEFAULT 0
);
```

### Insert Initial Record

```sql
INSERT INTO attendance_days VALUES (0);
```

---

## ⚙️ Configuration

Update the database credentials inside `AttendanceJDBC.java`.

```java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/attendance_db",
    "root",
    "your_password"
);
```

---

## ▶️ How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/AttendanceManagementSystem.git
cd AttendanceManagementSystem
```

### 2. Compile the Program

```bash
javac AttendanceJDBC.java
```

### 3. Run the Program

```bash
java AttendanceJDBC
```

---

## 📋 Menu Options

```text
===== Attendance Management System =====

1. Add Student
2. Mark Attendance
3. View Report
4. Exit
```

---

## 💻 Sample Output

### Add Student

```text
Enter choice: 1
Enter student name: John
Student added successfully.
```

### Mark Attendance

```text
Enter choice: 2
Is John present? (yes/no): yes
Attendance marked successfully.
```

### View Report

```text
===== Attendance Report =====

--------------------------------
Name          : John
Present Days  : 5
Total Days    : 6
Attendance %  : 83%
```

---

## 🔄 Working Process

1. Add students to the system.
2. Select **Mark Attendance** to record daily attendance.
3. Total working days are automatically incremented.
4. Present students have their attendance count updated.
5. View attendance reports with percentage calculations.

---

## 📖 Concepts Used

* JDBC Connectivity
* SQL Queries
* Prepared Statements
* ResultSet Processing
* Exception Handling
* Menu-Driven Programming
* Database Operations in Java

---

## 🔮 Future Enhancements

* Delete student records.
* Update student details.
* Store date-wise attendance history.
* Export reports to PDF or Excel.
* Add authentication for administrators.
* Develop a GUI using Java Swing or JavaFX.

---

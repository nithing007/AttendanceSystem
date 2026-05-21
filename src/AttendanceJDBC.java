import java.sql.*;
import java.util.Scanner;

public class AttendanceJDBC {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/attendance_db",
                    "root",
                    "nithin007"
            );

            int choice;

            do {

                System.out.println("\n===== Attendance Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. Mark Attendance");
                System.out.println("3. View Report");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

                choice = sc.nextInt();
                sc.nextLine();

                // ================= ADD STUDENT =================

                if (choice == 1) {

                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();

                    // Check duplicate student
                    PreparedStatement check = con.prepareStatement(
                            "SELECT * FROM students WHERE name=?"
                    );

                    check.setString(1, name);

                    ResultSet rs = check.executeQuery();

                    if (rs.next()) {
                        System.out.println("Student already exists.");
                    } else {

                        PreparedStatement ps = con.prepareStatement(
                                "INSERT INTO students(name) VALUES(?)"
                        );

                        ps.setString(1, name);

                        ps.executeUpdate();

                        System.out.println("Student added successfully.");
                    }
                }

                // ================= MARK ATTENDANCE =================

                else if (choice == 2) {

                    // Increase total working days
                    Statement st = con.createStatement();

                    st.executeUpdate(
                            "UPDATE attendance_days SET total_days = total_days + 1"
                    );

                    ResultSet rs = st.executeQuery(
                            "SELECT name FROM students"
                    );

                    while (rs.next()) {

                        String name = rs.getString("name");

                        System.out.print("Is " + name + " present? (yes/no): ");

                        String status = sc.nextLine();

                        if (status.equalsIgnoreCase("yes")) {

                            PreparedStatement ps = con.prepareStatement(
                                    "UPDATE students SET present_days = present_days + 1 WHERE name=?"
                            );

                            ps.setString(1, name);

                            ps.executeUpdate();
                        }
                    }

                    System.out.println("Attendance marked successfully.");
                }

                // ================= VIEW REPORT =================

                else if (choice == 3) {

                    ResultSet days = con.createStatement().executeQuery(
                            "SELECT total_days FROM attendance_days"
                    );

                    days.next();

                    int totalDays = days.getInt("total_days");

                    ResultSet r = con.createStatement().executeQuery(
                            "SELECT * FROM students"
                    );

                    System.out.println("\n===== Attendance Report =====");

                    // Handle division by zero
                    if (totalDays == 0) {

                        System.out.println("No attendance marked yet.");

                    } else {

                        while (r.next()) {

                            String name = r.getString("name");

                            int presentDays = r.getInt("present_days");

                            int percent =
                                    (presentDays * 100) / totalDays;

                            System.out.println("--------------------------------");

                            System.out.println("Name          : " + name);
                            System.out.println("Present Days  : " + presentDays);
                            System.out.println("Total Days    : " + totalDays);
                            System.out.println("Attendance %  : " + percent + "%");
                        }
                    }
                }

                // ================= INVALID CHOICE =================

                else if (choice != 4) {

                    System.out.println("Invalid choice. Try again.");
                }

            } while (choice != 4);

            con.close();

            System.out.println("Application Closed.");

        } catch (Exception e) {

            System.out.println("Error : " + e.getMessage());
        }

        sc.close();
    }
}
import java.sql.*;
import java.util.Scanner;

public class AttendanceJDBC {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/attendance_db",
            "root",
            "nithin007"
        );

        int choice;

        do {
            System.out.println("\n1.Add Student");
            System.out.println("2.Mark Attendance");
            System.out.println("3.View Report");
            System.out.println("4.Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();  

            if (choice == 1) {
                System.out.print("Enter name: ");
                PreparedStatement ps =
                        con.prepareStatement("INSERT INTO students(name) VALUES(?)");
                ps.setString(1, sc.nextLine());
                ps.executeUpdate();
                System.out.println("Student added.");
            }

            else if (choice == 2) {
                con.createStatement()
                   .executeUpdate("UPDATE attendance_days SET total_days = total_days + 1");

                ResultSet rs =
                    con.createStatement().executeQuery("SELECT name FROM students");

                while (rs.next()) {
                    String name = rs.getString("name");
                    System.out.print("Is " + name + " present? ");
                    if (sc.nextLine().equalsIgnoreCase("yes")) {
                        PreparedStatement ps =
                            con.prepareStatement(
                              "UPDATE students SET present_days = present_days + 1 WHERE name=?");
                        ps.setString(1, name);
                        ps.executeUpdate();
                    }
                }
                System.out.println("Attendance marked.");
            }

            else if (choice == 3) {
                ResultSet days =
                    con.createStatement().executeQuery("SELECT total_days FROM attendance_days");
                days.next();
                int totalDays = days.getInt(1);

                ResultSet r =
                    con.createStatement().executeQuery("SELECT * FROM students");

                System.out.println("\n--- Attendance Report ---");
                while (r.next()) {
                    int percent =
                        (r.getInt("present_days") * 100) / totalDays;
                    System.out.println(r.getString("name") + " : " + percent + "%");
                }
            }

        } while (choice != 4);

        con.close();
        sc.close();
    }
}

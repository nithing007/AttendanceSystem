import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {

    public static void main(String[] args) {

        try {
            String url =
              "jdbc:mysql://localhost:3306/attendance_db?useSSL=false&allowPublicKeyRetrieval=true";
            String user = "root";
            String pass = "nithin007";

            Connection con = DriverManager.getConnection(url, user, pass);

            System.out.println("DATABASE CONNECTED SUCCESSFULLY");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

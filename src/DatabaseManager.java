import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseManager {
    public static Connection getConnection() throws Exception {
        Properties properties = new Properties();
        try (FileInputStream input =
                new FileInputStream("config/db.properties")) {
            properties.load(input);
        }
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url, username, password);
    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Fix Version Error on DB Connection - https://www.youtube.com/watch?v=X6cNy-YO-y8&t=119s

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/ticketingInfo"; // database name at the end
    private static final String USER = "SystemAdmin"; // {root, SystemAdmin}
    private static final String PASSWORD = "admin123"; // {password123, admin123}
    public static void connect() throws SQLException {
        Connection connection = null;
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to the database successfully!");
            } catch (SQLException e) {
                System.out.println("Connection failed!");
            }
            finally {
                if(connection != null)
                    connection.close();
            }
    }
}

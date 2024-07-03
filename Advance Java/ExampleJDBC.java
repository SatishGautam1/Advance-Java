import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExampleJDBC {

    // Database URL
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/mydb";
    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Load the JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");
            
            // Attempt to connect to the database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            if (conn != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } finally {
            // Close the connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close connection: " + e.getMessage());
                }
            }
        }
    }
}

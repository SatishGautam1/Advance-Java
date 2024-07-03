import java.sql.*;

public class Mysql {
    public static void main(String[] args) {
        // JDBC URL for MariaDB database named 'Student' on localhost
        String jdbcUrl = "jdbc:mariadb://localhost:3306/Student";
        String username = "root"; // MariaDB username
        String password = "p0intbre@k"; // MariaDB password

        try {
            // Step 1: Establish the connection
            System.out.println("Connecting to database...");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected successfully!");

            // Step 2: Perform database operations
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name FROM Name");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }

            // Step 3: Close the connection
            connection.close();
            System.out.println("Connection closed.");

        } catch (SQLException e) {
            System.out.println("Connection failed! Check output console");
            e.printStackTrace();
        }
    }
}

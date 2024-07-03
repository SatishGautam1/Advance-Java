import java.sql.*;

public class StudentJDBCExample {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mariadb://localhost:3306/College";
        String username = "root";
        String password = "root";

        try {
            // Step 1: Establish the connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to database!");

            // Step 2: Execute query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT rollno, name, age FROM Students");

            // Step 3: Process the result set
            while (resultSet.next()) {
                int rollno = resultSet.getInt("rollno");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println("Roll No: " + rollno + ", Name: " + name + ", Age: " + age);
            }

            // Step 4: Close the connection
            connection.close();
            System.out.println("Connection closed.");

        } catch (SQLException e) {
            System.out.println("Connection failed! Check output console");
            e.printStackTrace();
        }
    }
}

import java.sql.*;
public class JdbcDemo{
    public static void main(String[]args){
        try{
        Class.forName("org.mariadb.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost/College","root","root");
        PreparedStatement ps = con.prepareStatement("Update Student SET name=?,age=? WHERE rollno=?");
        ps.setString(1,"Shyam");
        ps.setInt(2, 24);
        ps.setInt(3, 1);

        int rows = ps.executeUpdate();
        System.out.println(rows + " rows updated");

           }
           catch(SQLException ex){
            System.out.println("SQL error:" + ex);
           }
           catch(ClassNotFoundException ex){
            ex.printStackTrace();
           }
    }
}
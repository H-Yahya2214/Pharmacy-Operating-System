import java.sql.*;
import java.util.Scanner;

public class Costumer {
    public double loyalty_points;


    public void Add(String name,double ID) {
        PreparedStatement preparedStatement;
        Connection conn = null;
        Statement stmt = null;
//        public void loyalty_points()

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to the database");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "haneen", "haneen2004");
            System.out.println("You are now connected to the database");

            Statement statement = conn.createStatement();

            // SQL query to insert data
            String sql = "INSERT INTO Costumer (name, branch, ID, phone_number, loyalty_points) VALUES (?, ?, ?, ?, ?)";
            statement.execute(sql);

            // Creating a PreparedStatement object to execute SQL query
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            //preparedStatement.setString(2, branch);
            preparedStatement.setDouble(3, ID);
            //preparedStatement.setLong(4, phone_number);
            preparedStatement.setDouble(5, loyalty_points);


            // Executing the PreparedStatement to insert data
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
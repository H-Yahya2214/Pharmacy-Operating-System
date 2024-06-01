import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Orders implements OrdersOperations {


    public double daily_total;

@Override
    public void order_info(String date, String costumer_name, double order_ID, double total_price) {

        PreparedStatement preparedStatement;
        Connection conn = null;
        Statement stmt = null;

        try {


            System.out.println("Connecting to the database");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "haneen", "haneen2004");
            System.out.println("You are now connected to the database");

            Statement statement = conn.createStatement();

            // SQL query to insert data
            String sql = "INSERT INTO orders (date,costumer_name,order_ID,total_price) VALUES (?, ?, ?, ?)";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, costumer_name);
            preparedStatement.setDouble(3, order_ID);
            preparedStatement.setDouble(4, total_price);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

@Override
    public double add_order(String name) {
        PreparedStatement preparedStatement;
        Connection conn = null;
        Statement stmt = null;

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "haneen", "haneen2004");
            System.out.println("You are now connected to the database");

            Statement statement = conn.createStatement();

            String countprice = "SELECT price FROM Drugs WHERE name = ?";
            PreparedStatement priceStatement = conn.prepareStatement(countprice);
            priceStatement.setString(1, name);


            ResultSet priceResultSet = priceStatement.executeQuery();



            double p = 0;
            if (priceResultSet.next()) {
                p = priceResultSet.getDouble(1);
                return p;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;

    }

    public double d_total() {
        return daily_total;
    }

    @Override
    public void display() {

    }
}
import java.sql.*;
import java.util.Scanner;
import java.sql.SQLException;


public class Cosmetics implements CosmeticsOperations {


    public boolean Add(String name,String expiration_date,String supplier, String storage_conditions, double price,double stock,double average_monthly_consumption)throws SQLException {

        PreparedStatement preparedStatement;
        Connection conn = null;
        Statement stmt = null;

        try {
            System.out.println("Connecting to the database");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "haneen", "haneen2004");
            System.out.println("You are now connected to the database");
            Statement statement = conn.createStatement();
            String countQuery = "SELECT COUNT(*) FROM cosmetics WHERE name = ?";
            PreparedStatement countStatement = conn.prepareStatement(countQuery);
            countStatement.setString(1, name);

            // Execute the count query and retrieve the result set
            ResultSet countResultSet = countStatement.executeQuery();

            // Extract the count value from the result set
            int count = 0;
            if (countResultSet.next()) {
                count = countResultSet.getInt(1);
            }

            // Check if count equals 0
            if (count == 0) {
                return false;}

            // SQL query to insert data
            String sql = "INSERT INTO cosmetics (name, expiration_date, supplier, storage_conditions, price, stock, average_monthly_consumption) VALUES ( ?, ?, ?, ?, ?, ?, ?)";

            // Creating a PreparedStatement object to execute SQL query
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, expiration_date);
            preparedStatement.setString(3, supplier);
            preparedStatement.setString(4, storage_conditions);
            preparedStatement.setDouble(6, price);
            preparedStatement.setDouble(7, stock);
            preparedStatement.setDouble(8, average_monthly_consumption);

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

        return true;


    }


    public  boolean Remove(String comse) {
        PreparedStatement preparedStatement;
        Connection conn = null;
        Statement stmt = null;

        try {
            System.out.println("Connecting to the database");

            conn = DriverManager.getConnection("jdbc:mysql://mysql/pharmacy", "haneen", "haneen2004");
            System.out.println("You are now connected to the database");
            System.out.println("Enter the name of the cosmetic you want to delete: \n");
            Statement statement = conn.createStatement();

            String countQuery = "SELECT COUNT(*) FROM cosmetics WHERE name = ?";
            PreparedStatement countStatement = conn.prepareStatement(countQuery);
            countStatement.setString(1, comse);

            // Execute the count query and retrieve the result set
            ResultSet countResultSet = countStatement.executeQuery();

            // Extract the count value from the result set
            int count = 0;
            if (countResultSet.next()) {
                count = countResultSet.getInt(1);
            }

            // Check if count equals 0
            if (count == 0) {
                return false;}



            String st = "DELETE FROM cosmetics WHERE name= ?";
            preparedStatement = conn.prepareStatement(st);
            preparedStatement.setString(1, comse);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;

    }

    public  boolean Update(String Cosmetic, int choice,String Value ) {
        try {

            PreparedStatement preparedStatement;
            Connection conn = null;
            Statement stmt = null;


            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to the database");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "haneen", "haneen2004");
            System.out.println("You are now connected to the database");



            Statement statement = conn.createStatement();
            String countQuery = "SELECT COUNT(*) FROM cosmetics WHERE name = ?";
            PreparedStatement countStatement = conn.prepareStatement(countQuery);
            countStatement.setString(1, Cosmetic);

            // Execute the count query and retrieve the result set
            ResultSet countResultSet = countStatement.executeQuery();

            // Extract the count value from the result set
            int count = 0;
            if (countResultSet.next()) {
                count = countResultSet.getInt(1);
            }

            // Check if count equals 0
            if (count == 0) {
                return false;}


            String st = "SELECT stock FROM cosmetics WHERE name= ?";
            preparedStatement = conn.prepareStatement(st);
            preparedStatement.setString(1, Cosmetic);


            if (choice == 1) {


                String updateSt = "UPDATE cosmetics SET name= ? WHERE name =Cosmetic";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setString(1, Value);

            }
            if (choice == 2) {

                String updateSt = "UPDATE cosmetics SET expiration_date= ? WHERE name = Cosmetic";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setString(1, Value);

            }
            if (choice == 3) {

                String updateSt = "UPDATE cosmetics SET supplier= ? WHERE name = Cosmetic";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setString(1, Value);

            }

            if (choice == 4) {

                String updateSt = "UPDATE cosmetics SET storage_conditions= ? WHERE name = Cosmetic";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setString(1, Value);

            }

            if (choice == 5) {
                Boolean newstatus = Boolean.parseBoolean(Value);

                String updateSt = "UPDATE cosmetics SET prescription_needed= ? WHERE name = Cosmetic";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setBoolean(1, newstatus);

            }

            if (choice == 6) {

                int newcode = Integer.parseInt(Value);

                String updateSt = "UPDATE cosmetics SET code= ? WHERE name = Cosmetic";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setInt(1, newcode);

            }
            if (choice == 7) {
                double newprice = Double.parseDouble(Value);

                String updateSt = "UPDATE cosmetics SET price= ? WHERE name = Cosmetic";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setDouble(1, newprice);

            }
            if (choice == 8) {
                double newstock = Double.parseDouble(Value);

                String updateSt = "UPDATE cosmetics SET stock= ? WHERE name = Cosmetic";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setDouble(1, newstock);

            }
            if (choice == 9) {
                double newm_a_c = Double.parseDouble(Value);

                String updateSt = "UPDATE cosmetics SET average_monthly_consumption= ? WHERE name = Cosmetic";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setDouble(1, newm_a_c);

            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
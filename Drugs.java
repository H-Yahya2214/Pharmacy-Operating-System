import java.util.Scanner;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.SQLException;


public class Drugs implements DrugsOperations {


    public Boolean Prescription_needed;
    public int code;
    public double price;
    public double stock;
    public double average_monthly_consumption;
    public static int capacity;



    @Override
    public boolean Add(String name, String expirationDate, String supplier, String storageConditions, Boolean prescriptionNeeded, double price, double stock, double averageMonthlyConsumption) throws SQLException {
        PreparedStatement preparedStatement;
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to the database");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "haneen", "haneen2004");
            System.out.println("You are now connected to the database");

            Statement statement = conn.createStatement();
            String countQuery = "SELECT COUNT(*) FROM drugs WHERE name = ?";
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
            if (count > 0) {
                return true;
            } else {


                // SQL query to insert data
                String sql = "INSERT INTO drugs (name, expiration_date, supplier, storage_conditions, prescription_needed, price, stock, average_monthly_consumption) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


                // Creating a PreparedStatement object to execute SQL query
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, expirationDate);
                preparedStatement.setString(3, supplier);
                preparedStatement.setString(4, storageConditions);
                preparedStatement.setBoolean(5, prescriptionNeeded);
                preparedStatement.setDouble(6, price);
                preparedStatement.setDouble(7, stock);
                preparedStatement.setDouble(8, averageMonthlyConsumption);
                statement.execute(sql);


                // Executing the PreparedStatement to insert data
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Data inserted successfully!");
                }
            }
        }

        catch(SQLException e){
            e.printStackTrace();
        }
        return false;    }

    @Override
    public boolean Remove(String drug) throws SQLException {
        PreparedStatement preparedStatement;
        Connection conn = null;
        Statement stmt = null;

        try {
            System.out.println("Connecting to the database");

            conn = DriverManager.getConnection("jdbc:mysql://mysql/pharmacy", "haneen", "haneen2004");
            System.out.println("You are now connected to the database");


            Statement statement = conn.createStatement();
            String countQuery = "SELECT COUNT(*) FROM drugs WHERE name = ?";
            PreparedStatement countStatement = conn.prepareStatement(countQuery);
            countStatement.setString(1, drug);

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


            String st = "DELETE FROM drugs WHERE name= ?";
            preparedStatement = conn.prepareStatement(st);
            preparedStatement.setString(1, drug);
            preparedStatement.executeUpdate();


        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean Update(String drug, int choice, String newValue) throws SQLException {
        try{

            PreparedStatement preparedStatement;
            Connection conn = null;
            Statement stmt = null;


            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to the database");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "haneen", "haneen2004");
            System.out.println("You are now connected to the database");



            Statement statement = conn.createStatement();
            String countQuery = "SELECT COUNT(*) FROM drugs WHERE name = ?";
            PreparedStatement countStatement = conn.prepareStatement(countQuery);
            countStatement.setString(1, drug);

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


            String st = "SELECT stock FROM drugs WHERE name= ?";
            preparedStatement = conn.prepareStatement(st);
            preparedStatement.setString(1, drug);

            if (choice == 1) {


                String updateSt = "UPDATE drugs SET name= ? WHERE name = drug";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setString(1, newValue);

            }
            if (choice == 2) {

                String updateSt = "UPDATE drugs SET expiration_date= ? WHERE name = drug";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setString(1, newValue);

            }
            if (choice == 3) {


                String updateSt = "UPDATE drugs SET supplier= ? WHERE name = drug";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setString(1, newValue);

            }

            if (choice == 4) {


                String updateSt = "UPDATE drugs SET storage_conditions= ? WHERE name = drug";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setString(1, newValue);

            }

            if (choice == 5) {
                boolean NewBoolean= Boolean.parseBoolean(newValue);
                String updateSt = "UPDATE drugs SET prescription_needed= ? WHERE name = drug";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setBoolean(1, NewBoolean);

            }

            if (choice == 6) {

                int NeeName = Integer.parseInt(newValue);
                String updateSt = "UPDATE drugs SET code= ? WHERE name = drug";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setInt(1, NeeName);

            }
            if (choice == 7) {
                int NeePrice = Integer.parseInt(newValue);
                String updateSt = "UPDATE drugs SET price= ? WHERE name = drug";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setDouble(1, NeePrice);

            }
            if (choice == 8) {
                Double NewStock = Double.parseDouble(newValue);
                String updateSt = "UPDATE drugs SET stock= ? WHERE name = drug";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setDouble(1, NewStock);

            }
            if (choice == 9) {

                int Nee_a_c = Integer.parseInt(newValue);
                String updateSt = "UPDATE drugs SET average_monthly_consumption= ? WHERE name = drug";
                preparedStatement = conn.prepareStatement(updateSt);
                preparedStatement.setDouble(1, Nee_a_c );

            }
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;    }

    @Override
    public void getCapacity(String capacity1) {
         capacity = Integer.parseInt(capacity1);
    }
}
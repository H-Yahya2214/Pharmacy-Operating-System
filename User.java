import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    public String name;
    public String branch;
    public int ID;


    public void Add(String name,String branch,int ID) {

        PreparedStatement preparedStatement = null;
        Connection conn = null;



        this.name = name;
        this.branch = branch;

        try {

            System.out.println("Connecting to the database");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "haneen", "haneen2004");
            System.out.println("You are now connected to the database");

            // SQL query to insert data
            String sql = "INSERT INTO user (name, branch,ID) VALUES (?, ?,?)";


            // Creating a PreparedStatement object to execute SQL query
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, branch);
            preparedStatement.setInt(3, ID);

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
        finally {

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    }
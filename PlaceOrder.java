import java.sql.*;
import  java.util.Scanner;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PlaceOrder {

    public void placeOrder(int orderchoice,String drugName) {
        Scanner scanner = new Scanner(System.in);
        PreparedStatement preparedStatement;
        Connection conn = null;
        Statement stmt = null;
        double sum=0;
        Orders order = new Orders();
            if (orderchoice == 1) {
                try {

                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "haneen", "haneen2004");
                    System.out.println("You are now connected to the database");

                    Statement statement = conn.createStatement();



                    // Prepare SQL statement to retrieve count of records for the specified drug name
                        String countQuery = "SELECT COUNT(*) FROM drugs WHERE name = ?";
                        PreparedStatement countStatement = conn.prepareStatement(countQuery);
                        countStatement.setString(1, drugName);

                        // Execute the count query and retrieve the result set
                        ResultSet countResultSet = countStatement.executeQuery();

                        // Extract the count value from the result set
                        int count = 0;
                        if (countResultSet.next()) {
                            count = countResultSet.getInt(1);
                        }

                        // Check if count equals 0
                        if (count == 0) {
                            System.out.println("This drug is not available");

//                        } else {
//                            System.out.println("This drug is available");
//                            System.out.println("Do you want to add any other item? \n1)YES \n2)NO");
//                            int addchoice = scanner.nextInt();
//                            if(addchoice==2){
//                                System.out.println("Do you confirm the order?  \n1)YES \n2)NO");
//                                int secchoice = scanner.nextInt();
//
//                                if(secchoice==2){
//
//                                    order.add_order(sum);
//                                }
//
//                                else {
//                                    String itemdelete = "DELETE Drugs  WHERE name = drugName";
//                                    preparedStatement = conn.prepareStatement(itemdelete);
//                                    preparedStatement.setString(1, drugName);
//
//                                    String countprice = "SELECT price FROM Drugs WHERE name = ?";
//                                    PreparedStatement priceStatement = conn.prepareStatement(countprice);
//                                    priceStatement.setString(1, drugName);
//
//                                    // Execute the count query and retrieve the result set
//                                    ResultSet priceResultSet = priceStatement.executeQuery();
//
//                                    // Extract the price from the result set
//
//                                    double p = 0;
//                                    if (priceResultSet.next()) {
//                                        p = priceResultSet.getDouble(1);
//                                         sum = sum +p;
//                                    }
//
//
//
//
//
//                                }
//                            }
//                            else {
//                                try {
//
//                                    String itemdelete = "DELETE Drugs  WHERE name = drugName";
//                                    preparedStatement = conn.prepareStatement(itemdelete);
//                                    preparedStatement.setString(1, drugName);
//
//                                    itemdelete = "DELETE Drugs  WHERE name = drugName";
//                                    preparedStatement = conn.prepareStatement(itemdelete);
//                                    preparedStatement.setString(1, drugName);
//
//                                }
//                                catch (SQLException e) {
//                                    throw new RuntimeException(e);
//                                }
//                            }

                            // You can proceed with further operations if the drug is available
                        }

                        // Close result set and statement
                        countResultSet.close();
                        countStatement.close();


                }
                catch (SQLException e) {
                    // Handle SQL exceptions
                    e.printStackTrace();
                }

            }

            else if (orderchoice == 2) {
                try {

                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "haneen", "haneen2004");
                    System.out.println("You are now connected to the database");

                    Statement statement = conn.createStatement();

                    // Get the drug name from the user


                    // Prepare SQL statement to retrieve count of records for the specified drug name
                    String countQuery = "SELECT COUNT(*) FROM cosmetics WHERE name = ?";
                    PreparedStatement countStatement = conn.prepareStatement(countQuery);
                    countStatement.setString(1, drugName);

                    // Execute the count query and retrieve the result set
                    ResultSet countResultSet = countStatement.executeQuery();

                    // Extract the count value from the result set
                    int count = 0;
                    if (countResultSet.next()) {
                        count = countResultSet.getInt(1);
                    }

                    // Check if count equals 0
                    if (count == 0) {
                        System.out.println("This cosmetic is not available");

                    } else {
                        System.out.println("This cosmetic is available");
                        System.out.println("Do you want to add any other item? \n1)YES \n2)NO");
                        int addchoice = scanner.nextInt();
                        if(addchoice==2){
                            System.out.println("Do you confirm the order?  \n1)YES \n2)NO");
                            int secchoice = scanner.nextInt();

//                            if(secchoice==2){
//
//                                order.add_order(sum);
//                                order.display();
//                            }
//
//                            else {
//                                String itemdelete = "DELETE cosmetics  WHERE name = drugName";
//                                preparedStatement = conn.prepareStatement(itemdelete);
//                                preparedStatement.setString(1, drugName);
//
//                                String countprice = "SELECT price FROM cosmetics WHERE name = ?";
//                                PreparedStatement priceStatement = conn.prepareStatement(countprice);
//                                priceStatement.setString(1, drugName);
//
//                                // Execute the count query and retrieve the result set
//                                ResultSet priceResultSet = priceStatement.executeQuery();
//
//                                // Extract the price from the result set
//
//                                double p = 0;
//                                if (priceResultSet.next()) {
//                                    p = priceResultSet.getDouble(1);
//                                    sum = sum +p;
//                                }
//
//                            }
                        }
                        else {
                            try {

                                String itemdelete = "DELETE cosmetics  WHERE name = drugName";
                                preparedStatement = conn.prepareStatement(itemdelete);
                                preparedStatement.setString(1, drugName);

                                itemdelete = "DELETE cosmetics  WHERE name = drugName";
                                preparedStatement = conn.prepareStatement(itemdelete);
                                preparedStatement.setString(1, drugName);

                                String countprice = "SELECT price FROM cosmetics WHERE name = ?";
                                PreparedStatement priceStatement = conn.prepareStatement(countprice);
                                priceStatement.setString(1, drugName);

                                // Execute the count query and retrieve the result set
                                ResultSet priceResultSet = priceStatement.executeQuery();

                                // Extract the price from the result set

                                double p = 0;
                                if (priceResultSet.next()) {
                                    p = priceResultSet.getDouble(1);
                                    sum = sum + p;


                                }
                            }
                            catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        // You can proceed with further operations if the drug is available
                    }

                    // Close result set and statement
                    countResultSet.close();
                    countStatement.close();


                }
                catch (SQLException e) {
                    // Handle SQL exceptions
                    e.printStackTrace();
                }

            }

            }
        }



import java.sql.SQLException;

public interface OrdersOperations {
    void order_info(String date, String costumer_name, double order_ID, double total_price) throws SQLException;
    double add_order(String name);

    double d_total();
    void display();
}
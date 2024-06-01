import java.sql.SQLException;

public interface CosmeticsOperations {
    boolean Add(String name, String expiration_date, String supplier, String storage_conditions, double price, double stock, double average_monthly_consumption) throws SQLException;
    boolean Remove(String cosmeticName) throws SQLException;
    boolean Update(String cosmeticName, int choice, String value) throws SQLException;
}
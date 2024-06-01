import java.sql.SQLException;

public interface DrugsOperations {
    boolean Add(String name, String expirationDate, String supplier, String storageConditions, Boolean prescriptionNeeded, double price, double stock, double averageMonthlyConsumption) throws SQLException;
    boolean Remove(String drug) throws SQLException;
    boolean Update(String drug, int choice, String newValue) throws SQLException;
    void getCapacity(String capacity1);
}

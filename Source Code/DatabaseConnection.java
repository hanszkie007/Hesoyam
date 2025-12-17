import java.sql.*;
import javax.swing.*;

public class DatabaseConnection {
   
    private static final String URL = "jdbc:mysql://localhost:3306/flood_alert_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    public static void initialize() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL Driver loaded successfully!");
            
            try (Connection testConn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                System.out.println("✅ Database connection successful!");
                createTables(testConn);
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("❌ ERROR: MySQL Driver not found!");
            System.err.println("Please add mysql-connector-java.jar to your classpath");
            showError("MySQL Driver not found!\nPlease add mysql-connector-java.jar to your classpath");
        } catch (SQLException e) {
            System.err.println("❌ ERROR: Database connection failed!");
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            
            String errorMsg = "Database connection failed!\n\n" +
                            "Please ensure:\n" +
                            "1. XAMPP/WAMP is running with MySQL started\n" +
                            "2. Create database: CREATE DATABASE flood_alert_system;\n" +
                            "3. MySQL credentials are correct\n\n" +
                            "Error: " + e.getMessage();
            showError(errorMsg);
        }
    }
    
    private static void createTables(Connection conn) throws SQLException {
        System.out.println("Creating tables...");
        Statement stmt = conn.createStatement();
     
        String usersTable = "CREATE TABLE IF NOT EXISTS users (" +
                           "id INT AUTO_INCREMENT PRIMARY KEY," +
                           "name VARCHAR(100) NOT NULL," +
                           "email VARCHAR(100) UNIQUE NOT NULL," +
                           "password VARCHAR(100) NOT NULL," +
                           "location VARCHAR(100) NOT NULL," +
                           "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        stmt.execute(usersTable);
        System.out.println("✅ Users table created/verified");
        
        String alertsTable = "CREATE TABLE IF NOT EXISTS alerts (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "type VARCHAR(50) NOT NULL," +
                            "area VARCHAR(100) NOT NULL," +
                            "evacuation_center VARCHAR(100) NOT NULL," +
                            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        stmt.execute(alertsTable);
        System.out.println("✅ Alerts table created/verified");
    
        String checkAdmin = "SELECT COUNT(*) FROM users WHERE email = 'admin@floodalert.com'";
        ResultSet rs = stmt.executeQuery(checkAdmin);
        rs.next();
        if (rs.getInt(1) == 0) {
            String insertAdmin = "INSERT INTO users (name, email, password, location) " +
                               "VALUES ('Admin', 'admin@floodalert.com', 'admin123', 'Headquarters')";
            stmt.executeUpdate(insertAdmin);
            System.out.println("✅ Admin user created (email: admin@floodalert.com, password: admin123)");
        }
        
        stmt.close();
        System.out.println("✅ All tables ready!");
    }
    
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return conn;
        } catch (SQLException e) {
            System.err.println("❌ Failed to create new connection: " + e.getMessage());
            
            SwingUtilities.invokeLater(() -> {
                showError("Cannot connect to database!\n\n" +
                         "Please check:\n" +
                         "1. MySQL is running\n" +
                         "2. Database exists\n\n" +
                         "Error: " + e.getMessage());
            });
            return null;
        }
    }
    
    public static boolean isConnected() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    
    private static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Database Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}
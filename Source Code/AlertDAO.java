import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AlertDAO {
    
    public static int createAlert(Alert alert) {
        String query = "INSERT INTO alerts (type, area, evacuation_center) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            System.out.println("Creating alert: Type=" + alert.getType() + 
                             ", Area=" + alert.getArea() + 
                             ", Evac=" + alert.getEvacuationCenter());
            
            pstmt.setString(1, alert.getType());
            pstmt.setString(2, alert.getArea());
            pstmt.setString(3, alert.getEvacuationCenter());
            
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            
            if (rowsAffected > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int newId = rs.getInt(1);
                        System.out.println("✅ Alert created with ID: " + newId);
                        return newId;
                    }
                }
            }
            
        } catch (SQLException e) {
            System.err.println("❌ SQL Error creating alert: " + e.getMessage());
            e.printStackTrace();
            
            String errorMsg = e.getMessage();
            if (errorMsg.contains("connection closed") || errorMsg.contains("Communications link failure")) {
                errorMsg = "Database connection failed. Please check if MySQL is running.";
            }
            
            JOptionPane.showMessageDialog(null,
                "Failed to create alert:\n" + errorMsg,
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }
    
    public static ArrayList<Alert> getAllAlerts() {
        ArrayList<Alert> alerts = new ArrayList<>();
        String query = "SELECT * FROM alerts ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                alerts.add(new Alert(
                    rs.getInt("id"),
                    rs.getString("type"),
                    rs.getString("area"),
                    rs.getString("evacuation_center"),
                    rs.getTimestamp("created_at")
                ));
            }
            
            System.out.println("Retrieved " + alerts.size() + " alerts from database");
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting alerts: " + e.getMessage());
            e.printStackTrace();
        }
        return alerts;
    }
}
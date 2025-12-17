import java.sql.*;
import java.util.ArrayList;

public class UserAlertDAO {
    
    public static void addAlertToAllUsers(int alertId) {
        String query = "INSERT INTO user_alerts (user_id, alert_id) " +
                      "SELECT id, ? FROM users";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, alertId);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void addAlertToUser(int userId, int alertId) {
        String query = "INSERT INTO user_alerts (user_id, alert_id) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, alertId);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // UPDATED: Add alert only to users in specific location
    public static void addAlertToUsersByLocation(int alertId, String location) {
        String query = "INSERT INTO user_alerts (user_id, alert_id) " +
                      "SELECT id, ? FROM users WHERE location = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, alertId);
            pstmt.setString(2, location);
            int rowsAffected = pstmt.executeUpdate();
            
            System.out.println("Alert #" + alertId + " added to " + rowsAffected + 
                             " users in location: " + location);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // IMPLEMENTED: Get alerts for a specific user
    public static ArrayList<Alert> getAlertsForUser(int userId) {
        ArrayList<Alert> alerts = new ArrayList<>();
        
        String query = "SELECT a.id, a.alert_type, a.affected_area, a.location, " +
                      "a.evacuation_center, a.timestamp " +
                      "FROM alerts a " +
                      "INNER JOIN user_alerts ua ON a.id = ua.alert_id " +
                      "WHERE ua.user_id = ? " +
                      "ORDER BY a.timestamp DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Alert alert = new Alert(userId, query, query, query, null);
                alert.setId(rs.getInt("id"));
                alert.setType(rs.getString("alert_type"));
                alert.setAffectedArea(rs.getString("affected_area"));
                alert.setLocation(rs.getString("location"));
                alert.setEvacuationCenter(rs.getString("evacuation_center"));
                alert.setTime(rs.getTimestamp("timestamp"));
                alerts.add(alert);
            }
            
            System.out.println("Loaded " + alerts.size() + " alerts for user ID: " + userId);
            
        } catch (SQLException e) {
            System.err.println("Error getting alerts for user ID " + userId + ": " + e.getMessage());
            e.printStackTrace();
        }
        
        return alerts;
    }
    
    // Optional: Check if user has a specific alert
    public static boolean hasAlert(int userId, int alertId) {
        String query = "SELECT COUNT(*) FROM user_alerts WHERE user_id = ? AND alert_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, alertId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    // Optional: Mark alert as read for user
    public static void markAlertAsRead(int userId, int alertId) {
        String query = "UPDATE user_alerts SET is_read = true WHERE user_id = ? AND alert_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, alertId);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Optional: Remove alert from user (useful for clearing old alerts)
    public static void removeAlertFromUser(int userId, int alertId) {
        String query = "DELETE FROM user_alerts WHERE user_id = ? AND alert_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, alertId);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Optional: Get unread alerts count for user
    public static int getUnreadAlertCount(int userId) {
        String query = "SELECT COUNT(*) FROM user_alerts WHERE user_id = ? AND is_read = false";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
}
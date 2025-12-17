import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String location;
    private ArrayList<Alert> notifications = new ArrayList<>();
    
    public User(int id, String name, String email, String password, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
    }
    
    public User(String name, String email, String password, String location) {
        this(0, name, email, password, location);
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public ArrayList<Alert> getNotifications() { return notifications; }
    public void setNotifications(ArrayList<Alert> notifications) { this.notifications = notifications; }
    public void addNotification(Alert alert) { this.notifications.add(alert); }
    public void clearNotifications() { this.notifications.clear(); }
}
import java.util.Date;

public class Alert {
    private int id;
    private String type;
    private String area;
    private String evacuationCenter;
    private Date time;
    
    public Alert(int id, String type, String area, String evacuationCenter, Date time) {
        this.id = id;
        this.type = type;
        this.area = area;
        this.evacuationCenter = evacuationCenter;
        this.time = time;
    }
    
    public Alert(String type, String area, String evacuationCenter) {
        this(0, type, area, evacuationCenter, new Date());
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    
    public String getEvacuationCenter() { return evacuationCenter; }
    public void setEvacuationCenter(String evacuationCenter) { this.evacuationCenter = evacuationCenter; }
    
    public Date getTime() { return time; }
    public void setTime(Date time) { this.time = time; }
    
    public String display() {
        return "FLOOD ALERT\n"
             + "Type: " + type + "\n"
             + "Affected Area: " + area + "\n"
             + "Evacuation Center: " + evacuationCenter + "\n"
             + "Time: " + time + "\n"
             + "--------------------------------\n";
    }

    public Object getAffectedArea() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAffectedArea'");
    }

    public void setAffectedArea(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAffectedArea'");
    }

    public void setLocation(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLocation'");
    }
}
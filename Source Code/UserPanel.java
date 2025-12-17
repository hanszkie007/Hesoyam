import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class UserPanel extends JFrame {
    
    private User user;
    private JTable alertsTable;
    private DefaultTableModel tableModel;
    
    public UserPanel(User user) {
        this.user = user;
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("User Panel - " + user.getName());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(StyleUtil.BACKGROUND);
        
        // Title Panel (SAME AS BEFORE)
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(StyleUtil.BACKGROUND);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Welcome, " + user.getName() + "!", SwingConstants.CENTER);
        titleLabel.setFont(StyleUtil.HEADING_FONT);
        titleLabel.setForeground(StyleUtil.PRIMARY);
        titlePanel.add(titleLabel);
        
        // Create Table Model for alerts (NEW - REPLACING TEXTAREA)
        String[] columns = {"Alert Type", "Affected Area", "Evacuation Center", "Time Issued"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        
        // Create Table (NEW - REPLACING TEXTAREA)
        alertsTable = new JTable(tableModel);
        alertsTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        alertsTable.setRowHeight(25);
        alertsTable.setSelectionBackground(new Color(200, 230, 255));
        
        // Style Table Header
        JTableHeader header = alertsTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(StyleUtil.PRIMARY);
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
        
        // Add Table to ScrollPane (REPLACING alertArea)
        JScrollPane scrollPane = new JScrollPane(alertsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Button Panel (SAME AS BEFORE)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(StyleUtil.BACKGROUND);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton viewButton = new JButton("View Alerts");
        JButton refreshButton = new JButton("Refresh");
        JButton clearButton = new JButton("Clear");
        JButton logoutButton = new JButton("Logout");
        
        StyleUtil.styleButton(viewButton);
        StyleUtil.styleButton(refreshButton);
        StyleUtil.styleButton(clearButton);
        StyleUtil.styleButton(logoutButton);
        
        viewButton.addActionListener(e -> viewAlerts());
        refreshButton.addActionListener(e -> refreshAlerts());
        clearButton.addActionListener(e -> {
            tableModel.setRowCount(0); // Clear table instead of text area
            JOptionPane.showMessageDialog(this, "Display cleared.", "Info", JOptionPane.INFORMATION_MESSAGE);
        });
        logoutButton.addActionListener(e -> {
            dispose();
            new UserMenu().setVisible(true);
        });
        
        buttonPanel.add(viewButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(logoutButton);
        
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER); // Now adds table instead of text area
        add(buttonPanel, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        
        // Auto-load alerts on panel open
        viewAlerts();
    }
    
    private void viewAlerts() {
        // Clear existing data in table
        tableModel.setRowCount(0);
        
        // Check if user has notifications
        if (user.getNotifications().isEmpty()) {
            // Add "no alerts" row to table
            Object[] noAlertsRow = {"No Alerts Available", "-", "-", "-"};
            tableModel.addRow(noAlertsRow);
        } else {
            // Add each alert as a row in the table
            for (Alert alert : user.getNotifications()) {
                Object[] row = {
                    alert.getType(),
                    alert.getArea(),
                    alert.getEvacuationCenter(),
                    alert.getTime()
                };
                tableModel.addRow(row);
            }
            
            // Show count in title or status (optional)
            setTitle("User Panel - " + user.getName() + " [" + user.getNotifications().size() + " alerts]");
        }
    }
    
    private void refreshAlerts() {
        // Reload alerts from database
        user.setNotifications(AlertDAO.getAllAlerts());
        viewAlerts(); // This will update the table
        
        // Show count in message
        String message;
        if (user.getNotifications().isEmpty()) {
            message = "No alerts available at the moment.";
        } else {
            message = user.getNotifications().size() + " alerts refreshed!";
        }
        
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
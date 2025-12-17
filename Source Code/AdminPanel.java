import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminPanel extends JFrame {
    
    private JTable alertsTable;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;
    
    public AdminPanel() {
        initializeUI();
        loadAlertsData();
    }
    
    private void initializeUI() {
        setTitle("Flood Alert System - Admin Panel");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 248, 255));
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(220, 235, 245));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel welcomeLabel = new JLabel("Welcome to Admin Control Panel", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(0, 80, 120));
        titlePanel.add(welcomeLabel);
        
        add(titlePanel, BorderLayout.NORTH);
        
        // Create Tabbed Pane with TWO TABS
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Tab 1: View All Alerts
        JPanel viewAlertsPanel = createViewAlertsPanel();
        tabbedPane.addTab("View All Alerts", viewAlertsPanel);
        
        // Tab 2: Create New Alert
        JPanel createAlertPanel = createCreateAlertPanel();
        tabbedPane.addTab("Create New Alert", createAlertPanel);
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Bottom Panel with Logout Button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(220, 235, 245));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        logoutButton.addActionListener(e -> {
            dispose();
            new AdminLogin().setVisible(true);
        });
        
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        exitButton.addActionListener(e -> System.exit(0));
        
        bottomPanel.add(logoutButton);
        bottomPanel.add(Box.createHorizontalStrut(20));
        bottomPanel.add(exitButton);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
    }
    
    private JPanel createViewAlertsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Header Panel with Refresh Button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 248, 255));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel headerLabel = new JLabel("All Flood Alerts");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerLabel.setForeground(new Color(0, 80, 120));
        
        JButton refreshButton = new JButton("🔄 Refresh");
        refreshButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        refreshButton.addActionListener(e -> loadAlertsData());
        
        headerPanel.add(headerLabel, BorderLayout.WEST);
        headerPanel.add(refreshButton, BorderLayout.EAST);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        
        // Table for alerts
        String[] columnNames = {"ID", "Alert Type", "Affected Area", "Evacuation Center", "Time Created"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        alertsTable = new JTable(tableModel);
        alertsTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        alertsTable.setRowHeight(30);
        alertsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        alertsTable.getTableHeader().setBackground(new Color(220, 235, 245));
        
        JScrollPane scrollPane = new JScrollPane(alertsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
   private JPanel createCreateAlertPanel() {
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBackground(Color.WHITE);
    panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(15, 15, 15, 15);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.WEST;
    
    // Alert Type
    gbc.gridx = 0;
    gbc.gridy = 0;
    JLabel typeLabel = new JLabel("Alert Type:*");
    typeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
    panel.add(typeLabel, gbc);
    
    gbc.gridx = 1;
    String[] alertTypes = {"Minor Flood", "Moderate Flood", "Major Flood", "Severe Flood", "Flash Flood"};
    JComboBox<String> typeCombo = new JComboBox<>(alertTypes);
    typeCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    typeCombo.setPreferredSize(new Dimension(250, 35));
    panel.add(typeCombo, gbc);
    
    // Affected Area
    gbc.gridx = 0;
    gbc.gridy = 1;
    JLabel areaLabel = new JLabel("Affected Area:*");
    areaLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
    panel.add(areaLabel, gbc);
    
    gbc.gridx = 1;
    JTextField areaField = new JTextField();
    areaField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    areaField.setPreferredSize(new Dimension(250, 35));
    areaField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));
    panel.add(areaField, gbc);
    
    // Evacuation Center
    gbc.gridx = 0;
    gbc.gridy = 2;
    JLabel evacLabel = new JLabel("Evacuation Center:*");
    evacLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
    panel.add(evacLabel, gbc);
    
    gbc.gridx = 1;
    JTextField evacField = new JTextField();
    evacField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    evacField.setPreferredSize(new Dimension(250, 35));
    evacField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));
    panel.add(evacField, gbc);
    
    // Button Panel
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
    buttonPanel.setBackground(Color.WHITE);
    
    JButton submitButton = new JButton("Create Alert");
    styleButton(submitButton, new Color(39, 174, 96));
    
    submitButton.addActionListener(e -> {
        String type = (String) typeCombo.getSelectedItem();
        String area = areaField.getText().trim();
        String evacuationCenter = evacField.getText().trim();
        
        // Validation
        if (area.isEmpty() || evacuationCenter.isEmpty()) {
            JOptionPane.showMessageDialog(panel,
                "Please fill in all required fields!",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Create Alert object
        Alert newAlert = new Alert(type, area, evacuationCenter);
        System.out.println("Attempting to save alert to database...");
        
        // Save to database using AlertDAO
        int alertId = AlertDAO.createAlert(newAlert);
        
        if (alertId != -1) {
            JOptionPane.showMessageDialog(panel,
                "✅ Alert created successfully!\nAlert ID: " + alertId,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Clear form
            areaField.setText("");
            evacField.setText("");
            
            // Refresh the alerts table
            loadAlertsData();
            
            // Switch to View Alerts tab
            tabbedPane.setSelectedIndex(0);
        } else {
            // Show more detailed error
            JOptionPane.showMessageDialog(panel,
                "❌ Failed to create alert.\n\nPossible reasons:\n" +
                "1. Database not connected\n" +
                "2. Database table doesn't exist\n" +
                "3. Connection error\n\n" +
                "Check console for details.",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    });
    
    JButton clearButton = new JButton("Clear Form");
    styleButton(clearButton, new Color(231, 76, 60));
    
    clearButton.addActionListener(e -> {
        areaField.setText("");
        evacField.setText("");
        typeCombo.setSelectedIndex(0);
    });
    
    buttonPanel.add(submitButton);
    buttonPanel.add(clearButton);
    
    panel.add(buttonPanel, gbc);
    
    return panel;
}
    
    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color.darker(), 1),
            BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void loadAlertsData() {
        if (tableModel == null) return;
        
        // Clear existing data
        tableModel.setRowCount(0);
        
        try {
            // Get alerts from database using AlertDAO
            ArrayList<Alert> alerts = AlertDAO.getAllAlerts();
            
            // Add alerts to table
            for (Alert alert : alerts) {
                Object[] row = {
                    alert.getId(),
                    alert.getType(),
                    alert.getArea(),
                    alert.getEvacuationCenter(),
                    alert.getTime()
                };
                tableModel.addRow(row);
            }
            
            // If no alerts found
            if (alerts.isEmpty()) {
                Object[] row = {"No alerts found", "", "", "", ""};
                tableModel.addRow(row);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error loading alerts: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        // For testing the AdminPanel directly
        SwingUtilities.invokeLater(() -> {
            new AdminPanel().setVisible(true);
        });
    }
}
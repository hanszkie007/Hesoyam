import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UserRegistration extends JFrame {
    
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField locationField;
    
    public UserRegistration() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("User Registration - Flood Alert System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 248, 255));
        
        // Title Panel
        JPanel titlePanel = createTitlePanel();
        
        // Form Panel
        JPanel formPanel = createFormPanel();
        
        // Button Panel
        JPanel buttonPanel = createButtonPanel();
        
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
    }
    
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(220, 235, 245));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 15, 20));
        
        JLabel titleLabel = new JLabel("CREATE NEW ACCOUNT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 80, 120));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Join the Flood Alert Community", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(80, 80, 80));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);
        
        return titlePanel;
    }
    
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Full Name Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(new Color(0, 80, 120));
        formPanel.add(nameLabel, gbc);
        
        // Full Name Field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.WEST;
        nameField = new JTextField(20);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameField.setBackground(Color.WHITE);
        nameField.setForeground(Color.BLACK);
        nameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 120, 200), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        nameField.setPreferredSize(new Dimension(250, 40));
        nameField.setMinimumSize(new Dimension(250, 40));
        formPanel.add(nameField, gbc);
        
        // Email Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        emailLabel.setForeground(new Color(0, 80, 120));
        formPanel.add(emailLabel, gbc);
        
        // Email Field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.WEST;
        emailField = new JTextField(20);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        emailField.setBackground(Color.WHITE);
        emailField.setForeground(Color.BLACK);
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 120, 200), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        emailField.setPreferredSize(new Dimension(250, 40));
        emailField.setMinimumSize(new Dimension(250, 40));
        formPanel.add(emailField, gbc);
        
        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        passwordLabel.setForeground(new Color(0, 80, 120));
        formPanel.add(passwordLabel, gbc);
        
        // Password Field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.WEST;
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLACK);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 120, 200), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        passwordField.setEchoChar('•');
        passwordField.setPreferredSize(new Dimension(250, 40));
        passwordField.setMinimumSize(new Dimension(250, 40));
        formPanel.add(passwordField, gbc);
        
        // Location Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        locationLabel.setForeground(new Color(0, 80, 120));
        formPanel.add(locationLabel, gbc);
        
        // Location Field
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.WEST;
        locationField = new JTextField(20);
        locationField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        locationField.setBackground(Color.WHITE);
        locationField.setForeground(Color.BLACK);
        locationField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 120, 200), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        locationField.setPreferredSize(new Dimension(250, 40));
        locationField.setMinimumSize(new Dimension(250, 40));
        formPanel.add(locationField, gbc);
        
        // Add requirements note
        JLabel requirementsLabel = new JLabel("Password must be at least 6 characters");
        requirementsLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        requirementsLabel.setForeground(new Color(120, 120, 120));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 15, 0, 15);
        formPanel.add(requirementsLabel, gbc);
        
        return formPanel;
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        buttonPanel.setBackground(new Color(220, 235, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20));
        
        JButton registerButton = createStyledButton("REGISTER", new Color(39, 174, 96));
        JButton cancelButton = createStyledButton("CANCEL", new Color(231, 76, 60));
        
        registerButton.addActionListener(e -> handleRegistration());
        cancelButton.addActionListener(e -> {
            dispose();
            new UserMenu().setVisible(true);
        });
        
        // Add Enter key support for registration
        getRootPane().setDefaultButton(registerButton);
        
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);
        
        return buttonPanel;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker(), 2),
            BorderFactory.createEmptyBorder(12, 35, 12, 35)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void handleRegistration() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String location = locationField.getText().trim();
        
        // Reset field borders
        resetFieldBorders();
        
        // Validation
        boolean isValid = true;
        
        if (name.isEmpty()) {
            highlightField(nameField);
            isValid = false;
        }
        
        if (email.isEmpty()) {
            highlightField(emailField);
            isValid = false;
        } else if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid email address!",
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            highlightField(emailField);
            emailField.requestFocus();
            return;
        }
        
        if (password.isEmpty()) {
            highlightField(passwordField);
            isValid = false;
        } else if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, 
                "Password must be at least 6 characters!",
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            highlightField(passwordField);
            passwordField.requestFocus();
            return;
        }
        
        if (location.isEmpty()) {
            highlightField(locationField);
            isValid = false;
        }
        
        if (!isValid) {
            JOptionPane.showMessageDialog(this, 
                "All fields are required!", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Check database connection first
        if (!DatabaseConnection.isConnected()) {
            JOptionPane.showMessageDialog(this, 
                "Cannot connect to database!\n" +
                "Please check:\n" +
                "1. XAMPP MySQL is running\n" +
                "2. Database exists\n" +
                "3. Internet connection", 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Try to register user
        boolean success = registerUser(name, email, password, location);
        
        if (success) {
            JOptionPane.showMessageDialog(this, 
                "Account created successfully!\n\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Location: " + location, 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
            new UserMenu().setVisible(true);
        }
    }
    
    private void resetFieldBorders() {
        Color borderColor = new Color(0, 120, 200);
        nameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        locationField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }
    
    private void highlightField(JTextField field) {
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.RED, 2),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.requestFocus();
    }
    
    private boolean registerUser(String name, String email, String password, String location) {
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "No database connection!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            // First check if email already exists
            String checkQuery = "SELECT COUNT(*) FROM users WHERE email = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, email);
            
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "Email already registered!", "Registration Error", JOptionPane.ERROR_MESSAGE);
                rs.close();
                checkStmt.close();
                highlightField(emailField);
                emailField.requestFocus();
                return false;
            }
            rs.close();
            checkStmt.close();
            
            // Insert new user
            String insertQuery = "INSERT INTO users (name, email, password, location) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, name);
            insertStmt.setString(2, email);
            insertStmt.setString(3, password);
            insertStmt.setString(4, location);
            
            int rowsAffected = insertStmt.executeUpdate();
            insertStmt.close();
            
            if (rowsAffected > 0) {
                System.out.println("User registered successfully: " + email);
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create account.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("SQL Error during registration:");
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Message: " + e.getMessage());
            
            String errorMsg = "Database error:\n";
            
            if (e.getErrorCode() == 1062) { // Duplicate entry
                errorMsg = "Email already registered!";
                highlightField(emailField);
                emailField.requestFocus();
            } else if (e.getErrorCode() == 1049) { // Unknown database
                errorMsg = "Database 'flood_alert_system' doesn't exist!\n" +
                          "Create it in phpMyAdmin first.";
            } else if (e.getErrorCode() == 1045) { // Access denied
                errorMsg = "Access denied! Check MySQL username/password.";
            } else {
                errorMsg = "SQL Error: " + e.getMessage();
            }
            
            JOptionPane.showMessageDialog(this, errorMsg, "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            SwingUtilities.invokeLater(() -> {
                nameField.requestFocusInWindow();
            });
        }
    }
}
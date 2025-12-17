import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminLogin extends JFrame {
    
    private JTextField userField;
    private JPasswordField passField;
    
    public AdminLogin() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Admin Login - Flood Alert System");
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
        
        // Make window focusable
        setFocusable(true);
        requestFocusInWindow();
    }
    
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(220, 235, 245));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("ADMINISTRATOR LOGIN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(0, 80, 120));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Secure Access Required", SwingConstants.CENTER);
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
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3; 
        gbc.anchor = GridBagConstraints.EAST; 
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        userLabel.setForeground(new Color(0, 80, 120));
        formPanel.add(userLabel, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7; 
        gbc.anchor = GridBagConstraints.WEST; 
        userField = new JTextField(20);
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userField.setBackground(Color.WHITE);
        userField.setForeground(Color.BLACK);
        userField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(0, 120, 200), 1),
        BorderFactory.createEmptyBorder(8, 10, 8, 10)
    ));
    
        userField.setPreferredSize(new Dimension(250, 40));
        userField.setMinimumSize(new Dimension(250, 40));
        formPanel.add(userField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        passLabel.setForeground(new Color(0, 80, 120));
        formPanel.add(passLabel, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.WEST;
        passField = new JPasswordField(20);
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passField.setBackground(Color.WHITE);
        passField.setForeground(Color.BLACK);
        passField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(0, 120, 200), 1),
        BorderFactory.createEmptyBorder(8, 10, 8, 10)
    ));
        passField.setEchoChar('•');
        passField.setPreferredSize(new Dimension(250, 40));
        passField.setMinimumSize(new Dimension(250, 40));
        formPanel.add(passField, gbc);
    
    return formPanel;
}
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        buttonPanel.setBackground(new Color(220, 235, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20));
        
        JButton loginButton = createStyledButton("LOGIN", new Color(39, 174, 96));
        JButton cancelButton = createStyledButton("CANCEL", new Color(231, 76, 60));
        
        loginButton.addActionListener(e -> handleLogin());
        cancelButton.addActionListener(e -> {
            dispose();
            new MainMenu().setVisible(true);
        });
        
        // Add Enter key support for login
        getRootPane().setDefaultButton(loginButton);
        
        buttonPanel.add(loginButton);
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
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void handleLogin() {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both username and password!",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            
            // Highlight empty field
            if (username.isEmpty()) {
                userField.requestFocus();
                userField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.RED, 2),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
            }
            if (password.isEmpty()) {
                passField.requestFocus();
                passField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.RED, 2),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
            }
            return;
        }
        
        // Default admin credentials
        if (username.equals("admin") && password.equals("admin123")) {
            JOptionPane.showMessageDialog(this,
                "Login successful! Welcome Administrator.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            dispose(); // Close login window
            
            // Open Admin Panel
            AdminPanel adminPanel = new AdminPanel();
            adminPanel.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(this,
                "Invalid credentials!\n\nTry:\nUsername: admin\nPassword: admin123",
                "Access Denied",
                JOptionPane.ERROR_MESSAGE);
            
            // Clear password field and focus on username
            passField.setText("");
            userField.requestFocus();
        }
    }
    
    // Add this method to auto-focus on username field when window opens
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            SwingUtilities.invokeLater(() -> {
                userField.requestFocusInWindow();
            });
        }
    }
    
    public static void main(String[] args) {
        // Use system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            AdminLogin login = new AdminLogin();
            login.setVisible(true);
        });
    }
}
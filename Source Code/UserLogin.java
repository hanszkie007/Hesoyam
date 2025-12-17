import javax.swing.*;
import java.awt.*;

public class UserLogin extends JFrame {
    
    private static User currentUser;
    private JTextField emailField;
    private JPasswordField passField;
    
    public UserLogin() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("User Login - Flood Alert System");
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
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("USER LOGIN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(0, 80, 120));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Access Your Alert Dashboard", SwingConstants.CENTER);
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
        
        // Email Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        emailLabel.setForeground(new Color(0, 80, 120));
        formPanel.add(emailLabel, gbc);
        
        // Email Field
        gbc.gridx = 1;
        gbc.gridy = 0;
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
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        passLabel.setForeground(new Color(0, 80, 120));
        formPanel.add(passLabel, gbc);
        
        // Password Field
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
            new UserMenu().setVisible(true);
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
    
    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = new String(passField.getPassword());
        
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both email and password!",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            
            // Highlight empty field
            if (email.isEmpty()) {
                emailField.requestFocus();
                emailField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.RED, 2),
                    BorderFactory.createEmptyBorder(8, 10, 8, 10)
                ));
            }
            if (password.isEmpty()) {
                passField.requestFocus();
                passField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.RED, 2),
                    BorderFactory.createEmptyBorder(8, 10, 8, 10)
                ));
            }
            return;
        }
        
        // Authenticate user
        User user = UserDAO.getUserByEmailAndPassword(email, password);
        
        if (user != null) {
            // Load user's notifications
            user.setNotifications(AlertDAO.getAllAlerts());
            currentUser = user;
            
            JOptionPane.showMessageDialog(this,
                "Login successful! Welcome, " + user.getName(),
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            dispose(); // Close login window
            
            // Open User Panel
            new UserPanel(user).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                "Invalid email or password!",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE);
            
            // Clear password field and focus on email
            passField.setText("");
            emailField.requestFocus();
        }
    }
    
    // Add this method to auto-focus on email field when window opens
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            SwingUtilities.invokeLater(() -> {
                emailField.requestFocusInWindow();
            });
        }
    }
    
    public static User getCurrentUser() {
        return currentUser;
    }
}
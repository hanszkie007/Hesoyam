import javax.swing.*;
import java.awt.*;

public class UserMenu extends JFrame {
    
    public UserMenu() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("User Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(StyleUtil.BACKGROUND);
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(StyleUtil.BACKGROUND);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 30, 20));
        
        JLabel titleLabel = new JLabel("User Portal", SwingConstants.CENTER);
        titleLabel.setFont(StyleUtil.HEADING_FONT);
        titleLabel.setForeground(StyleUtil.PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 20, 20));
        buttonPanel.setBackground(StyleUtil.BACKGROUND);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
        
        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");
        
        StyleUtil.styleButton(registerButton);
        StyleUtil.styleButton(loginButton);
        StyleUtil.styleButton(backButton);
        
        registerButton.addActionListener(e -> {
            UserRegistration userRegistration = new UserRegistration();
            userRegistration.setVisible(true);
        });
        
        loginButton.addActionListener(e -> {
            UserLogin userLogin = new UserLogin();
            userLogin.setVisible(true);
            dispose();
        });
        
        backButton.addActionListener(e -> {
            dispose();
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
        
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);
    }
}
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Flood Alert System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(StyleUtil.BACKGROUND);
        
        JPanel titlePanel = createTitlePanel();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(StyleUtil.BACKGROUND);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 150, 50, 150));

        JButton userButton = new JButton("USER");
        JButton adminButton = new JButton("ADMIN");
        JButton exitButton = new JButton("EXIT");

        Dimension buttonSize = new Dimension(170, 40); 
        userButton.setPreferredSize(buttonSize);
        userButton.setMaximumSize(buttonSize);
        userButton.setMinimumSize(buttonSize);
        
        adminButton.setPreferredSize(buttonSize);
        adminButton.setMaximumSize(buttonSize);
        adminButton.setMinimumSize(buttonSize);
        
        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);
        exitButton.setMinimumSize(buttonSize);

        StyleUtil.styleButton(userButton);
        StyleUtil.styleButton(adminButton);
        StyleUtil.styleButton(exitButton);

        userButton.addActionListener(e -> {
            dispose();
            UserMenu userMenu = new UserMenu();
            userMenu.setVisible(true);
        });

        adminButton.addActionListener(e -> {
            AdminLogin adminLogin = new AdminLogin();
            adminLogin.setVisible(true);
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        userButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(userButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(adminButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(exitButton);

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(StyleUtil.BACKGROUND);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 40, 20));

        // Main Title
        JLabel titleLabel = new JLabel("FloodAlert", SwingConstants.CENTER);
        titleLabel.setFont(StyleUtil.TITLE_FONT);
        titleLabel.setForeground(StyleUtil.PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Early Warning and Safety System", SwingConstants.CENTER);
        subtitleLabel.setFont(StyleUtil.SUBTITLE_FONT);
        subtitleLabel.setForeground(new Color(100, 100, 100));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 40, 0));

        JLabel iconLabel = new JLabel("⛈", SwingConstants.CENTER);
        int fontSize = 100; 
        iconLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, fontSize));
        Color iconColor = new Color(0, 100, 200);

        iconLabel.setForeground(iconColor);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); 

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);
        titlePanel.add(iconLabel);

        return titlePanel;
    }
}
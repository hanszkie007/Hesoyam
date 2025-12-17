import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Initialize database connection
        DatabaseConnection.initialize();
        
        // Start the applicationS
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}
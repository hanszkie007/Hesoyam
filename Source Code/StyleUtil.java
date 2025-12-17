import javax.swing.*;
import java.awt.*;

public class StyleUtil {
    
    public static final Color PRIMARY = new Color(0, 102, 153);
    public static final Color SECONDARY = new Color(41, 128, 185);
    public static final Color BACKGROUND = new Color(240, 248, 255);
    
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 36);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.ITALIC, 16);
    public static final Font HEADING_FONT = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font TEXT_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    
    public static void styleButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setBackground(SECONDARY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
import javax.swing.*;
import java.awt.*;

/**
 * Launcher / entry point.
 * Demonstrates clear separation of:
 *  - Backend: SeatManager, Seat, Booking, SeatCategory
 *  - Frontend: UserFrame, AdminFrame, SeatGridPanel, BookingDialog, LegendPanel
 *
 * Both User and Admin interfaces talk to the SAME backend instance (SeatManager).
 */
public class EventManagementApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventManagementApp::createAndShowLauncher);
    }

    private static void createAndShowLauncher() {
        // Backend instance shared by both UIs
        SeatManager seatManager = new SeatManager();

        JFrame frame = new JFrame("Event Management System - Launcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 220);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Event Management System", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        title.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton userButton = new JButton("Open User Panel");
        userButton.setBackground(new Color(0, 180, 80));
        userButton.setForeground(Color.WHITE);
        userButton.setFocusPainted(false);
        userButton.setBorderPainted(false);
        userButton.setOpaque(true);

        JButton adminButton = new JButton("Open Admin Panel");
        adminButton.setBackground(new Color(200, 30, 50));
        adminButton.setForeground(Color.WHITE);
        adminButton.setFocusPainted(false);
        adminButton.setBorderPainted(false);
        adminButton.setOpaque(true);

        userButton.addActionListener(e -> {
            UserFrame uf = new UserFrame(seatManager);
            uf.setVisible(true);
        });

        adminButton.addActionListener(e -> {
            AdminFrame af = new AdminFrame(seatManager);
            af.setVisible(true);
        });

        buttonPanel.add(userButton);
        buttonPanel.add(adminButton);

        frame.add(title, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
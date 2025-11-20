import javax.swing.*;
import java.awt.*;

/**
 * Frontend window for admin users.
 * Admin has more control over backend data via SeatManager.
 */
public class AdminFrame extends JFrame {

    public AdminFrame(SeatManager seatManager) {
        super("Event Management System - Admin Panel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        SeatGridPanel seatGridPanel = new SeatGridPanel(seatManager, true);
        JScrollPane scrollPane = new JScrollPane(seatGridPanel);

        LegendPanel legendPanel = new LegendPanel();

        JTextArea infoArea = new JTextArea(6, 30);
        infoArea.setEditable(false);
        infoArea.setBorder(BorderFactory.createTitledBorder("Admin Controls"));
        infoArea.setBackground(new Color(245, 245, 245));
        infoArea.setText(
                "Admin Panel:\n" +
                "- Click any seat (💺) to view details.\n" +
                "- For booked seats: view, edit, or cancel booking.\n" +
                "- For vacant seats: create bookings when required.\n" +
                "- Color code: see legend above the seating plan."
        );

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(legendPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        add(infoArea, BorderLayout.SOUTH);
    }
}
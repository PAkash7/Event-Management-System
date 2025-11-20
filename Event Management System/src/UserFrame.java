import javax.swing.*;
import java.awt.*;

/**
 * Frontend window for normal users.
 * Uses SeatManager (backend) through SeatGridPanel.
 */
public class UserFrame extends JFrame {

    public UserFrame(SeatManager seatManager) {
        super("Event Management System - User Panel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // Seat grid (frontend view of backend seat data)
        SeatGridPanel seatGridPanel = new SeatGridPanel(seatManager, false);
        JScrollPane scrollPane = new JScrollPane(seatGridPanel);

        // Legend for color coordination
        LegendPanel legendPanel = new LegendPanel();

        // Info / instructions
        JTextArea infoArea = new JTextArea(5, 30);
        infoArea.setEditable(false);
        infoArea.setBorder(BorderFactory.createTitledBorder("Instructions"));
        infoArea.setBackground(new Color(245, 245, 245));
        infoArea.setText(
                "User Panel:\n" +
                "- Click on a vacant seat (💺 icon) to book.\n" +
                "- 5-Star: Rows 1-10, 3-Star: Rows 11-20, General: Rows 21-100.\n" +
                "- Color code: see legend above the seating plan.\n" +
                "- Red seats are already booked."
        );

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(legendPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        add(infoArea, BorderLayout.SOUTH);
    }
}
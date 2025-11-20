import javax.swing.*;
import java.awt.*;

/**
 * Frontend grid that visualizes the backend Seat matrix.
 * Uses color-coding and a seat icon to represent each seat.
 */
public class SeatGridPanel extends JPanel {

    private final SeatManager seatManager;
    private final boolean isAdmin;

    public SeatGridPanel(SeatManager seatManager, boolean isAdmin) {
        super(new GridLayout(SeatManager.ROWS, SeatManager.COLS, 2, 2));
        this.seatManager = seatManager;
        this.isAdmin = isAdmin;
        setBorder(BorderFactory.createTitledBorder(
                isAdmin ? "Admin Seat View" : "User Seat View"));
        setBackground(new Color(30, 30, 30)); // dark background for contrast
        initGrid();
    }

    private void initGrid() {
        Seat[][] seats = seatManager.getSeats();
        for (int r = 0; r < SeatManager.ROWS; r++) {
            for (int c = 0; c < SeatManager.COLS; c++) {
                Seat seat = seats[r][c];

                // Add a simple "seat logo" using Unicode seat emoji + seat label
                JButton btn = new JButton("💺 " + seat.getLabel());

                updateSeatButtonAppearance(btn, seat);
                btn.setMargin(new Insets(1, 1, 1, 1));
                btn.setFocusPainted(false);
                btn.setBorderPainted(false);
                btn.setOpaque(true);

                if (isAdmin) {
                    btn.addActionListener(e -> onAdminSeatClick(seat, btn));
                } else {
                    btn.addActionListener(e -> onUserSeatClick(seat, btn));
                }

                add(btn);
            }
        }
    }

    /**
     * Color coordination for seating plan:
     * - Vacant:
     *      5-Star   -> Gold
     *      3-Star   -> Blue
     *      General  -> Green
     * - Booked (any category) -> Red
     */
    private void updateSeatButtonAppearance(JButton button, Seat seat) {
        if (seat.isBooked()) {
            button.setBackground(new Color(220, 20, 60));   // red
            button.setForeground(Color.WHITE);
            button.setToolTipText("BOOKED | Category: " + seat.getCategory() +
                    " | Fare: ₹" + seat.getFare());
            return;
        }

        switch (seat.getCategory()) {
            case FIVE_STAR -> {
                button.setBackground(new Color(255, 215, 0));   // Gold
                button.setForeground(Color.BLACK);
            }
            case THREE_STAR -> {
                button.setBackground(new Color(30, 144, 255));  // Blue
                button.setForeground(Color.WHITE);
            }
            case GENERAL -> {
                button.setBackground(new Color(60, 179, 113));  // Green
                button.setForeground(Color.BLACK);
            }
        }

        button.setToolTipText("VACANT | Category: " + seat.getCategory() +
                " | Fare: ₹" + seat.getFare());
    }

    private void onUserSeatClick(Seat seat, JButton button) {
        if (seat.isBooked()) {
            Booking b = seat.getBooking();
            String msg = "This seat is already booked.\n\n" +
                    seat.basicInfo() + "\n\n" +
                    b.prettyString();
            JOptionPane.showMessageDialog(this, msg,
                    "Seat Already Booked", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Booking booking = BookingDialog.showDialog(this, seat, null);
        if (booking != null) {
            seat.setBooking(booking);
            updateSeatButtonAppearance(button, seat);
        }
    }

    private void onAdminSeatClick(Seat seat, JButton button) {
        if (!seat.isBooked()) {
            int option = JOptionPane.showConfirmDialog(this,
                    seat.basicInfo() +
                            "\nStatus: VACANT\n\nDo you want to create a booking for this seat?",
                    "Vacant Seat - Admin",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                Booking booking = BookingDialog.showDialog(this, seat, null);
                if (booking != null) {
                    seat.setBooking(booking);
                    updateSeatButtonAppearance(button, seat);
                }
            }
            return;
        }

        Booking curr = seat.getBooking();
        String msg = "Seat Details:\n" +
                seat.basicInfo() + "\n\n" +
                curr.prettyString() +
                "\n\nChoose an action:";
        Object[] options = {"Close", "Edit Booking", "Cancel Booking"};
        int choice = JOptionPane.showOptionDialog(this, msg,
                "Admin - Booked Seat",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if (choice == 1) { // Edit
            Booking updated = BookingDialog.showDialog(this, seat, curr);
            if (updated != null) {
                seat.setBooking(updated);
                updateSeatButtonAppearance(button, seat);
            }
        } else if (choice == 2) { // Cancel
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to cancel this booking?",
                    "Confirm Cancel", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                seat.setBooking(null);
                updateSeatButtonAppearance(button, seat);
            }
        }
    }
}
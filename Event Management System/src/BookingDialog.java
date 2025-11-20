import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Frontend dialog used by User and Admin panels to create / edit bookings.
 * Talks to the backend model objects (Seat, Booking).
 */
public class BookingDialog {

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm");

    public static Booking showDialog(Component parent, Seat seat, Booking existingBooking) {
        JTextField nameField = new JTextField();
        JCheckBox lunchCheck = new JCheckBox("Lunch Required?");
        JTextField reportingField = new JTextField("17:00");
        JTextField leavingField = new JTextField("21:00");
        JTextField eventField = new JTextField("18:00");

        if (existingBooking != null) {
            nameField.setText(existingBooking.getName());
            lunchCheck.setSelected(existingBooking.isLunchIncluded());
            reportingField.setText(existingBooking.getReportingTime().format(TIME_FORMATTER));
            leavingField.setText(existingBooking.getLeavingTime().format(TIME_FORMATTER));
            eventField.setText(existingBooking.getEventTime().format(TIME_FORMATTER));
        }

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Seat:"));
        panel.add(new JLabel(seat.getLabel() + " (" + seat.getCategory() + ")"));
        panel.add(new JLabel("Fare (₹):"));
        panel.add(new JLabel(String.valueOf(seat.getFare())));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Lunch Available:"));
        panel.add(lunchCheck);
        panel.add(new JLabel("Reporting Time (HH:mm):"));
        panel.add(reportingField);
        panel.add(new JLabel("Leaving Time (HH:mm):"));
        panel.add(leavingField);
        panel.add(new JLabel("Event Time (HH:mm):"));
        panel.add(eventField);

        int result = JOptionPane.showConfirmDialog(parent, panel,
                existingBooking == null ? "New Booking" : "Edit Booking",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result != JOptionPane.OK_OPTION) {
            return null;
        }

        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Name cannot be empty.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        boolean lunch = lunchCheck.isSelected();

        try {
            LocalTime reportingTime = LocalTime.parse(reportingField.getText().trim(), TIME_FORMATTER);
            LocalTime leavingTime = LocalTime.parse(leavingField.getText().trim(), TIME_FORMATTER);
            LocalTime eventTime = LocalTime.parse(eventField.getText().trim(), TIME_FORMATTER);

            return new Booking(name, lunch, reportingTime, leavingTime, eventTime, seat.getFare());
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(parent, "Invalid time format. Use HH:mm (e.g., 18:30).",
                    "Time Format Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
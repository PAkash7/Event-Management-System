import javax.swing.*;
import java.awt.*;

/**
 * Small legend panel showing the color-code used in the seating plan.
 * This is purely frontend / UI.
 */
public class LegendPanel extends JPanel {

    public LegendPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createTitledBorder("Seat Legend"));

        add(createLegendItem("5-Star (Vacant)", new Color(255, 215, 0)));    // Gold
        add(createLegendItem("3-Star (Vacant)", new Color(30, 144, 255)));   // Blue
        add(createLegendItem("General (Vacant)", new Color(60, 179, 113)));  // Green
        add(createLegendItem("Booked (Any)", new Color(220, 20, 60)));       // Red
    }

    private JComponent createLegendItem(String text, Color color) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
        JLabel colorBox = new JLabel("   ");
        colorBox.setOpaque(true);
        colorBox.setBackground(color);
        colorBox.setPreferredSize(new Dimension(22, 22));

        JLabel label = new JLabel(text);

        panel.add(colorBox);
        panel.add(label);

        return panel;
    }
}
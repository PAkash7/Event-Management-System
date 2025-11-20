import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Booking {
    private final String name;
    private final boolean lunchIncluded;
    private final LocalTime reportingTime;
    private final LocalTime leavingTime;
    private final LocalTime eventTime;
    private final double fare;

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm");

    public Booking(String name, boolean lunchIncluded,
                   LocalTime reportingTime, LocalTime leavingTime,
                   LocalTime eventTime, double fare) {
        this.name = name;
        this.lunchIncluded = lunchIncluded;
        this.reportingTime = reportingTime;
        this.leavingTime = leavingTime;
        this.eventTime = eventTime;
        this.fare = fare;
    }

    public String getName() {
        return name;
    }

    public boolean isLunchIncluded() {
        return lunchIncluded;
    }

    public LocalTime getReportingTime() {
        return reportingTime;
    }

    public LocalTime getLeavingTime() {
        return leavingTime;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public double getFare() {
        return fare;
    }

    public String prettyString() {
        return "Name: " + name +
                "\nLunch: " + (lunchIncluded ? "Yes" : "No") +
                "\nReporting Time: " + reportingTime.format(TIME_FORMATTER) +
                "\nLeaving Time: " + leavingTime.format(TIME_FORMATTER) +
                "\nEvent Time: " + eventTime.format(TIME_FORMATTER) +
                "\nFare: ₹" + fare;
    }
}
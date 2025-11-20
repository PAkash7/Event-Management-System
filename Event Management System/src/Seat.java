public class Seat {
    private final int row;
    private final int col;
    private final SeatCategory category;
    private final double fare;
    private Booking booking;

    public Seat(int row, int col, SeatCategory category, double fare) {
        this.row = row;
        this.col = col;
        this.category = category;
        this.fare = fare;
    }

    public String getLabel() {
        return "R" + row + "S" + col;
    }

    public String basicInfo() {
        return "Seat: " + getLabel() +
                "\nRow: " + row +
                "\nColumn: " + col +
                "\nCategory: " + category +
                "\nFare: ₹" + fare;
    }

    public boolean isBooked() {
        return booking != null;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public SeatCategory getCategory() {
        return category;
    }

    public double getFare() {
        return fare;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
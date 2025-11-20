/**
 * Backend / business-logic layer.
 * Manages the full 2D grid of seats and their categories & fares.
 */
public class SeatManager {

    public static final int ROWS = 100;
    public static final int COLS = 10;

    public static final double FARE_FIVE_STAR = 2000.0;
    public static final double FARE_THREE_STAR = 1200.0;
    public static final double FARE_GENERAL = 800.0;

    private final Seat[][] seats = new Seat[ROWS][COLS];

    public SeatManager() {
        initSeats();
    }

    private void initSeats() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                SeatCategory category;
                if (r < 10) {
                    category = SeatCategory.FIVE_STAR; // Rows 1–10
                } else if (r < 20) {
                    category = SeatCategory.THREE_STAR; // Rows 11–20
                } else {
                    category = SeatCategory.GENERAL; // Rows 21–100
                }
                double fare;
                switch (category) {
                    case FIVE_STAR:
                        fare = FARE_FIVE_STAR;
                        break;
                    case THREE_STAR:
                        fare = FARE_THREE_STAR;
                        break;
                    default:
                        fare = FARE_GENERAL;
                        break;
                }
                seats[r][c] = new Seat(r + 1, c + 1, category, fare);
            }
        }
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public Seat getSeat(int rowIndex, int colIndex) {
        return seats[rowIndex][colIndex];
    }
}
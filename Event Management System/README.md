# Event Management System (Java Swing)



- **Color-coordinated seating plan** (different colors for 5-Star, 3-Star, General, and booked seats).
- **Clear separation of frontend (UI) and backend (logic)**.
- **Seat logo/icon** on every seat button for easy visual location.
- Separate **User** and **Admin** interfaces that both connect to the same backend (`SeatManager`).

## Architecture

### Backend (Business Logic / Data Layer)

- `SeatManager` – creates and manages the 100 × 10 seat grid, assigns categories and fares.
- `Seat` – represents a single seat (row, column, category, fare, booking).
- `SeatCategory` – enum: `FIVE_STAR`, `THREE_STAR`, `GENERAL`.
- `Booking` – holds booking details:
  - Name
  - Lunch availability
  - Reporting time
  - Leaving time
  - Event time
  - Fare in ₹

### Frontend (UI Layer)

- `EventManagementApp` – launcher window with buttons:
  - **Open User Panel**
  - **Open Admin Panel**
- `UserFrame` – UI for normal users (view & book vacant seats).
- `AdminFrame` – UI for admins (view, modify, cancel bookings).
- `SeatGridPanel` – visual grid of seats using color coding and a seat icon (💺).
- `LegendPanel` – shows color legend for:
  - 5-Star vacant
  - 3-Star vacant
  - General vacant
  - Booked seats
- `BookingDialog` – dialog used by both panels to create/edit bookings.

> Both `UserFrame` and `AdminFrame` receive the same `SeatManager` instance,
> which is how the **frontend connects with the backend**.

## Color Coordination

- Vacant seats:
  - **5-Star** → Gold
  - **3-Star** → Blue
  - **General** → Green
- **Booked** seats (any category) → Red
- Background of the grid is dark to highlight seat colors.

Each seat button text uses a **seat icon + seat id**, for example:

> `💺 R5S3`

## How to Compile and Run

1. Open a terminal in the `src` folder:

```bash
cd EventManagementSystem_v2/src
javac *.java
java EventManagementApp
```

Requires **Java 8 or later**.

## Seat Layout Rules

- Total seats: **100 rows × 10 seats**.
- Category by row:
  - Rows 1–10 → 5-Star
  - Rows 11–20 → 3-Star
  - Rows 21–100 → General
- Fares:
  - 5-Star: ₹2000
  - 3-Star: ₹1200
  - General: ₹800
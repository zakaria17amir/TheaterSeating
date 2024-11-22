package theater;

import java.lang.*;
import theater.seating.Seat;
import theater.seating.SeatType;

public class TheaterSeating {
    private Seat[][] seats;

    public Seat[][] getSeats() {
        return seats;
    }

    private int giftsTotal;

    public int getGiftsTotal() {
        return giftsTotal;
    }

    public TheaterSeating(int rowCount, int colCount) throws IllegalArgumentException {
        try {
            if (rowCount <= 0 || colCount <= 0)
                throw new IllegalArgumentException();
            this.seats = new Seat[rowCount][colCount];
        } catch (IllegalArgumentException e) {
            System.out.println("Dimensions should be positive integer" + e.getMessage());
        }
    }

    private void initSeating(int rowCount, int colCount) {

    }

    public Seat bookSeat() {
        return null;
    }

    public Seat bookSeat(int row, int col) {
        return null;
    }

    public Seat bookTailoredEmptySeat(SeatType st, boolean flag) {
        return null;
    }

    public int totalTakenGifts() {
        return 0;
    }

    public void decreaseGifts() {

    }

    public int getAmountOfGifts() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }
}

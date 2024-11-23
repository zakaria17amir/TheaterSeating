package theater;

import java.lang.*;
import theater.seating.Seat;
import theater.seating.SeatType;

public class TheaterSeating {
    private Seat[][] seats;

    public Seat[][] getSeats() {
        return seats;
    }

    private int initialAmountOfGifts = 0;
    private int giftsTotal = 0;

    public int getGiftsTotal() {
        return giftsTotal;
    }

    public TheaterSeating(int rowCount, int colCount) throws IllegalArgumentException {
        try {
            if (rowCount <= 0 || colCount <= 0)
                throw new IllegalArgumentException();
            this.seats = new Seat[rowCount][colCount];
            initSeating(rowCount, colCount);
        } catch (IllegalArgumentException e) {
            System.out.println("Dimensions should be positive integer" + e.getMessage());
        }
    }

    private void initSeating(int rowCount, int colCount) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                SeatType st;
                if (i == 0 || i == (rowCount - 1) || j == 0 || j == (colCount - 1))
                    st = SeatType.OT;
                else if (j == colCount / 2)
                    st = SeatType.MT;
                else
                    st = SeatType.IT;

                boolean hasGift = false;
                if ((i + j) % 2 == 1) {
                    hasGift = true;
                    giftsTotal++;
                }

                String id = "R" + (i + 1) + "C" + (j + 1);

                seats[i][j] = new Seat(id, hasGift, st);
            }
        }
        initialAmountOfGifts = giftsTotal;
    }

    public Seat bookSeat() {
        for (Seat[] seat_s : seats) {
            for (Seat seat : seat_s) {
                if (!seat.getIsOccupied()) {
                    seat.setIsOccupied(true);
                    return seat;
                }
            }
        }
        return null;
    }

    public Seat bookSeat(int row, int col) {
        if (seats[row][col].getIsOccupied()) {
            throw new IllegalArgumentException("Seat can not be booked");
        }
        seats[row][col].setIsOccupied(true);
        return seats[row][col];
    }

    public Seat bookTailoredEmptySeat(SeatType st, boolean left) {
        if (left) {
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length / 2; j++) {
                    if (seats[i][j].getSeatType() == st && !seats[i][j].getIsOccupied()) {
                        seats[i][j].setIsOccupied(true);
                        return seats[i][j];
                    }
                }
            }
        } else {
            for (int i = seats.length - 1; i >= 0; i--) {
                for (int j = seats[i].length - 1; j >= seats[i].length / 2; j--) {
                    if (seats[i][j].getSeatType() == st && !seats[i][j].getIsOccupied()) {
                        seats[i][j].setIsOccupied(true);
                        return seats[i][j];
                    }
                }
            }
        }
        return null;
    }

    public int totalTakenGifts() {
        return giftsTotal;
    }

    public void decreaseGifts() {
        giftsTotal--;
    }

    public int getAmountOfGifts() {
        return initialAmountOfGifts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Seat[] seat_s : seats) {
            for (Seat seat : seat_s) {
                if (seat.getIsOccupied()) {
                    sb.append("[B] ");
                } else {
                    sb.append("[A] ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

package theater.person;

import theater.seating.Seat;
import theater.seating.SeatType;
import theater.TheaterSeating;

public class Spectator {
    private Seat seat = null;

    public Seat getSeat() {
        return seat;
    }

    private final String name;

    public String getName() {
        return name;
    }

    public Spectator(String name) throws IllegalArgumentException {
        if (name == null)
            throw new IllegalArgumentException();
        this.name = name;
    }

    public boolean takeGift() {
        if (seat.getHasGift()) {
            seat.setHasGift(false);
            return true;
        }
        return false;
    }

    public void bookAnySeat(TheaterSeating ts) {
        seat = ts.bookSeat();
    }

    public void bookSpecificSeat(TheaterSeating ts, int row, int col) {
        seat = ts.bookSeat(row, col);
    }

    public void bookTailoredSeat(TheaterSeating ts) {
        if (name.length() % 2 == 0) {
            seat = ts.bookTailoredEmptySeat(SeatType.IT, true);
        } else if (name.length() < 3) {
            seat = ts.bookTailoredEmptySeat(SeatType.OT, true);
            if (seat == null) {
                seat = ts.bookTailoredEmptySeat(SeatType.OT, false);
            }
        } else {
            seat = ts.bookTailoredEmptySeat(SeatType.MT, true);
            if (seat == null) {
                seat = ts.bookTailoredEmptySeat(SeatType.MT, false);
            }
        }
    }
}

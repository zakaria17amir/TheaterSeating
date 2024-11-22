package theater.person;

import theater.seating.Seat;
import theater.TheaterSeating;

public class Spectator {
    private Seat seat;

    public Seat getSeat() {
        return seat;
    }

    private final String name;

    public String getName() {
        return name;
    }

    public Spectator(String name) {
        this.name = name;
    }

    public boolean takeGift() {
        return false;
    }

    public void bookAnySeat(TheaterSeating ts) {

    }

    public void bookSpecificSeat(TheaterSeating ts, int row, int col) {

    }

    public void bookTailoredSeat(TheaterSeating ts) {

    }
}

package theater.person;

import theater.seating.Seat;
import theater.seating.SeatType;
import theater.TheaterSeating;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SpectatorTest {
    private final static int ROW_COUNT = 4;
    private final static int COL_COUNT = 5;

    @Test
    public void testConstructorWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Spectator(null));
    }

    @Test
    public void testTakeGifts() {
        TheaterSeating ts = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Seat[][] seats = ts.getSeats();
        Spectator spectatorWithGift = new Spectator("GiftReceiver");
        Spectator spectatorWithNoGift = new Spectator("NotGiftReceiver");

        // the seats with (row num + col num ) = odd should have gifts
        spectatorWithGift.bookSpecificSeat(ts, 0, 1);
        spectatorWithNoGift.bookSpecificSeat(ts, 0, 0);
        assertTrue(spectatorWithGift.getSeat().getHasGift());
        assertFalse(spectatorWithNoGift.getSeat().getHasGift());

        assertTrue(spectatorWithGift.takeGift());
        assertFalse(spectatorWithNoGift.takeGift());

        // The gift is already taken when spectatorWithGift.takeGift() was called
        // So now takeGift should return false as there is no gift in that seat.
        assertFalse(spectatorWithGift.takeGift());
    }

    @Test
    public void testBookAnySeat() {
        TheaterSeating ts = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Spectator spectator = new Spectator("Jack");
        spectator.bookAnySeat(ts);
        assertTrue(spectator.getSeat().getIsOccupied());

    }

    @Test
    public void testBookSpecificSeat() {
        TheaterSeating ts = new TheaterSeating(ROW_COUNT, COL_COUNT);
        Seat[][] seats = ts.getSeats();
        Spectator spectator = new Spectator("Jack");
        // row and col must be less than ROW_COUNT and COL_COUNT
        int spectator_row = 2, spectator_col = 2;
        spectator.bookSpecificSeat(ts, spectator_row, spectator_col);

        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                if (i == spectator_row && j == spectator_col) {
                    assertTrue(seats[i][j].getIsOccupied());
                } else {
                    assertFalse(seats[i][j].getIsOccupied());
                }
            }
        }
    }

    @Test
    public void testGetName() {
        TheaterSeating ts = new TheaterSeating(ROW_COUNT, COL_COUNT);
        String spectatorName = "Jack";
        Spectator spectator = new Spectator(spectatorName);

        assertEquals(spectatorName, spectator.getName());

    }

    @Test
    public void testGetSeatInitialState() {
        Spectator spectator = new Spectator("Jack");
        assertNull(spectator.getSeat());

    }

}

package theater;

import theater.seating.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TheaterSeatingTest {

    @Test
    public void testInitialization() {
        int rowCount = 4;
        int colCount = 5;
        TheaterSeating ts = new TheaterSeating(rowCount, colCount);

        // Verify that the number of rows and columns in the seating array matches the
        // expected values.
        assertEquals(rowCount, ts.getSeats().length);
        for (Seat[] row : ts.getSeats()) {
            assertEquals(colCount, row.length);
        }

        // Ensure that seats on the circumference of the theater are assigned the
        // correct SeatType
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                Seat[][] seats = ts.getSeats();
                SeatType st;
                if (i == 0 || i == (rowCount - 1) || j == 0 || j == (colCount - 1)) {
                    st = SeatType.OT;
                } else if (j == colCount / 2) {
                    st = SeatType.MT;
                } else {
                    st = SeatType.IT;
                }

                assertEquals(st, seats[i][j].getSeatType());
            }
        }

    }

    @ParameterizedTest
    @CsvSource({
            "4, 5, 10", // 4x5 seating, expectedGifts 10
            "3, 5, 7", // 3x5 seating, expectedGifts 7
            "3, 7, 10" // 3x7 seating, expectedGifts 10
    })
    public void testGiftsInitialization(int rows, int cols, int expectedGifts) {
        TheaterSeating ts = new TheaterSeating(rows, cols);
        assertEquals(expectedGifts, ts.getAmountOfGifts());
    }

    @Test
    public void testBookSeat() {
        TheaterSeating ts = new TheaterSeating(4, 5);
        int row = 1, col = 1;
        Seat bookedSeat = ts.bookSeat(row, col);
        assertEquals(true, bookedSeat.getIsOccupied());

        try {
            ts.bookSeat(row, col);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testBookTailoredEmptySeat() {
        TheaterSeating ts = new TheaterSeating(4, 5);
        // booking a seat at left side and IT type
        Seat bookLeftITseat = ts.bookTailoredEmptySeat(SeatType.IT, true);
        if (bookLeftITseat != null)
            assertEquals(true, bookLeftITseat.getIsOccupied());

        // booking a seat at right side and OT type
        Seat bookRightOTseat = ts.bookTailoredEmptySeat(SeatType.OT, false);
        if (bookRightOTseat != null)
            assertEquals(true, bookRightOTseat.getIsOccupied());
    }

    @Test
    public void testText() {
        TheaterSeating ts = new TheaterSeating(3, 3);
        String expectedText = "[A] [A] [A] \n" +
                "[A] [A] [A] \n" +
                "[A] [A] [A] \n";
        assertEquals(expectedText, ts.toString());
        ts.bookSeat(1, 1);

        String expectedTextAfterBooking = "[A] [A] [A] \n" +
                "[A] [B] [A] \n" +
                "[A] [A] [A] \n";

        assertEquals(expectedTextAfterBooking, ts.toString());

    }

}

package theater.seating;

import theater.seating.SeatType;

public class Seat {

    private final String id;

    public String getId() {
        return id;
    }

    private boolean hasGift;

    public boolean getHasGift() {
        return hasGift;
    }

    public void setHasGift(boolean hasGift) {
        this.hasGift = hasGift;
    }

    private final SeatType seatType;

    public SeatType getSeatType() {
        return seatType;
    }

    private boolean isOccupied;

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Seat(String id, boolean hasGift, SeatType seatType) {
        this.id = id;
        this.hasGift = hasGift;
        this.seatType = seatType;
    }
}
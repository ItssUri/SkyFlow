package model.auxiliary;
import model.data.SeatType;

public class Seat {
    private SeatType seatType;
    private String seatNumber;
    private boolean isOccupied;
    public SeatType getSeatType() {
        return seatType;
    }
    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
    public String getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    public boolean isOccupied() {
        return isOccupied;
    }
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
    public Seat(SeatType seatType, String seatNumber, boolean isOccupied) {
        this.seatType = seatType;
        this.seatNumber = seatNumber;
        this.isOccupied = isOccupied;
    }

    
}

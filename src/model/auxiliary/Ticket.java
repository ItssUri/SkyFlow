package model.auxiliary;

public class Ticket {
    private String flightCode;
    private boolean hasCheckedBaggage;
    private boolean handbag;
    private String seatNumber;
    
    public String getFlightCode() {
        return flightCode;
    }
    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }
    public boolean ishasCheckedBaggage() {
        return hasCheckedBaggage;
    }
    public void sethasCheckedBaggage(boolean hasCheckedBaggage) {
        this.hasCheckedBaggage = hasCheckedBaggage;
    }
    public boolean isHandbag() {
        return handbag;
    }
    public void setHandbag(boolean handbag) {
        this.handbag = handbag;
    }
    public String getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    
    public Ticket(String flightCode, boolean hasCheckedBaggage, boolean handbag, String seatNumber) {
        this.flightCode = flightCode;
        this.hasCheckedBaggage = hasCheckedBaggage;
        this.handbag = handbag;
        this.seatNumber = seatNumber;
    }
    @Override
    public String toString() {
        return String.format("""
                             
                             | Ticket Code        | %-28s |
                             | Checked Baggage    | %-28s |
                             | Handbag            | %-28s |
                             | Seat Number        | %-28s |
                             +--------------------+------------------------------+""",
            String.valueOf(flightCode),
            String.valueOf(hasCheckedBaggage),
            String.valueOf(handbag),
            String.valueOf(seatNumber)
        );
    }

    

}

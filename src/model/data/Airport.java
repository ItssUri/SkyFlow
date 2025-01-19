package model.data;

public enum Airport {
    ATL("Hartsfield-Jackson Atlanta International Airport"),
    PEK("Beijing Capital International Airport"),
    LAX("Los Angeles International Airport"),
    DXB("Dubai International Airport"),
    HND("Haneda Airport"),
    ORD("O'Hare International Airport"),
    LHR("London Heathrow Airport"),
    PVG("Shanghai Pudong International Airport"),
    CDG("Charles de Gaulle Airport"),
    DFW("Dallas/Fort Worth International Airport"),
    CAN("Guangzhou Baiyun International Airport"),
    AMS("Amsterdam Airport Schiphol"),
    FRA("Frankfurt Airport"),
    IST("Istanbul Airport"),
    SIN("Singapore Changi Airport"),
    ICN("Incheon International Airport"),
    DEN("Denver International Airport"),
    BKK("Suvarnabhumi Airport"),
    JFK("John F. Kennedy International Airport"),
    SFO("San Francisco International Airport"),
    BCN("Barcelona El Prat Airport"),
    SEA("Seattle-Tacoma International Airport"),
    MIA("Miami International Airport"),
    SYD("Sydney Kingsford Smith Airport"),
    KUL("Kuala Lumpur International Airport"),
    MEX("Mexico City International Airport"),
    LGA("LaGuardia Airport"),
    SCL("Comodoro Arturo Merino Benítez International Airport"),
    ZRH("Zurich Airport"),
    DEL("Indira Gandhi International Airport"),
    YYZ("Toronto Pearson International Airport"),
    BOM("Chhatrapati Shivaji Maharaj International Airport"),
    MEL("Melbourne Airport"),
    CGK("Soekarno-Hatta International Airport"),
    HKG("Hong Kong International Airport"),
    DUB("Dublin Airport"),
    BNE("Brisbane Airport");
    
    private final String fullName;

    Airport(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}


/*
 * Airport airport = Airport.ATL;
    System.out.println("Airport Code: " + airport + ", Full Name: " + airport.getFullName());
 *  > Airport Code: ATL, Full Name: Hartsfield-Jackson Atlanta International Airport
 */
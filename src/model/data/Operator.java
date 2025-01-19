package model.data;

public enum Operator {
    RYANAIR("FR"),
    VUELING("VY"), 
    IBERIA("IB"), 
    AMERICAN_AIRLINES("AA"), 
    KLM("KL"), 
    PRIVATE("PV"),
    DELTA("DL"),
    UNITED("UA"),
    LUFTHANSA("LH"),
    AIR_FRANCE("AF"),
    EMIRATES("EK"),
    QATAR_AIRWAYS("QR"),
    BRITISH_AIRWAYS("BA"),
    AIR_CANADA("AC"),
    SINGAPORE_AIRLINES("SQ"),
    ANA("NH"),
    TURKISH_AIRLINES("TK"),
    QANTAS("QF"),
    SOUTHWEST_AIRLINES("WN"),
    AIR_NEW_ZEALAND("NZ"),
    AEROFLOT("SU"),
    SAS("SK"),
    FINNAIR("AY"),
    ALITALIA("AZ"),
    AIR_CHINA("CA"),
    JAPAN_AIRLINES("JL");

    // FORMAT
    // X = operator Y = random number
    //  
    
    private final String airlineCode;

    Operator(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineCode() {
        return airlineCode;
    }
}

package model.transportation;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.abstracts.Human;
import model.auxiliary.Ticket;
import model.data.Gender;
import model.data.Nationality;

public class Passenger extends Human{
    private Ticket ticket;
    
    public Ticket getTicket(){
        return ticket;
    }

    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }

    public Passenger(String name, String surnames, LocalDate birthDate, Gender gender, Nationality nationality,
            String id, String email, String phoneNumber, Ticket ticket) {
        super(name, surnames, birthDate, gender, nationality, id, email, phoneNumber);
        this.ticket = ticket;
    }

    public boolean checkId(){
        final Pattern DNI_REGEX = Pattern.compile("/^(\\d{8})([A-Z])$/",Pattern.CASE_INSENSITIVE);
        final Pattern CIF_REGEX = Pattern.compile("/^([ABCDEFGHJKLMNPQRSUVW])(\\d{7})([0-9A-J])$/",Pattern.CASE_INSENSITIVE);
        final Pattern NIE_REGEX = Pattern.compile("/^[XYZ]\\d{7,8}[A-Z]$/",Pattern.CASE_INSENSITIVE);
        Matcher DNI_MATCHER = DNI_REGEX.matcher(this.getId());
        boolean DNI_matchFound = DNI_MATCHER.find();
        Matcher CIF_MATCHER = CIF_REGEX.matcher(this.getId());
        boolean CIF_matchFound = CIF_MATCHER.find();
        Matcher NIE_MATCHER = NIE_REGEX.matcher(this.getId());
        boolean NIE_matchFound = NIE_MATCHER.find();
        return DNI_matchFound || CIF_matchFound || NIE_matchFound;
    }

    public boolean checkBoardingPass(){ //AB123456
        final Pattern PASS_REGEX = Pattern.compile("^[A-Z]{2}\\d{6}$",Pattern.CASE_INSENSITIVE);
        Matcher passMatcher = PASS_REGEX.matcher(this.ticket.getFlightCode());
        return passMatcher.find();
    }


    @Override
    public String toString() {
        return super.toString()+ticket.toString();
    }


}

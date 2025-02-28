package model.transportation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import model.data.Airport;
import model.data.FlightStatus;
import model.data.Operator;
import model.utils.StringUtils;
import model.workers.Pilot;

public class Flight {
    private ArrayList<Passenger> passengers;
    private Operator operator;
    private Plane plane;
    private Pilot pilot;
    private Airport originAirport;
    private Airport destinationAirport;
    private LocalDateTime scheduledDeparture;
    private double flightTime;
    private FlightStatus status;
    private String flightCode;

    

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public LocalDateTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(LocalDateTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public double getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(double flightTime) {
        this.flightTime = flightTime;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    
    
    public Flight(Operator operator,Plane plane, Pilot pilot, Airport originAirport, Airport destinationAirport,
            LocalDateTime scheduledDeparture, double flightTime, FlightStatus status, String flightCode) {
        this.passengers = new ArrayList<Passenger>();
        this.operator = operator;
        this.plane = plane;
        this.pilot = pilot;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.scheduledDeparture = scheduledDeparture;
        this.flightTime = flightTime;
        this.status = status;
        this.flightCode = flightCode;
    }

    public Flight(Operator operator,Plane plane, Airport originAirport, Airport destinationAirport,
            LocalDateTime scheduledDeparture, double flightTime, FlightStatus status, String flightCode) {
        this.passengers = new ArrayList<Passenger>();
        this.operator = operator;
        this.plane = plane;
        this.pilot = new Pilot("Pilot", "Example", null, null, null, "11111111L", flightCode, flightCode, flightCode, flightTime, null);
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.scheduledDeparture = scheduledDeparture;
        this.flightTime = flightTime;
        this.status = status;
        this.flightCode = flightCode;
    }


    public void delayDeparture(int delay) {
        this.scheduledDeparture.plusMinutes(delay);
        this.status = FlightStatus.DELAYED;
    }   

    public String generateFlightReport() {
        String operatorName = StringUtils.capitalizeFirstLetter(this.operator.name());
        String pilotRank = StringUtils.capitalizeFirstLetter(this.pilot.getPilotRank().name());
        StringBuilder sb = new StringBuilder();
        sb.append("\n\u001B[36m[")
        .append(operatorName).append(" - FLIGHT REPORT ").append(this.flightCode).append("] \n")
        .append(this.originAirport).append(" (").append(this.originAirport.getFullName()).append(") - ")
        .append(this.destinationAirport).append(" (").append(this.destinationAirport.getFullName()).append(")\n")
      
        .append("Number of passengers present in the flight: ").append(this.passengers.size()).append("\n")
        .append("Piloted by ").append(pilotRank).append(" ")
        .append(this.pilot.getSurnames()).append(", ").append(this.pilot.getName()).append("\n\n")
      
        .append("Plane Info:\n")
        .append(" Plane code: ").append(this.plane.getPlaneCode()).append("\n")
        .append(" Plane Model: ").append(this.plane.getModel()).append("\n\n")
      
        .append("Route Info:\n")
        .append(" Origin: ").append(this.originAirport.getFullName()).append("\n")
        .append(" Destination: ").append(this.destinationAirport.getFullName()).append("\n")
      
        .append(" Flight time: ").append(this.flightTime).append("h (")
        .append(this.flightTime * 60).append("min)\u001B[0m");      
        return sb.toString();
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return String.format("""
                             Flight Details:
                             +-----------------------+-----------------------------+
                             | Attribute             | Value                       |
                             +-----------------------+-----------------------------+
                             | Passengers            | %-27s |
                             | Operator              | %-27s |
                             | Plane                 | %-27s |
                             | Pilot                 | %-27s |
                             | Origin Airport        | %-27s |
                             | Destination Airport   | %-27s |
                             | Scheduled Departure   | %-27s |
                             | Flight Time           | %-27s |
                             | Status                | %-27s |
                             | Flight Code           | %-27s |
                             +-----------------------+-----------------------------+""",
            String.valueOf(passengers.size()), 
            String.valueOf(operator), 
            String.valueOf(plane.getPlaneCode()), 
            String.valueOf(pilot.getId()), 
            String.valueOf(originAirport), 
            String.valueOf(destinationAirport), 
            String.valueOf(scheduledDeparture), 
            String.valueOf(flightTime), 
            String.valueOf(status), 
            String.valueOf(flightCode)
        );
    }


    

}

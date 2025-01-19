package model.transportation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import model.workers.Pilot;
import model.data.Airport;
import model.data.FlightStatus;
import model.data.Operator;
import model.utils.StringUtils;

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
        this.passengers = new ArrayList<>();
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

    public void delayDeparture(int delay) {
        this.scheduledDeparture.plusMinutes(delay);
        this.status = FlightStatus.DELAYED;
    }   

    public String generateFlightReport() {
        String operatorName = StringUtils.capitalizeFirstLetter(this.operator.name());
        String pilotRank = StringUtils.capitalizeFirstLetter(this.pilot.getPilotRank().name());
        StringBuilder sb = new StringBuilder();
        sb.append("\n\u001B[36m[" + operatorName +  " - FLIGHT REPORT " + this.flightCode + "] \n" + this.originAirport + " (" + this.originAirport.getFullName() + ") - " + this.destinationAirport + " (" + this.destinationAirport.getFullName() + ")\n")
        .append("Number of passengers present in the flight: " + this.passengers.size()+"\n")
        .append("Piloted by " + pilotRank + " " + this.pilot.getSurnames() + "," + this.pilot.getName()+"\n")
        .append("Plane Info:\n Plane code " + this.plane.getPlaneCode() + "\n")
        .append(" Plane Model: " + this.plane.getModel() + "\n")
        .append("Route info:\n Origin: " + this.originAirport.getFullName() + "\n Destination: " + this.destinationAirport.getFullName()+"\n")
        .append(" Flight time: " + this.flightTime + "h ("+(this.flightTime*60)+"min)\u001B[0m");
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
        return String.format(
            "Flight Details:\n" +
            "+-----------------------+-----------------------------+\n" +
            "| Attribute             | Value                       |\n" +
            "+-----------------------+-----------------------------+\n" +
            "| Passengers            | %-27s |\n" +
            "| Operator              | %-27s |\n" +
            "| Plane                 | %-27s |\n" +
            "| Pilot                 | %-27s |\n" +
            "| Origin Airport        | %-27s |\n" +
            "| Destination Airport   | %-27s |\n" +
            "| Scheduled Departure   | %-27s |\n" +
            "| Flight Time           | %-27s |\n" +
            "| Status                | %-27s |\n" +
            "| Flight Code           | %-27s |\n" +
            "+-----------------------+-----------------------------+",
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

package model.transportation;

import java.time.Year;
import java.util.ArrayList;
import model.data.EngineType;
import model.data.Model;
import model.data.SeatType;
import model.auxiliary.Seat;

public class Plane {
    private Year manufactureYear;
    private Model model;
    private ArrayList<Seat> seats;
    private double tankCapacity;
    private EngineType engineType;
    private int capacity;
    private double topSpeed;
    private String planeCode;

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public Year getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Year manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(double topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getPlaneCode() {
        return planeCode;
    }

    public void setPlaneCode(String planeCode) {
        this.planeCode = planeCode;
    }

    public Plane(Model model, double tankCapacity, EngineType engineType, int capacity, 
                double topSpeed, String planeCode){
        this.seats = new ArrayList<>();
        this.model = model;
        this.tankCapacity = tankCapacity;
        this.engineType = engineType;
        this.capacity = capacity;
        this.topSpeed = topSpeed;
        this.planeCode = planeCode;
        this.manufactureYear = Year.now();
        int economySeats = (int) (capacity * 0.7); // 70% Economy
        int premiumEconomySeats = (int) (capacity * 0.1); // 10% Economy Premium
        int businessSeats = (int) (capacity * 0.15); // 15% Business
        int firstClassSeats = capacity - economySeats - premiumEconomySeats - businessSeats; // Remaining for First Class
        int seatCounter=1;
        for (int i = 0; i < economySeats; i++) {
            this.seats.add(new Seat(SeatType.ECONOMY, String.valueOf(seatCounter), false));
            seatCounter++;
        }
        for (int i = 0; i < premiumEconomySeats; i++) {
            this.seats.add(new Seat(SeatType.ECONOMY_PREMIUM, String.valueOf(seatCounter), false));
            seatCounter++;
        }
        for (int i = 0; i < businessSeats; i++) {
            this.seats.add(new Seat(SeatType.BUSINESS, String.valueOf(seatCounter), false));
            seatCounter++;
        }
        for (int i = 0; i < firstClassSeats; i++) {
            this.seats.add(new Seat(SeatType.FIRST_CLASS, String.valueOf(seatCounter), false));
            seatCounter++;
        }

    }

    @Override
    public String toString() {
        return String.format(
            "Plane Details:\n" +
            "+-------------------+------------------+\n" +
            "| Attribute         | Value            |\n" +
            "+-------------------+------------------+\n" +
            "| Manufacture Year  | %-16s |\n" +
            "| Model             | %-16s |\n" +
            "| Seats             | %-16s |\n" +
            "| Tank Capacity (L) | %-16s |\n" +
            "| Engine Type       | %-16s |\n" +
            "| Capacity          | %-16s |\n" +
            "| Top Speed (km/h)  | %-16s |\n" +
            "| Plane Code        | %-16s |\n" +
            "+-------------------+------------------+",
            String.valueOf(manufactureYear), 
            String.valueOf(model), 
            String.valueOf(seats.size()), 
            String.valueOf(tankCapacity), 
            String.valueOf(engineType), 
            String.valueOf(capacity), 
            String.valueOf(topSpeed), 
            String.valueOf(planeCode)
        );
    }

}

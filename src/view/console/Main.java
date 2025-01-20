package view.console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Thread;
import java.util.Scanner;

import model.abstracts.Worker;
import model.auxiliary.Seat;
import model.auxiliary.Ticket;
import model.data.Airport;
import model.data.EngineType;
import model.data.FlightStatus;
import model.data.Gender;
import model.data.Model;
import model.data.Nationality;
import model.data.Operator;
import model.data.PilotRank;
import model.data.SeatType;
import model.transportation.Flight;
import model.transportation.Passenger;
import model.transportation.Plane;
import model.workers.AirportOperator;
import model.workers.Pilot;
import model.workers.Stewardess;

import model.utils.ExceptionHandler;
import model.utils.StringUtils;

public class Main {
    static ArrayList<Plane> planes = new ArrayList<>();
    static ArrayList<Pilot> pilots= new ArrayList<>();
    static ArrayList<Flight> flights= new ArrayList<>();
    static ArrayList<AirportOperator> operators= new ArrayList<>();
    static ArrayList<Stewardess> stewardesses= new ArrayList<>();
    static ArrayList<Worker> workers= new ArrayList<>();
    static ArrayList<Passenger> passengers = new ArrayList<>();
    
    static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static final DateTimeFormatter DTF_DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    static final DateTimeFormatter DTF_TIME = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {
        try{
        
        planes.add(new Plane(Model.BOEING_737_MAX, 330, EngineType.TURBOJET, 40, 870, "PL8089"));
        pilots.add(new Pilot("Alice", "Trujillo", LocalDate.now(), Gender.FEMALE, Nationality.MALAYSIAN, "93384110Y", "alice.trujillo@example.com", "123123123", "AT0501", 0, PilotRank.SECOND_OFFICER));
        operators.add(new AirportOperator("John", "Doe", LocalDate.of(1985, 5, 15), Gender.MALE, Nationality.AMERICAN, "JOHNDOE123", "john.doe@example.com", "5551234567", "TOWER_OP_01", 15, "Tower Control", LocalTime.of(6, 0), LocalTime.of(18, 0)));
        flights.add(new Flight(Operator.AEROFLOT, planes.get(0), pilots.get(0), Airport.AMS, Airport.KUL, LocalDateTime.now(), 3.3, FlightStatus.LANDED, "AMSKL001"));
        stewardesses.add(new Stewardess("Jane", "Doe", LocalDate.of(1985, 11, 25), Gender.FEMALE, Nationality.BRUNEIAN, "JDOE123", "jane.doe@email.cocm", "12389312231", "jd123", 39, 130, 50));
        passengers.add(new Passenger("Ellen", "Joe", LocalDate.of(2001, 8, 5), Gender.FEMALE, Nationality.TONGAN, "123987L", "ellenjoe@email.com", "293857193", new Ticket("AMSKL001", false, true, "12")));
        flights.get(0).addPassenger(passengers.get(0));
        Scanner scanner = new Scanner(System.in);
        
        try {
                System.out.println("Welcome to Skyflow!");
                Thread.sleep(1000);
                System.out.print("\033[H\033[2J");
                System.out.flush();
        } catch (Exception e) {
            ExceptionHandler.consoleHandle(e);
        }
        boolean running = true;

        while (running) {
            System.out.println("Main Menu:");
            System.out.println("1. Flights");
            System.out.println("2. Passengers");
            System.out.println("3. Planes");
            System.out.println("4. Airport operators");
            System.out.println("5. Pilots");
            System.out.println("6. Stewardesses");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.next();

            switch (choice) {
                case "1":
                    handleFlights(scanner);
                    break;
                case "2":
                    handlePassengers(scanner);
                    break;
                case "3":
                    handlePlanes(scanner);
                    break;
                case "4":
                    handleAirportOperators(scanner);
                    break;
                case "5":
                    handlePilots(scanner);
                    break;
                case "6":
                    handleStewardesses(scanner);
                    break;
                case "0":
                    System.out.println("Thanks for using SkyFlow!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
    catch (Exception e) {
        ExceptionHandler.consoleHandle(e);
    }
    }

    private static void handleFlights(Scanner scanner) {
        boolean subMenuRunning = true;

        while (subMenuRunning) {
            System.out.println("\nFlights Menu:");
            System.out.println("1. Create Flight");
            System.out.println("2. List All flights");
            System.out.println("3. Delete Flight");
            System.out.println("4. Generate Flight Report");
            System.out.println("5. Print passengers");
            System.out.println("0. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int subChoice = scanner.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.println("Creating a new Flight...");
                    System.out.print("Flight Operator: ");
                    Operator _operator = Operator.valueOf(StringUtils.enumFormat(scanner.next()));
                    System.out.print("Plane Code: ");
                    String  _pcode = scanner.next();
                    Plane plane = null;
                    for (Plane p : planes) {
                        if (p.getPlaneCode().equals(_pcode)) {
                            plane = p;
                        }
                    }
                    if (plane == null) {
                        System.out.println("Plane not found!");
                        break;
                    }
                    System.out.print("Pilot Id: ");
                    String  _pid = scanner.next();
                    Pilot pilot = null;
                    for (Pilot p : pilots) {
                        if (p.getId().equals(_pid)) {
                            pilot = p;
                        }
                    }
                    if (pilot == null) {
                        System.out.println("Pilot not found!");
                        break;
                    }
                    System.out.print("Origin Airport Code: ");
                    Airport origin = Airport.valueOf(StringUtils.enumFormat(scanner.next()));
                    System.out.print("Destination Airport Code: ");
                    Airport destination = Airport.valueOf(StringUtils.enumFormat(scanner.next()));
                    System.out.print("Enter the date and time of departure (dd/MM/yyyy HH:mm): ");
                    String dateTimeInput = scanner.next();
                    LocalDateTime scheduledDeparture = LocalDateTime.parse(dateTimeInput, DTF_DATE_TIME);
                    System.out.print("Flight time (h): ");
                    Double flightTime  = scanner.nextDouble();
                    FlightStatus status = FlightStatus.SCHEDULED;
                    System.out.print("Enter flight code: ");
                    String flightCode = scanner.next();
                    Flight flight = new Flight(_operator, plane, pilot, origin, destination, scheduledDeparture, flightTime, status, flightCode);
                    flights.add(flight);
                    System.out.println("Flight Added!");
                    break;
                case 2:
                    System.out.println("Listing all flights...");
                    for (Flight f : flights) {
                        System.out.println(f.toString() + "\n");
                    }
                    System.out.println("Press enter to continue.");
                    try {
                        System.in.read();
                        scanner.nextLine();
                    } catch (Exception e) {
                        ExceptionHandler.consoleHandle(e);
                    }
                    break;
                case 3:
                    System.out.print("\nEnter the flight code: ");
                    String codeToDelete = scanner.next();
                    Iterator<Flight> iterator = flights.iterator();
                    boolean removed = false;
                    while (iterator.hasNext()) {
                        Flight f = iterator.next();
                        if (f.getFlightCode().equals(codeToDelete)) {
                            iterator.remove();
                            removed = true;
                            System.out.println("Flight removed: " + f.getFlightCode());
                        }
                    }
                    if (!removed) {
                        System.out.println("No flight found with code: " + codeToDelete);
                    }
                    break;
                case 4:
                    System.out.print("\nEnter the flight code: ");
                    String flightToCheck = scanner.next();
                    for (Flight f : flights) {
                        if (f.getFlightCode().equalsIgnoreCase(flightToCheck)) {
                            System.out.println(f.generateFlightReport());
                        }
                    }
                    break;
                case 5:
                    System.out.print("\nEnter the flight code: ");
                    String passengerCheck = scanner.next();
                    for (Flight f : flights) {
                        if (f.getFlightCode().equalsIgnoreCase(passengerCheck)) {
                            System.out.println("Flight Found! Passengers:");
                            for (Passenger passengerInFlight : f.getPassengers()) {
                                System.out.println(" " + passengerInFlight.getSurnames() + ", " + passengerInFlight.getName() + " | " + passengerInFlight.getEmail() + " | " + passengerInFlight.getId() + " | Seat: " + passengerInFlight.getTicket().getSeatNumber());
                            }
                        }
                    }
                    break;
                case 0:
                    subMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handlePassengers(Scanner scanner) {
        boolean subMenuRunning = true;

        while (subMenuRunning) {
            System.out.println("\nPassengers Menu:");
            System.out.println("1. Create Passenger");
            System.out.println("2. List All Passengers");
            System.out.println("3. Delete Passenger");
            System.out.println("4. Confirm Seat");
            System.out.println("0. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int subChoice = scanner.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.println("Creating a new Passenger...");
                    System.out.print("Passenger name: ");
                    String name = scanner.next();
                    System.out.print("Passenger surnames: ");
                    String surnames = scanner.next();
                    System.out.print("(dd/MM/yyyy) Passenger birth date: ");
                    LocalDate birthDate = LocalDate.parse(scanner.next(),DTF);
                    System.out.print("Passenger gender: ");
                    String gender = scanner.next();
                    System.out.print("Passenger nationality: ");
                    String nationality = scanner.next();
                    System.out.print("Passenger id: ");
                    String id = scanner.next();
                    System.out.print("Passenger email: ");
                    String email = scanner.next();
                    System.out.print("Passenger ticket code: ");
                    String ticketCode = scanner.next();
                    System.out.print("(yes/no) Does this passenger have a checked baggage? ");
                    Boolean hasCheckedBaggage = scanner.next().trim().toLowerCase().equals("yes");
                    System.out.print("(yes/no) Does this passenger have a handbag? ");
                    Boolean handbag = scanner.next().trim().toLowerCase().equals("yes");
                    System.out.print("Passenger seat number: ");
                    String seatNumber = scanner.next();
                    Ticket ticket = new Ticket(ticketCode, hasCheckedBaggage, handbag, seatNumber);
                    Passenger passenger = new Passenger(name, surnames, birthDate, Gender.valueOf(StringUtils.enumFormat(gender)), Nationality.valueOf(StringUtils.enumFormat(nationality)), id, email, seatNumber, ticket);
                    passengers.add(passenger);
                    break;
                case 2:
                    System.out.println("Listing all Passengers...");
                    for (Passenger p : passengers) {
                        System.out.println(p.toString() + "\n");
                    }
                    System.out.println("Press enter to continue.");
                    try {
                        System.in.read();
                        scanner.nextLine();
                    } catch (Exception e) {
                        ExceptionHandler.consoleHandle(e);
                    }
                    break;
                case 3:
                    System.out.print("\nEnter the passenger's ID: ");
                    String idToDelete = scanner.next();
                    Iterator<Passenger> iterator = passengers.iterator();
                    boolean removed = false;
                    while (iterator.hasNext()) {
                        Passenger p = iterator.next();
                        if (p.getId().equals(idToDelete)) {
                            iterator.remove();
                            removed = true;
                            System.out.println("Passenger removed: " + p.getId());
                        }
                    }
                    if (!removed) {
                        System.out.println("No passenger found with ID: " + idToDelete);
                    }
                    break;
                
                case 4:
                    System.out.print("\nEnter the passenger's ID: ");
                    String idToConfirm = scanner.next();
                    String passengerSeat = null;
                    String passengerFlightCode = null;
                    for (Passenger psg : passengers) {
                        if (psg.getId().equalsIgnoreCase(idToConfirm)) {
                            passengerSeat=psg.getTicket().getSeatNumber();
                            passengerFlightCode=psg.getTicket().getFlightCode();
                        }
                    }
                    if (passengerSeat == null) {
                        System.out.println("No passenger found with that ID!");
                        break;
                    }
                    for (Flight flight : flights) {
                        if (flight.getFlightCode().equalsIgnoreCase(passengerFlightCode)) {
                            for (Seat seat : flight.getPlane().getSeats()) {
                                if (seat.getSeatNumber().equalsIgnoreCase(passengerSeat)) {
                                    seat.setOccupied(true);
                                    System.out.println("Seat Confirmed in flight!");
                                }
                            }
                        }
                    }
                    break;
                case 0:
                    subMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handlePlanes(Scanner scanner) {
        boolean subMenuRunning = true;

        while (subMenuRunning) {
            System.out.println("\nPlanes Menu:");
            System.out.println("1. Create Plane");
            System.out.println("2. List All Planes");
            System.out.println("3. Remove Plane");
            System.out.println("4. List Seat Distribution");
            System.out.println("0. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int subChoice = scanner.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.println("Creating a new Plane...");
                    System.out.print("Plane model: ");
                    String planeModel = scanner.next();
                    System.out.print("Tank capacity (L): ");
                    double tankCapacity = scanner.nextDouble();
                    System.out.print("Engine type: ");
                    String engineType = scanner.next();
                    System.out.print("People capacity: ");
                    int capacity = scanner.nextInt();
                    System.out.print("Top speed: ");
                    double topSpeed = scanner.nextDouble();
                    System.out.print("Plane code: ");
                    String planeCode = scanner.next();
                    Plane plane = new Plane(Model.valueOf(StringUtils.enumFormat(planeModel)), tankCapacity, EngineType.valueOf(StringUtils.enumFormat(engineType)), capacity, topSpeed, planeCode);
                    planes.add(plane);
                    System.out.println("Plane added!");
                    break;
                case 2:
                    System.out.println("Listing all planes...");
                    for (Plane p : planes) {
                        System.out.println(p.toString() + "\n");
                    }
                    System.out.println("Press enter to continue.");
                    try {
                        System.in.read();
                        scanner.nextLine();
                    } catch (Exception e) {
                        ExceptionHandler.consoleHandle(e);
                    }
                    break;
                case 3:
                    System.out.print("\nEnter the Plane Code: ");
                    String codeToDelete = scanner.next();
                    Iterator<Plane> iterator = planes.iterator();
                    boolean removed = false;
                    while (iterator.hasNext()) {
                        Plane p = iterator.next();
                        if (p.getPlaneCode().equals(codeToDelete)) {
                            iterator.remove();
                            removed = true;
                            System.out.println("Plane removed: " + p.getPlaneCode());
                        }
                    }
                    if (!removed) {
                        System.out.println("No plane found with code: " + codeToDelete);
                    }
                    break;
                case 4:
                    System.out.print("\nEnter the Plane Code: ");
                    String planeCheckSeats = scanner.next();
                    for (Plane ps : planes) {
                        if (ps.getPlaneCode().equalsIgnoreCase(planeCheckSeats)) {
                            ArrayList<Seat> planeSeats = ps.getSeats();
                            System.out.println("Economy Seats: " + String.valueOf(planeSeats.stream().filter( Seat -> Seat.getSeatType() == SeatType.ECONOMY).count()));
                            System.out.println("Economy Premium Seats: " + String.valueOf(planeSeats.stream().filter( Seat -> Seat.getSeatType() == SeatType.ECONOMY_PREMIUM).count()));
                            System.out.println("Business Seats: " + String.valueOf(planeSeats.stream().filter( Seat -> Seat.getSeatType() == SeatType.BUSINESS).count()));
                            System.out.println("First Class Seats: " + String.valueOf(planeSeats.stream().filter( Seat -> Seat.getSeatType() == SeatType.FIRST_CLASS).count()));
                        }
                    }
                    break;
                case 0:
                    subMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleAirportOperators(Scanner scanner) {
        boolean subMenuRunning = true;

        while (subMenuRunning) {
            System.out.println("\nAirport operators Menu:");
            System.out.println("1. Create Airport Operator");
            System.out.println("2. List All Airport operators");
            System.out.println("3. Delete Airport Operator");
            System.out.println("4. Generate Operator's Paysheet");
            System.out.println("0. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int subChoice = scanner.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.println("Creating a new Airport Operator...");
                    System.out.print("Operator name: ");
                    String name = scanner.next();
                    System.out.print("Operator surnames: ");
                    String surnames = scanner.next();
                    System.out.print("(dd/MM/yyyy) Operator birth date: ");
                    LocalDate birthDate = LocalDate.parse(scanner.next(),DTF);
                    System.out.print("Operator gender: ");
                    String gender = scanner.next();
                    System.out.print("Operator nationality: ");
                    String nationality = scanner.next();
                    System.out.print("Operator id: ");
                    String id = scanner.next();
                    System.out.print("Operator email: ");
                    String email = scanner.next();
                    System.out.print("Operator phone number: ");
                    String phoneNumber = scanner.next();
                    System.out.print("Operator worker code: ");
                    String workerCode = scanner.next();
                    System.out.print("Operator worked hours: ");
                    double workedHours = scanner.nextDouble();
                    System.out.print("Operator salary: ");
                    double salary = scanner.nextDouble();
                    System.out.print("Operator charge: ");
                    String operatorCharge = scanner.next();
                    System.out.print("Enter the shift's start time (HH:mm): ");
                    LocalTime shiftStart = LocalTime.parse(scanner.next(),DTF);
                    System.out.print("Enter the shift's end time (HH:mm): ");
                    LocalTime shiftEnd = LocalTime.parse(scanner.next(),DTF);
                    AirportOperator operator = new AirportOperator(name, surnames, birthDate, Gender.valueOf(StringUtils.enumFormat(gender)), Nationality.valueOf(StringUtils.enumFormat(nationality)), id, email, phoneNumber, workerCode, workedHours, salary, operatorCharge, shiftStart, shiftEnd);
                    operators.add(operator);
                    System.out.println("Operator added!");
                    break;
                case 2:
                    System.out.println("Listing all Airport operators...");
                    for (AirportOperator a : operators) {
                        System.out.println(a.toString() + "\n");
                    }
                    System.out.println("Press enter to continue.");
                    try {
                        System.in.read();
                        scanner.nextLine();
                    } catch (Exception e) {
                        ExceptionHandler.consoleHandle(e);
                    }
                    break;
                case 3:
                    System.out.print("\nEnter the operator's ID: ");
                    String idToDelete = scanner.next();
                    Iterator<AirportOperator> iterator = operators.iterator();
                    boolean removed = false;
                    while (iterator.hasNext()) {
                        AirportOperator a = iterator.next();
                        if (a.getId().equals(idToDelete)) {
                            iterator.remove();
                            removed = true;
                            System.out.println("Operator removed: " + a.getId());
                        }
                    }
                    if (!removed) {
                        System.out.println("No operator found with ID: " + idToDelete);
                    }
                    break;
                case 4:
                    System.out.print("\nEnter the Operator's ID: ");
                    String payid = scanner.next();
                    for (AirportOperator opts : operators) {
                        if (opts.getId().equalsIgnoreCase(payid)) {
                            System.out.println(opts.generatePaysheet());
                        }
                    }
                    break;
                case 0:
                    subMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handlePilots(Scanner scanner) {
        boolean subMenuRunning = true;

        while (subMenuRunning) {
            System.out.println("\nPilots Menu:");
            System.out.println("1. Create Pilot");
            System.out.println("2. List All Pilots");
            System.out.println("3. Delete Pilot");
            System.out.println("4. Generate Pilot paysheet");
            System.out.println("0. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int subChoice = scanner.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.println("Creating a new Pilot...");
                    System.out.print("Pilot name: ");
                    String name = scanner.next();
                    System.out.print("Pilot surnames: ");
                    String surnames = scanner.next();
                    System.out.print("(dd/MM/yyyy) Pilot birth date: ");
                    LocalDate birthDate = LocalDate.parse(scanner.next(),DTF);
                    System.out.print("Pilot gender: ");
                    String gender = scanner.next();
                    System.out.print("Pilot nationality: ");
                    String nationality = scanner.next();
                    System.out.print("Pilot id: ");
                    String id = scanner.next();
                    System.out.print("Pilot email: ");
                    String email = scanner.next();
                    System.out.print("Pilot phone number: ");
                    String phoneNumber = scanner.next();
                    System.out.print("Pilot worker code: ");
                    String workerCode = scanner.next();
                    System.out.print("Pilot worked hours: ");
                    double workedHours = scanner.nextDouble();
                    System.out.print("Pilot salary: ");
                    double salary = scanner.nextDouble();
                    System.out.print("Pilot rank: ");
                    String pilotRank = scanner.next();
                    Pilot _pilot = new Pilot(name, surnames, birthDate, Gender.valueOf(StringUtils.enumFormat(gender)),Nationality.valueOf(StringUtils.enumFormat(nationality)), id, email, phoneNumber, workerCode, workedHours, salary, PilotRank.valueOf(StringUtils.enumFormat(pilotRank)));
                    pilots.add(_pilot);
                    break;
                case 2:
                    System.out.println("Listing all pilots...");
                    for (Pilot p : pilots) {
                        System.out.println(p.toString() + "\n");
                    }
                    System.out.println("Press enter to continue.");
                    try {
                        System.in.read();
                        scanner.nextLine();
                    } catch (Exception e) {
                        ExceptionHandler.consoleHandle(e);
                    }
                    break;
                case 3:
                    System.out.print("\nEnter the pilot's ID: ");
                    String idToDelete = scanner.next();
                    Iterator<Pilot> iterator = pilots.iterator();
                    boolean removed = false;
                    while (iterator.hasNext()) {
                        Pilot p = iterator.next();
                        if (p.getId().equals(idToDelete)) {
                            iterator.remove();
                            removed = true;
                            System.out.println("Pilot removed: " + p.getId());
                        }
                    }
                    if (!removed) {
                        System.out.println("No pilot found with ID: " + idToDelete);
                    }
                    break;
                case 4:
                    System.out.print("\nEnter the Pilot's ID: ");
                    String payid = scanner.next();
                    for (Pilot pts : pilots) {
                        if (pts.getId().equalsIgnoreCase(payid)) {
                            System.out.println(pts.generatePaysheet());
                        }
                    }
                    break;
                case 0:
                    subMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleStewardesses(Scanner scanner) {
        boolean subMenuRunning = true;

        while (subMenuRunning) {
            System.out.println("\nStewardesses Menu:");
            System.out.println("1. Create Stewardess");
            System.out.println("2. List All stewardesses");
            System.out.println("3. Delete Stewardess");
            System.out.println("4. Generate Paysheet");
            System.out.println("0. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int subChoice = scanner.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.println("Creating a new Stewardess...");
                    System.out.print("Stewardess name: ");
                    String name = scanner.next();
                    System.out.print("Stewardess surnames: ");
                    String surnames = scanner.next();
                    System.out.print("(dd/MM/yyyy) Stewardess birth date: ");
                    LocalDate birthDate = LocalDate.parse(scanner.next(),DTF);
                    System.out.print("Stewardess gender: ");
                    String gender = scanner.next();
                    System.out.print("Stewardess nationality: ");
                    String nationality = scanner.next();
                    System.out.print("Stewardess id: ");
                    String id = scanner.next();
                    System.out.print("Stewardess email: ");
                    String email = scanner.next();
                    System.out.print("Stewardess phone number: ");
                    String phoneNumber = scanner.next();
                    System.out.print("Stewardess worker code: ");
                    String workerCode = scanner.next();
                    System.out.print("Stewardess worked hours: ");
                    double workedHours = scanner.nextDouble();
                    System.out.print("Stewardess salary: ");
                    double salary = scanner.nextDouble();
                    System.out.print("Stewardess Height: ");
                    double height = scanner.nextDouble();
                    System.out.print("Stewardess Weight: ");
                    double weight = scanner.nextDouble();
                    Stewardess _swd = new Stewardess(name,surnames,birthDate,Gender.valueOf(StringUtils.enumFormat(gender)),Nationality.valueOf(StringUtils.enumFormat(nationality)),id,email,phoneNumber,workerCode,workedHours,salary,height,weight);
                    stewardesses.add(_swd);
                    System.out.println("Stewardess added!");
                    break;
                case 2:
                    System.out.println("Listing all stewardesses...");
                    for (Stewardess s : stewardesses) {
                        System.out.println(s.toString() + "\n");
                    }
                    System.out.println("Press enter to continue.");
                    try {
                        System.in.read();
                        scanner.nextLine();
                    } catch (Exception e) {
                        ExceptionHandler.consoleHandle(e);
                    }
                    break;
                case 3:
                    System.out.print("\nEnter the Stewardess's ID: ");
                    String idToDelete = scanner.next();
                    Iterator<Stewardess> iterator = stewardesses.iterator();
                    boolean removed = false;
                    while (iterator.hasNext()) {
                        Stewardess s = iterator.next();
                        if (s.getId().equals(idToDelete)) {
                            iterator.remove();
                            removed = true;
                            System.out.println("Stewardess removed: " + s.getId());
                        }
                    }
                    if (!removed) {
                        System.out.println("No Stewardess found with ID: " + idToDelete);
                    }
                    break;
                case 4:
                    System.out.print("\nEnter the Stewardess's ID: ");
                    String stwid = scanner.next();
                    for (Stewardess stw : stewardesses) {
                        if (stw.getId().equalsIgnoreCase(stwid)) {
                            System.out.println(stw.generatePaysheet());
                        }
                    }
                    break;
                case 0:
                    subMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}

package view.gui;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import model.auxiliary.Seat;
import model.auxiliary.Ticket;
import model.data.Airport;
import model.data.EngineType;
import model.data.FlightStatus;
import model.data.Gender;
import model.data.Model;
import model.data.Nationality;
import model.data.Operator;
import model.transportation.*;
import net.miginfocom.swing.*;
import model.utils.*;
/*
 * Created by JFormDesigner on Wed Feb 19 20:11:21 CET 2025
 */



/**
 * @author oriolvitmas
 */
public class Main extends JFrame {
	
	private ArrayList<Flight> Flights = new ArrayList<>();
	private ArrayList<Plane> Planes = new ArrayList<>();
	private ArrayList<Passenger> Passengers = new ArrayList<>();



    public Main() {
		Planes.add(new Plane(Model.AIRBUS_A330, 300, EngineType.TURBOJET, 120, 800, "1"));
		Flights.add(new Flight(Operator.AIR_CHINA, Planes.get(0), Airport.ATL, Airport.AMS, LocalDateTime.now(), 10.2, FlightStatus.ON_TIME, "ATLAMS001"));
		Ticket testTicket = new Ticket("ATLAMS001",true,true,"1");
		Passengers.add(new Passenger("Name", "Surname Surname", LocalDate.of(2005, 1, 1), Gender.FEMALE, Nationality.CUBAN, "11111111L", "example@example.com", "123123123", testTicket));
		initComponents();
		updateFlightTable();
		updatePassengerTable();
		updatePlaneTable();
	}

    public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatMacDarkLaf());
		} catch (UnsupportedLookAndFeelException e) {
			System.out.println("Failed to start theme");
		} // Set macOS Dark theme
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

	private void button1MouseClicked(MouseEvent e) {
		frame1.setVisible(true);
	}

	private void button3MouseClicked(MouseEvent e) {
		frame3.setVisible(true);
	}

	private void button2MouseClicked(MouseEvent e) {
		frame2.setVisible(true);
	}

	private void button4MouseClicked(MouseEvent e) {
		frame4.setVisible(true);
	}

	private void button7MouseClicked(MouseEvent e) { // Add new Flight
		JTextField operatorField = new JTextField();
		JTextField planeCodeField = new JTextField();
		JTextField airportOriginField = new JTextField();
		JTextField destinationAirportField = new JTextField();
		JTextField scheduledDepartureField = new JTextField();
		JTextField flightTimeField = new JTextField();
		JTextField statusField = new JTextField();
		JTextField flightCodeField = new JTextField();
	
		Object[] message = {
			"Flight Operator (e.g. Ryanair):", operatorField,
			"Plane Code:", planeCodeField,
			"Airport Origin (e.g. LAX)", airportOriginField,
			"Destination Airport (e.g. BCN):", destinationAirportField,
			"Scheduled Departure (dd/MM/yyyy HH:mm):", scheduledDepartureField,
			"Flight time:", flightTimeField,
			"Status (e.g. On Time):", statusField,
			"Flight Code:", flightCodeField,
		};
		
	
		int option = JOptionPane.showConfirmDialog(null, message, "Add New Flight", JOptionPane.OK_CANCEL_OPTION);
	
		if (option == JOptionPane.OK_OPTION) {
			try {
				Operator operator = Operator.valueOf(StringUtils.enumFormat(operatorField.getText()));
				String planeCode = planeCodeField.getText();
				Airport airportOrigin = Airport.valueOf(StringUtils.enumFormat(airportOriginField.getText()));
				Airport airportDestination = Airport.valueOf(StringUtils.enumFormat(destinationAirportField.getText()));
				DateTimeFormatter DTF_DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				LocalDateTime scheduledDeparture = LocalDateTime.parse(scheduledDepartureField.getText(), DTF_DATE_TIME);
				Double flightTime = Double.parseDouble(flightTimeField.getText());
				FlightStatus flightStatus = FlightStatus.valueOf(StringUtils.enumFormat(statusField.getText()));
				String flightCode = flightCodeField.getText();

				Plane plane = null;
				for (Plane planeObj : Planes) {
					if (planeObj.getPlaneCode().equals(planeCode)) {
						plane = planeObj;
					}
				}
				
				if (plane.equals(null)) {
					throw new Exception();
				}
				
				Flight flight = new Flight(operator, plane, airportOrigin, airportDestination, scheduledDeparture, flightTime, flightStatus, flightCode);
				Flights.add(flight);

				JOptionPane.showMessageDialog(null, "Flight added successfully!");
				
				updateFlightTable();
				// Refresh UI (if applicable)
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error: Invalid input. Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println(ex.toString());
				System.out.println(ex.getLocalizedMessage());
			}
		}
	}

	private void deleteFlight(MouseEvent e) {
		JTextField flightCodeField = new JTextField();
		Object[] message = {
			"Flight Code:", flightCodeField
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Delete Flight", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			try {
				boolean flightRemoved = false;
				Iterator<Flight> iterator = Flights.iterator();
				while (iterator.hasNext()) {
					Flight flight = iterator.next();
					if (flight.getFlightCode().equalsIgnoreCase(flightCodeField.getText().trim())) {
						iterator.remove();
						flightRemoved = true;
					}
				}
				if (flightRemoved) {
					JOptionPane.showMessageDialog(null, "Flight deleted successfully!");
					updateFlightTable();
				} else {
					JOptionPane.showMessageDialog(null, "Flight not found.");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error: Invalid input. Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void addPassenger(MouseEvent e) {
		JTextField nameField = new JTextField();
		JTextField surnamesField = new JTextField();
		JTextField birthDateField = new JTextField();
		JTextField genderField = new JTextField();
		JTextField nationalityField = new JTextField();
		JTextField idField = new JTextField();
		JTextField emailField = new JTextField();
		JTextField phoneNumberField = new JTextField();
		JTextField flightCodeField = new JTextField();
		JTextField checkkedBaggageField = new JTextField();
		JTextField handbagField = new JTextField();
		JTextField seatNumberField = new JTextField();
	
		Object[] message = {
			"First Name:", nameField,
			"Surnames:", surnamesField,
			"Birth Date (dd/MM/yyyy):", birthDateField,
			"Gender (e.g., MALE, FEMALE, OTHER):", genderField,
			"Nationality:", nationalityField,
			"ID (DNI/NIF):", idField,
			"Email:", emailField,
			"Phone Number:", phoneNumberField,
			"Flight Code:", flightCodeField,
			"Checked Baggage (true/false):", checkkedBaggageField,
			"Handbag (true/false):", handbagField,
			"Seat Number:", seatNumberField
		};
		
	
		int option = JOptionPane.showConfirmDialog(null, message, "Add New Plane", JOptionPane.OK_CANCEL_OPTION);
	
		if (option == JOptionPane.OK_OPTION) {
			try {
				String firstName = nameField.getText();
				String surnames = surnamesField.getText();
				DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate birthDate = LocalDate.parse(birthDateField.getText(),DTF);
				Gender gender = Gender.valueOf(StringUtils.enumFormat(genderField.getText()));
				Nationality nationality = Nationality.valueOf(StringUtils.enumFormat(nationalityField.getText()));
				String id = idField.getText();
				String email = emailField.getText();
				String phoneNumber = phoneNumberField.getText();
				String flightCode = flightCodeField.getText();
				Boolean checkedBaggage = Boolean.parseBoolean(checkkedBaggageField.getText());
				Boolean handbag = Boolean.parseBoolean(handbagField.getText());
				String seatNumber = seatNumberField.getText();
	
				// Create the new Plane object with the updated constructor
				Ticket ticket = new Ticket(flightCode, checkedBaggage, handbag, seatNumber);
				Passenger passenger = new Passenger(firstName, surnames, birthDate, gender, nationality, id, email, phoneNumber, ticket);
				Passengers.add(passenger);
				JOptionPane.showMessageDialog(null, "Plane added successfully!");
				
				updatePassengerTable();
				// Refresh UI (if applicable)
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error: Invalid input. Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void deletePassenger(MouseEvent e) {
		JTextField passengerIdField = new JTextField();
		Object[] message = {
			"Passenger ID:", passengerIdField
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Delete Passenger", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			try {
				boolean passengerRemoved = false;
				Iterator<Passenger> iterator = Passengers.iterator();
				while (iterator.hasNext()) {
					Passenger passenger = iterator.next();
					if (passenger.getId().equalsIgnoreCase(passengerIdField.getText().trim())) {
						iterator.remove();
						passengerRemoved = true;
					}
				}
				if (passengerRemoved) {
					JOptionPane.showMessageDialog(null, "Passenger deleted successfully!");
					updatePassengerTable();
				} else {
					JOptionPane.showMessageDialog(null, "Passenger not found.");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error: Invalid input. Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void checkSeats(MouseEvent e) {
		panel1.removeAll();
		panel1.revalidate();
		panel1.repaint();
		String inputtedFlightCode = textField1.getText();
		if (inputtedFlightCode.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Error: Invalid input. Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			for (Flight flight : Flights) { 
				if (inputtedFlightCode.trim().equals(flight.getFlightCode())) {
					System.out.println("Found flight");

					for (int i = 1; i < flight.getPlane().getSeats().size()+1; i++) {
						JButton seat = new JButton();
						Boolean isSeatTaken = false;
						String passengerName = "";
						for (Passenger passenger : Passengers) {
							if (passenger.getTicket().getSeatNumber().equalsIgnoreCase(String.valueOf(i))) {
								isSeatTaken = true;
								passengerName = passenger.getSurnames() + ", " + passenger.getName();
								if (!flight.getPassengers().contains(passenger)) {
									flight.addPassenger(passenger);
								}
								updateFlightTable();
							}
						}
						if (isSeatTaken) {
							if (flight.getPlane().getSeats().get(i-1).isOccupied()) {
								seat.setBackground(Color.RED);
								seat.setToolTipText("Seat " + i + "\nTaken by: " + passengerName + "\n" + StringUtils.capitalizeFirstLetter(flight.getPlane().getSeats().get(i-1).getSeatType().name()));
							} else {
								seat.setBackground(Color.decode("#FFCC00"));
								seat.setCursor(new Cursor(Cursor.HAND_CURSOR));
								seat.setToolTipText("Seat " + i + "\nUp for Comfirmation by: " + passengerName + "\n" + StringUtils.capitalizeFirstLetter(flight.getPlane().getSeats().get(i-1).getSeatType().name()));
								final int I = i;
								seat.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent ev) { 
										try {
											confirmSeat(I,flight);
											JOptionPane.showMessageDialog(null, "Confirmed seat Correctly.", "Success", JOptionPane.INFORMATION_MESSAGE);
											checkSeats(e);
										} catch (Exception exc) {
											JOptionPane.showMessageDialog(null, "Error Confirming Seat.", "Error", JOptionPane.ERROR_MESSAGE);
										}
									  }
								});
							}
							
							
						} else {
							switch (StringUtils.capitalizeFirstLetter(flight.getPlane().getSeats().get(i-1).getSeatType().name())) {
								case "Economy":
									seat.setBackground(Color.GREEN);
									break;
								case "Economy Premium":
									seat.setBackground(Color.decode("#005710"));
									break;
								case "Business":
									seat.setBackground(Color.decode("#0096D1"));
									break;
								case "First Class":
									seat.setBackground(Color.decode("#FF00BF"));
									break;
								default:
									seat.setBackground(Color.GREEN);
									break;
							}
							
							seat.setToolTipText("Seat " + i + "\nFree\n" + StringUtils.capitalizeFirstLetter(flight.getPlane().getSeats().get(i-1).getSeatType().name()));
						}
						
						panel1.add(seat);
					}

					for (Passenger passenger : Passengers) {
						if (passenger.getTicket().getFlightCode().equals(inputtedFlightCode.trim())) {
							System.out.println("Passenger " + passenger.getName() + " , " + passenger.getSurnames() + " has a ticket with seat code " + passenger.getTicket().getSeatNumber());
						}
					}
				}
			}
			panel1.revalidate();  // Refresh panel layout
			panel1.repaint();
		}
	}

	protected void confirmSeat(int i, Flight flight) {
		flight.getPlane().getSeats().get(i-1).setOccupied(true);
	}

	private void addPlane(MouseEvent e) {
		JTextField modelField = new JTextField();
		JTextField tankCapacityField = new JTextField();
		JTextField engineTypeField = new JTextField();
		JTextField capacityField = new JTextField();
		JTextField topSpeedField = new JTextField();
		JTextField planeCodeField = new JTextField();
	
		Object[] message = {
			"Model (e.g. Boeing 737):", modelField,
			"Tank Capacity:", tankCapacityField,
			"Engine Type (e.g., TURBOJET, TURBOSHAFT):", engineTypeField,
			"Capacity (No. People):", capacityField,
			"Top Speed (Km/h):", topSpeedField,
			"Plane Code:", planeCodeField
		};
	
		int option = JOptionPane.showConfirmDialog(null, message, "Add New Plane", JOptionPane.OK_CANCEL_OPTION);
	
		if (option == JOptionPane.OK_OPTION) {
			try {
				Model model = Model.valueOf(StringUtils.enumFormat(modelField.getText())); // Assuming Model has a constructor
				double tankCapacity = Double.parseDouble(tankCapacityField.getText());
				EngineType engineType = EngineType.valueOf(StringUtils.enumFormat(engineTypeField.getText())); // Assuming EngineType is an Enum
				int capacity = Integer.parseInt(capacityField.getText());
				double topSpeed = Double.parseDouble(topSpeedField.getText());
				String planeCode = planeCodeField.getText();
	
				// Create the new Plane object with the updated constructor
				Plane newPlane = new Plane(model, tankCapacity, engineType, capacity, topSpeed, planeCode);
				Planes.add(newPlane);
	
				JOptionPane.showMessageDialog(null, "Plane added successfully!");
				
				updatePlaneTable();
				// Refresh UI (if applicable)
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error: Invalid input. Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void updatePlaneTable() {
    // Clear existing table data
    DefaultTableModel model = (DefaultTableModel) table3.getModel();
	if (model == null) {
        System.out.println("Error: Table model is null");
        return;
    }
	table3.setVisible(true);
    model.setRowCount(0); // Remove all existing rows

    // Loop through the Planes list and add each plane's data to the table
    for (Plane plane : Planes) {
        Object[] rowData = {
            plane.getPlaneCode(),
            plane.getModel().name(),
            plane.getTankCapacity(),
            plane.getEngineType(),
            plane.getCapacity(),
            plane.getTopSpeed(),
            plane.getManufactureYear()
        };
		System.out.println(plane.toString());
        model.addRow(rowData);
    }
	model.fireTableDataChanged();
	table3.repaint();
}

	private void updateFlightTable() {
		// Clear existing table data
		DefaultTableModel model = (DefaultTableModel) table1.getModel();
		if (model == null) {
			System.out.println("Error: Table model is null");
			return;
		}
		table1.setVisible(true);
		model.setRowCount(0); // Remove all existing rows

		// Loop through the Planes list and add each plane's data to the table
		for (Flight flight : Flights) {
			Object[] rowData = {
				flight.getFlightCode(),
				flight.getOriginAirport().name(),
				flight.getDestinationAirport().name(),
				flight.getPlane().getPlaneCode(),
				flight.getPilot().getId(),
				flight.getOperator().getAirlineCode(),
				flight.getScheduledDeparture(),
				flight.getFlightTime(),
				flight.getStatus(),
				flight.getPassengers().size()
			};
			model.addRow(rowData);
		}
		model.fireTableDataChanged();
		table1.repaint();
	}

	private void updatePassengerTable() {
		// Clear existing table data
		DefaultTableModel model = (DefaultTableModel) table2.getModel();
		if (model == null) {
			System.out.println("Error: Table model is null");
			return;
		}
		table2.setVisible(true);
		model.setRowCount(0); // Remove all existing rows

		// Loop through the Planes list and add each plane's data to the table
		for (Passenger passenger : Passengers) {
			Object[] rowData = {
				passenger.getName(),
				passenger.getSurnames(),
				passenger.getBirthDate(),
				passenger.getGender().name(),
				passenger.getNationality().name(),
				passenger.getId(),
				passenger.getEmail(),
				passenger.getPhoneNumber(),
				passenger.getTicket().getFlightCode(),
				passenger.getTicket().getSeatNumber(),
			};
			model.addRow(rowData);
		}
		model.fireTableDataChanged();
		table2.repaint();
	}

	private void deletePlane(MouseEvent e) {
		JTextField planeCodeField = new JTextField();
		Object[] message = {
			"Plane Code:", planeCodeField
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Delete Plane", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			try {
				boolean planeRemoved = false;
				Iterator<Plane> iterator = Planes.iterator();
				while (iterator.hasNext()) {
					Plane plane = iterator.next();
					if (plane.getPlaneCode().equalsIgnoreCase(planeCodeField.getText().trim())) {
						iterator.remove();
						planeRemoved = true;
					}
				}
				if (planeRemoved) {
					JOptionPane.showMessageDialog(null, "Plane deleted successfully!");
					updatePlaneTable();
				} else {
					JOptionPane.showMessageDialog(null, "Plane not found.");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error: Invalid input. Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Iker
		label1 = new JLabel();
		button1 = new JButton();
		button2 = new JButton();
		button4 = new JButton();
		button3 = new JButton();
		label2 = new JLabel();
		label3 = new JLabel();
		frame1 = new JFrame();
		button7 = new JButton();
		button8 = new JButton();
		scrollPane1 = new JScrollPane();
		DefaultTableModel flightModel = new DefaultTableModel(new Object[]{"Flight Code", "Origin", "Destination", "Plane Code", "Pilot Id", "Operator", "Scheduled Departure", "Flight Time", "Status" ,"Passenger Number"}, 0
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Makes all cells non-editable
			};
		};
		table1 = new JTable(flightModel);
		table1.setCellSelectionEnabled(false);
		frame2 = new JFrame();
		button9 = new JButton();
		button10 = new JButton();
		scrollPane2 = new JScrollPane();
		DefaultTableModel passengerModel = new DefaultTableModel(new Object[]{"Name", "Surnames", "Birth Date", "Gender", "Nationality", "Id", "Email", "Phone Number", "Ticket Code" ,"Seat Number"}, 0
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Makes all cells non-editable
			};
		};
		table2 = new JTable(passengerModel);
		table2.setCellSelectionEnabled(false);
		frame3 = new JFrame();
		textField1 = new JTextField();
		label4 = new JLabel();
		panel1 = new JPanel();
		button13 = new JButton();
		frame4 = new JFrame();
		button11 = new JButton();
		button12 = new JButton();
		DefaultTableModel planeModel = new DefaultTableModel(new Object[]{"Plane Code", "Model", "Tank Capacity", "Engine Type", "Capacity", "Top Speed", "Year"}, 0
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Makes all cells non-editable
			};
		};
		table3 = new JTable(planeModel);
		table3.setCellSelectionEnabled(false);
		scrollPane3 = new JScrollPane(table3);

		//======== this ========
		setIconImage(new ImageIcon("C:\\Users\\oriolvitmas\\Downloads\\icon.png").getImage());
		setMinimumSize(new Dimension(700, 395));
		setTitle("SkyFlow GUI");
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
			"hidemode 3,align center center",
			// columns
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]",
			// rows
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]"));

		//---- label1 ----
		label1.setText("Welcome.");
		label1.setHorizontalTextPosition(SwingConstants.CENTER);
		contentPane.add(label1, "cell 7 0,alignx center,growx 0");

		//---- button1 ----
		button1.setText("Flights");
		button1.setFont(new Font("Candara", Font.PLAIN, 12));
		button1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button1.setToolTipText("Check, Add and Delete Flights");
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button1MouseClicked(e);
			}
		});
		contentPane.add(button1, "cell 7 3");

		//---- button2 ----
		button2.setText("Passengers");
		button2.setFont(new Font("Candara", Font.PLAIN, 12));
		button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button2.setToolTipText("Check, Add and Delete Passengers");
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button2MouseClicked(e);
			}
		});
		contentPane.add(button2, "cell 7 5");

		//---- button4 ----
		button4.setText("Planes");
		button4.setFont(new Font("Candara", Font.PLAIN, 12));
		button4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button4.setToolTipText("Check, Add and Delete Passengers");
		button4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button4MouseClicked(e);
			}
		});
		contentPane.add(button4, "cell 7 7");

		//---- button3 ----
		button3.setText("Check Seat Occupation");
		button3.setFont(new Font("Candara", Font.PLAIN, 12));
		button3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button3.setToolTipText("Visually Check Seat Occupation in a Flight");
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button3MouseClicked(e);
			}
		});
		contentPane.add(button3, "cell 7 9");

		//---- label2 ----
		label2.setText("\u00a9 SkyFlow");
		label2.setForeground(new Color(0x616161));
		contentPane.add(label2, "cell 7 11,alignx center,growx 0");

		//---- label3 ----
		label3.setText("2025");
		label3.setForeground(new Color(0x616161));
		contentPane.add(label3, "cell 7 12,alignx center,growx 0");
		pack();
		setLocationRelativeTo(getOwner());

		//======== frame1 ========
		{
			frame1.setMinimumSize(new Dimension(700, 395));
			frame1.setPreferredSize(null);
			frame1.setTitle("Flights");
			Container frame1ContentPane = frame1.getContentPane();

			//---- button7 ----
			button7.setText("Add New");
			button7.setAlignmentX(0.5F);
			button7.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					button7MouseClicked(e);
				}
			});

			//---- button8 ----
			button8.setText("Delete");
			button8.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					deleteFlight(e);
				}
			});

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(table1);
			}

			GroupLayout frame1ContentPaneLayout = new GroupLayout(frame1ContentPane);
			frame1ContentPane.setLayout(frame1ContentPaneLayout);
			frame1ContentPaneLayout.setHorizontalGroup(
				frame1ContentPaneLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, frame1ContentPaneLayout.createSequentialGroup()
						.addGap(44, 44, 44)
						.addGroup(frame1ContentPaneLayout.createParallelGroup()
							.addGroup(frame1ContentPaneLayout.createSequentialGroup()
								.addComponent(button7)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(button8))
							.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
						.addGap(44, 44, 44))
			);
			frame1ContentPaneLayout.setVerticalGroup(
				frame1ContentPaneLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, frame1ContentPaneLayout.createSequentialGroup()
						.addContainerGap(27, Short.MAX_VALUE)
						.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(frame1ContentPaneLayout.createParallelGroup()
							.addComponent(button8)
							.addComponent(button7))
						.addContainerGap())
			);
			frame1.pack();
			frame1.setLocationRelativeTo(frame1.getOwner());
		}

		//======== frame2 ========
		{
			frame2.setMinimumSize(new Dimension(700, 395));
			frame2.setPreferredSize(null);
			frame2.setTitle("Passengers");
			Container frame2ContentPane = frame2.getContentPane();

			//---- button9 ----
			button9.setText("Add New");
			button9.setAlignmentX(0.5F);
			button9.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					addPassenger(e);
				}
			});

			//---- button10 ----
			button10.setText("Delete");
			button10.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					deletePassenger(e);
				}
			});

			//======== scrollPane2 ========
			{
				scrollPane2.setViewportView(table2);
			}

			GroupLayout frame2ContentPaneLayout = new GroupLayout(frame2ContentPane);
			frame2ContentPane.setLayout(frame2ContentPaneLayout);
			frame2ContentPaneLayout.setHorizontalGroup(
				frame2ContentPaneLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, frame2ContentPaneLayout.createSequentialGroup()
						.addGap(44, 44, 44)
						.addGroup(frame2ContentPaneLayout.createParallelGroup()
							.addGroup(frame2ContentPaneLayout.createSequentialGroup()
								.addComponent(button9)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(button10))
							.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
						.addGap(44, 44, 44))
			);
			frame2ContentPaneLayout.setVerticalGroup(
				frame2ContentPaneLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, frame2ContentPaneLayout.createSequentialGroup()
						.addContainerGap(27, Short.MAX_VALUE)
						.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(frame2ContentPaneLayout.createParallelGroup()
							.addComponent(button10)
							.addComponent(button9))
						.addContainerGap())
			);
			frame2.pack();
			frame2.setLocationRelativeTo(frame2.getOwner());
		}

		//======== frame3 ========
		{
			frame3.setMinimumSize(new Dimension(700, 395));
			frame3.setPreferredSize(null);
			frame3.setTitle("Seats");
			Container frame3ContentPane = frame3.getContentPane();

			//---- label4 ----
			label4.setText("Flight Code");

			//======== panel1 ========
			{
				panel1.setBackground(new Color(0x2b2b2b));
				panel1.setBorder (null);
				panel1.setLayout(new GridLayout(0, 7, 10, 5));
			}

			//---- button13 ----
			button13.setText("Check");
			button13.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					checkSeats(e);
				}
			});

			GroupLayout frame3ContentPaneLayout = new GroupLayout(frame3ContentPane);
			frame3ContentPane.setLayout(frame3ContentPaneLayout);
			frame3ContentPaneLayout.setHorizontalGroup(
				frame3ContentPaneLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, frame3ContentPaneLayout.createSequentialGroup()
						.addContainerGap(50, Short.MAX_VALUE)
						.addGroup(frame3ContentPaneLayout.createParallelGroup()
							.addGroup(frame3ContentPaneLayout.createSequentialGroup()
								.addComponent(label4)
								.addContainerGap(587, Short.MAX_VALUE))
							.addGroup(frame3ContentPaneLayout.createSequentialGroup()
								.addGroup(frame3ContentPaneLayout.createParallelGroup()
									.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
									.addComponent(button13))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
								.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
								.addGap(85, 85, 85))))
			);
			frame3ContentPaneLayout.setVerticalGroup(
				frame3ContentPaneLayout.createParallelGroup()
					.addGroup(frame3ContentPaneLayout.createSequentialGroup()
						.addGap(14, 14, 14)
						.addComponent(label4)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(frame3ContentPaneLayout.createParallelGroup()
							.addGroup(frame3ContentPaneLayout.createSequentialGroup()
								.addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(button13))
							.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(27, Short.MAX_VALUE))
			);
			frame3.pack();
			frame3.setLocationRelativeTo(frame3.getOwner());
		}

		//======== frame4 ========
		{
			frame4.setMinimumSize(new Dimension(700, 395));
			frame4.setPreferredSize(null);
			frame4.setTitle("Planes");
			Container frame4ContentPane = frame4.getContentPane();

			//---- button11 ----
			button11.setText("Add New");
			button11.setAlignmentX(0.5F);
			button11.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					addPlane(e);
				}
			});

			//---- button12 ----
			button12.setText("Delete");
			button12.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					deletePlane(e);
				}
			});

			//======== scrollPane3 ========
			{
				scrollPane3.setViewportView(table3);
			}

			GroupLayout frame4ContentPaneLayout = new GroupLayout(frame4ContentPane);
			frame4ContentPane.setLayout(frame4ContentPaneLayout);
			frame4ContentPaneLayout.setHorizontalGroup(
				frame4ContentPaneLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, frame4ContentPaneLayout.createSequentialGroup()
						.addGap(44, 44, 44)
						.addGroup(frame4ContentPaneLayout.createParallelGroup()
							.addGroup(frame4ContentPaneLayout.createSequentialGroup()
								.addComponent(button11)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(button12))
							.addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
						.addGap(44, 44, 44))
			);
			frame4ContentPaneLayout.setVerticalGroup(
				frame4ContentPaneLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, frame4ContentPaneLayout.createSequentialGroup()
						.addContainerGap(27, Short.MAX_VALUE)
						.addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(frame4ContentPaneLayout.createParallelGroup()
							.addComponent(button12)
							.addComponent(button11))
						.addContainerGap())
			);
			frame4.pack();
			frame4.setLocationRelativeTo(frame4.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Iker
	private JLabel label1;
	private JButton button1;
	private JButton button2;
	private JButton button4;
	private JButton button3;
	private JLabel label2;
	private JLabel label3;
	private JFrame frame1;
	private JButton button7;
	private JButton button8;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JFrame frame2;
	private JButton button9;
	private JButton button10;
	private JScrollPane scrollPane2;
	private JTable table2;
	private JFrame frame3;
	private JTextField textField1;
	private JLabel label4;
	private JPanel panel1;
	private JButton button13;
	private JFrame frame4;
	private JButton button11;
	private JButton button12;
	private JScrollPane scrollPane3;
	private JTable table3;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

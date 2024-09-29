package org.saddy.parking;

import org.saddy.vehicles.*;

import javax.swing.*;

public class ParkingLotGUI {
    private JFrame frame;
    private ParkingLot parkingLot;
    private JTextField smallSpotsField;
    private JTextField mediumSpotsField;
    private JTextField largeSpotsField;
    private JTextField vehicleLicenseField;
    private JComboBox<String> vehicleTypeCombo;
    private JLabel availableSmallSpots;
    private JLabel availableMediumSpots;
    private JLabel availableLargeSpots;

    public ParkingLotGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Parking Lot System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Create components
        JLabel lblSmallSpots = new JLabel("Small Spots:");
        smallSpotsField = new JTextField(10);

        JLabel lblMediumSpots = new JLabel("Medium Spots:");
        mediumSpotsField = new JTextField(10);

        JLabel lblLargeSpots = new JLabel("Large Spots:");
        largeSpotsField = new JTextField(10);

        JButton btnCreateParkingLot = new JButton("Create Parking Lot");

        JLabel lblLicensePlate = new JLabel("License Plate:");
        vehicleLicenseField = new JTextField(10);

        JLabel lblVehicleType = new JLabel("Vehicle Type:");
        vehicleTypeCombo = new JComboBox<>(new String[]{"Small", "Medium", "Large"});

        JButton btnParkVehicle = new JButton("Park Vehicle");
        JButton btnRemoveVehicle = new JButton("Remove Vehicle");

        availableSmallSpots = new JLabel("Small spots available: 0");
        availableMediumSpots = new JLabel("Medium spots available: 0");
        availableLargeSpots = new JLabel("Large spots available: 0");

        // Add action listeners
        btnCreateParkingLot.addActionListener(e -> createParkingLot());
        btnParkVehicle.addActionListener(e -> parkVehicle());
        btnRemoveVehicle.addActionListener(e -> removeVehicle());

        // Set up layout
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Horizontal layout
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblSmallSpots)
                        .addComponent(lblMediumSpots)
                        .addComponent(lblLargeSpots)
                        .addComponent(lblLicensePlate)
                        .addComponent(lblVehicleType)
                        .addComponent(btnParkVehicle)
                        .addComponent(btnRemoveVehicle)
                        .addComponent(btnCreateParkingLot))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(smallSpotsField)
                        .addComponent(mediumSpotsField)
                        .addComponent(largeSpotsField)
                        .addComponent(vehicleLicenseField)
                        .addComponent(vehicleTypeCombo)
                        .addComponent(availableSmallSpots)
                        .addComponent(availableMediumSpots)
                        .addComponent(availableLargeSpots))
        );

        // Vertical layout
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSmallSpots)
                        .addComponent(smallSpotsField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMediumSpots)
                        .addComponent(mediumSpotsField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLargeSpots)
                        .addComponent(largeSpotsField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCreateParkingLot))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLicensePlate)
                        .addComponent(vehicleLicenseField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblVehicleType)
                        .addComponent(vehicleTypeCombo))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(availableSmallSpots))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(availableMediumSpots))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(availableLargeSpots))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnParkVehicle))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRemoveVehicle))
        );

        frame.pack();
        frame.setVisible(true);
    }

    private void createParkingLot() {
        int smallSpots = Integer.parseInt(smallSpotsField.getText());
        int mediumSpots = Integer.parseInt(mediumSpotsField.getText());
        int largeSpots = Integer.parseInt(largeSpotsField.getText());

        parkingLot = new ParkingLot(smallSpots, mediumSpots, largeSpots);
        JOptionPane.showMessageDialog(frame, "Parking Lot Created!");
        updateAvailabilityDisplay();
    }

    private void parkVehicle() {
        if (parkingLot == null) {
            JOptionPane.showMessageDialog(frame, "Create a parking lot first.");
            return;
        }

        String licensePlate = vehicleLicenseField.getText();
        String vehicleType = (String) vehicleTypeCombo.getSelectedItem();
        Vehicle vehicle;

        switch (vehicleType) {
            case "Small":
                vehicle = new SmallCar(licensePlate);
                break;
            case "Medium":
                vehicle = new MediumCar(licensePlate);
                break;
            case "Large":
                vehicle = new LargeCar(licensePlate);
                break;
            default:
                throw new IllegalArgumentException("Invalid vehicle type.");
        }

        boolean parked = parkingLot.parkVehicle(vehicle);

        if (parked) {
            JOptionPane.showMessageDialog(frame, "Vehicle parked successfully.");
        } else {
            JOptionPane.showMessageDialog(frame, "No available spots for this vehicle.");
        }

        updateAvailabilityDisplay();
    }

    private void removeVehicle() {
        if (parkingLot == null) {
            JOptionPane.showMessageDialog(frame, "Create a parking lot first.");
            return;
        }

        String licensePlate = vehicleLicenseField.getText().trim();
        boolean removed = parkingLot.removeVehicle(licensePlate);

        if (removed) {
            JOptionPane.showMessageDialog(frame, "Vehicle removed successfully.");
        } else {
            JOptionPane.showMessageDialog(frame, "Vehicle not found.");
        }

        updateAvailabilityDisplay();
    }

    private void updateAvailabilityDisplay() {
        availableSmallSpots.setText("Small spots available: " + parkingLot.countAvailableSpots(VehicleSize.SMALL));
        availableMediumSpots.setText("Medium spots available: " + parkingLot.countAvailableSpots(VehicleSize.MEDIUM));
        availableLargeSpots.setText("Large spots available: " + parkingLot.countAvailableSpots(VehicleSize.LARGE));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ParkingLotGUI::new);
    }
}

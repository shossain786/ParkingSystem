package org.saddy.parking;

import org.saddy.vehicles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        frame = new JFrame();
        frame.setTitle("Parking Lot System");
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblSmallSpots = new JLabel("Small Spots:");
        lblSmallSpots.setBounds(10, 20, 80, 25);
        frame.getContentPane().add(lblSmallSpots);

        smallSpotsField = new JTextField();
        smallSpotsField.setBounds(100, 20, 50, 25);
        frame.getContentPane().add(smallSpotsField);
        smallSpotsField.setColumns(10);

        JLabel lblMediumSpots = new JLabel("Medium Spots:");
        lblMediumSpots.setBounds(10, 50, 80, 25);
        frame.getContentPane().add(lblMediumSpots);

        mediumSpotsField = new JTextField();
        mediumSpotsField.setBounds(100, 50, 50, 25);
        frame.getContentPane().add(mediumSpotsField);
        mediumSpotsField.setColumns(10);

        JLabel lblLargeSpots = new JLabel("Large Spots:");
        lblLargeSpots.setBounds(10, 80, 80, 25);
        frame.getContentPane().add(lblLargeSpots);

        largeSpotsField = new JTextField();
        largeSpotsField.setBounds(100, 80, 50, 25);
        frame.getContentPane().add(largeSpotsField);
        largeSpotsField.setColumns(10);

        JButton btnCreateParkingLot = new JButton("Create Parking Lot");
        btnCreateParkingLot.setBounds(10, 120, 200, 30);
        frame.getContentPane().add(btnCreateParkingLot);

        btnCreateParkingLot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createParkingLot();
            }
        });

        JLabel lblLicensePlate = new JLabel("License Plate:");
        lblLicensePlate.setBounds(10, 160, 80, 25);
        frame.getContentPane().add(lblLicensePlate);

        vehicleLicenseField = new JTextField();
        vehicleLicenseField.setBounds(100, 160, 150, 25);
        frame.getContentPane().add(vehicleLicenseField);
        vehicleLicenseField.setColumns(10);

        JLabel lblVehicleType = new JLabel("Vehicle Type:");
        lblVehicleType.setBounds(10, 190, 80, 25);
        frame.getContentPane().add(lblVehicleType);

        vehicleTypeCombo = new JComboBox<>();
        vehicleTypeCombo.setModel(new DefaultComboBoxModel<>(new String[]{"Small", "Medium", "Large"}));
        vehicleTypeCombo.setBounds(100, 190, 100, 25);
        frame.getContentPane().add(vehicleTypeCombo);

        JButton btnParkVehicle = new JButton("Park Vehicle");
        btnParkVehicle.setBounds(10, 230, 150, 30);
        frame.getContentPane().add(btnParkVehicle);

        btnParkVehicle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parkVehicle();
            }
        });

        JButton btnRemoveVehicle = new JButton("Remove Vehicle");
        btnRemoveVehicle.setBounds(10, 270, 150, 30);
        frame.getContentPane().add(btnRemoveVehicle);

        btnRemoveVehicle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeVehicle();
            }
        });

        availableSmallSpots = new JLabel("Small spots available: 0");
        availableSmallSpots.setBounds(200, 160, 200, 25);
        frame.getContentPane().add(availableSmallSpots);

        availableMediumSpots = new JLabel("Medium spots available: 0");
        availableMediumSpots.setBounds(200, 190, 200, 25);
        frame.getContentPane().add(availableMediumSpots);

        availableLargeSpots = new JLabel("Large spots available: 0");
        availableLargeSpots.setBounds(200, 220, 200, 25);
        frame.getContentPane().add(availableLargeSpots);

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

        String licensePlate = vehicleLicenseField.getText().trim(); // Trim to avoid leading/trailing spaces
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
        SwingUtilities.invokeLater(() -> new ParkingLotGUI());
    }
}

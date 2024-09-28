package org.saddy.parking;

import javax.swing.*;

public class ParkingSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ParkingLotGUI());
    }

}

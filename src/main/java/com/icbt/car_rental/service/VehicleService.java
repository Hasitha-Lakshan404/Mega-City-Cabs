package com.icbt.car_rental.service;

import com.icbt.car_rental.model.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface VehicleService {
    void addVehicle(Vehicle vehicle) throws SQLException;

    List<Vehicle> getAllVehicles() throws SQLException;

    void updateVehicle(Vehicle updateVehicle) throws SQLException;

    void deleteVehicle(int vehicleId);
}

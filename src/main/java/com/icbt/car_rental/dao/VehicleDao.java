package com.icbt.car_rental.dao;

import com.icbt.car_rental.model.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDao {
    void addVehicle(Vehicle vehicle) throws SQLException;
    List<Vehicle> getAllVehicles(boolean isOnlyAvailableVehicles) throws SQLException;

    void updateVehicle(Vehicle vehicle) throws SQLException;

    void deleteVehicle(int vehicleId);

    public Vehicle getVehicleById(long vehicleId) throws SQLException;
}
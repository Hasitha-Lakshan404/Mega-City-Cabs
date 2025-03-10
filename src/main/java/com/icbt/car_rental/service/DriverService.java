package com.icbt.car_rental.service;

import com.icbt.car_rental.model.Driver;

import java.sql.SQLException;
import java.util.List;

public interface DriverService {
    void addDriver(Driver driver) throws SQLException;

    List<Driver> getAllDrivers(boolean isOnlyAvailableDrivers) throws SQLException;

    void updateDriver(Driver driver) throws SQLException;

    void deleteDriver(int driverId);

    public Driver getDriverById(long driverId) throws SQLException;
}

package com.icbt.car_rental.service.impl;

import com.icbt.car_rental.dao.DriverDao;
import com.icbt.car_rental.dao.impl.DriverDaoImpl;
import com.icbt.car_rental.model.Driver;
import com.icbt.car_rental.service.DriverService;

import java.sql.SQLException;
import java.util.List;

public class DriverServiceImpl implements DriverService {

    private DriverDao driverDao = new DriverDaoImpl();

    @Override
    public void addDriver(Driver driver) throws SQLException {
        driverDao.addDriver(driver);
    }

    @Override
    public List<Driver> getAllDrivers(boolean isOnlyAvailableDrivers) throws SQLException {
        return driverDao.getAllDrivers(isOnlyAvailableDrivers);
    }

    @Override
    public void updateDriver(Driver driver) throws SQLException {
        driverDao.updateDriver(driver);
    }

    @Override
    public void deleteDriver(int driverId) {
        driverDao.deleteDriver(driverId);
    }

    @Override
    public Driver getDriverById(long driverId) throws SQLException {
        return driverDao.getDriverById(driverId);
    }
}

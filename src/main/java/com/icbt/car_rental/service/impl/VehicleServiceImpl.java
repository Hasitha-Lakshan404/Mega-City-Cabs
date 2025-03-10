package com.icbt.car_rental.service.impl;

import com.icbt.car_rental.dao.VehicleDao;
import com.icbt.car_rental.dao.impl.VehicleDaoImpl;
import com.icbt.car_rental.model.Vehicle;
import com.icbt.car_rental.service.VehicleService;
import com.icbt.car_rental.service.VehicleService;

import java.sql.SQLException;
import java.util.List;

public class VehicleServiceImpl implements VehicleService {

    private final VehicleDao vehicleDao = new VehicleDaoImpl();

    @Override
    public void addVehicle(Vehicle customer) throws SQLException {
        vehicleDao.addVehicle(customer);
    }

    @Override
    public List<Vehicle> getAllVehicles(boolean isOnlyAvailableVehicles) throws SQLException {
        return vehicleDao.getAllVehicles(isOnlyAvailableVehicles);
    }

    @Override
    public void updateVehicle(Vehicle customer) throws SQLException {
        vehicleDao.updateVehicle(customer);
    }

    @Override
    public void deleteVehicle(int customerId) {
        vehicleDao.deleteVehicle(customerId);
    }

    @Override
    public Vehicle getVehicleById(long vehicleId) throws SQLException {
        return vehicleDao.getVehicleById(vehicleId);
    }
}

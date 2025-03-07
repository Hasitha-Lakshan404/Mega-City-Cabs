package com.icbt.car_rental.service.impl;

import com.icbt.car_rental.dao.VehicleDao;
import com.icbt.car_rental.dao.impl.VehicleDaoImpl;
import com.icbt.car_rental.model.Vehicle;
import com.icbt.car_rental.service.VehicleService;
import com.icbt.car_rental.service.VehicleService;

import java.sql.SQLException;
import java.util.List;

public class VehicleServiceImpl implements VehicleService {

    private final VehicleDao customerDao = new VehicleDaoImpl();

    @Override
    public void createVehicle(Vehicle customer) throws SQLException {
        customerDao.addVehicle(customer);
    }

    @Override
    public List<Vehicle> getAllVehicles() throws SQLException {
        return customerDao.getAllVehicles();
    }

    @Override
    public void updateVehicle(Vehicle customer) throws SQLException {
        customerDao.updateVehicle(customer);
    }

    @Override
    public void deleteVehicle(int customerId) {
        customerDao.deleteVehicle(customerId);
    }
}

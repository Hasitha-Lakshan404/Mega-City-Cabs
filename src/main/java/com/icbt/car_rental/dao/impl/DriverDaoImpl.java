package com.icbt.car_rental.dao.impl;

import com.icbt.car_rental.dao.DriverDao;
import com.icbt.car_rental.model.Driver;
import com.icbt.car_rental.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDaoImpl implements DriverDao {

    @Override
    public void addDriver(Driver driver) throws SQLException {

    }

    @Override
    public List<Driver> getAllDrivers(boolean isOnlyAvailableDrivers) throws SQLException {
        List<Driver> driverList = new ArrayList<>();
        String sql;

        if (isOnlyAvailableDrivers) {
            sql = "SELECT * FROM driver WHERE status = 'Available' ORDER BY id DESC";
        } else {
            sql = "SELECT * FROM driver ORDER BY id DESC";
        }

        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                driverList.add(new Driver(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("nic"),
                        rs.getString("address"),
                        rs.getString("contactNo"),
                        rs.getString("status")
                ));
            }
        }
        return driverList;
    }

    @Override
    public void updateDriver(Driver driver) throws SQLException {
        String sql = "UPDATE driver SET name = ?,nic = ?, address = ?, contactNo = ?, status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, driver.getName());
            pstmt.setString(2, driver.getNic());
            pstmt.setString(3, driver.getAddress());
            pstmt.setString(4, driver.getContactNo());
            pstmt.setString(5, driver.getStatus());
            pstmt.setLong(6, driver.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteDriver(int driverId) {

    }

    @Override
    public Driver getDriverById(long driverId) throws SQLException {
        Driver driver = null;
        String sql = "SELECT * FROM driver WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, driverId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    driver = new Driver(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("nic"),
                            rs.getString("address"),
                            rs.getString("contactNo"),
                            rs.getString("status"));
                }
            }
        }
        return driver;
    }
}

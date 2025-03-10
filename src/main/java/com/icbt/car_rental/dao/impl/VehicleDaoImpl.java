package com.icbt.car_rental.dao.impl;

import com.icbt.car_rental.dao.VehicleDao;
import com.icbt.car_rental.model.Vehicle;
import com.icbt.car_rental.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoImpl implements VehicleDao {

    @Override
    public void addVehicle(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO vehicle (brand, model, variant,year,fuelType,seatingCapacity,rentPerKm,currentMeeterReading,status) VALUES (?, ?, ?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehicle.getBrand());
            pstmt.setString(2, vehicle.getModel());
            pstmt.setString(3, vehicle.getVariant());
            pstmt.setInt(4, vehicle.getYear());
            pstmt.setString(5, vehicle.getFuelType());
            pstmt.setInt(6, vehicle.getSeatingCapacity());
            pstmt.setBigDecimal(7, vehicle.getRentPerKm());
            pstmt.setDouble(8, vehicle.getCurrentMeeterReading());
            pstmt.setString(9, vehicle.getStatus());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Vehicle> getAllVehicles(boolean isOnlyAvailableVehicles) throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql;

        if (isOnlyAvailableVehicles) {
            sql = "SELECT * FROM vehicle WHERE status = 'Available' ORDER BY id DESC";
        }else {
            sql = "SELECT * FROM vehicle ORDER BY id DESC";
        }

        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                vehicles.add(new Vehicle(
                        rs.getLong("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("variant"),
                        rs.getInt("year"),
                        rs.getString("fuelType"),
                        rs.getInt("seatingCapacity"),
                        rs.getBigDecimal("rentPerKm"),
                        rs.getDouble("currentMeeterReading"),
                        rs.getString("status")
                ));
            }
        }
        return vehicles;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) throws SQLException {
        String sql = "UPDATE vehicle SET brand = ?, model = ?, variant = ?,year = ?,fuelType = ?,seatingCapacity = ?,rentPerKm = ?,currentMeeterReading=?,status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehicle.getBrand());
            pstmt.setString(2, vehicle.getModel());
            pstmt.setString(3, vehicle.getVariant());
            pstmt.setInt(4, vehicle.getYear());
            pstmt.setString(5, vehicle.getFuelType());
            pstmt.setInt(6, vehicle.getSeatingCapacity());
            pstmt.setBigDecimal(7, vehicle.getRentPerKm());
            pstmt.setDouble(8, vehicle.getCurrentMeeterReading());
            pstmt.setString(9, vehicle.getStatus());
            pstmt.setLong(10, vehicle.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteVehicle(int vehicleId) {
        String sql = "DELETE FROM vehicle WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, vehicleId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vehicle getVehicleById(long vehicleId) throws SQLException {
        Vehicle vehicle = null;
        String sql = "SELECT * FROM vehicle WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, vehicleId);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    vehicle = new Vehicle(
                            rs.getLong("id"),
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getString("variant"),
                            rs.getInt("year"),
                            rs.getString("fuelType"),
                            rs.getInt("seatingCapacity"),
                            rs.getBigDecimal("rentPerKm"),
                            rs.getDouble("currentMeeterReading"),
                            rs.getString("status"));
                }
            }
        }
        return vehicle;
    }
}

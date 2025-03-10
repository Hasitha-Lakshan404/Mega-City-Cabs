package com.icbt.car_rental.dao.impl;

import com.icbt.car_rental.dao.BookingDetailsDao;
import com.icbt.car_rental.model.BookingDetails;
import com.icbt.car_rental.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDetailsDaoImpl implements BookingDetailsDao {

    @Override
    public void addBookingDetails(BookingDetails bookingDetails) throws SQLException {
        String sql = "INSERT INTO Booking_details (bookingId, vehicleId, driverId, beforeMeeterReading, afterMeeterReading) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, bookingDetails.getBookingId());
            ps.setLong(2, bookingDetails.getVehicleId());
            if (bookingDetails.getDriverId() != 0) {
                ps.setLong(3, bookingDetails.getDriverId());
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            ps.setDouble(4, bookingDetails.getBeforeMeeterReading());
            ps.setDouble(5, bookingDetails.getAfterMeeterReading());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    bookingDetails.setBookingDetailId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public BookingDetails getBookingDetailsById(int bookingDetailId) throws SQLException {
        BookingDetails details = null;
        String sql = "SELECT * FROM Booking_details WHERE bookingDetailId = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookingDetailId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    details = new BookingDetails();
                    details.setBookingDetailId(rs.getInt("bookingDetailId"));
                    details.setBookingId(rs.getInt("bookingId"));
                    details.setVehicleId(rs.getInt("vehicleId"));
                    int driverId = rs.getInt("driverId");
                    details.setDriverId(rs.wasNull() ? null : driverId);
                    details.setBeforeMeeterReading(rs.getDouble("beforeMeeterReading"));
                    details.setAfterMeeterReading(rs.getDouble("afterMeeterReading"));
                }
            }
        }
        return details;
    }

    @Override
    public List<BookingDetails> getAllBookingDetails() throws SQLException {
        List<BookingDetails> detailsList = new ArrayList<>();
        String sql = "SELECT * FROM Booking_details";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                BookingDetails details = new BookingDetails();
                details.setBookingDetailId(rs.getInt("bookingDetailId"));
                details.setBookingId(rs.getInt("bookingId"));
                details.setVehicleId(rs.getInt("vehicleId"));
                int driverId = rs.getInt("driverId");
                details.setDriverId(rs.wasNull() ? null : driverId);
                details.setBeforeMeeterReading(rs.getDouble("beforeMeeterReading"));
                details.setAfterMeeterReading(rs.getDouble("afterMeeterReading"));
                detailsList.add(details);
            }
        }
        return detailsList;
    }

    @Override
    public void updateBookingDetails(BookingDetails bookingDetails) throws SQLException {
        String sql = "UPDATE Booking_details SET bookingId=?, vehicleId=?, driverId=?, beforeMeeterReading=?, afterMeeterReading=? WHERE bookingDetailId=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, bookingDetails.getBookingId());
            ps.setLong(2, bookingDetails.getVehicleId());
            if (bookingDetails.getDriverId() != 0) {
                ps.setLong(3, bookingDetails.getDriverId());
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            ps.setDouble(4, bookingDetails.getBeforeMeeterReading());
            ps.setDouble(5, bookingDetails.getAfterMeeterReading());
            ps.setLong(6, bookingDetails.getBookingDetailId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteBookingDetails(int bookingDetailId) throws SQLException {
        String sql = "DELETE FROM Booking_details WHERE bookingDetailId = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookingDetailId);
            ps.executeUpdate();
        }
    }
}
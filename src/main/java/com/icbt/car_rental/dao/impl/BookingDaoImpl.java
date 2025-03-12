package com.icbt.car_rental.dao.impl;

import com.icbt.car_rental.dao.BookingDao;
import com.icbt.car_rental.model.Booking;
import com.icbt.car_rental.model.BookingDetails;
import com.icbt.car_rental.model.dto.GetAllBookingDTO;
import com.icbt.car_rental.util.DBConnection;

import java.sql.*;
import java.util.*;

public class BookingDaoImpl implements BookingDao {

    @Override
    public Booking addBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO Booking (customerId,paymentId, bookingDate, pickupDate, returnDate, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, booking.getCustomerId());
            ps.setLong(2, booking.getPaymentId());
            ps.setDate(3, booking.getBookingDate());
            ps.setDate(4, booking.getPickupDate());
            ps.setDate(5, booking.getReturnDate());
            ps.setString(6, booking.getStatus());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    booking.setBookingId(rs.getInt(1));
                }
            }
        }
        return booking;
    }

    @Override
    public Booking getBookingById(int bookingId) throws SQLException {
        Booking booking = null;
        String sql = "SELECT * FROM Booking WHERE bookingId = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookingId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    booking = new Booking();
                    booking.setBookingId(rs.getInt("bookingId"));
                    booking.setCustomerId(rs.getInt("customerId"));
                    booking.setPaymentId(rs.getInt("paymentId"));
                    booking.setBookingDate(rs.getDate("bookingDate"));
                    booking.setPickupDate(rs.getDate("pickupDate"));
                    booking.setReturnDate(rs.getDate("returnDate"));
                    booking.setStatus(rs.getString("status"));
                }
            }
        }
        return booking;
    }

    @Override
    public List<GetAllBookingDTO> getAllBookingsWithDetails() throws SQLException {
        String sql = "SELECT b.bookingId, b.customerId,c.first_name,c.last_name, b.paymentId, p.advanceAmount, p.totalAmount, p.paymentDate, p.paymentStatus, " +
                "b.bookingDate, b.pickupDate, b.returnDate, b.status, " +
                "bd.bookingDetailId, bd.vehicleId, bd.driverId, bd.beforeMeeterReading, bd.afterMeeterReading " +
                "FROM Booking b " +
                "JOIN payment p ON b.paymentId = p.id " +
                "LEFT JOIN customer c ON b.customerId = c.id " +
                "LEFT JOIN Booking_details bd ON b.bookingId = bd.bookingId " +
                "ORDER BY b.bookingId DESC";

        Map<Long, GetAllBookingDTO> bookingMap = new LinkedHashMap<>();

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long bookingId = rs.getLong("bookingId");
                GetAllBookingDTO bookingDTO = bookingMap.get(bookingId);

                if (bookingDTO == null) {
                    bookingDTO = new GetAllBookingDTO();
                    bookingDTO.setBookingId(bookingId);
                    bookingDTO.setCustomerId(rs.getLong("customerId"));
                    bookingDTO.setCustomerFirstName(rs.getString("first_name"));
                    bookingDTO.setCustomerLastName(rs.getString("last_name"));
                    bookingDTO.setPaymentId(rs.getLong("paymentId"));
                    bookingDTO.setAdvanceAmount(rs.getBigDecimal("advanceAmount"));
                    bookingDTO.setTotalAmount(rs.getBigDecimal("totalAmount"));
                    bookingDTO.setPaymentDate(rs.getDate("paymentDate"));
                    bookingDTO.setPaymentStatus(rs.getString("paymentStatus"));
                    bookingDTO.setBookingDate(rs.getDate("bookingDate"));
                    bookingDTO.setPickupDate(rs.getDate("pickupDate"));
                    bookingDTO.setReturnDate(rs.getDate("returnDate"));
                    bookingDTO.setStatus(rs.getString("status"));
                    bookingDTO.setBookingDetails(new ArrayList<>());
                    bookingMap.put(bookingId, bookingDTO);
                }

                int bookingDetailId = rs.getInt("bookingDetailId");
                if (!rs.wasNull()) {
                    BookingDetails detail = new BookingDetails();
                    detail.setBookingDetailId(bookingDetailId);
                    detail.setBookingId(rs.getInt("bookingId"));
                    detail.setVehicleId(rs.getInt("vehicleId"));
                    detail.setDriverId(rs.getInt("driverId"));
                    detail.setBeforeMeeterReading(rs.getDouble("beforeMeeterReading"));
                    detail.setAfterMeeterReading(rs.getDouble("afterMeeterReading"));

                    bookingDTO.getBookingDetails().add(detail);
                }
            }
        }
        return new ArrayList<>(bookingMap.values());
    }


    @Override
    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Booking";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("bookingId"));
                booking.setCustomerId(rs.getInt("customerId"));
                booking.setPaymentId(rs.getInt("paymentId"));
                booking.setBookingDate(rs.getDate("bookingDate"));
                booking.setPickupDate(rs.getDate("pickupDate"));
                booking.setReturnDate(rs.getDate("returnDate"));
                booking.setStatus(rs.getString("status"));
                bookings.add(booking);
            }
        }
        return bookings;
    }

    @Override
    public void updateBooking(Booking booking) throws SQLException {
        String sql = "UPDATE Booking SET customerId=?, paymentId=?, bookingDate=?, pickupDate=?, returnDate=?, status=? WHERE bookingId=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, booking.getCustomerId());
            ps.setLong(2, booking.getPaymentId());
            ps.setDate(3, booking.getBookingDate());
            ps.setDate(4, booking.getPickupDate());
            ps.setDate(5, booking.getReturnDate());
            ps.setString(6, booking.getStatus());
            ps.setLong(7, booking.getBookingId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteBooking(int bookingId) throws SQLException {
        String sql = "DELETE FROM Booking WHERE bookingId = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookingId);
            ps.executeUpdate();
        }
    }
}
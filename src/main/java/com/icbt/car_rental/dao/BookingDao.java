package com.icbt.car_rental.dao;

import com.icbt.car_rental.model.Booking;
import com.icbt.car_rental.model.dto.BookingDTO;

import java.sql.SQLException;
import java.util.List;

public interface BookingDao {
    Booking addBooking(Booking booking) throws SQLException;

    Booking getBookingById(int bookingId) throws SQLException; // searchById

    List<Booking> getAllBookings() throws SQLException;

    void updateBooking(Booking booking) throws SQLException;

    void deleteBooking(int bookingId) throws SQLException;
}

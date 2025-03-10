package com.icbt.car_rental.service;

import com.icbt.car_rental.model.Booking;
import com.icbt.car_rental.model.dto.BookingDTO;

import java.sql.SQLException;
import java.util.List;

public interface BookingService {
    void addBooking(BookingDTO booking) throws SQLException;

    List<Booking> getAllBookings() throws SQLException;

    void updateBooking(Booking updateBooking) throws SQLException;

    void deleteBooking(int bookingId) throws SQLException;
}

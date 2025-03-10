package com.icbt.car_rental.dao;

import com.icbt.car_rental.model.BookingDetails;

import java.sql.SQLException;
import java.util.List;

public interface BookingDetailsDao {
    void addBookingDetails(BookingDetails bookingDetails) throws SQLException;

    BookingDetails getBookingDetailsById(int bookingDetailId) throws SQLException; // searchById

    List<BookingDetails> getAllBookingDetails() throws SQLException;

    void updateBookingDetails(BookingDetails bookingDetails) throws SQLException;

    void deleteBookingDetails(int bookingDetailId) throws SQLException;
}

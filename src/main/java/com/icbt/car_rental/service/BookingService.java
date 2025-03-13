package com.icbt.car_rental.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.icbt.car_rental.model.Booking;
import com.icbt.car_rental.model.BookingDetails;
import com.icbt.car_rental.model.Payment;
import com.icbt.car_rental.model.dto.BookingDTO;
import com.icbt.car_rental.model.dto.GetAllBookingDTO;

import java.sql.SQLException;
import java.util.List;

public interface BookingService {
    void addBooking(BookingDTO booking) throws SQLException;

    List<Booking> getAllBookings() throws SQLException;

    List<GetAllBookingDTO> getAllBookingsWithDetails() throws SQLException, JsonProcessingException;

    void updateBooking(BookingDTO updateBooking,List<BookingDetails> bookingDetailsList ,Payment payment) throws SQLException;

    void deleteBooking(int bookingId) throws SQLException;

    void addAdminBooking(BookingDTO bookingDto, List<BookingDetails> bookingDetailsList);

}

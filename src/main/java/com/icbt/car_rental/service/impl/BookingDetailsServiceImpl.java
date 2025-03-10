package com.icbt.car_rental.service.impl;

import com.icbt.car_rental.dao.BookingDetailsDao;
import com.icbt.car_rental.dao.impl.BookingDetailsDaoImpl;
import com.icbt.car_rental.model.BookingDetails;
import com.icbt.car_rental.service.BookingDetailsService;

import java.sql.SQLException;
import java.util.List;

public class BookingDetailsServiceImpl implements BookingDetailsService {

    BookingDetailsDao bookingDetailsDao = new BookingDetailsDaoImpl();

    @Override
    public void addBookingDetails(BookingDetails bookingDetails) throws SQLException {
        bookingDetailsDao.addBookingDetails(bookingDetails);
    }

    @Override
    public BookingDetails getBookingDetailsById(int bookingDetailId) throws SQLException {
        return bookingDetailsDao.getBookingDetailsById(bookingDetailId);
    }

    @Override
    public List<BookingDetails> getAllBookingDetails() throws SQLException {
        return bookingDetailsDao.getAllBookingDetails();
    }

    @Override
    public void updateBookingDetails(BookingDetails bookingDetails) throws SQLException {
        bookingDetailsDao.updateBookingDetails(bookingDetails);
    }

    @Override
    public void deleteBookingDetails(int bookingDetailId) throws SQLException {
        bookingDetailsDao.deleteBookingDetails(bookingDetailId);
    }
}

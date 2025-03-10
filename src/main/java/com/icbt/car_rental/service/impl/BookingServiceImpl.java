package com.icbt.car_rental.service.impl;

import com.icbt.car_rental.dao.BookingDao;
import com.icbt.car_rental.dao.impl.BookingDaoImpl;
import com.icbt.car_rental.model.*;
import com.icbt.car_rental.model.dto.BookingDTO;
import com.icbt.car_rental.service.*;

import java.sql.SQLException;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    BookingDao bookingDao = new BookingDaoImpl();
    BookingDetailsService bookingDetailsService = new BookingDetailsServiceImpl();
    PaymentService paymentService = new PaymentServiceImpl();
    VehicleService vehicleService = new VehicleServiceImpl();
    DriverService driverService = new DriverServiceImpl();

    @Override
    public void addBooking(BookingDTO bookingDto) throws SQLException {

        Payment persistedPayment = getPersistedPayment(bookingDto);
        Booking persistedBooking = getPersistedBooking(bookingDto, persistedPayment);


        String[] vehicleIds = bookingDto.getVehicleIds();
        String[] driverIds = bookingDto.getDriverIds();

        if (vehicleIds != null && driverIds != null && vehicleIds.length == driverIds.length) {
            for (int i = 0; i < vehicleIds.length; i++) {
                int vehicleId = Integer.parseInt(vehicleIds[i]);

                Vehicle vehicleById = vehicleService.getVehicleById(vehicleId);

                String driverIdStr = driverIds[i];

                BookingDetails detail = new BookingDetails();
                detail.setBookingId(persistedBooking.getBookingId());
                detail.setVehicleId(vehicleId);
                detail.setBeforeMeeterReading(vehicleById.getCurrentMeeterReading());

                if (driverIdStr != null && !driverIdStr.isEmpty()) {
                    int driverId = Integer.parseInt(driverIdStr);
                    Driver driverById = driverService.getDriverById(driverId);
                    updateDriver(driverById);
                    detail.setDriverId(driverId);
                }
                bookingDetailsService.addBookingDetails(detail);
                updateVehicle(vehicleById);

            }
        }

    }

    private void updateDriver(Driver driverById) throws SQLException {
        driverById.setStatus("BOOKED");
        driverService.updateDriver(driverById);
    }

    private void updateVehicle(Vehicle vehicleById) throws SQLException {
        vehicleById.setStatus("Reserved");
        vehicleService.updateVehicle(vehicleById);
    }

    private Booking getPersistedBooking(BookingDTO bookingDto, Payment persistedPayment) throws SQLException {
        Booking booking = new Booking();
        booking.setCustomerId(bookingDto.getCustomerId());
        booking.setPickupDate(bookingDto.getPickupDate());
        booking.setReturnDate(bookingDto.getReturnDate());
        booking.setStatus(bookingDto.getStatus());
        booking.setPaymentId(persistedPayment.getId());
        booking.setBookingDate(bookingDto.getBookingDate());

        Booking persistedBooking = bookingDao.addBooking(booking);
        return persistedBooking;
    }

    private Payment getPersistedPayment(BookingDTO bookingDto) throws SQLException {
        Payment payment = new Payment();
        payment.setAdvanceAmount(bookingDto.getAdvanceAmount());
        payment.setPaymentDate(bookingDto.getBookingDate());
        payment.setPaymentStatus("ADVANCE_PAID");
        Payment persistedPayment = paymentService.addPayment(payment);
        return persistedPayment;
    }

    @Override
    public List<Booking> getAllBookings() throws SQLException {
        return bookingDao.getAllBookings();
    }

    @Override
    public void updateBooking(Booking updateBooking) throws SQLException {
        bookingDao.updateBooking(updateBooking);
    }

    @Override
    public void deleteBooking(int bookingId) throws SQLException {
        bookingDao.deleteBooking(bookingId);
    }
}

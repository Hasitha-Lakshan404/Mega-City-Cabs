package com.icbt.car_rental.controller;

import com.icbt.car_rental.model.*;
import com.icbt.car_rental.model.dto.GetAllBookingDTO;
import com.icbt.car_rental.service.*;
import com.icbt.car_rental.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "adminBookingServlet", value = "/adminBooking")
public class AdminBookingServlet extends HttpServlet {

    private final BookingService bookingService = new BookingServiceImpl();
    private final CustomerService customerService = new CustomerServiceImpl();
    private final VehicleService vehicleService = new VehicleServiceImpl();
    private final DriverService driverService = new DriverServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<GetAllBookingDTO> bookingList = bookingService.getAllBookingsWithDetails();
            List<Customer> customers = customerService.getAllCustomers();
            List<Vehicle> vehicles = vehicleService.getAllVehicles(true);
            List<Vehicle> adminVehicleList = vehicleService.getAllVehicles(false);
            List<Driver> drivers = driverService.getAllDrivers(true);
            List<Driver> adminDriverList = driverService.getAllDrivers(false);

            request.setAttribute("bookings", bookingList);
            request.setAttribute("customers", customers);
            request.setAttribute("vehicles", vehicles);
            request.setAttribute("drivers", drivers);
            request.setAttribute("adminVehicleList", adminVehicleList);
            request.setAttribute("adminDriverList", adminDriverList);

            request.getRequestDispatcher("/WEB-INF/views/bookingManagement.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error retrieving booking data", e);
        }
    }

}
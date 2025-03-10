package com.icbt.car_rental.controller;

import com.icbt.car_rental.model.Driver;
import com.icbt.car_rental.model.Vehicle;
import com.icbt.car_rental.model.dto.BookingDTO;
import com.icbt.car_rental.service.BookingDetailsService;
import com.icbt.car_rental.service.BookingService;
import com.icbt.car_rental.service.DriverService;
import com.icbt.car_rental.service.VehicleService;
import com.icbt.car_rental.service.impl.BookingDetailsServiceImpl;
import com.icbt.car_rental.service.impl.BookingServiceImpl;
import com.icbt.car_rental.service.impl.DriverServiceImpl;
import com.icbt.car_rental.service.impl.VehicleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "bookingServlet", value = "/booking")
public class BookingServlet extends HttpServlet {

    private final VehicleService vehicleService = new VehicleServiceImpl();
    private final DriverService driverService = new DriverServiceImpl();
    private final BookingService bookingService = new BookingServiceImpl();
    private final BookingDetailsService bookingDetailsService = new BookingDetailsServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Vehicle> vehicles = vehicleService.getAllVehicles(true);
            List<Driver> drivers = driverService.getAllDrivers(true);
            request.setAttribute("vehicles", vehicles);
            request.setAttribute("drivers", drivers);
            request.getRequestDispatcher("/WEB-INF/views/booking.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error processing " + e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            String pickupDateStr = request.getParameter("pickupDate");
            String returnDateStr = request.getParameter("returnDate");
            String customerId = request.getParameter("customerId");
            String advanceAmount = request.getParameter("advanceAmount");

            String[] vehicleIds = request.getParameterValues("vehicleIds[]");
            String[] driverIds = request.getParameterValues("driverIds[]");

            Date pickupDate = Date.valueOf(pickupDateStr);
            Date returnDate = Date.valueOf(returnDateStr);

            BookingDTO bookingDto = new BookingDTO.Builder()
                    .customerId(Long.parseLong(customerId))
                    .pickupDate(pickupDate)
                    .returnDate(returnDate)
                    .status("PENDING")
                    .bookingDate(Date.valueOf(LocalDate.now()))
                    .vehicleIds(vehicleIds)
                    .driverIds(driverIds)
                    .advanceAmount(new BigDecimal(advanceAmount))
                    .build();

            bookingService.addBooking(bookingDto);

            response.sendRedirect(request.getContextPath() + "/booking?customerId=" + customerId);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/booking?error=true");
        }
    }
}

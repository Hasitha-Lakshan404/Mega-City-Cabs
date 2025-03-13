package com.icbt.car_rental.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icbt.car_rental.model.*;
import com.icbt.car_rental.model.dto.BookingDTO;
import com.icbt.car_rental.model.dto.GetAllBookingDTO;
import com.icbt.car_rental.service.*;
import com.icbt.car_rental.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getParameter("action");
        System.out.println("Action: ---" + action);
        try {
            if ("create".equalsIgnoreCase(action)) {
                // Header fields
                String customerId = request.getParameter("customerId");
                String bookingDateStr = request.getParameter("bookingDate");
                String pickupDateStr = request.getParameter("pickupDate");
                String returnDateStr = request.getParameter("returnDate");
                String status = request.getParameter("status");
                String advanceAmountStr = request.getParameter("advanceAmount");
                String paymentStatus = request.getParameter("paymentStatus");

                // Parse header data
                Date bookingDate = Date.valueOf(bookingDateStr);
                Date pickupDate = Date.valueOf(pickupDateStr);
                Date returnDate = Date.valueOf(returnDateStr);
                BigDecimal advanceAmount = new BigDecimal(advanceAmountStr);

                // Get the booking details JSON string
                String bookingDetailsJson = request.getParameter("bookingDetailsJson");
                ObjectMapper mapper = new ObjectMapper();
                List<BookingDetails> bookingDetailsList = mapper.readValue(bookingDetailsJson,
                        new TypeReference<List<BookingDetails>>() {});

                // Calculate total amount on the backend from booking details
                BigDecimal totalAmount = BigDecimal.ZERO;
                for (BookingDetails detail : bookingDetailsList) {
                    int vehicleId = (int) detail.getVehicleId();
                    double before = detail.getBeforeMeeterReading();
                    double after = detail.getAfterMeeterReading();
                    double diff = after - before;
                    if (diff < 0) diff = 0;
                    BigDecimal rentPerKm = vehicleService.getVehicleById(vehicleId).getRentPerKm();
//                    totalAmount = totalAmount.add(BigDecimal.valueOf(diff * rentPerKm));
                    totalAmount = totalAmount.add(rentPerKm.multiply(BigDecimal.valueOf(diff)));
                }

                BookingDTO bookingDto = new BookingDTO.Builder()
                        .customerId(Long.parseLong(customerId))
                        .bookingDate(bookingDate)
                        .pickupDate(pickupDate)
                        .returnDate(returnDate)
                        .status(status)
                        .advanceAmount(advanceAmount)
//                        .totalAmount(totalAmount)
//                        .paymentStatus(paymentStatus)
                        .build();

                // Call service method to create booking along with details
                bookingService.addAdminBooking(bookingDto, bookingDetailsList);

            } else if ("update".equalsIgnoreCase(action)) {
                // Header fields for update
                String bookingIdStr = request.getParameter("bookingId");
                String paymentIdStr = request.getParameter("paymentId");
                String customerId = request.getParameter("customerId");
                String bookingDateStr = request.getParameter("bookingDate");
                String pickupDateStr = request.getParameter("pickupDate");
                String returnDateStr = request.getParameter("returnDate");
                String status = request.getParameter("status");
                String advanceAmountStr = request.getParameter("advanceAmount");
                String paymentStatus = request.getParameter("paymentStatus");

                Date bookingDate = Date.valueOf(bookingDateStr);
                Date pickupDate = Date.valueOf(pickupDateStr);
                Date returnDate = Date.valueOf(returnDateStr);
                BigDecimal advanceAmount = new BigDecimal(advanceAmountStr);

                // Get booking details JSON string and parse it
                String bookingDetailsJson = request.getParameter("bookingDetailsJson");
                ObjectMapper mapper = new ObjectMapper();
                List<BookingDetails> bookingDetailsList = mapper.readValue(bookingDetailsJson,
                        new TypeReference<List<BookingDetails>>() {});

                BigDecimal totalAmount = BigDecimal.ZERO;
                for (BookingDetails detail : bookingDetailsList) {
                    int vehicleId = (int) detail.getVehicleId();
                    double before = detail.getBeforeMeeterReading();
                    double after = detail.getAfterMeeterReading();
                    double diff = after - before;
                    if (diff < 0) diff = 0;
                    BigDecimal rentPerKm = vehicleService.getVehicleById(vehicleId).getRentPerKm();
//                    totalAmount = totalAmount.add(BigDecimal.valueOf(diff * rentPerKm));
                    totalAmount = totalAmount.add(rentPerKm.multiply(BigDecimal.valueOf(diff)));
                }

                BookingDTO bookingDto = new BookingDTO.Builder()
                        .bookingId(Long.parseLong(bookingIdStr))
                        .paymentId(Long.parseLong(paymentIdStr))
                        .customerId(Long.parseLong(customerId))
                        .bookingDate(bookingDate)
                        .pickupDate(pickupDate)
                        .returnDate(returnDate)
                        .status(status)
                        .advanceAmount(advanceAmount)
//                        .totalAmount(totalAmount)
//                        .paymentStatus(paymentStatus)
                        .build();

                Payment payment = new Payment();
                payment.setAdvanceAmount(advanceAmount);
                payment.setPaymentDate(bookingDate);
                payment.setPaymentStatus("ADVANCE_PAID");
                payment.setTotalAmount(totalAmount);

                bookingService.updateBooking(bookingDto, bookingDetailsList, payment);

            } else if ("delete".equalsIgnoreCase(action)) {
                String bookingIdStr = request.getParameter("bookingId");
                bookingService.deleteBooking(Integer.parseInt(bookingIdStr));
            }
            response.sendRedirect(request.getContextPath() + "/adminBooking");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/adminBooking?error=true");
        }
    }
}
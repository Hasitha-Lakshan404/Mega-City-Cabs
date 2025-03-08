package com.icbt.car_rental.controller;

import com.icbt.car_rental.model.Vehicle;
import com.icbt.car_rental.service.VehicleService;
import com.icbt.car_rental.service.impl.VehicleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "vehicleServlet", value = "/vehicle")
public class VehicleServlet extends HttpServlet {

    private VehicleService vehicleService = new VehicleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Vehicle> vehicles = vehicleService.getAllVehicles();
            request.setAttribute("vehicles", vehicles);
            request.getRequestDispatcher("/WEB-INF/views/vehicleManagement.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error retrieving vehicles", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "create":
                    Vehicle newVehicle = new Vehicle();
                    newVehicle.setBrand(request.getParameter("brand"));
                    newVehicle.setModel(request.getParameter("model"));
                    newVehicle.setVariant(request.getParameter("variant"));
                    newVehicle.setYear(Integer.parseInt(request.getParameter("year")));
                    newVehicle.setFuelType(request.getParameter("fuelType"));
                    newVehicle.setSeatingCapacity(Integer.parseInt(request.getParameter("seatingCapacity")));
                    newVehicle.setRentPerDay(new BigDecimal(request.getParameter("rentPerDay")));
                    newVehicle.setStatus(request.getParameter("status"));

                    vehicleService.addVehicle(newVehicle);
                    break;

                case "update":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Vehicle updateVehicle = new Vehicle();
                    updateVehicle.setId(id);
                    updateVehicle.setBrand(request.getParameter("brand"));
                    updateVehicle.setModel(request.getParameter("model"));
                    updateVehicle.setVariant(request.getParameter("variant"));
                    updateVehicle.setYear(Integer.parseInt(request.getParameter("year")));
                    updateVehicle.setFuelType(request.getParameter("fuelType"));
                    updateVehicle.setSeatingCapacity(Integer.parseInt(request.getParameter("seatingCapacity")));
                    updateVehicle.setRentPerDay(new BigDecimal(request.getParameter("rentPerDay")));
                    updateVehicle.setStatus(request.getParameter("status"));
                    vehicleService.updateVehicle(updateVehicle);
                    break;

                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    vehicleService.deleteVehicle(deleteId);
                    break;

                default:
                    break;
            }
            response.sendRedirect("vehicle");
        } catch (SQLException e) {
            throw new ServletException("Database error processing " + action, e);
        }
    }
}

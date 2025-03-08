package com.icbt.car_rental.controller;

import com.icbt.car_rental.model.Customer;
import com.icbt.car_rental.service.CustomerService;
import com.icbt.car_rental.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "customerServlet", value = "/customer")
public class CustomerServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("/WEB-INF/views/customerManagement.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error retrieving customers", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "create":
                    Customer newCustomer = new Customer();
                    newCustomer.setFirstName(request.getParameter("firstName"));
                    newCustomer.setLastName(request.getParameter("lastName"));
                    newCustomer.setEmail(request.getParameter("email"));
                    newCustomer.setPassword(request.getParameter("password"));
                    newCustomer.setAddress(request.getParameter("address"));
                    newCustomer.setContactNo(request.getParameter("contactNo"));
                    customerService.addCustomer(newCustomer);
                    break;

                case "update":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Customer updateCustomer = new Customer();
                    updateCustomer.setId(id);
                    updateCustomer.setFirstName(request.getParameter("firstName"));
                    updateCustomer.setLastName(request.getParameter("lastName"));
                    updateCustomer.setEmail(request.getParameter("email"));
                    updateCustomer.setPassword(request.getParameter("password"));
                    updateCustomer.setAddress(request.getParameter("address"));
                    updateCustomer.setContactNo(request.getParameter("contactNo"));
                    customerService.updateCustomer(updateCustomer);
                    break;

                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    customerService.deleteCustomer(deleteId);
                    break;

                default:
                    break;
            }
            response.sendRedirect("customer");
        } catch (SQLException e) {
            throw new ServletException("Database error processing " + action, e);
        }
    }
}

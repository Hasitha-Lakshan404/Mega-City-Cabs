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

@WebServlet()
public class CustomerServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response){

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String action = request.getParameter("action");

        try {
            if (action.equals("create")) {
                Customer customer = new Customer();
                customer.setFirstName(request.getParameter("firstName"));
                customer.setLastName(request.getParameter("lastName"));
                customer.setEmail(request.getParameter("email"));
                customer.setPassword(request.getParameter("password"));
                customer.setAddress(request.getParameter("address"));
                customer.setContactNo(request.getParameter("contactNo"));

                customerService.createCustomer(customer);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

}

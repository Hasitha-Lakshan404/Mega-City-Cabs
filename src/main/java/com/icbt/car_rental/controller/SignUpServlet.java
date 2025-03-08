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

@WebServlet(name = "signUpServlet", value = "/signup")
public class SignUpServlet extends HttpServlet {

    CustomerService customerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {

        try {
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            // Check if passwords match
            if (password.equals(confirmPassword)) {

                Customer newCustomer = new Customer();
                newCustomer.setFirstName(request.getParameter("firstName"));
                newCustomer.setLastName(request.getParameter("lastName"));
                newCustomer.setEmail(request.getParameter("email"));
                newCustomer.setPassword(password);
                newCustomer.setAddress(request.getParameter("address"));
                newCustomer.setContactNo(request.getParameter("contactNo"));
                newCustomer.setNic(request.getParameter("nic"));
                customerService.addCustomer(newCustomer);
                response.sendRedirect("index.jsp?signup=success");
            } else {
                response.sendRedirect("index.jsp?signup=error");
            }

        } catch (SQLException e) {
            throw new ServletException("Database error processing " + e);
        }


    }

}

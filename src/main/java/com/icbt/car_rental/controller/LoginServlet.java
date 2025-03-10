package com.icbt.car_rental.controller;

import com.icbt.car_rental.dao.AdminAuthDao;
import com.icbt.car_rental.dao.CustomerDao;
import com.icbt.car_rental.dao.impl.AdminAuthDaoImpl;
import com.icbt.car_rental.dao.impl.CustomerDaoImpl;
import com.icbt.car_rental.model.AdminAuth;
import com.icbt.car_rental.model.Customer;
import com.icbt.car_rental.service.AdminAuthService;
import com.icbt.car_rental.service.CustomerService;
import com.icbt.car_rental.service.impl.AdminAuthServiceImpl;
import com.icbt.car_rental.service.impl.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private final CustomerService customerService = new CustomerServiceImpl();
    private final AdminAuthService adminAuthService = new AdminAuthServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            String adminLogin = request.getParameter("adminLogin");

            if (adminLogin != null && adminLogin.equals("on")) {

                AdminAuth adminAuth = AdminAuth.builder()
                        .email(email)
                        .password(password)
                        .build();

                if (adminAuthService.validate(adminAuth)) {
                    response.sendRedirect("vehicle");
                }else{
                    invalidCredentialAndForwardToLoginPage(request, response);
                }
            } else {

                Customer customer = Customer.builder()
                        .email(email)
                        .password(password)
                        .build();

                Customer customerResponse = customerService.checkValidationAndGetCustomer(customer);
                if (customerResponse != null) {
                    response.sendRedirect("booking?customerId=" + customerResponse.getId());

                }else{
                    invalidCredentialAndForwardToLoginPage(request, response);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void invalidCredentialAndForwardToLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMessage", "Invalid email or password");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
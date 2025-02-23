package com.icbt.car_rental.service;

import com.icbt.car_rental.model.Customer;

import java.sql.SQLException;

public interface CustomerService {
    void createCustomer(Customer customer) throws SQLException;
}

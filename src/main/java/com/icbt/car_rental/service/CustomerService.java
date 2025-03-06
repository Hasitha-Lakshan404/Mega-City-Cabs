package com.icbt.car_rental.service;

import com.icbt.car_rental.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    void createCustomer(Customer customer) throws SQLException;
    List<Customer> getAllCustomers() throws SQLException;

    void updateCustomer(Customer updateCustomer) throws SQLException;

    void deleteCustomer(int customerId);
}

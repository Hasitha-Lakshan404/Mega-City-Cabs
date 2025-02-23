package com.icbt.car_rental.dao;

import com.icbt.car_rental.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    void addCustomer(Customer customer) throws SQLException;
    List<Customer> getAllCustomers() throws SQLException;
}

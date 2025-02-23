package com.icbt.car_rental.dao;

import com.icbt.car_rental.model.Customer;

import java.sql.SQLException;

public interface CustomerDao {
    void addCustomer(Customer customer) throws SQLException;
}

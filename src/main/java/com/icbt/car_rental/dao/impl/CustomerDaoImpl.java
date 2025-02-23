package com.icbt.car_rental.dao.impl;

import com.icbt.car_rental.dao.CustomerDao;
import com.icbt.car_rental.model.Customer;
import com.icbt.car_rental.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (first_name, last_name, email,password,address,contactNo) VALUES (?, ?, ?,?,?,?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPassword());
            pstmt.setString(5, customer.getAddress());
            pstmt.setString(6, customer.getContactNo());
            pstmt.executeUpdate();
        }
    }
}

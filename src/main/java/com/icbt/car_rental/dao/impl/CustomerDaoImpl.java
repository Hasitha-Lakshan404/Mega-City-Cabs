package com.icbt.car_rental.dao.impl;

import com.icbt.car_rental.dao.CustomerDao;
import com.icbt.car_rental.model.Customer;
import com.icbt.car_rental.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (first_name, last_name, email,password,address,contactNo,nic) VALUES (?, ?, ?,?,?,?,?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPassword());
            pstmt.setString(5, customer.getAddress());
            pstmt.setString(6, customer.getContactNo());
            pstmt.setString(7, customer.getNic());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer ORDER BY id DESC";
        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("contactNo"),
                        rs.getString("nic")
                ));
            }
        }
        return customers;
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET first_name = ?,last_name = ?, email = ?, address = ?, contactNo = ?, nic = ? WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getAddress());
            pstmt.setString(5, customer.getContactNo());
            pstmt.setString(6, customer.getNic());
            pstmt.setLong(7, customer.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM customer WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validate(Customer customer) throws SQLException {
        String sql = "SELECT COUNT(*) FROM customer WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getEmail());
            pstmt.setString(2, customer.getPassword());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}

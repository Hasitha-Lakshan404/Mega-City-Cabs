package com.icbt.car_rental.dao.impl;

import com.icbt.car_rental.dao.PaymentDao;
import com.icbt.car_rental.model.Payment;
import com.icbt.car_rental.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {

    @Override
    public Payment addPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment (advanceAmount, totalAmount, paymentDate, paymentStatus) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setBigDecimal(1, payment.getAdvanceAmount());
            ps.setBigDecimal(2, payment.getTotalAmount());
            ps.setDate(3, payment.getPaymentDate());
            ps.setString(4, payment.getPaymentStatus());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    payment.setId(rs.getInt(1));
                    return payment;
                }
            }
        }
        return null;
    }

    @Override
    public Payment getPaymentById(int id) throws SQLException {
        Payment payment = null;
        String sql = "SELECT * FROM payment WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    payment = new Payment();
                    payment.setId(rs.getLong("id"));
                    payment.setAdvanceAmount(rs.getBigDecimal("advanceAmount"));
                    payment.setTotalAmount(rs.getBigDecimal("totalAmount"));
                    payment.setPaymentDate(rs.getDate("paymentDate"));
                    payment.setPaymentStatus(rs.getString("paymentStatus"));
                }
            }
        }
        return payment;
    }

    @Override
    public List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getLong("id"));
                payment.setAdvanceAmount(rs.getBigDecimal("advanceAmount"));
                payment.setTotalAmount(rs.getBigDecimal("totalAmount"));
                payment.setPaymentDate(rs.getDate("paymentDate"));
                payment.setPaymentStatus(rs.getString("paymentStatus"));
                payments.add(payment);
            }
        }
        return payments;
    }

    @Override
    public void updatePayment(Payment payment) throws SQLException {
        String sql = "UPDATE payment SET advanceAmount=?, totalAmount=?, paymentDate=?, paymentStatus=? WHERE id=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, payment.getAdvanceAmount());
            ps.setBigDecimal(2, payment.getTotalAmount());
            ps.setDate(3, payment.getPaymentDate());
            ps.setString(4, payment.getPaymentStatus());
            ps.setLong(5, payment.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deletePayment(int id) throws SQLException {
        String sql = "DELETE FROM payment WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

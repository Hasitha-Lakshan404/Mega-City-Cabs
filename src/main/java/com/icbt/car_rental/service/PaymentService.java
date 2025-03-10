package com.icbt.car_rental.service;

import com.icbt.car_rental.model.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentService {
    Payment addPayment(Payment payment) throws SQLException;

    Payment getPaymentById(int id) throws SQLException ;

    List<Payment> getAllPayments() throws SQLException ;

    void updatePayment(Payment payment) throws SQLException ;

    void deletePayment(int id) throws SQLException ;
}

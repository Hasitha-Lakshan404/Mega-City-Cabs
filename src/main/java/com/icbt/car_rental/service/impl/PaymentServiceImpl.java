package com.icbt.car_rental.service.impl;

import com.icbt.car_rental.dao.PaymentDao;
import com.icbt.car_rental.dao.impl.PaymentDaoImpl;
import com.icbt.car_rental.model.Payment;
import com.icbt.car_rental.service.PaymentService;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    PaymentDao paymentDao = new PaymentDaoImpl();

    @Override
    public Payment addPayment(Payment payment) throws SQLException {
        return paymentDao.addPayment(payment);
    }

    @Override
    public Payment getPaymentById(int id) throws SQLException {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public List<Payment> getAllPayments() throws SQLException {
        return paymentDao.getAllPayments();
    }

    @Override
    public void updatePayment(Payment payment) throws SQLException {
        paymentDao.updatePayment(payment);
    }

    @Override
    public void deletePayment(int id) throws SQLException {
        paymentDao.deletePayment(id);
    }
}

package com.icbt.car_rental.service.impl;

import com.icbt.car_rental.dao.CustomerDao;
import com.icbt.car_rental.dao.impl.CustomerDaoImpl;
import com.icbt.car_rental.model.Customer;
import com.icbt.car_rental.service.CustomerService;

import java.sql.SQLException;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void createCustomer(Customer customer) throws SQLException {
        customerDao.addCustomer(customer);
    }
}

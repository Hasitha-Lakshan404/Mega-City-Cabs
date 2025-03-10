package com.icbt.car_rental.service.impl;

import com.icbt.car_rental.dao.CustomerDao;
import com.icbt.car_rental.dao.impl.CustomerDaoImpl;
import com.icbt.car_rental.model.Customer;
import com.icbt.car_rental.service.CustomerService;

import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        customerDao.addCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return customerDao.getAllCustomers();
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        customerDao.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        customerDao.deleteCustomer(customerId);
    }

    @Override
    public Customer checkValidationAndGetCustomer(Customer customer) throws SQLException {
       return customerDao.checkValidationAndGetCustomer(customer);
    }
}

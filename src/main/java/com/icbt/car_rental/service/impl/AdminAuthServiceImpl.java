package com.icbt.car_rental.service.impl;

import com.icbt.car_rental.dao.AdminAuthDao;
import com.icbt.car_rental.dao.impl.AdminAuthDaoImpl;
import com.icbt.car_rental.model.AdminAuth;
import com.icbt.car_rental.service.AdminAuthService;

import java.sql.SQLException;

public class AdminAuthServiceImpl implements AdminAuthService {

    private final AdminAuthDao adminAuthDao = new AdminAuthDaoImpl();

    @Override
    public boolean validate(AdminAuth adminAuth) throws SQLException {
        return adminAuthDao.validate(adminAuth);
    }
}

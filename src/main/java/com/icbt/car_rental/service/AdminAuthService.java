package com.icbt.car_rental.service;

import com.icbt.car_rental.model.AdminAuth;

import java.sql.SQLException;

public interface AdminAuthService {
    public boolean validate(AdminAuth adminAuth) throws SQLException;
}

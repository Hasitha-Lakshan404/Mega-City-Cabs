package com.icbt.car_rental.dao;

import com.icbt.car_rental.model.AdminAuth;

import java.sql.SQLException;

public interface AdminAuthDao {
    public boolean validate(AdminAuth adminAuth) throws SQLException;
}

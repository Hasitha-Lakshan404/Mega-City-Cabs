package com.icbt.car_rental.dao.impl;

import com.icbt.car_rental.dao.AdminAuthDao;
import com.icbt.car_rental.model.AdminAuth;
import com.icbt.car_rental.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminAuthDaoImpl implements AdminAuthDao {
    @Override
    public boolean validate(AdminAuth adminAuth) throws SQLException{

        String sql = "SELECT COUNT(*) FROM adminauth WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, adminAuth.getEmail());
            pstmt.setString(2, adminAuth.getPassword());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}

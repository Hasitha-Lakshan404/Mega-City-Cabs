package com.icbt.car_rental.model;

import com.icbt.car_rental.util.UserRole;

public class AdminAuth {
    private long id;
    private String email;
    private String password;
    private UserRole userRole;

    public AdminAuth(long id, String email, String password, UserRole userRole) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public AdminAuth() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public static class Builder {
        private long id;
        private String email;
        private String password;
        private UserRole userRole;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder userRole(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        public AdminAuth build() {
            return new AdminAuth(this.id, this.email, this.password, this.userRole);
        }
    }
}

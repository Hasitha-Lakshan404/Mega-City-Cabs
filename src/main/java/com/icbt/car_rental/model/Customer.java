package com.icbt.car_rental.model;

public class Customer {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String contactNo;
    public String nic;

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Customer(long id, String firstName, String lastName, String email, String password, String address, String contactNo, String nic) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contactNo = contactNo;
        this.nic = nic;
    }

    public Customer() {

    }



    private Customer(Builder builder) {
        this.setId(builder.id);
        this.setFirstName(builder.firstName);
        this.setLastName(builder.lastName);
        this.setEmail(builder.email);
        this.setPassword(builder.password);
        this.setAddress(builder.address);
        this.setContactNo(builder.contactNo);
        this.setNic(builder.nic);
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }


    public static class Builder {
        private long id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String address;
        private String contactNo;
        private String nic;

        public Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder userName(String userName) {
            this.email = userName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder contactNo(String contactNo) {
            this.contactNo = contactNo;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder nic(String nic) {
            this.nic = nic;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

}

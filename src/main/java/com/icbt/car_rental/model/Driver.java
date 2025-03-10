package com.icbt.car_rental.model;

public class Driver {
    private long id;
    private String name;
    private String nic;
    private String address;
    private String contactNo;
    private String status;

    public Driver() {
    }

    public Driver(long id, String name, String nic, String address, String contactNo, String status) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.contactNo = contactNo;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

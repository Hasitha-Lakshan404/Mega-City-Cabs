package com.icbt.car_rental.model;


import java.sql.Date;

public class Booking {
    private long bookingId;
    private long customerId;
    private long paymentId;
    private long driverId;
    private Date bookingDate;
    private Date pickupDate;
    private Date returnDate;
    private String status;

    public Booking(long bookingId, long customerId, long paymentId, long driverId, Date bookingDate, Date pickupDate, Date returnDate, String status) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.driverId = driverId;
        this.bookingDate = bookingDate;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Booking() {
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


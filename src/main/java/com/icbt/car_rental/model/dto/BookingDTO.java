package com.icbt.car_rental.model.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class BookingDTO {
    private long bookingId;
    private long customerId;
    private long paymentId;
    private long driverId;
    private Date bookingDate;
    private Date pickupDate;
    private Date returnDate;
    private String status;
    private String[] vehicleIds;
    private String[] driverIds;
    private BigDecimal advanceAmount;

    public BookingDTO(long bookingId, long customerId, long paymentId, long driverId, Date bookingDate, Date pickupDate, Date returnDate, String status, String[] vehicleIds, String[] driverIds, BigDecimal advanceAmount) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.driverId = driverId;
        this.bookingDate = bookingDate;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.status = status;
        this.vehicleIds = vehicleIds;
        this.driverIds = driverIds;
        this.advanceAmount = advanceAmount;
    }

    public BookingDTO() {
    }

    public BigDecimal getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(BigDecimal advanceAmount) {
        this.advanceAmount = advanceAmount;
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

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
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

    public String[] getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(String[] vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public String[] getDriverIds() {
        return driverIds;
    }

    public void setDriverIds(String[] driverIds) {
        this.driverIds = driverIds;
    }

    public static class Builder {
        private long bookingId;
        private long customerId;
        private long paymentId;
        private long driverId;
        private Date bookingDate;
        private Date pickupDate;
        private Date returnDate;
        private String status;
        private String[] vehicleIds;
        private String[] driverIds;
        private BigDecimal advanceAmount;


        public Builder bookingId(long bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public Builder customerId(long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder paymentId(long paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder driverId(long driverId) {
            this.driverId = driverId;
            return this;
        }

        public Builder bookingDate(Date bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }

        public Builder pickupDate(Date pickupDate) {
            this.pickupDate = pickupDate;
            return this;
        }

        public Builder returnDate(Date returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder vehicleIds(String[] vehicleIds) {
            this.vehicleIds = vehicleIds;
            return this;
        }

        public Builder driverIds(String[] driverIds) {
            this.driverIds = driverIds;
            return this;
        }

        public Builder advanceAmount(BigDecimal advanceAmount) {
            this.advanceAmount = advanceAmount;
            return this;
        }

        public BookingDTO build() {
            return new BookingDTO(bookingId, customerId, paymentId, driverId, bookingDate, pickupDate, returnDate, status, vehicleIds, driverIds, advanceAmount);
        }
    }
}

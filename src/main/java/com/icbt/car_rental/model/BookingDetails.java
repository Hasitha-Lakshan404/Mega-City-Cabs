package com.icbt.car_rental.model;

public class BookingDetails {
    private long bookingDetailId;
    private long bookingId;
    private long vehicleId;
    private long driverId;
    private double beforeMeeterReading;
    private double afterMeeterReading;

    public BookingDetails() {
    }

    public BookingDetails(long bookingDetailId, long bookingId, long vehicleId, long driverId, double beforeMeeterReading, double afterMeeterReading) {
        this.bookingDetailId = bookingDetailId;
        this.bookingId = bookingId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.beforeMeeterReading = beforeMeeterReading;
        this.afterMeeterReading = afterMeeterReading;
    }

    public double getAfterMeeterReading() {
        return afterMeeterReading;
    }

    public void setAfterMeeterReading(double afterMeeterReading) {
        this.afterMeeterReading = afterMeeterReading;
    }

    public double getBeforeMeeterReading() {
        return beforeMeeterReading;
    }

    public void setBeforeMeeterReading(double beforeMeeterReading) {
        this.beforeMeeterReading = beforeMeeterReading;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public long getBookingDetailId() {
        return bookingDetailId;
    }

    public void setBookingDetailId(long bookingDetailId) {
        this.bookingDetailId = bookingDetailId;
    }
}

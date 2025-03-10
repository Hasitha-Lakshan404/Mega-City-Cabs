package com.icbt.car_rental.model;

import java.math.BigDecimal;

public class Vehicle {
    private long id;
    private String brand;
    private String model;
    private String variant;
    private int year;
    private String fuelType;
    private int seatingCapacity;
    private BigDecimal rentPerKm;
    private double currentMeeterReading;
    private String status;

    public Vehicle() {
    }

    public Vehicle(long id, String brand, String model, String variant, int year, String fuelType, int seatingCapacity, BigDecimal rentPerKm, double currentMeeterReading, String status) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.variant = variant;
        this.year = year;
        this.fuelType = fuelType;
        this.seatingCapacity = seatingCapacity;
        this.rentPerKm = rentPerKm;
        this.currentMeeterReading = currentMeeterReading;
        this.status = status;
    }

    public double getCurrentMeeterReading() {
        return currentMeeterReading;
    }

    public void setCurrentMeeterReading(double currentMeeterReading) {
        this.currentMeeterReading = currentMeeterReading;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public BigDecimal getRentPerKm() {
        return rentPerKm;
    }

    public void setRentPerKm(BigDecimal rentPerKm) {
        this.rentPerKm = rentPerKm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

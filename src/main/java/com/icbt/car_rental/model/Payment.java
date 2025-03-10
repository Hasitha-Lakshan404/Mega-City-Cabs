package com.icbt.car_rental.model;

import java.math.BigDecimal;
import java.sql.Date;


public class Payment {
    private long id;
    private BigDecimal advanceAmount;
    private BigDecimal totalAmount;
    private Date paymentDate;
    private String paymentStatus;

    public Payment() {
    }

    public Payment(long id, BigDecimal advanceAmount, BigDecimal totalAmount, Date paymentDate, String status) {
        this.id = id;
        this.advanceAmount = advanceAmount;
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
        this.paymentStatus = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(BigDecimal advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}

package com.icbt.car_rental.model.dto;

import com.icbt.car_rental.model.BookingDetails;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class GetAllBookingDTO {
    private long bookingId;
    private long customerId;
    private String customerFirstName;
    private String customerLastName;
    private long paymentId;
    private BigDecimal advanceAmount;
    private BigDecimal totalAmount;
    private Date paymentDate;
    private String paymentStatus;
    private List<BookingDetails> bookingDetails;
    private Date bookingDate;
    private Date pickupDate;
    private Date returnDate;
    private String status;
    private String bookingDetailsJson;

    public String getBookingDetailsJson() {
        return bookingDetailsJson;
    }

    public void setBookingDetailsJson(String bookingDetailsJson) {
        this.bookingDetailsJson = bookingDetailsJson;
    }

    public GetAllBookingDTO(long bookingId, long customerId, String customerFirstName, String customerLastName, long paymentId, BigDecimal advanceAmount, BigDecimal totalAmount, Date paymentDate, String paymentStatus, List<BookingDetails> bookingDetails, Date bookingDate, Date pickupDate, Date returnDate, String status, String bookingDetailsJson) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.paymentId = paymentId;
        this.advanceAmount = advanceAmount;
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.bookingDetails = bookingDetails;
        this.bookingDate = bookingDate;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.status = status;
        this.bookingDetailsJson = bookingDetailsJson;
    }


    public GetAllBookingDTO() {
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

    public List<BookingDetails> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetails> bookingDetails) {
        this.bookingDetails = bookingDetails;
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

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }
}

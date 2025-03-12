<%--
  Created by IntelliJ IDEA.
  User: hlaks
  Date: 3/11/2025
  Time: 01:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.icbt.car_rental.model.Booking" %>
<%@ page import="com.icbt.car_rental.model.dto.GetAllBookingDTO" %>
<%@ page import="com.icbt.car_rental.model.Customer" %>
<%@ page import="com.icbt.car_rental.model.Vehicle" %>
<%@ page import="com.icbt.car_rental.model.Driver" %>
<html>
<head>
    <title>Booking Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>

        .main-content {
            margin-left: 220px;
            padding: 20px;
        }
        .sidebar {
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            width: 220px;
            padding: 20px;
            background: linear-gradient(135deg, rgba(78,77,91,1) 8%, rgba(21,21,55,1) 100%, rgba(0,212,255,1) 100%);
            color: #fff;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
        }
        .sidebar h4 {
            color: #fff;
            margin-bottom: 20px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .sidebar .nav-link {
            color: #fff;
            font-weight: 500;
            border-radius: 4px;
            padding: 10px 15px;
            margin-bottom: 5px;
            transition: background-color 0.3s ease, padding 0.3s ease;
        }
        .sidebar .nav-link:hover,
        .sidebar .nav-link.active {
            background-color: rgba(255, 255, 255, 0.2);
            padding-left: 20px;
        }
        /* Booking form custom styles */
        .booking-form {
            margin-top: 20px;
            margin-bottom: 40px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .booking-details-section {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 15px;
            border-radius: 5px;
            background-color: #fff;
        }
    </style>
</head>
<body>
<!-- Sidebar -->
<div class="sidebar">
    <h4>Car Reservation</h4>
    <hr>
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link" href="customer">Customer Management</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="vehicle">Vehicle Management</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="adminBooking">Booking Management</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="orderManagement.jsp">Order Management</a>
        </li>
    </ul>
</div>

<!-- Main Content -->
<div class="main-content">
    <div class="container">
        <h1>Booking Management</h1>

        <!-- Booking Form -->
        <form action="booking" method="post" class="booking-form">
            <!-- Hidden fields for update mode -->
            <input type="hidden" name="action" id="action" value="create">
            <input type="hidden" name="bookingId" id="bookingId" value="">
            <input type="hidden" name="paymentId" id="paymentId" value="">

            <!-- Booking Header Details -->
            <div class="mb-3">
                <label for="customerId" class="form-label">Customer</label>
                <select class="form-control" id="customerId" name="customerId" required>
                    <option value="">-- Select Customer --</option>
                    <c:forEach var="customer" items="${customers}">
                        <option value="${customer.id}">${customer.firstName} ${customer.lastName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="row g-3">
                <div class="mb-3 col-md-4">
                    <label for="bookingDate" class="form-label">Booking Date</label>
                    <input type="date" class="form-control" id="bookingDate" name="bookingDate" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="pickupDate" class="form-label">Pickup Date</label>
                    <input type="date" class="form-control" id="pickupDate" name="pickupDate" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="returnDate" class="form-label">Return Date</label>
                    <input type="date" class="form-control" id="returnDate" name="returnDate" required>
                </div>
            </div>
            <div class="mb-3">
                <label for="status" class="form-label">Booking Status</label>
                <select class="form-select" id="status" name="status" required>
                    <option value="" disabled selected></option>
                    <option value="PENDING">PENDING</option>
                    <option value="COMPLETED">COMPLETED</option>
                    <option value="CANCELLED">CANCELLED</option>
                </select>
            </div>

            <!-- Payment Details -->
            <h4>Payment Details</h4>
            <div class="row g-3">
                <div class="mb-3 col-md-4">
                    <label for="advanceAmount" class="form-label">Advance Amount</label>
                    <input type="number" step="0.01" class="form-control" id="advanceAmount" name="advanceAmount" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="totalAmount" class="form-label">Total Amount</label>
                    <input type="number" step="0.01" class="form-control" id="totalAmount" name="totalAmount">
                </div>
                <div class="mb-3 col-md-4">
                    <label for="paymentDate" class="form-label">Payment Date</label>
                    <input type="date" class="form-control" id="paymentDate" name="paymentDate" required>
                </div>
            </div>
            <div class="mb-3">
                <label for="paymentStatus" class="form-label">Payment Status</label>
                <select class="form-select" id="paymentStatus" name="paymentStatus" required>
                    <option value="" disabled selected></option>
                    <option value="ADVANCE_PAID">ADVANCE_PAID</option>
                    <option value="PENDING">PENDING</option>
                    <option value="CANCELED">CANCELED</option>
                    <option value="COMPLETE">COMPLETE</option>
                </select>
            </div>

            <!-- Booking Details Section -->
            <h4>Booking Details</h4>
            <div id="bookingDetailsContainer">
                <div class="booking-details-section">
                    <div class="row g-3">
                        <div class="mb-3 col-md-3">
                            <label class="form-label">Select Vehicle</label>
                            <select class="form-control" name="vehicleIds[]" required>
                                <option value="">-- None --</option>
                                <c:forEach var="vehicle" items="${vehicles}">
                                    <option value="${vehicle.id}">${vehicle.brand} ${vehicle.model} - Rent: LKR ${vehicle.rentPerKm}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3 col-md-3">
                            <label class="form-label">Select Driver (Optional)</label>
                            <select class="form-control" name="driverIds[]">
                                <option value="">-- None --</option>
                                <c:forEach var="driver" items="${drivers}">
                                    <option value="${driver.id}">${driver.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3 col-md-3">
                            <label class="form-label">Before Meter Reading</label>
                            <input type="number" step="0.01" class="form-control" name="beforeMeter[]" required>
                        </div>
                        <div class="mb-3 col-md-3">
                            <label class="form-label">After Meter Reading</label>
                            <input type="number" step="0.01" class="form-control" name="afterMeter[]" required>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Hidden template for booking details -->
            <div id="bookingDetailsTemplate" style="display: none;">
                <div class="booking-details-section">
                    <div class="row g-3">
                        <div class="mb-3 col-md-3">
                            <label class="form-label">Select Vehicle</label>
                            <select class="form-control" name="vehicleIds[]" required>
                                <option value="">-- None --</option>
                                <c:forEach var="vehicle" items="${adminVehicleList}">
                                    <option value="${vehicle.id}">${vehicle.brand} ${vehicle.model} - Rent: LKR ${vehicle.rentPerKm}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-3 col-md-3">
                            <label class="form-label">Select Driver (Optional)</label>
                            <select class="form-control" name="driverIds[]">
                                <option value="">-- None --</option>
                                <c:forEach var="driver" items="${adminDriverList}">
                                    <option value="${driver.id}">${driver.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3 col-md-3">
                            <label class="form-label">Before Meter Reading</label>
                            <input type="number" step="0.01" class="form-control" name="beforeMeter[]" required>
                        </div>
                        <div class="mb-3 col-md-3">
                            <label class="form-label">After Meter Reading</label>
                            <input type="number" step="0.01" class="form-control" name="afterMeter[]" required>
                        </div>
                    </div>
                </div>
            </div>

            <div class="mb-3">
                <button type="button" class="btn btn-secondary" id="addDetailBtn">Add Another Booking Detail</button>
            </div>
            <button type="submit" class="btn btn-primary" id="submitBtn">Save Booking</button>
            <button type="button" class="btn btn-secondary" id="resetBtn">Reset</button>
        </form>

        <!-- Booking Table -->
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Booking ID</th>
                <th>Customer</th>
                <th>Booking Date</th>
                <th>Pickup Date</th>
                <th>Return Date</th>
                <th>Status</th>
                <th>Advance Amount</th>
                <th>Payment Date</th>
                <th>Payment Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="booking" items="${bookings}">
                <tr class="align-middle"
                    data-bookingid="${booking.bookingId}"
                    data-customerid="${booking.customerId}"
                    data-bookingdate="${booking.bookingDate}"
                    data-pickupdate="${booking.pickupDate}"
                    data-returndate="${booking.returnDate}"
                    data-status="${booking.status}"
                    data-paymentid="${booking.paymentId}"
                    data-advanceamount="${booking.advanceAmount}"
                    data-totalamount="${booking.totalAmount}"
                    data-paymentdate="${booking.paymentDate}"
                    data-paymentstatus="${booking.paymentStatus}"
                    data-bookingdetailsjson='<c:out value="${booking.bookingDetailsJson}" escapeXml="true"/>'>
                    <td>${booking.bookingId}</td>
                    <td>${booking.customerFirstName} ${booking.customerLastName}</td>
                    <td>${booking.bookingDate}</td>
                    <td>${booking.pickupDate}</td>
                    <td>${booking.returnDate}</td>
                    <td>${booking.status}</td>
                    <td>${booking.advanceAmount}</td>
                    <td>${booking.paymentDate}</td>
                    <td>${booking.paymentStatus}</td>
                    <td>
                        <form action="booking" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="bookingId" value="${booking.bookingId}">
                            <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this booking?')">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- jQuery and Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function(){
        // Populate form with booking data on row click for update
        $("table tbody").on("click", "tr", function(e) {
            if(!$(e.target).closest("form").length) {
                var row = $(this);
                $("#bookingId").val(row.data("bookingid"));
                $("#customerId").val(row.data("customerid"));
                $("#bookingDate").val(row.data("bookingdate"));
                $("#pickupDate").val(row.data("pickupdate"));
                $("#returnDate").val(row.data("returndate"));
                $("#status").val(row.data("status"));
                $("#paymentId").val(row.data("paymentid"));
                $("#advanceAmount").val(row.data("advanceamount"));
                $("#totalAmount").val(row.data("totalamount"));
                $("#paymentDate").val(row.data("paymentdate"));
                $("#paymentStatus").val(row.data("paymentstatus"));

                // Set form to update mode
                $("#action").val("update");
                $("#submitBtn").text("Update Booking");

                // Get the JSON object (it should already be an object thanks to escaping/setting properly)
                var bookingDetailsData = row.data("bookingdetailsjson");
                console.log("Raw bookingDetails:", bookingDetailsData);
                console.log("Type of bookingDetails:", typeof bookingDetailsData);

                // Clear the container first
                $("#bookingDetailsContainer").empty();

                // Check that bookingDetails exists and is an array
                if (bookingDetailsData && Array.isArray(bookingDetailsData.bookingDetails)) {
                    // Iterate over each booking detail in the array
                    bookingDetailsData.bookingDetails.forEach(function(detail) {
                        // Clone the hidden template (removing display:none style automatically)
                        var $section = $("#bookingDetailsTemplate .booking-details-section").clone();

                        // Fill in the data for each booking detail
                        $section.find("select[name='vehicleIds[]']").val(detail.vehicleId);
                        $section.find("select[name='driverIds[]']").val(detail.driverId);
                        $section.find("input[name='beforeMeter[]']").val(detail.beforeMeeterReading);
                        $section.find("input[name='afterMeter[]']").val(detail.afterMeeterReading);

                        // Append the section to the container
                        $("#bookingDetailsContainer").append($section);
                    });
                } else {
                    console.error("bookingDetails is not an array");
                    // Optionally, append one empty template if no booking details exist
                    var $emptySection = $("#bookingDetailsTemplate .booking-details-section").clone();
                    $("#bookingDetailsContainer").append($emptySection);
                }
            }
        });

        // Reset form
        $("#resetBtn").click(function(){
            $("form")[0].reset();
            $("#bookingId, #paymentId, #action").val("");
            $("#submitBtn").text("Save Booking");
        });

        // Clone booking details section
        $("#addDetailBtn").click(function(){
            var newDetail = $(".booking-details-section").first().clone();
            newDetail.find("select, input").each(function(){
                $(this).val("");
            });
            $("#bookingDetailsContainer").append(newDetail);
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>



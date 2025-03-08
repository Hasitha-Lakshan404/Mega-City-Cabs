<%--
  Created by IntelliJ IDEA.
  User: hlaks
  Date: 2/23/2025
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.icbt.car_rental.model.Customer" %>
<html>
<head>
    <title>Customer Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .main-content {
            margin-left: 220px;
            padding: 20px;;
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
    </style>
</head>
<body>
<!-- Sidebar -->
<div class="sidebar">
    <h4>Car Reservation</h4>
    <%--seperate line--%>
    <hr>
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link active" href="customer">Customer Management</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="vehicle">Vehicle Management</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="carInventoryManagement.jsp">Car Inventory Management</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="orderManagement.jsp">Order Management</a>
        </li>
    </ul>
</div>

<!-- Main Content -->
<div class="main-content">
    <div class="container mt-2">
        <h1>Customer Management</h1>

        <!-- Customer Form -->
        <form action="customer" method="post" class="mb-4 mt-5">
            <input type="hidden" name="action" id="action" value="create">
            <input type="hidden" name="id" id="id" value="">

            <section class="row g-3">
                <div class="mb-3 col-md-4">
                    <label for="firstName" class="form-label">First Name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="lastName" class="form-label">Last Name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="address" class="form-label">Address</label>
                    <input type="text" class="form-control" id="address" name="address" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="contactNo" class="form-label">Contact No</label>
                    <input type="text" class="form-control" id="contactNo" name="contactNo" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="nic" class="form-label">NIC</label>
                    <input type="text" class="form-control" id="nic" name="nic" required>
                </div>
            </section>

            <button type="submit" class="btn btn-primary" id="submitBtn">Save</button>
            <button type="button" class="btn btn-secondary" id="resetBtn">Reset</button>
        </form>

        <!-- Customer Table -->
        <table class="table table-striped table-hover">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="customer" items="${customers}">
                <tr class="align-middle"
                    data-id="${customer.id}"
                    data-firstname="${customer.firstName}"
                    data-lastname="${customer.lastName}"
                    data-email="${customer.email}"
                    data-password="${customer.password}"
                    data-address="${customer.address}"
                    data-contactno="${customer.contactNo}">
                    <td>${customer.id}</td>
                    <td>${customer.firstName} ${customer.lastName}</td>
                    <td>${customer.email}</td>
                    <td>${customer.contactNo}</td>
                    <td>
                        <form action="customer" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${customer.id}">
                            <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?')">
                                Delete
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {
        $("table tbody").on("click", "tr", function (e) {
            if (!$(e.target).closest("form").length) { // Exclude delete button clicks
                let row = $(this);
                $("#id").val(row.data("id"));
                $("#firstName").val(row.data("firstname"));
                $("#lastName").val(row.data("lastname"));
                $("#email").val(row.data("email"));
                $("#password").val(row.data("password"));
                $("#address").val(row.data("address"));
                $("#contactNo").val(row.data("contactno"));

                // Set to update mode
                $("#action").val("update");
                $("#submitBtn").text("Update");
                $("#password").prop("readonly", true);
            }
        });

        // Reset form
        $("#resetBtn").click(function () {
            $("form")[0].reset();
            $("#id, #action").val("");
            $("#submitBtn").text("Save");
            $("#password").prop("readonly", false);
        });
    });
</script>
</body>
</html>

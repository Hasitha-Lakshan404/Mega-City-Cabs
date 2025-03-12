<%--
  Created by IntelliJ IDEA.
  User: hlaks
  Date: 3/6/2025
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Vehicle Management</title>
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
            <a class="nav-link" href="customer">Customer Management</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="vehicle">Vehicle Management</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="adminBooking">Booking Management</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="orderManagement.jsp">Order Management</a>
        </li>
    </ul>
</div>

<!-- Main Content -->
<div class="main-content">
    <div class="container mt-2">
        <h1>Vehicle Management</h1>

        <!-- Vehicle Form -->
        <form action="vehicle" method="post" class="mb-4 mt-5">
            <input type="hidden" name="action" id="action" value="create">
            <input type="hidden" name="id" id="id" value="">

            <section class="row g-3">
                <div class="mb-3 col-md-4">
                    <label for="brand" class="form-label">Brand</label>
                    <input type="text" class="form-control" id="brand" name="brand" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="model" class="form-label">Model</label>
                    <input type="text" class="form-control" id="model" name="model" required>
                </div>

                <div class="mb-3 col-md-4">
                    <label for="variant" class="form-label">Variant</label>
                    <select class="form-select" id="variant" name="variant" required>
                        <option value="" disabled selected></option>
                        <option value="Standard">Standard</option>
                        <option value="luxury">luxury</option>
                        <option value="Classic">Classic</option>
                        <option value="Sport">Sport</option>
                    </select>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="year" class="form-label">Year</label>
                    <input type="number" class="form-control" id="year" name="year" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="fuelType" class="form-label">Fuel Type</label>
                    <select class="form-select" id="fuelType" name="fuelType" required>
                        <option value="" disabled selected></option>
                        <option value="Hybrid">Hybrid</option>
                        <option value="Petrol">Petrol</option>
                        <option value="Diesel">Diesel</option>
                        <option value="Electric">Electric</option>
                    </select>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="seatingCapacity" class="form-label">Seating Capacity</label>
                    <input type="text" class="form-control" id="seatingCapacity" name="seatingCapacity" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="rentPerKm" class="form-label">Rent Per Km</label>
                    <input type="text" class="form-control" id="rentPerKm" name="rentPerKm" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="currentMeeterReading" class="form-label">Current Meeter Reading</label>
                    <input type="text" class="form-control" id="currentMeeterReading" name="currentMeeterReading" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="status" class="form-label">Status</label>
                    <select class="form-select" id="status" name="status" required>
                        <option value="" disabled selected></option>
                        <option value="Available">Available</option>
                        <option value="Under Maintenance">Under Maintenance</option>
                        <option value="Reserved">Reserved</option>
                    </select>
                </div>


            </section>

            <button type="submit" class="btn btn-primary" id="submitBtn">Save</button>
            <button type="button" class="btn btn-secondary" id="resetBtn">Reset</button>
        </form>

        <!-- Vehicle Table -->
        <table class="table table-striped table-hover">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Variant</th>
                <th>Year</th>
                <th>Fuel Type</th>
                <th>Seating Capacity</th>
                <th>Rent per Km</th>
                <th>Meeter Reading</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="vehicle" items="${vehicles}">
                <tr class="align-middle"
                    data-id="${vehicle.id}"
                    data-brand="${vehicle.brand}"
                    data-model="${vehicle.model}"
                    data-variant="${vehicle.variant}"
                    data-year="${vehicle.year}"
                    data-fuelType="${vehicle.fuelType}"
                    data-seatingCapacity="${vehicle.seatingCapacity}"
                    data-rentPerKm="${vehicle.rentPerKm}"
                    data-currentMeeterReading="${vehicle.currentMeeterReading}"
                    data-status="${vehicle.status}">
                    <td>${vehicle.id}</td>
                    <td>${vehicle.brand}</td>
                    <td>${vehicle.model}</td>
                    <td>${vehicle.variant}</td>
                    <td>${vehicle.year}</td>
                    <td>${vehicle.fuelType}</td>
                    <td>${vehicle.seatingCapacity}</td>
                    <td>${vehicle.rentPerKm}</td>
                    <td>${vehicle.currentMeeterReading}</td>
                    <td>${vehicle.status}</td>
                    <td>
                        <form action="vehicle" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${vehicle.id}">
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
            if (!$(e.target).closest("form").length) {
                let row = $(this);
                $("#id").val(row.data("id"));
                $("#brand").val(row.data("brand"));
                $("#model").val(row.data("model"));
                $("#variant").val(row.data("variant"));
                $("#year").val(row.data("year"));
                $("#fuelType").val(row.data("fueltype"));
                $("#seatingCapacity").val(row.data("seatingcapacity"));
                $("#rentPerKm").val(row.data("rentperkm"));
                $("#currentMeeterReading").val(row.data("currentmeeterreading"));
                $("#status").val(row.data("status"));

                // Set to update mode
                $("#action").val("update");
                $("#submitBtn").text("Update");
            }
        });

        // Reset form
        $("#resetBtn").click(function () {
            $("form")[0].reset();
            $("#id, #action").val("");
            $("#submitBtn").text("Save");
        });
    });
</script>
</body>
</html>


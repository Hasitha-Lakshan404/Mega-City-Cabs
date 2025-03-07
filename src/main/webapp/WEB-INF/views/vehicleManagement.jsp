<%--
  Created by IntelliJ IDEA.
  User: hlaks
  Date: 3/6/2025
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.icbt.car_rental.model.Vehicle" %>
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
                    <input type="text" class="form-control" id="variant" name="variant" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="year" class="form-label">Year</label>
                    <input type="number" class="form-control" id="year" name="year" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="fuelType" class="form-label">Fuel Type</label>
                    <input type="text" class="form-control" id="fuelType" name="fuelType" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="seatingCapacity" class="form-label">Seating Capacity</label>
                    <input type="text" class="form-control" id="seatingCapacity" name="seatingCapacity" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="rentPerDay" class="form-label">Rent Per Day</label>
                    <input type="text" class="form-control" id="rentPerDay" name="rentPerDay" required>
                </div>
                <div class="mb-3 col-md-4">
                    <label for="status" class="form-label">Status</label>
                    <input type="text" class="form-control" id="status" name="status" required>
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
                <th>year</th>
                <th>fuelType</th>
                <th>Seating Capacity</th>
                <th>Rent per Day</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
                if (vehicles != null) {
                    for (Vehicle vehicle : vehicles) {
            %>
            <tr class="align-middle"
                data-id="<%= vehicle.getId() %>"
                data-brand="<%= vehicle.getBrand() %>"
                data-model="<%= vehicle.getModel() %>"
                data-variant="<%= vehicle.getVariant() %>"
                data-year="<%= vehicle.getYear() %>"
                data-fuelType="<%= vehicle.getFuelType() %>"
                data-seatingCapacity="<%= vehicle.getSeatingCapacity() %>"
                data-rentPerDay="<%= vehicle.getRentPerDay() %>"
                data-status="<%= vehicle.getStatus() %>">
                <td><%= vehicle.getId() %></td>
                <td><%= vehicle.getBrand() %></td>
                <td><%= vehicle.getModel() %></td>
                <td><%= vehicle.getVariant() %></td>
                <td><%= vehicle.getYear() %></td>
                <td><%= vehicle.getFuelType() %></td>
                <td><%= vehicle.getSeatingCapacity() %></td>
                <td><%= vehicle.getRentPerDay() %></td>
                <td><%= vehicle.getStatus() %></td>

                <td>
                    <form action="vehicle" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%= vehicle.getId() %>">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?')">
                            Delete
                        </button>
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
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
                $("#rentPerDay").val(row.data("rentperday"));
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


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Book a Car</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <style>
    .booking-section {
      margin-bottom: 20px;
      padding: 15px;
      border: 1px solid #ccc;
      border-radius: 5px;
      background-color: #f9f9f9;
    }
    .section-header {
      margin-bottom: 10px;
      font-weight: bold;
    }
    /* Style disabled options with a light red background */
    option:disabled {
      background-color: #f8d7da;
      color: #721c24;
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <h2 class="mb-4">Book Your Vehicle</h2>
  <form action="booking" method="post">
    <input type="hidden" name="customerId" value="${param.customerId}" />
    <div class="form-group d-flex justify-content-start">
      <div class="mr-2">
        <label for="pickupDate">Pickup Date:</label>
        <input type="date" class="form-control" name="pickupDate" id="pickupDate" required>
      </div>
      <div>
        <label for="returnDate">Return Date:</label>
        <input type="date" class="form-control" name="returnDate" id="returnDate" required>
      </div>
    </div>
    <hr>
    <!-- Booking Details: Vehicle selection and driver selection -->
    <div id="bookingDetailsContainer">
      <div class="booking-section">
        <div class="section-header">Vehicle Booking Details</div>
        <div class="form-row">
          <div class="form-group col-md-6">
            <label for="vehicle">Select Vehicle:</label>
            <select class="form-control" name="vehicleIds[]" id="vehicle">
              <option value="">-- None --</option>
              <c:forEach var="vehicle" items="${vehicles}">
                <option value="${vehicle.id}">
                    ${vehicle.brand} ${vehicle.model}  - 1Km LKR ${vehicle.rentPerKm}/=
                </option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group col-md-6">
            <label for="driverId">Select Driver (Optional):</label>
            <select class="form-control" name="driverIds[]" id="driverId">
              <option value="">-- None --</option>
              <c:forEach var="driver" items="${drivers}">
                <option value="${driver.id}">${driver.name}</option>
              </c:forEach>
            </select>
          </div>
        </div>
      </div>
    </div>

    <div class="form-group d-flex justify-content-start">
      <div class="mr-2">
        <label for="advanceAmount" class="form-label">Advanced Amount</label>
        <input type="number" class="form-control" id="advanceAmount" name="advanceAmount" required>
      </div>
    </div>

    <div class="form-group d-flex justify-content-start">
      <button type="button" class="btn btn-secondary mr-2" id="addVehicleBtn">Add Another Vehicle</button>
      <button type="submit" class="btn btn-primary">Book Now</button>
    </div>
  </form>
</div>

<script>
  function updateVehicleDropdowns() {
    const vehicleSelects = document.querySelectorAll('select[name="vehicleIds[]"]');
    let selectedValues = Array.from(vehicleSelects)
            .map(select => select.value)
            .filter(value => value !== '');

    vehicleSelects.forEach(select => {
      const currentValue = select.value;
      Array.from(select.options).forEach(option => {
        option.disabled = false;
        if (option.value !== currentValue && selectedValues.includes(option.value)) {
          option.disabled = true;
        }
      });
    });
  }

  function updateDriverDropdowns() {
    const driverSelects = document.querySelectorAll('select[name="driverIds[]"]');
    let selectedValues = Array.from(driverSelects)
            .map(select => select.value)
            .filter(value => value !== '');

    driverSelects.forEach(select => {
      const currentValue = select.value;
      Array.from(select.options).forEach(option => {
        if (option.value === "") {
          option.disabled = false;
          return;
        }
        option.disabled = false;
        if (option.value !== currentValue && selectedValues.includes(option.value)) {
          option.disabled = true;
        }
      });
    });
  }

  document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('select[name="vehicleIds[]"]').forEach(select => {
      select.addEventListener('change', updateVehicleDropdowns);
    });
    document.querySelectorAll('select[name="driverIds[]"]').forEach(select => {
      select.addEventListener('change', updateDriverDropdowns);
    });

    document.getElementById('addVehicleBtn').addEventListener('click', function() {
      const container = document.getElementById('bookingDetailsContainer');
      const firstSection = document.querySelector('.booking-section');
      const newSection = firstSection.cloneNode(true);

      newSection.querySelectorAll('select, input').forEach(function(input) {
        if (input.tagName.toLowerCase() === 'select') {
          input.selectedIndex = 0; // Reset selection
          Array.from(input.options).forEach(option => {
            option.disabled = false;  // Reset disabled state
          });
          if (input.name === "vehicleIds[]") {
            input.addEventListener('change', updateVehicleDropdowns);
          }
          if (input.name === "driverIds[]") {
            input.addEventListener('change', updateDriverDropdowns);
          }
        } else if (input.tagName.toLowerCase() === 'input') {
          input.value = '';
        }
      });

      container.appendChild(newSection);
      updateVehicleDropdowns();
      updateDriverDropdowns();
    });
  });
</script>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>




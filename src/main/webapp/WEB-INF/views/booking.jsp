<%--
  Created by IntelliJ IDEA.
  User: hlaks
  Date: 3/9/2025
  Time: 2:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  </style>
</head>
<body>
<div class="container mt-5">
  <h2 class="mb-4">Book Your Car</h2>
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
    <!-- The booking_date and customer_id can be set in the backend -->

    <hr>

    <!-- Booking Details: Vehicle selection and meter readings -->
    <div id="bookingDetailsContainer">
      <div class="booking-section">
        <div class="section-header">Vehicle Booking Details</div>
        <div class="form-row">
          <div class="form-group col-md-6">
            <label for="vehicle">Select Car:</label>
            <select class="form-control" name="vehicleIds[]" id="vehicle">
              <c:forEach var="vehicle" items="${vehicles}">
                <option value="${vehicle.id}">
                    ${vehicle.brand} ${vehicle.model}
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
      <button type="button" class="btn btn-secondary mr-2" id="addVehicleBtn">Add Another Vehicle</button>
      <button type="submit" class="btn btn-primary">Book Now</button>
    </div>
  </form>
</div>

<!-- JavaScript for dynamic form addition -->
&lt;%&ndash;<script>
  let bookingDetailIndex = 1;
  document.getElementById('addVehicleBtn').addEventListener('click', function() {
    const container = document.getElementById('bookingDetailsContainer');
    const firstSection = document.querySelector('.booking-section');
    const newSection = firstSection.cloneNode(true);

    // Update element IDs and clear any previous values
    newSection.querySelectorAll('select, input').forEach(function(input) {
      if(input.id) {
        const newId = input.id.replace(/\d+$/, '') + bookingDetailIndex;
        input.id = newId;
      }
      if(input.tagName.toLowerCase() === 'input') {
        input.value = '';
      } else if(input.tagName.toLowerCase() === 'select') {
        input.selectedIndex = 0;
      }
    });

    container.appendChild(newSection);
    bookingDetailIndex++;
  });
</script>&ndash;%&gt;
<script>
  // Function to update the disabled state of vehicle dropdown options
  function updateVehicleDropdowns() {
    const vehicleSelects = document.querySelectorAll('select[name="vehicleIds[]"]');
    // Gather selected vehicle values from all dropdowns (ignoring empty selections)
    let selectedValues = Array.from(vehicleSelects)
            .map(select => select.value)
            .filter(value => value !== '');

    // Update each dropdown's options based on the selected values
    vehicleSelects.forEach(select => {
      const currentValue = select.value;
      Array.from(select.options).forEach(option => {
        // Re-enable option first
        option.disabled = false;
        // Disable option if it's selected in another dropdown
        if (option.value !== currentValue && selectedValues.includes(option.value)) {
          option.disabled = true;
        }
      });
    });
  }

  // When the document is ready, attach event listeners
  document.addEventListener('DOMContentLoaded', () => {
    // Attach the change event to each existing vehicle dropdown
    document.querySelectorAll('select[name="vehicleIds[]"]').forEach(select => {
      select.addEventListener('change', updateVehicleDropdowns);
    });

    // Handle the 'Add Another Vehicle' button click event
    document.getElementById('addVehicleBtn').addEventListener('click', function() {
      const container = document.getElementById('bookingDetailsContainer');
      const firstSection = document.querySelector('.booking-section');
      const newSection = firstSection.cloneNode(true);

      // Update element IDs (if needed) and clear any previous selections/values
      newSection.querySelectorAll('select, input').forEach(function(input) {
        if (input.tagName.toLowerCase() === 'select') {
          input.selectedIndex = 0;  // Reset selection
          input.addEventListener('change', updateVehicleDropdowns);  // Attach event listener
        } else if (input.tagName.toLowerCase() === 'input') {
          input.value = '';
        }
      });

      container.appendChild(newSection);
      // Update all dropdowns after adding a new section
      updateVehicleDropdowns();
    });
  });
</script>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>--%>
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

<!-- JavaScript for dynamic form addition and disabling selected options -->
<script>
  // Update vehicle dropdowns so that a selected vehicle in one dropdown is disabled in the others.
  function updateVehicleDropdowns() {
    const vehicleSelects = document.querySelectorAll('select[name="vehicleIds[]"]');
    let selectedValues = Array.from(vehicleSelects)
            .map(select => select.value)
            .filter(value => value !== '');

    vehicleSelects.forEach(select => {
      const currentValue = select.value;
      Array.from(select.options).forEach(option => {
        // Reset disabled state first
        option.disabled = false;
        if (option.value !== currentValue && selectedValues.includes(option.value)) {
          option.disabled = true;
        }
      });
    });
  }

  // Update driver dropdowns so that a selected driver in one dropdown is disabled in the others.
  function updateDriverDropdowns() {
    const driverSelects = document.querySelectorAll('select[name="driverIds[]"]');
    let selectedValues = Array.from(driverSelects)
            .map(select => select.value)
            .filter(value => value !== '');

    driverSelects.forEach(select => {
      const currentValue = select.value;
      Array.from(select.options).forEach(option => {
        // Do not disable the default "None" option
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
    // Attach event listeners to vehicle and driver dropdowns
    document.querySelectorAll('select[name="vehicleIds[]"]').forEach(select => {
      select.addEventListener('change', updateVehicleDropdowns);
    });
    document.querySelectorAll('select[name="driverIds[]"]').forEach(select => {
      select.addEventListener('change', updateDriverDropdowns);
    });

    // Clone booking section on "Add Another Vehicle" button click
    document.getElementById('addVehicleBtn').addEventListener('click', function() {
      const container = document.getElementById('bookingDetailsContainer');
      const firstSection = document.querySelector('.booking-section');
      const newSection = firstSection.cloneNode(true);

      // Reset values and update event listeners in the cloned section
      newSection.querySelectorAll('select, input').forEach(function(input) {
        if (input.tagName.toLowerCase() === 'select') {
          input.selectedIndex = 0; // Reset selection
          Array.from(input.options).forEach(option => {
            option.disabled = false;  // Reset disabled state
          });
          // Attach appropriate event listeners based on the select name
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
      // Update dropdowns after adding new section
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




<%--
  Created by IntelliJ IDEA.
  User: hlaks
  Date: 2/23/2025
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.icbt.car_rental.model.Customer" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Customers</h1>
<a href="customer?action=edit">Add New Customer</a>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Actions</th>
    </tr>

    <%
        List<Customer> customers = (List<Customer>) request.getAttribute("customers");
        if (customers != null) {
            for (Customer customer : customers) {
    %>
    <tr>
        <td><%= customer.getId() %></td>
        <td><%= customer.getFirstName() %></td>
        <td><%= customer.getEmail() %></td>
        <td><%= customer.getContactNo() %></td>
        <td>
            <a href="customer?action=edit&id=<%= customer.getId() %>">Edit</a>
            <form action="customer" method="post" style="display:inline">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%= customer.getId() %>">
                <input type="submit" value="Delete" onclick="return confirm('Are you sure?')">
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>

</table>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: hlaks
  Date: 2/23/2025
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Form</title>
</head>
<body>
<h1>${customer == null ? 'Add Customer' : 'Edit Customer'}</h1>
<form action="customer" method="post">
    <input type="hidden" name="action" value="${customer == null ? 'create' : 'update'}">
    <input type="hidden" name="id" value="${customer.id}">

    <div>
        <label>first Name:</label>
        <input type="text" name="firstName" value="${customer.firstName}" required>
    </div>
    <div>
        <label>last Name:</label>
        <input type="text" name="lastName" value="${customer.lastName}" required>
    </div>
    <div>
        <label>Email:</label>
        <input type="email" name="email" value="${customer.email}" required>
    </div>
    <div>
        <label>Password:</label>
        <input type="password" name="password" value="${customer.password}" required>
    </div>
    <div>
        <label>Address:</label>
        <input type="text" name="address" value="${customer.address}" required>
    </div>
    <div>
        <label>Contact No:</label>
        <input type="text" name="contactNo" value="${customer.contactNo}" required>
    </div>
    <div>
        <input type="submit" value="Save">
        <a href="customer">Cancel</a>
    </div>
</form>
</body>
</html>

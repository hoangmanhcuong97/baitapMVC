<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 25/8/2021
  Time: 9:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Customer</title>
</head>
<body>
<h1>Customer details</h1>
<p>
    <a href="/customers">Back to customer list</a>
</p>
<table>
    <tr>
        <td>Name: </td>
        <td>${requestScope["customer3"].getName()}</td>
    </tr>
    <tr>
        <td>Email: </td>
        <td>${requestScope["customer3"].getEmail()}</td>
    </tr>
    <tr>
        <td>Address: </td>
        <td>${requestScope["customer3"].getAddress()}</td>
    </tr>
</table>
</body>
</html>

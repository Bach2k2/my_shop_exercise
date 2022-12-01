<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: letro
  Date: 11/30/2022
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product list</title>
</head>
<body>

<table>
    <tr>
        <th>Product id</th>
        <th>Name</th>
        <th>Category id</th>
        <th>Image</th>
        <th>Price</th>
    </tr>   
    <c:forEach items="${products}" var="product">
        <tr>
            <td><c:out value="${product.pId}"></c:out></td>
            <td><c:out value="${product.name}"></c:out></td>
            <td><c:out value="${product.cId}"></c:out></td>
            <td><c:out value="${product.image}"></c:out></td>
            <td><c:out value="${product.price}"></c:out></td>
        </tr>
    </c:forEach>>
</table>
</body>
</html>

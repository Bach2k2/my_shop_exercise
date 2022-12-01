<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: letro
  Date: 12/1/2022
  Time: 8:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a product</title>
</head>
<body>
<h1>Add a product</h1>
<form action="/home" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="add">
    <table>
        <tr>
            <th>ID</th>
            <td><input type="text" name="pId"/></td>
        </tr>
        <tr>
            <th>Product name</th>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <th>Price</th>
            <td><input type="text" name="price"/></td>
        </tr>
        <tr>
            <th>Upload image</th>
            <td><input type="file" name="image"/></td>
        </tr>
        <tr>
            <th>Category</th>
            <td><select name="cId">
                <c:forEach items="${cList}" var="c">
                    <option value="${c.cId}">${c.cName}</option>
                </c:forEach>
            </select></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><textarea row="4" name="description"></textarea></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add product"></td>
        </tr>
    </table>
</form>

</body>
</html>
